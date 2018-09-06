package com.mindtree.restaurantsearchservice.service;

//import org.springframework.stereotype.Component;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mindtree.restaurantsearchservice.dao.SearchDao;
import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.vo.AreaSearchParams;
import com.mindtree.restaurantsearchservice.vo.CoOrdinateSearchParams;

@Service
public class RestaurantSearchServiceImpl implements RestaurantSearchServiceInterface {

	private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchServiceImpl.class);
	@Autowired
	SearchDao searchDao;

	@Value("${restaurant.page.size}")
	private Integer pageSize;

	@Override
	public Page<RestaurantModel> getRestaurantByAreaAndFilterParam(AreaSearchParams areaParams) {
		
		Pageable pageable = PageRequest.of(areaParams.getPage(), pageSize);
		Page<RestaurantModel> data = null;
		if (logger.isDebugEnabled()) {
			logger.debug("param data: area: " + areaParams.getArea() + " cuisine: " + areaParams.getCuisine() 
			+ " budget: " + areaParams.getBudget() + " rating: "
					+ areaParams.getRating() + " restauratnName: " + areaParams.getRestaurantName() + 
					" pageNo: " + areaParams.getPage());
		}
		if (areaParams.getRestaurantName() != null && !areaParams.getRestaurantName().isEmpty()) {
			data = searchDao.findByAreaAndNameDAO(areaParams.getArea(), areaParams.getRestaurantName(), pageable);
		} else if (areaParams.getCuisine() != null && !areaParams.getCuisine().isEmpty()) {

			data = searchDao.findByAreaAndCuisineDAO(areaParams.getArea(), 
					areaParams.getCuisine(), areaParams.getRating(), areaParams.getBudget(), pageable);
		} else {
			// fetch all restaurant based on default condition
			data = searchDao.findByAreaRatingBudgetDAO(areaParams.getArea(), 
					areaParams.getRating(), areaParams.getBudget(), pageable);
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
	public Page<RestaurantModel> getRestaurantByLocationAndFilterParam(CoOrdinateSearchParams coOrdinateParams) {
		Pageable pageable = PageRequest.of(coOrdinateParams.getPage(), pageSize);
		if (logger.isDebugEnabled()) {
			logger.debug("param data: latitude: "+coOrdinateParams.getLatitude()+" longitude: "+
					coOrdinateParams.getLongitude() + " cuisine: " + coOrdinateParams.getCuisine() + 
					" budget: " + coOrdinateParams.getBudget() + " rating: "
					+ coOrdinateParams.getRating() + " restauratnName: " + coOrdinateParams.getRestaurantName() +
					" pageNo: " + coOrdinateParams.getPage());
		}
		Page<RestaurantModel> data = null;
		if (coOrdinateParams.getRestaurantName() != null && !coOrdinateParams.getRestaurantName().isEmpty()) {
			data = searchDao.findByLonLatAndNameDAO(coOrdinateParams.getRestaurantName(), 
					coOrdinateParams.getDistance(), coOrdinateParams.getLatitude(), 
					coOrdinateParams.getLongitude(), pageable);
			 
		} else if (coOrdinateParams.getCuisine() != null && !coOrdinateParams.getCuisine().isEmpty()) {
			// pass all the parameter to the repository to fetch restaurant based on cuisine
			// and location
			data = searchDao.findByLonLatRatingBudgetDAO(coOrdinateParams.getCuisine(), 
					coOrdinateParams.getRating(), coOrdinateParams.getBudget(), 
					coOrdinateParams.getDistance(),coOrdinateParams.getLatitude(), coOrdinateParams.getLongitude(),
					pageable);
		} else {
			data = searchDao.findByLonAndLatDAO(coOrdinateParams.getRating(), coOrdinateParams.getBudget(), 
					coOrdinateParams.getDistance(),coOrdinateParams.getLatitude(), coOrdinateParams.getLongitude(), pageable);
		}
		if (data != null && logger.isDebugEnabled()) {
			logger.debug("response data: data: " + data.getContent());
		}
		return data;
	}
                    
	@Override
	public Page<RestaurantModel> getRestaurantByLocation(CoOrdinateSearchParams coOrdinateParams) {
		Pageable pageable = PageRequest.of(coOrdinateParams.getPage(), pageSize);
		float rating = 0, budget = 0;
		if (logger.isDebugEnabled()) {
			logger.debug("param data: latitude: "+coOrdinateParams.getLatitude()+" longitude: "+
					coOrdinateParams.getLongitude() + 
					" distance: "+ coOrdinateParams.getDistance() +" pageNo: " + coOrdinateParams.getPage());
		}
		Page<RestaurantModel> data = searchDao.findByLonAndLatDAO(rating, budget,coOrdinateParams.getDistance(),
				coOrdinateParams.getLatitude(), coOrdinateParams.getLongitude(), pageable);
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
		double distance=calculateDistance(lat, latitude, lon, longitude, 0.0, 0.0);
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
		if (resObj != null) {
			resObj.setRating(rating);
			resObj = searchDao.updateRestaurantDetails(resObj);
		}
		return resObj;
	}
	/**
	 * {@link:https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi}
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @returns Distance in Meters
	 */
	public static double calculateDistance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
}
