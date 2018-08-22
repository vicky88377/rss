package com.mindtree.restaurantsearchservice.service;

import org.springframework.stereotype.Component;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;

@Component
public class RestaurantSearchServiceImpl implements RestaurantSearchServiceInterface {

	@Override
	public RestaurantModel getRestaurantByBudgetAndArea(String location, String budget, int pageNo) {
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByCuisineAndArea(String location, String cuisine, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByNameAndArea(String location, String restaurantName, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByRatingAndArea(String location, int rating, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByArea(String location, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByLocationAndDistance(String latitude, String longitude, float distance,
			int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByBudgetAndLocation(String latitude, String longitude, String budget,
			int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByNameAndLocation(String latitude, String longitude, String name, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByCuisineAndLocation(String latitude, String longitude, String cuisine,
			int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByRatingndLocation(String latitude, String longitude, int rating, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getResaurantById(long resId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateDeliveryAddress(long resId, String latitude, String longitude) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
