package com.profiler.server.profilerAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.profiler.server.profilerAPI.model.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, String> {

	List<Resume> findResumesByUserId(String user_id);
}
