package com.neigbour.service.neigbourservice.controller.exception;


public class DistrictNotFoundException extends RuntimeException {


    public DistrictNotFoundException(Long id){super("Cound not find district with id " + id);}
}
