package com.thrive.api.test.authentication;



import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.pojos.LoginRequest;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiAccountManagerAuthTest extends BaseTestPage{
	
	LoginRequest amLoginValidRequest;
	Response response;
	
	@BeforeClass
	public void preRequsite() {
		
		amLoginValidRequest = new LoginRequest(Config.getAccountManagerUser(), Config.getAccountManagerPassword(),
				ThriveAppSharedData.TIMEZONE.getValue(), ThriveAppSharedData.LOCALE_EN.getValue());
	}
	
	@Test(description = "validate AM user get successful response message as 200 OK")
	public void validateAccountManagerLoginSuccessfulResponse() {
		
	getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
	
	getExtentTestLogger().log(Status.PASS, "Given Account Manager user has valid login request end point");		
	RequestSpecification reqSpec =	RestAssured.given().spec(getLoginRequestSpecification());
	
	getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
	reqSpec.when().body(amLoginValidRequest).post("");
	
	getExtentTestLogger().log(Status.PASS, "When user invokes the auth POST api");
	response = reqSpec.and().post("");
	
	getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
	Assert.assertEquals(response.statusCode(), 200);
	
	getExtentTestLogger().log(Status.PASS, "Then user get successful response message as 200 OK");
	Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");
		
	}
	
	@Test(description = "validate AM user get token in auth response")
	public void validateAccountManagerLoginResponseToken() {
		
	validateAccountManagerLoginSuccessfulResponse();
		
	getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
	
	getExtentTestLogger().log(Status.PASS, "Then user get token in auth response");
	Assert.assertTrue(! response.jsonPath().getString("token").isEmpty());
	Assert.assertTrue(! (response.jsonPath().getString("token") == null));
	
		
		
		
	}

}
