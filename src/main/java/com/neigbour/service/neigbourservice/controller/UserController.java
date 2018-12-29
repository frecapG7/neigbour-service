package com.neigbour.service.neigbourservice.controller;

import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.neigbour.service.neigbourservice.model.entity.User;
import com.neigbour.service.neigbourservice.model.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	public List<User> getUserList(){
		return IteratorUtils.toList(userRepository.findAll().iterator());
		
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Long id){
		return userRepository.findById(id).get();
	}
}
