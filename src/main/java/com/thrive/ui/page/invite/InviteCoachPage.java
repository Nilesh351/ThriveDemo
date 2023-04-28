package com.thrive.ui.page.invite;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;

public class InviteCoachPage extends UserManagementCommonPage {
	
	InvitedGlobalCoach invitedGlobalCoach;

	
	private By getEnterpriseDropdown() {
		return By.xpath(".//ng-select[@name='enterprise']");
	}
	
	private By getEnterpriseOptionElement(String enterprise) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+enterprise+"']");
	}
	
	private By getLanguageDropdown() {
		return By.xpath(".//ng-select[@name='email_language']");
	}
	
	private By getLanguageOptionElement(String language) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+language+"']");
	}
	
	private By getCategoryDropdown() {
		return By.xpath(".//ng-select[@name='category']");
	}
	
	private By getCategoryOptionElement(String category) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+category+"']");
	}
	
	private By getFirstNameInput() {
		return By.xpath(".//input[@name='first_Name']");
	}
	
	private By getLastNameInput() {
		return By.xpath(".//input[@name='last_Name']");
	}
	
	private By getEmailAddressInput() {
		return By.xpath(".//input[@name='userEmail']");
	}
	
	private By getSendInvitationButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.coaches.invite.send_invitation']"));
	}
	
	private By getEmailLanguageDropdown() {
		return By.xpath("//label[@for='email_language']//following-sibling::ng-select");
	}
	
	private By getEmailLanguageOption(String language) {
		return By.xpath("//label[@for='email_language']/..//ng-dropdown-panel//span[contains(text(),'"+language+"')]");
	}
	
	private By getBulkInviteButton() {
		return By.xpath(".//button[text()='Bulk Invite']");
	}
	
	public BulkInviteCoachPage clickBulkInviteButton() {
		LOGGER.info("click Bulk Invite button");
		click(getBulkInviteButton());
		return new BulkInviteCoachPage();
	}
	
	public InviteCoachPage setCoachEmail(String email) {
		LOGGER.info("provide global coach email address");
		setValue(email, getEmailAddressInput());
		return this;
	}
	
	public InviteCoachPage clickSendInvitationButtuon() {
		LOGGER.info("Clicking send invitation button");
		click(getSendInvitationButton());
		return this;
	}
	
	public InviteCoachPage selectLanguage(String language) {
		LOGGER.info("Selecting language");
		click(getLanguageDropdown());
		click(getLanguageOptionElement(language));
		return this;
	}
	
	public InviteCoachPage selectEnterprise(String language) {
		LOGGER.info("Selecting enterprise");
		click(getEnterpriseDropdown());
		click(getEnterpriseOptionElement(language));
		return this;
	}
	
	public InviteCoachPage selectCategories(List<String> categories) {
		LOGGER.info("Selecting categories");
		shortWait();
		for(String category : categories) {
			click(getCategoryDropdown());
			click(getCategoryOptionElement(category));
		}
		return this;
	}
	
	public InviteCoachPage selectCategories(String categories) {
		LOGGER.info("Selecting categories");
			click(getCategoryDropdown());
			click(getCategoryOptionElement(categories));
		return this;
	}
	
	public InviteCoachPage sendGlobalCoachInvitation(CoachInviteDetails globalCoachInviteDetails) {
		LOGGER.info("Sending invitation to global coach");
		commonCoachDetails(globalCoachInviteDetails);
		click(getSendInvitationButton());
		return this;
		
	}
	
	public InviteCoachPage sendGlobalCoachInvite(CoachInviteDetails gloablCoachInviteDetails) {
		LOGGER.info("Sending invitation");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			sendGlobalCoachInvitation(gloablCoachInviteDetails);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)){
			if(Config.getLocalizationLanguage().contains("en")) {
			selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
			sendGlobalCoachInvitation(gloablCoachInviteDetails);
			} else {
				selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue());
				sendGlobalCoachInvitation(gloablCoachInviteDetails);
			}
		}	
		return this;
	}
	
		
	private InviteCoachPage sendInternalCoachInvitation(CoachInviteDetails internalCoachInviteDetails) {
		LOGGER.info("Sending invitation to internal coach");
		selectEnterprise(internalCoachInviteDetails.getEnterprise());
		commonCoachDetails(internalCoachInviteDetails);
		return this;
		
	}
	
	private InviteCoachPage sendInternalCoachInvitationEA(CoachInviteDetails internalCoachInviteDetails) {
		LOGGER.info("Sending invitation to internal coach");
		commonCoachDetails(internalCoachInviteDetails);
		return this;
		
	}

	public InviteCoachPage sendInternalCoachInvite(CoachInviteDetails internalCoachInviteDetails) {
		LOGGER.info("Sending invitation");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			sendInternalCoachInvitation(internalCoachInviteDetails);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)){
			if(Config.getLocalizationLanguage().contains("en")) {
				selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
				sendInternalCoachInvitation(internalCoachInviteDetails);
				} else {
					selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue());
					sendInternalCoachInvitation(internalCoachInviteDetails);
				}
		}
		click(getSendInvitationButton());
		return this;
	}
	
	public InviteCoachPage sendInternalCoachInviteEA(CoachInviteDetails internalCoachInviteDetails) {
		LOGGER.info("Sending invitation");
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			sendInternalCoachInvitationEA(internalCoachInviteDetails);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)){
			if(Config.getLocalizationLanguage().contains("en")) {
				selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue());
				sendInternalCoachInvitationEA(internalCoachInviteDetails);
				} else {
					selectLanguage(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue());
					sendInternalCoachInvitationEA(internalCoachInviteDetails);
				}
		}
		click(getSendInvitationButton());
		return this;
	}
	
	
	
	private InviteCoachPage selectEmailLanguage() {
		if(Config.getTestPlatform().contains(TestPlatform.RM)) {
			click(getEmailLanguageDropdown());
			if(Config.getLocalizationLanguage().contains("fr")) {
			click(getEmailLanguageOption(ThriveAppSharedData.LANGUAGE_TYPE_FRENCH.getValue()));
			} else {
				click(getEmailLanguageOption(ThriveAppSharedData.LANGUAGE_TYPE_ENGLISH_US.getValue()));
			}
		}
		return this;
	}
	
	public InviteCoachPage commonCoachDetails(CoachInviteDetails globalCoachInviteDetails) {
		selectEmailLanguage();
		selectCategories(globalCoachInviteDetails.getCategories());
		setValue(globalCoachInviteDetails.getFirstName(), getFirstNameInput());
		setValue(globalCoachInviteDetails.getLastName(), getLastNameInput());
		setValue(globalCoachInviteDetails.getEmailAddress(), getEmailAddressInput());
		return this;
	}
		
	private By getEnterCoachDetailsElementLink() {
		return By.xpath("//p[contains(text(),'admin.coaches.invite.p1')]");
	}

	public void validateInviteCoachPageIsVisible() {
		LOGGER.info("Validate Invite Coach Page is visible");
		Assert.assertTrue(isElementVisible(getEnterCoachDetailsElementLink()),"Invite Coach page is not visible.");
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class, 'toast-close-button')]");
	}
	
	public String validateCoachInviteToaster(String expectedMessage,String email) {
		LOGGER.info("Validating toaster message");
		CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
		String actualMessage = getText(getAlertElement());
		if(!actualMessage.equals(expectedMessage)) {
			click(getToasterCloseButton());
			setCoachEmail(globalCoachInviteDetails.getEmailAddress());
			email = globalCoachInviteDetails.getEmailAddress();
			clickSendInvitationButtuon();
			actualMessage = getText(getAlertElement());
		}
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		click(getToasterCloseButton());
		return email;
	}
	
	public String validateInternalCoachInviteToaster(String expectedMessage,String email) {
		LOGGER.info("Validating internal coach invite toaster message");
		CoachInviteDetails coachInviteDetails = TestDataGenerator.generateCoachInviteDetails(false);
		String actualMessage = getText(getAlertElement());
		if(!actualMessage.equals(expectedMessage)) {
			click(getToasterCloseButton());
			setCoachEmail(coachInviteDetails.getEmailAddress());
			clickSendInvitationButtuon();
			email = coachInviteDetails.getEmailAddress();
			actualMessage = getText(getAlertElement());
		}
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		click(getToasterCloseButton());
		return email;
	}
	
	public InviteCoachPage validateGlobalCoachInvitedSuccessfully(CoachInviteDetails  coachInviteDetails) {
		LOGGER.info("Validating global coach received invitation sucessfully");
		for(int i=0; i<=3;i++){
			  try{
				 new AlertsAndMessagesPage().validateToaster(ThriveAppSharedData.INVITE_INTERNAL_COACH_NOTIFICATION.getValue());
			     break;
			  }
			  catch(Exception e){
			     System.out.println(e.getMessage());
			  }
			}		
		new ThriveHeaderMenuPage().clickUserManagementLink().clickCoachesLink().clickInvitedCoaches();
		invitedGlobalCoach=new InvitedGlobalCoach();
		invitedGlobalCoach.setSearch(coachInviteDetails.getEmailAddress());
		invitedGlobalCoach.validateEmailPresent(coachInviteDetails.getEmailAddress());
		return this;
	}
}