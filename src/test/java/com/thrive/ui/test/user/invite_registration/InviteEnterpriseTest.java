package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedEnterprisesPage;

public class InviteEnterpriseTest extends BaseTestPage {
	
	@Test(description = "Validate SA invite enterpriese successfully")
	public void validateEnterpriseInviteEnterprise() {
		
		InviteEnterprisePage inviteEnterprisePage;
		ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
		UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
		InvitedEnterprisesPage InvitedEnterprisesPage = new InvitedEnterprisesPage();
		ThriveLoginPage login = new ThriveLoginPage();
		
		InviteEnterpriseDetails inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();;
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();
		LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> enterprises");
		thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite enterprise option from actions dropdown");
		inviteEnterprisePage = userManagementCommonPage.selectInviteEnterpriseAction();
				
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		
		getExtentTestLogger().log(Status.PASS, "Enterprise invited successfully");
		String email = inviteEnterprisePage.validateEntInviteToaster(entInviteToaster,inviteEnterpriseDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Invited enterprise searched successfully");
		thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickInvitedEnterprise();
		InvitedEnterprisesPage.setSearch(email);
		InvitedEnterprisesPage.validateEnterprisePresent(email);
	}

}
