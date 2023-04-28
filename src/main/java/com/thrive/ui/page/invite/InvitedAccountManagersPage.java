package com.thrive.ui.page.invite;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class InvitedAccountManagersPage extends UserManagementCommonPage{
	
	private By getEmailAddressColumn(String email) {
		return By.xpath(getXpathByText("//th[contains(text(),'admin.account_manager.view.col_email')]/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	private By getAccountManagerCheckbox(String email) {
		return By.xpath(getXpathByText("//th[(text())='admin.user_management.acc_manager.emailid']/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']/../..//p-checkbox//div[contains(@class,'ui-chkbox-box')]"));
	}
	
	private By getCheckbox(int i) {
		return By.xpath(".//div[@class='ui-table-scrollable-body ng-star-inserted']//tr["+i+"]//p-checkbox");
	}
	
	private By getSelectedCheckbox() {
		return By.xpath(".//div[contains(@class,'ui-state-active')]");
	}
	
	public InvitedAccountManagersPage validateAccountManagerPresent(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)));
		return this;	
	}
	
	public InvitedAccountManagersPage validateAccountManagerNotPresent(String email) {
		LOGGER.info("Validating Account Manager not is present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)));
		return this;
	}
	
	private By getEmailAddressColumnRm(String email) {
		return By.xpath(getXpathByText("//th[(text())='private.profile.account_manager.email_address']/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	public InvitedAccountManagersPage checkAccountManagerCheckbox(String email) {
		click(getAccountManagerCheckbox(email));
		return this;
	}
	
	public InvitedAccountManagersPage validateAccountManagerPresentInsideInvite(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumnRm(email)));
		return this;	
	}
	
	
	public InvitedAccountManagersPage checkInvitedAccountManagerCheckbox(String email) {
		click(getAccountManagerCheckbox(email));
		return this;
	}
	
	public InvitedAccountManagersPage validateResetFilters() {
		LOGGER.info("Validating reset filter works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		//String enterpriseFilterText = getText(getFilterByRoleDropdownPlaceHolder());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		//softAssert.assertTrue(enterpriseFilterText.equalsIgnoreCase("FILTER BY ENTERPRISE"), "Failed to reset the Filter by Enterprise dropdown");
		softAssert.assertAll();
		return this;
	}
	
	private By getSearchValue() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	public InvitedAccountManagersPage validateClearFilters() {
		LOGGER.info("Validating reset filter works as expected");
		shortWait();
		int displayedRowsCount = findElements(getRowsLink()).size();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(displayedRowsCount > 1, "Clear Filter does not working properly");
		softAssert.assertTrue(isElementPresent(getSearchValue()));
		softAssert.assertAll();
		return this;
	}
	
	private By getActionsReInviteOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.acc_manager.reinvite_acc_manager']"));
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getActionsDeleteOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.acc_manager.delete_acc_manager']"));
	}
	
	public InvitedAccountManagersPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsReInviteOption()),"re-invite option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteOption()),"delete option not present");
		softAssert.assertAll();
		return this;
	}
	

}
