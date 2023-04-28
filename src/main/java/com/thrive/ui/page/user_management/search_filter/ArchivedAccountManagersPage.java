package com.thrive.ui.page.user_management.search_filter;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;

public class ArchivedAccountManagersPage extends UserManagementCommonPage{
	
	
	private By getEmailAddressColumn(String email) {
		return By.xpath("//th[contains(text(),'Email')]/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']");
	}
	
	private By getAccountManagersCheckboxFromEmail(String mailId) {
		return By.xpath("//span[contains(text(),'"+mailId+"')]//ancestor::tr//descendant::p-checkbox");
	}
	
	private By getCheckbox(int i) {
		return By.xpath(".//div[@class='ui-table-scrollable-body ng-star-inserted']//tr["+i+"]//p-checkbox");
	}
	
	private By getSelectedCheckbox() {
		return By.xpath(".//div[contains(@class,'ui-state-active')]");
	}
	
	private By getAccountManagersButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.acc_manager.account_managers']"));
	}
	
	
	public AccountManagersPage clickAccountManagersButton() {
		LOGGER.info("Clicking account managers button");
		click(getAccountManagersButton());
		return new AccountManagersPage();
	}
	
	public ArchivedAccountManagersPage validateAccountManagerPresent(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)));
		return this;
		
	}
	
	public ArchivedAccountManagersPage validateAccountManagerNotpresent(String email) {
		LOGGER.info("Validating Account Manager name is not present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)));
		return this;
	}
	
	public ArchivedAccountManagersPage checkArchivedAccountManagerCheckbox(String email) {
		click(getAccountManagersCheckboxFromEmail(email));
		return this;
	}
	
	private By getAccountManagerNameLink(String email) {
		return By.xpath(".//span[normalize-space(text())='"+email+"']/../preceding-sibling::td[1]//a");
	}
	
	public RegisterAccountMangerPersonalDetailsPage clickOnSearchedAccountManagerName(String email) {
		LOGGER.info(" Clicking on Search Account Manager name");
		click(getAccountManagerNameLink(email));
		return new RegisterAccountMangerPersonalDetailsPage();
	}
	
	private By getAccountManagerNameLink() {
		return By.xpath(".//td[@id='name']//span[contains(@class,'ng-star-inserted')]//a");
	}
	
	public RegisterAccountMangerPersonalDetailsPage clickOnAccountManagerName() {
		LOGGER.info(" Clicking on Account Manager name");
		click(getAccountManagerNameLink());
		return new RegisterAccountMangerPersonalDetailsPage();
	}
	
	private By getSearchValue() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	private By getFilterByEnterpriseDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.users.enterprises']"));
	}
	
	public ArchivedAccountManagersPage validateClearFilter() {
		LOGGER.info("Validate clicking Clear Filter resets all data");
		shortWait();
		int displayedRowsCount = findElements(getRowsLink()).size();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(displayedRowsCount > 1, "Clear Filter does not working properly");
		softAssert.assertTrue(isElementPresent(getSearchValue()));
		softAssert.assertTrue(isElementPresent(getFilterByEnterpriseDropdownValue()));
		softAssert.assertAll();
		return this;
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getUnArchiveElementArchivedAccountManager() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.acc_manager.unarchive_acc_manager']"));
	}
	
	private By getDeleteElementArchivedAccountManager() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.acc_manager.delete_acc_manager']"));
	}
	
	public ArchivedAccountManagersPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getUnArchiveElementArchivedAccountManager()),"Un-Archive option not present");
		softAssert.assertTrue(isElementPresent(getDeleteElementArchivedAccountManager()),"Delete option not present");
		softAssert.assertAll();
		return this;
	}
	
	
	
}
