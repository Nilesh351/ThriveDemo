package com.thrive.common.testdata.pojos.credits;

public class AllocateCredits {
	
	private String creditstype;
	private String Credits;
	private String Date;
	
	
	public AllocateCredits(String creditstype, String credits, String date) {
		super();
		this.creditstype = creditstype;
		Credits = credits;
		Date = date;
	}


	public String getCreditstype() {
		return creditstype;
	}


	public void setCreditstype(String creditstype) {
		this.creditstype = creditstype;
	}


	public String getCredits() {
		return Credits;
	}


	public void setCredits(String credits) {
		Credits = credits;
	}


	public String getDate() {
		return Date;
	}


	public void setDate(String date) {
		Date = date;
	}
	
	
}
