package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.PostTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTransportRepository extends JpaRepository<PostTransport, Long> {
}