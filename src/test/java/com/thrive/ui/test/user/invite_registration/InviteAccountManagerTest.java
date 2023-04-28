package com.thrive.ui.test.user.invite_registration;

import java.util.List;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.invite.InvitedAccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.AccountManagersPage;

public class InviteAccountManagerTest extends BaseTestPage {
	
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	AccountManagersPage accountManagersPage = new AccountManagersPage();
	InviteAccountManagerPage inviteAccountManagerPage = new InviteAccountManagerPage();
	InvitedAccountManagersPage invitedAccountManagersPage = new InvitedAccountManagersPage();
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	List<String> expeectedSortedNameColumn;
	List<String> actualSortedColumn;
	InviteAccountManagerDetails inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();
	
	@Test(description = "Validate SA invite account manager successfully")
	public void validateAccountManagerInviteAccountManager() {
		String accountManagerInviteToaster = ThriveAppSharedData.INVITE_ACCOUNT_MANAGER_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> account managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS,"Superadmin selects invite account manager option from actions dropdown");
		inviteAccountManagerPage = userManagementCommonPage.selectInviteAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invite");
		inviteAccountManagerPage.sendInvite(inviteAccountManagerDetails);

		getExtentTestLogger().log(Status.PASS, "Account manager invited successfully");
		String email = inviteAccountManagerPage.validateAMInviteToaster(accountManagerInviteToaster,inviteAccountManagerDetails.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Invited account manager searched successfully");
		thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickInvitedAccountManager();
		invitedAccountManagersPage.setSearch(email);
		invitedAccountManagersPage.validateAccountManagerPresent(email.toLowerCase());
	}

}
