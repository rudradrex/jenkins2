package com.hcl.employee.exception;

public class IdNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public IdNotFoundException(String message) {
		super(message);
	}

}
