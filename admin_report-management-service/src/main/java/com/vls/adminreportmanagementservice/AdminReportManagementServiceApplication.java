package com.vls.adminreportmanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class AdminReportManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminReportManagementServiceApplication.class, args);
    }

}
