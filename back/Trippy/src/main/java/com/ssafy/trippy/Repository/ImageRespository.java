package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRespository extends JpaRepository<Image, Long> {
    void deleteByFileName(String fileName);

    List<Image> findAllByDetailLocationId(Long detailLocationId);
}
