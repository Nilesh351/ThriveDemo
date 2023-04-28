package com.thrive.ui.test.user_management_search_filter;

import java.io.IOException;
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
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.user_management.search_filter.ArchivedEnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementArchivedEnterprisesSearchFilterTest extends UserManagementCommonPage {
	
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	ArchivedEnterprisesPage archivedEnterprisesPage = new ArchivedEnterprisesPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	InviteEnterpriseRequest inviteEntReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	RegisterEnterpriseRequest registerEntReqBody;
	ThriveLoginPage login = new ThriveLoginPage();
	ExtentTest extentTestLocal;
	String value ="500";
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	String archiveMessage = ThriveAppSharedData.ENT_ARCHIVE_MESSAGE.getValue();
	
	
	@Test(description = "Validate SA search archived enterprise successfully")
	public void validateSASearchArchivedEnterprise() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		ApiInviteEnterpriseTest inviteEntTest = new ApiInviteEnterpriseTest();
		inviteEntTest.getInviteEntResponse(inviteEntReqBody, headers);
		
		registerEntReqBody = ApiTestDataGenerator.generateRegisterEntReqBody();
		registerEntReqBody.setName(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite enterprise");

		getExtentTestLogger().log(Status.PASS, "SA register enterprise successfully");
		response = getRegisterEntResponse(registerEntReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to archive");
		enterprisesPage.setSearch(inviteEntReqBody.getCompany());
		enterprisesPage.checkEntepriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		enterprisesPage.selectArchiveEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin search the archived enterprise");
		enterprisesPage.clickArchivedEnterprise();
		archivedEnterprisesPage.setSearch(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Validate archived enterprise searched successfully");
		archivedEnterprisesPage.validateEnterprisePresent(inviteEntReqBody.getCompany());
	}
	
	@Test(description = "Validate SA 'Filter By Enterprise' for archived enterprise successfully")
	public void validateSAFilterArchivedEnterprise() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String archiveMessage = ThriveAppSharedData.ENT_ARCHIVE_MESSAGE.getValue();
		
		headers.put("Authorization", getApiAuthCode());
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		ApiInviteEnterpriseTest inviteEntTest = new ApiInviteEnterpriseTest();
		inviteEntTest.getInviteEntResponse(inviteEntReqBody, headers);
		
		registerEntReqBody = ApiTestDataGenerator.generateRegisterEntReqBody();
		registerEntReqBody.setName(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite enterprise");

		getExtentTestLogger().log(Status.PASS, "SA register enterprise successfully");
		response = getRegisterEntResponse(registerEntReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to archive");
		enterprisesPage.setSearch(inviteEntReqBody.getCompany());
		enterprisesPage.checkEntepriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		enterprisesPage.selectArchiveEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin 'Filter By enterprise' the archived enterprise");
		enterprisesPage.clickArchivedEnterprise();
		archivedEnterprisesPage.selectFilterByEnterprise(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Validate archived enterprise filtered successfully");
		archivedEnterprisesPage.validateEnterprisePresent(inviteEntReqBody.getCompany());
	}
	
	@Test(description = "Validate clear filters clears all apllied filters for archived enterprise tab")
	public void validateSAClearFiltersArchivedEnterprise() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String archiveMessage = ThriveAppSharedData.ENT_ARCHIVE_MESSAGE.getValue();
		
		headers.put("Authorization", getApiAuthCode());
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		ApiInviteEnterpriseTest inviteEntTest = new ApiInviteEnterpriseTest();
		inviteEntTest.getInviteEntResponse(inviteEntReqBody, headers);
		
		registerEntReqBody = ApiTestDataGenerator.generateRegisterEntReqBody();
		registerEntReqBody.setName(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite enterprise");

		getExtentTestLogger().log(Status.PASS, "SA register enterprise successfully");
		response = getRegisterEntResponse(registerEntReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to archive");
		enterprisesPage.setSearch(inviteEntReqBody.getCompany());
		enterprisesPage.checkEntepriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		enterprisesPage.selectArchiveEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provide archived enterprise to search and filter");
		enterprisesPage.clickArchivedEnterprise();
		archivedEnterprisesPage.setSearch(inviteEntReqBody.getCompany());
		archivedEnterprisesPage.selectFilterByEnterprise(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on clear filters ");
		archivedEnterprisesPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		archivedEnterprisesPage.validateClearFilter();
	}
	
	@Test(description =  "Validate pagination records match with selected pagination for Archived enterprise tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class) 
	public void validatePaginationRecordsArchivedEnterprise(int val) {
		
		extentTestLocal = extentReports.createTest("Selected pagination"+val, "pagination "+val+"selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Enterprises");
		archivedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickArchivedEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		archivedEnterprisesPage.scrollInVerticalDirection(value).selectPaginationValue(val);
		
		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		archivedEnterprisesPage.validatePaginationRecords(val);
		
	}
	
	
	@Test(description="Validate actions dropdwown contains all Specicifed options for Archived enterprises tab")
	public void validateActionsDropdownOptionsArchivedEnterprises() {		
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Enterprises");
		archivedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickArchivedEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		archivedEnterprisesPage.clickActionsDropdown();
		
		getExtentTestLogger().log(Status.PASS, "Validate download,Unarchived and delete options present in actions dropdown");
		archivedEnterprisesPage.validateActionDropdownElements();
	}
	
	
	@Test(description="Validate Archived Enterprise data gets updated when user get archived")
	public void validateArchivedEnterpriseDataUpdateValueChanges() {		
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived Enterprises");
		archivedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickArchivedEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin gets the total records count");
		String previousCount = archivedEnterprisesPage.captureCurrentDataCount();
		
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
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to archive");
		enterprisesPage.setSearch(inviteEntReqBody.getCompany());
		enterprisesPage.checkEntepriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		enterprisesPage.selectArchiveEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived enterprise");
		enterprisesPage.clickArchivedEnterprise();
				
		getExtentTestLogger().log(Status.PASS, "Archived Enterprises data count get updated successsfully");
		archivedEnterprisesPage.validateDataUpdate(previousCount);
		
	}
	
	private Response getRegisterEntResponse(RegisterEnterpriseRequest registerEntReqBody){
		String token = getRegisteredEntToken(inviteEntReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterEntUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerEntReqBody, ObjectMapperType.GSON)
				.post("");
		
	}
	
	

}
