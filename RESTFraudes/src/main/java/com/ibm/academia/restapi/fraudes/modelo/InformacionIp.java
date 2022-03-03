package com.ibm.academia.restapi.fraudes.modelo;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InformacionIp implements Serializable {

	private String ip;

	private String codigoPais;

	private String codigoPais3;

	private String nombrePais;

	private String banderaPais;

	private static final long serialVersionUID = 6276890184485475068L;

}
