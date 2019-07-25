package com.apside.prono.exception;

public class PlayerUnknownException extends PronosticException{
	
	public PlayerUnknownException(long idPlayer) {
		super("The player with the ID " + idPlayer + " doesn't exist !");
	}

}
