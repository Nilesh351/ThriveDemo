package com.thrive.ui.page.credits;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class ClientsCreditPage extends BaseTestPage{
	
	
	private By getClientCreditCount() {
		return By.xpath(".//div[@class='mycredit mycredits-background ng-star-inserted']/div/div[1]");
	}
	
	private By getCreditsNotAvailable() {
		return By.xpath(getXpathByText(".//div[normalize-space(text())='private.profile.client.no_credits']"));
	}
	
	
	public int ClientCreditCount() {
		LOGGER.info("Credit count available");
		int curCredit = 0;
		
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			String credits = getText(getClientCreditCount());
			String currentCredits = credits.split(" ")[2];
			curCredit = Integer.parseInt(currentCredits);
			
		} else if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			String credits = getText(getClientCreditCount());
			String currentCredits = credits.split(":")[1];
			curCredit = Integer.parseInt(currentCredits.trim());
		}

		return curCredit;
	}
	
	
	public ClientsCreditPage validateAssignedCreditsReflected(int current,int updated,String credits) {
		LOGGER.info("Validate Assigned credits reflected");
		int amountCredit = Integer.parseInt(credits);
		int modCredit = amountCredit + current;
		Assert.assertEquals(modCredit, updated,"credits are not updated");
		return this;
	}
	
	public ClientsCreditPage validateRemovedCreditsReflected(int current,int updated,String credits) {
		LOGGER.info("Validate removed credits reflected");
		int amountCredit = Integer.parseInt(credits);
		int modCredit = current - amountCredit;
		Assert.assertEquals(modCredit, updated,"credits are not updated");
		return this;
	}
	
	
	
	public ClientsCreditPage validateCreditsNotAvailable() {
		LOGGER.info("Credits are bot available");
		Assert.assertTrue(isElementPresent(getCreditsNotAvailable()));
		return this;
		
	}

}
