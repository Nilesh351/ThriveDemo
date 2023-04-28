package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.BulkInviteClientPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InvitedClientsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

public class BulkInviteClientTest extends UserManagementCommonPage{
	
	
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	ClientsPage clientPage = new ClientsPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	InviteClientPage inviteClientPage = new InviteClientPage();
	InvitedClientsPage invitedClientsPage = new InvitedClientsPage();
	BulkInviteClientPage bulkInviteClientPage = new BulkInviteClientPage();
	InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	String filePath = resourcePath + "/bulkinvite.csv";
	
	@Test(description = "Validate SA bulk invite client successfully")
	public void validateSABulkInviteClient() {
	  
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite client option from actions dropdown and click on bulk invite");
		bulkInviteClientPage = userManagementCommonPage.selectInviteClientAction().clickBulkInviteButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invite");
		bulkInviteClientPage.sendBulkInvite(filePath);

		getExtentTestLogger().log(Status.PASS, "Invited clients searched successfully");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		invitedClientsPage = clientPage.clickInvitedClients();
		invitedClientsPage.validateBulkinviteData(filePath);
	}

}
