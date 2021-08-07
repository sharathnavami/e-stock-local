package com.stock.market.model;

import org.springframework.stereotype.Component;

@Component
public class AuthToken {
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String token;
	

}
