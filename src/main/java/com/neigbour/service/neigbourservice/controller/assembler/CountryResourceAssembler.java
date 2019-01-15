package com.neigbour.service.neigbourservice.controller.assembler;

import com.neigbour.service.neigbourservice.controller.CityController;
import com.neigbour.service.neigbourservice.controller.CountryController;
import com.neigbour.service.neigbourservice.model.entity.Country;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;


@Component
public class CountryResourceAssembler implements ResourceAssembler<Country, Resource<Country>> {


    @Override
    public Resource<Country> toResource(Country country) {
        return new Resource<Country>(country ,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CountryController.class).one(country.getId())).withSelfRel(),
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CountryController.class).allCities(country.getId())).withRel("cities"),
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CountryController.class).all()).withRel("countries"));
    }
}
