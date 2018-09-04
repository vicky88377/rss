package com.mindtree.restaurantsearchservice.model;

public class RestaurantDetailResponse extends ResponseStatusModel {
	private RestaurantModel data;

	public RestaurantModel getData() {
		return data;
	}

	public void setData(RestaurantModel data) {
		this.data = data;
	}

}
