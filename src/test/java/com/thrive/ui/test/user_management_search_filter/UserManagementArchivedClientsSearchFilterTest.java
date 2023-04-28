package com.thrive.ui.test.user_management_search_filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteClientRequest;
import com.thrive.api.pojos.RegisterClientRequest;
import com.thrive.api.test.user_invite_register.ApiInviteClientTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.user_management.search_filter.ArchivedClientsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementArchivedClientsSearchFilterTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	ClientsPage clientPage = new ClientsPage();
	InviteClientPage inviteClientPage = new InviteClientPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	MailboxPage mailboxPage = new MailboxPage();
	ArchivedClientsPage archivedClientsPage = new ArchivedClientsPage();
	AllocateCreditsPage allocateCreditsPage = new AllocateCreditsPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
	RegisterClientRequest registerClientReqBody;
	InviteClientRequest inviteClientreqBody;
	Response response;
	ExtentTest extentTestLocal;
	String value = "500";
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();

	@Test(description = "Validate SA search archived client successfully")
	public void validateSASearchArchivedClientSuccessfully() throws IOException {

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

		getExtentTestLogger().log(Status.PASS, "Validate archived client searched successfully");
		clientPage.clickArchivedClients();
		archivedClientsPage.setSearch(inviteClientreqBody.getEmail());
		archivedClientsPage.validateClientPresent(inviteClientreqBody.getEmail());
	}

	@Test(description = "Validate 'Filter By Enterprise' filter data successfully")
	public void validateFilterByEnterprise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Invited client");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise to filter");
		userManagementCommonPage = archivedClientsPage.selectFilterByEnterprise(enterprise);

		getExtentTestLogger().log(Status.PASS, "Validate enterprise filter successfully");
		archivedClientsPage.validateEnterpriseNamePresent(enterprise);
	}

	@Test(description = "Validate clear filter clears all applied filters for Archived Clients tab")
	public void validateClearFiltersArchivedClients() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the Archived clients to be searched and filtered");
		archivedClientsPage.setSearch(clientUser);
		archivedClientsPage.selectFilterByEnterprise(enterprise);
		archivedClientsPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		archivedClientsPage.validateClearFilter();

	}

	@Test(description = "Validate pagination records match with selected pagination for Archived clients tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsArchivedClients(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		archivedClientsPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		archivedClientsPage.validatePaginationRecords(val);
	}

	@Test(description = "Validate superadmin scroll between the clients list successfully")
	public void validateScrollRightListArchivedClients() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on scroller");
		archivedClientsPage.clickRightScrollButton();

		getExtentTestLogger().log(Status.PASS, "Validate columns changed after scrollingS");
		archivedClientsPage.validateScrollColumnsPresent();

	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for Archived clients tab")
	public void validateActionsDropdownOptionsArchivedClients() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		archivedClientsPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download, delete and Un-archive options present in actions dropdown");
		archivedClientsPage.validateActionDropdownElements();
	}

	@Test(description = "Validate Archived Clients data gets updated when user get archived")
	public void validateArchivedClientsDataUpdateValueChanges() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin gets the total records count");
		String previousCount = archivedClientsPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "SA register client successfully");
		headers.put("Authorization", getApiAuthCode());
		inviteClientreqBody = ApiTestDataGenerator.generateInviteClientReqBody();
		ApiInviteClientTest inviteClientTest = new ApiInviteClientTest();
		inviteClientTest.getInviteClientResponse(inviteClientreqBody, headers);
		registerClientReqBody = ApiTestDataGenerator.generateRegisterClientReqBody();
		registerClientReqBody.setFirstName(inviteClientreqBody.getFirstName());
		registerClientReqBody.setLastName(inviteClientreqBody.getLastName());
		registerClientReqBody.setEmail(inviteClientreqBody.getEmail());
		response = getRegisterClientResponse(registerClientReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to archive");
		clientPage.setSearch(inviteClientreqBody.getEmail());
		clientPage.clickClientCheckboxEmail(inviteClientreqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		clientPage.selectArchiveClientAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate client archived successfuly");
		clientPage.validateClientArchiveToaster();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived Clients");
		clientPage.clickArchivedClients();

		getExtentTestLogger().log(Status.PASS, "Archived Clients data count get updated successsfully");
		archivedClientsPage.validateDataUpdate(previousCount);

	}

	private Response getRegisterClientResponse(RegisterClientRequest registerClientReqBody) {
		String token = getRegisterTokenClient(registerClientReqBody.getFirstName());
		return RestAssured.given().queryParam("token", token).spec(getRegisterUserReqSpec()).and().when()
				.body(registerClientReqBody, ObjectMapperType.GSON).post("");

	}

}
