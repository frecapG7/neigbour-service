package com.neigbour.service.neigbourservice.controller;

import com.neigbour.service.neigbourservice.controller.assembler.DistrictResourceAssembler;
import com.neigbour.service.neigbourservice.controller.assembler.PointOfInterestResourceAssembler;
import com.neigbour.service.neigbourservice.controller.exception.DistrictNotFoundException;
import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.DistrictRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/neigbour/api/districts")
@Slf4j
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictResourceAssembler districtResourceAssembler;
    @Autowired
    private PointOfInterestResourceAssembler pointOfInterestResourceAssembler;


    @GetMapping("/{id}")
    public Resource<District> one(@PathVariable Long id){
        log.debug("Asked for district with id {}", id);

        District district = districtRepository.findById(id)
                .orElseThrow(() -> new DistrictNotFoundException(id));
        return districtResourceAssembler.toResource(district);

    }

    @GetMapping("/{id}/pois")
    public Resources<Resource<PointOfInterest>> allPointOfInterest(@PathVariable Long id){
        log.debug("Asked for all poi linked to district with id {}", id);

        District district = districtRepository.findById(id)
                .orElseThrow(() -> new DistrictNotFoundException(id));

        List<Resource<PointOfInterest>> pointOfInterests = district.getPointOfInterestList().stream()
                .map(pointOfInterestResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(pointOfInterests,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(DistrictController.class).allPointOfInterest(id)).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<Object> createDistrict(@RequestBody District district){
        District savedDistrict = districtRepository.save(district);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDistrict.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
