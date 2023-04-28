package com.thrive.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InviteEnterpriseRequest {
	
	@SerializedName("company_name")
	@Expose
	private String company;
	@SerializedName("email_language")
	@Expose
	private String emailLanguage;
	@SerializedName("first_name")
	@Expose
	private String firstName;
	@SerializedName("last_name")
	@Expose
	private String lastName;
	@SerializedName("email")
	@Expose
	private String email;
	
	
	public InviteEnterpriseRequest(String company, String emailLanguage, String firstName, String lastName,
			String email) {
		super();
		this.company = company;
		this.emailLanguage = emailLanguage;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getEmailLanguage() {
		return emailLanguage;
	}


	public void setEmailLanguage(String emailLanguage) {
		this.emailLanguage = emailLanguage;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	

}
