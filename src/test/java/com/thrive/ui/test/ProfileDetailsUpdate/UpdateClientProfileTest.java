package com.thrive.ui.test.ProfileDetailsUpdate;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.user_details.ClientEmploymentDetails;
import com.thrive.common.testdata.pojos.user_details.ClientUpdateDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.AlertsAndMessagesPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.credits.ClientPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterClientAboutPage;
import com.thrive.ui.page.register.RegisterClientCareerDetailsPage;
import com.thrive.ui.page.register.RegisterClientCoachingPrioritiesPage;
import com.thrive.ui.page.register.RegisterClientEmploymentStatusPage;
import com.thrive.ui.page.register.RegisterClientInformationPage;
import com.thrive.ui.page.user_management.search_filter.ClientsPage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.UserManagementPage;

public class UpdateClientProfileTest extends BaseTestPage{
	
	
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	ClientsPage clientPage = new ClientsPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	ThriveLoginPage login = new ThriveLoginPage();
	LoginDetails loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	AlertsAndMessagesPage alertsAndMessagesPage = new AlertsAndMessagesPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	UserManagementPage userManagementPage = new UserManagementPage();
	ClientPersonalDetailsPage personalDetailsPage = new ClientPersonalDetailsPage();
	RegisterClientInformationPage registerClientInformationPage = new RegisterClientInformationPage();
	RegisterClientEmploymentStatusPage registerClientEmploymentStatusPage = new RegisterClientEmploymentStatusPage();
	RegisterClientCareerDetailsPage registerClientCareerDetailsPage = new RegisterClientCareerDetailsPage();
	RegisterClientAboutPage registerClientAboutPage = new RegisterClientAboutPage();
	ClientUpdateDetails clientUpdateDetails = TestDataGenerator.generateClientUpdateDeatils();
	ClientEmploymentDetails clientEmpUpdateDetails = TestDataGenerator.generateClientEmploymentDetails();
	RegisterClientCoachingPrioritiesPage registerClientCoachingPrioritiesPage = new RegisterClientCoachingPrioritiesPage();
	
	@Test(description="Validate SA successfully update client profile details")
	public void updateClientProfileDetails() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_PROFILE_UPDATE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Update clients personal details with SA Login");
		validateClientPersonalDetailsUpdate();
		
		getExtentTestLogger().log(Status.PASS, "Update clients employment details with SA Login");
		validateClientEmploymentDetailsUpdate();
		
//		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//			getExtentTestLogger().log(Status.PASS, "Update clients Career details with SA Login");
//			validateClientCareerDetailsUpdate();
//		}
		
