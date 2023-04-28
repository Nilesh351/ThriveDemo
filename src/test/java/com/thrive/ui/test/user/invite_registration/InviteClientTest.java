package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InvitedClientsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

public class InviteClientTest extends BaseTestPage{
	
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	ClientsPage clientPage = new ClientsPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	InviteClientPage inviteClientPage = new InviteClientPage();
	InvitedClientsPage invitedClientsPage = new InvitedClientsPage();
	InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
    
    
	@Test(description = "Validate SA invite client successfully")
	public void validateSAInviteClient() {
	    String clientInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite client option from actions dropdown");
		inviteClientPage = userManagementCommonPage.selectInviteClientAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invite");
		inviteClientPage.sendInvite(inviteClientDetails);
		
		getExtentTestLogger().log(Status.PASS, "Client invited successfully");
		String email = inviteClientPage.validateClientInviteToaster(clientInviteToaster,inviteClientDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Invited client searched successfully");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		invitedClientsPage = clientPage.clickInvitedClients();
		invitedClientsPage.setSearch(email); 
		invitedClientsPage.validateInvitedClientPresent(email.toLowerCase());
		
	}

}
