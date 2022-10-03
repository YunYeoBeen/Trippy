package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostTransport;
import com.ssafy.trippy.Domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repositorye/BE/Transport
public interface PostTransportRepository extends JpaRepository<PostTransport, Long> {

    List<PostTransport> findAllPostTransportByPost(Post post);
public interface PostTransportRepository extends JpaRepository<PostTransport, Long>, PostTransportRepositoryCustom {
    List<PostTransport> findAllPostTransportByPost(Post post);
    List<PostTransport> findAllPostTransportByTransport(Transport transport);
}