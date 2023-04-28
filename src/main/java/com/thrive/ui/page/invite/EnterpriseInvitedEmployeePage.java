package com.thrive.ui.page.invite;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class EnterpriseInvitedEmployeePage extends UserManagementCommonPage{
	
	protected By getSearchInput() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//input[@placeholder='Search']");
		} else {
			return By.xpath(".//input[@placeholder='Rechercher']");
		}
	}
	
	private By getResetFilterLink() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.users.reset_filter']"));
	}
	
	private By getEmailAddressElement(String username) {
		return By.xpath(".//td[@class='ng-star-inserted']//span[contains(text(),'"+username+"')]");
	}
	
	private By getReInviteEmployeeOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.empolyee.reinvite_empolyee)']"));
	}
	
	public UserManagementCommonPage clickReInviteEmployee() {
		click(getReInviteEmployeeOption());
		return this;
	}
	
	private By getActionsEmployeeDeleteOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.clients.archive.delete']"));
	}
	
	public UserManagementCommonPage clickDeleteEmployee() {
		click(getActionsEmployeeDeleteOption());
		return this;
	}
	
	private By getInvitedEmployeeCheckbox(String email) {
		return By.xpath(".//span[contains(text(),'"+email.toLowerCase()+"')]/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	public EnterpriseInvitedEmployeePage clickInvitedEmployeeCheckbox(String employee) {
		waitUntilElementIsPresent(getInvitedEmployeeCheckbox(employee));
		click(getInvitedEmployeeCheckbox(employee));
		return this;
	}
	
	private By getActionsDropdown() {
		return By.xpath(".//button[contains(@class,'action-btn')]");
	}
	
	private By getclickReInviteEmployee() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.empolyee.reinvite_empolyee']"));
	}
	
	
	public AlertsAndMessagesPage selectReinviteEmployeeAction() {
		LOGGER.info("VClicking Re-Invite Employee link");
		click(getActionsDropdown());
		click(getclickReInviteEmployee());
		return new AlertsAndMessagesPage() ;
	}
	
	private By getclickDeleteEmployee() {
		return By.xpath(getXpathByText(".//span[text()='admin.clients.archive.delete']"));
	}
	
	public AlertsAndMessagesPage selectDeleteEmployeeAction() {
		LOGGER.info("Clicking delete employee form Actions dropdown");
		click(getActionsDropdown());
		click(getclickDeleteEmployee());
		return new AlertsAndMessagesPage();
	}
	
	
	public UserManagementCommonPage clickClearFilters() {
		LOGGER.info("Clicking reset filter");
		click(getResetFilterLink());
		return this;
	}
	
	public EnterpriseInvitedEmployeePage validateInvitedEmployeeEmailPresent(String username) {
		LOGGER.info("Validating user name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressElement(username.toLowerCase())), "Failed to find the username element with name :" + username);
		return this;
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath(getXpathByText("//th[contains(text(),'admin.employees.employees.col_invited.email')]/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}	
	
	public EnterpriseInvitedEmployeePage validateAccountManagerNotPresent(String email) {
		LOGGER.info("Validating Account Manager not is present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)));
		return this;
	}
	
	public EnterpriseInvitedEmployeePage validateResetFilters() {
		LOGGER.info("Validating reset filter works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		softAssert.assertAll();
		return this;
	}
	
	
	private By getActionsReInviteEmployeeOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.empolyee.reinvite_empolyee']"));
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getActionsDeleteEmployeeOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.empolyee.delete_empolyee']"));
	}
	
	public EnterpriseInvitedEmployeePage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsReInviteEmployeeOption()),"re-invite employee option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteEmployeeOption()),"delete employee option not present");
		softAssert.assertAll();
		return this;
	}
	
	
}