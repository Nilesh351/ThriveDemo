package com.thrive.ui.test.user_management;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.RegisterInternalCoachRequest;
import com.thrive.api.test.user_invite_register.ApiInviteInternalCoachTest;
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
import com.thrive.ui.page.invite.InvitedInternalCoachPage;
import com.thrive.ui.page.user_management.search_filter.ArchivedInternalCoachPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
public class UserManagementInternalCoachesTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage;
	ThriveLoginPage login = new ThriveLoginPage();
	InternalCoachesPage internalCoachesPage = new InternalCoachesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InviteCoachPage inviteCoachPage = new InviteCoachPage();
	MailboxPage mailboxPage = new MailboxPage();
	ArchivedInternalCoachPage archivedInternalCoachPage = new ArchivedInternalCoachPage();
	InvitedInternalCoachPage invitedInternalCoachPage = new InvitedInternalCoachPage();
	CoachInviteDetails coachInviteDetails = TestDataGenerator.generateCoachInviteDetails(false);
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	String email = "Internal@.com";
	RegisterInternalCoachRequest registerInternalCoachReqBody;
	String enableMessage = ThriveAppSharedData.COACH_ENABLED_MESSAGE.getValue();
	String archiveMessage = ThriveAppSharedData.COACH_ARCHIVED_MESSAGE.getValue();
	String deleteMessage = ThriveAppSharedData.COACH_DELETE_MESSAGE.getValue();
	InviteCoachRequest inviteInternalCoachReqBody;
	Response response;
	UsersPage usersPage = new UsersPage();
	String currentDataCount;
	Map<String, String> headers = new HashMap<>();

	@Test(description = "Validate Superadmin successfully download Internalcoach all records")
	public void validateInternalCoachDownloadAllRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> InternalCoach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		internalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		internalCoachesPage.validateDownloadAllRecords();
	}

	@Test(description = "Validate Superadmin successfully download invite internalCoach all records")
	public void validateInvitedInternalCoachDownloadAllRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited internalcoach");
		invitedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickInvitedCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		invitedInternalCoachPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		invitedInternalCoachPage.validateDownloadAllRecords();
	}

	@Test(description = "Validate SA successfully reinvite the internal coach")
	public void validateInvitedInternalReInvite() {
		
		String reinvitecoachToaster = ThriveAppSharedData.COACH_REINVITE_MESSAGE.getValue();
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		
		getExtentTestLogger().assignCategory("UserManagement Actions- invited internal coach");
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> coaches -> invited coach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickInternalCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited internal coach to be searched");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		inviteCoachPage.sendInternalCoachInvite(coachInviteDetails);
		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		invitedInternalCoachPage = internalCoachesPage.clickInvitedCoachesButton();
		invitedInternalCoachPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS,"Superadmin select coach and click re-invite option from actions dropdown");
		alertsAndMessagesPage = invitedInternalCoachPage.clickInvitedInternalCoachCheckbox(email).selectReInviteOption();
		alertsAndMessagesPage.ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Internal Coach reinvited successfully");
		alertsAndMessagesPage.validateToaster(reinvitecoachToaster).closeToasterAlert();
		invitedInternalCoachPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS, "validate after re-invite the mail is send to the Internal Coach");
		thriveHeaderMenuPage.logout();
		mailboxPage.validateEmailPresentForCoach(coachInviteDetails.getFirstName());
	}

	@Test(description = "Validate SA successfully delete the invited Internal coach")
	public void validateInvitedInternalCoachDelete() {
		
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		String deleteInvitedCoachToaster = ThriveAppSharedData.COACH_DELETE_INVITED_MESSAGE.getValue();
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> coaches -> invited coach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickInternalCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited internal coach to be searched");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.sendInternalCoachInvite(coachInviteDetails);
		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		invitedInternalCoachPage = internalCoachesPage.clickInvitedCoachesButton();
		invitedInternalCoachPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS,"Superadmin selects coach and clicks delete option from actions dropdown");
		alertsAndMessagesPage = invitedInternalCoachPage.clickInvitedInternalCoachCheckbox(email).selectDeleteCoachAction();
		alertsAndMessagesPage.ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Coach delete successfully");
		alertsAndMessagesPage.validateToaster(deleteInvitedCoachToaster);
		
		getExtentTestLogger().log(Status.PASS, "Validate invited coach not present");
		invitedInternalCoachPage.setSearch(email);
		invitedInternalCoachPage.validateInvitedInternalCoachNotPresent(email);
	}

	@Test(description = "Validate superadmin successfully download archived internal coach all records")
	public void validateArchivedInternalCoachDownloadAllRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> internal coach -> archived");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickArchivedCoachButton();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		archivedInternalCoachPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		archivedInternalCoachPage.validateDownloadAllRecords();
	}

	@Test(description = "validate SA check errors when invite Internal coach")
	public void validateInternalCoachInviteErrorvalidationSA() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from action dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides enterprise, first name, last name ,email address");
		inviteCoachPage.setCoachEmail(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks the send invitation button");
		inviteCoachPage.clickSendInvitationButtuon();
		
		getExtentTestLogger().log(Status.PASS,"The error message is displayed if mandatory field is empty and provide wrong value");
		userManagementCommonPage.validateMandatoryFieldErrormsg();
		userManagementCommonPage.validateEmailErrorMsg();
	}

	@Test(description = "validate EA check errors when invite Internal coach")
	public void validateInternalCoachInviteErrorvalidationEA() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS,"Enterprise admin navigates to the Settings -> Users -> Users Dropdown -> Internal Coaches -> Registered");
		internalCoachesPage = thriveHeaderMenuPage.clickSettingsHeader().clickUsers().clickUsersDropdown().clickInternalCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects invite coach option from action dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS,"Enterprise admin provides enterprise, first name, last name ,email address");
		inviteCoachPage.setCoachEmail(email);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks the send invitation button");
		inviteCoachPage.clickSendInvitationButtuon();
		
		getExtentTestLogger().log(Status.PASS,"The error message is displayed if mandatory field is empty and provide wrong value");
		userManagementCommonPage.validateMandatoryFieldErrormsg();
		userManagementCommonPage.validateEmailErrorMsg();
	}

	@Test(description = "Validate SA selects no option while reinvite the internal coach")
	public void validateSASelectedNoOptionForInternalReInvite() {
		
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String tab = ThriveAppSharedData.INVITED_TAB.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> coaches -> invited coach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickInternalCoachesButton();
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited internal coach to be searched");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.sendInternalCoachInvite(coachInviteDetails);
		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		invitedInternalCoachPage = internalCoachesPage.clickInvitedCoachesButton();
		invitedInternalCoachPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS,"Superadmin select coach and click re-invite option from actions dropdown and select no option");
		alertsAndMessagesPage = invitedInternalCoachPage.clickInvitedInternalCoachCheckbox(coachInviteDetails.getEmailAddress()).selectReInviteOption();
		alertsAndMessagesPage.ClickNo();
		
		getExtentTestLogger().log(Status.PASS, "Validate SA remain in the invited tab");
		userManagementCommonPage.ValidateCurrentTab(tab);
	}

	@Test(description = "Validate Superadmin successfully download Internalcoach specifc records")
	public void validateInternalCoachDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> InternalCoach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		int expected = internalCoachesPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = internalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		internalCoachesPage.validateDownloadSpecificRecords(expected);
	}

	@Test(description = "Validate Superadmin successfully download invite internal coach specific records")
	public void validateInvitedInternalCoachDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited internalcoach");
		invitedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickInvitedCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the invited internal coach to download");
		int expected = invitedInternalCoachPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = invitedInternalCoachPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		invitedInternalCoachPage.validateDownloadSpecificRecords(expected);
	}

	@Test(description = "Validate superadmin successfully download archived internal coach specific records")
	public void validateArchivedInternalCoachDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> internal coach -> archived");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickArchivedCoachButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived coaches to download");
		int expected = archivedInternalCoachPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = archivedInternalCoachPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		archivedInternalCoachPage.validateDownloadSpecificRecords(expected);
	}

	@Test(description = "Validate SA archive internal coach successfully")
	public void validateSAArchiveInternalCoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite internal coach");
		
		getExtentTestLogger().log(Status.PASS, "SA register internal coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> InternalCoach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the internal coach to archive");
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.clickCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		internalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate client archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate archived coach present inside archived tab");
		internalCoachesPage.clickArchivedCoachButton();
		archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
		archivedInternalCoachPage.validateInternalCoachPresent(inviteInternalCoachReqBody.getEmail());
	}

	@Test(description = "Validate SA enable internal coach successfully")
	public void validateSAEnableInternalCoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite internal coach");
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
		
		
		getExtentTestLogger().log(Status.PASS, "SA register internal coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> InternalCoach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the internal coach to archive");
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.clickCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		internalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate client archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects internal coach to unarchive");
		internalCoachesPage.clickArchivedCoachButton();
		archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
		archivedInternalCoachPage.clickArchivedInternalCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select Unarchive option from actions dropdown");
		archivedInternalCoachPage.selectUnArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate Internal coach enabled successfuly");
		alertsAndMessagesPage.validateToaster(enableMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate Enabled coach present inside Internal coaches tab");
		archivedInternalCoachPage.clickInternalCoaches();
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.validateEmailPresent(inviteInternalCoachReqBody.getEmail().toLowerCase());
	}

	@Test(description = "Validate SA delete internal coach successfully")
	public void validateSADeleteInternalCoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite internal coach");
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "SA register internal coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> InternalCoach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the internal coach to archive");
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.clickCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		internalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate internal coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects internal coach to delete");
		internalCoachesPage.clickArchivedCoachButton();
		archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
		archivedInternalCoachPage.clickArchivedInternalCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select delete option from actions dropdown");
		archivedInternalCoachPage.selectDeleteCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate Internal coach deleted successfuly");
		alertsAndMessagesPage.validateToaster(deleteMessage);
		getExtentTestLogger().log(Status.PASS,"Validate deleted coach not present inside archived internal coaches tab");
		
		archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
		archivedInternalCoachPage.validateInternalCoachNotPresent(inviteInternalCoachReqBody.getEmail().toLowerCase());
	}

	@Test(description = "Validate enterprise admin successfully download Internal coach all records")
	public void validateInternalCoachDownloadAllRecordsEALogin() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal Coach");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		internalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		internalCoachesPage.validateDownloadAllRecords();
	}

	@Test(description = "Validate enterprise admin successfully download internal coach specific records")
	public void validateInternalCoachDownloadSpecificRecordsEALogin() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal Coach");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		int expected = internalCoachesPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		internalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		internalCoachesPage.validateDownloadSpecificRecords(expected);
	}

	@Test(description = "Validate EA successfully invite the internal coach")
	public void validateInvitedInternalInvite() {
		
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		
		getExtentTestLogger().assignCategory("UserManagement Actions- invited internal coach");
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin invites internal coach");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.commonCoachDetails(coachInviteDetails).clickSendInvitationButtuon();
		
		getExtentTestLogger().log(Status.PASS, "Validate invitation toaster");
		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Invited Employee searched successfully");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickInvitedCoachesButton();
		internalCoachesPage.setSearch(email);
		internalCoachesPage.validateInvitedUsersEmailPresent(email);
		
		getExtentTestLogger().log(Status.PASS, "Validate invitation mail sent successfully");
		mailboxPage.validateEmailPresentForCoach(coachInviteDetails.getFirstName());
	}

	@Test(description = "Validate EA archive internal coach successfully")
	public void validateEAArchiveInternalCoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		
		getExtentTestLogger().log(Status.PASS, "EA user invite internal coach");
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "EA register internal coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "EA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> InternalCoach");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects the internal coach to archive");
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		
		internalCoachesPage.clickCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		getExtentTestLogger().log(Status.PASS, "Enterprise admin select archive option from actions dropdown");
		internalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate internal coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate archived coach present inside archived tab");
		internalCoachesPage.clickArchivedCoachButton();
		archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
		archivedInternalCoachPage.validateInternalCoachPresent(inviteInternalCoachReqBody.getEmail());
	}

	@Test(description = "Validate EA successfully reinvite the internal coach")
	public void validateInvitedInternalReInviteEALogin() {
		
		String reinvitecoachToaster = ThriveAppSharedData.COACH_REINVITE_MESSAGE.getValue();
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		
		getExtentTestLogger().assignCategory("UserManagement Actions- invited internal coach");
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin invites internal coach");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.commonCoachDetails(coachInviteDetails).clickSendInvitationButtuon();
		
		getExtentTestLogger().log(Status.PASS, "Validate invitation toaster");
		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		invitedInternalCoachPage = internalCoachesPage.clickInvitedCoachesButton();
		invitedInternalCoachPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS,"Enterprise admin select coach and click re-invite option from actions dropdown");
		alertsAndMessagesPage = invitedInternalCoachPage.clickInvitedInternalCoachCheckbox(email).selectReInviteOption();
		alertsAndMessagesPage.ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Internal Coach reinvited successfully");
		alertsAndMessagesPage.validateToaster(reinvitecoachToaster);
		
		getExtentTestLogger().log(Status.PASS, "validate after re-invite the mail is send to the Internal Coach");
		thriveHeaderMenuPage.logout();
		mailboxPage.validateEmailPresentForCoach(coachInviteDetails.getFirstName());
	}

	@Test(description = "Validate EA successfully delete invited internal coach")
	public void validateInvitedInternalDeleteEALogin() {
		
		String deleteInvitedCoachToaster = ThriveAppSharedData.COACH_DELETE_INVITED_MESSAGE.getValue();
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		
		getExtentTestLogger().assignCategory("UserManagement Actions- invited internal coach");
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin invites internal coach");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.commonCoachDetails(coachInviteDetails).clickSendInvitationButtuon();
		
		getExtentTestLogger().log(Status.PASS, "Validate invitation toaster");
		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,coachInviteDetails.getEmailAddress());
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		invitedInternalCoachPage = internalCoachesPage.clickInvitedCoachesButton();
		invitedInternalCoachPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS,"Superadmin selects coach and clicks delete option from actions dropdown");
		alertsAndMessagesPage = invitedInternalCoachPage.clickInvitedInternalCoachCheckbox(email).selectDeleteCoachAction();
		alertsAndMessagesPage.ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Coach delete successfully");
		alertsAndMessagesPage.validateToaster(deleteInvitedCoachToaster);
		
		getExtentTestLogger().log(Status.PASS, "Validate invited coach not present");
		invitedInternalCoachPage.setSearch(email);
		invitedInternalCoachPage.validateInvitedInternalCoachNotPresent(email);
	}
	
	@Test(description = "Validate enterprise admin successfully download invited internal coach all records")
	public void validateInvitedInternalCoachDownloadAllRecordsEALogin() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal Coach -> Invited Internal Coaches");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickInvitedCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		internalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		internalCoachesPage.validateDownloadAllRecords();
	}
	
	
	@Test(description = "Validate enterprise admin successfully download specific records for invited internal coach")
	public void validateInvitedInternalCoachDownloadSpecificRecordsEALogin() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal Coach -> Invited Internal Coaches");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickInvitedCoachesButton();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		int expected = internalCoachesPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		internalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		internalCoachesPage.validateDownloadSpecificRecords(expected);
	}
	
	@Test(description = "Validate enterprise admin successfully download archived internal coach all records")
	public void validateArchivedInternalCoachDownloadAllRecordsEALogin() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal Coach -> Archived Internal Coaches");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickArchivedCoachButton();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		internalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		internalCoachesPage.validateDownloadAllRecords();
	}
	
	@Test(description = "Validate enterprise admin successfully download specific records for archived internal coach")
	public void validateArchivedInternalCoachDownloadSpecificRecordsEALogin() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal Coach -> Archived Internal Coaches");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickArchivedCoachButton();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		int expected = internalCoachesPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		internalCoachesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS,"Downloaded all records successfully in .csv format and count is accurate");
		internalCoachesPage.validateDownloadSpecificRecords(expected);
	}
	

	@Test(description = "Validate EA enable internal coach successfully")
	public void validateEAEnableInternalCoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
        headers.put("Authorization", getApiAuthCode());
		
		getExtentTestLogger().log(Status.PASS, "EA user invite internal coach");
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
		
		mediumWait();
		mediumWait();
		getExtentTestLogger().log(Status.PASS, "EA register internal coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		
		getExtentTestLogger().log(Status.PASS, "EA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> InternalCoach");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects the internal coach to archive");
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.clickCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin select archive option from actions dropdown");
		internalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate internal coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects internal coach to unarchive");
		internalCoachesPage.clickArchivedCoachButton();
		archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
		archivedInternalCoachPage.clickArchivedInternalCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin select unarchive option from actions dropdown");
		archivedInternalCoachPage.selectUnArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate Internal coach enabled successfuly");
		alertsAndMessagesPage.validateToaster(enableMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate Enabled coach present inside Internal coaches tab");
		archivedInternalCoachPage.clickInternalCoaches();
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.validateEmailPresent(inviteInternalCoachReqBody.getEmail().toLowerCase());
	}
	
	
	@Test(description = "Validate EA enable internal coach successfully")
	public void validateEADeleteInternalCoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
        headers.put("Authorization", getApiAuthCode());
		
		getExtentTestLogger().log(Status.PASS, "EA user invite internal coach");
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
		
		mediumWait();
		mediumWait();
		getExtentTestLogger().log(Status.PASS, "EA register internal coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		
		getExtentTestLogger().log(Status.PASS, "EA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> InternalCoach");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects the internal coach to archive");
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.clickCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin select archive option from actions dropdown");
		internalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate internal coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects internal coach to unarchive");
		internalCoachesPage.clickArchivedCoachButton();
		archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
		archivedInternalCoachPage.clickArchivedInternalCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin select delete option from actions dropdown");
		archivedInternalCoachPage.selectDeleteCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate Internal coach deleted successfuly");
		alertsAndMessagesPage.validateToaster(deleteMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate Enabled coach present inside Internal coaches tab");
		refreshPage();
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.validateEmailIsNotPresent(inviteInternalCoachReqBody.getEmail().toLowerCase());
	}
	
	
	private Response getRegisterInternalCoachResponse(RegisterInternalCoachRequest registerInternalCoachReqBody2) {
		String token = getRegisterInternalCoachToken(registerInternalCoachReqBody.getFirstName());
		return RestAssured.given().spec(getRegisterInternalCoachUserReqSpec()).queryParam("token", token).and().when()
				.body(registerInternalCoachReqBody, ObjectMapperType.GSON).post("");
	}
}
