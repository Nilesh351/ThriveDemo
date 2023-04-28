package com.thrive.common.testdata.pojos.invite_details;

public class InviteEnterpriseDetails {

	private String enterpriseName;
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	
	
	public InviteEnterpriseDetails(String enterpriseName, String firstName, String lastName, String emailAddress) {
		super();
		this.enterpriseName = enterpriseName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
	}
	
	
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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
