package com.ssafy.trippy.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Domain.LikePost;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseLikePostDto
{
    private Long id;
    @JsonProperty("post_id")
    private Long postId;
    @JsonProperty("member_id")
    private Long memberId;

    public ResponseLikePostDto(LikePost likePost) {
        this.id = likePost.getId();
        this.memberId = likePost.getMember().getId();
        this.postId = likePost.getPost().getId();
    }
}
