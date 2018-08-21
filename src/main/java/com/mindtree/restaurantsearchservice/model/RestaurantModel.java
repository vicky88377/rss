package com.mindtree.restaurantsearchservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantModel {
	@JsonProperty("restaurant_id")
	private String restaurantId;
	@JsonProperty("restaurant_name")
	private String restaurantName;
	@JsonProperty("restaurant_open_time")
	private String restaurantOpenTime;
	@JsonProperty("restaurant_close_time")
	private String restaurantCloseTime;
	@JsonProperty("minimum_order")
	private String minimumOrder;
	@JsonProperty("average_delivery_time")
	private String averageDeliveryTime;
	@JsonProperty("delivery_point")
	private String deliveryPoint;
	@JsonProperty("restaurant_image")
	private String restaurantImage;
	private String address;
	private String city;
	private String state;
	private String country;
	private String locality;
	@JsonProperty("locality_verbose")
	private String localityVerbose;
	private String rating;
	private String offer;
	private String latitude;
	private String longitude;

	/**
	 * @return the restaurantId
	 */
	public String getRestaurantId() {
		return restaurantId;
	}

	/**
	 * @param restaurantId
	 *            the restaurantId to set
	 */
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}

	/**
	 * @param restaurantName
	 *            the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	/**
	 * @return the restaurantOpenTime
	 */
	public String getRestaurantOpenTime() {
		return restaurantOpenTime;
	}

	/**
	 * @param restaurantOpenTime
	 *            the restaurantOpenTime to set
	 */
	public void setRestaurantOpenTime(String restaurantOpenTime) {
		this.restaurantOpenTime = restaurantOpenTime;
	}

	/**
	 * @return the restaurantCloseTime
	 */
	public String getRestaurantCloseTime() {
		return restaurantCloseTime;
	}

	/**
	 * @param restaurantCloseTime
	 *            the restaurantCloseTime to set
	 */
	public void setRestaurantCloseTime(String restaurantCloseTime) {
		this.restaurantCloseTime = restaurantCloseTime;
	}

	/**
	 * @return the minimumOrder
	 */
	public String getMinimumOrder() {
		return minimumOrder;
	}

	/**
	 * @param minimumOrder
	 *            the minimumOrder to set
	 */
	public void setMinimumOrder(String minimumOrder) {
		this.minimumOrder = minimumOrder;
	}

	/**
	 * @return the averageDeliveryTime
	 */
	public String getAverageDeliveryTime() {
		return averageDeliveryTime;
	}

	/**
	 * @param averageDeliveryTime
	 *            the averageDeliveryTime to set
	 */
	public void setAverageDeliveryTime(String averageDeliveryTime) {
		this.averageDeliveryTime = averageDeliveryTime;
	}

	/**
	 * @return the deliveryPoint
	 */
	public String getDeliveryPoint() {
		return deliveryPoint;
	}

	/**
	 * @param deliveryPoint
	 *            the deliveryPoint to set
	 */
	public void setDeliveryPoint(String deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}

	/**
	 * @return the restaurantImage
	 */
	public String getRestaurantImage() {
		return restaurantImage;
	}

	/**
	 * @param restaurantImage
	 *            the restaurantImage to set
	 */
	public void setRestaurantImage(String restaurantImage) {
		this.restaurantImage = restaurantImage;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the locality
	 */
	public String getLocality() {
		return locality;
	}

	/**
	 * @param locality
	 *            the locality to set
	 */
	public void setLocality(String locality) {
		this.locality = locality;
	}

	/**
	 * @return the localityVerbose
	 */
	public String getLocalityVerbose() {
		return localityVerbose;
	}

	/**
	 * @param localityVerbose
	 *            the localityVerbose to set
	 */
	public void setLocalityVerbose(String localityVerbose) {
		this.localityVerbose = localityVerbose;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the offer
	 */
	public String getOffer() {
		return offer;
	}

	/**
	 * @param offer
	 *            the offer to set
	 */
	public void setOffer(String offer) {
		this.offer = offer;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
