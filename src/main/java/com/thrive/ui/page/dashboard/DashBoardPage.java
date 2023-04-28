package com.thrive.ui.page.dashboard;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class DashBoardPage extends BaseTestPage{
	
	
	private By getPlatformInsightLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(
					".//ul[@class='dropdown-menu menu-poup display-block']//li[normalize-space(text())='PLATFORM INSIGHTS']");
		} else {
			return By.xpath(
					".//li[contains(@routerlink,'platform-insights') and contains(@class,'session-li active link-underline')]");
		}

	}
	
	private By getSessionInsightLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(".//ul[@class='dropdown-menu menu-poup display-block']//li[text()='SESSION	INSIGHTS']");
		}else {
			return By.xpath(".//li[contains(@routerlink,'session-insights') and contains(@class,'session-li ng-star-inserted')]");
		}	
	}
	
	private By getThriveInsightLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(".//ul[@class='dropdown-menu menu-poup display-block']//li[text()='THRIVE INSIGHTS']");
		}else {
			return By.xpath(".//li[contains(@routerlink,'thrive-insights') and contains(@class,'session-li ng-star-inserted')]");
		}
	}
	
	public PlatformInsightPage clickPlatformInsightLink() {
		LOGGER.info("Clicking platform insight link");
		click(getPlatformInsightLink());
		return new PlatformInsightPage();
	}
	
	
	public ThriveInsightPage clickThriveInsightPage() {
		LOGGER.info("Clicking Thrive insight link");
		click(getThriveInsightLink());
		return new ThriveInsightPage();
	}
	
	
	public DashBoardPage validateInsightTabsForSA() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getPlatformInsightLink()));
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			softAssert.assertTrue(isElementPresent(getSessionInsightLink()));
			softAssert.assertTrue(isElementPresent(getThriveInsightLink()));
		}
		softAssert.assertAll();
		return this;
	}
	
	public DashBoardPage validateInsightTabsForAM() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getPlatformInsightLink()));
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			softAssert.assertTrue(isElementPresent(getSessionInsightLink()));
		}
		softAssert.assertAll();
		return this;
	}
	
	
	public DashBoardPage validateInsightTabsForClient() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementNotVisible(getPlatformInsightLink()),"platform insight present to client");
		softAssert.assertTrue(isElementNotVisible(getSessionInsightLink()),"session insight present for client");
		softAssert.assertTrue(isElementNotVisible(getThriveInsightLink()),"thirve insight present for client");
		softAssert.assertAll();
		return this;
	}

}
