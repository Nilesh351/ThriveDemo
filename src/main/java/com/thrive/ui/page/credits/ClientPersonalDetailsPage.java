package com.thrive.ui.page.credits;

import org.openqa.selenium.By;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class ClientPersonalDetailsPage extends BaseTestPage {

	private By getCreditsLink() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText("//li[contains(text(),'admin.clients.client.step.credits')]"));
		} else {
			return By.xpath("//form//ul//li[3]");
		}
	}

	public ClientsCreditPage clickCredit() {
		LOGGER.info("Clicking Credit link");
		shortWait();
		click(getCreditsLink());
		return new ClientsCreditPage();
	}
}
