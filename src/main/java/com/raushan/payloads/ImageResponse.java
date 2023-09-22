package com.raushan.payloads;

public class ImageResponse {
	
	public String fileName;
	public String message;
	
	
	public ImageResponse() {
		super();
	}
	
	


	public ImageResponse(String fileName, String message) {
		super();
		this.fileName = fileName;
		this.message = message;
	}




	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
