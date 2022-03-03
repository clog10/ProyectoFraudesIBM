package com.ibm.academia.restapi.fraudes.controladores;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.commons.modelo.entidades.Fraude;
import com.ibm.academia.restapi.fraudes.excepciones.BadRequestException;
import com.ibm.academia.restapi.fraudes.excepciones.NotFoundException;
import com.ibm.academia.restapi.fraudes.modelo.dto.FraudeDTO;
import com.ibm.academia.restapi.fraudes.servicios.FraudeDAO;

import brave.Tracer;

@RestController
@RequestMapping("/fraude")
public class FraudeController {

	private final static Logger logger = LoggerFactory.getLogger(FraudeController.class);

	@Autowired
	private FraudeDAO fraudeDao;

	@Autowired
	private Environment environment;

	@Autowired
	private Tracer tracer;

	/**
	 * Endpoint para consultar la información de una Ip
	 * 
	 * @param ip
	 * @return Retorna un objeto de tipo FraudeDTO con la información más relevante
	 * @throws BadRequestException -> en caso que el formato de la ip sea invalido o
	 *                             ya esté baneado
	 * @author CJGL - 02/03/2022
	 */
	@GetMapping("/consultar-ip")
	public ResponseEntity<?> informacionIp(@RequestParam String ip) {

		if (!fraudeDao.validarIp(ip)) {
			tracer.currentSpan().tag("error.mensaje", "Formato de la dirección ip invalido");
			throw new BadRequestException("Formato de la dirección ip invalido");
		}

		Optional<Fraude> oFraude = fraudeDao.findFraudeByIp(ip);

		if (oFraude.isPresent()) {
			tracer.currentSpan().tag("error.mensaje", "La IP ha sido baneada anteriormente");
			throw new BadRequestException("La IP ha sido baneada anteriormente");
		}

		FraudeDTO informacion = null;

		try {
			informacion = fraudeDao.consultarIp(ip);
		} catch (Exception e) {
			logger.info("mensaje: " + e.getMessage() + " Causa: " + e.getCause());
			throw e;
		}

		informacion.setPuerto(Integer.parseInt(environment.getProperty("local.server.port")));

		return new ResponseEntity<FraudeDTO>(informacion, HttpStatus.OK);
	}

	/**
	 * Endpoint para agregar una ip a la lista negra
	 * 
	 * @param ip
	 * @param usuario
	 * @return Retorna un objeto de tipo Fraude que es la ip con su informacion y ha
	 *         sido baneada
	 * @throws BadRequestException -> en caso que la ip tenga un formato invalido o
	 *                             ya haya sido baneada con anterioridad
	 * @author CGJL - 02/03/2022
	 */
	@PostMapping("/ban-ip")
	public ResponseEntity<?> banIp(@RequestParam String ip, @RequestParam String usuario) {

		if (!fraudeDao.validarIp(ip)) {
			tracer.currentSpan().tag("error.mensaje", "Formato de la dirección ip invalido");
			throw new BadRequestException("Formato de la dirección ip invalido");
		}

		Optional<Fraude> oFraude = fraudeDao.findFraudeByIp(ip);

		if (oFraude.isPresent()) {
			tracer.currentSpan().tag("error.mensaje", "Formato de la dirección ip invalido");
			throw new BadRequestException("La IP ya ha sido baneada");
		}

		Fraude fraudeGuardado = fraudeDao.guardar(ip, usuario);
		fraudeGuardado.setPuerto(Integer.parseInt(environment.getProperty("local.server.port")));

		return new ResponseEntity<Fraude>(fraudeGuardado, HttpStatus.OK);
	}

	/**
	 * Endpoint para buscar una ip en la lista negra
	 * 
	 * @param ip
	 * @return Retorna un objeto de tipo Fraude que contiene la información de la ip
	 *         en caso que haya sido baneada
	 * @throws BadRequestException -> en caso que el formato de la ip sea invalido
	 * @throws NotFoundException   -> en caso que no exista en la lista negra
	 * @author CJGL - 02/03/2022
	 */
	@GetMapping("/buscar")
	public ResponseEntity<?> buscarPorIp(@RequestParam String ip) {

		if (!fraudeDao.validarIp(ip)) {
			tracer.currentSpan().tag("error.mensaje", "Formato de la dirección ip invalido");
			throw new BadRequestException("Formato de la dirección ip invalido");
		}

		Optional<Fraude> oFraude = fraudeDao.findFraudeByIp(ip);

		if (!oFraude.isPresent()) {
			tracer.currentSpan().tag("error.mensaje", "La ip no esta baneada");
			throw new NotFoundException("La ip no esta baneada");
		}

		oFraude.get().setPuerto(Integer.parseInt(environment.getProperty("local.server.port")));

		return new ResponseEntity<Fraude>(oFraude.get(), HttpStatus.OK);
	}

}
