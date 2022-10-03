package com.ssafy.trippy.Dto.Converter;

import com.ssafy.trippy.Domain.*;
import com.ssafy.trippy.Dto.Response.ResponseDetailLocationDto;
import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponseRouteDto;
import com.ssafy.trippy.Dto.Response.ResponseTransport;
import com.ssafy.trippy.Service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Converter {

    private final S3Uploader nonstatics3Uploader;

    private static S3Uploader s3Uploader;

    @PostConstruct
    private void initStatic(){
        s3Uploader = this.nonstatics3Uploader;
    }


    public static List<ResponseTransport> convertTransportList(List<Transport> transportList) {
        List<ResponseTransport> transports = new ArrayList<>();
        for (Transport transport : transportList) {
            ResponseTransport dto = ResponseTransport.builder()
                    .transport(transport).build();
            transports.add(dto);
        }
        return transports;
    }

    public static List<ResponseDetailLocationDto> convertDetailLocationList(List<DetailLocation> detailLocationList) {

        List<ResponseDetailLocationDto> detailLocations = new ArrayList<>();
        for (DetailLocation detailLocation : detailLocationList) {
            ResponseDetailLocationDto dto = ResponseDetailLocationDto.builder()
                    .detailLocation(detailLocation).build();
            detailLocations.add(dto);
            if(detailLocation.getFilename()!= null)
                dto.setFilepath(s3Uploader.getS3(detailLocation.getFilename()));
        }
        return detailLocations;
    }

    public static List<ResponseRouteDto> convertRouteList(List<Route> routeList) {
        List<ResponseRouteDto> routes = new ArrayList<>();
        for (Route route : routeList) {
            ResponseRouteDto dto = ResponseRouteDto.builder()
                    .route(route).build();

            routes.add(dto);
        }
        return routes;
    }

    public static List<ResponsePostCommentDto> convertPostCommentList(List<PostComment> postCommentList) {
        List<ResponsePostCommentDto> ResponsepostComments = new ArrayList<>();
        for (PostComment postComment : postCommentList) {
            ResponsePostCommentDto dto = ResponsePostCommentDto.builder()
                    .postComment(postComment).build();

            ResponsepostComments.add(dto);
        }
        return ResponsepostComments;
    }

    public static List<Transport> convertTransportsToPostTransports(List<PostTransport> postTransports) {
        List<Transport> transports = new ArrayList<>();
        for (PostTransport postTransport : postTransports) {
            transports.add(postTransport.getTransport());
        }
        return transports;
    }


}

