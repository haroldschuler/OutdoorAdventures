package com.harold.outdooradventures.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.harold.outdooradventures.models.LoginUser;
import com.harold.outdooradventures.models.User;
import com.harold.outdooradventures.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser) {
		newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
		return userRepo.save(newUser);
	}
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		if(findUserByEmail(newLoginObject.getEmail()) == null) {
			result.rejectValue("email", "no_match", "Incorrect email address");
			return null;
		}
		else if(!BCrypt.checkpw(newLoginObject.getPassword(), findUserByEmail(newLoginObject.getEmail()).getPassword())) {
			result.rejectValue("password", "incorrect", "Incorrect password");
			return null;
		}
		return findUserByEmail(newLoginObject.getEmail());
	}
	
	public User findUserByEmail(String email) {
		Optional<User> potentialUser = userRepo.findByEmail(email);
		if(potentialUser.isPresent()) {
			return potentialUser.get();
		}
		else {
			return null;
		}
	}
	public User findUserById(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if(potentialUser.isPresent()) {
			return potentialUser.get();
		}
		else {			
			return null;
		}
	}
}
