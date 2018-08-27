package com.mindtree.restaurantsearchservice.dao;

import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
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
	
	public void getRestaurantByAreaAndFilterParam () {
		restInterface.getRestaurantByAreaAndFilterParam(Matchers.anyString(), Matchers.anyString(),
				 Matchers.anyFloat(), Matchers.anyFloat(), null, Matchers.anyInt());
		
		Mockito.when(searchDao.findByAreaAndCuisineDAO(Matchers.anyString(), Matchers.anyString(),
				 Matchers.anyFloat(), Matchers.anyFloat(),new PageRequest(0, 10))).then(
						 );
	}
}
