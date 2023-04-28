package com.thrive.ui.page.user_management.search_filter;

import org.openqa.selenium.By;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.category.CoachCategoriesPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class CoachProfilePage extends BaseTestPage {
	
	private By getCoachCategoriesLink() {

		if (Config.getLocalizationLanguage().contains("en")) {
			if(Config.featureFlagStatus()) {
				return By.xpath(getXpathByText(".//span[text()='private.profile.coach.categories_list']"));
			} else {
			    return By.xpath(getXpathByText(".//li[text()='private.profile.coach.categories_list']"));
			}
		} else {
			return By.xpath("//li[contains(text(),'CATÉGORIE DE COACHING')]");
		}

	}

	private By getEnableButton() {
		return By.xpath(getXpathByText(".//a[text()='private.profile.coach.enable']"));
	}
	
	public CoachCategoriesPage clickCoachCategories() {
		LOGGER.info("Clicking coach categories");
		click(getCoachCategoriesLink());
		return new CoachCategoriesPage();
	}
	
	public AlertsAndMessagesPage clickEnable() {
		LOGGER.info("Clicking Enable button");
		click(getEnableButton());
		return new AlertsAndMessagesPage();
	}

}