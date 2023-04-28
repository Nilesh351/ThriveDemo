package com.thrive.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnableCategory {
	
	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("isenable")
	@Expose
	private Boolean isenable;
	
	

	public EnableCategory(Integer id, Boolean isenable) {
		super();
		this.id = id;
		this.isenable = isenable;
	}

	public Integer getId() {
	return id;
	}

	public void setId(Integer id) {
	this.id = id;
	}

	public Boolean getIsenable() {
	return isenable;
	}

	public void setIsenable(Boolean isenable) {
	this.isenable = isenable;
	}

}
