package com.thrive.ui.test.credit_consumption;

import java.util.List;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionSummaryDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.book_session.BookSessionConfirmationPage;
import com.thrive.ui.page.book_session.BookSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionSummaryPage;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.SessionDeatilsPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.personal_details_page.ClientsPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;





public class CreditConsumptionCompletedSessions extends BaseTestPage{

	SessionDeatilsPage sessionDeatilsPage;
	BookSessionSummaryPage bookSessionSummaryPage;
	BookSessionSummaryDetails bookSessionSummaryDetails;
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	SessionsPage sessionsPage;
	MailboxPage mailboxPage;
	BookSessionViewPage bookSessionViewPage;
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();
	BookSessionDetails bookSessionDetailswithoutTime;
	BookSessionConfirmationPage bookSessionConfirmationPage;
	List<String> sessionSchedule;
	ThriveLoginPage login = new ThriveLoginPage();
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	ClientsPersonalDetailsPage clientsPersonalDetailsPage =new ClientsPersonalDetailsPage();
	BookSessionDetailsPage bookSessionDetailsPage = new BookSessionDetailsPage();
	ClientsPage clientsPage;
	RegisterEnterpriseDetailsPage enterpriseDetailsPage = new RegisterEnterpriseDetailsPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	String updatedDate;
	int currentCredits;
	int remainCredits;
	int entpCredits;
	int usedCredit;
	int clientCredits;
	ExtentTest extentTestLocal;
	String sessionCompltedOnline = ThriveAppSharedData.END_SESSION.getValue();
	String sessionCompltedOffline = ThriveAppSharedData.SESSION_COMPLTED_OFFLINE.getValue();
	String clientDidNotAttend = ThriveAppSharedData.CLIENT_DID_NOT_ATTEND.getValue();
	String sessionStartRemainderCoach = ThriveAppSharedData.SESSION_START_EMAIL_TO_COACH.getValue();
	String sessionStartRemainderClient = ThriveAppSharedData.SESSION_START_EMAIL_TO_CLIENT.getValue();
	String sessionCompletionNotificationCoach = ThriveAppSharedData.SESSION_COMPLETION_EMAIL_TO_COACH.getValue();
	String sessionCompletionNotificationClient = ThriveAppSharedData.SESSION_COMPLETION_EMAIL_TO_CLIENT.getValue();
	String sessionBookingNotificationCoach = ThriveAppSharedData.SESSION_BOOKING_EMAIL_TO_COACH.getValue();
	String sessionBookingNotificationClient = ThriveAppSharedData.SESSION_BOOKING_EAMIL_TO_CLIENT.getValue();
	String rescheduleNotification = ThriveAppSharedData.SESSION_RESCHEDULE_NOTIFICATION.getValue();
	String completedSessionStatus = ThriveAppSharedData.COMPLETED.getValue();
	String completedOfflineSessionStatus = ThriveAppSharedData.COMPLETED_OFFLINE.getValue();
	String gcFirstName=globalCoachName.split(" ")[0];
	String icFirstName=internalCoachName.split(" ")[0];
	String eaFirstName=eaImmutableName.split(" ")[0];
	
	
	
	
	@Test(description = "Validate credit deductions and email notification when live session is completed online successfully by GC")
	public void validateSessionCompletedOnlineByGlobalCoach() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with global coach successfully");
		booksingleSessionWithCoach(entpCredits,gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to global coach");
		validateMailNotification(gcFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentCoach(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with SA Login");
		updateSessionScheduleWithSaLogin(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session start remainder mail notification to GC");
		validateMailNotification(gcFirstName,sessionStartRemainderCoach);
		mailboxPage.validateEmailPresentCoach(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session start remainder mail notification to client");
		validateMailNotification(eaFirstName,sessionStartRemainderClient);
		mailboxPage.validateEmailPresentClient(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with GC Login");
		updateSessionStatusWithCoachLogin(sessionCompltedOnline,gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate Enterprise credit deduction with SA login");
		validateEnterpriseCreditDeductionWithSALogin(true);
		
		getExtentTestLogger().log(Status.PASS, "Validate EA credit deduction with SA login");
		validateClientCreditDeductionWithSALogin();

		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		validateCreditDeductionWithClientLogin();
		
		getExtentTestLogger().log(Status.PASS, "Validate session completion mail notification to GC");
		validateMailNotification(gcFirstName,sessionCompletionNotificationCoach);
		
		getExtentTestLogger().log(Status.PASS, "Validate session completion mail notification to client");
		validateMailNotification(eaFirstName,sessionCompletionNotificationClient);
	}
	
	
	@Test(description = "Validate credit deductions and email notification when live session is completed offline successfully by GC")
	public void validateSessionCompletedOfflineByGlobalCoach() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with global coach successfully");
		booksingleSessionWithCoach(entpCredits,gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to global coach");
		validateMailNotification(gcFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentCoach(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with SA Login");
		updateSessionScheduleWithSaLogin(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session start remainder mail notification to GC");
		validateMailNotification(gcFirstName,sessionStartRemainderCoach);
		mailboxPage.validateEmailPresentCoach(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session start remainder mail notification to client");
		validateMailNotification(eaFirstName,sessionStartRemainderClient);
		mailboxPage.validateEmailPresentClient(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with GC Login");
		updateSessionStatusWithCoachLogin(sessionCompltedOffline,gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate Enterprise credit deduction with SA login");
		validateEnterpriseCreditDeductionWithSALogin(true);
		
		getExtentTestLogger().log(Status.PASS, "Validate EA credit deduction with SA login");
		validateClientCreditDeductionWithSALogin();

		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		validateCreditDeductionWithClientLogin();
		
		getExtentTestLogger().log(Status.PASS, "Validate session completion mail notification to GC");
		validateMailNotification(gcFirstName,sessionCompletionNotificationCoach);
		
		getExtentTestLogger().log(Status.PASS, "Validate session completion mail notification to client");
		validateMailNotification(eaFirstName,sessionCompletionNotificationClient);
	}
	
	@Test(description = "Validate credit deductions and email notification when live session is completed online successfully by IC")
	public void validateSessionCompletedOnlineByInternalCoach() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with internal coach successfully");
		booksingleSessionWithCoach(entpCredits,icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to internal coach");
		validateMailNotification(icFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentClient(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with SA Login");
		updateSessionScheduleWithSaLogin(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session start remainder mail notification to internal coach");
		validateMailNotification(icFirstName,sessionStartRemainderCoach);
		mailboxPage.validateEmailPresentCoach(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session start remainder mail notification to client");
		validateMailNotification(eaFirstName,sessionStartRemainderClient);
		mailboxPage.validateEmailPresentClient(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with IC Login");
		updateSessionStatusWithCoachLogin(sessionCompltedOnline,icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate Enterprise credit deduction with SA login");
		validateEnterpriseCreditDeductionWithSALogin(true);
		
		getExtentTestLogger().log(Status.PASS, "Validate EA credit deduction with SA login");
		validateClientCreditDeductionWithSALogin();

		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		validateCreditDeductionWithClientLogin();
		
		getExtentTestLogger().log(Status.PASS, "Validate session completion mail notification to IC");
		validateMailNotification(icFirstName,sessionCompletionNotificationCoach);
		
		getExtentTestLogger().log(Status.PASS, "Validate session completion mail notification to client");
		validateMailNotification(eaFirstName,sessionCompletionNotificationClient);
	}
	
	
	@Test(description = "Validate credit deductions and email notification when live session is completed offline successfully by IC")
	public void validateSessionCompletedOfflineByInternalCoach() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with internal coach successfully");
		booksingleSessionWithCoach(entpCredits,icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to internal coach");
		validateMailNotification(icFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentClient(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with SA Login");
		updateSessionScheduleWithSaLogin(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session start remainder mail notification to internal coach");
		validateMailNotification(icFirstName,sessionStartRemainderCoach);
		mailboxPage.validateEmailPresentCoach(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session start remainder mail notification to client");
		validateMailNotification(eaFirstName,sessionStartRemainderClient);
		mailboxPage.validateEmailPresentClient(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with IC Login");
		updateSessionStatusWithCoachLogin(sessionCompltedOffline,icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate Enterprise credit deduction with SA login");
		validateEnterpriseCreditDeductionWithSALogin(true);
		
		getExtentTestLogger().log(Status.PASS, "Validate EA credit deduction with SA login");
		validateClientCreditDeductionWithSALogin();

		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		validateCreditDeductionWithClientLogin();
		
		getExtentTestLogger().log(Status.PASS, "Validate session completion mail notification to IC");
		validateMailNotification(icFirstName,sessionCompletionNotificationCoach);
		
		getExtentTestLogger().log(Status.PASS, "Validate session completion mail notification to client");
		validateMailNotification(eaFirstName,sessionCompletionNotificationClient);
	}
	
	
	@Test(description = "Validate credit deductions and email notification  when session has passed with no action from EA or IC")
	public void validateSessionPassedWithNoActionByEAOrIC() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with internal coach successfully");
		booksingleSessionWithCoach(entpCredits,icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to internal coach");
		validateMailNotification(icFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentClient(eaFirstName);
		
		getDriver().get(Config.getLoginPageURL());
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions");
		sessionsPage = thriveHeaderMenuPage.clickCoachingButton().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Update Session chedule to past");
		sessionsPage.updateSessionScheduleToPast(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "User search the client name and click on it");
		refreshPage();
		int updatedClientCredits = thriveHeaderMenuPage.availableCredits();
		enterpriseDetailsPage.validateBookSessionCreditsReflected(clientCredits, updatedClientCredits, 0);
		
	}
	
	
	@Test(description = "Validate credit deductions and email notification  when session has passed with no action from EA or GC")
	public void validateSessionPassedWithNoActionByEAOrGC() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with internal coach successfully");
		booksingleSessionWithCoach(entpCredits,gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to internal coach");
		validateMailNotification(gcFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentClient(eaFirstName);
		
		getDriver().get(Config.getLoginPageURL());
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions");
		sessionsPage = thriveHeaderMenuPage.clickCoachingButton().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Update Session chedule to past");
		sessionsPage.updateSessionScheduleToPast(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "User search the client name and click on it");
		refreshPage();
		int updatedClientCredits = thriveHeaderMenuPage.availableCredits();
		enterpriseDetailsPage.validateBookSessionCreditsReflected(clientCredits, updatedClientCredits, 0);
		
	}
	
	@Test(description = "Validate credit deductions and email notification when SA Select status  session as completed  for GC")
	public void validateSessionCompletedOnlineBySAForGC() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with global coach successfully");
		booksingleSessionWithCoach(entpCredits,gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to global coach");
		validateMailNotification(gcFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentCoach(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with SA Login");
		updateSessionStatusWithSaLogin(gcFirstName,completedSessionStatus);

		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		validateCreditDeductionWithClientLogin();

		getExtentTestLogger().log(Status.PASS, "Validate Enterprise credit deduction with SA login");
		validateEnterpriseCreditDeductionWithSALogin(true);
		
		getExtentTestLogger().log(Status.PASS, "Validate EA credit deduction with SA login");
		validateClientCreditDeductionWithSALogin();
	}
	
	@Test(description = "Validate credit deductions and email notification when SA Select status  session as completed offline for GC")
	public void validateSessionCompletedOfflineBySAForGC() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with global coach successfully");
		booksingleSessionWithCoach(entpCredits,gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to global coach");
		validateMailNotification(gcFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(gcFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentCoach(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with SA Login");
		updateSessionStatusWithSaLogin(gcFirstName,completedOfflineSessionStatus);

		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		validateCreditDeductionWithClientLogin();

		getExtentTestLogger().log(Status.PASS, "Validate Enterprise credit deduction with SA login");
		validateEnterpriseCreditDeductionWithSALogin(true);
		
		getExtentTestLogger().log(Status.PASS, "Validate EA credit deduction with SA login");
		validateClientCreditDeductionWithSALogin();
	}
	
	@Test(description = "Validate credit deductions and email notification when SA Select status session as completed for IC")
	public void validateSessionCompletedOnlineBySAForIC() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with global coach successfully");
		booksingleSessionWithCoach(entpCredits,icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to global coach");
		validateMailNotification(icFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentCoach(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with SA Login");
		updateSessionStatusWithSaLogin(icFirstName,completedSessionStatus);

		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		validateCreditDeductionWithClientLogin();

		getExtentTestLogger().log(Status.PASS, "Validate Enterprise credit deduction with SA login");
		validateEnterpriseCreditDeductionWithSALogin(true);
		
		getExtentTestLogger().log(Status.PASS, "Validate EA credit deduction with SA login");
		validateClientCreditDeductionWithSALogin();
	}
	
	@Test(description = "Validate credit deductions and email notification when SA Select status  session as completed offline for IC")
	public void validateSessionCompletedOfflineBySAForIC() {
		
		getExtentTestLogger().assignCategory("Credits and Email Verification");
		
		getExtentTestLogger().log(Status.PASS, "Capture enterprise and EA current credits");
		entpCredits = enterpriseCredits();
		clientCredits = clientCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin book single session with global coach successfully");
		booksingleSessionWithCoach(entpCredits,icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to global coach");
		validateMailNotification(icFirstName,sessionBookingNotificationCoach);
		mailboxPage.validateEmailPresentCoach(icFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Validate session booking mail notification to enterprise admin");
		validateMailNotification(eaFirstName,sessionBookingNotificationClient);
		mailboxPage.validateEmailPresentCoach(eaFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Update session schedule with SA Login");
		updateSessionStatusWithSaLogin(icFirstName,completedOfflineSessionStatus);

		getExtentTestLogger().log(Status.PASS, "Validate credit deduction with EA login");
		validateCreditDeductionWithClientLogin();

		getExtentTestLogger().log(Status.PASS, "Validate Enterprise credit deduction with SA login");
		validateEnterpriseCreditDeductionWithSALogin(false);
		
		getExtentTestLogger().log(Status.PASS, "Validate EA credit deduction with SA login");
		validateClientCreditDeductionWithSALogin();
	}
	
	
	
	private int enterpriseCredits() {
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage  = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		enterprisesPage.setSearch(eaUserImmutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaUserImmutableEmail);
		
		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of Enterprise");
		entpCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		
		return entpCredits;
	}
	
	private int clientCredits() {
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		clientsPage  = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		clientsPage.setSearch(eaImmutableName);
		clientsPage.clickOnSearchedName(eaImmutableName);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Credits and capture client credits");
		clientCredits = clientsPersonalDetailsPage.clickCredits().captureClientCredits();
		thriveHeaderMenuPage.logout();
		
	    return clientCredits;
	}

	
    private CreditConsumptionCompletedSessions booksingleSessionWithCoach(int eaCredits,String coachType) {
		
		getExtentTestLogger().log(Status.PASS, "Credits available for enterprise admin");
		currentCredits = thriveHeaderMenuPage.availableCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to book a session");
		bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachType);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides single session details");
		boolean isCoachGlobal = false;
		if(coachType.contains("Global")) {
			isCoachGlobal = true;
		}
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(isCoachGlobal);
		bookSessionDetailsPage.enterSessionDetails(1, bookSessionDetailswithoutTime);
		remainCredits = bookSessionDetailsPage.remainingCreditsvalue();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The single sesssion booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
		sessionSchedule = bookSessionConfirmationPage.sessionDetails();
		
		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise admin");
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateCreditConsumption(remainCredits, UpdatedCredits);
		usedCredit = thriveHeaderMenuPage.creditsUsedForBookingCount(currentCredits, remainCredits);
		thriveHeaderMenuPage.logout();		
	
		return this;
	}
	    
    
    private void updateSessionScheduleWithSaLogin(String coachType) {
    	getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
    	getDriver().get(Config.getLoginPageURL());
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions");
		sessionsPage = thriveHeaderMenuPage.clickCoachingButton().clickSessions();
		
		if(coachType.contains("Global")) {
			getExtentTestLogger().log(Status.PASS, "Search Event and update session schedule");
			updatedDate=sessionsPage.updateSessionSchedule(sessionSchedule.get(1),sessionSchedule.get(0),expertise);
		} else {
			getExtentTestLogger().log(Status.PASS, "Search Event and update session schedule");
			updatedDate=sessionsPage.updateSessionSchedule(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);
		}
   		thriveHeaderMenuPage.logout();
    }
    
    private void updateSessionStatusWithSaLogin(String coachType,String sessionStatus) {
    	getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
    	getDriver().get(Config.getLoginPageURL());
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions");
		sessionsPage = thriveHeaderMenuPage.clickCoachingButton().clickSessions();
		
		if(coachType.contains("Global")) {
			getExtentTestLogger().log(Status.PASS, "Search Event and update session schedule");
			updatedDate=sessionsPage.updateSessionStatusWithSALogin(sessionSchedule.get(1),sessionSchedule.get(0),expertise,sessionStatus);
		} else {
			getExtentTestLogger().log(Status.PASS, "Search Event and update session schedule");
			updatedDate=sessionsPage.updateSessionStatusWithSALogin(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise,sessionStatus);
		}
   		thriveHeaderMenuPage.logout();
    }
    
    private void updateSessionStatusWithCoachLogin(String sessionStatus, String coachType) {
    	getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
    	getDriver().get(Config.getLoginPageURL());
    	if(coachType.contains("Global")) {
			LoginDetails loginDetailsCoach = new LoginDetails(globalCoachUser, globalCoachPassword);
			thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsCoach);
			
			getExtentTestLogger().log(Status.PASS, "Navigate to Sessions and join session");
			thriveHeaderMenuPage.clickSessions().joinSessionWithCoachLogin(expertise,updatedDate,sessionSchedule.get(1)).validateSessionJoined();
    	} else {
    		LoginDetails loginDetailsCoach = new LoginDetails(internalCoachUser, internalCoachPassword);
    		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsCoach);
    		
    		getExtentTestLogger().log(Status.PASS, "Navigate to Sessions and join session");
    		thriveHeaderMenuPage.clickSessions().joinSessionWithCoachLogin(internalExpertise,updatedDate,sessionSchedule.get(1)).validateSessionJoined();
    	}

		getExtentTestLogger().log(Status.PASS, "Select session status and validate session completed successfully");
		sessionsPage.selectPleaseSelectActionDropdownElement(sessionStatus);
		thriveHeaderMenuPage.logout();
    }

	
	 private void validateEnterpriseCreditDeductionWithSALogin(boolean isCreditUsed) {
	    	getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in enterprise admin login");
			
	    	launchThriveApp(Config.getLoginPageURL());
			thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
			this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
			
			getExtentTestLogger().log(Status.PASS, "User search the enterprise name and click on it");
			enterprisesPage.setSearch(eaUserImmutableEmail);
			enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaUserImmutableEmail);
					
			getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise");
			if(isCreditUsed == false) {
				usedCredit = 0;
			}
			refreshPage();
			int updatedEntpCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();
			enterpriseDetailsPage.validateBookSessionCreditsReflected(entpCredits, updatedEntpCredits, usedCredit);
	    }
	 
	 
	 private void validateClientCreditDeductionWithSALogin() {
		 
		 getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
			thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
			
			getExtentTestLogger().log(Status.PASS, "User search the client name and click on it");
			enterprisesPage.setSearch(eaImmutableName);
			clientsPage.clickOnSearchedName(eaImmutableName);
					
			getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from client account");
			refreshPage();
			int updatedClientCredits = clientsPersonalDetailsPage.clickCredits().captureClientCredits();
			enterpriseDetailsPage.validateBookSessionCreditsReflected(clientCredits, updatedClientCredits, usedCredit);
			thriveHeaderMenuPage.logout();
	 }
	 
	 private void validateCreditDeductionWithClientLogin() {
		    getExtentTestLogger().log(Status.PASS, "Login with client credentials");
			
	    	launchThriveApp(Config.getLoginPageURL());
			thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "User search the client name and click on it");
			refreshPage();
			int updatedClientCredits = thriveHeaderMenuPage.availableCredits();
			
			enterpriseDetailsPage.validateBookSessionCreditsReflected(clientCredits, updatedClientCredits, usedCredit);
			thriveHeaderMenuPage.logout();
	 }
	 
	 private void validateMailNotification(String userName, String subject) {
		 getExtentTestLogger().log(Status.PASS, "Login with client credentials");
		 
		 getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		 mailboxPage =  new MailboxPage().SearchYahooEmail(userName,subject);
		
	 }
}
