package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Location;
import lombok.Builder;

public class RequestLocationDto {
    private Long id;

    private String countryName;

    private String cityName;

    public Location toEntity() {
        return Location.builder()
                .id(id)
                .cityName(cityName)
                .countryName(countryName)
                .build();
    }

    @Builder
    public RequestLocationDto(String countryName, String cityName) {
        this.countryName = countryName;
        this.cityName = cityName;
    }
}
