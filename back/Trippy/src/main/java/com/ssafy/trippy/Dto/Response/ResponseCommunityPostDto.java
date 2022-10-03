package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Entity.CommunityPost;
import com.ssafy.trippy.Entity.Location;
import com.ssafy.trippy.Entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseCommunityPostDto {
    private Long id;
    private String title;
    private String description;
    private String memberId;
    private int category;
    private String countryName;
    private String cityName;
    private LocalDateTime meetingTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int recruitVolume;
    private int recruitCurrentVolume;
    private int startAge;
    private int endAge;
    private int gender;
    private boolean isLocal;


    public ResponseCommunityPostDto(CommunityPost communityPost) {
        this.id = communityPost.getId();
        this.title = communityPost.getTitle();
        this.description = communityPost.getDescription();
        this.memberId = communityPost.getMember().getName();
        this.category = communityPost.getCategory();
        this.countryName = communityPost.getLocation().getCountryName();
        this.cityName = communityPost.getLocation().getCityName();
        this.meetingTime = communityPost.getMeetingTime();
        this.startDate = communityPost.getStartDate();
        this.endDate = communityPost.getEndDate();
        this.recruitVolume = communityPost.getRecruitVolume();
        this.recruitCurrentVolume = communityPost.getRecruitCurrentVolume();
        this.startAge = communityPost.getStartAge();
        this.endAge = communityPost.getEndAge();
        this.gender = communityPost.getGender();
        this.isLocal = communityPost.isLocal();
    }



}
