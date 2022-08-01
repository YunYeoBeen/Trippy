package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.CommunityPost;
import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestCommunityPostDto {
    private Long id;
    private String title;
    private String description;
    private Long memberId;
    private int category;
    private Long locationId;
//    private String countryName;
//    private String cityName;
//    private Double latitude;
//    private Double longitude;
    private LocalDateTime meetingTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int recruitVolume;
    private int recruitCurrentVolume;
    private int startAge;
    private int endAge;
    private int gender;
    private boolean isLocal;


    // DTO -> ENTITY
    public CommunityPost toEntity() {
        return CommunityPost.builder()
                .id(id)
                .isLocal(isLocal)
                .category(category)
                .description(description)
                .endAge(endAge)
                .endDate(endDate)
                .gender(gender)
                .location(Location.builder().id(locationId).build())
                .member(Member.builder().id(memberId).build())
                .meetingTime(meetingTime)
                .recruitVolume(recruitVolume)
                .startAge(startAge)
                .startDate(startDate)
                .recruitCurrentVolume(recruitCurrentVolume)
                .title(title)
                .build();
    }



}
