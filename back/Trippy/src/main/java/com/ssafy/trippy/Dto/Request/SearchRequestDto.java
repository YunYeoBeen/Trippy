package com.ssafy.trippy.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchRequestDto {

    private String title;
    private int company;
    private Long transportId;

    @Builder
    public SearchRequestDto(String title, int company, Long transportId) {
        this.title = title;
        this.company = company;
        this.transportId = transportId;
    }
}
