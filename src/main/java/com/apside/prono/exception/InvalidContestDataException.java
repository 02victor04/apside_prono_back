package com.apside.prono.exception;

public class InvalidContestDataException extends PronosticException{
	
	public InvalidContestDataException() {
		super("The contest has invalid label or no label ! ");
	}

}
