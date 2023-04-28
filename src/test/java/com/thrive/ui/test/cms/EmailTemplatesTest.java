package com.thrive.ui.test.cms;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.ui.page.cms.EmailTemplatesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;


public class EmailTemplatesTest extends UserManagementCommonPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	EmailTemplatesPage emailTemplatesPage;
	int pagination=100;
	String scrollValue="800";
	
	List<String> errorTemplatesDBKeys = new ArrayList<String>();
	
	@Test(description = "Detection of error in email templates in CMS for Thrive")
	public void emailTemplatesErrorTetectionThrive() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to CMS -> Email Templates");
		emailTemplatesPage = thriveHeaderMenuPage.clickCMS();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin searches for error message in email templates");
		emailTemplatesPage.clickEmailTemplate().scrollInVerticalDirection(scrollValue);
		errorTemplatesDBKeys = emailTemplatesPage.searchErrorMessage(pagination);
		
		getExtentTestLogger().log(Status.PASS, "Printing DB kayes of email templates containing errors");
		emailTemplatesPage.printDBKeys(errorTemplatesDBKeys);
	}	

	@Test(description = "Detection of error in email templates in CMS for RM")
	public void emailTemplatesErrorTetectionRM() {

		getExtentTestLogger().assignCategory(TestCategory.SESSION_FILTERS.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to CMS -> Email Templates");
		emailTemplatesPage = thriveHeaderMenuPage.clickCMS();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin searches for error message in email templates");
		emailTemplatesPage.clickEmailTemplate().scrollInVerticalDirection(scrollValue);
		errorTemplatesDBKeys = emailTemplatesPage.searchErrorMessage(pagination);
		
		getExtentTestLogger().log(Status.PASS, "Printing DB kayes of email templates containing errors");
		emailTemplatesPage.printDBKeys(errorTemplatesDBKeys);
	}
}
