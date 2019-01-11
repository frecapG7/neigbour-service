package com.neigbour.service.neigbourservice.controller.assembler;

import com.neigbour.service.neigbourservice.controller.DistrictController;
import com.neigbour.service.neigbourservice.model.entity.District;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class DistrictResourceAssembler implements ResourceAssembler<District, Resource<District>> {


    @Override
    public Resource<District> toResource(District district) {
        return new Resource<District>(district,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DistrictController.class).one(district.getId())).withSelfRel(),
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DistrictController.class).allPointOfInterest(district.getId())).withRel("pois"));
    }
}
