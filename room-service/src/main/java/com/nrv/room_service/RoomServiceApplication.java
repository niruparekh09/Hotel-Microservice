package com.nrv.room_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * This is the main entry point for the Room Service of our Microservice.
 * Using {@code @EnableDiscoveryClient}, it will enable this service to be used to
 * manage Room related operations which will be accessible on the port 8081
 *
 * @author Nirav Parekh
 * @see EnableDiscoveryClient
 * @since 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RoomServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomServiceApplication.class, args);
    }

}
