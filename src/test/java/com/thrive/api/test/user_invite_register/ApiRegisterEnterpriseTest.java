package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteEnterpriseRequest;
import com.thrive.api.pojos.RegisterEnterpriseRequest;
import com.thrive.ui.core.BaseTestPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ApiRegisterEnterpriseTest extends BaseTestPage{
	
	RegisterEnterpriseRequest registerEntReqBody;
	InviteEnterpriseRequest inviteEntReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	
	
	@BeforeClass
	public void beforeTests() {
		headers.put("Authorization", getApiAuthCode());
		
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		ApiInviteEnterpriseTest inviteEntTest = new ApiInviteEnterpriseTest();
		inviteEntTest.getInviteEntResponse(inviteEntReqBody, headers);
		
		registerEntReqBody = ApiTestDataGenerator.generateRegisterEntReqBody();
		registerEntReqBody.setName(inviteEntReqBody.getCompany());
		
	}
	
	
	@Test(priority = 0)
	public void validateRegisterEntSuccessfulResponse() {
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite Enterprise request end point");
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid register Enterprise request end point");
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		System.out.println(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS,"When user invokes the invite POST api");
		response = getRegisterEntResponse(registerEntReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		System.out.println("Response Body is: " + response.getBody().asString());
		Assert.assertTrue(response.statusCode() == 200);
	}
	
	
	public Response getRegisterEntResponse(RegisterEnterpriseRequest registerEntReqBody){

		String token = getRegisteredEntToken(inviteEntReqBody.getFirstName());
	//	String token = getRegisteredEntToken(registerEntReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterEntUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerEntReqBody, ObjectMapperType.GSON)
				.post("");
		
	}

}
