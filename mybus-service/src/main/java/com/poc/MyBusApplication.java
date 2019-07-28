package com.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.poc.exception.CustomResponseErrorHandler;

@SpringBootApplication
public class MyBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBusApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate();
	}

	@Bean
	public CustomResponseErrorHandler errorHandler() {

		return new CustomResponseErrorHandler();
	}
}
