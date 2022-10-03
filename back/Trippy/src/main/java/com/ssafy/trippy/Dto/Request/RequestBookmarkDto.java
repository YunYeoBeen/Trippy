package com.ssafy.trippy.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Domain.Bookmark;
import com.ssafy.trippy.Domain.CommunityPost;
import com.ssafy.trippy.Domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBookmarkDto {

    @JsonProperty("community_post_id")
    private Long communityPostId;
    @JsonProperty("member_id")
    private Long memberId;

    public Bookmark toEntity() {
        return Bookmark.builder()
                .communityPost(CommunityPost.builder().id(communityPostId).build())
                .member(Member.builder().id(memberId).build())
                .build();
    }

}
