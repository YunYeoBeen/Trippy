package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.LikePost;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Dto.Request.RequestLikePostDto;
import com.ssafy.trippy.Dto.Response.ResponseLikePostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Repository.LikePostRepository;
import com.ssafy.trippy.Repository.PostRepository;
import com.ssafy.trippy.Service.LikePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LikePostServiceImpl implements LikePostService {

    private final LikePostRepository likePostRepository;

    private final PostRepository postRepository;
    @Override
    public void saveLikePost(RequestLikePostDto requestLikePostDto) {
        likePostRepository.save(requestLikePostDto.toEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponsePostDto> getLikePosts(Long memberId) {
        List<LikePost> likePosts = likePostRepository.findAllByMemberId(memberId);
        List<ResponsePostDto> postDtos = new ArrayList<>();
        for (LikePost likePost:likePosts){
            Post post = postRepository.findById(likePost.getId()).orElseThrow();
            postDtos.add(new ResponsePostDto(post));
        }
        return postDtos;
    }

    @Override
    public void deleteLikePost(RequestLikePostDto requestLikePostDto) {
        if(!likePostRepository.existsByMemberIdAndPostId(requestLikePostDto.getMemberId(),requestLikePostDto.getPostId())){
            throw new IllegalArgumentException("존재하지 않는 좋아요");
        }
        likePostRepository.deleteByMemberIdAndPostId(requestLikePostDto.getMemberId(),requestLikePostDto.getPostId());
    }
}
