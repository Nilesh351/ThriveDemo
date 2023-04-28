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
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.invite.InvitedAccountManagersPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.user_management.search_filter.AccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.ArchivedAccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.ConfigureEnterprises;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementArchivedAccountManagerSearchFilterTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderPage;
	ThriveLoginPage login = new ThriveLoginPage();
	AccountManagersPage accountManagersPage = new AccountManagersPage();
	InvitedAccountManagersPage invitedAccountManagersPage = new InvitedAccountManagersPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ConfigureEnterprises configureEnterprises = new ConfigureEnterprises();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	ArchivedAccountManagersPage archivedAccountManagersPage = new ArchivedAccountManagersPage();
	InviteAccountManagerPage inviteAccountManagerPage = new InviteAccountManagerPage();
	RegisterAccountMangerPersonalDetailsPage registerAccountMangerPersonalDetailsPage = new RegisterAccountMangerPersonalDetailsPage();
	ExtentTest extentTestLocal;
	RegisterAMRequest registerAMReqBody;
	InviteAMRequest inviteAMReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	String value = "500";
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	InviteAccountManagerDetails inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();

	@Test(description = "Validate SA search archived account manager successfully")
	public void validateSASearchArchivedAccountManagerSuccessfully() throws IOException {

		String archiveMessage = ThriveAppSharedData.ACCOUNT_MANAGER_ARCHIVE_MESSAGE.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);

		registerAMReqBody = ApiTestDataGenerator.generateRegisterAMReqBody();
		registerAMReqBody.setFirstName(inviteAMReqBody.getFirstName());
		registerAMReqBody.setLastName(inviteAMReqBody.getLastName());
		registerAMReqBody.setEmail(inviteAMReqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "SA user invite Account Manager");

		getExtentTestLogger().log(Status.PASS, "SA register account manager successfully");
		response = getRegisterAMResponse(registerAMReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archivevd Account Managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin search and select the account manager to archive");
		accountManagersPage.setSearch(inviteAMReqBody.getEmail());
		accountManagersPage.checkAccountManagerCheckbox(inviteAMReqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin selects archive option from dropdown and click on yes");
		accountManagersPage.selectArchiveAccountManager().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate account manager archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);

		getExtentTestLogger().log(Status.PASS, "Validate archived account manager present inside archived tab");
		accountManagersPage.clickArchivedAccountManager();
		archivedAccountManagersPage.setSearch(inviteAMReqBody.getEmail());
		archivedAccountManagersPage.validateAccountManagerPresent(inviteAMReqBody.getEmail());

	}

	@Test(description = "Validate 'Filter By Enterprise' filter data successfully")
	public void validateFilterByEnterprise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- client");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickArchivedAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise to filter");
		archivedAccountManagersPage.selectFilterByEnterprise(enterprise);

		getExtentTestLogger().log(Status.PASS, "Click on account manager name link");
		archivedAccountManagersPage.clickOnAccountManagerName();
		registerAccountMangerPersonalDetailsPage.clickEnterprisesTab();

		getExtentTestLogger().log(Status.PASS, "Validate enterprise filter successfully");
		configureEnterprises.selectEnterprise(enterprise);
		configureEnterprises.validateEnterpriseEnabled();
	}

	@Test(description = "Validate clear filter clears all applied filters for Archived Account Managers tab")
	public void validateClearFiltersArchivedAccountManagers() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickArchivedAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the Account Manager to be searched and filtered");
		archivedAccountManagersPage.setSearch(accountManagerUser);
		archivedAccountManagersPage.selectFilterByEnterprise(enterprise);
		archivedAccountManagersPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		archivedAccountManagersPage.validateClearFilter();

	}

	@Test(description = "Validate pagination records match with selected pagination for Archived Account Manager tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsArchivedAccountManager(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickArchivedAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		archivedAccountManagersPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		archivedAccountManagersPage.validatePaginationRecords(val);
	}
	
	@Test(description="Validate actions dropdwown contains all Specicifed options for Archived Account Manager tab")
	public void validateActionsDropdownOptionsArchivedAccountManager() {		
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickArchivedAccountManager();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		archivedAccountManagersPage.clickActionsDropdown();
		
		getExtentTestLogger().log(Status.PASS, "Validate download, Un-Archive and Delete options present in actions dropdown");
		archivedAccountManagersPage.validateActionDropdownElements();
	}
	
	@Test(description = "Validate Archived Account Manager data gets updated when user get archived")
	public void validateArchivedAccountManagerDataUpdateValueChanges() throws IOException {

		String archiveMessage = ThriveAppSharedData.ACCOUNT_MANAGER_ARCHIVE_MESSAGE.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickArchivedAccountManager();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin gets the total records count");
		String previousCount = archivedAccountManagersPage.captureCurrentDataCount();
		
		getExtentTestLogger().log(Status.PASS, "SA register account manager successfully");
		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);
		registerAMReqBody = ApiTestDataGenerator.generateRegisterAMReqBody();
		registerAMReqBody.setFirstName(inviteAMReqBody.getFirstName());
		registerAMReqBody.setLastName(inviteAMReqBody.getLastName());
		registerAMReqBody.setEmail(inviteAMReqBody.getEmail());
		response = getRegisterAMResponse(registerAMReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin search and select the account manager to archive");
		accountManagersPage.setSearch(inviteAMReqBody.getEmail());
		accountManagersPage.checkAccountManagerCheckbox(inviteAMReqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin selects archive option from dropdown and click on yes");
		accountManagersPage.selectArchiveAccountManager().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate account manager archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived Account Manager");
		accountManagersPage.clickArchivedAccountManager();
		
		getExtentTestLogger().log(Status.PASS, "Archived Account Manager data count get updated successsfully");
		archivedAccountManagersPage.validateDataUpdate(previousCount);
	}

	private Response getRegisterAMResponse(RegisterAMRequest registerAMReqBody) {
		String token = getRegisterTokenAM(registerAMReqBody.getFirstName());
		return RestAssured.given().spec(getRegisterAMUserReqSpec()).queryParam("token", token).and().when()
				.body(registerAMReqBody, ObjectMapperType.GSON).post("");
	}

}
