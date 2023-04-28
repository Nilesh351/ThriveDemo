package com.thrive.ui.page.book_session;

import org.openqa.selenium.By;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.utils.DBUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class ArrangeSessionDetailsPage extends BaseTestPage{
	
	
	private By getSelectClientDropdown() {
		return By.xpath("//ng-select[@name='clients']//div[contains(@class,'select-container')]");
	}
	
	private By getSelectClientValueFromDropdown(String clientName) {
		return By.xpath("//span[contains(text(),'" + clientName + "')]");
	}
	
	private By getSessionLengthDropdown() {
		return By.xpath("//ng-select[@name='creditType']//div[contains(@class,'select-container')]");
	}
	
	private By getSessionLengthValueFromDropdown(String length) {
		return By.xpath(".//span[contains(text(),'"+length+"')]");
	}
	
	private By getSessionDateDropdown() {
		return By.xpath("//ngbd-datepicker-i18n-input[@name='session_date']//input");
	}
	
	private By getFirstDateElement() {
		return By.xpath(".//input[@name='session_date']/following-sibling::ngb-datepicker//div[contains(@class,'btn-light ng-star-inserted')]");
	}
	
	private By getSessionTimeDropdown() {
		return By.xpath("//ng-select[@name='session_time']//input");
	}
	
	private By getFirstTimeElement() {
		return By.xpath(".//div[@class='ng-option ng-star-inserted']/span");
	}
	
	private By getExpertiseDropdown() {
		return By.xpath("//ng-select[@id='expertise']//input");
	}
	
	private By getExpertiseValueFromDropdown(String expertise) {
		return By.xpath(".//span[text()='"+expertise+"']");
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'private.arrange_session.arrange.step1.next')]"));
	}
	
	
	public ArrangeSessionDetailsPage selectClient(String clientName) {
		LOGGER.info("Selecting language");
		click(getSelectClientDropdown());
		click(getSelectClientValueFromDropdown(clientName));
		return this;
		
	}
	
	public ArrangeSessionDetailsPage selectSessionLength(String length) {
		LOGGER.info("Selecting Session length");
		click(getSessionLengthDropdown());
		click(getSessionLengthValueFromDropdown(length));
		return this;
		
	}
	
	public ArrangeSessionDetailsPage selectSessionDate() {
		LOGGER.info("Selecting Session Date");
		mediumWait();
		click(getSessionDateDropdown());
		click(getFirstDateElement());
		return this;
	}
	
	public ArrangeSessionDetailsPage selectTime() {
		LOGGER.info("Selecting Session Time");
		click(getSessionTimeDropdown());
		click(getFirstTimeElement());
		return this;
	}
	
	public ArrangeSessionDetailsPage selectExpertise(String expertise) {
		LOGGER.info("Selecting Session expertise");
		click(getExpertiseDropdown());
		click(getExpertiseValueFromDropdown(expertise));
		return this;
	}
	
	
	public BookSessionSummaryPage clickNextButton() {
		LOGGER.info("click on next button");
		click(getNextButton());
		return new BookSessionSummaryPage();
	}
	
	
	public ArrangeSessionDetailsPage enterSessionDetails(BookSessionDetails bookSeesionDeatils) {
		LOGGER.info("Entering session details");
		selectClient(Config.getClientName());
		selectSessionDate();
		selectTime();
		String expertise = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(bookSeesionDeatils.getExpertise()));
		if(Config.getLocalizationLanguage().contains("en")) {
			selectExpertise(bookSeesionDeatils.getExpertise());
		} else {
		selectExpertise(expertise);
		}
		return this;
	}
	
	
}
