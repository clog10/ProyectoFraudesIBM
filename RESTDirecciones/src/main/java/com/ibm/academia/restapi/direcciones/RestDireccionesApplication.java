package com.ibm.academia.restapi.direcciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class RestDireccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestDireccionesApplication.class, args);
	}

}
