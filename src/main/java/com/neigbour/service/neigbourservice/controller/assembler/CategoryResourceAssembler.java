package com.neigbour.service.neigbourservice.controller.assembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.neigbour.service.neigbourservice.model.entity.Category;

@Component
public class CategoryResourceAssembler implements ResourceAssembler<Category, Resource<Category>>{
	
	@Override
	public Resource<Category> toResource(Category entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
