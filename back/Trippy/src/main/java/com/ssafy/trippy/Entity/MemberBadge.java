package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class MemberBadge extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name="MEMBER_BADGE_ID")
    private Long id;

//    @Column(nullable = false)
//    @NotBlank(message="멤버 뱃지 이름은 필수입니다.")
    private String name;

//    @Column(nullable = false)
//    @NotBlank(message="멤버 뱃지 설명은 필수입니다.")
    private String desc;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "MENBER_ID")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BADGE_ID")
    private Badge badge;

    public MemberBadge(Member member, Badge badge) {
        this.member = member;
        this.badge = badge;
    }
}
