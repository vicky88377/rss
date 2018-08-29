package com.mindtree.restaurantsearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RestaurantSearchServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RestaurantSearchServiceApplication.class, args);
	}
}
