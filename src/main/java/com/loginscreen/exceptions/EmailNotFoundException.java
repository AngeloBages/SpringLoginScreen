package com.loginscreen.exceptions;

public class EmailNotFoundException extends LoginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2766215309043591130L;

	@Override
	public String getMessage() {
		return "Email not registered!";
	}
}
