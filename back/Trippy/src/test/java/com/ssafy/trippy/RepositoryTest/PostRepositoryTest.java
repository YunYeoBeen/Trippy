//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Domain.Member;
//import com.ssafy.trippy.Domain.Post;
//import com.ssafy.trippy.Repository.MemberRepository;
//import com.ssafy.trippy.Repository.PostRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class PostRepositoryTest {
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void 게시글등록() throws Exception {
//        // given
//        Member m = memberRepository.save(new Member("A@naver.com"));
//        Post post = postRepository.save(new Post(3,m));
//        // when
//        // then
//        assertEquals(post, postRepository.findByCompany(3).get());
//
//    }
//
//    @Test
//    public void 게시글삭제() throws Exception {
//        // given
//        Member m = memberRepository.save(new Member("A@naver.com"));
//        Post post = postRepository.save(new Post(3,m));
//        // when
//        postRepository.delete(post);
//        // then
//        assertEquals(false, postRepository.findByCompany(3).isPresent());
//
//    }
//
//    @Test
//    public void 작성자의게시글들 () throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        Post post1 = postRepository.save(new Post(3,member));
//        Post post2 = postRepository.save(new Post(4,member));
//        // when
//        List<Post> postList = postRepository.findAllByMember(member);
//        // then
//        assertEquals(2, postList.size());
//    }
//}
