package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thrive.api.core.BaseApiTest;
import com.thrive.api.pojos.GetInvitedAMRespnse;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ApiGetInvitedAMTest extends BaseApiTest{
	
	Response response;
	Map<String, String> reqParams = new HashMap<>();
	Map<String, String> headers = new HashMap<>();
	
	@BeforeClass
	public void beforeClass() {
		
		reqParams.put("limit", "1000");
		reqParams.put("offset", "0");
		headers.put("Authorization", getApiAuthCode());
		response = getInvitedAMResponse(reqParams, headers);
		
	}
	
	
	@Test
	public void validateGetInvitedAMSuccessfulResponse() {
		Assert.assertTrue(response.statusCode() == 200);
	}
	
	public Response getInvitedAMResponse(Map<String, String> reqParams, Map<String, String> headers) {
		
		return RestAssured.given()
				.spec(getInvitedAMReqSpec())
				.and()
				.headers(headers)
				.and()
				.queryParams(reqParams)
				.when()
				.get("");
		
	}
	
	public GetInvitedAMRespnse getInvitedAMResponseObject(Map<String, String> reqParams, Map<String, String> headers) {
		
		return RestAssured.given()
				.spec(getInvitedAMReqSpec())
				.and()
				.headers(headers)
				.and()
				.queryParams(reqParams)
				.when()
				.get("")
				.as(GetInvitedAMRespnse.class, ObjectMapperType.GSON);
		
	}

	

	

}
