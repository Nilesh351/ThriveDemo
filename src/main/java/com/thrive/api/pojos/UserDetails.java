package com.thrive.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {
	
	
	@SerializedName("key")
	@Expose
	private String key;
	@SerializedName("value")
	@Expose
	private String value;
	
	public UserDetails(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
