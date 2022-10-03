package com.ssafy.trippy.Service;


import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Entity.Member;

import java.util.List;

public interface PostService {

    List<ResponsePostDto> findAll();
    List<ResponsePostDto> findAllByMember(Member member);

    Long savePost(RequestPostDto requestPostDto);

    void deletePost(RequestPostDto requestPostDto);

    void updatePost(Long id, RequestPostDto requestPostDto);


}
