package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    List<LikePost> findAllByMemberId(Long memberId);
    void deleteByMemberIdAndPostId(Long memberId, Long postId);
    boolean existsByMemberIdAndPostId(Long memberId, Long postId);

}