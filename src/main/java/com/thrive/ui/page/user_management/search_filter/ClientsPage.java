package com.thrive.ui.page.user_management.search_filter;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteEmployeeDetails;
import com.thrive.common.testdata.utils.TestPlatform;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.AllocateCreditsPage;
import com.thrive.ui.page.credits.ClientPersonalDetailsPage;
import com.thrive.ui.page.invite.InviteClientPage;
import com.thrive.ui.page.invite.InvitedClientsPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;

public class ClientsPage extends UserManagementCommonPage {

	private By getEnterpriseClientLink() {
		return By.xpath(getXpathByText("//li[text()='admin.user_management.clients_title']"));
	}
	
	private By getClientLink(String clientName) {
		return By.xpath(".//a[contains(text(),'"+clientName+"')]");
	}
	
	private By getremoveinvitedClient() {
		return By.xpath(getXpathByText("//span[text()='admin.user_management.clients.reinvite_client']"));
	}
	
	private By getClientInviteLink() {
		return By.xpath(getXpathByText("//a[text()='admin.user_management.clients.invite_client']"));
	}
	
	private By getclient(String client) {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.name']/../../../../../../../../../../p-table[contains(@class,'clients-tbl')]//a[text()='"+client+"']"));
	}
	
	private By getSearchInviteClientName(String client) {
		return By.xpath(getXpathByText("//th[text()='admin.user_management.clients.col.name']/../../../../../../../../../../p-table[contains(@class,'clients-tbl')]//span[contains(text()='"+client+"')]"));
	}
	
	private By getInviteClientCheckbox(String clientName) {
		return By.xpath(".//span[contains(text(),'"+clientName+"')]/../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getEnterpriseCheckBox() {
		return By.xpath(accountManagerPassword);
	}
	//Working
	private By getClientsCheckbox(String clientName) {
		return By.xpath("//a[contains(text(),'"+clientName+"')]/../../..//p-checkbox//following-sibling::div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getClientCheckboxEmail(String email) {
		return By.xpath("//span[contains(text(),'"+email+"')]/../..//p-checkbox//div[contains(@class,'ui-chkbox-box')]");
	}
	
	private By getToasterCloseButton() {
		return By.xpath(".//button[contains(@class, 'toast-close-button')]");
	}
	
	private By getEmailAddressColumn(String email) {
		return By.xpath("//p-table[contains(@class,'clients')]//th[contains(text(),'Email')]/../../../../../following-sibling::div//span[normalize-space(text())='"+email.toLowerCase()+"']");
	}
	
	public ClientsPage validateClientPresent(String email) {
		LOGGER.info("Validating Account Manager name is present");
		Assert.assertTrue(isElementPresent(getEmailAddressColumn(email)));
		return this;
		
	}
	
	public ClientsPage clickClientCheckboxEmail(String email) {
		LOGGER.info("Clicking Client checkbox");
		click(getClientCheckboxEmail(email));
		return this;
	}
	
	private By getAllocateCreditButton() {
		return By.xpath(getXpathByText("//button[text()='shared.modals.admin_allocate_credits.allocate_button']"));
	}
	//This is for dev environment
	private By getResetFilter() {
		return By.xpath(getXpathByText("//span[text()='admin.user_management.users.reset_filter']"));
	}
	
	
	private By getSearchClientInput() {
		if (Config.getLocalizationLanguage().contains("en")) {
			return By.xpath(".//input[@placeholder='Search']");
		} else {
			return By.xpath(".//input[@placeholder='Rechercher']");
		}
	}
	
	private By getActionDropdown() {
		return By.xpath(".//button[contains(@class,'action-btn')]");
	}
	
	private By getAllocateCreditElement() {
		if(Config.getLocalizationLanguage().contains("en")) {
		return By.xpath(getXpathByText(".//span[text()='shared.modals.admin_allocate_credits.title']"));
		} else {
	    return By.xpath(".//span[contains(text(),'ALLOCATION DE CRÉDITS')]");
		}
	}
	
	private By getInviteClientElement() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.clients.invite_client')]"));
	}
	
	private By getDeleteClientElement() {
		return By.xpath(getXpathByText("//span[text()='admin.user_management.clients.delete_client']"));
	}
	
	private By getArchiveClientElement() {
		return By.xpath(getXpathByText(".//span[text()='admin.user_management.clients.archive_client']"));
	}
	
	private By getRemoveCreditsButton() {
		return By.xpath(getXpathByText(".//span[text()='shared.modals.admin_remove_credits.remove_credits']"));
	}
	
	private By getInvitedClientsButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.clients.invited_clients']"));
	}
	
