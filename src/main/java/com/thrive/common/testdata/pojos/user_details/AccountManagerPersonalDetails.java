package com.thrive.common.testdata.pojos.user_details;

public class AccountManagerPersonalDetails {
	
	private String password;
	private String mobileNumber;
	private String gender;
	private String country;
	private String nationality;
	private String ageRange;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String county;
	private String postcode;
	
	
	public AccountManagerPersonalDetails(String password, String mobileNumber, String gender, String country,
			String nationality, String ageRange, String address1, String address2, String city, String state,
			String county, String postcode) {
		super();
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.country = country;
		this.nationality = nationality;
		this.ageRange = ageRange;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.county = county;
		this.postcode = postcode;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getAgeRange() {
		return ageRange;
	}


	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
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
