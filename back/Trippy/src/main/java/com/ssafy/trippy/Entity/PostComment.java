package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PostComment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="COMMENT_ID")
    private Long id;

//    @Column(nullable = false)
//    @NotBlank(message="내용을 입력하세요")
    private String content;

//    @Column(name="REG_DT",nullable = false)
//    @NotBlank(message="등록날짜를 입력하세요")
    private LocalDateTime regDt;


//    @Column(name="IS_DELELTE", nullable = false)
//    @NotBlank(message="삭제여부를 입력하세요요")
    private Byte isDelete;

    // 대댓글 나중에
    private Long parent;
    private Long rootComment;
    private Long childComment;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    public PostComment(String content, Post post) {
        this.content = content;
        this.post = post;
    }
}
