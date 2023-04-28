package com.thrive.ui.page.user_management.search_filter;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InviteInternalCoachPage;

public class EnterprisePage extends BaseTestPage{
	
	private By getEnterpriseLink() {
		return By.xpath("//a[text()='ENTERPRISES']");
	}
	
	private By getSpecificEnterpriseLink(String entName) {
		return By.xpath("//a[normalize-space(text())='"+entName+"']");
	}
	
	private By getCreeatedEntName(String entname) {
		return By.xpath("//a[text()= '"+" " +entname+" " +"']");
	}
	
	private By getEnterpriseSearchDropdown() {
		return By.xpath("//ng-select[@name='filter']//input[@role='combobox']");
	}
	
	private By getSelectEnterpriseFromDropdown() {
		return By.xpath("//span[@class='ng-option-label ng-star-inserted']");
	}
	
	
	public EnterprisePage clickEnterprisesLink() {
		LOGGER.info("Clicking Enterprise Element");
		click(getEnterpriseLink());
		return new EnterprisePage();
	}
	
	public EnterprisePage clickOnSpecificEnterpriseNameLink(String entName) {
		LOGGER.info("Clicking given enterprise name element");
		click(getSpecificEnterpriseLink(entName));
		return new EnterprisePage();
	}
	
	public EnterprisePage validateCreateEnterpriseSuccessful(String entName) {
		LOGGER.info("Validate Create Enterprise Successful");
		setValue(entName,getEnterpriseSearchDropdown());
		selectGivenValueFromAutoDropdown(getSelectEnterpriseFromDropdown(), entName);
		Assert.assertTrue(isElementVisible(getCreeatedEntName(entName)), "Error: Enterprise is not created");
		Assert.assertEquals(getText(getCreeatedEntName(entName)),entName,"Enterprise is not created successfully");
		return this;
	} 
	
	
	public InviteInternalCoachPage getInviteInternalCoachPage() {
		return new InviteInternalCoachPage();
	}
	
	public InviteClientPage getInviteInternalClientPage(){
		return new InviteClientPage();
	}
	
	public InviteEnterprisePage getInviteEnterprisePage(){
		return new InviteEnterprisePage();
	}
	
	
	
}
