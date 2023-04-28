package com.thrive.ui.page.book_session;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.api.data_utils.DBQueries;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DBUtils;
import com.thrive.common.utils.DateTimeUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;

public class SessionsPage extends UserManagementCommonPage {

	String filterByTypeSession = ThriveAppSharedData.TYPE.getValue();
	AlertsAndMessagesPage alerts = new AlertsAndMessagesPage();

	private By getSessionHeader() {
		return By.xpath(getXpathByText("//h2[contains(text(),'private.sessions.sessions_title')]"));
	}

	private By getSessionDetails(String expertise, String date, String time) {
		return By.xpath(".//a[text()='" + expertise + "']/../../..//span[contains(text(),'" + date
				+ "')]/../..//following-sibling::td//span[contains(text(),'" + time + "')]");
	}

	private By getFilterByMonthDropdwon() {
		return By.xpath(
				getXpathByText(".//ng-select[@name='months']//div[text()='admin.session.filter_by_month']/..//input"));
	}

	protected By getFilterByMonthDropdownPlaceHolder() {
		return By.xpath(getXpathByText("//div[text()='admin.session.filter_by_month']"));
	}

	private By getSelectElementFromFilterByMonthDropdwon(String month) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(text(),'" + month + "')]");
	}

	private By getFilterByTypeDropdown() {
		return By.xpath(getXpathByText(
				".//ng-select[@name='sessionType']//div[text()='admin.session.filter_by_session_type']/..//input"));
	}

	protected By getFilterByTypeDropdownPlaceHolder() {
		return By.xpath(getXpathByText("//div[text()='admin.session.filter_by_session_type']"));
	}

	private By getSelectElementFromFilterByTypeDropdown(String type) {
		return By.xpath(
				".//div[@class='ng-dropdown-panel-items scroll-host']//span[normalize-space(text())='" + type + "']");
	}

	private By getFilterByEnterpriseDropdown() {
		return By.xpath(getXpathByText(
				".//ng-select[@name='enterprise']//div[text()='admin.session.filter_by_enterprise']/..//input"));
	}

	private By getFilterByEnterprisePlaceHolder() {
		return By.xpath(getXpathByText(".//div[text()='admin.session.filter_by_enterprise']"));
	}

	private By getSelectElementFromFilterByEnterpriseDropdown(String enterprise) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[text()='" + enterprise + "']");
	}

	private By getFilterByClientDropdown() {
		return By.xpath(getXpathByText(
				".//ng-select[@name='clients']//div[text()='admin.session.filter_by_client']/..//input"));
	}

	private By getFilterByClientPlaceHolder() {
		return By.xpath(getXpathByText(".//div[text()='admin.session.filter_by_client']"));
	}

	private By getSelectElementFromFilterByClientDropdown(String client) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[text()='" + client + "']");
	}

	private By getFilterByCoachDropdown() {
		return By.xpath(
				getXpathByText(".//ng-select[@name='coaches']//div[text()='admin.session.filter_by_coach']/..//input"));
	}

	private By getFilterByCoachTypeDropdown() {
		return By.xpath(".//ng-select[@name='coachesType']//input");
	}

	private By getFilterByCoachPlaceHolder() {
		return By.xpath(getXpathByText(".//div[text()='admin.session.filter_by_coach']"));
	}

	private By getSelectElementFromFilterByCoachDropdown(String coach) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[text()='" + coach + "']");
	}

	private By getSelectElementFromFilterByCoachTypeDropdown(String coachType) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[text()='" + coachType + "']");
	}

	private By getFilterByStatusDropdown() {
		return By.xpath(
				getXpathByText(".//ng-select[@name='status']//div[text()='admin.session.filter_by_status']/..//input"));
	}

	private By getFilterByStatusPlaceHolder() {
		return By.xpath(getXpathByText(".//div[text()='admin.session.filter_by_status']"));
	}

	private By getSelectElementFromFilterByStatusDropdown(String status) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[text()='" + status + "']");
	}

	private By getTimeFrameDropdown() {
		return By.xpath(getXpathByText(
				".//ng-select[@name='filter']//div[text()='admin.platform_insights.filter_by_timeframe']/../div[@class='ng-input']"));
	}

	private By getSelectElementFromTimeFrameDropdwon(String timeframe) {
		shortWait();
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[normalize-space(text())='"
				+ timeframe + "']");
	}

	private By getDateRangeDropdown() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//div[@class='pos-rel tp-rangepicker']/input[@placeholder='From - To']");
		} else {
			return By.xpath(".//div[@class='pos-rel tp-rangepicker']/input[@placeholder='De - À']");
		}
	}

	private By getSelectFromDate(String date) {
		return By
				.xpath(".//div[@class='calendar left ng-star-inserted']//td[contains(@class,'available')]/span[text()='"
						+ date + "']/..");
	}

	private By getSelectToDate(String date) {
		return By.xpath(
				".//div[@class='calendar right ng-star-inserted']//td[contains(@class,'available')]/span[text()='"
						+ date + "']/..");
	}

	private By getClearFiltersButton() {
		return By.xpath(getXpathByText(".//span[(text())='private.workshop.reset_filter']"));
	}

	private By getClearFiltersLink() {
		return By.xpath(getXpathByText(".//a[text()='private.workshop.reset_filter']"));
	}

	private By getLeftScroller() {
		return By.xpath(".//span[@class='glyphicon glyphicon-chevron-left']");
	}

	private By getRightScroller() {
		return By.xpath(".//span[@class='glyphicon glyphicon-chevron-right']");
	}

	private By getBookASessionButton() {
		return By.xpath(getXpathByText(".//a[contains(text(),'private.book_session.search.book_a_session')]"));
	}

	private By getMonthsColumnValue(String month) {
		return By.xpath("//td[contains(text(),'" + month + "')]");
	}

	private By getTypeCloumnValue(String type) {
		return By.xpath(getXpathByText(
				"//th[(text())='admin.sessions.view.col.session_type']/../../..//following-sibling::tbody//span[@title='"
						+ type + "']"));
	}

	private By getEnterpriseColumnValue(String enterprise) {
		return By.xpath(
				"//th[normalize-space(text())='Enterprise']/../../..//following-sibling::tbody//a[normalize-space(text())='"
						+ enterprise + "']");
	}

	private By getClientColumnValue(String client) {
		return By.xpath(
				"//th[normalize-space(text())='Client']/../../..//following-sibling::tbody//a[normalize-space(text())='"
						+ client + "']");
	}

	private By getCoachColumnValue(String coach) {
		return By.xpath(
				"//th[normalize-space(text())='Coach']/../../..//following-sibling::tbody//a[normalize-space(text())='"
						+ coach + "']");
	}

	private By getStatusColumnValue(String status) {
		return By.xpath(
				"//th[normalize-space(text())='Status']/../../..//following-sibling::tbody//td[normalize-space(text())='"
						+ status + "']");
	}

	private By getTimeFrameOption(String timeframe) {
		return By.xpath(".//span[text()='" + timeframe + "']");
	}

	private By getWelcomeToSessionWithStartSeesionButton() {
		return By.xpath(getXpathByText(
				".//p[contains(text(),'shared.modals.session_coach_modal.title')]/../../following-sibling::div//button[text()='shared.modals.buttons.start_button']"));
	}

	private By getPleaseSelectActionDropdwon() {
		return By.xpath(getXpathByText(".//div[text()='private.session.select_action_placeholder']/..//input"));
	}

	private By getEndSessionActionDropdownRM() {
		return By.xpath(".//ng-select[@name='sort']/..//input");
	}

	private By getFilterByDateRange() {
		return By.xpath(".//input[@placeholder='From - To']");
	}

	private By getPleaseSelectActionDropdownElement(String option) {
		return By.xpath(getXpathByText("//div[contains(text(),'private.session.end_session_actions.end_session')]"));
	}

	private By getExpertiseLink(String time, String date, String expertise) {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//p[text()='Booked']/..//preceding-sibling::td//span[text()='" + time
					+ "']/../..//preceding-sibling::td//span[text()='" + date
					+ "']/../..//preceding-sibling::td//a[text()='" + expertise + "']");
		} else {
			return By.xpath(".//p[text()='Réservée']/..//preceding-sibling::td//span[text()='" + time
					+ "']/../..//preceding-sibling::td//span[text()='" + date
					+ "']/../..//preceding-sibling::td//a[text()='"
					+ DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertise)) + "']");
		}
	}

	private By getSave() {
		return By.xpath(".//a[@title='Save']");
	}

	private By getCancel() {
		return By.xpath(".//span[@title='Cancel']");
	}

	public SessionsPage selectPleaseSelectActionDropdownElement(String option) {
		LOGGER.info("Selecting please action dropdown element");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			// waitUntilElementIsClickable(240,getPleaseSelectActionDropdwon());
			click(getPleaseSelectActionDropdwon());
		} else {
			click(getEndSessionActionDropdownRM());
		}
		click(getPleaseSelectActionDropdownElement(option));
		return this;
	}

	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//button[text()='private.my_sessions.export']"));
	}

	public SessionsPage clickDownloadButton() {
		click(getDownloadButton());
		return this;
	}

	public SessionsPage clickWelcomeToSessionStartSessionButton() {
		LOGGER.info("Clicking Welcome to session start button");
		click(getWelcomeToSessionWithStartSeesionButton());
		return this;
	}

	public SessionsPage selectFilterByMonth(String month) {
		LOGGER.info("Selecting filter by month");
		click(getFilterByMonthDropdwon());
		click(getSelectElementFromFilterByMonthDropdwon(month));
		return this;
	}

	public SessionsPage selectFilterByType(String type) {
		LOGGER.info("Selecting filter by type");
		click(getFilterByTypeDropdown());
		click(getSelectElementFromFilterByTypeDropdown(type));
		return this;
	}

	public SessionsPage selectFilterByEnterprise(String enterprise) {
		LOGGER.info("Selecting filter by enterprise");
		click(getFilterByEnterpriseDropdown());
		click(getSelectElementFromFilterByEnterpriseDropdown(enterprise));
		return this;
	}

	public SessionsPage selectFilterByClient(String client) {
		LOGGER.info("Selecting filter by client");
		click(getFilterByClientDropdown());
		click(getSelectElementFromFilterByClientDropdown(client));
		return this;
	}

	public SessionsPage selectFilterByCoach(String coach) {
		LOGGER.info("Selecting filter by coach");
		click(getFilterByCoachDropdown());
		click(getSelectElementFromFilterByCoachDropdown(coach));
		return this;
	}

	public SessionsPage selectFilterByCoachType(String coachType) {
		LOGGER.info("Selecting filter by coach type");
		click(getFilterByCoachTypeDropdown());
		click(getSelectElementFromFilterByCoachTypeDropdown(coachType));
		return this;
	}

	public SessionsPage selectFilterByStatus(String status) {
		LOGGER.info("Selecting filter by status");
		click(getFilterByStatusDropdown());
		click(getSelectElementFromFilterByStatusDropdown(status));
		return this;
	}

	public SessionsPage selectFilterByTimeframe(String timeframe) {
		LOGGER.info("Selecting filter by time frame");
		click(getTimeFrameDropdown());
		click(getSelectElementFromTimeFrameDropdwon(timeframe));
		return this;
	}

	public SessionsPage selectTimeFrame(String timeframe) {
		LOGGER.info("Selecting time frame");
		click(getTimeFrameDropdown());
		click(getSelectElementFromTimeFrameDropdwon(timeframe));
		return this;
	}

	public SessionsPage selectDateRange(String fromdate, String todate) {
		click(getDateRangeDropdown());
		shortWait();
		click(getSelectFromDate(fromdate));
		shortWait();
		click(getSelectFromDate(todate));
		return this;
	}

	public SessionsPage selectedDateRangeFilterValue() {
		LOGGER.info("Selecting date range");
		String value = getAttributeByValue("input", getDateRangeDropdown());
		System.out.println("the date range value" + value);
		return this;
	}

	public SessionsPage clickClearFilters() {
		LOGGER.info("Clicking clear filters");
		click(getClearFiltersButton());
		return this;
	}

	public SessionsPage clickClearFiltersLink() {
		LOGGER.info("Clicking clear filters link");
		click(getClearFiltersLink());
		return this;
	}

	public SessionsPage clickLeftScroller() {
		LOGGER.info("Clicking left scroller");
		click(getLeftScroller());
		return this;
	}

	public SessionsPage clickRightScroller() {
		LOGGER.info("Clicking right scroller");
		click(getRightScroller());
		return this;
	}

	public SessionsPage validateSeesionBookedSuccesssfully(String expertise, String date, String time) {
		LOGGER.info("Validating session booked successfully");
		Assert.assertTrue(isElementPresent(getSessionDetails(expertise, date, time)));
		return this;
	}

	public BookSessionViewPage clickBookSession() {
		LOGGER.info("Clicking Book a Session button");
		click(getBookASessionButton());
		return new BookSessionViewPage();
	}

	public AlertsAndMessagesPage clickSaveButton() {
		LOGGER.info("Clicking Save button");
		click(getSave());
		return new AlertsAndMessagesPage();
	}

	public SessionsPage clickCancelButton() {
		LOGGER.info("Clicking Cancle button");
		click(getCancel());
		return this;
	}

	public SessionsPage validateFilterByMonth(String month) {
		LOGGER.info("Validating filter by month works as expected");
		Assert.assertTrue(isElementPresent(getMonthsColumnValue(month)));
		return this;
	}

	public SessionsPage validateFilterByType(String type) {
		LOGGER.info("Validating filter by type works as expected");
		Assert.assertTrue(isElementPresent(getTypeCloumnValue(type)));
		return this;
	}

	public SessionsPage validateFilterByEnterprise(String enterprise) {
		LOGGER.info("Validating filter by enterprise works as expected");
		Assert.assertTrue(isElementPresent(getEnterpriseColumnValue(enterprise)));
		return this;
	}

	public SessionsPage validateFilterByClient(String client) {
		LOGGER.info("Validating filter by client works as expected");
		Assert.assertTrue(isElementPresent(getClientColumnValue(client)));
		return this;
	}

	public SessionsPage validateFilterByCoach(String coach) {
		LOGGER.info("Validating filter by coach works as expected");
		Assert.assertTrue(isElementPresent(getCoachColumnValue(coach)));
		return this;
	}

	public SessionsPage validateFilterByStatus(String status) {
		LOGGER.info("Validating filter by status works as expected");
		Assert.assertTrue(isElementPresent(getStatusColumnValue(status)));
		return this;
	}

	public SessionsPage validateTimeFrame(String timeframe) {
		LOGGER.info("Validating time frame works as expected");
		Assert.assertTrue(isElementPresent(getTimeFrameOption(timeframe)));
		return this;
	}

	public SessionsPage validateClearFilters() {
		LOGGER.info("Validating clear filters works as expected");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getFilterByMonthDropdownPlaceHolder()), "month filter is not cleared");
		softAssert.assertTrue(isElementPresent(getFilterByTypeDropdownPlaceHolder()), "type filter is not cleared");
		softAssert.assertTrue(isElementPresent(getFilterByEnterprisePlaceHolder()), "enterprise filter is not cleared");
		softAssert.assertTrue(isElementPresent(getFilterByStatusPlaceHolder()), "status filter is not cleared");
		softAssert.assertAll();
		return this;
	}

	public SessionsPage validateEntClearFilters() {
		LOGGER.info("Validating ent clear filters works as expected");
		Assert.assertTrue(isElementPresent(getFilterByDateRange()));
		return this;
	}

	public SessionsPage validateSessionElementVisible() {
		LOGGER.info("Validate session element is visible");
		Assert.assertTrue(isElementVisible(getSessionHeader()), "Session is not visible");
		return this;
	}

	public SessionDeatilsPage clickExpertiseLink(String time, String date, String expertise) {
		LOGGER.info("Clicking expertise Link");
		click(getExpertiseLink(time, date, expertise));
		return new SessionDeatilsPage();
	}

	private By getPaginationElement() {
		return By.xpath(".//span[@class='ui-paginator-pages']/a");
	}

	private By getSessionsToUpdate(String expertise, String date, String time) {
		return By.xpath(".//a[contains(text(),'" + expertise
				+ "')]//ancestor::td[1]//following-sibling::td[2]//span[contains(text(),'" + date
				+ "')]//ancestor::td[1]//following-sibling::td[1]//span[contains(text(),'" + time + "')]");
	}

	private By getSpecificSessionExpertiseLink(String expertise, String date, String time) {
		return By.xpath(".//a[contains(text(),'"+expertise+"')]//ancestor::td[1]//following-sibling::td[2]//span[contains(text(),'"+date+"')]//ancestor::td[1]//following-sibling::td[1]//span[contains(text(),'"+time+"')]//ancestor::td//preceding-sibling::td[3]//a[contains(text(),'"+expertise+"')]");
	}
	
	private By getSessionStatusContainer() {
		return By.xpath("//div[@id='tp-select-container']//div[contains(@class,'select-container')]");
	}
	
	private By getSessionStatus(String sessionStatus) {
		return By.xpath("//div[@title='"+sessionStatus+"']");
	}
	
	private By getBookedSessionPresent(String expertise, String date, String time) {
		return By.xpath(".//a[contains(text(),'" + expertise
				+ "')]//ancestor::td[1]//preceding-sibling::td[3]//span[contains(text(),'" + date
				+ "')]/..//following-sibling::p//span[contains(text(),'" + time + "')]");
	}

	private By getWorkshopDateLink(String eventName) {
		return By.xpath("//a[contains(text(),'" + eventName
				+ "')]//parent::td//following-sibling::td[@class='session-date']//div[contains(@class,'star-inserted')]//descendant::input");
	}

	private By getSessionLink(String expertise, String date, String time) {
		return By.xpath("//a[contains(text(),'" + expertise + "')]/..//following-sibling::td[2]//span[contains(text(),'"
				+ date + "')]/..//following-sibling::td//span[1][contains(text(),'" + time + "')]");
	}

	private By getEditSessionScheduleLink(String expertise, String date, String time) {
		return By.xpath("//a[contains(text(),'" + expertise + "')]/..//following-sibling::td[2]//span[contains(text(),'"
				+ date + "')]/..//following-sibling::td//span[1][contains(text(),'" + time
				+ "')]/..//preceding-sibling::td[5]//a");
	}

	private By getPreviousMonthArrowLink() {
		return By.xpath("//button[@aria-label='Previous month']//span[ @class='ngb-dp-navigation-chevron']");
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}

	private By getCalenderHeaderLink() {
		return By.xpath("//div[@class='ngb-dp-header']//ngb-datepicker-navigation");
	}

	private By getCurrentDateLink(String currentDate) {
		return By.xpath("//div[contains(text(),'" + currentDate + "')]//parent::div[contains(@class,'today')]");
	}

	private By getWorkshopStartTimeLink(String eventName) {
		return By.xpath("//a[contains(text(),'" + eventName
				+ "')]//parent::td//following-sibling::td//p-calendar[1]//input[@type='text']");
	}

	private By getWorkshopEndTimeLink(String eventName) {
		return By.xpath("//a[contains(text(),'" + eventName
				+ "')]//parent::td//following-sibling::td//p-calendar[2]//input[@type='text']");
	}

	private By getNextPageButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-caret-right']");
	}

	private By getLastPageButtonLink() {
		return By.xpath("//span[@class='ui-paginator-icon pi pi-step-forward']//parent::a");
	}

	private By getWorkshopSaveButtonLink(String eventName) {
		return By.xpath("//a[contains(text(),'" + eventName
				+ "')]//parent::td//preceding-sibling::td//descendant::a[@title='Save']");
	}

	private By getPaginationLink(int pageNumber) {
		return By.xpath(".//a[contains(@class,'ui-paginator-') and text()='" + pageNumber + "']");
	}

	private By getIncompleteSessionFeedbackWarningLink() {
		return By.xpath(".//div[@class='warning ls-07 ng-star-inserted']//span[text()='click here']");
	}

	private By getIncompleteSessionFeedbackWarningLinkRm() {
		return By.xpath(".//div[@class='warning ls-07 ng-star-inserted']//span[text()='Click here']");
	}

	private By getIncompleteSessionIcon() {
		return By
				.xpath(".//tr[@class='row-card bg-white border-left ng-star-inserted']//img[@class='icon-warning']/..");
	}	
	
	private By getSessionUpdateDates() {
		return By.xpath("//div[@role='gridcell']//div[not(contains(@class,'muted'))]");
	}
	
	public SessionFeedbackPage clickIncompleteSessionIcon() {
		LOGGER.info("click on the incomplete session icon");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			click(getIncompleteSessionFeedbackWarningLink());
		} else {
			click(getIncompleteSessionFeedbackWarningLinkRm());
		}

		click(getIncompleteSessionIcon());
		return new SessionFeedbackPage();
	}

	public String setRequiredSessionDateTime(String expertise, String date, String time) {
		LOGGER.info("Updating session date and time");
		shortWait();
		String curentDate = "";
		String expectedEndtime = "";
		exitLoop: while (true) {
			if (isElementPresent(getSessionLink(expertise, date, time))) {
				click(getEditSessionScheduleLink(expertise, date, time));
				click(getWorkshopDateLink(expertise));
				shortWait();
				Calendar calender = Calendar.getInstance();
				System.out.println(calender.getTime().toString());
				curentDate = "";
				if ((calender.getTime().toString().substring(8, 10)).substring(0, 1).equals("0")) {
					curentDate = calender.getTime().toString().substring(9, 10);
				} else {
					curentDate = calender.getTime().toString().substring(8, 10);
				}
				String currentMonth = calender.getTime().toString().substring(4, 7);
				int i = 1;
				while (i <= 12) {
					String sessionMonthYear = getText(getCalenderHeaderLink());
					String sessionMonth = sessionMonthYear.substring(0, 3);
					if (currentMonth.contains(sessionMonth)) {
						click(getCurrentDateLink(curentDate));
						break;
					} else {
						click(getPreviousMonthArrowLink());
					}
				}
				calender.add(Calendar.MINUTE, -4);
				int a = calender.get(Calendar.MINUTE);
				int b = calender.get(Calendar.HOUR_OF_DAY);
				String subTime = String.valueOf(b) + ":" + String.valueOf(a);
				System.out.println("Subtracted time : " + subTime);
				calender.add(Calendar.MINUTE, +15);
				int aa = calender.get(Calendar.MINUTE);
				int bb = calender.get(Calendar.HOUR_OF_DAY);
				String addTime = String.valueOf(bb) + ":" + String.valueOf(aa);
				String expectedStartTime = subTime;
				expectedEndtime = addTime;
				shortWait();
				setValue(expectedStartTime, getWorkshopStartTimeLink(expertise));
				shortWait();
				setValue(expectedEndtime, getWorkshopEndTimeLink(expertise));
				mediumWait();
				click(getWorkshopSaveButtonLink(expertise));
				break exitLoop;

			} else if ((findElement(getLastPageButtonLink()).getAttribute("class").contains("disabled"))) {

				break exitLoop;
			}
			click(getNextPageButtonLink());
			shortWait();
		}
		return curentDate;
	}

	public String setRequiredSessionDateToPast(String expertise, String date, String time) {
		LOGGER.info("Updating session date and time");
		shortWait();
		String curentDate = "";
		exitLoop: while (true) {
			if (isElementPresent(getSessionLink(expertise, date, time))) {
				click(getEditSessionScheduleLink(expertise, date, time));
				click(getWorkshopDateLink(expertise));
				shortWait();
				Calendar calender = Calendar.getInstance();
				System.out.println(calender.getTime().toString());
				curentDate = "";
				if ((calender.getTime().toString().substring(8, 10)).substring(0, 1).equals("0")) {
					curentDate = calender.getTime().toString().substring(9, 10);
				} else {
					curentDate = calender.getTime().toString().substring(8, 10);
				}
				String currentMonth = calender.getTime().toString().substring(4, 7);
				int i = 1;
				while (i <= 12) {
					String sessionMonthYear = getText(getCalenderHeaderLink());
					String sessionMonth = sessionMonthYear.substring(0, 3);
					if (currentMonth.contains(sessionMonth)) {
						click(getCurrentDateLink(curentDate));
						break;
					} else {
						click(getPreviousMonthArrowLink());
					}
				}
				
				for(int j=1;j<=12;j++) {
					click(getWorkshopDateLink(expertise));
					click(getPreviousMonthArrowLink());
					click(getSessionUpdateDates());
					click(getWorkshopSaveButtonLink(expertise));
					String actualMessage = getText(getAlertElement());
					if(actualMessage.contains(ThriveAppSharedData.PREMEETING_UPDATED_SUCCESSFULLY.getValue())) {
						new AlertsAndMessagesPage().closeToasterAlert();
						break exitLoop;
					} else {
						new AlertsAndMessagesPage().closeToasterAlert();
						click(getWorkshopDateLink(expertise));
						click(getPreviousMonthArrowLink());
						List<WebElement> datesAvailableForUpdate = findElements(getSessionUpdateDates());
						for(WebElement dateForUpdate : datesAvailableForUpdate) {
							dateForUpdate.click();
							click(getWorkshopSaveButtonLink(expertise));
							String actualAlertMessage = getText(getAlertElement());
							if(actualAlertMessage.contains(ThriveAppSharedData.PREMEETING_UPDATED_SUCCESSFULLY.getValue())) {
								new AlertsAndMessagesPage().closeToasterAlert();
								break exitLoop;
							} else {
								continue;
							}
						}
					}
				}
				

			} else if ((findElement(getLastPageButtonLink()).getAttribute("class").contains("disabled"))) {

				break exitLoop;
			}
			click(getNextPageButtonLink());
			shortWait();
		}
		return curentDate;
	}
	
	public String updateSessionSchedule(String time, String date, String expertise) {
		LOGGER.info("Updating session schedule");
		String expectedExpertise = "";
		if (Config.getLocalizationLanguage().contains("en")) {
			expectedExpertise = expertise;
		} else {
			expectedExpertise = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertise));
		}
		if (Config.getTestPlatform().contains(TestPlatform.THRIVE)) {
			selectFilterByType(filterByTypeSession);
		}
		
		if(expertise.contains("Int")) {
			selectFilterByCoach(internalCoachName);
		} else {
			selectFilterByCoach(globalCoachName);
		}

		String updatedDate = "";
		int pageCount = 1;
		try {
			if (isElementPresent(getPaginationElement())) {
				pageCount = findElements(getPaginationElement()).size();
			}
		} catch (NoSuchElementException e) {
			// TODOD Logger
		}

		for (int i = 1; i <= pageCount; i++) {
			if (isElementPresent(getSessionsToUpdate(expectedExpertise, date, time))) {
				updatedDate = setRequiredSessionDateTime(expectedExpertise, date, time);
				break;

			} else {
				click(getPaginationLink(i + 1));
			}
		}
		new AlertsAndMessagesPage().closeToasterAlert();
		return updatedDate;
	}
	
	public String updateSessionScheduleToPast(String time, String date, String expertise) {
		LOGGER.info("Updating session schedule");
		String expectedExpertise = "";
		if (Config.getLocalizationLanguage().contains("en")) {
			expectedExpertise = expertise;
		} else {
			expectedExpertise = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertise));
		}
		if (Config.getTestPlatform().contains(TestPlatform.THRIVE)) {
			selectFilterByType(filterByTypeSession);
		}
		
		if(expertise.contains("Int")) {
			selectFilterByCoach(internalCoachName);
		} else {
			selectFilterByCoach(globalCoachName);
		}

		String updatedDate = "";
		int pageCount = 1;
		try {
			if (isElementPresent(getPaginationElement())) {
				pageCount = findElements(getPaginationElement()).size();
			}
		} catch (NoSuchElementException e) {
			// TODOD Logger
		}

		for (int i = 1; i <= pageCount; i++) {
			if (isElementPresent(getSessionsToUpdate(expectedExpertise, date, time))) {
				updatedDate = setRequiredSessionDateToPast(expectedExpertise, date, time);
				break;

			} else {
				click(getPaginationLink(i + 1));
			}
		}
		return updatedDate;
	}

	public SessionsPage validateSessionPresent(String time, String date, String expertise) throws ParseException {

		LOGGER.info("Valdiate booked session present");
		int pageCount = 1;
		try {
			if (isElementPresent(getPaginationElement())) {
				pageCount = findElements(getPaginationElement()).size();
			}
		} catch (NoSuchElementException e) {
			// TODOD Logger
		}

		for (int i = 1; i <= pageCount; i++) {
			if (Config.featureFlagStatus()) {
				String formattedDate = DateTimeUtils.getParsedDate("MMM-dd", "dd-MM-yyyy", date);
				String[] mmDD = formattedDate.split("-");
				String requiredDateFormate_DD_MMM = mmDD[0] + " " + mmDD[1];
				String requiredDateFormate_MMM_DD = mmDD[1] + " " + mmDD[0];
				if (isElementPresent(getBookedSessionPresent(expertise, requiredDateFormate_DD_MMM, time))) {
					Assert.assertTrue(
							isElementPresent(getBookedSessionPresent(expertise, requiredDateFormate_DD_MMM, time)));
					break;
				} else if (isElementPresent(getBookedSessionPresent(expertise, requiredDateFormate_MMM_DD, time))) {
					Assert.assertTrue(
							isElementPresent(getBookedSessionPresent(expertise, requiredDateFormate_MMM_DD, time)));
					break;
				} else {
				}
				click(getPaginationLink(i + 1));

			} else {
				if (isElementPresent(getBookedSessionPresent(expertise, date, time))) {
					Assert.assertTrue(isElementPresent(getBookedSessionPresent(expertise, date, time)));
				} else {
					click(getPaginationLink(i + 1));
				}

			}
		}

		return this;
	}

	private By getJoinSessionButton(String expertise, String date) {

		if (Config.featureFlagStatus()) {
			if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(getXpathByText(".//a[contains(text(),'"+expertise+"')]//ancestor::td[1]//preceding-sibling::td[3]//span[contains(text(),'"+date+"')]//ancestor::td//following-sibling::td[1]//button[contains(text(),'private.my_sessions.join')]"));
			} else {
				return By.xpath(".//a[contains(text(),'" + expertise
						+ "')]//ancestor::td[1]//preceding-sibling::td[3]//span[contains(text(),'" + date
						+ "')]//ancestor::td//following-sibling::td[1]//button[contains(text(),'JOINDRE')]");
			}
		} else {
			if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(getXpathByText("//a[contains(text(),'" + expertise
						+ "')]//ancestor::td//following-sibling::td[2]//span[contains(text(),'" + date
						+ "')]//ancestor::td//preceding-sibling::td[4]//p//button[contains(text(),'private.my_sessions.join')]"));
			} else {
				return By.xpath("//a[contains(text(),'" + expertise
						+ "')]//ancestor::td//following-sibling::td[2]//span[contains(text(),'" + date
						+ "')]//ancestor::td//preceding-sibling::td[4]//p//button[contains(text(),'JOINDRE')]");
			}
		}
	}

	public SessionsPage joinSessionMeeting(String expertise, String date) {
		LOGGER.info("Joining pre-meeting");
		// shortWait();
		exitLoop: while (true) {
			if (isElementPresent(getJoinSessionButton(expertise, date))) {
				click(getJoinSessionButton(expertise, date));
				break exitLoop;
			} else if ((findElement(getLastPageButtonLink()).getAttribute("class").contains("disabled"))) {
				break exitLoop;
			}
			click(getNextPageButtonLink());
			shortWait();
		}
		return this;
	}

	public SessionsPage joinSessionWithEALogin(String expertise, String date, String time) {
		LOGGER.info("Joining session with EA login");
		String expectedExpertise = "";
		if (Config.getLocalizationLanguage().contains("en")) {
			expectedExpertise = expertise;
		} else {
			expectedExpertise = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertise));
		}
		joinSessionMeeting(expectedExpertise, date).click(getWelcomeToSessionWithStartSeesionButton());
		return this;
	}

	public SessionsPage joinSessionWithCoachLogin(String expertise, String date, String time) {
		LOGGER.info("Joining session with coach login");
		String expectedExpertise = "";
		if (Config.getLocalizationLanguage().contains("en")) {
			expectedExpertise = expertise;
		} else {
			expectedExpertise = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertise));
		}
		joinSessionMeeting(expectedExpertise, date).click(getWelcomeToSessionWithStartSeesionButton());
		try {
			new AlertsAndMessagesPage().closeToasterAlert();
		} catch (Exception e) {

		}
		return this;
	}

	private By getCompletedSessionStatusForCoach(String expertise, String date) {
		if(Config.featureFlagStatus()) {
			if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(getXpathByText(".//a[text()='" + expertise+ "']//ancestor::td[1]//following-sibling::td[2]//span[contains(text(),'" + date+ "')]//ancestor::td[1]//following-sibling::td[4]//p[(text())='private.detail.session_status.completed']"));
			} else {
				return By.xpath(".//a[text()='" + expertise+ "']//ancestor::td[1]//following-sibling::td[2]//span[contains(text(),'" + date+ "')]//ancestor::td[1]//following-sibling::td[4]//p[(text())='Terminée En Ligne']");
			}
		} else {
			if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(getXpathByText(".//a[text()='"+expertise+"']//ancestor::td[1]//following-sibling::td[1]//span[contains(text(),'"+date+"')]//ancestor::td[1]//following-sibling::td[5]//p[(text())='private.detail.session_status.completed']"));
			} else {
				return By.xpath(".//a[text()='" + expertise+ "']//ancestor::td[1]//following-sibling::td[2]//span[contains(text(),'" + date+ "')]//ancestor::td[1]//following-sibling::td[4]//p[(text())='Terminée En Ligne']");
			}
		}
	}

	private By getRebookSessionButton(String expertise, String date) {
		return By.xpath(
				".//a[text()='" + expertise + "']//ancestor::td//preceding-sibling::td[3]//span[contains(text(),'"
						+ date + "')]//ancestor::td//following-sibling::td//button[contains(text(),'Rebook')]");
	}

	public BookSessionDetailsPage clickRebookButton(String expertise, String date) {
		LOGGER.info("click on the rebook button of the completed window");
		shortWait();
		new ThriveHeaderMenuPage().clickMySessions();

		int pageCount = 1;
		try {
			if (isElementPresent(getPaginationElement())) {
				pageCount = findElements(getPaginationElement()).size();
			}
		} catch (NoSuchElementException e) {
			// TODOD Logger
		}

		for (int i = 1; i <= pageCount; i++) {
			if (isElementVisible(getRebookSessionButton(expertise, date))) {
				click(getRebookSessionButton(expertise, date));
				break;
			} else {
				click(getPaginationLink(i + 1));

			}
		}

		return new BookSessionDetailsPage();
	}

	public SessionsPage validateSessionCompletedSuccessfullyByCoach(String expertise, String date) {
		LOGGER.info("Validating session has been completed successfully by coach");
		shortWait();
		String expectedExpertise = "";
		if (Config.getLocalizationLanguage().contains("en")) {
			expectedExpertise = expertise;
		} else {
			expectedExpertise = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertise));
		}
		new ThriveHeaderMenuPage().clickMySessions();
		int pageCount = 1;
		try {
			if (isElementPresent(getPaginationElement())) {
				pageCount = findElements(getPaginationElement()).size();
			}
		} catch (NoSuchElementException e) {
			// TODOD Logger
		}

		for (int i = 1; i <= pageCount; i++) {
			if (isElementVisible(getCompletedSessionStatusForCoach(expectedExpertise, date))) {
				Assert.assertTrue(true);
				break;
			} else {
				click(getPaginationLink(i + 1));

			}
		}
		return this;
	}

	private By getVideoContainerLink() {
		return By.xpath("//div[@id='video-container']");
	}

	public SessionsPage validateSessionJoined() {
		LOGGER.info("Validate session joined successfully");
		Assert.assertTrue(isElementVisible(getVideoContainerLink()), "Failed to join session");
		return this;
	}

	private By getCompletedSessionStatusForClient(String expertise, String date) {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//a[text()='" + expertise
					+ "']//ancestor::td[1]//following-sibling::td[2]//span[contains(text(),'" + date
					+ "')]//ancestor::td[1]//following-sibling::td[3]//p[(text())='Completed']");
		} else {
			return By.xpath(".//a[text()='" + expertise
					+ "']//ancestor::td[1]//following-sibling::td[2]//span[contains(text(),'" + date
					+ "')]//ancestor::td[1]//following-sibling::td[3]//p[(text())='Terminée En Ligne']");
		}
	}

	public SessionsPage validateSessionCompletedSuccessfullyByClient(String expertise, String date) {
		LOGGER.info("Validating session has been completed successfully by client");
		String expectedExpaertise = "";
		if (Config.getLocalizationLanguage().contains("en")) {
			expectedExpaertise = expertise;
		} else {
			expectedExpaertise = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertise));
		}
		int pageCount = 1;
		try {
			if (isElementPresent(getPaginationElement())) {
				pageCount = findElements(getPaginationElement()).size();
			}
		} catch (NoSuchElementException e) {
			// TODOD Logger
		}

		for (int i = 1; i <= pageCount; i++) {
			if (isElementVisible(getCompletedSessionStatusForClient(expectedExpaertise, date))) {
				Assert.assertTrue(true);
				break;
			} else {
				click(getPaginationLink(i + 1));

			}
		}
		return this;
	}

	private By getStartSessionElemnt() {
		return By.xpath("//button[contains(text(),'Start session')]");
	}

	public SessionsPage clickStartSessionButton() {
		LOGGER.info("Clicking On StartSessionButton");
		click(getStartSessionElemnt());
		return new SessionsPage();
	}

	private By getSessionTipsElement() {
		return By.xpath("//button[contains(text(),' Session tips ')]");
	}

	public SessionsPage clickSessionTips() {
		LOGGER.info("Clicking On SessionTipsbutton");
		click(getSessionTipsElement());
		return this;
	}

	private By getClickHerePromptLink() {
		return By.xpath("//div[@class='ng-star-inserted']//a[contains(text(),'Click here to prompt them.')]");
	}

	private By getFAQLink() {
		return By.xpath("//a[contains(text(),'FAQs')]");
	}

	private By getCannotconnectPleaseEndSessionLink() {
		return By.xpath("//div[@class='ng-star-inserted']//a[contains(text(),'Click here to prompt them.')]");
	}

	public SessionsPage ValidatePromptFAQCantConnectLinks() {
		LOGGER.info("Validating Promptlink,FAQLink,Can'tConnectLink availability");
		Assert.assertTrue(isElementVisible(getClickHerePromptLink()), "Prompt Link not available");
		Assert.assertTrue(isElementVisible(getFAQLink()), "FAQ Link  not available");
		Assert.assertTrue(isElementVisible(getCannotconnectPleaseEndSessionLink()), "Can'tConnect Link Not available");
		return this;
	}

	public String updateSessionStatusWithSALogin(String time, String date, String expertise,String sessionStatus) {
		LOGGER.info("Updating session schedule");
		String expectedExpertise = "";
		if (Config.getLocalizationLanguage().contains("en")) {
			expectedExpertise = expertise;
		} else {
			expectedExpertise = DBUtils.getResultFromPostgresDB(DBQueries.getResultInFrench(expertise));
		}
		if (Config.getTestPlatform().contains(TestPlatform.THRIVE)) {
			selectFilterByType(filterByTypeSession);
		}
		
		if(expertise.contains("Int")) {
			selectFilterByCoach(internalCoachName);
		} else {
			selectFilterByCoach(globalCoachName);
		}

		String updatedDate = "";
		int pageCount = 1;
		try {
			if (isElementPresent(getPaginationElement())) {
				pageCount = findElements(getPaginationElement()).size();
			}
		} catch (NoSuchElementException e) {
			// TODOD Logger
		}

		for (int i = 1; i <= pageCount; i++) {
			if (isElementPresent(getSessionsToUpdate(expectedExpertise, date, time))) {
				click(getSpecificSessionExpertiseLink(expectedExpertise, date, time));
				waitUntilElementIsClickable(5, getSessionStatusContainer());
				click(getSessionStatusContainer());
				click(getSessionStatus(sessionStatus));
				alerts.validateToaster(ThriveAppSharedData.SESSION_STATUS_UPDATE_TOASTER.getValue());
				break;

			} else {
				click(getPaginationLink(i + 1));
			}
		}
		
		return updatedDate;
	}
	
	
}
