package com.thrive.api.test.user_invite_register;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.thrive.api.core.BaseApiTest;
import com.thrive.api.pojos.GetAllEnterpriseResponse;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ApiGetInvitedEntTest extends BaseApiTest{
	
	Response response;
	Map<String, String> reqParams = new HashMap<>();
	Map<String, String> headers = new HashMap<>();
	
	
	@BeforeClass
	public void beforeClass() {
		
		reqParams.put("limit", "1000");
		reqParams.put("offset", "0");
		headers.put("Authorization", getApiAuthCode());
		response = getInvitedEntResponse(reqParams, headers);
		
	}
	
	@Test
	public void validateGetInvitedEntSuccessfulResponse() {
		Assert.assertTrue(response.statusCode() == 200);
	}
	
	public Response getInvitedEntResponse(Map<String, String> reqParams, Map<String, String> headers) {
		
		return RestAssured.given()
				.spec(getInvitedEntReqSpec())
				.and()
				.headers(headers)
				.and()
				.queryParams(reqParams)
				.when()
				.get("");
		
	}
	
	public GetAllEnterpriseResponse getInvitedEntResponseObject(Map<String, String> reqParams, Map<String, String> headers) {
		
		return RestAssured.given()
				.spec(getInvitedEntReqSpec())
				.and()
				.headers(headers)
				.and()
				.queryParams(reqParams)
				.when()
				.get("")
				.as(GetAllEnterpriseResponse.class, ObjectMapperType.GSON);
		
	}

}
