package com.ssafy.trippy.Service;

import com.ssafy.trippy.Domain.Bookmark;
import com.ssafy.trippy.Dto.Request.RequestBookmarkDto;
import com.ssafy.trippy.Dto.Response.ResponseBookmarkDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;

import java.util.List;

public interface BookmarkService {

    void saveBookmark(RequestBookmarkDto requestBookmarkDto);

    List<ResponseCommunityPostDto> getBookmarks(Long memberId);

    void deleteBookmark(RequestBookmarkDto requestBookmarkDto);
}
