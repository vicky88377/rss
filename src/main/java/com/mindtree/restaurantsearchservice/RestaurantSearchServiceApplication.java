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
		// Page<RestaurantModel> data = repo.findByAreaRatingBudget("Bangalore", 3.0f, 200.0f, pageable);
		// Page<RestaurantModel> data = repo.findByAreaAndCuisine("Bangalore", "North Indian", 3.0f, 200.0f, pageable);
		// Page<RestaurantModel> data = repo.findByAreaAndName("Bangalore", "Sultans of Spice", pageable);
		// Page<RestaurantModel> data = repo.findById("50943", pageable);
		//Page<RestaurantModel> data = repo.findByLonAndLat(3 ,200 ,5, -47.95628333, -15.83451389, pageable);
		// Page<RestaurantModel> data = repo.findByLonLatAndName("Steakhouse",5, -47.95628333, -15.83451389, pageable);
		// Page<RestaurantModel> data = repo.findByLonLatRatingBudget("North Indian",3,200,1, 77.696664, 12.97537691, pageable);
		//data.stream().forEach(System.out::println);

	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantSearchServiceApplication.class, args);
	}
}
