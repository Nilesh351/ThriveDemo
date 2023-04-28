package com.thrive.ui.page.register;

import org.openqa.selenium.By;

import com.thrive.common.testdata.pojos.user_details.ClientRegistrationDetails;
import com.thrive.ui.core.BaseTestPage;

public class RegisterClientRegistrationDetailsPage extends BaseTestPage {
	
	private By getGenderDropdown() {
		return By.xpath(".//ng-select[@id='gender']");
	}
	
	private By getGenderOptionElement(String gender) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+gender+"']");
	}
	
	private By getEthnicityDropdown() {
		return By.xpath(".//ng-select[@id='ethnicity']");
	}
	
	private By getMobileInput() {
		return By.xpath(".//input[@id='phone']");
	}
	
	private By getEthnicityOptionElement(String ethnicity) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+ethnicity+"']");
	}
	
	private By getAgeDropdown() {
		return By.xpath(".//ng-select[@name='age_range']");
	}
	
	private By getAgeOptionElement(String age) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+age+"']");
	}
	
	private By getNationalityDropdown() {
		return By.xpath(".//ng-select[@name='nationality']");
	}
	
	private By getNationalityElement(String nationality) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+nationality+"']");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.client.step2.next']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.client.step3.previous']"));
	}
	
	public RegisterClientEmploymentStatusPage clickNext() {
		LOGGER.info("Clicking Next button");
		click(getNextButton());
		return new RegisterClientEmploymentStatusPage();
	}
	
	public RegisterClientRegistrationDetailsPage selectGender(String gender) {
		LOGGER.info("Selecting gender");
		click(getGenderDropdown());
		click(getGenderOptionElement(gender));
		return this;
	}
	
	public RegisterClientRegistrationDetailsPage selectEthinicity(String ethnicity) {
		LOGGER.info("Selecting ethinicity");
		click(getEthnicityDropdown());
		click(getEthnicityOptionElement(ethnicity));
		return this;
	}
	
	public RegisterClientRegistrationDetailsPage selectAge(String age) {
		LOGGER.info("Selecting age");
		click(getAgeDropdown());
		click(getAgeOptionElement(age));
		return this;
	}
	
	public RegisterClientRegistrationDetailsPage selectNationality(String nationality) {
		LOGGER.info("Selecting nationality");
		click(getNationalityDropdown());
		click(getNationalityElement(nationality));
		return this;
	}
	
	public RegisterClientRegistrationDetailsPage setMobileNumber(String mobile) {
		LOGGER.info("Setting value for mobile number");
		setValueWithoutValidation(mobile, getMobileInput());
		return this;
	}
	
	public RegisterClientEmploymentStatusPage submitClientRegistartionDetails(ClientRegistrationDetails clientRegistrationDetails) {
		LOGGER.info("Submitting client registration details");
		selectGender(clientRegistrationDetails.getGender().getValue())
		.setMobileNumber(clientRegistrationDetails.getMobileNumber())
		.selectEthinicity(clientRegistrationDetails.getEthinicity())
		.selectAge(clientRegistrationDetails.getAge().toString())
		.selectNationality(clientRegistrationDetails.getNationality())
		.clickNext();
		return new RegisterClientEmploymentStatusPage();
	}


}
