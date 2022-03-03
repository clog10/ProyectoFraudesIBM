package com.ibm.academia.restapi.direcciones.excepciones;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String mensaje) {
		super(mensaje);
	}

	private static final long serialVersionUID = 4106836389567077876L;

}
