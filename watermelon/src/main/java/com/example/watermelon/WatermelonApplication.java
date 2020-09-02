package com.example.watermelon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableEurekaClient
public class WatermelonApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatermelonApplication.class, args);
	}

}
