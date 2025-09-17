package com.profiler.server.profilerAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.profiler.server.profilerAPI.model.ImageFile;

public interface ImageRepository extends JpaRepository<ImageFile, Long> {
}