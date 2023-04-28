package com.thrive.ui.test.user_management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteClientRequest;
import com.thrive.api.pojos.RegisterClientRequest;
import com.thrive.api.test.user_invite_register.ApiInviteClientTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DateTimeUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.credits.RemoveCreditsPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InvitedClientsPage;
import com.thrive.ui.page.user_management.search_filter.ArchivedClientsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementClientsTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	ClientsPage clientPage = new ClientsPage();
	RemoveCreditsPage removeCreditsPage = new RemoveCreditsPage();
	InvitedClientsPage invitedClientsPage = new InvitedClientsPage();
	InviteClientPage inviteClientPage = new InviteClientPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	MailboxPage mailboxPage = new MailboxPage();
	ArchivedClientsPage archivedClientsPage = new ArchivedClientsPage();
	AllocateCreditsPage allocateCreditsPage = new AllocateCreditsPage();
	String nextYearDate = DateTimeUtils.getNextYearDate();
	String email = "Client@.com";
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
	String creditAmount = "1000";
	RegisterClientRequest registerClientReqBody;
	InviteClientRequest inviteClientreqBody;
	Response response;
	String enableMessage = ThriveAppSharedData.CLIENT_ENABLED_SUCCESSFULLY_MESSAGE.getValue();
	String deleteMessage = ThriveAppSharedData.CLIENT_DELETED_SUCCESSFULY_MESSAGE.getValue();
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();

	@Test(description = "Validate SA successfully download Client all records")
	public void validateClientDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		clientPage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		clientPage.validateDownloadAllRecords();
	}

	@Test(description = "Validate SA successfully download invited Client all records")
	public void validateInvitedClientDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited Clients");
		invitedClientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		invitedClientsPage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		invitedClientsPage.validateDownloadAllRecords();
	}

	@Test(description = "Validate SA successfully reinvite the client")
	public void validateInvitedClientReInvite() {
		String reinviteclientToaster = ThriveAppSharedData.ENT_REINVITE_MESSAGE.getValue();
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> client -> invited client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickClientButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited client to be searched");
		inviteClientPage = userManagementCommonPage.selectInviteClientAction();
		inviteClientPage.sendInvite(inviteClientDetails);
		String email = inviteClientPage.validateClientInviteToaster(clientInviteToaster,
				inviteClientDetails.getEmailAddress());
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		invitedClientsPage = clientPage.clickInvitedClients();
		invitedClientsPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS,
				"Superadmin selects client and clicks re-invite option from actions dropdown");
		alertsAndMessagesPage = invitedClientsPage.clickInviteClientCheckbox(email.toLowerCase())
				.selectReInviteOption();
		alertsAndMessagesPage.ClickYes();

		getExtentTestLogger().log(Status.PASS, "Client reinvited successfully");
		alertsAndMessagesPage.validateToaster(reinviteclientToaster);
		invitedClientsPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS, "validate after re-invite the mail is send to the Client");
		thriveHeaderMenuPage.logout();
		mailboxPage.validateEmailPresentForClient(inviteClientDetails.getFirstName());

	}

	@Test(description = "Validate SA successfully delete the Invited client")
	public void validateInvitedClientDelete() {
		String deleteclientToaster = ThriveAppSharedData.INVITED_CLIENT_DELETED_SUCCESSFULY_MESSAGE.getValue();
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> client -> invited client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickClientButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited client to be searched");
		inviteClientPage = userManagementCommonPage.selectInviteClientAction();
		inviteClientPage.sendInvite(inviteClientDetails);
		String email = inviteClientPage.validateClientInviteToaster(clientInviteToaster,
				inviteClientDetails.getEmailAddress());
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		invitedClientsPage = clientPage.clickInvitedClients();
		invitedClientsPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS,
				"Superadmin selects client and clicks delete option from actions dropdown");
		alertsAndMessagesPage = invitedClientsPage.clickInviteClientCheckbox(email.toLowerCase())
				.selectDeleteClientAction();
		alertsAndMessagesPage.ClickYes();

		getExtentTestLogger().log(Status.PASS, "Client delete invited client successfully");
		alertsAndMessagesPage.validateToaster(deleteclientToaster);

		getExtentTestLogger().log(Status.PASS, "Validate invited client not present");
		invitedClientsPage.setSearch(email);
		invitedClientsPage.validateInvitedClientNotPresent(email);

	}

	@Test(description = "Validate SA successfully download archived Client all records")
	public void validateArchivedClientDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> clients -> archived");
		archivedClientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		archivedClientsPage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		archivedClientsPage.validateDownloadAllRecords();
	}

	@Test(description = "Validate Next Year date present when allocating credits for Clients")
	public void validateNextYearDatePresentWhenAllocateCreditsToClient() {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to allocate credits");
		clientPage.setSearch(clientUser);
		clientPage.clickClientCheckboxEmail(clientUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin select allocate credits option from actions dropdown");
		userManagementCommonPage.selectAllocateCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Validate next year date is present");
		allocateCreditsPage.validateDatePresent(nextYearDate).clickCancelLink();

	}

	@Test(description = "validate SA check errors when invite Client")
	public void validateClientInviteErrorMsg() {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite client option from actions dropdown");
		inviteClientPage = userManagementCommonPage.selectInviteClientAction();

		getExtentTestLogger().log(Status.PASS,
				"Superadmin provides random enterprise name, first name, last name, email address and sso username");
		inviteClientPage.setEmailAddressInput(email);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on send invitation button");
		inviteClientPage.clickSendInvitationButton();

		getExtentTestLogger().log(Status.PASS,
				"The error message is displayed if mandatory field is empty and provide wrong value");
		userManagementCommonPage.validateMandatoryFieldErrormsg();
		userManagementCommonPage.validateEmailErrorMsg();
	}

	@Test(description = "Validate SA selects no option while reinvite the client")
	public void validateSASelectsNoOptionForClientReInvite() {
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		String tab = ThriveAppSharedData.INVITED_TAB.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> client -> invited client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickClientButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited client to be searched");
		inviteClientPage = userManagementCommonPage.selectInviteClientAction();
		inviteClientPage.sendInvite(inviteClientDetails);
		String email = inviteClientPage.validateClientInviteToaster(clientInviteToaster,
				inviteClientDetails.getEmailAddress());
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		invitedClientsPage = clientPage.clickInvitedClients();
		invitedClientsPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS,
				"Superadmin selects client and clicks re-invite option from actions dropdown and select no option");
		alertsAndMessagesPage = invitedClientsPage.clickInviteClientCheckbox(email.toLowerCase())
				.selectReInviteOption();
		alertsAndMessagesPage.ClickNo();

		getExtentTestLogger().log(Status.PASS, "Validate SA remain in the invited tab");
		userManagementCommonPage.ValidateCurrentTab(tab);

	}

	@Test(description = "Validate SA successfully download Client specific records")
	public void validateClientDownloadSpecificRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		int expected = clientPage.selectSpecificRecords();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = clientPage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		clientPage.validateDownloadSpecificRecords(expected);
	}

	@Test(description = "Validate SA successfully download invited Client Specific records")
	public void validateInvitedClientDownloadSpecificRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited Clients");
		invitedClientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the invited Client to download");
		int expected = invitedClientsPage.selectSpecificRecords();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = invitedClientsPage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		invitedClientsPage.validateDownloadSpecificRecords(expected);
	}

	@Test(description = "Validate SA successfully download archived Client specific records")
	public void validateArchivedClientDownloadSpecificRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> clients -> archived");
		archivedClientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived Clients to download");
		int expected = archivedClientsPage.selectSpecificRecords();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = archivedClientsPage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		archivedClientsPage.validateDownloadSpecificRecords(expected);
	}

	@Test(description = "Validate SA Delete credits from client error validation")
	public void validateSARemoveCreditsClientErrorValidation() {
		String clientErrorMsg = ThriveAppSharedData.CLIENT_CREDITS_ERROR_MESSAGE.getValue();

		getExtentTestLogger().assignCategory("User management - actions");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to allocate credits");
		clientPage.setSearch(clientUser);
		clientPage.clickClientCheckboxEmail(clientUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin select remove credits option from actions dropdown");
		userManagementCommonPage.selectRemoveCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides no. of credits to remove");
		removeCreditsPage.setNumberOfCredits(creditAmount);

		getExtentTestLogger().log(Status.PASS, "Validate error message is present");
		removeCreditsPage.validateErrorMessagePresentClient(clientErrorMsg);

	}

	@Test(description = "Validate SA archive client successfully")
	public void validateSAArchiveClientSuccessfully() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		headers.put("Authorization", getApiAuthCode());
		inviteClientreqBody = ApiTestDataGenerator.generateInviteClientReqBody();
		ApiInviteClientTest inviteClientTest = new ApiInviteClientTest();
		inviteClientTest.getInviteClientResponse(inviteClientreqBody, headers);

		registerClientReqBody = ApiTestDataGenerator.generateRegisterClientReqBody();
		registerClientReqBody.setFirstName(inviteClientreqBody.getFirstName());
		registerClientReqBody.setLastName(inviteClientreqBody.getLastName());
		registerClientReqBody.setEmail(inviteClientreqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite client");

		getExtentTestLogger().log(Status.PASS, "SA register client successfully");
		response = getRegisterClientResponse(registerClientReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to archive");
		clientPage.setSearch(inviteClientreqBody.getEmail());
		clientPage.clickClientCheckboxEmail(inviteClientreqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		clientPage.selectArchiveClientAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate client archived successfuly");
		clientPage.validateClientArchiveToaster();

		getExtentTestLogger().log(Status.PASS, "Validate archived client present inside archived tab");
		clientPage.clickArchivedClients();
		archivedClientsPage.setSearch(inviteClientreqBody.getEmail());
		archivedClientsPage.validateClientPresent(inviteClientreqBody.getEmail());
	}

	@Test(description = "Validate SA enable client successfully")
	public void validateSAEnableClientSuccessfully() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		headers.put("Authorization", getApiAuthCode());
		inviteClientreqBody = ApiTestDataGenerator.generateInviteClientReqBody();
		ApiInviteClientTest inviteClientTest = new ApiInviteClientTest();
		inviteClientTest.getInviteClientResponse(inviteClientreqBody, headers);

		registerClientReqBody = ApiTestDataGenerator.generateRegisterClientReqBody();
		registerClientReqBody.setFirstName(inviteClientreqBody.getFirstName());
		registerClientReqBody.setLastName(inviteClientreqBody.getLastName());
		registerClientReqBody.setEmail(inviteClientreqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite client");

		getExtentTestLogger().log(Status.PASS, "SA register client successfully");
		response = getRegisterClientResponse(registerClientReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to archive");
		clientPage.setSearch(inviteClientreqBody.getEmail());
		clientPage.clickClientCheckboxEmail(inviteClientreqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		clientPage.selectArchiveClientAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate client archived successfuly");
		clientPage.validateClientArchiveToaster();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects client to enable");
		clientPage.clickArchivedClients();
		archivedClientsPage.setSearch(inviteClientreqBody.getEmail());
		archivedClientsPage.checkClientCheckbox(inviteClientreqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select enable option from actions dropdown");
		archivedClientsPage.selectUnArchiveClientAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate client enable successfuly");
		alertsAndMessagesPage.validateToaster(enableMessage);

		getExtentTestLogger().log(Status.PASS, "Validate enabled client present inside clients tab");
		archivedClientsPage.clickClientsButton();
		clientPage.setSearch(inviteClientreqBody.getEmail());
		clientPage.validateClientPresent(inviteClientreqBody.getEmail());

	}

	@Test(description = "Validate SA delete client successfully")
	public void validateSADeleteClientSuccessfully() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		headers.put("Authorization", getApiAuthCode());
		inviteClientreqBody = ApiTestDataGenerator.generateInviteClientReqBody();
		ApiInviteClientTest inviteClientTest = new ApiInviteClientTest();
		inviteClientTest.getInviteClientResponse(inviteClientreqBody, headers);

		registerClientReqBody = ApiTestDataGenerator.generateRegisterClientReqBody();
		registerClientReqBody.setFirstName(inviteClientreqBody.getFirstName());
		registerClientReqBody.setLastName(inviteClientreqBody.getLastName());
		registerClientReqBody.setEmail(inviteClientreqBody.getEmail());
		
		System.out.println("email "+inviteClientreqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "SA user invite client");

		getExtentTestLogger().log(Status.PASS, "SA register client successfully");
		response = getRegisterClientResponse(registerClientReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to archive");
		clientPage.setSearch(inviteClientreqBody.getEmail());
		clientPage.clickClientCheckboxEmail(inviteClientreqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		clientPage.selectArchiveClientAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate client archived successfuly");
		clientPage.validateClientArchiveToaster();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects client to delete");
		clientPage.clickArchivedClients();
		archivedClientsPage.setSearch(inviteClientreqBody.getEmail());
		archivedClientsPage.checkClientCheckbox(inviteClientreqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select delete option from actions dropdown");
		archivedClientsPage.selectDeleteClientAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate client deleted successfuly");
		alertsAndMessagesPage.validateToaster(deleteMessage);

		getExtentTestLogger().log(Status.PASS, "Validate deleted client not present inside archived tab");
		archivedClientsPage.setSearch(inviteClientreqBody.getEmail());
		archivedClientsPage.validateClientNotPresent(inviteClientreqBody.getEmail().toLowerCase());

	}

	private Response getRegisterClientResponse(RegisterClientRequest registerClientReqBody) {
		String token = getRegisterTokenClient(registerClientReqBody.getFirstName());
		return RestAssured.given().queryParam("token", token).spec(getRegisterUserReqSpec()).and().when()
				.body(registerClientReqBody, ObjectMapperType.GSON).post("");

	}

}
