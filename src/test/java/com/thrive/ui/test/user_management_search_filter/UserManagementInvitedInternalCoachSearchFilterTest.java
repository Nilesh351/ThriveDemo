package com.thrive.ui.test.user_management_search_filter;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InvitedInternalCoachPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;

public class UserManagementInvitedInternalCoachSearchFilterTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	InvitedInternalCoachPage invitedInternalCoachPage = new InvitedInternalCoachPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	InternalCoachesPage internalCoachesPage = new InternalCoachesPage();
	InviteCoachPage inviteCoachPage;
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	CoachInviteDetails internalCoachInviteDetails = TestDataGenerator.generateInternalCoachInviteDetails(false);
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	ExtentTest extentTestLocal;
	String value = "1000";

	@Test(description = "Validate SA successfully search the invited Internal coach")
	public void validateInvitedInternalCoachSearch() {
		getExtentTestLogger().assignCategory("UserManagement Actions- invited Internal Coach");

		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited Internal coach to be searched");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.sendInternalCoachInvite(internalCoachInviteDetails);
		alertsAndMessagesPage.validateToaster(coachInviteToaster);
		thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickInvitedCoachesButton();
		inviteCoachPage.setSearch(internalCoachInviteDetails.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Invited internal coach searched successfully");
		invitedInternalCoachPage.validateEmailPresent(internalCoachInviteDetails.getEmailAddress());
	}

	@Test(description = "Validate pagination records match with selected pagination for Invited Internal coaches tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsInvitedCoaches(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> Internal coaches");
		invitedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
				.clickInvitedCoachesButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		invitedInternalCoachPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedInternalCoachPage.validatePaginationRecords(val);

	}

	@Test(description = "Validate actions dropdown contains all Specified options for Invited Coaches tab")
	public void validateActionsDropdownOptionsInvitedCoaches() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> Internal coaches");
		invitedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
				.clickInvitedCoachesButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		invitedInternalCoachPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download,reinvite, and delete options present in actions dropdown");
		invitedInternalCoachPage.validateActionDropdownElements();
	}

	@Test(description = "Validate clear filter clear all applied filters successfully")
	public void validateClearFiltersInvitedInternalCoaches() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> Internal coaches");
		invitedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
				.clickInvitedCoachesButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the coach to be searched and filtered");
		invitedInternalCoachPage.setSearch(internalCoachUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on clear filters");
		invitedInternalCoachPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		invitedInternalCoachPage.validateClearFilters();

	}
	
	
	@Test(description = "Validate Enterprise Admin successfully search the invited Internal coach")
	public void validateInvitedInternalCoachSearchEALogin() {
		getExtentTestLogger().assignCategory("UserManagement Actions- invited Internal Coach");

		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to user management -> Internal coaches");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin send invitation to internal coach");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.sendInternalCoachInviteEA(internalCoachInviteDetails);
		
		getExtentTestLogger().log(Status.PASS, "Validate invitation toaster");
		alertsAndMessagesPage.validateToaster(coachInviteToaster);
		
		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Invternal Coaches -> Invited Internal Coaches");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickInvitedCoachesButton();
		inviteCoachPage.setSearch(internalCoachInviteDetails.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Validate invited internal coach searched successfully");
		invitedInternalCoachPage.validateEmailPresent(internalCoachInviteDetails.getEmailAddress());
	}
	
	@Test(description = "Validate clear filter clear all applied filters successfully")
	public void validateClearFiltersInvitedInternalCoachesEALogin() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to user management -> Internal coaches");
		invitedInternalCoachPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink()
		.clickInvitedCoachesButton();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides the coach to be searched and filtered");
		invitedInternalCoachPage.setSearch(internalCoachUser);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin click on clear filters");
		invitedInternalCoachPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		invitedInternalCoachPage.validateClearFilters();

	}
	
	@Test(description = "Validate actions dropdown contains all Specified options for Invited Coaches tab")
	public void validateActionsDropdownOptionsInvitedCoachesEALogin() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to user management -> Internal coaches");
		invitedInternalCoachPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink()
		.clickInvitedCoachesButton();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on actions dropdwon");
		invitedInternalCoachPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,"Validate download,reinvite, and delete options present in actions dropdown");
		invitedInternalCoachPage.validateActionDropdownElements();
	}
	

	@Test(description = "Validate pagination records match with selected pagination for Invited Internal coaches tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsInvitedCoachesEALogin(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to user management -> Internal coaches");
		invitedInternalCoachPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink()
		.clickInvitedCoachesButton();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provide the pagination value ");
		invitedInternalCoachPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedInternalCoachPage.validatePaginationRecords(val);

	}

	
	
}
