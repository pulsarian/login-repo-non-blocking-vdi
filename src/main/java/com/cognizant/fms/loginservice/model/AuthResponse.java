package com.cognizant.fms.loginservice.model;


public class AuthResponse {
	
	private String token;
	
	public AuthResponse() {
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthResponse [token=" + token + "]";
	}
	
	
	
}