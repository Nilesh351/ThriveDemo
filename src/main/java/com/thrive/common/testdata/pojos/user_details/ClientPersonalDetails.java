package com.thrive.common.testdata.pojos.user_details;

import java.util.List;



import com.thrive.common.utils.DateTimeUtils.TimeFormat;

public class ClientPersonalDetails {
	
	private String password;
	private String mobileNumber;
	private String date;
	private List<String> languages;
	private String addressSearchable;
	private String addressClickable;
	private String SSOUserName;
	private String compnayName;
	private TimeFormat timeFormat;
	private String address1;
	private String address2;
	private String city;
	private String county;
	private String postcode;
	private String ageRange;
	private String gender;
	private String country;
	
	public ClientPersonalDetails(String password, String mobileNumber, String date, List<String> languages,String sSOUserName, String compnayName,
			TimeFormat timeFormat, String address1, String address2, String city, String county, String postcode,
			String ageRange, String gender, String country) {
		super();
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.date = date;
		this.languages = languages;
		this.addressSearchable = addressSearchable;
		this.addressClickable = addressClickable;
		SSOUserName = sSOUserName;
		this.compnayName = compnayName;
		this.timeFormat = timeFormat;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.county = county;
		this.postcode = postcode;
		this.ageRange = ageRange;
		this.gender = gender;
		this.country = country;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public String getAddressSearchable() {
		return addressSearchable;
	}

	public void setAddressSearchable(String addressSearchable) {
		this.addressSearchable = addressSearchable;
	}

	public String getAddressClickable() {
		return addressClickable;
	}

	public void setAddressClickable(String addressClickable) {
		this.addressClickable = addressClickable;
	}

	public String getSSOUserName() {
		return SSOUserName;
	}

	public void setSSOUserName(String sSOUserName) {
		SSOUserName = sSOUserName;
	}

	public String getCompnayName() {
		return compnayName;
	}

	public void setCompnayName(String compnayName) {
		this.compnayName = compnayName;
	}

	public TimeFormat getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(TimeFormat timeFormat) {
		this.timeFormat = timeFormat;
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

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
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
	
	
	

}
