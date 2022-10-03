//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Domain.Member;
//import com.ssafy.trippy.Domain.Post;
//import com.ssafy.trippy.Domain.PostComment;
//import com.ssafy.trippy.Repository.MemberRepository;
//import com.ssafy.trippy.Repository.PostCommentRepository;
//import com.ssafy.trippy.Repository.PostRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class PostCommentRepositoryTest {
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    PostCommentRepository postCommentRepository;
//
//    @Test
//    public void 댓글작성 () throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        Post post = postRepository.save(new Post(3,member));
//        PostComment postComment = postCommentRepository.save(new PostComment("안녕하세요 윤여빈입니다", post));
//        PostComment postComment2 = postCommentRepository.save(new PostComment("안녕하세요 홍유진입니다", post));
//
//        // when
//        List<PostComment> postComments = postCommentRepository.findAllByPost(post);
//
//        // then
//        assertEquals(2, postComments.size());
//
//    }
//
//    @Test
//    public void 댓글삭제 () throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        Post post = postRepository.save(new Post(3,member));
//        PostComment postComment = postCommentRepository.save(new PostComment("안녕하세요 윤여빈입니다", post));
//        PostComment postComment2 = postCommentRepository.save(new PostComment("안녕하세요 홍유진입니다", post));
//        // when
//        List<PostComment> postComments = postCommentRepository.findAllByPost(post);
//        postCommentRepository.delete(postComment);
//        if(postComments.contains(postComment)){
//            postComments.remove(postComment);
//        }
//        // then
//        assertEquals(1, postComments.size());
//        assertEquals(Optional.empty(), postCommentRepository.findById(postComment.getId()));
//
//
//    }
//
//}
