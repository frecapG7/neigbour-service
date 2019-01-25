package com.neigbour.service.neigbourservice.controller.assembler;

import com.neigbour.service.neigbourservice.controller.CategoryController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import com.neigbour.service.neigbourservice.model.entity.Category;

@Component
public class CategoryResourceAssembler implements ResourceAssembler<Category, Resource<Category>>{
	
	@Override
	public Resource<Category> toResource(Category category) {
		return new Resource<>(category,
				ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CategoryController.class).allCategories()).withSelfRel());
	}

	
}
