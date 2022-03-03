package com.ibm.academia.restapi.direcciones.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FraudeDTO implements Serializable {

	private String ip;
	private String nombrePais;
	private String codigoIso;
	private String monedaLocal;
	private BigDecimal cotizacionEuro;
	private Integer puerto;
	
	private static final long serialVersionUID = 5006758092087604614L;
}
