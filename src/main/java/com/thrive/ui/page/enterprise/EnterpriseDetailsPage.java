package com.thrive.ui.page.enterprise;


import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.category.EnterpriseConfigureCategoryPage;
import com.thrive.ui.page.category.ManageCategoryPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;

public class EnterpriseDetailsPage extends BaseTestPage{
	
	private By getcreditcount() {
		return By.xpath("//div[text()='20 credits']");
	}
	
	private By getClientLink() {
		return By.xpath(".//li[normalize-space(text()) ='CLIENTS']");
	}
	
	private By getEnterpriseBrandingLink() {
		return By.xpath(".//li[text()=' ENTERPRISE BRANDING ']");
	}
	
	private By getConfigureCategoriesLink() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//li[text()='private.profile.enterprise.categories_list']"));
		} else {
			return By.xpath("//li[contains(text(),'CONFIGURER LES CATÉGORIES')]");
		}
	}

	private By getRomeveCreditElement() {
		return By.xpath("//descendant::div[contains(text(),'MyThrive credits')]//parent::div[@class='ng-star-inserted']//descendant::div[@class='col-xs-12 mycredit-row ng-star-inserted'][1]//child::div[@style='cursor: pointer;']");
	}
	
	private By getEnterpriseCredits() {
		return By.xpath(".//div[@class='mycredit']/div/div[1]");
	}
	
	private By getRemoveCreditsIcon() {
		return By.xpath(".//div[normalize-space(text())='✖']");
	}
	
	public EnterpriseDetailsPage verifyCreditAllocationCount(String value) {
		LOGGER.info("verify credit allocation value");
		Assert.assertEquals(getText(getcreditcount()), value, "Allocate credit count successful");
		return this;
	}
	
	public int getEnterpriseCurrentCredits() {
		LOGGER.info("Get enterprise credits");
		int curCredit =0;
		
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			String eaCredit = getText(getEnterpriseCredits());
			String currentCredits = eaCredit.split(" ")[2];
			curCredit = Integer.parseInt(currentCredits);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			String eaCredit = getText(getEnterpriseCredits());
			String currentCredits = eaCredit.split(" ")[2];
			curCredit = Integer.parseInt(currentCredits);
		}
		
		return curCredit;
	}
	
	public EnterpriseDetailsPage clickRemoveCreditsIcon() {
		LOGGER.info("click remove credits icon");
		shortWait();
		click(getRemoveCreditsIcon());
		return this;
	}
	
	public ClientsPage clickClients() {
		LOGGER.info("Clicking Clients link");
		click(getClientLink());
		return new ClientsPage();
	}
	
	public EnterpriseConfigureCategoryPage clickConfigureCategories() {
		LOGGER.info("Clicking configure categories link");
		click(getConfigureCategoriesLink());
		return new EnterpriseConfigureCategoryPage();
	}

	
	public EnterpriseDetailsPage validateEnterpriseBrandingLinkVisible() {
		LOGGER.info("verify Enterprise Branding page is available");
		Assert.assertTrue(isElementVisible(getEnterpriseBrandingLink()), "Enterprise Branding Link is not visble");
		return this;
	}
	
	public EnterpriseDetailsPage validateEnterpriseBrandingLinkIsNotVisible() {
		LOGGER.info("verify Enterprise Branding page is available");
		Assert.assertTrue(isElementNotVisible(getEnterpriseBrandingLink()), "Enterprise Branding Link is visble");
		return this;
	}

	public EnterpriseDetailsPage clickRemoveCreditElement() {
		LOGGER.info("Clicking remove credit element");
		mediumWait();
		click(getRomeveCreditElement());
		return this;
	}
	
	private By getRemoveCreditPageLink() {
		return By.xpath("//div[@class='modal-content']");	
	}
	
	private By getSettingsDropdown() {
		return By.xpath(getXpathByText("//span[contains(@class,'header-submenu-list')]//span[contains(text(),'shared.coaching_program.submenu.settings')]"));
	}
	
	private By getCoachingCategories() {
		return By.xpath(getXpathByText("//div[contains(text(),'private.profile.enterprise.categories_list')]"));
	}
	
	public void validateRemoveCreditPageIsVisible() {
		LOGGER.info("Validating Remove Credit page appears.");
		Assert.assertTrue(isElementVisible(getRemoveCreditPageLink()),"Remove credit windoe is not visible.");
	}
	
	public EnterpriseDetailsPage validateCreditAssignedRefelected(int current,int updated,String count) {
		int providedcredit = Integer.parseInt(count);
		int expected = providedcredit + current;
		Assert.assertEquals(updated,expected,"credits are not matching");
		return this;
	}
	
	public EnterpriseDetailsPage validateBookSessionCreditsReflected(int current,int updated,int count) {
		LOGGER.info("Validate book session credit assigned reflected");
		int expected = current - count;
		Assert.assertEquals(updated, expected,"credits are not matching");
		return this;
		
	}
	
	public EnterpriseDetailsPage validateCancelSessionCreditsReflectedCheck(int current , int updated,int count) {
		LOGGER.info("Validate cancel session credit reflected");
		current = current - count;
		Assert.assertEquals(current, updated,"credits are not matching");
		return this;
	}
	
	public EnterpriseDetailsPage validateCreditRemovedRefected(int current,int updated,String count) {
		int providedcredit = Integer.parseInt(count);
		int expected = current - providedcredit;
		Assert.assertEquals(updated, expected,"credits are not matching");
		return this;
	}
	
	public EnterpriseDetailsPage clickSettingDropdown() {
		LOGGER.info("Clicking Settings dropdown");
		shortWait();
		click(getSettingsDropdown());
		return this;
	}
	
	public ManageCategoryPage clickCoachingCategories() {
		LOGGER.info("Clicking Coaching categories");
		click(getCoachingCategories());
		return new ManageCategoryPage();
	}
}