package com.thrive.ui.page.dashboard;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class InsightsPage extends BaseTestPage{
	
	private By getPlatformInsightLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(".//u[text()='Platform Insights']");
		}else {
			return By.xpath(".//li[contains(@routerlink,'platform-insights')]");
		}
	}
	
	
	private By getSessionInsightLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(".//u[text()='Session Insights']");
		} else {
			return By.xpath(".//li[contains(@routerlink,'session-insights')]");
		}
	}
	
	public InsightsPage validateInsightTabsForEA() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getPlatformInsightLink()));
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			softAssert.assertTrue(isElementPresent(getSessionInsightLink()));
		}
		softAssert.assertAll();
		return this;
	}

}
