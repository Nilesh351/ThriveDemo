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
import com.thrive.api.pojos.RegisterCoachRequest;
import com.thrive.api.test.user_invite_register.ApiInviteCoachTest;
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
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementCoachesSearchFilterTest extends UserManagementCommonPage {

	GlobalCoachesPage globalCoachesPage = new GlobalCoachesPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	ThriveLoginPage login = new ThriveLoginPage();
	InviteCoachPage inviteCoachPage;
	CoachCategoriesPage coachCategoriesPage = new CoachCategoriesPage();
	CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InvitedGlobalCoach invitedGlobalCoach = new InvitedGlobalCoach();
	RegisterCoachLanguagesPageRm registerCoachLanguagesPageRm = new RegisterCoachLanguagesPageRm();
	RegisterCoachMentoringExpPage registerCoachMentoringExpPage = new RegisterCoachMentoringExpPage();
	RegisterCoachCorporateExpPage registerCoachCorporateExpPage = new RegisterCoachCorporateExpPage();
	RegisterCoachSkillsAndQualificationPage registerCoachSkillsAndQualificationPage = new RegisterCoachSkillsAndQualificationPage();
	RegisterCoachProfileForClients registerCoachProfileForClients = new RegisterCoachProfileForClients();
	RegisterCoachPersonalDetailsPage registerCoachPersonalDetailsPage = new RegisterCoachPersonalDetailsPage();
	UsersPage usersPage = new UsersPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	List<String> language = new ArrayList<String>();
	List<String> industry = new ArrayList<String>();
	List<String> expertises = new ArrayList<String>();
	List<Integer> sessionsCountListBeforeSorting = new ArrayList<Integer>();
	List<Integer> sessionsCountListAfterSorting = new ArrayList<Integer>();
	String sortByMostBooked = ThriveAppSharedData.MOST_BOOKED.getValue();
	String sortByLeastBooked = ThriveAppSharedData.LEAST_BOOKED.getValue();
	InviteCoachRequest inviteCoachReqBody;
	RegisterCoachRequest registerCoachReqBody;
	String currentDataCount;
	Response response;
	ExtentTest extentTestLocal;
	int paginationValue = 100;
	String value = "1000";
	Map<String, String> headers = new HashMap<>();

	@Test(description = "Validate superadmin search coach successfully")
	public void validateGlobalCoachSearch() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		this.globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the coach to be searched");
		userManagementCommonPage = this.globalCoachesPage.setSearch(globalCoachUser);

		getExtentTestLogger().log(Status.PASS, "Global Coach searched successfully");
		globalCoachesPage.validateEmailPresent(globalCoachUser);

	}

	@Test(description = "Validate superadmin 'filter By language' successfully")
	public void validateFilterByLanguage() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filters by language");
		language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		globalCoachesPage.selectFilterByLanguage(language);

		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage = globalCoachesPage.clickOnCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate selected language present");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
			registerCoachProfileForClients.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		} else {
			registerCoachLanguagesPageRm = registerCoachPersonalDetailsPage.clickLanguages();
			registerCoachLanguagesPageRm.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		}

	}

	@Test(description = "Validate superadmin 'filter By coach type training coach' successfully")
	public void validateFilterByTrainingCoach() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filters by training coach");
		globalCoachesPage.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_TRAINING_COACH.getValue());

		getExtentTestLogger().log(Status.PASS, "Validate filter by training coach successfully.");
		globalCoachesPage.validateTrainingCoach();

	}

	@Test(description = "Validate superadmin 'filter By coach type global coach' successfully")
	public void validateFilterByGlobalCoach() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by global coach");
		globalCoachesPage.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_GLOBAL_COACH.getValue());

		getExtentTestLogger().log(Status.PASS, "validate filter by global coach successfully.");
		globalCoachesPage.validateGlobalCoach();

	}

	@Test(description = "Validate superadmin 'filter By industry' successfully")
	public void validateFilterByIndustry() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by industry");
		industry.add(ThriveAppSharedData.INDUSTRY.getValue());
		globalCoachesPage.selectFilterByIndustry(industry);

		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage = globalCoachesPage.clickOnCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate filter by industry successfully");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
			registerCoachProfileForClients.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
		} else {
			registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
			registerCoachMentoringExpPage.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
		}

	}

	@Test(description = "Validate superadmin 'filter By Expertise' successfully")
	public void validateFilterByExpertise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by expertise");
		expertises.add(expertise);
		globalCoachesPage.selectFilterByExpertise(expertises);

		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage = globalCoachesPage.clickOnCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate filter by expertise successfully");
		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
			registerCoachMentoringExpPage.validateExpertisePresent(topic, expertise);
		} else {
			coachCategoriesPage = registerCoachPersonalDetailsPage.clickCoachCategories();
			coachCategoriesPage.validateExpertisePresent(category);
		}
	}

	@Test(description = "Validate clear filter clear all applied filters successfully")
	public void validateClearFiltersCoaches() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the coach to be searched and filtered");
		globalCoachesPage.setSearch(globalCoachUser);
		globalCoachesPage.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_GLOBAL_COACH.getValue());
		globalCoachesPage.selectSortByValue(ThriveAppSharedData.MOST_BOOKED_OPTION.getValue());
		expertises.add(expertise);
		globalCoachesPage.selectFilterByExpertise(expertises);
		industry.add(ThriveAppSharedData.INDUSTRY.getValue());
		globalCoachesPage.selectFilterByIndustry(industry);
		language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		globalCoachesPage.selectFilterByLanguage(language);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on clear filters");
		globalCoachesPage.clickClearFilters();

		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		globalCoachesPage.validateClearFilters();

	}

	@Test(description = "Validate pagination records match with selected pagination for coaches tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsCoaches(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		globalCoachesPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate user searched successfully.");
		globalCoachesPage.validatePaginationRecords(val);

	}

	@Test(description = "Validate SA can scroll between the list Coaches tab")
	public void validateSAScrollCoachesTab() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin scroll between coaches tab");
		globalCoachesPage.clickRightScroller();

		getExtentTestLogger().log(Status.PASS, "Validate columns after the scrolling");
		globalCoachesPage.validateColumnsPresent();

	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for Coaches tab")
	public void validateActionsDropdownOptionsCoaches() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		globalCoachesPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,
				"Validate download,invite, and archive options present in actions dropdown");
		globalCoachesPage.validateActionDropdownElements();
	}


	@Test(description = "Validate Sort By Least Booked filter ")
	public void validateSortByLeastBooked() {

		getExtentTestLogger().log(Status.PASS, "Login with SA credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Collects Sessions count data before sorting");
		sessionsCountListBeforeSorting = globalCoachesPage.collectSessionsCount(paginationValue);

		getExtentTestLogger().log(Status.PASS, "Select Sort By option from dropdown");
		globalCoachesPage.selectSortByValue(sortByLeastBooked);

		getExtentTestLogger().log(Status.PASS, "Collects Sessions count data after sorting");
		sessionsCountListAfterSorting = globalCoachesPage.collectSessionsCount(paginationValue);

		getExtentTestLogger().log(Status.PASS,"Validate Session data is sorted as per the option selected from Sort By dropdown");
		globalCoachesPage.validateSessionCountDataSorted(sessionsCountListBeforeSorting, sessionsCountListAfterSorting,sortByLeastBooked);

	}
	
	@Test(description = "Validate Sort By Most Booked filter ")
	public void validateSortByMostBooked() {

		getExtentTestLogger().log(Status.PASS, "Login with SA credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Collects Sessions count data before sorting");
		sessionsCountListBeforeSorting = globalCoachesPage.collectSessionsCount(paginationValue);

		getExtentTestLogger().log(Status.PASS, "Select Sort By option from dropdown");
		globalCoachesPage.selectSortByValue(sortByMostBooked);

		getExtentTestLogger().log(Status.PASS, "Collects Sessions count data after sorting");
		sessionsCountListAfterSorting = globalCoachesPage.collectSessionsCount(paginationValue);

		getExtentTestLogger().log(Status.PASS,"Validate Session data is sorted as per the option selected from Sort By dropdown");
		globalCoachesPage.validateSessionCountDataSorted(sessionsCountListBeforeSorting, sessionsCountListAfterSorting,sortByMostBooked);

	}

	@Test(description = "Validate Coaches data gets updated after registering new coach")
	public void validateCoachesDataUpdate() {

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Coaches");
		globalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Capture current Coaches data count");
		currentDataCount = userManagementCommonPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "SA user invite Account Manager");
		headers.put("Authorization", getApiAuthCode());
		inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
		ApiInviteCoachTest inviteCoachTest = new ApiInviteCoachTest();
		inviteCoachTest.getInviteCoachResponse(inviteCoachReqBody, headers);

		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachReqBody();
		} else {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachRmReqBody();
		}
		registerCoachReqBody.setFirstName(inviteCoachReqBody.getFirstName());
		registerCoachReqBody.setLastName(inviteCoachReqBody.getLastName());
		registerCoachReqBody.setEmail(inviteCoachReqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "SA register coach successfully");
		response = getRegisterCoachResponse(registerCoachReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Validate Coaches data gets updated");
		usersPage.validateDataUpdate(currentDataCount);
	}

	private Response getRegisterCoachResponse(RegisterCoachRequest registerCoachReqBody) {
		String token = getRegisterCoachToken(registerCoachReqBody.getFirstName());
		return RestAssured.given().spec(getRegisterCoachUserReqSpec()).queryParam("token", token).and().when()
				.body(registerCoachReqBody, ObjectMapperType.GSON).post("");

	}

}