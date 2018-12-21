package com.neigbour.service.neigbourservice.model.repository;

import com.neigbour.service.neigbourservice.model.entity.PointOfInterest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointOfInterestRepository extends CrudRepository<PointOfInterest, Long> {





    public List<PointOfInterest> findByCategory(PointOfInterest.Category category);







}
