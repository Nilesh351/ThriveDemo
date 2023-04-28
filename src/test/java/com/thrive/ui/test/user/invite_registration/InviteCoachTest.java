package com.thrive.ui.test.user.invite_registration;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InvitedGlobalCoach;
import com.thrive.ui.page.invite.InvitedInternalCoachPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;

public class InviteCoachTest extends BaseTestPage {
	
	InviteCoachPage inviteCoachPage ;
    InvitedInternalCoachPage invitedInternalCoachPage = new InvitedInternalCoachPage();
    InternalCoachesPage internalCoachesPage= new InternalCoachesPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveLoginPage login = new ThriveLoginPage();
	GlobalCoachesPage globalCoachesPage;
	LoginDetails loginDetails;
	InvitedGlobalCoach invitedGlobalCoach = new  InvitedGlobalCoach();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
    CoachInviteDetails coachInviteDetails = TestDataGenerator.generateCoachInviteDetails(false);
    CoachInviteDetails internalCoachInviteDetails = TestDataGenerator.generateInternalCoachInviteDetails(false);
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	String expMesesage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();
	
	@Test(description = "Validate SA invite internal coach successfully")
	public void validateSaInviteInternalCoach() {
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> internal coaches");
		this.internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from action dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
		inviteCoachPage.sendInternalCoachInvite(internalCoachInviteDetails);
		
		getExtentTestLogger().log(Status.PASS, "Internal Coach invited successfully");
		String email = inviteCoachPage.validateInternalCoachInviteToaster(coachInviteToaster,internalCoachInviteDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Invited internal coach searched successfully");
		thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickInvitedCoachesButton();
	    invitedInternalCoachPage.setSearch(email);
	    invitedInternalCoachPage.validateEmailPresent(email);
	    
	}
	
	
	@Test(description="Invite global coach with SA login")
	public void validateInviteGlobalCoach() {
		String coachInviteToaster = ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue();
		CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	    
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite coach option from actions dropdown");
		inviteCoachPage = userManagementCommonPage.selectInviteCoachAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides all details and click on send invitation");
		inviteCoachPage.sendGlobalCoachInvite(globalCoachInviteDetails);
		
		getExtentTestLogger().log(Status.PASS, "Coach invited successfully");
		String email = inviteCoachPage.validateCoachInviteToaster(coachInviteToaster,globalCoachInviteDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Invited coach searched successfully");
		thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();
		invitedGlobalCoach.setSearch(email);
		invitedGlobalCoach.validateEmailPresent(email);
	}
	

}
