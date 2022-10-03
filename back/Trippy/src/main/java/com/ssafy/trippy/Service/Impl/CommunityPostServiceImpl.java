package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Update.UpdateCommunityPostDto;
import com.ssafy.trippy.Entity.CommunityPost;
import com.ssafy.trippy.Entity.Location;
import com.ssafy.trippy.Entity.PostComment;
import com.ssafy.trippy.Repository.CommunityPostRepository;
import com.ssafy.trippy.Repository.LocationRepository;
import com.ssafy.trippy.Service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityPostServiceImpl implements CommunityPostService {

    private final CommunityPostRepository communityPostRepository;
    private final LocationRepository locationRepository;


    @Transactional
    @Override
    public Long saveCommunityPost(RequestCommunityPostDto requestCommunityPostDto) {
        return communityPostRepository.save(requestCommunityPostDto.toEntity()).getId();
    }

    @Transactional
    @Override
    public void deleteCommunityPost(RequestCommunityPostDto requestCommunityPostDto) {
        communityPostRepository.delete(requestCommunityPostDto.toEntity());

    }

    @Transactional
    @Override
    public void updateCommunityPost(Long id, UpdateCommunityPostDto updateCommunityPostDto) {
        CommunityPost communityPost = communityPostRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 동행찾기글이 없습니다. id="+id));
        Long locationId = locationRepository.findIdByCityNameAndCountryName(updateCommunityPostDto.getCityName(), updateCommunityPostDto.getCountryName());

        communityPost.update(updateCommunityPostDto.getTitle(),
                updateCommunityPostDto.getDescription(),
                updateCommunityPostDto.getCategory(),
                updateCommunityPostDto.getMeetingTime(),
                updateCommunityPostDto.getStartDate(),
                updateCommunityPostDto.getEndDate(),
                updateCommunityPostDto.getRecruitVolume(),
                updateCommunityPostDto.getRecruitCurrentVolume(),
                updateCommunityPostDto.getStartAge(),
                updateCommunityPostDto.getEndAge(),
                updateCommunityPostDto.getGender(),
                updateCommunityPostDto.isLocal(),
                Location.builder().id(locationId).build());
    }

    @Override
    public List<ResponseCommunityPostDto> getAllCommunityPost() {
        List<CommunityPost> communityPosts = communityPostRepository.findAll();
        List<ResponseCommunityPostDto> communityPostDtos = communityPosts.stream()
                .map(m -> new ResponseCommunityPostDto(m))
                .collect(Collectors.toList());
        return communityPostDtos;
    }
}
