package com.ssafy.trippy.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RequestLikePostDto {

    @JsonProperty("post_id")
    private Long postId;
    @JsonProperty("member_id")
    private Long memberId;

    public LikePost toEntity() {
        return LikePost.builder()
                .post(Post.builder().id(postId).build())
                .member(Member.builder().id(memberId).build())
                .build();
    }

}
