package com.thrive.ui.page.register;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

import com.thrive.ui.core.BaseTestPage;

public class RegisterClientAboutPage extends BaseTestPage {
	
	private By getAboutTextArea() {
		return By.xpath(getXpathByText(".//label[(text())= 'register.coach.register.personal.step_6_name']/following-sibling::textarea"));
	}
	
	private By getConsentCheckbox() {
		return By.xpath(".//input[@id='email_updates']/following-sibling::span");
	}
	
	private By getRegisterButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step6.register']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step6.previous']"));
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	public RegisterClientAboutPage registerClientInfo(String aboutText) {
		LOGGER.info("Registering client information");
		try {
			setValue(aboutText, getAboutTextArea());
		} catch (StaleElementReferenceException e) {
			setValue(aboutText, getAboutTextArea());
		}
		click(getConsentCheckbox());
		click(getRegisterButton());
		return this;
		
	}
	
	public RegisterClientAboutPage  validateClientRegistrationSuccessful(String expectedSuccessMessage) {
		LOGGER.info("Validating client registration is successful");
		String actualMessage = getText(getAlertElement());
		Assert.assertEquals(actualMessage, expectedSuccessMessage, "Client Registration Failed With Error : " + actualMessage );
		return this;
	}
	

}
