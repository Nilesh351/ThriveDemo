package com.thrive.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("password")
	@Expose
	private String password;
	@SerializedName("timezone")
	@Expose
	private String timezone;
	@SerializedName("locale")
	@Expose
	private String locale;
	
	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

		
	

}
