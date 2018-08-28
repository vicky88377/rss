package com.mindtree.restaurantsearchservice.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.restaurantsearchservice.dao.SearchDao;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceImpl;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceInterface;

@RunWith(SpringRunner.class)
@WebMvcTest(value=RestaurantSearchServiceImpl.class)
@AutoConfigureMockMvc
public class RestaurantSearchServiceImplTest {

	@Autowired
	private RestaurantSearchServiceInterface restInterface;
	
	@MockBean
	private SearchDao searchDao;
	 
	RestaurantModel resObj= new RestaurantModel();
	RestaurantModel resObj2= new RestaurantModel();
	List<RestaurantModel> resList= new ArrayList<>(); 
	Page< RestaurantModel> resModel=null;
	
	@Before
	public void setup() {
		resObj.setAddress("1st Floor, Trade Centre, Near Jyothi Circle, "
				+ "Bunts Hostel Road, Balmatta, Mangalore");
		resObj.setAverageDeliveryTime("1.5");
		resObj.setCity("Mangalore");
		resObj.setLocality("Balmatta");
		resObj.setLocalityVerbose("South Indian, North Indian");
		resObj.setRating(2);
		resObj.setMinimumOrder(300);
		resObj.setRestaurantName("Maharaja Restaurant");
		resObj.setOffer("10%");
		resObj.setRestaurantOpenTime("9:00 AM");
		resObj.setRestaurantCloseTime("11.00PM");
		resObj.setDeliveryPoint("560058");
		resObj.setRestaurantId("3100153");
		
		resObj2.setRestaurantId("56618");
		resObj2.setRestaurantName("AB's - Absolute Barbecues");
		resObj2.setCity("Bangalore");
		resObj2.setAddress("90/4, 3rd Floor, Outer Ring Road, Munnekollaly Village, Marathahalli, Bangalore");
		resObj2.setLocality("Marathahalli");
		resObj2.setLocalityVerbose("Marathahalli, Bangalore");
		resObj2.setRating(3);
		resObj2.setRestaurantOpenTime("8:00 AM");
		resObj2.setRestaurantCloseTime("9:00 PM");
		resObj2.setRestaurantImage("https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg");
		resObj2.setMinimumOrder(200);
		resObj2.setAverageDeliveryTime("1");
		resObj2.setDeliveryPoint("560057");
		resObj2.setOffer("20%");
		resObj2.setLatitude(77.6993861);
		resObj2.setLongitude(12.94993396);
		List<RestaurantModel> resList= new ArrayList<>(); 
		resList.add(resObj);
		resList.add(resObj2);
	    resModel= new PageImpl<>(resList);
	}
	@Test
	public void getRestaurantByAreaAndCuisine () {
		
		Mockito.when(searchDao.findByAreaAndCuisineDAO(Matchers.anyString(), Matchers.anyString(),
				 Matchers.anyFloat(), Matchers.anyFloat(),Matchers.any())).thenReturn(resModel) ;
		Page<RestaurantModel> resPageObj=restInterface.getRestaurantByAreaAndFilterParam("Banglore",
				"North",0,0,null,0);
		 String resId= resPageObj.getContent().get(0).getRestaurantId();
		 Assert.assertEquals("Success", resId, "3100153");
		
		
	}
	
	@Test
	public void getRestaurantByAreaAndName() {
		
		Mockito.when(searchDao.findByAreaAndNameDAO(Matchers.anyString(), Matchers.anyString(),Matchers.any())).
		thenReturn(resModel) ;
		Page<RestaurantModel> resPageObj=restInterface.getRestaurantByAreaAndFilterParam("Banglore",
				null,0,0,"Dilli da dhaba",0);
		 
		 Assert.assertEquals("Success",2,resPageObj.getContent().size());
		
		
	}
	
	@Test
	public void getRestaurantByRatingAndBudget() {
		Mockito.when(searchDao.findByAreaRatingBudgetDAO(Matchers.anyString(), Matchers.anyFloat(),
				Matchers.anyFloat(), Matchers.any())).thenReturn(resModel);
		Page<RestaurantModel> resPageObj= restInterface.getRestaurantByAreaAndFilterParam("Banglore", null,200,3, null, 0);
		Assert.assertEquals("Success",2,resPageObj.getContent().size());
	}
}
