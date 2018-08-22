package com.mindtree.restaurantsearchservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.restaurantsearchservice.model.ResponseStatusModel;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceInterface;

@RestController
@RequestMapping("/restaurants")
public class RestaurantSearchController {
	
	@Autowired
	private RestaurantSearchServiceInterface service;
	
	@GetMapping("/search/{area}")
	public ResponseStatusModel searchRestaurantByArea(@PathVariable String area,@RequestParam String name,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="desc") String rating,@RequestParam(defaultValue="asc") String budget,
			@RequestParam(defaultValue="ALL") String cuisine) {
		if(name!=null || !name.isEmpty()) {
			RestaurantModel data = service.getRestaurantByNameAndArea(area, name, page);
		}
		return null;
	}
	
	@GetMapping("/search/{latitude}/{longitude}")
	public ResponseStatusModel searchRestaurantByCoordinates(String latitude,String longitude,@RequestParam String name,@RequestParam(defaultValue="1") int page,@RequestParam(defaultValue="ALL") String rating,@RequestParam(defaultValue="ALL") String budget,
			@RequestParam(defaultValue="ALL") String cuisine) {
		return null;
	}
	
	@GetMapping("{restaurant_id}")
	public ResponseStatusModel getRestaurantDetailsById(@PathVariable("restaurant_id") String restaurantId) {
		return null;
	}
	@GetMapping("/{restaurant_id}/food/{food_id}")
	public ResponseStatusModel getFoodDetailsByFoodId(@RequestParam("restaurant_id") String restaurantId,@RequestParam("food_id") String foodId) {
		return null;
	}
	
	@GetMapping("/{restaurant _id}/validate/{latitude}/{longitude}")
	public ResponseStatusModel validateDeliveryAddress(@PathVariable String latitude,@PathVariable String longitude) {
		return null;
	}
}
