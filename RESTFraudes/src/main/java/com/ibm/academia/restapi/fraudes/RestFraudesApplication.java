package com.ibm.academia.restapi.fraudes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.ibm.academia.restapi.commons.modelo.entidades"})
public class RestFraudesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestFraudesApplication.class, args);
	}

}
