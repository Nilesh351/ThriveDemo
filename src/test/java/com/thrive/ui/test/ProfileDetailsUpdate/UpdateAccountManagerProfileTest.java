package com.thrive.ui.test.ProfileDetailsUpdate;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.user_details.AccountManagerUpdateDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.user_management.search_filter.AccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.ConfigureEnterprises;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

public class UpdateAccountManagerProfileTest extends BaseTestPage{
	
	
	ThriveLoginPage login = new ThriveLoginPage();
	UsersPage usersPage;
	LoginDetails amLoginDetails = new LoginDetails(accountManagerUser, accountManagerPassword);
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	ConfigureEnterprises configureEnterprises = new ConfigureEnterprises();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	LoginDetails loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	AccountManagersPage accountManagersPage = new AccountManagersPage();
	RegisterAccountMangerPersonalDetailsPage registerAccountMangerPersonalDetailsPage= new RegisterAccountMangerPersonalDetailsPage();
	AccountManagerUpdateDetails accountManagerUpdateDetails = TestDataGenerator.generateAccountManagerUpdateDetails();
	
	@Test(description="Validate SA successfully update account manager profile details")
	public void validateAccountManagerPersonalDetails() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_PROFILE_UPDATE.getValue());
		
		login = new ThriveLoginPage();
		getExtentTestLogger().log(Status.PASS, "langauge dropdown,username,password and click login");
		thriveHeaderMenuPage = login.login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> account managers");
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "User search the account manager to update and click on it");
		accountManagersPage.setSearch(amUserUpdate);
		registerAccountMangerPersonalDetailsPage = accountManagersPage.clickOnSearchedAccountManagerName(amUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User provides the first name to update for account manager");
		registerAccountMangerPersonalDetailsPage.setFirstName(accountManagerUpdateDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "User provides the last name to update for account manager");
		registerAccountMangerPersonalDetailsPage.setLastName(accountManagerUpdateDetails.getLastName());
		
		getExtentTestLogger().log(Status.PASS, "User provides the mobile no to update for account manager");
		registerAccountMangerPersonalDetailsPage.setMobileNumber(accountManagerUpdateDetails.getMobileNumber());
		
		getExtentTestLogger().log(Status.PASS, "User provides the age range to update for account manager");
		registerAccountMangerPersonalDetailsPage.selectFirstAvailableAgeRange();
		
		getExtentTestLogger().log(Status.PASS, "User provides the gender to update for account manager");
		registerAccountMangerPersonalDetailsPage.selectFirstAvailableGenderValue();
		
		getExtentTestLogger().log(Status.PASS, "User provides the country to update for account manager");
		registerAccountMangerPersonalDetailsPage.selectFirstAvailableCountryValue();
		
		getExtentTestLogger().log(Status.PASS, "User provides the nationality to update for account manager");
		registerAccountMangerPersonalDetailsPage.selectFirstAvailableNationality();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on fill in address manually ");
		registerAccountMangerPersonalDetailsPage.clickFillManually();
		
		getExtentTestLogger().log(Status.PASS, "User provides the address1 to update for account manager");
		registerAccountMangerPersonalDetailsPage.setAddressLine1(accountManagerUpdateDetails.getAddress1());
		
		getExtentTestLogger().log(Status.PASS, "User provides the address2 to update for account manager");
		registerAccountMangerPersonalDetailsPage.setAddressLine2(accountManagerUpdateDetails.getAddress2());
		
		getExtentTestLogger().log(Status.PASS, "User provides the city to update for account manager");
		registerAccountMangerPersonalDetailsPage.setCity(accountManagerUpdateDetails.getCity());
		
		getExtentTestLogger().log(Status.PASS, "User provides the state to update for account manager");
		registerAccountMangerPersonalDetailsPage.setState(accountManagerUpdateDetails.getState());
		
		getExtentTestLogger().log(Status.PASS, "User provides the county to update for account manager");
		registerAccountMangerPersonalDetailsPage.setCounty(accountManagerUpdateDetails.getCounty());
		
		getExtentTestLogger().log(Status.PASS, "User provides the postcode to update for account manager");
		registerAccountMangerPersonalDetailsPage.setPostcode(accountManagerUpdateDetails.getPostcode());
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the update details button");
		registerAccountMangerPersonalDetailsPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets the updation notification");
		registerAccountMangerPersonalDetailsPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "The account manager personal details updated successfully");
		registerAccountMangerPersonalDetailsPage.validateAccountManagerPersonalDetails(accountManagerUpdateDetails);
		
	}
	
	@Test(description = "Validate SA disable enterprise for account manager successfully")
	public void validateSADisableEnterprisesuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_PROFILE_UPDATE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> archived Account Managers");
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin search the account manager to update and click on it");
		accountManagersPage.setSearch(accountManagerUser);
		registerAccountMangerPersonalDetailsPage = accountManagersPage.clickOnSearchedAccountManagerName(accountManagerUser);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on enterprises tab");
		configureEnterprises = registerAccountMangerPersonalDetailsPage.clickEnterprisesTab();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin search enterprise to enable");
		configureEnterprises.selectEnterprise(mutableEnterpirse);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin disable enterprise successfully");
		configureEnterprises.enableDisableEnterprise(false, mutableEnterpirse);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin click save changes button");
		configureEnterprises.clickSaveChangesButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate toaster message present");
		alertsAndMessagesPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Validate disabled enterprise not present inside enterprises tab of account manager");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(amLoginDetails);
		thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		enterprisesPage.setSearch(mutableEnterpirse);
		enterprisesPage.validateEnterpriseNameNotPresent(mutableEnterpirse);
	}
	
	@Test(description = "Validate SA enable enterprise for account manager successfully")
	public void validateSAEnableEnterprisesuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_PROFILE_UPDATE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> archived Account Managers");
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin search the account manager to update and click on it");
		accountManagersPage.setSearch(accountManagerUser);
		registerAccountMangerPersonalDetailsPage = accountManagersPage.clickOnSearchedAccountManagerName(accountManagerUser);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on enterprises tab");
		configureEnterprises = registerAccountMangerPersonalDetailsPage.clickEnterprisesTab();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin search enterprise to enable");
		configureEnterprises.selectEnterprise(mutableEnterpirse);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin enable enterprise successfully");
		configureEnterprises.enableDisableEnterprise(true, mutableEnterpirse);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin click save changes button");
		configureEnterprises.clickSaveChangesButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate toaster message present");
		alertsAndMessagesPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Validate enabled enterprise present inside enterprises tab of account manager");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(amLoginDetails);
		thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		enterprisesPage.setSearch(mutableEnterpirse);
		enterprisesPage.validateEnterpriseNamePresent(mutableEnterpirse);
	}
	

}
