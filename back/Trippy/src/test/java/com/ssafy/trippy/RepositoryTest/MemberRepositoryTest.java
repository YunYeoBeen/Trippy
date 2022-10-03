//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Entity.Member;
//import com.ssafy.trippy.Repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void 회원가입 () throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        // when
//
//        // then
//        assertEquals(member,memberRepository.findByEmail("tommy7899@naver.com").get());
//    }
//
//    @Test
//    public void 회원삭제 () throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("duwns7899@naver.com"));
//        // when
//        memberRepository.delete(member);
//        // then
//        assertEquals(null,memberRepository.findByEmail("duwns7899@naver.com").get());
//    }
//
//}
