package com.yash.demo.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling implements ErrorController {

	public ExceptionHandling() {
		// TODO Auto-generated constructor stub
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> studentNoFoundException(StudentNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CourseNotFoundException.class) 
	public ResponseEntity<String> courseNoFoundException(CourseNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	

}
