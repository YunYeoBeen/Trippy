package com.ssafy.trippy.Service;


import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Update.UpdateCommunityPostDto;
import com.ssafy.trippy.Entity.CommunityPost;
import com.ssafy.trippy.Entity.Member;

import java.util.List;

public interface CommunityPostService {
    Long saveCommunityPost(RequestCommunityPostDto requestCommunityPostDto);
    void deleteCommunityPost(RequestCommunityPostDto requestCommunityPostDto);

    void updateCommunityPost(Long id, UpdateCommunityPostDto updateCommunityPostDto);

    List<ResponseCommunityPostDto> getAllCommunityPost();

}
