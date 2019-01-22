package com.neigbour.service.neigbourservice.controller.exception;

public class PointOfInterestNotFound extends RuntimeException {

    public PointOfInterestNotFound(Long id){
        super("Could not find point of interest with id: " + id);
    }
}
