package com.thrive.common.testdata.pojos.invite_details;

import java.util.List;

public class CoachInviteDetails {
	
	private String enterprise;
	private String language;
	private List<String> categories;
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	public CoachInviteDetails(String enterprise, String language, List<String> categories, String firstName,
			String lastName, String emailAddress) {
		super();
		this.enterprise = enterprise;
		this.language = language;
		this.categories = categories;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}


	public CoachInviteDetails(List<String> categories, String firstName, String lastName, String emailAddress) {
		super();
		this.categories = categories;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}


	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}
