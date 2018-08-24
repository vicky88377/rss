package com.mindtree.restaurantsearchservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName="searchfood",type="foodmenu")
public class FoodDetails {
	@Id
	@JsonProperty("foodId")
	private String foodId;
	
	@JsonProperty("food_name")
	private String foodName;
	
	@JsonProperty("food_price")
	private float foodPrice;
	
	private String description;
	
	@JsonProperty("availability_status")
	private String availabilityStatus;
	
	@JsonProperty("restaurant_id")
	private String restaurantId;
	
	@JsonProperty("cuisines_id")
	private String cuisineId;

	/**
	 * @return the foodId
	 */
	public String getFoodId() {
		return foodId;
	}

	/**
	 * @param foodId
	 *            the foodId to set
	 */
	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	/**
	 * @return the foodName
	 */
	public String getFoodName() {
		return foodName;
	}

	/**
	 * @param foodName
	 *            the foodName to set
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	/**
	 * @return the price
	 */
	public float getFoodPrice() {
		return foodPrice;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setFoodPrice(float foodPrice) {
		this.foodPrice = foodPrice;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the availabilityStatus
	 */
	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	/**
	 * @param availabilityStatus
	 *            the availabilityStatus to set
	 */
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	/**
	 * @return the restuarantId
	 */
	public String getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restuarantId
	 *            the restuarantId to set
	 */
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the cuisineId
	 */
	public String getCuisineId() {
		return cuisineId;
	}

	/**
	 * @param cuisineId
	 *            the cuisineId to set
	 */
	public void setCuisineId(String cuisineId) {
		this.cuisineId = cuisineId;
	}

}
