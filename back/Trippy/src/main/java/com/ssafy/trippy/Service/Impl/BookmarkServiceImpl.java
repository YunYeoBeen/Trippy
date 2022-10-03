package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Bookmark;
import com.ssafy.trippy.Domain.CommunityPost;
import com.ssafy.trippy.Dto.Request.RequestBookmarkDto;
import com.ssafy.trippy.Dto.Response.ResponseBookmarkDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Repository.BookmarkRepository;
import com.ssafy.trippy.Repository.CommunityPostRepository;
import com.ssafy.trippy.Service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    private final CommunityPostRepository communityPostRepository;
    @Override
    public void saveBookmark(RequestBookmarkDto requestBookmarkDto) {
        bookmarkRepository.save(requestBookmarkDto.toEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCommunityPostDto> getBookmarks(Long memberId) {
        List<Bookmark> bookmarks = bookmarkRepository.findAllByMemberId(memberId);
        List<ResponseCommunityPostDto> communityPostDtos = new ArrayList<>();
        for (Bookmark bookmark:bookmarks){
            CommunityPost communityPost = communityPostRepository.findById(bookmark.getCommunityPost().getId()).orElseThrow();
            communityPostDtos.add(new ResponseCommunityPostDto(communityPost));
        }
        return communityPostDtos;
    }

    @Override
    public void deleteBookmark(RequestBookmarkDto requestBookmarkDto) {
        if(!bookmarkRepository.existsByMemberIdAndCommunityPostId(requestBookmarkDto.getMemberId(),requestBookmarkDto.getCommunityPostId())){
            throw new IllegalArgumentException("존재하지 않는 북마크");
        }
        bookmarkRepository.deleteByMemberIdAndCommunityPostId(requestBookmarkDto.getMemberId(),requestBookmarkDto.getCommunityPostId());
    }
}
