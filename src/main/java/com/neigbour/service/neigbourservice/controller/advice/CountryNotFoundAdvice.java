package com.neigbour.service.neigbourservice.controller.advice;

import com.neigbour.service.neigbourservice.controller.exception.CountryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CountryNotFoundAdvice {


    @ResponseBody
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String countryNotFoundHandler(CountryNotFoundException ex){
        return ex.getMessage();
    }
}
