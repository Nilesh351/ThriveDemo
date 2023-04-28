package com.thrive.common.testdata.pojos.user_details;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RegisterAccountMangerPersonalDetailsPage extends BaseTestPage {
	
	String nationality;
	String gender;
	String country;
	String agerange;

	private By getFirstNameInput() {
		return By.xpath(".//label[normalize-space(text())='First name']/following-sibling::input");
	}

	private By getLastNameInput() {
		return By.xpath(".//label[normalize-space(text())='Last name']/following-sibling::input");
	}

	private By getEmailAddressInput() {
		return By.xpath(".//label[normalize-space(text())='Email address']/following-sibling::input");
	}

	private By getPasswordInput() {
		return By.xpath(".//input[1][@id='password']");
	}

	private By getPhotoImage() {
		return By.xpath("//label[normalize-space(text())='Photo']/..//following-sibling::div/input[@id='photo']");
	}

	private By getMobileNumber() {
		return By.xpath(".//input[@id='phone']");
	}

	private By getAgeRangeDropdown() {
		return By.xpath(".//ng-select[@name='age_range']");
	}

	private By getAgeRangeOptionElement(String age) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='" + age + "']");
	}

	private By getSmsRemindersRadioNoOption() {
		return By.xpath(
				".//label[normalize-space(text())='Do you wish to receive SMS reminders']/..//following-sibling::div/label[normalize-space(text())='No']/span");
	}

	private By getSmsRemindersRadioYesOption() {
		return By.xpath(
				".//label[normalize-space(text())='Do you wish to receive SMS reminders']/..//following-sibling::div/label[normalize-space(text())='Yes']/span");
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
		return By.xpath(".//label[normalize-space(text())='Address']/following-sibling::input");
	}

	private By getPrivacyNoticeCheckbox() {
		return By.xpath(".//label[contains(text(),'Privacy Notice')]/..//following-sibling::div/div/div//span");
	}

	private By getFillInManuallyLink() {
		return By.xpath(".//a[text()='Fill in address manually']");
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
		return By.xpath(".//button[text()='Register']");
	}
	
	private By getRegisterToasterMsg() {
		return By.xpath(".//div[normalize-space(text())='Thank you for registering, once our team has approved your registration you will receive an email confirmation and have access to the system.']");
	}
	
	private By getUpdateDeatils() {
		return By.xpath(".//button[text()='Update details']");
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


	public RegisterAccountMangerPersonalDetailsPage setFirstName(String firstname) {
		LOGGER.info("Setting value for first name");
		setValue(firstname, getFirstNameInput());
		return this;
	}
	

	public RegisterAccountMangerPersonalDetailsPage setLastName(String lastname) {
		LOGGER.info("Setting value for last name");
		setValue(lastname, getLastNameInput());
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
		
		setMobileNumber(accountManagerPersonalDetails.getMobileNumber());
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
		
		String getFirstName = getAttributeByValue("value", getFirstNameInput());
		String getLastName = getAttributeByValue("value", getLastNameInput());
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
