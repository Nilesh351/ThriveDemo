package com.thrive.ui.page.credits;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.pojos.credits.AllocateCredits;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class AllocateCreditsPage extends BaseTestPage{
	
	private By getEnterpriseCheckbox(String entName) {
		return By.xpath(".//a[normalize-space(text())='"+entName+"']/../preceding-sibling::td//div[contains(@class, 'ui-corner')]/span");
	}
	
	private By getAllocateButton() {
		return By.xpath(getXpathByText("//button[text()='admin.enterprises.view.allocate']"));
	}
		
	private By getCreditTypeDropdown() {
		return By.xpath("//ng-select[@name='type']//input[@role='combobox']");
	}
	
	private By getCreditTypeOptionElement(String option) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()='"+option+"']");
	}
	
	private By getCreditTypeFromTheDropdownList(String credittype) {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+credittype+"']");
	}
	
	private By getNumberOfCreditsInput() {
		return By.xpath(getXpathByText("//label[contains(text(),'shared.modals.admin_allocate_credits.numbers_of_credits')]//following-sibling::input[@name='credits_Admin']"));
	}
	
	private By getExpiryDate() {
		return By.xpath("//input[@name='expires_At']");
	}
	
	private By getAllocateCreditsButton() {
		return By.xpath(getXpathByText("//button[contains(text(),'shared.modals.admin_allocate_credits.allocate_button')]"));
	}
	
	private By getCancelLink() {
		return By.xpath(getXpathByText("//a[text()='shared.modals.admin_remove_credits.cancel_button']"));
	}
	
	private By getYesButton() {
		return By.xpath(getXpathByText("//button[text()='shared.modals.admin_allocate_credits.yes_button']"));
	}
	
	private By getNoButton() {
		return By.xpath(getXpathByText("//button[text()='shared.modals.buttons.no_button']"));
	}
	
	private By getAllocateCreditNotification() {
		return By.xpath("//div[contains(@class,'toast-success')]//div");
	}
	
	private By getcreditAllocationDate() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//label[contains(text(),'shared.modals.admin_allocate_credits.expiry_date')]/..//input"));
		} else {
			return By.xpath(".//label[contains(text(),'Date d')]/..//input");
		}
	} 
	
	
	
	public AllocateCreditsPage clickEnterpriseCheckbox(String entName) {
		LOGGER.info("Clicking Enterprise checkbox");
		click(getEnterpriseCheckbox(entName));
		return this;
	}
	
	public AllocateCreditsPage clickCancelLink() {
		LOGGER.info("Clicking Enterprise checkbox");
		click(getCancelLink());
		return this;
	}
	
	public AlertsAndMessagesPage clickAllocateButton() {
		LOGGER.info("Clicking Allocate Button");
		waitUntilElementIsClickable(5,getAllocateButton());
		click(getAllocateButton());
		return new AlertsAndMessagesPage();
	}
	
	public AllocateCreditsPage clickCreditTypeDropdown(String credittype) {
		LOGGER.info("Click credit type dropdown");
		click(getCreditTypeDropdown());
		click(getCreditTypeFromTheDropdownList(credittype));
		return this;
	}
	
	public AllocateCreditsPage setNumberOfCredits(String i) {
		LOGGER.info("Set no of credits");
		setValue(i,getNumberOfCreditsInput());
		return this;
	}
	
	public AllocateCreditsPage setExpiryDate(String date) {
		LOGGER.info("Set expiry date");
		setValue(date,getExpiryDate());
		return this;
	}
	
	public AlertsAndMessagesPage clickAllocateCreditButton() {
		LOGGER.info("Clicking allocate credit button");
		click(getAllocateCreditsButton());
		return new AlertsAndMessagesPage();
	}
	
	
	public AllocateCreditsPage clickYesButton() {
		LOGGER.info("Click Yes button");
		click(getYesButton());
		return this;
	}
	
	public AllocateCreditsPage verifyCreditAllocationNotification(String notification) {
		LOGGER.info("Validate credit allication notification");
		Assert.assertEquals(getText(getAllocateCreditNotification()), notification, "Allocate credit notification appeared");
		return this;
	}
	
	public AllocateCreditsPage selectCreditType(String creditType) {
		LOGGER.info("Selecting credit type");
		setValue(creditType,getCreditTypeDropdown());
		click(getCreditTypeOptionElement(creditType));
		return this;
	}

//	public AllocateCreditsPage CreditAllocationDetails(AllocateCredits allocateCredits)
//	{
//		LOGGER.info("Filling Credit allocation details.");
//		clickCreditTypeDropdown(allocateCredits.getCreditstype());
//		setNumberOfCredits(allocateCredits.getCredits());
//		setExpiryDate(allocateCredits.getDate());
//		clickAllocateCreditButton();
//		clickYesButton();
//		return this;
//	}
	
	public AlertsAndMessagesPage allocateCreditToEnterprise(String creditType, String credits, String date) {
		LOGGER.info("Allocating credit to Enterprise");
		selectCreditType(creditType).
		setNumberOfCredits(credits).setExpiryDate(date).clickAllocateCreditButton();
		return new AlertsAndMessagesPage();
	}

	public AllocateCreditsPage validateAllocatCredtPageIsVisible() {
		LOGGER.info("Validate allocate credit page is visible");
		Assert.assertTrue(isElementVisible(getAllocateCreditsButton()),"Allocate credit page is not visible");
		return this;
	}
	
	public AllocateCreditsPage validateDatePresent(String expected) {
		LOGGER.info("Validate allocate credit page is visible");
		String actual = getAttributeByValue("value", getcreditAllocationDate());
		Assert.assertEquals(actual, expected,"dates are not matching");
		return this;
	}

	public AllocateCreditsPage CreditAllocationDetails(AllocateCredits allocateCredits)
	{
		LOGGER.info("Filling Credit allocation details.");
		setNumberOfCredits(allocateCredits.getCredits());
		setExpiryDate(allocateCredits.getDate());
		clickAllocateCreditButton();
		clickYesButton();
		return this;
	}
	
	
	public String expectedCreditAfterAllocation(String creditsBeforeAllocation,String creditCount) {
		LOGGER.info("Calculating expected credits after credit alloation");
		int creditAfterAllocation=Integer.valueOf(creditsBeforeAllocation)+Integer.valueOf(creditCount);
		return String.valueOf(creditAfterAllocation);
	}

	public AllocateCreditsPage validateCredits(String creditsBeforeAloocation,String creditsAfterAllocation) {
		LOGGER.info("Validate credits after acredits allocation");
		Assert.assertTrue(creditsBeforeAloocation.equals(creditsAfterAllocation),"Falied to validate credit allocation");
		return this;
	}
}