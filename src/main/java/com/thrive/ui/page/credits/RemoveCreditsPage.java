package com.thrive.ui.page.credits;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RemoveCreditsPage extends BaseTestPage{
	
	private By getNumberOfCredits() {
		return By.xpath(".//input[@name='credits_Admin']");
	}
	
	private By getRemoveQuantity() {
		return By.xpath(getXpathByText(".//div[normalize-space(text())='shared.modals.admin_client_delete_credits.quantity']/input"));
	}

	private By getRemoveCreditsButton() {
		return By.xpath(getXpathByText(".//button[text()='shared.modals.admin_remove_credits.remove_credits']"));
	}
	
	private By getDeleteQuantityButton() {
		return By.xpath(getXpathByText(".//button[text()='shared.modals.admin_client_delete_credits.delete_quantity_button']"));
	}

	private By getRemoveAllCreditsButton() {
		return By.xpath(getXpathByText(".//button[text()='shared.modals.admin_remove_credits.remove_all_credits']"));
	}
	
	private By  getDeleteAllButton() {
		return By.xpath(getXpathByText(".//button[text()='shared.modals.admin_client_delete_credits.delete_all_button']"));	
	}

	private By getCancelLink() {
		return By.xpath(getXpathByText(".//a[text()='shared.modals.admin_client_delete_credits.cancel_button']"));
	}
	
	private By getRemoveCreditLink() {
		return By.xpath("//div[contains(@class,'mycredit mycredits')]//descendant::div[contains(@class,'col-xs-12 my')][1]//div[contains(@class,'col-xs-1')]");
	}

	private By getDeleteQuantityElement() {
		return By.xpath(getXpathByText("//button[contains(text(),'shared.modals.admin_client_delete_credits.delete_quantity_button')]"));
	}
	
	private By geterrorMessage() {
		return By.xpath(".//p[contains(@class,'err-notify') and contains(text(),'Please select Credits Up to')]");
	}
	
	private By getErrorMessageClient(String errorMessage) {
		return By.xpath(".//p[text()='"+errorMessage+"']");
	}

	public RemoveCreditsPage setNumberOfCredits(String credits) {
		LOGGER.info("Setting value for number of credits");
		shortWait();
		setValue(credits, getNumberOfCredits());
		return this;
	}
	
	public RemoveCreditsPage setQuantityToDelete(String quantity) {
		LOGGER.info("Setting quantity to delete");
		setValue(quantity, getRemoveQuantity());
		return this;
	}

	public AlertsAndMessagesPage clickRemoveCredits() {
		LOGGER.info("Clicking remove credits");
		click(getRemoveCreditsButton());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage clickDeleteQuantity() {
		LOGGER.info("Clicking Delete Quantity");
		click(getDeleteQuantityButton());
		return new AlertsAndMessagesPage();
	}

	public AlertsAndMessagesPage clickRemoveAllCredits() {
		LOGGER.info("Clicking remove all credits button" );
		click(getRemoveAllCreditsButton());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage clickDeleteAll() {
		LOGGER.info("Clicking remove all credits button");
		click(getDeleteAllButton());
		return new AlertsAndMessagesPage();
	}

	public RemoveCreditsPage clickCancel() {
		LOGGER.info("Clicking cancle button");
		click(getCancelLink());
		return this;
	}

	public void validateRemoveCreditPageIsVisible() {
		LOGGER.info("Verify that remove credit page appears.");
		Assert.assertTrue(isElementVisible(getDeleteQuantityElement()),"Remove credit windoe is not visible.");
	}
	
	
	public RemoveCreditsPage clickRemoveCreditLink() {
		LOGGER.info("Clicking remove credits links");
		longWait();
		click(getRemoveCreditLink());
		return this;
	}
	
	public RemoveCreditsPage validateRemoveCreditPagIsVisible() {
		LOGGER.info("Verify that remove credit page appears.");
		Assert.assertTrue(isElementVisible(getRemoveCreditsButton()),"Remove credit windoe is not visible.");
		return this;
	}
	
	public RemoveCreditsPage validateErrorMessgaePresent() {
		LOGGER.info("Verify Error message is present");
		Assert.assertTrue(isElementPresent(geterrorMessage()),"Error message is not present");
		return this;
	}
	
	public RemoveCreditsPage validateErrorMessagePresentClient(String errorMsg) {
		LOGGER.info("Verify Error message is present");
		Assert.assertTrue(isElementPresent(getErrorMessageClient(errorMsg)),"Error message is not present");
		return this;
		
	}


}
