package com.loginscreen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loginscreen.exceptions.LoginException;
import com.loginscreen.service.UserService;

@Controller
@RequestMapping("/forgot-password")
public class PasswordController {

	private final UserService userService;
	
	public PasswordController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String showUpdatePasswordPage() {
		return "forgot-password";
	}
	
	@PostMapping
	public String updatePassword(@RequestParam("email") String email,
			                     @RequestParam("newPassword") String newPassword,
			                     @RequestParam("passwordConfirmation") String passwordConfirmation) {
		
		userService.changePassword(email, newPassword, passwordConfirmation);
		return "redirect:/login";
	}
	
	@ExceptionHandler(LoginException.class)
	public String handleLoginException(LoginException ex, RedirectAttributes ra) {
		ra.addFlashAttribute("errorMessage", ex.getMessage());
		return "redirect:/forgot-password";
	}
}
