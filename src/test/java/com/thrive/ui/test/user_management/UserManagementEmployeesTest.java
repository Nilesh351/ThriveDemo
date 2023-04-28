package com.thrive.ui.test.user_management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.thrive.api.data_utils.ApiTestDataGenerator;
import com.thrive.api.pojos.InviteEmployeeRequest;
import com.thrive.api.pojos.RegisterClientRequest;
import com.thrive.api.test.user_invite_register.ApiInviteEmployeeTest;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.providers.LoginTestDataProvider;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.DateTimeUtils;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.MailboxPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.credits.EmployeeCreditPage;
import com.thrive.ui.page.credits.RemoveCreditsPage;
import com.thrive.ui.page.enterprise.EnterpriseEmployeePage;
import com.thrive.ui.page.invite.EnterpriseInviteEmployeePage;
import com.thrive.ui.page.invite.EnterpriseInvitedEmployeePage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.register.RegisterClientRegistrationDetailsPage;
import com.thrive.ui.page.user_management.search_filter.ArchivedEmployeesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class UserManagementEmployeesTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage;
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	LoginDetails clientLoginDetails = new LoginDetails(clientUser, clientPassword);
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	EnterpriseInviteEmployeePage enterpriseInviteEmployeePage = new EnterpriseInviteEmployeePage();
	EnterpriseInvitedEmployeePage invitedEmployee = new EnterpriseInvitedEmployeePage();
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	EnterpriseEmployeePage employeePage = new EnterpriseEmployeePage();
	UserManagementPage userManagementPage = new UserManagementPage();
	AllocateCreditsPage allocateCreditsPage = new AllocateCreditsPage();
	ArchivedEmployeesPage archivedEmployeesPage = new ArchivedEmployeesPage();
	RegisterClientRegistrationDetailsPage regDetailsPage;
	RegisterClientEmploymentStatusPage empStatusPage;
	RegisterClientCareerDetailsPage careerDetailsPage;
	RemoveCreditsPage removeCreditsPage = new RemoveCreditsPage();
	LoginDetails employeeLoginDetails;
	MailboxPage mailboxPage = new MailboxPage();
	ExtentTest extentTestLocal;
	String email = "employee@.com";
	String creditsAmount = "10";
	String nextYearDate = DateTimeUtils.getNextYearDate();
	String allocateCreditToaster = ThriveAppSharedData.ALLOCATE_CREDIT_NOTIFICATION.getValue();
	String removeCreditToasterEA = ThriveAppSharedData.REMOVE_CREDIT_MESSAGE_EA.getValue();
	String removeCreditToaster = ThriveAppSharedData.REMOVE_CREDIT_NOTIFICATION.getValue();
	String enableMessage = ThriveAppSharedData.CLIENT_ENABLED_SUCCESSFULLY_MESSAGE.getValue();
	String deleteMessage = ThriveAppSharedData.CLIENT_DELETED_SUCCESSFULY_MESSAGE.getValue();
	EmployeeCreditPage employeeCreditPage = new EmployeeCreditPage();
	InviteEmployeeDetails employeeDeatils = TestDataGenerator.generateInviteEmployeeDetails();
	RegisterClientRequest registerClientReqBody;
	InviteEmployeeRequest inviteEmployeereqBody;
	Response response;
	String value = "500";
	Map<String, String> headers = new HashMap<>();
	Map<String, String> reqParams = new HashMap<>();

	@Test(description = "validate EA check errors when invite Employee")
	public void validateEmployeeInviteErrorvalidationEA() {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to Employees -> Invited Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides email address");
		enterpriseInviteEmployeePage.setEmailAddressInput(email);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin  clicks the send invitation button");
		enterpriseInviteEmployeePage.clickSendInvitationButton();

		getExtentTestLogger().log(Status.PASS,
				"The error message is displayed if mandatory field is empty and provide wrong value");
		userManagementCommonPage.validateMandatoryFieldErrormsg();
		userManagementCommonPage.validateEmailErrorMsg();
	}

	@Test(description = "Validate EA invite employee successfully.")
	public void validateEAInviteEmployeeSuccessfully() {

		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "EA navigates to Employees -> Invited Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);

		getExtentTestLogger().log(Status.PASS, "Employee invited successfully");
		String email = enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,
				employeeDeatils.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Invited Employee searched successfully");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
		invitedEmployee = employeePage.clickInvitedEmployees();
		invitedEmployee.setSearch(email);
		invitedEmployee.validateInvitedEmployeeEmailPresent(email);
	}

	@Test(description = "validate Only enterprise admin have access to employee tab")
	public void validateEmployeeTabPresentForEA() {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates Employees");
		userManagementPage = thriveHeaderMenuPage.navigateToUsers();

		getExtentTestLogger().log(Status.PASS, "validate employee tab present");
		userManagementPage.validateEmployeeElementVisible();
	}
	
	@Test(description = "Validate Login with user types and ensure this employee tab is not visible to them", dataProvider = "LoginDataProviderEmployee", dataProviderClass = LoginTestDataProvider.class)
	public void validateEmployeeTabNotPresent(LoginDetails loginData) {

		extentTestLocal = extentReports.createTest(loginData.getTestName(), loginData.getTestDescription());
		ExtentReportUtils.setExtentTest(extentTestLocal);

		LOGGER.info("onTestStart method - " + loginData.getTestName() + " start");

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(loginData);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees");
		userManagementPage = thriveHeaderMenuPage.clickUserManagementLink();

		getExtentTestLogger().log(Status.PASS, "validate employee tab not present");
		userManagementPage.validateEmployeeElementIsNotVisible();
	}

	@Test(description = "Validate EA register employee successfully.")
	public void validateRegisterEmployee() {

		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to Employees -> Invited Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();
		
		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);

		getExtentTestLogger().log(Status.PASS, "Employee invited successfully");
		String email = enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,
				employeeDeatils.getEmailAddress());
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage = new MailboxPage().searchClientMailAndClick(employeeDeatils.getFirstName());

		getExtentTestLogger().log(Status.PASS, "Enter client information");
		empStatusPage = new RegisterClientInformationPage()
				.submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());

		getExtentTestLogger().log(Status.PASS, "Enter client employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());

		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
				.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());

		getExtentTestLogger().log(Status.PASS, "Login with newly registerd client and validate login successful");
		launchThriveApp(Config.getLoginPageURL());
		employeeLoginDetails = new LoginDetails(email, ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(employeeLoginDetails).validateLoginSuccessful();

	}

	@Test(description = "Validate EA allocate credits to employee successfully.")
	public void validateEAEmployeeCreditAllocation() {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to Employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "User search the client and click on it");
		userManagementCommonPage = employeePage.setSearch(clientUser);
		employeeCreditPage = employeePage.clickEmployeeNameLink(clientUser);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of employee");
		int curCredits = employeeCreditPage.employeeCreditCount();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects the employee to allocate credits");
		employeePage.setSearch(clientUser);
		employeePage.clickEmployeeCheckboxEmail(clientUser);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin select allocate credits option from actions dropdown");
		userManagementCommonPage.selectAllocateCreditsActionEA();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides credits and date data");
		allocateCreditsPage.setNumberOfCredits(creditsAmount).setExpiryDate(nextYearDate);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin click on allocate credits button");
		allocateCreditsPage.clickAllocateCreditButton().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits assigned successfully to the client");
		alertsAndMessagesPage.validateCreditAllocationToaster(getResultByKey(allocateCreditToaster))
				.closeToasterAlert();

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the employee profile");
		refreshPage();
		userManagementCommonPage = employeePage.setSearch(clientUser);
		employeeCreditPage = employeePage.clickEmployeeNameLink(clientUser);
		int updatedCredit = employeeCreditPage.employeeCreditCount();
		employeeCreditPage.validateAssignedCreditsReflected(curCredits, updatedCredit, creditsAmount);
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Assigned credits should reflected in Employee login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);
		int ACredit = thriveHeaderMenuPage.AvailablCredits();
		thriveHeaderMenuPage.ValidateCreditsAssignedReflectedAtClient(curCredits, ACredit, creditsAmount);

	}

	@Test(description = "Validate EA remove credits from employee successfully.")
	public void validateEAEmployeeCreditRemoved() {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the Employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "User search the client and click on it");
		userManagementCommonPage = employeePage.setSearch(clientUser);
		employeeCreditPage = employeePage.clickEmployeeNameLink(clientUser);

		getExtentTestLogger().log(Status.PASS, "User gets the current credit count of employee");
		int curCredits = employeeCreditPage.employeeCreditCount();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects the employee to allocate credits");
		employeePage.setSearch(clientUser);
		employeePage.clickEmployeeCheckboxEmail(clientUser);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin select remove credits option from actions dropdown");
		userManagementCommonPage.selectRemoveCreditsAction();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides no. of credits to remove");
		removeCreditsPage.setNumberOfCredits(creditsAmount);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on remove credit button");
		removeCreditsPage.clickRemoveCredits().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Credits removed successfully to the Employee");
		alertsAndMessagesPage.validateRemoveCreditToaster(getResultByKey(removeCreditToaster)).closeToasterAlert();

		getExtentTestLogger().log(Status.PASS, "Credits are reflected in the employee profile");
		refreshPage();
		userManagementCommonPage = employeePage.setSearch(clientUser);
		employeeCreditPage = employeePage.clickEmployeeNameLink(clientUser);
		int updatedCredit = employeeCreditPage.employeeCreditCount();
		employeeCreditPage.validateRemovedCreditsReflected(curCredits, updatedCredit, creditsAmount);
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Removed credits should reflected in Employee login");
		launchThriveApp(Config.getLoginPageURL());
		thriveHeaderMenuPage = login.loginIgnoreAccept(clientLoginDetails);
		int ACredit = thriveHeaderMenuPage.AvailablCredits();
		thriveHeaderMenuPage.ValidateCreditsRemovedReflectedClient(curCredits, ACredit, creditsAmount);

	}

	@Test(description = "Validate EA successfully re-invite employee")
	public void validateInvitedEmployeeReInvite() {
		String reinviteEmployee = ThriveAppSharedData.EMPLOYEE_REINVITE_MESSAGE.getValue();
		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "EA provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);



		getExtentTestLogger().log(Status.PASS,"EA navigates to the user management -> employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "EA invites employee and searches it in invited employees tab");
		enterpriseInviteEmployeePage = employeePage.clickInviteEmployee();
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);
		String email = enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,employeeDeatils.getEmailAddress());
		invitedEmployee = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();
		invitedEmployee.setSearch(email);

		getExtentTestLogger().log(Status.PASS, "EA selects employee and clicks reinvite employee option from dropdown");
		alertsAndMessagesPage = invitedEmployee.clickInvitedEmployeeCheckbox(email).selectReInviteOption().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validte re-invitation toaster");
		alertsAndMessagesPage.validateToaster(reinviteEmployee);

		getExtentTestLogger().log(Status.PASS, "validate after re-invite the mail is send to the employee");
		thriveHeaderMenuPage.logout();
		mailboxPage.validateEmailPresentForClient(employeeDeatils.getFirstName());
	}

	@Test(description = "Validate EA successfully download employees all records")
	public void validateEmployeeDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "EA provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "EA navigates to the user management -> Employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		employeePage.clickDownload();

		getExtentTestLogger().log(Status.PASS,
				"Downloaded all records successfully in .csv format and count is accurate");
		employeePage.validateDownloadAllRecords();
	}
	
	@Test(description = "Validate EA successfully download invited employees all records")
	public void validateInvitedEmployeeDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "EA provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "EA navigates to the user management -> Employees -> Invited Employees");
		invitedEmployee = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		invitedEmployee.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		invitedEmployee.validateDownloadAllRecords();
	}
	
	@Test(description = "Validate SA successfully Delete Invited Employee")
	public void validateInvitedEmployeeDelete() {

		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		String deleteMessage = ThriveAppSharedData.INVITED_CLIENT_DELETED_SUCCESSFULY_MESSAGE.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "EA provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS,"EA navigates to the user management -> employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "EA invites employee and searches it in invited employees tab");
		enterpriseInviteEmployeePage = employeePage.clickInviteEmployee();
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);
		String email = enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,employeeDeatils.getEmailAddress());
		invitedEmployee = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();
		invitedEmployee.setSearch(email);

		getExtentTestLogger().log(Status.PASS, "EA selects employee and clicks reinvite employee option from dropdown");
		alertsAndMessagesPage = invitedEmployee.clickInvitedEmployeeCheckbox(email).selectDeleteEmployeeAction()
				.ClickYes();

		getExtentTestLogger().log(Status.PASS, "The Invited Employee Deleted successfully");
		alertsAndMessagesPage.validateToaster(deleteMessage);
		
		getExtentTestLogger().log(Status.PASS, "Validate invited Employee not present");
		invitedEmployee.setSearch(email);
		invitedEmployee.validateAccountManagerNotPresent(email);

	}

	@Test(description = "Validate EA enable employee successfully")
	public void validateSAEnableEmployeeSuccessfully() throws IOException {

		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to Employees -> Invited Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);

		getExtentTestLogger().log(Status.PASS, "Employee invited successfully");
		enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,
				employeeDeatils.getEmailAddress());
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage = new MailboxPage().searchClientMailAndClick(employeeDeatils.getFirstName());

		getExtentTestLogger().log(Status.PASS, "Enter employee information");
		empStatusPage = new RegisterClientInformationPage()
				.submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());

		getExtentTestLogger().log(Status.PASS, "Enter employee employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());

		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
				.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Validate employee archived successfuly");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects the employee to archive");
		employeePage.setSearch(employeeDeatils.getEmailAddress());
		employeePage.clickEmployeeCheckboxEmail(employeeDeatils.getEmailAddress().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin select archive option from actions dropdown");
		employeePage.selectArchiveEmployeeAction().ClickYes();
		
		getExtentTestLogger().log(Status.PASS, "Validate archived employee toaster");
		employeePage.validateEmployeeArchiveToaster();
		
		getExtentTestLogger().log(Status.PASS, "EA selects employee to enable");
		employeePage.clickArchivedLink().setSearch(employeeDeatils.getEmailAddress());
		
		getExtentTestLogger().log(Status.PASS, "Validate employee archived successfully");
		employeePage.validateEmployeePresent(employeeDeatils.getEmailAddress().toLowerCase());
		
	}
	
	@Test(description = "Validate EA successfully download archived employees all records")
	public void validateArchivedEmployeeDownloadAllRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "EA provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "EA navigates to the user management -> Employees -> Archived Employees");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();

		getExtentTestLogger().log(Status.PASS, "User clicks on download option from action dropdown");
		employeePage.clickDownload();

		getExtentTestLogger().log(Status.PASS, "Downloaded all records successfully in .csv format and count is accurate");
		employeePage.validateDownloadAllRecords();
	}
	

	@Test(description = "Validate EA enable employee successfully")
	public void validateEAEnableEmployeeSuccessfully() throws IOException {
        
		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();
		
		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to Employees -> Invited Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);
				
		getExtentTestLogger().log(Status.PASS, "Employee invited successfully");
		enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,employeeDeatils.getEmailAddress());
		thriveHeaderMenuPage.logout();
		
		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage =  new MailboxPage().searchClientMailAndClick(employeeDeatils.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "Enter employee information");
		empStatusPage = new RegisterClientInformationPage().submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());
		
		getExtentTestLogger().log(Status.PASS, "Enter employee employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());
		
		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
		.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Validate employee archived successfuly");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects the employee to archive");
		employeePage.setSearch(employeeDeatils.getEmailAddress());
		employeePage.clickEmployeeCheckboxEmail(employeeDeatils.getEmailAddress().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin select archive option from actions dropdown");
		employeePage.selectArchiveEmployeeAction().ClickYes();
		

		getExtentTestLogger().log(Status.PASS, "Validate employee archived successfuly");
		employeePage.validateEmployeeArchiveToaster();

		getExtentTestLogger().log(Status.PASS, "EA selects employee to enable");
		employeePage.clickArchivedLink();
		employeePage.setSearch(employeeDeatils.getEmailAddress());
		employeePage.clickEmployeeCheckboxEmail(employeeDeatils.getEmailAddress().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "Superadmin select enable option from actions dropdown");
		employeePage.selectUnArchiveClientAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate employee enable successfuly");
		alertsAndMessagesPage.validateToaster(enableMessage);

		getExtentTestLogger().log(Status.PASS, "Validate enabled employee present inside employee tab");
		employeePage = archivedEmployeesPage.clickEmployees();
		employeePage.setSearch(employeeDeatils.getEmailAddress());
		employeePage.validateEmployeePresent(employeeDeatils.getEmailAddress().toLowerCase());

	}

	@Test(description = "Validate EA deletes archived employee successfully")
	public void validateDeleteArchivedEmployee() throws IOException {

		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, " Enterprise admin navigates to Employees -> Invited Employees");
		enterpriseInviteEmployeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInviteEmployee();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin provides all details and click on send invitation button");
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);

		getExtentTestLogger().log(Status.PASS, "Employee invited successfully");
		enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,
				employeeDeatils.getEmailAddress());
		thriveHeaderMenuPage.logout();

		getExtentTestLogger().log(Status.PASS, "Login to mailbox and accept the invite");
		mailboxPage = new MailboxPage().searchClientMailAndClick(employeeDeatils.getFirstName());

		getExtentTestLogger().log(Status.PASS, "Enter employee information");
		empStatusPage = new RegisterClientInformationPage()
				.submitPersonalDetails(TestDataGenerator.generateClientPersonalDetails());

		getExtentTestLogger().log(Status.PASS, "Enter employee employment information");
		careerDetailsPage = empStatusPage.submitEmploymentDetails(TestDataGenerator.generateClientEmploymentDetails());

		getExtentTestLogger().log(Status.PASS, "Accept the privacy policy and register");
		careerDetailsPage.registerClientInfo()
				.validateClientRegistrationSuccessful(ThriveAppSharedData.CLIENT_REG_SUCCESS_MESSAGE.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Validate employee archived successfuly");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects the employee to archive");
		employeePage.setSearch(employeeDeatils.getEmailAddress());
		employeePage.clickEmployeeCheckboxEmail(employeeDeatils.getEmailAddress().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin select archive option from actions dropdown");
		employeePage.selectArchiveEmployeeAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate employee archived successfuly");
		employeePage.validateEmployeeArchiveToaster();

		getExtentTestLogger().log(Status.PASS, "EA selects employee to enable");
		employeePage.clickArchivedLink();
		employeePage.setSearch(employeeDeatils.getEmailAddress());
		employeePage.clickEmployeeCheckboxEmail(employeeDeatils.getEmailAddress().toLowerCase());
		
		getExtentTestLogger().log(Status.PASS, "Enterprise admin select enable option from actions dropdown");
		employeePage.selectDeleteClientAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate employee enable successfuly");
		alertsAndMessagesPage.validateToaster(deleteMessage);

		getExtentTestLogger().log(Status.PASS, "Validate deleted employee not present inside archived tab");
		refreshPage();
		archivedEmployeesPage.setSearch(employeeDeatils.getEmailAddress());
		archivedEmployeesPage.validateEmployeeNotPresent(employeeDeatils.getEmailAddress().toLowerCase());

	}

	@Test(description = "Validate Invited Employee data gets updated when new Employee gets invited")
	public void validateInvitedEmployeeDataUpdate() {

		String employeeInviteToaster = ThriveAppSharedData.INVITE_CLIENT_NOTIFICATION.getValue();

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to Employees -> Invited Employees");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();

		getExtentTestLogger().log(Status.PASS, "Superadmin gets the total records count");
		String previousCount = invitedEmployee.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "EA invites employee and searches it in invited employees tab");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee();
		enterpriseInviteEmployeePage = employeePage.clickInviteEmployee();
		enterpriseInviteEmployeePage.sendInvite(employeeDeatils);
		enterpriseInviteEmployeePage.validateEmployeeInviteToaster(employeeInviteToaster,
				employeeDeatils.getEmailAddress());

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to Employees -> Invited Employees");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();

		getExtentTestLogger().log(Status.PASS, "Validate invited Employee data gets updated");
		invitedEmployee.validateDataUpdate(previousCount);
	}

	@Test(description = "Validate Archived Employee data gets updated when user get archived")
	public void validateArchivedEmployeeDataUpdateValueChanges() {

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "EA navigates to Employees -> Archived Employees");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();

		getExtentTestLogger().log(Status.PASS, "EA gets the total records count");
		String previousCount = archivedEmployeesPage.captureCurrentDataCount();

		getExtentTestLogger().log(Status.PASS, "EA register Employee successfully");
		headers.put("Authorization", getApiAuthCodeEA());
		inviteEmployeereqBody = ApiTestDataGenerator.generateInviteEmployeeReqBody();
		ApiInviteEmployeeTest inviteEmployeeTest = new ApiInviteEmployeeTest();
		inviteEmployeeTest.getInviteEmployeeResponse(inviteEmployeereqBody, headers);
		registerClientReqBody = ApiTestDataGenerator.generateRegisterClientReqBody();
		registerClientReqBody.setFirstName(inviteEmployeereqBody.getFirstName());
		registerClientReqBody.setLastName(inviteEmployeereqBody.getLastName());
		registerClientReqBody.setEmail(inviteEmployeereqBody.getEmail());

		getExtentTestLogger().log(Status.PASS, "EA register employee successfully");
		response = getRegisterClientResponse(registerClientReqBody);

		getExtentTestLogger().log(Status.PASS, "Then user get successful response with 200 status code");
		Assert.assertTrue(response.statusCode() == 200, "Error Not get the 200 ok status");

		getExtentTestLogger().log(Status.PASS, "EA navigates to employee");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "EA selects the employee to archive");
		employeePage.setSearch(inviteEmployeereqBody.getEmail());
		employeePage.clickEmployeeCheckboxEmail(inviteEmployeereqBody.getEmail().toLowerCase());

		getExtentTestLogger().log(Status.PASS, "EA select archive option from actions dropdown");
		employeePage.selectArchiveEmployeeAction().ClickYes();

		getExtentTestLogger().log(Status.PASS, "Validate employee archived successfuly");
		employeePage.validateEmployeeArchiveToaster();

		getExtentTestLogger().log(Status.PASS, "EA selects archived employee tab");
		employeePage.clickArchivedLink();

		getExtentTestLogger().log(Status.PASS, "Archived Clients data count get updated successsfully");
		archivedEmployeesPage.validateDataUpdate(previousCount);

	}
	
	@Test(description = "Validate EA successfully download employees specific records")
	public void validateEmployeesDownloadSpecificRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees");
		employeePage = thriveHeaderMenuPage.navigateToUsers().clickEmployee();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects employees from the list");
		int expected = employeePage.selectSpecificRecords();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on download option from action dropdown");
		userManagementCommonPage = employeePage.clickDownload();

		getExtentTestLogger().log(Status.PASS,"Downloaded specific records successfully in .csv format and count is accurate");
		employeePage.validateDownloadSpecificRecords(expected);
	}

	@Test(description = "Validate EA successfully download invited employees specific records")
	public void validateInvitedEmployeesDownloadSpecificRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees -> Invited Employees");
		invitedEmployee = thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickInvitedEmployees();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects employees from the list");
		int expected = invitedEmployee.selectSpecificRecords();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on download option from action dropdown");
		userManagementCommonPage = employeePage.clickDownload();

		getExtentTestLogger().log(Status.PASS,"Downloaded specific records successfully in .csv format and count is accurate");
		employeePage.validateDownloadSpecificRecords(expected);
	}
	
	@Test(description = "Validate EA successfully download archived employees specific records")
	public void validateArchivedEmployeesDownloadSpecificRecords() throws IOException {

		getExtentTestLogger().assignCategory(TestCategory.USER_MANAGEMENT_ACTIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> Employees -> Archived Employees");
		thriveHeaderMenuPage.navigateToUsers().clickEmployee().clickArchivedLink();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin selects employees from the list");
		int expected = archivedEmployeesPage.selectSpecificRecords();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on download option from action dropdown");
		userManagementCommonPage = employeePage.clickDownload();

		getExtentTestLogger().log(Status.PASS,"Downloaded specific records successfully in .csv format and count is accurate");
		employeePage.validateDownloadSpecificRecords(expected);
	}
	
	
	private Response getRegisterClientResponse(RegisterClientRequest registerClientReqBody) {
		String token = getRegisterTokenClient(registerClientReqBody.getFirstName());
		return RestAssured.given().queryParam("token", token).spec(getRegisterUserReqSpec()).and().when()
				.body(registerClientReqBody, ObjectMapperType.GSON).post("");

	}

}
