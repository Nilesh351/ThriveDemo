package com.thrive.ui.page.enterprise;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.credits.ClientPersonalDetailsPage;
import com.thrive.ui.page.credits.EmployeeCreditPage;
import com.thrive.ui.page.invite.EnterpriseInviteEmployeePage;
import com.thrive.ui.page.invite.EnterpriseInvitedEmployeePage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;

public class EnterpriseEmployeePage extends UserManagementCommonPage {

	
	private By getInvitedEmployee() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.empolyee.invited']"));
	}
	
	private By getActionsDropdown() {
		return By.xpath(getXpathByText(".//button[contains(text(),'admin.user_management.actions')]"));
	}
	
	private By getActionsInviteEmployeeOption() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.empolyee.invite_empolyee')]"));
	}
	
	private By getActionsAllocateCreditsOption() {
		return By.xpath(getXpathByText(".//span[contains(text(),'shared.modals.admin_allocate_credits.title')]"));
	}
	
	protected By getSearchInput() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//input[@placeholder='Search']");
		} else {
			return By.xpath(".//input[@placeholder='Rechercher']");
		}
	}
	
	public EnterpriseInvitedEmployeePage clickInvitedEmployees() {
		LOGGER.info("Clicking Invited employees link");
		click(getInvitedEmployee());
		return new EnterpriseInvitedEmployeePage();
	}
	
	public EnterpriseEmployeePage clickAllocateCredits() {
		LOGGER.info("Clicking Allocate Credits link");
		click(getActionsAllocateCreditsOption());
		return this;
	}
	
	public EnterpriseInviteEmployeePage clickInviteEmployee() {
		LOGGER.info("Clicking Invite Employee link");
		click(getActionsDropdown());
		click(getActionsInviteEmployeeOption());
		return new EnterpriseInviteEmployeePage();
	}
	
	public UserManagementCommonPage setSearch(String enterprise) {
		LOGGER.info("Setting value to search");
		setValue(enterprise, getSearchInput());
		return new UserManagementCommonPage();
	}
	
	private By getClientNameLink(String email) {
		return By.xpath("//span[normalize-space(text())='"+email+"']/../preceding-sibling::td[1]//a");
	}
	
	public EmployeeCreditPage clickEmployeeNameLink(String email) {
		LOGGER.info("Clicking client name link");
		click(getClientNameLink(email));
		return new EmployeeCreditPage();
	}
	
	private By getEmployeeCheckboxEmail(String email) {
		return By.xpath("//span[contains(text(),'"+email+"')]/../..//p-checkbox//div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getArchiveClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.empolyee.archive_empolyee']"));
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class, 'toast-close-button')]");
	}
	
	private By getActionDropdownlink() {
		return By.xpath(".//button[contains(@class,'action-btn')]");
	}
	
	private By getInviteEmployeesLink() {
		return By.xpath("//span[contains(text(),'Invite Employee')]");
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath("//th[contains(text(),'Email')]//ancestor::div[contains(@class,'scrollable-header')]//following-sibling::div//span[contains(text(),'"+email+"')]");
	}
	
	private By getRegion(int i) {
		return By.xpath(getXpathByText("//p-table[not(contains(@class,'hidden'))]//th[contains(text(),'admin.employees.employees.col.region')]/../../../../..//following-sibling::div[contains(@class,'body')]//tr["+i+"]//td[6]"));
	}
	
	private By getDepartment(int i) {
		return By.xpath(getXpathByText("//p-table[not(contains(@class,'hidden'))]//th[contains(text(),'admin.employees.employees.col.department')]/../../../../..//following-sibling::div[contains(@class,'body')]//tr["+i+"]//td[5]"));
	}
	
	private By getRowElements() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[contains(@class,'body')]//tr");
	}
	
	private By getDisabledNextButton() {
		return By.xpath("//a[@class='ui-paginator-next ui-paginator-element ui-state-default ui-corner-all ui-state-disabled']");
	}
	
	private By getNextButton() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//a[contains(@class,'ui-paginator-next')]");
	}
	
	private By getSearchValue() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getByGetFilterByDepartment() {
		return By.xpath(getXpathByText("//div[contains(text(),'admin.dashboards.filter_by_department')]/.."));
	}

	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}

	private By getFilterByEnterpriseDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.users.enterprises']"));
	}
	
	private By getCreditsUsedforSessionCancelledLateColumn() {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.number_of_credits_used_for_sessions_cancelled_late']"));
	}
	
	private By getCreditsUsedforSessionNotAttendedColumn() {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.number_of_credits_used_for_sessions_not_attended']"));
	}
	
	private By getRightDirectionMovementButtonLink() {
		return By.xpath("//span[@class='glyphicon glyphicon-chevron-right']");
	}
	
	public EnterpriseEmployeePage clickEmployeeCheckboxEmail(String email) {
		LOGGER.info("Clicking Client checkbox");
		click(getEmployeeCheckboxEmail(email));
		return this;
	}
	
	public AlertsAndMessagesPage selectArchiveEmployeeAction() {
		LOGGER.info("Clicking Archive clients link");
		click(getActionsDropdown());
		click(getArchiveClientOption());
		return new AlertsAndMessagesPage();
	}
	
	
	public EnterpriseEmployeePage validateEmployeeArchiveToaster() {
		LOGGER.info("Validating toaster message");
		String expectedMessage;

		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			expectedMessage = ThriveAppSharedData.CLIENT_ARCHIVED_SUCCESSFULLY_MESSAGE.getValue();
		} else {
			expectedMessage = ThriveAppSharedData.CLIENT_ARCHIVED_SUCCESSFULY_MESSAGE_RM.getValue();
		}

		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :"
				+ "expected is : " + expectedMessage + " and actual is : " + actualMessage);
		click(getToasterCloseButton());
		return this;
	}
	
	public EnterpriseEmployeePage clickActionsDropdown() {
		click(getActionDropdownlink());
		return this;
	}
	
	public EnterpriseEmployeePage clickInviteEmployeeFromDropdown() {
		click(getInviteEmployeesLink());
		return this;
	}
	
	public EnterpriseEmployeePage validateEmployeePresent(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)));
		return this;	
	}
	
	public EnterpriseEmployeePage validateSelectedRegionPresent(String region,int paginationValue) {
		LOGGER.info("Validating only selected regions employees data is present");
		
		exitDo : do {
			selectPaginationValue(paginationValue);
			shortWait();
			int rowCount = findElements(getRowElements()).size();
			for(int i=1;i<=rowCount;i++) {
				if(!getText(getRegion(i)).contains(region)){
					Assert.assertTrue(false,"Filter By Region is not working as per expectation");
				}
			}
			if(isElementNotVisible(getDisabledNextButton())) {
				click(getNextButton());
			} else {
				break exitDo;
			}
		} while(isElementNotVisible(getDisabledNextButton()));
		Assert.assertTrue(true,"Failed to Filter By Region");
	    return this;
	}
	
	
	public EnterpriseEmployeePage validateSelectedDepartmentPresent(String department,int paginationValue) {
		LOGGER.info("Validating only selected department employees data is present");
		
		exitDo : do {
			selectPaginationValue(paginationValue);
			shortWait();
			int rowCount = findElements(getRowElements()).size();
			for(int i=1;i<=rowCount;i++) {
				if(!getText(getDepartment(i)).contains(department)){
					Assert.assertTrue(false,"Filter By Department is not working as per expectation");
				}
			}
			if(isElementNotVisible(getDisabledNextButton())) {
				click(getNextButton());
			} else {
				break exitDo;
			}
		} while(isElementNotVisible(getDisabledNextButton()));
		Assert.assertTrue(true,"Failed to Filter By Department");
	    return this;
	}
	
	public EnterpriseEmployeePage validateClearFilter() {
		LOGGER.info("Validate clicking Clear Filter resets all data");
		shortWait();
		int displayedRowsCount = findElements(getRowsLink()).size();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(displayedRowsCount > 1, "Clear Filter does not working properly");
		softAssert.assertTrue(isElementPresent(getSearchValue()));
		softAssert.assertTrue(isElementPresent(getByGetFilterByDepartment()));
		softAssert.assertAll();
		return this;
	}
	
	public EnterpriseEmployeePage clickRightScrollButton() {
		LOGGER.info("click right scroller button");
		while(isElementNotVisible(getCreditsUsedforSessionCancelledLateColumn())) {
			click(getRightDirectionMovementButtonLink());
		}
		return this;
	}
	
	public EnterpriseEmployeePage validateScrollColumnsPresent() {
		LOGGER.info("Validate Columns present after right scroll");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getCreditsUsedforSessionCancelledLateColumn()), "session late cancelled column not present");
		softAssert.assertTrue(isElementPresent(getCreditsUsedforSessionNotAttendedColumn()), "session not attended column not present");
		softAssert.assertAll();
		return this;
	}
}
