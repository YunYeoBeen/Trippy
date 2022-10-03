package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Long findIdByCityNameAndCountryName(String cityName, String countryName);
}