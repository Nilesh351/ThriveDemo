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
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.invite.InvitedAccountManagersPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.user_management.search_filter.AccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.ConfigureEnterprises;
import com.thrive.ui.page.user_management.search_filter.UsersPage;
import io.restassured.response.Response;

public class UserManagementInvitedAccountManagerSearchFilterTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderPage;
	ThriveLoginPage login = new ThriveLoginPage();
	AccountManagersPage accountManagersPage = new AccountManagersPage();
	InvitedAccountManagersPage invitedAccountManagersPage = new InvitedAccountManagersPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ConfigureEnterprises configureEnterprises = new ConfigureEnterprises();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InviteAccountManagerPage inviteAccountManagerPage = new InviteAccountManagerPage();
	RegisterAccountMangerPersonalDetailsPage registerAccountMangerPersonalDetailsPage = new RegisterAccountMangerPersonalDetailsPage();
	UsersPage usersPage = new UsersPage();
	RegisterAMRequest registerAMReqBody;
	InviteAMRequest inviteAMReqBody;
	ExtentTest extentTestLocal;
	String value = "500";
	String currentDataCount;
	Response response;
	Map<String, String> headers = new HashMap<>();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	InviteAccountManagerDetails inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();

	@Test(description = "Validate SA successfully search the invited Account Manager")
	public void validateInvitedGlobalCoachSearch() {

		getExtentTestLogger().assignCategory("UserManagement Actions- invited Internal Coach");

		String accountManagerInviteToaster = ThriveAppSharedData.INVITE_ACCOUNT_MANAGER_NOTIFICATION.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited Account Manager to be searched");
		inviteAccountManagerPage = userManagementCommonPage.selectInviteAccountManager();
		inviteAccountManagerPage.sendInvite(inviteAccountManagerDetails);
		String email = inviteAccountManagerPage.validateAMInviteToaster(accountManagerInviteToaster,
				inviteAccountManagerDetails.getEmailAddress());
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickInvitedAccountManager();
		invitedAccountManagersPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS, "Invited Account Manager searched successfully");
		invitedAccountManagersPage.validateAccountManagerPresent(email);
	}

	@Test(description = "Validate clear filter clear all applied filters successfully")
	public void validateClearFiltersInvitedAccountManager() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,
				"Superadmin navigates to the user management -> Invited Account Manager");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickInvitedAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the Account Manager to be searched");
		invitedAccountManagersPage.setSearch(accountManagerUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on clear filters");
		invitedAccountManagersPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		invitedAccountManagersPage.validateResetFilters();

	}

	@Test(description = "Validate pagination records match with selected pagination for Invited Account Manager tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsInvitedAccountManager(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,
				"Superadmin navigates to the user management -> Invited Account Manager");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickInvitedAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		invitedAccountManagersPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedAccountManagersPage.validatePaginationRecords(val);
	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for Invited Account Manager tab")
	public void validateActionsDropdownOptionsInvitedAccountManager() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,
				"Superadmin navigates to the user management -> Invited Account Manager");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickInvitedAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		invitedAccountManagersPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download, Re-Invite and delete options present in actions dropdown");
		invitedAccountManagersPage.validateActionDropdownElements();
	}
	
	 
	 @Test(description="Validate Invited Account Manager data gets updated after inviting new Account Manager")
	    public void validateInvitedAMDataUpdate() {
	    	
	    	getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
			thriveHeaderMenuPage = login.login(saLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Account Manager -> Invited Account Manager");
			invitedAccountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickInvitedAccountManager();
			
			getExtentTestLogger().log(Status.PASS, "Capture current invited account managers data count");
			currentDataCount = userManagementCommonPage.captureCurrentDataCount();

			getExtentTestLogger().log(Status.PASS, "SA user invite account manager");
			headers.put("Authorization", getApiAuthCode());
			inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
			ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
			response = inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);

			getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
			Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");
			
			getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Account Manager -> Invited Account Manager");
			thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickInvitedAccountManager();
			
			getExtentTestLogger().log(Status.PASS, "Validate invited Account Manager data gets updated");
			usersPage.validateDataUpdate(currentDataCount);
	    }

}

