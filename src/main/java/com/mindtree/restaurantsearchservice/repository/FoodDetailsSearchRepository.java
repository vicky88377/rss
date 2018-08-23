package com.mindtree.restaurantsearchservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mindtree.restaurantsearchservice.model.FoodDetails;

public interface FoodDetailsSearchRepository extends ElasticsearchRepository<FoodDetails, String> {

	// search all food menu of a single restaurant using restaurant id
	@Query("{\"match\":{\"restaurant_id\":\"?0\"}}")
	public Page<FoodDetails> getFoodDetailsByRestaurantId(String resId, Pageable page);
	
	// search single food detail of a single restaurant using restaurant id and food id
	@Query("{\"bool\":{\"must\":[{\"match\":{\"restaurant_id\":\"7304307\"}},{\"match\":{\"foodId\":\"44\"}}]}}")
	public FoodDetails getFoodDetailsByRestaurantIdAndFoodId(String resId, String foodId);
	
}