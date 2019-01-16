package com.java.autoshowroom.exception;

import org.springframework.http.HttpStatus;

public interface IApiException {

	int getID();
	HttpStatus getHttpStatus();
	String getResponseMessage();
}
