package com.loginscreen.exceptions;

public class InvalidEmailFormat extends LoginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5408021636241736340L;
	
	@Override
	public String getMessage() {
		return "Invalid Email format!";
	}

}
