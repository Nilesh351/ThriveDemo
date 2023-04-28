package com.thrive.ui.test.user_management_search_filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteCoachRequest;
import com.thrive.api.pojos.RegisterInternalCoachRequest;
import com.thrive.api.test.user_invite_register.ApiInviteInternalCoachTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.category.CoachCategoriesPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.invite.InvitedGlobalCoach;
import com.thrive.ui.page.register.RegisterCoachCorporateExpPage;
import com.thrive.ui.page.register.RegisterCoachLanguagesPageRm;
import com.thrive.ui.page.register.RegisterCoachMentoringExpPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterCoachProfileForClients;
import com.thrive.ui.page.register.RegisterCoachSkillsAndQualificationPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementInternalCoachesSearchFilterTest extends UserManagementCommonPage {

	InternalCoachesPage internalCoachesPage = new InternalCoachesPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveLoginPage login = new ThriveLoginPage();
	InviteCoachPage inviteCoachPage;
	CoachCategoriesPage coachCategoriesPage = new CoachCategoriesPage();
	CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InvitedGlobalCoach invitedGlobalCoach = new InvitedGlobalCoach();
	RegisterCoachLanguagesPageRm registerCoachLanguagesPageRm = new RegisterCoachLanguagesPageRm();
	RegisterCoachProfileForClients registerCoachProfileForClients = new RegisterCoachProfileForClients();
	RegisterCoachMentoringExpPage registerCoachMentoringExpPage = new RegisterCoachMentoringExpPage();
	RegisterCoachCorporateExpPage registerCoachCorporateExpPage = new RegisterCoachCorporateExpPage();
	RegisterCoachSkillsAndQualificationPage registerCoachSkillsAndQualificationPage = new RegisterCoachSkillsAndQualificationPage();
	RegisterCoachPersonalDetailsPage registerCoachPersonalDetailsPage = new RegisterCoachPersonalDetailsPage();
	UsersPage usersPage = new UsersPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	List<String> language = new ArrayList<String>();
	List<String> industry = new ArrayList<String>();
	List<String> expertises = new ArrayList<String>();
	List<Integer> sessionsCountListBeforeSorting = new ArrayList<Integer>();
	List<Integer> sessionsCountListAfterSorting = new ArrayList<Integer>();
	String sortByLeastBooked = ThriveAppSharedData.LEAST_BOOKED.getValue();
	String sortByMostBooked = ThriveAppSharedData.MOST_BOOKED.getValue();
	InviteCoachRequest inviteInternalCoachReqBody;
	RegisterInternalCoachRequest registerInternalCoachReqBody;
	Response response;
	String currentDataCount;
	ExtentTest extentTestLocal;
	String value = "1000";
	int paginationValue = 100;
	Map<String, String> headers = new HashMap<>();

	@Test(description = "Validate superadmin search Internal coach successfully")
	public void validateInternalCoachSearch() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the Internal coach to be searched");
		userManagementCommonPage = internalCoachesPage.setSearch(internalCoachUser);

		getExtentTestLogger().log(Status.PASS, "Internal Coach searched successfully");
		internalCoachesPage.validateEmailPresent(internalCoachUser);

	}

	@Test(description = "Validate superadmin 'filter By language' successfully")
	public void validateFilterByLanguage() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filters by language");
		language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		internalCoachesPage.selectFilterByLanguage(language);

		getExtentTestLogger().log(Status.PASS, "Superadmin click Internal coach name");
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnInternalCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate selected language present");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
			registerCoachProfileForClients.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		} else {
			registerCoachLanguagesPageRm = registerCoachPersonalDetailsPage.clickLanguages();
			registerCoachLanguagesPageRm.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		}

	}

	@Test(description = "Validate superadmin 'filter By industry' successfully")
	public void validateFilterByIndustry() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by industry");
		industry.add(ThriveAppSharedData.INDUSTRY.getValue());
		internalCoachesPage.selectFilterByIndustry(industry);

		getExtentTestLogger().log(Status.PASS, "Superadmin click Internal coach name");
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnInternalCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate filter by industry successfully");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
			registerCoachProfileForClients.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
		} else {
			registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
			registerCoachMentoringExpPage.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
		}

	}

	@Test(description = "Validate superadmin 'filter By Enterprise' successfully")
	public void validateFilterByEnterprise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by enterprise");
		internalCoachesPage.selectFilterByEnterprise(enterprise);

		getExtentTestLogger().log(Status.PASS, "Validate Filter By Enterprise successfully");
		internalCoachesPage.validateEnterpriseNamePresent(enterprise);

	}

	@Test(description = "Validate superadmin 'filter By Expertise' successfully")
	public void validateFilterByExpertise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by expertise");
		expertises.add(internalExpertise);
		internalCoachesPage.selectFilterByExpertise(expertises);

		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnInternalCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate filter by expertise successfully");
		registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
		registerCoachMentoringExpPage.validateExpertisePresent(internalTopic, internalExpertise);
	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for Internal Coaches tab")
	public void validateActionsDropdownOptionsInternalCoaches() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		internalCoachesPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download,invite, and archive options present in actions dropdown");
		internalCoachesPage.validateActionDropdownElements();
	}

	@Test(description = "Validate SA can scroll between the list Internal Coaches tab")
	public void validateSAScrollInternalCoachesTab() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin scroll between Internal coaches tab");
		internalCoachesPage.clickRightScroller();

		getExtentTestLogger().log(Status.PASS, "Validate columns after the scrolling");
		internalCoachesPage.validateColumnsPresentSA();

	}

	@Test(description = "Validate pagination records match with selected pagination for Internal coaches tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsInternalCoaches(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		internalCoachesPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		internalCoachesPage.validatePaginationRecords(val);

	}

	@Test(description = "Validate clear filter clear all applied filters successfully")
	public void validateClearFiltersInternalCoaches() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the coach to be searched and filtered");
		internalCoachesPage.setSearch(internalCoachUser);
		internalCoachesPage.selectSortByValue(ThriveAppSharedData.MOST_BOOKED_OPTION.getValue());
		expertises.add(internalExpertise);
		internalCoachesPage.selectFilterByExpertise(expertises);
		industry.add(ThriveAppSharedData.INDUSTRY.getValue());
		internalCoachesPage.selectFilterByIndustry(industry);
		language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		internalCoachesPage.selectFilterByLanguage(language);
		internalCoachesPage.selectFilterByEnterprise(enterprise);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on clear filters");
		internalCoachesPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		internalCoachesPage.validateClearFilters();

	}

	@Test(description = "Validate Coaches data gets updated after registering new internal coach")
	public void validateInternalCoachesDataUpdate() {

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Internal Coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Capture current internal coaches data count");
		currentDataCount = userManagementCommonPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "SA user invite internal coach");
		headers.put("Authorization", getApiAuthCode());
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
		getExtentTestLogger().log(Status.PASS, "SA register coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Internal Coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Validate internal coaches data gets updated");
		usersPage.validateDataUpdate(currentDataCount);
	}

	@Test(description = "Validate Internal Coaches data gets updated after registering new internal coach")
	public void validateInternalCoachesDataUpdateEALogin() {

		getExtentTestLogger().log(Status.PASS, "Logging in with enterprise admin user credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Internal Coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Capture current internal coaches data count");
		currentDataCount = userManagementCommonPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "EA user invite internal coach");
		headers.put("Authorization", getApiAuthCode());
		inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
		ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
		inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachReqBody();
		} else {
			registerInternalCoachReqBody = ApiTestDataGenerator.generateRegisterInternalCoachRmReqBody();
		}
		registerInternalCoachReqBody.setFirstName(inviteInternalCoachReqBody.getFirstName());
		registerInternalCoachReqBody.setLastName(inviteInternalCoachReqBody.getLastName());
		registerInternalCoachReqBody.setEmail(inviteInternalCoachReqBody.getEmail());
		getExtentTestLogger().log(Status.PASS, "SA register coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Internal Coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Validate internal coaches data gets updated");
		usersPage.validateDataUpdate(currentDataCount);
	}

	
	@Test(description = "Validate enterprise admin search Internal coach successfully")
	public void validateInternalCoachSearchEALogin() {
		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides the Internal coach to be searched");
		userManagementCommonPage = internalCoachesPage.setSearch(internalCoachUser);

		getExtentTestLogger().log(Status.PASS, "Internal Coach searched successfully");
		internalCoachesPage.validateEmailPresent(internalCoachUser);

	}
	
	@Test(description = "Validate enterprise admin 'filter By language' successfully")
	public void validateFilterByLanguageEALogin() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal coaches");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin filters by language");
		language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		internalCoachesPage.selectFilterByLanguage(language);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin click Internal coach name");
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnInternalCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate selected language present");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
			registerCoachProfileForClients.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		} else {
			registerCoachLanguagesPageRm = registerCoachPersonalDetailsPage.clickLanguages();
			registerCoachLanguagesPageRm.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		}

	}

	@Test(description = "Validate enterprise admin 'filter By Expertise' successfully")
	public void validateFilterByExpertiseEALogin() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin filter by expertise");
		expertises.add(internalExpertise);
		internalCoachesPage.selectFilterByExpertise(expertises);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin click coach name");
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnInternalCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate filter by expertise successfully");
		registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
		registerCoachMentoringExpPage.validateExpertisePresent(internalTopic, internalExpertise);
	}

	@Test(description = "Validate Enterprise admin 'filter By industry' successfully")
	public void validateFilterByIndustryEALogin() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin filter by industry");
		industry.add(ThriveAppSharedData.INDUSTRY.getValue());
		internalCoachesPage.selectFilterByIndustry(industry);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin click Internal coach name");
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnInternalCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate filter by industry successfully");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
			registerCoachProfileForClients.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
		} else {
			registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
			registerCoachMentoringExpPage.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
		}

	}
	
	@Test(description = "Validate Sort By Least Booked filter ")
	public void validateSortByLeastBooked() {
		getExtentTestLogger().log(Status.PASS, "Login with SA credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigates to the user management -> coaches");
		thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Collects Sessions count data before sorting");
		sessionsCountListBeforeSorting = internalCoachesPage.collectSessionsCount(paginationValue);

		getExtentTestLogger().log(Status.PASS, "Select Sort By option from dropdown");
		internalCoachesPage.selectSortByValue(sortByLeastBooked);

		getExtentTestLogger().log(Status.PASS, "Collects Sessions count data after sorting");
		sessionsCountListAfterSorting = internalCoachesPage.collectSessionsCount(paginationValue);

		getExtentTestLogger().log(Status.PASS,"Validate Session data is sorted as per the option selected from Sort By dropdown");
		internalCoachesPage.validateSessionCountDataSorted(sessionsCountListBeforeSorting, sessionsCountListAfterSorting,sortByLeastBooked);

	}
	
	@Test(description = "Validate Sort By Most Booked filter ")
	public void validateSortByMostBooked() {
		getExtentTestLogger().log(Status.PASS, "Login with EA credentials");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigates to the user management -> coaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Collects Sessions count data before sorting");
		sessionsCountListBeforeSorting = internalCoachesPage.collectSessionsCount(paginationValue);

		getExtentTestLogger().log(Status.PASS, "Select Sort By option from dropdown");
		internalCoachesPage.selectSortByValue(sortByMostBooked);

		getExtentTestLogger().log(Status.PASS, "Collects Sessions count data after sorting");
		sessionsCountListAfterSorting = internalCoachesPage.collectSessionsCount(paginationValue);

		getExtentTestLogger().log(Status.PASS,"Validate Session data is sorted as per the option selected from Sort By dropdown");
		internalCoachesPage.validateSessionCountDataSorted(sessionsCountListBeforeSorting, sessionsCountListAfterSorting,sortByMostBooked);

	}

	@Test(description = "Validate EA can scroll between the list Internal Coaches tab")
	public void validateEAScrollInternalCoachesTab() {

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the User Management -> Internal CSSoaches");
		internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin scroll between coaches tab");
		internalCoachesPage.clickRightScroller();

		getExtentTestLogger().log(Status.PASS, "Validate columns after the scrolling");
		internalCoachesPage.validateRightScrollEA();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin scroll between coaches tab");
		internalCoachesPage.clickLeftScroller();

		getExtentTestLogger().log(Status.PASS, "Validate columns after the scrolling");
		internalCoachesPage.validateLeftScrollEA();

	}
	
	private Response getRegisterInternalCoachResponse(RegisterInternalCoachRequest registerInternalCoachReqBody2) {
		String token = getRegisterInternalCoachToken(registerInternalCoachReqBody.getFirstName());
		return RestAssured.given().spec(getRegisterInternalCoachUserReqSpec()).queryParam("token", token).and().when()
				.body(registerInternalCoachReqBody, ObjectMapperType.GSON).post("");

	}

}