package com.thrive.ui.test.user_management_search_filter;

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
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.user_management.search_filter.AccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.ConfigureEnterprises;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementAccountManagerSearchFilterTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderPage;
	ThriveLoginPage login = new ThriveLoginPage();
	AccountManagersPage accountManagersPage = new AccountManagersPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ConfigureEnterprises configureEnterprises = new ConfigureEnterprises();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	RegisterAccountMangerPersonalDetailsPage registerAccountMangerPersonalDetailsPage= new RegisterAccountMangerPersonalDetailsPage();
	UsersPage usersPage = new UsersPage();
	RegisterAMRequest registerAMReqBody;
	InviteAMRequest inviteAMReqBody;
	ExtentTest extentTestLocal;
	String currentDataCount;
	String value ="500";
	Map<String, String> headers = new HashMap<>();
	Response response;
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	
	@Test(description = "Validate SA successfully search the Account Manager")
	public void validateAccountManagerSearch() {
		
		getExtentTestLogger().assignCategory("UserManagement Actions- Account Manager");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides the account manager to be searched");
		accountManagersPage.setSearch(accountManagerUser);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin Account manager searched successfully");
		accountManagersPage.validateAccountManagerPresent(accountManagerUser);
	}
	
	@Test(description = "Validate 'Filter By Enterprise' filter data successfully")
	public void validateFilterByEnterprise() {
		
		getExtentTestLogger().assignCategory("UserManagement Actions- client");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise to filter");
		accountManagersPage.selectFilterByEnterprise(enterprise);
		
		getExtentTestLogger().log(Status.PASS, "Click on account manager name link");
		accountManagersPage.clickOnAccountManagerName();
		registerAccountMangerPersonalDetailsPage.clickEnterprisesTab();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise filter successfully");
		configureEnterprises.selectEnterprise(enterprise);
		configureEnterprises.validateEnterpriseEnabled();
	}
	
	@Test(description="Validate clear filter clears all applied filters for Account Managers tab")
	public void validateClearFiltersAccountManagers() {		
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides the Account Manager to be searched and filtered");
		accountManagersPage.setSearch(accountManagerUser);
		accountManagersPage.selectFilterByEnterprise(enterprise);
		accountManagersPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		accountManagersPage.validateClearFilter();
		
	}
	
	@Test(description="Validate actions dropdwown contains all Specicifed options for Account Manager tab")
	public void validateActionsDropdownOptionsAccountManager() {		
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		accountManagersPage.clickActionsDropdown();
		
		getExtentTestLogger().log(Status.PASS, "Validate download, invite and archive options present in actions dropdown");
		accountManagersPage.validateActionDropdownElements();
	}
		
	@Test(description =  "Validate pagination records match with selected pagination for Account Manager tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class) 
	public void validatePaginationRecordsAccountManager(int val) {
		
		extentTestLocal = extentReports.createTest("Selected pagination"+val, "pagination "+val+"selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Manager");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		accountManagersPage.scrollInVerticalDirection(value).selectPaginationValue(val);
		
		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		accountManagersPage.validatePaginationRecords(val);	
	}
	
	 @Test(description="Validate Account Manager data gets updated when SA registers new Account Manager")
	 public void validateAccountMaangerDataUpdate() {
		 
		    getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
			thriveHeaderMenuPage = login.login(saLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Account Manager");
			thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

			getExtentTestLogger().log(Status.PASS, "Capture current archived internal coaches data count");
			currentDataCount = userManagementCommonPage.captureCurrentDataCount();

			getExtentTestLogger().log(Status.PASS, "SA user invite account manager");
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
			
			getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Account Manager");
			accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

			getExtentTestLogger().log(Status.PASS, "Validate archived internal coaches data gets updated");
			usersPage.validateDataUpdate(currentDataCount);
	 }
	
	 private Response getRegisterAMResponse(RegisterAMRequest registerAMReqBody){
			String token = getRegisterTokenAM(registerAMReqBody.getFirstName());
			return RestAssured.given()
					.spec(getRegisterAMUserReqSpec()).queryParam("token", token)
					.and()
					.when()
					.body(registerAMReqBody, ObjectMapperType.GSON)
					.post("");
		}
}
	
	
	

