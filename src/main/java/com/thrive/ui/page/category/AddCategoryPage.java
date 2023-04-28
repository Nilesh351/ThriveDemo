package com.thrive.ui.page.category;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

import com.thrive.common.testdata.pojos.CategoryDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class AddCategoryPage extends BaseTestPage{
	
	private By getEnglishInput(){
		return By.xpath("//input[@name='name_en']");
	}
	
	private By getEnglishUkInputRm() {
		return By.xpath("//input[@name='name_uk']");
	}
	
	private By getFrenchInput() {
		return By.xpath("//input[@name='name_fr']");
	}
	
	private By getCategoryDropdown() {
		return By.xpath("//ng-select[contains(@name,'category')]//input");
	}
	
	private By getInternalCategoryDropdown() {
		return By.xpath("//ng-select[@name='internal_category']//input[@role='combobox']");
	}
	
	private By getCategoryDropdownvalue() {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span");
	}
	
	private By getTopicDropdown() {
		return By.xpath("//ng-select[contains(@name,'expertise_category')]//input");
	}
	
	private By getInternalTopicDropdown() {
		return By.xpath("//ng-select[@name='internal_expertise_category']//input[@role='combobox']");
	}
	
	private By getTopicDropdownvalue() {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span");
	}
	
	private By getSaveButton() {
		return By.xpath(getXpathByText("//button[text()='private.add_edit_categories.button_save']"));
	}
	
	private By getDiscardButton() {
		return By.xpath(getXpathByText("//button[text()='private.add_edit_categories.button_discard']"));
	}
	
	private By getCategoryToasterMessageElement() {
		return By.xpath("//div[@class='toast-message ng-star-inserted']");
	}
	
	private By getCloseToasterMsg() {
		return By.xpath("//span[text()='×']");
	}
	
	private By getTopicToasterMessageElement() {
		return By.xpath("//div[@class='toast-message ng-star-inserted']");
	}
	
	private By getExpertiseToasterMessageElement() {
		return By.xpath("//div[@class='toast-message ng-star-inserted']");
	}
	
	public AddCategoryPage setValues(CategoryDetails categoryDetails) {
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			setValuesThrive(categoryDetails.getCategory());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			setValuesRm(categoryDetails.getCategory(), categoryDetails.getCategoryUk(), categoryDetails.getCategoryFr());
		}
		
		return this;
	}
	
	public AddCategoryPage setCategoryDetails(CategoryDetails categoryDetails) {
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			setValuesThrive(categoryDetails.getCategory());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			setValuesRm(categoryDetails.getCategory(), categoryDetails.getCategoryUk(), categoryDetails.getCategoryFr());
		}
		return this;
	}
	
	public AddCategoryPage setTopicDetailsForCategory(CategoryDetails categoryDetails) {
		if(Config.getLocalizationLanguage().contains("en")) {
		selectCategory(categoryDetails.getCategory());
		} else {
			selectCategory(categoryDetails.getCategoryFr());
		}
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			setValuesThrive(categoryDetails.getTopic());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			setValuesRm(categoryDetails.getTopic(), categoryDetails.getTopicUk(), categoryDetails.getTopicFr());
		}
		return this;
	}
	
	public AddCategoryPage setExpertiseDetailsForCategory(CategoryDetails categoryDetails) {
		if(Config.getLocalizationLanguage().contains("en")) {
		selectTopic(categoryDetails.getTopic());
		} else {
			selectTopic(categoryDetails.getTopicFr());
		}
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			setValuesThrive(categoryDetails.getExpertise());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			setValuesRm(categoryDetails.getExpertise(), categoryDetails.getExpertiseUk(), categoryDetails.getExpertiseFr());
		}
		return this;
	}
	
	private AddCategoryPage setValuesThrive(String english) {
		LOGGER.info("Set language value as English");
		shortWait();
		setValue(english, getEnglishInput());
		return this;
	}
	
	private AddCategoryPage setValuesRm(String englishUs,String englishUk,String french) {
		LOGGER.info("Set language for the rm");
		mediumWait();
		setValue(englishUs, getEnglishInput());
		setValue(englishUk, getEnglishUkInputRm());
		setValue(french, getFrenchInput());
		return this;
		
	}
	
	public AddCategoryPage setValueEnglishUKRm(String english) {
		LOGGER.info("Set language value as English uk");
		shortWait();
		setValue(english, getEnglishUkInputRm());
		return this;
	}
	
	public AddCategoryPage setValueFrench(String french) {
		LOGGER.info("Set language value as French");
		setValue(french, getFrenchInput());
		return this;
	}
	
	
	public AddCategoryPage selectCategory(String category) {
		LOGGER.info("Selecting category");
		shortWait();
		setValue(category, getCategoryDropdown());
		selectGivenValueFromAutoDropdown(getCategoryDropdownvalue(),category);
		return this;
	}
	
	public AddCategoryPage selectInternalCategory(String category) {
		LOGGER.info("Selecting internal category");
		shortWait();
		setValue(category, getInternalCategoryDropdown());
		selectGivenValueFromAutoDropdown(getCategoryDropdownvalue(),category);
		return this;
	}
	
	public AddCategoryPage selectTopic(String topic) {
		LOGGER.info("Adding topic");
		shortWait();
		setValue(topic, getTopicDropdown());
		selectGivenValueFromAutoDropdown(getTopicDropdownvalue(),topic);
		return this;
	}
	
	public AddCategoryPage selectInternalTopic(String topic) {
		LOGGER.info("Selecting internal topic.");
		setValue(topic, getInternalTopicDropdown());
		selectGivenValueFromAutoDropdown(getTopicDropdownvalue(),topic);
		return this;
	}
	
	public AddCategoryPage clickSaveButton() {
		LOGGER.info("Clicking Save button");
		shortWait();
		click(getSaveButton());
		return this;
	}
	
	public ManageCategoryPage clickDiscardButton() {
		LOGGER.info("Clicking discard button");
		click(getDiscardButton());
		return new ManageCategoryPage();
	}
	
	public AddCategoryPage validateCategoryToaster(String expectedmsg) {
		LOGGER.info("Validating Category toaster massage");
		String actualmsg= getText(getCategoryToasterMessageElement());
		Assert.assertEquals(actualmsg, expectedmsg,"Category is not created successfully");
		return this;
	}
	
	public AddCategoryPage closetoaster() {
		LOGGER.info("Closing toaster message");
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				click(getCloseToasterMsg());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		
		return this;
	}
	
	public AddCategoryPage validateTopicToaster(String expectedmsg) {
		LOGGER.info("Validating Topic toaster message");
		String actualmsg= getText(getTopicToasterMessageElement());
		Assert.assertEquals(actualmsg, expectedmsg,"Topic is not created successfully");
		return this;
	}
	
	public AddCategoryPage validateExpertiseToaster(String expectedmsg) {
		LOGGER.info("Validating Expertise toaster message");
		String actualmsg= getText(getExpertiseToasterMessageElement());
		Assert.assertEquals(actualmsg, expectedmsg,"Expertise is not created successfully");
		return this;
	}

}
