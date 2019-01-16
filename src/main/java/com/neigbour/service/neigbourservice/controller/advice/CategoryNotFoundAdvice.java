package com.neigbour.service.neigbourservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neigbour.service.neigbourservice.controller.exception.CategoryNotFoundException;

@ControllerAdvice
public class CategoryNotFoundAdvice {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CategoryNotFoundException.class)
	public String categoryNotFoundExceptionHandler(CategoryNotFoundException ex){
		return ex.getMessage();
	}
	
}
