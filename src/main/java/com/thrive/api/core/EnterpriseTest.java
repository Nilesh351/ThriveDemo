package com.thrive.api.core;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.thrive.api.data_utils.ApiSharedData;
import com.thrive.api.pojos.GetAllEnterpriseResponse;
import com.thrive.ui.core.Config;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EnterpriseTest extends BaseApiTest{
	
	@Test
	public void validateGetAllEnterpriseReturnsList() {
		GetAllEnterpriseResponse getAllEnterpriseResponse;
		getAllEnterpriseResponse = getAllEnterprisesList();
		Assert.assertTrue(getAllEnterpriseResponse.getResult().size() > 1, "Failed to get results from GetAllEnterprise");
		
	}
	
	@Test
	public void validateGetAllEnterpriseStatusCode() {
		int expectedStatusCode = Integer.parseInt(ApiSharedData.RESPONSE_STATUS_CODE_SUCESS.getValue());
		Assert.assertTrue(getAllEnterprisesResponse().statusCode() == expectedStatusCode, "Response status code is not " + expectedStatusCode);
	}
	
	@Test
	public void validateGetAllEnterpriseResponseTime() {
		int expectedResponseTime = Integer.parseInt(ApiSharedData.RESPONSE_STATUS_RESPONSE_THRESHOLD_MS.getValue());
		Assert.assertTrue(getAllEnterprisesResponse().getTime() < expectedResponseTime, "Response time has crossed threshold " + expectedResponseTime + "Mili Seconds");
	}
	
	
	public RequestSpecification getActiveEnterpriseRequestSpecification() {
		return setContentType()
				.setBaseUri(Config.getLoginPageURL())
				.setBasePath("/api/getAllEnterprises")
				.build();

	}
	
	public Response getAllEnterprisesResponse(){
	
		String code = getApiAuthCode();
		return RestAssured.given()
				.spec(getActiveEnterpriseRequestSpecification())
				.header("Authorization", code)
				.and()
				.queryParam("limit", "500").queryParam("offset", "0")
				.when()
				.get();
		
	}
	
	public GetAllEnterpriseResponse getAllEnterprisesList(){
		
		GetAllEnterpriseResponse getAllEnterpriseResponse;
	
		getAllEnterprisesResponse();
		getAllEnterpriseResponse = getAllEnterprisesResponse().as(GetAllEnterpriseResponse.class, ObjectMapperType.GSON);
		return getAllEnterpriseResponse;
		/*
		 * for(Result result : getAllEnterpriseResponse.getResult()) {
		 * if(result.getName().contains("auto-ent")) {
		 * System.out.println(result.getName()); } }
		 */
		
	}
	

}
