package com.thrive.ui.page.user_management.search_filter;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.invite.InvitedClientsPage;

public class ArchivedClientsPage extends UserManagementCommonPage{
	
	private By getSearchClientInput() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getFilterByEnterpriseDropdown() {
		return By.xpath(".//div[text()='FILTER BY ENTERPRISE']/../../../..//ng-select[@name='filter']");
	}
	
	private By getSelectOptionFromFilterByEnterpriseDropdown(String enterprise) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@title='"+enterprise+"']");	
	}
	
	private By getActionsDropdown() {
		return By.xpath(".//button[@id='dropdownBasic1']");
	}
	
	private By getSelectElementFromActionsDropdown(String option) {
		return By.xpath(".//div[@class='dropdown-menu show']//span[text()='"+option+"']");		
	}
	
	private By getClientsCheckbox(String clientname) {
		return By.xpath(".//span[normalize-space(text())='"+clientname+"']/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getNoButton() {
		return By.xpath(".//p[text()='ARE YOU SURE YOU WANT TO DELETE THIS CLIENT?']/../../..//button[text()='No']");
	}
	
	private By getYesButton() {
		return By.xpath(".//p[text()='ARE YOU SURE YOU WANT TO DELETE THIS CLIENT?']/../../..//button[text()='Yes']");
	}
	
	private By getClientDeletedToaster() {
		return By.xpath(".//div[normalize-space(text())='Client has been successfully deleted']");
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath("//p-table[contains(@class,'clients')]//th[contains(text(),'Email')]/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']");
	}
	
	private By getClientsButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.clients_tab']"));
	}
	
	
	public ClientsPage clickClientsButton() {
		LOGGER.info("Click clients button");
		click(getClientsButton());
		return new ClientsPage();	
	}
 
	public ArchivedClientsPage clickNo() {
		LOGGER.info("Clicking No button");
		click(getNoButton());
		return this;
	}
	
	public ArchivedClientsPage clickYes() {
		LOGGER.info("Clicking Yes button");
		click(getYesButton());
		return this;
	}
	
	public ArchivedClientsPage setSearchClient(String clientName) {
		LOGGER.info("Sending Client name to search");
		setValue(clientName, getSearchClientInput());
		return this;
	}
	
	public ArchivedClientsPage selectEnterpriseFromDropdown(String enterprise) {
		LOGGER.info("Sellecting enterprise from dropdown.");
		click(getFilterByEnterpriseDropdown());
		click(getSelectOptionFromFilterByEnterpriseDropdown(enterprise));
		return this;
	}
	
	public ArchivedClientsPage checkClientCheckbox(String clientName) {
		LOGGER.info("Clicking Clients checkbox.");
		click(getClientsCheckbox(clientName));
		return this;
	}
	
	public ArchivedClientsPage clickDeleteClientPage(String clientName,String option) {
		checkClientCheckbox(clientName);
		click(getActionsDropdown());
		click(getSelectElementFromActionsDropdown(option));
		return this;
	}
	
	public ArchivedClientsPage validateArchivedClientDeletedMessage(String expected)
	{
		LOGGER.info("Validating Delete client toaster message.");
		String actual = getText(getClientDeletedToaster());
		Assert.assertEquals(expected, actual);
		return this;
	}
	
	public ArchivedClientsPage validateArchivedClientDeletedSuccessfully(String client) {
		LOGGER.info("Validating client deleted successfully");
		Assert.assertTrue(isElementNotVisible(getClientsCheckbox(client)));
		return this;
	}

	private By getDeleteClientLink() {
		return By.xpath("//span[contains(text(),'DELETE CLIENT')]");
	
	}
	public AlertsAndMessagesPage clickDeleteClientLink() {
		LOGGER.info("Clicking on Delete Client link");
		click(getActionsDropdown());
		click(getDeleteClientLink());
		return new AlertsAndMessagesPage();
	}
	
	public ArchivedClientsPage validateClientPresent(String email) {
		LOGGER.info("Validating Client email is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)));
		return this;	
	}
	
	private By getEnterpriseName(String enterprise) {
		return By.xpath("//span[contains(text(),'"+enterprise+"')]");
	}
	
	public ArchivedClientsPage validateEnterpriseNamePresent(String name) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEnterpriseName(name)),"Failed to search enterprise bu name");
		return this;
	}
	
	public ArchivedClientsPage validateClientNotPresent(String email) {
		LOGGER.info("Validating Account Manager is not present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)));
		return this;
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	private By getSearchValue() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getFilterByEnterpriseDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.users.enterprises']"));
	}
	
	public ArchivedClientsPage validateClearFilter() {
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
	
	private By getCreditsUsedforSessionCancelledLateColumn() {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.number_of_credits_used_for_sessions_cancelled_late']"));
	}
	
	private By getCreditsUsedforSessionNotAttendedColumn() {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.number_of_credits_used_for_sessions_not_attended']"));
	}
	
	public ArchivedClientsPage validateScrollColumnsPresent() {
		LOGGER.info("Validate Columns present after right scroll");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getCreditsUsedforSessionCancelledLateColumn()), "session late cancelled column not present");
		softAssert.assertTrue(isElementPresent(getCreditsUsedforSessionNotAttendedColumn()), "session not attended column not present");
		softAssert.assertAll();
		return this;
	}
	
	private By getRightDirectionMovementButtonLink() {
		return By.xpath("//span[@class='glyphicon glyphicon-chevron-right']");
	}
	
	public ArchivedClientsPage clickRightScrollButton() {
		LOGGER.info("click right scroller button");
		while(isElementNotVisible(getCreditsUsedforSessionCancelledLateColumn())) {
			click(getRightDirectionMovementButtonLink());
		}
		return this;
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getActionsDeleteClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.delete_client']"));
	}
	
	private By getActionsUnarchiveClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.unarchive_client']"));
	}
	
	
	public ArchivedClientsPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsUnarchiveClientOption()),"Un-Archive client option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteClientOption()),"invite client option not present");
		softAssert.assertAll();
		return this;
	}
		

}
