package com.assignment.test.exception;

public class ResourceNotCreated extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public ResourceNotCreated(String message) {
		super(String.format("%s resource not created", message));
		this.message = message;
	}

}
