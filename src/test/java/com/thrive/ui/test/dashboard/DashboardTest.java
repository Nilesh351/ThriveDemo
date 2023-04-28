package com.thrive.ui.test.dashboard;

import java.util.List;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionSummaryDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.utils.MockDataGenerator;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DateTimeUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.book_session.BookSessionConfirmationPage;
import com.thrive.ui.page.book_session.BookSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionSummaryPage;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.SessionFeedbackPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.dashboard.DashBoardPage;
import com.thrive.ui.page.dashboard.InsightsPage;
import com.thrive.ui.page.dashboard.PlatformInsightPage;
import com.thrive.ui.page.dashboard.ThriveInsightPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;

public class DashboardTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();
	PlatformInsightPage platformInsightPage = new PlatformInsightPage();
	LoginDetails loginDetails;
	SessionsPage sessionsPage = new SessionsPage();
	MailboxPage mailboxPage;
	BookSessionViewPage bookSessionViewPage;
	BookSessionDetailsPage bookSessionDetailsPage = new BookSessionDetailsPage();
	BookSessionSummaryPage bookSessionSummaryPage = new BookSessionSummaryPage();
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	BookSessionSummaryDetails bookSessionSummaryDetails;
	BookSessionDetails bookSessionDetailswithoutTime;
	BookSessionConfirmationPage bookSessionConfirmationPage = new BookSessionConfirmationPage();
	SessionFeedbackPage sessionFeedbackPage = new SessionFeedbackPage();
	ThriveInsightPage thriveInsightPage = new ThriveInsightPage();
	InviteClientPage inviteClientPage = new InviteClientPage();
	AlertsAndMessagesPage toasterAlert;
	RegisterEnterpriseDetailsPage registerEnterpriseDetailsPage;
	RegisterAccountMangerPersonalDetailsPage registerAccountMangerPersonalDetailsPage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	ClientsPage clientPage = new ClientsPage();
	DashBoardPage dashBoardPage = new DashBoardPage();
	InsightsPage insightsPage = new InsightsPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	AllocateCreditsPage allocateCreditsPage = new AllocateCreditsPage();
	String subjectWelcome = ThriveAppSharedData.ENT_WELCOME_EMAIL_SUBJECT.getValue();
	String welcomeSubject = ThriveAppSharedData.CLIENT_WELCOME_EMAIL.getValue();
	String subjectConfirmation = ThriveAppSharedData.CLIENT_REG_CONFIRM_EMAIL_SUBJECT.getValue();
	String aboutMessage = "Test About -" + MockDataGenerator.generateSentence();
	String allocateCreditToaster = ThriveAppSharedData.ALLOCATE_CREDIT_NOTIFICATION.getValue();
	InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	LoginDetails amLoginDetails = new LoginDetails(accountManagerUser, accountManagerPassword);
	LoginDetails clientDetails = new LoginDetails(clientUser, clientPassword);
	LoginDetails coachLoginDetails = new LoginDetails(globalCoachUser, globalCoachPassword);
	LoginDetails internalCoachLoginDetails = new LoginDetails(internalCoachUser, internalCoachPassword);
	LoginDetails clientLoginDetails;
	String coachGlobalFirstName =  globalCoachName.split("First")[0];
	String coachInternalFirstName =  internalCoachName.split("First")[0];
	int bookSessionCount = 1;
	String creditsAmount = "10";
	String nextYearDate = DateTimeUtils.getNextYearDate();
	String sessionStatus = "End Session";
	String updatedDate;
	List<String> sessionSchedule;
	
	@Test(description = "Validate SA successfully download platform Insight data in pdf format")
	public void validateDownloadPlatformInsightData() {
	
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the Dashboard -> platform insight");
		platformInsightPage = thriveHeaderMenuPage.clickDashboard().clickPlatformInsightLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on download button");
		platformInsightPage.clickDownloadButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate records download in .pdf file");
		platformInsightPage.validatePlatformRecordsDownloadedPdf();
		
	}
	
	@Test(description = "Validate Register users count updated after new user registered successfully")
	public void validateRegisterUserCount() {	
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the Dashboard -> platform insight");
		platformInsightPage = thriveHeaderMenuPage.clickDashboard().clickPlatformInsightLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin gets registered users count");
		int currentcount = platformInsightPage.registeredCount();
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite client");
		inviteClientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().selectInviteClientAction();
		
		getExtentTestLogger().log(Status.PASS, "Enter client invite details and send invite");
		inviteClientPage.sendInvite(inviteClientDetails);
		alertsAndMessagesPage.validateToaster(clientInviteToaster);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchClientMailAndClick(inviteClientDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter client information");
		empStatusPage = new RegisterClientInformationPage().submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());
		
		getExtentTestLogger().log(Status.PASS, "Enter client employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());
		
		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
		.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login with Superadmin and check registered users valu updated");
		launchThriveApp(Config.getLoginPageURL());
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		platformInsightPage = thriveHeaderMenuPage.clickDashboard().clickPlatformInsightLink();
		platformInsightPage.validateRegisteredUsersCount(currentcount);
	
	}
	
	
	@Test(description = "Validate Active users count updated after new user book session successfully")
	public void validateActiveUserCount() {
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the Dashboard -> platform insight");
		platformInsightPage = thriveHeaderMenuPage.clickDashboard().clickPlatformInsightLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin gets Active users count");
		int currentcount = platformInsightPage.activeUsersCount();
		
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite client");
		inviteClientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().selectInviteClientAction();
		
		getExtentTestLogger().log(Status.PASS, "Enter client invite details and send invite");
		inviteClientPage.sendInvite(inviteClientDetails);
		alertsAndMessagesPage.validateToaster(clientInviteToaster);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchClientMailAndClick(inviteClientDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter client information");
		empStatusPage = new RegisterClientInformationPage().submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());
		
		getExtentTestLogger().log(Status.PASS, "Enter client employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());
		
		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
		.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue()).closeToasterAlert();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to allocate credits");
		clientPage.setSearch(inviteClientDetails.getEmailAddress());
		clientPage.clickClientCheckboxEmail(inviteClientDetails.getEmailAddress().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select allocate credits option from actions dropdown");
		userManagementCommonPage.selectAllocateCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides credits and date data");
		allocateCreditsPage.setNumberOfCredits(creditsAmount).setExpiryDate(nextYearDate);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on allocate credits button");
		allocateCreditsPage.clickAllocateCreditButton().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits assigned successfully to the client");
		alertsAndMessagesPage.validateCreditAllocationToaster(getResultByKey(allocateCreditToaster)).closeToasterAlert();
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "client provides username,password and click on login");
		clientLoginDetails = new LoginDetails(inviteClientDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Client navigates to the coaching -> book a session");
		this.bookSessionViewPage  = new CoachingMenuPage().clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Client selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Client provides single session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(true);
		bookSessionDetailsPage.enterSessionDetails(1, bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Client clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Client provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Client clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The single sesssion booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the Dashboard -> platform insight");
		platformInsightPage = thriveHeaderMenuPage.clickDashboard().clickPlatformInsightLink();
		platformInsightPage.valdiateActiveusersCount(currentcount);
		
	}
	
	@Test(description = "Validate Completed session count updated after session completed successfully")
	public void validateCompletedsessionCount() {
	
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the Dashboard -> platform insight");
		platformInsightPage = thriveHeaderMenuPage.clickDashboard().clickPlatformInsightLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin gets completed session count");
		int currentcount = platformInsightPage.completedSessionCount();
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
   		LoginDetails loginDetailsEA = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
   		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsEA);
		
   		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionDetailsPage=bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single book session details");
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
   		
   		getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions");
		sessionsPage = thriveHeaderMenuPage.clickCoachingButton().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Search Event and update session schedule");
		updatedDate=sessionsPage.updateSessionSchedule(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);
   		thriveHeaderMenuPage.logout();
   		
   		getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
		LoginDetails loginDetailsCoach = new LoginDetails(internalCoachUser, internalCoachPassword);
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsCoach);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions and join session");
		thriveHeaderMenuPage.clickMySessions();
		sessionsPage.joinSessionWithCoachLogin(internalExpertise,updatedDate,sessionSchedule.get(1)).validateSessionJoined();

		getExtentTestLogger().log(Status.PASS, "Select session status and validate session completed successfully");
		sessionsPage.selectPleaseSelectActionDropdownElement(sessionStatus);
		sessionsPage.validateSessionCompletedSuccessfullyByCoach(internalExpertise,updatedDate);
   		thriveHeaderMenuPage.logout();
   		
   		getExtentTestLogger().log(Status.PASS, "Login with Superadmin and check Completed session value updated");
		launchThriveApp(Config.getLoginPageURL());
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		platformInsightPage = thriveHeaderMenuPage.clickDashboard().clickPlatformInsightLink();
		platformInsightPage.validateCompletedSessionCount(currentcount);

	}
	
	
	@Test(description = "Validate superadmin can see session , platform and thrive insight tabs inside dashboard")
	public void validateSAHaveAcessToAllInsightTabs() {
	
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the Dashboard");
		thriveHeaderMenuPage.clickDashboard();
		
		getExtentTestLogger().log(Status.PASS, "Validate all the insight tabs are present for superadmin");
		dashBoardPage.validateInsightTabsForSA();
		
	}
	
	@Test(description = "Validate superadmin download thrive insight data in pdf format")
	public void validateSADownloadThriveInsightDatainPdfFormat() {
	
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the Dashboard -> thrive insight");
		thriveHeaderMenuPage.clickDashboard().clickThriveInsightPage();
		
		getExtentTestLogger().log(Status.PASS, "Validate all the insight tabs are present for superadmin");
		thriveInsightPage.selectDownloadOption(ThriveAppSharedData.FILETYPE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		thriveInsightPage.validateThriveRecordsDownloadedPdf();
		
	}
	
	
	@Test(description = "Validate enterprise admin can see all assigned insight tabs inside insights")
	public void validateEAHaveAcessToAssignedInsightTabs() {
	
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the Insights");
		thriveHeaderMenuPage.clickInsights();
		
		getExtentTestLogger().log(Status.PASS, "Validate all the Assigned insight tabs are present for enterprise admin");
		insightsPage.validateInsightTabsForEA();
		
	}
	
	@Test(description = "Validate account manager can see all assigned insight tabs insight dashboard")
	public void validateAMHaveAcessToAssignedInsightTabs() {
	
		getExtentTestLogger().log(Status.PASS, "Account manager provides username,password and click on login");
		thriveHeaderMenuPage = login.login(amLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Account manager navigates to dashboard");
		thriveHeaderMenuPage.clickDashboard();
		
		getExtentTestLogger().log(Status.PASS, "Validate all the Assigned insight tabs are present for enterprise admin");
		dashBoardPage.validateInsightTabsForAM();
		
	}
	
	@Test(description = "Validate Client not have access to any insights tab")
	public void validateClientNotHaveAccessToInsights() {
	
		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		thriveHeaderMenuPage = login.login(clientDetails);
		
		getExtentTestLogger().log(Status.PASS, "Validate no insight tabs present for client");
		dashBoardPage.validateInsightTabsForClient();
		
	}
	
	
	@Test(description = "Validate coach not have access to any insights tab")
	public void validateCoacahNotHaveAccessToInsights() {
	
		getExtentTestLogger().log(Status.PASS, "Coach provides username,password and click on login");
		thriveHeaderMenuPage = login.login(coachLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Validate coach not have access to dashboard tab");
		thriveHeaderMenuPage.validateDashboardNotPresentCoach();
	
	}
	
	@Test(description = "Validate internal coach not have access to any insights tab")
	public void validateInternalCoacahNotHaveAccessToInsights() {
	
		getExtentTestLogger().log(Status.PASS, "Internal Coach provides username,password and click on login");
		thriveHeaderMenuPage = login.login(internalCoachLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Validate coach not have access to dashboard tab");
		thriveHeaderMenuPage.validateDashboardNotPresentCoach();
	
	}
	
	
	
	
	
	
	
	

}
