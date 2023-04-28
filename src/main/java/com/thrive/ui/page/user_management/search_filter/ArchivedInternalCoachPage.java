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

public class ArchivedInternalCoachPage extends UserManagementCommonPage{
	
	
	private By getSearchInputBox() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getArchivedCoachCheckbox(String coachName) {
		return By.xpath(".//span[normalize-space(text())='" + coachName
				+ "']/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath(".//th[contains(text(),'Email')]/../../../../../..//following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']");
	}
	
	private By getInternalCoachesButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.internal_coaches_tab.coaches']"));
	}
	
	public InternalCoachesPage clickInternalCoaches() {
		LOGGER.info("click internal coaches");
		click(getInternalCoachesButton());
		return new InternalCoachesPage();
	}
	
	
	public ArchivedInternalCoachPage setSearchCoach(String coachName) {
		LOGGER.info("Setting internalcoach name to search");
		setValue(coachName, getSearchInputBox());
		return this;
	}
	
	public ArchivedInternalCoachPage clickArchivedInternalCoachCheckbox(String coachname) {
		LOGGER.info("Clicking archived internal coach checkbox");
		click(getArchivedCoachCheckbox(coachname));
		return this;
	}
	
	public ArchivedInternalCoachPage validateInternalCoachPresent(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)));
		return this;
	}
	
	public ArchivedInternalCoachPage validateInternalCoachNotPresent(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)));
		return this;
	}
	
	private By getCoachNameElement() {
		return By.xpath(".//span[@class='ng-star-inserted']//a");
	}
	
	public ArchivedInternalCoachPage validateEnterpriseNamePresent(String name) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEnterprise(name)),"Failed to search enterprise");
		return this;
	}
	
	private By getRightScroller() {
		return By.xpath(".//span[@class='glyphicon glyphicon-chevron-right']");
	}
	
	
	public ArchivedInternalCoachPage clickRightScroller() {
		LOGGER.info("Clicking on the right scroller");
		mediumWait();
		int i=1;
		 while(i<=4) {
			 click(getRightScroller());
			 i++;
		 }
			 
		return this;
	}
	
	private By getOnlineColumn() {
		return By.xpath(getXpathByText(".//th[contains(text(),'admin.user_management.coaches.col.%_online')]"));
	}
	
	private By getOfflineColumn() {
		return By.xpath(getXpathByText(".//th[contains(text(),'admin.user_management.coaches.col.%_offline')]"));
	}
	
	private By getSessionsColumn() {
		return By.xpath(getXpathByText(".//th[contains(text(),'admin.user_management.coaches.col.sessions')]"));
	}
	
	public ArchivedInternalCoachPage validateColumnsPresent() {
		LOGGER.info("validate after scrolling columns present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getOnlineColumn()),"online column not present");
		softAssert.assertTrue(isElementPresent(getOfflineColumn()),"offline column not present");
		softAssert.assertTrue(isElementPresent(getSessionsColumn()),"sessions column not present");
		softAssert.assertAll();
		return this;
	}

	private By getEnterprise(String enterprise) {
		return By.xpath("//a[contains(text(),'"+enterprise+"')]");
	}
	
	public RegisterCoachPersonalDetailsPage clickOnInternalCoachName() {
		LOGGER.info("Clicking coach name");
		click(getCoachNameElement());
		return new RegisterCoachPersonalDetailsPage();
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
	
	public ArchivedInternalCoachPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsUnarchiveInternalCoachOption()),"Un-Archive option not present for Internal coach");
		softAssert.assertTrue(isElementPresent(getActionsDeleteInternalCoachOption()),"Delete option not present for Internal coach");
		softAssert.assertAll();
		return this;
	}
	
	
		
}
