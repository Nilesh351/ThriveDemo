package com.thrive.ui.page.user_management.search_filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InvitedGlobalCoach;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;

public class GlobalCoachesPage extends UserManagementCommonPage {

	AlertsAndMessagesPage alertsAndMessagesPage;

	private By getManageCategoryLink() {
		return By.xpath(getXpathByText(".//li[text()=' admin.coaches.view.manage_coach_categories']"));
	}

	private By getCoahesLink() {
		return By.xpath(getXpathByText(".//li[text()='admin.user_management.coaches_title']"));
	}

	private By getInviteLink() {
		return By.xpath(getXpathByText(".//li[text()='admin.user_management.coaches.invite_coach']"));
	}

	private By getFilterCoachComboBox() {
		return By.xpath(".//input[@role='combobox']");
	}

	private By getFilterCoachOptionElement(String coachOptionName) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text() = '" + coachOptionName + "']");
	}

	private By getFilteredCoachElement(String coachUserName) {
		return By.xpath(".//span[normalize-space(text())='" + coachUserName.trim().toLowerCase()
				+ "']/../preceding-sibling::td[1]//a");
	}

	protected By getSearchInput() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//input[@placeholder='Search']");
		} else {
			return By.xpath(".//input[@placeholder='Rechercher']");
		}
	}

	private By getRowsLink() {
		return By.xpath(
				"//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}

	private By getNoOfEntry() {
		return By.xpath(".//div[contains(@class,'ui-paginator-bottom')]/span[1]");
	}

	public GlobalCoachesPage selectSortByValue(String sortBy) {
		LOGGER.info("Clicking Sort by");
		click(getSortByDropdown());
		click(getSortByOptionElement(sortBy));
		return this;
	}

	private By getSortByDropdown() {
		return By.xpath(getXpathByText(
				".//div[text()='admin.user_management.coaches.sort_by']/../../../..//ng-select[@name='filter']"));
	}

	private By getSortByOptionElement(String sortBy) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(text(),'" + sortBy + "')]");
	}

