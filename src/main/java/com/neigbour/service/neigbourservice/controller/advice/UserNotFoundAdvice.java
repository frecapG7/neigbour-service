package com.neigbour.service.neigbourservice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neigbour.service.neigbourservice.controller.exception.UserNotFoundException;

@ControllerAdvice
public class UserNotFoundAdvice {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundHandler(UserNotFoundException ex){
		return ex.getMessage();
	}
}
