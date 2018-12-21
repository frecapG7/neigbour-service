package com.neigbour.service.neigbourservice.controller;


import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "neigbour/api/poi")
@Slf4j
public class PointOfInterestController {



    @Autowired
    PointOfInterestRepository pointOfInterestRepository;

    @GetMapping("/poi/category/{category}")
    public List<PointOfInterest> getPoIByCategory(@PathVariable String categoryString){
        try{
            PointOfInterest.Category category = PointOfInterest.Category.fromString(categoryString);
            return pointOfInterestRepository.findByCategory(category);
        }catch (IllegalArgumentException e){
            log.error(e.getMessage());
        }
        return null;

    }


}
