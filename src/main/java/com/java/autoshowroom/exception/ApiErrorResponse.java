package com.java.autoshowroom.exception;

public class ApiErrorResponse {

	private final int code;
	private final int status;
	private final String message;

	public ApiErrorResponse(int code, int status, String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public int getStatus() {
		return status;
	}
}
