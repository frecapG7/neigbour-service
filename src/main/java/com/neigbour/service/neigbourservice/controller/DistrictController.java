package com.neigbour.service.neigbourservice.controller;

import com.neigbour.service.neigbourservice.model.entity.City;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.repository.DistrictRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/neigbour/api/district")
@Slf4j
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;

    @GetMapping("/{id}")
    public District getDistrictById(@PathVariable Long id){
        log.debug("Asked for district with id {}", id);

        Optional<District> district = districtRepository.findById(id);
        if(!district.isPresent()){
            throw new NullPointerException();
        }
        return district.get();
    }

    @PostMapping("")
    public ResponseEntity<Object> createDistrict(@RequestBody District district){
        District savedDistrict = districtRepository.save(district);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedDistrict.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
