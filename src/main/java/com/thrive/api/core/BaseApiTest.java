package com.thrive.api.core;

import com.aventstack.extentreports.ExtentTest;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.MailboxPage;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseApiTest {

	RequestSpecBuilder requestSpecBuilder;

	public RequestSpecBuilder setContentType() {
		this.requestSpecBuilder = new RequestSpecBuilder();
		return requestSpecBuilder.setContentType(ContentType.JSON).setAccept(ContentType.JSON);
	}

	protected RequestSpecification getLoginRequestSpecification() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/auth").build();

	}
	
	protected RequestSpecification getInvitedClientReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/clients/invitedList").build();

	}
	
	protected RequestSpecification getInvitedAMReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/account-managers/invitedList").build();

	}
	
	protected RequestSpecification getInvitedEntReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/company/invitedList").build();

	}
	
	protected RequestSpecification getRegisterUserReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/register").build();
	}
	
	protected RequestSpecification getRegisterEntUserReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/register/company").build();
	}
	
	protected RequestSpecification getRegisterAMUserReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/register/account-manager").build();
	}
	
	protected RequestSpecification getRegisterCoachUserReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/register/coach")
				.build();
	}
	
	protected RequestSpecification getRegisterInternalCoachUserReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/register/company/coach")
				.build();
	}
	
	protected RequestSpecification getInviteUserReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/invite")
				.build();
	}
	
	protected RequestSpecification getInviteCoachReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/invite/coach")
				.build();

	}
	
	protected RequestSpecification getInviteInternalCoachReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/invite/company/coach")
				.build();

	}
	
	protected RequestSpecification getInviteAMReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/invite/account-manager")
				.build();

	}
	
	protected RequestSpecification getInviteEntReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/invite/company")
				.build();

	}
	
	protected RequestSpecification getLoginReqSpec() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/auth")
				.build();

	}
	
	protected RequestSpecification getLoginReqSpecSqlInjected() {
		return setContentType().setBaseUri(Config.getLoginPageURL()).setBasePath("/api/auth?(SELECT * FROM PG_SLEEP(5))--")
				.build();

	}

	protected String getApiAuthCode() {

		String body = "{\r\n" + "    \"username\": \"" + Config.getSuperuserUser() + "\","+ "\r\n"
		+ "    \"password\": \"" + Config.getSuperuserPassword() + "\",\r\n" 
				+ "    \"timezone\": \"Asia/Calcutta\",\r\n"
				+ "    \"locale\": \"en\"\r\n" + "}";

		Response response = RestAssured.given().spec(getLoginRequestSpecification()).and().body(body).when().post();

		response.then().log().all();
		return "Bearer " + response.jsonPath().get("token").toString();
	}
	
	protected String getApiAuthCodeEA() {

		String body = "{\r\n" + "    \"username\": \"" + Config.getEAUserName() + "\","+ "\r\n"
		+ "    \"password\": \"" + Config.getEAUserPassword() + "\",\r\n" 
				+ "    \"timezone\": \"Asia/Calcutta\",\r\n"
				+ "    \"locale\": \"en\"\r\n" + "}";

		Response response = RestAssured.given().spec(getLoginRequestSpecification()).and().body(body).when().post();

		response.then().log().all();
		return "Bearer " + response.jsonPath().get("token").toString();
	}
	
	protected String getRegisterTokenAM(String firstName) {
		BaseTestPage baseTestPageUi = new BaseTestPage();
		new MailboxPage().searchAMMailAndClick(firstName);
		return baseTestPageUi.getCurrentUrl().split("=")[1];
	}
	
	protected String getRegisterTokenClient(String firstName) {
		BaseTestPage baseTestPageUi = new BaseTestPage();
		new MailboxPage().searchClientMailAndClick(firstName);
		return baseTestPageUi.getCurrentUrl().split("=")[1];
	}
	
	protected String getRegisterCoachToken(String firstName) {
		BaseTestPage baseTestPageUi = new BaseTestPage();
		new MailboxPage().searchCoachMailAndClick(firstName);
		return baseTestPageUi.getCurrentUrl().split("=")[1];
	}
	
	protected String getRegisterInternalCoachToken(String firstName) {
		BaseTestPage baseTestPageUi = new BaseTestPage();
		new MailboxPage().searchInternalCoachMailAndClick(firstName);
		return baseTestPageUi.getCurrentUrl().split("=")[1];
	}
	
	protected String getRegisteredEntToken(String firstName) {
		BaseTestPage baseTestPageUi = new BaseTestPage();
		new MailboxPage().searchEnterpriseMailAndClick(firstName);
		return baseTestPageUi.getCurrentUrl().split("=")[1];
	}
	
	protected ExtentTest getExtentTestLogger() {
		return ExtentReportUtils.getExtentTest();
	}
	


}
