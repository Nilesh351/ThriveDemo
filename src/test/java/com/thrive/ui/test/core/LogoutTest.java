package com.thrive.ui.test.core;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class LogoutTest extends UserManagementCommonPage{
	
	ThriveLoginPage login = new ThriveLoginPage();
	ExtentTest extentTestLocal;
	
	@Test(description =  "Validate all users able to logout successfully", dataProvider = "LogoutDataProvider", dataProviderClass = LoginTestDataProvider.class)
	public void validatAllLogoutSuccessful(LoginDetails loginData) {
		
		extentTestLocal = extentReports.createTest(loginData.getTestName(), loginData.getTestDescription());
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method - " + loginData.getTestName() + " start");

		getExtentTestLogger().assignCategory(TestCategory.USER_AUTHENTICATION.getValue());

		ThriveHeaderMenuPage thriveHeaderMenuPage;

		getExtentTestLogger().log(Status.PASS, "Login to the application with "+loginData.getUserType()+" user credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginData);

		getExtentTestLogger().log(Status.PASS, "user logs out from system");
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Validate user logs out from system successfully");
		login.ValidateUsernamePresent();
	}

}
