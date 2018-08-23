package com.mindtree.restaurantsearchservice.exceptions.handler;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class CommonExceptionHandler {
     
	
	@ExceptionHandler(value= {DataAccessException.class})
	public ResponseEntity noIdFound(DataAccessException exception){
		
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
}
