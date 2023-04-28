package com.thrive.ui.page.invite;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;

public class InvitedClientsPage extends UserManagementCommonPage {
	
	private By getSearchClientInput() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getFilterByEnterpriseDropdown() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.users.enterprises']/../../../..//ng-select[@name='filter']"));
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
		return By.xpath(".//span[normalize-space(text())='"+clientname+"']/../..//div[@class='ui-chkbox-box ui-widget ui-corner-all ui-state-default']");
	}
	
	private By getNoButton() {
		return By.xpath(getXpathByText(".//p[text()='shared.modals.admin_delete_client_modal.title']/../../..//button[text()='questions.registeration_client.iemfxiymwmq.no']"));
	}
	
	private By getYesButton() {
		return By.xpath(getXpathByText(".//p[text()='shared.modals.admin_delete_client_modal.title']/../../..//button[text()='questions.registeration_client.iemfxiymwmq.yes']"));
	}
	
	private By getInvitedClientDeletedToaster() {
		return By.xpath(getXpathByText(".//div[(text())='ui.messages.invited_client_has_been_deleted']"));
	}
	
//	private By getEnterprise(String enterprise) {
//		return By.xpath(getXpathByText(".//th[(text())='admin.clients.client.enterprise']/../../../../../following-sibling::div//span[text(),'"+enterprise+"']"));
//	}
//	
	private By getEnterprise(String enterprise) {
		return By.xpath("//span[contains(text(),'"+enterprise+"')]");
	}
	
	public InvitedClientsPage clickNo() {
		LOGGER.info("Clicking NO button");
		click(getNoButton());
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
	
	public InvitedClientsPage validateClearFilter() {
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
	
	
	private By getInviteclientEmail(String email) {
		return By.xpath(getXpathByText("//th[contains(text(),'private.client.email']/../../../../../../../../../../p-table[contains(@class,'clients-tbl')]//span[normalize-space(text())='"+email+"']"));
	}
	
	public InvitedClientsPage validateInviteClientByEmail(String email) {
		LOGGER.info("Validating invite client using mail");
		click(getInviteclientEmail(email));
		return this;
	}
	
	private By getInviteclientEmailRm(String email) {
		return By.xpath(getXpathByText("//th[(text())='admin.user_management.clients.col.emailid']/../../../../../../../../../../p-table[contains(@class,'clients-tbl')]//span[normalize-space(text())='"+email+"']"));
	}
	
	public InvitedClientsPage validateInviteClientEmail(String email) {
		LOGGER.info("Validating invite client using mail");
		click(getInviteclientEmailRm(email));
		return this;
	}
	
	public InvitedClientsPage clickYes() {
		LOGGER.info("Clicking YES button");
		click(getYesButton());
		return this;
	}
	
	public InvitedClientsPage setSearchClient(String clientName) {
		LOGGER.info("Setting client name to search");
		setValue(clientName, getSearchClientInput());
		return this;
	}
	
	public InvitedClientsPage selectEnterpriseFromDropdown(String enterprise) {
		LOGGER.info("Selecting expertise");
		click(getFilterByEnterpriseDropdown());
		click(getSelectOptionFromFilterByEnterpriseDropdown(enterprise));
		return this;
	}

	public InvitedClientsPage validateResetFilters() {
		LOGGER.info("Validating reset filrter works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		Assert.assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		return this;
	}
	
	private By getInviteClientCheckbox(String clientName) {
		return By.xpath(".//span[normalize-space(text())='"+clientName+"']/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	public InvitedClientsPage clickInviteClientCheckbox(String clientName) {
		LOGGER.info("Clicking clients checkbox");
		click(getInviteClientCheckbox(clientName));
		return this;
	}
	
	public InvitedClientsPage validateEnterprisePresent(String enterprise) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEnterprise(enterprise)),"Failed to search the enterprise:"+enterprise);
		return this;
	}
	
	public InvitedClientsPage validateEnterpriseNamePresent(String name) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEnterprise(name)),"Failed to search enterprise");
		return this;
	}

	
	
	private By getEmailAddressColumn(String email) {
		return By.xpath(getXpathByText(".//th[(text())='admin.user_management.clients.col.emailid']/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	public InvitedClientsPage validateInvitedClientPresent(String email) {
		LOGGER.info("Validating invited client name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)), "Fai to search invited client :" + email);
		return this;
	}
	
	public InvitedClientsPage validateInvitedClientNotPresent(String email) {
		LOGGER.info("Validating invited client is not present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)), "invited client is present :" + email);
		return this;
	}
	
	
	public InvitedClientsPage checkClientCheckbox(String clientName) {
		click(getClientsCheckbox(clientName));
		return this;
	}
	
	public InvitedClientsPage clickDeleteClientPage(String clientName,String option) {
		LOGGER.info("Clicking delete client page");
		checkClientCheckbox(clientName);
		click(getActionsDropdown());
		click(getSelectElementFromActionsDropdown(option));
		return this;
	}
		
	public InvitedClientsPage validateInvitedClientDeletedSuccessfully(String clientname) {
		LOGGER.info("Validating invited client deleted successfully");
		Assert.assertTrue(isElementNotVisible(getClientsCheckbox(clientname)));
		return this;
	}
	
	public InvitedClientsPage validateInvitedClientDeletedMessage(String expected) {
		LOGGER.info("Validating invited client delete message");
		String actual = getText(getInvitedClientDeletedToaster());
		Assert.assertEquals(actual, expected);
		return this;
	}
	
	private By getActionsReInviteClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.reinvite_client']"));
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getActionsDeleteClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.delete_client']"));
	}
	
	public InvitedClientsPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsReInviteClientOption()),"re-invite client option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteClientOption()),"delete client option not present");
		softAssert.assertAll();
		return this;
	}
	
	public InvitedClientsPage validateBulkinviteData(String file) {

		try {

			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			String[] nextRecord;

			while ((nextRecord = csvReader.readNext()) != null) {
				for (String cell : nextRecord) {
					shortWait();
					String value = cell;
					String email = value.split(" ")[0];
					setSearch(email);
					validateInvitedClientPresent(email);
					break;
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}
	
	
}
