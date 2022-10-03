package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestBookmarkDto;
import com.ssafy.trippy.Dto.Response.ResponseBookmarkDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Service.BookmarkService;
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
@RequestMapping("/auth/bookmark")
public class BookmarkController {

    private final MemberService memberService;
    private final BookmarkService bookmarkService;
    private static final String SUCCESS = "success";

    @PostMapping("/{community_post_id}")
    public ResponseEntity<?> saveBookmark(HttpServletRequest request, @PathVariable("community_post_id") Long communityPostId){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        RequestBookmarkDto requestBookmarkDto = new RequestBookmarkDto(communityPostId, memberId);
        bookmarkService.saveBookmark(requestBookmarkDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getBookmarks(HttpServletRequest request){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        List<ResponseCommunityPostDto> responseCommunityPostDtos = bookmarkService.getBookmarks(memberId);
        return new ResponseEntity<>(responseCommunityPostDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{community_post_id}")
    public ResponseEntity<?> removeBookmark(HttpServletRequest request, @PathVariable("community_post_id") Long communityPostId){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        RequestBookmarkDto requestBookmarkDto = new RequestBookmarkDto(communityPostId,memberId);
        bookmarkService.deleteBookmark(requestBookmarkDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
