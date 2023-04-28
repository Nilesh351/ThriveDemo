package com.thrive.ui.page.personal_details_page;

import org.openqa.selenium.By;

import com.thrive.ui.core.BaseTestPage;

public class ClientsPersonalDetailsPage extends BaseTestPage{

	private By getCreditsTitle() {
		return By.xpath(getXpathByText("//li[contains(text(),'admin.clients.client.step.credits')]"));
	}

	private By getClientCredits() {
		return By.xpath("//div[contains(@class,'mycredits-background')]/div/div[1]");
	}
	
	public ClientsPersonalDetailsPage clickCredits() {
		LOGGER.info("Clicking Credits");
		shortWait();
		click(getCreditsTitle());
		return this;
	}
	
	public int captureClientCredits() {
		LOGGER.info("Capturing client credits");
		String credits = getText(getClientCredits());
		return Integer.parseInt(credits.split(":")[1].trim());
	}
	
}
