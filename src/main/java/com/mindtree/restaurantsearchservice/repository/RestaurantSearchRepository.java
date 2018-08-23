package com.mindtree.restaurantsearchservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;

public interface RestaurantSearchRepository extends ElasticsearchRepository<RestaurantModel, String> {
	
	// Getting restaurant details by area(mandatory), rating and budget(optional) parameters
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"address\",\"locality\"]}},{\"range\":{\"rating\":{\"lte\":5,\"gte\":?1}}},{\"range\":{\"minimum_order_price\":{\"gte\":?2}}}]}},\"sort\":[{\"rating\":{\"order\":\"desc\"}}]")
	public Page<RestaurantModel> findByAreaRatingBudget(String area, float rating, float minimumOrderPrice, Pageable page);

	// Getting restaurant details by area as well as cuisine_type(mandatory), rating and budget(optional) parameters
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"address\",\"locality\"]}},{\"match\":{\"cuisen_type\":\"?1\"}},{\"match_phrase\":{\"cuisen_type\":\"?1\"}},{\"range\":{\"rating\":{\"lte\":5,\"gte\":?2}}},{\"range\":{\"minimum_order_price\":{\"gte\":?3}}}]}},\"sort\":[{\"rating\":{\"order\":\"desc\"}}]")
	public Page<RestaurantModel> findByAreaAndCuisine(String area, String cuisineType, float rating, float minimumOrderPrice, Pageable page);

	// Getting restaurant details by area and restaurant name
	@Query("{\"bool\":{\"should\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"address\",\"locality\"]}}],\"must\":[{\"match_phrase\":{\"restaurant_name\":{\"query\":\"?1\",\"boost\":3}}},{\"match\":{\"restaurant_name\":{\"query\":\"?1\"}}}]}}")
	public Page<RestaurantModel> findByAreaAndName(String area, String restaurantName, Pageable page);
	
	@Query("{\"match\":{\"restaurant_id\":\"?0\"}}")
	public Page<RestaurantModel> findById(String id, Pageable page);
	
}