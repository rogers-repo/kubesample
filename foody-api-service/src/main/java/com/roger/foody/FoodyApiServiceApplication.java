package com.roger.foody;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FoodyApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodyApiServiceApplication.class, args);
    }

}
