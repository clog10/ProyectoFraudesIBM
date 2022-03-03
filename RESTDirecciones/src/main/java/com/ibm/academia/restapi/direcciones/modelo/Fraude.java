package com.ibm.academia.restapi.direcciones.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Fraude implements Serializable {

	private Long id;
	private String ip;
	private String nombrePais;
	private String codigoIso;
	private String monedaLocal;
	private BigDecimal cotizacionEuro;
	private String usuarioCreacion;
	private Date fechaCreacion;
	private Integer puerto;
	
	private static final long serialVersionUID = -8677337841262893164L;
}
