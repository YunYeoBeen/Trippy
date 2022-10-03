package com.ssafy.trippy.Dto.Response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Domain.Follow;
import com.ssafy.trippy.Domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseFollowDto {
    private Long id;

    @JsonProperty("following_id")
    private Long followingId;

    @JsonProperty("follower_id")
    private Long followerId;

    public ResponseFollowDto(Follow follow){
        this.id = follow.getId();
        this.followingId = follow.getFollowing().getId();
        this.followerId = follow.getFollower().getId();
    }
}
