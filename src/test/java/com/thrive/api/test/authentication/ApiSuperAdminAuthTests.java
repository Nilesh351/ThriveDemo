package com.thrive.api.test.authentication;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.pojos.LoginRequest;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import io.restassured.response.Response;

public class ApiSuperAdminAuthTests extends ApiLoginCommon {
	
	
	LoginRequest saLoginValidRequest;
	LoginRequest saLoginInValidRequest;
	Response loginResponse;
	
	@BeforeClass
	public void preStepsForAuthApiTests() {
		saLoginValidRequest = new LoginRequest(Config.getSuperuserUser(), Config.getSuperuserPassword(),
				ThriveAppSharedData.TIMEZONE.getValue(), ThriveAppSharedData.LOCALE_EN.getValue());
	}
	
	@Test(description = "Validate auth api returns successful response with SA credentials")
	public void validateSaLoginSuccessfulResponse() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
		loginResponse = loginValid(saLoginValidRequest);
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(loginResponse.statusCode() == 200);
	}
	
	@Test(description = "Validate user get successful response in no more than 3 seconds")
	public void validateSaLoginResponseTime() {
		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
		int expResponseTime = 3;
		loginResponse = loginValid(saLoginValidRequest);
		getExtentTestLogger().log(Status.PASS, String.format("Then user get successful response in no mmore than %d seconds", expResponseTime));
		Assert.assertTrue((loginResponse.getTimeIn(TimeUnit.SECONDS) <= 3), "Response time " + loginResponse.getTimeIn(TimeUnit.SECONDS)
		+ " is greater than exp" + 3);
	}
	
	@Test(description = "Validate user get successful response body must have token value")
	public void validateSaLoginResponseTokenIsNotEmpty() {
		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
		loginResponse = loginValid(saLoginValidRequest);
		getExtentTestLogger().log(Status.PASS, "Then user get successful response body must have token value");
		Assert.assertTrue(!(loginResponse.jsonPath().getString("token").isEmpty()));
		Assert.assertTrue(!(loginResponse.jsonPath().getString("token") == null));
	}
	
	//@Test
	public void validateSaLoginResponseHeaders() {

	}
	
	@Test(description = "Validate user get response code as 405 while sending request with invalid HTTP method")
	public void validateSaLoginWithInvalidHttpMethod() {
		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
		Response response = LoginInvalidHttpMethod(saLoginValidRequest);
		getExtentTestLogger().log(Status.PASS, "Then user get response code as 405");
		Assert.assertTrue(response.statusCode() == 405);
		getExtentTestLogger().log(Status.PASS, "And user get response message  as 'Method Not Allowed'");
		Assert.assertTrue(response.statusLine().equalsIgnoreCase("HTTP/1.1 405 Method Not Allowed"));

	}
	
	@Test(description = "Validate user get response code as 400 while sending bad request")
	public void validateSaLoginWithBadData() {
		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
		LoginRequest saLoginBadRequest = new LoginRequest(Config.getSuperuserUser(), Config.getSuperuserPassword());
		loginResponse = loginValid(saLoginBadRequest);
		getExtentTestLogger().log(Status.PASS, "Then user get response code as 400");
		Assert.assertTrue(loginResponse.statusCode() == 400);
		getExtentTestLogger().log(Status.PASS, "And user get response message  as 'Bad Request'");
		Assert.assertTrue(loginResponse.statusLine().equalsIgnoreCase("HTTP/1.1 400 Bad Request"));
	}
	
	@Test(description = "Validate user get response code as 400 if SQL injected in end point url")
	public void validateSaLoginSQLInjection() {
		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
		loginResponse = LoginSqlInjection(saLoginValidRequest);
		getExtentTestLogger().log(Status.PASS, "Then user get response code as 400");
		Assert.assertTrue(loginResponse.statusCode() == 400);
		getExtentTestLogger().log(Status.PASS, "And user get response message  as 'Bad Request'");
		Assert.assertTrue(loginResponse.statusLine().equalsIgnoreCase("HTTP/1.1 400 Bad Request"));
		
	}
	
	@Test(description = "Validate user get response code as 401 if invalid crdentials send in request body")
	public void validateSaLoginUnauthorized() {
		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION_API.getValue());
		saLoginInValidRequest = new LoginRequest("invalid@yopmail.com", Config.getSuperuserPassword(),
				ThriveAppSharedData.TIMEZONE.getValue(), ThriveAppSharedData.LOCALE_EN.getValue());
		loginResponse = loginValid(saLoginInValidRequest);
		getExtentTestLogger().log(Status.PASS, "Then user get response code as 401");
		Assert.assertTrue(loginResponse.statusCode()==401);
		getExtentTestLogger().log(Status.PASS, "And user get response message  as '401 Unauthorized'");
		Assert.assertTrue(loginResponse.statusLine().equalsIgnoreCase("HTTP/1.1 401 Unauthorized"));
	}
	
	

}
