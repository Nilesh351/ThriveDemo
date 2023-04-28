package com.thrive.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expertise {
	
	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("level")
	@Expose
	private String level;

	public Expertise(Integer id, String level) {
		super();
		this.id = id;
		this.level = level;
	}

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public String getLevel() {
	return level;
	}

	public void setLevel(String level) {
	this.level = level;
	}

}
