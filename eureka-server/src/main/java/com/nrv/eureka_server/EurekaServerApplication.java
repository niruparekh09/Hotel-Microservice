package com.nrv.eureka_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * This is the main entry point for the Eureka Server of our Microservice.
 * Using {@code @EnableEurekaServer}, it will enable this service to be used as
 * Eureka Discovery server which will be accessible on the port 8761.
 *
 * @author Nirav Parekh
 * @see EnableEurekaServer
 * @since 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}
