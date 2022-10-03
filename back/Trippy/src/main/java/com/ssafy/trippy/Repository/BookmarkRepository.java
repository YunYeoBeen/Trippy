package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByMemberId(Long memberId);
    void deleteByMemberIdAndCommunityPostId(Long memberId, Long communityPostId);
    boolean existsByMemberIdAndCommunityPostId(Long memberId, Long communityPostId);
}