package com.mindtree.restaurantsearchservice.service;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;
/**
 * 
 * @author M1046464
 * still need add methods for fetching foodetails 
 */
public interface RestaurantSearchServiceInterface {
         public RestaurantModel getRestaurantByBudgetAndArea(String location,String budget,int pageNo); 
         public RestaurantModel getRestaurantByCuisineAndArea(String location,String cuisine,int pageNo);
         public RestaurantModel getRestaurantByNameAndArea(String location,String restaurantName,int pageNo);
         public RestaurantModel getRestaurantByRatingAndArea(String location,int rating,int pageNo);
         public RestaurantModel getRestaurantByArea(String location,int pageNo);
         public RestaurantModel getRestaurantByLocationAndDistance(String latitude,String longitude,float distance,int pageNo);
         public RestaurantModel getRestaurantByBudgetAndLocation(String latitude,String longitude,String budget,int pageNo);
         public RestaurantModel getRestaurantByNameAndLocation(String latitude,String longitude,String name,int pageNo);
         public RestaurantModel getRestaurantByCuisineAndLocation(String latitude,String longitude,String cuisine,int pageNo);
         public RestaurantModel getRestaurantByRatingndLocation(String latitude,String longitude,int rating,int pageNo);
         public RestaurantModel getResaurantById(long resId);
         //public RestaurantModel getRestaurantReviews(long resId,long pageNo);
         public boolean validateDeliveryAddress(long resId,String latitude,String longitude);
         
         
         
}