package com.neigbour.service.neigbourservice.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neigbour.service.neigbourservice.model.entity.Shop;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long>{

}
