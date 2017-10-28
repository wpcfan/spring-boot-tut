package dev.local.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The Main Spring Boot Application class that starts the authorization
 * server.</br>
 * </br>
 *
 * Note that the server is also a Eureka client so as to register with the
 * Eureka server and be auto-discovered by other Eureka clients.
 *
 * @author Peng Wang
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"dev.local"})
@EnableMongoRepositories({"dev.local.repositories"})
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
