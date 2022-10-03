package com.ssafy.trippy.Domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "POST_ID", "MEMBER_ID"}))
public class LikePost extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="LIKE_POST_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

//    public void setMember(Member member){
//        this.member = member;
//        if(member.getLikePosts().contains(this)){
//            member.getLikePosts().remove(this);
//        }else{
//            member.getLikePosts().add(this);
//        }
//    }
    public LikePost(Post post, Member member) {
        this.post = post;
        this.member = member;
    }
}
