package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByCityNameAndCountryName(String cityName, String countryName);
    Optional<Location> findByCountryNameAndCityName(String countryName, String cityName);
}
