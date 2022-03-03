package com.ibm.academia.restapi.fraudes.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.fraudes.repositorios.FraudeRepository;

@SpringBootTest
public class FraudeDAOImplTest {

	@Autowired
	private FraudeDAO fraudeDao;

	@MockBean
	private FraudeRepository fraudeRepository;

	@Test
	@DisplayName("Test: Buscar Ips baneadas por el cliente")
	void findFraudeByIp() {

		fraudeDao.findFraudeByIp(anyString());

		verify(fraudeRepository).findFraudeByIp(anyString());
	}

}
