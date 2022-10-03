package com.ssafy.trippy.Dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.trippy.Domain.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestPostDto {
    private String title;
    private Byte isDelete;
    private String countryName;
    private String cityName;
    private int company;
    private int count;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private int representativeImg;
    private Long member_id;
    private List<RequestPostTransPortDto> postTransports;
    private List<RequestDetailLocationDto> detailLocations;
    private Long location_id;
    private List<RequestRouteDto> routes;

    public Post toEntity() {
        List<DetailLocation> detailLocationList = new ArrayList<>();
        for (RequestDetailLocationDto detailLocation : detailLocations) {
            detailLocationList.add(detailLocation.toEntity());
        }
        List<Route> routeList = new ArrayList<>();
        for (RequestRouteDto requestRouteDto:routes){
            routeList.add(requestRouteDto.toEntity());
        }
        List<PostTransport> postTransportList = new ArrayList<>();
        for(RequestPostTransPortDto requestPostTransPortDto:postTransports){
            postTransportList.add(requestPostTransPortDto.toEntity());
        }
        return Post.builder()
                .company(company)
                .endDate(endDate)
                .postTransports(postTransportList)
                .member(Member.builder().id(member_id).build())
                .count(count)
                .startDate(startDate)
                .detailLocations(detailLocationList)
                .title(title)
                .representiveImg(representativeImg)
                .routes(routeList)
                .location(Location.builder().id(location_id).build())
                .build();
    }




    @Builder
    public RequestPostDto(String title, Byte isDelete, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representativeImg, Long memberId, List<RequestPostTransPortDto> postTransports, List<RequestDetailLocationDto> detailLocations, List<RequestRouteDto> routes) {
        this.title = title;
        this.isDelete = isDelete;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.representativeImg = representativeImg;
        this.member_id = member_id;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
        this.routes = routes;
    }
}
