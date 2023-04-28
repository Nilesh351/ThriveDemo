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

public class InvitedInternalCoachPage extends UserManagementCommonPage {

	private By getEmailAddressColumn(String email) {
		return By.xpath(getXpathByText(".//th[contains(text(),'admin.coaches.view.col_email')]/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	
	public InvitedInternalCoachPage validateEmailPresent(String email) {
		LOGGER.info("Validating invited email is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)), "Failed to seach invited enterprise :" + email);
		return this;
	}
	
	public InvitedInternalCoachPage validateInvitedInternalCoachNotPresent(String email) {
		LOGGER.info("Validating invited coach not present");
		Assert.assertTrue(isElementNotVisible(getEmailAddressColumn(email)), "Invited internal coach is present :" + email);
		return this;
	}
	
	private By getEmailAddressColumnRm(String email) {
		return By.xpath(getXpathByText(".//th[(text())='private.profile.coach.email_address']/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']"));
	}
	
	public InvitedInternalCoachPage validateEmailPresentValue(String email) {
		LOGGER.info("Validating invited email is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumnRm(email)), "Failed to seach invited enterprise :" + email);
		return this;
	}
	
	private By getInvitedInternalCoachCheckbox(String coachName) {
		return By.xpath(".//span[normalize-space(text())='"+coachName+"']/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	public InvitedInternalCoachPage clickInvitedInternalCoachCheckbox(String clientName) {
		LOGGER.info("Clicking coach checkbox");
		click(getInvitedInternalCoachCheckbox(clientName));
		return this;
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
	
	
	public InvitedInternalCoachPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getReInviteOption()),"reinvite option not present");
		softAssert.assertTrue(isElementPresent(getActionsDeleteCoachOption()),"delete option not present");
		softAssert.assertAll();
		return this;
	}
	
	public InvitedInternalCoachPage validateClearFilters() {
		LOGGER.info("validate clear filters clear all data");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getSearchInput()),"search fileld is not cleared");
		softAssert.assertAll();
		return this;
		
	}
	
	public InvitedInternalCoachPage validateBulkinviteDataInternalCoach(String file) {

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
