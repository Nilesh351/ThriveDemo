package com.thrive.ui.test.user_management_search_filter;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.enterprise.EnterpriseEmployeePage;
import com.thrive.ui.page.invite.EnterpriseInviteEmployeePage;
import com.thrive.ui.page.invite.EnterpriseInvitedEmployeePage;

public class UserManagementInvitedEmployeeSearchFilterTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	EnterpriseInviteEmployeePage enterpriseInviteEmployeePage = new EnterpriseInviteEmployeePage();
	EnterpriseInvitedEmployeePage invitedEmployee = new EnterpriseInvitedEmployeePage();
	InviteEmployeeDetails employeeDeatils = TestDataGenerator.generateInviteEmployeeDetails();
	EnterpriseEmployeePage employeePage = new EnterpriseEmployeePage();
	ExtentTest extentTestLocal;
	String value = "500";
	
	
	@Test(description = "Validate EA search invited employee successfully.")
	public void validateEASearchInvitedEmployeeSuccessfully() {

		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "EA navigates to Employees -> Invited Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);

		getExtentTestLogger().log(Status.PASS, "Employee invited successfully");
		String email = enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,
				employeeDeatils.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Invited Employee searched successfully");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
		invitedEmployee = employeePage.clickInvitedEmployees();
		invitedEmployee.setSearch(email);
		invitedEmployee.validateInvitedEmployeeEmailPresent(email);
	}
	
	@Test(description = "Validate EA clear filters for invited employee successfully.")
	public void validateEAClearFiltersOfInvitedEmployeeSuccessfully() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Invited Employee searched successfully");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin search the employee");
		invitedEmployee.setSearch(clientName);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin Clicks on clear filters");
		invitedEmployee.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		invitedEmployee.validateResetFilters();
		
	}
	
	@Test(description = "Validate actions dropdwown contains all Specicifed options for Invited Employee tab")
	public void validateActionsDropdownInvitedEmployee() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Invited Employee searched successfully");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin click on actions dropdwon");
		invitedEmployee.clickActions();
		
		getExtentTestLogger().log(Status.PASS, "Validate download, Re-Invite and Delete options present in actions dropdown");
		invitedEmployee.validateActionDropdownElements();
		
	}
	
	@Test(description = "Validate pagination records match with selected pagination for invited Employee tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsInvitedEmployee(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Invited Employee searched successfully");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provide the pagination value ");
		invitedEmployee.scrollInVerticalDirection(value).selectPaginationValueEA(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedEmployee.validatePaginationRecords(val);

	}
	
	
	

}
