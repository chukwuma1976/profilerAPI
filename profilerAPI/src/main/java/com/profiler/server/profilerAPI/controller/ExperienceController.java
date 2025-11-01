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

import com.profiler.server.profilerAPI.model.Experience;
import com.profiler.server.profilerAPI.model.Resume;
import com.profiler.server.profilerAPI.service.ExperienceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/profiler/experiences")
public class ExperienceController {
	@Autowired
	ExperienceService experienceService;
	
	@GetMapping("/{id}")
	@Operation(summary="This returns an experience with a matching id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="404", description="experience not found")
	})
	public ResponseEntity<Experience> getExperienceById(@PathVariable Long id) {
		Experience foundExperience = experienceService.getExperienceById(id);
		return ResponseEntity.ok(foundExperience);
	}

	@PostMapping("resume/{resumeId}")
	@Operation(summary="This creates and adds experience under a resume id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="201", description="accepted",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="422", description="unprocessable entity")
	})
	public ResponseEntity<Experience> createExperience(@Valid @RequestBody Experience experience, @PathVariable Long resumeId){
		Experience createdExperience = experienceService.addExperience(resumeId, experience);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(experience.getId()).toUri();
		return ResponseEntity.created(location).body(createdExperience);
	}
	
	@PutMapping("/{id}")
	@Operation(summary="This updates an existing experience")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="success",
	content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})}
	)
	public Experience updateExperience (@Valid @PathVariable Long id, @RequestBody Experience experience) {
		experienceService.getExperienceById(id);
		return experienceService.updateExperience(experience);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary="This deletes an existing resume")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success"),
			@ApiResponse(responseCode="404", description="resume not found")
	})
	public ResponseEntity<Experience> deleteExperience(@PathVariable Long id){
		experienceService.deleteExperience(id);
		return ResponseEntity.ok().build();
	}
	
}
