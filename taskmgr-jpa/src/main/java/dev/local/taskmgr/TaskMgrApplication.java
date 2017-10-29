package dev.local.taskmgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@EnableDiscoveryClient
@EnableResourceServer
@ComponentScan({"dev.local"})
@SpringBootApplication
@EnableFeignClients
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TaskMgrApplication {

    private final DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @PreAuthorize("#oauth2.hasScope('FOO')")
    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hi there";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/bye")
    public String bye() {
        return "Good bye, bro";
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskMgrApplication.class, args);
    }
}
