//package com.ssafy.trippy.ServiceTest;
//
//import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
//import com.ssafy.trippy.Entity.Bookmark;
//import com.ssafy.trippy.Entity.Location;
//import com.ssafy.trippy.Entity.Member;
//import com.ssafy.trippy.Entity.Post;
//import com.ssafy.trippy.Repository.BookmarkRepository;
//import com.ssafy.trippy.Repository.LocationRepository;
//import com.ssafy.trippy.Repository.MemberRepository;
//import com.ssafy.trippy.Repository.PostRepository;
//import com.ssafy.trippy.Service.CommunityPostService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@SpringBootTest
//@Transactional
//public class CommunityPostServiceTest {
//
//    @Autowired
//    private CommunityPostService communityPostService;
//
//    @Autowired
//    private LocationRepository locationRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    @Rollback(false)
//    public void 동행찾기글등록 () throws Exception {
//        // given
//        Member member = new Member();
//        member.setEmail("tommy7899@dsadsa.com");
//        memberRepository.save(member);
//        Location location = new Location(2L,"서울","죽전",111.111,111.11);
//        locationRepository.save(location);
//        RequestCommunityPostDto requestCommunityPostDto = RequestCommunityPostDto.builder()
//                .title("동행찾기")
//                .description("동행찾아요")
//                .memberId(member.getId())
//                .locationId(location.getId())
//                .build();
//
//        // when
//        Long id = communityPostService.saveCommunityPost(requestCommunityPostDto);
//        // then
//        assertEquals(3,id);
//    }
//
//}
