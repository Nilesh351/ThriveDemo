package com.thrive.ui.page.book_session;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class SessionDeatilsPage extends UserManagementCommonPage{
	
	private By getRescheduleLink() {
		return By.xpath(getXpathByText(".//div[@class='d-none d-md-flex reschedule-bx ng-star-inserted']//a[text()='private.detail.reschedule']"));
	}
	
	private By getCancelLink() {
		return By.xpath(getXpathByText(".//div[@class='d-none d-md-flex reschedule-bx ng-star-inserted']//a[text()='private.detail.cancel']"));
	}
	
	private By getDaysCount() {
		return By.xpath(getXpathByText(".//p[contains(text(),'private.preworkshop.pre_meeting_day')]/../h3"));
	}
	
	private By getHoursCount() {
		return By.xpath(getXpathByText(".//p[contains(text(),'private.preworkshop.pre_meeting_hours')]/../h3"));
	}
	
	private By getMinutesCount() {
		return By.xpath(getXpathByText(".//p[text()='private.preworkshop.pre_meeting_minutes']/../h3"));
	}
	
	private By getSeesionNotesTextarea() {
		return By.xpath(".//textarea[contains(@class,'ng-pristine ng-valid ng-touched') and @placeholder='Please enter your notes here.']");
	}
	
	
	
	public SessionDeatilsPage clickRescheduleLink() {
		LOGGER.info("Clicking Reschedule link");
		shortWait();
		click(getRescheduleLink());
		return this;
	}
	
	public AlertsAndMessagesPage clickCancelLink() {
		LOGGER.info("Clicking Cancel link");
		mediumWait();
		click(getCancelLink());
		return new AlertsAndMessagesPage();
	}
	
	public int hoursRemaining() {
		LOGGER.info("hours ramianing to join session");
		int totalHours = 0;

		mediumWait();
		String days = getText(getDaysCount());
		String hours = getText(getHoursCount());
		int rdays = Integer.parseInt(days);
		int rhours = Integer.parseInt(hours);
		int dayToHours = rdays * 24;
		totalHours = dayToHours + rhours;
		return totalHours;
	}
	
	public int creditConsumptionCheck(int current,int time) {
		LOGGER.info("Validate credit consumption with hours");
		
		if(Config.getEnv().equalsIgnoreCase("thrivestage")) {
			if (time >= 48) {
				current = current - 0;
			} else {
				current = current - 1;
			}
		} else {
			if (time >= 28) {
				current = current - 0;
			} else {
				current = current - 1;
			}
		}
		
		return current;
	}


}
