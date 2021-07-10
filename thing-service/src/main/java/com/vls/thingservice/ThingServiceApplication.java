package com.vls.thingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ThingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThingServiceApplication.class, args);
    }

}
