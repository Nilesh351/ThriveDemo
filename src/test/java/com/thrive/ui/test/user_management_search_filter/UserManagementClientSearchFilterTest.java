package com.thrive.ui.test.user_management_search_filter;

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
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.enterprise.EnterpriseEmployeePage;
import com.thrive.ui.page.invite.EnterpriseInviteEmployeePage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedEnterprisesPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementClientSearchFilterTest extends UserManagementCommonPage {

	ClientsPage clientsPage = new ClientsPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	EnterpriseInviteEmployeePage enterpriseInviteEmployeePage  = new EnterpriseInviteEmployeePage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveLoginPage login = new ThriveLoginPage();
	InviteEnterprisePage inviteEnterprisePage;
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InvitedEnterprisesPage InvitedEnterprisesPage = new InvitedEnterprisesPage();
	MailboxPage mailboxPage = new MailboxPage();
	EnterpriseEmployeePage employeePage = new EnterpriseEmployeePage();
	UsersPage usersPage = new UsersPage();
	InviteEnterpriseDetails inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
	InviteEmployeeDetails employeeDeatils = TestDataGenerator.generateInviteEmployeeDetails();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	String archivedEnt = ThriveAppSharedData.ARCHIVED_ENT_NAME.getValue();
	RegisterClientRequest registerClientReqBody;
	InviteClientRequest inviteClientreqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	ExtentTest extentTestLocal;
	String value ="500";
	String currentDataCount;
	
	
	@Test(description = "Validate SA successfully search the client")
	public void validateClientSearch() {

		getExtentTestLogger().assignCategory("UserManagement Actions- client");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the client to be searched");
		userManagementCommonPage = this.clientsPage.setSearch(clientUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin Account Manager searched successfully");
		clientsPage.validateClientPresent(clientUser);
	}

	@Test(description = "Validate 'Filter By Enterprise' filter data successfully")
	public void validateFilterByEnterprise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- client");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise to filter");
		userManagementCommonPage = clientsPage.selectFilterByEnterprise(enterprise);

		getExtentTestLogger().log(Status.PASS, "Validate enterprise filter successfully");
		clientsPage.validateEnterpriseNamePresent(enterprise);
	}

	@Test(description = "Validate clear filter clears all applied filters for Clients tab")
	public void validateClearFiltersClients() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the clients to be searched and filtered");
		clientsPage.setSearch(clientUser);
		clientsPage.selectFilterByEnterprise(enterprise);
		clientsPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		clientsPage.validateClearFilter();

	}

	@Test(description = "Validate pagination records match with selected pagination for clients tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsClients(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		clientsPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		clientsPage.validatePaginationRecords(val);
	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for clients tab")
	public void validateActionsDropdownOptionsClients() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		clientsPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download, invite, allocate credits,remove credits and archive options present in actions dropdown");
		clientsPage.validateActionDropdownElements();
	}

	@Test(description = "Validate superadmin scroll between the clients list successfully")
	public void validateScrollRightListClients() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on scroller");
		clientsPage.clickRightScrollButton();

		getExtentTestLogger().log(Status.PASS, "Validate columns changed after scrollingS");
		clientsPage.validateScrollColumnsPresent();

	}

	@Test(description = "Validate Client data gets updated when new Client get added")
	public void validateClientDataUpdateValueChanges() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin gets the total records count");
		String previousCount = clientsPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "SA register Client successfully");
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
		Assert.assertTrue(response.statusCode() == 200);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Clients data count get updated successsfully");
		clientsPage.validateDataUpdate(previousCount);

	}

	private Response getRegisterClientResponse(RegisterClientRequest registerClientReqBody) {
		String token = getRegisterTokenClient(registerClientReqBody.getFirstName());
		return RestAssured.given().queryParam("token", token).spec(getRegisterUserReqSpec()).and().when()
				.body(registerClientReqBody, ObjectMapperType.GSON).post("");

	}
	
	 @Test(description="Validate employees data gets updated when new employee gets registered")
	 public void validateEmployeeDataUpdate() {
		 
		    String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		 
	    	getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
			thriveHeaderMenuPage = login.login(eaLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, " Enterprise admin navigates to Employees -> Invited Employees");
			employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
			
			getExtentTestLogger().log(Status.PASS, "Capture current employees data count");
			currentDataCount = userManagementCommonPage.captureCurrentDataCount();
			
			employeePage.clickInviteEmployee();
			
			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides all details and click on send invitation button");
			enterpriseInviteEmployeePage.sendInvite(employeeDeatils);
					
			getExtentTestLogger().log(Status.PASS, "Employee invited successfully");
			enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,employeeDeatils.getEmailAddress());
			thriveHeaderMenuPage.logout();
			
			getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
			mailboxPage =  new MailboxPage().searchClientMailAndClick(employeeDeatils.getFirstName());
			
			getExtentTestLogger().log(Status.PASS, "Enter employee information");
			empStatusPage = new RegisterClientInformationPage().submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());
			
			getExtentTestLogger().log(Status.PASS, "Enter employee employment information");
			careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());
			
			getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
			careerDetailsPage.registerClientInfo()
			.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());
			
			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Validate employee data get updated successfuly");
			employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

			usersPage.validateDataUpdate(currentDataCount);
	 }
	 
}
