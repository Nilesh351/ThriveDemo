package com.thrive.ui.test.Email;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.RegisterCoachRequest;
import com.thrive.api.test.user_invite_register.ApiInviteCoachTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.ChangeEmailAddressPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class CoachEmailOperationsTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	Response response;
	RegisterCoachRequest registerCoachReqBody;
	ThriveLoginPage login = new ThriveLoginPage();
	GlobalCoachesPage globalCoachesPage = new GlobalCoachesPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails coachLoginDetails = new LoginDetails(globalCoachUser, globalCoachPassword);
	RegisterCoachPersonalDetailsPage registerCoachPersonalDetailsPage = new RegisterCoachPersonalDetailsPage();
	InviteCoachRequest inviteCoachReqBody;
	ThriveLoginPage thriveLoginPage = new ThriveLoginPage();
	MailboxPage mailboxPage;
	ChangeEmailAddressPage changeEmailAddressPage = new ChangeEmailAddressPage();
	LoginDetails coachLogin;
	Map<String, String> headers = new HashMap<>();
	
	
	@Test(description = "Validate Coach update email address successfully")
	public void validateCoachUpdateEmail() {
		
		String tosterMessage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();
		
		headers.put("Authorization", getApiAuthCode());
		inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
		ApiInviteCoachTest inviteCoachTest = new ApiInviteCoachTest();
		inviteCoachTest.getInviteCoachResponse(inviteCoachReqBody, headers);
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachReqBody();
		}else {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachRmReqBody();
		}
		registerCoachReqBody.setFirstName(inviteCoachReqBody.getFirstName());
		registerCoachReqBody.setLastName(inviteCoachReqBody.getLastName());
		registerCoachReqBody.setEmail(inviteCoachReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite coach");

		getExtentTestLogger().log(Status.PASS, "SA register coach successfully");
		response = getRegisterCoachResponse(registerCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "Login by SA and validate global coach");
		launchThriveApp(Config.getLoginPageURL());
		thriveLoginPage.login(saLoginDetails).clickUserManagementLink().clickCoachesLink()
		.searchAndClickCoach(inviteCoachReqBody.getEmail().toLowerCase()).clickEnable().ClickYes();
		alertsAndMessagesPage.validateToaster(tosterMessage);
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Coach provides username,password and click on login");
		coachLogin = new LoginDetails(inviteCoachReqBody.getEmail(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveHeaderMenuPage = login.loginIgnoreAccept(coachLogin);
		
		getExtentTestLogger().log(Status.PASS, "Coach click on Myprofile link");
		registerCoachPersonalDetailsPage = thriveHeaderMenuPage.clickCoachesMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Coach click on change email address link");
		String firstName = registerCoachPersonalDetailsPage.firstNameValue();
		changeEmailAddressPage = registerCoachPersonalDetailsPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Coach provide email address to change eamil");
		CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		changeEmailAddressPage.sendEmailDetails(globalCoachInviteDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Validate Coach email change toaster");
		alertsAndMessagesPage.validateToaster(ThriveAppSharedData.EMAIL_CHANGED_TOASTER.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and verify the account");
		mailboxPage =  new MailboxPage().searchVerifiedMailAndClick(firstName);
		
		getExtentTestLogger().log(Status.PASS, "Login With new email address");
		coachLogin = new LoginDetails(globalCoachInviteDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		login.clickLogin().loginIgnoreAccept(coachLogin);
		thriveHeaderMenuPage.validateLoginSuccessful();
	}
		

	private Response getRegisterCoachResponse(RegisterCoachRequest registerCoachReqBody){
		String token = getRegisterCoachToken(registerCoachReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterCoachUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerCoachReqBody, ObjectMapperType.GSON)
				.post("");
		
	}
	
	@Test(description = "Validate Coach update email address error validation")
	public void validateCoachUpdateEmailErrorValidation() {
		
		getExtentTestLogger().log(Status.PASS, "Coach provides username,password and click on login");
		thriveHeaderMenuPage = login.login(coachLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Coach click on Myprofile link");
		registerCoachPersonalDetailsPage = thriveHeaderMenuPage.clickCoachesMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Coach click on change email address link");
		changeEmailAddressPage = registerCoachPersonalDetailsPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Coach provide email address to change eamil");
		CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		changeEmailAddressPage.validateEmailDeatils(globalCoachInviteDetails.getEmailAddress(),globalCoachUser);
		
		getExtentTestLogger().log(Status.PASS, "Validate email update error message present");
		changeEmailAddressPage.validateEmailUpdateErrorValidation();
	}

}
