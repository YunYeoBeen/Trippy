package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.Follow;
import com.ssafy.trippy.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollowing(Member member);
    List<Follow> findAllByFollower(Member member);

}