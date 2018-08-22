package com.mindtree.restaurantsearchservice.service;

import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
/**
 * 
 * @author M1046464
 * still need add methods for fetching foodetails 
 */
public interface RestaurantSearchServiceInterface {
         
         public RestaurantModel getRestaurantByAreaAndFilterParam(String location,String cuisine,String budget,
        		 String rating,String name,int pageNo);
         public RestaurantModel getRestaurantByArea(String location,int pageNo);
         public RestaurantModel getRestaurantByLocationAndFilterParam(String latitude,String longitude,float distance,
        		 String cuisine,String budget,String rating,String name,int pageNo);
         public RestaurantModel getRestaurantByLocation(String latitude,String longitude,float distance);
         public RestaurantModel getResaurantById(long resId);
         //public RestaurantModel getRestaurantReviews(long resId,long pageNo);
         public FoodDetails getFoodDetailsOfARestuarant(long resId,long foodId);
         public boolean validateDeliveryAddress(long resId,String latitude,String longitude);
         
         
         
}