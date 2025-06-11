package com.profiler.server.profilerAPI.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profiler.server.profilerAPI.model.Resume;
import com.profiler.server.profilerAPI.model.User;

@Service
public class SeedDataBaseService {
	
	@Autowired
	private AuthService authService;
	@Autowired
	private ResumeService resumeService;

	public void seedData() throws JsonProcessingException{	
		List<Resume> resumes = null;
		List<User> users = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			users = objectMapper.readValue(new File("src/main/resources/static/users.json"), 
					objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(users);
		
		try {
			resumes = objectMapper.readValue(new File("src/main/resources/static/resumes.json"), 
					objectMapper.getTypeFactory().constructCollectionType(List.class, Resume.class));
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		users.forEach(user -> authService.register(user.getUsername(), user.getPassword(), user.getEmail()));
		resumeService.addResumeByUser(resumes.get(0), 1L);
		resumeService.addResumeByUser(resumes.get(4), 1L);
		resumeService.addResumeByUser(resumes.get(1), 2L);
		resumeService.addResumeByUser(resumes.get(2), 3L);
		resumeService.addResumeByUser(resumes.get(3), 3L);
		resumeService.addResumeByUser(resumes.get(5), 4L);
		resumeService.addResumeByUser(resumes.get(6), 5L);
		resumeService.addResumeByUser(resumes.get(7), 6L);
		
		System.out.println(resumes);

	}
}
