package com.thrive.common.testdata.pojos.user_details;

import java.util.List;

public class CoachAboutDetails {
	
	
	private String summry;
	private String about;
	private String topNewIndustry;
	private String coachOffer;
	private String coachExperience;
	private String testimoial;
	private String coachingSummary;
	private String professionalSummary;
	private String region;
	private int dateAwarded;
	private List<String> languages;
	private String industry;
	private String yearsAsCoach;
	private String qualification;
	
	
	public CoachAboutDetails(String summry, String about, String topNewIndustry, String coachOffer,
			String coachExperience, String testimoial, String coachingSummary, String professionalSummary,
			String region, int dateAwarded, List<String> languages, String industry,String yearsAsCoach,String qualification) {
		super();
		this.summry = summry;
		this.about = about;
		this.topNewIndustry = topNewIndustry;
		this.coachOffer = coachOffer;
		this.coachExperience = coachExperience;
		this.testimoial = testimoial;
		this.coachingSummary = coachingSummary;
		this.professionalSummary = professionalSummary;
		this.region = region;
		this.dateAwarded = dateAwarded;
		this.languages = languages;
		this.industry = industry;
		this.yearsAsCoach = yearsAsCoach;
		this.qualification = qualification;
	}



	public String getQualification() {
		return qualification;
	}



	public void setQualification(String qualification) {
		this.qualification = qualification;
	}



	public String getSummry() {
		return summry;
	}

	public String getYearsAsCoach() {
		return yearsAsCoach;
	}



	public void setYearsAsCoach(String yearsAsCoach) {
		this.yearsAsCoach = yearsAsCoach;
	}



	public void setSummry(String summry) {
		this.summry = summry;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getTopNewIndustry() {
		return topNewIndustry;
	}

	public void setTopNewIndustry(String topNewIndustry) {
		this.topNewIndustry = topNewIndustry;
	}

	public String getCoachOffer() {
		return coachOffer;
	}

	public void setCoachOffer(String coachOffer) {
		this.coachOffer = coachOffer;
	}

	public String getCoachExperience() {
		return coachExperience;
	}

	public void setCoachExperience(String coachExperience) {
		this.coachExperience = coachExperience;
	}

	public String getTestimoial() {
		return testimoial;
	}

	public void setTestimoial(String testimoial) {
		this.testimoial = testimoial;
	}

	public String getCoachingSummary() {
		return coachingSummary;
	}

	public void setCoachingSummary(String coachingSummary) {
		this.coachingSummary = coachingSummary;
	}

	public String getProfessionalSummary() {
		return professionalSummary;
	}

	public void setProfessionalSummary(String professionalSummary) {
		this.professionalSummary = professionalSummary;
	}



	public String getRegion() {
		return region;
	}



	public int getDateAwarded() {
		return dateAwarded;
	}



	public List<String> getLanguages() {
		return languages;
	}



	public String getIndustry() {
		return industry;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public void setDateAwarded(int dateAwarded) {
		this.dateAwarded = dateAwarded;
	}



	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}



	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	


}
