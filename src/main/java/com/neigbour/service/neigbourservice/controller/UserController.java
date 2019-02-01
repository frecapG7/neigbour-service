package com.neigbour.service.neigbourservice.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.neigbour.service.neigbourservice.model.content.ImageContentStore;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	private ImageContentStore contentStore;

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

	@PostMapping("/{id}/picture")
	public ResponseEntity<Object> updatePicture(@PathVariable Long id, @RequestParam("pictureFile") MultipartFile multipartFile) throws IOException{
		log.debug("Update profile picture of user {}" , id);

		User user = userRepository.findById(id)
				.orElseThrow(()->new UserNotFoundException(id));

		user.setPicture(multipartFile.getBytes());
		userRepository.save(user);

		return null;
	
	}
	
	
	@GetMapping(value="/{id}/picture")
	public ResponseEntity<ByteArrayResource> downloadUserPicture(@PathVariable Long id){
		log.debug("Download user picture with id: {}", id );
		User user = userRepository.findById(id)
				.orElseThrow(()-> new UserNotFoundException(id));
		ByteArrayResource resource = new ByteArrayResource(user.getPicture());
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
				.contentLength(resource.contentLength())
				.body(resource);
	}
}
