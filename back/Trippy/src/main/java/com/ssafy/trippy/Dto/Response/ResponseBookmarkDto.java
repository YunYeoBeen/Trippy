package com.ssafy.trippy.Dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Domain.Bookmark;
import com.ssafy.trippy.Domain.CommunityPost;
import com.ssafy.trippy.Domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBookmarkDto
{
    private Long id;
    @JsonProperty("community_post_id")
    private Long communityPostId;
    @JsonProperty("member_id")
    private Long memberId;

    public ResponseBookmarkDto(Bookmark bookmark) {
        this.id = bookmark.getId();
        this.memberId = bookmark.getMember().getId();
        this.communityPostId = bookmark.getCommunityPost().getId();
    }
}
