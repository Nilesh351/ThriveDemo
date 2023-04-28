package com.thrive.ui.test.endtoend;


import java.util.List;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionSummaryDetails;
import com.thrive.common.testdata.pojos.credits.AllocateCredits;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.pojos.user_details.ClientEmploymentDetails;
import com.thrive.common.testdata.pojos.user_details.ClientPersonalDetails;
import com.thrive.common.testdata.pojos.user_details.EnterpriseDetails;
import com.thrive.common.testdata.utils.MockDataGenerator;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.book_session.BookSessionConfirmationPage;
import com.thrive.ui.page.book_session.BookSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionSummaryPage;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachCategoriesPage;
import com.thrive.ui.page.category.EnterpriseConfigureCategoryPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.enterprise.EnterpriseDetailsPage;
import com.thrive.ui.page.invite.EnterpriseInviteEmployeePage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedClientsPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ClientMenuPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

public class EndToEndTest extends BaseTestPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage;
	MailboxPage mailboxPage;
	ClientMenuPage clientMenuPage;
	EnterpriseDetailsPage enterpriseDateilsPage;
	ClientsPage clientPage;
	EnterprisesPage enterprisesPage;
	InviteCoachPage inviteCoachPage;
	GlobalCoachesPage globalCoachesPage;
	EnterpriseConfigureCategoryPage enterpriseConfigureCategoryPage;
	CoachCategoriesPage coachCategoriesPage;
	BookSessionViewPage bookSessionViewPage;
	BookSessionDetailsPage bookSessionDetailsPage;
	SessionsPage sessionsPage;
	BookSessionDetails bookSessionDetailswithoutTime;
	BookSessionSummaryPage bookSessionSummaryPage;
	LoginDetails loginDetails;
	BookSessionSummaryDetails bookSessionSummaryDetails;
	BookSessionConfirmationPage bookSessionConfirmationPage;
	LoginDetails enterpriseLogin;
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	InviteEnterprisePage inviteEnterprisePage;
	EnterpriseInviteEmployeePage enterpriseInviteEmployeePage;
	
	RegisterEnterpriseDetailsPage registerEnterpriseDetailsPage;
	RegisterClientInformationPage registerClientInformationPage;
	
	AllocateCreditsPage allocateCreditsPage=new AllocateCreditsPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	InvitedClientsPage invitedClientsPage = new InvitedClientsPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InviteClientPage inviteClientPage=new InviteClientPage();


	CoachInviteDetails coachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
	InviteEmployeeDetails inviteEmpDetails1 = TestDataGenerator.generateInviteEmployeeDetails();
	InviteEmployeeDetails inviteEmpDetails2 = TestDataGenerator.generateInviteEmployeeDetails();
	AllocateCredits allocatecredits = TestDataGenerator.generateEnterpriseCredits();
	InviteEnterpriseDetails inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();

	String clientWelcomeSubject = ThriveAppSharedData.CLIENT_WELCOME_EMAIL.getValue();
	String globalCoachWelcomeSubject = ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT.getValue();
	String subjectWelcome = ThriveAppSharedData.ENT_WELCOME_EMAIL_SUBJECT.getValue();
	String subjectConfirmation = ThriveAppSharedData.CLIENT_REG_CONFIRM_EMAIL_SUBJECT.getValue();
	String aboutMessage = "Test About -" + MockDataGenerator.generateSentence();
	String sessionStatus = ThriveAppSharedData.END_SESSION.getValue();
	int bookSessionCount = 1;
	String coachGlobalFirstName =  internalCoachName.split("First")[0];
	
	LoginDetails loginDetailsSA = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails loginDetailsEA = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	LoginDetails loginDetailsClient = new LoginDetails(clientUser, clientPassword);
	
	@Test
	public void endToEndTest() {   

		getExtentTestLogger().assignCategory(" End-To-End Test ");
		
		getExtentTestLogger().log(Status.PASS, "SA logs in to application");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsSA);

		getExtentTestLogger().log(Status.PASS, "Invite an enterprise");
		inviteEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "Register enterprise");
		registerEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "Invite clients");
		EAInviteClients();
		
		getExtentTestLogger().log(Status.PASS, "SA assign EA permission to a client");
		SAAssignClientAsEA();
		
		getExtentTestLogger().log(Status.PASS, "SA allocates credits to enterprise");
		SAAllocatesCreditToEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "SA allocates credits to EA");
		SaAllocatesCreditToEA();
		
		getExtentTestLogger().log(Status.PASS, "SA enables global category");
		SaEnableGlobalCategory();
		
		getExtentTestLogger().log(Status.PASS, "SA invite & register global coach");
		SaInviteRegisterAndEnableGlobalCoach();
		
		getExtentTestLogger().log(Status.PASS, "SA assigns category to global coach");
		SaAssignCategegoryToGlobalCoach();
		
		getExtentTestLogger().log(Status.PASS, "EA books single session with global coach");
		EaBookSingleSession();
		
		getExtentTestLogger().log(Status.PASS, "EA joins session with global coach");
		EaJoinSession();
		
		getExtentTestLogger().log(Status.PASS, "Global coach joins session with EA");
		coachJoinSession();
		
		getExtentTestLogger().log(Status.PASS, "Global coach complets session");
		validateSessionCompletedSuccessfully();
		
	}
	
	private EndToEndTest inviteEnterprise() {

		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite Enterprise");
		inviteEnterprisePage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickInviteEnterprise();

		getExtentTestLogger().log(Status.PASS, "Enter Details for invite Enterprise");
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		alertsAndMessagesPage.closeToasterAlert();
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchEnterpriseMailAndClick(inviteEnterpriseDetails.getFirstName());

		return this;
	}
	
	private EndToEndTest registerEnterprise() {
		EnterpriseDetails enterpriseDetails = TestDataGenerator.generateEnterpriseDetails();
		ClientPersonalDetails clientPersonalDetails = TestDataGenerator.generateClientPersonalDetails();
		ClientEmploymentDetails clientEmploymentDetails = TestDataGenerator.generateClientEmploymentDetails();
		
		getExtentTestLogger().log(Status.PASS, "Enter Enterprise  information");
		registerClientInformationPage = new RegisterEnterpriseDetailsPage().submitEnterpriseDetails(enterpriseDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enter EA information");
		empStatusPage = registerClientInformationPage.submitPersonalDetails(clientPersonalDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enter EA employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(clientEmploymentDetails);
		
		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
		.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login with newly registerd client and validate login successful");
		launchThriveApp(Config.getLoginPageURL());
		enterpriseLogin = new LoginDetails(inviteEnterpriseDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(enterpriseLogin).validateLoginSuccessful();
		
		return this;
	}
	
	private EndToEndTest EAInviteClients() {
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Employees");
		enterpriseInviteEmployeePage=thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();
		
		getExtentTestLogger().log(Status.PASS, "Enter client invite details and send invite");
		enterpriseInviteEmployeePage.sendInvite(inviteEmpDetails1);
		
		getExtentTestLogger().log(Status.PASS, "Validate client invitation");
		enterpriseInviteEmployeePage.validateEmployeeInvitation(inviteEmpDetails1);
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Register client");
		clientPage =  new MailboxPage().searchClientMailAndClick(inviteEmpDetails1.getFirstName()).registerClient();

		getExtentTestLogger().log(Status.PASS, "Validate client registration");
		clientPage.validateEmployeeRegistration(inviteEmpDetails1,loginDetailsClient);
		thriveHeaderMenuPage.logout();
		
		return this;
	}
	
	private EndToEndTest SAAssignClientAsEA() {
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise credentials");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Clients -> Clients Personal Details");
		clientMenuPage=thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().setSearchClient(inviteEmpDetails1.getFirstName()).clickClient(inviteEmpDetails1.getFirstName());

		getExtentTestLogger().log(Status.PASS, "Set client as enterprise admin");
		clientMenuPage.clickAdministrator().validateClientSetAsEa(inviteEmpDetails1);
		thriveHeaderMenuPage.logout();
		
		return this;
	}
	
	private EndToEndTest SAAllocatesCreditToEnterprise() {
		
		getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Enterprise");
		enterprisesPage=thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Search enterprise and allocate credits");
		String enterpriseCreditsBeforeAllocation=enterprisesPage.setSearchEnterprise(mutableEnterpirse).captureEnterpriseCredits(eaMutableEmail);
		enterprisesPage.setSearchEnterprise(mutableEnterpirse).clickAllocateCredit(mutableEnterpirse).CreditAllocationDetails(allocatecredits);
		String expectedEntpCreditsAfterAllocation=allocateCreditsPage.expectedCreditAfterAllocation(enterpriseCreditsBeforeAllocation, allocatecredits.getCredits());
		
	    getExtentTestLogger().log(Status.PASS, "Validate credit allocation to enterprise");
	    allocateCreditsPage.verifyCreditAllocationNotification(ThriveAppSharedData.ALLOCATE_CREDIT_NOTIFICATION.getValue());
		refreshPage();
	    String actualEntpCreditsAfterAllocation=enterprisesPage.setSearchEnterprise(mutableEnterpirse).captureEnterpriseCredits(eaMutableEmail);
	    allocateCreditsPage.validateCredits(expectedEntpCreditsAfterAllocation, actualEntpCreditsAfterAllocation);
	    
		return this;
	}
	
	private EndToEndTest SaAllocatesCreditToEA() {
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Clients");
		String clientsCreditsBeforeAllocation=thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().setSearchClient(inviteEmpDetails1.getFirstName()).captureClientsCredits(inviteEmpDetails1.getFirstName());
		clientPage.clickAllocateCredit(inviteEmpDetails1.getFirstName()).CreditAllocationDetails(allocatecredits);
		String clientsExpectedCreditsAfterAllocation=allocateCreditsPage.expectedCreditAfterAllocation(clientsCreditsBeforeAllocation,allocatecredits.getCredits());
		
	
		getExtentTestLogger().log(Status.PASS, "Validate credit allocation to enterprise admin");
		allocateCreditsPage.verifyCreditAllocationNotification(ThriveAppSharedData.ALLOCATE_CREDIT_NOTIFICATION.getValue());
		refreshPage();
		String clientsActualCreditsAfterAllocation=thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().setSearchClient(inviteEmpDetails1.getFirstName()).captureClientsCredits(inviteEmpDetails1.getFirstName());
		allocateCreditsPage.validateCredits(clientsExpectedCreditsAfterAllocation, clientsActualCreditsAfterAllocation);		
		thriveHeaderMenuPage.logout();
		
		return this;
	}
	
	private EndToEndTest SaEnableGlobalCategory() {
		
		getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Enterprise -> Enterprise details -> Configure categories");
		enterpriseConfigureCategoryPage=thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu()
		.setSearchEnterprise(inviteEmpDetails1.getEnterprise()).clickEnterprise(inviteEmpDetails1.getEnterprise()).clickConfigureCategories();

		getExtentTestLogger().log(Status.PASS, "Enable category and validate enabled category");
		enterpriseConfigureCategoryPage.enableGlobalCategory(category).validateEnableCategory(category);
		
		return this;
	}
	
	private EndToEndTest SaInviteRegisterAndEnableGlobalCoach() {
		String tosterMessage= ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Coaches and invite global coach");
		inviteCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInviteCoachDepricated();
		
		getExtentTestLogger().log(Status.PASS, "Enter coach invite details and send invite");
		inviteCoachPage.sendGlobalCoachInvitation(coachInviteDetails);
	
		getExtentTestLogger().log(Status.PASS, "Validate Global Coach invited successfully");
		inviteCoachPage.validateGlobalCoachInvitedSuccessfully(coachInviteDetails);
		thriveHeaderMenuPage.logout();
	
		getExtentTestLogger().log(Status.PASS, "Register coach");
		launchThriveApp(Config.getLoginPageURL());
		globalCoachesPage = new MailboxPage().searchCoachMailAndClick(coachInviteDetails.getFirstName()).registerGlobalCaoch();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach regitration");
		globalCoachesPage.validateGlobalCoachRegistration(coachInviteDetails);
		
		getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Coaches -> Personal Detaisl and Enable coach");
		thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().searchAndClickCoach(coachInviteDetails.getEmailAddress())
		.clickEnable().ClickYes().validateToaster(tosterMessage);
		thriveHeaderMenuPage.logout();

		launchThriveApp(Config.getLoginPageURL());
		loginDetails = new LoginDetails(coachInviteDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(loginDetails).validateLoginSuccessful();
		thriveHeaderMenuPage.logout();
		
		return this;
	}
	
	private EndToEndTest SaAssignCategegoryToGlobalCoach() {
		
		getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Coaches -> Coach details -> Configure categories");
		coachCategoriesPage=thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().searchAndClickCoach(coachInviteDetails.getEmailAddress()).clickCoachCategories();
		
		getExtentTestLogger().log(Status.PASS, "Enable category to the coach and validate category enabled");
		coachCategoriesPage.enableCategoryToCoach(category).clickUpdateButton().validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		thriveHeaderMenuPage.logout();
		
		return this;
	}
	
	List<String> sessionSchedule;
	
	private EndToEndTest EaBookSingleSession() {
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
   		LoginDetails loginDetailsEA = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
   		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsEA);
		
   		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		bookSessionViewPage  = thriveHeaderMenuPage.clickMySessions().clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionDetailsPage=bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides multiple book session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(bookSessionCount);
		sessionSchedule=bookSessionConfirmationPage.sessionDetails();
   		thriveHeaderMenuPage.logout();
   		
   		return this;
	}
	
	String updatedDate;
	
	private EndToEndTest EaJoinSession() {
		
		getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsSA);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions");
		sessionsPage = thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Search Event and update session schedule");
		updatedDate=sessionsPage.updateSessionSchedule(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);
   		thriveHeaderMenuPage.logout();
   		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsEA);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions and join session");
		thriveHeaderMenuPage.clickMySessions().clickSessions().joinSessionWithEALogin(internalExpertise,updatedDate,sessionSchedule.get(1)).validateSessionJoined();
   		thriveHeaderMenuPage.logout();
   	
		return this;
		
	}
	
	private EndToEndTest coachJoinSession() {
		
		getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
		LoginDetails loginDetailsCoach = new LoginDetails(internalCoachUser, internalCoachPassword);
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsCoach);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions and join session");
		thriveHeaderMenuPage.clickMySessions().clickSessions().joinSessionWithCoachLogin(internalExpertise,updatedDate,sessionSchedule.get(1)).validateSessionJoined();

		getExtentTestLogger().log(Status.PASS, "Select session status and validate session completed successfully");
		sessionsPage.selectPleaseSelectActionDropdownElement(sessionStatus);
		sessionsPage.validateSessionCompletedSuccessfullyByCoach(internalExpertise,updatedDate);
   		thriveHeaderMenuPage.logout();
   		
		return this;
		
	}
	
	private EndToEndTest validateSessionCompletedSuccessfully() {
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsEA);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions");
		thriveHeaderMenuPage.clickMySessions();
		sessionsPage.validateSessionCompletedSuccessfullyByClient(internalExpertise,updatedDate);
		
		return this;
		
	}
	
	

}
