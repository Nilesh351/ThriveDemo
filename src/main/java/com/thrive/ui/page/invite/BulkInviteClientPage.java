package com.thrive.ui.page.invite;

import org.openqa.selenium.By;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.FileUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class BulkInviteClientPage extends UserManagementCommonPage{
	
	private By getEntepriseDropdown() {
		return By.xpath("//ng-select[@name='enterprise']");	
	}
	
	private By getEntepriseDropdownOptionElement(String enterprise) {
		return By.xpath("//span[contains(@class,'ng-option') and text() = '"+enterprise+"']");	
	}
	
	private By getAttachFileLink() {
		return By.xpath("//input[@id='import']//parent::div//descendant::input[@id='file']");
	}
	
	private By getSendInvitationButton() {
		return By.xpath("//button[@type='submit']");
	}
	
	public BulkInviteClientPage selectEnterprise(String entName) {
		LOGGER.info("Selecting enterprise");
		click(getEntepriseDropdown());
		click(getEntepriseDropdownOptionElement(entName));
		return this;
	}
	
	public BulkInviteClientPage clickSendInvitationButton() {
		LOGGER.info("click send invitation button");
		click(getSendInvitationButton());
		return this;
	}
	
	public BulkInviteClientPage selectInviteeEmailsFile(String filePath) {
		LOGGER.info("Selecting file containing invitee email addresses");	
		findElement(getAttachFileLink()).sendKeys(filePath);
		shortWait();
		return this;
	}
	
	private By getCloseButton() {
		return By.xpath(".//button[text()='close']");
	}
	
	public BulkInviteClientPage sendBulkInvite(String filePath) {
		LOGGER.info("send bulk invite");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
		}
		selectEnterprise(enterprise);
		FileUtils.writeDataAtOnceClient(filePath);
		selectInviteeEmailsFile(filePath);
		clickSendInvitationButton();
		click(getCloseButton());
		return this;
	}
	
	

}
