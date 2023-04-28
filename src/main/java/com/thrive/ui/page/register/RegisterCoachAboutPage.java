package com.thrive.ui.page.register;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.user_details.CoachAboutDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RegisterCoachAboutPage extends BaseTestPage{

	String getCoachingSummary;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();

	private By getTestimonialTextArea() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'register.coach.step6.testimonial')]/../following-sibling::textarea"));
	}

	private By getAboutTextArea() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'register.coach.register.personal.step_6_name')]/../following-sibling::textarea"));
	}

	private By getSummryTextArea() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_coach.summary.label')]/..//following-sibling::textarea"));
	}

	private By getTopOrganizationsCoachedIn() {
		return By.xpath(".//label[contains(text(),'Top organisations coached in')]/../following-sibling::textarea");
	}

	private By getCoachExperiencelTextArea() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_coach.cpvylpx347k.label')]/../following-sibling::textarea"));
	}

	private By getTopNewIndustriesTextArea() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_coach.ze4x7b4qgde.label')]/../following-sibling::textarea"));
	}

	private By getCoachingSummaryTextArea() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_coach.coaching_summary.label')]/../following-sibling::textarea"));
	}

	private By getProfessionalSummaryTextArea() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_coach.professional_summary.label')]/../following-sibling::textarea"));
	}

	private By getCoachOfferCheckbox(String checkboxLabel) {
		return By.xpath(".//label[contains(text(),'Verified for')]/../following-sibling::div//label[text()='"
				+ checkboxLabel+"']");
	}

	private By getFirstAvailableCheckbox() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_coach.cn3pkv3fs7b.label')]/following-sibling::div//label"));
	}

	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step6.previous']"));
	}

	private By getRegisterButton() {
		// return
		// By.xpath(getXpathByText(".//button[text()='register.coach.step6.register']"));
		return By.xpath(getXpathByText(
				"//button[contains(text(),'register.coach.step6.previous')]//following-sibling::button"));
	}

	private By getConsentCheckbox() {
		return By.xpath(".//input[@id='email_updates']/following-sibling::span");
	}

	private By getCoachOfferselected(String offer) {
		return By.xpath(".//label[text()='" + offer + "']/../input");
	}

	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}

	private By getTestimonialErrorMsg() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'register.coach.step6.testimonial')]/../following-sibling::div/p[text()='"
						+errorMsg+"']"));
	}

	private By getAboutTextAreaErrorMsg() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'register.coach.register.personal.step_6_name')]/../following-sibling::div/p[text()='"
						+errorMsg+"']"));
	}

	private By getProfessionalSummaryErrorMsg() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_coach.professional_summary.label')]/../following-sibling::div/p[text()='"
						+errorMsg+"']"));
	}

	private By getTopNewIndustriesErrorMsg() {
		return By.xpath(".//label[contains(text(),'Top new industries ')]/../following-sibling::div/p[text()='"
				+errorMsg+"']");
	}

	private By getCoachingSummaryErrorMsg() {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(
					".//label[contains(text(),'questions.registeration_coach.coaching_summary.label')]/../following-sibling::div/p[text()='"
							+errorMsg+"']"));
		} else {
			return By.xpath(".//label[contains(text(),'En quelques mots')]/../following-sibling::div/p[text()='"
					+errorMsg+"']");
		}
	}

	private By getPrivacyPolicyErrorMsg(String privacyErrorMsg) {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(
					".//label[contains(text(),'questions.registeration_coach.p4e2muzand9.label')]/..//p[text()='"
							+ privacyErrorMsg + "']"));
		} else {
			return By.xpath(
					".//label[contains(text(),'Notice d’Information')]/..//p[contains(text(),'Ceci est une question obligatoire.')]");
		}
	}
	private By getHeadLineSummaryErrorMsg() {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(".//label[contains(text(),'Headline Summary')]/..//following-sibling::div//p[text()='"+errorMsg+"']"));
		} else {
			return By.xpath(".//label[contains(text(),'Présentation Synthètique')]/../following-sibling::div//p[text()='"
					+ errorMsg + "']");
		}
	}

	private By getYearsAsCoachErrorMsg() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(
					".//label[contains(text(),'questions.registeration_coach.0588mrmyvul.label')]/../following-sibling::div/p[text()='"
							+ errorMsg + "']"));
		} else {
			return By.xpath(".//label[contains(text(),'années en tant que Coach')]/../following-sibling::div/p[text()='"
					+ errorMsg + "']");
		}
	}

	public RegisterCoachAboutPage registerCoach(CoachAboutDetails coachAboutDetails) {
		LOGGER.info("Registering coach");
		setValue(coachAboutDetails.getTestimoial(), getTestimonialTextArea());
		click(getRegisterButton());
		return this;
	}

	public RegisterCoachAboutPage setSummaryInput(String summary) {
		LOGGER.info("Setting value for summary input");
		setValue(summary, getSummryTextArea());
		return this;
	}

	private By getSummryTextAreaRm() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_coach.summary.label')]/../following-sibling::textarea"));
	}

	public RegisterCoachAboutPage setSummaryInputRm(String summary) {
		LOGGER.info("Setting value for summary input");
		setValue(summary, getSummryTextAreaRm());
		return this;
	}


	public RegisterCoachAboutPage setTopNewIndustries(String industry) {
		LOGGER.info("Setting value for top new industry");
		setValueWithoutValidation(industry, getTopNewIndustriesTextArea());
		return this;
	}

	public RegisterCoachAboutPage setAboutInput(String about) {
		LOGGER.info("Setting value for about input");
		setValue(about, getAboutTextArea());
		return this;
	}

	private By getSelectedCoachOffers(String coachOffer) {
		return By.xpath(".//label[normalize-space(text())='" + coachOffer + "']/../input");
	}

	public RegisterCoachAboutPage selectFirstAvailabelCoachOffers(String coachOffer) {
		LOGGER.info("Selecting first available coach offers");
		if(isChecked(getSelectedCoachOffers(coachOffer))) {
			click(getCoachOfferCheckbox(coachOffer));
			click(getCoachOfferCheckbox(coachOffer));
		}else {
			click(getCoachOfferCheckbox(coachOffer));
		}

		return this;
	}

	public RegisterCoachAboutPage setCoachExperience(String experience) {
		LOGGER.info("Setting value for coach experience");
		setValue(experience, getCoachExperiencelTextArea());
		return this;
	}


	public RegisterCoachAboutPage setProfessionalSummary(String profSummary) {
		LOGGER.info("Setting value for professional summary");
		setValue(profSummary, getProfessionalSummaryTextArea());
		return this;
	}

	public RegisterCoachAboutPage setTestinomial(String testinamial) {
		LOGGER.info("Setting value for testimonials");
		setValue(testinamial, getTestimonialTextArea());
		return this;
	}

	public RegisterCoachAboutPage setCoachingSummary(String coachingSummary) {
		LOGGER.info("Setting value for coaching summary");
		setValue(coachingSummary, getCoachingSummaryTextArea());
		return this;
	}

	private By getYearsAsCoachRm() {
		return By.xpath(getXpathByText(".//label[(text())='private.coach.years']/../..//input"));
	}


	public RegisterCoachAboutPage setYearsAsCoachRm(String yearsascoach) {
		LOGGER.info("Setting value for years as coach");
		setValue(yearsascoach, getYearsAsCoachRm());
		return this;
	}

	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		shortWait();
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}

	public RegisterCoachAboutPage clickregisterButton() {
		LOGGER.info("Clicking next button");
		shortWait();
		click(getRegisterButton());
		return this;
	}

	private By getDatePickerInput() {
		return By.xpath(".//input[@name='session_date']");
	}

	private By getDatePickerDateElement(Integer date) {
		return By.xpath(".//div[contains(@class,'btn-light ng-star') and text()='"+date.toString()+"']");
	}

	private By getDatePickerPrevElement() {
		return By.xpath(".//button[@title='Previous month']");
	}

	public RegisterCoachAboutPage selectDateAwarded(int date) {
		LOGGER.info("Selecting Date awarded");
		click(getDatePickerInput());
		click(getDatePickerPrevElement());
		click(getDatePickerDateElement(date));
		return this;
	}

	private By getRegionDropdown() {
		return By.xpath(".//ng-select[@name='regions']");
	}

	private By getRegionElement(String region) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+region+"']");
	}

	public RegisterCoachAboutPage selectRegion(String region) {
		click(getRegionDropdown());
		click(getRegionElement(region));
		return this;
	}

	private By getCareerIndustriesDropdown() {
		return By.xpath(".//ng-select[@name='industries']");
	}

	private By getCareerIndustriesOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}

	private By getCorporateExpertise() {
		return By.xpath(".//h2[contains(text(),'Corporate expertise')]");
	}

	public RegisterCoachAboutPage selectIndustry(String industry) {
		LOGGER.info("Selecting industry");
		click(getCareerIndustriesDropdown());
		click(getCareerIndustriesOptionElement(industry));
		//click(getIndustryDropdownArrow());
		return this;
	}

	private By getLanguagesDropdown() {
		return By.xpath(".//ng-select[@name='languages']");
	}

	private By getLanguagesOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}

	private By getQualificationsInput() {
		return By.xpath(".//input[contains(@id, 'qualifications')]");
	}

	public RegisterCoachAboutPage selectLanguages(List<String> languages) {
		LOGGER.info("Selecting languages");
		click(getLanguagesDropdown());
		for(String language : languages) {
			click(getLanguagesOptionElement(language));
		}
		return this;
	}

	public RegisterCoachAboutPage submitCoachAboutInfo(CoachAboutDetails coachAboutDetails,String yearsascoach) {
		LOGGER.info("Submitting coach about information");

		if(Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			try {
				setValue(coachAboutDetails.getSummry(), getSummryTextArea());

			} catch (StaleElementReferenceException e) {
				e.getStackTrace();
			}
			setValueWithoutValidation(coachAboutDetails.getTopNewIndustry(), getTopOrganizationsCoachedIn());
			//setValue(coachAboutDetails.getAbout(), getAboutTextArea());
			//setValue(coachAboutDetails.getCoachExperience(),
			// getCoachExperiencelTextArea());
			setValue(coachAboutDetails.getTestimoial(), getTestimonialTextArea());
			setValue(coachAboutDetails.getProfessionalSummary(), getProfessionalSummaryTextArea());
			selectRegion(coachAboutDetails.getRegion());
			selectDateAwarded(coachAboutDetails.getDateAwarded());
			selectLanguages(coachAboutDetails.getLanguages());
			selectIndustry(coachAboutDetails.getIndustry());
			setValue(coachAboutDetails.getQualification(), getQualificationsInput());
			setValue(coachAboutDetails.getYearsAsCoach(), getYearsAsCoachRm());
		}else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			try {
				setValue(coachAboutDetails.getSummry(), getSummryTextAreaRm());

			} catch (StaleElementReferenceException e) {
				setValue(coachAboutDetails.getSummry(), getSummryTextAreaRm());
			}
			setYearsAsCoachRm(yearsascoach);
		}
		click(getConsentCheckbox());
		setValue(coachAboutDetails.getCoachingSummary(), getCoachingSummaryTextArea());
		shortWait();
		click(getRegisterButton());
		return this;

	}

	public RegisterCoachAboutPage submitCoachAboutInfoRm(CoachAboutDetails  coachAboutDetails,String yearsascoach) {
		LOGGER.info("Submitting coach about information");
		try {
			setValue(coachAboutDetails.getSummry(), getSummryTextAreaRm());

		} catch (StaleElementReferenceException e) {
			setValue(coachAboutDetails.getSummry(), getSummryTextAreaRm());
		}
		setYearsAsCoachRm(yearsascoach);
		click(getConsentCheckbox());
		setValue(coachAboutDetails.getCoachingSummary(), getCoachingSummaryTextArea());
		shortWait();
		click(getRegisterButton());
		return this;

	}

	public RegisterCoachAboutPage validateAboutPageErrorValidation() {

		LOGGER.info("Validating coach about information error validation");
		SoftAssert softAssert = new SoftAssert();

		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {

			softAssert.assertTrue(
					isElementPresent(getPrivacyPolicyErrorMsg(ThriveAppSharedData.PRIVACY_NOTICE_ERROR_MSG.getValue())),
					"error message not present for privacy policy");
			softAssert.assertTrue(isElementPresent(getTopNewIndustriesErrorMsg()),
					"error message not present for new industries");
			softAssert.assertTrue(isElementPresent(getAboutTextAreaErrorMsg()), "error message not present for about");
			softAssert.assertTrue(isElementPresent(getProfessionalSummaryErrorMsg()),
					"error message not present for professional summary");
			softAssert.assertTrue(isElementPresent(getTestimonialErrorMsg()),
					"error message not present for testinomial");
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {

			softAssert.assertTrue(
					isElementPresent(
							getPrivacyPolicyErrorMsg(ThriveAppSharedData.PRIVACY_NOTICE_ERROR_MSG_RM.getValue())),
					"error message not present for privacy policy");
			softAssert.assertTrue(isElementPresent(getHeadLineSummaryErrorMsg()),
					"error message not present for Headline summary");
			softAssert.assertTrue(isElementPresent(getYearsAsCoachErrorMsg()),
					"error message not present for Years as coach");

		}

		softAssert.assertTrue(isElementPresent(getCoachingSummaryErrorMsg()),
				"error message not present for coaching summary");
		softAssert.assertAll();
		return this;
	}

	private By getYearesAsCoachErrorMessage() {
		return By.xpath((".//label[contains(text(),'Years as a Coach')]/..//following-sibling::div//p[text()='"
						+ errorMsg + "']"));
	}

	private By getRegionErrorMessage() {
		return By.xpath((".//label[contains(text(),'Region')]/..//following-sibling::div//p[text()='" + errorMsg + "']"));
	}

	private By getLanguagesErrorMessage() {
		return By.xpath((".//label[contains(text(),'Languages')]/..//following-sibling::div//p[text()='" + errorMsg + "']"));
	}

	private By getOrganisationsCoachedInErrorMessage() {
		return By.xpath((".//label[contains(text(),'Languages')]/..//following-sibling::div//p[text()='" + errorMsg + "']"));
	}

	private By getCoachingQualificationsErrorMessage() {
		return By.xpath((".//label[contains(text(),'coaching qualifications')]/..//following-sibling::div//p[text()='" + errorMsg
						+ "']"));
	}

	private By getDateAwardedErrorMessage() {
		return By.xpath((".//label[contains(text(),'Date awarded')]/..//following-sibling::div//p[text()='" + errorMsg + "']"));
	}

	private By getCoachedIndustrySectorsErrorMessage() {
		return By.xpath((".//label[contains(text(),'industry sectors')]/..//following-sibling::div//p[text()='"
						+ errorMsg + "']"));
	}

	private By getPrivacyNoticeErrorMsg() {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(".//label[contains(text(),'register.coach.step6.privacy_notice')]/..//p[contains(text(),'ui.messages.privacy_notice.required_field')]"));
		 } else {
			return By.xpath(".//label[contains(text(),'Notice d’Information')]/..//p[contains(text(),'Ceci est une question obligatoire.')]");
	    }
	}
	
	public RegisterCoachAboutPage validateProfileForClientPageErrors() {

		LOGGER.info("Validating Profile For Client Page Errors");
		SoftAssert softAssert = new SoftAssert();

		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {

			softAssert.assertTrue(isElementPresent(getYearesAsCoachErrorMessage()),
					"error message not present for Years As Coach");
			softAssert.assertTrue(isElementPresent(getRegionErrorMessage()), "error message not present for Region");
			softAssert.assertTrue(isElementPresent(getLanguagesErrorMessage()),
					"error message not present for Languages");
//	//		softAssert.assertTrue(isElementPresent(getHeadLineSummaryErrorMsg()),
//					"error message not present for Headline Summary");
			softAssert.assertTrue(isElementPresent(getOrganisationsCoachedInErrorMessage()),
					"error message not present for Organizations Coached In");
//		//	softAssert.assertTrue(isElementVisible(getCoachingSummaryErrorMsg()),
//					"error message not present for Coaching Summary");
			softAssert.assertTrue(isElementPresent(getCoachingQualificationsErrorMessage()),
					"error message not present for Coaching Qualifications");
			softAssert.assertTrue(isElementPresent(getDateAwardedErrorMessage()),
					"error message not present for Headline Summary");
			softAssert.assertTrue(isElementPresent(getCoachedIndustrySectorsErrorMessage()),
					"error message not present for Coached Industry sectors");
			softAssert.assertTrue(isElementPresent(getPrivacyNoticeErrorMsg()),
					"error message not present for Privacy Notice");
//			softAssert.assertTrue(isElementPresent(getProfessionalSummaryErrorMsg()),
//					"error message not present for Professional Summary");

		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {

			softAssert.assertTrue(
					isElementPresent(
							getPrivacyPolicyErrorMsg(ThriveAppSharedData.PRIVACY_NOTICE_ERROR_MSG_RM.getValue())),
					"error message not present for privacy policy");
			softAssert.assertTrue(isElementPresent(getHeadLineSummaryErrorMsg()),
					"error message not present for Headline summary");
			softAssert.assertTrue(isElementPresent(getYearsAsCoachErrorMsg()),
					"error message not present for Years as coach");

		}

//		softAssert.assertTrue(isElementPresent(getCoachingSummaryErrorMsg()),
//				"error message not present for coaching summary");
		softAssert.assertAll();
		return this;
	}

	public RegisterCoachAboutPage validateCoachAboutInfo(CoachAboutDetails coachAboutDetails) {
		LOGGER.info("Validating coach about information");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				getCoachingSummary = getAttributeByValue("value", getCoachingSummaryTextArea());
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);

		String getSummary = getAttributeByValue("value", getSummryTextArea());
		String getTestinomial = getAttributeByValue("value", getTestimonialTextArea());

		String getTopNewIndustries=" ";
		String getAbout=" ";
		String getCoachExperience=" ";
		String getProfessionalSummary= " ";
		String industries=" ";
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			getTopNewIndustries = getAttributeByValue("value", getTopNewIndustriesTextArea());
			getAbout = getAttributeByValue("value", getAboutTextArea());
			getCoachExperience = getAttributeByValue("value", getCoachExperiencelTextArea());
			getProfessionalSummary = getAttributeByValue("value", getProfessionalSummaryTextArea());
			industries= "• "+coachAboutDetails.getTopNewIndustry();
		}

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getSummary.equalsIgnoreCase(coachAboutDetails.getSummry()), "summary is not updated");
		softAssert.assertTrue(getCoachingSummary.equalsIgnoreCase(coachAboutDetails.getCoachingSummary()),
				"coaching summary is not updated");
		softAssert.assertTrue(getTestinomial.equalsIgnoreCase(coachAboutDetails.getTestimoial()),
				"testinomial is not updated");

		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			softAssert.assertTrue(getTopNewIndustries.equalsIgnoreCase(industries), "industry is not updated");
			softAssert.assertTrue(getAbout.equalsIgnoreCase(coachAboutDetails.getAbout()), "about is not updated");
			softAssert.assertTrue(getCoachExperience.equalsIgnoreCase(coachAboutDetails.getCoachExperience()),
					"coaching experience is not updated");
			softAssert.assertTrue(getProfessionalSummary.equalsIgnoreCase(coachAboutDetails.getProfessionalSummary()),
					"professional summary is not updated");
			softAssert.assertTrue(isChecked(getCoachOfferselected("Webinars")), "coach offer not selected");
		}
		softAssert.assertAll();
		return this;
	}

}