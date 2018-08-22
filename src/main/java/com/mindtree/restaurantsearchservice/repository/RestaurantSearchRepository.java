package com.mindtree.restaurantsearchservice.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;

public interface RestaurantSearchRepository extends ElasticsearchRepository<RestaurantModel, String> {

}