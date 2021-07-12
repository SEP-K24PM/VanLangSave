package com.vls.admin_postmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AdminPostManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminPostManagementServiceApplication.class, args);
    }

}

