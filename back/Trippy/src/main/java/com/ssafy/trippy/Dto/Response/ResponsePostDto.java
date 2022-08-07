package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.*;
import com.ssafy.trippy.Dto.Converter.Converter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponsePostDto {
    private Long id;
    private String title;
    private int company;
    private int count;
    private String countyName;
    private String cityName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int representativeImg;
    private String email;
    private List<ResponseTransport> name;
    private List<ResponseDetailLocationDto> detailLocations;

    private List<ResponsePostCommentDto> comments;
    private List<ResponseRouteDto> routes;


    @Builder
    public ResponsePostDto(Post post) {
        for (DetailLocation detailLocation: post.getDetailLocations()){
            this.countyName = detailLocation.getLocation().getCountryName();
            this.cityName = detailLocation.getLocation().getCityName();
        }
        this.id = post.getId();
        this.title = post.getTitle();
        this.company = post.getCompany();
        this.count = post.getCount();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
        this.representativeImg = post.getRepresentiveImg();
        this.email = post.getMember().getEmail();
        this.name = Converter.convertTransportList(Converter.convertTransportsToPostTransports(post.getPostTransports()));
        this.detailLocations = Converter.convertDetailLocationList(post.getDetailLocations());
        this.comments = Converter.convertPostCommentList(post.getPostComments());
        this.routes = Converter.convertRouteList(post.getRoutes());
    }
}
