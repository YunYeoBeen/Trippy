package com.ssafy.trippy.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "FOLLOWING_ID", "FOLLOWER_ID"}))
public class Follow extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "FOLLOW_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOWING_ID")
    private Member following;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FOLLOWER_ID")
    private Member follower;

    @Builder
    public Follow(Long id, Member following, Member follower) {
        this.id = id;
        this.following = following;
        this.follower = follower;
    }
}
