package com.eintrusty.security.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8118371507430237147L;
	private String token;
    private String type = "Bearer";
 
    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }
 
    public String getAccessToken() {
        return token;
    }
 
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }
 
    public String getTokenType() {
        return type;
    }
 
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

}
