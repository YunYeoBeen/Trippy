//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Domain.Follow;
//import com.ssafy.trippy.Domain.Member;
//import com.ssafy.trippy.Repository.FollowRepository;
//import com.ssafy.trippy.Repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class FollowRepositoryTest {
//
//    @Autowired
//    FollowRepository followRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void 팔로잉리스트 () throws Exception {
//        // given
//        Member A = memberRepository.save(new Member("A@naver.com"));
//        Member B = memberRepository.save(new Member("B@naver.com"));
//        Member C = memberRepository.save(new Member("C@naver.com"));
//        // when
//        // A<-B,C ||| B<-C 팔로우한 상태
//        Follow follow1 = followRepository.save(new Follow(A,B));
//        Follow follow2 = followRepository.save(new Follow(A,C));
//        Follow follow3 = followRepository.save(new Follow(B,C));
//
//        List<Follow> resultA = followRepository.findAllByFollowing(A);
//        Set<String> followingEmail = new HashSet<>();
//        for (Follow f : resultA) {
//            followingEmail.add(f.getFollower().getEmail());
//        }
//        // then
//        assertEquals(2,followingEmail.size());
//        assertEquals(true,followingEmail.contains("B@naver.com"));
//        assertEquals(true,followingEmail.contains("C@naver.com"));
//        assertEquals(false,followingEmail.contains("A@naver.com"));
//    }
//
//    @Test
//    public void 팔로우리스트 () throws Exception {
//        // given
//        Member A = memberRepository.save(new Member("A@naver.com"));
//        Member B = memberRepository.save(new Member("B@naver.com"));
//        Member C = memberRepository.save(new Member("C@naver.com"));
//        // when
//        // A<-B,C ||| B<-C 팔로우한 상태
//        Follow follow1 = followRepository.save(new Follow(A,B));
//        Follow follow2 = followRepository.save(new Follow(A,C));
//        Follow follow3 = followRepository.save(new Follow(B,C));
//
//        List<Follow> resultA = followRepository.findAllByFollower(C);
//        Set<String> followerEmail = new HashSet<>();
//        for (Follow f : resultA) {
//            followerEmail.add(f.getFollowing().getEmail());
//        }
//        // then
//        assertEquals(2,followerEmail.size());
//        assertEquals(true,followerEmail.contains("A@naver.com"));
//        assertEquals(true,followerEmail.contains("B@naver.com"));
//        assertEquals(false,followerEmail.contains("C@naver.com"));
//    }
//
//}