//	private By getTrainingCheckboxChecked() {
//		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//			return By.xpath(".//label[text()='Set as training coach']/..//div[@aria-checked='true']");
//		}else {
//			return By.xpath(".//label[text()='Enable training coach']/..//div[@aria-checked='true']");
//		}
//		
//	}
//	
//	private By getGlobalCheckboxUnchecked() {
//		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//			return By.xpath(".//label[text()='Set as training coach']/..//div[@aria-checked='false']");
//		}else {
//			return By.xpath(".//label[text()='Enable training coach']/..//div[@aria-checked='false']");
//		}
//		
//	}

	private By getTrainingCoachOptionValue() {
		return By
				.xpath(".//span[contains(text(),'Training Coach')]/../../../../../../..//span[contains(text(),'Yes')]");
	}

	private By getGlobalCoachOptionValue() {
		return By.xpath(".//span[contains(text(),'Training Coach')]/../../../../../../..//span[contains(text(),'No')]");
	}

	public GlobalCoachesPage validateTrainingCoach() {
		LOGGER.info("Validate training coach");
		Assert.assertTrue(isElementPresent(getTrainingCoachOptionValue()));
		return this;
	}

	public GlobalCoachesPage validateGlobalCoach() {
		LOGGER.info("Validate Global coach");
		Assert.assertTrue(isElementPresent(getGlobalCoachOptionValue()));
		return this;
	}

	private By getRightScroller() {
		return By.xpath(".//span[@class='glyphicon glyphicon-chevron-right']");
	}

	private By getOnlineColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.col.%_online')]"));
	}

	private By getOfflineColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.col.%_offline')]"));
	}

	private By getSessionsColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.col.sessions')]"));
	}

	private By getTrainingCoachColumn() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.header_training_coach')]"));
	}

	public GlobalCoachesPage validateColumnsPresent() {
		LOGGER.info("validate after scrolling columns present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getOnlineColumn()), "online column not present");
		softAssert.assertTrue(isElementPresent(getOfflineColumn()), "offline column not present");
		softAssert.assertTrue(isElementPresent(getSessionsColumn()), "sessions column not present");
		softAssert.assertTrue(isElementPresent(getTrainingCoachColumn()), "training coach column not present");
		softAssert.assertAll();
		return this;
	}

	public GlobalCoachesPage clickRightScroller() {
		LOGGER.info("Clicking on the right scroller");
		mediumWait();
		int i=1;
		 while(isElementNotVisible(getOnlineColumn())) {
			 click(getRightScroller());
			 i++;
		 }
		return this;
	}

	private By getActionDropdown() {
		return By.xpath(".//button[contains(@class,'action-btn')]");
	}

	private By getInviteCoachOptionElement() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.invite_coach')]"));
	}

	private By getExpertiseArrow() {
		return By.xpath(getXpathByText(
				"//div[text()='admin.user_management.coaches.experties']/../..//span[@class='ng-arrow-wrapper']"));
	}

	private By getGlobalCoach(String coach) {
		return By.xpath(getXpathByText(
				".//span[text()='admin.user_management.coaches.col.name']/../../../../../../following-sibling::div//a[contains(text(),'"
						+ coach + "')]"));
	}

	private By getNameColumn(String coach) {
		return By.xpath(getXpathByText(
				"//span[contains(text(),'admin.user_management.coaches.col.name')]/../../../../../../../../../../../p-table[contains(@class,'coaches-tbl')]//a[normalize-space(text())='"
						+ coach + "']"));
	}

	public GlobalCoachesPage validateEmailPresent(String email) {
		LOGGER.info("Validating email is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumnVal(email)),
				"Failed to seach invited enterprise :" + email);
		return this;
	}

	private By getEmailAddressColumnVal(String email) {
		return By.xpath(getXpathByText(
				".//span[(text())='admin.user_management.coaches.col.email']/../../../../../../following-sibling::div//span[normalize-space(text())='"
						+ email.toLowerCase() + "']"));
	}

	private By getLanguageDropdownvalue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.language']"));
	}

	private By getExpertiseDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.experties']"));
	}

	private By getIndustryDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.industry']"));
	}

	private By getSortByDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.sort_by']"));
	}

	private By getCoachTypeDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.filter_training_coach']"));
	}

	private By getCoachesHeader() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.coaches']"));
	}

	public GlobalCoachesPage selectExpertiseArrow() {
		click(getExpertiseArrow());
		return this;
	}

	private By getInvitedCoach() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.coaches.invited_coaches']"));
	}

	public InvitedGlobalCoach clickInvitedCoaches() {
		LOGGER.info("Clicking Invited Coaches link");
		click(getInvitedCoach());
		return new InvitedGlobalCoach();
	}

	private By getCoachesButton() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.coaches.coaches']"));
	}

	public GlobalCoachesPage clickCoachesButton() {
		LOGGER.info("Clicking Coaches button");
		click(getCoachesButton());
		return new GlobalCoachesPage();
	}

	private By getLanguageValue() {
		return By.xpath(getXpathByText(
				"//div[(text())='admin.user_management.coaches.language']/../..//span[contains(text(),'Language')]"));
	}

	public GlobalCoachesPage validateLanguage(String expected) {
		LOGGER.info("Validating language");
		String actual = getText(getLanguageValue());
		Assert.assertEquals(actual, expected);
		return this;
	}

	private By getExpertiseValue() {
		return By.xpath(
				"//div[normalize-space(text())='FILTER BY EXPERTISE']/../..//span[contains(text(),'Expertise')]");
	}

	public GlobalCoachesPage validateExpertise(String expected) {
		LOGGER.info("Validating expertise");
		String actual = getText(getExpertiseValue());
		Assert.assertEquals(actual, expected);
		return this;
	}

	private By getIndustryValue() {
		return By.xpath(getXpathByText(
				"//div[(text())='admin.user_management.coaches.industry']/../..//span[contains(text(),'INDUSTRY')]"));
	}

	public GlobalCoachesPage validateIndustry(String expected) {
		LOGGER.info("Validating industry");
		String actual = getText(getIndustryValue());
		Assert.assertEquals(actual, expected);
		return this;
	}

	public InviteCoachPage clickInviteCoachDepricated() {
		LOGGER.info("Clicking Invite Coach link");
		click(getActionDropdown());
		shortWait();
		click(getInviteCoachOptionElement());
		return new InviteCoachPage();
	}

	public GlobalCoachesPage clickFilterCoachOption(String coachOptionName) {
		LOGGER.info("Clicking filter coach option");
		click(getFilterCoachOptionElement(coachOptionName));
		return this;
	}

	public GlobalCoachesPage clickFilterCoach() {
		click(getFilterCoachComboBox());
		return this;
	}

	private By getArchivedCoach() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.coaches.archived_coaches']"));
	}

	public ArchivedGlobalCoachPage clickArchivedCoaches() {
		LOGGER.info("Clicking Archived Coaches link");
		click(getArchivedCoach());
		return new ArchivedGlobalCoachPage();
	}

	private By getGlobalCoachEmail(String email) {
		return By.xpath(getXpathByText(
				"//th[text()='admin.user_management.coaches.col.email']/../../../../../../../../../../p-table[contains(@class,'coaches-tbl ')]//span[normalize-space(text())='"
						+ email + "']"));
	}

	public GlobalCoachesPage validateGlobalCoachByName(String name) {
		LOGGER.info("Validatingg global coach name is present");
		Assert.assertTrue(isElementPresent(getNameColumn(name)));
		return this;
	}

	public GlobalCoachesPage clickCoaches() {
		LOGGER.info("Clicking Coaches link");
		click(getCoahesLink());
		return this;
	}

	public InviteCoachPage clickInvite() {
		click(getInviteLink());
		return new InviteCoachPage();
	}

	public GlobalCoachesPage setSearchCriteria(String coachName) {
		LOGGER.info("Setting coach name to search");
		setValue(coachName, getSearchInput());
		return new GlobalCoachesPage();
	}

	public RegisterCoachPersonalDetailsPage clickOnSearchedCoachName(String coachName) {
		LOGGER.info("Clicking " + coachName + " coach name");
		click(getFilteredCoachElement(coachName));
		return new RegisterCoachPersonalDetailsPage();
	}

	private By getCoachNameElement() {
		return By.xpath(".//span[@class='ng-star-inserted']//a");
	}

	public RegisterCoachPersonalDetailsPage clickOnCoachName() {
		LOGGER.info("Clicking coach name");
		click(getCoachNameElement());
		return new RegisterCoachPersonalDetailsPage();
	}

	public InviteCoachPage validateAccesstoInviteGlobalCoach(String user) {
		LOGGER.info("Validating access to invite global coach");
		shortWait();
		clickInviteCoachDepricated();
		shortWait();
		Assert.assertTrue(getDriver().getTitle().contains("Invite coaches"));
		System.out.println(user + " have access to invite global coach.");
		return new InviteCoachPage();
	}

	private By getCoachCheckbox(String coachName) {
		LOGGER.info("Get client name checkbox");
		return By.xpath("//span[normalize-space(text())='" + coachName
				+ "']/../../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}

	public GlobalCoachesPage clickCoachCheckbox(String coachName) {
		click(getCoachCheckbox(coachName));
		return this;
	}

	public GlobalCoachesPage clickActionsDropdown(String coachName) {
		LOGGER.info("Clicking Actions Dropdown");
		clickCoachCheckbox(coachName);
		click(getActionDropdown());
		return this;
	}

	public GlobalCoachesPage validateCoachesElementVisible() {
		LOGGER.info("Validate Coaches element is visible");
		Assert.assertTrue(isElementPresent(getCoachesHeader()), "Coaches is not visible");
		return this;
	}

	private By getFavourateCoachHeader() {
		return By.xpath(".//span[text()='Auto-Global-Coach-Imutabl-First- A.']");
	}

	private By getProfilepageCoachHeader() {
		return By.xpath(".//span[text()='Auto-Global-Coach-Imutabl-First- Auto-Global-Coach-Imutabl-Last-']");
	}

	public GlobalCoachesPage clickCoachHeader() {
		LOGGER.info("Clicking coach header");
		click(getFavourateCoachHeader());
		String parentTab = getParentWindowHandle();
		switchToChildWindow(parentTab);
		return this;
	}

	public GlobalCoachesPage validateProfilePageCoachElementVisible() {
		LOGGER.info("Validate profile page Coach header element is visible");
		Assert.assertTrue(isElementPresent(getProfilepageCoachHeader()), "Coach profile page is not visible");
		return this;
	}

	private By getAllocateCreditElement() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.allocate_credits']"));
	}

	public GlobalCoachesPage validateAllocatCreditLinkIsNotVisible() {
		LOGGER.info("Validate that allocate credit link is not visible.");
		Assert.assertTrue(isElementNotVisible(getAllocateCreditElement()), "Allocate credit link is visible.");
		return this;
	}

	private By getArchiveCoachPage() {
		return By.xpath(".//p[contains(text(),'ARE YOU SURE YOU WANT TO ARCHIVE THIS COACH?')]");
	}

	public GlobalCoachesPage validateArchiveCoachPageIsVisible() {
		LOGGER.info("Validate Archive Coach page is visible.");
		Assert.assertTrue(isElementVisible(getArchiveCoachPage()), "Archive Cioach Page is not visible.");
		return this;
	}

	public GlobalCoachesPage clickActionsDropdown() {
		click(getActionDropdown());
		return this;
	}

	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}

	private By getActionsInviteCoachOption() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.invite_coach')]"));
	}

	private By getActionsArchiveCoachOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.archive_coach']"));
	}

	private By getSessionCount(int i) {
		return By.xpath("//tbody//tr[" + i + "]//td[9]");
	}

	private By getNextPageButton() {
		return By.xpath("//a[contains(@class,'ui-paginator-next')]");
	}

	private By getDisabledNextButton() {
		return By.xpath(
				"//a[contains(@class,'ui-paginator-next ui-paginator-element ui-state-default ui-corner-all ui-state-disabled')]");
	}
	
	private By getLastpageButton() {
		return By.xpath("//a[contains(@class,'ui-paginator-last')]");
	}
	
	private By getLastDisplayedPageButton() {
		return By.xpath("//a[contains(@class,'ui-paginator-next')]//preceding-sibling::span[contains(@class,'paginator-pages')]//a[contains(@class,'active')]");
	}
	
	private By getFirstPageButton() {
		return By.xpath("//a[contains(@class,'ui-paginator-first')]");
	}

	public GlobalCoachesPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()), "Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsInviteCoachOption()), "invite option not present");
		softAssert.assertTrue(isElementPresent(getActionsArchiveCoachOption()), "archive option not present");
		softAssert.assertAll();
		return this;
	}

	public GlobalCoachesPage validateClearFilters() {
		LOGGER.info("validate clear filters clear all data");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getSearchInput()), "search fileld is not cleared");
		softAssert.assertTrue(isElementPresent(getCoachTypeDropdownValue()), "coach type value is not present");
		softAssert.assertTrue(isElementPresent(getLanguageDropdownvalue()), "expertise value is not present");
		softAssert.assertTrue(isElementPresent(getIndustryDropdownValue()), "industry value is not present");
		softAssert.assertTrue(isElementPresent(getExpertiseDropdownValue()), "expertise value is not present");
		softAssert.assertTrue(isElementPresent(getSortByDropdownValue()), "sortby value is not present");
		return this;

	}

	public GlobalCoachesPage setArchiedCoachSearch(String name) {
		LOGGER.info("Setting archived coach name to search");
		setValue(name, getSearchInput());
		return this;
	}

	public CoachProfilePage searchAndClickCoach(String coachName) {
		LOGGER.info("Searching and Clicking " + coachName + " coach name");
		setSearchCriteria(coachName).clickSearchedCoach(coachName);
		return new CoachProfilePage();
	}

	public CoachProfilePage clickSearchedCoach(String coachName) {
		LOGGER.info("Clicking " + coachName + " coach name");
		click(getFilteredCoachElement(coachName));
		return new CoachProfilePage();
	}

	public GlobalCoachesPage validateGlobalCoachRegistration(CoachInviteDetails coachInviteDetails) {
		LOGGER.info("Validating global coach registered successfully");
		alertsAndMessagesPage = new AlertsAndMessagesPage()
				.validateToaster(ThriveAppSharedData.COACH_REGISTER_MESSAGE.getValue());
		return this;
	}

	public List<Integer> collectSessionsCount(int paginationValue) {
		LOGGER.info("Collecting sessions count");
		List<Integer> sessionsCountList = new ArrayList<Integer>();
		selectPaginationValue(paginationValue);

		click(getLastpageButton());
		int numberOfPages = Integer.parseInt(getText(getLastDisplayedPageButton()));
		click(getFirstPageButton());
		Integer sessionCount;
		exitLoop: for (int i=0; i<=numberOfPages; i++) {

			int displayedRowCount = findElements(getRowsLink()).size();
			for (int j = 1; j <= displayedRowCount; j++) {
				sessionCount = Integer.parseInt(getText(getSessionCount(j)));
				sessionsCountList.add(sessionCount);
			}
			if (isElementVisible(getDisabledNextButton())) {
				break exitLoop;
			} else {
				click(getNextPageButton());
			}
		}
		return sessionsCountList;
	}

	public GlobalCoachesPage validateSessionCountDataSorted(List<Integer> listBeforeSort, List<Integer> listAfterSort,
			String sortBy) {
		if(sortBy.contains("Most")) {
			Collections.sort(listBeforeSort, Collections.reverseOrder());
		} else {
			Collections.sort(listBeforeSort);
		}
		LOGGER.info("Validating Session count data is sorted as per Sort By option selected");
		Assert.assertEquals(listBeforeSort, listAfterSort, "List is not sorted in required order");
		return this;
	}

}