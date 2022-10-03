package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.CommunityPost;
import com.ssafy.trippy.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    List<CommunityPost> findAllByMember(Member member);
}