	private By getArchivedClientsButton() {
		return By.xpath(getXpathByText(".//button[text()='admin.user_management.clients.archived_clients']"));
	}
	
	private By getNoButton() {
		return By.xpath(getXpathByText(".//button[text()='shared.modals.admin_allocate_credits.no_button']"));
	}
	
	private By getclientEmail(String email) {
		return By.xpath(getXpathByText("//th[text()='admin.user_management.clients.col.emailid']/../../../../../../../../../../p-table[contains(@class,'clients-tbl ')]//span[normalize-space(text())='"+email+"']"));
	}
	
	public ClientsPage validateClientByEmail(String email) {
		LOGGER.info("Validating client using Email");
		click(getclientEmail(email));
		return this;
	}
	
	private By getYesButton() {
		return By.xpath(getXpathByText(".//button[text()='ui.buttons.Yes']"));
	}
	
	private By getClientButton() {
		return By.xpath(getXpathByText("//button[text()='admin.user_management.clients_tab']"));
	}
	
	public ClientsPage clickClientButton() {
		LOGGER.info("Clicking client button");
		click(getClientButton());
		return this;
	}
	
	
	
	private By getclientArchiveMessage() {
		return By.xpath(".//div[normalize-space(text())='Client(s) has been successfully archived']");
	}
	
	private By getenterpriseforclient(String enterprise) {
		return By.xpath(getXpathByText(".//th[text()='admin.clients.client.enterprise']/../../../../../following-sibling::div//a[text()='"+enterprise+"']"));
	}
	
	private By getClientNameLink(String email) {
		return By.xpath("//span[normalize-space(text())='"+email+"']/../preceding-sibling::td[1]//a");
	}
	
	public RegisterClientInformationPage clickClientNameLink(String email) {
		LOGGER.info("Clicking client name link");
		click(getClientNameLink(email));
		return new RegisterClientInformationPage();
	}
	
	public ClientsPage validateEnterprisePresentForClient(String enterprise) {
		LOGGER.info("Validate Enterprise is present");
		Assert.assertTrue(isElementPresent(getenterpriseforclient(enterprise)), "Failed to search the Enterprise :" + enterprise);
		return this;
	}
	
	public InviteClientPage clickEnterpriseClientLink() {
		LOGGER.info("click on clients link");
		click(getEnterpriseClientLink());
		return new InviteClientPage();
	}
	
	public InviteClientPage clickClientInviteLink() {
		LOGGER.info("click on invite link");
		click(getClientInviteLink());
		return new InviteClientPage();
	}
	
	public ClientsPage clickClientsCheckbox(String clientName) {
		LOGGER.info("click clients checkbox");
		click(getClientsCheckbox(clientName));
		return this;
	}
	public ClientsPage clickInviteClientCheckbox(String clientName) {
		click(getInviteClientCheckbox(clientName));
		return this;
	}
	
	public ClientsPage clickAllocateCreditButton() {
		LOGGER.info("click Allocate credit button");
		click(getAllocateCreditButton());
		return this;
		
	}
	
	public ClientsPage getDeleteInviteClient() {
		LOGGER.info("Clicking Delete Client link");
		click(getDeleteClientElement());
		return this;
	}
	public ClientsPage clickInviteClientLink() {
		click(getInviteClientElement());
		return this;
	}
	
	public ArchivedClientsPage clickArchivedClients() {
		LOGGER.info("Clicking Archived clients link");
		click(getArchivedClientsButton());
		return new ArchivedClientsPage();
	}
	
	
	public InviteClientPage clickInviteClient() {
		LOGGER.info("Clicking Invite Client link");
		click(getActionDropdown());
		click(getInviteClientElement());
		return new InviteClientPage();
	}
	
