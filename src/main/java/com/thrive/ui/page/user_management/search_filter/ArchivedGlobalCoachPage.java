package com.thrive.ui.page.user_management.search_filter;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;

public class ArchivedGlobalCoachPage extends UserManagementCommonPage{
	
	
	private By getSearchInputBox() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getArchivedCoachCheckbox(String coachName) {
		return By.xpath(".//span[normalize-space(text())='" + coachName
				+ "']/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath(".//span[contains(text(),'Email')]/../../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']");
	}
	
	private By getCoachesButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.coaches.coaches']"));
	}
	
	public GlobalCoachesPage clickCoaches() {
		LOGGER.info("Click on coaches button");
		click(getCoachesButton());
		return new GlobalCoachesPage();
	}

	public ArchivedGlobalCoachPage setSearchCoach(String coachName) {
		LOGGER.info("Setting globalcoach name to search");
		setValue(coachName, getSearchInputBox());
		return this;
	}
	
	public ArchivedGlobalCoachPage clickArchivedGlobalCheckbox(String coachname) {
		LOGGER.info("Clicking archived coach checkbox");
		click(getArchivedCoachCheckbox(coachname));
		return this;
	}
	
	public ArchivedGlobalCoachPage validateCoachPresent(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)));
		return this;
	}
	
	public ArchivedGlobalCoachPage validateCoachNotPresent(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)));
		return this;
	}
	
	private By getCoachNameElement() {
		return By.xpath(".//span[@class='ng-star-inserted']//a");
	}
	
	public RegisterCoachPersonalDetailsPage clickOnCoachName() {
		LOGGER.info("Clicking coach name");
		click(getCoachNameElement());
		return new RegisterCoachPersonalDetailsPage();
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
	
	public ArchivedGlobalCoachPage validateGlobalCoach() {
		LOGGER.info("Validate Global coach");
		Assert.assertTrue(isElementPresent(getGlobalCoachOptionValue()));
		return this;
	}
	
	public ArchivedGlobalCoachPage validateTrainingCoach() {
		LOGGER.info("Validate training coach");
		Assert.assertTrue(isElementPresent(getTrainingCoachOptionValue()));
		return this;
	}
	
	private By getSortByDropdown() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.sort_by']/../../../..//ng-select[@name='filter']"));
	}

	private By getSortByOptionElement(String sortBy) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(text(),'"+sortBy+"')]");
	}
	
	public ArchivedGlobalCoachPage selectSortByValue(String sortBy) {
		LOGGER.info("Clicking Sort by");
		click(getSortByDropdown());
		click(getSortByOptionElement(sortBy));
		return this;
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
	
	
	public ArchivedGlobalCoachPage validateClearFilters() {
		LOGGER.info("validate clear filters clear all data");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getSearchInput()),"search fileld is not cleared");
		softAssert.assertTrue(isElementPresent(getCoachTypeDropdownValue()),"coach type value is not present");
		softAssert.assertTrue(isElementPresent(getLanguageDropdownvalue()),"expertise value is not present");
		softAssert.assertTrue(isElementPresent(getIndustryDropdownValue()),"industry value is not present");
		softAssert.assertTrue(isElementPresent(getExpertiseDropdownValue()),"expertise value is not present");
		softAssert.assertTrue(isElementPresent(getSortByDropdownValue()),"sortby value is not present");
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
	
	public ArchivedGlobalCoachPage validateColumnsPresent() {
		LOGGER.info("validate after scrolling columns present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getOnlineColumn()),"online column not present");
		softAssert.assertTrue(isElementPresent(getOfflineColumn()),"offline column not present");
		softAssert.assertTrue(isElementPresent(getSessionsColumn()),"sessions column not present");
		softAssert.assertTrue(isElementPresent(getTrainingCoachColumn()),"training coach column not present");
		softAssert.assertAll();
		return this;
	}
	
	public ArchivedGlobalCoachPage clickRightScroller() {
		LOGGER.info("Clicking on the right scroller");
		mediumWait();
		int i=1;
		 while(isElementNotVisible(getOnlineColumn())) {
			 click(getRightScroller());
			 i++;
		 }
			 
		return this;
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getActionsDeleteInternalCoachOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.delete_coach']"));
	}
	
	private By getActionsUnarchiveInternalCoachOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.unarchive_coach']"));
	}
	
	public ArchivedGlobalCoachPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsUnarchiveInternalCoachOption()),"Un-Archive option not present for Internal coach");
		softAssert.assertTrue(isElementPresent(getActionsDeleteInternalCoachOption()),"Delete option not present for Internal coach");
		softAssert.assertAll();
		return this;
	}
	
		
}
