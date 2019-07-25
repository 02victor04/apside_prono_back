package com.apside.prono.exception;

public abstract class PronosticException extends RuntimeException {
	
	public PronosticException() {
	}

	public PronosticException(String message, Throwable cause) {
		super(message, cause);
	}

	public PronosticException(String message) {
		super(message);
	}

	public PronosticException(Throwable cause) {
		super(cause);
	}

}
