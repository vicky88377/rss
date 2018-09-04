package com.mindtree.restaurantsearchservice.model;

import java.util.List;

public class FoodMenuResponse extends ResponseStatusModel {
	private List<FoodDetails> data;

	public List<FoodDetails> getData() {
		return data;
	}

	public void setData(List<FoodDetails> data) {
		this.data = data;
	}

}
