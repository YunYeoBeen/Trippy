package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Post;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestDetailLocationDto {

    private String detailLocationName;

    private float rating;

    private String detailLocationContent;

    private String imgPath;

    private Long post_id;

    private Long location_id;

    public DetailLocation toEntity() {
        return DetailLocation.builder()
                .detailLocationContent(detailLocationContent)
                .imgPath(imgPath)
                .post(Post.builder().id(post_id).build())
                .location(Location.builder().id(location_id).build())
                .rating(rating)
                .detailLocationName(detailLocationName)
    private String filename;

    private Long post_id;

    public DetailLocation toEntity() {
        return DetailLocation.builder()
                .detailLocationContent(detailLocationContent)
                .post(Post.builder().id(post_id).build())
                .rating(rating)
                .detailLocationName(detailLocationName)
                .filename(filename)
                .build();
    }

    @Builder
    public RequestDetailLocationDto(String detailLocationName, float rating, String detailLocationContent, Long post_id,String filename) {
        this.detailLocationName = detailLocationName;
        this.rating = rating;
        this.detailLocationContent = detailLocationContent;
        this.post_id = post_id;
        this.filename = filename;
    }
}
