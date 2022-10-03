package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Update.UpdateCommunityPostDto;
import com.ssafy.trippy.Service.CommunityPostService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityPostController {

    private final CommunityPostService communityPostService;

    private final MemberService memberService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";

    @PostMapping("/auth/community")
    public ResponseEntity<?> saveCommunityPost(HttpServletRequest request, @RequestBody @Valid RequestCommunityPostDto requestCommunityPostDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestCommunityPostDto.setMember_id(memberId);
        try {
            Long id = communityPostService.saveCommunityPost(requestCommunityPostDto);
            return new ResponseEntity<>(id, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/auth/community/{community_post_id}")
    public ResponseEntity<?> deleteCommunityPost(@PathVariable("community_post_id") Long community_post_id) {
        try {
            communityPostService.deleteCommunityPost(community_post_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/auth/community/{community_post_id}")
    public ResponseEntity<?> updateCommunityPost(HttpServletRequest request, @PathVariable("community_post_id") Long community_post_id, @RequestBody @Valid UpdateCommunityPostDto updateCommunityPostDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        updateCommunityPostDto.setMemberId(memberId);
        try {
            communityPostService.updateCommunityPost(community_post_id, updateCommunityPostDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/community")
    public ResponseEntity<?> getAllCommunityPostList() {
        try {
            List<ResponseCommunityPostDto> responsePostDtos = communityPostService.getAllCommunityPost();
            return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("게시글이 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/community/{community_post_id}")
    public ResponseEntity<?> detailCommunityPost(@PathVariable("community_post_id") Long id) {
        try {
            ResponseCommunityPostDto responseCommunityPostDto = communityPostService.findCommunityPost(id);
            return new ResponseEntity<>(responseCommunityPostDto, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }
}
