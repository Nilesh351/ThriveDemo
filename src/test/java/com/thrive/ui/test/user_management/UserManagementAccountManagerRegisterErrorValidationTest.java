package com.thrive.ui.test.user_management;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.utils.MockDataGenerator;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;

public class UserManagementAccountManagerRegisterErrorValidationTest extends UserManagementCommonPage{
	
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
	String subjectWelcome = ThriveAppSharedData.ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT.getValue();
	String subjectConfirmation = ThriveAppSharedData.ACCOUNT_MGR_ACNT_VERIFICATION_MESSAGE.getValue();
	String aboutMessage = "Test About -" + MockDataGenerator.generateSentence();
	
	
	
	
	@Test(description="Register Account Manager personal deatils error validation")
	public void registerAccountManagerErrorVslidation() {
		String accountManagerInviteToaster = ThriveAppSharedData.INVITE_ACCOUNT_MANAGER_NOTIFICATION.getValue();
		
		toasterAlert = new AlertsAndMessagesPage();
		inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();
		loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);

		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPage = new ThriveLoginPage().login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite Enterprise");
		inviteAccountManagerPage = thriveHeaderPage.clickUserManagementLink().clickAccountManagersLink().selectInviteAccountManager();

		getExtentTestLogger().log(Status.PASS, "Enter Details for invite Account Manager");
		inviteAccountManagerPage.sendInvite(inviteAccountManagerDetails);
		
		getExtentTestLogger().log(Status.PASS, "Click on send invitation button");
		alertsAndMessagesPage.validateToaster(accountManagerInviteToaster);
		thriveHeaderPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchAMMailAndClick(inviteAccountManagerDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter Account Manager Personal  information");
		registerAccountMangerPersonalDetailsPage = new RegisterAccountMangerPersonalDetailsPage().clickRegisterButton();

		getExtentTestLogger().log(Status.PASS, "Validate for Mandatory field error Message is present");
		registerAccountMangerPersonalDetailsPage.validateAMPersonalDeatilsErrorMsgValidation();
			
	}

}