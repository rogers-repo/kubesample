package com.stackroute.deliveryagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DeliveryAgentApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryAgentApiServiceApplication.class, args);
	}

}
