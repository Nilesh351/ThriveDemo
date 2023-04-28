package com.thrive.ui.test.book_session;

import java.text.ParseException;
import java.util.List;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionSummaryDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.book_session.ArrangeSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionConfirmationPage;
import com.thrive.ui.page.book_session.BookSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionSummaryPage;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.SessionFeedbackPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class BookSessionTest extends BaseTestPage{
	
	ThriveLoginPage login = new ThriveLoginPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails clientLoginDetails = new LoginDetails(clientUser, clientPassword);
	BookSessionViewPage bookSessionViewPage = new BookSessionViewPage();
	SessionsPage sessionsPage = new SessionsPage();
	BookSessionDetailsPage bookSessionDetailsPage = new BookSessionDetailsPage();
	BookSessionSummaryPage bookSessionSummaryPage = new BookSessionSummaryPage();
	ArrangeSessionDetailsPage arrangeSessionDetailsPage = new ArrangeSessionDetailsPage();
	BookSessionConfirmationPage bookSessionConfirmationPage;
	SessionFeedbackPage sessionFeedbackPage = new SessionFeedbackPage();
	BookSessionDetails bookSessionDetails;
	BookSessionDetails bookSessionDetailswithoutTime;
	BookSessionSummaryDetails bookSessionSummaryDetails;
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();
	int bookSessionCount = 2;
	String coachGlobalFirstName =  globalCoachName.split("First")[0];
	String coachInternalFirstName =  internalCoachName.split("First")[0];
	String CoachEntInternalCoach = internalMutableCoachName.split("Fir1")[0];
	
	
	@Test(description = "Validate EA successfully book single session with global coach")
	public void validateBookSingleSessionGlobalCoach() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(true);
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
	}
	
	@Test(description = "Validate EA successfully book multiple session with global coach")
	public void validateBookMultipleSessionGlobalCoach() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_MULTIPLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides multiple book session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(true);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		
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
	}
	
	@Test(description = "Validate client successfully book single session with global coach")
	public void validateBookSingleSessionGlobalCoachForClient() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Client user logged into the application");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Client navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
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
	}
	
	@Test(description = "Validate Client successfully book multiple session with global coach")
	public void validateBookMultipleSessionGlobalCoachForClient() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_MULTIPLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "client provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "client navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "client selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "client provides multiple book session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(true);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "client clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "client provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "client clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The multiple sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(bookSessionCount);
		bookSessionConfirmationPage.getSessionDetails();
	}
	
	@Test(description = "Validate EA successfully book single session with internal coach")
	public void validateBookSingleSessionInternalCoach() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects internal coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details internal category");
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
	}
	
	@Test(description = "Validate EA successfully book multiple session with internal coach")
	public void validateBookMultipleSessionInternalCoach() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_MULTIPLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects internal coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
	
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides multiple book session details with internal category");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		
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
	}
	
	@Test(description = "Validate client successfully book single session with Internal coach")
	public void validateBookSingleSessionInternalCoachForClient() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Client user logged into the application");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Client navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Client selects internal coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Client provides single session details with internal category");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
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
	}
	
	@Test(description = "Validate Client successfully book multiple session with Internal coach")
	public void validateBookMultipleSessionInternalCoachForClient() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_MULTIPLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "client provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "client navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "client selects internal coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "client provides multiple book session details with internal category");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(bookSessionCount, bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "client clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "client provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "client clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The multiple sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(bookSessionCount);
		bookSessionConfirmationPage.getSessionDetails();
	}
	
	@Test(description = "Validate EA successfully Arrange session with Internal coach")
	public void validateArrangeSessionInternalCoachForEA() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects internal coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on arrange session checkbox");
		bookSessionDetailsPage.clickArrangeSeesionChecbox();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details internal category");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		arrangeSessionDetailsPage.enterSessionDetails(bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = arrangeSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionSummaryPage.clickArrangeSessionFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The session arranged successfully");
		bookSessionSummaryPage.validateArrangeSessionBooking();
		
	}
	
	@Test(description = "Validate EA successfully Arrange session with Global coach")
	public void validateArrangeSessionGlobalCoachForEA() {
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on arrange session checkbox");
		bookSessionDetailsPage.clickArrangeSeesionChecbox();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details internal category");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(true);
		arrangeSessionDetailsPage.enterSessionDetails(bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = arrangeSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionSummaryPage.clickArrangeSessionFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The session arranged successfully");
		bookSessionSummaryPage.validateArrangeSessionBooking();
		
	}
	
	@Test(description = "Validate EA successfully rebook completed session")
	public void validatEASuccessfullyRebookSession() {
		
		List<String> sessionSchedule;
		String updatedDate;
		String sessionStatus = "End Session";
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
   		LoginDetails loginDetailsEA = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
   		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsEA);
		
   		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionDetailsPage=bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single book session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(1,bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
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
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
   		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsEA);
		
   		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching");
		thriveHeaderMenuPage.clickResumeJourney().clickMySessions();
		sessionsPage.clickIncompleteSessionIcon();
		sessionFeedbackPage.sessionFeedbackDetails().closeToasterAlert();
		sessionsPage.clickRebookButton(internalExpertise,updatedDate);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details internal category");
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
	}
	
	@Test(description = "Validate Client successfully rebook completed session")
	public void validatClientSuccessfullyRebookSession() {
		
		List<String> sessionSchedule;
		String updatedDate;
		String sessionStatus = "End Session";
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
   		LoginDetails loginDetailsClient = new LoginDetails(clientUser, clientPassword);
   		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsClient);
		
   		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionDetailsPage=bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single book session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(1,bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
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
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login with client credentials");
   		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsClient);
		
   		getExtentTestLogger().log(Status.PASS, "Client navigates to the coaching");
		thriveHeaderMenuPage.clickResumeJourney().clickMySessions();
		sessionsPage.clickIncompleteSessionIcon();
		sessionFeedbackPage.sessionFeedbackDetails().closeToasterAlert();
		sessionsPage.clickRebookButton(internalExpertise,updatedDate);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single session details internal category");
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
	}
	
	
	@Test(description = "Validate booked session present inside sessions tab for client")
	public void validatBookedSessionPresentInsideSessionsTabClient() throws ParseException {
		
		List<String> sessionSchedule;
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
   		LoginDetails loginDetailsClient = new LoginDetails(clientUser, clientPassword);
   		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsClient);
   		
   		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionDetailsPage=bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single book session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(1,bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
		sessionSchedule=bookSessionConfirmationPage.sessionDetails();
		thriveHeaderMenuPage.clickMySessions();
		
		getExtentTestLogger().log(Status.PASS, "Validate session present for client");
		sessionsPage.validateSessionPresent(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
		LoginDetails loginDetailsCoach = new LoginDetails(internalCoachUser, internalCoachPassword);
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsCoach);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions and join session");
		thriveHeaderMenuPage.clickMySessions();
		
		getExtentTestLogger().log(Status.PASS, "validate session present inside coach");
		sessionsPage.validateSessionPresent(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);

	}
	
	@Test(description = "Validate booked session present inside sessions tab for EA")
	public void validatBookedSessionPresentInsideSessionsTabEA() throws ParseException {
		
		List<String> sessionSchedule;
		
		getExtentTestLogger().assignCategory(TestCategory.BOOK_SESSION_SINGLE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
   		LoginDetails loginDetailsEA = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
   		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsEA);
   		
   		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionDetailsPage=bookSessionViewPage.clickBookASessionButton(coachInternalFirstName);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides single book session details");
		bookSessionDetailswithoutTime = TestDataGenerator.generateBookSessionDetailsWithoutTime(false);
		bookSessionDetailsPage.enterSessionDetails(1,bookSessionDetailswithoutTime);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on next");
		bookSessionSummaryPage = bookSessionDetailsPage.clickNextButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides pre session questions answers");
		bookSessionSummaryDetails = TestDataGenerator.generateBookSessionSummaryDetails();
		bookSessionSummaryPage.enterPrequestionDetails(bookSessionSummaryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin clicks on finish booking");
		bookSessionConfirmationPage = bookSessionSummaryPage.clickFinishBooking();
		
		getExtentTestLogger().log(Status.PASS, "The sesssions booked successfully");
		bookSessionConfirmationPage.validateSessionsBooked(1);
		sessionSchedule=bookSessionConfirmationPage.sessionDetails();
		thriveHeaderMenuPage.clickMySessions();
		
		getExtentTestLogger().log(Status.PASS, "Validate session present for client");
		sessionsPage.validateSessionPresent(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
		LoginDetails loginDetailsCoach = new LoginDetails(internalCoachUser, internalCoachPassword);
		thriveHeaderMenuPage = new ThriveLoginPage().loginIgnoreAccept(loginDetailsCoach);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Sessions and join session");
		thriveHeaderMenuPage.clickMySessions();
		
		getExtentTestLogger().log(Status.PASS, "validate session present inside coach");
		sessionsPage.validateSessionPresent(sessionSchedule.get(1),sessionSchedule.get(0),internalExpertise);

	}

	

}
