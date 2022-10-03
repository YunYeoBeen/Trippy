package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Image;
import com.ssafy.trippy.Dto.Response.ResponseDetailLocationDto;
import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import com.ssafy.trippy.Repository.DetailLocationRepository;
import com.ssafy.trippy.Repository.ImageRespository;
import com.ssafy.trippy.Service.DetailLocationService;
import com.ssafy.trippy.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final DetailLocationRepository detailLocationRepository;

    private final ImageRespository imageRespository;
    @Override
    public List<ResponseImageDto> getImagesByPostId(Long postId) {
        List<DetailLocation> detailLocations = detailLocationRepository.findAllByPostId(postId).orElseThrow(() -> new IllegalArgumentException("" +
                "post->detaillocaiton 불러오기 에러"));
        List<ResponseImageDto> images = new ArrayList<>();
        for (DetailLocation detailLocation: detailLocations){
            List<Image> addimages = imageRespository.findAllByDetailLocationId(detailLocation.getId());
            for (Image image:addimages){
                images.add(new ResponseImageDto(image));
            }
        }
        return images;
    }
}
