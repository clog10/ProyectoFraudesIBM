package com.ibm.academia.restapi.fraudes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RestFraudesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestFraudesApplication.class, args);
	}

}
