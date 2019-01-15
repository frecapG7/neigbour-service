package com.neigbour.service.neigbourservice.controller;

import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neigbour.service.neigbourservice.controller.advice.UserNotFoundAdvice;
import com.neigbour.service.neigbourservice.controller.assembler.UserResourceAssembler;
import com.neigbour.service.neigbourservice.controller.exception.UserNotFoundException;
import com.neigbour.service.neigbourservice.model.entity.User;
import com.neigbour.service.neigbourservice.model.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/neigbour/api/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserResourceAssembler userResourceAssembler;
	
	
	@GetMapping("/{id}")
	public Resource<User> one(@PathVariable Long id){
		log.debug("Asked for user with id " + id);
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return userResourceAssembler.toResource(user);
		
	}
}
