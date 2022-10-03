package com.ssafy.trippy.Dto.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.trippy.Domain.*;
import com.ssafy.trippy.Dto.Converter.Converter;
import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Service.S3Uploader;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String countryName;
    private String cityName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;
    private int representativeImg;
    private String name;
    private Long memberId;
    private List<ResponseTransport> postTransports;
    private List<ResponseDetailLocationDto> detailLocations;

    private List<ResponsePostCommentDto> comments;
    private List<ResponseRouteDto> routes;


    @Builder
    public ResponsePostDto(Post post) {
        this.countryName = post.getLocation().getCountryName();
        this.cityName = post.getLocation().getCityName();
        this.id = post.getId();
        this.title = post.getTitle();
        this.company = post.getCompany();
        this.count = post.getCount();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
        this.representativeImg = post.getRepresentiveImg();
        this.name = post.getMember().getName();
        this.postTransports = Converter.convertTransportList(Converter.convertTransportsToPostTransports(post.getPostTransports()));
        this.detailLocations = Converter.convertDetailLocationList(post.getDetailLocations());
        this.comments = Converter.convertPostCommentList(post.getPostComments());
        this.routes = Converter.convertRouteList(post.getRoutes());
        this.memberId = post.getMember().getId();
    }
}
