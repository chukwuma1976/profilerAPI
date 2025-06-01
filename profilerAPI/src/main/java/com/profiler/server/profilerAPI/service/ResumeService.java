package com.profiler.server.profilerAPI.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.exception.ResumeNotFoundException;
import com.profiler.server.profilerAPI.model.Resume;
import com.profiler.server.profilerAPI.repository.ResumeRepository;

@Service
public class ResumeService {

	private ResumeRepository resumeRepo;
	
	public ResumeService(ResumeRepository resumeRepo) {
		super();
		this.resumeRepo = resumeRepo;
	}
	
	public Resume createResume (Resume resume) {
		return this.resumeRepo.save(resume);
	}
	
	public List<Resume> getAllResumes(){
		return this.resumeRepo.findAll();
	}
	
	public Resume getResumeById (String id) {
		if (this.resumeRepo.findById(id).isPresent()) 
			return this.resumeRepo.findById(id).get();
		else throw new ResumeNotFoundException("Resume with id of " + id + " not found");	
	}
	
	public List<Resume> getResumesByUserId (String id){
		return this.resumeRepo.findResumesByUserId(id);
	}
	
	public Resume updateResume (Resume resume) {
		Resume updatedResume = resumeRepo.findById(resume.getId()).get();
		
		updatedResume.setFirstName(resume.getFirstName());
		updatedResume.setLastName(resume.getLastName());
		updatedResume.setPhoneNumber(resume.getPhoneNumber());
		updatedResume.setEmail(resume.getEmail());
		updatedResume.setLinkedIn(resume.getLinkedIn());
		updatedResume.setWebsite(resume.getWebsite());
		updatedResume.setSummary(resume.getSummary());
		updatedResume.setExperience(resume.getExperience());
		updatedResume.setEducation(resume.getEducation());
		updatedResume.setSkills(resume.getSkills());
		updatedResume.setAdditionalInfo(resume.getAdditionalInfo());
		updatedResume.setShareWithOthers(resume.isShareWithOthers());
		
		return resumeRepo.save(updatedResume);
	}
	
	public void deleteResume (String id) {
		if (this.resumeRepo.findById(id).isPresent()) 
			this.resumeRepo.deleteById(id);
		else throw new ResumeNotFoundException("Resume with id of " + id + " not found");	
	}

}
