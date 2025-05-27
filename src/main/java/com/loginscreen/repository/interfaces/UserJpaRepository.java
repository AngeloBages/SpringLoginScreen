package com.loginscreen.repository.interfaces;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.loginscreen.model.User;

public interface UserJpaRepository extends CrudRepository<User, String>{
	
	@Modifying
	@Query("UPDATE User u SET u.password = :password WHERE u.email = :email")
	void update(@Param("password") String password, @Param("email") String email);
}
