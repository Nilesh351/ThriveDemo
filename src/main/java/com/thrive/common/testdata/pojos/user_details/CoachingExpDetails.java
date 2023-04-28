package com.thrive.common.testdata.pojos.user_details;

import java.util.List;
import java.util.Map;

public class CoachingExpDetails {
	
	private List<String> levels;
	private List<String> companies;
	private int coachingHours;
	private List<String> sectors;
	private List<Map<String, String>> expertises;
	
	
	
	
	public CoachingExpDetails(List<String> levels, List<String> companies, int coachingHours, List<String> sectors,
			List<Map<String, String>> expertises) {
		super();
		this.levels = levels;
		this.companies = companies;
		this.coachingHours = coachingHours;
		this.sectors = sectors;
		this.expertises = expertises;
	}
	
	public List<String> getLevels() {
		return levels;
	}
	public void setLevels(List<String> levels) {
		this.levels = levels;
	}
	public List<String> getCompanies() {
		return companies;
	}
	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}
	public int getCoachingHours() {
		return coachingHours;
	}
	public void setCoachingHours(int coachingHours) {
		this.coachingHours = coachingHours;
	}
	public List<String> getSectors() {
		return sectors;
	}
	public void setSectors(List<String> sectors) {
		this.sectors = sectors;
	}
	public List<Map<String, String>> getExpertises() {
		return expertises;
	}
	public void setExpertises(List<Map<String, String>> expertises) {
		this.expertises = expertises;
	}
	
	
	
	

}
