package com.thrive.ui.test.Email;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteAMRequest;
import com.thrive.api.pojos.RegisterAMRequest;
import com.thrive.api.test.user_invite_register.ApiInviteAccountManagerTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.ChangeEmailAddressPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class AccountManagerEmailOperationsTest extends UserManagementCommonPage{
	
	RegisterAMRequest registerAMReqBody;
	InviteAMRequest inviteAMReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	MailboxPage mailboxPage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	LoginDetails amLoginDetails = new LoginDetails(accountManagerUser, accountManagerPassword);
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	ChangeEmailAddressPage changeEmailAddressPage = new ChangeEmailAddressPage();
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails accountManagerLogin;
	RegisterAccountMangerPersonalDetailsPage registerAccountMangerPersonalDetailsPage = new RegisterAccountMangerPersonalDetailsPage();
	
	
	@Test(description = "Validate Account manager update email address successfully")
	public void validateAMUpdateEmail() {
		
		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);

		registerAMReqBody = ApiTestDataGenerator.generateRegisterAMReqBody();
		registerAMReqBody.setFirstName(inviteAMReqBody.getFirstName());
		registerAMReqBody.setLastName(inviteAMReqBody.getLastName());
		registerAMReqBody.setEmail(inviteAMReqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "SA user invite Account Manager");

		getExtentTestLogger().log(Status.PASS, "SA register account manager successfully");
		response = getRegisterAMResponse(registerAMReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");
		
		getExtentTestLogger().log(Status.PASS, "Account manager provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		accountManagerLogin = new LoginDetails(inviteAMReqBody.getEmail(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveHeaderMenuPage = login.login(accountManagerLogin);
		
		getExtentTestLogger().log(Status.PASS, "Account manager click on Myprofile link");
		registerAccountMangerPersonalDetailsPage = thriveHeaderMenuPage.clickAccountManagerMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Account Manager click on change email address link");
		String firstName = registerAccountMangerPersonalDetailsPage.firstNameValue();
		changeEmailAddressPage = registerAccountMangerPersonalDetailsPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Account manager provide email address to change eamil");
		InviteAccountManagerDetails inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();
		changeEmailAddressPage.sendEmailDetails(inviteAccountManagerDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Validate Account manager email change toaster");
		alertsAndMessagesPage.validateToaster(ThriveAppSharedData.EMAIL_CHANGED_TOASTER.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and verify the account");
		mailboxPage =  new MailboxPage().searchVerifiedMailAndClick(firstName);
		
		getExtentTestLogger().log(Status.PASS, "Login With new email address");
		accountManagerLogin = new LoginDetails(inviteAccountManagerDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		login.clickLogin().loginIgnoreAccept(accountManagerLogin);
		thriveHeaderMenuPage.validateLoginSuccessful();
		
	}
	
	@Test(description = "Validate Account manager update email address error validation")
	public void validateAMUpdateEmailErrorValidation() {
		
		getExtentTestLogger().log(Status.PASS, "Account manager provides username,password and click on login");
		thriveHeaderMenuPage = login.login(amLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Account manager click on Myprofile link");
		registerAccountMangerPersonalDetailsPage = thriveHeaderMenuPage.clickAccountManagerMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Account Manager click on change email address link");
		changeEmailAddressPage = registerAccountMangerPersonalDetailsPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Account manager provide email address to change eamil");
		InviteAccountManagerDetails inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();
		changeEmailAddressPage.validateEmailDeatils(inviteAccountManagerDetails.getEmailAddress(),accountManagerUser);
		
		getExtentTestLogger().log(Status.PASS, "Validate email update error message present");
		changeEmailAddressPage.validateEmailUpdateErrorValidation();

	}
	
	
	
	private Response getRegisterAMResponse(RegisterAMRequest registerAMReqBody){
		String token = getRegisterTokenAM(registerAMReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterAMUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerAMReqBody, ObjectMapperType.GSON)
				.post("");
	}
	

}
