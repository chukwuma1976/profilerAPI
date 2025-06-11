package com.profiler.server.profilerAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.profiler.server.profilerAPI.model.User;
import com.profiler.server.profilerAPI.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/profiler/users")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {
	@Autowired UserService userService;
	
	@GetMapping()
	@Operation(summary="This returns all users")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="success",
	content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})}
	)
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@GetMapping("/id/{id}")
	@Operation(summary="This returns a user with a matching id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="404", description="user not found")
	})
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/username/{username}")
	@Operation(summary="This returns a user with a matching user name")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success",
					content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)}),
			@ApiResponse(responseCode="404", description="user not found")
	})
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/{id}")
	@Operation(summary="This updates an existing user")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="success",
	content= {@Content(mediaType=MediaType.APPLICATION_JSON_VALUE)})}
	)
	public User updateUser (@Valid @PathVariable String id, @RequestBody User user) {
		userService.getUserById(user.getId());
		return userService.updateUser(user);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary="This deletes an existing user")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200", description="success"),
			@ApiResponse(responseCode="404", description="user not found")
	})
	public ResponseEntity<User> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}

}
