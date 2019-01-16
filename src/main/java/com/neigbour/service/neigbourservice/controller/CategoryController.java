package com.neigbour.service.neigbourservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.neigbour.service.neigbourservice.controller.assembler.CategoryResourceAssembler;
import com.neigbour.service.neigbourservice.controller.exception.CategoryNotFoundException;
import com.neigbour.service.neigbourservice.model.entity.SubCategory;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.neigbour.service.neigbourservice.model.entity.Category;
import com.neigbour.service.neigbourservice.model.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/neigbour/api/categories")
public class CategoryController {

	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryResourceAssembler categoryResourceAssembler;
	
	@GetMapping("/")
	public Resources<Resource<Category>> allCategories(){

        log.debug("Asked for list of categories");

        List<Resource<Category>> categories = IteratorUtils.toList(categoryRepository.findAll().iterator())
				.stream()
				.map(categoryResourceAssembler::toResource)
				.collect(Collectors.toList());
        return new Resources<>(categories,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CategoryController.class).allCategories()).withSelfRel());
	}

	@GetMapping("/{id}")
	public List<SubCategory> allSubCategories(@PathVariable Long id){
		log.debug("Asked for all subcategories of category with id : {}", id);

		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(id));

		return category.getSubCategories();
	}
}
