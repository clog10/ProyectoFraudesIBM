package com.ibm.academia.restapi.fraudes.excepciones.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.restapi.fraudes.excepciones.BadRequestException;
import com.ibm.academia.restapi.fraudes.excepciones.NotFoundException;

@ControllerAdvice
public class RestFraudesException {

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<?> noExisteFraudeException(NotFoundException exception) {

		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("error", exception.getMessage());

		return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> formatoInvalidoException(BadRequestException exception) {

		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("error", exception.getMessage());

		return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	}
}
