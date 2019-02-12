package com.neigbour.service.neigbourservice.controller.advice;

import com.neigbour.service.neigbourservice.controller.exception.PointOfInterestNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PointOfInterestNotFoundAdvice {


    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PointOfInterestNotFound.class)
    public String  pointOfInterestNotFoundExceptionHandler(PointOfInterestNotFound e){
        return e.getMessage();
    }
}
