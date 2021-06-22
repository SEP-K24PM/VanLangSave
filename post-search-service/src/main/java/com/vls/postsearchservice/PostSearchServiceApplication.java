package com.vls.postsearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PostSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostSearchServiceApplication.class, args);
    }

}
