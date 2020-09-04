package com.example.strawberry;

import com.example.strawberry.filters.pre.SimpleFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class StrawberryApplication {

	public static void main(String[] args) {
		SpringApplication.run(StrawberryApplication.class, args);
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
}
