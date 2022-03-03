package com.ibm.academia.restapi.fraudes.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.fraudes.modelo.dto.FraudeDTO;
import com.ibm.academia.restapi.fraudes.modelo.entidades.Fraude;

public interface FraudeDAO {
	public Optional<Fraude> buscarPorId(Long id);

	public Fraude guardar(String ip, String usuario);

	public Iterable<Fraude> buscarTodos();

	public void eliminarPorId(Long id);

	public Iterable<Fraude> guardarVarios(Iterable<Fraude> entities);
	
	public FraudeDTO consultarIp(String ip);
	
	public boolean validarIp(String ip);
	
	public Optional<Fraude> findFraudeByIp(String ip);
}
