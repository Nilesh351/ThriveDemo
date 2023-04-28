package com.thrive.ui.test.ProfileDetailsUpdate;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.user_details.EnterpriseUpdateDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

public class UpdateEnterpriseProfileTest extends BaseTestPage{
	
	
	ThriveLoginPage login = new ThriveLoginPage();
	UsersPage usersPage;
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	LoginDetails loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	RegisterEnterpriseDetailsPage registerEnterpriseDetailsPage = new RegisterEnterpriseDetailsPage();
	EnterpriseUpdateDetails enterpriseUpdateDetails = TestDataGenerator.generateEnterpriseUpdateDetails();
	List<String> sessionLength = new ArrayList<String>();
	
	
	@Test(description="Validate SA successfully update enterprise profile details")
	public void validateEnterpriseDetailsUpdate() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_PROFILE_UPDATE.getValue());
		
		login = new ThriveLoginPage();
		getExtentTestLogger().log(Status.PASS, "langauge dropdown,username,password and click login");
		thriveHeaderMenuPage = login.login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> enterprises");
		enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise to update and click on it");
		enterprisesPage.setSearch(eaUserUpdate);
		registerEnterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaUserUpdate);
		 
		getExtentTestLogger().log(Status.PASS, "User Provides enterprise name to update for enterprise");
		registerEnterpriseDetailsPage.setEnterpriseName(enterpriseUpdateDetails.getEnterpriseName());
		
		getExtentTestLogger().log(Status.PASS, "User provides enterprise code to update for enterprise");
		registerEnterpriseDetailsPage.setEnterpriseCode(enterpriseUpdateDetails.getEnterpriseCode()); 
		
		getExtentTestLogger().log(Status.PASS, "User provides information useful for coach to update for enterprise");
		registerEnterpriseDetailsPage.setInformationUsefulForCoachToKnow(enterpriseUpdateDetails.getInfoUsefulForCoach());
		
		getExtentTestLogger().log(Status.PASS, "User provides website to update for enterprise");
		registerEnterpriseDetailsPage.setWebsite(enterpriseUpdateDetails.getWebsite());
		
		getExtentTestLogger().log(Status.PASS, "User provides address line1 to update for enterprise");
		registerEnterpriseDetailsPage.setAddressline1(enterpriseUpdateDetails.getAddressLine1());
		
		getExtentTestLogger().log(Status.PASS, "User provides addess line2 to update for enterprise");
		registerEnterpriseDetailsPage.setAddressline2(enterpriseUpdateDetails.getAddressLine2());
		
		getExtentTestLogger().log(Status.PASS, "User provides county to update for enterprise");
		registerEnterpriseDetailsPage.setCounty(enterpriseUpdateDetails.getCounty());
		
		getExtentTestLogger().log(Status.PASS, "User provides country to update for enterprise");
		registerEnterpriseDetailsPage.selectFirstAvailableCountry();
		
		getExtentTestLogger().log(Status.PASS, "User provides postcode to update for enterprise");
		registerEnterpriseDetailsPage.setPostcode(enterpriseUpdateDetails.getPostcode());
		
		getExtentTestLogger().log(Status.PASS, "User provides enterprise department to update for enterprise");
		registerEnterpriseDetailsPage.selectFirstAvailableEnterpriseDepartment();
		
		getExtentTestLogger().log(Status.PASS, "User provides enterprise units to update for enterprise");
		registerEnterpriseDetailsPage.selectFirstAvailabelEnterpriseUnit();
		
		getExtentTestLogger().log(Status.PASS, "User provides all coaches to update for enterprise");
		registerEnterpriseDetailsPage.selectAllCoaches(true);
		
		getExtentTestLogger().log(Status.PASS, "User provides credit provides on verification to update for enterprise");
		registerEnterpriseDetailsPage.setCreditAllocationOnVerification(enterpriseUpdateDetails.getCreditsOnVerification());
		
		getExtentTestLogger().log(Status.PASS, "User provides session length to update for enterprise");
		sessionLength.add("4 h");
		registerEnterpriseDetailsPage.selectSessionLength(sessionLength);
		
		getExtentTestLogger().log(Status.PASS, "User provides city to update for enterprise");
		registerEnterpriseDetailsPage.setCity(enterpriseUpdateDetails.getCity());
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			getExtentTestLogger().log(Status.PASS, "User provides allow recordings of session to update for enterprise");
			registerEnterpriseDetailsPage.selectAllowRecordingSessionOption(true);
			
			getExtentTestLogger().log(Status.PASS, "User provides enable session inside to update for enterprise");
			registerEnterpriseDetailsPage.selectEnableSessionInsights(true);
		}
		
		getExtentTestLogger().log(Status.PASS, "User clicks on update details for enterprise");
		registerEnterpriseDetailsPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets update notification");
		registerEnterpriseDetailsPage.validateToaster(ThriveAppSharedData.ENTERPRISE_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "The enterprise Details Updated successfully");
		registerEnterpriseDetailsPage.validateEnterpriseDetails(enterpriseUpdateDetails);
		
	}


}
