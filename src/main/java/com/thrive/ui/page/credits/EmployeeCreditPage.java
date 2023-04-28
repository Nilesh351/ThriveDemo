package com.thrive.ui.page.credits;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class EmployeeCreditPage extends BaseTestPage{
	
	private By getEmployeeCreditCount() {
		return By.xpath(".//div[contains(@class,'mycredit mycredits-background')]/div/div[1]");
	}
	
	
	public int employeeCreditCount() {
		LOGGER.info("Credit count available");
		int curCredit = 0;
		
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			String credits = getText(getEmployeeCreditCount());
			String currentCredits = credits.split(":")[1].trim();
			curCredit = Integer.parseInt(currentCredits);
			
		} else if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			String credits = getText(getEmployeeCreditCount());
			String currentCredits = credits.split(":")[1].trim();
			curCredit = Integer.parseInt(currentCredits);
		}
		return curCredit;
	}
	
	public EmployeeCreditPage validateAssignedCreditsReflected(int current,int updated,String credits) {
		LOGGER.info("Validate Assigned credits reflected");
		int amountCredit = Integer.parseInt(credits);
		int modCredit = amountCredit + current;
		Assert.assertEquals(modCredit, updated,"credits are not updated");
		return this;
	}
	
	public EmployeeCreditPage validateRemovedCreditsReflected(int current,int updated,String credits) {
		LOGGER.info("Validate removed credits reflected");
		int amountCredit = Integer.parseInt(credits);
		int modCredit = current - amountCredit;
		Assert.assertEquals(modCredit, updated,"credits are not updated");
		return this;
	}

}