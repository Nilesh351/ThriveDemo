package com.thrive.api.test.user_invite_register;



import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.GetAllEnterpriseResponse;
import com.thrive.api.pojos.InviteEnterpriseRequest;
import com.thrive.common.testdata.utils.TestCategory;


public class ApiInviteEnterpriseTest extends ApiInviteUserCommon{
	
	InviteEnterpriseRequest inviteEntReqBody;
	Response inviteEntResponse;
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	Response response;
	
	
	@BeforeClass
	public void beforeTests() {
		
		String authCode = getApiAuthCode();
		headers.put("Authorization", authCode);
		reqParams.put("limit", "1000");
		reqParams.put("offset", "0");
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		inviteEntResponse = getInviteEntResponse(inviteEntReqBody, headers);
	}
	
	
	@Test(description = "SA - validate invite Enterprise api returns successful response message as 200 OK", priority = 0)
	public void validateInviteEntSuccessfulResponse() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE_API.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite request end point");		
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertEquals(inviteEntResponse.statusCode(), 200);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response message as 200 OK");
		Assert.assertEquals(inviteEntResponse.statusLine(), "HTTP/1.1 200 OK");
	}
	
	
	@Test(description = "SA - validate invite Enterprise api returns successful response within 3 seconds")
	public void validateInviteClientResponseTime() {
		
		int expResponsetime = 3;
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE_API.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite request end point");		
		RequestSpecification reqSpec =	RestAssured.given().spec(getInviteUserReqSpec());
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		reqSpec.when().body(inviteEntReqBody);
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");
		if(response == null) {
			response = reqSpec.and().post("");
		}
		getExtentTestLogger().log(Status.PASS, "Then user get successful response within "+expResponsetime+" seconds");
		Assert.assertTrue(inviteEntResponse.getTimeIn(TimeUnit.SECONDS) <= expResponsetime);
	}
	
	@Test
	public void validateInvitedClientPresentInInvlitedList() {//not working 
		GetAllEnterpriseResponse response =  new ApiGetInvitedEntTest().getInvitedEntResponseObject(reqParams, headers);
		long invitedEntCount = response.getResult().stream().filter(r -> r.getEmail().equalsIgnoreCase(inviteEntReqBody.getEmail())).count();
		Assert.assertTrue(invitedEntCount > 0);
		
	}
	
	
	@Test
	public void validateInviteEntWithInvalidHttpMethod() {
		Response response = InviteEntInvalidHttpMethod(inviteEntReqBody, headers);
		Assert.assertTrue(response.statusCode() == 405);
		Assert.assertTrue(response.statusLine().equalsIgnoreCase("HTTP/1.1 405 Method Not Allowed"));

	}
	
	@Test
	public void validateUnauthrozedWithInvalidToken() {
		headers.put("Authorization", "invalicodeddsadsfwwweef3434535");
		inviteEntResponse = getInviteEntResponse(inviteEntReqBody, headers);
		Assert.assertTrue(inviteEntResponse.statusCode()==401);
	}
	
	@Test
	public void validateUnauthrozedWithoutToken() {
		headers.put("Authorization", "");
		inviteEntResponse = getInviteEntResponse(inviteEntReqBody, headers);
		Assert.assertTrue(inviteEntResponse.statusCode()==401);
	}
	
	

}
