package com.thrive.ui.page.invite;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class InviteClientPage extends UserManagementCommonPage {

	private By getLanguageDropdown() {
		return By.xpath("//ng-select[@name='email_language']//input[@role='combobox']");	
	}
	
	private By getlanguageFromDropdowlist() {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span");
	}
	
	private By getEntepriseDropdown() {
		return By.xpath("//ng-select[@name='enterprise']");	
	}
	
	private By getEntepriseDropdownOptionElement(String enterprise) {
		return By.xpath("//span[contains(@class,'ng-option') and text() = '"+enterprise+"']");	
	}
	
	private By getSSOInput() {
		return By.xpath("//input[@name='sso_user_id']");	
	}
	
	private By getFirstNameInputField() {
		return By.xpath("//input[@id='first_name']");
	}
	
	private By getLastNameInputField() {
		return By.xpath("//input[@id='last_name']");
	}
	
	private By getEmailInputField() {
		return By.xpath("//input[@type='email']");
		//return By.xpath("//input[@id='email']");
	}
	
	private By getSendInvitationButton() {
		return By.xpath("//button[@type='submit']");
	}
	
	private By getInviteClientEmailSendVerificationText() {
		return By.xpath(".//div[@aria-label='The invitation email has been sent to email adress you have entered.']");
	}
	
	private By getBulkInviteButton() {
		return By.xpath(".//button[text()='Bulk Invite']");
	}
	
	public BulkInviteClientPage clickBulkInviteButton() {
		LOGGER.info("click Bulk Invite button");
		click(getBulkInviteButton());
		return new BulkInviteClientPage();
	}
	
	public InviteClientPage selectValueFromLanguageDropdown() {
		LOGGER.info("Selecting language ");
		setValue("English-US",getLanguageDropdown());
		selectGivenValueFromAutoDropdown(getlanguageFromDropdowlist(),"English-US");
		return this;
	}
	
	public InviteClientPage setFirstNameInput(String firstname) {
		LOGGER.info("set First name input field");
		setValue(firstname,getFirstNameInputField());
		return this;
	}
	
	public InviteClientPage setLastNameInput(String lastname) {
		LOGGER.info("set Last name input field");
		setValue(lastname,getLastNameInputField());
		return this;
	}
	
	public InviteClientPage setEmailAddressInput(String email) {
		LOGGER.info("set Email Address input field");
		setValue(email,getEmailInputField());
		return this;
	}
	
	public InviteClientPage clickSendInvitationButton() {
		LOGGER.info("click send invitation button");
		click(getSendInvitationButton());
		return this;
	}
	
	private By getEmailAddressClient(String email) {
		return By.xpath(getXpathByText(".//th[(text())='admin.user_management.clients.col.emailid']/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	public InviteClientPage validateInvitedClientPresent(String email) {
		LOGGER.info("Validating invited coach names is present");
        Assert.assertTrue(isElementPresent(getEmailAddressClient(email)), "Failed to search the email:" + email);
	    return this;
	}
	
	public InviteClientPage selectEnterprise(String entName) {
		LOGGER.info("Selecting enterprise");
		click(getEntepriseDropdown());
		click(getEntepriseDropdownOptionElement(entName));
		return this;
	}
	
	public InviteClientPage setSSO(String ssoUser) {
		LOGGER.info("set sso name input field");
		setValue(ssoUser, getSSOInput());
		return this;
	}
	
	public InviteClientPage verifyInviteEnterpiseEmailNotification(String notificationtext) {
		LOGGER.info("Verify the Invitation coach email is send nofification");
		Assert.assertEquals(getText(getInviteClientEmailSendVerificationText()),notificationtext, "invite coach notification appeared");
		return this;
	}
	
	
	public InviteClientPage enterInviteClientDetails(InviteClientDetails inviteClientDetails) {
		LOGGER.info("Submitting details to invite clients");
		shortWait();
		if(Config.getEnv().equalsIgnoreCase("thrivedev")) {
			selectValueFromLanguageDropdown();
		}
		selectEnterprise(inviteClientDetails.getEnterprise());
		setFirstNameInput(inviteClientDetails.getFirstName()).setLastNameInput(inviteClientDetails.getLastName())
		.setEmailAddressInput(inviteClientDetails.getEmailAddress());
		//.setSSO(inviteClientDetails.getSSOUserName());
		return this;
	}
	
		
	//Right method
	public InviteClientPage sendInvite(InviteClientDetails inviteClientDetails) {
		LOGGER.info("Sending invitation");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			enterInviteClientDetails(inviteClientDetails);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)){
			if(Config.getLocalizationLanguage().contains("en")){
			selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
			enterInviteClientDetails(inviteClientDetails);
			} else {
				selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue());
				enterInviteClientDetails(inviteClientDetails);
			}
		}
		click(getSendInvitationButton());

		return this;
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class, 'toast-close-button')]");
	}
	
	public String validateClientInviteToaster(String expectedMessage,String email) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
		if(!actualMessage.equals(expectedMessage)) {
			click(getToasterCloseButton());
			setEmailAddressInput(inviteClientDetails.getEmailAddress());
			click(getSendInvitationButton());
			email = inviteClientDetails.getEmailAddress();
			actualMessage = getText(getAlertElement());
		}
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		click(getToasterCloseButton());
		return email;
	}

	
	public By getInviteClientElementLink() {
		return By.xpath(getXpathByText("//h2[contains(text(),'admin.clients.view.invite')]"));
	}
	
	public InviteClientPage validateInviteClientPageIsVisible() {
		LOGGER.info("Validate Invite Client page is visible");
		Assert.assertTrue(isElementVisible(getInviteClientElementLink()),"Invite Client page is not visible.");
	    return this;
	}
	
	
	
}
