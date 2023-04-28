package com.thrive.ui.test.user_management_search_filter;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedClientsPage;
import com.thrive.ui.page.invite.InvitedEnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;

public class UserManagementInvitedClientSearchFilterTest extends UserManagementCommonPage {

	ClientsPage clientsPage = new ClientsPage();
	InvitedClientsPage invitedClientsPage = new InvitedClientsPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveLoginPage login = new ThriveLoginPage();
	InviteEnterprisePage inviteEnterprisePage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InvitedEnterprisesPage InvitedEnterprisesPage = new InvitedEnterprisesPage();
	InviteClientPage inviteClientPage = new InviteClientPage();
	InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	String archivedEnt = ThriveAppSharedData.ARCHIVED_ENT_NAME.getValue();
	
	ExtentTest extentTestLocal;
	String value = "500";

	@Test(description = "Validate SA successfully search the Invited client")
	public void validateInvitedClientSearch() {
		
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory("UserManagement Actions- Invited Client");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Clients");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		inviteClientPage = userManagementCommonPage.selectInviteClientAction();
		inviteClientPage.sendInvite(inviteClientDetails);
		String email = inviteClientPage.validateClientInviteToaster(clientInviteToaster, inviteClientDetails.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the Invited client to be searched");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();
		invitedClientsPage.setSearch(inviteClientDetails.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Superadmin search invited client successfully");
		invitedClientsPage.validateInvitedClientPresent(email);
	}

	@Test(description = "Validate 'Filter By Enterprise' filter data successfully")
	public void validateFilterByEnterprise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Invited client");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise to filter");
		userManagementCommonPage = invitedClientsPage.selectFilterByEnterprise(enterprise);

		getExtentTestLogger().log(Status.PASS, "Validate enterprise filter successfully");
		invitedClientsPage.validateEnterpriseNamePresent(enterprise);
	}

	@Test(description = "Validate clear filter clears all applied filters for Invited Clients tab")
	public void validateClearFiltersEnterprises() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the Invited clients to be searched and filtered");
		invitedClientsPage.setSearch(clientUser);
		invitedClientsPage.selectFilterByEnterprise(enterprise);
		invitedClientsPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate fields cleared successfully");
		invitedClientsPage.validateClearFilter();

	}

	@Test(description = "Validate pagination records match with selected pagination for Invited clients tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsInvitedClients(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		invitedClientsPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		invitedClientsPage.validatePaginationRecords(val);
	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for Invited clients tab")
	public void validateActionsDropdownOptionsInvitedClients() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invited Clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		invitedClientsPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download, Re-Invite and Delete options present in actions dropdown");
		invitedClientsPage.validateActionDropdownElements();
	}
	
	@Test(description="Validate Invited clients data gets updated when new client get invited")
	public void validateInvitedClientsDataUpdateValueChanges() {		
		
		String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invite clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin gets the total records count");
		String previousCount = invitedClientsPage.captureCurrentDataCount();
				
		getExtentTestLogger().log(Status.PASS, "Go to user management  and invite Client");
		clientsPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		inviteClientPage = userManagementCommonPage.selectInviteClientAction();
		inviteClientPage.sendInvite(inviteClientDetails);
		inviteClientPage.validateClientInviteToaster(clientInviteToaster, inviteClientDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Invite clients");
		thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu().clickInvitedClients();
		
		getExtentTestLogger().log(Status.PASS, "Invited Enterprises data count get updated successsfully");
		inviteClientPage.validateDataUpdate(previousCount);
		
	}

}
