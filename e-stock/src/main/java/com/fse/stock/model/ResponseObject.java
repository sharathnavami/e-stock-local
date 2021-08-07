package com.fse.stock.model;

public class ResponseObject {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseObject(String message) {
		super();
		this.message = message;
	}
	
	

}
