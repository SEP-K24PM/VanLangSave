package com.vls.newsfeedservice;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class NewsfeedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsfeedServiceApplication.class, args);
    }

}
