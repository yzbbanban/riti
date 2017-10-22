package com.riti.cool.service.exception;


public class UserNameException extends RuntimeException {
	private static final long serialVersionUID = 6435296194529486206L;

	public UserNameException() {
	}

	public UserNameException(String message) {
		super(message);
	}

	public UserNameException(Throwable cause) {
		super(cause);
	}

	public UserNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
