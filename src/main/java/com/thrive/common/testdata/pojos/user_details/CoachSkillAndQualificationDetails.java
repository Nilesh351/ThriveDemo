package com.thrive.common.testdata.pojos.user_details;

import java.util.List;

public class CoachSkillAndQualificationDetails {
	
	private List<String> languages;
	private String topic;
	private String expertise;
	private String empStatus;
	private String workAs;
	private String qualification;
	private int dateAwarded;
	private String otherQualification;
	private String otherProfessionalQualification;
	private String techPlatform;
	


	public CoachSkillAndQualificationDetails(List<String> languages, String topic, String expertise, String empStatus,
			String workAs, String qualification, int dateAwarded, String otherQualification,
			String ptherProfessionalQualification) {
		super();
		this.languages = languages;
		this.topic = topic;
		this.expertise = expertise;
		this.empStatus = empStatus;
		this.workAs = workAs;
		this.qualification = qualification;
		this.dateAwarded = dateAwarded;
		this.otherQualification = otherQualification;
		this.otherProfessionalQualification = ptherProfessionalQualification;
	}
	
	
	public CoachSkillAndQualificationDetails(List<String> languages, String qualification, int dateAwarded,
			String otherQualification, String otherProfessionalQualification, String techPlatform) {
		super();
		this.languages = languages;
		this.qualification = qualification;
		this.dateAwarded = dateAwarded;
		this.otherQualification = otherQualification;
		this.otherProfessionalQualification = otherProfessionalQualification;
		this.techPlatform = techPlatform;
	}



	public String getTechPlatform() {
		return techPlatform;
	}

	public void setTechPlatform(String techPlatform) {
		this.techPlatform = techPlatform;
	}

	public List<String> getLanguages() {
		return languages;
	}


	public void setLanguage(List<String> languages) {
		this.languages = languages;
	}


	public String getTopic() {
		return topic;
	}


	public void setTopic(String topic) {
		this.topic = topic;
	}


	public String getExpertise() {
		return expertise;
	}


	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}


	public String getEmpStatus() {
		return empStatus;
	}


	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public int getDateAwarded() {
		return dateAwarded;
	}


	public void setDateAwarded(int dateAwarded) {
		this.dateAwarded = dateAwarded;
	}


	public String getOtherQualification() {
		return otherQualification;
	}


	public void setOtherQualification(String otherQualification) {
		this.otherQualification = otherQualification;
	}


	public String getOtherProfessionalQualification() {
		return otherProfessionalQualification;
	}


	public void setOtherProfessionalQualification(String otherProfessionalQualification) {
		this.otherProfessionalQualification = otherProfessionalQualification;
	}


	public String getWorkAs() {
		return workAs;
	}


	public void setWorkAs(String workAs) {
		this.workAs = workAs;
	}
	
	
	
	
	

}
