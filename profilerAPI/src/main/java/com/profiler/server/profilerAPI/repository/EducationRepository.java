package com.profiler.server.profilerAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.profiler.server.profilerAPI.model.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

}
