package com.neigbour.service.neigbourservice.controller;

import com.neigbour.service.neigbourservice.controller.assembler.CityResourceAssembler;
import com.neigbour.service.neigbourservice.controller.assembler.DistrictResourceAssembler;
import com.neigbour.service.neigbourservice.controller.exception.CityNotFoundException;
import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.Country;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/neigbour/api/cities")
@Slf4j
public class CityController {


    @Autowired
    CityRepository cityRepository;

    @Autowired
    CityResourceAssembler cityResourceAssembler;
    @Autowired
    DistrictResourceAssembler districtResourceAssembler;

    @GetMapping("/{id}")
    public Resource<City> one(@PathVariable Long id){
        log.debug("Asked for country with id {}", id);

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));


        return cityResourceAssembler.toResource(city);
    }



    @GetMapping("/{id}/districts")
    public Resources<Resource<District>> allDistricts(@PathVariable Long id){
        log.debug("Asked for list of district linked to city with id : {}", id);

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        List<Resource<District>> districts = city.getDistrictList().stream()
                .map(districtResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(districts,
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CityController.class).allDistricts(id)).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<?> createCity(@RequestBody City city) throws URISyntaxException {
        Resource<City> cityResource = cityResourceAssembler.toResource(cityRepository.save(city));

        return ResponseEntity
                .created(new URI(cityResource.getId().expand().getHref()))
                .body(cityResource);
    }


}
