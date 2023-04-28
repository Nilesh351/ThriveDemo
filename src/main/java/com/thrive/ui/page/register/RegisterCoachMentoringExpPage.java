package com.thrive.ui.page.register;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.pojos.user_details.CoachingExpDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RegisterCoachMentoringExpPage extends BaseTestPage {
	
	String peopleCoached;
	String companiesCoached;
	String industrySectorsCoached;
	String topicval = "Test Topic Mutable UATG";
	String expertiseval = "Test Expertise Mutable UATG"; 
	String expertiseOption;
	String getCoachingHours;
	String internaltopicval = "Auto-Int-Mutable-Topic-1";
	String internalexpval = "Auto-Int-Mutable-Expertise-1";
	String internalexpertiseOption;
	String coachingHours = "40";
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	
	private By getLevelOfPeopleCoachedDropdown() {
		return By.xpath(".//ng-select[@name='coached_people']");
	}
	
	private By getLevelOfPeopleCoachedRemoveIcon() {
		return By.xpath(".//ng-select[@name='coached_people']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getLevelOfPeopleCoachedOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getLevelOfPeopleCoachedArrow() {
		return By.xpath(".//ng-select[@name='coached_people']//span[contains(@class,'ng-arrow-wrapper')]");
	}
	
	private By getCoachedCompanyDropdown() {
		return By.xpath(".//ng-select[@name='coached_companies']");
	}
	
	private By getCoachedCompanyRemoveIcon() {
		return By.xpath(".//ng-select[@name='coached_companies']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getCoachedCompanyOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getCoachedCompanyArrow() {
		return By.xpath(".//ng-select[@name='coached_companies']//span[contains(@class,'ng-arrow-wrapper')]");
	}
	
	private By getCoachingHoursInput() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_coach.iqwxwuj91k7.label')]/../following-sibling::input"));
	}
	
	private By getSectorsDropdown() {
		return By.xpath(".//ng-select[@name='industries']");
	}
	
	private By getSectorsRemoveIcon() {
		return By.xpath(".//ng-select[@name='industries']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getSectorsOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getSectorsArrow() {
		return By.xpath(".//ng-select[@name='industries']//span[contains(@class,'ng-arrow-wrapper')]");
	}
	
	private By getFirstAvailableElementFromDropdown() {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step5.next']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step5.previous']"));
	}
	
	private By getTopicElement(String topicName) {
		return By.xpath(".//label[contains(text(),'"+topicName+"')]");
	}
	
	private By getExpertiseElement(String expertiseName) {
		return By.xpath(".//small[text()='"+expertiseName+"']");
	}
	
	private By getExpertRadioOptionElement(String expertiseName) {
		return By.xpath(getXpathByText(".//small[text()='"+expertiseName.toLowerCase()+"']/../../../..//following-sibling::div//label[contains(text(),'ui.buttons.expert')]"));
	    //return By.xpath(".//small[text()='"+expertiseName.toLowerCase()+"']/../../../..//following-sibling::div//label[contains(text(),'Expert')]");
	}
	
	private By getFirstAvailableExpertise(String expertise) {
		return By.xpath(".//small[text()='"+expertise+"']/../../../../../../../../..//.//div[@class='d-flex v-align bt-padding']//span[@class='check']/..");
	}
	
	private By getCoachedLevelPeopleValue(String coachedPeople) {
		return By.xpath(".//span[text()='"+coachedPeople+"']");
	}
	
	private By getCoachedCompanyValue(String coachedCompany) {
		return By.xpath(".//span[text()='"+coachedCompany+"']");
	}
	 
	private By getCoachedIndustrySectorsValue(String coachedIndustrySectors) {
		return By.xpath(".//span[text()='"+coachedIndustrySectors+"']");
	}
	
	private By getSelectedExpertiseValue(String expertise) {
		return By.xpath(".//small[text()='"+expertise+"']/../../../../../../../../..//.//div[@class='d-flex v-align bt-padding']//span[@class='check active']/..");
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	private By getLevelOfPeopleCoachedDropdownErrorMsg() {
		return By.xpath((".//ng-select[@name='coached_people']/..//following-sibling::div/p[text()='"+errorMsg+"']"));
	}

	private By getCoachingHoursInputErrorMsg() {
		return By.xpath(".//label[contains(text(), 'how many hours of coaching')]/../following-sibling::div/p[text()='"+errorMsg+"']");
	}
	
	private By getSectorsDropdwonErrorMsg() {
		return By.xpath(".//ng-select[@name='industries']/..//p[text()='"+errorMsg+"']");
	}
	
	private By getRateLevelOfExpertiseErrorMsg() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(".//label[contains(text(),'rate the level of your expertise')]/../..//following-sibling::div//p[text()='"+ThriveAppSharedData.EXPERTISE_SELECT_ERROR_MSG.getValue()+"']");
	} else {
		return By.xpath(".//label[contains(text(),'Merci de sélectionner les secteurs dans lesquels vous')]/../..//following-sibling::div//p[text()='"+ThriveAppSharedData.EXPERTISE_SELECT_ERROR_MSG.getValue()+"']");
	       }
	}
	
	
	public RegisterCoachMentoringExpPage selectExpertise(List<Map<String, String>> expertises) {
		LOGGER.info("Selecting expertise");
		String topic;
		String expertise;
		for(Map<String, String> expertisMap : expertises) {
				topic = expertisMap.get("topic");
				expertise = expertisMap.get("expertise");
				click(getTopicElement(topic));
				click(getExpertiseElement(expertise));
				click(getExpertRadioOptionElement(expertise));
		}
		
		return this;
		
	}
	
	private By getIndustryName(String industry) {
		return By.xpath(".//span[text()='"+industry+"']");
	}
	
	public RegisterCoachMentoringExpPage validateIndustryPresent(String val) {
		LOGGER.info("Validate selected language present");
		Assert.assertTrue(isElementPresent(getIndustryName(val)));
		return this;
	}
	
	public RegisterCoachMentoringExpPage selectAvailableExpertiseOption() {
		LOGGER.info("Selecting first available expertise");
		String topicValue="";
		String expertiseValue="";
		if(Config.getLocalizationLanguage().contains("fr")) {
		topicValue = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(topicval));
		expertiseValue= DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertiseval));
	   } else {
		   topicValue = topicval;
		   expertiseValue = expertiseval;
	   }
		click(getTopicElement(topicValue));
		click(getExpertiseElement(expertiseValue));
		expertiseOption = getText(getFirstAvailableExpertise(expertiseValue));
		click(getFirstAvailableExpertise(expertiseValue));
		return this;
		
	}
	
	public RegisterCoachMentoringExpPage selectInternalAvailableExpertiseOption() {
		LOGGER.info("Selecting Internal available expertise");
		click(getTopicElement(internalMutableTopic));
		click(getExpertiseElement(internalMutableExpertise));
		internalexpertiseOption = getText(getFirstAvailableExpertise(internalMutableExpertise));
		click(getFirstAvailableExpertise(internalMutableExpertise));
		return this;
		
	}
	
	
	public RegisterCoachMentoringExpPage setCoachingHours() {
		LOGGER.info("Setting value for coaching hours");
		setValue(coachingHours, getCoachingHoursInput());
		return this;
	}
	
	private By getPleaseCompleteForm() {
		return By.xpath("//p[contains(text(),'complete')]");
	}
	
	public RegisterCoachMentoringExpPage selectCaochedLevelsPeople(List<String> levels) {
		LOGGER.info("Selecting Coached level peoples");
		click(getLevelOfPeopleCoachedDropdown());
		for(String level : levels) {
			click(getLevelOfPeopleCoachedOptionElement(level));
		}
		
		click(getPleaseCompleteForm());
		//click(getLevelOfPeopleCoachedArrow());
		return this;
	}
	
	public RegisterCoachMentoringExpPage selectFirstAvailableCoachesLevelPeople() {
		LOGGER.info("Selecting  first available Coached level peoples");
		click(getLevelOfPeopleCoachedDropdown());
		click(getLevelOfPeopleCoachedRemoveIcon());
		peopleCoached = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		
	//	click(getLevelOfPeopleCoachedArrow());
		return this;
	}
	
	public RegisterCoachMentoringExpPage selectCompanies(List<String> companies) {
		LOGGER.info("Selecting companies");
		click(getCoachedCompanyDropdown());
		for(String company : companies) {
			click(getCoachedCompanyOptionElement(company));
		}
		click(getPleaseCompleteForm());
		//click(getCoachedCompanyArrow());
		return this;
	}
	
	public RegisterCoachMentoringExpPage selectFirstAvailableCoachedCompany() {
		LOGGER.info("Selecting first available companies");
		click(getCoachedCompanyDropdown());
		click(getCoachedCompanyRemoveIcon());
		companiesCoached = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		//click(getCoachedCompanyArrow());
		return this;
	}
	
	public RegisterCoachMentoringExpPage clickNextButton() {
		LOGGER.info("Clicking Next button");
		shortWait();
		click(getNextButton());
		return this;
	}
	
	public RegisterCoachMentoringExpPage selectSectors(List<String> sectors) {
		LOGGER.info("Selecting sectors");
		click(getSectorsDropdown());
		for(String sector : sectors) {
			click(getSectorsOptionElement(sector));
		}
		click(getPleaseCompleteForm());
		//click(getSectorsArrow());
		return this;
	}
	
	private By getStep3() {
		return By.xpath(getXpathByText(".//h2[1][contains(text(),'registration_steps.w2hro9e5p9.label')]"));
	}
	
	public RegisterCoachMentoringExpPage selectSectorsRm(List<String> sectors) {
		LOGGER.info("Selecting sectors");
		click(getSectorsDropdown());
		for(String sector : sectors) {
			click(getSectorsOptionElement(sector));
		}
		click(getStep3());
		return this;
	}
	
	public RegisterCoachMentoringExpPage selectFirstAvailableIndustrySectors() {
		LOGGER.info("Selecting first available sectors");
		click(getSectorsDropdown());
		click(getSectorsRemoveIcon());
		industrySectorsCoached = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
	//	click(getSectorsArrow());
		return this;
	}
	
	public RegisterCoachProfileForClients submitCoachingExpDetails(CoachingExpDetails coachingExpDetails) {
		LOGGER.info("Submitting coaching experience details");
		
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
//			selectCaochedLevelsPeople(coachingExpDetails.getLevels()).selectCompanies(coachingExpDetails.getCompanies())
//					.setValue(Integer.toString(coachingExpDetails.getCoachingHours()), getCoachingHoursInput());
//			selectSectors(coachingExpDetails.getSectors());
			selectExpertise(coachingExpDetails.getExpertises());
			shortWait();
			click(getNextButton());
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			selectSectorsRm(coachingExpDetails.getSectors()).selectExpertise(coachingExpDetails.getExpertises());
			shortWait();
			click(getNextButton());
		}
		return new RegisterCoachProfileForClients();
		
	}
		
	
	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		shortWait();
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}
	
	public RegisterCoachMentoringExpPage validateExpertisePresent(String topic,String expertise) {
		LOGGER.info("Validating Expertise Present");
		click(getTopicElement(topic));
		Assert.assertTrue(isElementPresent(getExpertiseElement(expertise)));
		return this;
	}
	
	public RegisterCoachMentoringExpPage validateMentoringExperience() {
		LOGGER.info("Validating mentoring experience");
		int counter = 1;
		boolean flag = false;
		String topicValue="";
		String expertiseValue="";
		if(Config.getLocalizationLanguage().contains("fr")) {
		topicValue = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(topicval));
		expertiseValue= DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertiseval));
	   } else {
		   topicValue = topicval;
		   expertiseValue = expertiseval;
	   }
		do {
			try {
				shortWait();
//				if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//					getCoachingHours = getAttributeByValue("value", getCoachingHoursInput());
//				}
				click(getTopicElement(topicValue));
				click(getExpertiseElement(expertiseValue));
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
	
		String getExpertise = getText(getSelectedExpertiseValue(expertiseValue));
		
		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertTrue(isElementPresent(getCoachedIndustrySectorsValue(industrySectorsCoached)));
		softAssert.assertTrue(getExpertise.equalsIgnoreCase(expertiseOption));
//		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//			softAssert.assertTrue(getCoachingHours.equalsIgnoreCase(coachingHours));
//			softAssert.assertTrue(isElementPresent(getCoachedCompanyValue(companiesCoached)));
//			softAssert.assertTrue(isElementPresent(getCoachedLevelPeopleValue(peopleCoached)));
//		}
		softAssert.assertAll();
		return this;
	}
	
	public RegisterCoachMentoringExpPage validateInternalCoachMentoringExperience() {
		LOGGER.info("Validating mentoring experience");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
//				if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//					getCoachingHours = getAttributeByValue("value", getCoachingHoursInput());
//				}
				click(getTopicElement(internaltopicval));
				click(getExpertiseElement(internalexpval));
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
	
		String getExpertise = getText(getSelectedExpertiseValue(internalexpval));
		
		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertTrue(isElementPresent(getCoachedIndustrySectorsValue(industrySectorsCoached)));
		softAssert.assertTrue(getExpertise.equalsIgnoreCase(internalexpertiseOption));
//		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//			softAssert.assertTrue(getCoachingHours.equalsIgnoreCase(coachingHours));
//			softAssert.assertTrue(isElementPresent(getCoachedCompanyValue(companiesCoached)));
//			softAssert.assertTrue(isElementPresent(getCoachedLevelPeopleValue(peopleCoached)));
//		}
		softAssert.assertAll();
		return this;
	}
	
	
	
	public RegisterCoachMentoringExpPage validateMentoringExperienceErrorValidation() {
			
			LOGGER.info("Validating Internal mentoring experience error validation");
			SoftAssert softAssert = new SoftAssert();
			
			if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
//				softAssert.assertTrue(isElementPresent(getLevelOfPeopleCoachedDropdownErrorMsg()),"error message not present for level of people coached");
//				softAssert.assertTrue(isElementPresent(getCoachingHoursInputErrorMsg()),"error message not present for coaching hours");
				softAssert.assertTrue(isElementPresent(getRateLevelOfExpertiseErrorMsg()),"error message not present for rate level of expertise");
			//	softAssert.assertTrue(isElementPresent(getSectorsDropdwonErrorMsg()),"error message not present for industry sectors coached");
			}else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {		
				softAssert.assertTrue(isElementPresent(getRateLevelOfExpertiseErrorMsg()),"error message not present for rate level of expertise");
				softAssert.assertTrue(isElementPresent(getSectorsDropdwonErrorMsg()),"error message not present for industry sectors coached");
			}
			
			softAssert.assertAll();
			return this;
		}
	
	
	public RegisterCoachMentoringExpPage validateInternalMentoringExperience() {
		LOGGER.info("Validating Internal mentoring experience");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				getCoachingHours = getAttributeByValue("value", getCoachingHoursInput());
				click(getTopicElement(internaltopicval));
				click(getExpertiseElement(internalexpval));
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
	
		String getExpertise = getText(getSelectedExpertiseValue(internalexpval));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getCoachingHours.equalsIgnoreCase(coachingHours));
		softAssert.assertTrue(isElementPresent(getCoachedCompanyValue(companiesCoached)));
		softAssert.assertTrue(isElementPresent(getCoachedLevelPeopleValue(peopleCoached)));
		softAssert.assertTrue(isElementPresent(getCoachedIndustrySectorsValue(industrySectorsCoached)));
		softAssert.assertTrue(getExpertise.equalsIgnoreCase(internalexpertiseOption));
		softAssert.assertAll();
		return this;
	}

}