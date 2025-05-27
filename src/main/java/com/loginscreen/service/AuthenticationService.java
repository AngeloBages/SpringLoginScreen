package com.loginscreen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	private static List<PasswordRule> passwordRules = List.of(new PasswordRule(".*[a-z].*", "-The password must contain lowercase letters"),
			new PasswordRule(".*[A-Z].*", "-The password must contain capital letters"),
			new PasswordRule(".*[!@#\\$%\\^&\\*()_\\+\\-=\\[\\]\\{\\}\\|;:'\\\",\\.<>\\?/]+.*", "-The password must contain special characters"),
			new PasswordRule(".*\\d.*", "-The password must contain numbers"),
			new PasswordRule(".*.{8,}.*", "-The password must be at least 8 characters long"));

	public boolean validateEmail(String email) {
		return email.matches("\\w+@[a-z]+\\.[a-z]{2,3}");
	}
	
	public List<String> getPasswordValidationErrors(String password) {
	    List<String> errors = new ArrayList<>();
	    for (PasswordRule pr : passwordRules) {
	        if (!Pattern.matches(pr.rule, password)) {
	            errors.add(pr.message);
	        }
	    }
	    return errors;
	}
	

	private static class PasswordRule {
		String rule;
		String message;
		
		PasswordRule(String pattern, String rule){
			this.rule = pattern;
			this.message = rule;
		}
	}
}
