package com.thrive.common.testdata.pojos.user_details;

public class ClientEmploymentDetails {
	
	private String workDuration;
	private String department;
	private String region;
	private String position;
	private String dateFormat;
	private String reference;
	
	
	public ClientEmploymentDetails(String workDuration, String department, String region, String position,
			String dateFormat, String reference) {
		super();
		this.workDuration = workDuration;
		this.department = department;
		this.region = region;
		this.position = position;
		this.dateFormat = dateFormat;
		this.reference = reference;
	}
	
	public ClientEmploymentDetails(String workDuration,String position, String region, String department) {
		super();
		this.workDuration = workDuration;
		this.position = position;
		this.region = region;
		this.department = department;
	}
	
	
	
	public String getWorkDuration() {
		return workDuration;
	}
	public void setWorkDuration(String workDuration) {
		this.workDuration = workDuration;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	

}
