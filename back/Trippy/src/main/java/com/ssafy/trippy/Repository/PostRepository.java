package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByCompany(int count);
    List<Post> findAllByMember(Member member);
    Optional<Post> findById(Long id);
    Optional<List<Post>> findAllByLocationId(Long location_id);
}