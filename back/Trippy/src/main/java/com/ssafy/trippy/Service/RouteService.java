package com.ssafy.trippy.Service;

import com.ssafy.trippy.Domain.Route;
import com.ssafy.trippy.Dto.Request.RequestRouteDto;
import com.ssafy.trippy.Dto.Response.ResponseRouteDto;

import java.util.List;
import java.util.PriorityQueue;

public interface RouteService {
    List<ResponseRouteDto> findRouteName(Long id);
    void saveRoute(RequestRouteDto requestRouteDto);
}
