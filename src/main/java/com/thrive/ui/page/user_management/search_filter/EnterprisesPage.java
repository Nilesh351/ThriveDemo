package com.thrive.ui.page.user_management.search_filter;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.enterprise.EnterpriseDetailsPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedEnterprisesPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;

public class EnterprisesPage extends UserManagementCommonPage {
	
	private By getCreateEnterpriseLink() {
		return By.xpath(getXpathByText("//span[contains(text(),'admin.user_management.enterprises.create_enterprise')]"));
	}
	private By getActionsDropdownOptionsLink() {
		return By.xpath("//div[@class='dropdown-menu show']");
	}
	private By getSearchEnterpriseInput() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//input[@placeholder='Search']");
		} else {
			return By.xpath(".//input[@placeholder='Rechercher']");
		}
	}
	
	private By getEnterpriseLink(String entName) {
		return By.xpath(".//a[text()='"+entName+"']");
	}
	
	private By getActionDropdown() {
		return By.xpath(".//button[contains(@class,'action-btn')]");
	}
	
	private By getAllocateCreditElement() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.allocate_credits']"));
		} else {
		return By.xpath(".//span[text()='ALLOUER DES CRÉDITS']");
		}
	}
	
	private By getEnterpriseCheckbox(String enterpriseName) {
		return By.xpath(".//a[normalize-space(text())='"+enterpriseName+"']/../..//preceding-sibling::td//p-checkbox");
	}
	
	private By getInviteEnterpriseElement() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.invite_enterprise']"));
	}
	
	private By getEnterpriseButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.enterprises_tab']"));
	}
	
	private By getExportElement() {
		return By.xpath(getXpathByText(".//span[text()='admin.clients.invite.export_btn']"));
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getArchiveElement() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.archive_enterprise']"));
	}

	private By getEnterprise(String enterprise) {
		return By.xpath("//a[contains(text(),'"+enterprise+"')]");
	}
	
	private By getInvitedEnterpriseButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'admin.enterprises.view.invite_enterprise')]"));
		//return By.xpath(".//button[text()='Invite an Enterprise']");
	}
	
	private By getArchivedEnterpriseButton() {
		//return By.xpath(".//button[text()='Archived Enterprises']");
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.archived_enterprises']"));
	}
	
	public By getenterprisecheckbox(String enterprise) {
		return By.xpath(".//span[normalize-space(text())='"+enterprise+"']/../..//preceding-sibling::td//p-checkbox");
	}
	
	private By getEnterpriseNameLink(String email) {
		return By.xpath(".//span[normalize-space(text())='"+email+"']/../../td[@class='sticky-body ng-star-inserted']/span");
	}
	
	private By getEmailAddressColumn(String name) {
        return By.xpath(getXpathByText(".//span[contains(text(),'admin.account.manager.enterprise_email')]//ancestor::div[contains(@class,'ui-widget-header')]//following-sibling::div[contains(@class,'scrollable-body')]//span[contains(text(),'"+name+"')]"));
	}
	

	private By getCityColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.account.manager.enterprise_city')]"));
	}
	
	private By getCreditsRemainingColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.account.manager.enterprise_credit_reamaining')]"));
	}
	
	private By getCreditsUsedColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.account.manager.enterprise_credit_use')]"));
	}
	
	private By getEnterpriseAdminColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.account.manager.enterprise_admin')]"));
	}
	
	private By getEmailAddressColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.account.manager.enterprise_email')]"));
	}
	
	public EnterprisesPage validateEnterprisePresent(String name) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(name)), "Failed to seach enterprise :" + name);
		return this;
	}
	
	private By getDataCountDropdownLink() {
		return By
				.xpath("//div[contains(@class,'ui-dropdown-label-container')]//following-sibling::div[@role='button']");
	}

	private By getDataCountLink(int count) {
		return By.xpath("//p-dropdownitem//li//span[(text()='" + count + "')]");
	}
	
	public EnterprisesPage selectPaginationValue(int val) {
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
		
	public EnterprisesPage validateEnterpriseNamePresent(String name) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEnterprise(name)),"Failed to search enterprise");
		return this;
	}
	
	public EnterprisesPage validateEnterpriseNameNotPresent(String name) {
		LOGGER.info("Validating enterprise name not is present");
		Assert.assertTrue(isElementNotVisible(getEnterprise(name)),"Failed to search enterprise");
		return this;
	}
	
	public RegisterEnterpriseDetailsPage clickOnSearchedEnterpriseName(String email) {
		LOGGER.info("Clicking enterprise name");
		click(getEnterpriseNameLink(email));
		return new RegisterEnterpriseDetailsPage();
	}

	public EnterprisesPage clickCreteEnterpriseLink() {
		click(getCreateEnterpriseLink());
		return this;
	}
	
	public EnterprisesPage setSearchEnterprise(String entName) {
		LOGGER.info("Setting enterprise name to search");
		setValue(entName, getSearchEnterpriseInput());
		return this;
	}
	
	public EnterprisesPage checkEntepriseCheckbox(String entName) {
		click(getEnterpriseCheckbox(entName));
		return this;
	}
	
	public RegisterEnterpriseDetailsPage clickEnterprise(String entName) {
		click(getEnterpriseLink(entName));
		return new RegisterEnterpriseDetailsPage();
	}
	
	public InviteEnterprisePage clickInviteEnterprise() {
		LOGGER.info("Clicking Invite Enterprise link");
		click(getActionDropdown());
		click(getInviteEnterpriseElement());
		return new InviteEnterprisePage();
	}
	
	public EnterprisesPage clickEnterpriseButton() {
		click(getEnterpriseButton());
		return this;
	}
	
	public EnterprisesPage clickActionsDropdown() {
		click(getActionDropdown());
		return this;
	}
	
	public void clickInviteEnterpriseLink() {
		click(getInviteEnterpriseElement());
	}
	
	public EnterprisesPage clickExport() {
		LOGGER.info("Clcking Export button");
		click(getActionDropdown());
		click(getExportElement());
		return this;
	}
	
	public EnterprisesPage clickArcive(String entName) {
		LOGGER.info("Clicking Archive Enterprise");
		checkEntepriseCheckbox(entName);
		click(getActionDropdown());
		click(getArchiveElement());
		return this;
	}
	
	public InvitedEnterprisesPage clickInvitedEnterprise() {
		LOGGER.info("Clicking Invited enterprise link");
		click(getInvitedEnterpriseButton());
		return new InvitedEnterprisesPage();
	}
	
	private By getInviteAnEnterpriseButton() {
		if(Config.getTestPlatform().contains(TestPlatform.THRIVE)) {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.enterprises.invite_enterprise']"));
		} else {
		return By.xpath(getXpathByText(".//button[text()='admin.enterprises.view.invite_enterprise']"));
		}
	}
	
	public InvitedEnterprisesPage clickInviteAnEnterprise() {
		LOGGER.info("Clicking Invite An enterprise link");
		click(getInviteAnEnterpriseButton());
		return new InvitedEnterprisesPage();
	}
	
	public ArchivedEnterprisesPage clickArchivedEnterprise() {
		LOGGER.info("Clicking Archived enterprise link");
		click(getArchivedEnterpriseButton());
		return new ArchivedEnterprisesPage();
	}
	
	public EnterprisesPage clickEnterprisesCheckbox(String enterprise) {
		LOGGER.info("Clicking enterprise checkbox");
		click(getenterprisecheckbox(enterprise));
		return this;
	}
	
	private By getRightDirectionMovementButtonLink() {
		return By.xpath("//span[@class='glyphicon glyphicon-chevron-right']");
	}
	
	private By getLeftDirectionMovementButtonLink() {
		return By.xpath("//span[@class='glyphicon glyphicon-chevron-left']");
	}
	
	public EnterprisesPage clickRightScrollButton() {
		LOGGER.info("click right scroller button");
		while(isElementNotVisible(getCreditsUsedColumn())) {
			click(getRightDirectionMovementButtonLink());
		}
		return this;
	}
	
	public EnterprisesPage clickLeftScrollButton() {
		LOGGER.info("click left scroller button");
		while(isElementNotVisible(getEnterpriseAdminColumn())) {
			click(getLeftDirectionMovementButtonLink());
		}
		return this;
	}
	
	public EnterprisesPage validateResetFilters() {
		LOGGER.info("Validate reset filter works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		String enterpriseFilterText = getText(getFilterByRoleDropdownPlaceHolder());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		softAssert.assertTrue(enterpriseFilterText.equalsIgnoreCase("FILTER BY ENTERPRISE"), "Failed to reset the Filter by Enterprise dropdown");
		softAssert.assertAll();
		return this;
	}
	
	public EnterprisesPage validateRightScrollColumnsPresent() {
		LOGGER.info("Validate Columns present after right scroll");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getCityColumn()), "city column present");
		softAssert.assertTrue(isElementPresent(getCreditsRemainingColumn()), "Credits remaining column not present");
		softAssert.assertTrue(isElementPresent(getCreditsUsedColumn()), "Credits used column not present");
		softAssert.assertAll();
		return this;
	}
	
	public EnterprisesPage validateLeftScrollColumnsPresent() {
		LOGGER.info("Validate Columns present after left scroll");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getEnterpriseAdminColumn()), "enterprise admin column not present");
		softAssert.assertTrue(isElementPresent(getEmailAddressColumn()), "email address column not present");
		softAssert.assertAll();
		return this;
	}
	
	public List<WebElement> getActionsDropdownOptions() {
		return getDriver().findElements(getActionsDropdownOptionsLink());
	}
	
	public InviteEnterprisePage enterInviteEnterprisePage(String user) {
	    clickActionsDropdown();
		clickInviteEnterpriseLink();
		shortWait();
		return new InviteEnterprisePage();
	}
	
	private By getArchiveAlertPage() {
		return By.xpath(".//div[@class='modal-content']");
	}
	
	public EnterprisesPage validateArchiveEnterprisePageIsVisible() {
		LOGGER.info("Validate Archive Enterprise page is visible.");
		Assert.assertTrue(isElementVisible(getArchiveAlertPage()),"Archive Enterprise page is not visible.");
	    return this;
	}
	
	public EnterprisesPage setEnterpriseSearch(String enterprise) {
		LOGGER.info("Setting enterprise name to search");
		setValue(enterprise, getSearchInput());
		return this;
	}


	private By getActionsArchiveEnterpriseOption() {
		return By.xpath(".//span[text()='ARCHIVE']");
	}
	
	public AlertsAndMessagesPage selectArchiveEnterpriseAction() {
		LOGGER.info("Clicking Archive enterprise link");
		clickActionsDropdown();
		click(getActionsArchiveEnterpriseOption());
		return new AlertsAndMessagesPage();
	}
	
	private By getClientsLink() {
		return By.xpath(getXpathByText(".//li[normalize-space(text()) ='admin.user_management.clients_title']"));
	} 
	
	public EnterprisesPage validateClientsLinkIsNotPresent(){
		LOGGER.info("Validate Clients link is not visible");
		Assert.assertTrue(isElementNotVisible(getClientsLink()),"Clients link is present");
	    return this;
	}
	
	private By getAccountManagersLink() {
		return By.xpath(getXpathByText(".//li[text()='admin.user_management.account_manager']"));
	}
	
	public EnterprisesPage validateAccountManagerLinkNotVisible() {
		LOGGER.info("Validate Account Manager link is not visible");
		Assert.assertTrue(isElementNotVisible((getAccountManagersLink())), "Account Manager link is visible");
		return this;
	}
	
	private By getCoachesLink() {
		return By.xpath(getXpathByText(".//li[text()='admin.user_management.coaches_title']"));
	}
	
	public EnterprisesPage validateCoachesLinkIsNotVisible() {
		LOGGER.info("Validate Coaches link is not visible");
		Assert.assertTrue(isElementNotVisible((getCoachesLink())), "Coaches link is visible");
		return this;
	}
	
	private By getSearchValue() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getFilterByEnterpriseDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.users.enterprises']"));
	}
	
	private By getActionsCreateEnterpriseOption() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.create_enterprise']"));
		} else {
			return By.xpath("//span[contains(text(),'CRÉER UNE ENTREPRISE')]");
		}
	}
	
	private By getActionsReInviteEnterpirseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.reinvite_enterprise']"));
	}
	
	public EnterprisesPage validateClearFilter() {
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
	
	public EnterprisesPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsCreateEnterpriseOption()),"create enterprise option not present");
		softAssert.assertTrue(isElementPresent(getInviteEnterpriseElement()),"invite enterprise option not present");
		softAssert.assertTrue(isElementPresent(getAllocateCreditElement()),"allocate credits option not present");
		softAssert.assertTrue(isElementPresent(getActionsArchiveEnterpriseOption()),"archive option not present");
		return this;
	}
	
	public AllocateCreditsPage clickAllocateCredit(String entName) {
		LOGGER.info("Clicking allocate credit link");
		checkEntepriseCheckbox(entName);
		click(getActionDropdown());
		click(getAllocateCreditElement());
		return new AllocateCreditsPage();
	}
	
	private By getEnterpriseCreditsColumn(String email) {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//span[contains(text(),'"+email+"')]/..//following-sibling::td[3]");
	}
		
	public String captureEnterpriseCredits(String email) {
		LOGGER.info("Capturing enterprise credits from Credits column");
		return getText(getEnterpriseCreditsColumn(email));
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	private By getNoOfEntry() {
		return By.xpath(".//div[contains(@class,'ui-paginator-bottom')]/span[1]");
	}
	

}