package com.ssafy.trippy.Dto.Request;

//import com.ssafy.trippy.Domain.Geocode;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.Route;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class RequestRouteDto {
    private Long id;
    private String routeName;
    private int idx;
    private Long postId;

    private Double lng;
    private Double lat;

//    private Long geocodeId;

    public Route toEntity() {
        return Route.builder().
                id(id)
                .routeName(routeName)
                .idx(idx)
                .lat(lat)
                .lng(lng)
                .post(Post.builder().id(postId).build())
                .build();
    }

    @Builder
    public RequestRouteDto(Long id, String routeName,int idx, Long postId, Double lat, Double lng) {
        this.id = id;
        this.routeName = routeName;
        this.idx = idx;
        this.postId = postId;
        this.lat = lat;
        this.lng = lng;
//        this.geocodeId = geocodeId;
    }
}
