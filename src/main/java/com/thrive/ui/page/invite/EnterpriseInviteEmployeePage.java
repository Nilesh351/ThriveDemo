package com.thrive.ui.page.invite;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

public class EnterpriseInviteEmployeePage  extends UserManagementCommonPage{
	
	AlertsAndMessagesPage alertsAndMessagesPage=new AlertsAndMessagesPage();
	UserManagementPage userManagementPage;

	private By getImportEmployeeFile() {
		LOGGER.info("Get import employees file");
		return By.xpath(".//input[@id='import']");	
	}
	
	private By getFirstNameInputField() {
		LOGGER.info("Get firstname element");
		return By.xpath("//input[@id='first_name']");
	}
	
	private By getLastNameInputField() {
		LOGGER.info("Get lastname element");
		return By.xpath("//input[@id='last_name']");
	}
	
	private By getEmailInputField() {
		LOGGER.info("Get email element");
		return By.xpath("//input[@name='userEmail']");
	}
	private By getSSOInput() {
		return By.xpath("//input[@name='sso_user_id']");	
	}
	
	private By getSendInvitationButton() {
		LOGGER.info("Get send invitation button");
		return By.xpath("//button[@type='submit']");
	}
	
	private By getInviteEmployeeEmailSendVerificationText() {
		LOGGER.info("Get invite Employee Email send verification");
		return By.xpath(".//div[@aria-label='The invitation email has been sent to email adress you have entered.']");
	}
	
	
	public EnterpriseInviteEmployeePage setFirstNameInput(String firstname) {
		LOGGER.info("set First name input field");
		setValue(firstname,getFirstNameInputField());
		return this;
	}
	
	public EnterpriseInviteEmployeePage setLastNameInput(String lastname) {
		LOGGER.info("set Last name input field");
		setValue(lastname,getLastNameInputField());
		return this;
	}
	
	public EnterpriseInviteEmployeePage setEmailAddressInput(String email) {
		LOGGER.info("set Email Address input field");
		setValue(email,getEmailInputField());
		return this;
	}
	
	public EnterpriseInviteEmployeePage clickSendInvitationButton() {
		LOGGER.info("click send invitation button");
		click(getSendInvitationButton());
		return this;
	}
	
	public EnterpriseInviteEmployeePage setSSO(String ssoUser) {
		LOGGER.info("Validating value for SSO username");
		setValue(ssoUser, getSSOInput());
		return this;
	}
	
	public EnterpriseInviteEmployeePage verifyInviteEnterpiseEmailNotification(String notificationtext) {
		LOGGER.info("Verify the Invitation coach email is send nofification");
		Assert.assertEquals(getText(getInviteEmployeeEmailSendVerificationText()),notificationtext, "invite coach notification appeared");
		return this;
	}
	
	public EnterpriseInviteEmployeePage enterInviteEmployeeDetails(InviteEmployeeDetails inviteEmployeeDetails) {
		LOGGER.info("Submitting details to invite employee");
		setFirstNameInput(inviteEmployeeDetails.getFirstName()).setLastNameInput(inviteEmployeeDetails.getLastName())
		.setEmailAddressInput(inviteEmployeeDetails.getEmailAddress());
	//	.setSSO(inviteEmployeeDetails.getSSOUserName());
		return this;
	}
	
	public EnterpriseInviteEmployeePage sendInvite(InviteEmployeeDetails inviteEmployeeDetails) {
		LOGGER.info("Sending inivtation");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			enterInviteEmployeeDetails(inviteEmployeeDetails);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)){
			if(Config.getLocalizationLanguage().contains("en")){
			selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
			enterInviteEmployeeDetails(inviteEmployeeDetails);
			} else {
				selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue());
				enterInviteEmployeeDetails(inviteEmployeeDetails);
			}
		}
		click(getSendInvitationButton());
		return this;
		
	}
	
	public By getInviteEmployeeMessageLink() {
		return By.xpath(getXpathByText("//h2[contains(text(),'admin.employees.invite.title')]"));
	}
	
	public EnterpriseInviteEmployeePage validateInviteEmployeePageIsVisible() {
		LOGGER.info("Validate Employee link is not visible");
		Assert.assertTrue(isElementVisible(getInviteEmployeeMessageLink()),"Invite Client page is not visible.");
	    return this;
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class, 'toast-close-button')]");
	}
	
	public String validateEmployeeInviteToaster(String expectedMessage,String email) {
		LOGGER.info("Validating toaster message");
		InviteEmployeeDetails employeeDeatils = TestDataGenerator.generateInviteEmployeeDetails();
		String actualMessage = getText(getAlertElement());
		if(!actualMessage.equals(expectedMessage)) {
			click(getToasterCloseButton());
			setEmailAddressInput(employeeDeatils.getEmailAddress());
			clickSendInvitationButton();
			actualMessage = getText(getAlertElement());
			email = employeeDeatils.getEmailAddress();
		}
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		click(getToasterCloseButton());
		return email;
	}
	
	private By getInvitedEmployeeLink(String name,String email) {
		return By.xpath("//p-table[contains(@class,'empolyees')]//span[contains(text(),'"+name+"')]/../..//following-sibling::td[1]//span[contains(text(),'"+email.toLowerCase()+"')]");
	}
	
	public EnterpriseInviteEmployeePage validateEmployeeInvitation(InviteEmployeeDetails inviteEmployeeDetails) {
		LOGGER.info("Validating user has been invited successfully");
		alertsAndMessagesPage.validateToaster(ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue());
		new ThriveHeaderMenuPage().navigateToUsers().clickEmployee().clickInvitedEmployees().setSearch(inviteEmployeeDetails.getFirstName());
	    Assert.assertTrue(isElementPresent(getInvitedEmployeeLink(inviteEmployeeDetails.getFirstName(),inviteEmployeeDetails.getEmailAddress())),"Emplyoee is not present in invited employees");
	    return this;
	}
}