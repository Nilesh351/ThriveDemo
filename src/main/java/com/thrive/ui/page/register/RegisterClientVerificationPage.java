package com.thrive.ui.page.register;

import org.openqa.selenium.By;

import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class RegisterClientVerificationPage extends BaseTestPage{
	
	private By getLoginLink() {
		return By.xpath(getXpathByText(".//a[text()='public.verify.login']"));
	}
	
	public ThriveLoginPage clickLogin() {
		LOGGER.info("Clicking Login button");
		click(getLoginLink());
		return new ThriveLoginPage();
	}

}
