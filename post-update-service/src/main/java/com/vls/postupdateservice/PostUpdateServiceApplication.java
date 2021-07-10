package com.vls.postupdateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PostUpdateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostUpdateServiceApplication.class, args);
    }

}
