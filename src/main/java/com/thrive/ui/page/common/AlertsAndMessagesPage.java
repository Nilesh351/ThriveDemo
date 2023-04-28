package com.thrive.ui.page.common;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

public class AlertsAndMessagesPage extends BaseTestPage {

	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class, 'toast-close-button')]");
	}
	
	private By getYesButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'shared.modals.buttons.yes_button')]"));
	}
	
	private By getToasterDeleteClient() {
		return By.xpath("//div[text()=' Invited client has been deleted successfully. ']");
	}
	
	private By getNoButton() {
		return By.xpath(getXpathByText(".//button[text()='ui.buttons.No']"));
	}
	
	private By getReInviteEnterpriseToaster() {
		return By.xpath(".//div[normalize-space(text())='Invitation has been resent to this user.']");
	}
	
	private By getRemoveEnterpriseToaster() {
		return By.xpath(".//div[normalize-space(text())='Enterprise has been successfully deleted']");
	}
	
	private By getArchiveEnterpriseToaster() {
		return By.xpath(".//div[normalize-space(text())='Enterprise has been successfully archived']");
	}
	
	private By getCreateEnterpriseToaster() {
		return By.xpath(".//div[normalize-space(text())='Enterprise created successfully']");
	}
	
	private By getCreateEnterpriseToasterRm() {
		return By.xpath(".//div[normalize-space(text())='The Enterprise have been created.']");
	}
	
	private By getInvitationToaster() {
		return By.xpath(".//div[normalize-space(text())='The invitation email has been sent to email adress you have entered.']");
	}
	
	private By getCreditAllocationToaster() {
		return By.xpath(".//div[normalize-space(text())='Credits have been successfully allocated.']");
	}

	private By getArchiveToaster() {
		return By.xpath("//div[normalize-space(text())='Client(s) has been successfully archived']");
	}
	

	private By getRemoveCreditToaster() {
		return By.xpath(".//div[normalize-space(text())='Credits have been removed successfully.']");
	}
	
	private By getBookingConfirmedToaster() {
		return By.xpath(".//div[normalize-space(text())='Booking is confirmed.']");
	}
	
	private By getNoOfLanguage(int language) {
		return By.xpath("//span[text()='Language("+language+")']");
	}
	
	public AlertsAndMessagesPage validateLanguageFilter(int sizeoflanguage) {
		LOGGER.info("Validating language filter works as expected");
	    Assert.assertTrue(isElementPresent(getNoOfLanguage(sizeoflanguage)));
		return this;
	}
	
	private By getNoOfRegion(int region) {
		return By.xpath(".//div[@class='ng-input']//child::input[@autocomplete='a05f98b22cf0"+region+"']");
	}
	
	public AlertsAndMessagesPage validateRegionFilter(int sizeofregion) {
		LOGGER.info("Validating region filter works as expected");
		Assert.assertTrue(isElementPresent(getNoOfRegion(sizeofregion)));
		return this;
	}
	
	private By getNoOfExpertise(int expertise) {
		return By.xpath("//span[text()='Expertise("+expertise+")']");
	}
	
	public AlertsAndMessagesPage validateExpertiseFilter(int sizeofexpertise) {
		LOGGER.info("Validating expertise filter works as expected");
	    Assert.assertTrue(isElementPresent(getNoOfExpertise(sizeofexpertise)));
		return this;
	}
	
	private By getNoOfIndustry(int industry) {
		return By.xpath("//span[text()='INDUSTRY("+industry+")']");
	}
	
	public AlertsAndMessagesPage validateIndustryFilter(int sizeofindustry) {
		LOGGER.info("Validating Industry filter works as expected");
	    Assert.assertTrue(isElementPresent(getNoOfIndustry(sizeofindustry)));
		return this;
	}
	
	public AlertsAndMessagesPage validateRemoveCreditToaster(String expectedMessage) {
		LOGGER.info("Validating Remove Credit toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to remove credits");
		return this;
	}

	public AlertsAndMessagesPage ClickYes() {
		LOGGER.info("Clicking Yes button");
		click(getYesButton());
		return this;
	}

	public AlertsAndMessagesPage ClickNo() {
		LOGGER.info("Clicking No button");
		click(getNoButton());
		return this;
	}

	public AlertsAndMessagesPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		closeToasterAlert();
		return this;
	}
	
	public UserManagementPage validateCreateEnterpriseToaster() {
		LOGGER.info("Validating Create Enterprise toaster message");
		
		String message = "Create enterprise unsuccessfull";
		String actual = getText(getAlertElement());
		
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			Assert.assertEquals(actual, ThriveAppSharedData.ENT_CREATED_MESSAGE.getValue(), message);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			Assert.assertEquals(actual, ThriveAppSharedData.ENT_CREATED_MESSAGE_RM.getValue(), message);
		} else {
			Assert.fail(message);
		}
		
		return new UserManagementPage();
	}
	
	public AlertsAndMessagesPage validateReinviteToaster(String expectedMessage) {
		LOGGER.info("Validating Reinvite toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertEquals(actualMessage, expectedMessage);
		return this;
	}
	
	public AlertsAndMessagesPage validateRemoveEnterpriseToaster(String expectedMessage) {
		LOGGER.info("Validating Remove Enterprise toaster message");
		String actualMessage=getText(getRemoveEnterpriseToaster());
		Assert.assertEquals(actualMessage, expectedMessage);
		return this;
	}
	
	public AlertsAndMessagesPage validateCreditAllocationToaster(String expected) {
		LOGGER.info("Validating Credit allocation toaster message");
		String actual=getText(getAlertElement());
		Assert.assertTrue(actual.equalsIgnoreCase(expected));
		return this;
	}

	
	private By getAlertWindow() {
		return By.xpath(".//div[@class='modal-content']");
	}

	public AlertsAndMessagesPage validateArchiveClientAlertIsVisible() {
		LOGGER.info("Validate Archive Client alert is visible.");
		Assert.assertTrue(isElementVisible(getAlertWindow()),"Archive client alert is not visible.");
	    return this;
	}
	
	public AlertsAndMessagesPage validateArchiveEmployeeAlertIsVisible() {
		LOGGER.info("Validate Archive Employee alert is visible..");
		Assert.assertTrue(isElementVisible(getAlertWindow()),"Archive employee alert is not visible.");
	    return this;
	}
	
	public AlertsAndMessagesPage validateDeletClientAlertIsVisible() {
		LOGGER.info("Validate Delete Client alert is visible.");
		Assert.assertTrue(isElementVisible(getAlertWindow()),"Delete client alert is not visible.");
	    return this;
	}
	
	public UserManagementPage closeToasterAlert() {
		LOGGER.info("Closing toaster alert ");
		click(getToasterCloseButton());
		return new UserManagementPage();
	}
	
}