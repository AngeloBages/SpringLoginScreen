package com.loginscreen.exceptions;

public class IncorrectPasswordException extends LoginException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1340541251817710899L;

	public IncorrectPasswordException() {
		super();
	}
	
	public IncorrectPasswordException(String message) {
		super(message);
	}
}
