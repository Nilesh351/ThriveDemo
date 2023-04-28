package com.thrive.ui.page.common;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.testng.Assert;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterCoachCorporateExpPage;
import com.thrive.ui.page.register.RegisterCoachEmploymentPage;
import com.thrive.ui.page.register.RegisterCoachMentoringExpPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterCoachProfileForClients;
import com.thrive.ui.page.register.RegisterCoachSkillsAndQualificationPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;

public class MailboxPage extends BaseTestPage {
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	RegisterCoachEmploymentPage registerCoachEmploymentPage;
	RegisterCoachSkillsAndQualificationPage registerCoachSkillsAndQualificationPage;
	RegisterCoachCorporateExpPage registerCoachCorporateExpPage;
	RegisterCoachMentoringExpPage registerCoachMentoringExpPage;
	RegisterCoachProfileForClients registerCoachAboutPage;

	private By getEmailAddressInput() {
		return By.xpath(".//span[contains(text(),'maildrop.cc')]/preceding-sibling::input");
	}

	private By getYahooUserNameInput() {
		return By.xpath(".//input[@name='username']");
	}

	private By getYahooEmailAddressInput() {
		return By.xpath(".//input[@name='login']");
	}

	private By getYahooNextButton() {
		return By.xpath(".//input[@name='signin']");
	}

	private By getYahooPasswordInput() {
		return By.xpath(".//input[@name='password']");
	}

	private By getYahooLoginButton() {
		return By.xpath(".//button[@id='btnLogin']");
	}

	private By getYahooSearchInput() {
		return By.xpath(".//input[@role='combobox']");
	}

	private By getYahooAdvamceSearchToggle() {
// return By.xpath(".//button[@title='Toggle advanced search pane']");
		return By.xpath(".//div[@data-test-id='advanced-search-button']");
	}

	private By getYahooAdvanceSearchSubjectInput() {
		return By.xpath(".//input[@id='adv-search-subject-input']");
	}

	private By getYahooAdvanceSearchKeywordInput() {
		return By.xpath(".//input[@id='adv-search-keyword-input']");
	}

	private By getYahooAdvanceSearchToInput() {
		return By.xpath(".//span[text()='To']/..//input[contains(@class, 'select-input')]");
	}

	private By getYahooSearchButton() {
		return By.xpath(".//button[@data-test-id='search-basic-btn']");
	}

	private By getYahooAdvancedSearchButton() {
		return By.xpath(".//button[@data-test-id='adv-search-search-btn']");
	}

	private By getYahooSearchedMailElement() {
		return By.xpath(".//span[@data-test-id = 'message-subject']");
	}

	private By getViewInboxButton() {
		return By.xpath(".//span[contains(text(),'View Inbox')]/..");
	}

	private By getReloadButton() {
		return By.xpath(".//span[text()='Reload']");
	}

	private By getEmailElement(String emailSubject) {
		return By.xpath(".//span[contains(text(), '" + emailSubject + "')]");
	}

	private By getMessageContainerIframe() {
		return By.xpath(".//iframe[@class='messagedata-iframe']");
	}

	private By getClickableLink(String link) {
		return By.xpath(".//a[text()='" + link + "']");
	}

	private By getClickableLink() {
		return By.xpath(".//a[text()='this link']");
	}

	private By getClickableLinkRm() {
		return By.xpath(".//font[text()='this link']/..");
	}

	private By getClickSetUpYourMyThriveAccount() {
		return By.xpath(".//font[contains(text(),'your MyThrive Account')]");
	}

	private By getVerifyNowButton() {
		return By.xpath(".//span[contains(text(),'VERIFY')]");
//return By.xpath(".//a[@rel='nofollow noopener noreferrer']");
	}

	private By getClickSetUpYourMyThriveAccountForCoach() {
		return By.xpath(".//span[contains(text(),'your MyThrive Account')]");
	}

	private By getApproveYourNewCoach() {
		return By.xpath(".//span[text()='Approve your new coach']");
	}

