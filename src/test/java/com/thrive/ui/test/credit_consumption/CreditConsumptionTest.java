package com.thrive.ui.test.credit_consumption;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionSummaryDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.book_session.BookSessionConfirmationPage;
import com.thrive.ui.page.book_session.BookSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionSummaryPage;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.SessionDeatilsPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;

public class CreditConsumptionTest extends BaseTestPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage;
	ThriveLoginPage login = new ThriveLoginPage();
	BookSessionViewPage bookSessionViewPage = new BookSessionViewPage();
	BookSessionDetails bookSessionDetailsP;
	BookSessionDetails bookSessionDetailswithoutTime;
	BookSessionDetailsPage bookSessionDetailsPage = new BookSessionDetailsPage();
	BookSessionSummaryPage bookSessionSummaryPage;
	BookSessionSummaryDetails bookSessionSummaryDetails;
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	RegisterEnterpriseDetailsPage enterpriseDetailsPage = new RegisterEnterpriseDetailsPage();
	SessionsPage sessionsPage = new SessionsPage();
	BookSessionConfirmationPage bookSessionConfirmationPage;
	SessionDeatilsPage sessionDeatilsPage;
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();
	int bookSessionCount = 2;
	String followonsession ="Follow-on session";
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDeatils = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	LoginDetails clientLoginDetails = new LoginDetails(clientUser, clientPassword);
	String coachInternalFirstName = internalCoachName.split("First")[0];
	String coachGlobalFirstName =  globalCoachName.split("First")[0];
	

	@Test(description = "Validate EA successfully book single session with Internal coach coach and credit deduction")
	public void validateBookSingleSessionEAInternalCoach() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDeatils);

		getExtentTestLogger().log(Status.PASS, "Internal coach book single session successfully");
		bookSingleSessionInternalCoach();
	}
	
	
	@Test(description = "Validate EA successfully book single session with global coach and credit deduction")
	public void validateBookSingleSessionEAGlobalCoach() {
		
		getExtentTestLogger().assignCategory("Coaching Book session - EA");
		
		getExtentTestLogger().log(Status.PASS, "enterprise admin current credits");
		int eaCredits = enterpriseAdminCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDeatils);
		
		getExtentTestLogger().log(Status.PASS, "Global coach book single session successfully");
		booksingleSessionGlobalCoach(eaCredits);
		
	}
	
	
	
	@Test(description = "Validate Client successfully book single session with Internal coach coach and credit deduction")
	public void validateBookSingleSessionClientInternalCoach() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());

		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(clientLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Internal coach book single session successfully");
		bookSingleSessionInternalCoach();
	}
	
	@Test(description = "Validate Client successfully book single session with Global coach and credits deduction")
	public void validateBookSingleSessionClientGlobalCoach() {

		getExtentTestLogger().assignCategory("Coaching Book session - Client");

		getExtentTestLogger().log(Status.PASS, "enterprise current credits");
		int eaCredits = enterpriseAdminCredits();

		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Global coach book single session successfully");
		booksingleSessionGlobalCoach(eaCredits);
	}
	
	

	@Test(description = "Validate EA successfully book multiple session with internal coach and credit deduction")
	public void validateBookMultipleSessionInternalCoach() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDeatils);
		
		getExtentTestLogger().log(Status.PASS, "Multiple sessions booked with Internal Coach Successfully");
		bookMultipleSessionInternalCoach();

		
	}
	
	@Test(description = "Validate EA successfully book multiple session with global coach and credit deduction")
	public void validateBookMultipleSessionGlobalCoach() {
		
		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());
		
		getExtentTestLogger().log(Status.PASS, "enterprise current credits");
		int eaCredits = enterpriseAdminCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDeatils);
		
		getExtentTestLogger().log(Status.PASS, "Global coach book multiple session successfully");
		bookMultipleSessionGlobalCoach(eaCredits);
		
	}
	
	@Test(description = "Validate Client successfully book multiple session with internal coach and credit deduction")
	public void validateclientBookMultipleSessionInternalCoach() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());

		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		thriveHeaderMenuPage = login.login(clientLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Multiple sessions booked with Internal Coach Successfully");
		bookMultipleSessionInternalCoach();
	}
	
	@Test(description = "Validate Client successfully book multiple session with Global coach and credit deduction")
	public void validateBookMultipleSessionClientGlobalCoach() {
		
		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());
		
		getExtentTestLogger().log(Status.PASS, "enterprise current credits");
		int eaCredits = enterpriseAdminCredits();
		
		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Global coach book multiple session successfully");
		bookMultipleSessionGlobalCoach(eaCredits);
		
	}

	@Test(description = "Validate EA credit successfully refunded for single session with internal coach")
	public void validateBookSingleSessionEAInternalCoachRefund() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDeatils);
		
		getExtentTestLogger().log(Status.PASS, "Single session credits refunded successfully");
		singleInternalSessionCreditRefund();

		
	}
	
	@Test(description = "Validate client credit successfully refunded for single session with internal coach")
	public void validateBookSingleSessionClientInternalCoachRefund() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());

		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		thriveHeaderMenuPage = login.login(clientLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Single session credits refunded successfully");
		singleInternalSessionCreditRefund();
	}
	
	
	@Test(description = "Validate EA credits successfully refund for single session of Global coach")
	public void validateBookSingleSessionEAGlobalCoachRefund() {
		
		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());
		
		getExtentTestLogger().log(Status.PASS, "enterprise current credits");
		int eaCredits = enterpriseAdminCredits();
		
		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDeatils);
		
		getExtentTestLogger().log(Status.PASS, "Global coach single session refunded successfully");
		singleGlobalSessionRefund(eaCredits);
		
	}
	
	
	@Test(description = "Validate Client credits successfully refund for single session of Global coach")
	public void validateBookSingleSessionClientGlobalCoachRefund() {
		
		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());
		
		getExtentTestLogger().log(Status.PASS, "enterprise current credits");
		int eaCredits = enterpriseAdminCredits();
		
		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Global coach single session refunded successfully");
		singleGlobalSessionRefund(eaCredits);
		
	}
	

	@Test(description = "Validate EA credit successfully refunded for multiple session with internal coach")
	public void validateBookMultipleSessionEAInternalCoachRefund() {
	

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDeatils);
		
		getExtentTestLogger().log(Status.PASS, "Internal Coach multiple session refunded successfully");
		multipleInternalSessionCreditRefund();
	}
	
	
	
	@Test(description = "Validate Client credit successfully refunded for multiple session with internal coach")
	public void validateBookMultipleSessionClientInternalCoachRefund() {
	
		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());

		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		thriveHeaderMenuPage = login.login(clientLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Internal Coach multiple session refunded successfully");
		multipleInternalSessionCreditRefund();
	}
	
	
	@Test(description = "Validate EA and enterprise credits successfully refunded for multiple session of global coach")
	public void validateBookMultipleSessionEAGlobalCoachRefund() {
		
		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());
		
		getExtentTestLogger().log(Status.PASS, "enterprise current credits");
		int eaCredits = enterpriseAdminCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDeatils);
		
		getExtentTestLogger().log(Status.PASS, "global Coach multiple session refunded successfully");
		multipleGlobalSessionRefund(eaCredits);
	}
	
	
	@Test(description = "Validate Client and enterprise credits successfully refunded for multiple session of global coach")
	public void validateBookMultipleSessionClientGlobalCoachRefund() {
		
		getExtentTestLogger().assignCategory(TestCategory.CREDIT_CONSUMPTION.getValue());
		
		getExtentTestLogger().log(Status.PASS, "enterprise current credits");
		int eaCredits = enterpriseAdminCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "global Coach multiple session refunded successfully");
		multipleGlobalSessionRefund(eaCredits);
	}
	
	
	private CreditConsumptionTest bookSingleSessionInternalCoach() {
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects internal coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(1, bookSessionDetailswithoutTime);
		int remainCredits = bookSessionDetailsPage.remainingCreditsvalue();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();

		getExtentTestLogger().log(Status.PASS, "The single sesssion booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
		bookSessionConfirmationPage.getSessionDetails();

		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise admin");
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateCreditConsumption(remainCredits, UpdatedCredits);
		
		return this;
	}
	
	private CreditConsumptionTest bookMultipleSessionInternalCoach() {
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickMySessions().clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects internal coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);

		getExtentTestLogger().log(Status.PASS,
				"Enterpriseadmin provides multiple book session details with internal category");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		int remainCredits = bookSessionDetailsPage.remainingCreditsvalue();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();

		getExtentTestLogger().log(Status.PASS, "The multiple sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(bookSessionCount);
		bookSessionConfirmationPage.getSessionDetails();

		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise admin");
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateCreditConsumption(remainCredits, UpdatedCredits);
		
		return this;
	}
	
	
	private CreditConsumptionTest singleInternalSessionCreditRefund() {
		
		getExtentTestLogger().log(Status.PASS, "Credits available for enterprise admin");
		int currentCredits = thriveHeaderMenuPage.availableCredits();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickMySessions().clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(1, bookSessionDetailswithoutTime);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();

		getExtentTestLogger().log(Status.PASS, "The single sesssion booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
		bookSessionConfirmationPage.getSessionDetails();

		getExtentTestLogger().log(Status.PASS, "click expertise from session");
		String date = bookSessionConfirmationPage.sessionFirstDateDetails();
		String time = bookSessionConfirmationPage.sessionFirstTimeDetails();
		String onlyDate = bookSessionConfirmationPage.sessionOnlyDate();
		sessionsPage = thriveHeaderMenuPage.clickMySessions().clickSessions();
		sessionsPage.selectDateRange(onlyDate, onlyDate);
		sessionDeatilsPage = sessionsPage.clickExpertiseLink(time, date, internalExpertise);

		getExtentTestLogger().log(Status.PASS, "Cancel the session");
		int hTime = sessionDeatilsPage.hoursRemaining();
		sessionDeatilsPage.clickCancelLink().ClickYes();
		int totalCreditconsume = sessionDeatilsPage.creditConsumptionCheck(currentCredits, hTime);

		getExtentTestLogger().log(Status.PASS, "Validate correct are remaining");
		thriveHeaderMenuPage.clickRefreshBrowser();
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateTotalCreditConsumption(totalCreditconsume, UpdatedCredits);
		
		return this;
	}
	
	private CreditConsumptionTest multipleInternalSessionCreditRefund() {
		
		getExtentTestLogger().log(Status.PASS, "Credits available for enterprise admin");
		int currentCredits = thriveHeaderMenuPage.availableCredits();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickMySessions().clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();

		getExtentTestLogger().log(Status.PASS, "The multiple sesssion booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(bookSessionCount);
		bookSessionConfirmationPage.getSessionDetails();

		getExtentTestLogger().log(Status.PASS, "click expertise from session");
		String fDate = bookSessionConfirmationPage.sessionFirstDateDetails();
		String fTime = bookSessionConfirmationPage.sessionFirstTimeDetails();
		String sDate = bookSessionConfirmationPage.sessionSecondDateDetails();
		String sTime = bookSessionConfirmationPage.sessionSecondTimeDetails();
		String onlyDate = bookSessionConfirmationPage.sessionOnlyDate();
		String secondDate = bookSessionConfirmationPage.sessionOnlyDateSecond();
		sessionsPage = thriveHeaderMenuPage.clickSessions();
		sessionsPage.selectDateRange(onlyDate, onlyDate);
		sessionDeatilsPage = sessionsPage.clickExpertiseLink(fTime, fDate, internalExpertise);

		getExtentTestLogger().log(Status.PASS, "Cancel the session");
		int hfTime = sessionDeatilsPage.hoursRemaining();
		sessionDeatilsPage.clickCancelLink().ClickYes();
		int creditconsume = sessionDeatilsPage.creditConsumptionCheck(currentCredits, hfTime);
		getExtentTestLogger().log(Status.PASS, "click expertise of the  session");	
		sessionsPage = thriveHeaderMenuPage.clickMySessions().clickSessions();
		sessionsPage.selectDateRange(secondDate, secondDate);
		sessionDeatilsPage = sessionsPage.clickExpertiseLink(sTime, sDate, followonsession);

		getExtentTestLogger().log(Status.PASS, "Cancel the session");
		int hsTime = sessionDeatilsPage.hoursRemaining();
		sessionDeatilsPage.clickCancelLink().ClickYes();
		int totalCreditconsume = sessionDeatilsPage.creditConsumptionCheck(creditconsume, hsTime);
		getExtentTestLogger().log(Status.PASS, "Validate correct are remaining");
		thriveHeaderMenuPage.clickRefreshBrowser();
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateTotalCreditConsumption(totalCreditconsume, UpdatedCredits);
		
		return this;
	}
	
	private int enterpriseAdminCredits() {
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage  = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		enterprisesPage.setSearch(eaUserImmutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaUserImmutableEmail);
		
		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of Enterprise");
		int eaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		thriveHeaderMenuPage.logout();
		
		return eaCredits;
	}
	
	private CreditConsumptionTest booksingleSessionGlobalCoach(int eaCredits) {
		
		getExtentTestLogger().log(Status.PASS, "Credits available for enterprise admin");
		int currentCredits = thriveHeaderMenuPage.availableCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickMySessions().clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(true);
		bookSessionDetailsPage.enterSessionDetails(1, bookSessionDetailswithoutTime);
		int remainCredits = bookSessionDetailsPage.remainingCreditsvalue();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The single sesssion booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
		bookSessionConfirmationPage.getSessionDetails();
		
		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise admin");
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateCreditConsumption(remainCredits, UpdatedCredits);
		int usedCredit = thriveHeaderMenuPage.creditsUsedForBookingCount(currentCredits, remainCredits);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in enterprise admin login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		enterprisesPage.setSearch(eaUserImmutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaUserImmutableEmail);
				
		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise");
		int updatedEaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		enterpriseDetailsPage.validateBookSessionCreditsReflected(eaCredits, updatedEaCredits, usedCredit);
		
		
		return this;
	}
	
	
	private CreditConsumptionTest bookMultipleSessionGlobalCoach(int eaCredits) {
		
		getExtentTestLogger().log(Status.PASS, "Credits available for enterprise admin");
		int currentCredits = thriveHeaderMenuPage.availableCredits();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides multiple book session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(true);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		int remainCredits = bookSessionDetailsPage.remainingCreditsvalue();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The multiple sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(bookSessionCount);
		bookSessionConfirmationPage.getSessionDetails();
		
		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise admin");
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateCreditConsumption(remainCredits, UpdatedCredits);
		int usedCredit = thriveHeaderMenuPage.creditsUsedForBookingCount(currentCredits, remainCredits);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in enterprise admin login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		enterprisesPage.setSearch(eaUserImmutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaUserImmutableEmail);
				
		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise");
		int updatedEaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		enterpriseDetailsPage.validateBookSessionCreditsReflected(eaCredits, updatedEaCredits, usedCredit);
		
		return this;
	}
	
	
	private CreditConsumptionTest singleGlobalSessionRefund(int eaCredits) {
		
		getExtentTestLogger().log(Status.PASS, "Credits available for client");
		int currentCredits = thriveHeaderMenuPage.availableCredits();
		
		getExtentTestLogger().log(Status.PASS, "Client navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickMySessions().clickBookSessionLink();
		
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
		bookSessionConfirmationPage.getSessionDetails();
		
		getExtentTestLogger().log(Status.PASS, "click expertise from session");
		String date = bookSessionConfirmationPage.sessionFirstDateDetails();
		String time = bookSessionConfirmationPage.sessionFirstTimeDetails(); 
		String onlyDate = bookSessionConfirmationPage.sessionOnlyDate();
		sessionsPage = thriveHeaderMenuPage.clickMySessions().clickSessions();
		sessionsPage.selectDateRange(onlyDate, onlyDate);
		sessionDeatilsPage = sessionsPage.clickExpertiseLink(time,date,expertise);
		
		getExtentTestLogger().log(Status.PASS, "Cancel the session");
		int hTime = sessionDeatilsPage.hoursRemaining();
		sessionDeatilsPage.clickCancelLink().ClickYes();
		int totalCreditconsume = sessionDeatilsPage.creditConsumptionCheck(currentCredits, hTime);
		
		getExtentTestLogger().log(Status.PASS, "Validate correct are remaining");
		sessionDeatilsPage.clickRefreshBrowser();
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateTotalCreditConsumption(totalCreditconsume, UpdatedCredits);
		int credits = currentCredits - totalCreditconsume;
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in enterprise admin login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		enterprisesPage.setSearch(eaUserImmutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaUserImmutableEmail);
				
		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise");
		int updatedEaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		enterpriseDetailsPage.validateCancelSessionCreditsReflectedCheck(eaCredits, updatedEaCredits,credits);
		
		return this;
	
	}
	
	private CreditConsumptionTest multipleGlobalSessionRefund(int eaCredits) {
		
		getExtentTestLogger().log(Status.PASS, "Credits available for enterprise admin");
		int currentCredits = thriveHeaderMenuPage.availableCredits();
	
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickMySessions().clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(true);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		String followExpertise = bookSessionDetailswithoutTime.getFollowOnSession();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The single sesssion booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(bookSessionCount);
		bookSessionConfirmationPage.getSessionDetails();
		
		getExtentTestLogger().log(Status.PASS, "click expertise from session");
		String fDate = bookSessionConfirmationPage.sessionFirstDateDetails();
		String fTime = bookSessionConfirmationPage.sessionFirstTimeDetails(); 
		String sDate = bookSessionConfirmationPage.sessionSecondDateDetails();
		String sTime = bookSessionConfirmationPage.sessionSecondTimeDetails();
		String onlyDate = bookSessionConfirmationPage.sessionOnlyDate();
		String secondDate = bookSessionConfirmationPage.sessionOnlyDateSecond();
		sessionsPage = thriveHeaderMenuPage.clickSessions();
		sessionsPage.selectDateRange(onlyDate, onlyDate);
		sessionDeatilsPage = sessionsPage.clickExpertiseLink(fTime,fDate,expertise);
		
		getExtentTestLogger().log(Status.PASS, "Cancel the session");
		int hfTime = sessionDeatilsPage.hoursRemaining();
		sessionDeatilsPage.clickCancelLink().ClickYes();
		int creditconsume = sessionDeatilsPage.creditConsumptionCheck(currentCredits, hfTime);
		
		getExtentTestLogger().log(Status.PASS, "click expertise of the  session");
		sessionsPage = thriveHeaderMenuPage.clickSessions();
		sessionsPage.selectDateRange(secondDate, secondDate);
		sessionDeatilsPage = sessionsPage.clickExpertiseLink(sTime,sDate,followExpertise);
		
		getExtentTestLogger().log(Status.PASS, "Cancel the session");
		int hsTime = sessionDeatilsPage.hoursRemaining();
		sessionDeatilsPage.clickCancelLink().ClickYes();
		int totalCreditconsume = sessionDeatilsPage.creditConsumptionCheck(creditconsume, hsTime);
		
		getExtentTestLogger().log(Status.PASS, "Validate correct are remaining");
		thriveHeaderMenuPage.clickRefreshBrowser();
		int UpdatedCredits = thriveHeaderMenuPage.availableCredits();
		thriveHeaderMenuPage.validateTotalCreditConsumption(totalCreditconsume, UpdatedCredits);
		int credits = currentCredits - totalCreditconsume;
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in enterprise admin login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		enterprisesPage.setSearch(eaUserImmutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaUserImmutableEmail);
				
		getExtentTestLogger().log(Status.PASS, "Validate correct credits deducted from enterprise");
		int updatedEaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		enterpriseDetailsPage.validateCancelSessionCreditsReflectedCheck(eaCredits, updatedEaCredits,credits);
		
		return this;
	}
	

}
