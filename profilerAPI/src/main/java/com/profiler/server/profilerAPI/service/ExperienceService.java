package com.profiler.server.profilerAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.exception.ResumeNotFoundException;
import com.profiler.server.profilerAPI.model.Experience;
import com.profiler.server.profilerAPI.model.Resume;
import com.profiler.server.profilerAPI.repository.ExperienceRepository;
import com.profiler.server.profilerAPI.repository.ResumeRepository;

@Service
public class ExperienceService {

	@Autowired
	private ExperienceRepository experienceRepo;
	@Autowired
	private ResumeRepository resumeRepo;
	
	public Experience getExperienceById(Long experienceId) {
		if (experienceRepo.findById(experienceId).isPresent()){
			return experienceRepo.findById(experienceId).get();
		}
		return null;
	}
	
	public Experience addExperience(Long resumeId, Experience experience ) {
		if (this.resumeRepo.findById(resumeId).isPresent()) {
			Resume resume = this.resumeRepo.findById(resumeId).get();
			experience.setResume(resume);
			return experienceRepo.save(experience);
		} else throw new ResumeNotFoundException("Resume with id of " + resumeId + " not found");
	}
	
	public Experience updateExperience(Experience experience) {
		Experience updatedExperience = this.experienceRepo.findById(experience.getId()).get();
		
		updatedExperience.setEmployer(experience.getEmployer());
		updatedExperience.setDescription(experience.getDescription());
		updatedExperience.setTitle(experience.getTitle());
		updatedExperience.setCity(experience.getCity());
		updatedExperience.setState(experience.getState());
		updatedExperience.setStartDate(experience.getStartDate());
		updatedExperience.setEndDate(experience.getEndDate());

		return experienceRepo.save(updatedExperience);
	}
	
	public void deleteExperience(Long experienceId) {
		if (experienceRepo.findById(experienceId).isPresent()) {
			experienceRepo.deleteById(experienceId);
		}
	}
	
}
