package com.thrive.ui.page.register;

import org.openqa.selenium.By;

import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class ChangeEmailAddressPage extends UserManagementCommonPage{
	
	
	private By getNewEmailAddressInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'shared.modals.email_address_change_modal.new_email_label')]//following-sibling::input"));
	}
	
	private By getConfirmNewEmailAddresInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'shared.modals.email_address_change_modal.confirm_new_email_label')]//following-sibling::input"));
	}
	
	
	private By getSaveChangesButton() {
		return By.xpath(getXpathByText(".//span[text()='shared.modals.email_address_change_modal.confirm_new_email_message']/..//following-sibling::button"));
	}
	
	
	public AlertsAndMessagesPage sendEmailDetails(String email) {
		LOGGER.info("Provide email details");
		setValue(email, getNewEmailAddressInput());
		setValue(email, getConfirmNewEmailAddresInput());
		click(getSaveChangesButton());
		return new AlertsAndMessagesPage();
	}
	
	public ChangeEmailAddressPage validateEmailDeatils(String newEmail,String confirmEmail) {
		LOGGER.info("Validate email details");
		setValue(newEmail, getNewEmailAddressInput());
		setValue(confirmEmail, getConfirmNewEmailAddresInput());
		click(getSaveChangesButton());
		return this;
	}
	
	
	

}
