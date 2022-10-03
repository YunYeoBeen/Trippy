package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

//    public void setMember(Member member) {
//        this.member = member;
//        if(member.getBookmarks().contains(this)) {
//            member.getBookmarks().remove(this);
//        }else{
//            member.getBookmarks().add(this);
//        }
//    }


}
