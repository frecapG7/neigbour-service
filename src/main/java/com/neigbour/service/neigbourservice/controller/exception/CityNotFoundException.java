package com.neigbour.service.neigbourservice.controller.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(Long id){ super("Could not find city with id " + id); }
}
