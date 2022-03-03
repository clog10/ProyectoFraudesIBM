package com.ibm.academia.restapi.direcciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EntityScan({"com.ibm.academia.restapi.commons.modelo.entidades"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class RestDireccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDireccionesApplication.class, args);
	}

} 
