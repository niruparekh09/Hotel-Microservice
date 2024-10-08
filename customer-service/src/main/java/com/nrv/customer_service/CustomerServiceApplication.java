package com.nrv.customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * This is the main entry point for the Customer Service of our Microservice.
 * Using {@code @EnableDiscoveryClient}, it will enable this service to be used to
 * manage Customer related operations which will be accessible on the port 8081
 *
 * @author Nirav Parekh
 * @see EnableDiscoveryClient
 * @since 1.0
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

}
