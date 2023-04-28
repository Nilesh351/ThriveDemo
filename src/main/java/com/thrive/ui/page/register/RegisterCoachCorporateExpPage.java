package com.thrive.ui.page.register;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.user_details.CoachCorporateExpDetails;
import com.thrive.common.testdata.pojos.user_details.CoachCorporateExpUpdateDetails;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RegisterCoachCorporateExpPage extends BaseTestPage {
	
	String seniorPosition;
	String companies;
	String industry;
	String workingWeekInCorporate;
	String careerInBusiness;
	String careerInCorporateRole;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	
	private By getPositionDropdown() {
		return By.xpath(".//ng-select[@name='other_senior_position']");
	}
	
	private By getPositionOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getCompaniesDropdown() {
		return By.xpath(".//ng-select[@name='career_companies']");
	}
	
	private By getCompaniesRemoveIcon() {
		return By.xpath(".//ng-select[@name='career_companies']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getCompaniesOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getCareerIndustriesDropdown() {
		return By.xpath(".//ng-select[@name='career_industries']");
	}
	
	private By getCareerIndustriesRemoveIcon() {
		return By.xpath(".//ng-select[@name='career_industries']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getCareerIndustriesOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getCareerSmeRoles() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_coach.xarz5r2gzab.label')]/../following-sibling::input"));
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step4.next']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step5.previous']"));
	}
	
	
	private By getWorkingWeekInCorporateSliderElement() {
		return By.xpath(".//label[contains(text(),'How much of your working week do you spend in a corporate role? ')]/../following-sibling::div//span[@aria-valuemax='100']");
	}
	
	private By getCareerHasBeenInBusinessSliderElement() {
		return By.xpath(".//label[contains(text(),'How much of your career has been in business (SME Roles)?')]/../following-sibling::div//span[@aria-valuemax='100']");
	}
	
	private By getCareerHasBeenInCorporateRoleSliderElement() {
		return By.xpath(".//label[contains(text(),'How much of your career has been in corporate roles? ')]/../following-sibling::div//span[@aria-valuemax='100']");
	}
	
	private By getCompaniesProvidedCoachingTo(){
		return By.xpath(".//label[text()='Which companies have you provided coaching to? ']/../following-sibling::input");
	}
	
	private By getRolesYouHeld() {
		return By.xpath(".//label[contains(text(),'Please tell us the roles you held or hold')]/../following-sibling::input");
	}
	
	private By getIndustryDropdownArrow() {
		return By.xpath(".//ng-select[@name='career_industries']/div//span[@class='ng-arrow-wrapper']");
	}
	
	private By getPleaseCompletesFormLink() {
		return By.xpath("//p[contains(text(),'complete')]");
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
	
	private By getSeniorPositionValue() {
		return By.xpath(".//ng-select[@id='other_senior_position']//div[@class='ng-value ng-star-inserted']//span[2]");
	}
	
	private By getCompaniesWorkedAsEmployeeValue(String companiesWorkAsEmployee) {
		return By.xpath(".//span[text()='"+companiesWorkAsEmployee+"']");
	}
	
	private By getIndustrySectorsValue(String industryScetors) {
		return By.xpath(".//span[text()='"+industryScetors+"']");
	}
	
	private By getPositionDropdownErrorMsg() {
		return By.xpath(".//ng-select[@name='other_senior_position']/..//p[text()='"+errorMsg+"']");
	}
	
	private By getCareerIndustriesErrorMsg() {
		return By.xpath(".//ng-select[@name='career_industries']/..//p[text()='"+errorMsg+"']");
	}
	
	private By getCareerSmeRolesErrorMsg() {
		return By.xpath(".//label[contains(text(), 'Career sme')]/../following-sibling::div/p[text()='This field is required']");
	}
	
	private By getWorkingWeekInCorporateSliderErrorMsg() {
		return By.xpath(".//label[contains(text(),'How much of your working week do you spend in a corporate role? ')]/../following-sibling::div//p[text()='"+errorMsg+"']");
	}
	
	private By getseniorPositionName() {
		return By.xpath(".//label[contains(text(),'senior position')]");
	}
	
	
	public RegisterCoachCorporateExpPage selectPosition(String position) {
		LOGGER.info("Selecting position");
		click(getPositionDropdown());
		click(getPositionOptionElement(position));
		return this;
	}
	
	public RegisterCoachCorporateExpPage selectFirstAvailableSeniorPoition() {
		LOGGER.info("Selecting first available position");
		click(getPositionDropdown());
		seniorPosition = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}
	
	public RegisterCoachCorporateExpPage selectCompanies(List<String> companies) {
		LOGGER.info("Selecting companies");
		click(getCompaniesDropdown());
		for(String company : companies) {
			click(getCompaniesOptionElement(company));
		}
		return this;
	}
	
	public RegisterCoachCorporateExpPage selectFirstAvailableCompany() {
		LOGGER.info("Selecting first avalilable companies");
		click(getCompaniesDropdown());
		click(getCompaniesRemoveIcon());
		companies = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		click(getseniorPositionName());
		return this;
		
	}
	
	private By getCorporateExpertise() {
		return By.xpath(".//h2[contains(text(),'Corporate expertise')]");
	}
	
	public RegisterCoachCorporateExpPage selectIndustry(String industry) {
		LOGGER.info("Selecting industry");
		click(getCareerIndustriesDropdown());
		click(getCareerIndustriesOptionElement(industry));
		click(getCorporateExpertise());
		//click(getIndustryDropdownArrow());
		return this;
	}
	
	private By getIndustryName(String industry) {
		return By.xpath(".//span[text()='"+industry+"']");
	}
	
	public RegisterCoachCorporateExpPage validateIndustryPresent(String val) {
		LOGGER.info("Validate selected language present");
		Assert.assertTrue(isElementPresent(getIndustryName(val)));
		return this;
	}
	
	public RegisterCoachCorporateExpPage selectFirstAvailabelIndustry() {
		LOGGER.info("Selecting first availalble industry");
		click(getCareerIndustriesDropdown());
		click(getCareerIndustriesRemoveIcon());
		industry = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		click(getseniorPositionName());
		//click(getIndustryDropdownArrow());
		return this;
	}
	
	public RegisterCoachCorporateExpPage setCompaniesProvidedCoachingToInput(String companiesProvidedCoaching) {
		LOGGER.info("Setting value for Companies provided coaching");
		setValue(companiesProvidedCoaching, getCompaniesProvidedCoachingTo());
		return this;
	}
	
	public RegisterCoachCorporateExpPage setRolesYouHeld(String roleHeld) {
		LOGGER.info("Setting value for Roles you hold");
		setValue(roleHeld, getRolesYouHeld());
		return this;
	}
	
	public RegisterCoachCorporateExpPage setCareerSMERoles(String role) {
		LOGGER.info("Setting value for Career SME roles");
		setValue(role, getCareerSmeRoles());
		return this;
	}
	
	public RegisterCoachCorporateExpPage setWorkingWeekInCorporateRole() {
		LOGGER.info("Setting value forWorking in corporate roles");
		workingWeekInCorporate = getAttributeByValue("aria-valuenow", getWorkingWeekInCorporateSliderElement());
		click(getWorkingWeekInCorporateSliderElement());
		dragDropByCoordinates(getWorkingWeekInCorporateSliderElement(), 10, 0);
		return this;
	}
	
	public RegisterCoachCorporateExpPage setCareerInBusiness() {
		LOGGER.info("Setting value for Companies provided coaching");
		careerInBusiness = getAttributeByValue("aria-valuenow", getCareerHasBeenInBusinessSliderElement());
		click(getCareerHasBeenInBusinessSliderElement());
		dragDropByCoordinates(getCareerHasBeenInBusinessSliderElement(), 10,0);
		return this;
	}
	
	public RegisterCoachCorporateExpPage setCareerInCorporateRoles() {
		LOGGER.info("Setting value for Career incorporate roles");
		careerInCorporateRole = getAttributeByValue("aria-valuenow", getCareerHasBeenInCorporateRoleSliderElement());
		click(getCareerHasBeenInCorporateRoleSliderElement());
		dragDropByCoordinates(getCareerHasBeenInCorporateRoleSliderElement(), 10, 0);
		return this;
	}
	
	public RegisterCoachCorporateExpPage clickNextButton() {
		LOGGER.info("Click on Next button");
		shortWait();
		click(getNextButton());
		return this;
	}
	
	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}
	
	public RegisterCoachCorporateExpPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		return this;
	}
	
	public RegisterCoachMentoringExpPage submitCoachCorporateExpDetails(CoachCorporateExpDetails  CoachCorporateExpDetails) {
		LOGGER.info("Submitting coach corporate experience details");
		selectPosition(CoachCorporateExpDetails.getPosition());
		click(getWorkingWeekInCorporateSliderElement());
		dragDropByCoordinates(getWorkingWeekInCorporateSliderElement(), 200, 0);
		selectCompanies(CoachCorporateExpDetails.getCompanies());
		selectIndustry(CoachCorporateExpDetails.getIndustry());
		setValue(CoachCorporateExpDetails.getCareerSmeRole(), getCareerSmeRoles());
		click(getNextButton());
		return new RegisterCoachMentoringExpPage();
	}
	
	
	public RegisterCoachCorporateExpPage validateCoachCorporateExpErrorValidation() {
			
			LOGGER.info("Validating coach corporate experience details error validation");
			
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(isElementVisible(getPositionDropdownErrorMsg()),"Error message is not prsent for position dropdown");
			softAssert.assertTrue(isElementVisible(getCareerIndustriesErrorMsg()),"Error message not present for career industries");
			softAssert.assertTrue(isElementVisible(getCareerSmeRolesErrorMsg()),"Error message not present for career sme role");
			softAssert.assertTrue(isElementVisible(getWorkingWeekInCorporateSliderErrorMsg()),"Error mesage not present for working week in corporate");
			softAssert.assertAll();
			return this;
		}
	
	public RegisterCoachCorporateExpPage validateCoachCorporateExpertiseDetails(CoachCorporateExpUpdateDetails coachCorporateExpUpdateDetails) {
		LOGGER.info("Validating coach corporate experience details");
		String getSeniorPosition=" ";
		String getWorkingWeekInCorporate=" ";
		String getCareerSmeRole="";
		String getProvidedCoachingTo = getAttributeByValue("value", getCompaniesProvidedCoachingTo());	
		String getRolesHold = getAttributeByValue("value", getRolesYouHeld());
		
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				getCareerSmeRole = getAttributeByValue("value", getCareerSmeRoles());
				getSeniorPosition = getText(getSeniorPositionValue());
				getWorkingWeekInCorporate = getAttributeByValue("aria-valuenow", getWorkingWeekInCorporateSliderElement());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		
		getWorkingWeekInCorporate = getAttributeByValue("aria-valuenow", getWorkingWeekInCorporateSliderElement());
		String getCareerInBusiness = getAttributeByValue("aria-valuenow", getCareerHasBeenInBusinessSliderElement());
		String getCareerInCorporateRole = getAttributeByValue("aria-valuenow", getCareerHasBeenInCorporateRoleSliderElement());	
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getProvidedCoachingTo.equalsIgnoreCase(coachCorporateExpUpdateDetails.getCompaniedProviedCoaching()),"Company provided coaching not updated");
		softAssert.assertTrue(getRolesHold.equalsIgnoreCase(coachCorporateExpUpdateDetails.getRolesHeld()),"roles hold not updated");
		softAssert.assertTrue(getCareerSmeRole.equalsIgnoreCase(coachCorporateExpUpdateDetails.getCareerSmeRole()),"career sme role not updated");
		softAssert.assertTrue(getSeniorPosition.equalsIgnoreCase(seniorPosition));
		softAssert.assertTrue(isElementPresent(getCompaniesWorkedAsEmployeeValue(companies)),"Companies not updated");
		softAssert.assertTrue(isElementPresent(getIndustrySectorsValue(industry)),"industry not updated");
		softAssert.assertNotEquals(workingWeekInCorporate, getWorkingWeekInCorporate,"working week in corporate is not updated");
		softAssert.assertNotEquals(careerInBusiness, getCareerInBusiness,"career in business is not updated");
		softAssert.assertNotEquals(careerInCorporateRole, getCareerInCorporateRole,"career in corporate role is not updated");
		softAssert.assertAll();
		return this;
	}
	
	

}
