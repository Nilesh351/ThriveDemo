package com.thrive.ui.page.invite;

import java.util.List;

import org.openqa.selenium.By;

import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.FileUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class BulkInviteCoachPage extends UserManagementCommonPage{
	
	
	private By getCategoryDropdown() {
		return By.xpath(".//ng-select[@name='category']");
	}
	
	private By getCategoryOptionElement(String category) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+category+"']");
	}
	
	private By getSendInvitationButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.coaches.invite.send_invitation']"));
	}
	
	private By getEnterpriseDropdown() {
		return By.xpath(".//ng-select[@name='enterprise']");
	}
	
	private By getEnterpriseOptionElement(String enterprise) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+enterprise+"']");
	}
	
	private By getAttachFileLink() {
		return By.xpath("//input[@id='import']//parent::div//descendant::input[@id='file']");
	}
	
	public BulkInviteCoachPage selectEnterprise(String language) {
		LOGGER.info("Selecting enterprise");
		click(getEnterpriseDropdown());
		click(getEnterpriseOptionElement(language));
		return this;
	}
	
	public BulkInviteCoachPage clickSendInvitationButtuon() {
		LOGGER.info("Clicking send invitation button");
		click(getSendInvitationButton());
		return this;
	}
	
	public BulkInviteCoachPage selectInviteeEmailsFile(String filePath) {
		LOGGER.info("Selecting file containing invitee email addresses");	
		findElement(getAttachFileLink()).sendKeys(filePath);
		shortWait();
		return this;
	}
	
	public BulkInviteCoachPage selectCategories(List<String> categories) {
		LOGGER.info("Selecting categories");
		for(String category : categories) {
			click(getCategoryDropdown());
			click(getCategoryOptionElement(category));
		}
		return this;
	}
	
	private By getCloseButton() {
		return By.xpath(".//button[text()='close']");
	}
	
	public BulkInviteCoachPage sendBulkInviteCaoach(String filePath,CoachInviteDetails gloablCoachInviteDetails) {
		LOGGER.info("send bulk invite coach");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
		}
		selectCategories(gloablCoachInviteDetails.getCategories());
		FileUtils.writeDataAtOnceCoach(filePath);
		selectInviteeEmailsFile(filePath);
		clickSendInvitationButtuon();
		click(getCloseButton());
		return this;
	}
	
	public BulkInviteCoachPage sendBulkInviteInternalCaoach(String filePath, CoachInviteDetails internalCoachInviteDetails) {
		LOGGER.info("send bulk invite coach");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
		}
		selectEnterprise(internalCoachInviteDetails.getEnterprise());
		selectCategories(internalCoachInviteDetails.getCategories());
		FileUtils.writeDataAtOnceInternalCoach(filePath);
		selectInviteeEmailsFile(filePath);
		clickSendInvitationButtuon();
		click(getCloseButton());
		return this;
	}

}
