package com.example.sunflower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SunflowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunflowerApplication.class, args);
	}

}