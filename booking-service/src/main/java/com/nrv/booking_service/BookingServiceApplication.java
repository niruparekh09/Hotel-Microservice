package com.nrv.booking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * This is the main entry point for the Booking Service of our Microservice.
 * Using {@code @EnableDiscoveryClient}, it will enable this service to be used to
 * manage Booking related operations which will be accessible on the port 8082
 *
 * @author Nirav Parekh
 * @see EnableDiscoveryClient
 * @since 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

}
