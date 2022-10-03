//package com.ssafy.trippy.ServiceTest;
//
//import com.ssafy.trippy.Entity.Badge;
//import com.ssafy.trippy.Entity.Member;
//import com.ssafy.trippy.Repository.BadgeRepository;
//import com.ssafy.trippy.Repository.MemberRepository;
//import com.ssafy.trippy.Service.MemberBadgeService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//public class BadgeServiceTest {
//    @Autowired
//    MemberBadgeService badgeService;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    BadgeRepository badgeRepository;
//
//
//    @Test
//    public void 유저가_가진_뱃지_리스트() throws Exception {
//        // given
//        Member member = memberRepository.save(Member.builder().id(1L).email("tommy7899@naver.com").build());
//        badgeRepository.save(Badge.builder().name("경기도의 자랑").desc("경기도를 5회 이상 다녀온 당신").build());
//        badgeRepository.save(Badge.builder().name("대전의 자랑").desc("대전을 5회 이상 다녀온 당신").build());
//        // when
//        member
//
//        // then
//
//    }
//}
