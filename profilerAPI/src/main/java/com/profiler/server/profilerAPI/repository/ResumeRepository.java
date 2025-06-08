package com.profiler.server.profilerAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.profiler.server.profilerAPI.model.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

	List<Resume> findResumesByUserId(Long id);
	
	@Query("SELECT r FROM Resume r WHERE r.shareWithOthers = true")
	List<Resume> findAllResumesThatCanBeShared();
}
