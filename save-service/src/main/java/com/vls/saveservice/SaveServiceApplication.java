package com.vls.saveservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SaveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaveServiceApplication.class, args);
    }

}
