package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.Badge;
import com.ssafy.trippy.Entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
}