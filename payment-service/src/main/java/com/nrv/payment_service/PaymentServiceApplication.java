package com.nrv.payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * This is the main entry point for the Payment Service of our Microservice.
 * Using {@code @EnableDiscoveryClient}, it will enable this service to be used to
 * manage Payment related operations which will be accessible on the port 8084
 *
 * @author Nirav Parekh
 * @see EnableDiscoveryClient
 * @since 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

}
