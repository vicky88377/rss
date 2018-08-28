package com.mindtree.restaurantsearchservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;

public interface RestaurantSearchRepository extends ElasticsearchRepository<RestaurantModel, String> {

	// Getting restaurant details by area(mandatory), rating and
	// budget(optional) parameters
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"address\",\"locality\"],\"fuzziness\":2}},{\"range\":{\"rating\":{\"lte\":5,\"gte\":?1}}},{\"range\":{\"minimum_order_price\":{\"gte\":?2}}}]}},\"sort\":[{\"rating\":{\"order\":\"desc\"}}]")
	public Page<RestaurantModel> findByAreaRatingBudget(String area, float rating, float minimumOrderPrice,
			Pageable page);

	// Getting restaurant details by area as well as cuisine_type(mandatory),
	// rating and budget(optional) parameters
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"address\",\"locality\"],\"fuzziness\":2}},{\"multi_match\":{\"query\":\"?1\",\"fields\":[\"cuisen_type\"],\"fuzziness\":2}},{\"range\":{\"rating\":{\"lte\":5,\"gte\":?2}}},{\"range\":{\"minimum_order_price\":{\"gte\":?3}}}]}},\"sort\":[{\"rating\":{\"order\":\"desc\"}}]")
	public Page<RestaurantModel> findByAreaAndCuisine(String area, String cuisineType, float rating,
			float minimumOrderPrice, Pageable page);

	// Getting restaurant details by area and restaurant name
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"city\",\"locality\",\"address\"],\"fuzziness\":2}},{\"multi_match\":{\"query\":\"?1\",\"fields\":[\"restaurant_name\"],\"fuzziness\":2}}]}}")
	public Page<RestaurantModel> findByAreaAndName(String area, String restaurantName, Pageable page);

	// Getting restaurant my restaurant id
	@Query("{\"match\":{\"restaurant_id\":\"?0\"}}")
	public Page<RestaurantModel> findById(String id, Pageable page);

	// Getting restaurant details by location, distance, rating & budget 
	@Query("{\"bool\":{\"must\":[{\"range\":{\"rating\":{\"lte\":5,\"gte\":?0}}},{\"range\":{\"minimum_order_price\":{\"gte\":?1}}}],\"filter\":{\"geo_distance\":{\"distance\":\"?2km\",\"location\":{\"lat\":?3,\"lon\":?4}}}}}")
	public Page<RestaurantModel> findByLonAndLat(float rating, float minimumOrderPrice, float distance, double latitude,
			double longitude, Pageable page);

	// Getting restaurant details by restaurant name, location & distance
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"Steakhouse\",\"fields\":[\"restaurant_name\"],\"fuzziness\":2}}],\"filter\":{\"geo_distance\":{\"distance\":\"10km\",\"location\":{\"lat\":-47.95628333,\"lon\":-15.83451389}}}}}")
	public Page<RestaurantModel> findByLonLatAndName(String restaurantName, float distance, double latitude,
			double longitude, Pageable page);

	// Getting restaurant details by location, distance &
	// cuisine_type(mandatory), rating and budget(optional) parameters
	@Query("{\"bool\":{\"must\":[{\"multi_match\":{\"query\":\"North\",\"fields\":[\"cuisen_type\"],\"fuzziness\":2}},{\"range\":{\"rating\":{\"lte\":5,\"gte\":3}}},{\"range\":{\"minimum_order_price\":{\"gte\":200}}}],\"filter\":{\"geo_distance\":{\"distance\":\"1km\",\"location\":{\"lat\":77.696664,\"lon\":12.97537691}}}}},\"sort\":[{\"rating\":{\"order\":\"desc\"}}]")
	public Page<RestaurantModel> findByLonLatRatingBudget(String cuisineType, float rating, float minimumOrderPrice,
			float distance, double latitude, double longitude, Pageable page);
	

}