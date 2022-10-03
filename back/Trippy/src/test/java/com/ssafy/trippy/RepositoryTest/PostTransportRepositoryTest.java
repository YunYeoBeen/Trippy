//package com.ssafy.trippy.RepositoryTest;
//import com.ssafy.trippy.Domain.Member;
//import com.ssafy.trippy.Domain.Post;
//import com.ssafy.trippy.Domain.PostTransport;
//import com.ssafy.trippy.Domain.Transport;
//import com.ssafy.trippy.Repository.MemberRepository;
//import com.ssafy.trippy.Repository.PostRepository;
//import com.ssafy.trippy.Repository.PostTransportRepository;
//import com.ssafy.trippy.Repository.TransportRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class PostTransportRepositoryTest {
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Autowired
//    PostTransportRepository postTransportRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    TransportRepository transportRepository;
//
//    @Test
//    public void  이동수단_추가_후_조회() throws Exception {
//        // given
//        String bus = "버스";
//        String walk = "뚜벅이";
//
//        // 이동수단1, 2 저장
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        Post post = postRepository.save(new Post(3,member));
//        Transport transport1 = transportRepository.save(new Transport(bus));
//        Transport transport2 = transportRepository.save(new Transport(walk));
//
//        // when
//        // post의 이동수단 List에 이동수단 저장
//        PostTransport postTransport1 = postTransportRepository.save(new PostTransport(post, transport1));
//        PostTransport postTransport2 = postTransportRepository.save(new PostTransport(post, transport2));
//
//        post.getPostTransports().add(postTransport1);
//        post.getPostTransports().add(postTransport2);
//        // then
//        assertEquals(2, post.getPostTransports().size());
//
//    }
//
//    @Test
//    public void 이동수단_삭제_후_조회() throws Exception {
//        // given
//        String bus = "버스";
//        String walk = "뚜벅이";
//
//        // 이동수단1, 2 저장
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        Post post = postRepository.save(new Post(3,member));
//        Transport transport1 = transportRepository.save(new Transport(bus));
//        Transport transport2 = transportRepository.save(new Transport(walk));
//        // when
//        // post의 이동수단 List에 이동수단 저장
//        PostTransport postTransport1 = postTransportRepository.save(new PostTransport(post, transport1));
//        PostTransport postTransport2 = postTransportRepository.save(new PostTransport(post, transport2));
//
//        post.getPostTransports().add(postTransport1);
//        post.getPostTransports().add(postTransport2);
//
//        // post의 이동수단 List에 이동수단 삭제
//        postTransportRepository.delete(postTransport1);
//        if(post.getPostTransports().contains(postTransport1)){
//            post.getPostTransports().remove(postTransport1);
//        }
//
//        // then
//        assertEquals(1, post.getPostTransports().size());
//    }
//
//
//}
