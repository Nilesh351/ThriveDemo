package com.thrive.ui.page.book_session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.thrive.ui.core.BaseTestPage;

public class CoachBioPage extends BaseTestPage{
	
	
	private By getExpertInElementsList() {
		return By.xpath(
				"//div[contains(text(),'Expert in')]//following-sibling::accordion//descendant::accordion-group//button");
	}

	private By getExpertiesListLink() {
		return By.xpath(
				"//div[contains(text(),'Expert in')]//following-sibling::accordion//descendant::div[@role='tabpanel']");
	}

	private boolean verifyExperties(String expertiseName) {
		LOGGER.info("Validate experties");
		String parentwindow = getParentWindowHandle();
		switchToChildWindow(parentwindow);
		waitUntilElementIsClickable(15, getExpertInElementsList());
		Boolean flag = true;
		List<WebElement> elements = findElements(getExpertInElementsList());
		exitLooping: for (WebElement element : elements) {
			click(element);
			List<WebElement> expertiseList = findElements(getExpertiesListLink());
			for (WebElement expertise : expertiseList) {
				if (!(getText(expertise).equalsIgnoreCase(expertiseName))) {
					flag = false;
					break exitLooping;
				}
			}
		}
		return flag;
	}

	public CoachBioPage validateSelectedExpertiseCoachDate(String expertiseName) {
		LOGGER.info("Validate only selected expertise related data is displayed in coaches section");
		Assert.assertTrue(verifyExperties(expertiseName));
		return this;
	}

	private By getLanguagesListOnCoachBioPage() {
		return By.xpath("//div[contains(text(),'Languages')]//following-sibling::div");
	}

