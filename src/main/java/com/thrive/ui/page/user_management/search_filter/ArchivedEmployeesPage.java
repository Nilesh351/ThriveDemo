package com.thrive.ui.page.user_management.search_filter;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.enterprise.EnterpriseEmployeePage;

public class ArchivedEmployeesPage extends UserManagementCommonPage{

	private By getEmployeesLink() {
		return By.xpath(getXpathByText("//button[contains(text(),'shared.header.employees')]"));
	}

	public EnterpriseEmployeePage clickEmployees() {
		LOGGER.info("Clicking Employees");
		click(getEmployeesLink());
		return new EnterpriseEmployeePage();
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath("//th[contains(text(),'Email')]/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']");
	}
	
	public ArchivedEmployeesPage validateEmployeeNotPresent(String email) {
		LOGGER.info("Validating Account Manager is not present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)));
		return this;
	}
	
	public ArchivedEmployeesPage validateArchivedEmployeePresent(String email) {
		LOGGER.info("Validating Account Manager is not present");
		Assert.assertTrue(isElementVisible(getEmailAddressColumn(email)));
		return this;
	}
	
	private By getregion(String region) {
		return By.xpath(".//span[contains(text(),'"+region+"')]");
	}
	
	private By getdepartment(String department) {
		return By.xpath(".//span[contains(text(),'"+department+"')]");
	}
	
	public ArchivedEmployeesPage validateRegionPresent(String region) {
		LOGGER.info("Validating region is present");
		Assert.assertTrue(isElementPresent(getregion(region)),"region not present");
		return this;
	}
	
	public ArchivedEmployeesPage validateDepartmentPresent(String department) {
		LOGGER.info("Validating department is present");
		Assert.assertTrue(isElementPresent(getdepartment(department)),"department not present");
		return this;
	}
	
	protected By getSearchInput() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//input[@placeholder='Search']");
		} else {
			return By.xpath(".//input[@placeholder='Rechercher']");
		}
	}
	
	private By getRegionDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.dashboards.filter_by_region']"));
	}
	
	private By getyDepartmentDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.dashboards.filter_by_department']"));
	}
	
	
	public ArchivedEmployeesPage validateClearFilters() {
		LOGGER.info("validate clear filters clear all data");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getSearchInput()),"search fileld is not cleared");
		softAssert.assertTrue(isElementPresent(getRegionDropdownValue()),"region value is not present");
		softAssert.assertTrue(isElementPresent(getyDepartmentDropdownValue()),"department value is not present");
		softAssert.assertAll();
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
	
	private By getRightDirectionMovementButtonLink() {
		return By.xpath("//span[@class='glyphicon glyphicon-chevron-right']");
	}
	
	private By getCreditsUsedforSessionCancelledLateColumn() {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.number_of_credits_used_for_sessions_cancelled_late']"));
	}
	
	private By getCreditsUsedforSessionNotAttendedColumn() {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.number_of_credits_used_for_sessions_not_attended']"));
	}
	
	public ArchivedEmployeesPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsUnarchiveClientOption()),"Un-Archive client option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteClientOption()),"invite client option not present");
		softAssert.assertAll();
		return this;
	}

	
	public ArchivedEmployeesPage clickRightScrollButton() {
		LOGGER.info("click right scroller button");
		while(isElementNotVisible(getCreditsUsedforSessionCancelledLateColumn())) {
			click(getRightDirectionMovementButtonLink());
		}
		return this;
	}
	
	public ArchivedEmployeesPage validateScrollColumnsPresent() {
		LOGGER.info("Validate Columns present after right scroll");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getCreditsUsedforSessionCancelledLateColumn()), "session late cancelled column not present");
		softAssert.assertTrue(isElementPresent(getCreditsUsedforSessionNotAttendedColumn()), "session not attended column not present");
		softAssert.assertAll();
		return this;
	}
}
