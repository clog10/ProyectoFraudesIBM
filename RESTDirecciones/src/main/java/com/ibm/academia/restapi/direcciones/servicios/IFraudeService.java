package com.ibm.academia.restapi.direcciones.servicios;

import com.ibm.academia.restapi.commons.modelo.entidades.Fraude;
import com.ibm.academia.restapi.direcciones.modelo.dto.FraudeDTO;

public interface IFraudeService {
	public FraudeDTO consultarIp(String ip);

	public Fraude banIp(String ip, String usuario);

	public boolean validarIp(String ip);
}
