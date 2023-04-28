package com.thrive.ui.page.invite;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class InviteAccountManagerPage extends UserManagementCommonPage {
	
	private By getFirstNameInput() {
		return By.xpath(".//input[@name='first_Name']");
	}
	
	private By getLastNameInput() {
		return By.xpath(".//input[@name='last_Name']");
	}
	
	private By getEmailAddressInput() {
		return By.xpath(".//input[@name='userEmail']");
	}
	
	private By getSendInvitationButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.clients.invite.send_invitation']"));
	}
	
	private By getLanguageDropdown() {
		return By.xpath("//ng-select[@name='email_language']//input[@role='combobox']");	
	}
	
	private By getlanguageFromDropdowlist() {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span");
	}
	
	public InviteAccountManagerPage clickSendInvitation() {
		click(getSendInvitationButton());
		return this;
	}
	
	public InviteAccountManagerPage selectValueFromLanguageDropdown() {
		LOGGER.info("Selecting language ");
		setValue("English-US",getLanguageDropdown());
		selectGivenValueFromAutoDropdown(getlanguageFromDropdowlist(),"English-US");
		return this;
	}
	
	public InviteAccountManagerPage setAmEmail(String email) {
		LOGGER.info("set email address for client");
		setValue(email, getEmailAddressInput());
		return this;
	}
	
	public InviteAccountManagerPage enterInviteAmDeatils(InviteAccountManagerDetails inviteAccountManagerDetails) {
		LOGGER.info("Submitting details and sending invitation");
		if(Config.getEnv().equalsIgnoreCase("thrivedev")) {
			selectValueFromLanguageDropdown();
		}		
		setValue(inviteAccountManagerDetails.getFirstName(), getFirstNameInput());
		setValue(inviteAccountManagerDetails.getLastName(), getLastNameInput());
		setValue(inviteAccountManagerDetails.getEmailAddress(), getEmailAddressInput());
		return this;
	}
	
	public InviteAccountManagerPage sendInvite(InviteAccountManagerDetails inviteAccountManagerDetails) {
		LOGGER.info("Sending invitation");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			enterInviteAmDeatils(inviteAccountManagerDetails);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)){
			selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
			enterInviteAmDeatils(inviteAccountManagerDetails);
			if(Config.getLocalizationLanguage().contains("fr")) {
				selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue());
				enterInviteAmDeatils(inviteAccountManagerDetails);
			}
		}
		click(getSendInvitationButton());
		return this;
	}
	
	
	private By getEnterDetailsElementLink() {
		return By.xpath(getXpathByText("//p[contains(text(),'admin.account.invite.p1')]"));
	}
	
	public InviteAccountManagerPage validateInviteAmPageIsVisible() {
		LOGGER.info("Validate Invite Account Manager page visible");
		Assert.assertTrue(isElementVisible(getEnterDetailsElementLink()),"Invite Account manager page is not visible");
	    return this;
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class, 'toast-close-button')]");
	}
	
	public String validateAMInviteToaster(String expectedMessage,String email) {
		LOGGER.info("Validating toaster message");
		InviteAccountManagerDetails inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();
		String actualMessage = getText(getAlertElement());
		if(!actualMessage.equals(expectedMessage)) {
			click(getToasterCloseButton());
			setValue(inviteAccountManagerDetails.getEmailAddress(), getEmailAddressInput());
			clickSendInvitation();
			email = inviteAccountManagerDetails.getEmailAddress();
			actualMessage = getText(getAlertElement());
		}
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
					+ expectedMessage + " and actual is : " + actualMessage);
		click(getToasterCloseButton());
		return email;
	}

}
