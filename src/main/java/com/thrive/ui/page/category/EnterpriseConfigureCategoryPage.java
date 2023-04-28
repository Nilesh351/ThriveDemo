package com.thrive.ui.page.category;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.pojos.CategoryDetails;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class EnterpriseConfigureCategoryPage extends BaseTestPage{
	
	private By getShowAllButton() {
		return By.xpath(".//button[text()='SHOW ALL']");
	}
	
	private By getAddButton() {
		return By.xpath(".//ng-select[@name = 'add']");
	}
	
	private By getCateggoryType(String type) {
		return By.xpath(".//span[text()='"+type+"']");
	}
	
	private By getDisableCategoryLink(String catName) {
		return By.xpath(getXpathByText(".//span[contains(text(),'"+catName+"')]/../following-sibling::div//span[text()='private.manage_categories.label_disable_all']"));
	}
	
	private By getDisableTopic(String topicName) {
		return By.xpath(getXpathByText(".//span[text()='"+topicName+"']/../following-sibling::div//span[text()='private.manage_categories.label_disable_all']"));
	}
	
	private By getCategoryCollapseElement(String name) {
		return By.xpath(".//span[text()='"+name+"']/../../../i[contains(@class, 'pull-right')]");
	}
	
	private By getTopicCollapseElement(String name) {
		return getCategoryCollapseElement(name);
	}
	
	private By getCategoryToggle(String name) {
		return By.xpath(".//span[text()='"+name+"']/../..//span[@class='slider round']");
	}
	
	private By getTopicToggle(String name) {
		return getCategoryToggle(name);
	}
	
	private By getExpertiseToggle(String expertiseName) {
		return By.xpath("//span[text()='manual testing']/../../..//span[@class='slider round']");
	}
	
	private By getCategoryname(String category) {
		return By.xpath(".//span[text()='"+category+"']");
	}
	
	private By getTopicName(String topic) {
		return By.xpath(".//span[text()='"+topic+"']");
	}
	
	private By getExpertiseName(String expertise) {
		return By.xpath(".//span[text()='"+expertise+"']");
	}
	
	private By getCategoryEdit(String category) {
		return By.xpath(".//span[text()='"+category+"']/../..//following-sibling::div/span/img");
	}
	
	private By getTopicEdit(String topic) {
		return By.xpath(".//span[text()='"+topic+"']/../..//following-sibling::div/span/img");
	}
	
	private By getExpertiseEdit(String expertise) {
		return By.xpath(".//span[text()='"+expertise+"']/../../..//following-sibling::div/span/img");
	}
	
	private By getDisableAllLink(String category) {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText("//span[text()='"+category+"']/..//following-sibling::div/span[text()='private.manage_categories.label_disable_all']"));
	    } else {
	    	return By.xpath("//span[text()='"+category+"']/..//following-sibling::div/span[text()='Tout désactiver']");
	    }
	}
	
	private By getEnableAllLink(String category) {
	if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText("//span[text()='"+category+"']/..//following-sibling::div/span[text()='private.manage_categories.label_enable_all']"));
		} else {
			return By.xpath("//span[text()='"+category+"']/..//following-sibling::div/span[text()='Tout activer']");
		}
	}
	
	private By getInternalOnly() {
		return By.xpath(getXpathByText(".//button[text()='private.manage_categories.label_internal_only']"));
	}
	
	public EnterpriseConfigureCategoryPage clickAdd() {
		LOGGER.info("Cliccking Add link");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				click(getAddButton());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		return this;
	}
	
	public AddCategoryPage AddCategoryType(String type) {
		LOGGER.info("Adding category type");
		click(getCateggoryType(type));
		return new AddCategoryPage();
	}
	
	
	public EnterpriseConfigureCategoryPage clickDisableCategoryLink(String catName) {
		LOGGER.info("Clicking disable category link");
		click(getDisableCategoryLink(catName));
		return this;
	}
	
	public EnterpriseConfigureCategoryPage clickDisableTopicLink(String topicName) {
		LOGGER.info("Clicking disable topic link");
		click(getDisableTopic(topicName));
		return this;
	}
	
	public EnterpriseConfigureCategoryPage clickCategoryCollapseElement(CategoryDetails categoryDetailsame) {
		LOGGER.info("Clicking category collapse element");
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				if(Config.getLocalizationLanguage().contains("en")) {
					click(getCategoryCollapseElement(categoryDetailsame.getCategory()));
					} else {
						click(getCategoryCollapseElement(categoryDetailsame.getCategoryFr()));
					}
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		
		return this;
	}
	
	public EnterpriseConfigureCategoryPage clickTopicCollapseElement(CategoryDetails categoryDetailsame) {
		LOGGER.info("Clicking topic collapse element");
		if(Config.getLocalizationLanguage().contains("en")) {
			click(getCategoryCollapseElement(categoryDetailsame.getTopic()));
			} else {
				click(getCategoryCollapseElement(categoryDetailsame.getTopicFr()));
			}	
		return this;
	}
	
	public EnterpriseConfigureCategoryPage clickCategoryToggle(String Name) {
		LOGGER.info("Clicking category toggle");
		click(getCategoryToggle(Name));
		return this;
	}
	
	
	public EnterpriseConfigureCategoryPage clickTopicToggle(String Name) {
		LOGGER.info("Clicking topic toggle");
		click(getTopicToggle(Name));
		return this;
	}
	
	public EnterpriseConfigureCategoryPage clickExpertiseToggle(String expertiseName) {
		LOGGER.info("Clicking Expertise toggle");
		click(getExpertiseToggle(expertiseName));
		return this;
	}
	
	public AddCategoryPage  clickCategoryEdit(String category) {
		LOGGER.info("Clicking edit category link");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getCategoryEdit(category));
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
				
		return new AddCategoryPage();
	}
	
	public AddCategoryPage clickTopicEdit(String topic) {
		LOGGER.info("Clicking edit topic tlink");
		shortWait();
		click(getTopicEdit(topic));
		return new AddCategoryPage();
	}
	
	public AddCategoryPage clickExpertiseEdit(String expertise) {
		LOGGER.info("Clicking edit expertise tlink");
		click(getExpertiseEdit(expertise));
		return new AddCategoryPage();
	}
	
	public EnterpriseConfigureCategoryPage clickInternalOnlyButton() {
		LOGGER.info("Clicking internal only button");
		click(getInternalOnly());
		return this;
	}
	
	public EnterpriseConfigureCategoryPage validateCategory(CategoryDetails categoryDetails) {
		LOGGER.info("Validating category");
		if(Config.getLocalizationLanguage().contains("en")) {
		    Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getCategory())));
		} else {
			Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getCategoryFr()))); 
		}
		return this;
	}
	
	public EnterpriseConfigureCategoryPage validateTopic(CategoryDetails categoryDetails) {
		LOGGER.info("Validating topic");
		if(Config.getLocalizationLanguage().contains("en")) {
		    Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getTopic())));
		} else {
			Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getTopicFr()))); 
		}		return this;
	}
	
	public EnterpriseConfigureCategoryPage validateExpertise(CategoryDetails categoryDetails) {
		LOGGER.info("Validating expertise");
		if(Config.getLocalizationLanguage().contains("en")) {
		    Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getExpertise())));
		} else {
			Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getExpertiseFr()))); 
		}		return this;
	}
	
	
	public EnterpriseConfigureCategoryPage enableOrDisableCategories(boolean isEnable, List<String> categories) {
		LOGGER.info("Clicking Enable/Disable categories button");
		//click(getShowAllButton());
		for(String category : categories) {
			String expectedCategory="";
			if(Config.getLocalizationLanguage().contains("en")) {
				expectedCategory=category;
			} else {
				expectedCategory=DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(category));
			}
			if(isEnable) {
				if(isElementVisible(5, getEnableAllLink(expectedCategory))) {
					int counter = 0;
					boolean flag = true;
					do {
						try {
							shortWait();
							click(getEnableAllLink(expectedCategory));
						} catch (StaleElementReferenceException e) {
							flag = false;
							shortWait();
						}
						counter++;
					} while(!flag && counter<=5);
					
				}
			} else {
				if(isElementVisible(5, getDisableAllLink(expectedCategory))) {
					click(getDisableAllLink(expectedCategory));
				}
			}
		}	
		return this;
	}
	
	
	public EnterpriseConfigureCategoryPage validateEnableOrDisableCategories(boolean isEnable, List<String> categories) {
		LOGGER.info("Validating Enabled/Disabled categories");
		for(String category : categories) {
			if(isEnable) {
				Assert.assertTrue(isElementPresent(getEnableAllLink(category)));
			} else {
				Assert.assertTrue(isElementPresent(getDisableAllLink(category)));
				}
			}
			
		return this;
	}
	
	private By getCategoryStatus(String category) {
	if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath("//span[contains(text(),'"+category+"')]/..//following-sibling::div[1]//child::span[contains(text(),'able')]");
	} else {
		return By.xpath("//span[contains(text(),'"+category+"')]/..//following-sibling::div[1]//child::span[contains(text(),'activer')]");
	}
	}
	
	public EnterpriseConfigureCategoryPage enableGlobalCategory(String category) {
		LOGGER.info("Enabling "+category+" category for an enterprise");
		shortWait();
		List<String> categories=new ArrayList<>();
		categories.add(category);
		String categoryDBValue=DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(category));

		if(getText(getCategoryStatus(categoryDBValue)).contains("En") || !(getText(getCategoryStatus(categoryDBValue)).contains("Tout activer"))) {
			enableOrDisableCategories(true,categories);
		} else {
			enableOrDisableCategories(false,categories);
			shortWait();
			enableOrDisableCategories(true,categories);
		}
		
		return this;
	}
	
	public EnterpriseConfigureCategoryPage validateEnableCategory(String category) {
		LOGGER.info("Validate "+category+" category is enabled for an enterprise");
		List<String> categories=new ArrayList<>();
		String categoryDBValue=DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(category));
		categories.add(categoryDBValue);
		
		validateEnableOrDisableCategories(false,categories);
		return this;
	}
}
