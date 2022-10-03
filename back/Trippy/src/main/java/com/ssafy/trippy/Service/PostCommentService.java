package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;

public interface PostCommentService {
    Long create(ResponsePostCommentDto requestPostCommentDto);

    // 게시물에 해당되는 모든 댓글 가져오기
//    List<ResponsePostCommentDto> getAllPostComments(Post post);
}
