package com.thrive.ui.test.user_management;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
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

public class UserManagementCoachRegisterErrorValidationTest extends UserManagementCommonPage{
	

	RegisterCoachEmploymentPage registerCoachEmploymentPage;
	RegisterCoachSkillsAndQualificationPage registerCoachSkillsAndQualificationPage;
	RegisterCoachCorporateExpPage registerCoachCorporateExpPage;
	RegisterCoachMentoringExpPage registerCoachMentoringExpPage;
	RegisterCoachProfileForClients registerCoachAboutPage;
	RegisterCoachPersonalDetailsPage registerCoachPersonalDetailsPage = new RegisterCoachPersonalDetailsPage();
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
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	ThriveLoginPage thriveLoginPage;
	AlertsAndMessagesPage toasterAlert;
	
	
	@Test(description="Register global coach personal details error validation")
	public void registerGlobalCoachPersonalDetailsErrorValidation() {
		
		globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		loginDetailsCoach = new LoginDetails(globalCoachInviteDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();
		toasterAlert = new AlertsAndMessagesPage();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderMenuPage = thriveLoginPage.login(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Send global coach invite");
		thriveHeaderMenuPage.clickUserManagementLink()
		.clickCoachesLink().clickInviteCoachDepricated().sendGlobalCoachInvite(globalCoachInviteDetails);
		inviteCoachPage.validateCoachInviteToaster(ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue(),globalCoachInviteDetails.getEmailAddress());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchCoachMailAndClick(globalCoachInviteDetails.getFirstName());
			
		getExtentTestLogger().log(Status.PASS, "Click on next Button");
		registerCoachPersonalDetailsPage.clickNext();
	
		getExtentTestLogger().log(Status.PASS, "Validate Personal Deatils Error Message validation");
		registerCoachPersonalDetailsPage.ValidateCoachPersonalDeatilsErrorValidation();
	}
	
	
	@Test(description="Register global coach Employment error validation",enabled=false)
	public void registerGlobalCoachemploymentErrorValidation() {
		
		globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		loginDetailsCoach = new LoginDetails(globalCoachInviteDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();
		toasterAlert = new AlertsAndMessagesPage();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderMenuPage = thriveLoginPage.login(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Send global coach invite");
		thriveHeaderMenuPage.clickUserManagementLink()
		.clickCoachesLink().clickInviteCoachDepricated().sendGlobalCoachInvite(globalCoachInviteDetails);
		inviteCoachPage.validateCoachInviteToaster(ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue(),globalCoachInviteDetails.getEmailAddress());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchCoachMailAndClick(globalCoachInviteDetails.getFirstName());
			
		getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
		new RegisterCoachPersonalDetailsPage().submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());
		
		getExtentTestLogger().log(Status.PASS, "Click on next button");
		registerCoachEmploymentPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Submit coach qualification  details");
		registerCoachEmploymentPage.validateEmploymentErrorvalidation();
		
	}
	
	
	@Test(description="Register global coach language error validation",enabled=false)
	public void registerGlobalCoachLangugaeErrorValidation() {
		
		globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		loginDetailsCoach = new LoginDetails(globalCoachInviteDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();
		toasterAlert = new AlertsAndMessagesPage();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderMenuPage = thriveLoginPage.login(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Send global coach invite");
		thriveHeaderMenuPage.clickUserManagementLink()
		.clickCoachesLink().clickInviteCoachDepricated().sendGlobalCoachInvite(globalCoachInviteDetails);
		inviteCoachPage.validateCoachInviteToaster(ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue(),globalCoachInviteDetails.getEmailAddress());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchCoachMailAndClick(globalCoachInviteDetails.getFirstName());
			
		getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
		registerCoachPersonalDetailsPage.submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());
		
		getExtentTestLogger().log(Status.PASS, "Submit coach employment  details");
		registerCoachSkillsAndQualificationPage = registerCoachEmploymentPage
				.submitCoachEmploymentDetails(TestDataGenerator.generateCoachEmploymentDetails());
		
		getExtentTestLogger().log(Status.PASS, "Click on next button");
		registerCoachEmploymentPage.clickNext();
		
		getExtentTestLogger().log(Status.PASS, "Submit coach qualification  details");
		registerCoachEmploymentPage.validateLangugaesErrorvalidation();	
	}
	
	
	@Test(description="Register global coach skills and qulaification error message validation",enabled=false)
	public void registerGlobalCoachSkillsandQualificationErrorValidation() {
		
		globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		loginDetailsCoach = new LoginDetails(globalCoachInviteDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();
		toasterAlert = new AlertsAndMessagesPage();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderMenuPage = thriveLoginPage.login(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Send global coach invite");
		thriveHeaderMenuPage.clickUserManagementLink()
		.clickCoachesLink().clickInviteCoachDepricated().sendGlobalCoachInvite(globalCoachInviteDetails);
		inviteCoachPage.validateCoachInviteToaster(ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue(),globalCoachInviteDetails.getEmailAddress());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchCoachMailAndClick(globalCoachInviteDetails.getFirstName());
			
		getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
		new RegisterCoachPersonalDetailsPage().submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());
		
		getExtentTestLogger().log(Status.PASS, "Submit coach employment  details");
		registerCoachSkillsAndQualificationPage = registerCoachEmploymentPage.submitCoachEmploymentDetails(TestDataGenerator.generateCoachEmploymentDetails());
		
		getExtentTestLogger().log(Status.PASS, "Click on the next button");
		registerCoachSkillsAndQualificationPage.setQualification(ThriveAppSharedData.QUALIFICATION.getValue()).clickNext();
		
		getExtentTestLogger().log(Status.PASS, "Validate Coach skills and qualification error validation");
		registerCoachSkillsAndQualificationPage.validateCoachSkillsAndQualficationsErrorMsg();
	}
	
	
	@Test(description="Register global coach corporate expertise error validation",enabled=false)
	public void registerGlobalCoachCrporateExpertiseErrorValidation() {
		
		globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		loginDetailsCoach = new LoginDetails(globalCoachInviteDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();
		toasterAlert = new AlertsAndMessagesPage();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderMenuPage = thriveLoginPage.login(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Send global coach invite");
		thriveHeaderMenuPage.clickUserManagementLink()
		.clickCoachesLink().clickInviteCoachDepricated().sendGlobalCoachInvitation(globalCoachInviteDetails);
		inviteCoachPage.validateCoachInviteToaster(ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue(),globalCoachInviteDetails.getEmailAddress());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchCoachMailAndClick(globalCoachInviteDetails.getFirstName());
			
		getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
		new RegisterCoachPersonalDetailsPage().submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());
		
		getExtentTestLogger().log(Status.PASS, "Submit coach employment  details");
		registerCoachSkillsAndQualificationPage = registerCoachEmploymentPage.submitCoachEmploymentDetails(TestDataGenerator.generateCoachEmploymentDetails());
		
		getExtentTestLogger().log(Status.PASS, "Submit coach qualification  details");
		registerCoachCorporateExpPage = registerCoachSkillsAndQualificationPage.submitCoachSkillAndQualificationDetails(TestDataGenerator.generateGlobalCoachSkillAndQualificationDetails());
		
		getExtentTestLogger().log(Status.PASS, "Submit coach languages");
		registerCoachMentoringExpPage = registerCoachLanguagesPageRm.submitLanguagesDetails(language);	
		
		getExtentTestLogger().log(Status.PASS, "Click on next button");
		registerCoachCorporateExpPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Submit coach qualification  details");
		registerCoachCorporateExpPage.validateCoachCorporateExpErrorValidation();
		
	}
	
	
	@Test(description="Register global coach mentoring experience error validation")
	public void registerGlobalCoachMentoringExperienceErrorValidation() {
		
		globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		loginDetailsCoach = new LoginDetails(globalCoachInviteDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();
		toasterAlert = new AlertsAndMessagesPage();
		
		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderMenuPage = thriveLoginPage.login(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Send global coach invite");
		thriveHeaderMenuPage.clickUserManagementLink()
		.clickCoachesLink().clickInviteCoachDepricated().sendGlobalCoachInvite(globalCoachInviteDetails);
		inviteCoachPage.validateCoachInviteToaster(ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue(),globalCoachInviteDetails.getEmailAddress());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchCoachMailAndClick(globalCoachInviteDetails.getFirstName());
			
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			
			getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
			new RegisterCoachPersonalDetailsPage().submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());
			
//			getExtentTestLogger().log(Status.PASS, "Submit coach employment  details");
//			registerCoachSkillsAndQualificationPage = registerCoachEmploymentPage.submitCoachEmploymentDetails(TestDataGenerator.generateCoachEmploymentDetails());
//			
//			getExtentTestLogger().log(Status.PASS, "Submit coach qualification  details");
//			registerCoachCorporateExpPage = registerCoachSkillsAndQualificationPage.submitCoachSkillAndQualificationDetails(TestDataGenerator.generateGlobalCoachSkillAndQualificationDetails());
//			
//			getExtentTestLogger().log(Status.PASS, "Submit coach corporate  details");
//			registerCoachMentoringExpPage = registerCoachCorporateExpPage.submitCoachCorporateExpDetails(TestDataGenerator.generateCoachCorporateDetailsDetails());
		
		}else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			
			getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
			registerCoachLanguagesPageRm = new RegisterCoachPersonalDetailsPage()
					.submitCoachPersonalDetailsRm(TestDataGenerator.generateGlobalCoachPersonalDetails());

			getExtentTestLogger().log(Status.PASS, "Submit coach languages");
			registerCoachMentoringExpPage = registerCoachLanguagesPageRm.submitLanguagesDetails(language);
		}
		
		getExtentTestLogger().log(Status.PASS, "Click on next button");
		registerCoachMentoringExpPage = new	RegisterCoachMentoringExpPage().clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Valiadate coach mentoring experience error validation");
		registerCoachMentoringExpPage.validateMentoringExperienceErrorValidation();
	}
	
	
	@Test(description="Register global coach about error validation")
	public void registerGlobalCoachAboutErrorValidation() {
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();

		globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		loginDetailsCoach = new LoginDetails(globalCoachInviteDetails.getEmailAddress(),
				ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveLoginPage = new ThriveLoginPage();

		toasterAlert = new AlertsAndMessagesPage();

		getExtentTestLogger().log(Status.PASS, "Login with SA User");
		thriveHeaderMenuPage = thriveLoginPage.login(loginDetailsSA);

		getExtentTestLogger().log(Status.PASS, "Send global coach invite");
		thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInviteCoachDepricated()
				.sendGlobalCoachInvite(globalCoachInviteDetails);
		inviteCoachPage.validateCoachInviteToaster(coachInviteToaster,globalCoachInviteDetails.getEmailAddress());
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS,
				"Open mailbox and search invitation email and click link to open registration form");
		mailboxPage = new MailboxPage().searchCoachMailAndClick(globalCoachInviteDetails.getFirstName());

		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {

			getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
			new RegisterCoachPersonalDetailsPage()
					.submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());

//			getExtentTestLogger().log(Status.PASS, "Submit coach employment  details");
//			registerCoachSkillsAndQualificationPage = registerCoachEmploymentPage
//					.submitCoachEmploymentDetails(TestDataGenerator.generateCoachEmploymentDetails());

//			getExtentTestLogger().log(Status.PASS, "Submit coach qualification  details");
//			registerCoachCorporateExpPage = registerCoachSkillsAndQualificationPage
//					.submitCoachSkillAndQualificationDetails(
//							TestDataGenerator.generateGlobalCoachSkillAndQualificationDetails());

//			getExtentTestLogger().log(Status.PASS, "Submit coach corporate  details");
//			registerCoachMentoringExpPage = registerCoachCorporateExpPage
//					.submitCoachCorporateExpDetails(TestDataGenerator.generateCoachCorporateDetailsDetails());

		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {

			getExtentTestLogger().log(Status.PASS, "Submit coach personal details");
			registerCoachLanguagesPageRm = new RegisterCoachPersonalDetailsPage()
					.submitCoachPersonalDetailsRm(TestDataGenerator.generateGlobalCoachPersonalDetails());

			getExtentTestLogger().log(Status.PASS, "Submit coach languages");
			registerCoachMentoringExpPage = registerCoachLanguagesPageRm.submitLanguagesDetails(language);	
		}
		
		getExtentTestLogger().log(Status.PASS, "Submit coaching experience  details");
		registerCoachAboutPage = new RegisterCoachMentoringExpPage()
				.submitCoachingExpDetails(TestDataGenerator.generateCoachingExpDetails(true));
		
		getExtentTestLogger().log(Status.PASS, "Click on Register button");
		registerCoachAboutPage.clickregisterButton();
		
		getExtentTestLogger().log(Status.PASS, "Valiadate coach about error validation");
		registerCoachAboutPage.validateAboutPageErrorValidation();
		
	}

	
	

	
	

}