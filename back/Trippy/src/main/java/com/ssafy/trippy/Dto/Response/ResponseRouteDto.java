package com.ssafy.trippy.Dto.Response;

//import com.ssafy.trippy.Domain.Geocode;
import com.ssafy.trippy.Domain.Route;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseRouteDto {
    private String routeName;
    private int index;

    private Double lng;
    private Double lat;


    @Builder
    public ResponseRouteDto(Route route){
        this.routeName = route.getRouteName();
        this.index = route.getIdx();
        this.lng = route.getLng();
        this.lat = route.getLat();
    }


}
