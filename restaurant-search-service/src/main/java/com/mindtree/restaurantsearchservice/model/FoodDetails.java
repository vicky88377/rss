package com.mindtree.restaurantsearchservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodDetails {
	@JsonProperty("food_id")
	private String foodId;
	@JsonProperty("food_name")
	private String foodName;
	private String price;
	private String description;
	@JsonProperty("availability_status")
	private String availabilityStatus;
	@JsonProperty("restuarant_id")
	private String restuarantId;
	@JsonProperty("cuisine_id")
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
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
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
	public String getRestuarantId() {
		return restuarantId;
	}

	/**
	 * @param restuarantId
	 *            the restuarantId to set
	 */
	public void setRestuarantId(String restuarantId) {
		this.restuarantId = restuarantId;
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
