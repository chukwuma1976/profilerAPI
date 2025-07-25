package com.profiler.server.profilerAPI.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.profiler.server.profilerAPI.model.Education;
import com.profiler.server.profilerAPI.model.Education;
import com.profiler.server.profilerAPI.service.EducationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/profiler/educations")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {
	
	@Autowired
	EducationService educationService;
	
	@GetMapping("/{id}")
	@Operation(summary="This returns an education with a matching id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="404", description="education not found")
	})
	public ResponseEntity<Education> getEducationById(@PathVariable Long id) {
		Education foundEducation = educationService.getEducationById(id);
		return ResponseEntity.ok(foundEducation);
	}

	@PostMapping("resume/{resumeId}")
	@Operation(summary="This creates and adds education under a resume id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="201", description="accepted",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="422", description="unprocessable entity")
	})
	public ResponseEntity<Education> createEducation(@Valid @RequestBody Education education, @PathVariable Long resumeId){
		Education createdEducation = educationService.addEducation(resumeId, education);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(education.getId()).toUri();
		return ResponseEntity.created(location).body(createdEducation);
	}
	
	@PutMapping("/{id}")
	@Operation(summary="This updates an existing education")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="success",
	content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})}
	)
	public Education updateEducation (@Valid @PathVariable Long id, @RequestBody Education education) {
		educationService.getEducationById(id);
		return educationService.updateEducation(education);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary="This deletes an existing education")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success"),
			@ApiResponse(responseCode="404", description="education not found")
	})
	public ResponseEntity<Education> deleteEducation(@PathVariable Long id){
		educationService.deleteEducation(id);
		return ResponseEntity.ok().build();
	}
	
}
