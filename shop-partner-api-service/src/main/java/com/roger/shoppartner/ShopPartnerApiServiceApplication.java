package com.stackroute.shoppartner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ShopPartnerApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopPartnerApiServiceApplication.class, args);
	}

}
