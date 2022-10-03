package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Request.RequestLikePostDto;
import com.ssafy.trippy.Dto.Response.ResponseLikePostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;

import java.util.List;

public interface LikePostService {

    void saveLikePost(RequestLikePostDto requestLikePostDto);

    List<ResponsePostDto> getLikePosts(Long memberId);

    void deleteLikePost(RequestLikePostDto requestLikePostDto);
}
