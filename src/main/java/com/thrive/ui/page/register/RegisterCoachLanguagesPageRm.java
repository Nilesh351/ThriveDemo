package com.thrive.ui.page.register;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DBUtils;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class RegisterCoachLanguagesPageRm extends UserManagementCommonPage{
	
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	String language;
	
	private By getLanguageDropdown() {
		return By.xpath(".//ng-select[@name='languages']");
	}
	
	private By getLanguageRemoveIcon() {
		return By.xpath(".//ng-select[@name='languages']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getLanguageOptionElement(String language) {
		return By.xpath(getXpathByText(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+language+"']"));
	}
	
	private By getLanguageErrorValidation() {
		return By.xpath(".//ng-select[contains(@name,'languages')]/..//p[text()='"+errorMsg+"']");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step2.next']"));
	}
	
	private By getLanguagesHeading() {
		return By.xpath(getXpathByText("//label[contains(text(),'ui.forms.languages')]"));
	}
	
	private By getFirstAvailableValue() {
		return  By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getLanguageValue(String lang) {
		return By.xpath(".//span[text()='"+lang+"']");
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	private By getLanguageName() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_coach.vj09ethabie.label')]"));
	}
	
	
	public RegisterCoachLanguagesPageRm clickNext() {
		LOGGER.info("Select next button");
		waitUntilElementIsVisible(getLanguageDropdown());
		click(getNextButton());
		return this;
	}
	
	public RegisterCoachLanguagesPageRm selectFirstAvailableLanguage() {
		click(getLanguageDropdown());
		
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				click(getLanguageRemoveIcon());
				language = getText(getFirstAvailableValue());
				click(getFirstAvailableValue());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		click(getLanguageName());
		return this;
	}
	
	public RegisterCoachMentoringExpPage submitLanguagesDetails(String language) {
		LOGGER.info("Select language");
	//	String languageName=ThriveAppSharedData.LANGUAGE_TYPE_IN_FRENCH.getValue();
		click(getLanguageDropdown());
		if(Config.getLocalizationLanguage().contains("en")) {
		click(getLanguageOptionElement(language));
		} else {
			click(getLanguageOptionElement(language));
		}
		click(getLanguagesHeading());
		shortWait();
		click(getNextButton());
		return new RegisterCoachMentoringExpPage();
	}
	
	public RegisterCoachLanguagesPageRm validateLangugaesErrorvalidation() {
		LOGGER.info("Validating coach languages details error validation");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getLanguageErrorValidation()),"error message not presnt for Language");
		softAssert.assertAll();
		return this;
	}
	
	public RegisterCoachLanguagesPageRm validateLanguagesDetails() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getLanguageValue(language)),"language is not updated");
		softAssert.assertAll();
		return this;
	}
	
	private By getLanguageName(String lang) {
		return By.xpath(".//span[text()='"+lang+"']");
	}
	
	public RegisterCoachLanguagesPageRm validateLanguagePresent(String val) {
		LOGGER.info("Validate selected language present");
		Assert.assertTrue(isElementPresent(getLanguageName(val)));
		return this;
	}
	
	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}
	
	public RegisterCoachLanguagesPageRm validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		return this;
	}

	
}
