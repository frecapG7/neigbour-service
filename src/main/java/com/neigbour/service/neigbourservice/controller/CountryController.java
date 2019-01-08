package com.neigbour.service.neigbourservice.controller;

import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/neigbour/api/country")
@Slf4j
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("")
    public List<Country> getCountryList(){
        log.debug("Asked for list of country");
        return IteratorUtils.toList(countryRepository.findAll().iterator());
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Long id){
        log.debug("Asked for country with id {}", id);

        Optional<Country> country = countryRepository.findById(id);
        if(!country.isPresent()){
            throw new NullPointerException();
        }
        return country.get();
    }

    @GetMapping("/{id}/cities")
    public List<City> getCityList(@PathVariable Long id){
        log.debug("Asked for list of cities linked to country with id {}" , id);
        Optional<Country> country = countryRepository.findById(id);
        if(!country.isPresent()){
            return null;
        }
        return country.get().getCityList();
    }

    @PostMapping("")
    public ResponseEntity<Object> createCountry(@RequestBody Country country){
        log.debug("Inserting country {}", country);
        Country savedCountry = countryRepository.save(country);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCountry.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
