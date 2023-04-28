package com.thrive.common.testdata.pojos.invite_details;

public class InviteClientDetails {
	
	private String enterprise;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String SSOUserName;
	
	
	
	public InviteClientDetails(String enterprise, String firstName, String lastName, String emailAddress,
			String sSOUserName) {
		super();
		this.enterprise = enterprise;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		SSOUserName = sSOUserName;
	}
	
	
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
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
	public String getSSOUserName() {
		return SSOUserName;
	}
	public void setSSOUserName(String sSOUserName) {
		SSOUserName = sSOUserName;
	}
	
	
	

}
