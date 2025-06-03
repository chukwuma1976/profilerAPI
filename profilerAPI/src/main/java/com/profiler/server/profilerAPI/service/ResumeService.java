package com.profiler.server.profilerAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.exception.ResumeNotFoundException;
import com.profiler.server.profilerAPI.exception.UserNotFoundException;
import com.profiler.server.profilerAPI.model.Resume;
import com.profiler.server.profilerAPI.model.User;
import com.profiler.server.profilerAPI.repository.ResumeRepository;
import com.profiler.server.profilerAPI.repository.UserRepository;

@Service
public class ResumeService {

	@Autowired
	private ResumeRepository resumeRepo;
	@Autowired
	private UserRepository userRepo;
	
	public Resume createResume (Resume resume) {
		return this.resumeRepo.save(resume);
	}
	
	public Resume addResumeByUser (Resume resume, String userId) {
		if (this.userRepo.findById(userId).isPresent()) {
			User user = this.userRepo.findById(userId).get();
			resume.setUser(user);
			ArrayList<Resume> resumes = user.getResumes();
			resumes.add(resume);
			user.setResumes(resumes);
			this.userRepo.save(user);
			return resume;
		} else throw new UserNotFoundException("User with id " + userId + " does not exist");
	}
	
	//This only gets Resumes that are allowed to be shared with other users
	public List<Resume> getAllResumes(){
		return this.resumeRepo.findAllResumesThatCanBeShared();
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
