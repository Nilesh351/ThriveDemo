package com.thrive.ui.page.register;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.pojos.user_details.ClientPersonalDetails;
import com.thrive.common.testdata.pojos.user_details.ClientUpdateDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DateTimeUtils.TimeFormat;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.credits.ClientsCreditPage;

public class RegisterClientInformationPage extends UserManagementCommonPage {
	
	String nationality;
	String ethnicity;
	String gender;
	String country;
	String agerange;
	String firstlang;
	String dateformat;
	String timeformat;
	String getSSOid;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	
	private By getAddPProfilePicture() {
		return By.xpath(".//input[@name='photo']");
	}
	
	private By getMobileInput() {
		return By.xpath(".//input[@id='mobile_number']");
	}
	
	private By getYourUserNameInput() {
		return By.xpath(".//label[contains(text(),'your Username')]/following-sibling::input");
	}
	
	private By getFirstNameInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.x0d6zqwtfd.label')]/..//following-sibling::input"));
	}
	
	private By getFirstNameInputRm() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.lskjhoyf06q.label')]/..//following-sibling::input"));
	}
	
	private By getLastNameInput() {
		return By.xpath(getXpathByText(".//label[normalize-space(text())='questions.registeration_client.ofcgc5rlxh8.label']/..//following-sibling::input"));
	}
	
	private By getLastNameInputRm() {
		return By.xpath(getXpathByText(".//label[normalize-space(text())='questions.registeration_client.aljp0knmhvu.label']/..//following-sibling::input"));
	}
	
	private By getPasswordInput() {
		return By.xpath(".//input[1][@id='password']");
	}
	
	private By getAddressInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.lnj2c72ycgn.label')]/following-sibling::input"));
	}
	
	private By getAddressSuggestionElement(String address) {
		return By.xpath(".//*[contains(text(), '"+address+"')]");
	}
	
	private By getAgeRangeDrodown() {
		return By.xpath("//ng-select[@name='age_range']");
	}
	
	private By getAgeRangeDropdownvalue(String age) {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+age+"']");
	}
	
	private By getGenderDropdown() {
		return By.xpath("//ng-select[@name='gender']");
	}
	
	private By getGenderDropdownElement(String gender) {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+gender+"']");
	}
	
	private By getEthnicityDropdown() {
		return By.xpath("//ng-select[@name='ethnicity']");
	}
	
	private By getEthnicityDropdownValue(String ethnicity) {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+ethnicity+"']");
	}
	
	private By getCountryDropdown() {
		return By.xpath("//ng-select[@name='country']");
	}
	
	private By getCountryDropdownValue(String country) {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+country+"']");
	}
	
	private By getNationalityDropdwon() {
		return By.xpath("//ng-select[@name='nationality']");
	}
	
	private By getNationalityDropdwonValue(String nationality) {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+nationality+"']");
	}
	
	private By getSSOUserNameInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'private.profile.SSO')]/../following-sibling::input"));
	}
	
	private By getCompanyNameInput() {
		return By.xpath(".//label[contains(text(),'Company name')]/following-sibling::input");
	}
	
	private By getSelectTimeArrrowElement() {
		return By.xpath(".//ng-select[@name='clock_hours']");
	}
	
	private By getTimeFormatElement(TimeFormat timeformat) {
		return By.xpath(".//span[contains(@class,'ng-option-label') and text()='"+timeformat.getValue()+"']");
	}
	
	
	private By getTimeFormatDropdown() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_client.ysudbtpax9b.label')]/following-sibling::ng-select"));
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.client.step2.next']"));
	}
	
	private By getFillAddressLink() {
		return By.xpath("//input[@id='find-address']//following-sibling::a");
	}
	
	private By getAddress1Input() {
		return By.xpath(".//input[@id='address1']");
	}
	
	private By getAddress2Input() {
		return By.xpath(".//input[@id='address2']");
	}
	
	private By getCityInput() {
		return By.xpath(".//input[@id='city']");
	}
	
	private By getStateInput() {
		return By.xpath(".//input[@id='state']");
	}
	
	private By getcountyInput() {
		return By.xpath(".//input[@id='county']");
	}
	
	private By getpostcodeInput() {
		return By.xpath(".//input[@id='postcode']");
	}
	
	private By getDateFormatDropdown() {
		return By.xpath(".//ng-select[@name='date_format']");
	}
	
	private By getDateFormatOptionElement(String dateFormat) {
		return By.xpath(".//div[contains(@class,'ng-option')]//span[contains(@class, 'ng-star') and text()='"+dateFormat+"']");
	}
	
	private By getLanguagesDropdown() {
		return By.xpath(".//ng-select[@name='languages']");
	}
	
	private By getLanguagesRemoveIcon() {
		return By.xpath(".//ng-select[@name='languages']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getLanguagesOptionElement(String languages) {
		return By.xpath(".//span[contains(@class,'ng-star') and text()='"+languages+"']");
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	

	private By getFirstAvailableElementFromDropdown() {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getEmploymentlink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(".//span[contains(text(),'registration_steps.eh65uxohwi.label')]"));
		} else {
		   return By.xpath(getXpathByText(".//li[(text())='register.client.register.personal.step_2_name']"));
		}
	}
	
	private By getCareerLink() {
		return By.xpath(getXpathByText(".//li[(text())='register.client.register.personal.step_3_name']"));
	}
	
	private By getTimeFormatDropdwonvalue(String timeformat) {
		return By.xpath(".//span[contains(@class,'ng-option-label') and text()='"+timeformat+"']");
	}
	
	private By getTimeFormatValue() {
		return By.xpath(".//ng-select[@name='clock_hours']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getDateFormatValue() {
		return By.xpath(".//ng-select[@name='date_format']//div[@class='ng-value ng-star-inserted']//span");
	}
	
	private By getNationalityValue() {
		return By.xpath(".//ng-select[@name='nationality']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getCountryValue() {
		return By.xpath(".//ng-select[@name='country']//div[@class='ng-value ng-star-inserted']//span");
	}
	
	private By getEthnicityValue() {
		return By.xpath(".//ng-select[@name='ethnicity']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getGenderValue() {
		return By.xpath(".//ng-select[@name='gender']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getAgerangeValue() {
		return By.xpath(".//ng-select[@name='age_range']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getLanguagesValue(String country) {
		return By.xpath(".//span[text()='"+country+"']");
	}
	
    private By getAgeRangeDropdown() {
    	return By.xpath(".//ng-select[@name='age_range']");
    }
    
    private By getAgeRangeDrodownErrorMsg() {
		return By.xpath(getXpathByText(".//ng-select[@name='age_range']/..//p[text()='"+errorMsg+"']"));	
	}
    
    private By getGenderDropdownErrorMeg() {
		return By.xpath(getXpathByText(".//ng-select[@name='gender']/..//p[text()='"+errorMsg+"']"));
	}
    
    private By getCountryDropdownErrorMsg() {
		return By.xpath(getXpathByText(".//ng-select[@name='country']/..//p[text()='"+errorMsg+"']"));
	}
    
    private By getAddressInputErrorMsg() {
    	if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//small[contains(text(),'ui.forms.typing_address_hint')]/..//p[text()='"+errorMsg+"']"));
    	} else {
    		return By.xpath(getXpathByText(".//small[contains(text(),'entrer votre adresse ci-dessous et faites votre choix dans la liste')]/..//p[text()='"+errorMsg+"']"));
    	}
    }
    
    private By getMobileNumberErrorMsg() {
    	return By.xpath(getXpathByText(".//label[@for='mobile_number']/../..//p[text()='"+errorMsg+"']"));
    }

    
    private By getpasswordErrorMsg() {
		return By.xpath(getXpathByText(".//input[1][@id='password']//following-sibling::div/p[text()='"+errorMsg+"']"));
	}
	
    private By getLanguageDropdownErrorMsg() {
		return By.xpath(getXpathByText(".//ng-select[@name='languages']//following-sibling::div/p[text()='"+errorMsg+"']"));
	}
    
    private By getChangeEmailAddressLink() {
    	return By.xpath(getXpathByText(".//a[contains(text(),'private.profile.coach.change_email_address')]"));
    }
    
    private By getCreditsLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(".//span[normalize-space(text())='Credits']");
		} else {
			if(Config.getLocalizationLanguage().contains("en")){
				return By.xpath(".//li[normalize-space(text())='CREDITS']");
			}else {
				return By.xpath("//form//ul//li[3]");
			}
			
		}
	}
    
    private By getCoachingPrioritiesLink() {
    	return By.xpath(getXpathByText("//li[contains(text(),'registration_steps.xi9jjtdv42f.label')]"));
    }
    
    public RegisterClientCoachingPrioritiesPage clickCoachingPriorities() {
    	LOGGER.info("Clicking coaching priorities");
    	click(getCoachingPrioritiesLink());
    	return new RegisterClientCoachingPrioritiesPage();
    }
    
    public ChangeEmailAddressPage clickChangeEmailAddressLink() {
    	LOGGER.info("Clicking Change email address link");
    	click(getChangeEmailAddressLink());
    	return new ChangeEmailAddressPage();
    }

	public ClientsCreditPage clickCredit() {
		LOGGER.info("Clicking Credit link");
		shortWait();
		click(getCreditsLink());
		return new ClientsCreditPage();
	}
	
	public RegisterClientEmploymentStatusPage clickEmployment() {
		LOGGER.info("Clicking employment");
		click(getEmploymentlink());
		return new RegisterClientEmploymentStatusPage();
	}
	
	public RegisterClientCareerDetailsPage clickCareer() {
		LOGGER.info("Clicking Career link");
		click(getCareerLink());
		return new RegisterClientCareerDetailsPage();
	}
	
	public RegisterClientInformationPage setFirstName(String firstName) { 
		LOGGER.info("Setting value for first name");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(firstName, getFirstNameInput());
		}else {
			setValue(firstName, getFirstNameInputRm());
		}
		return this;
	}
	
	
	public RegisterClientInformationPage setLastName(String lastName) {
		LOGGER.info("Setting value for lasts name");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(lastName, getLastNameInput());
		}else {
			setValue(lastName, getLastNameInputRm());
		}
		
		return this;
	}
	
	public RegisterClientInformationPage setMobileNumber(String mobile) {
		LOGGER.info("Setting value for mobile number");
		setValueWithoutValidation(mobile, getMobileInput());
		return this;
	}
		
	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}
	
	public RegisterClientInformationPage selectDateFormat(String dateFormat) {
		LOGGER.info("Selecting date format");
		click(getDateFormatDropdown());
		click(getDateFormatOptionElement(dateFormat));
		return this;
	}
	
	public RegisterClientInformationPage SelectFirstAvailableDateFormat() {
		LOGGER.info("Selecting first available date format");
		click(getDateFormatDropdown());
		dateformat = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterClientInformationPage selectFirstTimeFormat() {
		LOGGER.info("Selecting first available time format");
		click(getSelectTimeArrrowElement());
		timeformat = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterClientInformationPage selectLanguage(List<String> languages) {
		LOGGER.info("Selecting language");
		click(getLanguagesDropdown());
		for(String language : languages) {
			click(getLanguagesOptionElement(language));
		}
		return this;
	}
	
	public RegisterClientInformationPage selectLanguageDropdownValue() {
		LOGGER.info("Selecting language from dropdown");
		click(getLanguagesDropdown());
		click(getLanguagesRemoveIcon());
		click(getLanguagesDropdown());
		firstlang = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	
	}
	
	public RegisterClientInformationPage selectAgeRange(String age) {
		LOGGER.info("Selecting age range");
		click(getAgeRangeDrodown());
		click(getAgeRangeDropdownvalue(age));
		return this;
	}
	
	public RegisterClientInformationPage selectFirstAvailableAgeRange() {
		LOGGER.info("Selecting first available age range");
		click(getAgeRangeDrodown());
		agerange = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterClientInformationPage selectGender(String gender) {
		LOGGER.info("Selecting gender");
		click(getGenderDropdown());
		click(getGenderDropdownElement(gender));
		return this;
	}
	
	public RegisterClientInformationPage selectFirstAvailableGender() {
		LOGGER.info("Selecting first available gender");
		click(getGenderDropdown());
		gender = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	
	public RegisterClientInformationPage clickFillAddressManuallyLink() {
		LOGGER.info("Clicking 'Fill address manually' link ");
		click(getFillAddressLink());
		return this;
	}
	
	public RegisterClientInformationPage selectEthnicity(String ethnicity) {
		LOGGER.info("Selecting ethnicity");
		click(getEthnicityDropdown());
		click(getEthnicityDropdownValue(ethnicity));
		return this;
	}
	
	public RegisterClientInformationPage SelectFirstAvailableEthnicity() {
		LOGGER.info("Selecting first available ethnicity");
		click(getEthnicityDropdown());
		ethnicity = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterClientInformationPage selectCountry(String country) {
		LOGGER.info("Selecting country");
		click(getCountryDropdown());
		click(getCountryDropdownValue(country));
		return this;
	}
	
	public RegisterClientInformationPage selectFirstAvailableCountry() {
		LOGGER.info("Selecting first available country");
		click(getCountryDropdown());
		country = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
		
	public RegisterClientInformationPage selectNationalityDropdwon(String nationality) {
		LOGGER.info("Selecting nationality");
		click(getNationalityDropdwon());
		click(getNationalityDropdwonValue(nationality));
		return this;
	}
	
	public RegisterClientInformationPage selectFirstAvailableNationality() {
		LOGGER.info("Selecting first available nationality");
		click(getNationalityDropdwon());
		nationality = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterClientInformationPage setAddress1(String address1) {
		LOGGER.info("Setting value for address1");
		setValue(address1, getAddress1Input());
		return this;
	}
	
	public RegisterClientInformationPage setAddress2(String address2) {
		LOGGER.info("Setting value for address2");
		setValue(address2, getAddress2Input());
		return this;
	}
	
	public RegisterClientInformationPage setCity(String city) {
		LOGGER.info("Setting value for city");
		setValue(city, getCityInput());
		return this;
	}
	
	public RegisterClientInformationPage setState(String state) {
		LOGGER.info("Setting value for state");
		setValue(state, getStateInput());
		return this;
	}
	
	public RegisterClientInformationPage setCounty(String county) {
		LOGGER.info("Setting value for country");
		setValue(county, getcountyInput());
		return this;
	}
	
	public RegisterClientInformationPage setPostCode(String postcode) {
		LOGGER.info("Setting value for post code");
		setValue(postcode, getpostcodeInput());
		return this;
	}
	
	public RegisterClientInformationPage setSSOUserId(String ssouserid) {
		LOGGER.info("Setting value for SSO user id");
		setValue(ssouserid, getSSOUserNameInput());
		return this;
	}
	
	
	
	public RegisterClientInformationPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		return this;
	}
	

	public RegisterClientEmploymentStatusPage submitPersonalDetails(ClientPersonalDetails clientPersonalDetails) {
		LOGGER.info("Submitting personal details");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getPasswordInput());
				setValueWithoutValidation(clientPersonalDetails.getPassword(), getPasswordInput());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		uploadProfileImage();
		setMobileNumber(clientPersonalDetails.getMobileNumber());
		//selectLanguage(clientPersonalDetails.getLanguages());
		click(getFillAddressLink());
		setValue(clientPersonalDetails.getAddress1(), getAddress1Input());
		setValue(clientPersonalDetails.getAddress2(), getAddress2Input());
		setValue(clientPersonalDetails.getCity(), getCityInput());
		click(getMobileInput());
		setValue(clientPersonalDetails.getCounty(), getcountyInput());
		setValue(clientPersonalDetails.getPostcode(), getpostcodeInput());
		//selectDateFormat(clientPersonalDetails.getDate());
		//click(getSelectTimeArrrowElement());
		//click(getTimeFormatElement(clientPersonalDetails.getTimeFormat()));
		selectAgeRange(clientPersonalDetails.getAgeRange());
		selectGender(clientPersonalDetails.getGender());
		
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			selectLanguage(clientPersonalDetails.getLanguages());
			selectCountry(clientPersonalDetails.getCountry());
			click(getSelectTimeArrrowElement());
		}	
		click(getNextButton());
		return new RegisterClientEmploymentStatusPage();
	}
	
	public RegisterClientInformationPage clickNextButton() {
		LOGGER.info("Click on next button");
		mediumWait();
		click(getNextButton());
		return this;
		
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


	public RegisterClientInformationPage validatePersonalDetailsErrorValidation() {
			
			LOGGER.info("Validating pesonal details error validation");
			
			SoftAssert softAssert = new SoftAssert();
			if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
				softAssert.assertTrue(isElementVisible(getCountryDropdownErrorMsg()),"Country error message not present");
				softAssert.assertTrue(isElementVisible(getAddressInputErrorMsg()),"Address error message not present");
			}
			
			softAssert.assertTrue(isElementVisible(getpasswordErrorMsg()),"error message not present for password");
		//	softAssert.assertTrue(isElementVisible(getLanguageDropdownErrorMsg()),"Error message not present for language");
			softAssert.assertTrue(isElementVisible(getGenderDropdownErrorMeg()),"Gender error message not present");
			softAssert.assertTrue(isElementVisible(getAgeRangeDrodownErrorMsg()),"error message not present for age range");
			softAssert.assertTrue(isElementVisible(getMobileNumberErrorMsg()),"error message not present for mobile number");
			softAssert.assertAll();
			return this;
		}
	
	
	public RegisterClientInformationPage validateClientPersonalDetails(ClientUpdateDetails clientUpdateDetails) {
		LOGGER.info("Validating client pesonal details");
		
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getFillAddressLink());
				 getSSOid = getAttributeByValue("value", getSSOUserNameInput());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
		String getFirstName;
		String getEthnicity =" ";
		String getLastName;
		String getNationality =" ";
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			getFirstName = getAttributeByValue("value", getFirstNameInput());
			getLastName = getAttributeByValue("value", getLastNameInput());
			getEthnicity = getText(getEthnicityValue());
			getNationality = getText(getNationalityValue());
		}else {
			getFirstName = getAttributeByValue("value", getFirstNameInputRm());
			getLastName = getAttributeByValue("value", getLastNameInputRm());
		}
		
		String getMobileNo = getAttributeByValue("value", getMobileInput()).replaceAll(" ", "");
		String getAddress1 = getAttributeByValue("value", getAddress1Input());
		String getAddress2 = getAttributeByValue("value", getAddress2Input());
		String getState = getAttributeByValue("value", getStateInput());
		String getCity = getAttributeByValue("value", getCityInput());
		String getCounty = getAttributeByValue("value", getcountyInput());
		String getPostcode = getAttributeByValue("value", getpostcodeInput());
		String getAgerange = getText(getAgerangeValue());
		String getGender = getText(getGenderValue());
		String getCountry = getText(getCountryValue());
		String getDateformat = getText(getDateFormatValue());
		String getTimeformat = getText(getTimeFormatValue());
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getFirstName.equalsIgnoreCase(clientUpdateDetails.getFirstName()),"Firstname is not updated");
		softAssert.assertTrue(getLastName.equalsIgnoreCase(clientUpdateDetails.getLastName()),"Lastname is not updated");
		softAssert.assertTrue(getMobileNo.equalsIgnoreCase(clientUpdateDetails.getMobileNumber()),"MobileNo is not updated");
		softAssert.assertTrue(getAddress1.equalsIgnoreCase(clientUpdateDetails.getAddress1()),"address1 is not updated");
		softAssert.assertTrue(getAddress2.equalsIgnoreCase(clientUpdateDetails.getAddress2()),"address2 is not updated");
		softAssert.assertTrue(getState.equalsIgnoreCase(clientUpdateDetails.getState()),"State is not updated");
		softAssert.assertTrue(getCity.equalsIgnoreCase(clientUpdateDetails.getCity()),"city is not updated");
		softAssert.assertTrue(getCounty.equalsIgnoreCase(clientUpdateDetails.getCounty()),"county is not updated");
		softAssert.assertTrue(getPostcode.equalsIgnoreCase(clientUpdateDetails.getPostcode()),"potcode is not updated");
		softAssert.assertTrue(getAgerange.equalsIgnoreCase(agerange),"AgeRange is not updated");
		softAssert.assertTrue(getGender.equalsIgnoreCase(gender),"Gender is not updated");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			softAssert.assertTrue(getEthnicity.equalsIgnoreCase(ethnicity),"ethnicity is not updated");
			softAssert.assertTrue(getNationality.equalsIgnoreCase(nationality),"nationality is not updated");
		}
		softAssert.assertTrue(getCountry.equalsIgnoreCase(country),"Country is not updated");
		softAssert.assertTrue(getDateformat.equalsIgnoreCase(dateformat),"dateformat is not updated");
		softAssert.assertTrue(getTimeformat.equalsIgnoreCase(timeformat),"timeformat is not updated");
		softAssert.assertTrue(isElementPresent(getLanguagesValue(country)));
		softAssert.assertAll();
		
		
		return this;
	}

}