package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.pojos.user_details.ClientEmploymentDetails;
import com.thrive.common.testdata.pojos.user_details.ClientPersonalDetails;
import com.thrive.common.testdata.pojos.user_details.EnterpriseDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;

public class RegisterEnterpriseTest extends BaseTestPage {

	ThriveHeaderMenuPage thriveHeaderPage;
	LoginDetails loginDetails;
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
	String subjectWelcome = ThriveAppSharedData.ENT_WELCOME_EMAIL_SUBJECT.getValue();
	String subjectConfirmation = ThriveAppSharedData.CLIENT_REG_CONFIRM_EMAIL_SUBJECT.getValue();

	@Test
	public void registerEnterprise() {
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();

		toasterAlert = new AlertsAndMessagesPage();
		ClientPersonalDetails clientPersonalDetails = TestDataGenerator.generateClientPersonalDetails();
		EnterpriseDetails enterpriseDetails = TestDataGenerator.generateEnterpriseDetails();
		inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
		ClientEmploymentDetails clientEmploymentDetails = TestDataGenerator.generateClientEmploymentDetails();
		loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);

		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_REGISTER.getValue());

		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPage = new ThriveLoginPage().login(loginDetails);

		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite Enterprise");
		inviteEnterprisePage = thriveHeaderPage.clickUserManagementLink().clickEnterprisesMenu()
				.clickInviteEnterprise();

		getExtentTestLogger().log(Status.PASS, "Enter Details for invite Enterprise");
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		String email = inviteEnterprisePage.validateEntInviteToaster(entInviteToaster,
				inviteEnterpriseDetails.getEmailAddress());
		thriveHeaderPage.logout();

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage = new MailboxPage().searchEnterpriseMailAndClick(inviteEnterpriseDetails.getFirstName());

		getExtentTestLogger().log(Status.PASS, "Enter Enterprise  Details");
		registerClientInformationPage = new RegisterEnterpriseDetailsPage().submitEnterpriseDetails(enterpriseDetails);

		getExtentTestLogger().log(Status.PASS, "Enter EA Personal Details");
		empStatusPage = registerClientInformationPage.submitPersonalDetails(clientPersonalDetails);

		getExtentTestLogger().log(Status.PASS, "Enter EA epmployment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(clientEmploymentDetails);

		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
				.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());

		getExtentTestLogger().log(Status.PASS, "Login with newly registerd client and validate login successful");
		launchThriveApp(Config.getLoginPageURL());
		enterpriseLogin = new LoginDetails(email, ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(enterpriseLogin).validateLoginSuccessful();

	}

}
