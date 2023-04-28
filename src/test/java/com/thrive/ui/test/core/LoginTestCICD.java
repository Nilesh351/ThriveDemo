package com.thrive.ui.test.core;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class LoginTestCICD extends BaseTestPage{

	
	
	
	@Test(description = "Validate SA user is able to login successfully")
	public void validateSaLoginSuccessful() {

		getExtentTestLogger().assignCategory("User Authentication");

		LoginDetails loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
		ThriveHeaderMenuPage thriveHeaderMenuPage;

		getExtentTestLogger().log(Status.PASS, "Login to the application with SA credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetails);

		getExtentTestLogger().log(Status.PASS, "Validate SA user logs in successfully");
		thriveHeaderMenuPage.validateLoginSuccessful();

	}
	
	@Test(description = "Validate EA user is able to login successfully")
	public void validateEaLoginSuccessful() {

		getExtentTestLogger().assignCategory("User Authentication");

		LoginDetails loginDetailsEa = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
		ThriveHeaderMenuPage thriveHeaderMenuPage;

		getExtentTestLogger().log(Status.PASS, "Login to the application with EA credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsEa);

		getExtentTestLogger().log(Status.PASS, "Validate EA user logs in successfully");
		thriveHeaderMenuPage.validateLoginSuccessful();

	}

	@Test(description = "Validate Client user is able to login successfully")
	public void validateClientLoginSuccessful() {

		getExtentTestLogger().assignCategory("User Authentication");

		LoginDetails loginDetailsClient = new LoginDetails(clientUser, clientPassword);
		ThriveHeaderMenuPage thriveHeaderMenuPage;

		getExtentTestLogger().log(Status.PASS, "Login to the application with Client credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsClient);

		getExtentTestLogger().log(Status.PASS, "Validate Client user logs in successfully");
		thriveHeaderMenuPage.validateLoginSuccessful();

	}
	
	@Test(description = "Validate Global Coach user is able to login successfully")
	public void validateGlobalCoachLoginSuccessful() {

		getExtentTestLogger().assignCategory("User Authentication");

		LoginDetails loginDetailsGlobalCoach = new LoginDetails(globalCoachUser, globalCoachPassword);
		ThriveHeaderMenuPage thriveHeaderMenuPage;

		getExtentTestLogger().log(Status.PASS, "Login to the application with Global Coach credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsGlobalCoach);

		getExtentTestLogger().log(Status.PASS, "Validate Global Coach user logs in successfully");
		thriveHeaderMenuPage.validateLoginSuccessful();

	}

	@Test(description = "Validate Internal Coach user is able to login successfully")
	public void validateInternalCoachLoginSuccessful() {

		getExtentTestLogger().assignCategory("User Authentication");

		LoginDetails loginDetailsInternalCoach = new LoginDetails(internalCoachUser, internalCoachPassword);
		ThriveHeaderMenuPage thriveHeaderMenuPage;

		getExtentTestLogger().log(Status.PASS, "Login to the application with Internal Coach credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsInternalCoach);

		getExtentTestLogger().log(Status.PASS, "Validate Internal Coach user logs in successfully");
		thriveHeaderMenuPage.validateLoginSuccessful();

	}

	@Test(description = "Validate Account Manager user is able to login successfully")
	public void validateAccountManagerLoginSuccessful() {

		getExtentTestLogger().assignCategory("User Authentication");

		LoginDetails loginDetailsAm = new LoginDetails(accountManagerUser, accountManagerPassword);
		ThriveHeaderMenuPage thriveHeaderMenuPage;

		getExtentTestLogger().log(Status.PASS, "Login to the application with Account Manager credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(loginDetailsAm);

		getExtentTestLogger().log(Status.PASS, "Validate Account Manager user logs in successfully");
		thriveHeaderMenuPage.validateLoginSuccessful();

	}
	
}
