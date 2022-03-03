package com.ibm.academia.restapi.direcciones.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.commons.modelo.entidades.Fraude;
import com.ibm.academia.restapi.direcciones.clientes.FraudeClienteRest;
import com.ibm.academia.restapi.direcciones.modelo.dto.FraudeDTO;

@Service("fraudeServiceFeign")
public class FraudeServiceFeign implements IFraudeService {
	
	@Autowired
	private FraudeClienteRest fraudeClienteRest;

	@Override
	public FraudeDTO consultarIp(String ip) {
		return fraudeClienteRest.consultarIp(ip);
	}

	@Override
	public Fraude banIp(String ip, String usuario) {
		return fraudeClienteRest.banIp(ip, usuario);
	}

	@Override
	public boolean validarIp(String ip) {
		String[] tokens = ip.split("\\.");
		if (tokens.length != 4) {
			return false;
		}
		for (String str : tokens) {
			int i = Integer.parseInt(str);
			if ((i < 0) || (i > 255)) {
				return false;
			}
		}
		return true;
	}
}
