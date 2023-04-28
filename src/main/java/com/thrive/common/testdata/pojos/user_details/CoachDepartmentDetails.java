package com.thrive.common.testdata.pojos.user_details;

import java.util.List;

public class CoachDepartmentDetails {
	
	private String position;
	private List<String> industries;
	
	public CoachDepartmentDetails(String position, List<String> industries) {
		super();
		this.position = position;
		this.industries = industries;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<String> getIndustries() {
		return industries;
	}
	public void setIndustries(List<String> industries) {
		this.industries = industries;
	}
	
	

}