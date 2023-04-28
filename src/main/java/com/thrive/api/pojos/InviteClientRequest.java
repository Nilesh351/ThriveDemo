package com.thrive.api.pojos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InviteClientRequest {

	@SerializedName("company")
	@Expose
	private Integer company;
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
	@SerializedName("sso_user_id")
	@Expose
	private String ssoUserId;
	
	

	public InviteClientRequest(Integer company, String emailLanguage, String firstName, String lastName, String email,
			String ssoUserId) {
		super();
		this.company = company;
		this.emailLanguage = emailLanguage;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.ssoUserId = ssoUserId;
	}

	public Integer getCompany() {
	return company;
	}

	public void setCompany(Integer company) {
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

	public String getSsoUserId() {
	return ssoUserId;
	}

	public void setSsoUserId(String ssoUserId) {
	this.ssoUserId = ssoUserId;
	}

}
