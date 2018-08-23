package com.mindtree.restaurantsearchservice.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.repository.FoodDetailsSearchRepository;
import com.mindtree.restaurantsearchservice.repository.RestaurantSearchRepository;

public class SearchDaoImpl implements SearchDao {

	private RestaurantSearchRepository restaurantSearchRepository;
	private FoodDetailsSearchRepository foodDetailsSearchRepository;
	
	@Override
	public Page<RestaurantModel> findByAreaRatingBudgetDAO(String area, float rating, float minimumOrderPrice,
			Pageable page) {
		
		return restaurantSearchRepository.findByAreaRatingBudget(area, rating, minimumOrderPrice, page);
	}

	@Override
	public Page<RestaurantModel> findByAreaAndCuisineDAO(String area, String cuisineType, float rating,
			float minimumOrderPrice, Pageable page) {
		
		return restaurantSearchRepository.findByAreaAndCuisine(area, cuisineType, rating, minimumOrderPrice, page);
	}

	@Override
	public Page<RestaurantModel> findByAreaAndNameDAO(String area, String restaurantName, Pageable page) {
		
		return restaurantSearchRepository.findByAreaAndName(area, restaurantName, page);
	}

	@Override
	public RestaurantModel findByIdDAO(String id) {
		Optional<RestaurantModel> resData=restaurantSearchRepository.findById(id);
		if(resData.isPresent()) {
			return resData.get();
		}
		return null;
		
	}

	@Override
	public Page<RestaurantModel> findByLonAndLatDAO(float rating, float minimumOrderPrice, float distance, double latitude, double longitude, Pageable page) {
		
		return restaurantSearchRepository.findByLonAndLat(rating, minimumOrderPrice, distance, latitude, longitude, page);
	}

	@Override
	public Page<RestaurantModel> findByLonLatAndNameDAO(String restaurantName, float distance, double latitude,
			double longitude, Pageable page) {
		
		return restaurantSearchRepository.findByLonLatAndName(restaurantName, distance, latitude, longitude, page);
	}

	@Override
	public Page<RestaurantModel> findByLonLatRatingBudgetDAO(String cuisineType, float rating, float minimumOrderPrice,
			float distance, double latitude, double longitude, Pageable page) {
		
		return restaurantSearchRepository.findByLonLatRatingBudget(cuisineType, rating, minimumOrderPrice, distance, latitude, longitude, page);
	}

	@Override
	public Page<FoodDetails> getFoodDetailsByRestaurantIdDAO(String resId,Pageable page) {
		
		return foodDetailsSearchRepository.getFoodDetailsByRestaurantId(resId,page);
	}

	@Override
	public FoodDetails getFoodDetailsByRestaurantIdAndFoodIdDAO(String resId, String foodId) {
		
		return foodDetailsSearchRepository.getFoodDetailsByRestaurantIdAndFoodId(resId, foodId);
	}
	
}
