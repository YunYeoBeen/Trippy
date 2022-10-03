package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Entity.DetailLocation;
import com.ssafy.trippy.Entity.Post;
import com.ssafy.trippy.Entity.PostTransport;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponsePostDto {
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

    public ResponsePostDto (Post post){
        this.id=post.getId();
        this.title = post.getTitle();
        this.isDelete = post.getIsDelete();
        this.company=post.getCompany();
        this.count = post.getCount();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
        this.representiveImg = post.getRepresentiveImg();
        this.memberId = post.getMember().getId();
        this.postTransports = post.getPostTransports();
        this.detailLocations = post.getDetailLocations();
    }

    @Builder
    public ResponsePostDto(Long id, String title, Byte isDelete, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representiveImg, Long memberId, List<PostTransport> postTransports, List<DetailLocation> detailLocations) {
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
