package com.java.autoshowroom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = { Throwable.class })
	public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(Throwable ex){
		
		if(ex instanceof IApiException) {
			IApiException apiEx = (IApiException) ex; 
			ApiErrorResponse response = new ApiErrorResponse(apiEx.getID(), apiEx.getHttpStatus().value(), apiEx.getResponseMessage());
			return new ResponseEntity<ApiErrorResponse>(response, apiEx.getHttpStatus());
		}
		
		ApiErrorResponse response = new ApiErrorResponse(-1, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());
		return new ResponseEntity<ApiErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
