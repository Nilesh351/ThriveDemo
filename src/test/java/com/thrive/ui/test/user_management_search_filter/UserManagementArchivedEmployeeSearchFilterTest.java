package com.thrive.ui.test.user_management_search_filter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteEmployeeRequest;
import com.thrive.api.pojos.RegisterClientRequest;
import com.thrive.api.test.user_invite_register.ApiInviteEmployeeTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.EmployeeCreditPage;
import com.thrive.ui.page.enterprise.EnterpriseEmployeePage;
import com.thrive.ui.page.invite.EnterpriseInvitedEmployeePage;
import com.thrive.ui.page.user_management.search_filter.ArchivedEmployeesPage;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementArchivedEmployeeSearchFilterTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	ArchivedEmployeesPage archivedEmployeesPage = new ArchivedEmployeesPage();
	EnterpriseInvitedEmployeePage invitedEmployee = new EnterpriseInvitedEmployeePage();
	InviteEmployeeDetails employeeDeatils = TestDataGenerator.generateInviteEmployeeDetails();
	EnterpriseEmployeePage employeePage = new EnterpriseEmployeePage();
	ExtentTest extentTestLocal;
	String value = "500";
	EmployeeCreditPage employeeCreditPage = new EmployeeCreditPage();
	RegisterClientRequest registerClientReqBody;
	InviteEmployeeRequest inviteEmployeereqBody;
	Response response;
	List<String> region = new ArrayList<String>();
	List<String> department = new ArrayList<String>();
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	
	
	@Test(description = "Validate Enterprise admin search archived employee successfully")
	public void validateEASearchEmployee() {

		getExtentTestLogger().log(Status.PASS, "EA register Employee successfully");
		headers.put("Authorization", getApiAuthCodeEA());
		inviteEmployeereqBody = ApiTestDataGenerator.generateInviteEmployeeReqBody();
		ApiInviteEmployeeTest inviteEmployeeTest = new ApiInviteEmployeeTest();
		inviteEmployeeTest.getInviteEmployeeResponse(inviteEmployeereqBody, headers);
		registerClientReqBody = ApiTestDataGenerator.generateRegisterClientReqBody();
		registerClientReqBody.setFirstName(inviteEmployeereqBody.getFirstName());
		registerClientReqBody.setLastName(inviteEmployeereqBody.getLastName());
		registerClientReqBody.setEmail(inviteEmployeereqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "EA register client successfully");
		response = getRegisterClientResponse(registerClientReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "EA navigates to employee");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "EA selects the employee to archive");
		employeePage.setSearch(inviteEmployeereqBody.getEmail());
		employeePage.clickEmployeeCheckboxEmail(inviteEmployeereqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "EA select archive option from actions dropdown");
		employeePage.selectArchiveEmployeeAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate employee archived successfuly");
		employeePage.validateEmployeeArchiveToaster();

		getExtentTestLogger().log(Status.PASS, "EA selects archived employee tab");
		employeePage.clickArchivedLink();
		
		getExtentTestLogger().log(Status.PASS, "EA search archived employee successfully");
		archivedEmployeesPage.setSearch(inviteEmployeereqBody.getEmail());
		archivedEmployeesPage.validateArchivedEmployeePresent(inviteEmployeereqBody.getEmail());
	
	}
	
	@Test(description = "Validate Enterprise admin 'Filter By Region' successfully")
	public void validateEAFilterByRegion() {
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "EA navigates to archived employee tab");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();
		
		getExtentTestLogger().log(Status.PASS, "EA Filter by region");
		region.add(ThriveAppSharedData.REGION.getValue());
		archivedEmployeesPage.selectFilterByRegion(region);
		
		getExtentTestLogger().log(Status.PASS, "validate Filter by region successfully");
		archivedEmployeesPage.validateRegionPresent(ThriveAppSharedData.REGION.getValue());
		
	}
	
	@Test(description = "Validate Enterprise admin 'Filter By Department' successfully")
	public void validateEAFilterByDepartment() {
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "EA navigates to archived employee tab");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();
		
		getExtentTestLogger().log(Status.PASS, "EA Filter by region");
		department.add(ThriveAppSharedData.DEPARTMENT_FINANCE.getValue());
		archivedEmployeesPage.selectFilterByDepartment(department);
		
		getExtentTestLogger().log(Status.PASS, "validate Filter by department successfully");
		archivedEmployeesPage.validateDepartmentPresent(ThriveAppSharedData.DEPARTMENT_FINANCE.getValue());
		
	}
	
	
	@Test(description = "Validate clear filter clear all applied filters successfully")
	public void validateClearFiltersArchivedEmployee() {
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "EA navigates to archived employee tab");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides the coach to be searched and filtered");
		archivedEmployeesPage.setSearch(eaUserImmutableEmail);
		region.add(ThriveAppSharedData.REGION.getValue());
		archivedEmployeesPage.selectFilterByRegion(region);
		department.add(ThriveAppSharedData.DEPARTMENT_FINANCE.getValue());
		archivedEmployeesPage.selectFilterByDepartment(department);
		
		getExtentTestLogger().log(Status.PASS, "EA click on clear filters");
		archivedEmployeesPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		archivedEmployeesPage.validateClearFilters();
		
	}

	@Test(description = "Validate Enterprise admin scroll between the employees list successfully")
	public void validateScrollRightListArchivedEmployees() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Archived Employees");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on scroller");
		archivedEmployeesPage.clickRightScrollButton();

		getExtentTestLogger().log(Status.PASS, "Validate columns changed after scrolling");
		archivedEmployeesPage.validateScrollColumnsPresent();
	}
	
	@Test(description = "Validate actions dropdwown contains all Specicifed options for Archived employees tab")
	public void validateActionsDropdownOptionsArchivedEmployees() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Archived Employees");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on actions dropdwon");
		archivedEmployeesPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,"Validate download, delete and Un-archive options present in actions dropdown");
		archivedEmployeesPage.validateActionDropdownElements();
	}
	
	@Test(description = "Validate pagination records match with selected pagination for employees tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsEmployees(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with emterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provide the pagination value ");
		employeePage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		employeePage.validatePaginationRecords(val);
	}
	
	private Response getRegisterClientResponse(RegisterClientRequest registerClientReqBody) {
		String token = getRegisterTokenClient(registerClientReqBody.getFirstName());
		return RestAssured.given().queryParam("token", token).spec(getRegisterUserReqSpec()).and().when()
				.body(registerClientReqBody, ObjectMapperType.GSON).post("");

	}

}