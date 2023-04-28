package com.thrive.ui.page.book_session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;

import freemarker.log.Logger;

public class BookSessionViewPage extends BaseTestPage {

	private By getThisWeekButton() {
		return By.xpath("//button[normalize-space(text())='This week']");
	}

	private By getFavourateCheckBox() {
		return By.xpath(getXpathByText(".//span[contains(text(),'private.book_session.search.favourites')]/.."));
	}
	
	private By getCoachTypeCheckBox(String coachtype) {
		return By.xpath(".//span[contains(text(),'"+coachtype+"')]/..");
	}

	private By getCoachesHeader() {
		return By.xpath(".//span[text()='Coaches']");
	}

	private By getDayButton(String day) {
		return By.xpath("//button[normalize-space(text())='" + day + "']");
	}

	private By getAddFavourateCoach() {
		return By.xpath(
				".//span[@class='fnt-size col-white cursor d-flex align-items-center justify-content-center h-100']/..//child::i");
	}

	private By getCoachesButton() {
		return By.xpath("//button[normalize-space(text())='Coaches']");
	}

	private By getSelectCategoryOption(String option) {
		return By.xpath("//label[normalize-space(text())='" + option + "']");
	}

	private By getlanguagesButton() {
		return By.xpath("//button[contains(text(),'LANGUAGE')]");
	}

	private By getSelectlanguageOption(String lang) {
		return By.xpath("//small[contains(text(),'" + lang + "')]");
	}

	private By getBookASessionButton(String coachName) {
			return By.xpath("//span[contains(text(),'" + coachName+"')]/../..//following-sibling::div[contains(@class,'profile-info-bx')]//a");
	}

	private By getSelectClientDropdwon() {
		return By.xpath(".//ng-select[@name='clients']");
	}

