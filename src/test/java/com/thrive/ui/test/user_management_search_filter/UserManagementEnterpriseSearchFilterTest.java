package com.thrive.ui.test.user_management_search_filter;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteEnterpriseRequest;
import com.thrive.api.pojos.RegisterEnterpriseRequest;
import com.thrive.api.test.user_invite_register.ApiInviteEnterpriseTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedEnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementEnterpriseSearchFilterTest extends UserManagementCommonPage {

	EnterprisesPage enterprisesPage = new EnterprisesPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveLoginPage login = new ThriveLoginPage();
	InviteEnterprisePage inviteEnterprisePage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InvitedEnterprisesPage InvitedEnterprisesPage = new InvitedEnterprisesPage();
	InviteEnterpriseDetails inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();;
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	String archivedEnt = ThriveAppSharedData.ARCHIVED_ENT_NAME.getValue();
	ExtentTest extentTestLocal;
	InviteEnterpriseRequest inviteEntReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	RegisterEnterpriseRequest registerEntReqBody;
	String value = "500";

	@Test(description = "Validate SA successfully search the enterprise")
	public void validateEnterpriseSearch() {

		getExtentTestLogger().assignCategory("UserManagement Actions- enterprise");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the enterprise to be searched");
		userManagementCommonPage = this.enterprisesPage.setSearch(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "Superadmin Enterprise searched successfully");
		enterprisesPage.validateEnterprisePresent(eaMutableEmail);
	}

	@Test(description = "Validate 'Filter By Enterprise' filter data successfully")
	public void validateFilterByEnterprise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- enterprise");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise to filter");
		userManagementCommonPage = enterprisesPage.selectFilterByEnterprise(enterprise);

		getExtentTestLogger().log(Status.PASS, "Validate enterprise filter successfully");
		enterprisesPage.validateEnterpriseNamePresent(enterprise);
	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for enterprises tab")
	public void validateActionsDropdownOptionsEnterprises() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		enterprisesPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download,invite enterprise , allocate credits,create enterprise and archive options present in actions dropdown");
		enterprisesPage.validateActionDropdownElements();
	}

	@Test(description = "Validate clear filter clears all applied filters for enterprises tab")
	public void validateClearFiltersEnterprises() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the enterprise to be searched and filtered");
		enterprisesPage.setSearch(eaMutableEmail);
		enterprisesPage.selectFilterByEnterprise(enterprise);
		enterprisesPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		enterprisesPage.validateClearFilter();

	}

	@Test(description = "Validate pagination records match with selected pagination for enterprise tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsEnterprise(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		enterprisesPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate user searched successfully.");
		enterprisesPage.validatePaginationRecords(val);
	}

	@Test(description = "Validate superadmin scroll right between the list")
	public void validateScrollRightListEnterprises() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on scroller");
		enterprisesPage.clickRightScrollButton();

		getExtentTestLogger().log(Status.PASS, "Validate columns changed after scrollingS");
		enterprisesPage.validateRightScrollColumnsPresent();

	}

	@Test(description = "Validate superadmin scroll left between the list")
	public void validateScrollLeftListEnterprises() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on scroller");
		enterprisesPage.clickRightScrollButton();
		enterprisesPage.clickLeftScrollButton();

		getExtentTestLogger().log(Status.PASS, "Validate columns changed after scrollingS");
		enterprisesPage.validateLeftScrollColumnsPresent();

	}

	@Test(description = "Validate Enterprise data gets updated when new enterprise get added")
	public void validateEnterpriseDataUpdateValueChanges() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin gets the total records count");
		String previousCount = enterprisesPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "SA register enterprise successfully");
		headers.put("Authorization", getApiAuthCode());
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		ApiInviteEnterpriseTest inviteEntTest = new ApiInviteEnterpriseTest();
		inviteEntTest.getInviteEntResponse(inviteEntReqBody, headers);

		registerEntReqBody = ApiTestDataGenerator.generateRegisterEntReqBody();
		registerEntReqBody.setName(inviteEntReqBody.getCompany());
		response = getRegisterEntResponse(registerEntReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Enterprises data count get updated successsfully");
		enterprisesPage.validateDataUpdate(previousCount);

	}

	private Response getRegisterEntResponse(RegisterEnterpriseRequest registerEntReqBody) {
		String token = getRegisteredEntToken(inviteEntReqBody.getFirstName());
		return RestAssured.given().spec(getRegisterEntUserReqSpec()).queryParam("token", token).and().when()
				.body(registerEntReqBody, ObjectMapperType.GSON).post("");

	}

}
