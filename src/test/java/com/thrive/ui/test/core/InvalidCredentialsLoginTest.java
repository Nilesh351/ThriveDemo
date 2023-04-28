package com.thrive.ui.test.core;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class InvalidCredentialsLoginTest extends UserManagementCommonPage{
	
	
	ExtentTest extentTestLocal;
	ThriveLoginPage login = new ThriveLoginPage();
	
	@Test(description =  "Validate all users not able to login with invalid credentials", dataProvider = "InvalidLoginDataProvider", dataProviderClass = LoginTestDataProvider.class) 
	public void validatAllInvalidLoginCheck(LoginDetails loginData) {
		
		extentTestLocal = extentReports.createTest(loginData.getTestName(), loginData.getTestDescription());
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method - " + loginData.getTestName() + " start");

		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION.getValue());

		getExtentTestLogger().log(Status.PASS, "Login to the application with "+loginData.getUserType()+" Invalid user credentials");
		login.loginDetails(loginData);

		getExtentTestLogger().log(Status.PASS, "Validate error message present");
		login.validateLoginErrorMessagePresent();
	}
	

}
