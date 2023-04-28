package com.thrive.common.testdata.pojos.user_details;

import java.util.List;

public class CreateEnterpriseDetails {
	
	private String entname;
	private String website;
	private String address1;
	private String address2;
	private String city;
	private String county;
	private String country;
	private List<String> departments;
	private List<String> units;
	private String postcode;
	private boolean isAllowRecording;
	private List<String> sessionLength;
	
	public CreateEnterpriseDetails(String entname, String website, String address1, String address2, String city,
			String county, String postcode) {
		//super();
		this.entname = entname;
		this.website = website;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.county = county;
		this.postcode = postcode;
	}
	
	

	public CreateEnterpriseDetails(String entname, String website, String address1, String address2, String city,
			String county, String country, String postcode, List<String> departments, List<String> units,
			boolean isAllowRecording, List<String> sessionLength) {
		super();
		this.entname = entname;
		this.website = website;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.county = county;
		this.country = country;
		this.departments = departments;
		this.units = units;
		this.postcode = postcode;
		this.isAllowRecording = isAllowRecording;
		this.sessionLength = sessionLength;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public boolean isAllowRecording() {
		return isAllowRecording;
	}

	public void setAllowRecording(boolean isAllowRecording) {
		this.isAllowRecording = isAllowRecording;
	}

	public List<String> getSessionLength() {
		return sessionLength;
	}

	public void setSessionLength(List<String> sessionLength) {
		this.sessionLength = sessionLength;
	}


	public  String getEntname() {
		return entname;
	}

	public void setEntname(String entname) {
		this.entname = entname;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
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
	
}
