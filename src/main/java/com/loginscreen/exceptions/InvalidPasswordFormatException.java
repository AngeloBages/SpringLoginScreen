package com.loginscreen.exceptions;

public class InvalidPasswordFormatException extends LoginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5913786963945443717L;

	public InvalidPasswordFormatException() {
		super();
	}
	
	public InvalidPasswordFormatException(String message) {
		super(message);
	}

}
