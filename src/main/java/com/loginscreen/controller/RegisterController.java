package com.loginscreen.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loginscreen.exceptions.LoginException;
import com.loginscreen.model.User;
import com.loginscreen.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	private final UserService userService;
	
	public RegisterController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String showRegisterPage() {
		return "register";
	}
	
	@PostMapping
	public String registerAccount(@ModelAttribute User user) {
		userService.registerUser(user);
		return "redirect:/login";
	}
	
	@ExceptionHandler(LoginException.class)
	public String handleLoginException(LoginException ex, RedirectAttributes ra) {
		ra.addFlashAttribute("errorMessage", ex.getMessage());
		return "redirect:/register";
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public String handleDataIntegrityViolation(DataIntegrityViolationException ex, RedirectAttributes redirectAttributes) {
	    redirectAttributes.addFlashAttribute("errorMessage", "Email already in use!");
	    return "redirect:/register";
	}
}
