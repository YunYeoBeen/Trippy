package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.CommunityPost;
import com.ssafy.trippy.Domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.PriorityQueue;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByPostId(Long routeId);
}