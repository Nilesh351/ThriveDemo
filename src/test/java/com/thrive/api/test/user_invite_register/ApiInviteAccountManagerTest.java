package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.GetInvitedAMRespnse;
import com.thrive.api.pojos.InviteAMRequest;
import com.thrive.common.testdata.utils.TestCategory;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiInviteAccountManagerTest extends ApiInviteUserCommon{
	
	
	InviteAMRequest inviteAMReqBody;
	Response inviteAMResponse;
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	Response response;
	
	@BeforeClass
	public void beforeTests() {
		
		String authCode = getApiAuthCode();
		headers.put("Authorization", authCode);
		reqParams.put("limit", "1000");
		reqParams.put("offset", "0");
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		inviteAMResponse = getInviteAMResponse(inviteAMReqBody, headers);
	}
	
	@Test(description = "SA - validate invite Account Manager api returns successful response message as 200 OK", priority = 0)
	public void validateInviteClientSuccessfulResponse() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE_API.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite request end point");		
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");

		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertEquals(inviteAMResponse.statusCode(), 200);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response message as 200 OK");
		Assert.assertEquals(inviteAMResponse.statusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(description = "SA - validate invite Account Manager api returns successful response within 3 seconds")
	public void validateInviteAMResponseTime() {
		
		int expResponsetime = 3;
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE_API.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite request end point");		
		RequestSpecification reqSpec =	RestAssured.given().spec(getInviteAMReqSpec());
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		reqSpec.when().body(inviteAMReqBody);
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");
		if(response == null) {
			response = reqSpec.and().post("");
		}
		getExtentTestLogger().log(Status.PASS, "Then user get successful response within "+expResponsetime+" seconds");
		Assert.assertTrue(inviteAMResponse.getTimeIn(TimeUnit.SECONDS) <= expResponsetime);
	}
	
	
	@Test
	public void validateInvitedAMPresentInInvlitedList() {
		GetInvitedAMRespnse response =  new ApiGetInvitedAMTest().getInvitedAMResponseObject(reqParams, headers);
		long invitedAMCount = response.getResult().stream().filter(r -> r.getEmail().equalsIgnoreCase(inviteAMReqBody.getEmail())).count();
		Assert.assertTrue(invitedAMCount > 0);
		
	}
	
	
	@Test
	public void validateInviteAMWithInvalidHttpMethod() {
		Response response = InviteAMInvalidHttpMethod(inviteAMReqBody, headers);
		Assert.assertTrue(response.statusCode() == 405);
		Assert.assertTrue(response.statusLine().equalsIgnoreCase("HTTP/1.1 405 Method Not Allowed"));

	}
	
	@Test
	public void validateUnauthrozedWithInvalidToken() {
		headers.put("Authorization", "invalicodeddsadsfwwweef3434535");
		inviteAMResponse = getInviteAMResponse(inviteAMReqBody, headers);
		Assert.assertTrue(inviteAMResponse.statusCode()==401);
	}
	
	@Test
	public void validateUnauthrozedWithoutToken() {
		headers.put("Authorization", "");
		inviteAMResponse = getInviteAMResponse(inviteAMReqBody, headers);
		Assert.assertTrue(inviteAMResponse.statusCode()==401);
	}
	
	

}
