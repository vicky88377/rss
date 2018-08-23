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

import com.mindtree.restaurantsearchservice.model.FoodDetails;
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
	public ResponseStatusModel searchRestaurantByArea(@PathVariable String area,
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "0") Float rating,
			@RequestParam(required = false, defaultValue = "0") Float budget,
			@RequestParam(required = false) String cuisine) {
		if (logger.isDebugEnabled()) {
			logger.debug("searching restaurant with area=" + area + " with name=" + name + ",page=" + page + ",rating="
					+ rating + ",budget=" + budget + ",cuisine=" + cuisine);
		}
		Page<RestaurantModel> data = service.getRestaurantByAreaAndFilterParam(area, cuisine, budget, rating, name,
				page);

		return createResponse(data);
	}

	@GetMapping("/search/{latitude}/{longitude}")
	public ResponseStatusModel searchRestaurantByCoordinates(@PathVariable Double latitude,
			@PathVariable Double longitude, @RequestParam(required=false) String name, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "0") Float rating,
			@RequestParam(required = false, defaultValue = "0") Float budget,
			@RequestParam(required = false) String cuisine,
			@RequestParam(required = false, defaultValue = "1") Float distance) {

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
		RestaurantModel data = service.getResaurantById(restaurantId);
		
		return createResponse(data);
	}

	@GetMapping("/{restaurant_id}/food/{food_id}")
	public ResponseStatusModel getFoodDetailsByFoodId(@RequestParam("restaurant_id") String restaurantId,
			@RequestParam("food_id") String foodId) {
		if (logger.isDebugEnabled()) {
		}
		FoodDetails data = service.getFoodDetailsOfARestuarant(restaurantId, foodId);
		return createResponse(data);
	}

	@GetMapping("/{restaurant_id}/validate/{latitude}/{longitude}")
	public ResponseStatusModel validateDeliveryAddress(@PathVariable("restaurant_id") String restaurantId,@PathVariable Double latitude, @PathVariable Double longitude) {
		if (logger.isDebugEnabled()) {
		}
		boolean data = service.validateDeliveryAddress(restaurantId, latitude, longitude);
		ResponseStatusModel status=new ResponseStatusModel();
		if(data==false) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Failure Response");
			}
			status.setMessage("Delivery is not available for your area");
			status.setStatusCode(401);
			status.setStatus("SUCCESS");
		}else {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Success Response");
			}
			status.setMessage("Delivery is available for your area");	
			status.setStatusCode(200);
			status.setStatus("SUCCESS");
			status.setData(data);
		}
		return status;
	}

	private ResponseStatusModel createResponse(Object data) {
		ResponseStatusModel status=new ResponseStatusModel();
		if(data==null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Failure Response");
			}
			status.setMessage("No Data Found");
			status.setStatusCode(401);
			status.setStatus("SUCCESS");
		}else {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Success Response");
			}
			status.setMessage("Data Found");
			status.setStatusCode(200);
			status.setStatus("SUCCESS");
			status.setData(data);
		}
		return status;
	}
	
	private ResponseStatusModel createResponse(Page<RestaurantModel> data) {
		ResponseStatusModel responseStatus = new ResponseStatusModel();
		if (!data.getContent().isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Success Response");
			}
			responseStatus.setData(data);
			responseStatus.setStatusCode(200);
			responseStatus.setStatus("SUCCESS");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Failure Response");
			}
			responseStatus.setStatusCode(401);
			responseStatus.setStatus("SUCCESS");
			responseStatus.setMessage("No Data Found");
		}
		return responseStatus;
	}
	
}
