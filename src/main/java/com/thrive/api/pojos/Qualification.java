package com.thrive.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Qualification {
	
	@SerializedName("awarded_at")
	@Expose
	private String awardedAt;
	@SerializedName("name")
	@Expose
	private String name;
	
	public Qualification(String awardedAt, String name) {
		super();
		this.awardedAt = awardedAt;
		this.name = name;
	}

	public String getAwardedAt() {
	return awardedAt;
	}

	public void setAwardedAt(String awardedAt) {
	this.awardedAt = awardedAt;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

}
