package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostTransport;
import com.ssafy.trippy.Domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTransportRepository extends JpaRepository<PostTransport, Long>, PostTransportRepositoryCustom {
    List<PostTransport> findAllPostTransportByPost(Post post);
    List<PostTransport> findAllPostTransportByTransport(Transport transport);
}