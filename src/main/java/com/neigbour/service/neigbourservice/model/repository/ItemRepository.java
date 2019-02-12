package com.neigbour.service.neigbourservice.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.neigbour.service.neigbourservice.model.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{

}
