package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Request.RequestFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;

import java.util.List;

public interface FollowService {
    ResponseFollowDto follow(RequestFollowDto requestFollowDto);
    void unfollow(RequestFollowDto requestFollowDto);
    List<ResponseMemberDto> getFollowers(Long memberId);
    List<ResponseMemberDto> getFollowings(Long memberId);
    Long getFollowersCnt(Long memberId);
    Long getFollowingsCnt(Long memberId);
}
