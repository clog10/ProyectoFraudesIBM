package com.ibm.academia.restapi.fraudes.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Fraude;

@Repository
public interface FraudeRepository extends CrudRepository<Fraude, Long>{

	public Optional<Fraude> findFraudeByIp(String ip);
	
}
