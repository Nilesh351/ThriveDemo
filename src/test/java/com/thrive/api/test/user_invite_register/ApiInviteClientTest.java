package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.GetInvitedClientsResponse;
import com.thrive.api.pojos.InviteClientRequest;
import com.thrive.api.pojos.Result;
import com.thrive.common.testdata.utils.TestCategory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiInviteClientTest extends ApiInviteUserCommon {
	
	InviteClientRequest inviteClientReqBody;
	Response inviteClientResponse;
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();
	Response response;
	
	@BeforeClass
	public void beforeTests() {
		
		String authCode = getApiAuthCode();
		headers.put("Authorization", authCode);
		reqParams.put("limit", "1000");
		reqParams.put("offset", "0");
		inviteClientReqBody = ApiTestDataGenerator.generateInviteClientReqBody();
		inviteClientResponse = getInviteClientResponse(inviteClientReqBody, headers);
	}
	
	
	@Test(description = "SA - validate invite client api returns successful response message as 200 OK", priority = 0)
	public void validateInviteClientSuccessfulResponse() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE_API.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite request end point");		
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertEquals(inviteClientResponse.statusCode(), 200);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response message as 200 OK");
		Assert.assertEquals(inviteClientResponse.statusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(description = "SA - validate invite client api returns successful response within 3 seconds")
	public void validateInviteClientResponseTime() {
		
		int expResponsetime = 3;
		
		getExtentTestLogger().assignCategory(TestCategory.USER_ONBOARDING_INVITE_API.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Given SA user has valid invite request end point");		
		RequestSpecification reqSpec =	RestAssured.given().spec(getInviteUserReqSpec());
		
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		reqSpec.when().body(inviteClientReqBody);
		
		getExtentTestLogger().log(Status.PASS, "When user invokes the invite POST api");
		if(response == null) {
			response = reqSpec.and().post("");
		}
		getExtentTestLogger().log(Status.PASS, "Then user get successful response within "+expResponsetime+" seconds");
		Assert.assertTrue(inviteClientResponse.getTimeIn(TimeUnit.SECONDS) <= expResponsetime);
	}
	
	
	@Test
	public void validateInvitedClientPresentInInvlitedList() {
		GetInvitedClientsResponse response =  new ApiGetInvitedClientTest().getInvitedClientsResponseObject(reqParams, headers);
		long invitedClientCount = response.getResult().stream().filter(r -> r.getEmail().equalsIgnoreCase(inviteClientReqBody.getEmail())).count();
		Assert.assertTrue(invitedClientCount > 0);
		
	}
	
	@Test
	public void validateInvitedClientPresentWithSameReqData() {
		
		GetInvitedClientsResponse response =  new ApiGetInvitedClientTest().getInvitedClientsResponseObject(reqParams, headers);
		List<Result> results =  response.getResult().stream().filter(r -> r.getEmail().equalsIgnoreCase(inviteClientReqBody.getEmail())).collect(Collectors.toList());
		SoftAssert softAssert = new SoftAssert();
		if(results.size() > 0) {
			softAssert.assertTrue(inviteClientReqBody.getFirstName().equalsIgnoreCase(results.get(0).getFirstName()));
			softAssert.assertTrue(inviteClientReqBody.getLastName().equalsIgnoreCase(results.get(0).getLastName()));
			softAssert.assertTrue(inviteClientReqBody.getEmail().equalsIgnoreCase(results.get(0).getEmail()));
		} else {
			Assert.fail("Get Client Invite Response Does Not have The provided Invite in the request ");
		}
		
		softAssert.assertAll();
	}
	
	@Test
	public void validateInvitedClientPresentInDbUniquly() {
		GetInvitedClientsResponse response =  new ApiGetInvitedClientTest().getInvitedClientsResponseObject(reqParams, headers);
		long invitedClientCount = response.getResult().stream().filter(r -> r.getEmail().equalsIgnoreCase(inviteClientReqBody.getEmail())).count();
		Assert.assertTrue(invitedClientCount == 1);
	}
	
	@Test
	public void validateInvitedClientResponseHeaders() {
		
	}
	
	@Test
	public void validateInviteClientWithInvalidHttpMethod() {
		Response response = InviteClientInvalidHttpMethod(inviteClientReqBody, headers);
		Assert.assertTrue(response.statusCode() == 405);
		Assert.assertTrue(response.statusLine().equalsIgnoreCase("HTTP/1.1 405 Method Not Allowed"));

	}
	
	//@Test
	public void validateInviteClientWithBadData() {
		
	}
	
	//@Test
	public void validateInviteClientSQLInjection() {
		
	}
	
	@Test
	public void validateUnauthrozedWithInvalidToken() {
		headers.put("Authorization", "invalicodeddsadsfwwweef3434535");
		inviteClientResponse = getInviteClientResponse(inviteClientReqBody, headers);
		Assert.assertTrue(inviteClientResponse.statusCode()==401);
	}
	
	@Test
	public void validateUnauthrozedWithoutToken() {
		headers.put("Authorization", "");
		inviteClientResponse = getInviteClientResponse(inviteClientReqBody, headers);
		Assert.assertTrue(inviteClientResponse.statusCode()==401);
	}
	
	
	

}
