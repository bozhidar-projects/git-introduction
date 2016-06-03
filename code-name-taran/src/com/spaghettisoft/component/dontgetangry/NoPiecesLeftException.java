package com.spaghettisoft.component.dontgetangry;

public class NoPiecesLeftException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "No pieces left!";
	
	@Override
	public String getMessage() {
		return ERROR_MESSAGE;
	}
}