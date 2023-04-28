package com.thrive.ui.test.Email;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.RegisterCoachRequest;
import com.thrive.api.pojos.RegisterInternalCoachRequest;
import com.thrive.api.test.user_invite_register.ApiInviteInternalCoachTest;
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
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class InternalCoachEmailOperationsTest extends UserManagementCommonPage{
	
	
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	Response response;
	RegisterCoachRequest registerCoachReqBody;
	ThriveLoginPage login = new ThriveLoginPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails internalCoachLoginDetails = new LoginDetails(internalCoachUser, internalCoachPassword);
	RegisterCoachPersonalDetailsPage registerCoachPersonalDetailsPage = new RegisterCoachPersonalDetailsPage();
	ThriveLoginPage thriveLoginPage = new ThriveLoginPage();
	RegisterInternalCoachRequest registerInternalCoachReqBody;
	InviteCoachRequest inviteInternalCoachReqBody;
	MailboxPage mailboxPage;
	ChangeEmailAddressPage changeEmailAddressPage = new ChangeEmailAddressPage();
	LoginDetails coachLogin;
	Map<String, String> headers = new HashMap<>();
	
	
	@Test(description = "Validate Internal Coach update email address successfully")
	public void validateInternalCoachUpdateEmail() {

		String tosterMessage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();

		headers.put("Authorization", getApiAuthCode());
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "SA user invite internal coach");

		getExtentTestLogger().log(Status.PASS, "SA register internal coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);

		getExtentTestLogger().log(Status.PASS, "Login by SA and validate global coach");
		launchThriveApp(Config.getLoginPageURL());
		thriveLoginPage.loginIgnoreAccept(saLoginDetails).clickUserManagementLink().clickInternalCoachesLink()
				.searchAndClickCoach(inviteInternalCoachReqBody.getEmail().toLowerCase()).clickEnable().ClickYes();
		alertsAndMessagesPage.validateToaster(tosterMessage);
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Internal Coach provides username,password and click on login");
		coachLogin = new LoginDetails(inviteInternalCoachReqBody.getEmail(),
				ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveHeaderMenuPage = login.login(coachLogin);

		getExtentTestLogger().log(Status.PASS, "Internal Coach click on Myprofile link");
		registerCoachPersonalDetailsPage = thriveHeaderMenuPage.clickCoachesMyprofileLink();

		getExtentTestLogger().log(Status.PASS, " internal Coach click on change email address link");
		String firstName = registerCoachPersonalDetailsPage.firstNameValue();
		changeEmailAddressPage = registerCoachPersonalDetailsPage.clickChangeEmailAddressLink();

		getExtentTestLogger().log(Status.PASS, "Internal Coach provide email address to change eamil");
		CoachInviteDetails internalCoachInviteDetails = TestDataGenerator.generateInternalCoachInviteDetails(false);
		changeEmailAddressPage.sendEmailDetails(internalCoachInviteDetails.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Validate Coach email change toaster");
		alertsAndMessagesPage.validateToaster(ThriveAppSharedData.EMAIL_CHANGED_TOASTER.getValue());

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and verify the account");
		mailboxPage = new MailboxPage().searchVerifiedMailAndClick(firstName);

		getExtentTestLogger().log(Status.PASS, "Login With new email address");
		coachLogin = new LoginDetails(internalCoachInviteDetails.getEmailAddress(),
				ThriveAppSharedData.COMMON_PAASWORD.getValue());
		login.clickLogin().loginIgnoreAccept(coachLogin);
		thriveHeaderMenuPage.validateLoginSuccessful();
	}
	
	private Response getRegisterInternalCoachResponse(RegisterInternalCoachRequest registerInternalCoachReqBody2) {
		String token = getRegisterInternalCoachToken(registerInternalCoachReqBody.getFirstName());
		return RestAssured.given().spec(getRegisterInternalCoachUserReqSpec()).queryParam("token", token).and().when()
				.body(registerInternalCoachReqBody, ObjectMapperType.GSON).post("");

	}
	
	@Test(description = "Validate Internal Coach update email address error validation")
	public void validateInternalCoachUpdateEmailErrorValidation() {
		
		getExtentTestLogger().log(Status.PASS, "Internal Coach provides username,password and click on login");
		thriveHeaderMenuPage = login.login(internalCoachLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Internal Coach click on Myprofile link");
		registerCoachPersonalDetailsPage = thriveHeaderMenuPage.clickCoachesMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Internal Coach click on change email address link");
		changeEmailAddressPage = registerCoachPersonalDetailsPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Internal Coach provide email address to change eamil");
		CoachInviteDetails internalCoachInviteDetails = TestDataGenerator.generateInternalCoachInviteDetails(false);
		changeEmailAddressPage.validateEmailDeatils(internalCoachInviteDetails.getEmailAddress(),internalCoachUser);
		
		getExtentTestLogger().log(Status.PASS, "Validate email update error message present");
		changeEmailAddressPage.validateEmailUpdateErrorValidation();
	}

}
