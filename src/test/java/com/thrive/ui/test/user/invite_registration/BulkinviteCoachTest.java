package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.BulkInviteCoachPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InvitedGlobalCoach;
import com.thrive.ui.page.invite.InvitedInternalCoachPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;

public class BulkinviteCoachTest extends UserManagementCommonPage{
	
	
	InviteCoachPage inviteCoachPage ;
    InvitedInternalCoachPage invitedInternalCoachPage = new InvitedInternalCoachPage();
    InternalCoachesPage internalCoachesPage= new InternalCoachesPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveLoginPage login = new ThriveLoginPage();
	GlobalCoachesPage globalCoachesPage;
	LoginDetails loginDetails;
	InvitedGlobalCoach invitedGlobalCoach = new  InvitedGlobalCoach();
	BulkInviteCoachPage bulkInviteCoachPage = new BulkInviteCoachPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
    CoachInviteDetails internalCoachInviteDetails = TestDataGenerator.generateInternalCoachInviteDetails(false);
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	String expMesesage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();
	String filePath = resourcePath + "/bulkinvite.csv";
	
	@Test(description = "Validate SA bulk invite coach successfully")
	public void validateSABulkInviteCoach() {
	  
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE.getValue());
		CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coach");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from actions dropdown nad click on bulk invite");
		bulkInviteCoachPage = userManagementCommonPage.selectInviteCoachAction().clickBulkInviteButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invite");
		bulkInviteCoachPage.sendBulkInviteCaoach(filePath, globalCoachInviteDetails);
		
		getExtentTestLogger().log(Status.PASS, "Invited coaches searched successfully");
		thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();
		invitedGlobalCoach.validateBulkinviteDataCoach(filePath);	
	}
	
	@Test(description = "Validate SA bulk invite internal coach successfully")
	public void validateSABulkInviteInternalCoach() {
	  
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coach");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from actions dropdown nad click on bulk invite");
		bulkInviteCoachPage = userManagementCommonPage.selectInviteCoachAction().clickBulkInviteButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invite");
		bulkInviteCoachPage.sendBulkInviteInternalCaoach(filePath,internalCoachInviteDetails);
		
		getExtentTestLogger().log(Status.PASS, "Invited coaches searched successfully");
		thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickInvitedCoachesButton();
		invitedInternalCoachPage.validateBulkinviteDataInternalCoach(filePath);	
	}

}
