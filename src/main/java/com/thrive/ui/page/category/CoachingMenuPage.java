package com.thrive.ui.page.category;

import org.openqa.selenium.By;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;

public class CoachingMenuPage extends ThriveHeaderMenuPage {
	private By getManageCategoriesLink() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath("//li[contains(text(),'MANAGE CATEGORIES')]");
		} else {
			return By.xpath("//li[contains(text(),'GÉRER LES CATÉGORIES DE COACHS')]");
		}
	}

	private By getBookSessionLink() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(".//li[text()='shared.header.book_a_session']"));
		} else {
			return By.xpath(getXpathByText("//li[contains(text(),'private.profile.coach.session_availability')]//preceding-sibling::li[3]"));
		}
	}

	private By getSessionsLink() {
		return By.xpath("//ul//li[(contains(text(),'SESSIONS')) and not(contains(@class,'hidden'))]");
	}

	private By getSessionsLinkFeatureFlag() {
		return By.xpath(getXpathByText(
				".//li[@class='session-li tab-focus' and normalize-space(text())='shared.header.sessions']"));
	}

	private By getConfigureCategoryLink() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(getXpathByText(".//li[text()='private.profile.enterprise.categories_list']"));
		} else {
			return By.xpath("//li[contains(text(),'CONFIGURER LES CATÉGORIES')]");
		}
	}

	public ManageCategoryPage clickManageCategories() {
		LOGGER.info("Clicking Manage Categories link");
		if (Config.featureFlagStatus()) {
			clickCoachingButton();
		} else {
			clickMySessions();
		}
		click(getManageCategoriesLink());
		return new ManageCategoryPage();
	}

	public ManageCategoryPage clickConfigureCategories() {
		LOGGER.info("Clicking Configure Categories link");
		click(getConfigureCategoryLink());
		return new ManageCategoryPage();
	}

	private By getResumeJourneyButtonEADashboard() {
		return By.xpath(getXpathByText("//span[contains(text(),'private.clientdashboard.coaching')]//ancestor::div[contains(@class,'align-items')]//following-sibling::div//button[contains(text(),'private.clientdashboard.button.resume_journey')]"));
	}

	private By getBookASessionButton() {
		return By.xpath(getXpathByText(".//button[text()='private.coach.book_a_session']"));
	}

	public BookSessionViewPage clickBookSessionLink() {
		LOGGER.info("Clicking Book A Session link");
		if (Config.featureFlagStatus()) {
			click(getResumeJourneyButtonEADashboard());
			click(getBookASessionButton());
		} else {
			clickCoachingButton();
			click(getBookSessionLink());
		}
		return new BookSessionViewPage();
	}

	public SessionsPage clickSessions() {
		LOGGER.info("Clicking Sessions link");
		if (Config.featureFlagStatus()) {
			clickMySessions();
			click(getSessionsLinkFeatureFlag());
		} else {
			waitUntilElementIsClickable(5, getSessionsLink());
			click(getSessionsLink());
		}
		return new SessionsPage();
	}
}