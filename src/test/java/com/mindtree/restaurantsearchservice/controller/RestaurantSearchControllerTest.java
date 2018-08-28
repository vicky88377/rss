package com.mindtree.restaurantsearchservice.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceImpl;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceInterface;

@RunWith(SpringRunner.class)
@WebMvcTest(value=RestaurantSearchController.class,secure=false)
@AutoConfigureMockMvc
public class RestaurantSearchControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RestaurantSearchServiceInterface service;
	
	private Page<RestaurantModel> restaurants;
	private final String EXPECTED_RES_JSON="{\"status_code\":200,\"status\":\"SUCCESS\",\"data\":{\"content\":[{\"address\":\"2989/B, 12th Main Road, HAL 2nd Stage, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18407918\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18407918/menu?page=0\"}],\"restaurant_id\":\"18407918\",\"restaurant_name\":\"Bombay Brasserie\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Modern Indian\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"297, 100 Feet Road, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/56464\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/56464/menu?page=0\"}],\"restaurant_id\":\"56464\",\"restaurant_name\":\"Glen's Bakehouse\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"Bakery, Desserts, Cafe\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"28, 4th 'B' Cross, Koramangala 5th Block, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Koramangala 5th Block\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/51040\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/51040/menu?page=0\"}],\"restaurant_id\":\"51040\",\"restaurant_name\":\"Truffles\",\"start_time\":\"8:00 AM\",\"end_time\":\"9:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"American, Burger, Cafe\",\"locality_verbose\":\"Koramangala 5th Block, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"11, 80 Feet Road, Opposite Indian Oil Petrol Pump, Koramangala 6th Block, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Koramangala 6th Block\",\"rating\":1.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18305628\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18305628/menu?page=0\"}],\"restaurant_id\":\"18305628\",\"restaurant_name\":\"Eat Street\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":400.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560059\",\"display_image\":\"https://www.zomato.com/images/logo/zomato_business_new_logo.png\",\"cuisen_type\":\"North Indian, Chinese, Italian, Street Food, Desserts\",\"locality_verbose\":\"Koramangala 6th Block, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"118, Koramangala Industrial Area, Koramangala 7th Block, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Koramangala 7th Block\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18385443\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18385443/menu?page=0\"}],\"restaurant_id\":\"18385443\",\"restaurant_name\":\"Koramangala Social\",\"start_time\":\"8:00 AM\",\"end_time\":\"9:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Continental, American\",\"locality_verbose\":\"Koramangala 7th Block, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"9/1, 1st Floor, Above Surya Nissan, VRR Orchid, Doddanakkundi, Marathahalli, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Marathahalli\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18353121\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18353121/menu?page=0\"}],\"restaurant_id\":\"18353121\",\"restaurant_name\":\"Flechazo\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Asian, Mediterranean, North Indian\",\"locality_verbose\":\"Marathahalli, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"215, 216 & 220, Devasandra Village, Kasaba Hobli, New BEL Road, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"New BEL Road\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18366652\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18366652/menu?page=0\"}],\"restaurant_id\":\"18366652\",\"restaurant_name\":\"Onesta\",\"start_time\":\"8:00 AM\",\"end_time\":\"9:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"Pizza, Cafe, Italian\",\"locality_verbose\":\"New BEL Road, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"501, Binnamangala Extension, 1st stage, C.M.H Road, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/18221572\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/18221572/menu?page=0\"}],\"restaurant_id\":\"18221572\",\"restaurant_name\":\"Onesta\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"Pizza, Cafe, Italian\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"105, 1st A Cross Road, Jyothi Nivas College Road, Koramangala 5th Block, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Koramangala 5th Block\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/54162\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/54162/menu?page=0\"}],\"restaurant_id\":\"54162\",\"restaurant_name\":\"The Black Pearl\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"North Indian, European, Mediterranean\",\"locality_verbose\":\"Koramangala 5th Block, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"90/4, 3rd Floor, Outer Ring Road, Munnekollaly Village, Marathahalli, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Marathahalli\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost/restaurants/56618\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost/restaurants/56618/menu?page=0\"}],\"restaurant_id\":\"56618\",\"restaurant_name\":\"AB's - Absolute Barbecues\",\"start_time\":\"8:00 AM\",\"end_time\":\"9:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"European, Mediterranean, North Indian\",\"locality_verbose\":\"Marathahalli, Bangalore\",\"offer_info\":\"20%\"}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true},\"offset\":0,\"pageSize\":10,\"pageNumber\":0,\"unpaged\":false,\"paged\":true},\"facets\":[],\"totalPages\":5,\"totalElements\":41,\"size\":10,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true},\"numberOfElements\":10,\"first\":true,\"last\":false}}";
	
	@Before
	public void Setup() {
		//service=Mockito.mock(RestaurantSearchServiceImpl.class);
		List<RestaurantModel> list=new ArrayList<>();
		RestaurantModel r1=new RestaurantModel();
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
		r1.setRestaurantImage("https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg");
		r1.setOffer("20%");
		r1.setLatitude(0);
		r1.setLongitude(0);
		r1.setRating(3f);
		RestaurantModel r2=new RestaurantModel();
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
		Pageable pageable=PageRequest.of(0, 2);
		restaurants=new PageImpl<>(list, pageable, 4);
	}
	
	@Test
	public void TestGetRestaurantsByArea() throws Exception {
		//
		//Mockito.when(service.getRestaurantByAreaAndFilterParam(Matchers.anyString(), Matchers.anyString(), Matchers.anyFloat(), Matchers.anyFloat(), Matchers.anyString(), Matchers.anyInt()));
		
		Mockito.doReturn(restaurants).when(service).getRestaurantByAreaAndFilterParam(Matchers.anyString(), Matchers.anyString(), Matchers.anyFloat(), Matchers.anyFloat(), Matchers.anyString(), Matchers.anyInt());
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/search/bangalore")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(EXPECTED_RES_JSON));
	}
	
	@Test
	public void TestGetRestaurantsByAreafailure() throws Exception {
		//Mockito.when(service.getRestaurantByAreaAndFilterParam(Matchers.anyString(), Matchers.anyString(), Matchers.anyFloat(), Matchers.anyFloat(), Matchers.anyString(), Matchers.anyInt()));
		//RestaurantSearchServiceInterface service=Mockito.mock(RestaurantSearchServiceImpl.class);
		Mockito.doReturn(null).when(service).getRestaurantByAreaAndFilterParam(Matchers.anyString(), Matchers.anyString(), Matchers.anyFloat(), Matchers.anyFloat(), Matchers.anyString(), Matchers.anyInt());
		mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/search/bangalore")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json("{\"status_code\":401,\"status\":\"SUCCESS\",\"message\":\"No Data Found\"}"));
	}
}
