package com.thrive.ui.test.user_management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteEnterpriseRequest;
import com.thrive.api.pojos.RegisterEnterpriseRequest;
import com.thrive.api.test.user_invite_register.ApiInviteEnterpriseTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEnterpriseDetails;
import com.thrive.common.testdata.pojos.user_details.CreateEnterpriseDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DateTimeUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.credits.RemoveCreditsPage;
import com.thrive.ui.page.enterprise.CreateEnterprisePage;
import com.thrive.ui.page.invite.InviteEnterprisePage;
import com.thrive.ui.page.invite.InvitedEnterprisesPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ArchivedEnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementEnterprisesTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	CreateEnterprisePage createEnterprisePage = new CreateEnterprisePage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	ThriveLoginPage login = new ThriveLoginPage();
	MailboxPage mailboxPage = new MailboxPage();
	AllocateCreditsPage allocateCreditsPage = new AllocateCreditsPage();
	InviteEnterprisePage inviteEnterprisePage =new InviteEnterprisePage();
	ArchivedEnterprisesPage archivedEnterprisesPage = new ArchivedEnterprisesPage();
	InvitedEnterprisesPage invitedEnterprisesPage = new InvitedEnterprisesPage();
	RegisterEnterpriseDetailsPage enterpriseDetailsPage = new RegisterEnterpriseDetailsPage();
	RemoveCreditsPage removeCreditsPage = new RemoveCreditsPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails amloginDetails = new LoginDetails(accountManagerUser, accountManagerPassword);
	String nextYearDate = DateTimeUtils.getNextYearDate();
	InviteEnterpriseDetails inviteEnterpriseDetails = TestDataGenerator.generateInviteEnterpriseDetails();
	String entCreatedToaster = ThriveAppSharedData.ENT_CREATED_MESSAGE.getValue();
	String email="ent@.com";
	String quantity = "1000";
	RegisterEnterpriseRequest registerEntReqBody;
	String archiveMessage = ThriveAppSharedData.ENT_ARCHIVE_MESSAGE.getValue();
	String enableMessage = ThriveAppSharedData.ENT_ENABLE_MESSAGE.getValue();
	String deleteMessage = ThriveAppSharedData.ENT_DELETE_MESSAGE.getValue();
	InviteEnterpriseRequest inviteEntReqBody;
	Response response;
	Map<String, String> headers = new HashMap<>();
	
	@Test(description = "Validate SA successfully download Enterprise all records")
	public void validateEnterpriseDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterpriseses");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		enterprisesPage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		enterprisesPage.validateDownloadAllRecords();
	}
	
	
	@Test(description = "Validate SA create enterprise successfully")
	public void validateEnterpriseCreateEnterprise() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		CreateEnterpriseDetails createEnterpriseDetails = TestDataGenerator.generateCreateEnterpriseDetails();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects create enterprise options from actions dropdown");
		createEnterprisePage = userManagementCommonPage.selectCreateEnterpriseAction();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin  provides data to create enterprise");
		createEnterprisePage.submitCreateEnterpriseDetails(createEnterpriseDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on create enterprise button");
		createEnterprisePage.clickCreateEnterpriseButton();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise created successfully");
		alertsAndMessagesPage.validateCreateEnterpriseToaster();
		
		getExtentTestLogger().log(Status.PASS, "Created enterprise searched successfully");
		thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
	    enterprisesPage.setSearch(createEnterpriseDetails.getEntname());
	    enterprisesPage.validateEnterpriseNamePresent(createEnterpriseDetails.getEntname());
	}
	
	
	@Test(description = "Validate SA successfully reinvite the enterprise")
	public void validateInvitedEnterpriseReInvite() {
		String subjectWelcome = ThriveAppSharedData.ENT_WELCOME_EMAIL_SUBJECT.getValue();
		String subjectWelcomeFrench = ThriveAppSharedData.CLIENT_WELCOME_EMAIL_SUBJECT_RM_FRENCH.getValue();
		String reinviteEntToaster = ThriveAppSharedData.ENT_REINVITE_MESSAGE.getValue(); 
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> client -> invited client");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickEnterpriseButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited enterprise to be searched");
		inviteEnterprisePage = userManagementCommonPage.selectInviteEnterpriseAction();
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		String email = inviteEnterprisePage.validateEntInviteToaster(entInviteToaster,inviteEnterpriseDetails.getEmailAddress());
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		invitedEnterprisesPage = enterprisesPage.clickInvitedEnterprise();
		userManagementCommonPage= invitedEnterprisesPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise and clicks re-invite option from actions dropdown");
		alertsAndMessagesPage = invitedEnterprisesPage.clickEnterpriseCheckbox(email.toLowerCase()).selectReInviteEnterprise();
		alertsAndMessagesPage.ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "The enterprise reinvited successfully");
		alertsAndMessagesPage.validateToaster(reinviteEntToaster);
		
		getExtentTestLogger().log(Status.PASS, "Validate after re-invite the mail is send to the enterprise");
		thriveHeaderMenuPage.logout();
		if(Config.getLocalizationLanguage().contains("en")){
		mailboxPage =  new MailboxPage().searchYahooMail(inviteEnterpriseDetails.getFirstName(), subjectWelcome);
		} else {
		mailboxPage =  new MailboxPage().searchYahooMail(inviteEnterpriseDetails.getFirstName(), subjectWelcomeFrench);
		}
		mailboxPage.validateEmailPresent(inviteEnterpriseDetails.getFirstName());
	}
	
	@Test(description = "Validate SA successfully delete the Invited enterprise")
	public void validateInvitedEnterpriseDelete() {
		
		String deleteToaster = ThriveAppSharedData.ENT_DELETE_INVITED_MESSAGE.getValue();
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> client -> invited client");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickEnterpriseButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited enterprise to be searched");
		inviteEnterprisePage = userManagementCommonPage.selectInviteEnterpriseAction();
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		String email = inviteEnterprisePage.validateEntInviteToaster(entInviteToaster,inviteEnterpriseDetails.getEmailAddress());
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		invitedEnterprisesPage = enterprisesPage.clickInvitedEnterprise();
		userManagementCommonPage= invitedEnterprisesPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise and clicks re-invite option from actions dropdown");
		alertsAndMessagesPage = invitedEnterprisesPage.clickEnterpriseCheckbox(email.toLowerCase()).selectDeleteEnterpriseAction();
		alertsAndMessagesPage.ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "The Invited enterprise Deleted successfully");
		alertsAndMessagesPage.validateToaster(deleteToaster);
		
		getExtentTestLogger().log(Status.PASS, "Validate invited enterprise not present");
		invitedEnterprisesPage.setSearch(email);
		invitedEnterprisesPage.validateEnterpriseNotPresent(email);
		
	}

	@Test(description = "Validate superadmin successfully download invite an enterprise all records")
	public void validateInviteAnEnterpriseDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited an enterprise");
		this.invitedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickInvitedEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		invitedEnterprisesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		invitedEnterprisesPage.validateDownloadAllRecords();
	}
	
	@Test(description = "Validate superadmin successfully download archived enterprise all records")
	public void validateArchivedEnterpriseDownloadAllRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> enterprise -> archived");
		archivedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickArchivedEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		archivedEnterprisesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		archivedEnterprisesPage.validateDownloadAllRecords();

	}
	
	@Test(description = "Validate Next Year date present when allocating credits for enterprise")
	public void validateNextYearDatePresentWhenAllocateCreditsToEnterprise() {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to allocate credits");
		enterprisesPage.setSearch(eaMutableEmail);
		enterprisesPage.clickEnterprisesCheckbox(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "Account manager selects allocate credits option from actions dropdown");
		userManagementCommonPage.selectAllocateCreditsAction();
		
		getExtentTestLogger().log(Status.PASS, "Validate next year date is present");
		allocateCreditsPage.validateDatePresent(nextYearDate).clickCancelLink();
		
	}

	@Test(description = "Validate SA selects no option while reinvite the enterprise")
	public void validateSASelectsNoOptionForEnterpriseReInvite() {
		String entInviteToaster = ThriveAppSharedData.INVITE_ENTERPRISE_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String tab = ThriveAppSharedData.INVITED_TAB.getValue();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to user management -> client -> invited client");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickEnterpriseButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited enterprise to be searched");
		inviteEnterprisePage = userManagementCommonPage.selectInviteEnterpriseAction();
		inviteEnterprisePage.sendInvite(inviteEnterpriseDetails);
		String email = inviteEnterprisePage.validateEntInviteToaster(entInviteToaster,inviteEnterpriseDetails.getEmailAddress());
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		invitedEnterprisesPage = enterprisesPage.clickInvitedEnterprise();
		userManagementCommonPage= invitedEnterprisesPage.setSearch(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise and clicks re-invite option from actions dropdown and click no option");
		alertsAndMessagesPage = invitedEnterprisesPage.clickEnterpriseCheckbox(email.toLowerCase()).selectReInviteEnterprise();
		alertsAndMessagesPage.ClickNo();
		
		getExtentTestLogger().log(Status.PASS, "Validate SA remain in the invited tab");
		userManagementCommonPage.ValidateCurrentTab(tab);

	}
	
	@Test(description = "Validate SA invite error message for mandatory and invalid input")
	public void validateEnterpriseErrorValidationSA() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects invite enterprise option from actions dropdown");
		inviteEnterprisePage = userManagementCommonPage.selectInviteEnterpriseAction();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		inviteEnterprisePage.setEmailAddressInput(email);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on send invitation button");
		inviteEnterprisePage.clickSendInvitationButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate Error Message For ent empty and invalid field");
		userManagementCommonPage.validateMandatoryFieldErrormsg();
		userManagementCommonPage.validateEmailErrorMsg();
	}
	

	@Test(description = "Validate AM invite error message for mandatory and invalid input")
	public void validateEnterpriseErrorValidationAM() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Account Manager provides username,password and click on login");
		thriveHeaderMenuPage = login.login(amloginDetails);

		getExtentTestLogger().log(Status.PASS, "Account Manager navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Account Manager selects invite enterprise option from actions dropdown");
		inviteEnterprisePage = userManagementCommonPage.selectInviteEnterpriseAction();

		getExtentTestLogger().log(Status.PASS, "Account Manager provides username,password and click on login");
		inviteEnterprisePage.setEmailAddressInput(email);
		
		getExtentTestLogger().log(Status.PASS, "Account Manager clicks on send invitation button");
		inviteEnterprisePage.clickSendInvitationButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate Error Message For ent empty and invalid field");
		userManagementCommonPage.validateMandatoryFieldErrormsg();
		userManagementCommonPage.validateEmailErrorMsg();
	}
	
	
	@Test(description="Validate SA Delete credits from enterprise error validation")
	public void validateSARemoveCreditsEnterpriseErrorValidation() {
		
		getExtentTestLogger().assignCategory("User management - actions");
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		enterprisesPage.setSearch(eaMutableEmail);
		enterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaMutableEmail);

		getExtentTestLogger().log(Status.PASS, "Superadmin click on the remove credits icon");
		enterpriseDetailsPage.clickRemoveCreditsIcon();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides no. of credits to remove");
		removeCreditsPage.setQuantityToDelete(quantity);

		getExtentTestLogger().log(Status.PASS, "Validate error message is present");
		removeCreditsPage.validateErrorMessgaePresent();
		
	}


	@Test(description = "Validate SA successfully download Enterprise Specific records")
	public void validateEnterpriseDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterpriseses");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to download");
		int expected = enterprisesPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = enterprisesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		enterprisesPage.validateDownloadSpecificRecords(expected);
	}
	
	
	@Test(description = "Validate superadmin successfully download invite an enterprise specific records")
	public void validateInviteAnEnterpriseDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited an enterprise");
		invitedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickInvitedEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived enterprise to download");
		int expected = invitedEnterprisesPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = invitedEnterprisesPage.clickDownload();
				
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		invitedEnterprisesPage.validateDownloadSpecificRecords(expected);
    
    }
	
	@Test(description = "Validate superadmin successfully download archived enterprise Specific records")
	public void validateArchivedEnterpriseDownloadSpecificRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> enterprise -> archived");
		archivedEnterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu().clickArchivedEnterprise();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived enterprise to download");
		int expected = archivedEnterprisesPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = archivedEnterprisesPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		invitedEnterprisesPage.validateDownloadSpecificRecords(expected);
	}
	
	
	@Test(description = "Validate SA archive enterprise successfully")
	public void validateSAArchiveEnterpriseSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String archiveMessage = ThriveAppSharedData.ENT_ARCHIVE_MESSAGE.getValue();
		
		headers.put("Authorization", getApiAuthCode());
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		ApiInviteEnterpriseTest inviteEntTest = new ApiInviteEnterpriseTest();
		inviteEntTest.getInviteEntResponse(inviteEntReqBody, headers);
		
		registerEntReqBody = ApiTestDataGenerator.generateRegisterEntReqBody();
		registerEntReqBody.setName(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite enterprise");

		getExtentTestLogger().log(Status.PASS, "SA register enterprise successfully");
		response = getRegisterEntResponse(registerEntReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to archive");
		enterprisesPage.setSearch(inviteEntReqBody.getCompany());
		enterprisesPage.checkEntepriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		enterprisesPage.selectArchiveEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate archived enterprise present inside archived tab");
		enterprisesPage.clickArchivedEnterprise();
		archivedEnterprisesPage.setSearch(inviteEntReqBody.getCompany());
		archivedEnterprisesPage.validateEnterprisePresent(inviteEntReqBody.getCompany());
	}
	
	
	@Test(description = "Validate SA Enable enterprise successfully")
	public void validateSAEnableEnterpriseSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		ApiInviteEnterpriseTest inviteEntTest = new ApiInviteEnterpriseTest();
		inviteEntTest.getInviteEntResponse(inviteEntReqBody, headers);
		
		registerEntReqBody = ApiTestDataGenerator.generateRegisterEntReqBody();
		registerEntReqBody.setName(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite enterprise");

		getExtentTestLogger().log(Status.PASS, "SA register enterprise successfully");
		response = getRegisterEntResponse(registerEntReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to archive");
		enterprisesPage.setSearch(inviteEntReqBody.getCompany());
		enterprisesPage.checkEntepriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		enterprisesPage.selectArchiveEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprise to enable");
		enterprisesPage.clickArchivedEnterprise();
		archivedEnterprisesPage.setSearch(inviteEntReqBody.getCompany());
		archivedEnterprisesPage.clickEnterpriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select enable option from actions dropdown");
		archivedEnterprisesPage.selectUnArchiveEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise enabled successfuly");
		alertsAndMessagesPage.validateToaster(enableMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate enabled enterprise present inside archived tab");
		archivedEnterprisesPage.clickEnterprises();
		enterprisesPage.setSearch(inviteEntReqBody.getCompany());
		enterprisesPage.validateEnterpriseNamePresent(inviteEntReqBody.getCompany());
	}
	
	@Test(description = "Validate SA Delete enterprise successfully")
	public void validateSADeleteEnterpriseSuccessfully() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		headers.put("Authorization", getApiAuthCode());
		inviteEntReqBody = ApiTestDataGenerator.generateInviteEnterpriseReqBody();
		ApiInviteEnterpriseTest inviteEntTest = new ApiInviteEnterpriseTest();
		inviteEntTest.getInviteEntResponse(inviteEntReqBody, headers);
		
		registerEntReqBody = ApiTestDataGenerator.generateRegisterEntReqBody();
		registerEntReqBody.setName(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "SA user invite enterprise");

		getExtentTestLogger().log(Status.PASS, "SA register enterprise successfully");
		response = getRegisterEntResponse(registerEntReqBody);
		
		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200);
		
		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enterprise to archive");
		enterprisesPage.setSearch(inviteEntReqBody.getCompany());
		enterprisesPage.checkEntepriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		enterprisesPage.selectArchiveEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects enterprisse to delete");
		enterprisesPage.clickArchivedEnterprise();
		archivedEnterprisesPage.setSearch(inviteEntReqBody.getCompany());
		archivedEnterprisesPage.clickEnterpriseCheckbox(inviteEntReqBody.getCompany());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select Delete option from actions dropdown");
		archivedEnterprisesPage.selectDeleteEnterpriseAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate enterprise deleted successfuly");
		alertsAndMessagesPage.validateToaster(deleteMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate deleted enterprise not present inside enterprises tab");
		archivedEnterprisesPage.setSearch(inviteEntReqBody.getCompany());
		archivedEnterprisesPage.validateEnterpriseNotPresent(inviteEntReqBody.getCompany());
	}
	
	
	public Response getRegisterEntResponse(RegisterEnterpriseRequest registerEntReqBody){
		String token = getRegisteredEntToken(inviteEntReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterEntUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerEntReqBody, ObjectMapperType.GSON)
				.post("");
		
	}

}