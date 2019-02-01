package com.neigbour.service.neigbourservice.controller;


import com.neigbour.service.neigbourservice.controller.assembler.PointOfInterestResourceAssembler;
import com.neigbour.service.neigbourservice.controller.exception.PointOfInterestNotFound;
import com.neigbour.service.neigbourservice.model.entity.District;
import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import com.neigbour.service.neigbourservice.model.repository.DistrictRepository;
import com.neigbour.service.neigbourservice.model.repository.PointOfInterestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/neigbour/api/pois")
@Slf4j
public class PointOfInterestController {

    @Autowired
    PointOfInterestRepository pointOfInterestRepository;

    @Autowired
    PointOfInterestResourceAssembler pointOfInterestResourceAssembler;


    @PostMapping("")
    public ResponseEntity<Object> createPointOfInterest(@RequestBody PointOfInterest pointOfInterest) throws URISyntaxException {
        log.debug("Creating new point of interest");
        Resource<PointOfInterest> result = pointOfInterestResourceAssembler.toResource(pointOfInterestRepository.save(pointOfInterest));

        return ResponseEntity
                .created(new URI(result.getId().expand().getHref()))
                .body(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePointOfInterest(@RequestBody PointOfInterest pointOfInterest, @PathVariable Long id) throws URISyntaxException{
        log.debug("Updating point of interest with id {}", id);

        PointOfInterest udpdatedPointOfInterest = pointOfInterestRepository.findById(id)
                .map(poi -> {
                    poi.setName(pointOfInterest.getName());
                    poi.setAddress(pointOfInterest.getAddress());
                    poi.setPhoneNumber(pointOfInterest.getPhoneNumber());
                    poi.setUri(pointOfInterest.getUri());
                    poi.setDistrict(pointOfInterest.getDistrict());
                    poi.setCategory(pointOfInterest.getCategory());
                    return pointOfInterestRepository.save(poi);
                })
                .orElseThrow(() -> new PointOfInterestNotFound(id));

        Resource<PointOfInterest> result = pointOfInterestResourceAssembler.toResource(udpdatedPointOfInterest);

        return ResponseEntity
                .created(new URI(result.getId().expand().getHref()))
                .body(result);
    }

    @GetMapping("/{id}")
    public Resource<PointOfInterest> getPointOfInterestById(@PathVariable Long id){
        log.debug("Fetch point of interest by id : {}", id);

        PointOfInterest pointOfInterest = pointOfInterestRepository.findById(id).orElseThrow(() -> new PointOfInterestNotFound(id));

        return pointOfInterestResourceAssembler.toResource(pointOfInterest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePointOfInterest(@PathVariable Long id){
        log.debug("Deleting poi with id : {}", id);
        pointOfInterestRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }




}
