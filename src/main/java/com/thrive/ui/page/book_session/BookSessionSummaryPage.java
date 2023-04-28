package com.thrive.ui.page.book_session;

import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.pojos.book_session.BookSessionSummaryDetails;
import com.thrive.ui.core.BaseTestPage;

public class BookSessionSummaryPage extends BaseTestPage{
	
	private By getPreSessionQuestionTextArea(String quetionLabel) {
		return By.xpath(".//label[contains(text(),'"+quetionLabel+"')]/../following-sibling::textarea");
	}
	
	public BookSessionSummaryPage setPreSessionQuestion(String quetionLabel, String answer) {
		setValue(answer, getPreSessionQuestionTextArea(quetionLabel));
		return this;
	}
	
	private By getPreSessionQuestionRadioOptionYes(String quetionLabel) {
		return By.xpath(".//label[contains(text(), '"+quetionLabel+"')]/../following-sibling::div//label[contains(text(),'Yes')]");
	}
	
	private By getErrorMessage() {
		return By.xpath(".//p[text()='This field is required']");
	}
	
	private By getPreSessionQuestionRadioOptionNo(String quetionLabel) {
		return By.xpath(".//label[contains(text(), '"+quetionLabel+"')]/../following-sibling::div//label[contains(text(),'No')]");
	}
	
	public BookSessionSummaryPage selectPreSessionQuestionRadioOption(String quetionLabel, boolean isYes) {
		LOGGER.info("Selecting Pre-Session Questons radio button ");
		if(isYes) {
			click(getPreSessionQuestionRadioOptionYes(quetionLabel));
		} else {
			click(getPreSessionQuestionRadioOptionNo(quetionLabel));
		}
		return this;
	}
	
	private By getFinishBookingButton() {
	//	return By.xpath(getXpathByText(".//button[contains(text(),'private.book_session.book.step2.previous')]/following-sibling::button[contains(text(),'private.book_session.book.step2.finish')]"));
	    return By.xpath("//div[contains(@class,'form-group')]//button[@type='submit'][2]");
	}
	
	private By getSessionBookingComepletionWindow() {
		return By.xpath("//app-session-booking-completed");
	}
	
	private By getFinishButtonArrangeSession() {
		//return By.xpath(getXpathByText(".//button[contains(text(),'private.arrange_session.arrange.step2.previous')]/following-sibling::button[contains(text(),'private.arrange_session.arrange.step2.finish')]"));
		return By.xpath("//div[contains(@class,'form-group')]//button[@type='submit']");
	}
	
	private By getOkButton() {
		return By.xpath(".//a[text()='OK']");
	}
	
	public BookSessionConfirmationPage clickFinishBooking() {
		LOGGER.info("Clicking Finish Booking button");
		click(getFinishBookingButton());
		return new BookSessionConfirmationPage();
	}
	
	public BookSessionConfirmationPage clickArrangeSessionFinishBooking() {
		LOGGER.info("Clicking Finish Booking button");
		click(getFinishButtonArrangeSession());
		return new BookSessionConfirmationPage();
	}
	
	public BookSessionSummaryPage validateErrorMessagePresent() {
		LOGGER.info("Validating error message");
		Assert.assertTrue(isElementPresent(getErrorMessage()));
		return this;
	}
	
	public BookSessionSummaryPage validateArrangeSessionBooking() {
		LOGGER.info("Validate enterprsie admin has successfully booked session for client");
		Assert.assertTrue(isElementVisible(getSessionBookingComepletionWindow()));
		click(getOkButton());
		return this;
	}
	
	
	public BookSessionSummaryPage enterPrequestionDetails(BookSessionSummaryDetails bookSessionSummaryDetails) {
		LOGGER.info("Filling Summary questions details");
		Map<String, String> preQuestionAnswers = bookSessionSummaryDetails.getPreQuestionAnswers();
		
		for(Map.Entry<String, String> entry : preQuestionAnswers.entrySet()) {
			
			
			if(entry.getValue().equalsIgnoreCase("yes") && isElementPresent(getPreSessionQuestionRadioOptionYes(entry.getKey()))) {
				selectPreSessionQuestionRadioOption(entry.getKey(), true);
			} else if (entry.getValue().equalsIgnoreCase("yes") && ! isElementPresent(getPreSessionQuestionRadioOptionYes(entry.getKey()))) {
				continue;
			} else if (entry.getValue().equalsIgnoreCase("no") && isElementPresent(getPreSessionQuestionRadioOptionNo(entry.getKey()))) {
				selectPreSessionQuestionRadioOption(entry.getKey(), false);
			} else if (entry.getValue().equalsIgnoreCase("no") && !isElementPresent(getPreSessionQuestionRadioOptionNo(entry.getKey()))) {
				continue;   
			} else {
				setPreSessionQuestion(entry.getKey(), entry.getValue());
			}
			
		}
		return this;
		
	}
	

}