	private By getSelectElementFromSessionClientDropdown(String client) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='" + client + "']");
	}

	private By getSessionlengthDropdown() {
		return By.xpath("//ng-select[@id='creditType+0']");
	}

	private By getSelectValueSessionlengthDropdown(String credits) {
		return By
				.xpath("//div[@class='ng-dropdown-panel-items scroll-host']//span[contains(text(),'" + credits + "')]");
	}

	private By getSessionDateInput() {
		return By.xpath(".//input[@id='session_date']");
	}

	private By getSessionDatePreviousMonth() {
		return By.xpath(".//button[@title='Previous month']");
	}

	private By getSessionDateElement(String date) {
		return By.xpath(
				".//input[@id='session_date']/../..//div[contains(@class,'btn-light') and text()='" + date + "']");
	}

	private By getSessionDateNextElement() {
		return By.xpath(".//button[@title='Next month']");
	}

	private By getSelectTimeDropdown() {
		return By.xpath("//ng-select[@id='session_tim+0']");
	}

	private By getSelectValueForTimeDropdown(String time) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[contains(text(),'" + time + "')]");
	}

	private By getSelectExpertiseDropdown() {
		return By.xpath("//ng-select[@id='expertise+0']");
	}

	private By getSelectValueForExpertiseDropdown(String expertise) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//span[text()='" + expertise + "']");
	}

	private By getNextButton() {
		return By.xpath(".//button[normalize-space(text())='Next']");
	}

	private By getCoachingSessionHelpWithTextFields() {
		return By.xpath(
				".//label[normalize-space(text())='What specifically do you want this coaching session to help with?']/../textarea");
	}

	private By getWhatBackgroundReleventForConversationTextFields() {
		return By.xpath(
				"//label[normalize-space(text())='What background or context is relevant for this conversation?']/../textarea");
	}

	private By getAnythingHelpfulToShareWithCoachTextFields() {
		return By.xpath(
				".//label[normalize-space(text())='Is there anything else that would be helpful to share with your coach?']/../textarea");
	}

	private By getSuccessfulInMakingChangesSinceLastSessionRadio(String choice) {
		return By.xpath(
				".//label[normalize-space(text())='Were you successful in making changes since your last session?']/..//div/label[normalize-space(text())='"
						+ choice + "']");
	}

	private By getRecommendedMythriveToSomeoneRadio(String option) {
		return By.xpath(
				".//label[normalize-space(text())='Did you recommend MyThrive to someone else?']/..//div/label[normalize-space(text())='"
						+ option + "']");
	}

	private By getFinishBookingButton() {
		return By.xpath("//button[contains(@class,'h-maxw-991')  and  normalize-space(text())='FINISH BOOKING']");
	}

	private By getPreviousButton() {
		return By.xpath("//button[text()='Previous']");
	}

	private By getOkButton() {
		return By.xpath("//a[text()='OK']");
	}

	private By getAddRowButton() {
		return By.xpath(".//button[normalize-space(text())='Add Row']");
	}

	private By getBookedButton() {
		return By.xpath(".//div[text()='Booked']");
	}

	private By getCoachSearchInput() {
		return By.xpath(
				".//button[normalize-space(text())='COACH SEARCH']/../../..//following-sibling::div//input[@placeholder='KEYWORD']");
	}

	private By getCoachNameLink(String coach) {
		return By.xpath(".//span[contains(text(),'" + coach + "')]");
	}

	private By getClearFiltersButton() {
		//		if (Config.getLocalizationLanguage().contains("en")) {
		//			return By.xpath(getXpathByText("//span[contains(text(),'private.workshop.reset_filter')]"));
		//		} else {
		//			return By.xpath(getXpathByText(
		//					"//span[contains(text(),'admin.private_sessions.expand_all')]/../..//preceding-sibling::div[contains(@class,'inline')]//i//following-sibling::span"));
		//		}
		return By.xpath("//span[contains(@class,'reset-filtertxt c-pointer')]");
	}

	private By getCoachType() {
		return By.xpath(".//button[normalize-space(text())='COACH TYPE']/../../../../..");
	}

	private By getRegion() {
		return By.xpath(".//button[normalize-space(text())='REGION']/../../../../..");
	}

	private By getRegionCheckbox(String region) {
		return By.xpath(".//span[contains(text(),'"+region+"')]");
	}

	private By getExpandExpertise() {
		return By.xpath(".//button[normalize-space(text())='EXPERTISE']/../../../../..");
	}

	private By getExpertiseDropdown() {
		return By.xpath(".//ng-select[@name='expertise']//input");
	}

	private By getExpertiseDropdownElement(String expertise) {
		return By.xpath(".//small[text()='" + expertise + "']/..");
	}

	private By getExpertisePresent(String expertise) {
		return By.xpath(".//span[text()='" + expertise + "']");
	}

	private By getExpandIndustry() {
		return By.xpath(".//button[normalize-space(text())='INDUSTRY']/../../../../..");
	}

	private By getIndustryDropdown() {
		return By.xpath(".//div[text()='Choose or search an industry']/..//input");
	}
	
	private By getTopicDropdown() {
		return By.xpath(".//ng-select[@name='expertise']//input");
	}
	
	private By getInternalTopicsDropdown() {
		return By.xpath(".//ng-select[@name='internal_expertise']//input");
	}

	private By getIndustryDropdownElement(String industry) {
		return By.xpath(".//small[text()='" + industry + "']/..");
	}

	private By getIndustryPresent(String industry) {
		return By.xpath(".//span[text()='" + industry + "']");
	}
	
	private By getExternalCoachLink() {
		return By.xpath(".//p[normalize-space(text())='My Thrive']");
	}

	private By getInternalCachLink() {
		return By.xpath(".//p[normalize-space(text())='INTERNAL']");
	}

	private By getRegionPresent(String region) {
		return By.xpath(".//span[normalize-space(text())='" + region + "']");
	}

	private By getFavourateCoachHeader() {
		return By.xpath(".//span[text()='Auto-Int-Coach-Immutable-First- A.']");
	}

	private By getProfilepageCoachHeader() {
		return By.xpath(".//span[text()='Auto-Int-Coach-Immutable-First- Auto-Int-Coach-Immutable-Last']");
	}

	private By getShowAllResultsLink() {
		return By.xpath(".//a[@title='Show All Results']");
	}
	
	private By getLangaugesDropdown() {
		return By.xpath(
				"//button[contains(text(),'LANGUAGE')]//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']//ng-select");
	}
	
	private By getCoachesRegionLink() {
		return By.xpath("//h3//following-sibling::div[1]");
	}
	
	
	public BookSessionViewPage validateSelectedRegionCoaches(String region) {
		LOGGER.info("Validate only selected region related coaches list is displayed");
		List<WebElement> coachesRegions = findElements(getCoachesRegionLink());
		for (WebElement coachRegion : coachesRegions) {
			if (!(getText(coachRegion).contains(region))) {
				Assert.assertTrue(false, "Coach in different region found");
			}
		}
		Assert.assertTrue(true);
		return this;
	}
	
	public CoachBioPage clickFirstCoachName() {
		LOGGER.info("Clicking first name in Coaches section");
		waitUntilElementIsClickable(10, getCoachesNameLink());
		WebElement coachName = findElement(getCoachesNameLink());
		click(coachName);
		return new CoachBioPage();
	}
	
	private By getLanguageCloseallOption() {
		return By.xpath(".//span[text()='×']/..");
	}
	
	public BookSessionViewPage clickLanguageDropdown() {
		LOGGER.info("Clixking languages button");
		clickByJavaScript(getlanguagesButton());
		click(getLanguageCloseallOption());
		click(getLangaugesDropdown());
		return this;
	}

	public BookSessionViewPage clickCoachHeader() {
		LOGGER.info("Clicking Coach ");
		click(getFavourateCoachHeader());
		String parentTab = getParentWindowHandle();
		switchToChildWindow(parentTab);
		return this;
	}

	public BookSessionViewPage clickFavourateCheckBox() {
		LOGGER.info("Clicking favorite Checkbox");
		shortWait();
		click(getFavourateCheckBox());
		return this;
	}
	
	public BookSessionViewPage clickCoachTypeCheckbox(String coachType) {
		LOGGER.info("Clicking external Checkbox");
		shortWait();
		click(getCoachTypeCheckBox(coachType));
		return this;
	}

	public BookSessionViewPage clickAddFavourateCoach() {
		LOGGER.info("Clicking add favorite coach");
		click(getAddFavourateCoach());
		return this;
	}

	public BookSessionViewPage setCoach(String coach) {
		LOGGER.info("Set coach name");
		setSearchCoach(coach);
		return this;
	}

	public ThriveHeaderMenuPage clickBooked() {
		LOGGER.info("Clicking Booked button");
		click(getBookedButton());
		return new ThriveHeaderMenuPage();
	}

	public BookSessionViewPage selectCoachType(String coachType) {
		LOGGER.info("Selscting coach type");
		shortWait();
		click(getCoachType());
		click(getCoachTypeCheckbox(coachType));
		return this;
	}

	public BookSessionViewPage selectRegion(String region) {
		LOGGER.info("Selecting Region");
		click(getRegion());
		click(getRegionCheckbox(region));
		return this;
	}

	public BookSessionViewPage selectExpertise(String expertise) {
		LOGGER.info("Selecting Expertise");
		click(getExpandExpertise());
		click(getExpertiseDropdown());
		click(getExpertiseDropdownElement(expertise));
		return this;
	}

	public BookSessionViewPage selectIndustry(String industry) {
		LOGGER.info("Selecting Industry");
		click(getExpandIndustry());
		click(getIndustryDropdown());
		click(getIndustryDropdownElement(industry));
		return this;
	}
	
	public BookSessionViewPage selectTopicExpertise(String expertise) {
		LOGGER.info("Selecting Expertise");
		click(getTopicDropdown());
		click(getExpertiseDropdownElement(expertise));
		return this;
	}
	
	public BookSessionViewPage selectInternalTopicExpertise(String expertise) {
		LOGGER.info("Selecting Internal Expertise");
		click(getInternalTopicsDropdown());
		click(getExpertiseDropdownElement(expertise));
		return this;
	}

	public BookSessionViewPage selectClientFromDropdwon(String client) {
		LOGGER.info("Selecting client from dropdown");
		click(getSelectClientDropdwon());
		click(getSelectElementFromSessionClientDropdown(client));
		return this;
	}

	public BookSessionViewPage clickThisWeekButton() {
		LOGGER.info("Clicking this week button");
		click(getThisWeekButton());
		return this;
	}

	public BookSessionViewPage clickDayButton(String day) {
		LOGGER.info("Clicking day button");
		click(getDayButton(day));
		return this;
	}

	public BookSessionViewPage clickCoachesButton() {
		LOGGER.info("Clicking coaches button");
		click(getCoachesButton());
		return this;
	}

	public BookSessionViewPage selectCategoryCheckbox(String option) {
		LOGGER.info("Selecting category checkbox");
		click(getSelectCategoryOption(option));
		return this;
	}

	public BookSessionViewPage clickLanguageButton() {
		LOGGER.info("Clixking languages button");
		click(getlanguagesButton());
		return this;
	}

	public BookSessionViewPage selectlanguageCheckbox(String lang) {
		LOGGER.info("Selecting language checkbox");
		click(getSelectlanguageOption(lang));
		return this;
	}

	public BookSessionViewPage clickShowAllLink() {
		click(getShowAllResultsLink());
		return this;
	}

	public BookSessionViewPage setSearchCoach(String keyword) {
		clickClearFilters();
		shortWait();
		clickCoachSearch();
		setValue(keyword, getSearchKeywordInput());
		return this;
	}

	public BookSessionDetailsPage clickBookASessionButton(String coachname) {
		LOGGER.info("Clicking Book A Session button");
		setSearchCoach(coachname);
		shortWait();
		String parentTab = "";
		int counter = 1;
		Set<String> priorWindowsSet = getDriver().getWindowHandles();
		List<String> priorWindowsList = convertSetToList(priorWindowsSet);
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getBookASessionButton(coachname));
				mediumWait();
			} catch (Exception e) {
				if (e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while (flag && counter <= 2);
		// Switching to the new tab that gets opened after clicking on coach
		Set<String> newWindowsSet = getDriver().getWindowHandles();
		List<String> newWindowsList = convertSetToList(newWindowsSet);
		getDriver().switchTo().window(newWindowsList.get(priorWindowsList.size()));
		return new BookSessionDetailsPage();
	}

	public BookSessionViewPage selectSessionLengthFromDropdown(String credit) {
		LOGGER.info("Selecting session length");
		click(getSessionlengthDropdown());
		click(getSelectValueSessionlengthDropdown(credit));
		return this;
	}

	public BookSessionViewPage selectSessionDate(String date) {
		LOGGER.info("Selecting session date");
		click(getSessionDateInput());
		click(getSessionDateElement(date));
		return this;
	}

	public BookSessionViewPage selectTimeFromDropdown(String time) {
		LOGGER.info("Selecting time");
		click(getSelectTimeDropdown());
		click(getSelectValueForTimeDropdown(time));
		return this;
	}

	public BookSessionViewPage selectExpertiseFromDropdown(String expertise) {
		LOGGER.info("Selecting expertise");
		click(getSelectExpertiseDropdown());
		click(getSelectValueForExpertiseDropdown(expertise));
		return this;
	}

	public BookSessionViewPage clickNextButton() {
		LOGGER.info("Selecting Next button");
		click(getNextButton());
		return this;
	}

	public BookSessionViewPage setValueForCoachingSessionHelpwithTextField(String help) {
		LOGGER.info("Set coaching session value");
		setValue(help, getCoachingSessionHelpWithTextFields());
		return this;
	}

	public BookSessionViewPage setvalueForBackgroundReleventForConversationTextField(String background) {
		LOGGER.info("Set value of conversation relevant background");
		setValue(background, getWhatBackgroundReleventForConversationTextFields());
		return this;
	}

	public BookSessionViewPage setvalueAnythingHelpfulToShareWithCoachTextFields(String share) {
		LOGGER.info("Set value of Anlything useful to share with coach");
		setValue(share, getAnythingHelpfulToShareWithCoachTextFields());
		return this;
	}

	public BookSessionViewPage clickSuccessfulInMakingChangesSinceLastSessionRadio(String choice) {
		LOGGER.info("Clicking Successfull in making changes from last session radion button");
		click(getSuccessfulInMakingChangesSinceLastSessionRadio(choice));
		return this;
	}

	public BookSessionViewPage clickRecommendedMythriveToSomeoneRadio(String option) {
		LOGGER.info("Clicking Recommand MyThrive radion button");
		click(getRecommendedMythriveToSomeoneRadio(option));
		return this;
	}

	public BookSessionViewPage clickFinishBookingButton() {
		LOGGER.info("Clicking finish booking button");
		click(getFinishBookingButton());
		return this;
	}

	public BookSessionViewPage clickOkButton() {
		LOGGER.info("Clicking OK button");
		click(getOkButton());
		return this;
	}

	private By getSearchKeywordInput() {
		return By.xpath("//input[@id='keyword-input']");
	}

	public BookSessionViewPage setSearchKeyword(String keyword) {
		setValue(keyword, getSearchKeywordInput());
		return this;
	}

	private By getCoachTypeCheckbox(String coachType) {
		return By.xpath(".//span[contains(text(),'" + coachType + "')]/preceding-sibling::label");
	}

	public BookSessionViewPage checkUncheckTypeCheckbox(boolean checkUncheck, String coachType) {
		checkCheckbox(checkUncheck, getCoachTypeCheckbox(coachType));
		return this;
	}

	public BookSessionViewPage clickAddRow() {
		LOGGER.info("Clicking Add Row button");
		click(getAddRowButton());
		return this;
	}

	public BookSessionViewPage clickClearFilters() {
		LOGGER.info("Clicking clear Filters");
		click(getClearFiltersButton());
		return this;
	}

	public BookSessionViewPage validateCoachPresent(String coachFirstName) {
		LOGGER.info("Validating coach name is present");
		Assert.assertTrue(isElementPresent(getCoachNameLink(coachFirstName)), "Coach not present");
		return this;
	}

	public BookSessionViewPage validateCoachTypePresent(String coachType) {
		LOGGER.info("Validating coach type is present");
		if (coachType == "External") {
			Assert.assertTrue(isElementPresent(getExternalCoachLink()));
		} else {
			Assert.assertTrue(isElementPresent(getInternalCachLink()));
		}
		return this;
	}

	public BookSessionViewPage validateRegionPresent(String region) {
		LOGGER.info("validating region is present");
		Assert.assertTrue(isElementPresent(getRegionPresent(region)));
		return this;
	}

	public BookSessionViewPage validateExpertisepresent(String expertise) {
		LOGGER.info("validating Expertise is present");
		Assert.assertTrue(isElementPresent(getExpertisePresent(expertise)));
		return this;
	}

	public BookSessionViewPage validateIndustryPresent(String industry) {
		LOGGER.info("validating Industry type is present");
		Assert.assertTrue(isElementPresent(getIndustryPresent(industry)));
		return this;
	}

	public BookSessionViewPage validateClearFilters() {
		LOGGER.info("validating Clear Filters works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getCoachSearchInput());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(searchPlaceHolder.equalsIgnoreCase("Keyword"), "Coach Search input box is not reset");
		softAssert.assertAll();
		return this;
	}

	public BookSessionViewPage generateBookSessionDetailsEnterprise(BookSessionDetails bookSeesionDeatils) {
		LOGGER.info("Generting detils to Book A Session");
		selectSessionLengthFromDropdown(bookSeesionDeatils.getCredit());
		selectSessionDate(bookSeesionDeatils.getDate());
		selectTimeFromDropdown(bookSeesionDeatils.getTime());
		selectExpertiseFromDropdown(bookSeesionDeatils.getExpertise());
		return this;
	}

	public BookSessionViewPage generatePreSessionQuestionDetails(BookSessionDetails bookSessionDetails) {
		LOGGER.info("Generating Pre-Session quesions details");
		setValueForCoachingSessionHelpwithTextField(bookSessionDetails.getSessionhelp());
		setvalueForBackgroundReleventForConversationTextField(bookSessionDetails.getBackground());
		setvalueAnythingHelpfulToShareWithCoachTextFields(bookSessionDetails.getShare());
		clickSuccessfulInMakingChangesSinceLastSessionRadio(bookSessionDetails.getOption());
		clickRecommendedMythriveToSomeoneRadio(bookSessionDetails.getChoice());
		return this;
	}

	public BookSessionViewPage generateBookSessionDetailsClient(BookSessionDetails bookSeesionDeatils) {
		LOGGER.info("Generating details to Book A Session");
		selectSessionLengthFromDropdown(bookSeesionDeatils.getCredit());
		selectSessionDate(bookSeesionDeatils.getDate());
		selectTimeFromDropdown(bookSeesionDeatils.getTime());
		selectExpertiseFromDropdown(bookSeesionDeatils.getExpertise());
		clickNextButton();
		setValueForCoachingSessionHelpwithTextField(bookSeesionDeatils.getSessionhelp());
		setvalueForBackgroundReleventForConversationTextField(bookSeesionDeatils.getBackground());
		setvalueAnythingHelpfulToShareWithCoachTextFields(bookSeesionDeatils.getShare());
		// clickSuccessfulInMakingChangesSinceLastSessionRadio(bookSeesionDeatils.getOption());
		// clickRecommendedMythriveToSomeoneRadio(bookSeesionDeatils.getChoice());
		clickFinishBookingButton();
		clickOkButton();
		return this;
	}

	public BookSessionViewPage validateBookSessionElementVisible() {
		LOGGER.info("Validate Book a session element is visible");
		Assert.assertTrue(isElementVisible(getCoachesHeader()), "Book a session is not visible");
		return this;
	}

	public BookSessionViewPage validateBookSessionElementIsNotVisible() {
		LOGGER.info("Validate Book a session element is not visible");
		Assert.assertTrue(isElementNotVisible(getCoachesHeader()), "Book a session is not visible");
		return this;
	}

	public BookSessionViewPage validateFavourateCoachElementVisible() {
		LOGGER.info("Validate Favourate Coach element is visible");
		Assert.assertTrue(isElementVisible(getFavourateCoachHeader()), "Favourate Coach is not visible");
		return this;
	}

	public BookSessionViewPage validateProfilePageCoachElementVisible() {
		LOGGER.info("Validate profile page Coach header element is visible");
		Assert.assertTrue(isElementPresent(getProfilepageCoachHeader()), "Coach profile page is not visible");
		return this;
	}

	private By availableCoachesLink() {
		return By.xpath("//div[@class='flex-1 d-flex align-items-baseline bs-heading']");
	}

	public BookSessionViewPage validateBookASessionPageIsVisible() {
		LOGGER.info("Validate Book A Session link is visible");
		Assert.assertTrue(isElementVisible(availableCoachesLink()), "Book A Session page is not reached");
		return this;
	}

	private By getDateRangeInput() {
		return By.xpath("//input[@placeholder='From - To']");
	}

	private By getCurrentDateInRangeLink() {
		return By.xpath(
				"//div[@class='md-drppicker drops-down-auto ltr shown double']//descendant::td[contains(@class,'active available')]");
	}

	private By getAllSelectedDatesLink() {
		return By.xpath(
				"//div[@class='md-drppicker drops-down-auto ltr shown double']//descendant::td[contains(@class,'in-range')]");
	}

	private By getLeftMonthMonthHeaderLink() {
		return By.xpath(
				"//div[@class='md-drppicker drops-down-auto ltr shown double']//descendant::th[contains(@class,'month drp-animate')]");
	}

	private By getDateRangeNextMonthHeaderLink() {
		return By.xpath("//th[@class='month']");
	}

	public String getCurrentMonth() {
		LOGGER.info("Getting current date time");
		java.util.Date date = new java.util.Date();
		String month = date.toString().substring(4, 7);
		return month;
	}

	public int getTotalSelectedDateCount() {
		LOGGER.info("Getting selected dates count");
		int selectedDates = findElements(getAllSelectedDatesLink()).size()
				+ findElements(getCurrentDateInRangeLink()).size();
		return selectedDates;
	}

	public BookSessionViewPage clickDateRange() {
		LOGGER.info("Clicking Date Range");
		shortWait();
		click(getDateRangeInput());
		return this;
	}

	public Boolean verifyDefaultDateRange() {
		LOGGER.info("Verifying selected date range");
		if (getTotalSelectedDateCount() == 30 && getText(getLeftMonthMonthHeaderLink()).contains(getCurrentMonth())) {
			return true;
		} else {
			return false;
		}
	}

	public BookSessionViewPage validateDefaultDateRange() {
		LOGGER.info("Validating default date range");
		Assert.assertTrue(verifyDefaultDateRange() && isElementPresent(getCurrentDateInRangeLink()),
				"Selected default range is incorrect");
		return this;
	}

	public List<String> captureSelectedDates() {
		List<String> selectedDatesList = new ArrayList<>();
		List<WebElement> selectedDates = findElements(getAllSelectedDatesLink());
		for (WebElement date : selectedDates) {
			selectedDatesList.add(getText(date));
		}
		try {
			selectedDatesList.add(getText(getCurrentDateInRangeLink()));
		} catch (Exception e) {
			System.out.println("This element is pat present" + e.getMessage());
		}
		return selectedDatesList;
	}

	private By getCoachDetailsCoachAvailabilityDatesLink() {
		return By.xpath(
				"//div[@class='card-shadow-box']//div[contains(@class,'profile-info')]//descendant::label//span");
	}

	public boolean verifyCoachAvailabilityDateFromCoachDtails() {
		LOGGER.info("Verifying coaches data displayed");
		List<WebElement> coachAvailabilityDates = findElements(getCoachDetailsCoachAvailabilityDatesLink());
		Boolean flag = true;
		for (WebElement date : coachAvailabilityDates) {
			try {
				String dat = getText(date).substring(10, 12);
				if (dat.contains("0")) {
					dat = dat.substring(1);
				}
				String mon = getText(date).substring(13, 16);
				if (getText(getLeftMonthMonthHeaderLink()).contains(mon)) {
					if (!(captureSelectedDates().contains(dat))) {
						flag = false;
						break;
					}
				} else if (getText(getDateRangeNextMonthHeaderLink()).contains(mon)) {
					if (!(captureSelectedDates().contains(dat))) {
						flag = false;
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(" this date is not present :" + e.getMessage());
			}
		}
		return flag;
	}

	public BookSessionViewPage verifyDisplayedCoachesData() {
		LOGGER.info("Verfying coach availability in selected date range");
		Assert.assertTrue(verifyCoachAvailabilityDateFromCoachDtails(), "Coaches data not selected properly");
		return this;
	}

	private By getCurrentDateLink() {
		return By.xpath(
				"//div[@class='md-drppicker drops-down-auto ltr shown double']//descendant::td[contains(@class,'today')]");
	}

	private By getNextMonthDateLink() {
		return By.xpath(
				"//th[@class='month']//ancestor::thead//following-sibling::tbody//descendant::span[contains(text(),'28')]/..");
	}

	public BookSessionViewPage selectDateRange() {
		LOGGER.info("Selecting date range");
		click(getCurrentDateLink());
		waitUntilElementIsVisible(10, getNextMonthDateLink());
		click(getNextMonthDateLink());
		return this;
	}

	public boolean verifyDateRangeSelection() {
		LOGGER.info("Verifying that date range is selected");
		click(getDateRangeInput());
		List<WebElement> selectedDates = findElements(getAllSelectedDatesLink());
		Boolean flag = true;
		for (WebElement selectedDate : selectedDates) {
			if (!(isElementVisible(selectedDate))) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public BookSessionViewPage validateDateDateRangeSelection() {
		LOGGER.info("validate date range is selected");
		Assert.assertTrue(verifyDateRangeSelection(), "Failed to select date range");
		return this;

	}

	private By getNextMonthToggle() {
		return By.xpath(
				"//div[@class='calendar right ng-star-inserted']//descendant::th[contains(@class,'next available ng-star-inserted')]");
	}

	private By getPreviousMonthToggle() {
		return By.xpath(
				"//div[@class='calendar left ng-star-inserted']//descendant::th[contains(@class,'prev available ng-star-inserted')]");
	}

	public BookSessionViewPage changeToNextMonth() {
		LOGGER.info("Changing to next month by clicking right month toggle");
		click(getNextMonthToggle());
		return this;
	}

	public BookSessionViewPage changeToPreviousMonth() {
		LOGGER.info("Changing to previous month by clicking left month toggle");
		click(getPreviousMonthToggle());
		return this;
	}

	public String getLeftMonthHeaderValue() {
		LOGGER.info("Caputre month header value");
		String monthHeader = getText(getLeftMonthMonthHeaderLink());
		String monthValue = monthHeader.substring(0, (monthHeader.length() - 4));
		return monthValue;
	}

	public BookSessionViewPage validateRightToggleFunctioning() {
		LOGGER.info("Validate right month toggle functioning");
		String monthAfterChange = getText(getLeftMonthMonthHeaderLink());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 2);
		String fullTimeInstance = calendar.getTime().toString();
		String expectedMonthChange = fullTimeInstance.substring(4, 7);
		Assert.assertFalse(monthAfterChange.contains(expectedMonthChange), "Failed to change month using month toggle");
		return this;
	}

	public BookSessionViewPage validateLeftToggleFunctioning() {
		LOGGER.info("Validate left month toggle functioning");
		String monthAfterChange = getText(getLeftMonthMonthHeaderLink());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -2);
		String fullTimeInstance = calendar.getTime().toString();
		String expectedMonthChange = fullTimeInstance.substring(4, 7);
		Assert.assertFalse(monthAfterChange.contains(expectedMonthChange), "Failed to change month using month toggle");
		return this;
	}

	private By getDateRangeClearButtonLink() {
		return By.xpath("//div[contains(text(),'DATE RANGE')]//child::span");
	}

	public BookSessionViewPage clickClearDateRangeButton() {
		LOGGER.info("Clicking clear date range button");
		click(getDateRangeClearButtonLink());
		return this;
	}

	public BookSessionViewPage validateClearDateRage() {
		LOGGER.info("Validate selected date raange cleared");
		Assert.assertFalse(findElements(getAllSelectedDatesLink()).size() > 1, "Failed to clear date range");
		return this;
	}

	private By getExternalCoachCheckBoxLink() {
		return By.xpath("//span[contains(text(),'External')]//preceding-sibling::label");
	}

	private By getInternalCoachCheckBoxLink() {
		return By.xpath("//span[contains(text(),'Internal coach')]//preceding-sibling::label");
	}

	private By getFilterByCoachTypeLink() {
		return By.xpath("//button[contains(text(),'FILTER BY COACH TYPE ')]");
	}
	
	private By getExternalCoaches() {
		return By.xpath(".//p[contains(text(),'My Thrive')]");
	}
	
	private By getInternalCoaches() {
		return By.xpath(".//p[contains(text(),'INTERNAL')]");
	}

	public BookSessionViewPage validateCoachTypeOptions() {
		LOGGER.info("Validate Filter By Coach type options contains Favorites, External, Internal coach");
		if (isElementNotVisible(getFavourateCheckBox())) {
			click(getFilterByCoachTypeLink());
		}
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getFavourateCheckBox()), "Favorite Coach Type is not present");
		softAssert.assertTrue(isElementVisible(getExternalCoachTypeLink()), "External Coach Type is not present");
		softAssert.assertTrue(isElementVisible(getInternalCoachTypeLink()), "Internal Coach Type is not present");
		softAssert.assertAll();
		return this;
	}
	
	public BookSessionViewPage validateCoachesTypePresent(String coachtype) {
		LOGGER.info("Validate Coaches type present");
		if (coachtype.equalsIgnoreCase("Favourites")) {
			Assert.assertTrue(isElementNotVisible(getNonFavoriteCoachesListLink()),
					"Filed to filter Favorite coaches list");
		} else if (coachtype.equalsIgnoreCase("External")) {
			Assert.assertTrue(isElementPresent(getExternalCoaches()));
		} else {
			Assert.assertTrue(isElementPresent(getInternalCoaches()));
		}
		return this;
	}
	
	public BookSessionViewPage validateCoachesPresent() {
		LOGGER.info("Validate Coaches present");
		Assert.assertTrue(isElementPresent(getExternalCoaches()));
		return this;
	}

	public BookSessionViewPage validateFavoriteCoaches() {
		LOGGER.info("Validate only favorite coaches list is displayed");
		shortWait();
		Assert.assertTrue(isElementNotVisible(getNonFavoriteCoachesListLink()),
				"Filed to filter Favorite coaches list");
		return this;
	}

	private By getNonFavoriteCoachesListLink() {
		return By.xpath("//div[@class='card-shadow-box']//i[contains(@class,'grayed_out')]");
	}

	public BookSessionViewPage clickExternalCoachType() {
		LOGGER.info("Clicking External in Filter By Coach Type");
		waitUntilElementIsClickable(15, getExternalCoachCheckBoxLink());
		click(getExternalCoachCheckBoxLink());
		return this;
	}

	public BookSessionViewPage clickInternalCoachType() {
		LOGGER.info("Clicking Internal in Filter By Coach Type");
		waitUntilElementIsClickable(15, getInternalCoachCheckBoxLink());
		click(getInternalCoachCheckBoxLink());
		return this;
	}

	public By getInternalCoachTypeTagsLink() {
		return By
				.xpath("//div[@class='card-shadow-box']//span[@class='coachtype-tag']//p[contains(text(),'INTERNAL')]");
	}

	public By getExternalCoachTypeTagsLink() {
		return By.xpath(
				"//div[@class='card-shadow-box']//span[@class='coachtype-tag']//p[contains(text(),'My Thrive')]");
	}

	public BookSessionViewPage validateExternalCoachesList() {
		LOGGER.info("Validate only External coaches list is displayed");
		viewFullCoachList();
		Assert.assertTrue(isElementNotVisible(getInternalCoachTypeTagsLink()),
				"Failted to filter External Coach category coaches list");
		return this;
	}

	private By getShowMoreCoachesButtonLink() {
		return By.xpath("//a[contains(text(),'Show More Coaches')]");
	}

	public BookSessionViewPage viewFullCoachList() {
		LOGGER.info("Clicking Show More Coaches to view complete coach list");
		while (!(isElementNotVisible(getShowMoreCoachesButtonLink()))) {
			if (isElementVisible(getShowMoreCoachesButtonLink())) {
				click(getShowMoreCoachesButtonLink());
			} else {
				break;
			}
		}
		return this;
	}

	public BookSessionViewPage validateInternalCoachesList() {
		LOGGER.info("Validate only Internal coaches list is displayed");
		viewFullCoachList();
		shortWait();
		Assert.assertTrue(isElementNotVisible(getExternalCoachTypeTagsLink()),
				"Failted to filter Internal Coach category coaches list");
		return this;
	}

	private By getCoachTypeAllCheckBoxLink() {
		return By.xpath("//label[@class='d-inline-flex text-break']//preceding-sibling::input");
	}

	private int getSelectedCheckBoxCount() {
		int selectedCategoriesCount = 0;

		if (isChecked(getFavourateCheckBox())) {
			selectedCategoriesCount = selectedCategoriesCount + 1;
		}
		List<WebElement> categorries = findElements(getCoachTypeAllCheckBoxLink());

		for (WebElement category : categorries) {
			if (category.isSelected()) {
				selectedCategoriesCount = selectedCategoriesCount + 1;
			}
		}
		return selectedCategoriesCount;
	}

	public int countSelectedCheckBoxNumber() {
		LOGGER.info("Counting number of checkbox selected");
		int selectedCheckBoxCount = getSelectedCheckBoxCount();
		if (selectedCheckBoxCount == 0) {
			click((getExternalCoachLink()));
			selectedCheckBoxCount = getSelectedCheckBoxCount();
			if (selectedCheckBoxCount == 0) {
				click(getInternalCoachCheckBoxLink());
				selectedCheckBoxCount = getSelectedCheckBoxCount();
				if (selectedCheckBoxCount == 0) {
					click(getFavourateCoachHeader());
					selectedCheckBoxCount = getSelectedCheckBoxCount();
				}
			}
		}
		return selectedCheckBoxCount;
	}

	public BookSessionViewPage validateCountIsDisplayed(int selectionCount) {
		LOGGER.info("Validate selected number of check boxes is displayed in Coach Type heading");
		String count = String.valueOf(selectionCount);
		Assert.assertTrue(getText(getFilterByCoachTypeLink()).contains(count),
				"Selected number of check boxes is not displayed in the heading");
		return this;
	}

	private By getExternalCoachTypeLink() {
		return By.xpath("//span[contains(text(),'External')]");
	}

	private By getInternalCoachTypeLink() {
		return By.xpath("//span[contains(text(),'Internal')]");
	}

	public BookSessionViewPage clickExternal() {
		LOGGER.info("Click External Coach Type");
		click(getExternalCoachTypeLink());
		return this;
	}

	public BookSessionViewPage clickInternal() {
		LOGGER.info("Click Internal Coach Type");
		click(getInternalCoachTypeLink());
		return this;
	}

	private By getExternalCategoriesLink() {
		return By.xpath(
				"//span[contains(text(),'External')]//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']//descendant::div[@class='tp-customcb-bx ng-star-inserted']");
	}

	private By getInternalCategoriesLink() {
		return By.xpath(
				"//span[contains(text(),'Internal coach')]//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']//descendant::div[@class='tp-customcb-bx ng-star-inserted']");
	}

	public List<String> captureExternalCategories() {
		LOGGER.info("Capturing external categories");
		shortWait();
		List<String> externalCategoriesList = new ArrayList<>();
		List<WebElement> externalCategories = findElements(getExternalCategoriesLink());
		for (WebElement category : externalCategories) {
			externalCategoriesList.add(getText(category));
		}
		return externalCategoriesList;
	}

	public List<String> captureInternalCategories() {
		LOGGER.info("Capturing internal categories");
		shortWait();
		List<String> externalCategoriesList = new ArrayList<>();
		List<WebElement> externalCategories = findElements(getInternalCategoriesLink());
		for (WebElement category : externalCategories) {
			externalCategoriesList.add(getText(category));
		}
		return externalCategoriesList;
	}

	private By getExternalExpertiseDropdownLink() {
		return By.xpath(
				"//button[contains(text(),'External Expertise')]//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']//div[contains(@class,'ng-select-container')]");
	}

	private By getInternalExpertiseDropdownLink() {
		return By.xpath(
				"//button[contains(text(),'Internal Expertise')]//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']//div[contains(@class,'ng-select-container')]");
	}

	public BookSessionViewPage clickExternalExpertiseDropdown() {
		LOGGER.info("Clicking external expertise dropdown");
		click(getExternalExpertiseDropdownLink());
		return this;
	}

	public BookSessionViewPage clickInternalExpertiseDropdown() {
		LOGGER.info("Clicking internal expertise dropdown");
		click(getInternalExpertiseDropdownLink());
		return this;
	}

	private By getDropdownExpertiseLink() {
		return By.xpath("//ng-dropdown-panel[@role='listbox']//div[@id='tp-multiselect']");
	}

	public List<String> captureDropdownExpertise() {
		LOGGER.info("Capturing all dropdown categories");
		List<String> catogoriesList = new ArrayList();
		List<WebElement> categories = findElements(getDropdownExpertiseLink());
		for (WebElement category : categories) {
			catogoriesList.add(getText(category));
		}
		return catogoriesList;
	}

	public BookSessionViewPage selectDropdownMultipleExpertise() {
		LOGGER.info("Selecting multiple dropdown expertise");
		List<WebElement> expertiseList = findElements(getDropdownExpertiseLink());
		for (WebElement expertise : expertiseList) {
			click(expertise);
		}
		return this;
	}

	private By getExpertiseCheckBoxLink(String expertise) {
		return By.xpath("//small[contains(text(),'" + expertise + "')]//ancestor::div[@id='tp-multiselect']//input");
	}

	public BookSessionViewPage selectExpertiseName(String expertise) {
		LOGGER.info("Selecting expertise");
		click(getExpertiseCheckBoxLink(expertise));
		return this;
	}

	private By getCoachesNameLink() {
		return By.xpath("//div[@class='card-shadow-box']//h3//span[contains(@class,'font-family-Nunito')]");
	}

	private By getSeletedExpertiseNameLink(String expertise) {
		return By.xpath("//span[contains(text(),'" + expertise + "')]");
	}

	public BookSessionViewPage validateExpertiseDislpayedBelowDropdown(String expertise, String expertiseType) {
		LOGGER.info("Validate selected expertise name is displayed below the dropdown");
		int dropdownY = 0;
		if (expertiseType.equalsIgnoreCase("External")) {
			dropdownY = getElementYCoordinate(getExternalExpertiseDropdownLink());
		} else {
			dropdownY = getElementYCoordinate(getInternalExpertiseDropdownLink());
		}
		int selectedExpeertiseY = getElementYCoordinate(getSeletedExpertiseNameLink(expertise));
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getSeletedExpertiseNameLink(expertise)),
				"The selected expertise is not displayed");
		softAssert.assertTrue(dropdownY < selectedExpeertiseY,
				"The selected expertise is not displayed below the dropdown");
		softAssert.assertAll();
		return this;
	}

	private By getClearSelectedExpertiseButtonLink() {
		return By.xpath(
				"//button[contains(text(),'Expertise')]//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']//div[contains(@class,'ng-select-container')]//descendant::span[contains(@class,'ng-clear-')]");
	}

	public BookSessionViewPage deselectExpertise() {
		LOGGER.info("Deselecting selected expeertise");
		click(getClearSelectedExpertiseButtonLink());
		return this;
	}

	public BookSessionViewPage validateAllExpertiseDeselection(List<String> selectedExpertise) {
		LOGGER.info("Validate all selectd expertise deselected");
		Boolean flag = true;
		int expertiesCount = selectedExpertise.size();
		for (int i = 0; i < expertiesCount; i++) {
			if (isElementVisible(getSeletedExpertiseNameLink(selectedExpertise.get(i)))) {
				flag = false;
			}
		}
		Assert.assertTrue(flag, "Falied to deselect all expertise");
		return this;
	}

	private By getDelectSpecificExpertiseLink(String expertiseName) {
		return By.xpath("//span[contains(text(),'" + expertiseName + "')]//following-sibling::a");
	}

	public BookSessionViewPage deselectSpecificExpertise(String expertiseName) {
		LOGGER.info("Deselecting expertise");
		click(getDelectSpecificExpertiseLink(expertiseName));
		return this;
	}

	public BookSessionViewPage validateSpecificExpertiseDeselection(String expertiseName) {
		LOGGER.info("Validate clicking 'x' button in front of expertise name, deselects expertise");
		Assert.assertTrue(isElementNotVisible(getSeletedExpertiseNameLink(expertiseName)),
				"Falied to deselect specific expertise");
		return this;
	}

	private By getCoachSearchLink() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(".//button[text()='admin.private_sessions.coach_search']"));
		} else {
			return By.xpath(getXpathByText(
					"//button[contains(text(),'private.book_session.search.languages')]//ancestor::accordion-group//preceding-sibling::accordion-group[1]//button"));
		}
	}

	public BookSessionViewPage clickCoachSearch() {
		LOGGER.info("Clicking Coach Search");
		click(getCoachSearchLink());
		return this;
	}

	public BookSessionViewPage validateKeywordSearch(String searchKeyword) {
		LOGGER.info("Validate only searched keyword related coach data is displayed");
		Boolean flag = false;
		List<WebElement> coachesNameList = findElements(getCoachesNameLink());
		for (WebElement coachName : coachesNameList) {
			if ((getText(coachName).contains(searchKeyword))) {
				flag = true;
				break;
			}
		}
		Assert.assertTrue(flag, "Failed to search keyword related coach data");
		return this;
	}

	public BookSessionViewPage selectMultiple() {
		LOGGER.info("Selecting expertise");
		click(getExpertiseCheckBoxLink(expertise));
		return this;
	}

	//--------------------------------------------------------------------------------------------------------//

	private By getCheckox() {
		return By.xpath("//label[contains(text(),'Arrange Session ')]");
	}

	public BookSessionViewPage clickArrangeSessioncheckbox() {
		LOGGER.info("Clicks ArrangeSessionCheckbox");
		click(getCheckox());
		return this;
	}

	private By getclientname () {
		return By.xpath("//ng-select[@name='clients']//input[@type='text']");
	}

	public BookSessionViewPage selectclientname() {
		click(getclientname());
		return this;
	}

	private By getDropdwonSessionlengthOptions() {
		return By.xpath("//ng-select[@name='creditType']//input[@type='text']");
	}

	public BookSessionViewPage verifySessionLength() {
		LOGGER.info("verify SessionLength");
		Assert.assertFalse(getDriver().findElement(getDropdwonSessionlengthOptions()).isEnabled() && getDriver().findElement(getDropdwonSessionlengthOptions()).isDisplayed());
		return this;
	}

	private By getDatepickeroptions() {
		return By.xpath("//input[@id='session_date']");
	}

	public BookSessionViewPage verifySessionDate() {
		LOGGER.info("verify SessionLength");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementNotVisible(getDatepickeroptions()),
				"The selected Element is enabled");
		return this;
	}

	private By getTimeDropownOptions () {
		return By.xpath("//ng-select[@name='session_time']//input[@type='text']");
	}

	public BookSessionViewPage verifySessionTime() {
		LOGGER.info("verify SessionTime");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementNotVisible(getTimeDropownOptions()),
				"The selected Element is enabled");
		return this;
	}

	private By getLengthdropdown() {
		return By.xpath("//div[contains(text(),'LENGTH')]");
	}

	private By getLengthDropdownoptions() {
		return By.xpath("//div[@title='1 credit (length 30 min)']");
	}

	public BookSessionViewPage provideLengthDropdownvalues() {
		click(getLengthdropdown());
		click(getLengthDropdownoptions());
		return this;
	}
	
	private By getSessionDateelement() {
		return By.xpath("//ngb-datepicker[@class='dropdown-menu show ng-star-inserted']//div[@class='btn-light ng-star-inserted'][normalize-space()='26']");
	}

	public BookSessionViewPage selectSessionDate1() {
		LOGGER.info("Selecting session date");
		click(getSessionDateInput());
		click(getSessionDateelement());
		return this;
	}

	private By getMultipleSessionButton() {
		return By.xpath("//button[contains(text(),' Add Row ')]");
	}

	public BookSessionViewPage verifyMulipleSessionbutton() {
		LOGGER.info("verify SessionTime");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementNotVisible(getMultipleSessionButton()),
				"The selected Element is enabled");
		return this;

	}



}
