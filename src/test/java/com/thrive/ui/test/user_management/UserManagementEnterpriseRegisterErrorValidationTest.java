package com.thrive.ui.test.user_management;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;


public class UserManagementEnterpriseRegisterErrorValidationTest extends UserManagementCommonPage{
	
	
	ThriveHeaderMenuPage thriveHeaderPage;
	LoginDetails loginDetails;
	LoginDetails enterpriseLogin;
	MailboxPage mailboxPage = new MailboxPage();
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	AlertsAndMessagesPage toasterAlert;
	InviteEnterprisePage inviteEnterprisePage;
	InviteEnterpriseDetails inviteEnterpriseDetails;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	RegisterEnterpriseDetailsPage registerEnterpriseDetailsPage = new RegisterEnterpriseDetailsPage();
	RegisterClientInformationPage registerClientInformationPage;
	
	
	String subjectWelcome = ThriveAppSharedData.ENT_WELCOME_EMAIL_SUBJECT.getValue();
	String subjectConfirmation = ThriveAppSharedData.CLIENT_REG_CONFIRM_EMAIL_SUBJECT.getValue();
	
	
	@Test(description = "Validate Enterprise register error validation")
	public void registerEnterprise() {
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();

		toasterAlert = new AlertsAndMessagesPage();
		inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
		loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_REGISTER.getValue());

		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPage = new ThriveLoginPage().login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite Enterprise");
		inviteEnterprisePage = thriveHeaderPage.clickUserManagementLink().clickEnterprisesMenu().clickInviteEnterprise();

		getExtentTestLogger().log(Status.PASS, "Enter Details for invite Enterprise");
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		alertsAndMessagesPage.validateToaster(entInviteToaster);
		thriveHeaderPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchEnterpriseMailAndClick(inviteEnterpriseDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Click  save and continue");
		registerEnterpriseDetailsPage.clickSaveAndContinueButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise details error validation");
		registerEnterpriseDetailsPage.valiadateEnterpriseDeatilsErrorValidation();
	
	}
	
	
}