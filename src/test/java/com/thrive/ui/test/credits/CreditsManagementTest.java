package com.thrive.ui.test.credits;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DateTimeUtils;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.credits.ClientPersonalDetailsPage;
import com.thrive.ui.page.credits.ClientsCreditPage;
import com.thrive.ui.page.credits.RemoveCreditsPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;

public class CreditsManagementTest extends BaseTestPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	ClientsPage clientPage = new ClientsPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	RegisterEnterpriseDetailsPage enterpriseDetailsPage = new RegisterEnterpriseDetailsPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	RemoveCreditsPage removeCreditsPage = new RemoveCreditsPage();
	ClientPersonalDetailsPage clientPersonalDetailsPage = new ClientPersonalDetailsPage();
	ClientsCreditPage clientsCreditPage = new ClientsCreditPage();
	RegisterClientInformationPage registerClientInformationPage = new RegisterClientInformationPage();
	String creditsAmount = "10";
	String quantity = "1";
	String nextYearDate = DateTimeUtils.getNextYearDate();
	String allocateCreditToaster = ThriveAppSharedData.ALLOCATE_CREDIT_NOTIFICATION.getValue();
	String removeCreditToasterEA = ThriveAppSharedData.REMOVE_CREDIT_MESSAGE_EA.getValue();
	String removeCreditToaster = ThriveAppSharedData.REMOVE_CREDIT_NOTIFICATION.getValue();
	AllocateCreditsPage allocateCreditsPage = new AllocateCreditsPage();
	LoginDetails clientLoginDetails = new LoginDetails(clientUser, clientPassword);
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDeatails = new LoginDetails(eaMutableEmail, eaMutablePassword);
	LoginDetails amLoginDetails = new LoginDetails(accountManagerUser, accountManagerPassword);

	@Test(description = "Validate SA allocate credits to enterprise successfully")
	public void validateEnterpriseAllocateCredits() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "User search the enterprise and click on it");
		enterprisesPage.setSearch(eaMutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of Enterprise");
		int eaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to allocate credits");
		enterprisesPage.setSearch(eaMutableEmail);
		enterprisesPage.clickEnterprisesCheckbox(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "Superadmin selects allocate credits option from actions dropdown");
		userManagementCommonPage.selectAllocateCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides credits and date");
		allocateCreditsPage.setNumberOfCredits(creditsAmount);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on allocate credits button");
		allocateCreditsPage.clickAllocateCreditButton().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits assigned successfully to the enterprise");
		alertsAndMessagesPage.validateCreditAllocationToaster(getResultByKey(allocateCreditToaster))
				.closeToasterAlert();

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the enterprise profile");
		shortWait();
		enterprisesPage.setSearch(eaMutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaMutableEmail);
		int eaupdatedcredit = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		enterpriseDetailsPage.validateCreditAssignedRefelected(eaCredits, eaupdatedcredit, creditsAmount);

	}

	@Test(description = "Validate SA remove credits from enterprise")
	public void validateSARemoveCreditFromEnterprise() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "User search the enterprise and click on it");
		enterprisesPage.setSearch(eaMutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of Enterprise");
		int eaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();

		getExtentTestLogger().log(Status.PASS, "Superadmin click on the remove credits icon");
		enterpriseDetailsPage.clickRemoveCreditsIcon();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides no. of credits to remove");
		removeCreditsPage.setQuantityToDelete(quantity);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on Delete Quantity button");
		removeCreditsPage.clickDeleteQuantity().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits remove successfully from account maanger");
		alertsAndMessagesPage.validateToaster(getResultByKey(removeCreditToasterEA));

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the Account manager profile");
		shortWait();
		int eaupdatedcredit = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		enterpriseDetailsPage.validateCreditRemovedRefected(eaCredits, eaupdatedcredit, quantity);
	}

	@Test(description = "Validate SA Allocate Credits to Client")
	public void validateClientAllocateCreditsReflected() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "User search the client and click on it");
		userManagementCommonPage = clientPage.setSearch(clientUser);
		registerClientInformationPage = clientPage.clickClientNameLink(clientUser);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of client");
		clientsCreditPage = registerClientInformationPage.clickCredit();
		int curCredits = clientsCreditPage.ClientCreditCount();

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to allocate credits");
		clientPage.setSearch(clientUser);
		clientPage.clickClientCheckboxEmail(clientUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin select allocate credits option from actions dropdown");
		userManagementCommonPage.selectAllocateCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides credits and date data");
		allocateCreditsPage.setNumberOfCredits(creditsAmount).setExpiryDate(nextYearDate);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on allocate credits button");
		allocateCreditsPage.clickAllocateCreditButton().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits assigned successfully to the client");
		alertsAndMessagesPage.validateCreditAllocationToaster(getResultByKey(allocateCreditToaster))
				.closeToasterAlert();

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the client profile");
		userManagementCommonPage = clientPage.setSearch(clientUser);
		registerClientInformationPage = clientPage.clickClientNameLink(clientUser);
		clientsCreditPage = registerClientInformationPage.clickCredit();
		int updatedCredit = clientsCreditPage.ClientCreditCount();
		clientsCreditPage.validateAssignedCreditsReflected(curCredits, updatedCredit, creditsAmount);
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in client login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);
		int ACredit = thriveHeaderMenuPage.AvailablCredits();
		thriveHeaderMenuPage.ValidateCreditsAssignedReflectedAtClient(curCredits, ACredit, creditsAmount);
	}

	@Test(description = "Validate SA remove credits From client successfully")
	public void validateClientRemoveCreditsReflected() {

		getExtentTestLogger().assignCategory("Credit management - client");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "User search the client and click on it");
		userManagementCommonPage = clientPage.setSearch(clientUser);
		registerClientInformationPage = clientPage.clickClientNameLink(clientUser);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of client");
		clientsCreditPage = registerClientInformationPage.clickCredit();
		int curCredits = clientsCreditPage.ClientCreditCount();

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to allocate credits");
		clientPage.setSearch(clientUser);
		clientPage.clickClientCheckboxEmail(clientUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin select remove credits option from actions dropdown");
		userManagementCommonPage.selectRemoveCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides no. of credits to remove");
		removeCreditsPage.setNumberOfCredits(creditsAmount);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on remove credit button");
		removeCreditsPage.clickRemoveCredits().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits removed successfully");
		alertsAndMessagesPage.validateRemoveCreditToaster(getResultByKey(removeCreditToaster)).closeToasterAlert();

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the client profile");
		userManagementCommonPage = clientPage.setSearch(clientUser);
		registerClientInformationPage = clientPage.clickClientNameLink(clientUser);
		clientsCreditPage = registerClientInformationPage.clickCredit();
		int updatedCredit = clientsCreditPage.ClientCreditCount();
		clientsCreditPage.validateRemovedCreditsReflected(curCredits, updatedCredit, creditsAmount);
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in client profile");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);
		int ACredit = thriveHeaderMenuPage.AvailablCredits();
		thriveHeaderMenuPage.ValidateCreditsRemovedReflectedClient(curCredits, ACredit, creditsAmount);
	}

	@Test(description = "Validate SA Allocate Credits to Enterprise admin")
	public void validateEAtAllocateCreditsReflected() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "User search the client and click on it");
		userManagementCommonPage = clientPage.setSearch(eaMutableEmail);
		registerClientInformationPage = clientPage.clickClientNameLink(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of client");
		clientsCreditPage = registerClientInformationPage.clickCredit();
		int curCredits = clientsCreditPage.ClientCreditCount();

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to allocate credits");
		clientPage.setSearch(eaMutableEmail);
		clientPage.clickClientCheckboxEmail(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "Superadmin select allocate credits option from actions dropdown");
		userManagementCommonPage.selectAllocateCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides credits and date data");
		allocateCreditsPage.setNumberOfCredits(creditsAmount).setExpiryDate(nextYearDate);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on allocate credits button");
		allocateCreditsPage.clickAllocateCreditButton().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits assigned successfully to the client");
		alertsAndMessagesPage.validateCreditAllocationToaster(getResultByKey(allocateCreditToaster))
				.closeToasterAlert();

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the client profile");
		userManagementCommonPage = clientPage.setSearch(eaMutableEmail);
		registerClientInformationPage = clientPage.clickClientNameLink(eaMutableEmail);
		clientsCreditPage = registerClientInformationPage.clickCredit();
		int updatedCredit = clientsCreditPage.ClientCreditCount();
		clientsCreditPage.validateAssignedCreditsReflected(curCredits, updatedCredit, creditsAmount);
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in client login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDeatails);
		int ACredit = thriveHeaderMenuPage.AvailablCredits();
		thriveHeaderMenuPage.ValidateCreditsAssignedReflectedAtClient(curCredits, ACredit, creditsAmount);
	}

	@Test(description = "Validate SA remove credits From Enterprise admin successfully")
	public void validateEARemoveCreditsReflected() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "User search the client and click on it");
		userManagementCommonPage = clientPage.setSearch(eaMutableEmail);
		registerClientInformationPage = clientPage.clickClientNameLink(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of client");
		clientsCreditPage = registerClientInformationPage.clickCredit();
		int curCredits = clientsCreditPage.ClientCreditCount();

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> client");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the client to allocate credits");
		clientPage.setSearch(eaMutableEmail);
		clientPage.clickClientCheckboxEmail(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "Superadmin select remove credits option from actions dropdown");
		userManagementCommonPage.selectRemoveCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides no. of credits to remove");
		removeCreditsPage.setNumberOfCredits(creditsAmount);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on remove credit button");
		removeCreditsPage.clickRemoveCredits().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits removed successfully");
		alertsAndMessagesPage.validateRemoveCreditToaster(getResultByKey(removeCreditToaster)).closeToasterAlert();

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the client profile");
		userManagementCommonPage = clientPage.setSearch(eaMutableEmail);
		registerClientInformationPage = clientPage.clickClientNameLink(eaMutableEmail);
		clientsCreditPage = registerClientInformationPage.clickCredit();
		int updatedCredit = clientsCreditPage.ClientCreditCount();
		clientsCreditPage.validateRemovedCreditsReflected(curCredits, updatedCredit, creditsAmount);
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in client profile");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaLoginDeatails);
		int ACredit = thriveHeaderMenuPage.AvailablCredits();
		thriveHeaderMenuPage.ValidateCreditsRemovedReflectedClient(curCredits, ACredit, creditsAmount);
	}

	@Test(description = "Validate AM assign credits to enterprise successfully")
	public void validateAccountManagerAssignCreditToEnterprise() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Account manager provides username,password and click on login");
		thriveHeaderMenuPage = login.login(amLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Account manager navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenuAM();

		getExtentTestLogger().log(Status.PASS, "User search the enterprise and click on it");
		enterprisesPage.setSearch(eaMutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of Enterprise");
		int eaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();

		getExtentTestLogger().log(Status.PASS, "Account manager navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickProfileEnterpriseMenu();

		getExtentTestLogger().log(Status.PASS, "Account manager selects the enterprise to allocate credits");
		enterprisesPage.setSearch(eaMutableEmail);
		enterprisesPage.clickEnterprisesCheckbox(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "Account manager selects allocate credits option from actions dropdown");
		userManagementCommonPage.selectAllocateCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Account manager provides credits and date");
		allocateCreditsPage.setNumberOfCredits(creditsAmount);

		getExtentTestLogger().log(Status.PASS, "Account manager clicks on allocate credits button");
		allocateCreditsPage.clickAllocateCreditButton().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits assigned successfully to the enterprise");
		alertsAndMessagesPage.validateCreditAllocationToaster(getResultByKey(allocateCreditToaster))
				.closeToasterAlert();

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the enterprise profile");
		shortWait();
		enterprisesPage.setSearch(eaMutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaMutableEmail);
		int eaupdatedcredit = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		enterpriseDetailsPage.validateCreditAssignedRefelected(eaCredits, eaupdatedcredit, creditsAmount);
	}

	@Test(description = "Validate AM remove credits from enterprise successfully")
	public void validateAccountManagerRemoveCreditFromEnterprise() {

		getExtentTestLogger().assignCategory(TestCategory.CREDIT_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Account Manager provides username,password and click on login");
		thriveHeaderMenuPage = login.login(amLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Account manager navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenuAM();

		getExtentTestLogger().log(Status.PASS, "User search the enterprise and click on it");
		enterprisesPage.setSearch(eaMutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of Enterprise");
		int eaCredits = enterpriseDetailsPage.getEnterpriseCurrentCredits();

		getExtentTestLogger().log(Status.PASS, "Account manager click on the remove credits icon");
		enterpriseDetailsPage.clickRemoveCreditsIcon();

		getExtentTestLogger().log(Status.PASS, "Account manager provides no. of credits to remove");
		removeCreditsPage.setQuantityToDelete(quantity);

		getExtentTestLogger().log(Status.PASS, "Account manager clicks on Delete Quantity button");
		removeCreditsPage.clickDeleteQuantity().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits remove successfully from account maanger");
		alertsAndMessagesPage.validateToaster(getResultByKey(removeCreditToasterEA));

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the enterprise profile");
		shortWait();
		int eaupdatedcredit = enterpriseDetailsPage.getEnterpriseCurrentCredits();
		enterpriseDetailsPage.validateCreditRemovedRefected(eaCredits, eaupdatedcredit, quantity);
	}

}
