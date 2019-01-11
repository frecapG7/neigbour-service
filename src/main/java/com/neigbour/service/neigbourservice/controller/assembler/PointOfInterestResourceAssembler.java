package com.neigbour.service.neigbourservice.controller.assembler;

import com.neigbour.service.neigbourservice.controller.PointOfInterestController;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PointOfInterestResourceAssembler implements ResourceAssembler<PointOfInterest, Resource<PointOfInterest>> {


    @Override
    public Resource<PointOfInterest> toResource(PointOfInterest pointOfInterest) {
        return new Resource<PointOfInterest>(pointOfInterest,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PointOfInterestController.class).getPointOfInterestById(pointOfInterest.getId())).withSelfRel());
    }
}
