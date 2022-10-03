package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.Follow;
import com.ssafy.trippy.Dto.Request.RequestFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Service.FollowService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/auth/follow")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class FollowController {
    private final FollowService followService;

    private final MemberService memberService;
    private static final String SUCCESS = "success";

    @PostMapping
    public ResponseEntity<?> follow(HttpServletRequest request, @RequestBody RequestFollowDto requestFollowDto){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestFollowDto.setFollowerId(memberId);
        ResponseFollowDto responseFollowDto = followService.follow(requestFollowDto);
        return new ResponseEntity<>(responseFollowDto, HttpStatus.OK);
    }

    @PostMapping("/undo")
    public ResponseEntity<?> unfollow(HttpServletRequest request,@RequestBody RequestFollowDto requestFollowDto){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestFollowDto.setFollowerId(memberId);
        followService.unfollow(requestFollowDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/follower")
    public ResponseEntity<?> getFollowers(HttpServletRequest request){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        List<ResponseMemberDto> responseMemberDtos = followService.getFollowers(memberId);
        return new ResponseEntity<>(responseMemberDtos, HttpStatus.OK);
    }

    @GetMapping("/following")
    public ResponseEntity<?> getFollowings(HttpServletRequest request){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        List<ResponseMemberDto> responseMemberDtos = followService.getFollowings(memberId);
        return new ResponseEntity<>(responseMemberDtos, HttpStatus.OK);
    }

    @GetMapping("/follower/cnt")
    public ResponseEntity<?> getFollowersCnt(HttpServletRequest request){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        Long responseCnt = followService.getFollowersCnt(memberId);
        return new ResponseEntity<Long>(responseCnt, HttpStatus.OK);
    }

    @GetMapping("/following/cnt")
    public ResponseEntity<?> getFollowingsCnt(HttpServletRequest request){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        Long responseCnt = followService.getFollowingsCnt(memberId);
        return new ResponseEntity<Long>(responseCnt, HttpStatus.OK);
    }
}
