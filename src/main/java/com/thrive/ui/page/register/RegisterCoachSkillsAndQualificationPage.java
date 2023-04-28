package com.thrive.ui.page.register;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.user_details.CoachSkillAndQualificationDetails;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RegisterCoachSkillsAndQualificationPage extends BaseTestPage {
	
	String language;
	String techPlatform;
	String date;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	
	private By getLanguagesDropdown() {
		return By.xpath(".//ng-select[@name='languages']");
	}
	
	private By getlanguagesRemoveIcon() {
		return By.xpath(".//ng-select[@name='languages']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getLanguagesOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getTopicElement(String topic) {
		return By.xpath(".//label[@class='di-block' and text()='"+topic+"']");
	}
	
	private By getExpertiseElement(String topic) {
		return By.xpath(".//small[text()='"+topic+"']");
	}
	
	private By getCanCoachRadioOption() {
		return By.xpath(getXpathByText(".//label[contains(text(),'ui.buttons.can_coach')]/span"));
	}
	
	private By getExpertRadioOption() {
		return By.xpath(getXpathByText(".//label[contains(text(),'ui.buttons.expert')]/span"));
	}
	
	private By getEmpStatusDropdown() {
		return By.xpath(".//ng-select[@name='employment_status']");
	}
	
	private By getWorkAsDropdown() {
		return By.xpath(".//ng-select[@name='company_type']");
	}
	
	private By getWorkAsOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	
	private By getEmpStatusOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getQualificationsInput() {
		return By.xpath(".//input[contains(@id, 'qualifications')]");
	}
	
	private By getDatePickerInput() {
		return By.xpath(".//input[@name='session_date']");
	}
	
	private By getFirstAvailableDate() {
		return By.xpath(".//ngb-datepicker//div[@class='btn-light ng-star-inserted']");
	}
	
	private By getDatePickerPrevElement() {
		return By.xpath(".//button[@title='Previous month']");
	}
	
	private By getDatePickerDateElement(Integer date) {
		return By.xpath(".//div[contains(@class,'btn-light ng-star') and text()='"+date.toString()+"']");
	}
	
	private By getOtherQualificationInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'register.coach.step3.other_relevant_qualifications')]/../following-sibling::input"));
	}
	
	private By getOtherProfessionalQualificationInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'register.coach.step3.other_qualifications')]/../following-sibling::input"));
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step3.next']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step3.previous']"));
	}
	
	private By getFirstAvailableValue() {
		return  By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getTechPlatformDropdown() {
		return By.xpath(".//ng-select[@name='tech_expertise']");
	}
	
	private By getTechPlatformRemoveIcon() {
		return By.xpath(".//ng-select[@name='tech_expertise']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getTechPlatformOptionElement(String option) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+option+"']");
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	private By getLanguageValue(String lang) {
		return By.xpath(".//span[text()='"+lang+"']");
	}
	
	private By getTechPlatformValue(String techplatform) {
		return By.xpath(".//span[text()='"+techplatform+"']");
	}
	
	private By getDatePickerErrorMsg() {
		return By.xpath(".//input[@name='session_date']/../..//p[text()='"+errorMsg+"']");
	}
	
	private By getLanguageDropdownErrormsg() {
		return By.xpath(".//ng-select[@name='languages']/..//p[text()='"+errorMsg+"']");
	}
	
	private By getLanguagesHeading() {
		return By.xpath("//label[@for='languages']");
	}
	
	public RegisterCoachProfileForClients clickNext() {
		LOGGER.info("Clicking Next button");
		click(getNextButton());
		return new RegisterCoachProfileForClients();
	}
	
	public RegisterCoachSkillsAndQualificationPage selectLanguages(List<String> languages) {
		LOGGER.info("Selecting languages");
		click(getLanguagesDropdown());
		for(String language : languages) {
			click(getLanguagesOptionElement(language));
		}
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage selectFirstAvailabellanguage() {
		LOGGER.info("Selecting first available languages");
		click(getLanguagesDropdown());
		click(getlanguagesRemoveIcon());
		language = getText(getFirstAvailableValue());
		click(getFirstAvailableValue());
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage selectExpertise(String topic, String expertise) {
		LOGGER.info("Selecting expertise");
		click(getTopicElement(topic));
		click(getExpertiseElement(expertise));
		click(getCanCoachRadioOption());
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage selectTechPlatform(String techPlatform) {
		LOGGER.info("Selecting technical platform");
		click(getTechPlatformDropdown());
		click(getTechPlatformOptionElement(techPlatform));
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage selectFirstAvailabelTechPlatform() {
		LOGGER.info("Selecting first available technical platform");
		click(getTechPlatformDropdown());
		click(getTechPlatformRemoveIcon());
		techPlatform = getText(getFirstAvailableValue());
		click(getFirstAvailableValue());
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage selectEmpStatus(String empStatus) {
		LOGGER.info("Selecting emp status");
		click(getEmpStatusDropdown());
		click(getEmpStatusOptionElement(empStatus));
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage selectWorkAs(String workAsOption) {
		LOGGER.info("Selecting Work as");
		click(getWorkAsDropdown());
		click(getWorkAsOptionElement(workAsOption));
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage selectDateAwarded(int date) {
		LOGGER.info("Selecting Date awarded");
		click(getDatePickerInput());
		click(getDatePickerPrevElement());
		click(getDatePickerDateElement(date));
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage selectFirstAvailableDate() {
		LOGGER.info("Selecting first available");
		date = getAttributeByValue("value", getDatePickerInput());
		click(getDatePickerInput());
		click(getFirstAvailableDate());
		return this;
	}
	
	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}
	
	public RegisterCoachSkillsAndQualificationPage setQualification(String qualification) {
		LOGGER.info("Setting value for qualification");
		setValue(qualification, getQualificationsInput());
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage setOtherQualification(String otherQualification) {
		LOGGER.info("Setting value for Other qualification");
		setValue(otherQualification, getOtherQualificationInput());
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage setOtherProfessionalQualification(String otherProfessinalQualification) {
		LOGGER.info("Setting value for Other professional qualification");
		setValue(otherProfessinalQualification, getOtherProfessionalQualificationInput());
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		return this;
	}
	
	
	private By getLanguageName(String lang) {
		return By.xpath(".//span[text()='"+lang+"']");
	}
	
	public RegisterCoachSkillsAndQualificationPage validateLanguagePresent(String val) {
		LOGGER.info("Validate selected language present");
		Assert.assertTrue(isElementPresent(getLanguageName(val)));
		return this;
	}
	
	
	public RegisterCoachCorporateExpPage submitCoachSkillAndQualificationDetails(CoachSkillAndQualificationDetails  coachSkillAndQualificationDetails) {
		LOGGER.info("Submitting coach skills and qualification");
		setValue(coachSkillAndQualificationDetails.getQualification(), getQualificationsInput());
		selectDateAwarded(coachSkillAndQualificationDetails.getDateAwarded());
		setValue(coachSkillAndQualificationDetails.getOtherQualification(), getOtherQualificationInput());
		setValue(coachSkillAndQualificationDetails.getOtherProfessionalQualification(), getOtherProfessionalQualificationInput());
		selectLanguages(coachSkillAndQualificationDetails.getLanguages());
		selectTechPlatform(coachSkillAndQualificationDetails.getTechPlatform());
		//click(getLanguagesHeading());
		clickNext();
		
		return new RegisterCoachCorporateExpPage();
	}
	
	public RegisterCoachSkillsAndQualificationPage validateCoachSkillsAndQualficationsErrorMsg() {
			
			LOGGER.info("Validating coach skills and qualification error validation");
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(isElementVisible(getDatePickerErrorMsg()),"error message not present for date picker");
			softAssert.assertTrue(isElementVisible(getLanguageDropdownErrormsg()),"error message not present for language");
			softAssert.assertAll();
			return this;
		}
	
	
	public RegisterCoachSkillsAndQualificationPage validateCoachSkillsAndQualificationsDetails(CoachSkillAndQualificationDetails  coachSkillAndQualificationDetails) {
		LOGGER.info("Validating coach skills and qualification");
		String qualification = getAttributeByValue("value", getQualificationsInput());
		String otherQualification = getAttributeByValue("value", getOtherQualificationInput());
		String otherProfessionalQualification = getAttributeByValue("value", getOtherProfessionalQualificationInput());
		String getDate = getAttributeByValue("value", getDatePickerInput());
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(qualification.equalsIgnoreCase(coachSkillAndQualificationDetails.getQualification()),"qualification is not updated");
		softAssert.assertTrue(otherQualification.equalsIgnoreCase(coachSkillAndQualificationDetails.getOtherQualification()),"other qualification is not updated");
		softAssert.assertTrue(otherProfessionalQualification.equalsIgnoreCase(coachSkillAndQualificationDetails.getOtherProfessionalQualification()),"other professional qualification is not updated");
		softAssert.assertTrue(isElementPresent(getLanguageValue(language)),"language is not updated");
		softAssert.assertTrue(isElementPresent(getTechPlatformValue(techPlatform)),"tech platform not updated");
		softAssert.assertNotEquals(date,getDate,"date not updated");
		softAssert.assertAll();
		return this;
	}
	
	
	
	

}
