package com.ssafy.trippy.Config;

import com.fasterxml.jackson.databind.JsonNode;
import com.ssafy.trippy.Config.Redis.RedisService;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Repository.MemberRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.NotActiveException;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("spring.jwt.secret")
    private String secretKey;

    // 토큰 유효시간 60분
    private Long tokenValidMillisecond = 60 * 60 * 1000L;

    // 리프레시 토큰 1주
    private long RefreshtokenValidMillisecond = 60 * 60 * 24 * 7 * 1000L;

    private final UserDetailsService userDetailService;
    private final RedisService redisService;
    @Value("${spring.jwt.blacklist.access-token}")
    private String blackListATPrefix;
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userId, List<String> roles, Long tokenInvalidTime) {
        Claims claims = Jwts.claims().setSubject(userId); // claims 생성 및 payload 설정
        claims.put("roles", roles); // 권한 설정, key/ value 쌍으로 저장
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims) // 발행 유저 정보 저장
                .setIssuedAt(date) // 발행 시간 저장
                .setExpiration(new Date(date.getTime() + tokenInvalidTime)) // 토큰 유효 시간 저장
                .signWith(SignatureAlgorithm.HS256, secretKey) // 해싱 알고리즘 및 키 설정
                .compact(); // 생성
    }

    public String createAccessToken(String userId, List<String> roles){
        return createToken(userId, roles, tokenValidMillisecond);
    }

    public String createRefreshToken(String userId, List<String> roles) {
        String refreshToken = this.createToken(userId, roles, RefreshtokenValidMillisecond);
        redisService.setValues(userId, refreshToken, Duration.ofMillis(RefreshtokenValidMillisecond));
        return refreshToken;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validationToken(String token){
        try {
            String expiredAT = redisService.getValues(blackListATPrefix + token);
            if (expiredAT != null) {
                throw new ExpiredJwtException(null, null, null);
            }
            Jws<Claims> claimsJwts = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJwts.getBody().getExpiration().before(new Date()); // 만료 날짜가 현재 시간보다 이전이면 false
        }catch (Exception e){
            return false;
        }
    }

    public void checkRefreshToken(String userId, String refreshToken) {
        String redisRT = redisService.getValues(userId);
        if (!refreshToken.equals(redisRT)) {
            throw new IllegalStateException("토큰이 만료되었습니다.");
        }
    }

    public void logout(String userId, String accessToken) {
        long expiredAccessTokenTime = getExpiredTime(accessToken).getTime() - new Date().getTime();
        redisService.setValues(blackListATPrefix + accessToken, userId, Duration.ofMillis(expiredAccessTokenTime));
        redisService.deleteValues(userId); // Delete RefreshToken In Redis
    }

    private Date getExpiredTime(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
    }
}