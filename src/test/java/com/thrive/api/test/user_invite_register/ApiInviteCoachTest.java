package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.common.testdata.utils.TestCategory;

import io.restassured.response.Response;

public class ApiInviteCoachTest extends ApiInviteUserCommon{
	
	
	InviteCoachRequest inviteCoachReqBody;
	Response inviteCoachResponse;
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	Response response;
	
	
	@BeforeClass
	public void beforeTests() {
		
		String authCode = getApiAuthCode();
		headers.put("Authorization", authCode);
		reqParams.put("limit", "1000");
		reqParams.put("offset", "0");
		inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
		inviteCoachResponse = getInviteCoachResponse(inviteCoachReqBody, headers);
	}
	
	
	@Test(description = "SA - validate invite coach api returns successful response message as 200 OK", priority = 0)
	public void validateInviteCoachSuccessfulResponse() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE_API.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite request end point");		
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");

		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		System.out.println("Response Body is: " + inviteCoachResponse.getBody().asString());
		Assert.assertEquals(inviteCoachResponse.statusCode(), 200);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response message as 200 OK");
		Assert.assertEquals(inviteCoachResponse.statusLine(), "HTTP/1.1 200 OK");
	}
	

}
