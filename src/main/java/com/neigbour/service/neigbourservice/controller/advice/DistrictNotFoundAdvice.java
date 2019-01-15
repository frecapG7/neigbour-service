package com.neigbour.service.neigbourservice.controller.advice;

import com.neigbour.service.neigbourservice.controller.exception.DistrictNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DistrictNotFoundAdvice {


    @ResponseBody
    @ExceptionHandler(DistrictNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String districtNotFoundHandler(DistrictNotFoundException ex){ return ex.getMessage(); }
}
