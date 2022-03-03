package com.ibm.academia.restapi.direcciones.controladores;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.commons.modelo.entidades.Fraude;
import com.ibm.academia.restapi.direcciones.excepciones.BadRequestException;
import com.ibm.academia.restapi.direcciones.modelo.dto.FraudeDTO;
import com.ibm.academia.restapi.direcciones.servicios.IFraudeService;

import brave.Tracer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/fraude")
public class FraudeController {

	private final static Logger logger = LoggerFactory.getLogger(FraudeController.class);

	@Autowired
	private CircuitBreakerFactory circuitBreaker;
	
	@Autowired
	private Tracer tracer;

	@Autowired
	@Qualifier("fraudeServiceFeign")
	private IFraudeService fraudeService;

	/**
	 * Endpoint que consulta la información de la Ip
	 * 
	 * @param ip
	 * @return Retorna un objeto de tipo FraudeDTO con la información necesaria
	 * @throws BadRequestException -> en caso que el formato de la dirección Ip sea
	 *                             invalido
	 * @author CJGL * 02/03/2022
	 */
	@CircuitBreaker(name = "fraudes", fallbackMethod = "metodoAlternativoConsultarIp")
	@GetMapping("/consultar-ip")
	public FraudeDTO consultarIp(@RequestParam String ip) {

		if (!fraudeService.validarIp(ip)) {
			tracer.currentSpan().tag("error.mensaje", "Formato de la dirección ip invalido");
			throw new BadRequestException("Formato invalido de la Ip");
		}
		return fraudeService.consultarIp(ip);
	}

	/**
	 * Endpoint que banea una direccion Ip
	 * 
	 * @param ip
	 * @param usuario
	 * @return Retorna un objeto de tipo Fraude que contiene la información de la Ip
	 *         baneada
	 * @throws BadRequestException -> en caso que el formato de la dirección ip sea
	 *                             invalido
	 * @author CJGL - 02/03/2022
	 */
	@GetMapping("/banear-ip")
	public Fraude banIp(@RequestParam String ip, @RequestParam String usuario) {

		if (!fraudeService.validarIp(ip)) {
			tracer.currentSpan().tag("error.mensaje", "Formato de la dirección ip invalido");
			throw new BadRequestException("Formato invalido de la Ip");
		}
		return circuitBreaker.create("direcciones").run(() -> fraudeService.banIp(ip, usuario),
				e -> metodoAlternativoBanearIp(ip, e));
	}

	/**
	 * Método alternativo para consultarIp y controlar las excepciones del api
	 * principal
	 * 
	 * @param ip
	 * @param e
	 * @return Retorna un objeto de tipo FraudeDTO con información de la ip en todos
	 *         sus campos
	 * @author CJGL - 02/03/2022
	 */
	public FraudeDTO metodoAlternativoConsultarIp(String ip, Throwable e) {
		logger.info(e.getMessage());

		FraudeDTO fraude = new FraudeDTO();

		fraude.setIp(ip);
		fraude.setMonedaLocal(ip);
		fraude.setCotizacionEuro(new BigDecimal(0));
		fraude.setCodigoIso(ip);
		fraude.setNombrePais(ip);

		return fraude;
	}

	/**
	 * Metodo alternativo para banIp y controlar sus excepciones
	 * 
	 * @param ip
	 * @param e
	 * @return Retorna un objeto de tipo Fraude con la ip en sus campos
	 * @author CJGL - 02/03/2022
	 */
	public Fraude metodoAlternativoBanearIp(String ip, Throwable e) {
		logger.info(e.getMessage());
		Fraude fraude = new Fraude();
		fraude.setIp(ip);
		fraude.setCodigoIso(ip);
		fraude.setCotizacionEuro(new BigDecimal(0));
		fraude.setMonedaLocal(ip);
		fraude.setNombrePais(ip);
		return fraude;
	}
}
