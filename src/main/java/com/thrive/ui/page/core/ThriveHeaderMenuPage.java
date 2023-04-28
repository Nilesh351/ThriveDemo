package com.thrive.ui.page.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.cms.EmailTemplatesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.dashboard.DashBoardPage;
import com.thrive.ui.page.dashboard.InsightsPage;
import com.thrive.ui.page.password_policy.ChangePasswordPage;
import com.thrive.ui.page.register.AdministratorDetailsPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

public class ThriveHeaderMenuPage extends UserManagementCommonPage {

	private By getCoachingLink() {
			if(Config.featureFlagStatus()) {
				return By.xpath(getXpathByText(".//span[text()='shared.header.coaching']"));
			} else {
				return By.xpath(getXpathByText(".//a[text()='shared.header.coaching']"));
			}
	}

	private By getMySessionsLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(".//span[text()='shared.breadcrumb.my_sessions']"));
		} else {
			return By.xpath(getXpathByText(".//a[text()='shared.header.coaching']"));
		}
	}

	private By getSessionsLink() {
		return By.xpath("//ul//li[(contains(text(),'SESSIONS')) and not(contains(@class,'hidden'))]");
	}

	private By getPreCallTestButton() {
		return By.xpath(".//button[@routerlink ='/precall-test']");
	}

	private By getThriveLogo() {
		return By.xpath(".//img[@alt='My Thrive']");
	}

	private By getRMLogo() {
		return By.xpath(".//img[contains(@alt,'Right Coach')]");
	}

	private By getUserManagementLink() {
		return By.xpath(getXpathByText(".//li[@class='header-list-1']/a[text()='shared.header.users']"));
	}

	private By getUserManagementLinkForFatureFlag() {
		return By.xpath(getXpathByText(".//li[@class='header-list-1 dropdown main-dropdown']//span[text()='shared.header.users']"));
	}

	private By getDashboardLink() {
		return By.xpath(".//li[@class='header-list-1']/a[contains(@routerlink,'dashboard')]");
	}

	private By getDashboardFeatureFlag() {
		return By.xpath("//span[contains(text(),'DASHBOARD')]");
	}

	private By getInsightsLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(".//li[@class='header-list d-none d-lg-flex ng-star-inserted'][1]");
		}else {
			return By.xpath(".//li[@class='header-list-1']/a[contains(@routerlink,'platform-insights')]");
		}
		
	}

	private By getProfileCircleImg() {
		return By.xpath(".//div[contains(@class,'avtara-circle')]");
	}

	private By getLogoutLink() {
		return By.xpath(getXpathByText(".//u[text()='shared.header.logout']"));
	}

	private By getChangePasswordLink() {
		return By.xpath(getXpathByText(".//u[text()='register.client.step1.change_password']"));
	}

	private By getLogoutButton() {
		return By.xpath(getXpathByText(".//span[text()='shared.header.logout']"));
	}

	private By getClientMyprofileLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(
					getXpathByText(".//u[text()='shared.header.my_profile']"));
		}else {
			return By.xpath(getXpathByText(".//div[@routerlink='/profile/client']/u[text()='shared.header.my_profile']"));
		}
		
	}

	private By getAccountManagerMyprofileLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(
					getXpathByText(".//u[text()='shared.header.my_profile']"));
		}else {
			return By.xpath(
					getXpathByText(".//div[@routerlink='/profile/account-manager']/u[text()='shared.header.my_profile']"));
		}
		
	}

	private By getSuperadminMyProfileLink() {
		return By.xpath(getXpathByText(".//div[@routerlink='/profile/admin']/u[text()='shared.header.my_profile']"));
	}

	private By getCoachMyprofileLink() {
		if(Config.featureFlagStatus()) {
			return By.xpath(
					getXpathByText(".//u[text()='shared.header.my_profile']"));
		}else {
			return By.xpath(getXpathByText(".//div[@routerlink='/profile/coach']/u[text()='shared.header.my_profile']"));
		}
		
	}

	private By getAvailableCredits() {
		if(Config.featureFlagStatus()) {
			return By.xpath("//div[contains(@class,'credit-menu align')]");
		} else {
		    return By.xpath("//a[contains(@class,'credits main-heading')]//b");
		}
	}

	private By getMyCreditFeatureFlag() {
		return By.xpath(".//div[contains(@class,'credit-menu align-self-center')]/div");
	}

	private By getLocalTime() {
		return By.xpath(getXpathByText(".//div[contains(text(),'shared.header.current_time')]"));
	}

	private By getBookASessionButton() {
		return By.xpath("//div[@class='btn-div pt-0 pb-0 pr-0 booksession-bx']//a[contains(text(),'Book a session')]");
	}

	private By getBookASessionTab() {
			return By.xpath(getXpathByText("//li[contains(text(),'private.my_sessions.book_a_session')]"));
	}

	private By getJoinSessionLink() {
		return By.xpath(
				getXpathByText("//tr[@class='row-card bg-white border-left ng-star-inserted']//p//button[contains(text(),'private.my_sessions.join')]"));
	}

	private By getCoachesLink() {
		return By.xpath(getXpathByText(".//li[@class='header-list-1']/a[text()='admin.user_management.coaches_title']"));
	}
	
	private By getResumeJourneyButtonEADashboard() {
		return By.xpath(getXpathByText(
				"//span[contains(text(),'private.clientdashboard.coaching')]/../..//following-sibling::div//button[contains(text(),'private.clientdashboard.button.resume_journey')]"));
	}

	private By getSettingsHeader() {
		return By.xpath("//ul//li[3]//div[contains(@class,'menu-hover')]");
	}

	private By getUsersOption() {
		return By.xpath(getXpathByText("//u[contains(text(),'shared.coaching_program.submenu.drop_users')]"));
	}
	
	private By getCmsLink() {
		return By.xpath(getXpathByText("//u[contains(text(),'shared.header.cms_link')]"));
	}
	
	private By profileImageBox() {
		return By.xpath("//div[contains(@class,'profile-image-box') and not (contains(@class,'dropdown-item'))]");
	}

	public ThriveHeaderMenuPage clickSettingsHeader() {
		LOGGER.info("Clicking on Setting icon in header");
		click(getSettingsHeader());
		return this;
	}

	public UsersPage clickUsers() {
		LOGGER.info("Clicking on Users option in settings");
		click(getUsersOption());
		return new UsersPage();
	}

	public ThriveHeaderMenuPage validateLoginSuccessful() {

		String message = "Login Unsuccessful";

		LOGGER.info("Validate Login Successful");
		if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.THRIVE)) {
			int counter = 1;
			boolean flag = false;
			do {
				try {
					shortWait();
					Assert.assertTrue(isElementVisible(getThriveLogo()), message);
				} catch (Exception e) {
					if (e.getCause().getMessage().contains("stale element")) {
						flag = true;
						shortWait();
					}
				}
				counter++;
			} while (flag && counter <= 5);
		} else if (Config.getTestPlatform().equalsIgnoreCase(TestPlatform.RM)) {
			int counter = 1;
			boolean flag = false;
			do {
				try {
					shortWait();
					Assert.assertTrue(isElementVisible(getRMLogo()), message);
				} catch (Exception e) {
					if (e.getCause().getMessage().contains("stale element")) {
						flag = true;
						shortWait();
					}
				}
				counter++;
			} while (flag && counter <= 5);
		} else {
			Assert.fail(message);
		}

		return this;
	}

	public ThriveHeaderMenuPage validatesuccessfulLoginRm() {
		LOGGER.info("Validate Successful Login");
		Assert.assertTrue(isElementVisible(getLocalTime()), "Login Unsuccessful");
		return this;
	}

	public ThriveLoginPage logout() {
		LOGGER.info("Clicking logout link ");
		click(getProfileCircleImg());
		if (Config.featureFlagStatus()) {
			click(getLogoutButton());
		} else {
			click(getLogoutLink());
		}

		return new ThriveLoginPage();
	}

	public ChangePasswordPage clickChangePassword() {
		LOGGER.info("Clicking change Password link ");
		int counter = 1;
		boolean flag = false;
		do {
			try {
				shortWait();
				click(getProfileCircleImg());
			} catch (Exception e) {
				if (e.getCause().getMessage().contains("stale element")) {
					flag = true;
					shortWait();
				}
			}
			counter++;
		} while (flag && counter <= 5);
		click(getChangePasswordLink());
		return new ChangePasswordPage();
	}

	public RegisterClientInformationPage clickClientMyprofileLink() {
		LOGGER.info("Clicking client MyProfile link");
		click(getProfileCircleImg());
		click(getClientMyprofileLink());
		return new RegisterClientInformationPage();
	}

	public RegisterAccountMangerPersonalDetailsPage clickAccountManagerMyprofileLink() {
		LOGGER.info("Clicking Account manager MyProfile link");
		click(getProfileCircleImg());
		click(getAccountManagerMyprofileLink());
		return new RegisterAccountMangerPersonalDetailsPage();
	}

	public AdministratorDetailsPage clickSuperadminProfileLink() {
		LOGGER.info("Clicking Superadmin MyProfile link");
		click(getProfileCircleImg());
		click(getSuperadminMyProfileLink());
		return new AdministratorDetailsPage();
	}

	public RegisterCoachPersonalDetailsPage clickCoachesMyprofileLink() {
		LOGGER.info("Clicking Coaches MyProfile link");
		click(getProfileCircleImg());
		click(getCoachMyprofileLink());
		return new RegisterCoachPersonalDetailsPage();
	}

	public UserManagementPage clickUserManagementLink() {
		LOGGER.info("Clicking user management link ");
		shortWait();
		if (Config.featureFlagStatus()) {
			click(getUserManagementLinkForFatureFlag());
		} else {
			click(getUserManagementLink());
		}
		return new UserManagementPage();
	}


	public ThriveHeaderMenuPage clickResumeJourney() {
		LOGGER.info("Clicking resume journey button");
		click(getResumeJourneyButtonEADashboard());
		return this;
	}

	public CoachingMenuPage clickMySessions() {
		LOGGER.info("Clicking My sessions");
		waitUntilElementIsPresent(10,getMySessionsLink());
		click(getMySessionsLink());
		return new CoachingMenuPage();
	}

	private By getSettingsIcon() {
		return By.xpath(".//li[@class='header-list d-none d-lg-flex ng-star-inserted'][2]/div");
	}

	public ThriveHeaderMenuPage clickSettingIcon() {
		LOGGER.info("Click on Setting Icon");
		click(getSettingsIcon());
		return this;
	}

	public BookSessionViewPage clickCoaches() {
		LOGGER.info("Clicking Coaches");
		click(getCoachesLink());
		return new BookSessionViewPage();
	}

	public DashBoardPage clickDashboard() {
		LOGGER.info("Clicking Dashboard");
		shortWait();
		if (Config.featureFlagStatus()) {
			click(getDashboardFeatureFlag());
		} else {
			click(getDashboardLink());
		}
		return new DashBoardPage();
	}

	public InsightsPage clickInsights() {
		LOGGER.info("Clicking Insights link");
		click(getInsightsLink());
		return new InsightsPage();
	}

	public ThriveHeaderMenuPage validateDashboardNotPresentCoach() {
		LOGGER.info("validate dashboard tab not present for coach");
		Assert.assertTrue(isElementNotVisible(getDashboardLink()));
		return this;
	}

	public int AvailablCredits() {
		LOGGER.info("Get Available Credits");
		shortWait();
		String credits;
		String currentCreditc = "";
		if(Config.featureFlagStatus()) {
			shortWait();
			credits = getText(getMyCreditFeatureFlag());
		}else {
			credits = getText(getAvailableCredits());
		}
		
		if (Config.getLocalizationLanguage().contains("fr")) {
			currentCreditc = credits.split(" ")[3];
		} else {
			currentCreditc = credits.split(" ")[2];
		}
		int expectedCredit = Integer.parseInt(currentCreditc);
		return expectedCredit;
	}

	public ThriveHeaderMenuPage ValidateCreditsAssignedReflectedAtClient(int current, int updated, String credits) {
		LOGGER.info("Validate Credits Assigned reflected");
		int amountCredit = Integer.parseInt(credits);
		int actual = current + amountCredit;
		Assert.assertEquals(actual, updated, "credits are not matching");
		return this;
	}

	public ThriveHeaderMenuPage ValidateCreditsRemovedReflectedClient(int current, int updated, String credit) {
		LOGGER.info("Validate Credits Removed reflected");
		int amountCredit = Integer.parseInt(credit);
		int actualCredit = current - amountCredit;
		Assert.assertEquals(actualCredit, updated, "credits are not matching");
		return this;
	}

	public int availableCredits() {
		LOGGER.info("Get Available credits");
		mediumWait();
		String credits = getText(getAvailableCredits());
		String currentCreditc = credits.split(":")[1].trim();
		int expectedCredit = Integer.parseInt(currentCreditc);
		return expectedCredit;
	}

	public int creditsUsedForBookingCount(int available, int updated) {
		int credit = available - updated;
		return credit;
	}

	public ThriveHeaderMenuPage validateCreditConsumption(int actual, int expected) {
		LOGGER.info("Validate credit consumption");
		Assert.assertEquals(actual, expected, "credits are not matching");
		return this;
	}

	public ThriveHeaderMenuPage validateTotalCreditConsumption(int actual, int expected) {
		LOGGER.info("Validate credit consumption with hours");
		Assert.assertEquals(actual, expected, "credits are not matching");
		return this;
	}

	public WebElement getUserManagementElement() {
		return getDriver().findElement(getUserManagementLink());
	}

	public WebElement getProfileImageBox() {
		return findElement(profileImageBox());
	}
	
	public WebElement getUserManagementHeaderLink() {
		return findElement(getUserManagementLink());
	}
	
	public WebElement getUsersOptionLink() {
		return findElement(getUsersOption());
	}
	
	public ThriveHeaderMenuPage clickUserManagementTab() {
		LOGGER.info("Clicking user management link ");
		click(getUserManagementLink());
		return this;
	}

	public ThriveHeaderMenuPage clickBookASessionLink() {
		LOGGER.info("Clicking on BookASessionLink");
		click(getBookASessionButton());
		return this;
	}

	public ThriveHeaderMenuPage ValidateBookASessionPage() {
		LOGGER.info("Validating BookASessionTab");
		Assert.assertEquals(getText(getBookASessionTab()), "BOOK A SESSION");
		return this;
	}

	public ThriveHeaderMenuPage clickCoachingButton() {
		LOGGER.info("Clicking Coaching");
		shortWait();
		click(getCoachingLink());
		return this;
	}

	public SessionsPage clickSessions() {
		LOGGER.info("Clickng Sessions link");
		click(getSessionsLink());
		return new SessionsPage();
	}


	public SessionsPage clickJoinSessionButton() {
		LOGGER.info("Click joinSessionButton");
		click(getJoinSessionLink());
		return new SessionsPage();
	}

	public EmailTemplatesPage clickCMS() {
		LOGGER.info("Clicking CMS ");
		click(getProfileCircleImg());
		click(getCmsLink());
		return new EmailTemplatesPage();
	}
	
	public UserManagementPage navigateToUsers() {
		LOGGER.info("Navigating to users");
		if(Config.featureFlagStatus()) {
			clickSettingIcon().clickUsers().clickUsersDropdown();
		} else {
			clickUserManagementLink();
		}
		return new UserManagementPage();
	}
}