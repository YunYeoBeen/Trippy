package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.PostComment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long>, CustomPostCommentRepository{
    @Query("select c from PostComment c left join fetch c.parent where c.id=:id")
    Optional<PostComment> findPostCommentByIdWithParent(@Param("id") Long id);
}
