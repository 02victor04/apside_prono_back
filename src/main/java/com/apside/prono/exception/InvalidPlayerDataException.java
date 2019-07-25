package com.apside.prono.exception;

public class InvalidPlayerDataException extends PronosticException{
	
	public InvalidPlayerDataException() {
		super("The player has invalid data or no data ! ");
	}

}
