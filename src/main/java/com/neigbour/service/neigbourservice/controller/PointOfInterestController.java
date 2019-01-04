package com.neigbour.service.neigbourservice.controller;


import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.DistrictRepository;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "neigbour/api/poi")
@Slf4j
public class PointOfInterestController {

    @Autowired
    PointOfInterestRepository pointOfInterestRepository;

    @Autowired
    DistrictRepository districtRepository;


    @GetMapping("/category/{category}")
    public List<PointOfInterest> getPoIByCategory(@PathVariable String category){
        try{
            PointOfInterest.Category poiCategory = PointOfInterest.Category.fromString(category);
            return pointOfInterestRepository.findByCategory(poiCategory);
        }catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }
        return null;
    }

    @GetMapping("district/{id}")
    public List<PointOfInterest> getPoIByDistrict(@PathVariable Long id){
        try{
            Optional<District> district = districtRepository.findById(id);
            if(!district.isPresent()){
                log.error("District {} doesn't exist", id);
                throw new NullPointerException();
            }
            return pointOfInterestRepository.findByDistrict(district.get());
        }catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }
        return null;
    }
    @PostMapping("")
    public ResponseEntity<Object> createPointOfInterest(@RequestBody PointOfInterest pointOfInterest){
        log.debug("Creating new point of interest");
        PointOfInterest savedPointOfInterest = pointOfInterestRepository.save(pointOfInterest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
                .buildAndExpand(savedPointOfInterest.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public PointOfInterest getPointOfInterestById(@PathVariable Long id){
        log.debug("Fetch point of interest by id : {}", id);
        Optional<PointOfInterest> pointOfInterest = pointOfInterestRepository.findById(id);
        if(!pointOfInterest.isPresent()){
            log.error("Point of interest {} doesn't exist", id);
            return null;
        }
        return pointOfInterest.get();
    }




}
