package com.ssafy.trippy.Domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostComment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="COMMENT_ID")
    private Long id;

    private String content;

    private LocalDateTime regDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private PostComment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<PostComment> children = new ArrayList<>();

    public static PostComment createComment(String content, Post post, Member member, PostComment parent){
        PostComment postComment = new PostComment();
        postComment.post = post;
        postComment.member = member;
        postComment.content = content;
        postComment.parent = parent;
        return postComment;
    }

    @Builder
    public PostComment(String content) {
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }


}
