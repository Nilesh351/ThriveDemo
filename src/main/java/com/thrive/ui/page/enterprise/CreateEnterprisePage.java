package com.thrive.ui.page.enterprise;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.pojos.user_details.CreateEnterpriseDetails;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;

public class CreateEnterprisePage extends BaseTestPage{
	
	private By getCreateEnterpriseLink() {
		return By.xpath("//li[text()='CREATE ENTERPRISES']");
	}
	
	private By getEnterpriseNameInputField() {
		return By.xpath("//input[@id='name']");
	}
	
	private By getContactUsLink() {
		return By.xpath(".//a[@href=\"mailto:ask@thrivepartners.co.uk?subject=Enquiry%20from%20MyThrive%20Website\"]");
	}
	
	private By getWebsiteInputField() {
		return By.xpath("//input[@id='website']");
	}
	
	private By getAddressLine1InputField() {
		return By.xpath("//input[@id='address1']");
	}
	
	private By getAddressLine2InputField() {
		return By.xpath("//input[@id='address2']");
	}
	
	private By getCityInputField() {
		return By.xpath("//input[@id='city']");
	}
	
	private By getCountyInputField() {
		return By.xpath("//input[@id='county']");
	}
	
	private By getCountryTestNewDropdown() {
		return By.xpath("//ng-select[@name='country']//input");
	}
	
	private By getPostcodeInputField() {
		return By.xpath("//input[@id='postcode']");
	}
	
	private By getEnterpriseDepartmentNewDropdown() {
		return By.xpath("//ng-select[@name='department']//input[@role='combobox']");
	}
	
	private By getEnterpriseUnitNewDropdown() {
		return By.xpath("//ng-select[@name='region']//input[@role='combobox']");
	}
	
	private By getValueFromCountryDropdown() {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span");	
	}
	
	private By getValueFromEnterpriseDepatmentDropdown() {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span");
	}
	
	private By getValueFromEnterpriseUnitDropdown() {
		return By.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span");	
	}
	
