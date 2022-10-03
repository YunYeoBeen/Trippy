//package com.ssafy.trippy.Service.Impl;
//
//import com.ssafy.trippy.Domain.Route;
//import com.ssafy.trippy.Dto.Request.RequestRouteDto;
//import com.ssafy.trippy.Dto.Response.ResponseRouteDto;
//import com.ssafy.trippy.Repository.RouteRepository;
//import com.ssafy.trippy.Service.RouteService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class RouteServiceImpl implements RouteService {
//    private final RouteRepository routeRepository;
//
//
//    @Override
//    public List<ResponseRouteDto> findRouteName(Long id) {
//        List<Route> routes = routeRepository.findAllByPostId(id);
//        List<ResponseRouteDto> routeDtos = new ArrayList<>();
//        for (Route route : routes) {
//            ResponseRouteDto dto  = ResponseRouteDto.builder()
//                    .routeName(route.getRouteName())
//                    .index(route.getIndex())
//                    .build();
//            routeDtos.add(dto);
//        }
//        return routeDtos;
//    }
//
//    @Transactional
//    @Override
//    public void saveRoute(RequestRouteDto requestRouteDto) {
//        routeRepository.save(requestRouteDto.toEntity());
//    }
//
//}
