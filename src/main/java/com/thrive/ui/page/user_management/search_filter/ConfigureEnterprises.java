package com.thrive.ui.page.user_management.search_filter;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.category.CoachCategoriesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;

public class ConfigureEnterprises extends UserManagementCommonPage{
	
	
	
	private By getFilterByDropdown() {
		return By.xpath(".//ng-select[@name='filter']");
	}
	
	private By getFilterByDropdownValue(String enterprise) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+enterprise+"']");
	}
	
	private By getEnableRadioButton(String enterprise) {
		return By.xpath(".//a[text()='"+enterprise+"']/../..//following-sibling::td//label[contains(text(),'Enabled')]//following-sibling::span[contains(@class,'check')]");
	}
	
	private By getDiableRadioButton(String enterprise) {
		return By.xpath(".//a[text()='"+enterprise+"']/../..//following-sibling::td//label[contains(text(),'Disabled')]//following-sibling::span[contains(@class,'check')]");
	}
	
	private By getEnterpriseElement(String enterprise) {
		return By.xpath(".//a[text()='"+enterprise+"']");
	}
	
	private By getSaveChangesButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'private.profile.account_manager.update_details_button')]"));
	}
	
	public ConfigureEnterprises clickSaveChangesButton() {
		LOGGER.info("click save changes button");
		click(getSaveChangesButton());
		return this;
	}
	
	public ConfigureEnterprises selectEnterprise(String enterprise) {
		LOGGER.info("Select enterprise from the dropdown");
		click(getFilterByDropdown());
		mediumWait();
		click(getFilterByDropdownValue(enterprise));
		return this;
	}
	
	
	public ConfigureEnterprises enableDisableEnterprise(boolean isEnable, String enterprise) {

		LOGGER.info("Establishing enterprise status change");
		shortWait();
		if (isElementVisible(2, getEnterpriseElement(enterprise))) {
			String isEnableActive = getAttributeByValue("class", getEnableRadioButton(enterprise));
			System.out.println("isenabled "+isEnableActive);
			if (isEnable) {
				if (isEnableActive.contains("active")) {
					click(getDiableRadioButton(enterprise));
					click(getEnableRadioButton(enterprise));
				} else {
					click(getEnableRadioButton(enterprise));
				}

			} else {
				if (!isEnableActive.contains("active")) {
					click(getEnableRadioButton(enterprise));
					click(getDiableRadioButton(enterprise));
				} else {
					click(getDiableRadioButton(enterprise));
				}
			}
		}

		return this;
	}
	
	private By getEnabledEnterprise() {
		return By.xpath(".//label[normalize-space(text())='Enabled']//span[@class='check active']");
	}
	
	public ConfigureEnterprises validateEnterpriseEnabled() {
		LOGGER.info("Select validate selected enterprise is enabled");
		Assert.assertTrue(isElementPresent(getEnabledEnterprise()));
		return this;
	}
	
}
