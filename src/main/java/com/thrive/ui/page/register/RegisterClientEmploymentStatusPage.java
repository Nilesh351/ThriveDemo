package com.thrive.ui.page.register;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.user_details.ClientEmploymentDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class RegisterClientEmploymentStatusPage extends BaseTestPage {
	
	String positionValthrive;
	String workDurationVal;
	String regionVal;
	String dept;
	String getemploymentduration;
	String getdepartment;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	
	private By getWorkDurationDropdown() {
		return By.xpath(".//ng-select[@name='duration_of_employment']");
	}
	
	private By getWorkDurationDropdownRm() {
		return By.xpath(".//ng-select[@name='duration_of_employment']");
	}
	
	private By getWorkDurationOptionElement(String workDuration) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+workDuration+"']");
	}
	
	private By getDepartmentDropdown() {
		return By.xpath(".//ng-select[@name='department']");
	}
	
	private By getDepartmentOptionElement(String department) {
		return By.xpath(".//div[contains(@class,'ng-option')]//span[contains(@class, 'ng-star') and text()='"+department+"']");
	}
	
	private By getRegionDropdown() {
		return By.xpath(".//ng-select[@name='regions']");
	}
	
	private By getRegionOptionElement(String region) {
		return By.xpath(".//span[contains(@class, 'ng-star') and text()='"+region+"']");
	}
	
	private By getPositionDropdown() {
		return By.xpath(".//ng-select[@name='other_senior_position']");
	}
	
	private By getPositionInputRm() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_client.8tsfeif4mu.label']/../..//input"));
	}
	
	private By getPositionOptionElement(String position) {
		return By.xpath(".//span[contains(@class, 'ng-star') and text()='"+position+"']");
	}
	
	private By getDateFormatDropdown() {
		return By.xpath(".//ng-select[@name='date_formates']");
	}
	
	private By getDateFormatOptionElement(String dateFormat) {
		return By.xpath(".//span[contains(@class, 'ng-star') and text()='"+dateFormat+"']");
	}
	
	private By getReferenceInput() {
		return By.xpath(getXpathByText(".//label[(text())='admin.employees.employees.col.reference_number']/following-sibling::input"));
	}
	
	private By getDepartmentValue() {
		return By.xpath(".//ng-select[@name='department']//div[@class='ng-value ng-star-inserted']/span");
	}
	
	private By getPositionValue() {
		return By.xpath(".//ng-select[@name='other_senior_position']//div[@class='ng-value ng-star-inserted']/span[2]");
	}
	
	private By getRegionValue() {
		return By.xpath(".//ng-select[@name='regions']//div[@class='ng-value ng-star-inserted']/span");
	}
	
	private By getDurationOfEmploymentValue() {
		return By.xpath(".//ng-select[@name='duration_of_employment']//div[@class='ng-value ng-star-inserted']/span[2]");
	}
	
	private By getFirstAvailableElementFromDropdown() {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.client.step2.next']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.client.step3.previous']"));
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	private By getConsentCheckboxRm() {
		return By.xpath(".//input[@id='email_updates']/following-sibling::span");
	}
	
	private By getRegisterButtonRm() {
		//return By.xpath(getXpathByText(".//button[text()='register.client.step3.register']"));
		return By.xpath("//button[@type='submit']");
	}
	
	private By getDepartmentErrormsg() {
		return By.xpath(".//ng-select[@name='department']/..//following-sibling::div/p[text()='"+errorMsg+"']");
	}
	
	private By getRegionErrorMsg() {
		return By.xpath(".//ng-select[@name='regions']/..//following-sibling::div/p[text()='"+errorMsg+"']");
	}
	
	private By getPositionErrorMsg() {
		return By.xpath(".//ng-select[@name='other_senior_position']/..//following-sibling::div/p[text()='"+errorMsg+"']");
	}
	
	private By getPositionErrorMsgRm() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_client.8tsfeif4mu.label']/..//following-sibling::div//p[text()='"+errorMsg+"']"));
	} else {
		return By.xpath(".//label[normalize-space(text())='Poste']/..//following-sibling::div//p[text()='"+errorMsg+"']");
    	}
	}
	
	private By getWorkDurationErrorMsg() {
		return By.xpath(".//ng-select[@name='duration_of_employment']/..//following-sibling::div/p[text()='"+errorMsg+"']");
	}
	
	private By getPrivacyNoticeChecboxErrorMsg(String privacyErrorMsg) {
		//return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.5pnochasike.label')]/..//following-sibling::div/div/div//span/../../../../..//p[text()='"+privacyerrormsg+"']"));
	  if(Config.getLocalizationLanguage().contains("en")){
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.5pnochasike.label')]//following-sibling::div[contains(@class,'error-message')]//p[contains(text(),'"+privacyErrorMsg+"')]"));
	} else {
		return By.xpath(".//label[contains(text(),'Notice de confidentialité')]//following-sibling::div[contains(@class,'error-message')]//p[contains(text(),'"+privacyErrorMsg+"')]");
	}
	}
	
	public RegisterClientEmploymentStatusPage registerClientInfo() {
		LOGGER.info("Registring client informaion");
		click(getConsentCheckboxRm());
		click(getRegisterButtonRm());
		return this;
	}
	
	public RegisterClientEmploymentStatusPage  validateClientRegistrationSuccessful(String expectedSuccessMessage) {
		LOGGER.info("Validating registration is successful");
		String actualMessage = getText(getAlertElement());
		Assert.assertEquals(actualMessage, expectedSuccessMessage, "Client Registration Failed With Error : " + actualMessage );
		return this;
	}
	
	public RegisterClientEmploymentStatusPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectWorkDuration(String workDuration) {
		LOGGER.info("Selecting work duration");
		click(getWorkDurationDropdown());
		click(getWorkDurationOptionElement(workDuration));
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectWorkDurationRm(String workDuration) {
		LOGGER.info("Selecting work duration");
		click(getWorkDurationDropdownRm());
		click(getWorkDurationOptionElement(workDuration));
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectFirstWorkDuration() {
		LOGGER.info("Selecting first work duration");
		click(getWorkDurationDropdown());
		workDurationVal = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				getemploymentduration = getText(getDurationOfEmploymentValue());
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
	
	public RegisterClientEmploymentStatusPage selectDepartment(String department) {
		LOGGER.info("Selecting department");
		click(getDepartmentDropdown());
		click(getDepartmentOptionElement(department));
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectFirstDepartment() {
		LOGGER.info("Selecting first department");
		click(getDepartmentDropdown());
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				dept = getText(getFirstAvailableElementFromDropdown());
				click(getFirstAvailableElementFromDropdown());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectRegion(String region) {
		LOGGER.info("Selecting region");
		click(getRegionDropdown());
		click(getRegionOptionElement(region));
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectFirstAvailableRegion() {
		LOGGER.info("Selecting first available region");
		click(getRegionDropdown());
		regionVal = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectPosition(String position) {
		LOGGER.info("Selecting position");
		click(getPositionDropdown());
		click(getPositionOptionElement(position));
		return this;
	}
	
	public RegisterClientEmploymentStatusPage setPositionValue(String position) {
		LOGGER.info("Set position value");
		setValue(position, getPositionInputRm());
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectFirstAvailablePostion(String positionVal) {
		LOGGER.info("Selecting first available position");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
		click(getPositionDropdown());
		positionValthrive = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		}
		else {
			setValue(positionVal, getPositionInputRm());
		}
		return this;
	}
	
	public RegisterClientEmploymentStatusPage selectDateFormat(String dateFormat) {
		LOGGER.info("Selecting date format");
		click(getDateFormatDropdown());
		click(getDateFormatOptionElement(dateFormat));
		return this;
	}
	
	public RegisterClientEmploymentStatusPage setRefernce(String refernce) {
		LOGGER.info("Settingvalue for referance ");
		setValue(refernce, getReferenceInput());
		return this;
	}
	
	
	public RegisterClientCareerDetailsPage clickNext() {
		LOGGER.info("Clicking Next button");
		shortWait();
		click(getNextButton());
		return new RegisterClientCareerDetailsPage();
	}

	public RegisterClientRegistrationDetailsPage clickPrevious() {
		LOGGER.info("Clicking previous buttons");
		click(getPreviousButton());
		return new RegisterClientRegistrationDetailsPage();
	}
	
	public RegisterClientCareerDetailsPage submitClientEmploymentDetails(ClientEmploymentDetails clientRegistrationDetails) {
		LOGGER.info("Submitting client employment details");
		selectWorkDuration(clientRegistrationDetails.getWorkDuration())
		.selectDepartment(clientRegistrationDetails.getDepartment())
		.selectRegion(clientRegistrationDetails.getRegion())
		.selectPosition(clientRegistrationDetails.getPosition())
		.selectDateFormat(clientRegistrationDetails.getDateFormat())
		.setRefernce(clientRegistrationDetails.getReference())
		.clickNext();
		return new RegisterClientCareerDetailsPage();
	}
	
	public RegisterClientCareerDetailsPage submitEmploymentDetails(ClientEmploymentDetails clientRegistrationDetails) {
	LOGGER.info("Submitting employment details");
		
		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
		selectDepartment(clientRegistrationDetails.getDepartment())
		.selectPosition(clientRegistrationDetails.getPosition())
		.selectRegion(clientRegistrationDetails.getRegion())
		 	.selectWorkDuration(clientRegistrationDetails.getWorkDuration())
			.clickNext();
		}else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			selectDepartment(clientRegistrationDetails.getDepartment())
			.setPositionValue(clientRegistrationDetails.getPosition())
			.selectRegion(clientRegistrationDetails.getRegion())
			.selectWorkDurationRm(clientRegistrationDetails.getWorkDuration());
			//.registerClientInfo();
		}
		return new RegisterClientCareerDetailsPage();
	}
	
	public RegisterClientEmploymentStatusPage validateEmployment(ClientEmploymentDetails clientEmpDetails) {
		LOGGER.info("Validating employment");
		String getposition = " ";
		String getregion= getText(getRegionValue());
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
					getposition = getText(getPositionValue());
				}else {
					getposition = getAttributeByValue("value",getPositionInputRm());
				}
				getdepartment = getText(getDepartmentValue());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		
		
		workDurationVal = getText(getDurationOfEmploymentValue());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getdepartment.equalsIgnoreCase(dept),"department not updated");
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			softAssert.assertTrue(getposition.equalsIgnoreCase(positionValthrive),"position not updated");
		}else {
			softAssert.assertTrue(getposition.equalsIgnoreCase(clientEmpDetails.getPosition()),"position not get updated");
		}
		
		softAssert.assertTrue(getregion.equalsIgnoreCase(regionVal),"region not updated");
		softAssert.assertTrue(getemploymentduration.equalsIgnoreCase(workDurationVal),"employment duration not updated");
		softAssert.assertAll();	
		return this;
	}
	
	public RegisterClientEmploymentStatusPage validateEmploymentErrorValidation() {
			
			LOGGER.info("Validating employment error validation");
			
			SoftAssert softAssert = new SoftAssert();
			shortWait();
			if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
				clickNext();
				//softAssert.assertTrue(isElementPresent(getPositionErrorMsg()),"Position error message not present for position");
			}
			else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
				click(getRegisterButtonRm());
				softAssert.assertTrue(isElementVisible(getPrivacyNoticeChecboxErrorMsg(ThriveAppSharedData.PRIVACY_NOTICE_ERROR_MSG_RM.getValue())),"privacy notive error message not present");
				softAssert.assertTrue(isElementPresent(getPositionErrorMsgRm()),"Position error message not present for position");
			}
			softAssert.assertTrue(isElementVisible(getDepartmentErrormsg()),"Department error message not present for department");
			
			softAssert.assertTrue(isElementVisible(getRegionErrorMsg()),"Region error messsage not present for region");
			softAssert.assertTrue(isElementVisible(getWorkDurationErrorMsg()),"Work duration error message not present for duration");
			softAssert.assertAll();
			return this;
		}
	

}