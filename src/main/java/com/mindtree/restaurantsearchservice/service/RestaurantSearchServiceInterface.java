package com.mindtree.restaurantsearchservice.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
/**
 * 
 * @author M1046464
 * still need add methods for fetching foodetails 
 */
public interface RestaurantSearchServiceInterface {
         
         public Page<RestaurantModel> getRestaurantByAreaAndFilterParam(String location,String cuisine,float budget,
        		 float rating,String name,int pageNo);
         //public Page<RestaurantModel> getRestaurantByArea(String location,int pageNo);
         public Page<RestaurantModel> getRestaurantByLocationAndFilterParam(double latitude,double longitude,float distance,
        		 String cuisine,float budget,float rating,String name,int pageNo);
         public Page<RestaurantModel> getRestaurantByLocation(double latitude,double longitude,float distance,int pageNo);
         public RestaurantModel getResaurantById(String  resId);
         //public RestaurantModel getRestaurantReviews(long resId,long pageNo);
         public FoodDetails getFoodDetailsOfARestuarant(String  resId,String foodId);
         public boolean validateDeliveryAddress(String resId, double latitude, double longitude);
         public List<FoodDetails> getAllFoodDetailsByRestaurantId(String resId);
         public RestaurantModel updateRatingBasedOnRestaurantId(String resId,float rating);
         
         
         
}