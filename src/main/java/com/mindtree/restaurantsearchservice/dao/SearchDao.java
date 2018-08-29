package com.mindtree.restaurantsearchservice.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;

import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;

public interface SearchDao {
	
	// restaurant search APIs
	public Page<RestaurantModel> findByAreaRatingBudgetDAO(String area, float rating, float minimumOrderPrice,
			Pageable page);
	
	public Page<RestaurantModel> findByAreaAndCuisineDAO(String area, String cuisineType, float rating,
			float minimumOrderPrice, Pageable page);
	
	public Page<RestaurantModel> findByAreaAndNameDAO(String area, String restaurantName, Pageable page);
	
	public RestaurantModel findByIdDAO(String id);
	
	public Page<RestaurantModel> findByLonAndLatDAO(float rating, float minimumOrderPrice,float distance, double latitude, double longitude, Pageable page);

	public Page<RestaurantModel> findByLonLatAndNameDAO(String restaurantName, float distance, double latitude,
			double longitude, Pageable page);
	
	public Page<RestaurantModel> findByLonLatRatingBudgetDAO(String cuisineType, float rating, float minimumOrderPrice,
			float distance, double latitude, double longitude, Pageable page);
	
	// food search APIs
	public List<FoodDetails> getFoodDetailsByRestaurantIdDAO(String resId);
	
	public FoodDetails getFoodDetailsByRestaurantIdAndFoodIdDAO(String resId, String foodId);
	
	public RestaurantModel updateRestaurantDetails(RestaurantModel model);
	
}
