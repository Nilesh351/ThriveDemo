package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteAMRequest;
import com.thrive.api.pojos.RegisterAMRequest;
import com.thrive.ui.core.BaseTestPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ApiRegisterAccountManagerTest extends BaseTestPage{
	
	
	RegisterAMRequest registerAMReqBody;
	InviteAMRequest inviteAMReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	
	
	@BeforeClass
	public void beforeTests() {
		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);
		
		registerAMReqBody = ApiTestDataGenerator.generateRegisterAMReqBody();
		registerAMReqBody.setFirstName(inviteAMReqBody.getFirstName());
		registerAMReqBody.setLastName(inviteAMReqBody.getLastName());
		registerAMReqBody.setEmail(inviteAMReqBody.getEmail());
		
	}
	
	
	@Test(priority = 0)
	public void validateRegisterAMSuccessfulResponse() {
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite Account Manager request end point");
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid register Account Manager request end point");
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		System.out.println("email "+inviteAMReqBody.getEmail());
		
		getExtentTestLogger().log(Status.PASS,"When user invokes the invite POST api");
		response = getRegisterAMResponse(registerAMReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		System.out.println("Response Body is: " + response.getBody().asString());
		System.out.println("respnse "+response.statusCode());
		Assert.assertTrue(response.statusCode() == 200);
	}
	
	
	public Response getRegisterAMResponse(RegisterAMRequest registerAMReqBody){
		String token = getRegisterTokenAM(registerAMReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterAMUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerAMReqBody, ObjectMapperType.GSON)
				.post("");
		
	}

}
