package com.mindtree.restaurantsearchservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchController.class);

	@Autowired
	private RestaurantSearchServiceInterface service;

	@GetMapping("/search/{area}")
	public ResponseStatusModel searchRestaurantByArea(@PathVariable String area, @RequestParam(required=false) String name,
			@RequestParam(defaultValue = "0") Integer page, @RequestParam(required=false)Float rating, @RequestParam(required=false) Float budget,
			@RequestParam(required=false) String cuisine) {
		if (logger.isDebugEnabled()) {
			logger.debug("searching restaurant with area=" + area + " with name=" + name + ",page=" + page + ",rating="
					+ rating + ",budget=" + budget + ",cuisine=" + cuisine);
		}
		Page<RestaurantModel> data = service.getRestaurantByAreaAndFilterParam(area, cuisine, budget, rating, name,
				page);

		return createResponse(data);
	}

	@GetMapping("/search/{latitude}/{longitude}")
	public ResponseStatusModel searchRestaurantByCoordinates(double latitude, double longitude,
			@RequestParam String name, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(required=false) Float rating, @RequestParam(required=false) Float budget,
			@RequestParam(required=false) String cuisine, @RequestParam(required=false) Float distance) {

		if (logger.isDebugEnabled()) {
			logger.debug("searching restaurant with latitude,longitude=" + latitude + "," + longitude + " with name="
					+ name + ",page=" + page + ",rating=" + rating + ",budget=" + budget + ",cuisine=" + cuisine);
		}
		Page<RestaurantModel> data = service.getRestaurantByLocationAndFilterParam(latitude, longitude, distance,
				cuisine, budget, rating, name, page);

		return createResponse(data);
	}

	@GetMapping("{restaurant_id}")
	public ResponseStatusModel getRestaurantDetailsById(@PathVariable("restaurant_id") String restaurantId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getting restaurant details of restaurant id=" + restaurantId);
		}
		
		
		return null;
	}

	@GetMapping("/{restaurant_id}/food/{food_id}")
	public ResponseStatusModel getFoodDetailsByFoodId(@RequestParam("restaurant_id") String restaurantId,
			@RequestParam("food_id") String foodId) {
		if (logger.isDebugEnabled()) {
		}
		return null;
	}

	@GetMapping("/{restaurant _id}/validate/{latitude}/{longitude}")
	public ResponseStatusModel validateDeliveryAddress(@PathVariable String latitude, @PathVariable String longitude) {
		if (logger.isDebugEnabled()) {
		}
		return null;
	}

	private ResponseStatusModel createResponse(Page<RestaurantModel> data) {
		ResponseStatusModel responseStatus = new ResponseStatusModel();
		if (data != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Success Response");
			}
			responseStatus.setData(new Object[] { data });
			responseStatus.setStatusCode(200);
			responseStatus.setStatus("SUCCESS");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Failure Response");
			}
			responseStatus.setStatusCode(400);
			responseStatus.setStatus("FAILURE");
		}
		return responseStatus;
	}
}
