package com.thrive.common.testdata.pojos;

public class LoginDetails {

	private String username;
	private String password;
	private String testName;
	private String testDescription;
	private String userType;
	
	public LoginDetails(String username, String password, String testName, String testDescription, String userType) {
		super();
		this.username = username;
		this.password = password;
		this.testName = testName;
		this.userType = userType;
		this.testDescription = testDescription;
	}
	

	public LoginDetails(String username,String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
	
	
}
