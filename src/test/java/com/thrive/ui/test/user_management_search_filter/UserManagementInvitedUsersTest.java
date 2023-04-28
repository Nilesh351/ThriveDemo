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
import com.thrive.api.test.user_invite_register.ApiInviteAccountManagerTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.EnterpriseInviteEmployeePage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedUsersPage;
import com.thrive.ui.page.user_management.search_filter.AccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

import io.restassured.response.Response;

public class UserManagementInvitedUsersTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	LoginDetails amLoginDetails = new LoginDetails(accountManagerUser,accountManagerPassword);
	InternalCoachesPage internalCoachesPage= new InternalCoachesPage();
	InviteCoachPage inviteCoachPage ;
	EnterpriseInviteEmployeePage enterpriseInviteEmployeePage  = new EnterpriseInviteEmployeePage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	CoachInviteDetails internalCoachInviteDetails = TestDataGenerator.generateInternalCoachInviteDetails(false);
	String userRole = ThriveAppSharedData.ROLE.getValue();
	AccountManagersPage accountManagersPage = new AccountManagersPage();
	InviteAccountManagerPage inviteAccountManagerPage = new InviteAccountManagerPage();
	InviteEnterprisePage inviteEnterprisePage;
	GlobalCoachesPage globalCoachesPage;
	ClientsPage clientPage = new ClientsPage();
	InviteClientPage inviteClientPage = new InviteClientPage();
	String coachGlobalFirstName =  globalCoachName.split("First")[0];
	String eaFirstName = eaImmutableName.split("First")[0];
	ThriveLoginPage login = new ThriveLoginPage();
	InvitedUsersPage invitedUsersPage = new InvitedUsersPage();
	InviteAMRequest inviteAMReqBody;
	UsersPage usersPage = new UsersPage();
	Response response;
	Map<String, String> headers = new HashMap<>();
	String searchInput = "test-auto";
	InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
	InviteEnterpriseDetails inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();;
	CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
	InviteAccountManagerDetails inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();
	ExtentTest extentTestLocal;
	String value ="1000";
	String currentDataCount;
	String updatedDataCount;
	InviteEmployeeDetails employeeDeatils = TestDataGenerator.generateInviteEmployeeDetails();
	
	@Test(description="Validate search box can accept any input for superadmin")
	public void validateInvitedSearchBoxSA() {
		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails).validateLoginSuccessful();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> internal coaches");
		this.internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from action dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
		inviteCoachPage.sendInternalCoachInvite(internalCoachInviteDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Search user with email id");
		invitedUsersPage.setSearch(internalCoachInviteDetails.getEmailAddress().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Validate search box can accept any input and search result displayed");
		invitedUsersPage.validateUserEmailPresent(internalCoachInviteDetails.getEmailAddress().toLowerCase());
		
	}
	
	@Test(description="Validate search box can accept any input for enterprise admin")
	public void validateInvitedSearchBoxEA() {
		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails).validateLoginSuccessful();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Search user with email id");
		invitedUsersPage.setSearch(employeeDeatils.getEmailAddress().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Validate search box can accept any input and search result displayed");
		invitedUsersPage.validateUserEmailPresent(employeeDeatils.getEmailAddress().toLowerCase());
		
	}
	
	@Test(description="Validate search box can accept partial input for superadmin")
	public void validatePartialInputSearchBoxSA() {
		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails).validateLoginSuccessful();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> internal coaches");
		this.internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from action dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
		inviteCoachPage.sendInternalCoachInvite(internalCoachInviteDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		invitedUsersPage.setSearch(internalCoachInviteDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Validate search box can accept partial input and search result displayed");
		invitedUsersPage.validateUserNamePresent(internalCoachInviteDetails.getFirstName());
	}
	
	
	@Test(description="Validate search box can accept partial input for superadmin")
	public void validatePartialInputSearchBoxEA() {
		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails).validateLoginSuccessful();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		invitedUsersPage.setSearch(employeeDeatils.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Validate search box can accept partial input and search result displayed");
		invitedUsersPage.validateUserNamePresent(employeeDeatils.getFirstName());
	}
	
	
	@Test(description="Validate Page View revert to Default after input cleared superadmin")
	public void validatePageViewRevertToDefaultSA() {
		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails).validateLoginSuccessful();
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		int expected = invitedUsersPage.getAllRecordsPresent();
		invitedUsersPage.setSearch(userRole);
		invitedUsersPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate search box can accept partial input and search result displayed");
		invitedUsersPage.validateRevertToDefaultPage(expected);
		
	}
	
	@Test(description="Validate Page View revert to Default after input cleared superadmin")
	public void validatePageViewRevertToDefaultEA() {
		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails).validateLoginSuccessful();
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		int expected = invitedUsersPage.getAllRecordsPresent();
		invitedUsersPage.setSearch(userRole);
		invitedUsersPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate search box can accept partial input and search result displayed");
		invitedUsersPage.validateRevertToDefaultPage(expected);
		
	}
	
	
	@Test(description="Validate clear filter clears all applied filters SA")
	public void validateClearFiltersSA() {		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails).validateLoginSuccessful();
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		invitedUsersPage.setSearch(eaFirstName);
		invitedUsersPage.selectUserRole(userRole);
		invitedUsersPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		invitedUsersPage.validateClearFilter();
		
	}
	
	@Test(description="Validate clear filter clears all applied filters EA")
	public void validateClearFiltersEA() {		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails).validateLoginSuccessful();
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Search user with partial input");
		invitedUsersPage.setSearch(eaFirstName);
		invitedUsersPage.selectUserRole(userRole);
		invitedUsersPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		invitedUsersPage.validateClearFilter();
	}
	
	@Test(description = "Validate SA successfully download all users records")
	public void validateUsersAllRecordsDownloadSA() {	
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
			
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Click on download button");
		invitedUsersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		invitedUsersPage.validateRecordsDownloaded();
		
	}
	
	@Test(description = "Validate EA successfully download all users records")
	public void validateUsersAllRecordsDownloadEA() {	
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
			
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Click on download button");
		invitedUsersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		invitedUsersPage.validateRecordsDownloaded();
		
	}
	
	@Test(description = "Validate SA successfully download filtered users records")
	public void validateUsersFilteredRecordsDownloadSA() {	
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
			
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Click download");
		invitedUsersPage.selectUserRole(userRole);
		invitedUsersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		invitedUsersPage.validateRecordsDownloaded();
		
	}
	
	@Test(description = "Validate EA successfully download filtered users records")
	public void validateUsersFilteredRecordsDownloadEA() {	
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters -Invited Users");
			
		getExtentTestLogger().log(Status.PASS, "Logging in with enterpise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Click download");
		invitedUsersPage.selectUserRole(userRole);
		invitedUsersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		invitedUsersPage.validateRecordsDownloaded();
		
	}
	
	@Test(description = "Validate SA successfully download filtered users records and matches details")
	public void validateUsersFilteredRecordsDownloadCountSA() throws IOException {		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Click download");
		invitedUsersPage.selectUserRole(userRole);
		invitedUsersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		invitedUsersPage.validateDownloadAllRecords();
		
	}
	
	@Test(description = "Validate EA successfully download filtered users records and matches details")
	public void validateUsersFilteredRecordsDownloadCountEA() throws IOException {		
		getExtentTestLogger().assignCategory("UserManagement SearchAndFilters - Invited Users");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Click download");
		invitedUsersPage.selectUserRole(userRole);
		invitedUsersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		invitedUsersPage.validateDownloadAllRecords();
		
	}
	
	
	@Test(description =  "Validate 'Search By Role' filter data and displayed as per selected user For SA", dataProvider = "invitedUsersRoleDataSA", dataProviderClass = LoginTestDataProvider.class) 
	public void validateUsersSearchByRoleSA(String role) {
		
		extentTestLocal = extentReports.createTest(role, role+"Test");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method - " + role + " start");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Select user role");
		invitedUsersPage.selectUserRole(role);
		
		getExtentTestLogger().log(Status.PASS, "Validate only searched users role data is displayed");
		invitedUsersPage.validateUserRoleDataDisplayed(role);
	}
	
	@Test(description =  "Validate 'Search By Role' filter data and displayed as per selected user For EA", dataProvider = "invitedUsersRoleDataEA", dataProviderClass = LoginTestDataProvider.class) 
	public void validateUsersSearchByRoleEA(String role) {
		
		extentTestLocal = extentReports.createTest(role, role+"Test");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method - " + role + " start");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Select user role");
		invitedUsersPage.selectUserRole(role);
		
		getExtentTestLogger().log(Status.PASS, "Validate only searched users role data is displayed");
		invitedUsersPage.validateUserRoleDataDisplayed(role);
	}
	
	@Test(description =  "Validate pagination records match with selected pagination SA", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class) 
	public void validatePaginationRecordsSA(int val) {
		
		extentTestLocal = extentReports.createTest("Selected pagination"+val, "pagination "+val+"selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "provide the user name");
		invitedUsersPage.scrollInVerticalDirection(value).selectPaginationValue(val);
		
		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedUsersPage.validatePaginationRecords(val);
		
	}
	
	@Test(description =  "Validate pagination records match with selected pagination EA", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class) 
	public void validatePaginationRecordsEA(int val) {
		
		extentTestLocal = extentReports.createTest("Selected pagination"+val, "pagination "+val+"selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "provide the user name");
		invitedUsersPage.scrollInVerticalDirection(value).selectPaginationValue(val);
		
		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedUsersPage.validatePaginationRecords(val);
		
	}
	
	@Test(description =  "Validate pagination works fine for previous and next and records changes properly for SA") 
	public void validatePaginationNextPervLinkSA() {
		
		LOGGER.info("onTestStart method user present");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "click on last page button");
		invitedUsersPage.clickLastPageButton();
		invitedUsersPage.validateLastPageRecords();
		
		getExtentTestLogger().log(Status.PASS, "click on first page button");
		invitedUsersPage.clickFirstPageButton();
		invitedUsersPage.validateFirstPageRecords();
		
		getExtentTestLogger().log(Status.PASS, "click on next page changes the records");
		invitedUsersPage.verifyPaginationData();
	}
	
	@Test(description =  "Validate pagination works fine for previous and next and records changes properly for EA") 
	public void validatePaginationNextPervLinkEA() {
		
		LOGGER.info("onTestStart method user present");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "click on last page button");
		invitedUsersPage.clickLastPageButton();
		invitedUsersPage.validateLastPageRecords();
		
		getExtentTestLogger().log(Status.PASS, "click on first page button");
		invitedUsersPage.clickFirstPageButton();
		invitedUsersPage.validateFirstPageRecords();
		
		getExtentTestLogger().log(Status.PASS, "click on next page changes the records");
		invitedUsersPage.verifyPaginationData();
	}
	
	
	@Test(description =  "Validate user can search all types of users for SA'") 
	public void validateSearchUserTypesSA() {
		
		LOGGER.info("onTestStart method user present");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "search invited account manager searched successfully");
		inviteAccountManager();
		
		getExtentTestLogger().log(Status.PASS, "search invited client searched successfully");
		inviteClient();
		
		getExtentTestLogger().log(Status.PASS, "search invited coach searched successfully");
		inviteCoach();
		
		getExtentTestLogger().log(Status.PASS, "search invited internal coach searched successfully");
		inviteInternalCoach();
		
		getExtentTestLogger().log(Status.PASS, "search invited enterprise searched successfully");
		inviteEnterprise();
		
	}
	
	
	@Test(description =  "Validate user can search all types of users for EA'") 
	public void validateSearchUserTypesEA() {
		
		LOGGER.info("onTestStart method user present");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "search invited employee searched successfully");
		inviteEmployee();
		
		getExtentTestLogger().log(Status.PASS, "search invite internal coach searched successfully");
		inviteInternalCoachEA();
		
	}
	
	@Test(description="Validate Invited Users data gets updated after inviting new user")
	public void validateInvitedusersDataupdate() {
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		invitedUsersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Capture current Invited Users data count");
		currentDataCount = usersPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "Invite AM");
		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		response = inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Users");
		invitedUsersPage = thriveHeaderMenuPage.clickUserManagementLink().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "Validate All Users data gets updated");
		usersPage.validateDataUpdate(currentDataCount);
	}
		
	
	private UserManagementInvitedUsersTest userSearchedSuccessfully(String user) {
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invited Users");
		invitedUsersPage=thriveHeaderMenuPage.navigateToUsers().clickUsers().clickInvitedUsers();
		
		getExtentTestLogger().log(Status.PASS, "provide the user name");
		invitedUsersPage.setSearch(user);
		
		getExtentTestLogger().log(Status.PASS, "Validate user searched successfully.");
		invitedUsersPage.validateSearchBoxInput(user);
		
		return this;
	}
	
	
	private UserManagementInvitedUsersTest inviteAccountManager() {
		
		String accountManagerInviteToaster = ThriveAppSharedData.INVITE_ACCOUNT_MANAGER_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> account managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS,"Superadmin selects invite account manager option from actions dropdown");
		inviteAccountManagerPage = userManagementCommonPage.selectInviteAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invite");
		inviteAccountManagerPage.sendInvite(inviteAccountManagerDetails);
		String email = inviteAccountManagerPage.validateAMInviteToaster(accountManagerInviteToaster,inviteAccountManagerDetails.getEmailAddress());
		userSearchedSuccessfully(email.toLowerCase());
		
		return this;
	}
	
	private UserManagementInvitedUsersTest inviteClient() {
		
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite client option from actions dropdown");
		inviteClientPage = userManagementCommonPage.selectInviteClientAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invite");
		inviteClientPage.sendInvite(inviteClientDetails);
		String email = inviteClientPage.validateClientInviteToaster(clientInviteToaster,inviteClientDetails.getEmailAddress());
		userSearchedSuccessfully(email.toLowerCase());
		
		return this;
	}
	
	private UserManagementInvitedUsersTest inviteCoach() {
		
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
			
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from actions dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
		inviteCoachPage.sendGlobalCoachInvite(globalCoachInviteDetails);
		String email = inviteCoachPage.validateCoachInviteToaster(coachInviteToaster,globalCoachInviteDetails.getEmailAddress());
		userSearchedSuccessfully(email.toLowerCase());
		
		return this;
	}
	
	private UserManagementInvitedUsersTest inviteInternalCoach() {
		
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> internal coaches");
		this.internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from action dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
		inviteCoachPage.sendInternalCoachInvite(internalCoachInviteDetails);
		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,internalCoachInviteDetails.getEmailAddress());
		userSearchedSuccessfully(email.toLowerCase());
		
		return this;
	}
	
	private UserManagementInvitedUsersTest inviteEnterprise() {
		
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> enterprises");
		thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite enterprise option from actions dropdown");
		inviteEnterprisePage = userManagementCommonPage.selectInviteEnterpriseAction();
				
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		String email = inviteEnterprisePage.validateEntInviteToaster(entInviteToaster,inviteEnterpriseDetails.getEmailAddress());
		userSearchedSuccessfully(email.toLowerCase());
		
		return this;
	}
	
	private UserManagementInvitedUsersTest inviteEmployee() {
		
		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);
		String email = enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,employeeDeatils.getEmailAddress());
		userSearchedSuccessfully(email.toLowerCase());
		
		return this;
	}
	
	
	private UserManagementInvitedUsersTest inviteInternalCoachEA() {
		
			String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		
			getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> internal coaches");
			this.internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLinkEA();
			
			getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from action dropdown");
			inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
			
			getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
			inviteCoachPage.sendInternalCoachInviteEA(internalCoachInviteDetails);
			String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,internalCoachInviteDetails.getEmailAddress());
			userSearchedSuccessfully(email.toLowerCase());
			
			return this;
		}
	
}
