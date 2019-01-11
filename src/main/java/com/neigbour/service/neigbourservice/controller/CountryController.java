package com.neigbour.service.neigbourservice.controller;

import com.neigbour.service.neigbourservice.controller.assembler.CityResourceAssembler;
import com.neigbour.service.neigbourservice.controller.assembler.CountryResourceAssembler;
import com.neigbour.service.neigbourservice.controller.exception.CountryNotFoundException;
import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/neigbour/api/countries")
@Slf4j
public class CountryController {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    CountryResourceAssembler countryResourceAssembler;
    @Autowired
    CityResourceAssembler cityResourceAssembler;


    @GetMapping("/{id}")
    public Resource<Country> one(@PathVariable Long id){
        log.debug("Asked for country with id {}", id);

        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        return countryResourceAssembler.toResource(country);

    }

    @GetMapping("")
    public Resources<Resource<Country>> all(){
        log.debug("Asked for all countries");

        List<Resource<Country>> countries = IteratorUtils.toList(countryRepository.findAll().iterator())
                .stream()
                .map(countryResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(countries,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CountryController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}/cities")
    public Resources<Resource<City>> allCities(@PathVariable Long id){
        log.debug("Asked for all city linked to country {}", id);

        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        List<Resource<City>> cities = country.getCityList().stream()
                .map(cityResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(cities,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CountryController.class).allCities(id)).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<?> newCountry(@RequestBody Country country) throws URISyntaxException {
        log.debug("Inserting country {}", country);

        Resource<Country> countryResource = countryResourceAssembler.toResource(countryRepository.save(country));

        return ResponseEntity
                .created(new URI(countryResource.getId().expand().getHref()))
                .body(countryResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCountry(@RequestBody Country newCountry , @PathVariable Long id) throws URISyntaxException{
        log.debug("Edit country with id {}", id);
        Country updatedCountry = countryRepository.findById(id).map(country -> {
            country.setNameEn(newCountry.getNameEn());
            country.setNameFr(newCountry.getNameFr());
            country.setCityList(newCountry.getCityList());
            return countryRepository.save(country);
        }).orElseThrow(() -> new CountryNotFoundException(id));


        Resource<Country> countryResource = countryResourceAssembler.toResource(updatedCountry);
        return ResponseEntity
                .created(new URI(countryResource.getId().expand().getHref()))
                .body(countryResource);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id){
        countryRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }



}