		getExtentTestLogger().log(Status.PASS, "Update clients coaching priorities details with SA Login");
		
	}
	
	private UpdateClientProfileTest validateClientPersonalDetailsUpdate() {
		
		getExtentTestLogger().log(Status.PASS, "langauge dropdown,username,password and click login");
		thriveHeaderMenuPage = login.login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> Clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the client to update and click on it");
		userManagementCommonPage = clientPage.setSearch(clientUserUpdate);
		registerClientInformationPage = clientPage.clickClientNameLink(clientUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User provides the first name to update");
		registerClientInformationPage.setFirstName(clientUpdateDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "User provides the last name to update");
		registerClientInformationPage.setLastName(clientUpdateDetails.getLastName());
		
		getExtentTestLogger().log(Status.PASS, "User provides the mobile no to update");
		registerClientInformationPage.setMobileNumber(clientUpdateDetails.getMobileNumber());
		
		getExtentTestLogger().log(Status.PASS, "User provides the language to update");
		registerClientInformationPage.selectLanguageDropdownValue();
		
		getExtentTestLogger().log(Status.PASS, "User provides the age range to update");
		registerClientInformationPage.selectFirstAvailableAgeRange();
		
		getExtentTestLogger().log(Status.PASS, "User provides the gender to update");
		registerClientInformationPage.selectFirstAvailableGender();
		
		
		getExtentTestLogger().log(Status.PASS, "User provides the country to update");
		registerClientInformationPage.selectFirstAvailableCountry();
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			
			getExtentTestLogger().log(Status.PASS, "User provides the ethnicity to update");
			registerClientInformationPage.SelectFirstAvailableEthnicity();
			
			getExtentTestLogger().log(Status.PASS, "User provides the nationality to update");
			registerClientInformationPage.selectFirstAvailableNationality();
		}
		
		getExtentTestLogger().log(Status.PASS, "User clicks on fill in address manually");
		registerClientInformationPage.clickFillAddressManuallyLink();
		
		getExtentTestLogger().log(Status.PASS, "User provides the address1 to update");
		registerClientInformationPage.setAddress1(clientUpdateDetails.getAddress1());
		
		getExtentTestLogger().log(Status.PASS, "User provides the address2 to update");
		registerClientInformationPage.setAddress2(clientUpdateDetails.getAddress2());
		
		getExtentTestLogger().log(Status.PASS, "User provides the city to update");
		registerClientInformationPage.setCity(clientUpdateDetails.getCity());
		
		getExtentTestLogger().log(Status.PASS, "User provides the state to update");
		registerClientInformationPage.setState(clientUpdateDetails.getState());
		
		getExtentTestLogger().log(Status.PASS, "User provides the county to update");
		registerClientInformationPage.setCounty(clientUpdateDetails.getCounty());
		
		getExtentTestLogger().log(Status.PASS, "User provides the postcode to update");
		registerClientInformationPage.setPostCode(clientUpdateDetails.getPostcode());
		
		getExtentTestLogger().log(Status.PASS, "User provides the date format to update");
		registerClientInformationPage.SelectFirstAvailableDateFormat();
		
		getExtentTestLogger().log(Status.PASS, "User provides the time format to update");
		registerClientInformationPage.selectFirstTimeFormat();
				
		getExtentTestLogger().log(Status.PASS, "User clicks on update details for client");
		registerClientInformationPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets update notification");
		registerClientInformationPage.validateToaster(ThriveAppSharedData.CLIENT_DETAILS_UPDATED.getValue());
		
		getExtentTestLogger().log(Status.PASS, "The client Personal Details Updated successfully");
		registerClientInformationPage.validateClientPersonalDetails(clientUpdateDetails);
		
		return this;
		
	}
	
	private UpdateClientProfileTest validateClientEmploymentDetailsUpdate() {
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> Clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the client to update and click on it");
		userManagementCommonPage = clientPage.setSearch(clientUserUpdate);
		registerClientInformationPage = clientPage.clickClientNameLink(clientUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User selects the employment tab of client");
		registerClientEmploymentStatusPage = registerClientInformationPage.clickEmployment();
		
		getExtentTestLogger().log(Status.PASS, "User provides the department to update");
		registerClientEmploymentStatusPage.selectFirstDepartment();
		
		getExtentTestLogger().log(Status.PASS, "User selects the position to update");
		registerClientEmploymentStatusPage.selectFirstAvailablePostion(clientEmpUpdateDetails.getPosition());
		
		getExtentTestLogger().log(Status.PASS, "User selects the region to update");
		registerClientEmploymentStatusPage.selectFirstAvailableRegion();	
		
		getExtentTestLogger().log(Status.PASS, "User selects the duration of employment to update");
		registerClientEmploymentStatusPage.selectFirstWorkDuration();
		
		getExtentTestLogger().log(Status.PASS, "The user clicks on update details of Employment");
		registerClientEmploymentStatusPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets update notification");
		registerClientInformationPage.validateToaster(ThriveAppSharedData.CLIENT_DETAILS_UPDATED.getValue());
		
		getExtentTestLogger().log(Status.PASS, "The client Employment updated successfully");
		registerClientEmploymentStatusPage.validateEmployment(clientEmpUpdateDetails);
		
		return this;
	}
	
	private UpdateClientProfileTest validateClientCareerDetailsUpdate() {
				
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> Clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the client to update and click on it");
		userManagementCommonPage = clientPage.setSearch(clientUserUpdate);
		registerClientInformationPage = clientPage.clickClientNameLink(clientUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User selects the career tab of client ");
		registerClientCareerDetailsPage	= registerClientInformationPage.clickCareer();
		
		getExtentTestLogger().log(Status.PASS, "User provide the attitube to update");
		registerClientCareerDetailsPage.selectFirstAvailalbeAttitubeRadioOption();
		
		getExtentTestLogger().log(Status.PASS, "User provides the Future team to update");
		registerClientCareerDetailsPage.selectFirstAvailableFutureTeamRadio();
		
		getExtentTestLogger().log(Status.PASS, "User provides the Future company to update");
		registerClientCareerDetailsPage.selectFirstAvailableFutureComapnyRadio();
		
		getExtentTestLogger().log(Status.PASS, "User provides the Future industry to update");
		registerClientCareerDetailsPage.selectFirstAvailableFutureIndustry();	
		
		getExtentTestLogger().log(Status.PASS, "The user clicks on update details of career");
		registerClientCareerDetailsPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets update notification");
		registerClientInformationPage.validateToaster(ThriveAppSharedData.CLIENT_DETAILS_UPDATED.getValue());
		
		getExtentTestLogger().log(Status.PASS, "The client career details updated successfully");
		registerClientCareerDetailsPage.validateCareer();
		
		return this;
	}

	public UpdateClientProfileTest validateClientCoachingPrioritiesUpdate() {
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> Clients");
		clientPage = thriveHeaderMenuPage.clickUserManagementLink().clickClientMenu();
				
		getExtentTestLogger().log(Status.PASS, "User search the client to update and click on it");
		userManagementCommonPage = clientPage.setSearch(clientUserUpdate);
		registerClientInformationPage = clientPage.clickClientNameLink(clientUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User selects coaching priorities tab of client ");
		registerClientCoachingPrioritiesPage = registerClientInformationPage.clickCoachingPriorities();
		
		getExtentTestLogger().log(Status.PASS, "User sets value of coaching priorities");
		registerClientCoachingPrioritiesPage.setCoachingPriorities(clientUpdateDetails.getCoachingPriorities());
				
		getExtentTestLogger().log(Status.PASS, "User sets value of professional career");
		registerClientCoachingPrioritiesPage.setProfessionalCareerSatisfaction();
		
		getExtentTestLogger().log(Status.PASS, "User sets value of meaning and purpose");
		registerClientCoachingPrioritiesPage.setProfessionalCareerSatisfaction();
		
		getExtentTestLogger().log(Status.PASS, "User sets value of management and leadership skills");
		registerClientCoachingPrioritiesPage.setManagementAndLeadershipSkillsSatisfaction();
		
		getExtentTestLogger().log(Status.PASS, "User sets value of well being satisfaction");
		registerClientCoachingPrioritiesPage.setWellBeingSatisfaction();
		
		getExtentTestLogger().log(Status.PASS, "User sets value of personal life satisfaction");
		registerClientCoachingPrioritiesPage.setPersonalHomeLifeSatisfaction();
						
		getExtentTestLogger().log(Status.PASS, "Validate coaching priorities details updated successfully");
		registerClientCoachingPrioritiesPage.validateCoachingPrioritiesUpdate(clientUpdateDetails);
						
		return this;
	}
	
}
