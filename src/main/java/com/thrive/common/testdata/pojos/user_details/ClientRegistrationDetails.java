package com.thrive.common.testdata.pojos.user_details;

import com.thrive.common.testdata.utils.Enums.Gender;

public class ClientRegistrationDetails {
	
	private Gender gender;
	private String mobileNumber;
	private String ethinicity;
	private String age;
	private String nationality;
	
	public ClientRegistrationDetails(Gender gender, String mobileNumber, String ethinicity, String age,
			String nationality) {
		super();
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.ethinicity = ethinicity;
		this.age = age;
		this.nationality = nationality;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEthinicity() {
		return ethinicity;
	}
	public void setEthinicity(String ethinicity) {
		this.ethinicity = ethinicity;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	

}
