package com.thrive.common.testdata.pojos.user_details;

import java.util.List;

public class CoachCorporateExpDetails {
	
	private String position;
	private List<String> companies;
	private String industry;
	private String careerSmeRole;
	
	public CoachCorporateExpDetails(String position, List<String> companies, String industry, String careerSmeRole) {
		super();
		this.position = position;
		this.companies = companies;
		this.industry = industry;
		this.careerSmeRole = careerSmeRole;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<String> getCompanies() {
		return companies;
	}

	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCareerSmeRole() {
		return careerSmeRole;
	}

	public void setCareerSmeRole(String careerSmeRole) {
		this.careerSmeRole = careerSmeRole;
	}
	
	
	
	
	

}
