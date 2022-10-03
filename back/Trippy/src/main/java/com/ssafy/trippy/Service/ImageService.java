package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Response.ResponseImageDto;

import java.util.List;

public interface ImageService {
    List<ResponseImageDto> getImagesByPostId(Long postId);
}