	public ClientsPage setSearchClient(String clientName) {
		LOGGER.info("Set client name to search");
		setValue(clientName, getSearchClientInput());
		waitUntilElementIsVisible(15,getClientsCheckbox(clientName));
		checkClientCheckbox(clientName);
		return this;
	}
	
	public ClientsPage getRemoveInviteClient() {
		LOGGER.info("Clicking Invite Client link");
		click(getremoveinvitedClient());
		return this;
	}
	
	public ClientsPage checkClientCheckbox(String clientName) {
		LOGGER.info("Clicking Client checkbox");
		click(getClientsCheckbox(clientName));
		return this;
	}
	
	public ClientMenuPage clickClient(String clientName) {
		click(getClientLink(clientName));
		return new ClientMenuPage();
	}
	
	public InvitedClientsPage clickInvitedClients() {
		LOGGER.info("Clicking Invited clients link");
		click(getInvitedClientsButton());
		return new InvitedClientsPage();
	}
	
	public ClientsPage clickNo() {
		LOGGER.info("Clicking No button");
		click(getNoButton());
		return this;
	}
	
	public ClientsPage clickYes() {
		LOGGER.info("Clicking Yes button");
		click(getYesButton());
		return this;
	}
	
	public ClientsPage validateIsClientPresent(String client) {
		LOGGER.info("Validating client name is present");
		Assert.assertTrue(isElementPresent(getclient(client)),"Failed to search Client:" + client );
		return this;
	}
	public ClientsPage validateSearchInviteClient(String client) {
		Assert.assertTrue(isElementPresent(getSearchInviteClientName(client)));
		return this;
	}
	
	public AlertsAndMessagesPage selectArchiveClientElement(String clientName) {
		LOGGER.info("Clicking Archive Client link");
		click(getActionDropdown());
		click(getArchiveClientElement());
		return new AlertsAndMessagesPage();
	}
	
	public ClientsPage clickResetFilter() {
		LOGGER.info("Clicking Reset Filter");
		click(getResetFilter());
		return this;
	}
	
	public ClientsPage validateArchivedClientMessage(String expected) {
		LOGGER.info("Validating Archived Client message");
		String actual = getText(getclientArchiveMessage());
		Assert.assertEquals(actual, expected);
		return this;
	}
	
	public ClientsPage validateArchivedClientSuccessfully(String client) {
		LOGGER.info("Validating Archived Client ");
		Assert.assertTrue(isElementNotVisible(getClientsCheckbox(client)));
		return this;
	}
	public ClientsPage validateResetFilters(String user) {
		LOGGER.info("Validating reset filter works as expected");
		String searchPlaceHolder = getAttributeByValue("placeholder", getSearchInput());
		assertTrue(searchPlaceHolder.equalsIgnoreCase("Search"), "Search input box is not reset");
		return this;
	}
	public ClientsPage validatAccessToInviteClients(String user) {
		LOGGER.info("Validating Access to invite client ");
		shortWait();
		clickInviteClient();
		shortWait();
		Assert.assertTrue(getDriver().getTitle().contains("Invite clients"),"This "+user+" don't have access to invite client.");
		return this;
	}
	
	public void validateEmployeeRegistration(InviteEmployeeDetails inviteEmployeeDetails,LoginDetails loginDetails) {
		LOGGER.info("Validating client registered successfully");
		launchThriveApp(Config.getLoginPageURL());
		loginDetails = new LoginDetails(inviteEmployeeDetails.getEmailAddress(), ThriveAppSharedData.COMMON_PAASWORD.getValue());
		new ThriveLoginPage().loginIgnoreAccept(loginDetails).validateLoginSuccessful();
	}
	
