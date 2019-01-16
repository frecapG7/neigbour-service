package com.neigbour.service.neigbourservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neigbour.service.neigbourservice.c
import com.neigbour.service.neigbourservice.controller.exception.CityNotFoundException;
import com.neigbour.service.neigbourservice.model.entity.Category;
import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.District;
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

        Category category = categoryRepository.findAll().iterator()
        List<Resource<District>> districts = city.getDistrictList().stream()
                .map(districtResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(districts,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CityController.class).allDistricts(id)).withSelfRel());
    
	}
}
