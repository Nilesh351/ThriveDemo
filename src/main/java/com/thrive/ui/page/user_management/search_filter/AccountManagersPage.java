package com.thrive.ui.page.user_management.search_filter;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.invite.InvitedAccountManagersPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;

public class AccountManagersPage extends UserManagementCommonPage{
	
	private By getActionDropdown() {
		return By.xpath(".//button[contains(@class,'action-btn')]");
	}
	
	private By getAccountManagersButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.acc_manager.account_managers']"));
	}
	
	private By getInviteAccountManagerLinkfromDropdown() {
		return By.xpath(getXpathByText("//span[contains(text(),'admin.user_management.acc_manager.invite_acc_manager')]"));
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath(getXpathByText("//th[text()='admin.user_management.coaches.col.email']/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	private By getNameColumn(String accountManager) {
		return By.xpath(getXpathByText("//th[text()='admin.user_management.coaches.col.name']/../../../../../following-sibling::div//a[text()='"+accountManager+"']"));
	}
	
	private By getInvitedAccountManagersButton() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.coaches.invited_coaches']"));
	}
	
	private By getArchivedAccountManagersButton() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.acc_manager.archived_account_managers']"));
	}
	
	
	
	public ArchivedAccountManagersPage clickArchivedAccountManager() {
		LOGGER.info("Clicking archived account managers link");
		click(getArchivedAccountManagersButton());
		return new ArchivedAccountManagersPage();
	}
	
	protected By getUserTableNameElements() {
		return By.xpath(".//td[contains(@class,'ng-star-inserted') and @id='name']");
	}
	
	private By getNameColumnHeader() {
		return By.xpath("//th[contains(@class,'click-it ui-sortable-column ng-star-inserted ui-state-highlight')]//child::p-sorticon");
	}
	
	public InviteAccountManagerPage clickInviteAccountManagerLink() {
		LOGGER.info("Clicking Invite Account Manager link");
		click(getInviteAccountManagerLinkfromDropdown());
		return new InviteAccountManagerPage();
	}
	
	public AccountManagersPage clickActionsDropdown() {
		LOGGER.info("Clicking Actions Dropdown");
		click(getActionDropdown());
		return this;
	}
	
	public AccountManagersPage clickNameColumnHeader() {
		LOGGER.info("Clicking name column header");
		click(getNameColumnHeader());
		return this;
	}
	
	public List<String> getNameColumnSorted() {
		return getSortedList(getUserTableNameElements());
		
	}
	
	private By getAccountManagerNameLink(String email) {
		return By.xpath(".//span[normalize-space(text())='"+email+"']/../preceding-sibling::td[1]//a");
	}
	
	private By getAccountManagerNameLink() {
		return By.xpath(".//td[@id='name']//span[contains(@class,'ng-star-inserted')]//a");
	}
	
	public AccountManagersPage validateAccountManagerPresent(String email) {
		LOGGER.info("Validate Account Manager is persent");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)));
		return this;
		
	}
	
	public RegisterAccountMangerPersonalDetailsPage clickOnSearchedAccountManagerName(String email) {
		LOGGER.info(" Clicking on Search Account Manager name");
		click(getAccountManagerNameLink(email));
		return new RegisterAccountMangerPersonalDetailsPage();
	}
	
	public RegisterAccountMangerPersonalDetailsPage clickOnAccountManagerName() {
		LOGGER.info(" Clicking on Account Manager name");
		click(getAccountManagerNameLink());
		return new RegisterAccountMangerPersonalDetailsPage();
	}
	
	public AccountManagersPage clickAccountManagers() {
		LOGGER.info("Clicking Account Managers");
		click(getAccountManagersButton());
		return this;
	}
	
	public InvitedAccountManagersPage clickInvitedAccountManager() {
		LOGGER.info("Clicking Invited account managers link");
		click(getInvitedAccountManagersButton());
		return new InvitedAccountManagersPage();
	}
	
	public AccountManagersPage validateResetFilters() {
		LOGGER.info("Validate Reset filter works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		String enterpriseFilterText = getText(getFilterByRoleDropdownPlaceHolder());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		softAssert.assertTrue(enterpriseFilterText.equalsIgnoreCase("FILTER BY ENTERPRISE"), "Failed to reset the Filter by Enterprise dropdown");
		softAssert.assertAll();
		return this;
	}

	private By getArchiveAccountManagerLink() {
		return By.xpath(getXpathByText("//span[contains(text(),'admin.user_management.acc_manager.archive_acc_manager')]"));
	}
	
	public AccountManagersPage clickArchiveAccountManagerLink() {
		LOGGER.info("Clicking Archived account managers link");
		click(getArchiveAccountManagerLink());
		return this;
    }
	
	private By getArchiveAccountManagerPage() {
		return By.xpath(getXpathByText("//p[contains(text(),'shared.modals.admin_account_manager_archive_modal.title')]"));
	}
	
	public AccountManagersPage validateArchiveAccountManagerPageIsVisible() {
		LOGGER.info("Validate Archive Account Manager page is visible.");
		Assert.assertTrue(isElementVisible(getArchiveAccountManagerPage()),"Archive Account Manager Page is not visible.");
		return this;
	}
	
	private By getAccountManagersCheckboxFromEmail(String mailId) {
		return By.xpath("//span[contains(text(),'"+mailId+"')]//ancestor::tr//descendant::p-checkbox");
	}
	
	private By getSearchAccountManagerInput() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getAccountmanagerCheckbox(String accountmanagerName)
	{
		LOGGER.info("Get client name checkbox");
		return By.xpath(".//span[normalize-space(text())='"+accountmanagerName+"']/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	public AccountManagersPage clickAccountManagerCheckox(String accountmanager) {
		click(getAccountmanagerCheckbox(accountmanager));
		return this;
	}

	
	public AccountManagersPage checkAccountManagerCheckbox(String mailId) {
		LOGGER.info("Clicking account managers checkbox.");
		click(getAccountManagersCheckboxFromEmail(mailId));
		return this;
	}
	
	public AccountManagersPage setSearchAccountManager(String name) {
		LOGGER.info("Sending account managers name to search.");
		setValue(name, getSearchAccountManagerInput());
		checkAccountManagerCheckbox(name);
		return this;
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
	
	public AccountManagersPage validateClearFilter() {
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
	
	private By getInviteAccountManagerElement() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.acc_manager.invite_acc_manager')]"));
	}
	
	private By getArchiveAccountManagerElement() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.acc_manager.archive_acc_manager']"));
	}
	
	public AccountManagersPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getInviteAccountManagerElement()),"invite option not present");
		softAssert.assertTrue(isElementPresent(getArchiveAccountManagerElement()),"archive option not present");
		softAssert.assertAll();
		return this;
	}
	
}
