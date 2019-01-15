package com.neigbour.service.neigbourservice.controller.assembler;

import com.neigbour.service.neigbourservice.controller.CityController;
import com.neigbour.service.neigbourservice.model.entity.City;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CityResourceAssembler implements ResourceAssembler<City, Resource<City>> {

    @Override
    public Resource<City> toResource(City city) {
        return new Resource<City>(city,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CityController.class).one(city.getId())).withSelfRel(),
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CityController.class).allDistricts(city.getId())).withRel("districts")
                );
    }
}
