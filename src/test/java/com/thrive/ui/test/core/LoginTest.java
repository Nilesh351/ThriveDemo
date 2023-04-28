package com.thrive.ui.test.core;

import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;



public class LoginTest extends BaseTestPage {

	/**
	 * @type - data driven login test using data provider
	 * This single test covers all 6 test case for login with 6 different types of user
	 * Test case id - 
	 *
	 */
	
	ExtentTest extentTestLocal;
	
	@Test(description =  "Validate all users able to login successfully", dataProvider = "LoginDataProvider", dataProviderClass = LoginTestDataProvider.class)
  
	public void validatAllLoginSuccessful(LoginDetails loginData) {
		
		extentTestLocal = extentReports.createTest(loginData.getTestName(), loginData.getTestDescription());
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method - " + loginData.getTestName() + " start");

		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION.getValue());

		ThriveHeaderMenuPage thriveHeaderMenuPage;

		getExtentTestLogger().log(Status.PASS, "Login to the application with "+loginData.getUserType()+" user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginData);

		getExtentTestLogger().log(Status.PASS, "Validate user logs in successfully");
		thriveHeaderMenuPage.validateLoginSuccessful();

	}
	
	
	

}
