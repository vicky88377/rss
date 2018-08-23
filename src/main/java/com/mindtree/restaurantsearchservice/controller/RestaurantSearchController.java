package com.mindtree.restaurantsearchservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.restaurantsearchservice.model.ResponseStatusModel;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceInterface;

@RestController
@RequestMapping("/restaurants")
public class RestaurantSearchController {
	private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchController.class);

	@Autowired
	private RestaurantSearchServiceInterface service;

	@GetMapping("/search/{area}")
	public ResponseStatusModel searchRestaurantByArea(@PathVariable String area, @RequestParam String name,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") String rating,
			@RequestParam(defaultValue = "0") String budget, @RequestParam(defaultValue = "ALL") String cuisine) {
		if(logger.isDebugEnabled()) {
			logger.debug("searching restaurant with area="+area +" with name="+name+",page="+page+",rating="+rating+",budget="+budget+",cuisine="+cuisine);
		}
		if (name != null || !name.isEmpty()) {
		}
		return null;
	}

	@GetMapping("/search/{latitude}/{longitude}")
	public ResponseStatusModel searchRestaurantByCoordinates(String latitude, String longitude,
			@RequestParam String name, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "ALL") String rating, @RequestParam(defaultValue = "ALL") String budget,
			@RequestParam(defaultValue = "ALL") String cuisine) {
		if(logger.isDebugEnabled()) {
			logger.debug("searching restaurant with latitude,longitude="+latitude+","+ longitude+" with name="+name+",page="+page+",rating="+rating+",budget="+budget+",cuisine="+cuisine);
		}
		return null;
	}

	@GetMapping("{restaurant_id}")
	public ResponseStatusModel getRestaurantDetailsById(@PathVariable("restaurant_id") String restaurantId) {
		if(logger.isDebugEnabled()) {
			logger.debug("getting restaurant details of restaurant id="+restaurantId);
		}
		return null;
	}

	@GetMapping("/{restaurant_id}/food/{food_id}")
	public ResponseStatusModel getFoodDetailsByFoodId(@RequestParam("restaurant_id") String restaurantId,
			@RequestParam("food_id") String foodId) {
		if(logger.isDebugEnabled()) {
		}
		return null;
	}

	@GetMapping("/{restaurant _id}/validate/{latitude}/{longitude}")
	public ResponseStatusModel validateDeliveryAddress(@PathVariable String latitude, @PathVariable String longitude) {
		if(logger.isDebugEnabled()) {
		}
		return null;
	}
}
