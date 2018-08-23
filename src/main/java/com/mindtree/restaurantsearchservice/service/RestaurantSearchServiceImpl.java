package com.mindtree.restaurantsearchservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.repository.RestaurantSearchRepository;

@Component
public class RestaurantSearchServiceImpl implements RestaurantSearchServiceInterface {

	@Autowired
	RestaurantSearchRepository restaurantRepo;

	@Value("$restaurant.page.size")
	private int pageSize;

	@Override
	public Page<RestaurantModel> getRestaurantByAreaAndFilterParam(String location, String cuisine, float budget,
			float rating, String name, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<RestaurantModel> data = null;
		if (name != null && !name.isEmpty()) {
			// pass all the parameter to the repository to fetch restaurant based on name
			// and location
		} else if (cuisine != null && !cuisine.isEmpty()) {

			data = restaurantRepo.findByAreaAndCuisine(location, cuisine, rating, budget, pageable);
		} else {
			// fetch all restaurant based on default condition
			data = restaurantRepo.findByAreaRatingBudget(location, rating, budget, pageable);
		}
		return data;
	}

	@Override
	public Page<RestaurantModel> getRestaurantByArea(String location, int pageNo) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		float rating=0, budget=0;
		Page<RestaurantModel> data = restaurantRepo.findByAreaRatingBudget(location, rating, budget, pageable);
		return data;
	}

	@Override
	public Page<RestaurantModel> getRestaurantByLocationAndFilterParam(double latitude, double longitude,
			float distance, String cuisine, float budget, float rating, String name, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<RestaurantModel> data = null;
		if (name != null && !name.isEmpty()) {
			data = restaurantRepo.findByLonLatAndName(name, distance, latitude, longitude, pageable);
		} else if (cuisine != null && !cuisine.isEmpty()) {
			// pass all the parameter to the repository to fetch restaurant based on cuisine
			// and location
			data = restaurantRepo.findByLonLatRatingBudget(cuisine, rating, budget, distance, latitude, longitude,
					pageable);
		} else {
			data = restaurantRepo.findByLonAndLat(rating, budget, distance, latitude, longitude,
					pageable);
		}
		return null;
	}

	@Override
	public Page<RestaurantModel> getRestaurantByLocation(double latitude, double longitude, float distance,
			int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		float rating = 0, budget = 0;
		Page<RestaurantModel> data = restaurantRepo.findByLonAndLat(rating, budget, distance, latitude, longitude,
				pageable);
		return data;
	}

	@Override
	public Page<RestaurantModel> getResaurantById(String resId) {
		Pageable pageable = PageRequest.of(1, pageSize);
		Page<RestaurantModel> data = restaurantRepo.findById(resId, pageable);
		return data;
	}

	@Override
	public boolean validateDeliveryAddress(String resId, String latitude, String longitude) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FoodDetails getFoodDetailsOfARestuarant(String resId, long foodId) {
		// TODO Auto-generated method stub
		return null;
	}

}
