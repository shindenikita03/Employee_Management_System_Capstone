package com.capstone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandling {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		Map<String,String> errorMap = new HashMap<>();
		
		e.getBindingResult().getAllErrors().forEach(
				error -> {
					    errorMap.put(((FieldError)error).getField(), error.getDefaultMessage());
				});
		
		return new ResponseEntity<Object>(errorMap,HttpStatus.BAD_REQUEST);
		
		
	}

 
    
    @ExceptionHandler(IdNotFound.class)
	public ResponseEntity<ErrorMessage> handleUserIdNotFoundException(IdNotFound e){
		
		return new ResponseEntity<ErrorMessage>(new ErrorMessage("ID not found",e.getMessage()),HttpStatus.NOT_FOUND);
	}
    
    @ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleGlobalException(Exception e){
		
		return new ResponseEntity<ErrorMessage>(new ErrorMessage("Internal server error...",e.getMessage()),HttpStatus.BAD_REQUEST);
	}

    // Other exception handlers can be added here
}
