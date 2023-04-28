package com.thrive.ui.page.password_policy;

import org.openqa.selenium.By;

import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class ChangePasswordPage extends UserManagementCommonPage {
	
	private By getExistingPasswordInput() {
		return By.xpath(".//input[@name='password_existing']");
	}
	
	private By getNewPasswordInput() {
		return By.xpath(".//input[@placement ='right' and @name='password']");
	}
	
	private By getConfirmPasswordInput() {
		return By.xpath(".//input[@name='confirmPassword']");
	}
	
	private By getSaveButton() {
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			return By.xpath(getXpathByText(".//button[text()='private.profile.password.save']"));
		}else {
			return By.xpath(getXpathByText(".//button[text()='private.profile.password.save']"));
		}
	}
	
	public ChangePasswordPage setExistingPassword(String password) {
		LOGGER.info("Provide existing password");
		shortWait();
		setValue(password, getExistingPasswordInput());
		return this;
	}
	
	public ChangePasswordPage setNewPassword(String password) {
		LOGGER.info("Provide New password");
		shortWait();
		setValue(password, getNewPasswordInput());
		return this;
	}
	
	public ChangePasswordPage setConfirmPassword(String password) {
		LOGGER.info("confirm the password");
		shortWait();
		setValue(password, getConfirmPasswordInput());
		return this;
	}
	
	public ChangePasswordPage clickSaveButton() {
		LOGGER.info("click on save button");
		click(getSaveButton());
		return this;
	}

}
