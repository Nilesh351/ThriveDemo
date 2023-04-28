package com.thrive.ui.test.Email;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteClientRequest;
import com.thrive.api.pojos.RegisterClientRequest;
import com.thrive.api.test.user_invite_register.ApiInviteClientTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.ChangeEmailAddressPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ClientEmailOperationsTest extends UserManagementCommonPage{
	
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	ThriveLoginPage login = new ThriveLoginPage();
	RegisterClientRequest registerClientReqBody;
	InviteClientRequest inviteClientreqBody;
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	ChangeEmailAddressPage changeEmailAddressPage = new ChangeEmailAddressPage();
	RegisterClientInformationPage registerClientInformationPage = new RegisterClientInformationPage();
	LoginDetails clientLoginDetails = new LoginDetails(clientUser, clientPassword);
	Response response;
	MailboxPage mailboxPage;
	LoginDetails clientLogin;
	
	@Test(description = "Validate client update email address successfully")
	public void validateClientUpdateEmail() {
		
		headers.put("Authorization", getApiAuthCode());
		inviteClientreqBody = ApiTestDataGenerator.generateInviteClientReqBody();
		ApiInviteClientTest inviteClientTest = new ApiInviteClientTest();
		inviteClientTest.getInviteClientResponse(inviteClientreqBody, headers);
		
		registerClientReqBody = ApiTestDataGenerator.generateRegisterClientReqBody();
		registerClientReqBody.setFirstName(inviteClientreqBody.getFirstName());
		registerClientReqBody.setLastName(inviteClientreqBody.getLastName());
		registerClientReqBody.setEmail(inviteClientreqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite client");

		getExtentTestLogger().log(Status.PASS, "SA register client successfully");
		response = getRegisterClientResponse(registerClientReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");
		
		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		clientLogin = new LoginDetails(inviteClientreqBody.getEmail(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		thriveHeaderMenuPage = login.login(clientLogin);
		
		getExtentTestLogger().log(Status.PASS, "Client click on Myprofile link");
		registerClientInformationPage = thriveHeaderMenuPage.clickClientMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Client click on change email address link");
		String firstName = registerClientInformationPage.firstNameValue();
		changeEmailAddressPage = registerClientInformationPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Client provide email address to change eamil");
		InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
		changeEmailAddressPage.sendEmailDetails(inviteClientDetails.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Validate client email change toaster");
		alertsAndMessagesPage.validateToaster(ThriveAppSharedData.EMAIL_CHANGED_TOASTER.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and verify the account");
		mailboxPage =  new MailboxPage().searchVerifiedMailAndClick(firstName);
		
		getExtentTestLogger().log(Status.PASS, "Login With new email address");
		clientLogin = new LoginDetails(inviteClientDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		login.clickLogin().loginIgnoreAccept(clientLogin);
		thriveHeaderMenuPage.validateLoginSuccessful();
		
	}
	
	private Response getRegisterClientResponse(RegisterClientRequest registerClientReqBody){
		String token = getRegisterTokenClient(registerClientReqBody.getFirstName());
		return RestAssured.given().queryParam("token", token)
				.spec(getRegisterUserReqSpec())
				.and()
				.when()
				.body(registerClientReqBody, ObjectMapperType.GSON)
				.post("");
		
	}
	
	
	@Test(description = "Validate Client update email address error validation")
	public void validateClientUpdateEmailErrorValidation() {
		
		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		thriveHeaderMenuPage = login.login(clientLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Client click on Myprofile link");
		registerClientInformationPage = thriveHeaderMenuPage.clickClientMyprofileLink();
		
		getExtentTestLogger().log(Status.PASS, "Client click on change email address link");
		changeEmailAddressPage = registerClientInformationPage.clickChangeEmailAddressLink();
		
		getExtentTestLogger().log(Status.PASS, "Client provide email address to change eamil");
		InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
		changeEmailAddressPage.validateEmailDeatils(inviteClientDetails.getEmailAddress(),clientUser);
		
		getExtentTestLogger().log(Status.PASS, "Validate email update error message present");
		changeEmailAddressPage.validateEmailUpdateErrorValidation();
	}

}
