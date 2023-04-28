package com.thrive.common.testdata.pojos.invite_details;


public class InviteEnterpriseCoachDetails{
	
	private String firstname;
	private String lastname;
	private String email;
	
	public InviteEnterpriseCoachDetails(String firstname,String lastname,String email)
	{
		this.firstname=firstname;
		this.lastname=lastname;
		this.email=email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
