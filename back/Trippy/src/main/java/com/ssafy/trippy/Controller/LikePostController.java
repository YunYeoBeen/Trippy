package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestLikePostDto;
import com.ssafy.trippy.Dto.Response.ResponseLikePostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Service.LikePostService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/auth/likepost")
public class LikePostController {

    private final LikePostService likePostService;

    private final MemberService memberService;
    private static final String SUCCESS = "success";

    @PostMapping
    public ResponseEntity<?> saveLikePost(HttpServletRequest request, @RequestBody RequestLikePostDto requestLikePostDto){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestLikePostDto.setMemberId(memberId);
        likePostService.saveLikePost(requestLikePostDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getLikePosts(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        List<ResponsePostDto> responsePostDtos = likePostService.getLikePosts(memberId);
        return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> removeLikePost(HttpServletRequest request, @RequestBody RequestLikePostDto requestLikePostDto){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestLikePostDto.setMemberId(memberId);
        likePostService.deleteLikePost(requestLikePostDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
