package com.mindtree.restaurantsearchservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.restaurantsearchservice.model.DeliveryInfoResponse;
import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.FoodMenuResponse;
import com.mindtree.restaurantsearchservice.model.RestaurantDetailResponse;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.model.RestaurantsResponse;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceInterface;
import com.mindtree.restaurantsearchservice.vo.AreaSearchParams;
import com.mindtree.restaurantsearchservice.vo.CoOrdinateSearchParams;

@RestController
@RequestMapping("/restaurants")
public class RestaurantSearchController {
	private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchController.class);

	@Autowired
	private RestaurantSearchServiceInterface service;

	@GetMapping(value = "/search/{area}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestaurantsResponse searchRestaurantByArea(@PathVariable String area,
			@RequestParam(required = false) String name, @RequestParam(defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "0") Float rating,
			@RequestParam(required = false, defaultValue = "0") Float budget,
			@RequestParam(required = false) String cuisine) {
		if (logger.isDebugEnabled()) {
			logger.debug("searching restaurant with area=" + area + " with name=" + name + ",page=" + page + ",rating="
					+ rating + ",budget=" + budget + ",cuisine=" + cuisine);
		}

		AreaSearchParams params = new AreaSearchParams();
		params.setArea(area);
		params.setBudget(budget);
		params.setCuisine(cuisine);
		params.setPage(page);
		params.setRestaurantName(name);
		params.setRating(rating);

		Page<RestaurantModel> data = service.getRestaurantByAreaAndFilterParam(params);

		if (data != null) {
			createLinksForRestaurant(data.getContent());
		}
		return createRestaurantResponse(data);
	}

	@GetMapping(path = "/search/{latitude}/{longitude}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestaurantsResponse searchRestaurantByCoordinates(@PathVariable Double latitude,
			@PathVariable Double longitude, @RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(required = false, defaultValue = "0") Float rating,
			@RequestParam(required = false, defaultValue = "0") Float budget,
			@RequestParam(required = false) String cuisine,
			@RequestParam(required = false, defaultValue = "1") Float distance) {
		if (logger.isDebugEnabled()) {
			logger.debug("searching restaurant with latitude,longitude=" + latitude + "," + longitude + " with name="
					+ name + ",page=" + page + ",rating=" + rating + ",budget=" + budget + ",cuisine=" + cuisine);
		}

		CoOrdinateSearchParams params = new CoOrdinateSearchParams();
		params.setLatitude(latitude);
		params.setLongitude(longitude);
		params.setBudget(budget);
		params.setCuisine(cuisine);
		params.setPage(page);
		params.setRestaurantName(name);
		params.setRating(rating);
		params.setDistance(distance);
		
		Page<RestaurantModel> data = service.getRestaurantByLocationAndFilterParam(params);
		if (data != null) {
			createLinksForRestaurant(data.getContent());
		}

		return createRestaurantResponse(data);
	}

	@GetMapping(value = "{restaurant_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestaurantDetailResponse getRestaurantDetailsById(@PathVariable("restaurant_id") String restaurantId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getting restaurant details of restaurant id=" + restaurantId);
		}
		RestaurantModel data = service.getResaurantById(restaurantId);
		if (data != null) {
			Link getAllFood = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
					.methodOn(RestaurantSearchController.class).getFoodDetailsByRestaurantId(data.getRestaurantId()))
					.withRel("FoodMenu");
			data.add(getAllFood);
		}
		return createRestaurantDetailResponse(data);
	}

	/*
	 * @GetMapping("/{restaurant_id}/food/{food_id}") public ResponseStatusModel
	 * getFoodDetailsByFoodId(@PathVariable("restaurant_id") String restaurantId,
	 * 
	 * @PathVariable("food_id") String foodId) { if (logger.isDebugEnabled()) {
	 * logger.debug("getting food details of restaurant id=" + restaurantId +
	 * " , food id=" + foodId); } FoodDetails data =
	 * service.getFoodDetailsOfARestuarant(restaurantId, foodId); if
	 * (logger.isDebugEnabled()) { logger.debug("Food Details" + data); } return
	 * createGenericResponse(data); }
	 */

	@GetMapping(value="/{restaurant_id}/validate/{latitude}/{longitude}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public DeliveryInfoResponse validateDeliveryAddress(@PathVariable("restaurant_id") String restaurantId,
			@PathVariable Double latitude, @PathVariable Double longitude) {
		if (logger.isDebugEnabled()) {
		}
		boolean data = service.validateDeliveryAddress(restaurantId, latitude, longitude);
		DeliveryInfoResponse status = new DeliveryInfoResponse();
		if (data == false) {
			if (logger.isDebugEnabled()) {
				logger.debug("No Data Available");
			}
			status.setMessage("Delivery is not available for your area");
			status.setStatusCode(401);
			status.setStatus("SUCCESS");
			status.setResult(data);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Success Response");
			}
			status.setMessage("Delivery is available for your area");
			status.setStatusCode(200);
			status.setStatus("SUCCESS");
			status.setResult(data);
		}
		return status;
	}

	@GetMapping(value="/{restaurant_id}/menu", produces = { MediaType.APPLICATION_JSON_VALUE })
	public FoodMenuResponse getFoodDetailsByRestaurantId(@PathVariable("restaurant_id") String restaurantId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getting food details of restaurant id=" + restaurantId);
		}
		List<FoodDetails> data = service.getAllFoodDetailsByRestaurantId(restaurantId);

		return createResponseForFoods(data);
	}

	@PutMapping(value="/{restaurant_id}/reviews/{rating}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public RestaurantDetailResponse updateRatingDetailOfRestaurant(@PathVariable("restaurant_id") String restaurantId,
			@PathVariable("rating") float rating) {
		if (logger.isDebugEnabled()) {
			logger.debug("getting food details of restaurant id=" + restaurantId + " , rating=" + rating);
		}

		RestaurantModel resObj = service.updateRatingBasedOnRestaurantId(restaurantId, rating);
		return createRestaurantDetailResponse(resObj);

	}

	private List<RestaurantModel> createLinksForRestaurant(List<RestaurantModel> data) {
		for (RestaurantModel r : data) {
			String restaurantId = r.getRestaurantId();
			Link selfLink = ControllerLinkBuilder.linkTo(RestaurantSearchController.class).slash(restaurantId)
					.withSelfRel();
			Link getAllFood = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
					.methodOn(RestaurantSearchController.class).getFoodDetailsByRestaurantId(restaurantId))
					.withRel("FoodMenu");
			r.add(selfLink, getAllFood);
		}
		return data;
	}

	/*
	 * private List<FoodDetails> createLinksForFood(List<FoodDetails> data) { for
	 * (FoodDetails f : data) { String restaurantId =f.getRestaurantId(); String
	 * foodId=f.getFoodId(); Link selfLink =
	 * ControllerLinkBuilder.linkTo(ControllerLinkBuilder
	 * .methodOn(RestaurantSearchController.class).getFoodDetailsByFoodId(
	 * restaurantId, foodId)) .withSelfRel(); f.add(selfLink); } return data; }
	 */

	private RestaurantsResponse createRestaurantResponse(Page<RestaurantModel> data) {
		RestaurantsResponse responseStatus = new RestaurantsResponse();
		if (data != null && !data.getContent().isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Success Response");
			}
			responseStatus.setStatusCode(200);
			responseStatus.setStatus("SUCCESS");
			responseStatus.setData(data.getContent());
			responseStatus.setPageNo(data.getNumber());
			responseStatus.setPageSize(data.getSize());
			responseStatus.setTotalpages(data.getTotalPages());
			responseStatus.setTotalElements(data.getNumberOfElements());

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("No Data Available");
			}
			responseStatus.setStatusCode(401);
			responseStatus.setStatus("SUCCESS");
			responseStatus.setMessage("No Data Found");
		}
		return responseStatus;
	}

	private RestaurantDetailResponse createRestaurantDetailResponse(RestaurantModel data) {
		RestaurantDetailResponse responseStatus = new RestaurantDetailResponse();
		if (data != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Success Response");
			}
			responseStatus.setStatusCode(200);
			responseStatus.setStatus("SUCCESS");
			responseStatus.setData(data);

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("No Data Available");
			}
			responseStatus.setStatusCode(401);
			responseStatus.setStatus("FAILURE");
			responseStatus.setMessage("No Data Found");
		}
		return responseStatus;
	}

	private FoodMenuResponse createResponseForFoods(List<FoodDetails> data) {
		FoodMenuResponse responseStatus = new FoodMenuResponse();
		if (!data.isEmpty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("Creating Success Response");
			}
			responseStatus.setData(data);
			responseStatus.setStatusCode(200);
			responseStatus.setStatus("SUCCESS");
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("No Data Available");
			}
			responseStatus.setStatusCode(401);
			responseStatus.setStatus("SUCCESS");
			responseStatus.setMessage("No Data Found");
		}
		return responseStatus;
	}

}
