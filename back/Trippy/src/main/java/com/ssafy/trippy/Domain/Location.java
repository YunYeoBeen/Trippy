package com.ssafy.trippy.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Location extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="LOCATION_ID")
    private Long id;

    //    @Column(nullable = false)
//    @NotBlank(message="국가이름을 입력하세요")
    private String countryName;

    //    @Column(nullable = false)
//    @NotBlank(message="도시이름을 입력하세요")
    private String cityName;


    @Builder
    public Location(Long id, String countryName, String cityName) {
        this.id = id;
        this.countryName = countryName;
        this.cityName = cityName;
    }
}
