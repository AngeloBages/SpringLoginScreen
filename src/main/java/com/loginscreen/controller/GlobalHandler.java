package com.loginscreen.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalHandler {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, RedirectAttributes ra) {
		ra.addFlashAttribute("errorMessage", "Unexpected error: " + ex.getMessage());
		return "redirect:/login";
	}
}
