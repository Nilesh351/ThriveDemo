package com.thrive.common.testdata.pojos.user_details;

public class CoachCorporateExpUpdateDetails {
	
	private String companiedProviedCoaching;
	private String rolesHeld;
	private String careerSmeRole;
	
	
	public CoachCorporateExpUpdateDetails(String companiedProviedCoaching, String rolesHeld, String careerSmeRole) {
		super();
		this.companiedProviedCoaching = companiedProviedCoaching;
		this.rolesHeld = rolesHeld;
		this.careerSmeRole = careerSmeRole;
	}


	public String getCompaniedProviedCoaching() {
		return companiedProviedCoaching;
	}


	public void setCompaniedProviedCoaching(String companiedProviedCoaching) {
		this.companiedProviedCoaching = companiedProviedCoaching;
	}


	public String getRolesHeld() {
		return rolesHeld;
	}


	public void setRolesHeld(String rolesHeld) {
		this.rolesHeld = rolesHeld;
	}


	public String getCareerSmeRole() {
		return careerSmeRole;
	}


	public void setCareerSmeRole(String careerSmeRole) {
		this.careerSmeRole = careerSmeRole;
	}
	
	
	
	
	

}
