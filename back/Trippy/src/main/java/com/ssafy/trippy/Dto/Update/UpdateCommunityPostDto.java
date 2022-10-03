package com.ssafy.trippy.Dto.Update;

import com.ssafy.trippy.Entity.CommunityPost;
import com.ssafy.trippy.Entity.Location;
import com.ssafy.trippy.Entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateCommunityPostDto {
    private String title;
    private String description;
    private String memberId;
    private int category;
    private String countryName;
    private String cityName;
    private Double latitude;
    private Double longitude;
    private LocalDateTime meetingTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int recruitVolume;
    private int recruitCurrentVolume;
    private int startAge;
    private int endAge;
    private int gender;
    private boolean isLocal;

    @Builder
    public UpdateCommunityPostDto(String title, String description, String memberId, int category, String countryName, String cityName, Double latitude, Double longitude, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, int gender, boolean isLocal) {
        this.title = title;
        this.description = description;
        this.memberId = memberId;
        this.category = category;
        this.countryName = countryName;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
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
