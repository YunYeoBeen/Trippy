//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Entity.DetailLocation;
//import com.ssafy.trippy.Entity.Location;
//import com.ssafy.trippy.Entity.Member;
//import com.ssafy.trippy.Entity.Post;
//import com.ssafy.trippy.Repository.DetailLocationRepository;
//import com.ssafy.trippy.Repository.LocationRepository;
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
//public class DetailLocationRepositoryTest {
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    LocationRepository locationRepository;
//
//    @Autowired
//    DetailLocationRepository detailLocationRepository;
//
//    @Test
//    public void 상세지역설정_후_조회 () throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("A@naver.com"));
//        Post post = postRepository.save(new Post(3,member));
//        Location location = locationRepository.save(new Location("Korea", "seoul"));
//        Location location2 = locationRepository.save(new Location("Korea", "daejeon"));
//        DetailLocation detailLocation = detailLocationRepository.save(new DetailLocation(post, location));
//        DetailLocation detailLocation2 = detailLocationRepository.save(new DetailLocation(post, location2));
//
//        // when
//        post.getDetailLocations().add(detailLocation);
//        post.getDetailLocations().add(detailLocation2);
//
//        // then
//        assertEquals(2, post.getDetailLocations().size());
//    }
//
//    @Test
//    public void 상세지역삭제_후_조회 () throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("A@naver.com"));
//        Post post = postRepository.save(new Post(3,member));
//        Location location = locationRepository.save(new Location("Korea", "seoul"));
//        Location location2 = locationRepository.save(new Location("Korea", "daejeon"));
//        DetailLocation detailLocation = detailLocationRepository.save(new DetailLocation(post, location));
//        DetailLocation detailLocation2 = detailLocationRepository.save(new DetailLocation(post, location2));
//
//        // when
//        post.getDetailLocations().add(detailLocation);
//        post.getDetailLocations().add(detailLocation2);
//
//        detailLocationRepository.delete(detailLocation);
//        if(post.getDetailLocations().contains(detailLocation)){
//            post.getDetailLocations().remove(detailLocation);
//        }
//
//        // then
//        assertEquals(1,post.getDetailLocations().size());
//
//    }
//}
