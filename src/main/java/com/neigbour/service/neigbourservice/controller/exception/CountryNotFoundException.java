package com.neigbour.service.neigbourservice.controller.exception;

public class CountryNotFoundException extends RuntimeException{

    public CountryNotFoundException(Long id){
        super("Could not find country with id " + id);
    }
}
