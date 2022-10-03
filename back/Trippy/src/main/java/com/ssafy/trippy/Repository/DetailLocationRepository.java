package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetailLocationRepository extends JpaRepository<DetailLocation, Long> {
//    List<DetailLocation> findDetailLocationById(Long id);

    Optional<List<DetailLocation>> findAllByPostId(Long postId);
}