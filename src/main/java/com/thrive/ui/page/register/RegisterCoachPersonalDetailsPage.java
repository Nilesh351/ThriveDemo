package com.thrive.ui.page.register;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.user_details.CoachUpdateDetails;
import com.thrive.common.testdata.pojos.user_details.GlobalCoachProfileDetails;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DateTimeUtils.TimeFormat;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.category.CoachCategoriesPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RegisterCoachPersonalDetailsPage extends BaseTestPage {
	
	String nationality;
	String ethnicity;
	String gender;
	String country;
	String agerange;
	String region;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	String privacyErrorMsg = ThriveAppSharedData.PRIVACY_NOTICE_ERROR_MSG.getValue();
	
	private By getCoachOfferCheckbox(String checkboxLabel) {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_coach.cn3pkv3fs7b.label')]/following-sibling::div//label[text()='"+checkboxLabel+"']"));
	}
	
	private By getFirstNameInput() {
		return By.xpath(getXpathByText(".//label[normalize-space(text())='questions.registeration_coach.osr21gni4u.label']/..//following-sibling::input"));
	}
	
	private By getFirstNameInputRm() {
		return By.xpath(getXpathByText(".//label[normalize-space(text())='questions.registeration_coach.u8u7kotduip.label']/..//following-sibling::input"));
	}
	
	private By getLastNameInput() {
		return By.xpath(getXpathByText(".//label[normalize-space(text())='questions.registeration_coach.lw8m09ooj0b.label']/..//following-sibling::input"));
	}
	
	private By getLastNameInputRm() {
		return By.xpath(getXpathByText(".//label[normalize-space(text())='questions.registeration_coach.19mzq6hdewmi.label']/..//following-sibling::input"));
	}
	
	private By getPasswordInput() {
		return By.xpath(".//input[1][@name='password']");
	}
	
	private By getCoachingExperienceTextArea() {
		return By.xpath(getXpathByText(".//label[(text())='private.sessions_detail.coaching_experience']/following-sibling::textarea"));
	}
	
	private By getTestimonialsTextArea() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_coach.testimonials.label']/following-sibling::textarea"));
	}
	
	private By getFillAddressLink() {
		//return By.xpath(getXpathByText(".//a[contains(text(),'ui.forms.typing_address_fill')]"));
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
	
	private By getcountyOptionElement(String country) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()='"+country+"']");
	}
	
	private By getpostcodeInput() {
		return By.xpath(".//input[@id='postcode']");
	}
	
	private By getSelectTimeArrrowElement() {
		return By.xpath(".//ng-select[@name='clock_hours']");
	}
	
	private By getTimeFormatElement(TimeFormat timeformat) {
		return By.xpath(".//span[contains(@class,'ng-option-label') and text()='"+timeformat.getValue()+"']");
	}
	
	private By getMobileInput() {
		return By.xpath(".//input[@id='mobile_number']");
	}
	
	private By getMobileInputRm() {
		return By.xpath(".//input[@id='mobile_number']");
	}
	
	private By getGenderDropdown() {
		return By.xpath(".//ng-select[@name='gender']");
	}
	
	private By getGenderDropdownRm() {
		return By.xpath(".//ng-select[@name='gender']");
	}
	
	private By getGenderOptionElement(String gender) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+gender+"']");
	}
	
	private By getEthnicityDropdown() {
		return By.xpath(".//ng-select[@id='ethnicity']");
	}
	
	private By getEthnicityOptionElement(String ethnicity) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+ethnicity+"']");
	}
	
	private By getAgeDropdown() {
		return By.xpath(".//ng-select[@name='age_range']");
	}
	
	private By getAgeOptionElement(String age) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+age+"']");
	}
	
	private By getNationalityDropdown() {
		return By.xpath(".//ng-select[@name='nationality']");
	}
	
	private By getNationalityElement(String nationality) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+nationality+"']");
	}
	
	private By getRegionDropdown() {
		return By.xpath(".//ng-select[@name='regions']");
	}
	
	private By getRegionElement(String region) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+region+"']");
	}
	
	private By getCountryDropdown() {
		return By.xpath("//ng-select[@name='country']");
	}
	
	private By getCountryDropdownRm() {
		return By.xpath(".//ng-select[@name='country']");
	}
	
	private By getCountryOptionElement(String country) {
		return By.xpath(".//span[contains(@class, 'ng-star') and text()='"+country+"']");
	}
	
	private By getFirstAvailableElementFromDropdown() {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getGenderValue() {
		return By.xpath(".//ng-select[@name='gender']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getEthnicityValue() {
		return By.xpath(".//ng-select[@name='ethnicity']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getNationalityValue() {
		return By.xpath(".//ng-select[@name='nationality']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getRegionValue() {
		return By.xpath(".//ng-select[@name='regions']//div[@class='ng-value ng-star-inserted']//span");
	}
	
	private By getCountryValue() {
		return By.xpath(".//ng-select[@name='country']//div[@class='ng-value ng-star-inserted']//span");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step2.next']"));
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step2.previous']"));
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	private By getEmploymentLink() {
		return By.xpath(getXpathByText(".//li[(text())='register.coach.register.personal.step_2_name']"));
	}
	
	private By getLanguagesLink() {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//li[contains(text(),'LANGUAGES')]");
		}else {
			return By.xpath(getXpathByText(".//li[normalize-space(text())='COMPÉTENCES LINGUISTIQUES']"));
		}
		
	}
	
	private By getSkillsAndQualificationsLink() {
		return By.xpath(getXpathByText(".//li[(text())='register.coach.register.personal.step_3_name']"));
	}
	
	private By getCorporateExpertise() {
		return By.xpath(getXpathByText(".//li[(text())='register.coach.register.personal.step_4_name']"));
	}
	
	private By getCoachingExperienceLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(".//span[text()='registration_steps.defwppo968u.label']"));
		}else {
			if(Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(getXpathByText(".//li[(text())='private.profile.coach.coaching_experience']"));
				} else {
				return By.xpath(".//li[normalize-space(text())='EXPÉRIENCE DE COACHING']");
				}
		}
		
	}
	
	private By getProfileForClientLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(getXpathByText("//span[contains(text(),'registration_steps.dyy1ob7r348.label')]"));
		}else {
			return By.xpath(getXpathByText("//li[contains(text(),'registration_steps.dyy1ob7r348.label')]"));
		}
		
	}
	
	private By getPasswordErrorMsg() {
		return By.xpath(getXpathByText(".//input[1][@id='password']/..//p[text()='"+errorMsg+"']"));
	}
	
	private By getAddressInputErrorMsg() {
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			return By.xpath(
					getXpathByText(".//input[contains(@name,'find-address')]/..//p[text()='"+errorMsg+"']"));
		} else {
			if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(
						getXpathByText(".//input[contains(@name,'find-address')]/..//p[text()='" + errorMsg + "']"));
			} else {
				return By.xpath(getXpathByText(
						".//small[contains(text(),'entrer votre adresse ci-dessous et faites votre choix dans la liste')]/..//p[text()='"
								+ errorMsg + "']"));
			}
		}
	}
	
	private By getGenderDropdownErrorMeg() {
		return By.xpath(getXpathByText(".//ng-select[@name='gender']/..//p[text()='"+errorMsg+"']"));
	}
	
	private By getRegionErrorMsg() {
		return By.xpath(getXpathByText(".//ng-select[@name='regions']/..//p[text()='"+errorMsg+"']"));
	}
	
	private By getCountryErrorMsg() {
		return By.xpath(getXpathByText(".//ng-select[@name='country']/..//p[text()='"+errorMsg+"']"));
	}
	
	private By getNationalityDropdownErrorMeg() {
		return By.xpath(getXpathByText(".//ng-select[@name='nationality']/..//p[text()='"+errorMsg+"']"));
	}
	
	private By getMobileNumberErrorMsg() {
		return By.xpath(getXpathByText(".//input[@id='mobile_number']/../..//following-sibling::div//p[contains(text(),'"+errorMsg+"')]"));
	}
	
	public RegisterCoachEmploymentPage clickEmployment() {
		LOGGER.info("Clicking employment");
		click(getEmploymentLink());
		return new RegisterCoachEmploymentPage();
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
		
	private By getCoachCategoriesLink() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//li[text()='private.profile.coach.categories_list_title']"));
		} else {
			return By.xpath("//li[contains(text(),'CATÉGORIE DE COACHING')]");
		}
	}
	
	public CoachCategoriesPage clickCoachCategories() {
		LOGGER.info("Clicking coach categories");
		click(getCoachCategoriesLink());
		return new CoachCategoriesPage();
	}
	
	public RegisterCoachLanguagesPageRm clickLanguages() {
		LOGGER.info("Clicking Langugaes");
		click(getLanguagesLink());
		return new RegisterCoachLanguagesPageRm();
	}
	
	public RegisterCoachSkillsAndQualificationPage clickSkillsAndQualifications() {
		LOGGER.info("Clicking skills and qualification");
		click(getSkillsAndQualificationsLink());
		return new RegisterCoachSkillsAndQualificationPage();
	}
	
	public RegisterCoachCorporateExpPage clickCorporateExpertise() {
		LOGGER.info("Clicking Corporate expertise");
		click(getCorporateExpertise());
		return new RegisterCoachCorporateExpPage();
	}
	
	public RegisterCoachMentoringExpPage clickCoachingExperience() {
		LOGGER.info("Clicking Coaching experience");
		isElementPresent(getCoachingExperienceLink());
		click(getCoachingExperienceLink());
		return new RegisterCoachMentoringExpPage();
	}
	
	public RegisterCoachProfileForClients clickProfileForClient() {
		LOGGER.info("Clicking Profile For client");
		click(getProfileForClientLink());
		return new RegisterCoachProfileForClients();
	}
	
	public RegisterCoachDepartmentPage clickNext() {
		LOGGER.info("Clicking Next button");
		waitUntilElementIsVisible(getPasswordInput());
		click(getNextButton());
		return new RegisterCoachDepartmentPage();
	}
	
	private By getSSOUserNameInput() {
		return By.xpath(".//label[contains(text(),'SSO')]/following-sibling::input");
	}
	
	
	
	public RegisterCoachPersonalDetailsPage selectGender(String gender) {
		LOGGER.info("Selecting gender");
		click(getGenderDropdown());
		click(getGenderOptionElement(gender));
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectGenderRm(String gender) {
		LOGGER.info("Selecting gender");
		click(getGenderDropdownRm());
		click(getGenderOptionElement(gender));
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectFirstAvailableGenderValue() {
		LOGGER.info("Selecting first available gender");
		click(getGenderDropdown());
		gender = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectCountry(String country) {
		LOGGER.info("Selecting country");
		click(getCountryDropdown());
		click(getCountryOptionElement(country));
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage SelectCountryRm(String country) {
		LOGGER.info("Selecting country");
		click(getCountryDropdownRm());
		click(getCountryOptionElement(country));
		return this;

	}
	
	public RegisterCoachPersonalDetailsPage selectFirstAvailableCountryValue() {
		LOGGER.info("Selecting first available country");
		click(getCountryDropdown());
		country = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectEthinicity(String ethnicity) {
		LOGGER.info("Selecting ethinicity");
		click(getEthnicityDropdown());
		click(getEthnicityOptionElement(ethnicity));
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage SelectFirstAvailableEthnicity() {
		LOGGER.info("Selecting first available ethinicity");
		click(getEthnicityDropdown());
		ethnicity = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectAge(String age) {
		LOGGER.info("Selecting age");
		click(getAgeDropdown());
		click(getAgeOptionElement(age));
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectFirstAvailableAgeRange() {
		LOGGER.info("Selecting first available age range");
		click(getAgeDropdown());
		agerange = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	
	public RegisterCoachPersonalDetailsPage selectNationality(String nationality) {
		LOGGER.info("Selecting Nationality");
		click(getNationalityDropdown());
		click(getNationalityElement(nationality));
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectFirstAvailableNationality() {
		LOGGER.info("Selecting first available nationality");
		click(getNationalityDropdown());
		nationality = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectRegion(String region) {
		click(getRegionDropdown());
		click(getRegionElement(region));
		return this;
	}
	
	private By getRegionDropdownRm() {
		return By.xpath(".//ng-select[@name='regions']");
	}
	
	public RegisterCoachPersonalDetailsPage selectRegionRm(String region) {
		click(getRegionDropdownRm());
		click(getRegionElement(region));
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage selectFirstAvailabelRegion() {
		LOGGER.info("Selecting first available region");
		click(getRegionDropdown());
		region = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	
	
	public RegisterCoachPersonalDetailsPage setFirstName(String firstname) {
		LOGGER.info("Setting value for first name");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(firstname, getFirstNameInput());
		}else {
			setValue(firstname, getFirstNameInputRm());
		}
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setLastName(String lastName) {
		LOGGER.info("Setting value for last name");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(lastName, getLastNameInput());
		}else {
			setValue(lastName, getLastNameInputRm());
		}
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setMobileNumber(String mobile) {
		LOGGER.info("Setting value for mobile number");
		setValueWithoutValidation(mobile, getMobileInput());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setMobileNumberRm(String mobile) {
		LOGGER.info("Setting value for mobile number");
		setValueWithoutValidation(mobile, getMobileInputRm());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage clickFillInAddressManuallyLink() {
		LOGGER.info("Clicking 'Fill in address manually' link");
		click(getFillAddressLink());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setAddress1(String address1) {
		LOGGER.info("Setting value for address1");
		setValue(address1, getAddress1Input());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setAddress2(String address2) {
		LOGGER.info("Setting value for address2");
		setValue(address2, getAddress2Input());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setCity(String city) {
		LOGGER.info("Setting value for citys");
		setValue(city, getCityInput());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setState(String state) {
		LOGGER.info("Setting value for state");
		setValue(state, getStateInput());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setCounty(String county) {
		LOGGER.info("Setting value for country");
		setValue(county, getcountyInput());
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage setPostCode(String postcode) {
		LOGGER.info("Setting value for post code");
		setValue(postcode, getpostcodeInput());
		return this;
	}
	
	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}
	
	public RegisterCoachPersonalDetailsPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		return this;
	}
	
	public RegisterCoachPersonalDetailsPage enterAddres(GlobalCoachProfileDetails globalCoachProfileDetails) {
		setValue(globalCoachProfileDetails.getAddress1(), getAddress1Input());
		setValue(globalCoachProfileDetails.getAddress2(), getAddress2Input());
		setValue(globalCoachProfileDetails.getCity(), getCityInput());
		setValue(globalCoachProfileDetails.getState(), getStateInput());
		setValue(globalCoachProfileDetails.getCounty(), getcountyInput());
		setValue(globalCoachProfileDetails.getPostcode(), getpostcodeInput());
		return this;
	}
	
	public RegisterCoachMentoringExpPage submitCoachPersonalDetails(GlobalCoachProfileDetails globalCoachProfileDetails) {
		LOGGER.info("Submitting coach personal details");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getPasswordInput());
				setValueWithoutValidation(globalCoachProfileDetails.getPassword(), getPasswordInput());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
		setValueWithoutValidation(globalCoachProfileDetails.getMobileNumber(), getMobileInput());
		selectGender(globalCoachProfileDetails.getGender());
		//selectEthinicity(globalCoachProfileDetails.getEthnicity());
		selectCountry(globalCoachProfileDetails.getCountry());
		//selectRegion(globalCoachProfileDetails.getRegion());
		selectNationality(globalCoachProfileDetails.getNationality());
		click(getFillAddressLink());
		enterAddres(globalCoachProfileDetails);
		clickNext();
		return new RegisterCoachMentoringExpPage();
	}
	
	public RegisterCoachLanguagesPageRm submitCoachPersonalDetailsRm(GlobalCoachProfileDetails globalCoachProfileDetails) {
		LOGGER.info("Submitting coach personal details");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getPasswordInput());
				setValueWithoutValidation(globalCoachProfileDetails.getPassword(), getPasswordInput());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
		setValueWithoutValidation(globalCoachProfileDetails.getMobileNumber(), getMobileInputRm());
		selectGenderRm(globalCoachProfileDetails.getGender());
		SelectCountryRm(globalCoachProfileDetails.getCountry());
		selectRegionRm(globalCoachProfileDetails.getRegion());
		selectNationality(globalCoachProfileDetails.getNationality());
		click(getFillAddressLink());
		enterAddres(globalCoachProfileDetails);
		clickNext();
		return new RegisterCoachLanguagesPageRm();
	}
	
	
	public RegisterCoachPersonalDetailsPage ValidateCoachPersonalDeatilsErrorValidation() {
		LOGGER.info("Validating coach personal details error validation");
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getPasswordErrorMsg()),"password field error message not present");
		softAssert.assertTrue(isElementVisible(getGenderDropdownErrorMeg()),"Gender field error message not present");
		softAssert.assertTrue(isElementVisible(getNationalityDropdownErrorMeg()),"Nationality field error message not present");
		softAssert.assertTrue(isElementPresent(getAddressInputErrorMsg()),"address field error message not present");
	//	softAssert.assertTrue(isElementVisible(getRegionErrorMsg()),"Region field error messge not present");
		softAssert.assertTrue(isElementVisible(getMobileNumberErrorMsg()),"Mobile Number field error message not present");
		softAssert.assertTrue(isElementVisible(getCountryErrorMsg()),"Country field error message not present");
		softAssert.assertAll();
		return this;
	}
	
	
	public RegisterCoachPersonalDetailsPage validateCoachPersonalDetails(CoachUpdateDetails coachUpdateDetails) {
		LOGGER.info("Validating coach personal details");
		String getGender=" ";
		String getNationality = "";
		
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				click(getFillAddressLink());
				getGender = getText(getGenderValue());
				getNationality = getText(getNationalityValue());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		
		String getFirstName;
		String getLastName;
		String getEthnicity =" ";
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			getFirstName = getAttributeByValue("value", getFirstNameInput());
			getLastName = getAttributeByValue("value", getLastNameInput());
			getEthnicity = getText(getEthnicityValue());
		}
		else {
			getFirstName = getAttributeByValue("value", getFirstNameInputRm());
			getLastName = getAttributeByValue("value", getLastNameInputRm());
		}
		
		String getMobileNo = getAttributeByValue("value", getMobileInput()).replaceAll(" ", "");
		String getAddress1 = getAttributeByValue("value", getAddress1Input());
		String getAddress2 = getAttributeByValue("value", getAddress2Input());
		String getCity = getAttributeByValue("value", getCityInput());
		String getState = getAttributeByValue("value", getStateInput());
		String getCounty = getAttributeByValue("value", getcountyInput());
		String getPostcode = getAttributeByValue("value", getpostcodeInput());
		
	//	String getRegion = getText(getRegionValue());
		String getCountry = getText(getCountryValue());
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getFirstName.equalsIgnoreCase(coachUpdateDetails.getFirstName()),"Firstname is not updated");
		softAssert.assertTrue(getLastName.equalsIgnoreCase(coachUpdateDetails.getLastName()),"Lastname is not updated");
		softAssert.assertTrue(getMobileNo.equalsIgnoreCase(coachUpdateDetails.getMobileNumber()),"Mobile No is not updated");
		softAssert.assertTrue(getAddress1.equalsIgnoreCase(coachUpdateDetails.getAddress1()),"Address1 is not updated");
		softAssert.assertTrue(getAddress2.equalsIgnoreCase(coachUpdateDetails.getAddress2()),"Address2 is not updated");
		softAssert.assertTrue(getCity.equalsIgnoreCase(coachUpdateDetails.getCity()),"City is not updated");
		softAssert.assertTrue(getState.equalsIgnoreCase(coachUpdateDetails.getState()),"State is not updated");
		softAssert.assertTrue(getCounty.equalsIgnoreCase(coachUpdateDetails.getCounty()),"County is not updated");
		softAssert.assertTrue(getPostcode.equalsIgnoreCase(coachUpdateDetails.getPostcode()),"Postcode is not updated");
		softAssert.assertTrue(getGender.equalsIgnoreCase(gender));
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			softAssert.assertTrue(getEthnicity.equalsIgnoreCase(ethnicity));
		}
		softAssert.assertTrue(getNationality.equalsIgnoreCase(nationality));
//		softAssert.assertTrue(getRegion.equalsIgnoreCase(region));
		softAssert.assertTrue(getCountry.equalsIgnoreCase(country));
		softAssert.assertAll();
		return this;
	}
	

}