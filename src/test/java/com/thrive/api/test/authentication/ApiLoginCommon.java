package com.thrive.api.test.authentication;

import com.aventstack.extentreports.Status;
import com.thrive.api.pojos.LoginRequest;
import com.thrive.ui.core.BaseTestPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class ApiLoginCommon extends BaseTestPage {
	
	protected Response loginValid(LoginRequest reqBody) {
		
		getExtentTestLogger().log(Status.PASS, "Given user has valid login request end point");
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		getExtentTestLogger().log(Status.PASS, "When user invokes the auth POST api");
		return RestAssured.given()
				.spec(getLoginReqSpec())
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.post("");
	
		
	}
	
	protected Response LoginInvalidHttpMethod(LoginRequest reqBody) {
		
		getExtentTestLogger().log(Status.PASS, "Given user has valid login request end point");
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		getExtentTestLogger().log(Status.PASS, "And user provides invalid HTTP method");
		getExtentTestLogger().log(Status.PASS, "When user invokes the auth POST api");
		return RestAssured.given()
				.spec(getLoginReqSpec())
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.get("");
	
		
	}
	
	protected Response LoginSqlInjection(LoginRequest reqBody) {
		
		getExtentTestLogger().log(Status.PASS, "Given user has valid login request end point");
		getExtentTestLogger().log(Status.PASS, "And user provides valid JSON request body");
		getExtentTestLogger().log(Status.PASS, "And user injects sql command in end point url");
		getExtentTestLogger().log(Status.PASS, "When user invokes the auth POST api");
		
		return RestAssured.given()
				.spec(getLoginReqSpecSqlInjected())
				.and()
				.when()
				.body(reqBody, ObjectMapperType.GSON)
				.post("");
	
		
	}

}
