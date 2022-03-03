package com.ibm.academia.restapi.direcciones.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.academia.restapi.commons.modelo.entidades.Fraude;
import com.ibm.academia.restapi.direcciones.modelo.dto.FraudeDTO;

@FeignClient(name = "api-fraudes")
public interface FraudeClienteRest {
	
	@GetMapping("/api/v1/rest-fraudes/fraude/consultar-ip")
	public FraudeDTO consultarIp(@RequestParam String ip);
	
	@PostMapping("/api/v1/rest-fraudes/fraude/ban-ip")
	public Fraude banIp(@RequestParam String ip, @RequestParam String usuario);

}
