package com.thrive.ui.page.register;

import org.openqa.selenium.By;

import com.thrive.ui.core.BaseTestPage;

public class AdministratorDetailsPage extends BaseTestPage{
	
	
	private By getChangeEmailAddressLink() {
    	return By.xpath(getXpathByText(".//a[contains(text(),'private.profile.admin.field_email_change')]"));
    }
	
	public ChangeEmailAddressPage clickChangeEmailAddressLink() {
    	LOGGER.info("Clicking Change email address link");
    	click(getChangeEmailAddressLink());
    	return new ChangeEmailAddressPage();
    }
	
}
