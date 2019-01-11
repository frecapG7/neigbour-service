package com.neigbour.service.neigbourservice.controller.advice;


import com.neigbour.service.neigbourservice.controller.exception.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CityNotFoundAdvice {


    @ResponseBody
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String cityNotFoundHandler(CityNotFoundException ex){ return ex.getMessage();}
}
