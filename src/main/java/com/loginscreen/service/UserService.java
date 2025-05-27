package com.loginscreen.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loginscreen.exceptions.EmailNotFoundException;
import com.loginscreen.exceptions.IncorrectPasswordException;
import com.loginscreen.exceptions.InvalidEmailFormat;
import com.loginscreen.exceptions.InvalidPasswordFormatException;
import com.loginscreen.model.User;
import com.loginscreen.repository.interfaces.UserGateway;

@Service
public class UserService {

	private final UserGateway repository;
	private final AuthenticationService authentication;
	
	public UserService(UserGateway repository, AuthenticationService authentication) {
		this.repository = repository;
		this.authentication = authentication;
	}
	
	@Transactional
	public User registerUser(User user) {
	    if(!authentication.validateEmail(user.getEmail())) throw new InvalidEmailFormat();

	    if (repository.existsById(user.getEmail())) {
	        throw new DataIntegrityViolationException("Email already in use!");
	    }

	    List<String> passwordErrors = authentication.getPasswordValidationErrors(user.getPassword());
	    if (!passwordErrors.isEmpty()) {
	        throw new InvalidPasswordFormatException(String.join("<br>", passwordErrors));
	    }

	    return repository.add(user);
	}
	
	@Transactional
	public User changePassword(String email, String newPassword, String passwordConfirmation) {
		if(!repository.existsById(email)) throw new EmailNotFoundException();
	
		List<String> passwordErrors = authentication.getPasswordValidationErrors(newPassword);
	    if (!passwordErrors.isEmpty()) {
	        throw new InvalidPasswordFormatException(String.join("<br>", passwordErrors));
	    }
		if(!newPassword.equals(passwordConfirmation)) throw new IncorrectPasswordException("The Passwords don't match");
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(newPassword);
		
		return repository.update(user);
	}
	
	public void authenticate(User user) {
		User registeredUser = repository.findById(user.getEmail())
				.orElseThrow(() -> new EmailNotFoundException());
		
		if(!user.getPassword().equals(registeredUser.getPassword())) {
			throw new IncorrectPasswordException("Incorrect password!");
		}
	}
}
