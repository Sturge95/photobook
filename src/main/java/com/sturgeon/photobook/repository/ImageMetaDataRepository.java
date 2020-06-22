package com.sturgeon.photobook.repository;

import com.sturgeon.photobook.bo.ImageMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMetaDataRepository extends JpaRepository<ImageMetaData, Long> {
}