	private By getFirstNameInput() {
		return By.xpath(".//label[text()='First Name ']/following-sibling::input");
	}

	private By getEmailPresentcoach(String firstname) {
		return By.xpath(".//font[contains(text(),'" + firstname + "')]");
	}

	private By getLoginButton() {
		return By.xpath(".//span[text()='LOG IN']");
	}

	public ThriveLoginPage clickLogin() {
		click(getLoginButton());
		return new ThriveLoginPage();
	}

	public MailboxPage searchMailDropAndClickOnLink(String searchInput, String emailSubject) {
		LOGGER.info("Searching mail dropbox link and clicking on it");
		int counter = 0;
		launchMailApp(Config.getMaildropURL());
		setValue(searchInput, getEmailAddressInput());
		click(getViewInboxButton());
		while (!isElementVisible(1, getEmailElement(emailSubject)) && counter <= 10) {
			click(getReloadButton());
			counter++;
		}
		click(getEmailElement(emailSubject));
		switchToFrame(getMessageContainerIframe());
		Set<String> beforeWindows = getAllBrowserTabs();
		controlClick(getClickableLink());
		switchToDefaultFromFrame();
		switchToNewlyCreatedTab(beforeWindows);
		return this;
	}

	private By getClickableLinkWithoutText() {
		return By.xpath(
				"//td[contains(@class,'mobile-padding')]//a[@rel ='nofollow noopener noreferrer' and @target='_blank'][1]");
	}

	public MailboxPage searchYahooMailAndClickLink(String searchInput, String emailSubject) {
		LOGGER.info("Searching Yahoo mail and clicking link");
		if (Config.getLocalizationLanguage().contains("en")) {
			SearchYahooEmail(searchInput, emailSubject);
		} else {
			SearchYahooEmail(searchInput, emailSubject);
		}
		Set<String> beforeWindows = getAllBrowserTabs();
// click(getClickableLink());
		findElement(getClickableLinkWithoutText()).click();
		switchToNewlyCreatedTab(beforeWindows);
		return this;
	}

	public MailboxPage searchYahooMailAndClickLinkRm(String searchInput, String emailSubject) {
		LOGGER.info("Searching Yahoo mail and clicking link");
		SearchYahooEmail(searchInput, emailSubject);
		Set<String> beforeWindows = getAllBrowserTabs();
// click(getClickableLinkRm());
		findElement(getClickableLinkWithoutText()).click();
		switchToNewlyCreatedTab(beforeWindows);
		return this;
	}

	public MailboxPage searchYahooMail(String searchInput, String emailSubject) {
		SearchYahooEmail(searchInput, emailSubject);
		return this;
	}

