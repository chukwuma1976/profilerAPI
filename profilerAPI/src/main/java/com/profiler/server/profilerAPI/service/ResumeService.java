package com.profiler.server.profilerAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.exception.ResumeNotFoundException;
import com.profiler.server.profilerAPI.exception.UserNotFoundException;
import com.profiler.server.profilerAPI.model.Education;
import com.profiler.server.profilerAPI.model.Experience;
import com.profiler.server.profilerAPI.model.Resume;
import com.profiler.server.profilerAPI.repository.EducationRepository;
import com.profiler.server.profilerAPI.repository.ExperienceRepository;
import com.profiler.server.profilerAPI.repository.ResumeRepository;
import com.profiler.server.profilerAPI.repository.UserRepository;

@Service
public class ResumeService {

	@Autowired
	private ResumeRepository resumeRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ExperienceRepository experienceRepo;
	@Autowired
	private EducationRepository educationRepo;
	
	public Resume createResume (Resume resume) {
		return this.resumeRepo.save(resume);
	}
	
	public Resume addResumeByUser (Resume resume, Long userId) {
		if (this.userRepo.findById(userId).isPresent()) {
			resume.setUserId(userId);
			Resume newResume = this.resumeRepo.save(resume);
			for (Experience experience : newResume.getExperience()) {
				experience.setResume(newResume);
				this.experienceRepo.save(experience);
			}
			for (Education education : newResume.getEducation()) {
				education.setResume(newResume);
				this.educationRepo.save(education);
			}
			resumeRepo.save(newResume);
			return newResume;
		} else throw new UserNotFoundException("User with id " + userId + " does not exist");
	}
	
//	public void removeChildren (Resume resume) {
//		for (Experience experience : resume.getExperience()) {
//			this.experienceRepo.deleteById(experience.getId());
//		}
//		for (Education education : resume.getEducation()) {
//			this.educationRepo.deleteById(education.getId());
//		}
//	}
	
	//This gets all resumes whether they can be shared or not, ONLY FOR TESTING
	public List<Resume> getAllSharedAndUnsharedResumes(){
		return this.resumeRepo.findAll();
	}
	
	//This only gets Resumes that are allowed to be shared with other users
	public List<Resume> getAllResumes(){
		return this.resumeRepo.findAllResumesThatCanBeShared();
	}
	
	public Resume getResumeById (Long id) {
		if (this.resumeRepo.findById(id).isPresent()) 
			return this.resumeRepo.findById(id).get();
		else throw new ResumeNotFoundException("Resume with id of " + id + " not found");	
	}
	
	public List<Resume> getResumesByUserId (Long id){
		return this.resumeRepo.findResumesByUserId(id);
	}
	
	public Resume updateResume (Resume resume) {
		Resume updatedResume = this.resumeRepo.findById(resume.getId()).get();
//		this.removeChildren(updatedResume);
		
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
		
		updatedResume.setExperience(resume.getExperience());
		updatedResume.setEducation(resume.getEducation());
		
//		Resume processUpdatedResume = this.initializeResume(updatedResume, updatedResume.getUserId());
//		
//		return resumeRepo.save(processUpdatedResume);
		return resumeRepo.save(updatedResume);
	}
	
	public void deleteResume (Long id) {
		if (this.resumeRepo.findById(id).isPresent()) 
			this.resumeRepo.deleteById(id);
		else throw new ResumeNotFoundException("Resume with id of " + id + " not found");	
	}

}
