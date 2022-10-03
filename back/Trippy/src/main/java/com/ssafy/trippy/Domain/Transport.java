package com.ssafy.trippy.Domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Transport extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name="TRANSPORT_ID")
    private Long id;

    @Column(nullable = false)
//    @NotBlank(message="운송수단을 입력하세요")
    private String name;

    @Builder
    public Transport(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
