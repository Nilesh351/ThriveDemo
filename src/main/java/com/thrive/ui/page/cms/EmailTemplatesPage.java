package com.thrive.ui.page.cms;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class EmailTemplatesPage extends UserManagementCommonPage {

	AlertsAndMessagesPage alertsAndMessagesPage;

	private By getEmailTemplates() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath("//span[contains(text(),'Email Templates')]");
		} else {
			return By.xpath("//span[contains(text(),'Email Templates')]");
		}
	}

	private By getTemplateEditButton(int i) {
		if (Config.getTestPlatform().contains("thrive")) {
			return By.xpath(".//p-tabpanel[@header='Email Templates']//div[contains(@class,'scrollable-body')]//tr[" + i
					+ "]//td[3]//button");
		} else {
			if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath(".//p-tabpanel[@header='Email Templates']//div[contains(@class,'scrollable-body')]//tr["
						+ i + "]//td[4]//button");
			} else {
				return By.xpath(".//p-tabpanel[@header='Email Templates']//div[contains(@class,'scrollable-body')]//tr["
						+ i + "]//td[3]//button");
			}
		}
	}

	private By getTemplateSaveButton() {
		if (Config.getTestPlatform().contains("thrive")) {
			return By.xpath(getXpathByText("//span[contains(text(),'admin.email_template.save')]"));
		} else {
			if (Config.getLocalizationLanguage().contains("en")) {
				return By.xpath("//span[contains(text(),'Save')]");
			} else {
				return By.xpath("//span[contains(text(),'Sauvegarder')]");
			}
		}
	}

	private By getDBKey(int i) {
		return By.xpath(".//p-tabpanel[@header='Email Templates']//div[contains(@class,'scrollable-body')]//tr[" + i
				+ "]//td[2]//span");
	}

	private By getCancleButton() {
		if (Config.getTestPlatform().contains("thrive")) {
			return By.xpath(getXpathByText("//span[contains(text(),'admin.email_template.cancel')]"));
		} else {
			return By.xpath("//span[contains(text(),'Cancel')]");
		}
	}

	private By getDisabledNextButton() {
		return By.xpath(
				"//p-tabpanel[@header='Email Templates']//a[contains(@class,'next ui-paginator-element ui-state-default ui-corner-all ui-state-disabled')]");
	}

	private By getDataCountDropdownLink() {
		return By.xpath(
				".//p-tabpanel[@header='Email Templates']//div[contains(@class,'ui-dropdown-label-container')]//following-sibling::div[@role='button']");
	}

	private By getDataCountLink(int count) {
		return By.xpath("//p-dropdownitem//li//span[(text()='" + count + "')]");
	}

	private By getNextPageButton() {
		return By.xpath(
				".//p-tabpanel[@header='Email Templates']//a[contains(@class,'ui-paginator-next ui-paginator-element ui-state-default ui-corner-all')]");
	}

	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class, 'toast-close-button')]");
	}

	public UserManagementCommonPage selectPaginationDropdownValue(int val) {
		int counter = 0;
		boolean flag = true;
		do {
			try {
				click(getDataCountDropdownLink());
				shortWait();
				click(getDataCountLink(val));
			} catch (StaleElementReferenceException e) {
				flag = false;
				shortWait();
			}
			counter++;
		} while (!flag && counter <= 15);
		return this;
	}

	public EmailTemplatesPage clickEmailTemplate() {
		LOGGER.info("Clicking Email Templates edit button");
		click(getEmailTemplates());
		return this;
	}

	public List<String> searchErrorMessage(int paginationValue) {
		LOGGER.info("Searching error message in email templates");
		int errorCount=0;
		int templtesCoount = 0;
		selectPaginationDropdownValue(paginationValue);

		List<String> errorTemplateDBKeys = new ArrayList<String>();

		exitDoLoop: do {
			
			for (int j = 1; j <= paginationValue; j++) {
				templtesCoount++;
				click(getTemplateEditButton(j));
				click(getTemplateSaveButton());
				shortWait();
				alertsAndMessagesPage = new AlertsAndMessagesPage();
				if (isElementVisible(getToasterCloseButton())) {
					alertsAndMessagesPage.closeToasterAlert();
				} else {
					errorCount++;
					System.out.println(j);
					click(getCancleButton());
					shortWait();
					waitUntilElementIsVisible(getDBKey(j));
					String dbKey = getText(getDBKey(j));
					errorTemplateDBKeys.add(dbKey);
					System.out.println(dbKey);
				}
				if (!isElementVisible(getTemplateEditButton(j + 1))) {
					break exitDoLoop;
				}
			}
			if(isElementVisible(getNextPageButton())) {
				click(getNextPageButton());
			} else {
				break exitDoLoop;
			}
		} while (true);
		System.out.println("Total number of templdates containing erros : "+errorCount);
		System.out.println("Total number of templdates : "+templtesCoount);
		return errorTemplateDBKeys;
	}

	public void printDBKeys(List<String> dbKeys) {
		LOGGER.info("Pringitng DB keys");
		for (String key : dbKeys) {
			System.out.println(key);
		}
	}

}
