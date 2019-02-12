package com.neigbour.service.neigbourservice.controller.advice;

import com.neigbour.service.neigbourservice.controller.exception.ItemNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ItemNotFoundAdvice {


    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotfoundException.class)
    public String itemNotFoundExceptionHandler(ItemNotfoundException e){
        return e.getMessage();
    }

}
