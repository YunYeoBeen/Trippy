package com.ssafy.trippy.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityPost extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "COMMUNITY_POST_ID")
    private Long id;

    //    @Column(nullable = false)
//    @NotBlank(message="제목을 입력하세요")
    private String title;

    //    @Column(nullable = false)
//    @NotBlank(message="활동내용을 입력하세요")
    private String description;

    //    @Column(nullable = false)
//    @NotBlank(message="카테고리를 입력하세요")
    private int category;

    //    @Column(nullable = false)
//    @NotBlank(message="만나는 시간을 입력하세요")
    private LocalDateTime meetingTime;

    //    @Column(nullable = false)
//    @NotBlank(message="시작날을 입력하세요")
    private LocalDateTime startDate;

    //    @Column(nullable = false)
//    @NotBlank(message="끝나는 날을 입력하세요")
    private LocalDateTime endDate;

    //    @Column(nullable = false)
//    @NotBlank(message="최대인원을 입력하세요")
    private int recruitVolume;

    //    @Column(nullable = false)
//    @NotBlank(message="현재인원을 입력하세요")
    private int recruitCurrentVolume;

    //    @Column(nullable = false)
//    @NotBlank(message="최소나이를 입력하세요")
    private int startAge;

    //    @Column(nullable = false)
//    @NotBlank(message="최대나이를 입력하세요")
    private int endAge;

    //    @Column(nullable = false)
//    @NotBlank(message="성별을 입력하세요")
    private String gender;

    //    @Column(nullable = false)
//    @NotBlank(message="참여가능지역을 입력하세요")
    private boolean Local;

    private String place;
    private boolean Day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @OneToMany(mappedBy = "communityPost", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder
    public CommunityPost(Long id, String title, String description, int category, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, String gender, boolean Local, Member member, Location location, String place, boolean Day) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.meetingTime = meetingTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitVolume = recruitVolume;
        this.recruitCurrentVolume = recruitCurrentVolume;
        this.startAge = startAge;
        this.endAge = endAge;
        this.gender = gender;
        this.Local = Local;
        this.member = member;
        this.location = location;
        this.place = place;
        this.Day = Day;
    }

    // update문
    public void update(String title, String description, int category, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, String gender, boolean Local, Location location, String place, boolean Day) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.meetingTime = meetingTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitVolume = recruitVolume;
        this.recruitCurrentVolume = recruitCurrentVolume;
        this.startAge = startAge;
        this.endAge = endAge;
        this.gender = gender;
        this.Local = Local;
        this.location = location;
        this.place = place;
        this.Day = Day;
    }

}
