//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Domain.Badge;
//import com.ssafy.trippy.Domain.Member;
//import com.ssafy.trippy.Domain.MemberBadge;
//import com.ssafy.trippy.Repository.BadgeRepository;
//import com.ssafy.trippy.Repository.MemberBadgeRepository;
//import com.ssafy.trippy.Repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class MemberBadgeRepositoryTest {
//    @Autowired
//    BadgeRepository badgeRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    MemberBadgeRepository memberBadgeRepository;
//
//    @Test
//    public void 뱃지부여하기() throws Exception {
//        // given
//
//        // 멤버 생성, 뱃지 생성
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        Badge badge = badgeRepository.save(new Badge("경기도의 딸"));
//
//        // when
//
//        // 멤버에게 뱃지 부여하기.
//        memberBadgeRepository.save(new MemberBadge(member, badge));
//        List<MemberBadge> badges = memberBadgeRepository.findAllByMember(member);
//
//        // then
//        assertEquals("경기도의 딸", badges.get(0).getBadge().getName());
//
//    }
//
//}
