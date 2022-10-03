package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Dto.Request.SearchRequestDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostTransportRepositoryCustom {
    List<ResponsePostDto> findAllBySearch(String title, int company,Long locationId);
}
