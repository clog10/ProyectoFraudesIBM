package com.ibm.academia.restapi.fraudes.excepciones;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String mensaje) {
		super(mensaje);
	}
	
	private static final long serialVersionUID = -4967452910201282318L;
}
