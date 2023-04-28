package com.thrive.api.test.user_invite_register;

import java.util.Map;

import com.thrive.api.pojos.InviteAMRequest;
import com.thrive.api.pojos.InviteClientRequest;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.InviteEmployeeRequest;
import com.thrive.api.pojos.InviteEnterpriseRequest;
import com.thrive.ui.core.BaseTestPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ApiInviteUserCommon extends BaseTestPage {
	
	public Response getInviteClientResponse(InviteClientRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteUserReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.post("");
	
		
	}
	
	public Response getInviteEmployeeResponse(InviteEmployeeRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteUserReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.post("");
	
		
	}
	
	public Response getInviteCoachResponse(InviteCoachRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteCoachReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.post("");
	
		
	}
	
	public Response getInviteInternalCoachResponse(InviteCoachRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteInternalCoachReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.post("");
	
		
	}
	
	public Response getInviteAMResponse(InviteAMRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteAMReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.post("");
	
		
	}
	
	
	public Response InviteAMInvalidHttpMethod(InviteAMRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteAMReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.get("");
	
		
	}
	
	public Response InviteClientInvalidHttpMethod(InviteClientRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteUserReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.get("");
	
		
	}
	
	public Response getInviteEntResponse(InviteEnterpriseRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteEntReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.post("");
	
		
	}
	
	public Response InviteEntInvalidHttpMethod(InviteEnterpriseRequest reqBody, Map<String, String> headers) {
		return RestAssured.given()
				.spec(getInviteEntReqSpec())
				.and()
				.headers(headers)
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.get("");
	
		
	}
	
	

}
