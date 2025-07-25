package com.profiler.server.profilerAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.exception.ResumeNotFoundException;
import com.profiler.server.profilerAPI.model.Education;
import com.profiler.server.profilerAPI.model.Resume;
import com.profiler.server.profilerAPI.repository.EducationRepository;
import com.profiler.server.profilerAPI.repository.ResumeRepository;

@Service
public class EducationService {

	@Autowired
	private EducationRepository educationRepo;
	@Autowired
	private ResumeRepository resumeRepo;
	
	public Education getEducationById(Long educationId) {
		if (educationRepo.findById(educationId).isPresent()){
			return educationRepo.findById(educationId).get();
		}
		return null;
	}
	
	public Education addEducation(Long resumeId, Education education ) {
		if (this.resumeRepo.findById(resumeId).isPresent()) {
			Resume resume = this.resumeRepo.findById(resumeId).get();
			education.setResume(resume);
			return educationRepo.save(education);
		} else throw new ResumeNotFoundException("Resume with id of " + resumeId + " not found");
	}
	
	public Education updateEducation(Education education) {
		Education updatedEducation = this.educationRepo.findById(education.getId()).get();
		
		updatedEducation.setInstitution(education.getInstitution());
		updatedEducation.setFieldOfStudy(education.getFieldOfStudy());
		updatedEducation.setDescriptionEdu(education.getDescriptionEdu());
		updatedEducation.setCity(education.getCity());
		updatedEducation.setState(education.getState());
		updatedEducation.setGraduationDate(education.getGraduationDate());
		updatedEducation.setAwards(education.getAwards());

		return educationRepo.save(updatedEducation);
	}
	
	public void deleteEducation(Long educationId) {
		if (educationRepo.findById(educationId).isPresent()) {
			educationRepo.deleteById(educationId);
		}
	}
	
}
