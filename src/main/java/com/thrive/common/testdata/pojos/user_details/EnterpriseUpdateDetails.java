package com.thrive.common.testdata.pojos.user_details;

public class EnterpriseUpdateDetails {
	
	private String enterpriseName;
	private String enterpriseCode;
	private String infoUsefulForCoach;
	private String website;
	private String addressLine1;
	private String addressLine2;
	private String county;
	private String postcode;
	private String creditsOnVerification;
	private String city;
	
	
	public EnterpriseUpdateDetails(String enterpriseName, String enterpriseCode, String infoUsefulForCoach,
			String website, String addressLine1, String addressLine2, String county, String postcode,
			String creditsOnVerification, String city) {
		super();
		this.enterpriseName = enterpriseName;
		this.enterpriseCode = enterpriseCode;
		this.infoUsefulForCoach = infoUsefulForCoach;
		this.website = website;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.county = county;
		this.postcode = postcode;
		this.creditsOnVerification = creditsOnVerification;
		this.city = city;
	}


	public String getEnterpriseName() {
		return enterpriseName;
	}


	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}


	public String getEnterpriseCode() {
		return enterpriseCode;
	}


	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}


	public String getInfoUsefulForCoach() {
		return infoUsefulForCoach;
	}


	public void setInfoUsefulForCoach(String infoUsefulForCoach) {
		this.infoUsefulForCoach = infoUsefulForCoach;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getAddressLine1() {
		return addressLine1;
	}


	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}


	public String getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
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


	public String getCreditsOnVerification() {
		return creditsOnVerification;
	}


	public void setCreditsOnVerification(String creditsOnVerification) {
		this.creditsOnVerification = creditsOnVerification;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}
	

}
