package com.thrive.ui.page.register;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.pojos.user_details.ClientCareerDetails;
import com.thrive.common.utils.DBUtils;
import com.thrive.common.testdata.pojos.user_details.ClientCareerDetails;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RegisterClientCareerDetailsPage extends BaseTestPage{
	
	String getattitude;
	String getFutureTeam;
	String getFutureComapny; 
	String getFutureIndustry;
	String futureIndustry;
	String privacyErrorMsg = ThriveAppSharedData.PRIVACY_NOTICE_ERROR_MSG.getValue();
	
	private By getAttitudeRadioOption(String attitudeLabel) {
		return By.xpath(getXpathByText(".//label[contains(text(),'register.client.step3.career_label')]/following-sibling::div//label[contains(text(),'"+attitudeLabel+"')]/span"));
	}
	
	private By getFirstAvailableAttitudeRadio() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.x7iluelmtoj.label')]/following-sibling::div//label/span[@class='check']/.."));
	}
	
	private By getSelectedAttitudeRadioValue() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.x7iluelmtoj.label')]/following-sibling::div//label/span[@class='check active']/.."));
	}
	
	private By getFutureTeamRadioOption(int futureTeamOption) {
		return By.xpath(".//label[contains(text(),'Future Team')]/following-sibling::div/div/following-sibling::span[1]/label[normalize-space((text()) ='"+futureTeamOption+"')]/span");
	}
	
	private By getFirstAvailableFutureTeamRadioOption() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.w2ljtagl0e.label')]/../following-sibling::div/div/following-sibling::span/label/span[@class='check']/.."));
	}
	
	private By getSelectedFutureTeamRadiovalue() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.uuioybren7.label')]/../following-sibling::div/div/following-sibling::span/label/span[@class='check active']/.."));
	}
	
	private By getFutureCompanyRadioOption(int futureCompanyOption) {
		return By.xpath(".//label[contains(text(),'Future Company')]/following-sibling::div/div/following-sibling::span[2]/label[normalize-space((text()) ='"+futureCompanyOption+"')]/span");
	}
	
	private By getFirstAvailableFutureCompanyRadio() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.uuioybren7.label')]/../following-sibling::div/div/following-sibling::span/label/span[@class='check']/.."));
	}
	
	private By getSelectedFutureCompanyRadiovalue() {
		return By.xpath((".//label[contains(text(),'future of your company?')]/../following-sibling::div/div/following-sibling::span/label/span[@class='check active']/.."));
	}
	
	private By getFutureIndustryRadioOption(int futureIndustryOption) {
		return By.xpath(".//label[contains(text(),'Future Industry')]/following-sibling::div/div/following-sibling::span[3]/label[normalize-space((text()) ='"+futureIndustryOption+"')]/span");
	}
	
	private By getFistAvailableFutureIndustryradio() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.jyk60ewbteh.label')]/../following-sibling::div/div/following-sibling::span/label/span[@class='check']/.."));
	}
	
	private By getSelectedFutureIndustryRadioOption() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.jyk60ewbteh.label')]/../following-sibling::div/div/following-sibling::span/label/span[@class='check active']/.."));
	}
	
	private By getFutureAtCompanyRadioOption(int futureAtCompanyOption) {
		return By.xpath(".//label[contains(text(),'Future At Company')]/following-sibling::div/div/following-sibling::span[4]/label[normalize-space((text()) ='"+futureAtCompanyOption+"')]/span");
	}
	
	private By getRecommendedCompanyRadioOption(int recommendedCompanyOption) {
		return By.xpath(".//label[contains(text(),'Recommended Company')]/following-sibling::div/div/following-sibling::span[5]/label[normalize-space((text()) ='"+recommendedCompanyOption+"')]/span");
	}
	
	private By getSatisfiedCompanyRadioOption(int satisfiedCompanyOption) {
		return By.xpath(".//label[contains(text(),'Satisfied Company')]/following-sibling::div/div/following-sibling::span[6]/label[normalize-space((text()) ='"+satisfiedCompanyOption+"')]/span");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step3.next']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.client.step3.previous']"));
	}
	
	private By getConsentCheckbox() {
		return By.xpath(".//input[@id='email_updates']/following-sibling::span");
	}
	
	private By getRegisterButton() {
		return By.xpath(getXpathByText("//button[text()='register.client.step3.previous']//following-sibling::button"));
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='register.client.step3.update_details']"));
	}
	
	public RegisterClientCareerDetailsPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectAttitudeRadioOption(String attitudeLabel) {
		LOGGER.info("Selecting attitude radio option");
		click(getAttitudeRadioOption(attitudeLabel));
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectFirstAvailalbeAttitubeRadioOption() {
		LOGGER.info("Selecting first available attitude");
		getattitude = getText(getFirstAvailableAttitudeRadio());
		click(getFirstAvailableAttitudeRadio());
		return this;
	}
	
	
	public RegisterClientCareerDetailsPage selectFutureTeamRadioOption(int futureTeamOption) {
		LOGGER.info("Selectig future team ");
		click(getFutureTeamRadioOption(futureTeamOption));
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectFirstAvailableFutureTeamRadio() {
		LOGGER.info("Selectig first available future team ");
		getFutureTeam = getText(getFirstAvailableFutureTeamRadioOption());
		click(getFirstAvailableFutureTeamRadioOption());
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectFirstAvailableFutureComapnyRadio() {
		LOGGER.info("Selectig first available future company");
		getFutureComapny = getText(getFirstAvailableFutureCompanyRadio());
		click(getFirstAvailableFutureCompanyRadio());
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectFutureCompanyRadioOption(int futureCompanyOption) {
		LOGGER.info("Selectig future company");
		click(getFutureCompanyRadioOption(futureCompanyOption));
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectFutureIndustryRadioOption(int futureIndustryOption) {
		LOGGER.info("Selectig future industry");
		click(getFutureIndustryRadioOption(futureIndustryOption));
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectFirstAvailableFutureIndustry() {
		LOGGER.info("Selectig first available future industry team");
		getFutureIndustry = getText(getFistAvailableFutureIndustryradio());
		click(getFistAvailableFutureIndustryradio());
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectFutureAtCompanyRadioOption(int futureAtCompanyOption) {
		LOGGER.info("Selectig future at company");
		click(getFutureAtCompanyRadioOption(futureAtCompanyOption));
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectRecommendedCompanyRadioOption(int recommendedCompanyOption) {
		LOGGER.info("Selectig 'Recommended company'");
		click(getRecommendedCompanyRadioOption(recommendedCompanyOption));
		return this;
	}
	
	public RegisterClientCareerDetailsPage selectSatisfiedCompanyRadioOption(int satisfiedCompanyOption) {
		LOGGER.info("Selectig satisfied company");
		click(getSatisfiedCompanyRadioOption(satisfiedCompanyOption));
		return this;
	}
	
	public RegisterClientCareerDetailsPage registerClientInfo() {
		LOGGER.info("Registring client informaion");
		click(getConsentCheckbox());
		click(getRegisterButton());
		return this;
	}
	
	private By getPrivateErrorMsg() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(".//label[normalize-space(text()='Privacy Notice')]/..//p[text()='"+privacyErrorMsg+"']");
		} else {
		return By.xpath(".//label[contains(text(),'Notice d')]/..//p[text()='"+privacyErrorMsg+"']");
		}
	}
	
	public AlertsAndMessagesPage  validateClientRegistrationSuccessful(String expectedSuccessMessage) {
		LOGGER.info("Validating registration is successful");
	//	String getAlertQuery=DBQueries.getQueryForDBKey(expectedSuccessMessage);
		String actualMessage = getText(getAlertElement());
		Assert.assertEquals(actualMessage, expectedSuccessMessage, "Client Registration Failed With Error : " + actualMessage );
		
		return new AlertsAndMessagesPage();
	}	
	
	
	public RegisterClientCareerDetailsPage clickRegisterButton() {
		LOGGER.info("Clicking Register Button");
		click(getRegisterButton());
		return this;
	}
	
	
	public RegisterClientCareerDetailsPage validateCareerErrorValidation() {
		LOGGER.info("Validating career error validation");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getPrivateErrorMsg()),"error is not present for privacy");
		softAssert.assertAll();	
		return this;
		
	}
	

	public RegisterClientAboutPage submitClientCareerDetails(ClientCareerDetails clientCareerDetails) {
		LOGGER.info("Submitting client career details");
		selectAttitudeRadioOption(clientCareerDetails.getAttitudeRadioLabel())
		.selectFutureAtCompanyRadioOption(clientCareerDetails.getFutureAtCompanyRadioLabel())
		.selectRecommendedCompanyRadioOption(clientCareerDetails.getRecommendedCompanyRadioLabel())
		.selectSatisfiedCompanyRadioOption(clientCareerDetails.getSatisfyCompanyRadioLabel())
		.selectFutureCompanyRadioOption(clientCareerDetails.getFutureCompanyRadioLabel())
		.selectFutureIndustryRadioOption(clientCareerDetails.getFutureIndustryRadioLabel())
		.click(getNextButton());
		return new RegisterClientAboutPage();
	}
	
	public RegisterClientCareerDetailsPage validateCareer() {
		LOGGER.info("Validating career");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				 futureIndustry = getText(getSelectedFutureIndustryRadioOption());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
			
		String attitude = getText(getSelectedAttitudeRadioValue());
		String futureTeam = getText(getSelectedFutureTeamRadiovalue()) ;
		String futureComapny = getText(getSelectedFutureCompanyRadiovalue());
			
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getattitude.equalsIgnoreCase(attitude));
		softAssert.assertTrue(getFutureTeam.equalsIgnoreCase(futureTeam));
		softAssert.assertTrue(getFutureComapny.equalsIgnoreCase(futureComapny));
		softAssert.assertTrue(getFutureIndustry.equalsIgnoreCase(futureIndustry));
		softAssert.assertAll();
		return this;
	}

}