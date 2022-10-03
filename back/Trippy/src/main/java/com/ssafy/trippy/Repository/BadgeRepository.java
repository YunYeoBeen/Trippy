package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.Badge;
import com.ssafy.trippy.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    List<Badge> findAllByMember(Member member);
}