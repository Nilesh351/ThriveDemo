package com.thrive.ui.page.register;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.user_details.CoachEmploymentDetails;
import com.thrive.common.testdata.pojos.user_details.CoachUpdateDetails;
import com.thrive.common.testdata.utils.MockDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class RegisterCoachEmploymentPage extends BaseTestPage {
	
	String employmentStatus;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();
	
	private By getEmpStatusDropdown() {
		return By.xpath(".//ng-select[@name='employment_status']");
	}
	
	private By getEmpStatusOptionElement(String option) {
		return By.xpath(".//span[contains(@class, 'ng-option') and text()='"+option+"']");
	}
	
	private By getFirstAvailableElementFromDropdown() {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getOtherOrganizationTextArea() {
		return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_coach.l8im5jtse4s.label')]/../following-sibling::textarea"));
	}
	
	private By getWorkAsDropdown() {
		return By.xpath(".//ng-select[@name='company_type']");
	}
	
	private By getWorkAsOptionElement(String optionLabel) {
		return By.xpath(".//span[contains(@class,'ng-option') and text()= '"+optionLabel+"']");
	}
	
	private By getEmpStatusValue() {
		return By.xpath(".//ng-select[@id='employment_status']//div[@class='ng-value ng-star-inserted']/span[2]");
	}
	
	private By getEnterpiseInput() {
		return By.xpath(".//input[@name='company_name']");
	}
	
	private By getWebsite() {
		return By.xpath(".//input[@name='company_website']");
	}
	
	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.coach.update_details_button']"));
	}
	
	private By getDoYouWorkAsDropdown() {
		return By.xpath(".//ng-select[@name='company_type']");
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step2.next']"));
	}
	
	private By getPreviousButton() {
		return By.xpath(getXpathByText(".//button[text()='register.coach.step2.previous']"));
	}
	
	private By getPleaseSpecift() {
		return By.xpath(".//input[@name='specify_employment_status']");
	}
	
	private By getEmpStatusDropdownErrorMsg() {
		return By.xpath(".//ng-select[@name='employment_status']/..//p[text()='"+errorMsg+"']");
	}
	
	public RegisterCoachEmploymentPage selectEmploymentStatus(String empStatus) {
		LOGGER.info("Selecting employment status");
		click(getEmpStatusDropdown());
		click(getEmpStatusOptionElement(empStatus));
		return this;
	}
	
	public RegisterCoachEmploymentPage setOtherOrganizationscurrentlyAssociateWith(String otherorgassociate) {
		LOGGER.info("Setting value for Other organizations current associate");
		setValue(otherorgassociate, getOtherOrganizationTextArea());
		return this;
	}
	
	public RegisterCoachEmploymentPage clickNextButton() {
		LOGGER.info("click next button");
		waitUntilElementIsVisible(getEmpStatusDropdown());
		click(getNextButton());
		return this;
	}
	
	public RegisterCoachEmploymentPage selectWorkAs(String workAsOption) {
		LOGGER.info("Selecting Work as");
		click(getWorkAsDropdown());
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getWorkAsOptionElement(workAsOption));
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
	
	public RegisterCoachEmploymentPage selectFirstAvailableEmpStatueValue() {
		
		String webSite = MockDataGenerator.generateRandomWebsite();
		LOGGER.info("Selecting first available employment status");
		click(getEmpStatusDropdown());
		employmentStatus = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		
		if(employmentStatus.equalsIgnoreCase("Employed")) {
			setValue(enterprise, getEnterpiseInput());
			setValue(webSite, getWebsite());
		}
		else if(employmentStatus.equalsIgnoreCase("Other")) {
			setValue(MockDataGenerator.generateRandomWord(5), getPleaseSpecift());
		}
		else {
			click(getDoYouWorkAsDropdown());
			click(getFirstAvailableElementFromDropdown());
		}
		return this;
	}
	
	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}
	
	public RegisterCoachEmploymentPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :" + "expected is : " 
		+ expectedMessage + " and actual is : " + actualMessage);
		return this;
	}
	
	public RegisterCoachSkillsAndQualificationPage submitCoachEmploymentDetails(CoachEmploymentDetails coachEmploymentDetails) {
		LOGGER.info("Submitting coach employment details");
		selectEmploymentStatus(coachEmploymentDetails.getEmpStatus());
		setValue(coachEmploymentDetails.getOtherOrganization(), getOtherOrganizationTextArea());
		selectWorkAs(coachEmploymentDetails.getWorkAs());
		click(getNextButton());
		return new RegisterCoachSkillsAndQualificationPage();
	}
	
	public RegisterCoachEmploymentPage validateEmploymentErrorvalidation() {
		LOGGER.info("Validating coach employment details error validation");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getEmpStatusDropdownErrorMsg()),"error message not presnt for status");
		softAssert.assertAll();
		return this;
	}
	
	
	public RegisterCoachEmploymentPage validateCoachEmploymentDetails(CoachUpdateDetails coachUpdateDetails) {
		LOGGER.info("Validating coach employment details");
		String empStatus = " ";
		String getOtherOrgAssociation = getAttributeByValue("value", getOtherOrganizationTextArea());
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				empStatus = getText(getEmpStatusValue());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
				
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getOtherOrgAssociation.equalsIgnoreCase(coachUpdateDetails.getOtherOrgAssociateWith()));
		softAssert.assertTrue(empStatus.equalsIgnoreCase(employmentStatus));
		softAssert.assertAll();
		return this;
	}

	
	public RegisterCoachDepartmentPage clickNext() {
		LOGGER.info("Clicking Next button");
		shortWait();
		click(getNextButton());
		return new RegisterCoachDepartmentPage();
	}
	
	private By getLanguageErrorValidation() {
		return By.xpath(".//ng-select[contains(@name,'languages')]//following-sibling::div//p[text()='"+errorMsg+"']");
	}
	
	public RegisterCoachEmploymentPage validateLangugaesErrorvalidation() {
		LOGGER.info("Validating coach languages details error validation");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getLanguageErrorValidation()),"error message not presnt for Language");
		softAssert.assertAll();
		return this;
	}
}