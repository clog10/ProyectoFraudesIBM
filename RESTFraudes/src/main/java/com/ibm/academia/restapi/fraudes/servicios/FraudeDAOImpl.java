package com.ibm.academia.restapi.fraudes.servicios;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.academia.restapi.commons.modelo.entidades.Fraude;
import com.ibm.academia.restapi.fraudes.modelo.InformacionIp;
import com.ibm.academia.restapi.fraudes.modelo.dto.FraudeDTO;
import com.ibm.academia.restapi.fraudes.modelo.mapper.FraudeMapper;
import com.ibm.academia.restapi.fraudes.repositorios.FraudeRepository;

@Service
public class FraudeDAOImpl implements FraudeDAO {

	private final FraudeRepository fraudeRepository;

	@Value("${ipInfo.baseUrl}")
	private String baseUrlIpInfo;

	@Value("${countryInfo.baseUrl}")
	private String baseUrlCountryInfo;

	@Value("${countryInfo.key}")
	private String keyCountryInfo;

	@Value("${exchangeRatesApi.baseUrl}")
	private String baseUrlExchangeRatesApi;

	@Value("${exchangeRatesApi.key}")
	private String keyExchangeRatesApi;

	@Autowired
	public FraudeDAOImpl(FraudeRepository fraudeRepository) {
		this.fraudeRepository = fraudeRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Fraude> buscarPorId(Long id) {
		return fraudeRepository.findById(id);
	}

	@Override
	@Transactional
	public Fraude guardar(String ip, String usuario) {
		
		InformacionIp informacionIp = informacionGeneral(ip);
		Fraude fraude = informacionFraude(informacionIp);
		fraude.setUsuarioCreacion(usuario);
		
		return fraudeRepository.save(fraude);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Fraude> buscarTodos() {
		return fraudeRepository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		fraudeRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Iterable<Fraude> guardarVarios(Iterable<Fraude> entities) {
		return fraudeRepository.saveAll(entities);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Fraude> findFraudeByIp(String ip) {
		return fraudeRepository.findFraudeByIp(ip);
	}

	@Override
	public FraudeDTO consultarIp(String ip) {

		InformacionIp informacionIp = informacionGeneral(ip);

		Fraude fraude = informacionFraude(informacionIp);

		FraudeDTO fraudeDto = FraudeMapper.mapFraude(informacionIp, fraude);

		return fraudeDto;
	}

	private InformacionIp informacionGeneral(String ip) {
		String uri = baseUrlIpInfo + "ip?" + ip;
		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject(uri, String.class);

		JsonParser parser = new JsonParser();
		JsonObject gsonObj = parser.parse(result).getAsJsonObject();

		InformacionIp info = new InformacionIp();

		info.setIp(ip);
		info.setCodigoPais(gsonObj.get("countryCode").getAsString());
		info.setCodigoPais3(gsonObj.get("countryCode3").getAsString());
		info.setNombrePais(gsonObj.get("countryName").getAsString());
		info.setBanderaPais(gsonObj.get("countryEmoji").getAsString());

		return info;
	}

	private Fraude informacionFraude(InformacionIp informacionIp) {
		String uri = baseUrlCountryInfo + "name/" + informacionIp.getNombrePais() + "?access_key=" + keyCountryInfo
				+ "&fullText=false";

		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject(uri, String.class);

		JsonParser parser = new JsonParser();
		JsonElement gsonElement = parser.parse(result);

		JsonArray array = gsonElement.getAsJsonArray();

		Fraude fraude = new Fraude();

		fraude.setIp(informacionIp.getIp());
		fraude.setNombrePais(informacionIp.getNombrePais());

		for (JsonElement element : array) {
			if (element.isJsonObject()) {
				JsonObject informacion = element.getAsJsonObject();
				fraude.setCodigoIso(informacion.get("alpha3Code").getAsString());

				JsonElement curriencies = informacion.get("currencies");
				JsonArray array2 = curriencies.getAsJsonArray();

				for (JsonElement currency : array2) {
					JsonObject info2 = currency.getAsJsonObject();
					fraude.setMonedaLocal(info2.get("code").getAsString());
					fraude.setCotizacionEuro(cotizacionMonedaEuro(info2.get("code").getAsString()));
				}
			}
		}

		return fraude;
	}

	private BigDecimal cotizacionMonedaEuro(String monedaLocal) {

		String uri = baseUrlExchangeRatesApi + "latest?access_key=" + keyExchangeRatesApi + "&symbols=" + monedaLocal;

		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject(uri, String.class);

		JsonParser parser = new JsonParser();
		JsonObject gsonObj = parser.parse(result).getAsJsonObject();

		JsonObject rates = gsonObj.get("rates").getAsJsonObject();

		BigDecimal cotizacion = new BigDecimal(rates.get(monedaLocal).getAsString());

		return cotizacion;
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
