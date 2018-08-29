package com.mindtree.restaurantsearchservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mindtree.restaurantsearchservice.model.FoodDetails;
import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceImpl;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RestaurantSearchControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	@Autowired
	private RestaurantSearchController controller;
	@MockBean
	private RestaurantSearchServiceImpl service;

	private Page<RestaurantModel> restaurants;
	private final String EXPECTED_RES_JSON = "{\"status_code\":200,\"status\":\"SUCCESS\",\"data\":{\"content\":[{\"address\":\"2989/B, 12th Main Road, HAL 2nd Stage, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18407918\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18407918/menu?page=0\"}],\"restaurant_id\":\"18407918\",\"restaurant_name\":\"Bombay Brasserie\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Modern Indian\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"297, 100 Feet Road, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/56464\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/56464/menu?page=0\"}],\"restaurant_id\":\"56464\",\"restaurant_name\":\"Glen's Bakehouse\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"Bakery, Desserts, Cafe\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"28, 4th 'B' Cross, Koramangala 5th Block, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Koramangala 5th Block\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/51040\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/51040/menu?page=0\"}],\"restaurant_id\":\"51040\",\"restaurant_name\":\"Truffles\",\"start_time\":\"8:00 AM\",\"end_time\":\"9:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"American, Burger, Cafe\",\"locality_verbose\":\"Koramangala 5th Block, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"11, 80 Feet Road, Opposite Indian Oil Petrol Pump, Koramangala 6th Block, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Koramangala 6th Block\",\"rating\":1.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18305628\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18305628/menu?page=0\"}],\"restaurant_id\":\"18305628\",\"restaurant_name\":\"Eat Street\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":400.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560059\",\"display_image\":\"https://www.zomato.com/images/logo/zomato_business_new_logo.png\",\"cuisen_type\":\"North Indian, Chinese, Italian, Street Food, Desserts\",\"locality_verbose\":\"Koramangala 6th Block, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"118, Koramangala Industrial Area, Koramangala 7th Block, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Koramangala 7th Block\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18385443\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18385443/menu?page=0\"}],\"restaurant_id\":\"18385443\",\"restaurant_name\":\"Koramangala Social\",\"start_time\":\"8:00 AM\",\"end_time\":\"9:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Continental, American\",\"locality_verbose\":\"Koramangala 7th Block, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"9/1, 1st Floor, Above Surya Nissan, VRR Orchid, Doddanakkundi, Marathahalli, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Marathahalli\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18353121\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18353121/menu?page=0\"}],\"restaurant_id\":\"18353121\",\"restaurant_name\":\"Flechazo\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Asian, Mediterranean, North Indian\",\"locality_verbose\":\"Marathahalli, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"215, 216 & 220, Devasandra Village, Kasaba Hobli, New BEL Road, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"New BEL Road\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18366652\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18366652/menu?page=0\"}],\"restaurant_id\":\"18366652\",\"restaurant_name\":\"Onesta\",\"start_time\":\"8:00 AM\",\"end_time\":\"9:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"Pizza, Cafe, Italian\",\"locality_verbose\":\"New BEL Road, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"501, Binnamangala Extension, 1st stage, C.M.H Road, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18221572\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18221572/menu?page=0\"}],\"restaurant_id\":\"18221572\",\"restaurant_name\":\"Onesta\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"Pizza, Cafe, Italian\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"105, 1st A Cross Road, Jyothi Nivas College Road, Koramangala 5th Block, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Koramangala 5th Block\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/54162\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/54162/menu?page=0\"}],\"restaurant_id\":\"54162\",\"restaurant_name\":\"The Black Pearl\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"North Indian, European, Mediterranean\",\"locality_verbose\":\"Koramangala 5th Block, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"90/4, 3rd Floor, Outer Ring Road, Munnekollaly Village, Marathahalli, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Marathahalli\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/56618\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/56618/menu?page=0\"}],\"restaurant_id\":\"56618\",\"restaurant_name\":\"AB's - Absolute Barbecues\",\"start_time\":\"8:00 AM\",\"end_time\":\"9:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"European, Mediterranean, North Indian\",\"locality_verbose\":\"Marathahalli, Bangalore\",\"offer_info\":\"20%\"}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true},\"offset\":0,\"pageSize\":10,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"facets\":[],\"totalPages\":5,\"totalElements\":41,\"size\":10,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true},\"numberOfElements\":10,\"first\":true,\"last\":false}}";
	RestaurantModel r1;

	@Before
	public void Setup() {
		// service=Mockito.mock(RestaurantSearchServiceImpl.class);
		List<RestaurantModel> list = new ArrayList<>();
		r1 = new RestaurantModel();
		r1.setRestaurantId("18407918");
		r1.setRestaurantName("Bombay Brasserie");
		r1.setRestaurantOpenTime("11:00 AM");
		r1.setRestaurantCloseTime("11:00 PM");
		r1.setMinimumOrder(200f);
		r1.setAverageDeliveryTime("1");
		r1.setAddress("2989/B, 12th Main Road, HAL 2nd Stage, Indiranagar, Bangalore");
		r1.setLocality("Indiranagar");
		r1.setDeliveryPoint("560057");
		r1.setCity("Bangalore");
		r1.setLocalityVerbose("Indiranagar, Bangalore");
		r1.setCuisine("Modern Indian");
		r1.setRestaurantImage(
				"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg");
		r1.setOffer("20%");
		r1.setLatitude(0);
		r1.setLongitude(0);
		r1.setRating(3f);
		RestaurantModel r2 = new RestaurantModel();
		r2.setRestaurantId("56464");
		r2.setRestaurantName("Glen's Bakehouse");
		r2.setRestaurantOpenTime("10:00 AM");
		r2.setRestaurantCloseTime("11:00 PM");
		r2.setMinimumOrder(300f);
		r2.setAverageDeliveryTime("1.5");
		r2.setAddress("297, 100 Feet Road, Indiranagar, Bangalore");
		r2.setLocality("Indiranagar");
		r2.setDeliveryPoint("560058");
		r2.setCity("Bangalore");
		r2.setLocalityVerbose("Indiranagar, Bangalore");
		r2.setCuisine("Bakery, Desserts, Cafe");
		r2.setRestaurantImage("http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg");
		r2.setOffer("20%");
		r2.setLatitude(0);
		r2.setLongitude(0);
		r2.setRating(2f);

		list.add(r1);
		list.add(r2);
		restaurants = new PageImpl<>(list);
	}

	@Test
	@Ignore
	public void testGetRestaurantsByArea() throws Exception {
		//Mockito.when(service.getRestaurantByAreaAndFilterParam(Mockito.anyString(), Mockito.anyString(),Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyString(), Mockito.anyInt())).thenReturn(restaurants);

		 Mockito.doReturn(restaurants).when(service).getRestaurantByAreaAndFilterParam(Mockito.anyString(),
				 Mockito.anyString(), Mockito.anyFloat(), Mockito.anyFloat(),
				 Mockito.anyString(), Mockito.anyInt());
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/search/bangalore").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(EXPECTED_RES_JSON));
	}

	@Test
	public void testGetRestaurantsByAreafailure() throws Exception {
		Mockito.when(service.getRestaurantByAreaAndFilterParam(Mockito.anyString(), Mockito.anyString(),
				Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyString(), Mockito.anyInt())).thenReturn(null);
		// RestaurantSearchServiceInterface
		// service=Mockito.mock(RestaurantSearchServiceImpl.class);
		// Mockito.doReturn(null).when(service).getRestaurantByAreaAndFilterParam(Matchers.anyString(),
		// Matchers.anyString(), Matchers.anyFloat(), Matchers.anyFloat(),
		// Matchers.anyString(), Matchers.anyInt());
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/search/bangalore"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.json("{\"status_code\":401,\"status\":\"SUCCESS\",\"message\":\"No Data Found\"}"));
	}

	@Test
	@Ignore
	public void testGetRestaurantsByCoordinates() throws Exception {
		Mockito.when(service.getRestaurantByLocationAndFilterParam(Mockito.anyDouble(), Mockito.anyDouble(),
				Mockito.anyFloat(), Mockito.anyString(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyString(),
				Mockito.anyInt())).thenReturn(restaurants);

		// Mockito.doReturn(restaurants).when(service).getRestaurantByAreaAndFilterParam(Matchers.anyString(),
		// Matchers.anyString(), Matchers.anyFloat(), Matchers.anyFloat(),
		// Matchers.anyString(), Matchers.anyInt());
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/search/0/0"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(EXPECTED_RES_JSON));
	}

	@Test
	public void testGetRestaurantsByCoordinatesFailure() throws Exception {
		Mockito.when(service.getRestaurantByLocationAndFilterParam(Mockito.anyDouble(), Mockito.anyDouble(),
				Mockito.anyFloat(), Mockito.anyString(), Mockito.anyFloat(), Mockito.anyFloat(), Mockito.anyString(),
				Mockito.anyInt())).thenReturn(null);

		// Mockito.doReturn(restaurants).when(service).getRestaurantByAreaAndFilterParam(Matchers.anyString(),
		// Matchers.anyString(), Matchers.anyFloat(), Matchers.anyFloat(),
		// Matchers.anyString(), Matchers.anyInt());
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/search/0/0"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.json("{\"status_code\":401,\"status\":\"SUCCESS\",\"message\":\"No Data Found\"}"));
	}

	@Test
	public void testGetRestaurantDetailsById() throws Exception {
		Mockito.when(service.getResaurantById(Mockito.anyString())).thenReturn(r1);
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/18407918"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(
						"{\"status_code\":200,\"status\":\"SUCCESS\",\"data\":{\"address\":\"2989/B, 12th Main Road, HAL 2nd Stage, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18407918/menu\"}],\"restaurant_id\":\"18407918\",\"restaurant_name\":\"Bombay Brasserie\",\"start_time\":\"11:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Modern Indian\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"}}"));
	}

	@Test
	public void testGetRestaurantDetailsByIdFailure() throws Exception {
		Mockito.when(service.getResaurantById(Mockito.anyString())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/18407918"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
						.json("{\"status_code\":401,\"status\":\"SUCCESS\",\"message\":\"No Data Found\"}"));
	}
	
	@Test
	public void testValidateAddress() throws Exception {
		String expected="{\"status_code\":200,\"status\":\"SUCCESS\",\"message\":\"Delivery is available for your area\",\"data\":true}";
		Mockito.when(service.validateDeliveryAddress(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/625521/validate/0/0")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(expected));
	}
	@Test
	public void testValidateAddressFailure() throws Exception {
		String expected="{\"status_code\":401,\"status\":\"SUCCESS\",\"message\":\"Delivery is not available for your area\"}";
		Mockito.when(service.validateDeliveryAddress(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/625521/validate/0/0")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(expected));
	}
	
	@Test
	public void testGetFoodMenu() throws Exception {
		List<FoodDetails> list=new ArrayList<>();
		FoodDetails foodDetails=new FoodDetails();
		foodDetails.setCuisineId("14");
		foodDetails.setAvailabilityStatus("1");
		foodDetails.setDescription("cooked flesh high spice");
		foodDetails.setFoodId("14");
		foodDetails.setFoodName("grill");
		foodDetails.setFoodPrice(242);
		foodDetails.setRestaurantId("6310470");
		list.add(foodDetails);
		FoodDetails foodDetails1=new FoodDetails();
		foodDetails1.setCuisineId("12");
		foodDetails1.setAvailabilityStatus("1");
		foodDetails1.setDescription("cooked flesh high spice");
		foodDetails1.setFoodId("15");
		foodDetails1.setFoodName("grill");
		foodDetails1.setFoodPrice(242);
		foodDetails1.setRestaurantId("6310470");
		list.add(foodDetails1);

		String expected="{\"status_code\":200,\"status\":\"SUCCESS\",\"data\":[{\"description\":\"cooked flesh high spice\",\"foodId\":\"14\",\"food_name\":\"grill\",\"food_price\":242.0,\"availability_status\":\"1\",\"restaurant_id\":\"6310470\",\"cuisines_id\":\"14\"},{\"description\":\"cooked flesh high spice\",\"foodId\":\"15\",\"food_name\":\"grill\",\"food_price\":242.0,\"availability_status\":\"1\",\"restaurant_id\":\"6310470\",\"cuisines_id\":\"12\"}]}";
		Mockito.when(service.getAllFoodDetailsByRestaurantId(Mockito.anyString())).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/6310470/menu")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(expected));
	}
	
	@Test
	public void testGetFoodMenuInvalidRestId() throws Exception {
		String expected="{\"status\":\"SUCCESS\",\"message\":\"No Data Found\",\"status_code\":401}";
		Mockito.when(service.getAllFoodDetailsByRestaurantId(Mockito.anyString())).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/12345/menu")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(expected));
	}
	
	@Test
	public void testUpdateReviewRating() throws Exception {
		r1.setRating(4);
		String expected="{\"status_code\":200,\"status\":\"SUCCESS\",\"data\":{\"address\":\"2989/B, 12th Main Road, HAL 2nd Stage, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":4.0,\"latitude\":0.0,\"longitude\":0.0,\"restaurant_id\":\"18407918\",\"restaurant_name\":\"Bombay Brasserie\",\"start_time\":\"11:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Modern Indian\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"}}";
		Mockito.when(service.updateRatingBasedOnRestaurantId(Mockito.anyString(), Mockito.anyFloat())).thenReturn(r1);
		mockMvc.perform(MockMvcRequestBuilders.put("/restaurants/18407918/reviews/4")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(expected));
	}
	
	@Test
	public void testUpdateReviewRatingInvalidId() throws Exception {
		String expected="{\"status_code\":401,\"status\":\"SUCCESS\",\"message\":\"No Data Found\"}";
		Mockito.when(service.updateRatingBasedOnRestaurantId(Mockito.anyString(), Mockito.anyFloat())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.put("/restaurants/18407918/reviews/4")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(expected));
	}
}
