package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.PostComment;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePostCommentDto implements Serializable {

    private Long id;
    private String content;
    private Long postId;
    private Long memberId;
    private Long parentId;

    private List<PostComment> children = new ArrayList<>();

    @Builder
    public ResponsePostCommentDto(PostComment postComment) {
        this.content = postComment.getContent();
        this.postId = postComment.getPost().getId();
        this.parentId = postComment.getParent()== null ? 0 : postComment.getParent().getId();
        this.memberId = postComment.getMember().getId();
    }

    public ResponsePostCommentDto(Long id, String content, Long memberId) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
    }


    public static ResponsePostCommentDto convertCommentToDto(PostComment postComment){
        return new ResponsePostCommentDto(postComment.getId(), postComment.getContent(), postComment.getMember().getId());

    }

}
