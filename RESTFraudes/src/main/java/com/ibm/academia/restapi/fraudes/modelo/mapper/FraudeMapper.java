package com.ibm.academia.restapi.fraudes.modelo.mapper;

import com.ibm.academia.restapi.commons.modelo.entidades.Fraude;
import com.ibm.academia.restapi.fraudes.modelo.InformacionIp;
import com.ibm.academia.restapi.fraudes.modelo.dto.FraudeDTO;

public class FraudeMapper {
	
	public static FraudeDTO mapFraude(InformacionIp informacionIp, Fraude fraude){
		FraudeDTO fraudeDto = new FraudeDTO();
		
		fraudeDto.setIp(informacionIp.getIp());
		fraudeDto.setNombrePais(informacionIp.getNombrePais());
		fraudeDto.setCodigoIso(fraude.getCodigoIso());
		fraudeDto.setMonedaLocal(fraude.getMonedaLocal());
		fraudeDto.setCotizacionEuro(fraude.getCotizacionEuro());
		fraudeDto.setPuerto(fraude.getPuerto());
		
		return fraudeDto;
	}
}
