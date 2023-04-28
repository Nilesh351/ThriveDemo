package com.thrive.ui.page.book_session;

import org.openqa.selenium.By;

import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class SessionFeedbackPage extends UserManagementCommonPage{
	
	
	private By getSessionChangeYourViewCheckbox() {
		return By.xpath(".//label[contains(text(),'change your view of')]/../following-sibling::div[1]//label");
	}
	
	private By getSatisfiedWithCoachRadio() {
		return By.xpath(".//label[contains(text(),'How satisfied were you with the coach')]/../following-sibling::div//div[2]//label");
	}
	
	private By getRecommendCoachingToFriendsRadio() {
		return By.xpath(".//label[contains(text(),'coaching to your friends')]/../following-sibling::div//div[2]//label");
	}
	
	private By getActionsHaveImpactRadio() {
		return By.xpath(".//label[contains(text(),'How likely will your actions have an impact')]/../following-sibling::div//div[2]//label");
	}
	
	private By getSubmitToCoachButtton() {
		return By.xpath(".//button[contains(text(),'Submit')]");
	}
	
	private By getSatisfiedWithCoachingSessionRadio() {
		return By.xpath(".//label[contains(text(),'How satisfied were you with the coaching session?')]/../following-sibling::div//div[2]//label");
	}
	
	private By getInvestmentCareerDevelopementDropdown() {
		return By.xpath(".//label[contains(text(),'your career development')]/../..//ng-select[@id='reason']//input");
	}
	
	private By getFirstAvailableDropdownValue() {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span");
	}
	
	private By getLearnedSesionIntoPractiseDropdown() {
		return By.xpath(".//label[contains(text(),'session into practice? ')]/../..//ng-select[@id='reason']//input");
	}
	
	private By getLearningIntoPracticeOption() {
		return By.xpath(".//ng-dropdown-panel//div[@role='option'][1]");
	}
	
	public SessionFeedbackPage selectInvestmentCareerDevelopementDropdownValue() {
		LOGGER.info("select Investment Career developement Dropdown value");
		click(getInvestmentCareerDevelopementDropdown());
		click(getFirstAvailableDropdownValue());
		return this;	
	}
	
	public SessionFeedbackPage selectLearnedSessionIntoPractiseDropdownValue() {
		LOGGER.info("select Learned Session Into Practise Dropdown Value");
		click(getLearnedSesionIntoPractiseDropdown());
		click(getFirstAvailableDropdownValue());
		return this;
	}
	
	
	public SessionFeedbackPage clickSessionChangeYourViewCheckbox() {
		LOGGER.info("Click session chnage your view checkbox");
		click(getSessionChangeYourViewCheckbox());
		return this;
	}
	
	public SessionFeedbackPage clickSatisfiedWithCoachRadioRadio() {
		LOGGER.info("Click satisfied with caoch radio");
		click(getSatisfiedWithCoachRadio());
		return this;
	}
	
	public SessionFeedbackPage clickRecommendCoachingToFriendsRadio() {
		LOGGER.info("Click Recommend Coaching to friends radio");
		click(getRecommendCoachingToFriendsRadio());
		return this;
	}
	
	public SessionFeedbackPage clickActionsHaveInpactRadio() {
		LOGGER.info("Click Actions have impact radio");
		click(getActionsHaveImpactRadio());
		return this;
	}
	
	public SessionFeedbackPage clickSubmitToCoachButton() {
		LOGGER.info("Click submit to coach button");
		click(getSubmitToCoachButtton());
		return this;
	}
	
	private SessionFeedbackPage selectLearningIntoPracticeOption() {
		click(getLearnedSesionIntoPractiseDropdown());
		click(getLearningIntoPracticeOption());
		return this;
	}
	
	public AlertsAndMessagesPage sessionFeedbackDetails() {
		LOGGER.info("Provide session feedback details");
		if(Config.getTestPlatform().equalsIgnoreCase("rm")) {
			click(getSatisfiedWithCoachingSessionRadio());
			selectInvestmentCareerDevelopementDropdownValue();
			selectLearnedSessionIntoPractiseDropdownValue();
		}
		click(getSessionChangeYourViewCheckbox());
		click(getSatisfiedWithCoachRadio());
		selectLearningIntoPracticeOption();
		click(getSatisfiedWithCoachingSessionRadio());
		click(getRecommendCoachingToFriendsRadio());
		click(getActionsHaveImpactRadio());
		click(getSubmitToCoachButtton());
		return new AlertsAndMessagesPage();
	}
	

}
