package com.loginscreen.repository.interfaces;

import java.util.Optional;

import com.loginscreen.model.User;

public interface UserGateway {

	User add(User user);
	Optional<User> findById(String email);
	User update(User user);
	boolean existsById(String email);
}
