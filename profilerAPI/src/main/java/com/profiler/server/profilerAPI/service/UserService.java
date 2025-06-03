package com.profiler.server.profilerAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profiler.server.profilerAPI.exception.UserNotFoundException;
import com.profiler.server.profilerAPI.model.User;
import com.profiler.server.profilerAPI.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	public List<User> getUsers (){
		return userRepo.findAll();
	}
	
	public User getUserById (String id) {
		if (userRepo.findById(id).isPresent())
			return userRepo.findById(id).get();
		else throw new UserNotFoundException("User with id " + id + " does not exist");
	}
	
	public User updateUser(User user) {
		User updatedUser = userRepo.findById(user.getId()).get();
		updatedUser.setUsername(user.getUsername());
		updatedUser.setPassword(user.getPassword());
		updatedUser.setEmail(user.getEmail());
		return userRepo.save(updatedUser);
	}
	
	public void deleteUser (String id) {
		if (userRepo.findById(id).isPresent())
			userRepo.deleteById(id);
		else throw new UserNotFoundException("User with id " + id + " does not exist");
	}


}
