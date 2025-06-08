package com.profiler.server.profilerAPI.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.profiler.server.profilerAPI.model.Resume;
import com.profiler.server.profilerAPI.service.ResumeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/profiler/resumes")

public class ResumeController {
	@Autowired
	private ResumeService resumeService;
	
	@PostMapping("/{userId}")
	@Operation(summary="This creates a new resume")
	@ApiResponses(value = {
			@ApiResponse(responseCode="201", description="accepted",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="422", description="unprocessable entity")
	})
	public ResponseEntity<Resume> createResume(@Valid @RequestBody Resume resume, @PathVariable String userId){
		Resume createdResume = resumeService.addResumeByUser(resume, userId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(resume.getId()).toUri();
		return ResponseEntity.created(location).body(createdResume);
	}
	
	@GetMapping()
	@Operation(summary="This returns all sharable resumes")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="success",
	content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})}
	)
	public List<Resume> getResumes(){
		return resumeService.getAllResumes();
	}
	
	@GetMapping("/{id}")
	@Operation(summary="This returns a resume with a matching id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="404", description="resume not found")
	})
	public ResponseEntity<Resume> getResumeById(@PathVariable String id) {
		Resume foundResume = resumeService.getResumeById(id);
		return ResponseEntity.ok(foundResume);
	}
	
	@GetMapping("user/{id}")
	@Operation(summary="This returns resumes from a matching user")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="404", description="resume not found")
	})
	public List<Resume> getResumeByUser(@PathVariable String id) {
		return resumeService.getResumesByUserId(id);
	}
	
	@PutMapping("/{id}")
	@Operation(summary="This updates an existing resume")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="success",
	content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})}
	)
	public Resume updateResume (@Valid @PathVariable String id, @RequestBody Resume resume) {
		resumeService.getResumeById(resume.getId());
		return resumeService.updateResume(resume);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary="This deletes an existing resume")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success"),
			@ApiResponse(responseCode="404", description="resume not found")
	})
	public ResponseEntity<Resume> deleteResume(@PathVariable String id){
		resumeService.deleteResume(id);
		return ResponseEntity.ok().build();
	}


}