	public MailboxPage searchAMMailAndClick(String searchInput) {
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			searchYahooMailAndClickButton(searchInput,
					ThriveAppSharedData.ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT.getValue());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMailAndClickLink(searchInput,
						ThriveAppSharedData.ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT_RM.getValue());
			} else {
				searchYahooMailAndClickLink(searchInput,
						ThriveAppSharedData.ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT_FRENCH.getValue());
			}
		}
		return this;
	}

	public MailboxPage searchVerifiedMailAndClick(String searchInput) {
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			searchVerifyYahooMailAndClickButton(searchInput, ThriveAppSharedData.VERIFY_EMAIL_SUBJECT.getValue());
		} else {
			searchYahooMailAndClickLinkRm(searchInput, ThriveAppSharedData.VERIFY_EMAIL_SUBJECT_RM.getValue());
		}
		return this;
	}

	public MailboxPage searchClientMailAndClick(String searchInput) {
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			searchYahooMailAndClickButton(searchInput, ThriveAppSharedData.CLIENT_WELCOME_EMAIL.getValue());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMailAndClickLinkRm(searchInput,
						ThriveAppSharedData.CLIENT_WELCOME_EMAIL_SUBJECT_RM.getValue());
			} else {
				searchYahooMailAndClickLinkRm(searchInput,
						ThriveAppSharedData.CLIENT_WELCOME_EMAIL_SUBJECT_RM_FRENCH.getValue());
			}
		}
		return this;
	}

	public MailboxPage searchEnterpriseMailAndClick(String searchInput) {
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			searchYahooMailAndClickLink(searchInput, ThriveAppSharedData.ENT_WELCOME_EMAIL_SUBJECT.getValue());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMailAndClickLinkRm(searchInput, ThriveAppSharedData.ENT_WELCOME_EMAIL_SUBJECT_RM.getValue());
			} else {
				searchYahooMailAndClickLinkRm(searchInput,
						ThriveAppSharedData.CLIENT_WELCOME_EMAIL_SUBJECT_RM_FRENCH.getValue());
			}
		}
		return this;
	}

	public MailboxPage searchYahooMailAndClickButton(String searchInput, String emailSubject) {
		LOGGER.info("Searching Yahoo mail and clicking button");
		SearchYahooEmail(searchInput, emailSubject);
		Set<String> beforeWindows = getAllBrowserTabs();
		click(getClickSetUpYourMyThriveAccount());
//findElement(getClickableLinkWithoutText()).click();
		switchToNewlyCreatedTab(beforeWindows);
		return this;
	}

	public MailboxPage searchVerifyYahooMailAndClickButton(String searchInput, String emailSubject) {
		LOGGER.info("Searching verify Yahoo mail and clicking button");
		SearchYahooEmail(searchInput, emailSubject);
		Set<String> beforeWindows = getAllBrowserTabs();
		click(getVerifyNowButton());
		switchToNewlyCreatedTab(beforeWindows);
		return this;
	}

	public MailboxPage searchInCoachYahooMailAndClickButton(String searchInput, String emailSubject) {
		LOGGER.info("Searching Yahoo mail and clicking button");
		SearchYahooEmail(searchInput, emailSubject);
		Set<String> beforeWindows = getAllBrowserTabs();
		click(getClickSetUpYourMyThriveAccountForCoach());
		switchToNewlyCreatedTab(beforeWindows);
		return this;
	}

	private By getResetButton() {
		return By.xpath(".//span[text()='Reset']");
	}

	public MailboxPage searchYahooMailAndClickResetButton(String searchInput, String emailSubject) {
		LOGGER.info("Searching Yahoo mail and clicking button");
		SearchYahooEmail(searchInput, emailSubject);
		Set<String> beforeWindows = getAllBrowserTabs();
		click(getResetButton());
		switchToNewlyCreatedTab(beforeWindows);
		return this;
	}

	public MailboxPage searchResetPasswordMailAndClick(String searchInput) {
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			searchYahooMailAndClickResetButton(searchInput, ThriveAppSharedData.RESET_PASSWORD.getValue());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMailAndClickLinkRm(searchInput, ThriveAppSharedData.RESET_PASSWORD_RM.getValue());
			} else {
				searchYahooMailAndClickLinkRm(searchInput, ThriveAppSharedData.RESET_PASSWORD_RM_FRENCH.getValue());
			}
		}
		return this;
	}

	public MailboxPage searchCoachMailAndClick(String searchInput) {
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			searchInCoachYahooMailAndClickButton(searchInput,
					ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT.getValue());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMailAndClickLinkRm(searchInput,
						ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT_RM.getValue());
			} else {
				searchYahooMailAndClickLinkRm(searchInput,
						ThriveAppSharedData.CLIENT_WELCOME_EMAIL_SUBJECT_RM_FRENCH.getValue());
			}
		}
		return this;
	}

	public MailboxPage searchInternalCoachMailAndClick(String searchInput) {
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			searchYahooMailAndClickButton(searchInput, ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT.getValue());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMailAndClickLinkRm(searchInput,
						ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT_RM.getValue());
			} else {
				searchYahooMailAndClickLinkRm(searchInput,
						ThriveAppSharedData.CLIENT_WELCOME_EMAIL_SUBJECT_RM_FRENCH.getValue());
			}
		}
		return this;
	}

	public ThriveLoginPage searchYahooMailAndClickLogin(String searchInput, String emailSubject) {
		LOGGER.info("Searching Yahoo mail and clicking login");
		SearchYahooEmail(searchInput, emailSubject);
		Set<String> beforeWindows = getAllBrowserTabs();
		click(getApproveYourNewCoach());
		switchToNewlyCreatedTab(beforeWindows);
		return new ThriveLoginPage();
	}

	public MailboxPage SearchYahooEmail(String searchInput, String emailSubject) {
		searchInput = "\"" + searchInput + "\"";
		launchMailApp(Config.getYahooMailURL());
		setValue(Config.getYahooMailUserName(), getYahooEmailAddressInput());
		setValue(Config.getYahooMailPassword(), getYahooPasswordInput());
		click(getYahooLoginButton());
		mediumWait();
		click(getYahooAdvamceSearchToggle());
		setValue(searchInput, getYahooAdvanceSearchKeywordInput());
		setValue(emailSubject, getYahooAdvanceSearchSubjectInput());
		click(getYahooAdvancedSearchButton());
		mediumWait();
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getYahooSearchedMailElement());
				break;
			} catch (Exception e) {
				if (e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while (flag && counter <= 5);
		return this;
	}

	private By getEnterpriseDetailsElement() {
		return By.xpath("//h2[contains(text(),'Step 1 - Enterprise Details ')]");
	}

	public MailboxPage validateRegistrationPageIsVisibleForEnterprise() {
		LOGGER.info("Validate registration page is visible.");
		Assert.assertTrue(isElementVisible(getEnterpriseDetailsElement()), "Registration page is not visible.");
		return this;
	}

	private By getEmailPresent(String firstName) {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//td[normalize-space(text())='Hello " + firstName + ",']");
		} else {
			return By.xpath(".//td[normalize-space(text())='Bonjour " + firstName + ",']");
		}
	}

	private By getResetEmailPresent(String firstName) {
		return By.xpath(".//td[normalize-space(text())='Hi " + firstName + ",']");
	}

	public MailboxPage validateEmailPresent(String firstName) {
		LOGGER.info("Validate email present");
		Assert.assertTrue(isElementPresent(getEmailPresent(firstName)));
		return this;
	}

	public MailboxPage validateEmailPresentForCoach(String firstname) {
		LOGGER.info("Validate email present for coach");
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			SearchYahooEmail(firstname, ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT.getValue());
			Assert.assertTrue(isElementPresent(getEmailPresentcoach(firstname)));
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			searchYahooMail(firstname, ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT_RM.getValue());
			Assert.assertTrue(isElementPresent(getEmailPresent(firstname)));
		}
		return this;
	}

	public MailboxPage validateEmailPresentForResetPassword(String firstname) {
		LOGGER.info("Validate email present for coach");
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			searchYahooMail(firstname, ThriveAppSharedData.RESET_PASSWORD.getValue());
			Assert.assertTrue(isElementPresent(getResetEmailPresent(firstname)));
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMail(firstname, ThriveAppSharedData.RESET_PASSWORD_RM.getValue());
			} else {
				searchYahooMail(firstname, ThriveAppSharedData.RESET_PASSWORD_RM_FRENCH.getValue());
			}
			Assert.assertTrue(isElementPresent(getEmailPresent(firstname)));
		}
		return this;
	}

	public MailboxPage validateEmailPresentForClient(String firstname) {
		LOGGER.info("Validate email present for coach");
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			SearchYahooEmail(firstname, ThriveAppSharedData.CLIENT_WELCOME_EMAIL.getValue());
			Assert.assertTrue(isElementPresent(getEmailPresent(firstname)));
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMail(firstname, ThriveAppSharedData.CLIENT_WELCOME_EMAIL_SUBJECT_RM.getValue());
			} else {
				searchYahooMail(firstname, ThriveAppSharedData.CLIENT_WELCOME_EMAIL_SUBJECT_RM_FRENCH.getValue());
			}
			Assert.assertTrue(isElementPresent(getEmailPresent(firstname)));
		}
		return this;
	}

	public MailboxPage validateEmailPresentForAM(String firstname) {
		LOGGER.info("Validate email present for coach");
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			SearchYahooEmail(firstname, ThriveAppSharedData.ACCOUNT_MANAGER_WELCOME_EMAIL_SUBJECT.getValue());
			Assert.assertTrue(isElementPresent(getEmailPresent(firstname)));
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			if (Config.getLocalizationLanguage().contains("en")) {
				searchYahooMail(firstname, ThriveAppSharedData.ACCOUNT_MGR_WELCOME_MESSAGE.getValue());
			} else {
				searchYahooMail(firstname, ThriveAppSharedData.ACCOUNT_MGR_WELCOME_MESSAGE_FRENCH.getValue());
			}
			Assert.assertTrue(isElementPresent(getEmailPresent(firstname)));
		}
		return this;
	}

	private By getPersonalDetailsElemtnt() {
		return By.xpath("//h2[contains(text(),'Personal Details')]");
	}

	public MailboxPage validateRegistrationPageIsVisibleForAm() {
		LOGGER.info("Validate registration page is visible.");
		Assert.assertTrue(isElementVisible(getPersonalDetailsElemtnt()), "Registration page is not visible.");
		return this;
	}

	public MailboxPage validateRegistrationPageIsVisibleForClient() {
		LOGGER.info("Validate registration page is visible.");
		Assert.assertTrue(isElementVisible(getPersonalDetailsElemtnt()), "Registration page is not visible.");
		return this;
	}

	public ClientsPage registerClient() {
		LOGGER.info("Registering client by submitting details");
		empStatusPage = new RegisterClientInformationPage()
				.submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());
		careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());
		careerDetailsPage.registerClientInfo()
				.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());
		return new ClientsPage();
	}

	public GlobalCoachesPage registerGlobalCaoch() {
		LOGGER.info("Registering global coach by submitting details");
		String yearsascoach = "12";
		new RegisterCoachPersonalDetailsPage()
				.submitCoachPersonalDetails(TestDataGenerator.generateGlobalCoachPersonalDetails());
// if(Config.getTestPlatform().contains(TestPlatform.THRIVE)) {
// registerCoachSkillsAndQualificationPage = new RegisterCoachEmploymentPage().submitCoachEmploymentDetails(TestDataGenerator.generateCoachEmploymentDetails());
// registerCoachCorporateExpPage = registerCoachSkillsAndQualificationPage.submitCoachSkillAndQualificationDetails(TestDataGenerator.generateGlobalCoachSkillAndQualificationDetails());
// registerCoachMentoringExpPage = registerCoachCorporateExpPage.submitCoachCorporateExpDetails(TestDataGenerator.generateCoachCorporateDetailsDetails());
// } else {
// new RegisterCoachLanguagesPageRm().submitLanguagesDetails(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
// }
		registerCoachAboutPage = new RegisterCoachMentoringExpPage()
				.submitCoachingExpDetails(TestDataGenerator.generateCoachingExpDetails(true));
		registerCoachAboutPage.submitCoachAboutInfo(TestDataGenerator.generateCoachAboutDetails(), yearsascoach);
		return new GlobalCoachesPage();
	}
	
	public MailboxPage validateEmailPresentCoach(String coachName) {
		LOGGER.info("Validating email present for coach");
		shortWait();
		Assert.assertTrue(isElementVisible(getEmailPresentcoach(coachName)),"Email notification not received");
		return this;
	}
	
	public MailboxPage validateEmailPresentClient(String clientName) {
		LOGGER.info("Validating email present for client");
        shortWait();
		Assert.assertTrue(isElementVisible(getEmailPresentcoach(clientName)),"Email notification not received");
		return this;
	}
}
