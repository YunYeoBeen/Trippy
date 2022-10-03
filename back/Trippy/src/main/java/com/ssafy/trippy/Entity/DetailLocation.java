package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class DetailLocation extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="DETAIL_LOCATION_ID")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message="상세지역명을 입력하세요")
    private String detailLocationName;

    @Column(nullable = false)
    @NotBlank(message="별점을 입력하세요")
    private float rating;

    @Column(nullable = false)
    @NotBlank(message="상세지역글을 입력하세요")
    private String detailLocationContent;

    @Column(name="IMG_PATH")
    private String imgPath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="LOCATION_ID")
    private Location location;

    public DetailLocation(Post post, Location location) {
        this.post = post;
        this.location = location;
    }
}
