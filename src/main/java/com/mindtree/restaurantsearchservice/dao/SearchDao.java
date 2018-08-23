package com.mindtree.restaurantsearchservice.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;

public interface SearchDao {
	
	public Page<RestaurantModel> findByAreaRatingBudgetDAO(String area, float rating, float minimumOrderPrice,
			Pageable page);
	
	public Page<RestaurantModel> findByAreaAndCuisineDAO(String area, String cuisineType, float rating,
			float minimumOrderPrice, Pageable page);
	
	public Page<RestaurantModel> findByAreaAndNameDAO(String area, String restaurantName, Pageable page);
	
	public Page<RestaurantModel> findByIdDAO(String id, Pageable page);
	
	public Page<RestaurantModel> findByLonAndLatDAO(float rating, float minimumOrderPrice,float distance, double latitude, double longitude, Pageable page);

	public Page<RestaurantModel> findByLonLatAndNameDAO(String restaurantName, float distance, double latitude,
			double longitude, Pageable page);
	
	public Page<RestaurantModel> findByLonLatRatingBudgetDAO(String cuisineType, float rating, float minimumOrderPrice,
			float distance, double latitude, double longitude, Pageable page);
	
}
