package com.ssafy.trippy.Service;


import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Update.UpdatePostDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    List<ResponsePostDto> findAll();
    List<ResponsePostDto> findAllByMember(Member member);

    void savePost(RequestPostDto requestPostDto);

    void deletePost(Long id);

    void updatePost(Long id, RequestPostDto requestPostDto);
    Long savePost(RequestPostDto requestPostDto, List<MultipartFile> images);

    void deletePost(Long id);

    void updatePost(Long id, RequestPostDto requestPostDto, List<MultipartFile> images);

    ResponsePostDto findPostId(Long id);
    List<ResponsePostDto> findByCity(Location location);



}
