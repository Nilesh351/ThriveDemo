package com.thrive.ui.test.user_management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.RegisterCoachRequest;
import com.thrive.api.test.user_invite_register.ApiInviteCoachTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InvitedGlobalCoach;
import com.thrive.ui.page.user_management.search_filter.ArchivedGlobalCoachPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementCoachesTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	ThriveLoginPage login = new ThriveLoginPage();
	MailboxPage mailboxPage = new MailboxPage();
	InviteCoachPage inviteCoachPage = new InviteCoachPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	GlobalCoachesPage globalCoachesPage = new GlobalCoachesPage();
	InvitedGlobalCoach invitedGlobalCoach = new InvitedGlobalCoach();
	UsersPage usersPage = new UsersPage();
	CoachInviteDetails coachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
	String subjectWelcome = ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT.getValue();
	ArchivedGlobalCoachPage archivedGlobalCoachPage = new ArchivedGlobalCoachPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	String email="global@.com";
	RegisterCoachRequest registerCoachReqBody;
	String archiveMessage = ThriveAppSharedData.COACH_ARCHIVED_MESSAGE.getValue();
	String enableMessage = ThriveAppSharedData.COACH_ENABLED_MESSAGE.getValue();
	String deleteMessage = ThriveAppSharedData.COACH_DELETE_MESSAGE.getValue();
	InviteCoachRequest inviteCoachReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	
	
	
	@Test(description = "Validate superadmin successfully download globalcoach all records")
	public void validateGlobalCoachDownloadAllRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		globalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		globalCoachesPage.validateDownloadAllRecords();
	}
	
	
	@Test(description = "Validate Superadmin successfully download invite GlobalCoach all records")
	public void validateInvitedGlobalCoachDownloadAllRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited internalcoach");
		invitedGlobalCoach = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		invitedGlobalCoach.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		invitedGlobalCoach.validateDownloadAllRecords();
	}
	
	

	@Test(description = "Validate SA successfully reinvite the global coach")
	public void validateInvitedGlobalCoachReInvite() {
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		String reinvitecoachToaster = ThriveAppSharedData.COACH_REINVITE_MESSAGE.getValue(); 
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> coaches -> invited coach");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited global coach to be searched");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.sendGlobalCoachInvite(coachInviteDetails);
		String email = inviteCoachPage.validateCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		invitedGlobalCoach = globalCoachesPage.clickInvitedCoaches();
		invitedGlobalCoach.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coach and clicks re-invite option from actions dropdown");
		alertsAndMessagesPage = invitedGlobalCoach.clickInvitedGlobalCheckbox(email).selectReInviteOption();
		alertsAndMessagesPage.ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Coach reinvited successfully");
		alertsAndMessagesPage.validateToaster(reinvitecoachToaster);
		userManagementCommonPage = invitedGlobalCoach.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS, "validate after re-invite the mail is send to the Global Coach");
		thriveHeaderMenuPage.logout();
		mailboxPage.validateEmailPresentForCoach(coachInviteDetails.getFirstName());
		
	}
	
	@Test(description = "Validate SA successfully delete the invited global coach")
	public void validateInvitedGlobalCoachDelete() {
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		String deleteInvitedCoachToaster = ThriveAppSharedData.COACH_DELETE_INVITED_MESSAGE.getValue(); 
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> coaches -> invited coach");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited global coach to be searched");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.sendGlobalCoachInvite(coachInviteDetails);
		String email = inviteCoachPage.validateCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		invitedGlobalCoach = globalCoachesPage.clickInvitedCoaches();
		invitedGlobalCoach.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coach and clicks delete option from actions dropdown");
		alertsAndMessagesPage = invitedGlobalCoach.clickInvitedGlobalCheckbox(email).selectDeleteCoachAction();
		alertsAndMessagesPage.ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Coach delete successfully");
		alertsAndMessagesPage.validateToaster(deleteInvitedCoachToaster);
		
		getExtentTestLogger().log(Status.PASS, "Validate invited coach not present");
		invitedGlobalCoach.setSearch(email);
		invitedGlobalCoach.validateInvitedCoachNotPresent(email);
	}
	

	@Test(description = "Validate superadmin successfully download archived coach all records")
	public void validateArchivedcoachDownloadAllRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Coach -> archived");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = archivedGlobalCoachPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		archivedGlobalCoachPage.validateDownloadAllRecords();
	}
	
	

	@Test(description="validate SA check errors when invite global coach")
	public void validateInviteCoachErrorValidation() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	    
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from actions dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides coach categories, first name, last name and email address");
		inviteCoachPage.setCoachEmail(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin click on the send invitation button");
		inviteCoachPage.clickSendInvitationButtuon();
		
		getExtentTestLogger().log(Status.PASS, "The error message is displayed if mandatory field is empty and provide wrong value");
		userManagementCommonPage.validateMandatoryFieldErrormsg();
		userManagementCommonPage.validateEmailErrorMsg();
		
	}


	@Test(description = "Validate SA selects no option while reinvite the global coach")
	public void validateSASelectsNoOptionForGlobalCoachReInvite() {
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String tab = ThriveAppSharedData.INVITED_TAB.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> coaches -> invited coach");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited global coach to be searched");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.sendGlobalCoachInvite(coachInviteDetails);
		String email = inviteCoachPage.validateCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		invitedGlobalCoach = globalCoachesPage.clickInvitedCoaches();
		invitedGlobalCoach.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coach and clicks re-invite option from actions dropdown and selects no option");
		alertsAndMessagesPage = invitedGlobalCoach.clickInvitedGlobalCheckbox(email).selectReInviteOption();
		alertsAndMessagesPage.ClickNo();
		
		getExtentTestLogger().log(Status.PASS, "Validate SA remain in the invited tab");
		userManagementCommonPage.ValidateCurrentTab(tab);
		
	}
	
	
	
	@Test(description = "Validate superadmin successfully download globalcoach Specific records")
	public void validateGlobalCoachDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "superadmin selects specific records");
		int expected = globalCoachesPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = globalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		globalCoachesPage.validateDownloadSpecificRecords(expected);
	}
	
	
	@Test(description = "Validate Superadmin successfully download invited globalcoach Specific records")
	public void validateinvitedGlobalCoachDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited globalcoach");
		invitedGlobalCoach = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the invited coach to download");
		int expected = invitedGlobalCoach.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = invitedGlobalCoach.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		invitedGlobalCoach.validateDownloadSpecificRecords(expected);
	}
	
	
	@Test(description = "Validate superadmin successfully download archived coach specific records")
	public void validateArchivedcoachDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Coach -> archived");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived coaches to download");
		int expected = archivedGlobalCoachPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = archivedGlobalCoachPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		archivedGlobalCoachPage.validateDownloadSpecificRecords(expected);
	}
	
	@Test(description = "Validate SA archive coach successfully")
	public void validateSAArchiveCoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String archiveMessage = ThriveAppSharedData.COACH_ARCHIVED_MESSAGE.getValue();
		
		headers.put("Authorization", getApiAuthCode());
		inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
		ApiInviteCoachTest inviteCoachTest = new ApiInviteCoachTest();
		inviteCoachTest.getInviteCoachResponse(inviteCoachReqBody, headers);
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachReqBody();
		}else {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachRmReqBody();
		}
		registerCoachReqBody.setFirstName(inviteCoachReqBody.getFirstName());
		registerCoachReqBody.setLastName(inviteCoachReqBody.getLastName());
		registerCoachReqBody.setEmail(inviteCoachReqBody.getEmail());
		
		
		getExtentTestLogger().log(Status.PASS, "SA user invite coach");

		getExtentTestLogger().log(Status.PASS, "SA register coach successfully");
		response = getRegisterCoachResponse(registerCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the coach to archive");
		globalCoachesPage.setSearch(inviteCoachReqBody.getEmail());
		globalCoachesPage.clickCoachCheckbox(inviteCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		globalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate archived coach present inside archived tab");
		globalCoachesPage.clickArchivedCoaches();
		archivedGlobalCoachPage.setSearch(inviteCoachReqBody.getEmail());
		archivedGlobalCoachPage.validateCoachPresent(inviteCoachReqBody.getEmail());
	}
	
	@Test(description = "Validate SA enable coach successfully")
	public void validateSAEnableoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
		ApiInviteCoachTest inviteCoachTest = new ApiInviteCoachTest();
		inviteCoachTest.getInviteCoachResponse(inviteCoachReqBody, headers);
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachReqBody();
		}else {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachRmReqBody();
		}
		registerCoachReqBody.setFirstName(inviteCoachReqBody.getFirstName());
		registerCoachReqBody.setLastName(inviteCoachReqBody.getLastName());
		registerCoachReqBody.setEmail(inviteCoachReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite coach");

		getExtentTestLogger().log(Status.PASS, "SA register coach successfully");
		response = getRegisterCoachResponse(registerCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the coach to archive");
		globalCoachesPage.setSearch(inviteCoachReqBody.getEmail());
		globalCoachesPage.clickCoachCheckbox(inviteCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		globalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coach to unarchive");
		globalCoachesPage.clickArchivedCoaches();
		archivedGlobalCoachPage.setSearch(inviteCoachReqBody.getEmail());
		archivedGlobalCoachPage.clickArchivedGlobalCheckbox(inviteCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select unarchive option from actions dropdown");
		archivedGlobalCoachPage.selectUnArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach enabled successfuly");
		alertsAndMessagesPage.validateToaster(enableMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate Enabled coach present inside coaches tab");
		archivedGlobalCoachPage.clickCoaches();
		globalCoachesPage.setSearch(inviteCoachReqBody.getEmail());
		globalCoachesPage.validateEmailPresent(inviteCoachReqBody.getEmail().toLowerCase());	
	}
	
	@Test(description = "Validate SA delete coach successfully")
	public void validateSADeleteoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
		ApiInviteCoachTest inviteCoachTest = new ApiInviteCoachTest();
		inviteCoachTest.getInviteCoachResponse(inviteCoachReqBody, headers);
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachReqBody();
		}else {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachRmReqBody();
		}
		registerCoachReqBody.setFirstName(inviteCoachReqBody.getFirstName());
		registerCoachReqBody.setLastName(inviteCoachReqBody.getLastName());
		registerCoachReqBody.setEmail(inviteCoachReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite coach");

		getExtentTestLogger().log(Status.PASS, "SA register coach successfully");
		response = getRegisterCoachResponse(registerCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the coach to archive");
		globalCoachesPage.setSearch(inviteCoachReqBody.getEmail());
		globalCoachesPage.clickCoachCheckbox(inviteCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		globalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coach to delete");
		globalCoachesPage.clickArchivedCoaches();
		archivedGlobalCoachPage.setSearch(inviteCoachReqBody.getEmail());
		archivedGlobalCoachPage.clickArchivedGlobalCheckbox(inviteCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select delete option from actions dropdown");
		archivedGlobalCoachPage.selectDeleteCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach deleted successfuly");
		alertsAndMessagesPage.validateToaster(deleteMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate Deleted coach not present inside archived coaches tab");
		archivedGlobalCoachPage.setSearch(inviteCoachReqBody.getEmail());
		archivedGlobalCoachPage.validateCoachNotPresent(inviteCoachReqBody.getEmail().toLowerCase());	
	}	 	 
	 
	private Response getRegisterCoachResponse(RegisterCoachRequest registerCoachReqBody){
		String token = getRegisterCoachToken(registerCoachReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterCoachUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerCoachReqBody, ObjectMapperType.GSON)
				.post("");
		
	}
	

}