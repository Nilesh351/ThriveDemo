package com.thrive.ui.test.user_management;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.pojos.user_details.ClientEmploymentDetails;
import com.thrive.common.testdata.pojos.user_details.ClientPersonalDetails;
import com.thrive.common.testdata.utils.MockDataGenerator;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterClientRegistrationDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisePage;

public class UserManagementClientRegisterErrorValidationTest extends UserManagementCommonPage{
	
	ThriveLoginPage mythrivelogin = new ThriveLoginPage();
	InviteClientPage internalclient = new InviteClientPage();
	EnterprisePage entpage = new EnterprisePage();
	ClientsPage entclient = new ClientsPage();
	ThriveHeaderMenuPage thriveHeaderPag;
	RegisterClientRegistrationDetailsPage regDetailsPage;
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	RegisterClientInformationPage registerClientInformationPage = new RegisterClientInformationPage();
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

	
	@Test(description="Register client personal deatils error validation")
	public void registerClientPersonalDeatilsErrorValidation() {
		
		InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPag = new ThriveLoginPage().login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite client");
		inviteClientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInviteClient();
		
		getExtentTestLogger().log(Status.PASS, "Enter client invite details and send invite");
		inviteClientPage.sendInvite(inviteClientDetails);
		inviteClientPage.validateClientInviteToaster(clientInviteToaster,inviteClientDetails.getEmailAddress());
		thriveHeaderPag.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchClientMailAndClick(inviteClientDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Click on nect button");
		registerClientInformationPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise personal details validation");
		registerClientInformationPage.validatePersonalDetailsErrorValidation();
		
	}
	
	@Test(description="Register client Employment error validation")
	public void registerClientEmploymentErrorValidation() {
		
		InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
		ClientPersonalDetails clientPersonalDetails = TestDataGenerator.generateClientPersonalDetails();
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPag = new ThriveLoginPage().login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite client");
		inviteClientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInviteClient();
		
		getExtentTestLogger().log(Status.PASS, "Enter client invite details and send invite");
		inviteClientPage.sendInvite(inviteClientDetails);
		inviteClientPage.validateClientInviteToaster(clientInviteToaster,inviteClientDetails.getEmailAddress());
		thriveHeaderPag.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchClientMailAndClick(inviteClientDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter EA information");
		empStatusPage = registerClientInformationPage.submitPersonalDetails(clientPersonalDetails);
		
		getExtentTestLogger().log(Status.PASS, "validate employment error validation");
		empStatusPage.validateEmploymentErrorValidation();
	
	}
	
	@Test(description="Register client Career error validation")
	public void registerClientCareerErrorValidation() {
		
		InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
		ClientPersonalDetails clientPersonalDetails = TestDataGenerator.generateClientPersonalDetails();
		ClientEmploymentDetails clientEmploymentDetails = TestDataGenerator.generateClientEmploymentDetails();
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPag = new ThriveLoginPage().login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite client");
		inviteClientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInviteClient();
		
		getExtentTestLogger().log(Status.PASS, "Enter client invite details and send invite");
		inviteClientPage.sendInvite(inviteClientDetails);
		inviteClientPage.validateClientInviteToaster(clientInviteToaster,inviteClientDetails.getEmailAddress());
		thriveHeaderPag.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchClientMailAndClick(inviteClientDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter EA information");
		empStatusPage = registerClientInformationPage.submitPersonalDetails(clientPersonalDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enter EA employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(clientEmploymentDetails);
		
		getExtentTestLogger().log(Status.PASS, "Click on the register button");
		careerDetailsPage.clickRegisterButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate carrer error validation");
		careerDetailsPage.validateCareerErrorValidation();
		
	}
	
	

}