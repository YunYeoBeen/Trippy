package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.Bookmark;
import com.ssafy.trippy.Entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
}