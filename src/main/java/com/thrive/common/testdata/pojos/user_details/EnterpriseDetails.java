package com.thrive.common.testdata.pojos.user_details;

import java.util.List;

public class EnterpriseDetails {
	
	private String enterpriseName;
	private String website;
	private String address1;
	private String address2;
	private String county;
	private String country;
	private String postcode;
	private List<String> departments;
	private List<String> units;
	private boolean isSessionRecordingAllowed; 
	private List<String> sessionLength;
	private String city;
	
	
	public EnterpriseDetails(String website, String address1, String address2, String county,
			String country, String postcode, List<String> departments, List<String> units,
			boolean isSessionRecordingAllowed, List<String> sessionLength, String city) {
		super();
		this.website = website;
		this.address1 = address1;
		this.address2 = address2;
		this.county = county;
		this.country = country;
		this.postcode = postcode;
		this.departments = departments;
		this.units = units;
		this.isSessionRecordingAllowed = isSessionRecordingAllowed;
		this.sessionLength = sessionLength;
		this.city = city;
	}


	public String getEnterpriseName() {
		return enterpriseName;
	}


	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
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


	public String getCounty() {
		return county;
	}


	public void setCounty(String county) {
		this.county = county;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public List<String> getDepartments() {
		return departments;
	}


	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}


	public List<String> getUnits() {
		return units;
	}


	public void setUnits(List<String> units) {
		this.units = units;
	}


	public boolean isSessionRecordingAllowed() {
		return isSessionRecordingAllowed;
	}


	public void setSessionRecordingAllowed(boolean isSessionRecordingAllowed) {
		this.isSessionRecordingAllowed = isSessionRecordingAllowed;
	}


	public List<String> getSessionLength() {
		return sessionLength;
	}


	public void setSessionLength(List<String> sessionLength) {
		this.sessionLength = sessionLength;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	
	
	
	

}
