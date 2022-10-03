package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.Member;
import com.ssafy.trippy.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByCompany(int count);
    List<Post> findAllByMember(Member member);
    Post findById(Post post);
}