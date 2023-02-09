package com.saurs.swapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackageClasses = com.saurs.swapi.clients.PlanetClient.class)
@SpringBootApplication
public class SwApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwApiApplication.class, args);
	}

}
