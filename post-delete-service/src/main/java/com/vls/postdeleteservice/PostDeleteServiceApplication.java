package com.vls.postdeleteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PostDeleteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostDeleteServiceApplication.class, args);
    }

}
