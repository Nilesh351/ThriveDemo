package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.register.RegisterCoachProfileForClients;
import com.thrive.ui.page.register.RegisterCoachCorporateExpPage;
import com.thrive.ui.page.register.RegisterCoachEmploymentPage;
import com.thrive.ui.page.register.RegisterCoachLanguagesPageRm;
import com.thrive.ui.page.register.RegisterCoachMentoringExpPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterCoachSkillsAndQualificationPage;

public class RegisterCoachTest extends BaseTestPage {

	ThriveHeaderMenuPage thriveHeaderPag;
	RegisterCoachEmploymentPage registerCoachEmploymentPage;
	RegisterCoachSkillsAndQualificationPage registerCoachSkillsAndQualificationPage;
	RegisterCoachCorporateExpPage registerCoachCorporateExpPage;
	RegisterCoachMentoringExpPage registerCoachMentoringExpPage;
	RegisterCoachProfileForClients registerCoachAboutPage;
	LoginDetails loginDetailsSA = new LoginDetails(superAdminUser, superAdminUserPassword);
	CoachInviteDetails globalCoachInviteDetails;
	RegisterCoachLanguagesPageRm registerCoachLanguagesPageRm;
	LoginDetails loginDetailsCoach;
	String subjectWelcome = ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT.getValue();
	String subjectCoachRegConfirmation = ThriveAppSharedData.COACH_REGISTRATION_CONFIRMATION.getValue();
	String subjectNewCoachOnBoard = ThriveAppSharedData.NEW_COACH_ON_BOARD.getValue();
	String subjectVerfication = null;
	String yearsascoach = "10";
	MailboxPage mailboxPage;
	InviteCoachPage inviteCoachPage = new InviteCoachPage();
	String language = ThriveAppSharedData.LANGUAGE_ENGLISH.getValue();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage thriveLoginPage;
	AlertsAndMessagesPage toasterAlert;

	@Test
	public void registerGlobalCoach() {
		String tosterMessage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		LoginDetails coachLoginDetails;

		globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		loginDetailsCoach = new LoginDetails(globalCoachInviteDetails.getEmailAddress(),
				ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();

		toasterAlert = new AlertsAndMessagesPage();

		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_REGISTER.getValue());

		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPag = thriveLoginPage.login(loginDetailsSA);

		getExtentTestLogger().log(Status.PASS, "Send global coach invite");
		thriveHeaderPag.clickUserManagementLink().clickCoachesLink().clickInviteCoachDepricated()
				.sendGlobalCoachInvite(globalCoachInviteDetails);
		String email = inviteCoachPage.validateCoachInviteToaster(coachInviteToaster,
				globalCoachInviteDetails.getEmailAddress());
		thriveHeaderPag.logout();

		getExtentTestLogger().log(Status.PASS,
				"Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchCoachMailAndClick(globalCoachInviteDetails.getFirstName());

		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {

			getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
			registerCoachMentoringExpPage = new RegisterCoachPersonalDetailsPage()
					.submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {

			getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
			registerCoachLanguagesPageRm = new RegisterCoachPersonalDetailsPage()
					.submitCoachPersonalDetailsRm(TestDataGenerator.generateGlobalCoachPersonalDetails());

			getExtentTestLogger().log(Status.PASS, "Submit coach languages");
			registerCoachMentoringExpPage = registerCoachLanguagesPageRm.submitLanguagesDetails(language);
		}

		getExtentTestLogger().log(Status.PASS, "Submit coaching experience  details");
		registerCoachAboutPage = registerCoachMentoringExpPage
				.submitCoachingExpDetails(TestDataGenerator.generateCoachingExpDetails(true));

		getExtentTestLogger().log(Status.PASS, "Submit coach about  details");
		registerCoachAboutPage.submitCoachAboutInfo(TestDataGenerator.generateCoachAboutDetails(), yearsascoach);
		toasterAlert.validateToaster(ThriveAppSharedData.COACH_REGISTER_MESSAGE.getValue());

		getExtentTestLogger().log(Status.PASS, "Login by SA and validate global coach");
		thriveLoginPage.loginIgnoreAccept(loginDetailsSA).clickUserManagementLink().clickCoachesLink()
				.searchAndClickCoach(email.toLowerCase()).clickEnable().ClickYes().validateToaster(tosterMessage);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login with newly registerd coach and validate login successful");
		launchThriveApp(Config.getLoginPageURL());
		coachLoginDetails = new LoginDetails(email, ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(coachLoginDetails).validateLoginSuccessful();

	}

	@Test
	public void registerInternalCoach() {
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		String tosterMessage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();
		LoginDetails intCoachLoginDetails;

		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_REGISTER.getValue());

		CoachInviteDetails internalCoachInviteDetails = TestDataGenerator.generateInternalCoachInviteDetails(false);
		loginDetailsCoach = new LoginDetails(internalCoachInviteDetails.getEmailAddress(),
				ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();
		toasterAlert = new AlertsAndMessagesPage();

		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderPag = thriveLoginPage.login(loginDetailsSA);

		getExtentTestLogger().log(Status.PASS, "Send Internal coach invite");
		thriveHeaderPag.clickUserManagementLink().clickInternalCoachesLink().clickInviteCoachDepricated()
				.sendInternalCoachInvite(internalCoachInviteDetails);

		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,
				internalCoachInviteDetails.getEmailAddress());
		thriveHeaderPag.logout();

		getExtentTestLogger().log(Status.PASS,
				"Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchInternalCoachMailAndClick(internalCoachInviteDetails.getFirstName());

		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {

			getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
			registerCoachMentoringExpPage = new RegisterCoachPersonalDetailsPage()
					.submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {

			getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
			registerCoachLanguagesPageRm = new RegisterCoachPersonalDetailsPage()
					.submitCoachPersonalDetailsRm(TestDataGenerator.generateGlobalCoachPersonalDetails());

			getExtentTestLogger().log(Status.PASS, "Submit coach languages");
			registerCoachMentoringExpPage = registerCoachLanguagesPageRm.submitLanguagesDetails(language);
		}

		getExtentTestLogger().log(Status.PASS, "Submit coaching experience  details");
		registerCoachAboutPage = registerCoachMentoringExpPage
				.submitCoachingExpDetails(TestDataGenerator.generateCoachingExpDetails(false));

		getExtentTestLogger().log(Status.PASS, "Submit coach about  details");
		registerCoachAboutPage.submitCoachAboutInfo(TestDataGenerator.generateCoachAboutDetails(), yearsascoach);
		toasterAlert.validateToaster(ThriveAppSharedData.COACH_REGISTER_MESSAGE.getValue());

		getExtentTestLogger().log(Status.PASS, "Login by SA and validate global coach");
		thriveLoginPage.loginIgnoreAccept(loginDetailsSA).clickUserManagementLink().clickInternalCoachesLink()
				.searchAndClickCoach(email.toLowerCase()).clickEnable().ClickYes().validateToaster(tosterMessage);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login with newly registerd Internal coach and validate login successful");
		launchThriveApp(Config.getLoginPageURL());
		intCoachLoginDetails = new LoginDetails(email, ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(intCoachLoginDetails).validateLoginSuccessful();

	}

}
