package com.thrive.ui.page.book_session;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DBUtils;
import com.thrive.common.utils.DateTimeUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class BookSessionDetailsPage extends BaseTestPage{
	
	String[] vals = new String[2];
	
	private By getArrangeSessionCheckbox() {
		return By.xpath(getXpathByText(".//label[contains(text(),'shared.header.arrange_session')]//span"));	
	}
	
	public ArrangeSessionDetailsPage clickArrangeSeesionChecbox() {
		LOGGER.info("Clicking Arrage Session button");
		click(getArrangeSessionCheckbox());
		return new ArrangeSessionDetailsPage();
	}
	
	private By getLengthDropdown(int index) {
		return By.xpath(".//ng-select[@id='creditType+"+index+"']/div");
	}
	
	private By getLengthDropdownVal() {
		return By.xpath(".//ng-select[contains(@id,'creditType')]/div");
	}
	
	private By getLengthDropdownOption(String length) {
		return By.xpath(".//div[contains(@class,'ng-star') and contains(text(),'"+length+"')]");
	}
	
	public BookSessionDetailsPage selectLength(int index, String length) {
		LOGGER.info("Selecting session length");
		shortWait();
		click(getLengthDropdown(index));
		click(getLengthDropdownOption(length));
		return this;
		
	}
	
	public BookSessionDetailsPage selectLengthval(String length) {
		LOGGER.info("Selecting session length");
		shortWait();
		click(getLengthDropdownVal());
		click(getLengthDropdownOption(length));
		return this;
		
	}
	
	
	private By getMonthNameElement() {
		return By.xpath(".//input[@name='session_date']/following-sibling::ngb-datepicker//div[contains(@class,'ngb-dp-month-name')]");
	}
	
	private By getDatePickerElement(int index) {
		return By.xpath(".//ngbd-datepicker-i18n-input[@id='SSS"+index+"']/input[@id='session_date']");
	}
	
	private By getDatePicker() {
		return By.xpath("//input[@id='session_date']");
	}
	
	private By getDateElement(Integer date) {
		return By.xpath(".//input[@name='session_date']/following-sibling::ngb-datepicker//div[contains(@class,'btn-light ng-star') and text()='"+date.toString()+"']");
	}
	
	private By getFirstDateElement() {
		return By.xpath(".//input[@name='session_date']/following-sibling::ngb-datepicker//div[contains(@class,'btn-light ng-star')]");
	}
	
	private By getDateElement(int index) {
		return By.xpath(".//input[@name='session_date']/following-sibling::ngb-datepicker//div[@class='ngb-dp-day ng-star-inserted']["+index+"]/div[contains(@class,'btn-light ng-star-inserted')]");
	}
	
	private By getNextMonthArrowElement() {
		return By.xpath(".//input[@name='session_date']/following-sibling::ngb-datepicker//button[@title='Next month']/span");
	}
	
	
	private By getErrorMsg() {
		return By.xpath(getXpathByText(".//p[(text())='ui.messages.required_field']"));
	}
	
	public BookSessionDetailsPage validateErrorMsgPresent() {
		LOGGER.info("Validating Error message is displayed");
		Assert.assertTrue(isElementPresent(getErrorMsg()));
		return this;
	}
	
	private By getNextMonthArrow() {
		return By.xpath("//ngb-datepicker[contains(@class,'dropdown-menu')]//button[@title='Next month']");
	}
	
	public BookSessionDetailsPage selectSpecificDate(int index, String dateMMDDYYYY) {
		LOGGER.info("Selecting date.");
		int counter = 1;
		int date = Integer.parseInt(dateMMDDYYYY.split("/")[0]);
		int month = Integer.parseInt(dateMMDDYYYY.split("/")[1]);
		//String year = dateMMDDYYYY.split("/")[2];
		
		String monthName = DateTimeUtils.getMonthName(month);
		click(getDatePickerElement(index));
		click(getDateElement(date));
		if(!getText(getMonthNameElement()).contains(monthName)) {
			while(getText(getMonthNameElement()).contains(monthName) && counter <=12) {
				click(getNextMonthArrowElement());
			}
		}
		
		return this;
	}
	
	public BookSessionDetailsPage selectFirstVailableDate(int index) {
		LOGGER.info("Selecting first viable date.");
		click(getDatePickerElement(index));
		shortWait();
		index = index + 1;
			if (isElementVisible(getDateElement(index))) {
		         click(getDateElement(index));
 		       } else {
 			     click(getNextMonthArrowElement());
 			    click(getDateElement(index));
 		       }

		return this;
	}
	
//	public BookSessionDetailsPage selectDate(int index) {
//		LOGGER.info("Selecting first viable date.");
//		click(getDatePicker());
//		shortWait();
//		click(getDateElement(index));
//		
//		return this;
//	}
	
	
	private By getTimeElement(int index) {
		return By.xpath(".//ng-select[@id='session_tim+"+index+"']/div");
	}
	
	private By getTimeElement(String time) {
		return By.xpath(".//div[contains(@class,'ng-star') and text()='"+time+"']");
	}
	
	private By getFirstTimeElement() {
		return By.xpath(".//ng-select[contains(@id,'session_tim')]//div[1][contains(@class,'ng-star')]");
	}
	
	public BookSessionDetailsPage selectTime(int index, String time) {
		LOGGER.info("Selecting time");
		click(getTimeElement(index));
		click(getTimeElement(time));
		return this;
		
	}
	
	public BookSessionDetailsPage selectFirstAvailableTime(int index) {
		LOGGER.info("Selecting first available time");
		click(getTimeElement(index));
		click(getFirstTimeElement());
		return this;
		
	}
	
	private By getExpertiseDropdownOption(String option) {
		if(Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//div[@title='"+option+"']/span");
		}else {
			return By.xpath("//div[contains(@class,'expertise-option')]//span[contains(text(),'"+DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(option))+"')]");
		}
	}
	    
	private By getExpertiseDropdown(int index) {
		return By.xpath(".//ng-select[@id='expertise+"+index+"']/div");
	}
	
	public BookSessionDetailsPage selectExpertise(int index, String expertise) {
		LOGGER.info("Selecting expertise");
		click(getExpertiseDropdown(index));
		click(getExpertiseDropdownOption(expertise));
		return this;
		
	}
	
	private By getAddRowButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'private.coach.add_row')]"));
	}
	
	public BookSessionDetailsPage clickAddRow() {
		LOGGER.info("Clicking addition arrow button");
		click(getAddRowButton());
		return this;
	}
	
	private By getFollowOnSessionDropdown() {
		return By.xpath(".//span[normalize-space(text())='Follow-On Session']");
	}
	
	public BookSessionDetailsPage selectFolloupSession(String followOnSession) {
		LOGGER.info("Selecting followup Session");
        click(getFollowOnSessionDropdown());
		click(getExpertiseDropdownOption(followOnSession));
		return this;
		
	}
	
	private By getRemainingCreditsValue() {
		return By.xpath(".//div[contains(@class,'d-flex justify-content-end')]//span");
	}
	
	public int remainingCreditsvalue() {
		LOGGER.info("Get selected credits value");
		String val = getText(getRemainingCreditsValue());
		String credits = val.split(" ")[1];
		shortWait();
		int rCredits = Integer.parseInt(credits);
		return rCredits;
	}
	
	private By getNextButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'private.book_session.book.step1.next')]"));
	}
	
	private By getSelectedAllTimeSlotError() {
		return By.xpath(getXpathByText(".//div[(text())='ui.coach_session.no_time']"));
	}
	
	public BookSessionSummaryPage clickNextButton() {
		LOGGER.info("Clicking Next button");
		click(getNextButton());
		return new BookSessionSummaryPage();
	}
	
	public Integer getRemainingCredits() {
		return Integer.parseInt(getText((By.xpath(getXpathByText(".//span[contains(text(), 'private.coach.remaining_credits') and contains(text(), 'private.profile.enterprise.credits_title')])")))).split(" ")[1]);
	}
	
	public BookSessionDetailsPage enterSessionDetails(int sessionCount, BookSessionDetails bookSeesionDeatils) {
		LOGGER.info("Entering session details");
		for(int count = 0; count < sessionCount; count ++) {
			
			selectLength(count, bookSeesionDeatils.getCredit());
			selectFirstVailableDate(count);
			shortWait();
			selectFirstAvailableTime(count);	
			
			if(count == 0) {
				selectExpertise(count, bookSeesionDeatils.getExpertise());
			} else {
				selectExpertise(count, bookSeesionDeatils.getFollowOnSession());
			}
			
			if(sessionCount > 1 && count == sessionCount-2) {
				clickAddRow();
			} else {
				break;
			}
		}
		scrollByJavaScript("0", "500");
	
		return this;
	}
	
	
	

}
