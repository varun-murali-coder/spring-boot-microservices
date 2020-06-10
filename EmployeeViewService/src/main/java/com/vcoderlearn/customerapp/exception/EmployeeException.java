package com.vcoderlearn.customerapp.exception;

public class EmployeeException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5389095754144381712L;

	public EmployeeException(String message) {
		super(message);
	}
	
	public EmployeeException(String message,Throwable ex) {
		super(message,ex);

	}
}
