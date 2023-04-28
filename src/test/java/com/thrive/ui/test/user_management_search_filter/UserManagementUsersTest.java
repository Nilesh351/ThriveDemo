package com.thrive.ui.test.user_management_search_filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteAMRequest;
import com.thrive.api.pojos.RegisterAMRequest;
import com.thrive.api.test.user_invite_register.ApiInviteAccountManagerTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementUsersTest extends UserManagementCommonPage {

	UsersPage usersPage = new UsersPage();
	RegisterAMRequest registerAMReqBody;
	InviteAMRequest inviteAMReqBody;
	Response response;
	ApiInviteAccountManagerTest inviteAMTest;
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	LoginDetails amLoginDetails = new LoginDetails(accountManagerUser, accountManagerPassword);
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	String userRole = ThriveAppSharedData.ROLE.getValue();
	Map<String, String> headers = new HashMap<>();
	String coachGlobalFirstName = globalCoachName.split("First")[0];
	String eaFirstName = eaImmutableName.split("First")[0];
	ThriveLoginPage login = new ThriveLoginPage();
	ExtentTest extentTestLocal;
	int paginationValue = 100;
	String value = "1000";
	String currentDataCount;
	String updatedDataCount;

	@Test(description = "Validate search box can accept any input for superadmin")
	public void validateSearchBoxSA() {

		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails).validateLoginSuccessful();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Search user with email id");
		usersPage.setSearch(eaUserImmutableEmail);

		getExtentTestLogger().log(Status.PASS, "Validate search box can accept any input and search result displayed");
		usersPage.validateSearchBoxInput(eaUserImmutableEmail);

	}

	@Test(description = "Validate search box can accept any input for enterprise admin")
	public void validateSearchBoxEA() {

		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails).validateLoginSuccessful();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Search user with email id");
		usersPage.setSearch(eaUserImmutableEmail);

		getExtentTestLogger().log(Status.PASS, "Validate search box can accept any input and search result displayed");
		usersPage.validateSearchBoxInput(eaUserImmutableEmail);

	}

	@Test(description = "Validate search box can accept partial input for superadmin")
	public void validatePartialInputSearchBoxSA() {

		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails).validateLoginSuccessful();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		usersPage.setSearch(coachGlobalFirstName);

		getExtentTestLogger().log(Status.PASS,
				"Validate search box can accept partial input and search result displayed");
		usersPage.validateUserNamePresent(coachGlobalFirstName);

	}

	@Test(description = "Validate search box can accept partial input for enterpriseadmin")
	public void validatePartialInputSearchBoxEA() {

		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails).validateLoginSuccessful();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		usersPage.setSearch(eaFirstName);

		getExtentTestLogger().log(Status.PASS,
				"Validate search box can accept partial input and search result displayed");
		usersPage.validateUserNamePresent(eaFirstName);

	}

	@Test(description = "Validate Page View revert to Default after input cleared superadmin")
	public void validatePageViewRevertToDefaultSA() {

		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails).validateLoginSuccessful();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		int expected = usersPage.getAllRecordsPresent();
		System.out.println("expected value" + expected);
		usersPage.setSearch(coachGlobalFirstName);
		usersPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS,
				"Validate search box can accept partial input and search result displayed");
		usersPage.validateRevertToDefaultPage(expected);

	}

	@Test(description = "Validate Page View revert to Default after input cleared enterprise admin")
	public void validatePageViewRevertToDefaultEA() {

		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails).validateLoginSuccessful();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		int expected = usersPage.getAllRecordsPresent();
		usersPage.setSearch(eaFirstName);
		usersPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS,
				"Validate search box can accept partial input and search result displayed");
		usersPage.validateRevertToDefaultPage(expected);

	}

	@Test(description = "Validate clear filter clears all applied filters SA")
	public void validateClearFiltersSA() {
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails).validateLoginSuccessful();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		usersPage.setSearch(eaFirstName);
		usersPage.selectUserRole(userRole);
		usersPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		usersPage.validateClearFilter();

	}

	@Test(description = "Validate clear filter clears all applied filters EA")
	public void validateClearFiltersEA() {
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails).validateLoginSuccessful();

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		usersPage.setSearch(eaFirstName);
		usersPage.selectUserRole(userRole);
		usersPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		usersPage.validateClearFilter();
	}

	@Test(description = "Validate SA successfully download all users records")
	public void validateUsersAllRecordsDownloadSA() {
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Click on download button");
		usersPage.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		usersPage.validateRecordsDownloaded();

	}

	@Test(description = "Validate EA successfully download all users records")
	public void validateUsersAllRecorddDownloadEA() {

		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "click on download button");
		usersPage.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		usersPage.validateRecordsDownloaded();

	}

	@Test(description = "Validate SA successfully download filtered users records")
	public void validateUsersFilteredRecordsDownloadSA() {
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Click download");
		usersPage.selectUserRole(userRole);
		usersPage.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		usersPage.validateRecordsDownloaded();

	}

	@Test(description = "Validate EA successfully download filtered users records")
	public void validateUsersFilteredRecordsDownloadEA() {
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Click download");
		usersPage.selectUserRole(userRole);
		usersPage.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		usersPage.validateRecordsDownloaded();

	}

	@Test(description = "Validate SA successfully download filtered users records and matches details")
	public void validateUsersFilteredRecordsDownloadCountSA() throws IOException {
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Click download");
		usersPage.selectUserRole(userRole);
		usersPage.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		usersPage.validateDownloadAllRecords();

	}

	@Test(description = "Validate EA successfully download filtered users records and matches details")
	public void validateUsersFilteredRecordsDownloadCountEA() throws IOException {
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Users");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Click download");
		usersPage.selectUserRole(userRole);
		usersPage.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		usersPage.validateDownloadAllRecords();

	}

	@Test(description = "Validate 'Search By Role' filter data and displayed as per selected user For SA", dataProvider = "usersRoleDataSA", dataProviderClass = LoginTestDataProvider.class)
	public void validateUsersSearchByRoleSA(String role) {

		extentTestLocal = extentReports.createTest(role, role + "Test");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method - " + role + " start");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Select user role");
		usersPage.clickFilterByRole().selectUserRole(role);

		getExtentTestLogger().log(Status.PASS, "Validate only searched users role data is displayed");
		usersPage.validateUserRoleDataDisplayed(role,paginationValue);
	}

	@Test(description = "Validate 'Search By Role' filter data and displayed as per selected user For EA", dataProvider = "usersRoleDataEA", dataProviderClass = LoginTestDataProvider.class)
	public void validateUsersSearchByRoleEA(String role) {

		extentTestLocal = extentReports.createTest(role, role + "Test");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method - " + role + " start");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Select user role");
		usersPage.clickFilterByRole().selectUserRole(role);

		getExtentTestLogger().log(Status.PASS, "Validate only searched users role data is displayed");
		usersPage.validateUserRoleDataDisplayed(role,paginationValue);
	}

	@Test(description = "Validate user can search all types of users for SA'", dataProvider = "usersSearchDataSA", dataProviderClass = LoginTestDataProvider.class)
	public void validateSearchUserTypesSA(String user) {

		extentTestLocal = extentReports.createTest("SearchUser" + user, "User " + user + "searched successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method user present");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "provide the user name");
		usersPage.setSearch(user);

		getExtentTestLogger().log(Status.PASS, "Validate user searched successfully.");
		usersPage.validateSearchBoxInput(user);
	}

	@Test(description = "Validate user can search all types of users for EA'", dataProvider = "usersSearchDataEA", dataProviderClass = LoginTestDataProvider.class)
	public void validateSearchUserTypesEA(String user) {

		extentTestLocal = extentReports.createTest("SearchUser" + user, "User " + user + "searched successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method user present");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "provide the user name");
		usersPage.setSearch(user);

		getExtentTestLogger().log(Status.PASS, "Validate user searched successfully.");
		usersPage.validateSearchBoxInput(user);
	}

	@Test(description = "Validate pagination records match with selected pagination SA", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsSA(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "provide pagination value");
		usersPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		usersPage.validatePaginationRecords(val);

	}

	@Test(description = "Validate pagination records match with selected pagination EA", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsEA(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "provide the pagination value");
		usersPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		usersPage.validatePaginationRecords(val);

	}

	@Test(description = "Validate pagination works fine for previous and next and records changes properly for SA")
	public void validatePaginationNextPervLinkSA() {

		LOGGER.info("onTestStart method user present");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "click on last page button");
		usersPage.clickLastPageButton();
		usersPage.validateLastPageRecords();

		getExtentTestLogger().log(Status.PASS, "click on first page button");
		usersPage.clickFirstPageButton();
		usersPage.validateFirstPageRecords();

		getExtentTestLogger().log(Status.PASS, "click on next page changes the records");
		usersPage.verifyPaginationData();
	}

	@Test(description = "Validate pagination works fine for previous and next and records changes properly EA")
	public void validatePaginationNextPervLinkEA() {

		LOGGER.info("onTestStart method user present");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.navigateToUsers().clickUsers();

		getExtentTestLogger().log(Status.PASS, "click on last page button");
		usersPage.clickLastPageButton();
		usersPage.validateLastPageRecords();

		getExtentTestLogger().log(Status.PASS, "click on first page button");
		usersPage.clickFirstPageButton();
		usersPage.validateFirstPageRecords();

		getExtentTestLogger().log(Status.PASS, "click on next page changes the records");
		usersPage.verifyPaginationData();

	}

	@Test(description = "Validate all users data gets updated after registering new user")
	public void validateAllUsersDataUpdate() {

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Capture current All Users data count");
		currentDataCount = usersPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "SA user invite Account Manager");
		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);

		getExtentTestLogger().log(Status.PASS, "Register Account Manager");
		registerAMReqBody = ApiTestDataGenerator.generateRegisterAMReqBody();
		registerAMReqBody.setFirstName(inviteAMReqBody.getFirstName());
		registerAMReqBody.setLastName(inviteAMReqBody.getLastName());
		registerAMReqBody.setEmail(inviteAMReqBody.getEmail());

		response = getRegisterAMResponse(registerAMReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		usersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers();

		getExtentTestLogger().log(Status.PASS, "Validate All Users data gets updated");
		usersPage.validateDataUpdate(currentDataCount);
	}

	private Response getRegisterAMResponse(RegisterAMRequest registerAMReqBody) {
		String token = getRegisterTokenAM(registerAMReqBody.getFirstName());
		return RestAssured.given().spec(getRegisterAMUserReqSpec()).queryParam("token", token).and().when()
				.body(registerAMReqBody, ObjectMapperType.GSON).post("");
	}

}
