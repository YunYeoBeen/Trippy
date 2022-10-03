package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.PostComment;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomPostCommentRepository {
    List<PostComment> findPostCommentByPostId(Long postId);
}
