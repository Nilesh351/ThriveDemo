package com.thrive.ui.page.register;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.pojos.user_details.ClientUpdateDetails;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class RegisterClientCoachingPrioritiesPage extends UserManagementCommonPage {

	    String professionalCareerSatisfactionValue;
	    String meaningAndPurposeSatisfactionValue;
	    String managementAndLearningSatisfactionSkillsValue;
	    String wellBeingSatisfactionValue;
	    String personalLifeSatisfactionValue;
	
		private By getCoachingPriorities() {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.biursw4mask.label')]/..//following-sibling::input"));
		}
	 
		private By getProfessionalCarrer() {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.biursw4mask.label')]/..//following-sibling::div//label[contains(@class,'radio')]//span[not(contains(@class,'active'))]"));
		}
		
		private By getSelectedProfessionalCareer(String professionalCareerSatisfactionValue) {
			return By.xpath(getXpathByText("//label[contains(text(),'professional career')]/..//following-sibling::div//label[contains(text(),'"+professionalCareerSatisfactionValue+"')]//span[contains(@class,'active')]"));
		}
		
		private By getMeaningAndPurpose() {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.xrmbt6x2wbp.label')]/..//following-sibling::div//label[contains(@class,'radio')]//span[not(contains(@class,'active'))]"));
		}
		
		private By getSelectedMeaningAndPurpose(String meaningAndPurposeSatisfactionValue) {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.xrmbt6x2wbp.label')]/..//following-sibling::div//label[contains(text(),'"+meaningAndPurposeSatisfactionValue+"')]//span[not(contains(@class,'active'))]"));
		}
		
		private By getManagementAndLeadershipSkills() {
			return By.xpath("//label[contains(text(),'questions.registeration_client.u870b08mr4k.label')]/..//following-sibling::div//label[contains(@class,'radio')]//span[not(contains(@class,'active'))]");
		}
		
		private By getSelectedManagementAndLeadershipSkills(String managementAndLearningSkillsSatisfactionValue) {
			return By.xpath("//label[contains(text(),'questions.registeration_client.u870b08mr4k.label')]/..//following-sibling::div//label[contains(text(),'"+managementAndLearningSkillsSatisfactionValue+"')]//span[not(contains(@class,'active'))]");
		}
		
		private By getWellBeing() {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.miwq43hdq7s.label')]/..//following-sibling::div//label[contains(@class,'radio')]//span[not(contains(@class,'active'))]"));
		}
		
		private By getSelectedWellBeing(String wellBeingSatisfactionValue) {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.miwq43hdq7s.label')]/..//following-sibling::div//label[contains(text(),'"+wellBeingSatisfactionValue+"')]//span[not(contains(@class,'active'))]"));
		}
		
		private By getPersonalLife() {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.jk4gwiyu5y.label')]/..//following-sibling::div//label[contains(@class,'radio')]//span[not(contains(@class,'active'))]"));
		}
		
		private By getSelectedPersonalLife(String personalLifeSatisfactionValue) {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.jk4gwiyu5y.label')]/..//following-sibling::div//label[contains(text(),'"+personalLifeSatisfactionValue+"')]//span[not(contains(@class,'active'))]"));
		}
		
		private By getMotivatedAreasToWorkOn(String fieldToBeUpdated) {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.h9slajcdmkb.label')]/..//following-sibling::div[contains(@class,'single-select')]//label[contains(text(),'"+fieldToBeUpdated+"')]"));
		}
		
		private By getMotivatedAreasToWorkOn() {
			return By.xpath("//div[contains(@class,'single-select-chk')]//label");
		}
		
		private By getFrequentTriggersTitleLink() {
			return By.xpath(getXpathByText("//label[contains(text(),'questions.registeration_client.71jadqdwh3n.label')]"));
		}
		
		private By getFrequentTriggers() {
			return By.xpath("//div[@ role ='tabpanel']//div[@role='button']//label");
		}
		
		private By getTriggerOptions() {
			return By.xpath("//div[@role='tabpanel']//div[contains(@class,'panel-body')]//accordion-group[contains(@class,'open')]//label[contains(@class,'single-select')]//label");
		}
		
		public RegisterClientCoachingPrioritiesPage setCoachingPriorities(String coachingPriority) {
			LOGGER.info("Setting coaching priorities");
			findElement(getCoachingPriorities()).clear();
			setValue(coachingPriority, getCoachingPriorities());
			return this;
		}
		
		public RegisterClientCoachingPrioritiesPage setProfessionalCareerSatisfaction() {
			LOGGER.info("Changing value for professional career satisfaction");
			professionalCareerSatisfactionValue = getText(getProfessionalCarrer());
			click(getProfessionalCarrer());
			return this;	
		}
		
		public RegisterClientCoachingPrioritiesPage setMeaningAndPurposeSatisfaction() {
			LOGGER.info("Changing value for meaning and purpose satisfaction");
			meaningAndPurposeSatisfactionValue = getText(getMeaningAndPurpose());
			click(getMeaningAndPurpose());
			return this;	
		}
		
		public RegisterClientCoachingPrioritiesPage setManagementAndLeadershipSkillsSatisfaction() {
			LOGGER.info("Changing value for management and leadership skills satisfaction");
			managementAndLearningSatisfactionSkillsValue = getText(getManagementAndLeadershipSkills());
			click(getManagementAndLeadershipSkills());
			return this;	
		}
		
		public RegisterClientCoachingPrioritiesPage setWellBeingSatisfaction() {
			LOGGER.info("Changing value for well being satisfaction");
			wellBeingSatisfactionValue = getText(getWellBeing());
			click(getWellBeing());
			return this;	
		}
		
		public RegisterClientCoachingPrioritiesPage setPersonalHomeLifeSatisfaction() {
			LOGGER.info("Changing value for personal life satisfaction");
			personalLifeSatisfactionValue = getText(getPersonalLife());
			click(getPersonalLife());
			return this;	
		}
		
		private String updateMotivatedAreasToWork() {
			LOGGER.info("Changing value for most motivated areas to work");
			List<WebElement> motivatedAreas = findElements(getMotivatedAreasToWorkOn());
			String selectedArea ="";
			for(WebElement motivatedArea : motivatedAreas) {
				if(motivatedArea.isSelected()) {
					click(motivatedArea);
					continue;
				} else {
					click(motivatedArea);
					selectedArea = getText(motivatedArea);
				}
			}
			return selectedArea;
		}
		
		private String updateFrequenltyUsedCoachingTriggers() {
			LOGGER.info("Changing frequently used coaching triggers");
			click(getFrequentTriggersTitleLink());
			List<WebElement> triggers = findElements(getFrequentTriggers());
			String selectedTrigger ="";
			for(WebElement trigger : triggers) {
				if(trigger.isSelected()) {
					click(trigger);
					continue;
				} else {
					click(trigger);
					selectedTrigger = getText(trigger);
				}
			}
			return selectedTrigger;
		}
		
		
		public void validateCoachingPrioritiesUpdate(ClientUpdateDetails clientUpdateDetails) {
			LOGGER.info("Validate coaching priorities updated successfully");
			SoftAssert softAssert = new SoftAssert();
			String coachingPriority = getAttributeByValue("value", getCoachingPriorities());
			softAssert.assertTrue(coachingPriority.equals(clientUpdateDetails.getCoachingPriorities()));
			softAssert.assertTrue(isElementPresent(getSelectedProfessionalCareer(professionalCareerSatisfactionValue)));
			softAssert.assertTrue(isElementPresent(getSelectedMeaningAndPurpose(meaningAndPurposeSatisfactionValue)));
			softAssert.assertTrue(isElementPresent(getSelectedManagementAndLeadershipSkills(managementAndLearningSatisfactionSkillsValue)));
			softAssert.assertTrue(isElementPresent(getSelectedWellBeing(wellBeingSatisfactionValue)));
			softAssert.assertTrue(isElementPresent(getSelectedPersonalLife(personalLifeSatisfactionValue)));
            softAssert.assertTrue(findElement(getMotivatedAreasToWorkOn(updateMotivatedAreasToWork())).isSelected());
            softAssert.assertTrue(findElement(getMotivatedAreasToWorkOn(updateFrequenltyUsedCoachingTriggers())).isSelected());

			softAssert.assertAll();
		
		}
		
}
