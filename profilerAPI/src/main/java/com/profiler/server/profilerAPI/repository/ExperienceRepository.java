package com.profiler.server.profilerAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profiler.server.profilerAPI.model.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

}
