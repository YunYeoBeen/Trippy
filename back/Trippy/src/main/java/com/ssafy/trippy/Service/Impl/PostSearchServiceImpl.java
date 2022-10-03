package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.PostTransport;
import com.ssafy.trippy.Dto.Request.SearchRequestDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Repository.PostRepository;
import com.ssafy.trippy.Repository.PostTransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostSearchServiceImpl {
    private final PostTransportRepository postTransportRepository;

    public List<ResponsePostDto> searchPost(SearchRequestDto searchRequestDto){
        return postTransportRepository.findAllBySearch(searchRequestDto.getTitle(), searchRequestDto.getCompany(), searchRequestDto.getTransportId());
    }
}
