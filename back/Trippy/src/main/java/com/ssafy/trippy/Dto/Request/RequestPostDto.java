package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RequestPostDto {
    private Long id;
    private String title;
    private Byte isDelete;
    private int company;
    private int count;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int representiveImg;
    private Long memberId;
    private List<PostTransport> postTransports;
    private List<DetailLocation> detailLocations;

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .company(company)
                .endDate(endDate)
                .postTransports(postTransports)
                .member(Member.builder().id(memberId).build())
                .count(count)
                .startDate(startDate)
                .detailLocations(detailLocations)
                .title(title)
                .isDelete(isDelete)
                .representiveImg(representiveImg)
                .build();
    }

    @Builder
    public RequestPostDto(Long id, String title, Byte isDelete, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representiveImg, Long memberId, List<PostTransport> postTransports, List<DetailLocation> detailLocations) {
        this.id = id;
        this.title = title;
        this.isDelete = isDelete;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.representiveImg = representiveImg;
        this.memberId = memberId;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
    }
}
