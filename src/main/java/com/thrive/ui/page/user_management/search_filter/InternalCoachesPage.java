package com.thrive.ui.page.user_management.search_filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.invite.EnterpriseInvitedEmployeePage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InviteInternalCoachPage;
import com.thrive.ui.page.invite.InvitedInternalCoachPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;



public class InternalCoachesPage extends UserManagementCommonPage {
	
	private By getEnterpriseCoachesLink() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.enterprise.coaches_title']"));
	}
	
	private By getInviteCoachOptionElement() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.invite_coach')]"));
	}
	
	private By getCoachInviteLink() {
		return By.xpath(getXpathByText("//a[text()='admin.coaches.view.invite']"));
	}
	
	
	private By getEmailAddressColumnVal(String email) {
		return By.xpath(getXpathByText(".//th[normalize-space(text())='admin.user_management.coaches.col.email']/../../../../../following-sibling::div//span[normalize-space(text())='"+email+"']"));
	}
	
	public InternalCoachesPage validateEmailPresent(String email) {
		LOGGER.info("Validating email is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumnVal(email)), "Failed to seach invited enterprise :" + email);
		return this;
	}
	
	public InternalCoachesPage validateEmailIsNotPresent(String email) {
		LOGGER.info("Validating email is not present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumnVal(email)), "Failed to delete archived user :" + email);
		return this;
	}
	
	public InviteInternalCoachPage clickOnEnterpriseCoachesink() {
		LOGGER.info("Click enterprise coaches link element");
		click(getEnterpriseCoachesLink());
		return new InviteInternalCoachPage();
	}
	
	
	private By getInvitedCoach() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.coaches.invited_coaches']"));
	}
	
	public InvitedInternalCoachPage clickInvitedCoachesButton() {
		LOGGER.info("Click Invited coach button");
		click(getInvitedCoach());
		return new InvitedInternalCoachPage();
	}
	
	private By getCoaches() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.internal_coaches_tab.coaches']"));
	}
	
	public InternalCoachesPage clickInternalCoachesButton() {
		LOGGER.info("Clicking coches button");
		click(getCoaches());
		return this;
	}
	
	private By getCoachNameElement() {
		return By.xpath(getXpathByText("//th[contains(text(),'admin.user_management.coaches.col.name')]//ancestor::div[contains(@class,'header')]//following-sibling::div//span[@class='ng-star-inserted']//a"));
	}
	
	public RegisterCoachPersonalDetailsPage clickOnInternalCoachName() {
		LOGGER.info("Clicking coach name");
		click(getCoachNameElement());
		return new RegisterCoachPersonalDetailsPage();
	}
	

	public InviteInternalCoachPage clickOnInviteCoachLink() {
		LOGGER.info("Click coach invite link element");
		click(getCoachInviteLink());
		return new InviteInternalCoachPage();
	}
	
	private By getActionDropdown() {
		return By.xpath(".//button[contains(@class,'action-btn')]");
	}
	
	private By getFilterCoachComboBox() {
		return By.xpath(".//input[@role='combobox']");
	}
	
	private By getFilterCoachOptionElement(String coachOptionName) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text() = '"+coachOptionName+"']");
	}
	
	private By getFilteredCoachElement(String coachUserName) {
		return By.xpath(".//span[normalize-space(text())='"+coachUserName.trim()+"']/../preceding-sibling::td[1]//a");
	}
	
	protected By getSearchInput() {
		 if(Config.getLocalizationLanguage().contains("en")) {
		    	return By.xpath(".//input[@placeholder='Search']");
		    } else {
		    	return By.xpath(".//input[@placeholder='Rechercher']");
		    }
	}
	private By getInternalCoachEmail(String email) {
		return By.xpath(getXpathByText("//th[text()='admin.user_management.coaches.col.email']/../../../../../../../../../../p-table[contains(@class,'coaches-tbl ')]//span[normalize-space(text())='"+email+"']"));
	}
	
	private By getInternalCoach(String coach) {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.coaches.col.name']/../../../../../following-sibling::div//a[contains(text(),'"+coach+"')]"));
	}
	
	public InternalCoachesPage validateInternalCoachByEmail(String email) {
		click(getInternalCoachEmail(email));
		return this;
	}
	
	private By getenterprise(String enterprise) {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.coaches.col.enterprise']/../../../../../following-sibling::div//a[text()='"+enterprise+"']"));
	}
	
	public InternalCoachesPage validateEnterprisePresent(String enterprise) {
		LOGGER.info("Validate enterprise name is present");
		Assert.assertTrue(isElementPresent(getenterprise(enterprise)), "Failed to search the Enterprise :" + enterprise);
		return this;
	}
	
	private By getArchivedCoach() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.internal_coaches_tab.archived_coaches']"));
	}
	
	public ArchivedInternalCoachPage clickArchivedCoachButton() {
		LOGGER.info("Clicking coches button");
		click(getArchivedCoach());
		return new ArchivedInternalCoachPage();
	}
	
	public InternalCoachesPage validateEnterpriseNamePresent(String name) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEnterprise(name)),"Failed to search enterprise");
		return this;
	}

	private By getEnterprise(String enterprise) {
		return By.xpath("//a[contains(text(),'"+enterprise+"')]");
	}
	
	public InviteCoachPage clickInviteCoachDepricated() {
		LOGGER.info("Click invite coach link");
		click(getActionDropdown());
		click(getInviteCoachOptionElement());
		return new InviteCoachPage();
	}
	
	public InternalCoachesPage setSearchCriteria(String coachName) {
		LOGGER.info("Setting coach name to search");
		setValue(coachName, getSearchInput());
		return this;
	}
	
	public CoachProfilePage clickSearchedCoach(String coachName) {
		LOGGER.info("Clicking "+coachName+ " coach name");
		click(getFilteredCoachElement(coachName));
		return new CoachProfilePage();
	}
	
	public RegisterCoachPersonalDetailsPage clickOnSearchedCoachName(String coachName) {
		LOGGER.info("Clicking "+coachName+ " coach name");
		click(getFilteredCoachElement(coachName));
		return new RegisterCoachPersonalDetailsPage();
	}
	
	public CoachProfilePage searchAndClickCoach(String coachName) {
		LOGGER.info("Searching and Clicking "+coachName+ " coach name");
		setSearchCriteria(coachName).clickSearchedCoach(coachName);
		return new CoachProfilePage();
	}
	
	public CoachProfilePage clickInternalCoach(String coach) {
		click(getInternalCoach(coach));
		return new CoachProfilePage();
	}
	
	private By getCoachCheckbox(String coachName) {
		return By.xpath("//span[normalize-space(text())='"+coachName+"']/../../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	
	public InternalCoachesPage clickActionsDropdown(String coachName) {
		LOGGER.info("Clicking actions dropdown");
		click(getCoachCheckbox(coachName));
		click(getActionDropdown());
		return this;
    }
	
	private By getAllocateCreditElement() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.allocate_credits']"));
	}
	
	public InternalCoachesPage validateAllocatCreditLinkIsNotVisible() {
		LOGGER.info("Validate that allocate credit link is not visible.");
		Assert.assertTrue(isElementNotVisible(getAllocateCreditElement()),"Allocate credit link is visible.");
	    return this;
	}
	

	private By getArchiveCoachPage() {
		return By.xpath(getXpathByText(".//p[contains(text(),'shared.modals.admin_coach_archive_modal.title')]"));
	}
	
	
	public InternalCoachesPage clickCoachCheckbox(String coachName) {
		click(getCoachCheckbox(coachName));
		return this;
	}
	
	public InternalCoachesPage validateArchiveInternalCoachPageIsVisible() {
		LOGGER.info("Validate Archive Internal Coach page is visible.");
		Assert.assertTrue(isElementVisible(getArchiveCoachPage()),"Archive Internal Coach Page is not visible.");
		return this;
	}
	
	private By getRightScroller() {
		return By.xpath(".//span[@class='glyphicon glyphicon-chevron-right']");
	}
	
	private By getLeftScroller() {
		return By.xpath(".//span[@class='glyphicon glyphicon-chevron-left']");
	}
	
	public InternalCoachesPage clickRightScroller() {
		LOGGER.info("Clicking on the right scroller");
		mediumWait();
		int i=1;
		 while(i<=4) {
			 if(isElementVisible(getRightScroller())) {
				 click(getRightScroller());
			 }
			 i++;
		 }	 
		return this;
	}
	
	public InternalCoachesPage clickLeftScroller() {
		LOGGER.info("Clicking on the left scroller");
		mediumWait();
		int i=1;
		 while(i<=4) {
			 if(isElementVisible(getLeftScroller())) {
				 click(getLeftScroller());
			 }
			 i++;
		 }		 
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
	
	public InternalCoachesPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getActionsInviteCoachOption()),"invite option not present");
		softAssert.assertTrue(isElementPresent(getActionsArchiveCoachOption()),"archive option not present");
		softAssert.assertAll();
		return this;
	}
	
	private By getSortByDropdown() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.sort_by']/../../../..//ng-select[@name='filter']"));
	}

	private By getSortByOptionElement(String sortBy) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(text(),'"+sortBy+"')]");
	}
	
	public InternalCoachesPage selectSortByValue(String sortBy) {
		LOGGER.info("Clicking Sort by");
		click(getSortByDropdown());
		click(getSortByOptionElement(sortBy));
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
	
	private By getApprovedColumn() {
		return By.xpath(getXpathByText(".//th[contains(text(),'admin.user_management.coaches.col.approved')]"));
	}
	
	private By getEmailAddressColumn() {
		return By.xpath(getXpathByText(".//th[contains(text(),'admin.user_management.coaches.col.email')]"));
	}
	
	public InternalCoachesPage validateColumnsPresentSA() {
		LOGGER.info("Validate after scrolling columns present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getOnlineColumn()),"online column not present");
		softAssert.assertTrue(isElementPresent(getOfflineColumn()),"offline column not present");
		softAssert.assertTrue(isElementPresent(getSessionsColumn()),"sessions column not present");
		softAssert.assertAll();
		return this;
	}
	
	public InternalCoachesPage validateLeftScrollEA() {
		LOGGER.info("Validate scrolling in left direction");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getEmailAddressColumn()),"Email Address column not present, failed to scroll in left direction");
		softAssert.assertAll();
		return this;
	}
	
	public InternalCoachesPage validateRightScrollEA() {
		LOGGER.info("Validate scrolling in right direction");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getApprovedColumn()),"Approved column not present, failed to scroll in right direction");
		softAssert.assertTrue(isElementPresent(getSessionsColumn()),"Sessions column not present, failed to scroll in right direction");
		softAssert.assertAll();
		return this;
	}
	
	private By getCoachTypeDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.filter_training_coach']"));
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
	
	private By getFilterByEnterpriseDropdownValue() {
		return By.xpath(".//div[text()='FILTER BY ENTERPRISE']");
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

	private By getSessionCount(int i) {
		return By.xpath("//tbody//tr[" + i + "]//td[8]");
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	private By getNextPageButton() {
		return By.xpath("//a[contains(@class,'ui-paginator-next')]");
	}
	
	public InternalCoachesPage validateClearFilters() {
		LOGGER.info("validate clear filters clear all data");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getSearchInput()),"search fileld is not cleared");
		//softAssert.assertTrue(isElementPresent(getSortByDropdown()),"Sort type value is not present");
		softAssert.assertTrue(isElementPresent(getLanguageDropdownvalue()),"expertise value is not present");
		softAssert.assertTrue(isElementPresent(getIndustryDropdownValue()),"industry value is not present");
		softAssert.assertTrue(isElementPresent(getExpertiseDropdownValue()),"expertise value is not present");
		softAssert.assertTrue(isElementPresent(getSortByDropdownValue()),"sortby value is not present");
		softAssert.assertTrue(isElementPresent(getFilterByEnterpriseDropdownValue()),"Enterprise value is not present");
		return this;
		
	}

	
	public List<Integer> collectSessionsCount(int paginationValue) {
		LOGGER.info("Collecting sessions count");
		List<Integer> sessionsCountList = new ArrayList<Integer>();
		int numberOfPages;
		
		selectPaginationValue(paginationValue);
		int displayedRowCount = findElements(getRowsLink()).size();
		
		if(displayedRowCount == paginationValue) {
			click(getLastpageButton());
			numberOfPages = Integer.parseInt(getText(getLastDisplayedPageButton()));
			click(getFirstPageButton());
		} else {
			numberOfPages = 1;
		}
		Integer sessionCount;
		exitLoop: for (int i=0; i<=numberOfPages; i++) {

			
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
	
	public InternalCoachesPage validateSessionCountDataSorted(List<Integer> listBeforeSort, List<Integer> listAfterSort,String sortBy) {
		LOGGER.info("Validating Session count data is sorted as per Sort By option selected");	
		if(sortBy.contains("Most")) {
			Collections.sort(listBeforeSort, Collections.reverseOrder());
		} else {
			Collections.sort(listBeforeSort);
		}
		
		Assert.assertEquals(listBeforeSort, listAfterSort, "List is not sorted in required order");
		return this;
	}
	
}

