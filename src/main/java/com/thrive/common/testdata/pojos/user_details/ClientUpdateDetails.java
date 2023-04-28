package com.thrive.common.testdata.pojos.user_details;

public class ClientUpdateDetails {
	
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String county;
	private String postcode;
	private String ssoUseName;
	private String coachingPriorities;
	
	public ClientUpdateDetails(String firstName, String lastName, String mobileNumber, String address1, String address2,
			String city, String state, String county, String postcode, String ssoUseName, String coachingPriorities) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.county = county;
		this.postcode = postcode;
		this.ssoUseName = ssoUseName;
		this.coachingPriorities = coachingPriorities;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public String getCoachingPriorities() {
		return coachingPriorities;
	}
	
	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getSsoUseName() {
		return ssoUseName;
	}

	public void setSsoUseName(String ssoUseName) {
		this.ssoUseName = ssoUseName;
	}
	
	public void setCoachingPriorities(String coachingPriorities) {
		this.coachingPriorities = coachingPriorities;
	}
		
	
}
