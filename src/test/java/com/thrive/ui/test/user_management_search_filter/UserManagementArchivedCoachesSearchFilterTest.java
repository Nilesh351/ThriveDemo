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
import com.thrive.api.pojos.RegisterCoachRequest;
import com.thrive.api.test.user_invite_register.ApiInviteCoachTest;
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
import com.thrive.ui.page.invite.InvitedGlobalCoach;
import com.thrive.ui.page.register.RegisterCoachCorporateExpPage;
import com.thrive.ui.page.register.RegisterCoachLanguagesPageRm;
import com.thrive.ui.page.register.RegisterCoachMentoringExpPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterCoachProfileForClients;
import com.thrive.ui.page.user_management.search_filter.ArchivedGlobalCoachPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementArchivedCoachesSearchFilterTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	ThriveLoginPage login = new ThriveLoginPage();
	MailboxPage mailboxPage = new MailboxPage();
	InviteCoachPage inviteCoachPage = new InviteCoachPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	GlobalCoachesPage globalCoachesPage = new GlobalCoachesPage();
	InvitedGlobalCoach invitedGlobalCoach = new InvitedGlobalCoach();
	CoachInviteDetails coachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
	String subjectWelcome = ThriveAppSharedData.COACH_WELCOME_EMAIL_SUBJECT.getValue();
	ArchivedGlobalCoachPage archivedGlobalCoachPage = new ArchivedGlobalCoachPage();
	RegisterCoachLanguagesPageRm registerCoachLanguagesPageRm = new RegisterCoachLanguagesPageRm();
	RegisterCoachCorporateExpPage registerCoachCorporateExpPage = new RegisterCoachCorporateExpPage();
	RegisterCoachMentoringExpPage registerCoachMentoringExpPage = new RegisterCoachMentoringExpPage();
	CoachCategoriesPage coachCategoriesPage = new CoachCategoriesPage();
	RegisterCoachProfileForClients  registerCoachProfileForClients = new RegisterCoachProfileForClients();
	RegisterCoachPersonalDetailsPage registerCoachPersonalDetailsPage = new RegisterCoachPersonalDetailsPage();
	UsersPage usersPage = new UsersPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	RegisterCoachRequest registerCoachReqBody;
	List<String> language = new ArrayList<String>();
	String archiveMessage = ThriveAppSharedData.COACH_ARCHIVED_MESSAGE.getValue();
	InviteCoachRequest inviteCoachReqBody;
	Response response;
	String currentDataCount;
	Map<String, String> headers = new HashMap<>();
	List<String> expertises = new ArrayList<String>();
	List<String> industry = new ArrayList<String>();
	ExtentTest extentTestLocal;
	String value ="1000";
	
	
	@Test(description = "Validate SA search archived coach successfully")
	public void validateSAArchiveCoachSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String archiveMessage = ThriveAppSharedData.COACH_ARCHIVED_MESSAGE.getValue();
		
		headers.put("Authorization", getApiAuthCode());
		inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
		ApiInviteCoachTest inviteCoachTest = new ApiInviteCoachTest();
		inviteCoachTest.getInviteCoachResponse(inviteCoachReqBody, headers);
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachReqBody();
		}else {
			registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachRmReqBody();
		}
		registerCoachReqBody.setFirstName(inviteCoachReqBody.getFirstName());
		registerCoachReqBody.setLastName(inviteCoachReqBody.getLastName());
		registerCoachReqBody.setEmail(inviteCoachReqBody.getEmail());
	
		getExtentTestLogger().log(Status.PASS, "SA user invite coach");

		getExtentTestLogger().log(Status.PASS, "SA register coach successfully");
		response = getRegisterCoachResponse(registerCoachReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the coach to archive");
		globalCoachesPage.setSearch(inviteCoachReqBody.getEmail());
		globalCoachesPage.clickCoachCheckbox(inviteCoachReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		globalCoachesPage.selectArchiveCoachAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate archived coach searched successfully");
		globalCoachesPage.clickArchivedCoaches();
		archivedGlobalCoachPage.setSearch(inviteCoachReqBody.getEmail());
		archivedGlobalCoachPage.validateCoachPresent(inviteCoachReqBody.getEmail());
	}
	
	
	@Test(description = "Validate superadmin 'filter By language' successfully")
	public void validateFilterByLanguage() {
		
		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	    
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin filters by language");
		language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		archivedGlobalCoachPage.selectFilterByLanguage(language);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage=archivedGlobalCoachPage.clickOnCoachName();
		 
		getExtentTestLogger().log(Status.PASS, "Validate selected language present");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
			registerCoachProfileForClients.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		}else {
			registerCoachLanguagesPageRm=registerCoachPersonalDetailsPage.clickLanguages();
			registerCoachLanguagesPageRm.validateLanguagePresent(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		}
		
	}
	
	@Test(description = "Validate superadmin 'filter By industry' successfully")
	public void validateFilterByIndustry() {
		
		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	    
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin filter by industry");
		industry.add(ThriveAppSharedData.INDUSTRY.getValue());
		archivedGlobalCoachPage.selectFilterByIndustry(industry);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage=archivedGlobalCoachPage.clickOnCoachName();
		 
		getExtentTestLogger().log(Status.PASS, "Validate filter by industry successfully");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachProfileForClients = registerCoachPersonalDetailsPage.clickProfileForClient();
			registerCoachProfileForClients.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
		}else {
			registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
			registerCoachMentoringExpPage.validateIndustryPresent(ThriveAppSharedData.INDUSTRY.getValue());
		}
		
	}
	
	@Test(description = "Validate superadmin 'filter By Expertise' successfully")
	public void validateFilterByExpertise() {
		
		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	    
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin filter by expertise");
		expertises.add(expertise);
		archivedGlobalCoachPage.selectFilterByExpertise(expertises);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin click coach name");
		registerCoachPersonalDetailsPage=archivedGlobalCoachPage.clickOnCoachName();
		 
		getExtentTestLogger().log(Status.PASS, "Validate filter by expertise successfully");
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
			registerCoachMentoringExpPage.validateExpertisePresent(topic, expertise);
		}else {
			coachCategoriesPage = registerCoachPersonalDetailsPage.clickCoachCategories();
			coachCategoriesPage.validateExpertisePresent(category);
		}
	}
	
	
	@Test(description = "Validate superadmin 'filter By coach type global coach' successfully")
	public void validateFilterByGlobalCoach() {
		
		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	    
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin filter by global coach");
		archivedGlobalCoachPage.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_GLOBAL_COACH.getValue());
		
		getExtentTestLogger().log(Status.PASS, "validate filter by global coach successfully.");
		archivedGlobalCoachPage.validateGlobalCoach();
		
	}
	
	
	@Test(description = "Validate superadmin 'filter By coach type training coach' successfully")
	public void validateFilterByTrainingCoach() {
		
		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	    
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin filters by training coach");
		archivedGlobalCoachPage.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_TRAINING_COACH.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Validate filter by training coach successfully.");
		archivedGlobalCoachPage.validateTrainingCoach();
		
	}
	
	@Test(description = "Validate clear filter clear all applied filters successfully")
	public void validateClearFiltersArchivedCoaches() {
		
		getExtentTestLogger().assignCategory("UserManagement Actions- Global Coach");
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	    
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides the coach to be searched and filtered");
		archivedGlobalCoachPage.selectSortByValue(ThriveAppSharedData.MOST_BOOKED_OPTION.getValue());
		archivedGlobalCoachPage.setSearch(globalCoachUser);
		archivedGlobalCoachPage.selectFilterByCoachType(ThriveAppSharedData.COACH_TYPE_GLOBAL_COACH.getValue());
		expertises.add(expertise);
		archivedGlobalCoachPage.selectFilterByExpertise(expertises);
		industry.add(ThriveAppSharedData.INDUSTRY.getValue());
		archivedGlobalCoachPage.selectFilterByIndustry(industry);
		language.add(ThriveAppSharedData.LANGUAGE_ENGLISH.getValue());
		archivedGlobalCoachPage.selectFilterByLanguage(language);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin click on clear filters");
		archivedGlobalCoachPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate all filelds cleared successfully");
		archivedGlobalCoachPage.validateClearFilters();
		
	}
	
	
	@Test(description =  "Validate pagination records match with selected pagination for archived coaches tab", dataProvider = "selectPagination", dataProviderClass = LoginTestDataProvider.class) 
	public void validatePaginationRecordsArchivedCoaches(int val) {
		
		extentTestLocal = extentReports.createTest("Selected pagination"+val, "pagination "+val+"selected successfully");
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method pagination is matching");
		
		getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provide the pagination value ");
		archivedGlobalCoachPage.scrollInVerticalDirection(value).selectPaginationValue(val);
		
		getExtentTestLogger().log(Status.PASS, "Validate pagination shows correct no of records as per selection");
		archivedGlobalCoachPage.validatePaginationRecords(val);
		
	}
	
	@Test(description="Validate SA can scroll between the list Coaches tab")
	public void validateSAScrollArchivedCoachesTab() {		
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin scroll between coaches tab");
		archivedGlobalCoachPage.clickRightScroller();
		
		getExtentTestLogger().log(Status.PASS, "Validate columns after the scrolling");
		archivedGlobalCoachPage.validateColumnsPresent();

	}
	
	@Test(description="Validate actions dropdwown contains all Specicifed options for archived Coaches tab")
	public void validateActionsDropdownOptionsArchivedCoaches() {		
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Archived coaches");
		archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on actions dropdwon");
		archivedGlobalCoachPage.clickActionsDropdown();
		
		getExtentTestLogger().log(Status.PASS, "Validate download,Unarchive, and delete options present in actions dropdown");
		archivedGlobalCoachPage.validateActionDropdownElements();
	}
	
	 @Test(description="Validate Archived Coaches data gets updated when SA archives coach")
	 public void validateArchivedCoachDataUpdate() {
		 
	    	getExtentTestLogger().log(Status.PASS, "Logging in with super admin user credentials");
			thriveHeaderMenuPage = login.login(saLoginDetails);
			
			getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Coaches -> Archived Coaches");
			thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
			
			getExtentTestLogger().log(Status.PASS, "Capture current Coaches data count");
			currentDataCount = userManagementCommonPage.captureCurrentDataCount();

			getExtentTestLogger().log(Status.PASS, "SA user invite Account Manager");
			headers.put("Authorization", getApiAuthCode());
			inviteCoachReqBody = ApiTestDataGenerator.generateInviteCoachReqBody();
			ApiInviteCoachTest inviteCoachTest = new ApiInviteCoachTest();
			inviteCoachTest.getInviteCoachResponse(inviteCoachReqBody, headers);

			if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
				registerCoachReqBody = ApiTestDataGenerator.generateRegisterCoachReqBody();
			}else {
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
			
			getExtentTestLogger().log(Status.PASS, "Search registered coach and archive the coach");
			globalCoachesPage.setSearch(inviteCoachReqBody.getFirstName()).selectUserCheck(inviteCoachReqBody.getFirstName()).selectArchiveCoachAction().ClickYes();
			
			getExtentTestLogger().log(Status.PASS, "Navigate to User Management -> Coaches -> Archived Coaches");
			archivedGlobalCoachPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink().clickArchivedCoaches();
			
			getExtentTestLogger().log(Status.PASS, "Validate Archived Coaches data gets updated");
			usersPage.validateDataUpdate(currentDataCount);
	 }
	 
	
	
	private Response getRegisterCoachResponse(RegisterCoachRequest registerCoachReqBody){
		String token = getRegisterCoachToken(registerCoachReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterCoachUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerCoachReqBody, ObjectMapperType.GSON)
				.post("");
		
	}

}