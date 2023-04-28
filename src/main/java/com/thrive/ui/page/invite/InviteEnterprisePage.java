package com.thrive.ui.page.invite;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class InviteEnterprisePage extends UserManagementCommonPage  {

	
	private By getInviteEnterpriseLink() {
		return By.xpath(getXpathByText("//li[text()='admin.user_management.enterprises.invite_enterprise']"));
	}
	
	private By getLanguageDropdown() {
		return By.xpath("//ng-select[@name='email_language']//input[@role='combobox']");	
	}
	
	private By getlanguageFromDropdowlist() {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span");
	}
	
	private By getEnterprisesInputField() {
		return By.xpath("//input[@name='invite_enterprise']");
	}
	
	private By getFirstNameInputField() {
		return By.xpath("//input[@name='first_Name']");
	}
	
	private By getLastNameInputField() {
		return By.xpath("//input[@name='last_Name']");
	}
	
	private By getEmailAddressInputField() {
		return By.xpath("//input[@name='userEmail']");
	}
	
	private By getSendInvitationButton() {
		return By.xpath(getXpathByText("//button[text()='admin.clients.invite.send_invitation']"));
	}
	private By getInviteEnterpriseEmailSendVerificationText() {
		return By.xpath(".//div[@aria-label='The invitation email has been sent to email adress you have entered.']");
	}
	
	public InviteEnterprisePage clickInviteEnterpriseLink() {
		LOGGER.info("click on the invite enterprise link");
		click(getInviteEnterpriseLink());
		return this;
	}
	
	public InviteEnterprisePage selectValueFromLanguageDropdown() {
		LOGGER.info("click language Dropdown");
		setValue("English-US",getLanguageDropdown());
		selectGivenValueFromAutoDropdown(getlanguageFromDropdowlist(),"English-US");
		return this;
	}
	
	public InviteEnterprisePage setEnterpriseInput(String enterprisename) {
		LOGGER.info("set enterprise name input field");
		setValue(enterprisename,getEnterprisesInputField());
		return this;
	}
	
	public InviteEnterprisePage setFirstNameInput(String firstname) {
		LOGGER.info("set First name input field");
		setValue(firstname,getFirstNameInputField());
		return this;
	}
	
	public InviteEnterprisePage setLastNameInput(String lastname) {
		LOGGER.info("set Last name input field");
		setValue(lastname,getLastNameInputField());
		return this;
	}
	
	public InviteEnterprisePage setEmailAddressInput(String email) {
		LOGGER.info("set Email Address input field");
		setValue(email,getEmailAddressInputField());
		return this;
	}
	
	public AlertsAndMessagesPage clickSendInvitationButton() {
		LOGGER.info("click send invitation button");
		click(getSendInvitationButton());
		return new AlertsAndMessagesPage();
	}
	
	public InviteEnterprisePage verifyInviteEnterpiseEmailNotification(String notificationtext)
	{
		LOGGER.info("Verify the Invitation coach email is send nofification");
		Assert.assertEquals(getText(getInviteEnterpriseEmailSendVerificationText()),notificationtext, "invite coach notification appeared");
		return this;
	}
	
	
	public InviteEnterprisePage sendInviteEnterprise(InviteEnterpriseDetails  inviteEnterpriseDetails) {
		LOGGER.info("Submitting enterprise details to inivte");
		selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
		setEnterpriseInput(inviteEnterpriseDetails.getEnterpriseName())
		.setFirstNameInput(inviteEnterpriseDetails.getFirstName()).
		setLastNameInput(inviteEnterpriseDetails.getLastName())
		.setEmailAddressInput(inviteEnterpriseDetails.getEmailAddress());
		return this;
	}
	
	public InviteEnterprisePage enterInviteEnterpriseDetails(InviteEnterpriseDetails  inviteEnterpriseDetails) {
		LOGGER.info("Submitting enterprise details to inivte");
		if(Config.getEnv().equalsIgnoreCase("thrivedev")) {
			selectValueFromLanguageDropdown();
		}
		setEnterpriseInput(inviteEnterpriseDetails.getEnterpriseName())
		.setFirstNameInput(inviteEnterpriseDetails.getFirstName()).
		setLastNameInput(inviteEnterpriseDetails.getLastName())
		.setEmailAddressInput(inviteEnterpriseDetails.getEmailAddress());
		return this;
	}
	
	
	public InviteEnterprisePage sendInvite(InviteEnterpriseDetails  inviteEnterpriseDetails) {
		LOGGER.info("Sending invitation");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			enterInviteEnterpriseDetails(inviteEnterpriseDetails);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)){
			if(Config.getLocalizationLanguage().contains("en")){
				selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
				enterInviteEnterpriseDetails(inviteEnterpriseDetails);
			}  else {
			selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue());
			enterInviteEnterpriseDetails(inviteEnterpriseDetails);
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
	
	public String validateEntInviteToaster(String expectedMessage,String email) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		InviteEnterpriseDetails inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
		if(!actualMessage.equals(expectedMessage)) {
			click(getToasterCloseButton());
			setEmailAddressInput(inviteEnterpriseDetails.getEmailAddress());
			clickSendInvitationButton();
			actualMessage = getText(getAlertElement());
			email = inviteEnterpriseDetails.getEmailAddress();
		}
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		click(getToasterCloseButton());
		return email;
	}
	
	private By getEnterEnterpriseDetailsElement() {
		return By.xpath("//p[contains(text(),'Please enter the details of the enterprise')]");
	}
	
	public InviteEnterprisePage validateInviteEnterprisePageInVisible() {
		LOGGER.info("Validate Invite enterrpise page is visible");
		Assert.assertTrue(isElementVisible(getEnterEnterpriseDetailsElement()),"Invite Enterprise page is not visible");
		return this;
	}

}
