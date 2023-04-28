package com.thrive.ui.page.category;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class CoachCategoriesPage extends BaseTestPage{
	
	private By getEnableRadioButton(String categoryName) {
		return By.xpath(".//a[text()='"+categoryName+"']/../following-sibling::td//input[@value='enable']/following-sibling::span[contains(@class, check-lef)]");
	}
	
	private By getCategoryElement(String categoryName) {
		return By.xpath(".//a[text()='"+categoryName+"']");
	}
	
	private By getDiableRadioButton(String categoryName) {
		return By.xpath(".//a[text()='"+categoryName+"']/../following-sibling::td//input[@value='disable']/following-sibling::span[contains(@class, check-lef)]");
	}
	
	private By getUpdateButton() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	private By getToasterMessageElement() {
		return By.xpath("//div[contains(@class,'toast-message ng-star-inserted')]");
	}
	
	private By getPaginationLink(int pageNumber) {
		return By.xpath(".//a[contains(@class,'ui-paginator-') and text()='"+pageNumber+"']");
	}
	
	private By getPaginationElement() {
		return By.xpath(".//span[@class='ui-paginator-pages']/a");
	}
	
	private By getNextpage() {
		return By.xpath(".//a[contains(@class,'ui-paginator-next ui-paginator-element ui-state-default ui-corner-all')]");
	}
	
	private By getFirstPage() {
		return By.xpath(".//a[contains(@class,'ui-paginator-first ui-paginator-element ui-state-default ui-corner-all')]");
	}
	
	private By getLastPage() {
		return By.xpath(".//a[contains(@class,'ui-paginator-last ui-paginator-element ui-state-default ui-corner-all')]");
	}
	
	private By getCurrentPage() {
		return By.xpath("//a[contains(@class,'ui-state-active')]");
	}
	
	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class,'toast-close-button')]");
	}
	
	private By getSaveChangesButton() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	
	public CoachCategoriesPage clickSaveChangesButton() {
		LOGGER.info("Clicking save changes button");
		click(getSaveChangesButton());
		return this;
	}
	
	private By getNameColumnHeaderElement() {
		return By.xpath(".//th[contains(@class,'ui-sortable-column ui-state-highlight ng-star-inserted')]");
	}
	
	
	public CoachCategoriesPage enableDisableCategories(boolean isEnable, List<String> categories) {
		
		LOGGER.info("Establishing categories status change");
		
		//TODO Forcefully sorting the categories but need to put better search mechanism to search the category faster
		shortWait();
		//click(getNameColumnHeaderElement());
		int pageCount = 1;
		
		for(String category : categories) {
			String expectedCategory="";
			if(Config.getLocalizationLanguage().contains("en")) {
				expectedCategory=category;
			} else {
				expectedCategory=DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(category));
			}
			for(int i = 1 ; i <=pageCount ; i++) {
				if(isElementVisible(2, getCategoryElement(expectedCategory))) {
					String isEnableActive = getAttributeByValue("class", getEnableRadioButton(expectedCategory));
					if(isEnable) {
						if(isEnableActive.contains("active")) {
							click(getDiableRadioButton(expectedCategory));
							click(getEnableRadioButton(expectedCategory));
						} else {
							click(getEnableRadioButton(expectedCategory));
						}
						
					} else {
						if(!isEnableActive.contains("active")) {
							click(getEnableRadioButton(expectedCategory));
							click(getDiableRadioButton(expectedCategory));
						} else {
							click(getDiableRadioButton(expectedCategory));
						}
					}
					break;
				} else {
					click(getLastPage());
					String count = getText(getCurrentPage());
					pageCount = Integer.parseInt(count);
					click(getFirstPage());
					click(getPaginationLink(i+1));
				}
			}
		}
		return this;
	}
	
	public CoachCategoriesPage validateExpertisePresent(String category) {
		
		LOGGER.info("Validate expertise present");
		shortWait();
		int pageCount;
		click(getNameColumnHeaderElement());
		click(getLastPage());
		String count = getText(getCurrentPage());
		pageCount = Integer.parseInt(count);
		click(getFirstPage());
		
			for(int i = 1 ; i <=pageCount ; i++) {
				if(isElementVisible(2, getCategoryElement(category))) {
					Assert.assertTrue(isElementPresent(getEnableRadioButton(category)));
				} else {
					click(getPaginationLink(i+1));
				}
			}
		
		return this;
	}
	
	
	public CoachCategoriesPage clickUpdateButton() {
		LOGGER.info("Clicking update button");
		click(getUpdateButton());
		return this;
	}
	
	public CoachCategoriesPage closeToasterAlert() {
		click(getToasterCloseButton());
		return this;
	}
	
	public CoachCategoriesPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getToasterMessageElement());
		String expectedToaster="";
		if(Config.getLocalizationLanguage().contains("en")) {
			expectedToaster=expectedMessage;
		} else {
			expectedToaster=ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE_FRENCH.getValue();
		}
		Assert.assertTrue(actualMessage.contains(expectedToaster), "Failed to update the coach categories");
		try {
			new AlertsAndMessagesPage().closeToasterAlert();
		} catch (Exception e){
		}
		return this;
	}
	

	public CoachCategoriesPage enableCategoryToCoach(String category) {
		LOGGER.info("Enabling category to coach");
		List<String> categories=new ArrayList<>();
		categories.add(category);
		enableDisableCategories(true,categories);
		return this;
	}

}
