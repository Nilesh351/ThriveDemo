package com.thrive.ui.page.invite;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.ui.core.BaseTestPage;

public class InviteInternalCoachPage extends BaseTestPage{
	
	private By getlanguageDropdown() {
		return By.xpath("//ng-select[@name='email_language']");
	}
	
	private By getlanguageFromDropdown() {
		return By.xpath("//span[text()='English-US']");
	}
	
	private By getCoachCategoriesDropdown() {
		return By.xpath("//ng-select[@name='category']");
	}
	
	private By getCoachCategoryFromDropdown() {
		return By.xpath("//span[text()='acintcategory01']");
	}
	
	private By getFirstNameInputField() {
		return By.xpath("//input[@name='first_Name']");
	}
	
	private By getLastNameInputField() {
		return By.xpath("//input[@name='last_Name']");
	}
	
	private By getEmailInputField() {
		return By.xpath("//input[@name='userEmail']");
	}
	
	private By getSendInvitationButton() {
		return By.xpath("//button[@type='submit']");
	}
	
	private By getInviteEmailSendVerificationText() {
		return By.xpath(".//div[@aria-label='The invitation email has been sent to email adress you have entered.']");
	}
	
	public InviteInternalCoachPage clickSelectLanguageDropdown() {
		LOGGER.info("Click language dropdown");
		click(getlanguageDropdown());
		return this;
	}
	
	public InviteInternalCoachPage selectLanguageFromDropdown() {
		LOGGER.info("Click language from dropdown");
		click(getlanguageFromDropdown());
		return this;
	}
	
	public InviteInternalCoachPage clickCoachCategoryDropdown() {
		LOGGER.info("Click coach category dropdown");
		click(getCoachCategoriesDropdown());
		return this;
	}
	
	public InviteInternalCoachPage selectCoachFromDropdown() {
		LOGGER.info("Click coach category from  dropdown");
		click(getCoachCategoryFromDropdown());
		return this;
	}
	
	public InviteInternalCoachPage enterFirstNameInput(String firstname) {
		LOGGER.info("Enter firstname input");
		setValue(firstname,getFirstNameInputField());
		return this;
	}
	
	public InviteInternalCoachPage enterLastNameInput(String lastname) {
		LOGGER.info("Enter lastname input");
		setValue(lastname,getLastNameInputField());
		return this;
	}
	
	public InviteInternalCoachPage enterEmailIAddressnput(String email) {
		LOGGER.info("Enter eamil input");
		setValue(email,getEmailInputField());
		return this;
	}
	
	public InviteInternalCoachPage clickOnSendInvitationButton() {
		LOGGER.info("Click submit button");
		click(getSendInvitationButton());
		return this;
	}
	
	public InviteInternalCoachPage VerifyInviteCoachEmailNotification(String notification)
	{
		LOGGER.info("Verify the Invitation coach email is send nofification ");
		//Assert.assertTrue(isElementVisible(getInviteEmailSendVerificationText()), "Invite Coach notification is not appeared");
		Assert.assertEquals(getText(getInviteEmailSendVerificationText()),notification, "invite client notification appeared");
		return this;
	}
	
	
	public InviteInternalCoachPage enterInviteCoachDetails(String firstname,String lastname,String email) {
		LOGGER.info("Submitting coach details to invite");
		clickSelectLanguageDropdown().selectLanguageFromDropdown()
		.clickCoachCategoryDropdown().selectCoachFromDropdown()
		.enterFirstNameInput(firstname).
		enterLastNameInput(lastname).enterEmailIAddressnput(email);
		return this;
	}
	
}
