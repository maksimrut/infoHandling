package com.rutkouski.infohandling.exception;

public class InfoHandlingException extends Exception {

	public InfoHandlingException() {
		super();
	}

	public InfoHandlingException(String message, Throwable cause) {
		super(message, cause);
	}

	public InfoHandlingException(String message) {
		super(message);
	}

	public InfoHandlingException(Throwable cause) {
		super(cause);
	}
}
