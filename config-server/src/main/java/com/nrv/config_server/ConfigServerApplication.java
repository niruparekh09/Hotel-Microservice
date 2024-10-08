package com.nrv.config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * This is the main entry point for the Config Server of our Microservice.
 * Using {@code @EnableConfigServer}, it will enable this application to be used
 * as a config server, and it will run on the port 8888
 *
 * @author Nirav Parekh
 * @see EnableConfigServer
 * @since 1.0
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
