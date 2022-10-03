//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Domain.LikePost;
//import com.ssafy.trippy.Domain.Member;
//import com.ssafy.trippy.Domain.Post;
//import com.ssafy.trippy.Repository.LikePostRepository;
//import com.ssafy.trippy.Repository.MemberRepository;
//import com.ssafy.trippy.Repository.PostRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class LikePostRepositoryTest {
//
//    @Autowired
//    PostRepository postRepository;
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    LikePostRepository likePostRepository;
//
//    @Test
//    public void 본인이누른좋아요게시글  () throws Exception {
//        // given
//        Member memberA = memberRepository.save(new Member("a@naver.com"));
//        Member memberB = memberRepository.save(new Member("B@naver.com"));
//
//        // B가 게시글을 2개 등록
//        Post postB = postRepository.save(new Post(4,memberB));
//        Post postC = postRepository.save(new Post(5,memberB));
//        // when
//
//        // memberA가 b의 게시글을 좋아요 누름
//        // 테스트한 방법 2개
//        // 1) Member테이블에 있는 LikePosts 리스트 활용
//        // 2) LikePostRepository에 있는 findAllBy 활용
//
//        // 1)
//        // like(service)
//        LikePost likePost1 = likePostRepository.save(new LikePost(postB,memberA));
//        LikePost likePost2 = likePostRepository.save(new LikePost(postC,memberA));
//        memberA.getLikePosts().add(likePost1);
//        memberA.getLikePosts().add(likePost2);
//
//        // unlike(service)
//        likePostRepository.delete(likePost1);
//        memberA.getLikePosts().remove(likePost1);
//        // 2)
////        List<LikePost> AlikePosts = likePostRepository.findAllLikePostByMember(memberA);
//
//        // then
//        assertEquals(1, memberA.getLikePosts().size());
//
//    }
//
//
//
//
//
//}
