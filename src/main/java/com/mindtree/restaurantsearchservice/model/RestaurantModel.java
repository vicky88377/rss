package com.mindtree.restaurantsearchservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "searchrestaurants", type = "restaurantinfo")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class RestaurantModel extends ResourceSupport {
	@Id
	@JsonProperty("restaurant_id")
	private String restaurantId;

	@JsonProperty("restaurant_name")
	private String restaurantName;

	@JsonProperty("start_time")
	private String restaurantOpenTime;

	@JsonProperty("end_time")
	private String restaurantCloseTime;

	@JsonProperty("minimum_order_price")
	private float minimumOrder;

	@JsonProperty("average_delivery_time")
	private String averageDeliveryTime;

	@JsonProperty("delivery_point")
	private String deliveryPoint;

	@JsonProperty("display_image")
	private String restaurantImage;

	private String address;

	private String city;

	private String state;

	private String country;

	private String locality;
	@JsonProperty("cuisen_type")
	private String cuisine;

	@JsonProperty("locality_verbose")
	private String localityVerbose;

	private float rating;

	@JsonProperty("offer_info")
	private String offer;

	private double latitude;
	private double longitude;

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
	public float getMinimumOrder() {
		return minimumOrder;
	}

	/**
	 * @param minimumOrder
	 *            the minimumOrder to set
	 */
	public void setMinimumOrder(float minimumOrder) {
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
	public float getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(float rating) {
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
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the cuisine
	 */
	public String getCuisine() {
		return cuisine;
	}

	/**
	 * @param cuisine
	 *            the cuisine to set
	 */
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

}
