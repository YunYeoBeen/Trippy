package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.DetailLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailLocationRepository extends JpaRepository<DetailLocation, Long> {
}