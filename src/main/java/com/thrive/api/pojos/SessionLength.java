package com.thrive.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionLength {
	
	@SerializedName("session05")
	@Expose
	private Boolean session05;
	@SerializedName("session1")
	@Expose
	private Boolean session1;
	
	public SessionLength(Boolean session05, Boolean session1) {
		super();
		this.session05 = session05;
		this.session1 = session1;
	}

	public Boolean getSession05() {
		return session05;
	}

	public Boolean getSession1() {
		return session1;
	}

	public void setSession05(Boolean session05) {
		this.session05 = session05;
	}

	public void setSession1(Boolean session1) {
		this.session1 = session1;
	}

	
	
	

}
