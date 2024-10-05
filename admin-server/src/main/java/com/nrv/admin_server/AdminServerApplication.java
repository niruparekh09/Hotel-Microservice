package com.nrv.admin_server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main entry point for the Admin Server of our Microservice.
 * Using {@code @EnableAdminServer}, it will enable this service to be used as
 * admin server which will be accessible on the port 1111
 *
 * @author Nirav Parekh
 * @see EnableAdminServer
 * @since 1.0
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }

}
