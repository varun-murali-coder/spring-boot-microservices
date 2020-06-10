package com.vcoderlearn.customerapp.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vcoderlearn.customerapp.exception.EmployeeException;
import com.vcoderlearn.customerapp.model.ApiError;

@ControllerAdvice
public class EmployeeExceptionHandler {
	
	
	@ExceptionHandler(value= {EmployeeException.class})
	public ResponseEntity<ApiError> handleEmployeeError(EmployeeException exp,Throwable e){
		ApiError apiError=new ApiError(e.getMessage(),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ApiError>(apiError,HttpStatus.BAD_REQUEST);
	}

}
