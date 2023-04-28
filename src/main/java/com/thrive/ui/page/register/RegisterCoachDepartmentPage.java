package com.thrive.ui.page.register;

import java.util.List;

import org.openqa.selenium.By;

import com.thrive.common.testdata.pojos.user_details.CoachDepartmentDetails;
import com.thrive.ui.core.BaseTestPage;

public class RegisterCoachDepartmentPage extends BaseTestPage {
	
	private By getPositionDropdown() {
		return By.xpath(".//ng-select[@name='other_senior_position']");
	}
	
	private By getPositionOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getCareerIndustriesDropdown() {
		return By.xpath(".//ng-select[@name='career_industries']");
	}
	
	private By getCareerIndustriesOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step3.next']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step3.previous']"));
	}
	
	public RegisterCoachSkillsAndQualificationPage clickNext() {
		LOGGER.info("Clicking Next button");
		click(getNextButton());
		return new RegisterCoachSkillsAndQualificationPage();
	}
	
	public RegisterCoachDepartmentPage selectPosition(String position) {
		LOGGER.info("Selecting position");
		click(getPositionDropdown());
		click(getPositionOptionElement(position));
		return this;
	}
	
	public RegisterCoachDepartmentPage selectCareerIndustries(List<String> industries) {
		LOGGER.info("Selecting career industries");
		click(getCareerIndustriesDropdown());
		for(String industry : industries) {
			click(getCareerIndustriesOptionElement(industry));
		}
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage submitCoachDepartmentDetails(CoachDepartmentDetails coachDepartmentDetails) {
		LOGGER.info("Submitting coach department details");
		selectPosition(coachDepartmentDetails.getPosition())
		.selectCareerIndustries(coachDepartmentDetails.getIndustries())
		.clickNext();
		return new RegisterCoachSkillsAndQualificationPage();
	}
	

}
