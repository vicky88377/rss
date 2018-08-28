package com.mindtree.restaurantsearchservice.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.repository.FoodDetailsSearchRepository;
import com.mindtree.restaurantsearchservice.repository.RestaurantSearchRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SearchDaoImplTests {

	@MockBean
	RestaurantSearchRepository restaurantSearchRepo;

	@MockBean
	FoodDetailsSearchRepository foodSearchRepo;
	
	RestaurantModel restaurant1 = new RestaurantModel();
	RestaurantModel restaurant2 = new RestaurantModel();
	FoodDetails foodDetails = new FoodDetails();
	
	List<RestaurantModel> restList = new ArrayList<>();
	List<FoodDetails> foodList = new ArrayList<>();

	Pageable page;

	Page<RestaurantModel> expectedResult;
	Page<FoodDetails> expectedFoodDetailsResult;

	@Before
	public void setup() {

		restaurant1.setRestaurantId("18385443");
		restaurant1.setRestaurantName("Koramangala Social");
		restaurant1.setCity("Bangalore");
		restaurant1.setAddress("118, Koramangala Industrial Area, Koramangala 7th Block, Bangalore");
		restaurant1.setLocality("Koramangala 7th Block");
		restaurant1.setLocalityVerbose("Koramangala 7th Block, Bangalore");
		restaurant1.setRating(3);
		restaurant1.setRestaurantOpenTime("8:00 AM");
		restaurant1.setRestaurantCloseTime("9:00 PM");
		restaurant1.setRestaurantImage(
				"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg");
		restaurant1.setMinimumOrder(200);
		restaurant1.setAverageDeliveryTime("1");
		restaurant1.setDeliveryPoint("560057");
		restaurant1.setOffer("20%");
		restaurant1.setLatitude(77.61413042);
		restaurant1.setLongitude(12.93566181);
		restaurant1.setCuisine("Continental, American");

		restaurant2.setRestaurantId("56618");
		restaurant2.setRestaurantName("AB's - Absolute Barbecues");
		restaurant2.setCity("Bangalore");
		restaurant2.setAddress("90/4, 3rd Floor, Outer Ring Road, Munnekollaly Village, Marathahalli, Bangalore");
		restaurant2.setLocality("Marathahalli");
		restaurant2.setLocalityVerbose("Marathahalli, Bangalore");
		restaurant2.setRating(3);
		restaurant2.setRestaurantOpenTime("8:00 AM");
		restaurant2.setRestaurantCloseTime("9:00 PM");
		restaurant2.setRestaurantImage(
				"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg");
		restaurant2.setMinimumOrder(200);
		restaurant2.setAverageDeliveryTime("1");
		restaurant2.setDeliveryPoint("560057");
		restaurant2.setOffer("20%");
		restaurant2.setLatitude(77.6993861);
		restaurant2.setLongitude(12.94993396);
		restaurant2.setCuisine("European, Mediterranean, North Indian");

		restList.add(restaurant1);
		restList.add(restaurant2);

		page = PageRequest.of(0, 10);

		expectedResult = new PageImpl<>(restList);
		
		foodDetails.setCuisineId("14");
		foodDetails.setAvailabilityStatus("1");
		foodDetails.setDescription("cooked flesh high spice");
		foodDetails.setFoodId("14");
		foodDetails.setFoodName("grill");
		foodDetails.setFoodPrice(242);
		foodDetails.setRestaurantId("6310470");
		foodList.add(foodDetails);
		page = PageRequest.of(0, 10);
		
		expectedFoodDetailsResult = new PageImpl<>(foodList);
	}

	@Test
	public void findByAreaRatingBudgetDAOSuccess() throws Exception {

		Mockito.when(restaurantSearchRepo.findByAreaRatingBudget(Matchers.anyString(), Matchers.anyFloat(),
				Matchers.anyFloat(), Matchers.anyObject())).thenReturn(expectedResult);

		assertEquals(expectedResult, restaurantSearchRepo.findByAreaRatingBudget("bangalore", 1, 100, page));
	}

	@Test
	public void findByAreaAndCuisineDAOSuccess() throws Exception {

		Mockito.when(restaurantSearchRepo.findByAreaAndCuisine(Matchers.anyString(), Matchers.anyString(),
				Matchers.anyFloat(), Matchers.anyFloat(), Matchers.anyObject())).thenReturn(expectedResult);

		assertEquals(expectedResult,
				restaurantSearchRepo.findByAreaAndCuisine("bangalore", "north indian", 1, 100, page));
	}

	@Test
	public void findByAreaAndNameDAO() throws Exception {

		Mockito.when(restaurantSearchRepo.findByAreaAndName(Matchers.anyString(), Matchers.anyString(),
				Matchers.anyObject())).thenReturn(expectedResult);

		assertEquals(expectedResult,
				restaurantSearchRepo.findByAreaAndName("bangalore", "AB's - Absolute Barbecues", page));
	}

	@Test
	public void findByIdDAO() throws Exception {

		Optional<RestaurantModel> expectedResult = Optional.of(restaurant1);

		Mockito.when(restaurantSearchRepo.findById(Matchers.anyString())).thenReturn(expectedResult);

		assertEquals(expectedResult, restaurantSearchRepo.findById("1"));
	}
	
	//Unit test cases to find restaurant details by longitude,latitude,rating, minimum order price & distance
	@Test
	public void findByLonAndLatDAOSuccess() throws Exception {
		Mockito.when(restaurantSearchRepo.findByLonAndLat(Matchers.anyFloat(), Matchers.anyFloat(), Matchers.anyFloat(),
				Matchers.anyDouble(), Matchers.anyDouble(), Matchers.anyObject())).thenReturn(expectedResult);
		assertEquals(expectedResult, restaurantSearchRepo.findByLonAndLat(3, 200, 1, 77.6993861, 12.94993396, page));
	}
	
	//Unit test cases to find restaurant details by Restaurant name,distance,longitude & latitude
	@Test
	public void findByLonLatAndNameDAOSuccess() throws Exception {
		Mockito.when(restaurantSearchRepo.findByLonLatAndName(Matchers.anyString(), Matchers.anyFloat(),
				Matchers.anyDouble(), Matchers.anyDouble(), Matchers.anyObject())).thenReturn(expectedResult);
		assertEquals(expectedResult, restaurantSearchRepo.findByLonLatAndName("AB's - Absolute Barbecues", 1,
				77.6993861, 12.94993396, page));
	}
	
	//Unit test cases to find restaurant details by cusine type, rating, minimum order price, longitude,latitude & distance
	@Test
	public void findByLonLatRatingBudgetDAOSuccess() throws Exception {
		Mockito.when(restaurantSearchRepo.findByLonLatRatingBudget(Matchers.anyString(), Matchers.anyFloat(),
				Matchers.anyFloat(), Matchers.anyFloat(), Matchers.anyDouble(), Matchers.anyDouble(),
				Matchers.anyObject())).thenReturn(expectedResult);
		assertEquals(expectedResult, restaurantSearchRepo.findByLonLatRatingBudget("North Indian", 3, 200, 1,
				77.6993861, 12.94993396, page));
	}
	
	// Unit test cases to find food details by restaurantId
	@Test
	public void getFoodDetailsByRestaurantIdDAOSuccess() throws Exception {
		Mockito.when(foodSearchRepo.getFoodDetailsByRestaurantId(Matchers.anyString(), Matchers.anyObject()))
				.thenReturn(expectedFoodDetailsResult);
		assertEquals(expectedFoodDetailsResult, foodSearchRepo.getFoodDetailsByRestaurantId("6310470", page));
	}

	// Unit test cases to find food details by restaurantId & foodId
	@Test
	public void getFoodDetailsByRestaurantIdAndFoodIdDAOSuccess() throws Exception {
		FoodDetails expectedFoodDetailsResult = foodDetails;
		Mockito.when(foodSearchRepo.getFoodDetailsByRestaurantIdAndFoodId(Matchers.anyString(), Matchers.anyString()))
				.thenReturn(foodDetails);
		assertEquals(expectedFoodDetailsResult, foodSearchRepo.getFoodDetailsByRestaurantIdAndFoodId("6310470", "14"));
	}
	
}
