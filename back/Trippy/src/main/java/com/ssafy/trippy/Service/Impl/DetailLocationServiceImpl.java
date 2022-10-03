package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;
import com.ssafy.trippy.Dto.Response.ResponseDetailLocationDto;
import com.ssafy.trippy.Repository.DetailLocationRepository;
import com.ssafy.trippy.Service.DetailLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailLocationServiceImpl implements DetailLocationService {

    private final DetailLocationRepository detailLocationRepository;

    @Override
    public ResponseDetailLocationDto findDetailLocation(Long id) {
        DetailLocation detailLocation = detailLocationRepository.findById(id).get();
        ResponseDetailLocationDto responseDetailLocationDto = new ResponseDetailLocationDto(detailLocation);
        return responseDetailLocationDto;
    }

    @Override
    public Long saveDetailLocation(RequestDetailLocationDto requestDetailLocationDto) {
        return detailLocationRepository.save(requestDetailLocationDto.toEntity()).getId();
    }

    @Override
    public List<ResponseDetailLocationDto> findDetailLocationByPostId(Long postId) {
        List<DetailLocation> detailLocations = detailLocationRepository.findAllByPostId(postId).orElseThrow(() -> new IllegalArgumentException(
                "postId -> detailLocation 받아오기 실패"));
        List<ResponseDetailLocationDto> responseDetailLocationDtos = new ArrayList<>();
        for (DetailLocation detailLocation:detailLocations){
            responseDetailLocationDtos.add(new ResponseDetailLocationDto(detailLocation));
        }
        return responseDetailLocationDtos;
    }

}
