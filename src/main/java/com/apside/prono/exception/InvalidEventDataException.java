package com.apside.prono.exception;

public class InvalidEventDataException extends PronosticException{
	
	public InvalidEventDataException() {
		super("The event has invalid data or no data ! ");
	}

}
