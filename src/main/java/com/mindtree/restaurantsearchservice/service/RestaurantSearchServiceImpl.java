package com.mindtree.restaurantsearchservice.service;

import org.springframework.stereotype.Component;

import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;

@Component
public class RestaurantSearchServiceImpl implements RestaurantSearchServiceInterface {

	@Override
		public RestaurantModel getRestaurantByAreaAndFilterParam(String location, String cuisine, String budget,
			String rating, String name, int pageNo)  {
		
		if(name!=null && !name.isEmpty()) {
			// pass all the parameter to the repository to fetch restaurant based on name and location
		}
		else if(cuisine!=null && !cuisine.isEmpty()) {
			// pass all the parameter to the repository to fetch restaurant based on cuisine and location
		}
		else {
			//fetch all restaurant based on default condition
		}
		return null;
		}
		
	
	

	

	@Override
	public RestaurantModel getRestaurantByArea(String location, int pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByLocationAndFilterParam(String latitude, String longitude, float distance,
			String cuisine, String budget, String rating, String name, int pageNo) {
		if(name!=null && !name.isEmpty()) {
			// pass all the parameter to the repository to fetch restaurant based on name and location
		}
		else if(cuisine!=null && !cuisine.isEmpty()) {
			// pass all the parameter to the repository to fetch restaurant based on cuisine and location
		}
		else {
			//fetch all restaurant based on default condition
		}
		return null;
	}

	@Override
	public RestaurantModel getRestaurantByLocation(String latitude, String longitude, float distance) {
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

	@Override
	public FoodDetails getFoodDetailsOfARestuarant(long resId, long foodId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
