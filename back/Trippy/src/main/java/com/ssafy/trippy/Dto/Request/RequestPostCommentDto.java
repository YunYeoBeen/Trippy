package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.PostComment;
import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPostCommentDto {

    private Long id;
    private String content;
    private Long memberId;

    private Long postId;

    private Long parentId;
    private List<PostComment> children = new ArrayList<>();

    @Builder
    public RequestPostCommentDto(Long id, String content, Long memberId) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;

    }

    public PostComment toEntity(){
        return PostComment.builder()
                .id(id)
                .content(content)
                .member(Member.builder().id(memberId).build())
                .children(children)
                .build();
    }


}
