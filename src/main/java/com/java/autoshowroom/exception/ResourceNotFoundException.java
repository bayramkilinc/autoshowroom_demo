package com.java.autoshowroom.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException implements IApiException{

	private static final long serialVersionUID = -4643153952232081863L;
	
	private final static int id = 1001;
	private final static HttpStatus httpStatus = HttpStatus.NOT_FOUND;

	public ResourceNotFoundException(long id, String model) {
        super(String.format("%s id'li %s bulunamadı.", id, model));
    }

	public ResourceNotFoundException(String message) {
        super(message);
    }
    
	@Override
	public int getID() {
		return id;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	@Override
	public String getResponseMessage() {
		return super.getMessage();
	}
	
}
