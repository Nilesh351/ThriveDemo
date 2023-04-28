package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.pojos.user_details.AccountManagerPersonalDetails;
import com.thrive.common.testdata.utils.MockDataGenerator;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;

public class RegisterAccountManagerTest extends BaseTestPage {
	
	ThriveHeaderMenuPage thriveHeaderPage;
	LoginDetails loginDetails;
	LoginDetails accountManagerLogin;
	MailboxPage mailboxPage;
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	AlertsAndMessagesPage toasterAlert;
	InviteAccountManagerPage inviteAccountManagerPage;
	InviteAccountManagerDetails inviteAccountManagerDetails;
	RegisterEnterpriseDetailsPage registerEnterpriseDetailsPage;
	RegisterAccountMangerPersonalDetailsPage registerAccountMangerPersonalDetailsPage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	String accountmanagertoaster = ThriveAppSharedData.ACCOUNT_MANAGER_REGISTER_MESSAGE.getValue();
	String subjectWelcome = ThriveAppSharedData.ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT.getValue();
	String subjectConfirmation = ThriveAppSharedData.ACCOUNT_MGR_ACNT_VERIFICATION_MESSAGE.getValue();
	String aboutMessage = "Test About -" + MockDataGenerator.generateSentence();
	
	
	@Test
	public void registerAccountManager() {
		String accountManagerInviteToaster = ThriveAppSharedData.INVITE_ACCOUNT_MANAGER_NOTIFICATION.getValue();
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_REGISTER.getValue());
		
		toasterAlert = new AlertsAndMessagesPage();
		inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();
		AccountManagerPersonalDetails accountManagerPersonalDetails = TestDataGenerator.generateAccountManagerPersonalDetails();
		loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);

		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPage = new ThriveLoginPage().login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite Enterprise");
		inviteAccountManagerPage = thriveHeaderPage.clickUserManagementLink().clickAccountManagersLink().selectInviteAccountManager();

		getExtentTestLogger().log(Status.PASS, "Enter Details for invite Account Manager and send invite");
		inviteAccountManagerPage.sendInvite(inviteAccountManagerDetails);
		//inviteAccountManagerPage.enterInviteAmDeatils();
		String email = inviteAccountManagerPage.validateAMInviteToaster(accountManagerInviteToaster,inviteAccountManagerDetails.getEmailAddress());
		thriveHeaderPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchAMMailAndClick(inviteAccountManagerDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter Account Manager Personal  information");
		registerAccountMangerPersonalDetailsPage = new RegisterAccountMangerPersonalDetailsPage().submitAccountMgrDetails(accountManagerPersonalDetails);

		getExtentTestLogger().log(Status.PASS, "Validate toaster message and close");
		registerAccountMangerPersonalDetailsPage.toasterMessageValidation(ThriveAppSharedData.ACCOUNT_MANAGER_REGISTER_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login with newly registerd Account Manager and validate login successful");
		launchThriveApp(Config.getLoginPageURL());
		accountManagerLogin = new LoginDetails(email, ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(accountManagerLogin).validateLoginSuccessful();
			
	}
	
}