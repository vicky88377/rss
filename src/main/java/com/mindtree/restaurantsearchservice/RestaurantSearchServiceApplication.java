package com.mindtree.restaurantsearchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.repository.RestaurantSearchRepository;

@SpringBootApplication
public class RestaurantSearchServiceApplication implements CommandLineRunner {
	@Autowired
	private RestaurantSearchRepository repo;

	@Override
	public void run(String... args) throws Exception {
		
		Pageable pageable = new PageRequest(0, 10);
		Page<RestaurantModel> data = repo.findByAreaRatingBudget("Bangalore", 3.0f, 200.0f, pageable);
		
		data.stream().forEach(System.out::println);
	
		
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantSearchServiceApplication.class, args);
	}
}
