package com.thrive.ui.page.invite;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;

public class InvitedEnterprisesPage extends UserManagementCommonPage{

	
	private By getNameColumn(String invitedent) {
		return By.xpath(getXpathByText(".//th[(text())='admin.enterprises.view.col.name']/../../../../../following-sibling::div//span[normalize-space(text())='"+invitedent+"']"));
	}
	
	private By getEnterpriseCheckbox(String entEmail) {
		return By.xpath("//span[normalize-space(text())='"+entEmail.toLowerCase()+"']/../..//p-checkbox//div[contains(@class,'ui-chkbox-box')]");
		//return By.xpath(".//span[normalize-space(text())='"+entEmail+"']/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.account.manager.enterprise_email')]/../../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	private By getDataCountDropdownLink() {
		return By
				.xpath("//div[contains(@class,'ui-dropdown-label-container')]//following-sibling::div[@role='button']");
	}

	private By getDataCountLink(int count) {
		return By.xpath("//p-dropdownitem//li//span[(text()='" + count + "')]");
	}
	
	private By getActionsReInviteEnterpirseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.reinvite_enterprise']"));
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getActionsDeleteEnterpriseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.delete_enterprise']"));
	}
	
	public InvitedEnterprisesPage selectPaginationValue(int val) {
		int counter = 0;
		boolean flag = true;
		do {
			try {
				click(getDataCountDropdownLink());
				shortWait();
				click(getDataCountLink(val));
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=15);
		return this;
	}
	
	

	public InvitedEnterprisesPage validateInvitedEnterprise(String inviteent) {
		LOGGER.info("Validating invited enterprise name is present");
		Assert.assertTrue(isElementPresent(getNameColumn(inviteent)));
		return this;
	}
	
	
	
	public InvitedEnterprisesPage validateEnterprisePresent(String email) {
		LOGGER.info("Validating enterprise is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)), "Failed to seach invited enterprise :" + email);
		return this;
	}
	
	public InvitedEnterprisesPage validateEnterpriseNotPresent(String email) {
		LOGGER.info("Validating enterprise is not present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)), "Enterprise is present :" + email);
		return this;
	}
	
	private By getEmailAddressColumnValue(String email) {
		return By.xpath(getXpathByText(".//span[(text())='admin.user_management.enterprises.col.email_address']/../../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	
	public InvitedEnterprisesPage validateInvitedEnterprisePresent(String email) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumnValue(email)), "Failed to seach invited enterprise :" + email);
		return this;
	}
	
	
	public InvitedEnterprisesPage clickEnterpriseCheckbox(String entEmail) {
		LOGGER.info("Clicking enterprise checkbox");
		click(getEnterpriseCheckbox(entEmail));
		return this;
	}
	
	public InvitedEnterprisesPage validateResetFilters() {
		LOGGER.info("Validating reset filter works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		Assert.assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		return this;
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	private By getNoOfEntry() {
		return By.xpath(".//div[contains(@class,'ui-paginator-bottom')]/span[1]");
	}
	
	
	
	private By getSearchValue() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	public InvitedEnterprisesPage validateClearFilter() {
		LOGGER.info("Validate clicking Clear Filter resets all data");
		shortWait();
		int displayedRowsCount = findElements(getRowsLink()).size();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(displayedRowsCount > 1, "Clear Filter does not working properly");
		softAssert.assertTrue(isElementPresent(getSearchValue()));
		softAssert.assertAll();
		return this;
	}
	
	public InvitedEnterprisesPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsReInviteEnterpirseOption()),"re-invite enterprise option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteEnterpriseOption()),"invite enterprise option not present");
		return this;
	}
		
	
}
