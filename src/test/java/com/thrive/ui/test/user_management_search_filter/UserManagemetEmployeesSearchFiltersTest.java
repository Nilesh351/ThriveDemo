package com.thrive.ui.test.user_management_search_filter;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.enterprise.EnterpriseEmployeePage;
import com.thrive.ui.page.user_management.search_filter.ArchivedEmployeesPage;

public class UserManagemetEmployeesSearchFiltersTest extends UserManagementCommonPage{


	    ThriveLoginPage login = new ThriveLoginPage();
		LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
		ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
		EnterpriseEmployeePage employeePage = new EnterpriseEmployeePage();
		UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
		ArchivedEmployeesPage archivedEmployeesPage = new ArchivedEmployeesPage();
		String email = "test.auto.enterprise2@yopmail.com";
		int paginationValue = 100;
		ExtentTest extentTestLocal;
		String value ="500";
		
		@Test(description = "Validate EA search employee successfylly")
		public void validateEmployeeInviteErrorvalidationEA() {
			
			getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
			
			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Enterprise navigates to Employees -> Invited Employees");
			employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
			
			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides email address");
			employeePage.setSearch(email);
			
			getExtentTestLogger().log(Status.PASS, "Enterprise Account Manager searched successfully");
			employeePage.validateEmployeePresent(email);
		}
		
		@Test(description = "Validate Filter By Region on Employees page")
		public void validateEmployeesFilterByRegion() {
			
			String region = ThriveAppSharedData.REGION.getValue();
			
			getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
			
			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Enterprise navigates to Employees -> Invited Employees");
			employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
			
			getExtentTestLogger().log(Status.PASS, "Enterprise selects region from Filter By Region dropdown");
			employeePage.clickFilterByRegion().selectRegion(region);
			
			getExtentTestLogger().log(Status.PASS, "Validate only selected regions employees data get displayed");
			employeePage.validateSelectedRegionPresent(region,paginationValue);
		}
		
		
		@Test(description = "Validate Filter By Department on Employees page")
		public void validateEmployeesFilterByDepartment() {
			
			String department = ThriveAppSharedData.DEPARTMENT.getValue();
			
			getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
			
			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Enterprise navigates to Employees -> Invited Employees");
			employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
			
			getExtentTestLogger().log(Status.PASS, "Enterprise selects department from Filter By Department dropdown");
			employeePage.clickFilterByDepartment().selectDepartment(department);
			
			getExtentTestLogger().log(Status.PASS, "Validate only selected departments employees data get displayed");
			employeePage.validateSelectedDepartmentPresent(department,paginationValue);
		}
	

		@Test(description = "Validate clear filter clears all applied filters for Employees tab")
		public void validateClearFiltersEmployees() {

			String department = ThriveAppSharedData.DEPARTMENT.getValue();
			
			getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Employees");
			employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

			getExtentTestLogger().log(Status.PASS, "Superadmin provides the Employees to be searched and filtered");
			employeePage.setSearch(clientUser);
			employeePage.clickFilterByDepartment().selectDepartment(department);
			employeePage.clickClearFilters();

			getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
			employeePage.validateClearFilter();

		}
		
		@Test(description = "Validate superadmin scroll between the employees list successfully")
		public void validateScrollRightListEmployees() {

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees");
			employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on scroller");
			employeePage.clickRightScrollButton();

			getExtentTestLogger().log(Status.PASS, "Validate columns changed after scrollingS");
			employeePage.validateScrollColumnsPresent();

		}
		
		
		@Test(description = "Validate pagination records match with selected pagination for employees tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
		public void validatePaginationRecordsEmployees(int val) {

			extentTestLocal = extentReports.createTest("Selected pagination" + val,
					"pagination " + val + "selected successfully");
			ExtentReportUtils.setExtentTest(extentTestLocal);

			LOGGER.info("onTestStart method pagination is matching");

			getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees");
			employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provide the pagination value ");
			employeePage.scrollInVerticalDirection(value).selectPaginationValue(val);

			getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
			employeePage.validatePaginationRecords(val);
		}
		
		
		@Test(description = "Validate actions dropdwown contains all Specicifed options for Archived Employees tab")
		public void validateActionsDropdownOptionsArchivedEmployees() {

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Archived Clients");
			thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on actions dropdwon");
			archivedEmployeesPage.clickActionsDropdown();

			getExtentTestLogger().log(Status.PASS,"Validate download, delete and Un-archive options present in actions dropdown");
			archivedEmployeesPage.validateActionDropdownElements();
		}
		
}
