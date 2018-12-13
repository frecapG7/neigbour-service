package com.neigbour.service.neigbourservice.controller;

import java.util.List;
import java.util.function.Supplier;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.neigbour.service.neigbourservice.model.entity.Restaurant;
import com.neigbour.service.neigbourservice.model.repository.RestaurantRepository;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@GetMapping("/restaurant")
	public List<Restaurant> getRestaurants() {

		return IteratorUtils.toList(restaurantRepository.findAll().iterator());
	}

	@GetMapping("/restaurant/{id}")
	public Restaurant getRestaurantById(@PathVariable Long id) {
		return restaurantRepository.findById(id)
				.orElse(Restaurant.builder().name("todo").description("todo").address("todo").build());
	}
}