	public Boolean verifySelectedLanguageCoachData(String languageName) {
		LOGGER.info("Verifying language on coach bio page");
		String parentwindow = getParentWindowHandle();
		switchToChildWindow(parentwindow);
		waitUntilElementIsClickable(15, getLanguagesListOnCoachBioPage());
		Boolean flag = false;
		List<WebElement> elements = findElements(getLanguagesListOnCoachBioPage());
		for (WebElement element : elements) {
			if (getText(element).contains(languageName)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public CoachBioPage validateSelectedLanguageCoachData(String languageName) {
		LOGGER.info("Validate only selected language related data is displayed in coaches section");
		Assert.assertTrue(verifySelectedLanguageCoachData(languageName));
		return this;
	}
	
	private By getTopicbutton(String topic) {
		return By.xpath(".//button[contains(text(),'"+topic+"')]/../../../../../..");
	}
	
	private By getExpertise(String expertise) {
		return By.xpath(".//span[contains(text(),'"+expertise+"')]");
	}
	
	public CoachBioPage validateExpertisepresent(String expertise) {
		LOGGER.info("Validate selected expertise present");
		Assert.assertTrue(isElementPresent(getExpertise(expertise)));
		return this;
	}

	private By getIndustriesListOnCoachBioPage() {
		return By.xpath("//div[contains(text(),'Industry')]//following-sibling::div//descendant::span");
	}

	private By getBookASessionButton() {
		return By.xpath("//button[contains(text(),'Book a session')]");
	}

	public Boolean verifySelectedIndustryCoachData(String industryName) {
		LOGGER.info("Verifying industry on coach bio page");
		String parentwindow = getParentWindowHandle();
		switchToChildWindow(parentwindow);
		Boolean flag = false;
		shortWait();
		List<WebElement> elements = findElements(getIndustriesListOnCoachBioPage());
		for (WebElement element : elements) {
			if (getText(element).contains(industryName)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public CoachBioPage validateSelectedIndustryCoachData(String industryName) {
		LOGGER.info("Validate only selected industry related data is displayed in coaches section");
		Assert.assertTrue(verifySelectedIndustryCoachData(industryName));
		return this;
	}

	public CoachBioPage validateCoachBioPageIsVisible() {
		LOGGER.info("Validating user is redirected to coach bio page");
		String parentwindow = getParentWindowHandle();
		switchToChildWindow(parentwindow);
		Assert.assertTrue(isElementVisible(getCoachDetailsLink()), "User is not redirected to Coach bio page");
		return this;
	}

	private By getCoachDetailsLink() {
		return By.xpath("//app-coachview//div//div[contains(@class,'row')][1]");
	}

	private By getQualifications() {
		return By.xpath("//h4[contains(text(),'Qualifications')]");
	}

	private By getTopNewIndustries() {
		return By.xpath("//h4[contains(text(),'Top new industries')]");
	}

	private By getAbout() {
		return By.xpath("//h4[contains(text(),'About')]");
	}

	private By getTestimonials() {
		return By.xpath("//h4[contains(text(),'Testimonial')]");
	}

	private By getCoachingSummary() {
		return By.xpath("//h4[contains(text(),'Coaching Summary')]");
	}

	private By getLocation() {
		return By.xpath("//h4[contains(text(),'Location')]");
	}

	private By getLevelOfPeopleCoached() {
		return By.xpath("//h4[contains(text(),'level of people have you coached')]");
	}

	private By getProfessionalSummary() {
		return By.xpath("//h4[contains(text(),'Professional Summary')]");
	}

	private By getVerifiedFor() {
		return By.xpath("//div[contains(text(),'VERIFIED FOR')]");
	}

	private By getCoachPhoto() {
		return By.xpath("//div[contains(@class,'coach-pic')]//i//following-sibling::img");
	}

	private By getCoachType() {
		return By.xpath(".//span[@class='coachtype-tag']");
	}

	private By getFavouriteTag() {
		return By.xpath("//div[contains(@class,'coach-pic')]//i");
	}

	private By getCoachInitials() {
		return By.xpath("//div[contains(@class,'text-align-center')]//span");
	}

	private By getCoachAvailabilitySection() {
		return By.xpath("//div[contains(@class,'booking-dates-container')]");
	}

	private By getCoachName() {
		return By.xpath("//span[@class='coachtype-tag']//preceding-sibling::span[contains(@class,'coach-name')]");
	}

	private By getCoachSummary() {
		return By.xpath("//div[contains(@class,'profile-info')]//child::p[2]");
	}

	private By getCoachRegion() {
		return By.xpath("//p[contains(@class,'coach-country')]");
	}

	private By getCoachExperience() {
		return By.xpath("//p[contains(@class,'coach-country')]//following-sibling::div");
	}

	private By getAvailabilityDate() {
		return By.xpath(
				"//div[contains(@class,'booking-dates-container')]//div[contains(@class,'booking-dates')][1]//child::div[contains(@class,'font')]");
	}

	private By getAvailabilityTime() {
		return By.xpath(
				"//div[contains(@class,'booking-dates-container')]//div[contains(@class,'booking-dates ')][1]//div[contains(@class,'bookslot-container')]//div[1]//button[contains(@class,'btn')]");
	}

	public CoachBioPage validateCoachBioPageContent() {
		LOGGER.info("Validate coach bio page conent");
		String parentwindow = getParentWindowHandle();
		switchToChildWindow(parentwindow);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getCoachDetailsLink()),
				"Coach details are not visible on coach bio page");
		softAssert.assertTrue(isElementVisible(getQualifications()),
				"Qualifications are not visible on coach bio page");
		softAssert.assertTrue(isElementVisible(getTopNewIndustries()),
				"Top new industries are not visible on coach bio page");
		softAssert.assertTrue(isElementVisible(getAbout()), "About details are not visible on coach bio page");
		softAssert.assertTrue(isElementVisible(getTestimonials()), "Testimonials are not visible on coach bio page");
		softAssert.assertTrue(isElementPresent(getCoachingSummary()),
				"Coaching Summary is not visible on coach bio page");
		softAssert.assertTrue(isElementVisible(getLocation()), "Location is not visible on coach bio page");
		softAssert.assertTrue(isElementVisible(getLevelOfPeopleCoached()),
				"Level of peoples coached is not visible on coach bio page");
		softAssert.assertTrue(isElementVisible(getProfessionalSummary()),
				"Professional summary is not visible on coach bio page");
		softAssert.assertTrue(isElementVisible(getVerifiedFor()),
				"Verified For details are not visible on coach bio page");
		softAssert.assertAll();
		return this;
	}

	private Boolean validateCoachPhotoAvailability() {
		LOGGER.info("Validating coach photo availability");
		Boolean flag = false;
		try {
			if (isElementVisible(getCoachPhoto()) || isElementVisible(findElement(getCoachInitials()))) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	public CoachBioPage validateCoachDetailsContent() {
		LOGGER.info("Validate coach details content on coach bio page");
		String parentwindow = getParentWindowHandle();
		switchToChildWindow(parentwindow);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementVisible(getFavouriteTag()),
				"Favourite tag is not present in coach details on coach bio page");
		softAssert.assertTrue(validateCoachPhotoAvailability(),
				"Coach photo is not present in coach details on coach bio page");
		softAssert.assertTrue(isElementVisible(getBookASessionButton()),
				"Book A Session button is not present in coach details on coach bio page");
		softAssert.assertTrue(isElementVisible(getCoachAvailabilitySection()),
				"Coach Availability is not present in coach details on coach bio page");
		softAssert.assertTrue(isElementVisible(getCoachName()),
				"Coach Name is not present in coach details on coach bio page");
		softAssert.assertTrue(isElementPresent(getCoachSummary()),
				"Coach summary is not present in coach details on coach bio page");
		softAssert.assertTrue(isElementVisible(getCoachType()),
				"Coach type is not present in coach details on coach bio page");
		softAssert.assertTrue(isElementVisible(getCoachRegion()),
				"Coach region is not present in coach details on coach bio page");
		softAssert.assertTrue(isElementPresent(getCoachExperience()),
				"Coach Experience is not present in coach details on coach bio page");
		softAssert.assertAll();

		return this;
	}

	public BookSessionDetailsPage clickBookASession() {
		LOGGER.info("Click Book A Session");
		click(getBookASessionButton());
		return new BookSessionDetailsPage();
	}

	public List<String> selectSessionSchedule() {
		LOGGER.info("Selecting session date and time on Book A Session page");
		String parentwindow = getParentWindowHandle();
		switchToChildWindow(parentwindow);
		List<String> sessionSchedule = new ArrayList<>();
		String scheduleDate;
		String availabilityDate = "";
		WebElement availabilityTimes = findElement(getAvailabilityTime());
		String scheduleTime = getText(availabilityTimes);
		sessionSchedule.add(scheduleTime);
		click(getAvailabilityTime());
		availabilityDate = getText(getAvailabilityDate());
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		if (availabilityDate.equalsIgnoreCase("Today")) {
			scheduleDate = dateTimeFormatter.format(now);
			sessionSchedule.add(scheduleDate);
		} else if (availabilityDate.equalsIgnoreCase("Tommorow")) {
			scheduleDate = now.plusDays(1).format(dateTimeFormatter);
			sessionSchedule.add(scheduleDate);
		} else {
			scheduleDate = now.parse(availabilityDate, dateTimeFormatter).toString();
			sessionSchedule.add(scheduleDate);
		}
		return sessionSchedule;
	}

	private By getTopics() {
		return By.xpath("//accordion//accordion-group//button");
	}

	private By getExpertise() {
		return By.xpath(
				"//accordion//accordion-group//button//ancestor::div[@role='tab']//following-sibling::div[@role='tabpanel']");
	}

	public Boolean validateTopicSelection(String selectedExpertise) {
		LOGGER.info("Validate only selected topic related coach data is displayed");
		Boolean flag = false;
		List<WebElement> topicsList = findElements(getTopics());
		exitLoop:for (WebElement topic : topicsList) {
			click(topic);
			List<WebElement> expertiseElementsList = findElements(getExpertise());
			for (WebElement expertiseElement : expertiseElementsList) {
				if (getText(expertiseElement).equalsIgnoreCase(selectedExpertise)) {
					flag = true;
					break exitLoop;
				}
			}
		}
		return flag;
	}

}
