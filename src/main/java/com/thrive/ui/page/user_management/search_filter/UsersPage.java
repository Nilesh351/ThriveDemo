package com.thrive.ui.page.user_management.search_filter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.data_utils.DBQueries;
import com.thrive.api.pojos.InviteAMRequest;
import com.thrive.api.pojos.RegisterAMRequest;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.enterprise.EnterpriseEmployeePage;
import com.thrive.ui.page.invite.InvitedUsersPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UsersPage extends UserManagementCommonPage{

	
	Long verticalLocationBeforeScroll;
	Long verticalLocationAfterScroll;
	int horizontalLocationBeforeScroll;
	int horizontalLocationAfterScroll;
	Boolean flag = true;

	private By getEmailAddressElement(String username) {
		return By.xpath(".//tbody[@class='ui-table-tbody']//span[contains(text(),'" + username + "')]");
	}

	private By getNameElement(String name) {
		return By.xpath("//a[contains(text(),'"+name+"')]");
	}

	private By getRoleElement(String role) {
		return By.xpath(".//td[@id='roleName']//span[contains(text(),'" + role + "')]");
	}

	private By getUserTableRowElement() {
		return By.xpath(".//table[@class='ui-table-scrollable-body-table']//tr");
	}

	private By getInvitedUsers() {
		return By.xpath(getXpathByText(".//button[contains(text(),'shared.coaching_program.submenu.invited_users')]"));
	}

	private By getNameColumnHeader() {
		return By.xpath(
				".//th[contains(@class,'sticky-body ui-sortable-column ui-state-highlight ng-star-inserted')]//child::span[contains( text(),'Name')]");
	}

	private By getUsersDropdown() {
		return By.xpath(getXpathByText("//span//span[contains(text(),'shared.coaching_program.submenu.user')]"));
	}
	
	private By getEmployeesLink() {
		return By.xpath(getXpathByText("//a[contains(text(),'shared.coaching_program.submenu.employees')]"));
	}
	
	private By getRegisteredLink() {
		return By.xpath(getXpathByText(".//a[contains(text(),'shared.coaching_program.submenu.employees')]/../..//li[text()='shared.coaching_program.submenu.all_users']"));
	}
	
	private By getRegisteredInternalCOachesLink() {
		return By.xpath(getXpathByText(".//a[contains(text(),'shared.coaching_program.submenu.internal_coaches')]/../..//li[text()='shared.coaching_program.submenu.all_users']"));
	}
	
	private By getInternalCoachesLink() {
		return By.xpath(getXpathByText("//a[contains(text(),'shared.coaching_program.submenu.internal_coaches')]"));
	}
	
	public InternalCoachesPage clickInternalCoaches() {
		LOGGER.info("Clicking Internal Coaches");
		click(getInternalCoachesLink());
		shortWait();
		click(getRegisteredInternalCOachesLink());
		return new InternalCoachesPage();
	}
	
	public EnterpriseEmployeePage clickEmployees() {
		LOGGER.info("Clicking Employees");
		click(getEmployeesLink());
		shortWait();
		click(getRegisteredLink());
		return new EnterpriseEmployeePage();
	}
	
	public UsersPage clickUsersDropdown() {
		LOGGER.info("Clicling users dropdown");
		click(getUsersDropdown());
		return this;
	}
	
	public UsersPage clickRegisteredUsers() {
		LOGGER.info("Clicking Registered");
		click(getRegisteredLink());
		return new UsersPage();
	}
	
	public InvitedUsersPage clickInvitedUsers() {
		LOGGER.info("Clicking Invited Users link");
		click(getInvitedUsers());
		return new InvitedUsersPage();
	}

	public UsersPage validateUserEmailPresent(String username) {
		LOGGER.info("Validating User email present");
		Assert.assertTrue(isElementPresent(getEmailAddressElement(username)),
				"Failed to find the username element with name :" + username);
		return this;
	}

	public UsersPage validateUserNamePresent(String name) {
		LOGGER.info("Validating User name present");
		Assert.assertTrue(isElementPresent(getNameElement(name)),
				"Failed to find the user's name element with name :" + name);
		return this;
	}

	public UsersPage validateUserRolePresent(String role) {
		LOGGER.info("Validating User role present");
		Assert.assertTrue(isElementPresent(getRoleElement(role)),
				"Failed to find the user's name element with name :" + role);
		return this;
	}

	public UsersPage validateFilterByRole(String filterOption) {
		LOGGER.info("Validating filter by role works as expected");
		int expectedFilteredRows = findElements(getUserTableRowElement()).size();
		int actualFilteredRows = findElements(getRoleElement(filterOption)).size();
		Assert.assertEquals(actualFilteredRows, expectedFilteredRows, "Filter by role is not as expected ");
		return this;
	}

	public UserManagementCommonPage clickNameColumnHeader() {
		LOGGER.info("licking name column header");
		click(getNameColumnHeader());
		return this;
	}

	public List<String> getNameColumnSorted() {
		return getSortedList(getUserTableNameElements());

	}

	public UsersPage validateResetFilters() {
		LOGGER.info("Validating reset filter works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		String roleFilterText = getText(getFilterByRoleDropdownPlaceHolder());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		softAssert.assertTrue(roleFilterText.equalsIgnoreCase("Filter by Role"),
				"Failed to reset the Filter by Role dropdown");
		softAssert.assertAll();
		return this;
	}

	private By getUserCheckbox(String enterpriseName) {
		return By.xpath(".//a[text()='" + enterpriseName
				+ "']/../../preceding-sibling::td//div[contains(@class,'ui-chkbox-box ui-widget ui-corner-all ui-state-default')]");
	}

	public UsersPage clickUserCheckbox(String entName) {
		LOGGER.info("Clicking user checkbox");
		click(getUserCheckbox(entName));
		return this;
	}

	private By getSearchUserInput() {
		return By.xpath(".//input[@placeholder='Search']");
	}

	public UsersPage setSearchUser(String name) {
		LOGGER.info("Setting user name value to search");
		setValue(name, getSearchUserInput());
		clickUserCheckbox(name);
		return this;
	}

	public UsersPage validateSearchBoxInput(String userMailId) {
		LOGGER.info("Validate search box can accept any input");
		validateUserEmailPresent(userMailId);
		return this;
	}
	
	

	private By getUserRolesLink() {
		return By.xpath("//ng-select[@name='filter']//descendant::div[@role='option']");
	}

	private By getFilterByRoleDropdownLink() {
		return By.xpath(getXpathByText("//div[contains(text(),'admin.user_management.users.filter_by_role')]//ancestor::div[@class='ng-select-container']"));
	}

	public UsersPage clickFilterByRole() {
		LOGGER.info("Clicking Filter By Roles");
		click(getFilterByRoleDropdownLink());
		return this;
	}

	private By getUserRoleLink(String userRole) {
		return By.xpath("//span[text()='" + userRole + "']/..");
	}
	
	private By getCurrentPaginationvalue() {
		return By.xpath("//div[@class='ui-dropdown-label-container ng-tns-c81-18']/span");
	}
	
	private By getNoOfEntry() {
		return By.xpath(".//div[contains(@class,'ui-paginator-bottom')]/span[1]");
	}
	
	public UsersPage validateLastPageRecords() {
		String entries = getText(getNoOfEntry());
		String total = entries.split(" ")[5];
		String lastpage = entries.split(" ")[3];
		Assert.assertEquals(total, lastpage,"records are not matching for last page");
		return this;
		
	}
	
	public UsersPage validateFirstPageRecords() {
		String entries = getText(getNoOfEntry());
		String firstpage = entries.split(" ")[3];
		int firstrecords = Integer.parseInt(firstpage);
		int records = findElements(getRowsLink()).size();
		Assert.assertEquals(firstrecords, records,"records are not matching for first page");
		return this;
	}

	public UsersPage selectUserRole(String role) {
		LOGGER.info("Selecting user role");
		String userRole=role;
		if(Config.getLocalizationLanguage().contains("fr")) {
			userRole=DBUtils.getResultFromPostgresDB(DBQueries.getUserRoleInFrench(role));
		}
		click(getFilterByRoleDropdownLink());
		click(getUserRoleLink(userRole));
		return this;
	}

	public Boolean verifyUsersRoles() {
		LOGGER.info("Verifying dropdown contains all user roles");
		List<String> userRoles = getUsersRolesList();
		List<String> dropdownUserRoles = new ArrayList<>();
		List<WebElement> options = findElements(getUserRolesLink());
		for (WebElement option : options) {
			dropdownUserRoles.add(getText(option));
		}
		Boolean result = userRoles.containsAll(dropdownUserRoles);
		return result;
	}

	public UsersPage validateDropdownRoles() {
		LOGGER.info("Validate Filter By Role dropdown contains all user roles");
		Assert.assertTrue(verifyUsersRoles(), "Dropdown does not contains all user roles");
		return this;
	}

	private By getNextPageButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-caret-right']");
	}
	
	public UsersPage clickNextButton() {
		click(getNextPageButtonLink());
		return this;
	}
	
	private By getPreviousButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-caret-left']");
	}
	
	public UsersPage clickPreviousButton() {
		click(getPreviousButtonLink());
		return this;
	}

	private By getLastPageButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-step-forward']//parent::a");
	}
	
	public UsersPage clickLastPageButton() {
		click(getLastPageButtonLink());
		return this;
	}
	
	private By getFirstPageButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-step-backward']//parent::a");
	}
	
	public UsersPage clickFirstPageButton() {
		click(getFirstPageButtonLink());
		return this;
	}

	private By getUserRoleLink(int i) {
		return By.xpath("//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr[" + i
				+ "]//td[@id='roleName']");
	}

	private By getLastPageLink() {
		return By.xpath(
				"//span[@class='ui-paginator-icon pi pi-caret-right']//parent::a//preceding-sibling::span[1]//child::a[contains(@class,'active')]");
	}

	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}

	public By getDownloadButton() {
		return By.xpath(getXpathByText("//button[contains(text(),'admin.user_management.users.export')]"));
	}

	
	public int getTotalPageCount() {
		LOGGER.info("Capturing total number of pages displayed");
		return Integer.parseInt(getText(getLastPageLink()));
	}

	private By getRoleColumnHeaderLink() {
		return By.xpath("//thead[@class='ui-table-thead']//th[3]");
	}
	
	private By getEmailAddressColumnHeaderLink() {
		return By.xpath("//thead[@class='ui-table-thead']//th[4]");
	}

	private By getRightDirectionMovementButtonLink() {
		return By.xpath("//span[@class='glyphicon glyphicon-chevron-right']");
	}

	private By getDataCountDropdownLink() {
		return By
				.xpath("//div[contains(@class,'ui-dropdown-label-container')]//following-sibling::div[@role='button']");
	}

	private By getDataCountLink(int count) {
		return By.xpath("//p-dropdownitem//li//span[(text()='" + count + "')]");
	}
	
	
	
	public UsersPage selectPaginationValue(int val) {
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

	public Boolean verifyDisplayedUserRoleData(String userRole) {
		LOGGER.info("Verifying displayed user roles");
		Boolean flag = true;
		click(getLastPageButtonLink());
		int totalPageCount = getTotalPageCount();
		click(getFirstPageButtonLink());
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
	
	private By getDisabledNextButton() {
		return By.xpath(".//a[@class='ui-paginator-next ui-paginator-element ui-state-default ui-corner-all ui-state-disabled']");
	}
	
	private By getSelectedPage() {
		return By.xpath(".//a[contains(@class,'ui-state-active')]");
	}
	
	public UsersPage verifyPaginationData() {
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

	public UsersPage validateUserRoleDataDisplayed(String userRole, int paginationValue) {
		LOGGER.info("Validate only searched user role data is displayed");
		String role=userRole;
		selectPaginationValue(paginationValue);
		if(Config.getLocalizationLanguage().contains("fr")) {
			role=DBUtils.getResultFromPostgresDB(DBQueries.getUserRoleInFrench(userRole));
		}
		Assert.assertTrue(verifyDisplayedUserRoleData(role), "Failed to display only selected user role data");
		return this;
	}

	public UsersPage validateClearFilter() {
		LOGGER.info("Validate clicking Clear Filter resets all data");
		shortWait();
		int displayedRowsCount = findElements(getRowsLink()).size();
		Assert.assertTrue(displayedRowsCount > 1, "Clear Filter does not working properly");
		return this;
	}

	public UsersPage clickDownload() {
		LOGGER.info("Clicking download");
		waitUntilElementIsVisible(getDownloadButton());
		click(getDownloadButton());
		return this;
	}

	public static boolean isFileDownloaded(String userType) {
		LOGGER.info("Verifying file downloade");
		String fileName = userType.toLowerCase() + ".csv";
		String downloadFilepath = Config.getDownloadFolderPath();
		File dir = new File(downloadFilepath);
		File[] dir_contents = dir.listFiles();

		if (dir_contents != null) {
			for (File dir_content : dir_contents) {
				if (dir_content.getName().equals(fileName))
					return true;
			}
		}
		return false;
	}

	public UsersPage validateFileDownload(String userType) {
		LOGGER.info("Vaidate file downloaded successfully");
		Assert.assertTrue(isFileDownloaded(userType), "File download failed");
		return this;
	}

	public UsersPage scrollInVerticalDirection(String offsetValue) {
		LOGGER.info("Moving scroll bar in vertical direction");
		shortWait();
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		verticalLocationBeforeScroll = (Long) executor.executeScript("return window.pageYOffset;");
		scrollByJavaScript("0", offsetValue);
		return this;
	}

	public UsersPage scrollInHorizonaltDirection() {
		LOGGER.info("Moving scroll bar in horizontal direction");
		waitUntilElementIsVisible(15, getLastPageButtonLink());
		click(getRightDirectionMovementButtonLink());
		horizontalLocationBeforeScroll = findElement(getRoleColumnHeaderLink()).getLocation().getX();
		shortWait();
		return this;
	}
	
	private By getFilterByRole() {
		return By.xpath(".//div[text()='FILTER BY ROLE']");
	}
	
	private By getSearchFieldvalue() {
		return By.xpath(".//input[contains(@class,'ui-state-filled')]");
	}
	
	public UsersPage ValidateClearFilters() {
		LOGGER.info("Moving scroll bar in horizontal direction");
		Assert.assertTrue(isElementPresent(getFilterByRole()));
		Assert.assertTrue(isElementNotVisible(getSearchFieldvalue()));
		return this;
	}

	public UsersPage validateVerticalScroll() {
		LOGGER.info("Validate scroll in vertical direction");
		shortWait();
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		verticalLocationAfterScroll = (Long) executor.executeScript("return window.pageYOffset;");
		Assert.assertTrue(verticalLocationBeforeScroll < verticalLocationAfterScroll, "Failed to scroll in vertical direction");
		return this;
	}

	public UsersPage validateHorizontalScroll() {
		LOGGER.info("Validate scroll in horizontal direction");
		waitUntilElementIsVisible(20, getRoleColumnHeaderLink());
		horizontalLocationAfterScroll = findElement(getRoleColumnHeaderLink()).getLocation().getX();
		Assert.assertTrue(horizontalLocationBeforeScroll > horizontalLocationAfterScroll, "Failed to scroll in horizontal direction");
		return this;
	}
	
	public UsersPage validateNumberOfDataSetDisplayed() {
		LOGGER.info("Validate number of data set displayed");
		Assert.assertTrue(flag, "Number of data set displayed is different than selected data count");
		return this;
	}

}
