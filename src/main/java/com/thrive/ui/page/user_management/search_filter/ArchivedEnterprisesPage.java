package com.thrive.ui.page.user_management.search_filter;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.invite.InvitedEnterprisesPage;


public class ArchivedEnterprisesPage extends UserManagementCommonPage{
	
	
	private By getEnterprise(String enterprise) {
		return By.xpath("//a[contains(text(),'"+enterprise+"')]");
	}
	
	private By getEnterpriseCheckbox(String enterprise) {
		return By.xpath(".//a[contains(text(),'"+enterprise+"')]/../../preceding-sibling::td[1]//div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getEnterprisesButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.enterprises_tab']"));
	}
	
	public EnterprisesPage clickEnterprises() {
		LOGGER.info("Click enterprises");
		click(getEnterprisesButton());		
		return new EnterprisesPage();
	}
	
	public ArchivedEnterprisesPage clickEnterpriseCheckbox(String enterprise) {
		LOGGER.info("Clicking enterprise checkbox");
		click(getEnterpriseCheckbox(enterprise));
		return this;
	}
	
	public ArchivedEnterprisesPage validateEnterprisePresent(String enterprise) {
		LOGGER.info("Validating Enterprise name is present in list .");
		Assert.assertTrue(isElementPresent(getEnterprise(enterprise)), "Failed to search the enterprise :" + enterprise);
		return this;
	}
	
	public ArchivedEnterprisesPage validateEnterpriseNotPresent(String enterprise) {
		LOGGER.info("Validating Enterprise name is not present in list .");
		Assert.assertTrue(isElementNotVisible(getEnterprise(enterprise)), "Failed to search the enterprise :" + enterprise);
		return this;
	}
	
	public ArchivedEnterprisesPage setEnterpriseSearch(String enterprise) {
		LOGGER.info("Set enterprise name to search");
		setValue(enterprise, getSearchInput());
		return this; 
	}

	private By getRemoveEnterpriseLink() {
		return By.xpath("//span[contains(text(),'REMOVE')]");
	}
	
	public AlertsAndMessagesPage clickRemoveEnterprise() {
		LOGGER.info("Clicking Remove Enterprise link");
		click(getRemoveEnterpriseLink());
		return new AlertsAndMessagesPage();
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
	
	private By getDataCountDropdownLink() {
		return By
				.xpath("//div[contains(@class,'ui-dropdown-label-container')]//following-sibling::div[@role='button']");
	}

	private By getDataCountLink(int count) {
		return By.xpath("//p-dropdownitem//li//span[(text()='" + count + "')]");
	}
	
	public ArchivedEnterprisesPage selectPaginationValue(int val) {
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
	
	public ArchivedEnterprisesPage scrollInVerticalDirection(String offsetValue) {
		LOGGER.info("Moving scroll bar in vertical direction");
		Long verticalLocationBeforeScroll;
		shortWait();
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		verticalLocationBeforeScroll = (Long) executor.executeScript("return window.pageYOffset;");
		mediumWait();
		scrollByJavaScript("0", offsetValue);
		return this;
	}
	
	private By getNoOfEntry() {
		return By.xpath(".//div[contains(@class,'ui-paginator-bottom')]/span[1]");
	}
	
	public ArchivedEnterprisesPage validatePaginationRecords(int pagination) {
		LOGGER.info("Validate number of data set displayed");
		int records = findElements(getRowsLink()).size();
		String val = getText(getNoOfEntry());
		String total = val.split(" ")[3];
		if(Integer.valueOf(total)<pagination) {
		    Assert.assertTrue(true);
		} else {
		LOGGER.info("Insufficient number of records");
	        Assert.assertEquals(pagination, records,"records are not matching");
		}
		return this;
	}
	
	public ArchivedEnterprisesPage validateClearFilter() {
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
	
	private By getActionsDeleteEnterpriseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.delete_enterprise']"));
	}
	
	private By getActionsUnarchiveEnterpriseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.unarchive_enterprise']"));
	}
	
	public ArchivedEnterprisesPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsUnarchiveEnterpriseOption()),"Un-Archive enterprise option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteEnterpriseOption()),"invite enterprise option not present");
		softAssert.assertAll();
		return this;
	}
	
}
