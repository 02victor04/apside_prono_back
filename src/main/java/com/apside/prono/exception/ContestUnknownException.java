package com.apside.prono.exception;

public class ContestUnknownException extends PronosticException{
	
	public ContestUnknownException(long idContest) {
		super("The player with the ID " + idContest + " doesn't exist !");
	}

}
