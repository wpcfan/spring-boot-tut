package dev.local.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
@Configuration
public class ConfigApplication {
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
