package com.neigbour.service.neigbourservice.controller;

import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/neigbour/api/city")
@Slf4j
public class CityController {


    @Autowired
    CityRepository cityRepository;


    @GetMapping("/{id}")
    public City getCityById(@PathVariable Long id){
        log.debug("Asked for country with id {}", id);

        Optional<City> city = cityRepository.findById(id);
        if(!city.isPresent()){
            throw new NullPointerException();
        }
        return city.get();
    }

    @PostMapping("")
    public ResponseEntity<Object> createCity(@RequestBody City city){
        City savedCity = cityRepository.save(city);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCity.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
