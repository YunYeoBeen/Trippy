//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Entity.CommunityPost;
//import com.ssafy.trippy.Entity.Member;
//import com.ssafy.trippy.Repository.CommunityPostRepository;
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
//public class CommunityPostRepositoryTest {
//
//    @Autowired
//    CommunityPostRepository communityPostRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void 동행찾기글등록 ()throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//
//        // when
//        communityPostRepository.save(new CommunityPost(member, "동행구해요-핸썸-"));
//        communityPostRepository.save(new CommunityPost(member, "동행 안구해요 절대"));
//
//        List<CommunityPost> communityPosts = communityPostRepository.findAllByMember(member);
//        // then
//
//        assertEquals(2, communityPosts.size());
//    }
//
//    @Test
//    public void 동행찾기글삭제 () throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        // when
//        CommunityPost post1 = communityPostRepository.save(new CommunityPost(member, "동행구해요-핸썸-"));
//        CommunityPost post2 = communityPostRepository.save(new CommunityPost(member, "동행 안구해요 절대"));
//
//        communityPostRepository.delete(post2);
//        List<CommunityPost> communityPosts = communityPostRepository.findAllByMember(member);
//        // then
//        assertEquals(1, communityPosts.size());
//
//    }
//
//
//
//}
