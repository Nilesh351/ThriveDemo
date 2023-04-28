package com.thrive.ui.test.Coaching;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class SessionFiltersTest extends UserManagementCommonPage{
	
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();
	SessionsPage SessionsPage = new SessionsPage();
	
	@Test(description = "Validate SA successfully filter by type")
	public void validateFilterByType() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coaches option ans clicks on sessions");
		SessionsPage =  thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by type dropdown value");
		SessionsPage.selectFilterByType(ThriveAppSharedData.TYPE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Valdiate selected type is present");
		SessionsPage.validateFilterByType(ThriveAppSharedData.TYPE_COL.getValue());
		
	}
	
	@Test(description = "Validate SA successfully filter by month")
	public void validateFilterByMonth() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coaches option ans clicks on sessions");
		SessionsPage =  thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by month dropdown value");
		SessionsPage.selectFilterByMonth(ThriveAppSharedData.MONTH.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Valdiate selected month is present");
		SessionsPage.validateFilterByMonth(ThriveAppSharedData.MONTH_COL.getValue());
		
	}
	
	@Test(description = "Validate SA successfully filter by enterprise")
	public void validateFilterByEnterprise() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coaches option ans clicks on sessions");
		SessionsPage =  thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by enterprise dropdown value");
		SessionsPage.selectFilterByEnterprise(enterprise);
		
		getExtentTestLogger().log(Status.PASS, "Valdiate selected enterprise is present");
		SessionsPage.validateFilterByEnterprise(enterprise);
		
	}
	
	@Test(description = "Validate SA successfully filter by client")
	public void validateFilterByClient() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coaches option ans clicks on sessions");
		SessionsPage =  thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by client dropdown value");
		SessionsPage.selectFilterByClient(clientName);
		
		getExtentTestLogger().log(Status.PASS, "Valdiate selected client is present");
		SessionsPage.validateFilterByClient(clientName);
		
	}
	
	@Test(description = "Validate SA successfully filter by coach type and coach")
	public void validateFilterByCoachType() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coaches option ans clicks on sessions");
		SessionsPage =  thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by coach type dropdown value");
		SessionsPage.selectFilterByCoachType(ThriveAppSharedData.INSIGHT_COACHES.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by coach dropdown value");
		SessionsPage.selectFilterByCoach(internalCoachName);
		
		getExtentTestLogger().log(Status.PASS, "Valdiate selected coach is present");
		SessionsPage.validateFilterByCoach(internalCoachName);
		
	}
	
	@Test(description = "Validate SA successfully filter by status")
	public void validateFilterByStatus() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coaches option ans clicks on sessions");
		SessionsPage =  thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by client dropdown value");
		SessionsPage.selectFilterByClient(clientName);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by status dropdown value");
		SessionsPage.selectFilterByStatus(ThriveAppSharedData.STATUS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Valdiate selected coach is present");
		SessionsPage.validateFilterByStatus(ThriveAppSharedData.STATUS_COL.getValue());
		
	}
	
	@Test(description = "Validate SA successfully all applied filters")
	public void validateClearFilters() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coaches option ans clicks on sessions");
		SessionsPage =  thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by type dropdown value");
		SessionsPage.selectFilterByType(ThriveAppSharedData.TYPE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by month dropdown value");
		SessionsPage.selectFilterByMonth(ThriveAppSharedData.MONTH.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by enterprise dropdown value");
		SessionsPage.selectFilterByEnterprise(enterprise);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects filter by status dropdown value");
		SessionsPage.selectFilterByStatus(ThriveAppSharedData.STATUS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on clear filters button");
		SessionsPage.clickClearFilters();
		
		getExtentTestLogger().log(Status.PASS, "Validate filters cleared successfully");
		SessionsPage.validateClearFilters();
		
	}
	
	@Test(description = "Validate SA successfully Download in csv format")
	public void validateFiltersDownloadRecords() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects coaches option ans clicks on sessions");
		SessionsPage =  thriveHeaderMenuPage.clickMySessions().clickSessions();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on download button");
		SessionsPage.clickDownloadButton();
		
		getExtentTestLogger().log(Status.PASS, "Validate file downloaded successfully");
		SessionsPage.validateRecordsDownloaded();
	}

}
