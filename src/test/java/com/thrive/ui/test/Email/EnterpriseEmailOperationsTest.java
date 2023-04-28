package com.thrive.ui.test.Email;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.pojos.user_details.ClientEmploymentDetails;
import com.thrive.common.testdata.pojos.user_details.ClientPersonalDetails;
import com.thrive.common.testdata.pojos.user_details.EnterpriseDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.register.ChangeEmailAddressPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;


public class EnterpriseEmailOperationsTest extends UserManagementCommonPage{
	ThriveHeaderMenuPage thriveHeaderPage;
	LoginDetails enterpriseLogin;
	MailboxPage mailboxPage;
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	AlertsAndMessagesPage toasterAlert;
	InviteEnterprisePage inviteEnterprisePage;
	InviteEnterpriseDetails inviteEnterpriseDetails;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	RegisterEnterpriseDetailsPage registerEnterpriseDetailsPage;
	RegisterClientInformationPage registerClientInformationPage;
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaMutableEmail, eaMutablePassword);
	ThriveLoginPage login = new ThriveLoginPage();
	ChangeEmailAddressPage changeEmailAddressPage = new ChangeEmailAddressPage();
	
	
	@Test(description = "Validate EA update email successfully")
	public void validateEAUpdateEmail() {
		
		toasterAlert = new AlertsAndMessagesPage();
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();
		ClientPersonalDetails clientPersonalDetails = TestDataGenerator.generateClientPersonalDetails();
		EnterpriseDetails enterpriseDetails = TestDataGenerator.generateEnterpriseDetails();
		inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
		ClientEmploymentDetails clientEmploymentDetails = TestDataGenerator.generateClientEmploymentDetails();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite Enterprise");
		inviteEnterprisePage = thriveHeaderPage.clickUserManagementLink().clickEnterprisesMenu().clickInviteEnterprise();

		getExtentTestLogger().log(Status.PASS, "Enter Details for invite Enterprise");
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		String email =inviteEnterprisePage.validateEntInviteToaster(entInviteToaster,inviteEnterpriseDetails.getEmailAddress());
		thriveHeaderPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchEnterpriseMailAndClick(inviteEnterpriseDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter Enterprise  Details and register");
		new RegisterEnterpriseDetailsPage().submitEnterpriseDetails(enterpriseDetails).submitPersonalDetails(clientPersonalDetails)
		.submitEmploymentDetails(clientEmploymentDetails).registerClientInfo()
		.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		enterpriseLogin = new LoginDetails(email, ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveHeaderPage = login.loginIgnoreAccept(enterpriseLogin);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin click on Myprofile link");
		registerClientInformationPage = thriveHeaderPage.clickClientMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin click on change email address link");
		String firstName = registerClientInformationPage.firstNameValue();
		changeEmailAddressPage = registerClientInformationPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provide email address to change eamil");
		inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
		changeEmailAddressPage.sendEmailDetails(inviteEnterpriseDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Validate Enterprise admin email change toaster");
		alertsAndMessagesPage.validateToaster(ThriveAppSharedData.EMAIL_CHANGED_TOASTER.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and verify the account");
		mailboxPage =  new MailboxPage().searchVerifiedMailAndClick(firstName);
		
		getExtentTestLogger().log(Status.PASS, "Login With new email address");
		enterpriseLogin = new LoginDetails(inviteEnterpriseDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		login.clickLogin().loginIgnoreAccept(enterpriseLogin);
		thriveHeaderPage.validateLoginSuccessful();
		
	}
	
	
	@Test(description = "Validate Enterprise admin update email address error validation")
	public void validateEAUpdateEmailErrorValidation() {
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin click on Myprofile link");
		registerClientInformationPage = thriveHeaderPage.clickClientMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin click on change email address link");
		changeEmailAddressPage = registerClientInformationPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provide email address to change eamil");
		inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
		changeEmailAddressPage.validateEmailDeatils(inviteEnterpriseDetails.getEmailAddress(),eaMutableEmail);
		
		getExtentTestLogger().log(Status.PASS, "Validate email update error message present");
		changeEmailAddressPage.validateEmailUpdateErrorValidation();
	}
	

}
