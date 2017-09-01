package com.cfw.movies.commons.exception;

public class UnknownFileTypeException extends ServiceException {

	private static final long serialVersionUID = -353096963648080144L;

	public UnknownFileTypeException(String message) {
		super(message);
	}
	
	public UnknownFileTypeException(String message, Throwable cause) {
		super(message, cause);
	}

}
