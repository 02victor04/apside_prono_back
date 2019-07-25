package com.apside.prono.exception;

public class EventUnknownException extends PronosticException{
	
	public EventUnknownException(long idEvent) {
		super("The event with the ID " + idEvent + " doesn't exist !");
	}

}
