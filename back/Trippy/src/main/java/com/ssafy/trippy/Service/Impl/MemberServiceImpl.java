package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestLoginDto;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseLoginDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Service.MemberService;
import com.ssafy.trippy.Service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final S3Uploader s3Uploader;
    @Override
    public ResponseMemberDto signup(RequestMemberDto requestMemberDto) {
        if(chkDuplicate(requestMemberDto.getEmail())){
            return new ResponseMemberDto(Member.builder().build());
        }
        String rawPassword = requestMemberDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        requestMemberDto.encodePassword(encodedPassword);
        return new ResponseMemberDto(memberRepository.save(requestMemberDto.toEntity()));
    }

    @Override
    public ResponseLoginDto login(String email, String password) {
        Member member = memberRepository.findByEmail(email).get();
        if(member == null){
            throw new IllegalArgumentException("email을 확인하세요.");
        }
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new IllegalArgumentException("password를 확인하세요.");
        }
        String accessToken = jwtProvider.createAccessToken(member.getUsername(), member.getRoles());
        String refreshToken = jwtProvider.createRefreshToken(member.getUsername(), member.getRoles());
        return new ResponseLoginDto(accessToken, refreshToken);
    }

    @Override
    public ResponseLoginDto reIssueAccessToken(String email, String refreshToken) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        jwtProvider.checkRefreshToken(email, refreshToken);
        String accessToken = jwtProvider.createAccessToken(member.getEmail(), member.getRoles());
        return new ResponseLoginDto(accessToken, refreshToken);
    }

    @Override
    public boolean chkDuplicate(String email){
        return memberRepository.existsByEmail(email);
    }

    @Override
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    @Override
    public void updateMember(Long id, UpdateMemberDto updateMemberDto){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        member.update(updateMemberDto);
        memberRepository.save(member);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseMemberDto selectMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        ResponseMemberDto responseMemberDto = new ResponseMemberDto(member);
        if(member.getImg_path()!=null)
            responseMemberDto.setImg_link(s3Uploader.getS3(member.getImg_path()));
        return responseMemberDto;
    }

    @Override
    public void logout(Long id, String accessToken) {
        Member member = memberRepository.findById(id).get();
        jwtProvider.logout(member.getEmail(), accessToken);
    }

    @Override
    public void changePw(RequestLoginDto requestLoginDto) {
        Member member = memberRepository.findByEmail(requestLoginDto.getEmail()).get();
        String rawPassword = requestLoginDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        member.updatePw(encodedPassword);
        memberRepository.save(member);
    }

    @Override
    public Long getIdByToken(String token){
        String email = jwtProvider.getUserPk(token);
        System.out.println(email);
        Long id = memberRepository.findByEmail(email).orElseThrow().getId();
        return id;
    }

}
