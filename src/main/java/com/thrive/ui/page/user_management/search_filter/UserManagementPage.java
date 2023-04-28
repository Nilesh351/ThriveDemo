package com.thrive.ui.page.user_management.search_filter;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.enterprise.EnterpriseEmployeePage;

public class UserManagementPage extends BaseTestPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();

	private By getUsersLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(".//a[contains(text(),'admin.user_management.users_title')]"));
		} else {
			return By.xpath(getXpathByText(".//li[contains(text(),'admin.user_management.users_title')]"));
		}
	}
	
	private By getRegisteredUsers() {
		return By.xpath(getXpathByText("//a[contains(text(),'shared.coaching_program.submenu.users')]/..//following-sibling::div//li[text()='shared.coaching_program.submenu.all_users']"));
	}
	
	private By getUserManagementMenuListLink() {
		return By.xpath("//ul[@class='ul-menu']");
	}

	private By getEnterprisesLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(
					"//ul[contains(@class,'display-block')]//li//a[text()='admin.user_management.enterprises_title']"));
		} else {
			return By.xpath(getXpathByText(".//li[contains(text(),'admin.user_management.enterprises_title')]"));
		}

	}

	private By getEnterprisesLinkAM() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(
					".//ul[@class='ul-menu dropdown-menu menu-poup display-block']//a[text()='admin.user_management.enterprises_title']"));
		} else {
			return By.xpath(getXpathByText(".//li[contains(text(),'admin.user_management.enterprises_title')]"));
		}

	}

	private By getProfileEnterprisesLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(
					".//li[@class='header-list-1 dropdown main-dropdown active-menu']//a[text()='admin.user_management.enterprises_title']"));
		} else {
			return By.xpath(getXpathByText(".//li[contains(text(),'admin.user_management.enterprises_title')]"));
		}
	}

	private By getCoachesLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(
					".//li[@class='header-list-1 dropdown main-dropdown']//a[text()='admin.user_management.coaches_title']"));
		} else {
			return By.xpath(getXpathByText("//li[contains(text(),'admin.user_management.coaches_title')]"));
		}

	}

	private By geIntCoachesLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText("//a[contains(text(),'admin.user_management.internal_coaches')]"));
		} else {
			return By.xpath(getXpathByText("//li[contains(text(),'admin.user_management.internal_coaches')]"));
		}

	}     
	
	private By getRegisteredIntCoaches() {
		return By.xpath(getXpathByText("//a[contains(text(),'shared.coaching_program.submenu.internal_coaches')]/..//following-sibling::div//li[contains(text(),'shared.coaching_program.submenu.all_internal_coaches')]"));
	}  

	private By getClientsLink() {
		if (Config.getLocalizationLanguage().contains("en")) {
			if (Config.featureFlagStatus()) {
				return By.xpath(getXpathByText(
						".//li[contains(@class,'header-list-1 dropdown main-dropdown')]//a[contains(text(),'admin.user_management.clients_title')]"));
			} else {
				return By.xpath(getXpathByText("//li[contains(text(),'admin.user_management.clients_title')]"));
			}

		} else {
			return By.xpath("//app-user-management//ul//li[5]");
		}
	}

	private By getAccountManagersLink() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText(
					".//li[@class='header-list-1 dropdown main-dropdown']//a[text()='admin.user_management.account_manager']"));
		} else {
			return By.xpath(getXpathByText(".//li[text()='admin.user_management.account_manager']"));
		}

	}

	private By getEmployeeLink() {
		if (Config.getLocalizationLanguage().contains("en")) {
			if (Config.featureFlagStatus()) {
				return By.xpath(getXpathByText("//a[contains(text(),'shared.coaching_program.submenu.employees')]"));
			} else {
				return By.xpath(
						getXpathByText("//li[normalize-space(text())='shared.coaching_program.submenu.employees']"));
			}
		} else {
			return By.xpath("//li[contains(text(),'EMPLOYÉS')]");
		}
	}

	private By geIntCoachesLinkEA() {
		if (Config.featureFlagStatus()) {
			return By.xpath(getXpathByText("//a[contains(text(),'shared.coaching_program.submenu.internal_coaches')]"));
		} else {
			return By.xpath(getXpathByText("//li[contains(text(),'admin.user_management.internal_coaches')]"));
		}

	}

	private By getRegisteredEmployees() {
		return By.xpath(getXpathByText(
				"//a[contains(text(),'shared.coaching_program.submenu.employees')]/..//following-sibling::div//li[contains(text(),'shared.coaching_program.submenu.all_employees')]"));
	}

	private By getRegisteredInternalCoaches() {
		return By.xpath(getXpathByText(
				"//a[contains(text(),'shared.coaching_program.submenu.internal_coaches')]/..//following-sibling::div//li[text()='shared.coaching_program.submenu.all_internal_coaches']"));
	}

	private By getRegisterdInternalCoachesLink() {
		return By.xpath(getXpathByText("//a[contains(text(),'admin.user_management.internal_coaches')]/../..//li[contains(text(),'shared.coaching_program.submenu.all_internal_coaches')]"));
	}
	
	private By getEnterpriseDetails() {
		return By.xpath(getXpathByText(".//li[text()='admin.user_management.enterprise_details']"));
	}
	
	public UserManagementPage clickEnterpriseDetails() {
		click(getEnterpriseDetails());
		return this;
	}

	public WebElement getEnterpriseElement() {
		return getDriver().findElement(getEnterprisesLink());
	}

	public WebElement getAccountManagerElement() {
		return getDriver().findElement(getAccountManagersLink());
	}

	public WebElement getEmployeeElement() {
		return getDriver().findElement(getEmployeeLink());
	}

	public WebElement getClientsElement() {
		return getDriver().findElement(getClientsLink());
	}

	public WebElement getCoachesElement() {
		return getDriver().findElement(getCoachesLink());
	}

	public EnterprisesPage clickEnterprisesMenu() {
		LOGGER.info("Clicking Enterprises link");
		click(getEnterprisesLink());
		return new EnterprisesPage();
	}

	public EnterprisesPage clickEnterprisesMenuAM() {
		LOGGER.info("Clicking Enterprises link for Account manager");
		click(getEnterprisesLinkAM());
		return new EnterprisesPage();
	}

	public EnterprisesPage clickProfileEnterpriseMenu() {
		LOGGER.info("Clicking Enterprises link form enterprise profile");
		click(getProfileEnterprisesLink());
		return new EnterprisesPage();
	}

	public EnterpriseEmployeePage clickEmployee() {
		LOGGER.info("Clicking Employee link");
		click(getEmployeeLink());
		if (Config.featureFlagStatus()) {
			click(getRegisteredEmployees());
		}
		return new EnterpriseEmployeePage();
	}

	public UserManagementPage validateEnterrpisesLinkNotVisible() {
		LOGGER.info("Validate enterrpises link is not visible");
		Assert.assertTrue(isElementNotVisible(getEnterprisesLink()), "Enterprise link is visible");
		return this;
	}

	public List<WebElement> getUserManagementMenuList() {
		return getDriver().findElements(getUserManagementMenuListLink());
	}

	public EnterprisesPage enterEnterprisesPage(String user) {
		try {
			if (getEnterpriseElement().isDisplayed()) {
				shortWait();
				clickEnterprisesMenu();
			}
		} catch (Exception e) {
			Assert.assertTrue(false, "The " + user + " user don't have access to invite global coach.");
		}
		shortWait();
		Assert.assertTrue(getDriver().getTitle().contains("Enterprises"),
				"The " + user + " user don't have access to invite Enterprise Page.");
		return new EnterprisesPage();
	}

	public UserManagementPage validateAccountManagerLinkNotVisible() {
		LOGGER.info("Validate Account Manager link is not visible");
		Assert.assertTrue(isElementNotVisible((getAccountManagersLink())), "Account Manager link is visible");
		return this;
	}

	public UserManagementPage validateEnterpisesElementIsVisible() {
		LOGGER.info("Validate enterrpises link is not visible");
		Assert.assertTrue(isElementVisible(getEnterprisesLink()), "Enterprise link is visible");
		return this;
	}

	public UserManagementPage validateUsersElementVisible() {
		LOGGER.info("Validate enterrpises link is visible");
		Assert.assertTrue(isElementVisible(getUsersLink()), "Users link is not visible");
		return this;
	}

	public UserManagementPage validateUsersElementVisibleToEa() {
		LOGGER.info("Validate enterrpises link is not visible");
		Assert.assertTrue(isElementVisible(getUsersLink()), "Users link is not visible");
		return this;
	}

	public UserManagementPage validateUsersElementNotVisibleToAm() {
		LOGGER.info("Validate Users link is not visible");
		Assert.assertTrue(isElementNotVisible(getUsersLink()), "Users link is not visible");
		return this;
	}

	public UserManagementPage validateUsersElementNotVisibleToClient() {
		LOGGER.info("Validate Users link is not visible");
		Assert.assertTrue(isElementNotVisible(getUsersLink()), "Users link is not visible");
		return this;
	}

	public UserManagementPage validateGlobalCoachElementVisible() {
		LOGGER.info("Validate Global coach link is visible");
		Assert.assertTrue(isElementVisible(getCoachesLink()), "Coaches element is not visible");
		return this;
	}

	public UserManagementPage validateGlobalCoachElementIsNotVisible() {
		LOGGER.info("Validate Global coach link is not visible");
		Assert.assertTrue(isElementNotVisible(getCoachesLink()), "Coaches element is visible");
		return this;
	}

	public UserManagementPage validateInternalCoachElementVisible() {
		LOGGER.info("Validate Internal coach link is visible");
		Assert.assertTrue(isElementVisible(geIntCoachesLink()), "Internal Coaches element is not visible");
		return this;
	}

	public UserManagementPage validateInternalCoachElementIsNotVisible() {
		LOGGER.info("Validate Internal coach link is visible");
		Assert.assertTrue(isElementNotVisible(geIntCoachesLink()), "Internal Coaches element is visible");
		return this;
	}

	public UserManagementPage validateClientElementVisible() {
		LOGGER.info("Validate Client link is visible");
		Assert.assertTrue(isElementVisible(getClientsLink()), "Client element is not visible");
		return this;
	}

	public UserManagementPage validateClientElementIsNotVisible() {
		LOGGER.info("Validate Client link is not visible");
		Assert.assertTrue(isElementNotVisible(getClientsLink()), "Client element is visible");
		return this;
	}

	public UserManagementPage validateAccountManagerElementVisible() {
		LOGGER.info("Validate Account manager link is visible");
		Assert.assertTrue(isElementVisible(getAccountManagersLink()), "Account manager element is not visible");
		return this;
	}

	public UserManagementPage validateAccountManagerElementIsNotVisible() {
		LOGGER.info("Validate Account manager link is not visible");
		Assert.assertTrue(isElementNotVisible(getAccountManagersLink()), "Account manager element is visible");
		return this;
	}

	public UserManagementPage validateEmployeeElementVisible() {
		LOGGER.info("Validate Employee link is visible");
		Assert.assertTrue(isElementVisible(getEmployeeElement()), "Employee element is not visible");
		return this;
	}

	public UserManagementPage validateEmployeeElementIsNotVisible() {
		LOGGER.info("Validate Employee link is visible");
		Assert.assertTrue(isElementPresent(getEmployeeLink()), "Employee element is not visible");
		return this;
	}

	public UserManagementPage validateEnterpriseDetailsElementVisible() {
		LOGGER.info("Validate Employee link is visible");
		Assert.assertTrue(isElementVisible(getEnterpriseDetails()), "Enterprise details is not visible");
		return this;
	}

	public UserManagementPage validateEnterpriseDetailsElementIsNotVisible() {
		LOGGER.info("Validate Employee link is not visible");
		Assert.assertTrue(isElementNotVisible(getEnterpriseDetails()), "Enterprise details is visible");
		return this;
	}

	public UserManagementPage validateClientsLinkIsNotPresent() {
		LOGGER.info("Validate Clients link is not visible");
		Assert.assertTrue(isElementNotVisible(getClientsLink()), "Clients link is present");
		return this;
	}

	public UserManagementPage validateCoachesLinkIsNotVisible() {
		LOGGER.info("Validate Coaches link is not visible");
		Assert.assertTrue(isElementNotVisible((getCoachesLink())), "Coaches link is visible");

		return this;
	}

	public AccountManagersPage clickAccountManagersLink() {
		LOGGER.info("Clicking Account Managers link");
		click(getAccountManagersLink());
		return new AccountManagersPage();
	}

	public UsersPage clickUsers() {
		LOGGER.info("Clicking Users link");
		click(getUsersLink());
		if (Config.featureFlagStatus()) {
			click(getRegisteredUsers());
		} else {
			click(getUsersLink());
		}
		return new UsersPage();
	}

	public ClientsPage clickClientMenu() {
		LOGGER.info("Clicking Clients link");
		shortWait();
		click(getClientsLink());
		return new ClientsPage();
	}

	public GlobalCoachesPage clickCoachesLink() {
		LOGGER.info("Clicking Coaches link");
		click(getCoachesLink());
		return new GlobalCoachesPage();
	}

	public InternalCoachesPage clickInternalCoachesLinkEA() {
		LOGGER.info("Clicking Internal coaches link");
		click(geIntCoachesLinkEA());
		if (Config.featureFlagStatus()) {
			click(getRegisteredInternalCoaches());
		} else {
			click(geIntCoachesLink());
		}
		return new InternalCoachesPage();
	}
	
	public InternalCoachesPage clickInternalCoachesLink() {
		LOGGER.info("Clicking Internal coaches link");
		click(geIntCoachesLink());
		if(Config.featureFlagStatus()) {
		   click(getRegisteredInternalCoaches());
		}
		return new InternalCoachesPage();
	}
}