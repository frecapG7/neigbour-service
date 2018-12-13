package com.neigbour.service.neigbourservice.model.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.neigbour.service.neigbourservice.model.entity.Restaurant;
import com.neigbour.service.neigbourservice.util.TestConstants;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RestaurantRepositoryTest {

	@Autowired
	TestEntityManager testEntityManager;

	@Autowired
	RestaurantRepository restaurantRepository;
	
	

	@Test
	public void should_find_two_restaurant() {
		// Create mock data for insert
		Restaurant restaurant1 = Restaurant.builder().name("restaurant1").description("restaurant 1").address("test")
				.build();
		Restaurant restaurant2 = Restaurant.builder().name("restaurant2").description("restaurant 2").address("test")
				.build();

		testEntityManager.persist(restaurant1);
		testEntityManager.persist(restaurant2);

		Assert.assertEquals(2, restaurantRepository.count());
	}
	
	@Test
	public void should_save_a_restaurant(){
		Restaurant restaurant = restaurantRepository.save(
				Restaurant
				.builder()
				.name(TestConstants.restaurantNameTest)
				.description(TestConstants.restaurantDescriptionTest)
				.address(TestConstants.restaurantAddressTest)
				.build()
				);
		Assert.assertEquals(TestConstants.restaurantNameTest, restaurant.getName());
		Assert.assertEquals(TestConstants.restaurantDescriptionTest, restaurant.getDescription());
		Assert.assertEquals(TestConstants.restaurantAddressTest, restaurant.getAddress());
		
	}
}
