package com.betacom.car.exception;

public class AcademyException extends Exception{

	private static final long serialVersionUID = 1L;

	public AcademyException() {
		super();
	}
	
	public AcademyException(String message) {
		System.err.println(message);
	}
	

}
