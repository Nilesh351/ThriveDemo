package com.thrive.common.testdata.pojos.user_details;

public class CoachEmploymentDetails {
	
	private String empStatus;
	private String otherOrganization;
	private String workAs;
	
	

	public CoachEmploymentDetails(String empStatus, String otherOrganization, String workAs) {
		super();
		this.empStatus = empStatus;
		this.otherOrganization = otherOrganization;
		this.workAs = workAs;
	}



	public String getWorkAs() {
		return workAs;
	}

	public void setWorkAs(String workAs) {
		this.workAs = workAs;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getOtherOrganization() {
		return otherOrganization;
	}

	public void setOtherOrganization(String otherOrganization) {
		this.otherOrganization = otherOrganization;
	}
	
	

}
