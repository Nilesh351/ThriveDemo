package com.thrive.ui.page.invite;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

public class InvitedUsersPage extends UserManagementCommonPage {
	
	
	private By getEmailAddressElement(String username) {
		return By.xpath(".//td[@class='ng-star-inserted']//span[contains(text(),'"+username+"')]");
	}
	public InvitedUsersPage validateUserEmailPresent(String username) {
		Assert.assertTrue(isElementPresent(getEmailAddressElement(username)), "Failed to find the username element with name :" + username);
		return this;
	}
	
	private By getUserTableRowElement() {
		return By.xpath(".//table[@class='ui-table-scrollable-body-table']//tr");
	}
	
	private By getRoleElement(String role) {
		return By.xpath(".//td[@id='roleName']//span[contains(text(),'"+role+"')]");
	}
	
	private By getNameElement(String name) {
		return By.xpath("//span[contains(text(),'"+name+"')]");
	}
	
	public InvitedUsersPage validateUserNamePresent(String name) {
		LOGGER.info("Validating User name present");
		Assert.assertTrue(isElementPresent(getNameElement(name)),
				"Failed to find the user's name element with name :" + name);
		return this;
	}
	
	private By getFilterByRoleDropdownLink() {
		return By.xpath(getXpathByText("//div[contains(text(),'admin.user_management.users.filter_by_role')]//ancestor::div[@class='ng-select-container']"));
	}
	
	private By getUserRoleLink(String userRole) {
		return By.xpath("//span[text()='" + userRole + "']/..");
	}
	
	public InvitedUsersPage selectUserRole(String role) {
		LOGGER.info("Selecting user role");
		String userRole= role;
		if(Config.getLocalizationLanguage().contains("fr")) {
			userRole=DBUtils.getResultFromPostgresDB(DBQueries.getUserRoleInFrench(role));
		}
		click(getFilterByRoleDropdownLink());
		click(getUserRoleLink(userRole));
		return this;
	}
	
	public By getDownloadButton() {
		return By.xpath(getXpathByText("//button[contains(text(),'admin.user_management.users.export')]"));
	}
	
	public InvitedUsersPage clickDownload() {
		LOGGER.info("Clicking download");
		click(getDownloadButton());
		return this;
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	public InvitedUsersPage validateClearFilter() {
		LOGGER.info("Validate clicking Clear Filter resets all data");
		shortWait();
		int displayedRowsCount = findElements(getRowsLink()).size();
		Assert.assertTrue(displayedRowsCount > 1, "Clear Filter does not working properly");
		return this;
	}
	
	public InvitedUsersPage validateUserRolePresent(String role) {
		LOGGER.info("Validating user role is present");
		Assert.assertTrue(isElementPresent(getRoleElement(role)), "Failed to find the user's name element with name :" + role);
		return this;
	}
	
	private By getLastPageButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-step-forward']//parent::a");
	}
	
	public InvitedUsersPage clickLastPageButton() {
		shortWait();
		click(getLastPageButtonLink());
		return this;
	}
	
	private By getLastPageLink() {
		return By.xpath(
				"//span[@class='ui-paginator-icon pi pi-caret-right']//parent::a//preceding-sibling::span[1]//child::a[contains(@class,'active')]");
	}
	
	public int getTotalPageCount() {
		LOGGER.info("Capturing total number of pages displayed");
		return Integer.parseInt(getText(getLastPageLink()));
	}
	