	private By getCreateEnterpriseButton() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText("//button[contains(text(),'admin.enterprises.create.create_enterprise')]"));	
		} else {
		return By.xpath("//button[contains(text(),'Créer une entreprise')]");
		}
	}
	
	public CreateEnterprisePage clickCreateEnterprisesLink() {
		click(getCreateEnterpriseLink());
		return this;
	}
	
	public CreateEnterprisePage setEnterpriseNameInputField(String enterprisename) {
		LOGGER.info("Set enterprise name to search");
		setValue(enterprisename,getEnterpriseNameInputField());
		return this;
	}
	
	public CreateEnterprisePage setWebsiteInputField(String website) {
		LOGGER.info("Set value to website field");
		setValue(website,getWebsiteInputField());
		return this;
	}
	
	public CreateEnterprisePage setAddressLine1InputField(String address1) {
		LOGGER.info("Set value to Address line 1 field");
		setValue(address1,getAddressLine1InputField());
		return this;
	}
	
	public CreateEnterprisePage setAddressLine2InputField(String address2) {
		LOGGER.info("Set value to Address line 2 field");
		setValue(address2,getAddressLine2InputField());
		return this;
	}
	
	public CreateEnterprisePage setCityInputField(String city) {
		LOGGER.info("Set value to city field");
		setValue(city,getCityInputField());
		return this;
	}
	
	public CreateEnterprisePage setCountyInputField(String county) {
		LOGGER.info("Set value to county field");
		setValue(county,getCountyInputField());
		return this;
	}
	
	public CreateEnterprisePage selectValueFromCountrySuggestionDropdown(String country) {
		LOGGER.info("Selecting country from the dropdown");
		setValue(country,getCountryTestNewDropdown());
		selectGivenValueFromAutoDropdown(getValueFromCountryDropdown(),country);
		return this;
	}
	
	public CreateEnterprisePage setPostcodeInputField(String postcode) {
		LOGGER.info("Set value to Postcode field");
		setValue(postcode,getPostcodeInputField());
		return this;
	}
	
	private By getEnterpriseDepartmentsDropdown() {
		return By.xpath(".//ng-select[@name='department']");
	}
	
	private By getEnterpriseDepartmentsOptionElement(String Enterprise_department) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+Enterprise_department+"']");
	}
	
	private By getEnterpriseUnitDropdown() {
		return By.xpath(".//ng-select[@name='region']");
	}
	
	private By getEnterpriseUnitOptionElement(String Enterprise_unit) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='"+Enterprise_unit+"']");
	}
	
	public CreateEnterprisePage selectEnterpriseDepartments(List<String> departments) {
		LOGGER.info("Selecting enterprise departments");
		click(getEnterpriseDepartmentsDropdown());
		for(String department : departments) {
			click(getEnterpriseDepartmentsOptionElement(department));
		}
		return this;	
	}
	
	public CreateEnterprisePage selectEnterpriseUnit(List<String> units) {
		LOGGER.info("Selecting enterprise units");
		click(getEnterpriseUnitDropdown());
		for(String unit : units) {
			click(getEnterpriseUnitOptionElement(unit));
		}
		return this;
	}
	
	public CreateEnterprisePage selectValueFromEnterpriseUnitDropdown(String entunit){
		LOGGER.info("Select Enterprise units from dropdown ");
		setValue(entunit,getEnterpriseUnitNewDropdown());
		selectGivenValueFromAutoDropdown(getValueFromEnterpriseUnitDropdown(),entunit);
		return this;
	}
	
	public AlertsAndMessagesPage clickCreateEnterpriseButton() {
		LOGGER.info("Clicking create enterprise button");
		//moveToElement(getContactUsLink());
		click(getCreateEnterpriseButton());
		return new AlertsAndMessagesPage();
	}
	
	private By getAllowRecordingsOfSessionstoAllowCollectionOfInsightsRadio(boolean isAllowed) {
	if(Config.getLocalizationLanguage().contains("en")){
		if(isAllowed) {
			return By.xpath(getXpathByText("//label[contains(text(),'recordings of sessions')]/..//following-sibling::span//label[contains(text(),'ui.buttons.Yes')]/span"));
		} else {
			return By.xpath(getXpathByText("//label[contains(text(),'recordings of sessions')]/..//following-sibling::span//label[contains(text(),'ui.buttons.No')]/span"));
		}
	} else {
		if(isAllowed) {
			return By.xpath("//label[contains(text(),'Autoriser les enregistrements de sessions pour permettre la collecte d')]/..//following-sibling::span//label[contains(text(),'Oui')]/span");
		} else {
			return By.xpath("//label[contains(text(),'Autoriser les enregistrements de sessions pour permettre la collecte d')]/..//following-sibling::span//label[contains(text(),'Non')]/span");
		}
	}
	}
	
	private By getSessionLengthAvailableCheckbox(String length) {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath("//label[contains(text(),'Session Length Available')]/..//following-sibling::div//label[normalize-space(text())='"+length+"']/span");
		} else {
		return By.xpath("//label[contains(text(),'Durée de la session disponible')]/..//following-sibling::div//label[normalize-space(text())='"+length+"']/span");
		}
	}
	
	public CreateEnterprisePage selectSessionLength(List<String> lengths) {
		LOGGER.info("Selecting session length");
		for(String length : lengths ) {
			click(getSessionLengthAvailableCheckbox(length));
		}
		return this;
	}
	
	public CreateEnterprisePage selectAllowRecordingSessionOption(boolean isAllowed) {
		LOGGER.info("Selecting radion button Related to Session Recording");
		click(getAllowRecordingsOfSessionstoAllowCollectionOfInsightsRadio(isAllowed));
		return this;
	}
	
	
	public CreateEnterprisePage submitCreateEnterpriseDetails(CreateEnterpriseDetails createEnterpriseDetails) {
		LOGGER.info("Submitting details to create enterprise");
		setEnterpriseNameInputField(createEnterpriseDetails.getEntname());
		setWebsiteInputField(createEnterpriseDetails.getWebsite());
		setAddressLine1InputField(createEnterpriseDetails.getAddress1());
		setAddressLine2InputField(createEnterpriseDetails.getAddress2());
		setCityInputField(createEnterpriseDetails.getCity());
		setCountyInputField(createEnterpriseDetails.getCounty());
		selectValueFromCountrySuggestionDropdown(createEnterpriseDetails.getCountry());
		setPostcodeInputField(createEnterpriseDetails.getPostcode());
		selectEnterpriseDepartments(createEnterpriseDetails.getDepartments());
		selectEnterpriseUnit(createEnterpriseDetails.getUnits());
		selectAllowRecordingSessionOption(createEnterpriseDetails.isAllowRecording());
		selectSessionLength(createEnterpriseDetails.getSessionLength());
		return this;
	}
	

	private By getCreateEnterpriseHeaderElement() {
		return By.xpath(".//h2[text()='Enterprise Details']");
	}
	
	public CreateEnterprisePage validateCreateEnterprisePageVisible() {
		LOGGER.info("Validate create enterrpise page visible");
		Assert.assertTrue(isElementVisible(getCreateEnterpriseHeaderElement()), "Create enterprise page is not visible");
		return this;
	}

	public void validateCreateEnterprisePage( String user) {
		LOGGER.info("Validate Create Enterprise Page");
		Assert.assertTrue(getDriver().getTitle().contains("Create Enterprises"),"This user "+user +" don't have access to Create Enterprise");
	}

}