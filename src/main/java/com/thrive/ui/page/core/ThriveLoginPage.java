package com.thrive.ui.page.core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.OtpUtil;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class ThriveLoginPage extends BaseTestPage{
	
	private By getUsernameFiled() {
		LOGGER.info("Get the username Field");
		return By.xpath(".//div[@id='login-container']//input[@name='email']");
	} 
		
	private By getPasswordField() {
		LOGGER.info("Get the Password Field");
		return By.xpath(".//div[@id='login-container']//input[@name='password']");
	} 
	
	private By getNextButton() {
		LOGGER.info("Get the username Field");
		return By.xpath(getXpathByText(".//div[@id='login-container']//button[normalize-space(text())='private.book_session.book.step1.next']"));
	}


	private By getSubmitButton() {
		LOGGER.info("Get Submit Button");
		return By.xpath(getXpathByText(".//div[@id='login-container']//button[normalize-space(text()='public.login.login')]"));
	}

	private By clickNext() {
		return By.xpath(getXpathByText("//div[@id='login-container']//button[contains(text(),'private.book_session.book.step1.next')]"));
	}
	
	private By getLoginButton() {
		return By.xpath(".//div[@id='login-container']//a[contains(text(),'Log')]");
	}
	
	public ThriveLoginPage clickLogin() {
		LOGGER.info("Click login button");
		click(getLoginButton());
		return this;
	}
	
	
	public ThriveLoginPage setUsername(String username) {
		LOGGER.info("Set Username");
		mediumWait();
		//Using below method  because sometime it does not work with other setValue method
		setValueWithoutValidation(username,getUsernameFiled());
		scrollByJavaScript("0","500");
		return this;
	}
	
	
	public ThriveLoginPage setPassword(String password) {
		LOGGER.info("Set Password");
		setValue(password,getPasswordField());
		return this;
	}
	
	public ThriveLoginPage clickOnSubmit() {
		LOGGER.info("Click on Submit");
		click(getSubmitButton());
		return this;
	}
	
	public ThriveLoginPage clickAccept() {
		LOGGER.info("Click on the accept cookie button");
		if (isElementPresent(getAccceptButton())) {
			click(getAccceptButton());
		}
		return this;
	}
	
	private By getAccceptButton() {
		return By.xpath(getXpathByText(".//a[text()='public.cookie_accept']"));
	}
	

	private By getCodeInput() {
		return By.xpath("//otp-input//input");
	}
	
	public ThriveLoginPage setCode(String code) {
		setValue(String.valueOf(code), getCodeInput());
		return this;
	}
	
	private By getCodeInputBox() {
		return By.xpath(".//input[@autocomplete='one-time-code']");
	}
	
	public ThriveLoginPage setAuthCode(String code) {
		
		List<WebElement> inputBoxes = findElements(getCodeInputBox());
		int codeInputBoxCount = findElements(getCodeInputBox()).size();
		for(int i = 0 ; i<codeInputBoxCount ; i++ ) {
			setValue(Config.getWaitSecsMedium(), String.valueOf(code.charAt(i)), inputBoxes.get(i));
		}
		return this;
	}
	

	private By getLanguageDropdown() {
		LOGGER.info("Get the Dropdown");
		return By.xpath(".//div[@id='login-container']//ng-select[@name='langFilter']");
	}

	private By getLanguageDropdownOptionElement(String language) {
		LOGGER.info("Get the language from  Dropdown");
		return By.xpath(".//div[@id='login-container']//span[text()='"+language+"']");
	} 
	
	private By getResendVerificationButton() {
		return By.xpath(getXpathByText(".//button[text()='shared.modals.verify_modal.resend_button']"));
	}
	
	public ThriveLoginPage clickResendVerificationButton() {
		LOGGER.info("click resend verification button");
		click(getResendVerificationButton());
		return this;
	}
	
	public ThriveLoginPage selectLanguage() {
		LOGGER.info("select language from language dropdown");
		click(getLanguageDropdown());
		if(Config.getLocalizationLanguage().equalsIgnoreCase("en")) {
			click(getLanguageDropdownOptionElement(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue()));
		} else if (Config.getLocalizationLanguage().equalsIgnoreCase("fr")) {
			click(getLanguageDropdownOptionElement(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue()));
		}

		return this;
	}
	
	public ThriveLoginPage clickonNext() {
		click(clickNext());
		return this;
	}
	
	private ThriveLoginPage enterLoginDetails(LoginDetails loginDetails) {
		setUsername(loginDetails.getUsername()).clickonNext()
		.setPassword(loginDetails.getPassword()).clickOnSubmit();
		
		if(Config.isMfaEnforced() && loginDetails.getUsername().equalsIgnoreCase(superAdminUser)) {
			String authCode = OtpUtil.getAuthCode();
			setAuthCode(authCode);
		}
		return this;
	}
	
	private ThriveLoginPage enterLoginDetailsRm(LoginDetails loginDetails) {
		setUsername(loginDetails.getUsername()).setPassword(loginDetails.getPassword()).clickOnSubmit();
		
		if (Config.isMfaEnforced() && loginDetails.getUsername().equalsIgnoreCase(superAdminUser)) {
			String authCode = OtpUtil.getAuthCode();
			setAuthCode(authCode);
		}
		return this;
	}
	
	
	public ThriveHeaderMenuPage login(LoginDetails loginDetails) {	
		LOGGER.info("Mythrive Login");	
		loginPrivate(loginDetails);
		if(isElementVisible(getAccceptButton())) {
			clickAccept();
		}
		return new ThriveHeaderMenuPage();
	}
	
	public ThriveLoginPage loginDetails(LoginDetails loginDetails) {
		LOGGER.info("Validate login details");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			shortWait();
			setUsername(loginDetails.getUsername()).clickonNext()
			.setPassword(loginDetails.getPassword()).clickOnSubmit();
		} else {
			selectLanguage();
			setUsername(loginDetails.getUsername()).setPassword(loginDetails.getPassword()).clickOnSubmit();
		}
		return this;
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}

	public ThriveLoginPage validateLoginErrorMessagePresent() {
		LOGGER.info("Validate error message present if invalid credentials provided");
		String actualMessage = getText(getAlertElement());
		String expectedMessage;
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			expectedMessage = ThriveAppSharedData.LOGIN_ERROR_MESSAGE.getValue();
		} else {
			expectedMessage = ThriveAppSharedData.LOGIN_ERROR_MESSAGE_RM.getValue();
		}
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
				+ expectedMessage + " and actual is : " + actualMessage);
		return this;
	}
	
	public ThriveLoginPage ValidateUsernamePresent() {
		LOGGER.info("Validate username field present");
		Assert.assertTrue(isElementPresent(getUsernameFiled()));
		return this;
	}
	
	private By getForgotYourPasswordLink() {
		return By.xpath(getXpathByText(".//div[@id='login-container']//a[normalize-space(text())='public.login.forgot']"));
	}

	public ThriveLoginPage clickForgotYourPasswordLink() {
		LOGGER.info("Click Forgot your password link");
		mediumWait();
		click(getForgotYourPasswordLink());
		return this;
	}

	private By getResetMyPasswordButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'coaching_program.users.reset_filter')]"));
	}

	public ThriveLoginPage clickResetMyPassword() {
		LOGGER.info("Click Reset my password Button");
		click(getResetMyPasswordButton());
		return this;
	}
	
	public ThriveLoginPage resetPassword(LoginDetails loginDetails) {
		try {
			clickAccept();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			setUsername(loginDetails.getUsername().toUpperCase()).clickonNext().clickForgotYourPasswordLink()
					.setUsername(loginDetails.getUsername()).clickResetMyPassword();
		} else {
			selectLanguage();
			setUsername(loginDetails.getUsername()).clickForgotYourPasswordLink()
					.setUsername(loginDetails.getUsername()).clickResetMyPassword();
		}
		return this;
	}
	
	public ThriveHeaderMenuPage loginIgnoreAccept(LoginDetails loginDetails) {
		
		LOGGER.info("Mythrive Login with No Accept Pop Up");
		loginPrivate(loginDetails);
		return new ThriveHeaderMenuPage();
	}
	
	private ThriveHeaderMenuPage loginPrivate(LoginDetails loginDetails) {
		switch(Config.getTestPlatform()) {

		case TestPlatform.RM :
			mediumWait();
			selectLanguage();	
			enterLoginDetailsRm(loginDetails);
			
			break;
			
		case TestPlatform.THRIVE :
			enterLoginDetails(loginDetails);
			break;
			
		case TestPlatform.THRIVEUAT :
			enterLoginDetails(loginDetails);
			break;
			
		case TestPlatform.RMUAT :
			mediumWait();
			selectLanguage();	
			enterLoginDetailsRm(loginDetails);
			break;
			
		}
				
		return new ThriveHeaderMenuPage();
	}
		
	
}