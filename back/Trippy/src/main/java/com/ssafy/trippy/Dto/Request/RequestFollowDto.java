package com.ssafy.trippy.Dto.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Domain.Follow;
import com.ssafy.trippy.Domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class RequestFollowDto {
    private Long id;
    @JsonProperty("following_id")
    private Long followingId;
    @JsonProperty("follower_id")
    private Long followerId;

    public Follow toEntity() {
        return Follow.builder()
                .id(id)
                .follower(Member.builder().id(followerId).build())
                .following(Member.builder().id(followingId).build())
                .build();
    }

}
