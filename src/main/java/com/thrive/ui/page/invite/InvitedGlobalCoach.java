package com.thrive.ui.page.invite;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;

public class InvitedGlobalCoach extends UserManagementCommonPage {
	
	private By getEmailAddressColumn(String email) {
		return By.xpath(".//span[contains(text(),'admin.user_management.coaches.col.email')]//ancestor::div[contains(@class,'table-scrollable-header')]//following-sibling::div[contains(@class,'body')]//tr//span[contains(text(),'"+email.toLowerCase()+"')]");
	}


	public InvitedGlobalCoach validateEmailPresent(String email) {
		LOGGER.info("Validating email is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumnVal(email)), "Failed to seach invited caoch :" + email);
		return this;
	}
	
	public InvitedGlobalCoach validateInvitedCoachNotPresent(String email) {
		LOGGER.info("Validating invited coach not present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumnVal(email)), "Invited coach is present :" + email);
		return this;
	}
	
	private By getEmailAddressColumnVal(String email) {
		return By.xpath(getXpathByText(".//span[(text())='admin.user_management.coaches.col.email']/../../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	public InvitedGlobalCoach validateEmailPresentVal(String email) {
		LOGGER.info("Validating email is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumnVal(email)), "Failed to seach invited enterprise :" + email);
		return this;
	}
	
	private By getInvitedCoachCheckbox(String coachName) {
		return By.xpath(".//span[normalize-space(text())='"+coachName+"']/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}

	private By getActionsDeleteCoachOption() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.coaches.delete_coach']"));
	}

	private By getReInviteOption() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.coaches.reinvite_coach')]"));
		} else {
			return By.xpath(".//span[contains(text(),'RÉINVITER')]");
		}
	}
	
	public InvitedGlobalCoach clickInvitedGlobalCheckbox(String clientName) {
		LOGGER.info("Clicking coach checkbox");
		click(getInvitedCoachCheckbox(clientName));
		return this;
	}
		
	private By getCoachTypeDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.filter_training_coach']"));
	}
	
	public InvitedGlobalCoach validateClearFilters() {
		LOGGER.info("validate clear filters clear all data");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getSearchInput()),"search fileld is not cleared");
		softAssert.assertTrue(isElementPresent(getCoachTypeDropdownValue()),"coach type value is not present");
		return this;
		
	}
		
	public InvitedGlobalCoach validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getReInviteOption()),"reinvite option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteCoachOption()),"delete option not present");
		softAssert.assertAll();
		return this;
	}
	
	private By getTrainingCoachOptionValue() {
		return By.xpath(".//span[contains(text(),'Training Coach')]/../../../../../../..//span[contains(text(),'Yes')]");
	}
	
	private By getGlobalCoachOptionValue() {
		return By.xpath(".//span[contains(text(),'Training Coach')]/../../../../../../..//span[contains(text(),'No')]");
	}
	
	public InvitedGlobalCoach validateTrainingCoachPresent() {
		LOGGER.info("Validate training coach present");
		Assert.assertTrue(isElementPresent(getTrainingCoachOptionValue()),"Training coach not present");
		return this;
	}
	
	public InvitedGlobalCoach validateGlobalCoachPresent() {
		LOGGER.info("Validate global coach present");
		Assert.assertTrue(isElementPresent(getGlobalCoachOptionValue()),"Global coach not present");
		return this;
	}
	
	public InvitedGlobalCoach validateBulkinviteDataCoach(String file) {

		try {

			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			String[] nextRecord;

			while ((nextRecord = csvReader.readNext()) != null) {
				for (String cell : nextRecord) {
					shortWait();
					String value = cell;
					String email = value.split(" ")[0];
					setSearch(email);
					validateEmailPresent(email);
					break;
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}
	
	
	
}
	

