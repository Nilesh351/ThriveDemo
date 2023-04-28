package com.thrive.ui.page.register;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.user_details.AccountManagerPersonalDetails;
import com.thrive.common.testdata.pojos.user_details.AccountManagerUpdateDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.utils.DBUtils;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.user_management.search_filter.ConfigureEnterprises;

public class RegisterAccountMangerPersonalDetailsPage extends BaseTestPage {
	
	String nationality;
	String gender;
	String country;
	String agerange;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	
	

	private By getFirstNameInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_account_manager.ylgor99qd8.label')]/../following-sibling::input"));
	}
	
	private By getFirstNameInputRm() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_account_manager.kvzqhjoymzs.label')]/../following-sibling::input"));
	}

	private By getLastNameInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_account_manager.dceu4ihxrvj.label')]/../following-sibling::input"));
	}
	
	private By getLastNameInputRm() {
		return By.xpath(getXpathByText(".//label[text()='questions.registeration_account_manager.dv0h2v1d7ud.label']/../following-sibling::input"));
	}
	

	private By getEmailAddressInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_account_manager.829br48u7ri.label')]/../following-sibling::input"));
	}
	
	private By getEmailAddressInputRm() {
		return By.xpath(getXpathByText(".//label[contains(text(),'.//label[contains(text(),'questions.registeration_account_manager.oiukr50o14p.label')]/../following-sibling::input"));
	}

	private By getPasswordInput() {
		return By.xpath(".//input[1][@id='password']");
	}

	private By getPhotoImage() {
		return By.xpath(getXpathByText("//label[(text())='questions.registeration_account_manager.2d5e3hzquy5.label']/..//following-sibling::div/input[@id='photo']"));
	}

	private By getMobileNumber() {
		return By.xpath(".//input[@id='mobile_number']");
	}
	
	private By getMobileNumberRm() {
		return By.xpath("//input[@id='mobile_number']");
	}

	private By getAgeRangeDropdown() {
		return By.xpath(".//ng-select[@name='age_range']");
	}

	private By getAgeRangeOptionElement(String age) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='" + age + "']");
	}

	private By getSmsRemindersRadioNoOption() {
		return By.xpath(
				".//label[(text())='questions.registeration_account_manager.a7b8f1suqwg.label']/..//following-sibling::div/label[(text())='questions.registeration_account_manager.btz9lfpqej.no']/span");
	}

	private By getSmsRemindersRadioYesOption() {
		return By.xpath(
				".//label[(text())='questions.registeration_account_manager.a7b8f1suqwg.label']/..//following-sibling::div/label[(text())='questions.registeration_account_manager.btz9lfpqej.yes']/span");
	}

	private By getGenderDropdown() {
		return By.xpath(".//ng-select[@name='gender']");
	}

	private By getGenderOptionElement(String gender) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='" + gender + "']");
	}

	private By getCountryDropdown() {
		return By.xpath(".//ng-select[@name='country']");
	}

	private By getCountryOptionElement(String Country) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='" + Country + "']");
	}

	private By getNationalityDropdown() {
		return By.xpath(".//ng-select[@name='nationality']");
	}

	private By getNationalityOptionElement(String nationality) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='" + nationality + "']");
	}

	private By getAddressInput() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_account_manager.nvd41duc3gj.label']/following-sibling::input"));
	}

	private By getPrivacyNoticeCheckbox() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_account_manager.0hga6skob1jv.label')]/..//following-sibling::div/div/div//span"));
	}

	private By getFillInManuallyLink() {
		return By.xpath("//input[@id='find-address']//following-sibling::a");
	}

	private By getAddressLine1Input() {
		return By.xpath(".//input[@id='address1']");
	}

	private By getAddressLine2Input() {
		return By.xpath(".//input[@id='address2']");
	}

	private By getCityInput() {
		return By.xpath(".//input[@id='city']");
	}

	private By getStateInput() {
		return By.xpath(".//input[@id='state']");
	}

	private By getCountyInput() {
		return By.xpath(".//input[@id='county']");
	}

	private By getPostcodeInput() {
		return By.xpath(".//input[@id='postcode']");
	}

	private By getRegisterButton() {
		return By.xpath("//div[contains(@class,'form-group actions')]//button");
		//return By.xpath(getXpathByText(".//button[text()='register.account_manager.step3.register']"));
	}
	
	private By getRegisterToasterMsg() {
		return By.xpath(getXpathByText(".//div[(text())='ui.messages.registered_successful']"));
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getFirstAvailableElementFromDropdown() {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getGenderValue() {
		return By.xpath(".//ng-select[@name='gender']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getNationalityValue() {
		return By.xpath(".//ng-select[@name='nationality']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getCountryValue() {
		return By.xpath(".//ng-select[@name='country']//div[@class='ng-value ng-star-inserted']//span");
	}
	
	private By getAgeRangeValue() {
		return By.xpath(".//ng-select[@name='age_range']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getPasswordErrorMsg() {
		return By.xpath(".//input[1][@id='password']/..//p[text()='"+errorMsg+"']");
	}
	
	private By getAageRangeDrodownErrorMsg() {
			return By.xpath(".//ng-select[@name='age_range']/..//p[text()='"+errorMsg+"']");	
		}
	
	private By getGenderDropdownErrorMeg() {
			return By.xpath(".//ng-select[@name='gender']/..//p[text()='"+errorMsg+"']");
		}
	
	private By getNationalDropdownErrorMeg() {
			return By.xpath(".//ng-select[@name='nationality']/..//p[text()='"+errorMsg+"']");
		}
	
	private By getAddressInputErrorMsg() {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//small[contains(text(),'Start entering your address below and select from the list')]/..//p[text()='"+errorMsg+"']");
	} else {
		return By.xpath(".//small[contains(text(),'Commencez à entrer votre adresse ci-dessous et faites votre choix dans la liste')]/..//p[text()='"+errorMsg+"']");

	      }
	}
	
	private By getPrivacyNoticeChecboxErrorMsg(String privacyerrormsg) {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//label[contains(text(),'Privacy Notice')]/..//following-sibling::div/div/div//span/../../../../..//p[text()='"+privacyerrormsg+"']");
		}else {
			return By.xpath(".//label[contains(text(),'Notice de confidentialité')]/..//following-sibling::div/div/div//span/../../../../..//p[text()='"+privacyerrormsg+"']");
		}
	}
	
	
	private By getEnterprisesTab() {
		if(Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(".//span[contains(text(),'shared.sub_header.Enterprise')]"));
		}else {
			return By.xpath(getXpathByText(".//li[contains(text(),'shared.sub_header.Enterprise')]"));
		}
		
	}
	
	public ConfigureEnterprises clickEnterprisesTab() {
		LOGGER.info("click on enterprises tab inside account manager");
		click(getEnterprisesTab());
		return new ConfigureEnterprises();
	}

	public RegisterAccountMangerPersonalDetailsPage setFirstName(String firstname) {
		LOGGER.info("Setting value for first name");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(firstname, getFirstNameInput());
		}
		else {
			setValue(firstname, getFirstNameInputRm());
		}
		return this;
	}
	
	private By getChangeEmailAddressLink() {
    	return By.xpath(getXpathByText(".//a[contains(text(),'private.profile.coach.change_email_address')]"));
    }
	
	 public ChangeEmailAddressPage clickChangeEmailAddressLink() {
	    	LOGGER.info("Clicking Change email address link");
	    	click(getChangeEmailAddressLink());
	    	return new ChangeEmailAddressPage();
	    }
	
	public String firstNameValue() {
		LOGGER.info("get First name value");
		String getFirstName;
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			getFirstName = getAttributeByValue("value", getFirstNameInput());
		}else {
			getFirstName = getAttributeByValue("value", getFirstNameInputRm());
		}
		
		return getFirstName;
		
	}
	
	public String emailValue() {
		LOGGER.info("get email value");
		String email;
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			email = getAttributeByValue("value", getEmailAddressInput());
		}else {
			email = getAttributeByValue("value", getEmailAddressInputRm());
		}
		return email;
	}
	

	public RegisterAccountMangerPersonalDetailsPage setLastName(String lastname) {
		LOGGER.info("Setting value for last name");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(lastname, getLastNameInput());
		}
		else {
			setValue(lastname, getLastNameInputRm());
		}
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setEmailAddress(String emailaddress) {
		LOGGER.info("Setting value for email address");
		setValue(emailaddress, getEmailAddressInput());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setPassword(String password) {
		LOGGER.info("Setting value for password");
		setValue(password, getPasswordInput());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setPhoto() {
		LOGGER.info("Setting photo");
		click(getPhotoImage());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setMobileNumber(String mobileno) {
		LOGGER.info("Setting mobile number");
		setValueWithoutValidation(mobileno, getMobileNumber());
		return this;
	}
	
	public RegisterAccountMangerPersonalDetailsPage setMobileNumberRm(String mobileno) {
		LOGGER.info("Setting mobile number");
		setValueWithoutValidation(mobileno, getMobileNumberRm());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage selectAgeRange(String age) {
		LOGGER.info("Selecting age range");
		click(getAgeRangeDropdown());
		click(getAgeRangeOptionElement(age));
		return this;
	}
	
	public RegisterAccountMangerPersonalDetailsPage selectFirstAvailableAgeRange() {
		LOGGER.info("Selecting first available range");
		click(getAgeRangeDropdown());
		agerange = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage selectSmsRemindersNoOption() {
		LOGGER.info("Selecting 'SMS reminder NO option' ");
		click(getSmsRemindersRadioNoOption());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage selectSmsReminderYesoption() {
		LOGGER.info("Selecting 'SMS remainder YES option' ");
		click(getSmsRemindersRadioYesOption());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage selectGender(String gender) {
		LOGGER.info("Selecting gender");
		click(getGenderDropdown());
		click(getGenderOptionElement(gender.toString()));
		return this;
	}
	
	public RegisterAccountMangerPersonalDetailsPage selectFirstAvailableGenderValue() {
		LOGGER.info("Selecting first availble gender");
		click(getGenderDropdown());
		gender = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage selectCountry(String country) {
		LOGGER.info("Selecting country");
		click(getCountryDropdown());
		click(getCountryOptionElement(country));
		return this;
	}
	
	public RegisterAccountMangerPersonalDetailsPage selectFirstAvailableCountryValue() {
		LOGGER.info("Selecting first available country");
		click(getCountryDropdown());
		country = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage selectNationality(String nationality) {
		LOGGER.info("Selecting nationality");
		click(getNationalityDropdown());
		click(getNationalityOptionElement(nationality));
		return this;
	}
	
	public RegisterAccountMangerPersonalDetailsPage selectFirstAvailableNationality() {
		LOGGER.info("Selecting first available nationality");
		click(getNationalityDropdown());
		nationality = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setAddress(String Address) {
		LOGGER.info("Setting value for address");
		click(getAddressInput());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setAddressLine1(String address1) {
		LOGGER.info("Setting value for address line 1");
		setValue(address1, getAddressLine1Input());
		return this;
	}
 
	public RegisterAccountMangerPersonalDetailsPage setAddressLine2(String address2) {
		LOGGER.info("Setting value for address line 2");
		setValue(address2, getAddressLine2Input());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setCity(String city) {
		LOGGER.info("Setting value for city");
		setValue(city, getCityInput());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setState(String state) {
		LOGGER.info("Setting value for state");
		setValue(state, getStateInput());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setCounty(String county) {
		LOGGER.info("Setting value for country");
		setValue(county, getCountyInput());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage setPostcode(String postcode) {
		LOGGER.info("Setting value for post code");
		setValue(postcode, getPostcodeInput());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage clickPrivacyNotice() {
		LOGGER.info("Setting value for privacy notices");
		click(getPrivacyNoticeCheckbox());
		return this;
	}

	public RegisterAccountMangerPersonalDetailsPage clickRegisterButton() {
		LOGGER.info("Clicking register button");
		click(getRegisterButton());
		return this;
	}
	
	public RegisterAccountMangerPersonalDetailsPage clickFillManually() {
		LOGGER.info("Clicking 'Fill manually' link");
		click(getFillInManuallyLink());
		return this;
	}
	
	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details ");
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}
	
	public RegisterAccountMangerPersonalDetailsPage toasterMessageValidation(String toaster) {
		LOGGER.info("Validating toaster message");
		//String expected=DBUtils.getResultFromPostgresDB(toaster);
		String Actual=getText(getRegisterToasterMsg());
		Assert.assertEquals(Actual, toaster);
		return this;
	}
	
	public RegisterAccountMangerPersonalDetailsPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		return this;
	}

	
	public RegisterAccountMangerPersonalDetailsPage submitAccountMgrDetails(AccountManagerPersonalDetails accountManagerPersonalDetails ) {
		LOGGER.info("Submitting account manager detials");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				//click(getPasswordInput());
				setValue(accountManagerPersonalDetails.getPassword(), getPasswordInput());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
	
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			setMobileNumber(accountManagerPersonalDetails.getMobileNumber());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {

			setMobileNumberRm(accountManagerPersonalDetails.getMobileNumber());
		}
		
		selectAgeRange(accountManagerPersonalDetails.getAgeRange());
		selectGender(accountManagerPersonalDetails.getGender());
		selectCountry(accountManagerPersonalDetails.getCountry());
		selectNationality(accountManagerPersonalDetails.getNationality());
		clickFillManually();
		setAddressLine1(accountManagerPersonalDetails.getAddress1());
		setAddressLine2(accountManagerPersonalDetails.getAddress2());
		setCity(accountManagerPersonalDetails.getCity());
		setState(accountManagerPersonalDetails.getState());
		setCounty(accountManagerPersonalDetails.getCounty());
		setPostcode(accountManagerPersonalDetails.getPostcode());
		clickPrivacyNotice();
		clickRegisterButton();	
		return this;
		
		
	}
	
	
	public RegisterAccountMangerPersonalDetailsPage submitAccountMgrDetailsRm(AccountManagerPersonalDetails accountManagerPersonalDetails ) {
		LOGGER.info("Submitting account manager detials");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getPasswordInput());
				setValue(accountManagerPersonalDetails.getPassword(), getPasswordInput());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
		setMobileNumberRm(accountManagerPersonalDetails.getMobileNumber());
		selectAgeRange(accountManagerPersonalDetails.getAgeRange());
		selectGender(accountManagerPersonalDetails.getGender());
		selectCountry(accountManagerPersonalDetails.getCountry());
		selectNationality(accountManagerPersonalDetails.getNationality());
		clickFillManually();
		setAddressLine1(accountManagerPersonalDetails.getAddress1());
		setAddressLine2(accountManagerPersonalDetails.getAddress2());
		setCity(accountManagerPersonalDetails.getCity());
		setState(accountManagerPersonalDetails.getState());
		setCounty(accountManagerPersonalDetails.getCounty());
		setPostcode(accountManagerPersonalDetails.getPostcode());
		clickPrivacyNotice();
		clickRegisterButton();
		return this;
		
	}
	
	
	public RegisterAccountMangerPersonalDetailsPage validateAMPersonalDeatilsErrorMsgValidation() {
		
		LOGGER.info("Validating Account Manager persoanl details error msg validation");	
		shortWait();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getPasswordErrorMsg()),"Password fileds error message not present");
		softAssert.assertTrue(isElementVisible(getAageRangeDrodownErrorMsg()),"Age-Range error message not present");
		softAssert.assertTrue(isElementVisible(getGenderDropdownErrorMeg()),"Gender error message not present");
		softAssert.assertTrue(isElementVisible(getNationalDropdownErrorMeg()),"Nationality error message not present");
		softAssert.assertTrue(isElementVisible(getAddressInputErrorMsg()),"Address error message not present");
		
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			softAssert.assertTrue(isElementVisible(getPrivacyNoticeChecboxErrorMsg(ThriveAppSharedData.PRIVACY_NOTICE_ERROR_MSG.getValue())),"privacy notice error message not present");
		}
		else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			softAssert.assertTrue(isElementVisible(getPrivacyNoticeChecboxErrorMsg(ThriveAppSharedData.PRIVACY_NOTICE_ERROR_MSG_RM.getValue())),"privacy notive error message not present");
		}
		
		softAssert.assertAll();
		return this;
	}
	
	public RegisterAccountMangerPersonalDetailsPage validateAccountManagerPersonalDetails(AccountManagerUpdateDetails accountManagerUpdateDetails) {
		LOGGER.info("Validating Account Manager persoanl details");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getFillInManuallyLink());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
		String getFirstName;
		String getLastName;
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			 getFirstName = getAttributeByValue("value", getFirstNameInput());
			 getLastName = getAttributeByValue("value", getLastNameInput());
		}else {
			getFirstName = getAttributeByValue("value", getFirstNameInputRm());
			getLastName = getAttributeByValue("value", getLastNameInputRm());
		}
		
		String getMobileNo = getAttributeByValue("value", getMobileNumber()).replaceAll(" ", "");
		String getAddress1 = getAttributeByValue("value", getAddressLine1Input());
		String getAddress2 = getAttributeByValue("value", getAddressLine2Input());
		String getCity = getAttributeByValue("value", getCityInput());
		String getState = getAttributeByValue("value", getStateInput());
		String getCounty = getAttributeByValue("value", getCountyInput());
		String getPostcode = getAttributeByValue("value", getPostcodeInput());
		String getGender = getText(getGenderValue());
		String getNationality = getText(getNationalityValue());
		String getCountry = getText(getCountryValue());
		String getAgeRange = getText(getAgeRangeValue());
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getFirstName.equalsIgnoreCase(accountManagerUpdateDetails.getFirstName()),"Firstname is not updated");
		softAssert.assertTrue(getLastName.equalsIgnoreCase(accountManagerUpdateDetails.getLastName()),"Lastname is not updated");
		softAssert.assertTrue(getMobileNo.equalsIgnoreCase(accountManagerUpdateDetails.getMobileNumber()),"Mobile No is not updated");
		softAssert.assertTrue(getAddress1.equalsIgnoreCase(accountManagerUpdateDetails.getAddress1()),"Address1 is not updated");
		softAssert.assertTrue(getAddress2.equalsIgnoreCase(accountManagerUpdateDetails.getAddress2()),"Address2 is not updated");
		softAssert.assertTrue(getCity.equalsIgnoreCase(accountManagerUpdateDetails.getCity()),"City is not updated");
		softAssert.assertTrue(getState.equalsIgnoreCase(accountManagerUpdateDetails.getState()),"State is not updated");
		softAssert.assertTrue(getCounty.equalsIgnoreCase(accountManagerUpdateDetails.getCounty()),"County is not updated");
		softAssert.assertTrue(getPostcode.equalsIgnoreCase(accountManagerUpdateDetails.getPostcode()),"Postcode is not updated");
		softAssert.assertTrue(getGender.equalsIgnoreCase(gender));
		softAssert.assertTrue(getNationality.equalsIgnoreCase(nationality));
		softAssert.assertTrue(getAgeRange.equalsIgnoreCase(agerange));
		softAssert.assertTrue(getCountry.equalsIgnoreCase(country));
		softAssert.assertAll();
		
		return this;
	}
	

}