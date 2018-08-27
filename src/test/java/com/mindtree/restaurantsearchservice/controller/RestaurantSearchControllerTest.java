package com.mindtree.restaurantsearchservice.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mindtree.restaurantsearchservice.model.RestaurantModel;
import com.mindtree.restaurantsearchservice.service.RestaurantSearchServiceInterface;


@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantSearchControllerTest {
		
		@Autowired
		private MockMvc mockMvc;
		@MockBean
		private RestaurantSearchServiceInterface service;
		private static final String EXPECTED_RESTAURANTS="{\"status_code\": 200,\"status\": \"SUCCESS\",\"data\": {\"content\": [{\"address\":\"2989/B, 12th Main Road, HAL 2nd Stage, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":3.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:9001/restaurants/18407918\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost:9001/restaurants/18407918/menu?page=0\"}],\"restaurant_id\":\"18407918\",\"restaurant_name\":\"Bombay Brasserie\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":200.0,\"average_delivery_time\":\"1\",\"delivery_point\":\"560057\",\"display_image\":\"https://www.golfreizen.nu/wp-content/gallery/spanje-costa-calida-mar-menor-golf-resort/InterContinental-Mar-Menor-Golf-Resort-Spa-00.jpg\",\"cuisen_type\":\"Modern Indian\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"},{\"address\":\"297, 100 Feet Road, Indiranagar, Bangalore\",\"city\":\"Bangalore\",\"locality\":\"Indiranagar\",\"rating\":2.0,\"latitude\":0.0,\"longitude\":0.0,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:9001/restaurants/56464\"},{\"rel\":\"FoodMenu\",\"href\":\"http://localhost:9001/restaurants/56464/menu?page=0\"}],\"restaurant_id\":\"56464\",\"restaurant_name\":\"Glen's Bakehouse\",\"start_time\":\"10:00 AM\",\"end_time\":\"11:00 PM\",\"minimum_order_price\":300.0,\"average_delivery_time\":\"1.5\",\"delivery_point\":\"560058\",\"display_image\":\"http://nrai.org/site/wp-content/uploads/2017/03/Zomato-Logo-640x300.jpg\",\"cuisen_type\":\"Bakery, Desserts, Cafe\",\"locality_verbose\":\"Indiranagar, Bangalore\",\"offer_info\":\"20%\"}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true},\"offset\":0,\"pageSize\":2,\"pageNumber\":0,\"paged\":true,\"unpaged\":false},\"facets\":[],\"totalElements\":4,\"totalPages\":2,\"size\":2,\"number\":0,\"sort\":{\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements\":2,\"last\":false}}";
		
		/*@BeforeClass
		public static void setup() {
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
		}*/
		
		
		@Test
		public void testGetRestaurantWithAreaNameSuccessScenario() throws Exception {
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
			Page<RestaurantModel> restaurants=new PageImpl<>(list, pageable, 4);
			Mockito.when(service.getRestaurantByAreaAndFilterParam(Matchers.anyString(), Matchers.anyString(), Matchers.anyFloat(), Matchers.anyFloat(), Matchers.anyString(), Matchers.anyInt())).thenReturn(restaurants);
			mockMvc.perform(MockMvcRequestBuilders.get("/restaurants/search/Bangalore")).andExpect(status().isOk()).andExpect(content().json(EXPECTED_RESTAURANTS));
		}
}	
