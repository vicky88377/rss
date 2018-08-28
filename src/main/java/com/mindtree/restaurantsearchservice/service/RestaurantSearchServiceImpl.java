package com.mindtree.restaurantsearchservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.stereotype.Service;
import com.mindtree.restaurantsearchservice.dao.SearchDao;
import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;

@Service
public class RestaurantSearchServiceImpl implements RestaurantSearchServiceInterface {

	private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchServiceImpl.class);
	@Autowired
	SearchDao searchDao;

	@Value("${restaurant.page.size}")
	private Integer pageSize;

	@Override
	public Page<RestaurantModel> getRestaurantByAreaAndFilterParam(String location, String cuisine, float budget,
			float rating, String name, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<RestaurantModel> data = null;
		if (logger.isDebugEnabled()) {
			logger.debug("param data: area: " + location + " cuisine: " + cuisine + " budget: " + budget + " rating: "
					+ rating + " restauratnName: " + name + " pageNo: " + pageNo);
		}
		if (name != null && !name.isEmpty()) {
			data = searchDao.findByAreaAndNameDAO(location, name, pageable);
		} else if (cuisine != null && !cuisine.isEmpty()) {

			data = searchDao.findByAreaAndCuisineDAO(location, cuisine, rating, budget, pageable);
		} else {
			// fetch all restaurant based on default condition
			data = searchDao.findByAreaRatingBudgetDAO(location, rating, budget, pageable);
		}
		if (data != null && logger.isDebugEnabled()) {
			logger.debug("response data: " + data.getContent());
		}
		return data;
	}

	/*@Override
	public Page<RestaurantModel> getRestaurantByArea(String location, int pageNo) {
		if (logger.isDebugEnabled()) {
			logger.debug("params data: location: " + location + " pageNo:" + pageNo);
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		float rating = 0, budget = 0;
		Page<RestaurantModel> data = restaurantRepo.findByAreaRatingBudgetDAO(location, rating, budget, pageable);
		if (data != null && logger.isDebugEnabled()) {
			logger.debug("response data: data: " + data.getContent());
		}
		return data;
	}*/

	@Override
	public Page<RestaurantModel> getRestaurantByLocationAndFilterParam(double latitude, double longitude,
			float distance, String cuisine, float budget, float rating, String name, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		if (logger.isDebugEnabled()) {
			logger.debug("param data: latitude: "+latitude+" longitude: "+longitude + " cuisine: " + cuisine + 
					" budget: " + budget + " rating: "
					+ rating + " restauratnName: " + name + " pageNo: " + pageNo);
		}
		Page<RestaurantModel> data = null;
		if (name != null && !name.isEmpty()) {
			data = searchDao.findByLonLatAndNameDAO(name, distance, latitude, longitude, pageable);
		} else if (cuisine != null && !cuisine.isEmpty()) {
			// pass all the parameter to the repository to fetch restaurant based on cuisine
			// and location
			data = searchDao.findByLonLatRatingBudgetDAO(cuisine, rating, budget, distance, latitude, longitude,
					pageable);
		} else {
			data = searchDao.findByLonAndLatDAO(rating, budget, distance, latitude, longitude, pageable);
		}
		if (data != null && logger.isDebugEnabled()) {
			logger.debug("response data: data: " + data.getContent());
		}
		return data;
	}

	@Override
	public Page<RestaurantModel> getRestaurantByLocation(double latitude, double longitude, float distance,
			int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		float rating = 0, budget = 0;
		if (logger.isDebugEnabled()) {
			logger.debug("param data: latitude: "+latitude+" longitude: "+longitude + 
					" distance: "+ distance +" pageNo: " + pageNo);
		}
		Page<RestaurantModel> data = searchDao.findByLonAndLatDAO(rating, budget, distance, latitude, longitude,
				pageable);
		if (data != null && logger.isDebugEnabled()) {
			logger.debug("response data: data: " + data.getContent());
		}
		return data;
	}

	@Override
	public RestaurantModel getResaurantById(String resId) {
		if (logger.isDebugEnabled()) {
			logger.debug("param data: resId: "+resId);
		}
		RestaurantModel data = searchDao.findByIdDAO(resId);
		if (data != null && logger.isDebugEnabled()) {
			logger.debug("response data: data: " + data);
		}
		return data;
	}

	@Override
	public boolean validateDeliveryAddress(String resId, double latitude, double longitude) {
		
		
		RestaurantModel data = searchDao.findByIdDAO(resId);
		if (logger.isDebugEnabled()) {
			logger.debug("param data: latitude: "+latitude+" longitude: "+ longitude);
		}
		double lat = data.getLatitude();
		double lon = data.getLongitude();
		double distance = Math.sqrt(Math.pow((latitude - lat), 2) + Math.pow((longitude - lon), 2));
		if (distance <= 5) {
			return true;
		}
		return false;
	}

	@Override
	public FoodDetails getFoodDetailsOfARestuarant(String resId, String foodId) {
		if (logger.isDebugEnabled()) {
			logger.debug("param data: resId: "+resId+" foodId: "+ foodId);
		}
		if(resId!=null && foodId!=null && !resId.isEmpty() && !foodId.isEmpty()) {
		return searchDao.getFoodDetailsByRestaurantIdAndFoodIdDAO(resId, foodId);
		}
		else {
			return null;
		}
	}

	@Override
	public List<FoodDetails> getAllFoodDetailsByRestaurantId(String resId) {
		if (logger.isDebugEnabled()) {
			logger.debug("param data: resId: "+resId);
		}
		if(resId!=null && !resId.isEmpty()) {
		return searchDao.getFoodDetailsByRestaurantIdDAO(resId);
		}
		else {
			return null;
		}
	}

	@Override
	public RestaurantModel updateRatingBasedOnRestaurantId(String resId, float rating) {
		RestaurantModel resObj=searchDao.findByIdDAO(resId);
		resObj.setRating(rating);
		RestaurantModel resObj1=searchDao.updateRestaurantDetails(resObj);
		return resObj1;
	}

}
