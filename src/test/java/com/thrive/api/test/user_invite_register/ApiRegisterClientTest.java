package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteClientRequest;
import com.thrive.api.pojos.RegisterClientRequest;
import com.thrive.ui.core.BaseTestPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ApiRegisterClientTest extends BaseTestPage {
	
	RegisterClientRequest registerClientReqBody;
	InviteClientRequest inviteClientreqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	
	@BeforeClass
	public void beforeTests() {
		headers.put("Authorization", getApiAuthCode());
		inviteClientreqBody = ApiTestDataGenerator.generateInviteClientReqBody();
		ApiInviteClientTest inviteClientTest = new ApiInviteClientTest();
		inviteClientTest.getInviteClientResponse(inviteClientreqBody, headers);
		
		registerClientReqBody = ApiTestDataGenerator.generateRegisterClientReqBody();
		registerClientReqBody.setFirstName(inviteClientreqBody.getFirstName());
		registerClientReqBody.setLastName(inviteClientreqBody.getLastName());
		registerClientReqBody.setEmail(inviteClientreqBody.getEmail());
		
	}
	
	@Test(priority = 0)
	public void validateRegisterClientSuccessfulResponse() {
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite Client request end point");
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid register Client request end point");
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		System.out.println("email "+inviteClientreqBody.getEmail());
		System.out.println(registerClientReqBody.getPhoto());
		
		getExtentTestLogger().log(Status.PASS,"When user invokes the invite POST api");
		response = getRegisterClientResponse(registerClientReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		System.out.println("Response Body is: " + response.getBody().asString());
		Assert.assertTrue(response.statusCode() == 200);
	}
	
	@Test()
	public void validateRegisterClient() {
		Assert.assertTrue(response.jsonPath().getString("status").equalsIgnoreCase("ok"));
	}
	
	
	public Response getRegisterClientResponse(RegisterClientRequest registerClientReqBody){
		String token = getRegisterTokenClient(registerClientReqBody.getFirstName());
		return RestAssured.given().queryParam("token", token)
				.spec(getRegisterUserReqSpec())
				.and()
				.when()
				.body(registerClientReqBody, ObjectMapperType.GSON)
				.post("");
		
	}
	
	
	
	
	
	

}
