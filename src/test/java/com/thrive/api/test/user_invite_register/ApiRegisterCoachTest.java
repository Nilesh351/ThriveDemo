package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.RegisterCoachRequest;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;


public class ApiRegisterCoachTest extends BaseTestPage{
	
	
	RegisterCoachRequest registerCoachReqBody;
	InviteCoachRequest inviteCoachReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	
	
	@BeforeClass
	public void beforeTests() {
		headers.put("Authorization", getApiAuthCode());
		
		inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
		ApiInviteCoachTest inviteCoachTest = new ApiInviteCoachTest();
		inviteCoachTest.getInviteCoachResponse(inviteCoachReqBody, headers);
		if(Config.getTestPlatform().equalsIgnoreCase("thirve")) {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachReqBody();
		}
		else {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachRmReqBody();
		}
		
		registerCoachReqBody.setFirstName(inviteCoachReqBody.getFirstName());
		registerCoachReqBody.setLastName(inviteCoachReqBody.getLastName());
		registerCoachReqBody.setEmail(inviteCoachReqBody.getEmail());
		
	}
	
	
	@Test(priority = 0)
	public void validateRegisterCoachSuccessfulResponse() {
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite Global Coach request end point");
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid register Global Coach request end point");
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		System.out.println("email "+inviteCoachReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS,"When user invokes the invite POST api");
		response = getRegisterCoachResponse(registerCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		System.out.println("Response Body is: " + response.getBody().asString());
		Assert.assertTrue(response.statusCode() == 200);
	}
	
	
	
	public Response getRegisterCoachResponse(RegisterCoachRequest registerCoachReqBody){
		String token = getRegisterCoachToken(registerCoachReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterCoachUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerCoachReqBody, ObjectMapperType.GSON)
				.post("");
		
	}
	
	
	

}