	public AllocateCreditsPage clickAllocateCredit(String clientName) {
		LOGGER.info("Clicking on Allocate Credit link");
		click(getActionDropdown());
		click(getAllocateCreditElement());
		return new AllocateCreditsPage();
	}

	
	private By getClientCreditsColumn(String clientName) {
		return By.xpath("//a[contains(text(),'"+clientName+"')]/../..//following-sibling::td[6]");
	}
	
	public String captureClientsCredits(String clientName) {
		LOGGER.info("Capturing clients credits from Credits column");
		return getText(getClientCreditsColumn(clientName));
	}
	
	private By getAlertElement() {
		return By.xpath(".//div[@role='alertdialog']");
	}
	
	public ClientsPage validateClientArchiveToaster() {
		LOGGER.info("Validating toaster message");
		String expectedMessage;

		if (Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			expectedMessage = ThriveAppSharedData.CLIENT_ARCHIVED_SUCCESSFULLY_MESSAGE.getValue();
		} else {
			expectedMessage = ThriveAppSharedData.CLIENT_ARCHIVED_SUCCESSFULY_MESSAGE_RM.getValue();
		}

		String actualMessage = getText(getAlertElement());
		Assert.assertTrue(actualMessage.equalsIgnoreCase(expectedMessage), "Failed to validate the alert message :"
				+ "expected is : " + expectedMessage + " and actual is : " + actualMessage);
		click(getToasterCloseButton());
		return this;
	}
	
	private By getRowsLink() {
		return By.xpath("//p-table[not(contains(@class,'hidden'))]//div[@class='ui-table-scrollable-body ng-star-inserted']//descendant::tr");
	}
	
	private By getSearchValue() {
		return By.xpath(".//input[@placeholder='Search']");
	}
	
	private By getFilterByEnterpriseDropdownValue() {
		return By.xpath(getXpathByText(".//div[text()='admin.user_management.users.enterprises']"));
	}
	
	public ClientsPage validateClearFilter() {
		LOGGER.info("Validate clicking Clear Filter resets all data");
		shortWait();
		int displayedRowsCount = findElements(getRowsLink()).size();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(displayedRowsCount > 1, "Clear Filter does not working properly");
		softAssert.assertTrue(isElementPresent(getSearchValue()));
		softAssert.assertTrue(isElementPresent(getFilterByEnterpriseDropdownValue()));
		softAssert.assertAll();
		return this;
	}
	
	private By getCreditsUsedforSessionCancelledLateColumn() {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.number_of_credits_used_for_sessions_cancelled_late']"));
	}
	
	private By getCreditsUsedforSessionNotAttendedColumn() {
		return By.xpath(getXpathByText(".//th[text()='admin.user_management.clients.col.number_of_credits_used_for_sessions_not_attended']"));
	}
	
	public ClientsPage validateScrollColumnsPresent() {
		LOGGER.info("Validate Columns present after right scroll");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getCreditsUsedforSessionCancelledLateColumn()), "session late cancelled column not present");
		softAssert.assertTrue(isElementPresent(getCreditsUsedforSessionNotAttendedColumn()), "session not attended column not present");
		softAssert.assertAll();
		return this;
	}
	
	private By getRightDirectionMovementButtonLink() {
		return By.xpath("//span[@class='glyphicon glyphicon-chevron-right']");
	}
	
	public ClientsPage clickRightScrollButton() {
		LOGGER.info("click right scroller button");
		while(isElementNotVisible(getCreditsUsedforSessionCancelledLateColumn())) {
			click(getRightDirectionMovementButtonLink());
		}
		return this;
	}
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//span[contains(text(),'admin.user_management.export')]"));
	}
	
	public ClientsPage validateActionDropdownElements() {
		LOGGER.info("validate actions dropdown elements present");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(getDownloadButton()),"Download option not present");
		softAssert.assertTrue(isElementPresent(getAllocateCreditElement()),"allocate credits option not present");
		softAssert.assertTrue(isElementPresent(getRemoveCreditsButton()),"remove credits option not present");
		softAssert.assertTrue(isElementPresent(getInviteClientElement()),"invite option not present");
		softAssert.assertTrue(isElementPresent(getArchiveClientElement()),"archive option not present");
		softAssert.assertAll();
		return this;
	}
	
}
