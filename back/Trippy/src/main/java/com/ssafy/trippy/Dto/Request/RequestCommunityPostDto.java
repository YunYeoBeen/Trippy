package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Entity.CommunityPost;
import com.ssafy.trippy.Entity.Location;
import com.ssafy.trippy.Entity.Member;
import lombok.Builder;
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

    @Builder
    public RequestCommunityPostDto(Long id, String title, String description, Long memberId, int category, Long locationId, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, int gender, boolean isLocal) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.memberId = memberId;
        this.category = category;
        this.locationId = locationId;
        this.meetingTime = meetingTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitVolume = recruitVolume;
        this.recruitCurrentVolume = recruitCurrentVolume;
        this.startAge = startAge;
        this.endAge = endAge;
        this.gender = gender;
        this.isLocal = isLocal;
    }
}
