package com.neigbour.service.neigbourservice.controller.assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import com.neigbour.service.neigbourservice.controller.UserController;
import com.neigbour.service.neigbourservice.model.entity.User;

@Component
public class UserResourceAssembler implements ResourceAssembler<User, Resource<User>>{

	@Override
	public Resource<User> toResource(User entity) {
		return new Resource<User>(entity, 
				ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(UserController.class).one(entity.getId())).withSelfRel(),
				ControllerLinkBuilder.linkTo(controller));
	}
	
}
