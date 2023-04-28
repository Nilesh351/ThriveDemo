package com.thrive.ui.page.common;
import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.FileUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.credits.RemoveCreditsPage;
import com.thrive.ui.page.enterprise.CreateEnterprisePage;
import com.thrive.ui.page.enterprise.EnterpriseDetailsPage;
import com.thrive.ui.page.invite.EnterpriseInviteEmployeePage;
import com.thrive.ui.page.invite.EnterpriseInvitedEmployeePage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;


public class UserManagementCommonPage extends BaseTestPage {
	
	
	String extension = ThriveAppSharedData.EXTENSION.getValue();
	String pdfExtension = ThriveAppSharedData.PDFEXTENSION.getValue();
	
	private By getArchivedLink() {
		return By.xpath("//button[contains(text(),'Archived')]");
	}
	
	private By getCoachFilterOptionElementCommon(String option) {
		return By.xpath("//div[contains(@class,'options ng') and @title='"+option+"']/input");
	}
	
	private By getOtherFilterOptionElementCommon(String option) {
		return By.xpath("//div[contains(@class,'options ng') and @title='"+option+"']/span");
	}
	
	protected By getSearchInput() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//input[@placeholder='Search']");
		} else {
			return By.xpath(".//input[@placeholder='Rechercher']");
		}
	}
	
	//Note - In development environment, the text is Reset Filters
	private By getClearFilterLink() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.users.reset_filter']"));
	}
	
	private By getExportButton() {
		return By.xpath(".//button[text()='EXPORT']");
	}
	
	
	private By getFilterByRoleDropdown() {
		return By.xpath(".//ng-select[@name='filter']//input");
	}
		
	protected By getFilterByRoleDropdownPlaceHolder() {
		return By.xpath(".//ng-select[@name='filter']//div[@class='ng-placeholder']");
	}
	
	private By getRoleFilterOptionElement(String filterOption) {
		return getOtherFilterOptionElementCommon(filterOption);
	}
	
	private By getFilterByEnterpriseDropdown() {
		return By.xpath(".//div[text()='FILTER BY ENTERPRISE']/../../../..//ng-select[@name='filter']//input");
	}
	
	private By getFilterByLanguageDropdown() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.language']/../../../..//ng-select[@id='filter']//input"));
	}
	
	private By getFilterByExtertiseDrodown() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.experties']/../../../..//ng-select[@name='filter']//input"));
	}
	
	private By getFilterByIndustryDropdown() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.coaches.industry']/../../../..//ng-select[@name='filter']//input"));
	}
	
	private By getFilterByRegionDropdown() {
		return By.xpath(getXpathByText(".//div[text()='admin.dashboards.filter_by_region']/../../../..//ng-select[@name='filter']//input"));
	}
	
	private By getFilterByDepartmentDropdown() {
		return By.xpath(getXpathByText(".//div[text()='admin.dashboards.filter_by_department']/../../../..//ng-select[@name='filter']//input"));
	}
	
	private By getSortByDropdown() {
		return By.xpath(getXpathByText(".//div[text()='private.book_session.search.sort_by']/../../../..//ng-select[@name='filter']"));
	}

	private By getSortByOptionElement(String option) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(text(),'"+option+"')]");
	}
	
	private By getActionsDropdown() {
		// return By.xpath(".//button[contains(@class,'action-btn')]");//
		return By.xpath(getXpathByText(".//button[contains(text(),'admin.user_management.actions')]"));
	}

	private By getActionsExportOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.clients.invite.export_btn']"));
	}
	
	private By getActionsInviteEnterpriseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.invite_enterprise']"));
	}

	private By getActionsCreateEnterpriseOption() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.create_enterprise']"));
		} else {
			return By.xpath("//span[contains(text(),'CRÉER UNE ENTREPRISE')]");
		}
	}

	private By getActionsArchiveEnterpriseOption() {
		return By.xpath(".//span[text()='ARCHIVE']");
	}
	
	private By getActionsAllocateCreditsOption() {
		return By.xpath(getXpathByText("//span[contains(text(),'shared.modals.employees_allocate_credits_modal.title')]"));
	}
	
	private By getActionsAllocateCreditsOptionEmployee() {
		return By.xpath(getXpathByText(".//span[contains(text(),'shared.modals.employees_allocate_credits_modal.title')]"));
	}
	
	private By getActionsInviteCoachOption() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.invite_coach')]"));
	}
	
	private By getActionsArchiveCoachOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.archive_coach']"));
	}
	
	private By getActionsDeleteCoachOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.delete_coach']"));
	}
	
	private By getActionsReInviteCoachOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.reinvite_coach']"));
	}
	
	private By getActionsUnarchiveCoachOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.unarchive_coach']"));
	}
	
	private By getInviteClientOption() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.clients.invite_client')]"));
	}
	
	private By getActionsRemoveCreditsOption() {
		return By.xpath(getXpathByText(".//span[text()='private.profile.enterprise.remove']"));
	}
	
	private By getResentUserVerificationOption() {
		return By.xpath(".//span[text()='Resend User Verification']");
	}
	
	private By getArchiveClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.archive_client']"));
	}
	
	private By getDeleteClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.delete_client']"));
	}
	
	private By getUnArchiveClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.unarchive_client']"));
	}
	
	private By getReInviteClientOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.reinvite_client']"));
	}
	
	private By getInviteAccountManagerOptionElement() {
		return By.xpath(
				getXpathByText(".//span[contains(text(),'admin.user_management.acc_manager.invite_acc_manager')]"));
	}
	
	private By getArchiveAccountManagerOptionElement() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.acc_manager.archive_acc_manager')]"));
	}
	
	private By getDeleteAccountManagerOptionElement() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.acc_manager.delete_acc_manager']"));
	}
	
	private By getInviteOptionElementRm() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.invite_coach']"));
	}
	
	private By getReInviteAccountManagerOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.acc_manager.reinvite_acc_manager']"));
	}
	
	private By getEnableAccountManagerOption() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.acc_manager.archive_acc_manager')]"));
	}
	
	private By getReInviteOption() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.reinvite_coach')]"));
		} else {
			return By.xpath(".//span[contains(text(),'RÉINVITER')]");
		}
		}
	
	protected By getUserTableNameElements() {	
		//return By.xpath(".//table[contains(@class,'ui-table-scrollable-body-table')]//td[@id='firstName']//a");
		return By.xpath(".//span[contains(@class,'text-break ng-star-inserted')]//a");
	}
	
	private By getActionsUnarchiveEnterpriseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.unarchive_enterprise']"));
	}
	
	private By getActionsDeleteEnterpriseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.delete_enterprise']"));
	}
	
	private By getCurrentTabSelectedTab(String tab) {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath("//button[not(contains(@class,'btn-outline')) and contains(text(),'"+tab+"')]");
		} else {
			return By.xpath("//button[not(contains(@class,'btn-outline')) and contains(text(),'Invité')]");
		}
	}
	
	public UserManagementCommonPage ValidateCurrentTab(String tab) {
		Assert.assertTrue(isElementPresent(getCurrentTabSelectedTab(tab)));
		return this;
	}
	
	public AlertsAndMessagesPage clickUnArchiveEnterprise() {
		LOGGER.info("Clicking Unarchive Enterprise link");
		click(getActionsUnarchiveEnterpriseOption());
		return new AlertsAndMessagesPage();
	}
	
	private By getEnterprise(String enterprise) {
		return By.xpath("//a[contains(text(),'"+enterprise+"')]");
	}
	
	public UserManagementCommonPage validateEnterpriseNamePresent(String name) {
		LOGGER.info("Validating enterprise name is present");
		Assert.assertTrue(isElementPresent(getEnterprise(name)),"Failed to search enterprise");
		return this;
	}
	
	private By getPaginationElement() {
		return By.xpath(".//span[@class='ui-paginator-pages']/a");
	}
	
	private By getPreviousPage() {
		return By.xpath(".//a[@class='ui-paginator-first ui-paginator-element ui-state-default ui-corner-all']");
	}
	
	private By getPaginationLink(int pageNumber) {
		return By.xpath(".//a[contains(@class,'ui-paginator-') and text()='"+pageNumber+"']");
	}
	
	private By getforwordPages() {
		return By.xpath(".//span[@class='ui-paginator-pages']/a");
	}
	
	private By getActionsReInviteEnterpirseOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.enterprises.reinvite_enterprise']"));
	}
	
	
	
	private By getActionsRemoveEnterpriseOption() {
		return By.xpath(".//span[text()='REMOVE']");
	}
	
	private By clickDownloadButton() {
		LOGGER.info("Clicking download button");
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	private By getCheckedRecordsElement() {
		return By.xpath(".//div[contains(@class, 'ui-chkbox') and contains(@class, 'ui-state-active')]");	
	}
	
	private By getNextpage() {
		return By.xpath(".//a[contains(@class,'ui-paginator-next ui-paginator-element ui-state-default ui-corner-all')]");
	}
	
	private By getInviteLanguagetDropdownRm() {
		LOGGER.info("Get the Dropdown");
		return By.xpath(".//ng-select[@name='email_language']");
	}

	private By getInviteLanguageDropdownOptionElementRm(String language) {
		LOGGER.info("Get the language from  Dropdown");
		return By.xpath(".//span[text()='"+language+"']");
	} 
	
	
	private By getMandatoryFieldErrorMsg() {
		return By.xpath(getXpathByText(".//p[(text())='ui.messages.required_field']"));
	}

	public UserManagementCommonPage validateMandatoryFieldErrormsg() {
		LOGGER.info("Validating error message present if mandatory field value is empty");
		Assert.assertTrue(isElementPresent(getMandatoryFieldErrorMsg()),"Error msg is not present");
		return this;
	}
	
	private By getInvalidEmailValueMsg() {
		return By.xpath(getXpathByText(".//p[(text())='ui.messages.valid_email']"));
	}
	
	public UserManagementCommonPage validateEmailErrorMsg() {
		LOGGER.info("Validating error message present if email value is invalid");
		Assert.assertTrue(isElementPresent(getInvalidEmailValueMsg()),"Error msg is not present");
		return this;
	}
	
	private By getFilterByCoachTypeDropdown() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.filter_training_coach']/../../../..//ng-select[@name='filter']//input"));
	}
	
	private By getFilterByCoachTypeDropdownValue(String coachType) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(text(),'"+coachType+"')]");
	}
	
	public UserManagementCommonPage selectFilterByCoachType(String coachType) {
		LOGGER.info("select coach type value from dropdwon");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getFilterByCoachTypeDropdown());
				click(getFilterByCoachTypeDropdownValue(coachType));
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		return this;
	}
	
	public UserManagementCommonPage selectLanguage(String language) {
		LOGGER.info("select language from language dropdown");
		click(getInviteLanguagetDropdownRm());
		click(getInviteLanguageDropdownOptionElementRm(language));
		return this;
	}
	
	public UserManagementCommonPage clickDownload() {
		LOGGER.info("Clicking download button");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getActionsDropdown());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		click(clickDownloadButton());
		return this;
	}
	
	public UserManagementCommonPage validateThisFieldRequiredErrorMsgPresent() {
		LOGGER.info("validate this field is required error message");
		Assert.assertTrue(isElementPresent(getThisFieldRequiredErrorMsg()));
		return this;
	}
	
	private By getPasswordInvalidErrorMsg() {
		if(Config.getTestPlatform().contains("thrive")) {
		    return By.xpath(getXpathByText(".//p[contains(text(),'private.profile.password.password_policy.error.pattern')]"));
		} else {
			return By.xpath(".//p[contains(text(),'Ne peut pas être votre adresse mail')]");
		}
	}
	
	public UserManagementCommonPage validategetPasswordInvalidErrorMsgPresent() {
		LOGGER.info("validate Password invalid error message");
		Assert.assertTrue(isElementPresent(getPasswordInvalidErrorMsg()));
		return this;
	}
	
	private By getNewPasswordNotSameAsOldErrorMsg() {
		if(Config.getTestPlatform().contains("thrive")) {
			return By.xpath(".//p[text()='Your new password should not be the same as your old password']");
		} else {
			return By.xpath(getXpathByText(".//p[contains(text(),'private.profile.password.password_policy.error.match_old_pass')]"));
		}
		
	}
	
	public UserManagementCommonPage validateNewPasswordNotAsOldPasswordErrorMsgPresent() {
		LOGGER.info("validate old password cannot be same as new password error message");
		Assert.assertTrue(isElementPresent(getNewPasswordNotSameAsOldErrorMsg()));
		return this;
	}
	
	private By getNoCommonWordNumberErrorMessage() {
		return By.xpath(getXpathByText(".//div[normalize-space(text())='private.profile.password.password_policy.error.common_pattern']/i[@class='fa fa-times text-danger']"));
	}
	
	public UserManagementCommonPage validateNotCommonWordNumberErrorMsgPresent() {
		LOGGER.info("validate password not contains common words and numbers error message");
		Assert.assertTrue(isElementPresent(getNoCommonWordNumberErrorMessage()));
		return this;
	}
	
	private By getExistingPasswordIncorrectErrorMessage() {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//p[text()='Sorry, the existing password you have entered is not correct']");
		} else {
			return By.xpath(".//p[contains(text(),'Désolé, le mot de passe existant que vous avez entré n')]");
		}
	}
	
	public UserManagementCommonPage validateExistingPasswordIncorrectErrorMessage() {
		LOGGER.info("validate Existing Password Incorrect error message");
		Assert.assertTrue(isElementPresent(getExistingPasswordIncorrectErrorMessage()));
		return this;
	}
	
	
	private By getMinimumText() {
		return By.xpath(getXpathByText(".//div[contains(text(),'ui.tooltip.password.password_policy.minimum')]"));
	}
	
	private By getCharactersText() {
		return By.xpath(getXpathByText(".//div[contains(text(),'ui.tooltip.password.password_policy.characters')]"));
	}
		
	private By getSmallCaseLetters() {
		return By.xpath(getXpathByText(".//div[contains(text(),'ui.tooltip.password.password_policy.smallcase_character')]"));
	}
	
	private By getUpperCaseLetters() {
		return By.xpath(getXpathByText(".//div[contains(text(),'ui.tooltip.password.password_policy.uppercase_character')]"));
	}
	
	private By getSpecialCaseLetters() {
		return By.xpath(getXpathByText(".//div[contains(text(),'ui.tooltip.password.password_policy.special_character')]"));
	}
	
	private By getNumbersCount() {
		return By.xpath(getXpathByText(".//div[contains(text(),'ui.tooltip.password.password_policy.number')]"));
	}
	
	private By getNoSpaces() {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//div[contains(text(),'no spaces')]");
		} else {
			return By.xpath(".//div[contains(text(),'pas d')]");
		}
	}
	
	private By getCommonPatterns() {
		return By.xpath(getXpathByText(".//div[contains(text(),'private.profile.password.password_policy.error.common_pattern')]"));
	}
	
	public UserManagementCommonPage validatePatternValidationActivation() {
		LOGGER.info("validate password not contains common words and numbers error message");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getMinimumText()));
		softAssert.assertTrue(isElementPresent(getCharactersText()));
		softAssert.assertTrue(isElementPresent(getSmallCaseLetters()));
		softAssert.assertTrue(isElementPresent(getUpperCaseLetters()));
		softAssert.assertTrue(isElementPresent(getSpecialCaseLetters()));
		softAssert.assertTrue(isElementPresent(getNumbersCount()));
		softAssert.assertTrue(isElementPresent(getNoSpaces()));
		softAssert.assertTrue(isElementPresent(getCommonPatterns()));
		softAssert.assertAll();
		return this;
	}
	
	
	
	private By getThisFieldRequiredErrorMsg() {
		return By.xpath(getXpathByText(".//p[contains(text(),'ui.messages.required_field')]"));
	}
	
	public AllocateCreditsPage selectAllocateCreditsAction() {
		LOGGER.info("Clicking Allocate credits link");
		click(getActionsDropdown());
		click(getActionsAllocateCreditsOption());
		return new AllocateCreditsPage();
	}
	
	public AllocateCreditsPage selectAllocateCreditsActionEA() {
		LOGGER.info("Clicking Allocate credits link");
		click(getActionsDropdown());
		click(getActionsAllocateCreditsOptionEmployee());
		return new AllocateCreditsPage();
	}
	
	public UserManagementCommonPage validateRecordsDownloaded() {
		LOGGER.info("Validating records dowloaded successfully");
		Assert.assertTrue(FileUtils.isFileDownloaded(getDownloadedFolderPath,extension));
		FileUtils.delteDownloadedFile(getDownloadedFolderPath,extension);
		return this;
	}
	
	public UserManagementCommonPage validatePlatformRecordsDownloadedPdf() {
		LOGGER.info("Validating records dowloaded successfully in pdf format");	
		Assert.assertTrue(FileUtils.isFileDownloaded(getDownloadedFolderPath, pdfExtension));
		return this;
	}
	
	private By getEmailMismatchErrorMessage() {
		return By.xpath(getXpathByText(".//p[contains(text(),'ui.messages.email_verify_validation')]"));
	}
	
	public UserManagementCommonPage validateEmailUpdateErrorValidation() {
		LOGGER.info("Validate error validation for email update");	
		Assert.assertTrue(isElementPresent(getEmailMismatchErrorMessage()));
		return this;
	}
	
	public UserManagementCommonPage validateDownloadRecords() throws IOException {
		LOGGER.info("Validating Download records works as expected");
		String filename = FileUtils.getFileName();
		int ExpectedCount = findElements(getCheckedRecordsElement()).size();
		int actualCSVCount = FileUtils.getRecordCount(getDownloadedFolderPath+filename);
		Assert.assertEquals(actualCSVCount, ExpectedCount, "Selected and downloaded records does not match");
		return this;
	}
	
	
	public UserManagementCommonPage selectFilterByRole(String filterOption) {
		LOGGER.info("Selecting role");
		click(getFilterByRoleDropdown());
		click(getRoleFilterOptionElement(filterOption));
		return this;
	}
	
	public UserManagementCommonPage selectFilterByEnterprise(String filterOption) {
		LOGGER.info("Selecting enterprise");
		click(getFilterByEnterpriseDropdown());
		click(getOtherFilterOptionElementCommon(filterOption));
		return this;
	}
	
	public UserManagementCommonPage selectFilterByLanguage(List<String> filterOptions) {
		LOGGER.info("Selecting language");
		click(getFilterByLanguageDropdown());
		for(String filterOption : filterOptions) {
			click(getCoachFilterOptionElementCommon(filterOption));
		}
		return this;
	}
	
	public UserManagementCommonPage selectFilterByExpertise(List<String> filterOptions) {
		LOGGER.info("Selecting expertise");
		click(getFilterByExtertiseDrodown());
		for(String filterOption : filterOptions) {
			click(getCoachFilterOptionElementCommon(filterOption));
		}
		return this;
	}
	
	public UserManagementCommonPage selectFilterByIndustry(List<String> filterOptions) {
		LOGGER.info("Selecting industry");
		click(getFilterByIndustryDropdown());
		for(String filterOption : filterOptions) {
			click(getCoachFilterOptionElementCommon(filterOption));
		}
		return this;
	}
	
	public UserManagementCommonPage selectFilterByRegion(List<String> filterOptions) {
		LOGGER.info("Selecting region");
		click(getFilterByRegionDropdown());
		for(String filterOption : filterOptions) {
			click(getOtherFilterOptionElementCommon(filterOption));
		}
		return this;
	}
	
	public UserManagementCommonPage selectFilterByDepartment(List<String> filterOptions) {
		LOGGER.info("Selecting department");
		click(getFilterByDepartmentDropdown());
		for(String filterOption : filterOptions) {
			click(getOtherFilterOptionElementCommon(filterOption));
		}
		return this;
	}
	
	
	public UserManagementCommonPage setSearch(String enterprise) {
		LOGGER.info("Setting value to search");
        refreshPage();
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				setValue(enterprise, getSearchInput());
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
	
	public UserManagementCommonPage clickClearFilters() {
		LOGGER.info("Clicking clear filters");
		click(getClearFilterLink());
		return this;
	}
	
	public RemoveCreditsPage selectRemoveCreditsAction() {
    	LOGGER.info("Clicking remove credits link");
        click(getActionsDropdown());
  		mediumWait();
        click(getActionsRemoveCreditsOption());
		return new RemoveCreditsPage();
	}
	
	public CreateEnterprisePage selectCreateEnterpriseAction() {
		LOGGER.info("Clicking Create enterprise link");
		click(getActionsDropdown());
		click(getActionsCreateEnterpriseOption());
		return new CreateEnterprisePage();
	}

	
	public UserManagementCommonPage validateResetFilters() {
		LOGGER.info("Validating reset filters works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		return this;
	}
	
	public UserManagementCommonPage clickExport() {
		LOGGER.info("Clicking export");
		click(getExportButton());
		return this;
	}
	
	public UserManagementCommonPage clickActions() {
		LOGGER.info("Clicking Actions");
		click(getActionsDropdown());
		return this;
	}
	
	public UserManagementCommonPage clickFilterByEnterprise() {
		LOGGER.info("Clicking filter by enterprise");
		click(getFilterByEnterpriseDropdown());
		return this;
	}
	
	public UserManagementCommonPage clickFilterByLanguage() {
		LOGGER.info("Clicking filter by language");
		click(getFilterByLanguageDropdown());
		return this;
	}
	
	public UserManagementCommonPage clickFilterByExpertise() {
		LOGGER.info("Clicking filter by expertise");
		click(getFilterByExtertiseDrodown());
		return this;
	}
	
	public UserManagementCommonPage clickFilterByIndustry() {
		LOGGER.info("Clicking filter by industry");
		click(getFilterByIndustryDropdown());
		return this;
	}
	
	public UserManagementCommonPage clickSortBy() {
		LOGGER.info("Clicking Sort by");
		click(getSortByDropdown());
		return this;
	}
	
	public UserManagementCommonPage clickActionsExport() {
		LOGGER.info("Clicking Actions export");
		click(getActionsExportOption());
		return this;
	}
		
	public UserManagementCommonPage clickInviteCoach() {
		click(getActionsInviteCoachOption());
		return this;
	}
	
	public UserManagementCommonPage clickArchiveCoach() {
		LOGGER.info("Clicking Archive coach link");
		click(getActionsArchiveCoachOption());
		return this;
	}
	
	public UserManagementCommonPage clickDeleteCoach() {
		LOGGER.info("Clicking Delete coach link");
		click(getActionsDeleteCoachOption());
		return this;
	}
	
	public UserManagementCommonPage clickReInviteCoach() {
		LOGGER.info("Clicking ReInvite coach link");
		click(getActionsReInviteCoachOption());
		return this;
	}
	
	public UserManagementCommonPage clickUnArchiveCoach() {
		LOGGER.info("Clicking Archive coach link");
		click(getActionsUnarchiveCoachOption());
		return this;
	}
	
	public List<String> StoredData(List<String> list) {
		int pageCount = findElements(getPaginationElement()).size();		
		List<String> allElementsText=new ArrayList<>();
		boolean flag = true;
		while(flag) {
		for(int i = 1; i <= pageCount;i++){	
			List<WebElement> elementList= findElements(getUserTableNameElements());
			for(int j=0; j <elementList.size();j++){			
				int counter = 1;
				boolean flag1 = false;
				do {
					try {
						allElementsText.add(elementList.get(j).getText());			
					} catch (Exception e) {
						if(e.getCause().getMessage().contains("stale element")) {
							flag1 = true;
							shortWait();
						}
					}
					counter++;
				} while(flag1 && counter<=5);
				}
			if ((findElement(getNextpage()).getAttribute("class").contains("disabled"))) {
					 flag = false;
					break;
				}else {
					if(i==pageCount) {
						pageCount++;
					}					
					click(getPaginationLink(i+1));
				}				
			}
		}
		click(getPreviousPage());
		return allElementsText;	
	}
	
	public UserManagementCommonPage validateNameColumnSorted(List<String> expectedList, List<String> actualList) {
		LOGGER.info("Validating name column sorted");
		List<String> expected = StoredData(expectedList);
		 Collections.sort(expected,Collections.reverseOrder());
		 List<String> actual = StoredData(actualList);
		Assert.assertTrue(expected.equals(actual),"Failed to the validate order");
		return this;
	}
	
	public InviteEnterprisePage selectInviteEnterpriseAction() {
		LOGGER.info("Clicking Invite Enterprise link");
		click(getActionsDropdown());
		click(getActionsInviteEnterpriseOption());
		return new InviteEnterprisePage();
	}
	
	//Goes to Alert page to click Yes or No 
	public AlertsAndMessagesPage selectArchiveEnterpriseAction() {
		LOGGER.info("Clicking Archive enterprise link");
		click(getActionsDropdown());
		click(getActionsArchiveEnterpriseOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectRemoveEnterprise() {
		LOGGER.info("Clicking Remove enterprise link");
		click(getActionsDropdown());
		click(getActionsRemoveEnterpriseOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectReInviteEnterprise() {
		LOGGER.info("Clicking ReInvite enterprise link");
		click(getActionsDropdown());
		click(getActionsReInviteEnterpirseOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectReInviteOption() {
		LOGGER.info("Clicking ReInvite enterprise link");
		click(getActionsDropdown());
		click(getReInviteOption());
		return new AlertsAndMessagesPage();
	}
	
	public InviteCoachPage selectInviteCoachAction() {
		LOGGER.info("Clicking invite coach link");
		click(getActionsDropdown());
		clickInviteCoach();
		return new InviteCoachPage();
	}
	
	public InviteCoachPage selectInviteCoach() {
		LOGGER.info("Clicking invite coach");
		click(getActionsDropdown());
		click(getInviteOptionElementRm());
		return new InviteCoachPage();
	}
	
	public AlertsAndMessagesPage selectArchiveCoachAction() {
		click(getActionsDropdown());
		clickArchiveCoach();
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectDeleteCoachAction() {
		click(getActionsDropdown());
		clickDeleteCoach();
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectReinviteCoachCoachAction() {
		LOGGER.info("Clicking Reinvite link");
		click(getActionsDropdown());
		clickReInviteCoach();
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectUnArchiveCoachAction() {
		LOGGER.info("Clicking Archive link");
		click(getActionsDropdown());
		clickUnArchiveCoach();
		return new AlertsAndMessagesPage();
	}
	
	public InviteClientPage selectInviteClientAction() {
		LOGGER.info("Clicking invite link");
		click(getActionsDropdown());
		click(getInviteClientOption());
		return new InviteClientPage();
	}
	
	public InviteClientPage selectClientInviteAction() {
		LOGGER.info("Clicking invite link");
		click(getActionsDropdown());
		click(getInviteOptionElementRm());
		return new InviteClientPage();
	}
	
	
	public AlertsAndMessagesPage selectArchiveClientAction() {
		LOGGER.info("Clicking Archive clients link");
		click(getActionsDropdown());
		click(getArchiveClientOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectResendUserVerificationAction() {
		LOGGER.info("Clicking Resend user verification");
		click(getActionsDropdown());
		click(getResentUserVerificationOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectDeleteClientAction() {
		LOGGER.info("Clicking Delete client link");
		click(getActionsDropdown());
		click(getDeleteClientOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectUnArchiveClientAction() {
		LOGGER.info("Clicking Inarchive client link");
		click(getActionsDropdown());
		click(getUnArchiveClientOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectReinviteClientAction() {
		LOGGER.info("Clicking ReInvite client link");
		click(getActionsDropdown());
		click(getReInviteClientOption());
		return new AlertsAndMessagesPage();
	}
	
	public EnterprisesPage selectSortBy(String sortBy) {
		LOGGER.info("Clicking Sort by");
		click(getSortByDropdown());
		click(getSortByOptionElement(sortBy));
		return new EnterprisesPage();
	}
	
	public AlertsAndMessagesPage selectUnArchiveEnterpriseAction() {
		LOGGER.info("Clicking Unarchive enterprise link");
		click(getActionsDropdown());
		clickUnArchiveEnterprise();
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectDeleteEnterpriseAction() {
		LOGGER.info("Clicking delete enterprise link");
		click(getActionsDropdown());
		click(getActionsDeleteEnterpriseOption());
		return new AlertsAndMessagesPage();
	}
	
	public InviteAccountManagerPage selectInviteAccountManager() {
		LOGGER.info("Clicking Invite Account Manager link");
		click(getActionsDropdown());
		click(getInviteAccountManagerOptionElement());
		return new InviteAccountManagerPage();
	}
	
	public AlertsAndMessagesPage selectArchiveAccountManager() {
		LOGGER.info("Clicking Archive Account Manager link");
		click(getActionsDropdown());
		click(getArchiveAccountManagerOptionElement());
		return new AlertsAndMessagesPage();
	}
	
	
	public InviteAccountManagerPage selectInvite() {
		LOGGER.info("Clicking Invite link");
		click(getActionsDropdown());
		click(getInviteOptionElementRm());
		return new InviteAccountManagerPage();
	}
	

	public AlertsAndMessagesPage selectReInviteAccountManager() {
		LOGGER.info("Clicking ReInvite Account Manager link");
		click(getActionsDropdown());
		click(getReInviteAccountManagerOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectEnableAccountManager() {
		LOGGER.info("Clicking Enable Account Manager link");
		click(getActionsDropdown());
		click(getEnableAccountManagerOption());
		return new AlertsAndMessagesPage();
	}
	
	public AlertsAndMessagesPage selectDeleteAccountManagerAction() {
		LOGGER.info("Clicking Delete Account Manager link");
		click(getActionsDropdown());
		click(getDeleteAccountManagerOptionElement());
		return new AlertsAndMessagesPage();
	}
	
	private By getActionsInviteEmployeeOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.employees.invite.title']"));
	}
	
	public EnterpriseInviteEmployeePage clickInviteEmployee() {
		LOGGER.info("Clicking Invite Employee link");
		click(getActionsDropdown());
		click(getActionsInviteEmployeeOption());
		return new EnterpriseInviteEmployeePage();
	}
	
	public UserManagementCommonPage clickArchivedLink() {
		LOGGER.info("Clicking Archived link");
		click(getArchivedLink());
		return this;
	}
	
	private By getDeleteAmLink() {
		return By.xpath(getXpathByText("//span[contains(text(),'admin.user_management.acc_manager.delete_acc_manager')]"));
	}
	
	public AlertsAndMessagesPage clickDeleteLink() {
		LOGGER.info("Clicking Delete link");
		click(getDeleteAmLink());
		return new AlertsAndMessagesPage();
	}
	
	private By getNoOfEntry() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[contains(@class,'ui-paginator-bottom')]/span[1]");
	}
	
	public int getAllRecordsPresent() {
		LOGGER.info("Total records present");
		mediumWait();
		String entries = getText(getNoOfEntry());
		String noEnt = entries.split(" ")[5];
		int records=Integer.parseInt(noEnt); 
		return records;
		
	}
	
	
	public UserManagementCommonPage validateRevertToDefaultPage(int expected) {
		LOGGER.info("validating Download records works as expected");
		int actual = getAllRecordsPresent();
		System.out.println("Actual records"+actual);
		Assert.assertEquals(actual,expected,"not reverted to the default page");
		return this;
	}
	
	public UserManagementCommonPage validateDownloadAllRecords() throws IOException {
		LOGGER.info("validating Download records works as expected");
		String filename = FileUtils.getFileName();
		String entries = getText(getNoOfEntry());
		String noEnt = entries.split(" ")[5];
		int expected=Integer.parseInt(noEnt); 
		int actual = FileUtils.getRecordCountOfCsvFile(getDownloadedFolderPath+filename);
		actual = actual -1;
		FileUtils.delteDownloadedFile(getDownloadedFolderPath,extension);
		Assert.assertEquals(actual, expected,"Count is not matching");
		return this;
	}
	
	public UserManagementCommonPage validateDownloadSpecificRecords(int expected) {
		LOGGER.info("Validating Download specific records successfully");
		String filename = FileUtils.getFileName();
		int actual = FileUtils.getRecordCountOfCsvFile(getDownloadedFolderPath+filename);
		actual = actual - 1;
		FileUtils.delteDownloadedFile(getDownloadedFolderPath,extension);
		Assert.assertEquals(actual, expected,"Count is not matching");
		return this;
	}
	
	protected List<String> getUsersRolesList() {
			List<String> userRoles = new ArrayList<>();
			userRoles.add(ThriveAppSharedData.ROLE_ENTERPRISE_ADMIN.getValue());
			userRoles.add(ThriveAppSharedData.ROLE_ACCOUNT_MANAGER.getValue());
			userRoles.add(ThriveAppSharedData.ROLE_CLIENT.getValue());
			userRoles.add(ThriveAppSharedData.ROLE_COACH.getValue());
			userRoles.add(ThriveAppSharedData.ROLE_INTERNAL_COACH.getValue());
			userRoles.add(ThriveAppSharedData.ROLE_SUPER_ADMIN.getValue());
			return userRoles;
		}
	
	private By getActionDropdown() {
		return By.xpath(".//button[contains(@class,'action-btn')]");
	}
	
	public UserManagementCommonPage clickActionsDropdown() {
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getActionDropdown());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		return this;
	}
	
	public UserManagementCommonPage clickRefreshBrowser() {
		LOGGER.info("Refersh the current page");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				getDriver().navigate().refresh();
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
		return this;
	}
	
	
	private By getCheckbox(int i) {
		return By.xpath(".//div[@class='ui-table-scrollable-body ng-star-inserted']//tr["+i+"]//p-checkbox");
	}
	
	private By getSelectedCheckbox() {
		return By.xpath(".//div[contains(@class,'ui-state-active')]");
	}
	
	public int selectSpecificRecords() {
		LOGGER.info("Capturing specific invited Users Names");
		int i=1;
		while(i<=2) {
			waitUntilElementIsPresent(5, getCheckbox(i));
			click(getCheckbox(i));
			i++;
		}
		int expected = findElements(getSelectedCheckbox()).size();
		return expected;
		
	}
	
	private By getDataCountDropdownLink() {
		return By
				.xpath("//p-table[not(contains(@class,'hidden'))]//div[contains(@class,'ui-dropdown-label-container')]//following-sibling::div[@role='button']");
	}

	private By getDataCountLink(int count) {
		return By.xpath("//p-dropdownitem//li//span[(text()='" + count + "')]");
	}
	
	public UserManagementCommonPage selectPaginationValue(int val) {
		int counter = 0;
		boolean flag = true;
		do {
			try {
				click(getDataCountDropdownLink());
				shortWait();
				click(getDataCountLink(val));
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=15);
		return this;
	}
	
	private By getDataCountDropdownLinkEA() {
		return By
				.xpath(" //div[@id='employee-tbl-container']//div[contains(@class,'ui-dropdown-label-container')]//following-sibling::div[@role='button']");
	}

	
	public UserManagementCommonPage selectPaginationValueEA(int val) {
		int counter = 0;
		boolean flag = true;
		do {
			try {
				click(getDataCountDropdownLinkEA());
				shortWait();
				click(getDataCountLink(val));
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=15);
		return this;
	}
	
	
	
	public UserManagementCommonPage scrollInVerticalDirection(String offsetValue) {
		LOGGER.info("Moving scroll bar in vertical direction");
		Long verticalLocationBeforeScroll;
		shortWait();
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		verticalLocationBeforeScroll = (Long) executor.executeScript("return window.pageYOffset;");
		mediumWait();
		scrollByJavaScript("0", offsetValue);
		return this;
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	public UserManagementCommonPage validatePaginationRecords(int pagination) {
		LOGGER.info("Validate number of data set displayed");
		int records = findElements(getRowsLink()).size();
		if(pagination==records) {
		    Assert.assertEquals(pagination, records,"records are matching");
		} else {
		LOGGER.info("Insufficient number of records");
	        Assert.assertTrue(pagination!=records,"Insufficent no of records");
		}
		return this;
	}
	
	private By getProfileImage() {
		return By.xpath(".//input[@id='photo']");
	}
	
	private By getBrowseFilesLink() {
		return By.xpath(".//input[@id='file']");
	}
	
	private By getImageSaveButton() {
		return By.xpath(getXpathByText(".//button[text()='questions.save.values.value']"));
	}
	
	private By getRecordsCount() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//span[contains(@class,'ui-paginator-current')]");
	}
	
	private By getUserCheckBox(String userName) {
		return By.xpath("//a[contains(text(),'"+userName+"')]/../..//preceding-sibling::td//p-checkbox");
	}
	
	private By getSettingsIcon() {
		return By.xpath("//ul//li[3]//div[contains(@class,'dropdown-toggle')]");
	}
	
	private By getSettingsOption() {
		return By.xpath(getXpathByText("//u[contains(text(),'shared.coaching_program.submenu.drop_settings')]"));
	}
	
	private By getByGetFilterByRegion() {
		return By.xpath(getXpathByText("//div[contains(text(),'admin.dashboards.filter_by_region')]/.."));
	}
	
	private By getByGetFilterByDepartment() {
		return By.xpath(getXpathByText("//div[contains(text(),'admin.dashboards.filter_by_department')]/.."));
	}

	private By getDropdownValue(String region) {
		return By.xpath("//div[contains(@class,'usermgmt-options')]//span[contains(text(),'"+region+"')]");
	}
	
	
	private By getEmailAddressElement(String username) {
		return By.xpath(".//td[@class='ng-star-inserted']//span[contains(text(),'"+username+"')]");
	}
	
	private By getUserName(String name) {
		return By.xpath("//a[contains(text(),'"+name+"')]");
	}
	
	public UserManagementCommonPage uploadProfileImage() {
		LOGGER.info("Upload the Profile image");
		String imgPath = resourcePath + "/imagefile.jpg";
		click(getProfileImage());
		mediumWait();
		findElement(getBrowseFilesLink()).sendKeys(imgPath);
		click(getImageSaveButton());
		return this;
	}
	
	public String captureCurrentDataCount() {
		LOGGER.info("Capturing current data count");
		mediumWait();
		String[] countString = getText(getRecordsCount()).split(" ");
		String currentRecordsCount = countString[5];
		System.out.println("currentRecordsCount "+currentRecordsCount);
		return currentRecordsCount;
	}
		
	public UserManagementCommonPage selectUserCheck(String userName) {
		LOGGER.info("Selecting searched user");
		click(getUserCheckBox(userName));
		return this;
	}
	
	public EnterpriseDetailsPage clickSettingsDeprecated() {
		LOGGER.info("Clicking Settings option");
		shortWait();
		click(getSettingsIcon());
		waitUntilElementIsClickable(3, getSettingsOption());
		click(getSettingsOption());
		return new EnterpriseDetailsPage();
	}

	public UserManagementCommonPage validateDataUpdate(String preDataCount) {
		LOGGER.info("Validating All Users data updated successfully");
		int precount = Integer.parseInt(preDataCount);
		String newDataCount = captureCurrentDataCount();
		int newCount = Integer.parseInt(newDataCount);
		Assert.assertTrue(newCount == precount + 1, "Users data not get updated");
		return this;
	}

	public UserManagementCommonPage clickFilterByRegion() {
		LOGGER.info("Clicking Filter By Region");
		click(getByGetFilterByRegion());
		return this;
	}
	
	public UserManagementCommonPage selectRegion(String region) {
		LOGGER.info("Selecting "+region+" region");
		click(getDropdownValue(region));
		return this;
	}
	
	public UserManagementCommonPage clickFilterByDepartment() {
		LOGGER.info("Clicking Filter By Department");
		click(getByGetFilterByDepartment());
		return this;
	}
	
	public UserManagementCommonPage selectDepartment(String department) {
		LOGGER.info("Selecting "+department+" department");
		click(getDropdownValue(department));
		return this;
	}
	
	public UserManagementCommonPage validateInvitedUsersEmailPresent(String username) {
		LOGGER.info("Validating user name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressElement(username.toLowerCase())), "Failed to find the username element with name :" + username);
		return this;
  }

	public UserManagementCommonPage clickSettingsIcon() {
		LOGGER.info("Clicking setting icon");
		click(getSettingsIcon());
		return this;
	}
	
	public UserManagementCommonPage clickOnSearchedName(String name) {
		LOGGER.info("Clicking on searched user name");
		click(getUserName(name));
		return this;
	}

}