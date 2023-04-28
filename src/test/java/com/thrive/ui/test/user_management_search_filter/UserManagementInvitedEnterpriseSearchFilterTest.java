package com.thrive.ui.test.user_management_search_filter;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedEnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;

public class UserManagementInvitedEnterpriseSearchFilterTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveLoginPage login = new ThriveLoginPage();
	InviteEnterprisePage inviteEnterprisePage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InvitedEnterprisesPage invitedEnterprisesPage = new InvitedEnterprisesPage();
	InviteEnterpriseDetails inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	String archivedEnt = ThriveAppSharedData.ARCHIVED_ENT_NAME.getValue();
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	ExtentTest extentTestLocal;
	String value = "500";

	@Test(description = "Validate SA successfully search the invited Enterprise")
	public void validateInvitedEnterpriseSearch() {
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> Invite an Enterprise");
		thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited enterprise to be searched");
		inviteEnterprisePage = userManagementCommonPage.selectInviteEnterpriseAction();
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		String email = inviteEnterprisePage.validateEntInviteToaster(entInviteToaster,
				inviteEnterpriseDetails.getEmailAddress());
		thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickInvitedEnterprise();
		invitedEnterprisesPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS, "Invited enterprise searched successfully");
		invitedEnterprisesPage.validateEnterprisePresent(email);

	}

	@Test(description = "Validate clear filter clears all applied filters for invite an enterprise tab")
	public void validateClearFiltersInviteAnEnterprise() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invite an enterprise");
		invitedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu()
				.clickInvitedEnterprise();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the invited enterprise to be searched");
		invitedEnterprisesPage.setSearch(eaMutableEmail);
		invitedEnterprisesPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		invitedEnterprisesPage.validateClearFilter();

	}

	@Test(description = "Validate pagination records match with selected pagination for invited enterprise tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsInvitedEnterprise(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invite an Enterprise");
		invitedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu()
				.clickInvitedEnterprise();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		invitedEnterprisesPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedEnterprisesPage.validatePaginationRecords(val);

	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for Invited enterprises tab")
	public void validateActionsDropdownOptionsInvitedEnterprises() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invite an Enterprise");
		invitedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu()
				.clickInvitedEnterprise();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		invitedEnterprisesPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download,delete and re-invite options present in actions dropdown");
		invitedEnterprisesPage.validateActionDropdownElements();
	}

	@Test(description = "Validate Invited Enterprise data gets updated when new enterprise get invited")
	public void validateInvitedEnterpriseDataUpdateValueChanges() {
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invite an Enterprise");
		invitedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu()
				.clickInvitedEnterprise();

		getExtentTestLogger().log(Status.PASS, "Superadmin gets the total records count");
		String previousCount = invitedEnterprisesPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite Enterprise");
		inviteEnterprisePage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu()
				.clickInviteEnterprise();

		getExtentTestLogger().log(Status.PASS, "Enter Details for invite Enterprise");
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		inviteEnterprisePage.validateEntInviteToaster(entInviteToaster, inviteEnterpriseDetails.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invite an Enterprise");
		invitedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu()
				.clickInvitedEnterprise();

		getExtentTestLogger().log(Status.PASS, "Invited Enterprises data count get updated successsfully");
		invitedEnterprisesPage.validateDataUpdate(previousCount);

	}

}
