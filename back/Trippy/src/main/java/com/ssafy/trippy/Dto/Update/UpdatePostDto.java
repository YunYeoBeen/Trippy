package com.ssafy.trippy.Dto.Update;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.PostTransport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdatePostDto {
    private String title;
    private int company;
    private int count;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;
    private int representiveImg;
    private List<PostTransport> postTransports;
    private List<DetailLocation> detailLocations;

    public UpdatePostDto(String title, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representiveImg, List<PostTransport> postTransports, List<DetailLocation> detailLocations) {
        this.title = title;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.representiveImg = representiveImg;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
    }
}
