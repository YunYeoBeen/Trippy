package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {
    List<MemberBadge> findAllByMember(Member member);
}