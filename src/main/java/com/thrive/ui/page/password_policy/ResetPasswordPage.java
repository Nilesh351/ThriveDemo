package com.thrive.ui.page.password_policy;

import org.openqa.selenium.By;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class ResetPasswordPage extends UserManagementCommonPage{
	
	
	private By getNewPasswordInput() {
		return By.xpath(".//div[@id='login-container']//input[@name='password']");
	}
	
	private By getConfirmPasswordInput() {
		return By.xpath(".//div[@id='login-container']//input[@name='confirmPassword']");
	}
	
	private By getSaveButton() {
		return By.xpath(".//div[@id='login-container']//button[text()='Save']");
	}
	
	private By getUpdatePasswordButton() {
		return By.xpath(getXpathByText(".//div[@id='login-container']//button[contains(text(),'public.reset.save')]"));
	}
	
	public ResetPasswordPage setNewPassword(String password) {
		LOGGER.info("reset new password");
		shortWait();
		setValue(password, getNewPasswordInput());
		return this;
	}
	
	
	public ResetPasswordPage setConfirmPassword(String password) {
		LOGGER.info("reset confirm password");
		shortWait();
		setValue(password, getConfirmPasswordInput());
		return this;
	}
	
	public ResetPasswordPage clickSave() {
		shortWait();
		LOGGER.info("Click Save Button");
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			click(getSaveButton());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			click(getUpdatePasswordButton());
		}
		return this;
	}


}
