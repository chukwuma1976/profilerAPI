package com.profiler.server.profilerAPI.service;

import java.util.List;
import java.util.UUID;

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
	
	public User getUserById (Long id) {
		if (userRepo.findById(id).isPresent())
			return userRepo.findById(id).get();
		else throw new UserNotFoundException("User with id " + id + " does not exist");
	}
	
	public User getUserByUsername(String username) {
		if (userRepo.findByUsername(username) != null)
			return userRepo.findByUsername(username);
		else throw new UserNotFoundException("User with username " + username + "does not exist");
	}
	
	public User updateUser(User user) {
		User updatedUser = userRepo.findById(user.getId()).get();
		updatedUser.setPassword(user.getPassword());
		updatedUser.setEmail(user.getEmail());
		return userRepo.save(updatedUser);
	}
	
	public void deleteUser (Long id) {
		if (userRepo.findById(id).isPresent())
			userRepo.deleteById(id);
		else throw new UserNotFoundException("User with id " + id + " does not exist");
	}

    public String initiatePasswordReset(String usernameOrEmail) {
        User user = userRepo.findByUsername(usernameOrEmail);
        if (user == null) {
            user = userRepo.findByEmail(usernameOrEmail);
        }
        if (user == null) {
            return null; // user not found
        }

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepo.save(user);

        // TODO: send token by email instead of returning
        return token;
    }

    public boolean resetPassword(String token, String newPassword) {
        User user = userRepo.findByResetToken(token);
        if (user == null) {
            return false; // invalid token
        }

        user.setPassword(newPassword); // ⚠️ hash with PasswordEncoder in production
        user.setResetToken(null); // clear token after use
        userRepo.save(user);

        return true;
    }
}
