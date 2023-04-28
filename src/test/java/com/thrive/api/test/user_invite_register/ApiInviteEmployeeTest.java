package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteEmployeeRequest;
import com.thrive.common.testdata.utils.TestCategory;

import io.restassured.response.Response;

public class ApiInviteEmployeeTest extends ApiInviteUserCommon{
	
	InviteEmployeeRequest inviteEmployeeReqBody;
	Response inviteEmployeeResponse;
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	Response response;
	
	@BeforeClass
	public void beforeTests() {
		
		String authCode = getApiAuthCodeEA();
		headers.put("Authorization", authCode);
		reqParams.put("limit", "1000");
		reqParams.put("offset", "0");
		inviteEmployeeReqBody = ApiTestDataGenerator.generateInviteEmployeeReqBody();
		inviteEmployeeResponse = getInviteEmployeeResponse(inviteEmployeeReqBody, headers);
	}
	
	@Test(description = "SA - validate invite Employee api returns successful response message as 200 OK", priority = 0)
	public void validateInviteEmployeeSuccessfulResponse() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE_API.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite request end point");		
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertEquals(inviteEmployeeResponse.statusCode(), 200);
		System.out.println("Response Body is: " + inviteEmployeeResponse.getBody().asString());
		System.out.println("respnse "+inviteEmployeeResponse.statusCode());
		System.out.println("email "+inviteEmployeeReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response message as 200 OK");
		Assert.assertEquals(inviteEmployeeResponse.statusLine(), "HTTP/1.1 200 OK");
	}
	

}
