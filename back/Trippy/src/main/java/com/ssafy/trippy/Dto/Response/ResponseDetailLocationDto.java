package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Service.S3Uploader;
import lombok.*;

@Getter
@NoArgsConstructor
public class ResponseDetailLocationDto {

    private Long id;

    private String detailLocationName;

    private float rating;

    private String detailLocationContent;

    private String filename;

    private String filepath;

    @Builder
    public ResponseDetailLocationDto(DetailLocation detailLocation) {
        this.id = detailLocation.getId();
        this.detailLocationName = detailLocation.getDetailLocationName();
        this.rating = detailLocation.getRating();
        this.detailLocationContent =detailLocation.getDetailLocationContent();
        this.filename = detailLocation.getFilename();
    }

    public void setFilepath(String filepath){
        this.filepath = filepath;
    }
}
