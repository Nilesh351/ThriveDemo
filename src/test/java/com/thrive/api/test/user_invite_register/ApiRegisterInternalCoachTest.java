package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.RegisterInternalCoachRequest;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

	public class ApiRegisterInternalCoachTest extends BaseTestPage{
		
		RegisterInternalCoachRequest registerInternalCoachReqBody;
		InviteCoachRequest inviteInternalCoachReqBody;
		Response response;
		Map<String, String> headers = new HashMap<>();
		
		@BeforeClass
		public void beforeTests() {
			headers.put("Authorization", getApiAuthCode());
			
			inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
			ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
			inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
			if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
				registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
			}else {
				registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
			}
			registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
			registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
			registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
			
		}
		
		
		@Test(priority = 0)
		public void validateRegisterInternalCoachSuccessfulResponse() {
			
			getExtentTestLogger().log(Status.PASS, "Given SA user has valid Internal Coach request end point");
			
			getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
			
			getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");
			
			getExtentTestLogger().log(Status.PASS, "Given SA user has valid register Internal coach request end point");
			
			getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
			System.out.println("email "+inviteInternalCoachReqBody.getEmail());
			
			getExtentTestLogger().log(Status.PASS,"When user invokes the invite POST api");
			response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);
			
			getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
			System.out.println("Response Body is: " + response.getBody().asString());
			Assert.assertTrue(response.statusCode() == 200);
		}
		
		
		public Response getRegisterInternalCoachResponse(RegisterInternalCoachRequest registerInternalCoachReqBody2){
			String token = getRegisterInternalCoachToken(registerInternalCoachReqBody.getFirstName());
			return RestAssured.given()
					.spec(getRegisterInternalCoachUserReqSpec()).queryParam("token", token)
					.and()
					.when()
					.body(registerInternalCoachReqBody, ObjectMapperType.GSON)
					.post("");
			
		}
		
		
	
	}
