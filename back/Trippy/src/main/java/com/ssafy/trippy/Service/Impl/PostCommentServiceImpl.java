package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Advice.Exception.CommentNotFoundException;
import com.ssafy.trippy.Domain.PostComment;
import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Repository.PostCommentRepository;
import com.ssafy.trippy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCommentServiceImpl {
    private final PostCommentRepository postCommentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public List<ResponsePostCommentDto> findPostCommentByPostId(Long postId){
        postRepository.findById(postId).orElseThrow(()-> new CommentNotFoundException("해당 게시글의 댓글을 찾을 수가 없습니다."));
        return convertNestedStructure(postCommentRepository.findPostCommentByPostId(postId));
    }

    // 댓글 추가
    @Transactional
    public ResponsePostCommentDto createComment(RequestPostCommentDto requestPostCommentDto){
        PostComment comment = postCommentRepository.save(
                PostComment.createComment(requestPostCommentDto.getContent(),
                        postRepository.findById(requestPostCommentDto.getPostId()).get(),
                        memberRepository.findById(requestPostCommentDto.getMemberId()).get(),
                        requestPostCommentDto.getParentId() != null ?
                        postCommentRepository.findById(requestPostCommentDto.getParentId()).get():null)
        );
        return ResponsePostCommentDto.convertCommentToDto(comment);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId){
        PostComment postComment = postCommentRepository.findPostCommentByIdWithParent(commentId).orElseThrow(()-> new CommentNotFoundException("해당 댓글을 찾을 수가 없습니다."));
        postCommentRepository.delete(postComment);
    }

    //댓글 수정
    @Transactional
    public void updateComment(Long commentId, RequestPostCommentDto requestPostCommentDto) {
        PostComment postComment = postCommentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException("해당 댓글을 찾을 수가 없습니다."));
        postComment.update(requestPostCommentDto.getContent());
    }

    // 조상 댓글 찾기
    private PostComment getDeletableAncestorComment(PostComment postComment) {
        PostComment parent = postComment.getParent();
        if(parent != null && parent.getChildren().size() == 1){
            return getDeletableAncestorComment(parent);
        }
        return postComment;
    }

    private List<ResponsePostCommentDto> convertNestedStructure(List<PostComment> postComments){
        List<ResponsePostCommentDto> result = new ArrayList<>();
        Map<Long, ResponsePostCommentDto> map = new HashMap<>();

        postComments.stream().forEach(c -> {
            ResponsePostCommentDto dto = ResponsePostCommentDto.convertCommentToDto(c);
            map.put(dto.getId(),dto);
            if(c.getParent() != null) map.get(c.getParent().getId()).getChildren().add(c);
            else result.add(dto);
        });
        return result;
    }

}
