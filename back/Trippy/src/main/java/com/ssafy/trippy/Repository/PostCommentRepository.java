package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.Post;
import com.ssafy.trippy.Entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findAllByPost(Post post);

    @Override
    Optional<PostComment> findById(Long aLong);
}