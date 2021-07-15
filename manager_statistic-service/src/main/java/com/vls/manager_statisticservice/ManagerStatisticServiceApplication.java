package com.vls.manager_statisticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ManagerStatisticServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerStatisticServiceApplication.class, args);
    }

}
