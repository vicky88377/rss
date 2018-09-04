package com.mindtree.restaurantsearchservice.vo;

public class SearchParams{
   private String cuisine;
   private float budget;
   private String restaurantName;
   private float rating;
   private int page;
   
   
public String getCuisine() {
	return cuisine;
}
public void setCuisine(String cuisine) {
	this.cuisine = cuisine;
}
public float getBudget() {
	return budget;
}
public void setBudget(float budget) {
	this.budget = budget;
}
public String getRestaurantName() {
	return restaurantName;
}
public void setRestaurantName(String restaurantName) {
	this.restaurantName = restaurantName;
}
public float getRating() {
	return rating;
}
public void setRating(float rating) {
	this.rating = rating;
}
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
}