	private By getFirstPageButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-step-backward']//parent::a");
	}
	
	private By getUserRoleLink(int i) {
		return By.xpath("//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr[" + i
				+ "]//td[@id='roleName']");
	}
	
	private By getNextPageButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-caret-right']");
	}
	
	private By getNextPageEnabled() {
		return By.xpath(".//a[contains(@class,'ui-paginator-next ui-paginator-element ui-state-default ui-corner-all')]");
	}
	
	public Boolean verifyDisplayedUserRoleData(String userRole) {
		LOGGER.info("Verifying displayed user roles");
		Boolean flag = true;
		int totalPageCount = 1;
		if(isElementPresent(getNextPageEnabled()))
		{
			click(getLastPageButtonLink());
			totalPageCount = getTotalPageCount();
			click(getFirstPageButtonLink());
		}
		shortWait();
		exitLoop: for (int i = 1; i <= totalPageCount; i++) {
			int displayedRowsCount = findElements(getRowsLink()).size();
			if (isElementVisible(getRowsLink())) {
				for (int j = 1; j <= displayedRowsCount; j++) {
					if (!(getText(getUserRoleLink(j)).equalsIgnoreCase(userRole))) {
						flag = false;
						System.out.println("Different role found : " + getText(getUserRoleLink(i)));
						break exitLoop;
					}
				}
				click(getNextPageButtonLink());
				shortWait();
			}
		}
		return flag;
	}
	
	private By getNoOfEntry() {
		return By.xpath(".//div[contains(@class,'ui-paginator-bottom')]/span[1]");
	}
		
	private By getDataCountDropdownLink() {
		return By
				.xpath("//div[contains(@class,'ui-dropdown-label-container')]//following-sibling::div[@role='button']");
	}
	
	private By getDataCountLink(int count) {
		return By.xpath("//p-dropdownitem//li//span[(text()='" + count + "')]/..");
	}
	
	public InvitedUsersPage validateLastPageRecords() {
		shortWait();
		String entries = getText(getNoOfEntry());
		String total = entries.split(" ")[5];
		String lastpage = entries.split(" ")[3];
		Assert.assertEquals(total, lastpage,"records are not matching for last page");
		return this;
		
	}
	
	public InvitedUsersPage clickFirstPageButton() {
		click(getFirstPageButtonLink());
		return this;
	}
	
	public InvitedUsersPage validateSearchBoxInput(String userMailId) {
		LOGGER.info("Validate search box can accept any input");
		validateUserEmailPresent(userMailId);
		return this;
	}
	
	public InvitedUsersPage clickNextButton() {
		click(getNextPageButtonLink());
		return this;
	}
	
	private By getDisabledNextButton() {
		return By.xpath(".//a[@class='ui-paginator-next ui-paginator-element ui-state-default ui-corner-all ui-state-disabled']");
	}
	
	private By getSelectedPage() {
		return By.xpath(".//a[contains(@class,'ui-state-active')]");
	}
	
	public InvitedUsersPage verifyPaginationData() {
		int pagination = 100;
		selectPaginationValue(pagination);
		click(getLastPageButtonLink());
		int totalPageCount = getTotalPageCount();
		click(getFirstPageButtonLink());
		for (int i = 1; i <= totalPageCount; i++) {	
			if(isElementNotVisible(getDisabledNextButton())) {
				int records = findElements(getRowsLink()).size();
				String val = getText(getSelectedPage());
				int curpage = Integer.parseInt(val);
				int actual = records * curpage;
				String entries = getText(getNoOfEntry());
				String expval = entries.split(" ")[3];
				int expected = Integer.parseInt(expval);
				Assert.assertEquals(actual, expected,"records are not matching");
				clickNextButton();
				
			}else 
			{
				String val = getText(getNoOfEntry());
				String total = val.split(" ")[5];
				String lastpage = val.split(" ")[3];
				Assert.assertEquals(total, lastpage,"records are not matching for last page");
			}
				
			}
		return this;
	}
	
	public InvitedUsersPage validateFirstPageRecords() {
		String entries = getText(getNoOfEntry());
		String firstpage = entries.split(" ")[3];
		int firstrecords = Integer.parseInt(firstpage);
		int records = findElements(getRowsLink()).size();
		Assert.assertEquals(firstrecords, records,"records are not matching for first page");
		return this;
	}

	
	
	public InvitedUsersPage selectPaginationValue(int val) {
		int counter = 0;
		boolean flag = true;
		do {
			try {
				mediumWait();
				click(getDataCountDropdownLink());
				scrollByJavaScript("0", "500");
				click(getDataCountLink(val));
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=15);
		
		return this;
	}
	
	public InvitedUsersPage validateUserRoleDataDisplayed(String userRole) {
		LOGGER.info("Validate only searched user role data is displayed");
		String role=userRole;
		if(Config.getLocalizationLanguage().contains("fr")) {
			role=DBUtils.getResultFromPostgresDB(DBQueries.getUserRoleInFrench(userRole));
		}
		Assert.assertTrue(verifyDisplayedUserRoleData(role), "Failed to display only selected user role data");
		return this;
	}
	
	public InvitedUsersPage validateFilterByRole(String filterOption) {
		LOGGER.info("Validating Folter by role works as expected");
		int expectedFilteredRows = findElements(getUserTableRowElement()).size();
		int actualFilteredRows = findElements(getRoleElement(filterOption)).size();
		Assert.assertEquals(actualFilteredRows, expectedFilteredRows, "Filter by role is not as expected ");
		return this;
	}
}
