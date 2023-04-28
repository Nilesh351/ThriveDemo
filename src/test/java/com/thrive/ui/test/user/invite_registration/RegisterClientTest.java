package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
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
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterClientRegistrationDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisePage;

public class RegisterClientTest extends BaseTestPage {
	
	ThriveLoginPage mythrivelogin = new ThriveLoginPage();
	InviteClientPage internalclient = new InviteClientPage();
	EnterprisePage entpage = new EnterprisePage();
	ClientsPage entclient = new ClientsPage();
	ThriveHeaderMenuPage thriveHeaderPag;
	RegisterClientRegistrationDetailsPage regDetailsPage;
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	InviteClientPage inviteClientPage = new InviteClientPage();
	MailboxPage mailboxPage;
	LoginDetails clientLoginDetails;
	AlertsAndMessagesPage toasterAlert = new AlertsAndMessagesPage();
	
	LoginDetails loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	String subjectWelcome = ThriveAppSharedData.ENT_WELCOME_EMAIL_SUBJECT.getValue();
	String welcomeSubject = ThriveAppSharedData.CLIENT_WELCOME_EMAIL.getValue();
	String subjectConfirmation = ThriveAppSharedData.CLIENT_REG_CONFIRM_EMAIL_SUBJECT.getValue();
	String aboutMessage = "Test About -" + MockDataGenerator.generateSentence();

	
	@Test
	public void registerClient() {
		
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_REGISTER.getValue());

		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPag = new ThriveLoginPage().login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite client");
		inviteClientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().selectInviteClientAction();
		
		getExtentTestLogger().log(Status.PASS, "Enter client invite details and send invite");
		inviteClientPage.sendInvite(inviteClientDetails);
		String email = inviteClientPage.validateClientInviteToaster(clientInviteToaster,inviteClientDetails.getEmailAddress());
		thriveHeaderPag.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchClientMailAndClick(inviteClientDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter client information");
		empStatusPage = new RegisterClientInformationPage().submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());
		
		getExtentTestLogger().log(Status.PASS, "Enter client employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());
		
		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
		.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login with newly registerd client and validate login successful");
		
		launchThriveApp(Config.getLoginPageURL());
		clientLoginDetails = new LoginDetails(email, ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(clientLoginDetails).validateLoginSuccessful();
			
	}
	

}
