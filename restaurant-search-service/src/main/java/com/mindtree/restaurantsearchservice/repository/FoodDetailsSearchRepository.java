package com.mindtree.restaurantsearchservice.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mindtree.restaurantsearchservice.model.FoodDetails;

public interface FoodDetailsSearchRepository extends ElasticsearchRepository<FoodDetails, String> {

}