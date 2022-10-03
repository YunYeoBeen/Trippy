package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Service.Impl.PostCommentServiceImpl;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class PostCommentController {

    private final PostCommentServiceImpl postCommentService;

    private final MemberService memberService;
    private final String SUCCESS = "OK";
    private final String FAIL = "ERROR";


    // 댓글 등록
    @PostMapping("/auth/comment")
    public ResponseEntity<?> createComment(HttpServletRequest request, @RequestBody RequestPostCommentDto requestPostCommentDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestPostCommentDto.setMemberId(memberId);
        try {
            postCommentService.createComment(requestPostCommentDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/auth/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        try {
            postCommentService.deleteComment(commentId);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);

    }

    // 게시판에 달린 모든 댓글 조회
    @GetMapping("/comment/{postId}")
    public ResponseEntity<?> getAllPostComment(@PathVariable("postId") Long postId) {
        try {
            List<ResponsePostCommentDto> postCommentList = postCommentService.findPostCommentByPostId(postId);
            return new ResponseEntity<>(postCommentList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);

        }

    }

    @PutMapping("/auth/comment/{commentId}")
        public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId, @RequestBody @Valid RequestPostCommentDto requestPostCommentDto) {
        try {
            postCommentService.updateComment(commentId, requestPostCommentDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

}
