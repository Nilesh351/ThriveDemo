package com.thrive.ui.page.category;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.pojos.CategoryDetails;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class ManageCategoryPage extends BaseTestPage{
	
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
		shortWait();
		return By.xpath(".//span[text()='"+category+"']/../..//following-sibling::div/span/img");
	}
	
	private By getTopicEdit(String topic) {
		shortWait();
		return By.xpath(".//span[text()='"+topic+"']/../..//following-sibling::div/span/img");
	}
	
	private By getExpertiseEdit(String expertise) {
		shortWait();
		return By.xpath(".//span[text()='"+expertise+"']/../../..//following-sibling::div/span/img");
	}
	
	private By getDisableAllLink(String category) {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath("//span[text()='"+category+"']/..//following-sibling::div//span[text()='Disable all']");
		} else {
			return By.xpath("//span[text()='"+category+"']/..//following-sibling::div//span[text()='Tout désactiver']");
		}
	}
	
	private By getEnableAllLink(String category) {
	if(Config.getLocalizationLanguage().contains("en")) {
		    return By.xpath("//span[text()='"+category+"']/../..//following-sibling::div//span[text()='Enable all']");
		} else {
			return By.xpath("//span[contains(text(),'"+category+"')]/../..//following-sibling::div//span[contains(text(),'Tout activer')]");
		}
	}	
	public ManageCategoryPage clickAdd() {
		LOGGER.info("Clicking Add button");
		click(getAddButton());
		return this;
	}
	
	public AddCategoryPage AddCategoryType(String type) {
		LOGGER.info("Adding category type");
		click(getCateggoryType(type));
		return new AddCategoryPage();
	}
	
	
	public ManageCategoryPage clickDisableCategoryLink(String catName) {
		LOGGER.info("Clicking disable category link");
		click(getDisableCategoryLink(catName));
		return this;
	}
	
	public ManageCategoryPage clickDisableTopicLink(String topicName) {
		LOGGER.info("Clicking disable topic link");
		click(getDisableTopic(topicName));
		return this;
	}
	
	public ManageCategoryPage clickCategoryCollapseElement(CategoryDetails categoryDetailsame) {
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
	
	public ManageCategoryPage clickTopicCollapseElement(CategoryDetails categoryDetailsame) {
		LOGGER.info("Clicking topic collapse element");
		if(Config.getLocalizationLanguage().contains("en")) {
			click(getCategoryCollapseElement(categoryDetailsame.getTopic()));
			} else {
				click(getCategoryCollapseElement(categoryDetailsame.getTopicFr()));
			}	
		return this;
	}
	
	public ManageCategoryPage clickCategoryToggle(CategoryDetails categoryDetailsame) {
		LOGGER.info("Clicking category toggle");
		if(Config.getLocalizationLanguage().contains("en")) {
			click(getCategoryCollapseElement(categoryDetailsame.getExpertise()));
			} else {
				click(getCategoryCollapseElement(categoryDetailsame.getExpertiseFr()));
			}		
		return this;
	}
	
	
	public ManageCategoryPage clickTopicToggle(String Name) {
		LOGGER.info("Clicking topic toggle");
		click(getTopicToggle(Name));
		return this;
	}
	
	public ManageCategoryPage clickExpertiseToggle(String expertiseName) {
		LOGGER.info("Clicking expertiuse toggle");
		click(getExpertiseToggle(expertiseName));
		return this;
	}
	
	public AddCategoryPage  clickCategoryEdit(CategoryDetails categoryDetails) {
		LOGGER.info("Clicking edit category link");
		shortWait();
		if(Config.getLocalizationLanguage().contains("en")) {
		    click(getTopicEdit(categoryDetails.getCategory()));
		} else {
			click(getTopicEdit(categoryDetails.getCategoryFr())); 
		}		
		return new AddCategoryPage();
	}
	
	public AddCategoryPage clickTopicEdit(CategoryDetails categoryDetails) {
		LOGGER.info("Clicking edit topic link");
		shortWait();
		if(Config.getLocalizationLanguage().contains("en")) {
		    click(getTopicEdit(categoryDetails.getTopic()));
		} else {
			click(getTopicEdit(categoryDetails.getTopicFr())); 
		}
		return new AddCategoryPage();
	}
	
	public AddCategoryPage clickExpertiseEdit(CategoryDetails categoryDetails) {
		LOGGER.info("Clicking edit expertise link");
		shortWait();
		if(Config.getLocalizationLanguage().contains("en")) {
		    click(getTopicEdit(categoryDetails.getExpertise()));
		} else {
			click(getTopicEdit(categoryDetails.getExpertiseFr())); 
		}
		return new AddCategoryPage();
	}
	
	
	
	public ManageCategoryPage enableOrDisableCategories(boolean isEnable, List<String> categories) {
		LOGGER.info("Clicking Enable/Disable categories link");		
		for(String category : categories) {
			String expectedCategory="";
			if(Config.getLocalizationLanguage().contains("en")) {
				expectedCategory=category;
			} else {
				expectedCategory=DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(category));
			}
			if(isEnable) {
				//Short wait is needed as app is loading the enable link for a seconds in the dom
				shortWait();
				if(isElementVisible(2, getEnableAllLink(expectedCategory))) {
					click(getEnableAllLink(expectedCategory));
				} else {
					click(getDisableAllLink(expectedCategory));
					click(getEnableAllLink(expectedCategory));
				}
			}
				
				else {
					//Short wait is needed as app is loading the disable link for a seconds in the dom
					shortWait();
					if(isElementVisible(2, getDisableAllLink(expectedCategory))) {
						click(getDisableAllLink(expectedCategory));
					} else {
						click(getEnableAllLink(expectedCategory));
						click(getDisableAllLink(expectedCategory));
					}
				}
			}	
		return this;
	}
	
	
	public ManageCategoryPage validateEnableOrDisableCategories(boolean isEnable, List<String> categories) {
		LOGGER.info("Validating Enable/Disable categories");
		for(String category : categories) {
			String expectedCategory="";
			if(Config.getLocalizationLanguage().contains("en")) {
				expectedCategory=category;
			} else {
				expectedCategory=DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(category));
			}
			if(isEnable) {
				Assert.assertTrue(isElementPresent(getDisableAllLink(expectedCategory)));
			} else {
				Assert.assertTrue(isElementPresent(getEnableAllLink(expectedCategory)));
				}
			}
			
		return this;
	}
	
	
	public ManageCategoryPage validateCategory(CategoryDetails categoryDetails) {
		LOGGER.info("Validating category");
		if(Config.getLocalizationLanguage().contains("en")) {
		    Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getCategory())));
		} else {
			Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getCategoryFr()))); 
		}
		return this;
	}
	
	public ManageCategoryPage validateTopic(CategoryDetails categoryDetails) {
		LOGGER.info("Validating topic");
		if(Config.getLocalizationLanguage().contains("en")) {
		    Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getTopic())));
		} else {
			Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getTopicFr()))); 
		}		return this;
	}
	
	public ManageCategoryPage validateExpertise(CategoryDetails categoryDetails) {
		LOGGER.info("Validating expertise");
		if(Config.getLocalizationLanguage().contains("en")) {
		    Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getExpertise())));
		} else {
			Assert.assertTrue(isElementPresent(getCategoryname(categoryDetails.getExpertiseFr()))); 
		}		return this;
	}
	
	public ManageCategoryPage validateAddButton() {
		LOGGER.info("Validating add button");
		Assert.assertTrue(isElementPresent(getAddButton()));
		return this;
	}
	

}
