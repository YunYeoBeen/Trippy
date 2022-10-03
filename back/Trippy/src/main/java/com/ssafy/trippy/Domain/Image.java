package com.ssafy.trippy.Domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileUrl;

    @Column
    private Long detailLocationId;

    @Builder
    public Image(String fileName, String fileUrl, Long detailLocationId) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.detailLocationId = detailLocationId;
    }
}