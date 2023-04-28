package com.thrive.ui.page.user_management.search_filter;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class ClientMenuPage extends UserManagementCommonPage{

	ThriveHeaderMenuPage thriveHeaderMenuPage=new ThriveHeaderMenuPage();

	
	private By getUpdateDetails() {
		return By.xpath(getXpathByText("//button[contains(text(),'private.client.update_details')]"));
	}
	
	private By getAdministratorCheckbox() {
		if(Config.getTestPlatform().contains(TestPlatform.THRIVE)){
		return By.xpath(getXpathByText("//label[contains(text(),'admin.clients.client.administrator')]//span"));
		} else {
			return By.xpath(getXpathByText("//label[contains(text(),'private.client.administrator')]//span"));
		}
	}

	public ClientMenuPage clickAdministrator() {
		LOGGER.info("Clicking Administrator");
		waitUntilElementIsVisible(getAdministratorCheckbox());
		click(getAdministratorCheckbox());
		new AlertsAndMessagesPage().closeToasterAlert();
		return this;
	}
	
	public ClientMenuPage validateClientSetAsEa(InviteEmployeeDetails inviteEmployeeDetails) {
		LOGGER.info("Validate client has been set as enterprise admin successfully");
		thriveHeaderMenuPage.logout();
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
   		LoginDetails loginDetailsClient1 = new LoginDetails(inviteEmployeeDetails.getEmailAddress(),ThriveAppSharedData.COMMON_PAASWORD.getValue());
   		new ThriveLoginPage().loginIgnoreAccept(loginDetailsClient1);
   		if(Config.featureFlagStatus()) {
   			clickSettingsIcon();
   		}
   		if(Config.featureFlagStatus()) {
   			Assert.assertTrue(isElementVisible(thriveHeaderMenuPage.getUsersOptionLink()),"Falied to set client as enterprise admin");
   		} else {
   			Assert.assertTrue(isElementVisible(thriveHeaderMenuPage.getUserManagementHeaderLink()),"Falied to set client as enterprise admin");
   		}
	    return this;
	}
}
