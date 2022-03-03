package com.ibm.academia.restapi.fraudes.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.commons.modelo.entidades.Fraude;
import com.ibm.academia.restapi.fraudes.datos.DatosDummy;

@DataJpaTest
public class FraudeRepositoryTest {

	@Autowired
	private FraudeRepository fraudeRepository;

	@BeforeEach
	void setUp() {
		List<Fraude> fraudes = new ArrayList<Fraude>();
		fraudes.add(DatosDummy.fraude01());
		fraudes.add(DatosDummy.fraude02());
		fraudes.add(DatosDummy.fraude03());
		fraudeRepository.saveAll(fraudes);
	}

	@AfterEach
	void tearDown() {
		fraudeRepository.deleteAll();
	}

	@Test
	@DisplayName("Test: Buscar Ips baneadas")
	void findFraudeByIp() {
		Optional<Fraude> oExpected = fraudeRepository.findFraudeByIp(DatosDummy.fraude01().getIp());

		assertThat(oExpected.isPresent());
	}

}
