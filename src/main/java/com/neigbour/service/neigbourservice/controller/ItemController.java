package com.neigbour.service.neigbourservice.controller;


import com.neigbour.service.neigbourservice.controller.assembler.ItemResourceAssembler;
import com.neigbour.service.neigbourservice.controller.assembler.PointOfInterestResourceAssembler;
import com.neigbour.service.neigbourservice.controller.exception.PointOfInterestNotFound;
import com.neigbour.service.neigbourservice.model.entity.Item;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/neigbour/api")
@Slf4j
public class ItemController {


    @Autowired
    PointOfInterestRepository pointOfInterestRepository;
    @Autowired
    PointOfInterestResourceAssembler pointOfInterestResourceAssembler;
    @Autowired
    ItemResourceAssembler itemResourceAssembler;



    @GetMapping("/pois/{id}/items")
    public Resources<Resource<Item>> getPoiItems(@PathVariable Long id){
        log.debug("Asking for list of items linked to poi with id {}", id);

        PointOfInterest pointOfInterest = pointOfInterestRepository.findById(id)
                .orElseThrow(() -> new PointOfInterestNotFound(id));


        List<Resource<Item>> items = pointOfInterest.getItems().stream()
                .map(itemResourceAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(items,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PointOfInterestController.class).getPointOfInterestById(id)).withRel("poi"));


    }

    @PostMapping("/pois/{id}/items")
    public ResponseEntity<Object> addItem(@PathVariable Long id, @RequestBody Item item){
        log.debug("Adding new Item to pois with id {}", id);
        PointOfInterest pointOfInterest = pointOfInterestRepository.findById(id)
                .orElseThrow(() -> new PointOfInterestNotFound(id));

        pointOfInterest.getItems().add(item);
        PointOfInterest result = pointOfInterestRepository.save(pointOfInterest);


        List<Resource<Item>> items = result.getItems().stream()
                .map(itemResourceAssembler::toResource)
                .collect(Collectors.toList());


        return ResponseEntity
                .ok()
                .body(new Resources<>(items,
                        ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PointOfInterestController.class).getPointOfInterestById(id)).withRel("poi")))
                ;
    }

    @DeleteMapping("/pois/{id}/items")
    public ResponseEntity<?> removeItem(@PathVariable Long id, @RequestBody Item item){
        log.debug("Removing Item with id {} linked to pois with id {}",item.getId(), id);
        PointOfInterest pointOfInterest = pointOfInterestRepository.findById(id)
                .orElseThrow(() -> new PointOfInterestNotFound(id));

        pointOfInterest.getItems().remove(item);

        return ResponseEntity.noContent().build();

    }


}
