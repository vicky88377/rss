package com.mindtree.restaurantsearchservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;

public interface RestaurantSearchRepository extends ElasticsearchRepository<RestaurantModel, String> {

	// Getting restaurant details by area(mandatory), rating and
	// budget(optional) parameters
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"address\",\"locality\"]}},{\"range\":{\"rating\":{\"lte\":5,\"gte\":?1}}},{\"range\":{\"minimum_order_price\":{\"gte\":?2}}}]}},\"sort\":[{\"rating\":{\"order\":\"desc\"}}]")
	public Page<RestaurantModel> findByAreaRatingBudget(String area, float rating, float minimumOrderPrice,
			Pageable page);

	// Getting restaurant details by area as well as cuisine_type(mandatory),
	// rating and budget(optional) parameters
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"address\",\"locality\"]}},{\"match\":{\"cuisen_type\":\"?1\"}},{\"match_phrase\":{\"cuisen_type\":\"?1\"}},{\"range\":{\"rating\":{\"lte\":5,\"gte\":?2}}},{\"range\":{\"minimum_order_price\":{\"gte\":?3}}}]}},\"sort\":[{\"rating\":{\"order\":\"desc\"}}]")
	public Page<RestaurantModel> findByAreaAndCuisine(String area, String cuisineType, float rating,
			float minimumOrderPrice, Pageable page);

	// Getting restaurant details by area and restaurant name
	@Query("{\"bool\":{\"should\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"address\",\"locality\"]}}],\"must\":[{\"match_phrase\":{\"restaurant_name\":{\"query\":\"?1\",\"boost\":3}}},{\"match\":{\"restaurant_name\":{\"query\":\"?1\"}}}]}}")
	public Page<RestaurantModel> findByAreaAndName(String area, String restaurantName, Pageable page);

	// Getting restaurant my restaurant id
	@Query("{\"match\":{\"restaurant_id\":\"?0\"}}")
	public Page<RestaurantModel> findById(String id, Pageable page);

	// Getting restaurant details by location, distance, rating & budget 
	@Query("{\"bool\":{\"must\":[{\"range\":{\"rating\":{\"lte\":5,\"gte\":?0}}},{\"range\":{\"minimum_order_price\":{\"gte\":?1}}}],\"filter\":{\"geo_distance\":{\"distance\":\"?2km\",\"location\":{\"lat\":?3,\"lon\":?4}}}}}")
	public Page<RestaurantModel> findByLonAndLat(float rating, float minimumOrderPrice, float distance, double latitude,
			double longitude, Pageable page);

	// Getting restaurant details by restaurant name, location & distance
	@Query("{\"bool\":{\"must\":[{\"match\":{\"restaurant_name\":\"?0\"}},{\"match_phrase\":{\"restaurant_name\":\"?0\"}}],\"filter\":{\"geo_distance\":{\"distance\":\"?1km\",\"location\":{\"lat\":?2,\"lon\":?3}}}}}")
	public Page<RestaurantModel> findByLonLatAndName(String restaurantName, float distance, double latitude,
			double longitude, Pageable page);

	// Getting restaurant details by location, distance &
	// cuisine_type(mandatory), rating and budget(optional) parameters
	@Query("{\"bool\":{\"must\":[{\"match\":{\"cuisen_type\":\"?0\"}},{\"match_phrase\":{\"cuisen_type\":\"?0\"}},{\"range\":{\"rating\":{\"lte\":5,\"gte\":?1}}},{\"range\":{\"minimum_order_price\":{\"gte\":?2}}}],\"filter\":{\"geo_distance\":{\"distance\":\"?3km\",\"location\":{\"lat\":?4,\"lon\":?5}}}}},\"sort\":[{\"rating\":{\"order\":\"desc\"}}]")
	public Page<RestaurantModel> findByLonLatRatingBudget(String cuisineType, float rating, float minimumOrderPrice,
			float distance, double latitude, double longitude, Pageable page);

}