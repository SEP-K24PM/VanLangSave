package com.vls.admin_usermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AdminUserManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminUserManagementServiceApplication.class, args);
    }

}
