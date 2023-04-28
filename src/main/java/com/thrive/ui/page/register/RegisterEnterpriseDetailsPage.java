 package com.thrive.ui.page.register;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.user_details.EnterpriseDetails;
import com.thrive.common.testdata.pojos.user_details.EnterpriseUpdateDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.category.EnterpriseConfigureCategoryPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.enterprise.EnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;

public class RegisterEnterpriseDetailsPage extends BaseTestPage {
	
	String department;
	String unit;
	String country;
	String allowRecordings;
	String allCoaches;
	String enableSessionInsight;
	String getCity;
	String errorMsg = ThriveAppSharedData.MANDATORY_FIELD_ERROR_MSG.getValue();

	private By getEnterpriseaNameInput() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.5rcwszrcrm.label']/../following-sibling::input"));
	}
	
	private By getEnterpriseNameInputRm() {
		return By.xpath(".//label[contains(text(),'Nom de l')]/../following-sibling::input");
	}
	
	private By getEnterpriseCodeInput() {
		return By.xpath(getXpathByText("//label[(text())='questions.registeration_enterprise.aehpfvpyf8k.label']/../following-sibling::input"));
	}
	
	private By getEnterpriseCodeInputRm() {
		return By.xpath(getXpathByText("//label[(text())='questions.registeration_enterprise.0yzi6flkib0f.label']/../following-sibling::input"));
	}

	private By getInformationUsefulForCoachToKnow() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_enterprise.a337hv016.label')]/../following-sibling::textarea"));
	}
	
	private By getInformationUsefulForCoachToKnowRm() {
		return By.xpath(getXpathByText(
				".//label[contains(text(),'questions.registeration_enterprise.xkv2pn6gq4.label')]/../following-sibling::textarea"));
	}

	private By getWebsiteInput() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.kpqoyp3cazn.label']/../following-sibling::input"));
	}
	
	private By getWebsiteInputRm() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.ephlnodu2cm.label']/../following-sibling::input"));
	}

	private By getAddressLine1Input() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.ur64cnelgm8.label']/../following-sibling::input"));
	}
	
	private By getAddressLine1InputRm() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.3d7gl64v7qd.label']/../following-sibling::input"));
	}

	private By getAddressLine1ErrorMsg() {
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_enterprise.ur64cnelgm8.label')]/../following-sibling::div/p[text()='"+ errorMsg + "']"));
		}else {
			return By.xpath(getXpathByText(".//label[contains(text(),'questions.registeration_enterprise.3d7gl64v7qd.label')]/../following-sibling::div/p[text()='"+ errorMsg + "']"));
		}
		
	}
	
	private By getAddressLine2Input() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.h2c15ful075.label']/../following-sibling::input"));
	}
	
	private By getAddressLine2InputRm() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.fzqy3yxxlaw.label']/../following-sibling::input"));
	}

	private By getCountyInput() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.dcd33jos6u.label']/../following-sibling::input"));
	}
	
	private By getCountyInputRm() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.onrkzhgkep.label']/../following-sibling::input"));
	}

	private By getCountryDropdown() {
		return By.xpath(".//ng-select[@name='country']");
	}

	private By getCountryDropdownErrorMsg() {
		return By.xpath(getXpathByText(".//ng-select[@name='country']/..//following-sibling::div/p[text()='" + errorMsg + "']"));
	}

	private By getCountryOptionElement(String Countryname) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[contains(text(),'"+Countryname+"')]");
	}

	private By getPostcodeInput() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.5kus42miqkm.label']/../following-sibling::input"));
	}
	
	private By getPostcodeInputRm() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.a6amwboecm5.label']/../following-sibling::input"));
	}

	private By getPostCodeInputErrorMsg() {
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			return By.xpath(
					getXpathByText(".//label[(text())='questions.registeration_enterprise.5kus42miqkm.label']/..//following-sibling::div/p[text()='" + errorMsg + "']"));
		}else {
			return By.xpath(
					getXpathByText(".//label[(text())='questions.registeration_enterprise.a6amwboecm5.label']/..//following-sibling::div/p[text()='" + errorMsg + "']"));
		}
		
	}

	private By getEnterpriseDepartmentsDropdown() {
		return By.xpath(".//ng-select[@name='department']");
	}

	private By getEnterpriseDepartmentErrorMsg() {
		return By.xpath(getXpathByText(".//ng-select[@name='department']/..//following-sibling::div/p[text()='" + errorMsg + "']"));
	}

	private By getEnterpriseDepartmentsOptionElement(String Enterprise_department) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[contains(text(),'"+Enterprise_department+"')]");
	}
	
	private By getEnterpriseUnitDropdown() {
		return By.xpath(".//ng-select[@name='regions']");
	}

	private By getEnterpriseUnitErrorMsg() {
		return By.xpath(getXpathByText(".//ng-select[@name='regions']/..//following-sibling::div/p[text()='" + errorMsg + "']"));
	}

	private By getEnterpriseUnitOptionElement(String Enterprise_unit) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[contains(text(),'"+Enterprise_unit+"')]");
	}

	private By getAllowRecordingsOfSessionstoAllowCollectionOfInsightsRadio(boolean isAllowed) {
		if(Config.getLocalizationLanguage().contains("en")) {
		if (isAllowed) {
			return By.xpath(
					getXpathByText("//label[text()='register.enterprise.allow_recordings']/..//following-sibling::div//label[(text())='questions.registeration_enterprise.cbunsd7sryv.yes']/span"));
		} else {
			return By.xpath(
					getXpathByText("//label[text()='register.enterprise.allow_recordings']/..//following-sibling::div/label[(text())='questions.registeration_enterprise.cbunsd7sryv.no']/span"));
		}
		} else {
			if (isAllowed) {
				return By.xpath(
						getXpathByText("//label[contains(text(),'Autoriser les enregistrements de sessions pour permettre la collecte')]/..//following-sibling::div//label[(text())='questions.registeration_enterprise.792y739ju8m.yes']/span"));
			} else {
				return By.xpath(
						getXpathByText("//label[contains(text(),'Autoriser les enregistrements de sessions pour permettre la collecte')]/..//following-sibling::div/label[(text())='questions.registeration_enterprise.kre7baq251p.no']/span"));
			}
		}
	}

	private By getAllCoachesRadio(boolean isAllowed) {
		if (isAllowed) {
			return By.xpath(
					getXpathByText("//label[contains(text(),'questions.registeration_enterprise.7trf3oteg5y.label')]/..//following-sibling::div//label[contains(text(),'ui.buttons.Yes')]/span"));
		} else {
			return By.xpath(
					getXpathByText("//label[contains(text(),'questions.registeration_enterprise.7trf3oteg5y.label')]/..//following-sibling::div//label[contains(text(),'ui.buttons.No')]/span"));
		}
	}
	
	private By getAllCoachesRadioRm(boolean isAllowed) {
		if (isAllowed) {
			return By.xpath(
					getXpathByText("//label[(text())='questions.registeration_enterprise.kre7baq251p.label']/..//following-sibling::div//label[(text())='questions.registeration_enterprise.792y739ju8m.yes']/span"));
		} else {
			return By.xpath(
					getXpathByText("//label[(text())='questions.registeration_enterprise.kre7baq251p.label']/..//following-sibling::div//label[(text())='questions.registeration_enterprise.792y739ju8m.no']/span"));
		}
	}

	private By getEnableSessioninsightsRadio(boolean isAllowed) {
		if (isAllowed) {
			return By.xpath(
					getXpathByText("//label[(text())='questions.registeration_enterprise.wy9g2e0tk8l.label']/..//following-sibling::div//label[(text())='questions.registeration_enterprise.wy9g2e0tk8l.yes']/span"));
		} else {
			return By.xpath(
					getXpathByText("//label[(text())='questions.registeration_enterprise.wy9g2e0tk8l.label']/..//following-sibling::div//label[(text())='questions.registeration_enterprise.wy9g2e0tk8l.no']/span"));
		}
	}

	private By getCreditAllocationOnVerification() {
		return By.xpath(".//input[@name='credits']");
	}

	private By getLogoImage() {
		return By.xpath(getXpathByText("//label[(text())='questions.registeration_enterprise.3fu2ku1k5hs.label']/..//following-sibling::div/input[@id='photo']"));
	}

	private By getSessionLengthAvailableCheckbox(String length) {
		return By.xpath(
				getXpathByText("//label[(text())='questions.registeration_enterprise.zov06pgk2e.label']/..//following-sibling::div//label[(text())='"+ length + "']/span"));
	}
	
	private By getSessionLengthAvailableCheckboxRm(String length) {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText("//label[(text())='questions.registeration_enterprise.r990amog70p.label']/..//following-sibling::div//label[(text())='"+ length + "']/span"));
		} else {
		return By.xpath("//label[normalize-space(text())='Durée de la session disponible']/..//following-sibling::div//label[normalize-space(text())='"+ length + "']/span");
		}
	}
	
	

	private By getSeesionLengthAvailableErrorMsg() {
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			return By.xpath(getXpathByText(
					"//label[contains(text(),'questions.registeration_enterprise.zov06pgk2e.label')]/..//following-sibling::div/p[text()='"
							+ errorMsg + "']"));
		}
		else {
				if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(getXpathByText(
						"//label[(text())='questions.registeration_enterprise.r990amog70p.label']/..//following-sibling::div/p[text()='"
								+ errorMsg + "']"));
			} else {
				return By.xpath(
						"//label[contains(text(),'Durée de la session disponible')]//following-sibling::div//p[contains(text(),'Ce champ est requis')]");
			}
		}
	}

	private By getCityInput() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.as1l0d0ddul.label']/../following-sibling::input"));
	}
	
	private By getCityInputRm() {
		return By.xpath(getXpathByText(".//label[(text())='questions.registeration_enterprise.iieamd2633.label']/../following-sibling::input"));
	}

	private By getCityErrorMsg() {
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			return By.xpath(
					getXpathByText(".//label[(text())='questions.registeration_enterprise.as1l0d0ddul.label']/..//following-sibling::div/p[text()='" + errorMsg + "']"));
		}else {
			return By.xpath(
					getXpathByText(".//label[(text())='questions.registeration_enterprise.iieamd2633.label']/..//following-sibling::div/p[text()='" + errorMsg + "']"));
		}
		
	}

	private By getFirstAvailableElementFromDropdown() {
		return By.xpath(
				".//div[@class='ng-dropdown-panel-items scroll-host']//div[@class='ng-option ng-star-inserted']/span");
	}

	private By getAllowRecordingOfSessionsSelectedValue() {
		return By.xpath(getXpathByText(
				"//label[contains(text(),'questions.registeration_enterprise.cbunsd7sryv.label')]/..//following-sibling::div//span[contains(@class,'active')]/.."));
	}
	
	private By getAllowCoachesSelectedValue() {
		return By.xpath(getXpathByText(
				"//label[contains(text(),'questions.registeration_enterprise.7trf3oteg5y.label')]/..//following-sibling::div//span[contains(@class,'active')]"));
	}
	
	private By getAllowCoachesSelectedValueRm() {
		return By.xpath(getXpathByText(
				"//label[(text())='questions.registeration_enterprise.kre7baq251p.label']/..//following-sibling::div//span[@class='check active']/.."));
	}

	private By getEnbaleSessionInsightsSelectedValue() {
		return By.xpath(getXpathByText(
				"//label[(text())='questions.registeration_enterprise.wy9g2e0tk8l.label']/..//following-sibling::div//span[contains(@class,'active')]/.."));
	}

	private By getEnterpriseDepartmentValue(String department) {
		return By.xpath(".//span[text()='" + department + "']");
	}

	private By getEnterpriseUnitValue(String unit) {
		return By.xpath(".//span[text()='" + unit + "']");
	}

	private By getUpdateDeatils() {
		return By.xpath(getXpathByText(".//button[text()='private.profile.enterprise.update_details']"));
	}

	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}

	private By getSelectedSessionLength(String length) {
		return By.xpath(".//label[normalize-space(text())='" + length + "']/input");

	}

	private By getSaveAndontinueButton() {
		return By.xpath(getXpathByText(".//button[text()='register.enterprise.save']"));
	}
	
	private By getUnitRemoveIcon() {
		return By.xpath(".//ng-select[@name='regions']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getDepartmentRemoveIcon() {
		return By.xpath(".//ng-select[@name='department']//div[3][contains(@class,'ng-star-inserted')]//span[text()='×']");
	}
	
	private By getcreditcount() {
		return By.xpath("//div[text()='20 credits']");
	}
	
	private By getClientLink() {
	if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//li[(text()) ='admin.user_management.clients_title']"));
	 } else {
		return By.xpath(".//li[(text()) ='COACHÉS']");
	    }
	}
	
	private By getEnterpriseBrandingLink() {
		return By.xpath(getXpathByText(".//li[contains(text(),'page_titles.platform_branding')]"));
	}
	
	private By getConfigureCategoriesLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(".//span[text()='Coaching Categories']");
		} else {
			if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(getXpathByText(".//li[contains(text(),'private.profile.enterprise.categories_list')]"));
			} else {
				return By.xpath("//li[contains(text(),'CONFIGURER LES CATÉGORIES')]");
			}
		}

	}

	private By getRomeveCreditElement() {
		return By.xpath("//descendant::div[contains(text(),'MyThrive credits')]//parent::div[@class='ng-star-inserted']//descendant::div[@class='col-xs-12 mycredit-row ng-star-inserted'][1]//child::div[@style='cursor: pointer;']");
	}
	
	private By getEnterpriseCredits() {
		return By.xpath(".//div[@class='mycredit']/div/div[1]");
	}
	
	private By getRemoveCreditsIcon() {
		return By.xpath(".//div[normalize-space(text())='✖']");
	}
	
	public RegisterEnterpriseDetailsPage verifyCreditAllocationCount(String value) {
		LOGGER.info("verify credit allocation value");
		Assert.assertEquals(getText(getcreditcount()), value, "Allocate credit count successful");
		return this;
	}
	
	public int getEnterpriseCurrentCredits() {
		LOGGER.info("Get enterprise credits");
		int curCredit =0;
		
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			String eaCredit = getText(getEnterpriseCredits());
			String currentCredits = eaCredit.split(" ")[2];
			curCredit = Integer.parseInt(currentCredits);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			String eaCredit = getText(getEnterpriseCredits());
			String currentCredits = eaCredit.split(" ")[2].trim();
			curCredit = Integer.parseInt(currentCredits);
		}
		
		return curCredit;
	}
	
	public RegisterEnterpriseDetailsPage clickRemoveCreditsIcon() {
		LOGGER.info("click remove credits icon");
		shortWait();
		click(getRemoveCreditsIcon());
		return this;
	}
	
	public ClientsPage clickClients() {
		LOGGER.info("Clicking Clients link");
		click(getClientLink());
		return new ClientsPage();
	}
	
	public EnterpriseConfigureCategoryPage clickConfigureCategories() {
		LOGGER.info("Clicking configure categories link");
		waitUntilElementIsClickable(5, getConfigureCategoriesLink());
		click(getConfigureCategoriesLink());
		return new EnterpriseConfigureCategoryPage();
	}

	
	public RegisterEnterpriseDetailsPage validateEnterpriseBrandingLinkVisible() {
		LOGGER.info("verify Enterprise Branding page is available");
		Assert.assertTrue(isElementVisible(getEnterpriseBrandingLink()), "Enterprise Branding Link is not visble");
		return this;
	}
	
	public RegisterEnterpriseDetailsPage validateEnterpriseBrandingLinkIsNotVisible() {
		LOGGER.info("verify Enterprise Branding page is available");
		Assert.assertTrue(isElementNotVisible(getEnterpriseBrandingLink()), "Enterprise Branding Link is visble");
		return this;
	}

	public RegisterEnterpriseDetailsPage clickRemoveCreditElement() {
		LOGGER.info("Clicking remove credit element");
		mediumWait();
		click(getRomeveCreditElement());
		return this;
	}
	
	private By getRemoveCreditPageLink() {
		return By.xpath("//div[@class='modal-content']");	
	}
	
	public void validateRemoveCreditPageIsVisible() {
		LOGGER.info("Validating Remove Credit page appears.");
		Assert.assertTrue(isElementVisible(getRemoveCreditPageLink()),"Remove credit windoe is not visible.");
	}
	
	private By getSaveAndContinueButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'register.enterprise.save')]"));
	}
	
	public RegisterEnterpriseDetailsPage validateCreditAssignedRefelected(int current,int updated,String count) {
		int providedcredit = Integer.parseInt(count);
		int expected = providedcredit + current;
		Assert.assertEquals(updated,expected,"credits are not matching");
		return this;
	}
	
	public RegisterEnterpriseDetailsPage validateBookSessionCreditsReflected(int current,int updated,int count) {
		LOGGER.info("Validate book session credit assigned reflected");
		int expected = current - count;
		Assert.assertEquals(updated, expected,"credits are not matching");
		return this;
		
	}
	
	public RegisterEnterpriseDetailsPage validateCancelSessionCreditsReflectedCheck(int current , int updated,int count) {
		LOGGER.info("Validate cancel session credit reflected");
		current = current - count;
		Assert.assertEquals(current, updated,"credits are not matching");
		return this;
	}
	
	public RegisterEnterpriseDetailsPage validateCreditRemovedRefected(int current,int updated,String count) {
		int providedcredit = Integer.parseInt(count);
		int expected = current - providedcredit;
		Assert.assertEquals(updated, expected,"credits are not matching");
		return this;
	}

	public int enterpriseCredits() {
		LOGGER.info("Get enterprise credits");
		shortWait();
		String eaCredit = getText(getEnterpriseCredits());
		String credits = eaCredit.split(" ")[2];
		int curCredit = Integer.parseInt(credits);
		return curCredit;
	}

	public RegisterEnterpriseDetailsPage setEnterpriseName(String Ent_name) {
		LOGGER.info("Setting value for enterprise name");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValueWithoutValidation(Ent_name, getEnterpriseaNameInput());
		}
		else {
			setValueWithoutValidation(Ent_name, getEnterpriseNameInputRm());
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage setEnterpriseCode(String entCode) {
		LOGGER.info("Setting value for Enterprise code");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(entCode, getEnterpriseCodeInput());
		}
		else {
			setValue(entCode, getEnterpriseCodeInputRm());
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage setInformationUsefulForCoachToKnow(String info) {
		LOGGER.info("Setting value for 'Information useful for coach to know'");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(info, getInformationUsefulForCoachToKnow());
		}
		else {
			setValue(info, getInformationUsefulForCoachToKnowRm());
		}	
		return this;
	}

	public RegisterEnterpriseDetailsPage setWebsite(String website) {
		LOGGER.info("Setting value for Website");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValueWithoutValidation(website, getWebsiteInput());
		}
		else {
			setValueWithoutValidation(website, getWebsiteInputRm());
		}	
		return this;
	}
	
	public RegisterEnterpriseDetailsPage setCity(String city) {
		LOGGER.info("Setting value for city");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValue(city,getCityInput());
		}else {
			setValue(city, getCityInputRm());
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage setAddressline1(String address1) {
		LOGGER.info("Setting value for address line 1");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValueWithoutValidation(address1, getAddressLine1Input());
		}
		else {
			setValueWithoutValidation(address1, getAddressLine1InputRm());
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage setAddressline2(String address2) {
		LOGGER.info("Setting value for address line 2");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValueWithoutValidation(address2, getAddressLine2Input());
		}
		else {
			setValueWithoutValidation(address2, getAddressLine2InputRm());
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage setCounty(String county) {
		LOGGER.info("Setting value for country");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValueWithoutValidation(county, getCountyInput());
		}
		else {
			setValueWithoutValidation(county, getCountyInputRm());
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage selectCountry(String country) {
		LOGGER.info("Selecting country from dropdown");
		click(getCountryDropdown());
		click(getCountryOptionElement(country));
		return this;
	}

	public RegisterEnterpriseDetailsPage selectFirstAvailableCountry() {
		LOGGER.info("Selecting first available country from dropdown");
		click(getCountryDropdown());
		country = getText(getFirstAvailableElementFromDropdown());
		click(getFirstAvailableElementFromDropdown());
		return this;
	}

	public RegisterEnterpriseDetailsPage setPostcode(String postcode) {
		LOGGER.info("Setting value for post code");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			setValueWithoutValidation(postcode, getPostcodeInput());
		}
		else {
			setValueWithoutValidation(postcode, getPostcodeInputRm());
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage selectEnterpriseDepartments(List<String> departments) {
		LOGGER.info("Selecting departments");
		click(getEnterpriseDepartmentsDropdown());
		for (String department : departments) {
			click(getEnterpriseDepartmentsOptionElement(department));
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage selectFirstAvailableEnterpriseDepartment() {
		LOGGER.info("Selecting first available department");
		click(getDepartmentRemoveIcon());
		click(getEnterpriseDepartmentsDropdown());
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				department = getText(getFirstAvailableElementFromDropdown());
				click(getFirstAvailableElementFromDropdown());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		
		return this;
	}

	public RegisterEnterpriseDetailsPage selectEnterpriseUnit(List<String> units) {
		LOGGER.info("Selecting enterprise unit");
		click(getEnterpriseUnitDropdown());
		for (String unit : units) {
			click(getEnterpriseUnitOptionElement(unit));
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage selectFirstAvailabelEnterpriseUnit() {
		LOGGER.info("Selecting first available enterprise unit");
		click(getUnitRemoveIcon());
		click(getEnterpriseUnitDropdown());
		int counter = 0;
		boolean flag = true;
		do {
			try {
				shortWait();
				unit = getText(getFirstAvailableElementFromDropdown());
				click(getFirstAvailableElementFromDropdown());
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while(!flag && counter<=5);
		
		return this;
	}

	public RegisterEnterpriseDetailsPage selectAllowRecordingSessionOption(boolean isAllowed) {
		allowRecordings = getText(getAllowRecordingOfSessionsSelectedValue());
		click(getAllowRecordingsOfSessionstoAllowCollectionOfInsightsRadio(isAllowed));
		return this;
	}

	public RegisterEnterpriseDetailsPage selectAllCoaches(boolean isAllowed) {
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			allCoaches = getText(getAllowCoachesSelectedValue());
			click(getAllCoachesRadio(isAllowed));
		}
		else {
			allCoaches = getText(getAllowCoachesSelectedValueRm());
			click(getAllCoachesRadioRm(isAllowed));
		}
		return this;
	}

	public RegisterEnterpriseDetailsPage selectEnableSessionInsights(boolean isAllowed) {
		LOGGER.info("Selecting Enable session insight");
		enableSessionInsight = getText(getEnbaleSessionInsightsSelectedValue());
		click(getEnableSessioninsightsRadio(isAllowed));
		return this;
	}

	public RegisterEnterpriseDetailsPage setCreditAllocationOnVerification(String credit) {
		LOGGER.info("Selecting value for Credit allocation on verification");
		setValue(credit, getCreditAllocationOnVerification());
		return this;
	}

	public RegisterEnterpriseDetailsPage setLogo() {
		LOGGER.info("Selecting logo");
		click(getLogoImage());
		return this;
	}

	public RegisterEnterpriseDetailsPage selectSessionLength(List<String> lengths) {
		LOGGER.info("Selecting session length");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
		for (String length : lengths) {
			if(isChecked(getSelectedSessionLength(length))) {
				click(getSessionLengthAvailableCheckbox(length));	
				click(getSessionLengthAvailableCheckbox(length));
			}
			else {
				click(getSessionLengthAvailableCheckbox(length));
			}
		}
		}
		else {
			for (String length : lengths) {
				if(isChecked(getSelectedSessionLength(length))) {
					click(getSessionLengthAvailableCheckboxRm(length));	
					click(getSessionLengthAvailableCheckboxRm(length));
				}
				else {
					click(getSessionLengthAvailableCheckboxRm(length));
				}
			}
		}
		return this;
	}

	public AlertsAndMessagesPage clickUpdateDetails() {
		LOGGER.info("Clicking update details");
		click(getUpdateDeatils());
		return new AlertsAndMessagesPage();
	}

	public RegisterClientRegistrationDetailsPage clickSaveAndContinueButton() {
		LOGGER.info("Clicking Save And Coninue button");
		shortWait();
		click(getSaveAndContinueButton());
		return new RegisterClientRegistrationDetailsPage();
	}

	public RegisterEnterpriseDetailsPage validateToaster(String expectedMessage) {
		LOGGER.info("Validating toaster message");
		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.contains(expectedMessage), "Failed to validate the alert message :"
				+ "expected is : " + expectedMessage + " and actual is : " + actualMessage);
		return this;
	}

	

	public RegisterEnterpriseDetailsPage validateCreditConsumptionRefund(int actual, int expected) {
		LOGGER.info("Validate credit consumption");
		Assert.assertEquals(actual, expected, "credits are not matching");
		return this;
	}

	public RegisterEnterpriseDetailsPage validateCancelSessionCreditsReflected(int current, int updated, int count,
			int time) {
		LOGGER.info("Validate cancel session credit reflected");
		current = current - count;
		if (time >= 24) {
			Assert.assertEquals(current, updated, "credits are not matching");
		} else {
			Assert.assertEquals(current, updated, "credits are not matching");
		}
		return this;
	}

	

	public RegisterClientInformationPage submitEnterpriseDetails(EnterpriseDetails enterpriseDetails) {
		LOGGER.info("Submitting enterprise details");
		// setWebsite(enterpriseDetails.getWebsite());
		setAddressline1(enterpriseDetails.getAddress1());
		setAddressline2(enterpriseDetails.getAddress2());
		setCounty(enterpriseDetails.getCounty());
		selectCountry(enterpriseDetails.getCountry());
		setPostcode(enterpriseDetails.getPostcode());
		selectEnterpriseDepartments(enterpriseDetails.getDepartments());
		selectEnterpriseUnit(enterpriseDetails.getUnits());
		// selectAllowRecordingSessionOption(enterpriseDetails.isSessionRecordingAllowed());
		selectSessionLength(enterpriseDetails.getSessionLength());
		setCity(enterpriseDetails.getCity());
		clickSaveAndContinueButton();
		return new RegisterClientInformationPage();
	}

	public RegisterEnterpriseDetailsPage validateEnterpriseDetails(EnterpriseUpdateDetails enterpriseUpdateDetails) {
		LOGGER.info("Validating enterprise details");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				getCity = getAttributeByValue("value", getCityInput());
			} catch (Exception e) {
				if (e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while (flag && counter <= 5);
		String getEnterpriseName;
		String getEnterpriseCode;
		String getUsefulCoachInfo;
		String getWebsite;
		String getAddressLine1;
		String getAddressLine2;
		String getCounty;
		String getPostcode;
		String getCreditOnVerification;
		String getAllowRecordings = " ";
		String getEnableSessionInsight = " ";
		String getAllCoaches;
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			getEnterpriseName = getAttributeByValue("value", getEnterpriseaNameInput());
			getEnterpriseCode = getAttributeByValue("value", getEnterpriseCodeInput());
			getUsefulCoachInfo = getAttributeByValue("value", getInformationUsefulForCoachToKnow());
			getWebsite = getAttributeByValue("value", getWebsiteInput());
			getAddressLine1 = getAttributeByValue("value", getAddressLine1Input());
			getAddressLine2 = getAttributeByValue("value", getAddressLine2Input());
			getCounty = getAttributeByValue("value", getCountyInput());
			getPostcode = getAttributeByValue("value", getPostcodeInput());
			getCreditOnVerification = getAttributeByValue("value", getCreditAllocationOnVerification());
			getAllowRecordings = getText(getAllowRecordingOfSessionsSelectedValue());
			getAllCoaches = getText(getAllowCoachesSelectedValue());
			getEnableSessionInsight = getText(getEnbaleSessionInsightsSelectedValue());
		}
		else {
			getEnterpriseName = getAttributeByValue("value", getEnterpriseNameInputRm());
			getEnterpriseCode = getAttributeByValue("value", getEnterpriseCodeInputRm());
			getUsefulCoachInfo = getAttributeByValue("value", getInformationUsefulForCoachToKnowRm());
			getWebsite = getAttributeByValue("value", getWebsiteInputRm());
			getAddressLine1 = getAttributeByValue("value", getAddressLine1InputRm());
			getAddressLine2 = getAttributeByValue("value", getAddressLine2InputRm());
			getCounty = getAttributeByValue("value", getCountyInputRm());
			getPostcode = getAttributeByValue("value", getPostcodeInputRm());
			getCreditOnVerification = getAttributeByValue("value", getCreditAllocationOnVerification());
			getAllCoaches = getText(getAllowCoachesSelectedValueRm());
		}

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getEnterpriseName.equalsIgnoreCase(enterpriseUpdateDetails.getEnterpriseName()),
				"enterprise name not updated");
		softAssert.assertTrue(getEnterpriseCode.equalsIgnoreCase(enterpriseUpdateDetails.getEnterpriseCode()),
				"enterprise code not updated");
		softAssert.assertTrue(getUsefulCoachInfo.equalsIgnoreCase(enterpriseUpdateDetails.getInfoUsefulForCoach()),
				"info useful for coach not updated");
		softAssert.assertTrue(getWebsite.equalsIgnoreCase(enterpriseUpdateDetails.getWebsite()), "website not updated");
		softAssert.assertTrue(getAddressLine1.equalsIgnoreCase(enterpriseUpdateDetails.getAddressLine1()),
				"address line1 not updated");
		softAssert.assertTrue(getAddressLine2.equalsIgnoreCase(enterpriseUpdateDetails.getAddressLine2()),
				"address line2 not updated");
		softAssert.assertTrue(getCounty.equalsIgnoreCase(enterpriseUpdateDetails.getCounty()), "county not updated");
		softAssert.assertTrue(getPostcode.equalsIgnoreCase(enterpriseUpdateDetails.getPostcode()),
				"postcode not updated");
		softAssert.assertTrue(
				getCreditOnVerification.equalsIgnoreCase(enterpriseUpdateDetails.getCreditsOnVerification()),
				"credits on verification not updated");
		softAssert.assertTrue(getCity.equalsIgnoreCase(enterpriseUpdateDetails.getCity()), "city not updated");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive"))
		{
			softAssert.assertTrue(allowRecordings.equalsIgnoreCase(getAllowRecordings),
					"allow recordings not updated successfully");
			softAssert.assertTrue(enableSessionInsight.equalsIgnoreCase(getEnableSessionInsight),
					"enable session insight not updated");
		}
		softAssert.assertTrue(allCoaches.equalsIgnoreCase(getAllCoaches), "all coaches not updated");
		softAssert.assertTrue(isElementPresent(getEnterpriseDepartmentValue(department)), "department not updated");
		softAssert.assertTrue(isElementPresent(getEnterpriseUnitValue(unit)), "unit not updated");
		softAssert.assertTrue(isChecked(getSelectedSessionLength("4 h")), "session length not updated");
		softAssert.assertAll();
		return this;
	}

	public RegisterEnterpriseDetailsPage valiadateEnterpriseDeatilsErrorValidation() {
		LOGGER.info("Validating enterprise details error validation");

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getAddressLine1ErrorMsg()),
				"Error message not present for addressline1");
		softAssert.assertTrue(isElementVisible(getCityErrorMsg()), "Error message not present for city");
		softAssert.assertTrue(isElementVisible(getCountryDropdownErrorMsg()), "Error message not present for country");
		softAssert.assertTrue(isElementVisible(getEnterpriseDepartmentErrorMsg()),
				"Error message not present for department");
//		softAssert.assertTrue(isElementVisible(getEnterpriseUnitErrorMsg()), "Error message not present for unit");
		softAssert.assertTrue(isElementVisible(getPostCodeInputErrorMsg()), "Error message not present for postcode");
		softAssert.assertTrue(isElementVisible(getSeesionLengthAvailableErrorMsg()),
				"Error message not present for session length");
		softAssert.assertAll();
		return this;
	}
}