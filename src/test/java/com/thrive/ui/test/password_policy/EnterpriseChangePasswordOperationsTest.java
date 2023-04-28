package com.thrive.ui.test.password_policy;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.password_policy.ChangePasswordPage;

public class EnterpriseChangePasswordOperationsTest extends UserManagementCommonPage {

	ChangePasswordPage changePasswordPage = new ChangePasswordPage();
	ThriveLoginPage login = new ThriveLoginPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	String passwordval = "123abc";
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);

	@Test(description = "Validate while change password Fields cannot empty")
	public void validateChangePasswordCannotEmpty() {

		getExtentTestLogger().log(Status.PASS, "enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		changePasswordPage = thriveHeaderMenuPage.clickChangePassword();

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		changePasswordPage.clickSaveButton();

		getExtentTestLogger().log(Status.PASS,
				"Validate enterprise admin cannot provide empty fields and gets error message");
		changePasswordPage.validateThisFieldRequiredErrorMsgPresent();
	}

	@Test(description = "Validate change password user cannot provide email as password")
	public void validateChangePasswordCannotEmailAddress() {

		getExtentTestLogger().log(Status.PASS, "enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		changePasswordPage = thriveHeaderMenuPage.clickChangePassword();

		getExtentTestLogger().log(Status.PASS, "Provide all password details");
		changePasswordPage.setExistingPassword(eaUserPassword);
		changePasswordPage.setNewPassword(eaUserImmutableEmail);
		changePasswordPage.setConfirmPassword(eaUserImmutableEmail);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		changePasswordPage.clickSaveButton();

		getExtentTestLogger().log(Status.PASS,
				"Validate enterprise admin cannot provide email as password and gets error message");
		changePasswordPage.validategetPasswordInvalidErrorMsgPresent();
	}

	@Test(description = "Validate change password user cannot provide old password")
	public void validateChangePasswordCannotbeOldPassword() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		changePasswordPage = thriveHeaderMenuPage.clickChangePassword();

		getExtentTestLogger().log(Status.PASS, "Provide all password details");
		changePasswordPage.setExistingPassword(eaUserPassword);
		changePasswordPage.setNewPassword(eaUserPassword);
		changePasswordPage.setConfirmPassword(eaUserPassword);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		changePasswordPage.clickSaveButton();

		getExtentTestLogger().log(Status.PASS,
				"Validate Enterprise admin cannot provide old password as new password and gets error message");
		changePasswordPage.validateNewPasswordNotAsOldPasswordErrorMsgPresent();
	}

	@Test(description = "Validate change password user cannot contain common words and numbers")
	public void validateChangePasswordCannotContainCommonWordsAndNumbers() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		changePasswordPage = thriveHeaderMenuPage.clickChangePassword();

		getExtentTestLogger().log(Status.PASS, "Provide all password details");
		changePasswordPage.clickSaveButton();
		changePasswordPage.setExistingPassword(passwordval);
		changePasswordPage.setNewPassword(passwordval);
		changePasswordPage.setConfirmPassword(passwordval);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		changePasswordPage.clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "Validate Enterprise admin cannot provide common words and numbers");
		changePasswordPage.validateNotCommonWordNumberErrorMsgPresent();
	}

	@Test(description = "Validate change password Pattern validation activation")
	public void validateChangePasswordPatternValidationActivation() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		changePasswordPage = thriveHeaderMenuPage.clickChangePassword();

		getExtentTestLogger().log(Status.PASS, "Provide all password details");
		changePasswordPage.clickSaveButton();
		changePasswordPage.setExistingPassword(passwordval);
		changePasswordPage.setNewPassword(passwordval);
		changePasswordPage.setConfirmPassword(passwordval);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		changePasswordPage.clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "Validate pattern validation activation present");
		changePasswordPage.validatePatternValidationActivation();
	}

	@Test(description = "Validate Existing password incorrect validation")
	public void validateExistingPasswordIncorrectValidationActivation() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		changePasswordPage = thriveHeaderMenuPage.clickChangePassword();

		getExtentTestLogger().log(Status.PASS, "Provide all password details");
		changePasswordPage.clickSaveButton();
		changePasswordPage.setExistingPassword(passwordval);
		changePasswordPage.setNewPassword(eaUserPassword);
		changePasswordPage.setConfirmPassword(eaUserPassword);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		changePasswordPage.clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "Validate Existing password incorrect validation");
		changePasswordPage.validateExistingPasswordIncorrectErrorMessage();
	}

}
