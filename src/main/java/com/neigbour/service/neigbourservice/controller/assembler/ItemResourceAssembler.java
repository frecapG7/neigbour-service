package com.neigbour.service.neigbourservice.controller.assembler;


import com.neigbour.service.neigbourservice.controller.ItemController;
import com.neigbour.service.neigbourservice.controller.PointOfInterestController;
import com.neigbour.service.neigbourservice.model.entity.Item;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemResourceAssembler implements ResourceAssembler<Item, Resource<Item>> {


    @Override
    public Resource<Item> toResource(Item item) {
        return new Resource<>(item,
        		ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ItemController.class).getItemById(item.getId())).withSelfRel(),
        		ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PointOfInterestController.class).getPointOfInterestById(item.getPointOfInterest().getId())).withRel("poi"));
    }
}
