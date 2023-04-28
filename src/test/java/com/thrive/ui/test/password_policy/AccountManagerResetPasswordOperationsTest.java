package com.thrive.ui.test.password_policy;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.password_policy.ResetPasswordPage;

public class AccountManagerResetPasswordOperationsTest extends UserManagementCommonPage {

	ThriveLoginPage login = new ThriveLoginPage();
	MailboxPage mailboxPage = new MailboxPage();
	AlertsAndMessagesPage toasterAlert;
	ResetPasswordPage resetPasswordPage = new ResetPasswordPage();
	LoginDetails accountManagerUserUpdateDetails = new LoginDetails(amMutableEmail, amMutablePassword);
	String resetPasswordSubject = ThriveAppSharedData.RESET_PASSWORD.getValue();
	String updateAccountManagerFirstName = amMutableName.split(" ")[0];
	String passwordval = "123abc";

	@Test(description = "Validate while reset password Fields cannot empty")
	public void validateResetPasswordCannotEmpty() {

		getExtentTestLogger().log(Status.PASS, "Provide login details");
		login.resetPassword(accountManagerUserUpdateDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		mailboxPage = new MailboxPage().searchResetPasswordMailAndClick(updateAccountManagerFirstName);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		resetPasswordPage.clickSave();

		getExtentTestLogger().log(Status.PASS,
				"Validate Account Manager cannot provide empty password and gets error message");
		resetPasswordPage.validateThisFieldRequiredErrorMsgPresent();
	}

	@Test(description = "Validate reset password user cannot provide email as password")
	public void validateResetPasswordCannotEmailAddressErrorMsg() {

		getExtentTestLogger().log(Status.PASS, "Provide login details");
		login.resetPassword(accountManagerUserUpdateDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		mailboxPage = new MailboxPage().searchResetPasswordMailAndClick(updateAccountManagerFirstName);

		getExtentTestLogger().log(Status.PASS, "user provide all the password details");
		resetPasswordPage.clickSave();
		resetPasswordPage.setNewPassword(amUserUpdate);
		resetPasswordPage.setConfirmPassword(amUserUpdate);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		resetPasswordPage.clickSave();

		getExtentTestLogger().log(Status.PASS,
				"Validate Account Manager cannot provide email as password and gets error message");
		resetPasswordPage.validategetPasswordInvalidErrorMsgPresent();
	}

	@Test(description = "Validate reset password user cannot provide old password as new  password")
	public void validateResetPasswordCannotOldPasswordErrorMsg() {

		getExtentTestLogger().log(Status.PASS, "Provide login details");
		login.resetPassword(accountManagerUserUpdateDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		mailboxPage = new MailboxPage().searchResetPasswordMailAndClick(updateAccountManagerFirstName);

		getExtentTestLogger().log(Status.PASS, "user provide all the password details");
		resetPasswordPage.clickSave();
		resetPasswordPage.setNewPassword(amPasswordUpdate);
		resetPasswordPage.setConfirmPassword(amPasswordUpdate);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		resetPasswordPage.clickSave();

		getExtentTestLogger().log(Status.PASS,
				"Validate Account Manager cannot provide old password as new password and gets error message");
		resetPasswordPage.validateNewPasswordNotAsOldPasswordErrorMsgPresent();
	}

	@Test(description = "Validate reset password user cannot contain common words and numbers")
	public void validateResetPasswordCannotContainCommonWordsAndNumbers() {

		getExtentTestLogger().log(Status.PASS, "Provide login details");
		login.resetPassword(accountManagerUserUpdateDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		mailboxPage = new MailboxPage().searchResetPasswordMailAndClick(updateAccountManagerFirstName);

		getExtentTestLogger().log(Status.PASS, "user provide all the password details");
		resetPasswordPage.clickSave();
		resetPasswordPage.setNewPassword(passwordval);
		resetPasswordPage.setConfirmPassword(passwordval);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		resetPasswordPage.clickSave();

		getExtentTestLogger().log(Status.PASS, "Validate Account Manager cannot provide common words and numbers");
		resetPasswordPage.validateNotCommonWordNumberErrorMsgPresent();
	}

	@Test(description = "Validate reset password user email should be case insensitive when sending mail")
	public void validateResetPasswordEmailCaseInsensitive() {

		getExtentTestLogger().log(Status.PASS, "Provide email address to reset password");
		login.resetPassword(accountManagerUserUpdateDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		mailboxPage.validateEmailPresentForResetPassword(updateAccountManagerFirstName);

	}

	@Test(description = "Validate reset password Pattern validation activation")
	public void validateResetPasswordPatternValidationActivation() {

		getExtentTestLogger().log(Status.PASS, "Provide login details");
		login.resetPassword(accountManagerUserUpdateDetails);

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and click on reset");
		mailboxPage = new MailboxPage().searchResetPasswordMailAndClick(updateAccountManagerFirstName);

		getExtentTestLogger().log(Status.PASS, "user provide all the password details");
		resetPasswordPage.clickSave();
		resetPasswordPage.setNewPassword(passwordval);
		resetPasswordPage.setConfirmPassword(passwordval);

		getExtentTestLogger().log(Status.PASS, "click on the save button");
		resetPasswordPage.clickSave();

		getExtentTestLogger().log(Status.PASS, "Validate pattern validation activation present");
		resetPasswordPage.validatePatternValidationActivation();
	}

}
