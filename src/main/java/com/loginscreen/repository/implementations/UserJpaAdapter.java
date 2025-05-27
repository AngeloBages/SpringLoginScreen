package com.loginscreen.repository.implementations;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.loginscreen.model.User;
import com.loginscreen.repository.interfaces.UserGateway;
import com.loginscreen.repository.interfaces.UserJpaRepository;

@Repository
public class UserJpaAdapter implements UserGateway{

	private final UserJpaRepository repository;
	
	public UserJpaAdapter(UserJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public User add(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> findById(String email) {
		return repository.findById(email);
	}

	@Override
	public User update(User user) {
	repository.update(user.getPassword(), user.getEmail());
	return user;
	}
	
	@Override
	public boolean existsById(String email) {
		return repository.existsById(email);
	}
}
