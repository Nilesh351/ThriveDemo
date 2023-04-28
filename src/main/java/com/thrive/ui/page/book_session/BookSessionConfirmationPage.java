package com.thrive.ui.page.book_session;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;

public class BookSessionConfirmationPage extends BaseTestPage {

	public String date;
	public String time;
	int actualSessionBooked;
	List<String> sessionDetails = new ArrayList<>();

	public String[] details = new String[3];

	private By getBookedElement() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(".//div[text()='private.detail.session_status.booked']"));
		} else {
			return By.xpath(".//div[contains(text(),'Réservée')]");
		}
	}

	private By getBookedSessionDate(int index) {
		return By.xpath(".//div[@class='mv-bookesession']//div[" + index
				+ "][contains(@class,'card bg-light-gray')]//div[contains(@class,'d-inline-flex')]/div[1]//span");
	}

	private By getBookedSessiontime(int index) {
		return By.xpath(".//div[@class='mv-bookesession']//div[" + index
				+ "][contains(@class,'card bg-light-gray')]//div[contains(@class,'d-inline-flex')]/div[3]//span");
	}

	public BookSessionConfirmationPage validateSessionsBooked(int totalSession) {
		LOGGER.info("Validating " + totalSession + " booked successfully.");
		mediumWait();
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				actualSessionBooked = findElements(getBookedElement()).size();
			} catch (Exception e) {
				if(e.getCause().getMessage().contains("no such element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while(flag && counter<=5);
		
		System.out.println("actual session booked" + actualSessionBooked);
		Assert.assertEquals(actualSessionBooked, totalSession, "Failed to book Sessions");
		return this;
	}

	public BookSessionConfirmationPage getSessionDetails() {
		for (int i = 1; i <= actualSessionBooked; i++) {
			date = getText(getBookedSessionDate(i));
			time = getText(getBookedSessiontime(i)).toLowerCase();
			details[i] = time + " IST " + date + ".";
			System.out.println("The details are" + details[i]);
		}
		return this;
	}

	public String sessionFirstDateDetails() {
		shortWait();
		date = getText(getBookedSessionDate(1));
		return date;
	}

	public String sessionFirstTimeDetails() {
		shortWait();
		time = getText(getBookedSessiontime(1));
		return time;
	}

	public String sessionSecondDateDetails() {
		shortWait();
		date = getText(getBookedSessionDate(2));
		return date;
	}

	public String sessionSecondTimeDetails() {
		shortWait();
		time = getText(getBookedSessiontime(2));
		return time;
	}

	public String sessionOnlyDate() {
		date = getText(getBookedSessionDate(1));
		String odate = date.substring(0, 2);
		String regex = "^0+(?!$)";
		odate = odate.replaceAll(regex, "");
		return odate;
	}

	public String sessionOnlyDateSecond() {
		date = getText(getBookedSessionDate(2));
		String odate = date.substring(0, 2);
		String regex = "^0+(?!$)";
		odate = odate.replaceAll(regex, "");
		return odate;
	}

	public List<String> sessionDetails() {
		for (int i = 1; i <= actualSessionBooked; i++) {
			date = getText(getBookedSessionDate(i));
			time = getText(getBookedSessiontime(i));
			details[i] = time + " IST " + date + ".";
			System.out.println("The details are" + details[i]);
			if (i == 1) {
				sessionDetails.add(date);
				sessionDetails.add(time);
			}
		}
		return sessionDetails;
	}

}
