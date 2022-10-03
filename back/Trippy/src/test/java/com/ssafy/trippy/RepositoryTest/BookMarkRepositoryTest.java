//package com.ssafy.trippy.RepositoryTest;
//
//import com.ssafy.trippy.Domain.Bookmark;
//import com.ssafy.trippy.Domain.Member;
//import com.ssafy.trippy.Domain.Post;
//import com.ssafy.trippy.Repository.BookmarkRepository;
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
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class BookMarkRepositoryTest {
//
//    @Autowired
//    BookmarkRepository bookmarkRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Test
//    public void 본인이_북마크한_게시글_리스트_조회() throws Exception {
//        // given
//        Member member = memberRepository.save(new Member("tommy7899@naver.com"));
//        Post post = postRepository.save(new Post(3, member));
//        Post post1 = postRepository.save(new Post(4, member));
//        Post post2 = postRepository.save(new Post(5, member));
//        Post post3 = postRepository.save(new Post(6, member));
//
//        // 북마크 등록
//        Bookmark bookmark = bookmarkRepository.save(new Bookmark(member, post));
//        Bookmark bookmark1 = bookmarkRepository.save(new Bookmark(member, post1));
//        Bookmark bookmark2 = bookmarkRepository.save(new Bookmark(member, post2));
//        Bookmark bookmark3 = bookmarkRepository.save(new Bookmark(member, post3));
//
//        // when
//
//        //멤버가 등록한 북마크를 조회
//        member.getBookmarks().add(bookmark);
//        member.getBookmarks().add(bookmark1);
//        member.getBookmarks().add(bookmark2);
//        member.getBookmarks().add(bookmark3);
//
//        List<Bookmark> memberBookmarks = member.getBookmarks();
//        // then
//        assertEquals(3, member.getBookmarks().size());
//    }
//
//
//}
