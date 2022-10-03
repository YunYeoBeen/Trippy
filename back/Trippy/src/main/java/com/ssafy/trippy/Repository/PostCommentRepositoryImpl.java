package com.ssafy.trippy.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trippy.Domain.PostComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.trippy.Domain.QPostComment.postComment;

@Repository
@RequiredArgsConstructor
public class PostCommentRepositoryImpl implements CustomPostCommentRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<PostComment> findPostCommentByPostId(Long postId) {
        return queryFactory.selectFrom(postComment)
                .leftJoin(postComment.parent)
                .fetchJoin()
                .where(postComment.post.id.eq(postId))
                .orderBy(
                        postComment.parent.id.asc().nullsFirst(),
                        postComment.createdAt.asc()
                ).fetch();
    }
}
