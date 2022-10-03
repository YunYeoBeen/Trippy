package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Entity.Member;
import com.ssafy.trippy.Entity.Post;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Repository.PostRepository;
import com.ssafy.trippy.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    // 모든 post찾기
    @Transactional
    @Override
    public List<ResponsePostDto> findAll() {
        List<Post> all = postRepository.findAll();
        List<ResponsePostDto> responsePostDtoList = new ArrayList<>();
        for (Post post : all) {
            ResponsePostDto dto = ResponsePostDto.builder()
                    .id(post.getId())
                    .company(post.getCompany())
                    .endDate(post.getEndDate())
                    .count(post.getCount())
                    .memberId(post.getMember().getId())
                    .postTransports(post.getPostTransports())
                    .detailLocations(post.getDetailLocations())
                    .startDate(post.getStartDate())
                    .title(post.getTitle())
                    .isDelete(post.getIsDelete())
                    .representiveImg(post.getRepresentiveImg()).build();
            responsePostDtoList.add(dto);
        }
        return responsePostDtoList;
    }

    // 유저(본인)가 작성한 게시글 조회
    @Transactional
    @Override
    public List<ResponsePostDto> findAllByMember(Member member) {
        List<Post> posts = postRepository.findAllByMember(member);
        List<ResponsePostDto> responsePostDtoUserList = new ArrayList<>();
        for (Post post : posts) {
            ResponsePostDto dto = ResponsePostDto.builder()
                    .id(post.getId())
                    .company(post.getCompany())
                    .endDate(post.getEndDate())
                    .count(post.getCount())
                    .memberId(post.getMember().getId())
                    .postTransports(post.getPostTransports())
                    .detailLocations(post.getDetailLocations())
                    .startDate(post.getStartDate())
                    .title(post.getTitle())
                    .isDelete(post.getIsDelete())
                    .representiveImg(post.getRepresentiveImg()).build();
            responsePostDtoUserList.add(dto);
        }
        return responsePostDtoUserList;
    }

    // post 등록
    @Transactional
    @Override
    public Long savePost(RequestPostDto requestPostDto) {
        return postRepository.save(requestPostDto.toEntity()).getId();
    }

    // post 삭제
    @Transactional
    @Override
    public void deletePost(RequestPostDto requestPostDto) {
        postRepository.delete(requestPostDto.toEntity());
    }

    // post 수정
    @Transactional
    @Override
    public void updatePost(Long id, RequestPostDto requestPostDto) {
        Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        Optional<Member> member = memberRepository.findById(requestPostDto.getMemberId());
        post.update(requestPostDto.getId(),
                requestPostDto.getTitle(),
                requestPostDto.getIsDelete(),
                requestPostDto.getCompany(),
                requestPostDto.getCount(),
                requestPostDto.getStartDate(),
                requestPostDto.getEndDate(),
                requestPostDto.getRepresentiveImg(),
                member.get(),
                requestPostDto.getPostTransports(),
                requestPostDto.getDetailLocations());
    }

}
