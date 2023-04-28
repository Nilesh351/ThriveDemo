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
import com.thrive.ui.page.invite.InvitedGlobalCoach;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;

public class UserManagementInvitedCoachSearchFilterTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	InvitedGlobalCoach invitedGlobalCoach = new InvitedGlobalCoach();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	GlobalCoachesPage globalCoachesPage = new GlobalCoachesPage();
	InviteCoachPage inviteCoachPage;
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	ExtentTest extentTestLocal;
	String value = "1000";

	@Test(description = "Validate SA successfully search the invited global coach")
	public void validateInvitedGlobalCoachSearch() {
		getExtentTestLogger().assignCategory("UserManagement Actions- invited Global Coach");

		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited global coach to be searched");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		inviteCoachPage.sendGlobalCoachInvite(globalCoachInviteDetails);
		alertsAndMessagesPage.validateToaster(coachInviteToaster);
		thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();
		invitedGlobalCoach.setSearch(globalCoachInviteDetails.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Invited global coach searched successfully");
		invitedGlobalCoach.validateEmailPresent(globalCoachInviteDetails.getEmailAddress());
	}

	@Test(description = "Validate pagination records match with selected pagination for Invited coaches tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsInvitedCoaches(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited coaches");
		invitedGlobalCoach = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		invitedGlobalCoach.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedGlobalCoach.validatePaginationRecords(val);

	}

	@Test(description = "Validate actions dropdown contains all Specified options for Invited Coaches tab")
	public void validateActionsDropdownOptionsInvitedCoaches() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited coaches");
		invitedGlobalCoach = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		invitedGlobalCoach.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download,reinvite, and delete options present in actions dropdown");
		invitedGlobalCoach.validateActionDropdownElements();
	}

	@Test(description = "Validate clear filter clear all applied filters successfully")
	public void validateClearFiltersInvitedCoaches() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited coaches");
		invitedGlobalCoach = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the coach to be searched and filtered");
		invitedGlobalCoach.setSearch(globalCoachUser);
		invitedGlobalCoach.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_GLOBAL_COACH.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin click on clear filters");
		invitedGlobalCoach.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		invitedGlobalCoach.validateClearFilters();

	}

	@Test(description = "Validate superadmin 'filter By coach type training coach' successfully")
	public void validateFilterByTrainingCoach() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited coaches");
		invitedGlobalCoach = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();

		getExtentTestLogger().log(Status.PASS, "Superadmin filters by training coach");
		invitedGlobalCoach.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_TRAINING_COACH.getValue());

		getExtentTestLogger().log(Status.PASS, "Validate filter by training coach successfully.");
		invitedGlobalCoach.validateTrainingCoachPresent();

	}

	@Test(description = "Validate superadmin 'filter By coach type global coach' successfully")
	public void validateFilterByGlobalCoach() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited coaches");
		invitedGlobalCoach = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by global coach");
		invitedGlobalCoach.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_GLOBAL_COACH.getValue());

		getExtentTestLogger().log(Status.PASS, "validate filter by global coach successfully.");
		invitedGlobalCoach.validateGlobalCoachPresent();
	}
	
	

}
