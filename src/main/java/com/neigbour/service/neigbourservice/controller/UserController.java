package com.neigbour.service.neigbourservice.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neigbour.service.neigbourservice.controller.advice.UserNotFoundAdvice;
import com.neigbour.service.neigbourservice.controller.assembler.UserResourceAssembler;
import com.neigbour.service.neigbourservice.controller.exception.UserNotFoundException;
import com.neigbour.service.neigbourservice.model.entity.User;
import com.neigbour.service.neigbourservice.model.repository.UserRepository;
import com.neigbour.service.neigbourservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/neigbour/api/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserResourceAssembler userResourceAssembler;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/{id}")
	public Resource<User> one(@PathVariable Long id){
		log.debug("Asked for user with id " + id);
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return userResourceAssembler.toResource(user);
		
	}
	
	@PostMapping("{}")
	public ResponseEntity<Object> createUser(@RequestBody User user) throws URISyntaxException{
		log.debug("Adding user ");
		Resource<User> resource = userResourceAssembler.toResource(userRepository.save(user));
		return ResponseEntity.created(
				new URI(resource.getId().expand().getHref()))
				.build();
		
	}
	//First try method to post pic
	//Code from : https://stackoverflow.com/questions/50363639/how-spring-boot-jpahibernate-saves-images
	@PostMapping("/{id}/picture")
	public ResponseEntity<Object> updatePicture(@PathVariable Long id, @RequestParam("pictureFile") MultipartFile multipartFile){
		log.debug("Update profile picture of user {}" , id);
	
		userService.saveUserPicture(id, multipartFile);
		/*
		 * TODO
		return ResponseEntity
				.created(new URI(userResourceAssembler.toResource(us)))
		*/
		return null;
	
	}
	
	//Method will render picture
	@GetMapping("/{id}/picture")
	public void renderPicture(@PathVariable Long id, HttpServletResponse response) throws IOException{
		log.debug("Rendering picture of user : {}", id);
		
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		if(user.getPicture() != null){
			byte[] byteArray = new byte[user.getPicture().length];
			int i = 0;
			for(byte b : user.getPicture()){
				byteArray[i++] = b;
			}
			
			response.setContentType("image/jpeg");
			InputStream inputStream = new ByteArrayInputStream(byteArray);
			IOUtils.copy(inputStream, response.getOutputStream());
			
		}
	}
}
