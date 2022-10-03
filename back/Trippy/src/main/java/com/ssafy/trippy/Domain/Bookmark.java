package com.ssafy.trippy.Domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "MEMBER_ID", "COMMUNITY_POST_ID"}))
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="BOOKMARK_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMMUNITY_POST_ID")
    private CommunityPost communityPost;

    public void setMember(Member member) {
        this.member = member;
        if(member.getBookmarks().contains(this)) {
            member.getBookmarks().remove(this);
        }else{
            member.getBookmarks().add(this);
        }
    }


}
