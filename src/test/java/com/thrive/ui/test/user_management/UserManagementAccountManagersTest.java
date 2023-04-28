package com.thrive.ui.test.user_management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteAMRequest;
import com.thrive.api.pojos.RegisterAMRequest;
import com.thrive.api.test.user_invite_register.ApiInviteAccountManagerTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteAccountManagerDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.invite.InviteAccountManagerPage;
import com.thrive.ui.page.invite.InvitedAccountManagersPage;
import com.thrive.ui.page.register.RegisterAccountMangerPersonalDetailsPage;
import com.thrive.ui.page.user_management.search_filter.AccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.ArchivedAccountManagersPage;
import com.thrive.ui.page.user_management.search_filter.ConfigureEnterprises;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementAccountManagersTest extends UserManagementCommonPage{
	
	
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	AccountManagersPage accountManagersPage = new AccountManagersPage();
	InvitedAccountManagersPage invitedAccountManagersPage = new InvitedAccountManagersPage();
	InviteAccountManagerPage inviteAccountManagerPage = new InviteAccountManagerPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	MailboxPage mailboxPage = new MailboxPage();
	InviteAccountManagerDetails inviteAccountManagerDetails = TestDataGenerator.generateInviteAccountManagerDetails();
	ArchivedAccountManagersPage archivedAccountManagersPage = new ArchivedAccountManagersPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails amLoginDetails = new LoginDetails(accountManagerUser, accountManagerPassword);
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	UsersPage usersPage = new UsersPage();
	RegisterAMRequest registerAMReqBody;
	InviteAMRequest inviteAMReqBody;
	RegisterAccountMangerPersonalDetailsPage registerAccountMangerPersonalDetailsPage= new RegisterAccountMangerPersonalDetailsPage();
	String archiveMessage = ThriveAppSharedData.ACCOUNT_MANAGER_ARCHIVE_MESSAGE.getValue();
	String enableMessage = ThriveAppSharedData.ACCOUNT_MANAGER_ENABLE_MESSAGE.getValue();
	String deleteMessage = ThriveAppSharedData.ACCOUNT_MANAGER_DELETE_MESSAGE.getValue();
	ConfigureEnterprises configureEnterprises = new ConfigureEnterprises();
	Response response;
	Map<String, String> headers = new HashMap<>();
	
	
	@Test(description = "Validate SA successfully download Account managers all records")
	public void validateAccountmanagersDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		accountManagersPage.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		accountManagersPage.validateDownloadAllRecords();
	}
	
	
	@Test(description="Validate SA successfully download invited Account managers all records")
	public void validateDownloadInvitedAccountmanagersAllRecords() throws IOException  {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> invited Account Managers");
		invitedAccountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickInvitedAccountManager();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		invitedAccountManagersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		invitedAccountManagersPage.validateDownloadAllRecords();
	}
	
	

	@Test(description = "Validate SA successfully re-invite account manager")
	public void validateInvitedAccountManageReInvite() {
		String reinviteAccountManager = ThriveAppSharedData.ACCOUNT_MANAGER_REINVITE_MESSAGE.getValue();
		String accountManagerInviteToaster = ThriveAppSharedData.INVITE_ACCOUNT_MANAGER_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
	
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> account managers -> invited account managers");
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited account manager to be searched");
		inviteAccountManagerPage = userManagementCommonPage.selectInviteAccountManager();
		inviteAccountManagerPage.sendInvite(inviteAccountManagerDetails);
		String email = inviteAccountManagerPage.validateAMInviteToaster(accountManagerInviteToaster,inviteAccountManagerDetails.getEmailAddress());
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		invitedAccountManagersPage = accountManagersPage.clickInvitedAccountManager();
		invitedAccountManagersPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS,"Superadmin selects account manager and clicks reinvite account manager option from dropdown");
		alertsAndMessagesPage = invitedAccountManagersPage
				.checkInvitedAccountManagerCheckbox(email)
				.selectReInviteOption().ClickYes();

		getExtentTestLogger().log(Status.PASS, "The account manager reinvited successfully");
		alertsAndMessagesPage.validateToaster(reinviteAccountManager);
		invitedAccountManagersPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS, "validate after re-invite the mail is send to the Account Manager");
		thriveHeaderMenuPage.logout();
		mailboxPage.validateEmailPresentForAM(inviteAccountManagerDetails.getFirstName());
	}
	
	
	@Test(description = "Validate SA successfully Delete Invited account manager")
	public void validateInvitedAccountManagerDelete() {
		
		String accountManagerInviteToaster = ThriveAppSharedData.INVITE_ACCOUNT_MANAGER_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
	
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> account managers -> invited account managers");
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited account manager to be searched");
		inviteAccountManagerPage = userManagementCommonPage.selectInviteAccountManager();
		inviteAccountManagerPage.sendInvite(inviteAccountManagerDetails);
		String email = inviteAccountManagerPage.validateAMInviteToaster(accountManagerInviteToaster,inviteAccountManagerDetails.getEmailAddress());
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		invitedAccountManagersPage = accountManagersPage.clickInvitedAccountManager();
		invitedAccountManagersPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS,"Superadmin selects account manager and clicks delete account manager option from dropdown");
		alertsAndMessagesPage = invitedAccountManagersPage
				.checkInvitedAccountManagerCheckbox(email)
				.selectDeleteAccountManagerAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "The Invited Account Manager Deleted successfully");
		alertsAndMessagesPage.validateToaster(deleteMessage).closeToasterAlert();
		
		getExtentTestLogger().log(Status.PASS, "Validate invited Account Manager not present");
		invitedAccountManagersPage.setSearch(email);
		invitedAccountManagersPage.validateAccountManagerNotPresent(email);
		
	}
	

	@Test(description="Validate SA successfully download archived Account managers all records")
	public void validateDownloadArchivedAccountmanagersAllRecords() throws IOException  {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> archived Account Managers");
		archivedAccountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickArchivedAccountManager();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		archivedAccountManagersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		archivedAccountManagersPage.validateDownloadAllRecords();
	}
	
	@Test(description = "validate SA check errors when invite account manager")
	public void validateAccountManagerErrorMsg() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String email="accountmanager@.com";

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> account managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS,"Superadmin selects invite account manager option from actions dropdown");
		inviteAccountManagerPage = userManagementCommonPage.selectInviteAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides random first name, last name and email address");
		inviteAccountManagerPage.setAmEmail(email);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks send invitation button");
		inviteAccountManagerPage.clickSendInvitation();
		
		getExtentTestLogger().log(Status.PASS, "The error message is displayed if mandatory field is empty and provide wrong value");
		userManagementCommonPage.validateMandatoryFieldErrormsg();
		userManagementCommonPage.validateEmailErrorMsg();
		
	}
	
	
	@Test(description = "Validate SA selects no option while re-invite account manager")
	public void validateSASelectsNoOptionForAccountManageReInvite() {
		String accountManagerInviteToaster = ThriveAppSharedData.INVITE_ACCOUNT_MANAGER_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		String tab = ThriveAppSharedData.INVITED_TAB.getValue();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,"Superadmin navigates to the user management -> account managers -> invited account managers");
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides invited account manager to be searched");
		inviteAccountManagerPage = userManagementCommonPage.selectInviteAccountManager();
		inviteAccountManagerPage.sendInvite(inviteAccountManagerDetails);
		String email = inviteAccountManagerPage.validateAMInviteToaster(accountManagerInviteToaster,inviteAccountManagerDetails.getEmailAddress());
		accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		invitedAccountManagersPage = accountManagersPage.clickInvitedAccountManager();
		invitedAccountManagersPage.setSearch(email);

		getExtentTestLogger().log(Status.PASS,"Superadmin selects account manager and clicks reinvite account manager option from dropdown and selects no option");
		alertsAndMessagesPage = invitedAccountManagersPage
				.checkInvitedAccountManagerCheckbox(email)
				.selectReInviteOption().ClickNo();
		
		getExtentTestLogger().log(Status.PASS, "Validate SA remain in the invited tab");
		userManagementCommonPage.ValidateCurrentTab(tab);
		
	}
	
	
	@Test(description = "Validate SA successfully download Account managers Specific records")
    public void validateAccountmanagersDownloadSpecificRecords() throws IOException {
	   
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
	   
	    getExtentTestLogger().assignCategory("UserManagement Actions - Account Managers");
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();
		
		getExtentTestLogger().log(Status.PASS, "superadmin selects specific records");
		int expected = accountManagersPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = accountManagersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		accountManagersPage.validateDownloadSpecificRecords(expected);
	}
	
	@Test(description = "Validate SA successfully download invited Account managers Specific records")
	public void validateInvitedAccountmanagersDownloadSpecificRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS,
				"Superadmin navigates to the user management -> invited Account Managers");
		invitedAccountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink()
				.clickInvitedAccountManager();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the invited internal coach to download");
		int expected = invitedAccountManagersPage.selectSpecificRecords();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = invitedAccountManagersPage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		invitedAccountManagersPage.validateDownloadSpecificRecords(expected);
	}
	
	
	@Test(description = "Validate SA successfully download archived Account managers Specific records")
	public void validateAccountmanagersDownloadSpecificArchivedRecords() throws IOException {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> archived Account Managers");
		archivedAccountManagersPage=thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink().clickArchivedAccountManager();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the archived account managers to download");
		int expected = archivedAccountManagersPage.selectSpecificRecords();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		userManagementCommonPage = archivedAccountManagersPage.clickDownload();
		
		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		archivedAccountManagersPage.validateDownloadSpecificRecords(expected);
	}
	
	@Test(description = "Validate SA archive account manager successfully")
	public void validateAccountManagerArchiveSuccessfully() throws IOException {

		String archiveMessage = ThriveAppSharedData.ACCOUNT_MANAGER_ARCHIVE_MESSAGE.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);

		registerAMReqBody = ApiTestDataGenerator.generateRegisterAMReqBody();
		registerAMReqBody.setFirstName(inviteAMReqBody.getFirstName());
		registerAMReqBody.setLastName(inviteAMReqBody.getLastName());
		registerAMReqBody.setEmail(inviteAMReqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "SA user invite Account Manager");

		getExtentTestLogger().log(Status.PASS, "SA register account manager successfully");
		response = getRegisterAMResponse(registerAMReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin search and select the account manager to archive");
		accountManagersPage.setSearch(inviteAMReqBody.getEmail());
		accountManagersPage.checkAccountManagerCheckbox(inviteAMReqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin selects archive option from dropdown and click on yes");
		accountManagersPage.selectArchiveAccountManager().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate account manager archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);

		getExtentTestLogger().log(Status.PASS, "Validate archived account manager present inside archived tab");
		accountManagersPage.clickArchivedAccountManager();
		archivedAccountManagersPage.setSearch(inviteAMReqBody.getEmail());
		archivedAccountManagersPage.validateAccountManagerPresent(inviteAMReqBody.getEmail());

	}
	
	@Test(description = "Validate SA enable account manager successfully")
	public void validateAccountManagerEnabledSuccessfully() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);

		registerAMReqBody = ApiTestDataGenerator.generateRegisterAMReqBody();
		registerAMReqBody.setFirstName(inviteAMReqBody.getFirstName());
		registerAMReqBody.setLastName(inviteAMReqBody.getLastName());
		registerAMReqBody.setEmail(inviteAMReqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "SA user invite Account Manager");

		getExtentTestLogger().log(Status.PASS, "SA register account manager successfully");
		response = getRegisterAMResponse(registerAMReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin search and select the account manager to archive");
		accountManagersPage.setSearch(inviteAMReqBody.getEmail());
		accountManagersPage.checkAccountManagerCheckbox(inviteAMReqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin selects archive option from dropdown and click on yes");
		accountManagersPage.selectArchiveAccountManager().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate account manager archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);

		getExtentTestLogger().log(Status.PASS, "Superadmin selects account manager to enable");
		accountManagersPage.clickArchivedAccountManager();
		archivedAccountManagersPage.setSearch(inviteAMReqBody.getEmail());
		archivedAccountManagersPage.checkArchivedAccountManagerCheckbox(inviteAMReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects enable option from dropdown and click on yes");
		archivedAccountManagersPage.selectEnableAccountManager().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate account manager Enabled successfuly");
		alertsAndMessagesPage.validateToaster(enableMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate enabled account manager present inside account managers tab");
		archivedAccountManagersPage.clickAccountManagersButton();
		accountManagersPage.setSearch(inviteAMReqBody.getEmail());
		accountManagersPage.validateAccountManagerPresent(inviteAMReqBody.getEmail());
		
	}
	
	@Test(description = "Validate SA Delete account manager successfully")
	public void validateAccountManagerDeleteSuccessfully() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		headers.put("Authorization", getApiAuthCode());
		inviteAMReqBody = ApiTestDataGenerator.generateInviteAMReqBody();
		ApiInviteAccountManagerTest inviteAMTest = new ApiInviteAccountManagerTest();
		inviteAMTest.getInviteAMResponse(inviteAMReqBody, headers);

		registerAMReqBody = ApiTestDataGenerator.generateRegisterAMReqBody();
		registerAMReqBody.setFirstName(inviteAMReqBody.getFirstName());
		registerAMReqBody.setLastName(inviteAMReqBody.getLastName());
		registerAMReqBody.setEmail(inviteAMReqBody.getEmail());
		
		System.out.println("email "+inviteAMReqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "SA user invite Account Manager");

		getExtentTestLogger().log(Status.PASS, "SA register account manager successfully");
		response = getRegisterAMResponse(registerAMReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "SA provides username,password and click on login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Account Managers");
		this.accountManagersPage = thriveHeaderMenuPage.clickUserManagementLink().clickAccountManagersLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin search and select the account manager to archive");
		accountManagersPage.setSearch(inviteAMReqBody.getEmail());
		accountManagersPage.checkAccountManagerCheckbox(inviteAMReqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin selects archive option from dropdown and click on yes");
		accountManagersPage.selectArchiveAccountManager().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate account manager archived successfuly");
		alertsAndMessagesPage.validateToaster(archiveMessage);

		getExtentTestLogger().log(Status.PASS, "Superadmin selects account manager to delete");
		accountManagersPage.clickArchivedAccountManager();
		archivedAccountManagersPage.setSearch(inviteAMReqBody.getEmail());
		archivedAccountManagersPage.checkArchivedAccountManagerCheckbox(inviteAMReqBody.getEmail().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects delete option from dropdown and click on yes");
		archivedAccountManagersPage.selectDeleteAccountManagerAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate account manager deleted successfuly");
		alertsAndMessagesPage.validateToaster(deleteMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate deleted account manager not present inside archived account managers tab");
		archivedAccountManagersPage.setSearch(inviteAMReqBody.getEmail());
		archivedAccountManagersPage.validateAccountManagerNotpresent(inviteAMReqBody.getEmail().toLowerCase());
	}

	private Response getRegisterAMResponse(RegisterAMRequest registerAMReqBody){
		String token = getRegisterTokenAM(registerAMReqBody.getFirstName());
		return RestAssured.given()
				.spec(getRegisterAMUserReqSpec()).queryParam("token", token)
				.and()
				.when()
				.body(registerAMReqBody, ObjectMapperType.GSON)
				.post("");
	}
	
	

}