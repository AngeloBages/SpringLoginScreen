package com.loginscreen.controller;

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
@RequestMapping("/login")
public class LoginController {
	
	private final UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping
	public String logIn(@ModelAttribute User user) {
		userService.authenticate(user);
		return "redirect:home";
	}
	
	@ExceptionHandler(LoginException.class)
	public String handleLoginException(LoginException ex, RedirectAttributes ra) {
		ra.addFlashAttribute("errorMessage", ex.getMessage());
		return "redirect:/login";
	}
}
