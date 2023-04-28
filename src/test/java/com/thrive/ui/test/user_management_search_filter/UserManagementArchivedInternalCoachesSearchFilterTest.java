package com.thrive.ui.test.user_management_search_filter;

import java.io.IOException;
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
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.category.CoachCategoriesPage;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteCoachPage;
import com.thrive.ui.page.register.RegisterCoachCorporateExpPage;
import com.thrive.ui.page.register.RegisterCoachLanguagesPageRm;
import com.thrive.ui.page.register.RegisterCoachMentoringExpPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterCoachProfileForClients;
import com.thrive.ui.page.user_management.search_filter.ArchivedInternalCoachPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementArchivedInternalCoachesSearchFilterTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage;
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	ThriveLoginPage login = new ThriveLoginPage();
	MailboxPage mailboxPage = new MailboxPage();
	InviteCoachPage inviteCoachPage = new InviteCoachPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	InternalCoachesPage internalCoachesPage = new InternalCoachesPage();
	CoachInviteDetails coachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
	String subjectWelcome = ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT.getValue();
	ArchivedInternalCoachPage archivedInternalCoachPage = new ArchivedInternalCoachPage();
	RegisterCoachLanguagesPageRm registerCoachLanguagesPageRm = new RegisterCoachLanguagesPageRm();
	RegisterCoachCorporateExpPage registerCoachCorporateExpPage = new RegisterCoachCorporateExpPage();
	RegisterCoachMentoringExpPage registerCoachMentoringExpPage = new RegisterCoachMentoringExpPage();
	CoachCategoriesPage coachCategoriesPage = new CoachCategoriesPage();
	RegisterCoachProfileForClients registerCoachProfileForClients = new RegisterCoachProfileForClients();;
	RegisterCoachPersonalDetailsPage registerCoachPersonalDetailsPage = new RegisterCoachPersonalDetailsPage();
	UsersPage usersPage = new UsersPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	RegisterInternalCoachRequest registerInternalCoachReqBody;
	List<String> language = new ArrayList<String>();
	List<Integer> sessionsCountListBeforeSorting = new ArrayList<Integer>();
	List<Integer> sessionsCountListAfterSorting = new ArrayList<Integer>();
	String archiveMessage = ThriveAppSharedData.COACH_ARCHIVED_MESSAGE.getValue();
	String sortByLeastBooked = ThriveAppSharedData.LEAST_BOOKED.getValue();
	String sortByMostBooked = ThriveAppSharedData.MOST_BOOKED.getValue();
	InviteCoachRequest inviteInternalCoachReqBody;
	Response response;
	ExtentTest extentTestLocal;
	String value = "1000";
	int paginationValue = 100;
	String currentDataCount;
	Map<String, String> headers = new HashMap<>();
	List<String> expertises = new ArrayList<String>();
	List<String> industry = new ArrayList<String>();

	@Test(description = "Validate SA search archived internal coach successfully")
	public void validateSAArchiveInternalCoachSuccessfully() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		String archiveMessage = ThriveAppSharedData.COACH_ARCHIVED_MESSAGE.getValue();

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

		getExtentTestLogger().log(Status.PASS, "SA user invite Internal coach");

		getExtentTestLogger().log(Status.PASS, "SA register Internal coach successfully");
		response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);

		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Internal Coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the coach to archive");
		internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
		internalCoachesPage.clickCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		internalCoachesPage.selectArchiveCoachAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);

		getExtentTestLogger().log(Status.PASS, "Validate archived Internal coach searched successfully");
		internalCoachesPage.clickArchivedCoachButton();
		archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
		archivedInternalCoachPage.validateInternalCoachPresent(inviteInternalCoachReqBody.getEmail());
	}

	@Test(description = "Validate superadmin 'filter By language' successfully")
	public void validateFilterByLanguage() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,
				"Superadmin navigates to the user management -> Archived Internal coaches");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
				.clickArchivedCoachButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin filters by language");
		language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		archivedInternalCoachPage.selectFilterByLanguage(language);

		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage = archivedInternalCoachPage.clickOnInternalCoachName();

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

		getExtentTestLogger().log(Status.PASS,
				"Superadmin navigates to the user management -> Archived Internal coaches");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
				.clickArchivedCoachButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by industry");
		industry.add(ThriveAppSharedData.INDUSTRY.getValue());
		archivedInternalCoachPage.selectFilterByIndustry(industry);

		getExtentTestLogger().log(Status.PASS, "Superadmin click Internal coach name");
		registerCoachPersonalDetailsPage = archivedInternalCoachPage.clickOnInternalCoachName();

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

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> Archived Internal coaches");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
	    .clickArchivedCoachButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by expertise");
		expertises.add(internalExpertise);
		archivedInternalCoachPage.selectFilterByExpertise(expertises);

		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage = archivedInternalCoachPage.clickOnInternalCoachName();

		getExtentTestLogger().log(Status.PASS, "Validate filter by expertise successfully");
		registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
		registerCoachMentoringExpPage.validateExpertisePresent(internalTopic, internalExpertise);
	}

	@Test(description = "Validate superadmin 'filter By Enterprise' successfully")
	public void validateFilterByEnterprise() {

		getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> Archived Internal coaches");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
		.clickArchivedCoachButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin filter by enterprise");
		archivedInternalCoachPage.selectFilterByEnterprise(enterprise);

		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		archivedInternalCoachPage.validateEnterpriseNamePresent(enterprise);

	}

	@Test(description = "Validate SA can scroll between the list Archived Internal Coaches tab")
	public void validateSAScrollArchivedInternalCoachesTab() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> Archived Internal coaches");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
		.clickArchivedCoachButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin scroll between coaches tab");
		archivedInternalCoachPage.clickRightScroller();

		getExtentTestLogger().log(Status.PASS, "Validate columns after the scrolling");
		archivedInternalCoachPage.validateColumnsPresent();

	}

	@Test(description = "Validate pagination records match with selected pagination for Archived Internal coaches tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
	public void validatePaginationRecordsArchivedInternalCoaches(int val) {

		extentTestLocal = extentReports.createTest("Selected pagination" + val,
				"pagination " + val + "selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");

		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> Archived Internal coaches");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
		.clickArchivedCoachButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		archivedInternalCoachPage.scrollInVerticalDirection(value).selectPaginationValue(val);

		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		archivedInternalCoachPage.validatePaginationRecords(val);

	}

	@Test(description = "Validate actions dropdwown contains all Specicifed options for Archived Internal Coaches tab")
	public void validateActionsDropdownOptionsArchivedInternalCoaches() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> Archived Internal coaches");
		archivedInternalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink()
	    .clickArchivedCoachButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		archivedInternalCoachPage.clickActionsDropdown();

		getExtentTestLogger().log(Status.PASS,"Validate download,Unarchived and delete options present in actions dropdown");
		archivedInternalCoachPage.validateActionDropdownElements();
	}

	 @Test(description="Validate Archived Coaches data gets updated when SA archives Internal Coach")
	 public void validateArchivedInternalCoachDataUpdate() {
		 
		    getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
			thriveHeaderMenuPage = login.login(saLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Internal Coaches");
			thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickArchivedCoachButton();
			
			getExtentTestLogger().log(Status.PASS, "Capture current archived internal coaches data count");
			currentDataCount = userManagementCommonPage.captureCurrentDataCount();

			getExtentTestLogger().log(Status.PASS, "SA user invite internal coach");
			headers.put("Authorization", getApiAuthCode());
			inviteInternalCoachReqBody = ApiTestDataGenerator.generateInviteInternalCoachReqBody();
			ApiInviteInternalCoachTest inviteInternalCoachTest = new ApiInviteInternalCoachTest();
			response = inviteInternalCoachTest.getInviteInternalCoachResponse(inviteInternalCoachReqBody, headers);

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
			thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
			
			getExtentTestLogger().log(Status.PASS, "Search registered coach and archive the coach");
			internalCoachesPage.setSearch(inviteInternalCoachReqBody.getFirstName()).selectUserCheck(inviteInternalCoachReqBody.getFirstName()).selectArchiveCoachAction().ClickYes();
			
			getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Internal Coaches -> Archived Internal Coaches");
			thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink().clickArchivedLink();
			
			getExtentTestLogger().log(Status.PASS, "Validate archived internal coaches data gets updated");
			usersPage.validateDataUpdate(currentDataCount);
	 }
	 
	 
		
		@Test(description = "Validate Enterprise admin 'filter By industry' successfully")
		public void validateFilterByIndustryEALogin() {

			getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS,"Enterprise admin navigates to the user management -> Archived Internal coaches");
		    thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickArchivedCoachButton();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin filter by industry");
			industry.add(ThriveAppSharedData.INDUSTRY.getValue());
			archivedInternalCoachPage.selectFilterByIndustry(industry);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin click Internal coach name");
			registerCoachPersonalDetailsPage = archivedInternalCoachPage.clickOnInternalCoachName();

			getExtentTestLogger().log(Status.PASS, "Validate filter by industry successfully");
			if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
				registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
				registerCoachProfileForClients.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
			} else {
				registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
				registerCoachMentoringExpPage.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
			}

		}
		

		@Test(description = "Validate Enterprise admin search archived internal coach successfully")
		public void validateEAArchiveInternalCoachSuccessfully() throws IOException {

			getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

			String archiveMessage = ThriveAppSharedData.COACH_ARCHIVED_MESSAGE.getValue();

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

			getExtentTestLogger().log(Status.PASS, "Enterprise admin user invite Internal coach");

			getExtentTestLogger().log(Status.PASS, "Enterprise admin register Internal coach successfully");
			response = getRegisterInternalCoachResponse(registerInternalCoachReqBody);

			getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
			Assert.assertTrue(response.statusCode() == 200);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			launchThriveApp(Config.getLoginPageURL());
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Internal Coaches");
			internalCoachesPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin selects the coach to archive");
			internalCoachesPage.setSearch(inviteInternalCoachReqBody.getEmail());
			internalCoachesPage.clickCoachCheckbox(inviteInternalCoachReqBody.getEmail().toLowerCase());

			getExtentTestLogger().log(Status.PASS, "Enterprise admin select archive option from actions dropdown");
			internalCoachesPage.selectArchiveCoachAction().ClickYes();

			getExtentTestLogger().log(Status.PASS, "Validate coach archived successfuly");
			alertsAndMessagesPage.validateToaster(archiveMessage);

			getExtentTestLogger().log(Status.PASS, "Validate archived Internal coach searched successfully");
			internalCoachesPage.clickArchivedCoachButton();
			archivedInternalCoachPage.setSearch(inviteInternalCoachReqBody.getEmail());
			archivedInternalCoachPage.validateInternalCoachPresent(inviteInternalCoachReqBody.getEmail());
		}
	 
	 
		@Test(description = "Validate Enterprise admin 'filter By language' successfully")
		public void validateFilterByLanguageEALogin() {

			getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS,"Enterprise admin navigates to the user management -> Archived Internal coaches");
			archivedInternalCoachPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink()
			.clickArchivedCoachButton();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin filters by language");
			language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
			archivedInternalCoachPage.selectFilterByLanguage(language);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin click coach name");
			registerCoachPersonalDetailsPage = archivedInternalCoachPage.clickOnInternalCoachName();

			getExtentTestLogger().log(Status.PASS, "Validate selected language present");
			if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
				registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
				registerCoachProfileForClients.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
			} else {
				registerCoachLanguagesPageRm = registerCoachPersonalDetailsPage.clickLanguages();
				registerCoachLanguagesPageRm.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
			}

		}
	
		@Test(description = "Validate Enterprise admin 'filter By Expertise' successfully")
		public void validateFilterByExpertiseEALogin() {

			getExtentTestLogger().assignCategory("UserManagement Actions- Internal Coach");

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(saLoginDetails);

			getExtentTestLogger().log(Status.PASS,"Enterprise admin navigates to the user management -> Archived Internal coaches");
			archivedInternalCoachPage = thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink()
		    .clickArchivedCoachButton();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin filter by expertise");
			expertises.add(internalExpertise);
			archivedInternalCoachPage.selectFilterByExpertise(expertises);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin click coach name");
			registerCoachPersonalDetailsPage = archivedInternalCoachPage.clickOnInternalCoachName();

			getExtentTestLogger().log(Status.PASS, "Validate filter by expertise successfully");
			registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
			registerCoachMentoringExpPage.validateExpertisePresent(internalTopic, internalExpertise);
	 
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
		
	
		@Test(description = "Validate actions dropdwown contains all Specicifed options for Archived Internal Coaches tab")
		public void validateActionsDropdownOptionsArchivedInternalCoachesEALogin() {

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS,"Enterprise admin navigates to the user management -> Archived Internal coaches");
			thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickArchivedLink();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on actions dropdwon");
			archivedInternalCoachPage.clickActionsDropdown();

			getExtentTestLogger().log(Status.PASS,"Validate download,Unarchived and delete options present in actions dropdown");
			archivedInternalCoachPage.validateActionDropdownElements();
		}
		
		
		@Test(description = "Validate pagination records match with selected pagination for Archived Internal coaches tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class)
		public void validatePaginationRecordsArchivedInternalCoachesEALogin(int val) {

			extentTestLocal = extentReports.createTest("Selected pagination" + val,
					"pagination " + val + "selected successfully");
			ExtentReportUtils.setExtentTest(extentTestLocal);

			LOGGER.info("onTestStart method pagination is matching");

			getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
			thriveHeaderMenuPage = login.login(saLoginDetails);

			getExtentTestLogger().log(Status.PASS,"Enterprise admin navigates to the user management -> Archived Internal coaches");
			thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickArchivedLink();

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provide the pagination value ");
			archivedInternalCoachPage.scrollInVerticalDirection(value).selectPaginationValue(val);

			getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
			archivedInternalCoachPage.validatePaginationRecords(val);

		}
		
		@Test(description = "Validate EA can scroll between the list Archived Internal Coaches tab")
		public void validateEAScrollArchiveInternalCoachesTab() {

			getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
			thriveHeaderMenuPage = login.login(eaLoginDetails);

			getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the User Management -> Internal CSSoaches");
			thriveHeaderMenuPage.navigateToUsers().clickInternalCoachesLink().clickArchivedLink();

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