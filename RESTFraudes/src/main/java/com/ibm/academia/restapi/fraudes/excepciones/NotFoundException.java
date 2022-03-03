package com.ibm.academia.restapi.fraudes.excepciones;

public class NotFoundException extends RuntimeException {

	public NotFoundException(String mensaje) {
		super(mensaje);
	}

	private static final long serialVersionUID = 1116447032140106712L;
}
