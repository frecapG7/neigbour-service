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
@RequestMapping(value = "/neigbour/api/pois")
@Slf4j
public class PointOfInterestController {

    @Autowired
    PointOfInterestRepository pointOfInterestRepository;

    @Autowired
    DistrictRepository districtRepository;


    @GetMapping("/district/{id}")
    public List<PointOfInterest> getPoIByDistrict(@PathVariable Long id){
        Optional<District> district = districtRepository.findById(id);
        if(!district.isPresent()){
            log.error("District {} doesn't exist", id);
           return null;
        }
        return pointOfInterestRepository.findByDistrict(district.get());
    }
    @PostMapping("")
    public ResponseEntity<Object> createPointOfInterest(@RequestBody PointOfInterest pointOfInterest){
        log.debug("Creating new point of interest");
        log.debug("category : {}", pointOfInterest.getCategory());
        PointOfInterest savedPointOfInterest = pointOfInterestRepository.save(pointOfInterest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}")
                .buildAndExpand(savedPointOfInterest.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePointOfInterest(@RequestBody PointOfInterest pointOfInterest, @PathVariable Long id){
        log.debug("Updating point of interest with id {}", id);

        Optional<PointOfInterest> pointOfInterestOptionnal = pointOfInterestRepository.findById(id);
        if(!pointOfInterestOptionnal.isPresent()){
            return ResponseEntity.notFound().build();
        }
        pointOfInterest.setId(id);
        pointOfInterestRepository.save(pointOfInterest);
        return ResponseEntity.noContent().build();
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

    @DeleteMapping("/{id}")
    public void deletePointOfInterest(@PathVariable Long id){
        log.debug("Deleting poi with id : {}", id);
        pointOfInterestRepository.deleteById(id);
    }




}
