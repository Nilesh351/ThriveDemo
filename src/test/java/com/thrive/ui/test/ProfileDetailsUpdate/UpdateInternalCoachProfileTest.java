package com.thrive.ui.test.ProfileDetailsUpdate;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.user_details.CoachAboutDetails;
import com.thrive.common.testdata.pojos.user_details.CoachCorporateExpUpdateDetails;
import com.thrive.common.testdata.pojos.user_details.CoachSkillAndQualificationDetails;
import com.thrive.common.testdata.pojos.user_details.CoachUpdateDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.RegisterCoachProfileForClients;
import com.thrive.ui.page.register.RegisterCoachCorporateExpPage;
import com.thrive.ui.page.register.RegisterCoachEmploymentPage;
import com.thrive.ui.page.register.RegisterCoachLanguagesPageRm;
import com.thrive.ui.page.register.RegisterCoachMentoringExpPage;
import com.thrive.ui.page.register.RegisterCoachPersonalDetailsPage;
import com.thrive.ui.page.register.RegisterCoachSkillsAndQualificationPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.UsersPage;

public class UpdateInternalCoachProfileTest extends UserManagementCommonPage{
	
	ThriveLoginPage login = new ThriveLoginPage();
	UsersPage usersPage;
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	LoginDetails loginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	GlobalCoachesPage globalCoachesPage = new GlobalCoachesPage();
	InternalCoachesPage internalCoachesPage = new InternalCoachesPage();
	RegisterCoachPersonalDetailsPage registerCoachPersonalDetailsPage = new RegisterCoachPersonalDetailsPage();
	RegisterCoachEmploymentPage registerCoachEmploymentPage = new RegisterCoachEmploymentPage();
	RegisterCoachSkillsAndQualificationPage registerCoachSkillsAndQualificationPage = new RegisterCoachSkillsAndQualificationPage();  
	RegisterCoachProfileForClients registerCoachAboutPage = new RegisterCoachProfileForClients(); 
	RegisterCoachCorporateExpPage registerCoachCorporateExpPage = new RegisterCoachCorporateExpPage();
	RegisterCoachMentoringExpPage registerCoachMentoringExpPage = new RegisterCoachMentoringExpPage(); 
	CoachUpdateDetails coachUpdateDetails = TestDataGenerator.generateCoachUpdateDeatails();
	CoachSkillAndQualificationDetails coachSkillAndQualificationDetails = TestDataGenerator.generateGlobalCoachSkillAndQualificationDetails();
	CoachCorporateExpUpdateDetails coachCorporateExpUpdateDetails = TestDataGenerator.generateCoachCorporateUpdateDetails();
	CoachAboutDetails  coachAboutDetails = TestDataGenerator.generateCoachAboutDetails();
	RegisterCoachLanguagesPageRm registerCoachLanguagesPageRm = new RegisterCoachLanguagesPageRm();
	
	
	@Test(description="Validate SA successfully update internal coach profile details")
	public void updateInternalCaochProfileDetails() {
		
		getExtentTestLogger().assignCategory(TestCategory.USER_PROFILE_UPDATE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Update internal coach Personal details");
		validateInternalCoachPersonalDetailsUpdate();
		
		getExtentTestLogger().log(Status.PASS, "Update internal coach Mentoring experience details");
		validateInternalCoachMentoringExperience();
		
		getExtentTestLogger().log(Status.PASS, "Update internal coach about deatils");
		validateInternalCoachAbout();
		
//		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//			
//			getExtentTestLogger().log(Status.PASS, "Update internal coach Employmemt details");
//			validateInternalCoachEmploymentUpdate();
//			
//			getExtentTestLogger().log(Status.PASS, "Update internal coach Skills and Qualification details");
//			validateInternalCoachSkillsAndQualificatiosUpdate();
//			
//			getExtentTestLogger().log(Status.PASS, "Update internal coach Corporate expertise details");
//			validateInternalCoachMentoringExperience();
//		}
//		else {
//			getExtentTestLogger().log(Status.PASS, "Update internal coach languages details");
//			validateInternalCoachLanguagesUpdate();
//		}
		
		
	}
	
	
	
	private UpdateInternalCoachProfileTest validateInternalCoachPersonalDetailsUpdate() {
		
		login = new ThriveLoginPage();
		getExtentTestLogger().log(Status.PASS, "langauge dropdown,username,password and click login");
		thriveHeaderMenuPage = login.login(loginDetails);
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User search the coach to update and click on it");
		internalCoachesPage.setSearch(internalCoachUserUpdate);
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnSearchedCoachName(internalCoachUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User provides the first name to update");
		registerCoachPersonalDetailsPage.setFirstName(coachUpdateDetails.getFirstName());
		
		getExtentTestLogger().log(Status.PASS, "User provides the Last name to update");
		registerCoachPersonalDetailsPage.setLastName(coachUpdateDetails.getLastName());
		
		getExtentTestLogger().log(Status.PASS, "User provide the mobileno to update for coach");
		registerCoachPersonalDetailsPage.setMobileNumber(coachUpdateDetails.getMobileNumber());
		
		getExtentTestLogger().log(Status.PASS, "click fill in address manually link");
		registerCoachPersonalDetailsPage.clickFillInAddressManuallyLink();
		
		getExtentTestLogger().log(Status.PASS, "User provides the address1 to update for coach");
		registerCoachPersonalDetailsPage.setAddress1(coachUpdateDetails.getAddress1());
		
		getExtentTestLogger().log(Status.PASS, "User provides the address2 to update for coach");
		registerCoachPersonalDetailsPage.setAddress2(coachUpdateDetails.getAddress2());
		
		getExtentTestLogger().log(Status.PASS, "User provides the city to update for coach");
		registerCoachPersonalDetailsPage.setCity(coachUpdateDetails.getCity());
		
		getExtentTestLogger().log(Status.PASS, "User provides the state to update for coach");
		registerCoachPersonalDetailsPage.setState(coachUpdateDetails.getState());
		
		getExtentTestLogger().log(Status.PASS, "User provides the county to update for coach");
		registerCoachPersonalDetailsPage.setCounty(coachUpdateDetails.getCounty());
		
		getExtentTestLogger().log(Status.PASS, "User provides the postcode to update for coach");
		registerCoachPersonalDetailsPage.setPostCode(coachUpdateDetails.getPostcode());
		
		getExtentTestLogger().log(Status.PASS, "User provides the gender to update for coach");
		registerCoachPersonalDetailsPage.selectFirstAvailableGenderValue();
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			getExtentTestLogger().log(Status.PASS, "User provides the Ethnicity to update for coach");
			registerCoachPersonalDetailsPage.SelectFirstAvailableEthnicity();
		}
		
		getExtentTestLogger().log(Status.PASS, "User provides the nationality to update for coach");
		registerCoachPersonalDetailsPage.selectFirstAvailableNationality();
		
		getExtentTestLogger().log(Status.PASS, "User provides the region to update for coach");
	//	registerCoachPersonalDetailsPage.selectFirstAvailabelRegion();
		
		getExtentTestLogger().log(Status.PASS, "User provides the country to update for coach");
		registerCoachPersonalDetailsPage.selectFirstAvailableCountryValue();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the update details button");
		registerCoachPersonalDetailsPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets the updation notification");
		registerCoachPersonalDetailsPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Then all coach personal details fields updated successfully");
		registerCoachPersonalDetailsPage.validateCoachPersonalDetails(coachUpdateDetails);
		
		return this;
		
	}
	
	
	private UpdateInternalCoachProfileTest validateInternalCoachEmploymentUpdate() {
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User search the coach to update and click on it");
		internalCoachesPage.setSearch(internalCoachUserUpdate);
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnSearchedCoachName(internalCoachUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User click on employment link");
		registerCoachEmploymentPage = registerCoachPersonalDetailsPage.clickEmployment();
		
		getExtentTestLogger().log(Status.PASS, "provide the employment staue");
		registerCoachEmploymentPage.selectFirstAvailableEmpStatueValue();
		
		getExtentTestLogger().log(Status.PASS, "provide the other organizations currently associate with input");
		registerCoachEmploymentPage.setOtherOrganizationscurrentlyAssociateWith(coachUpdateDetails.getOtherOrgAssociateWith());
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the update details button");
		registerCoachEmploymentPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets the updation notification");
		registerCoachPersonalDetailsPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Then all coach Employment fields updated successfully");
		registerCoachEmploymentPage.validateCoachEmploymentDetails(coachUpdateDetails);
		
		return this;
	}
	
	
	private UpdateInternalCoachProfileTest validateInternalCoachSkillsAndQualificatiosUpdate() {
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User search the coach to update and click on it");
		internalCoachesPage.setSearch(internalCoachUserUpdate);
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnSearchedCoachName(internalCoachUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the skills and qualification link");
		registerCoachSkillsAndQualificationPage = registerCoachPersonalDetailsPage.clickSkillsAndQualifications();
		
		getExtentTestLogger().log(Status.PASS, "provide qualification to update");
		registerCoachSkillsAndQualificationPage.setQualification(coachSkillAndQualificationDetails.getQualification());
		
		getExtentTestLogger().log(Status.PASS, "provide Other qualifications relevent for coaching");
		registerCoachSkillsAndQualificationPage.setOtherQualification(coachSkillAndQualificationDetails.getOtherQualification());
		
		getExtentTestLogger().log(Status.PASS, "provide Other professional qualifications relevent for coaching");
		registerCoachSkillsAndQualificationPage.setOtherProfessionalQualification(coachSkillAndQualificationDetails.getOtherProfessionalQualification());
		
		getExtentTestLogger().log(Status.PASS, "provide date to update");
		registerCoachSkillsAndQualificationPage.selectFirstAvailableDate();
		
		getExtentTestLogger().log(Status.PASS, "provide language to update");
		registerCoachSkillsAndQualificationPage.selectFirstAvailabellanguage();
		
		getExtentTestLogger().log(Status.PASS, "provide tech platform to update");
		registerCoachSkillsAndQualificationPage.selectFirstAvailabelTechPlatform();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the update details button");
		registerCoachSkillsAndQualificationPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets the updation notification");
		registerCoachPersonalDetailsPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "all the skills and qualifications fields updated successfully");
		registerCoachSkillsAndQualificationPage.validateCoachSkillsAndQualificationsDetails(coachSkillAndQualificationDetails);
		
		return this;
	}
	
	
	private UpdateInternalCoachProfileTest validateInternalCoachLanguagesUpdate() {
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User search the coach to update and click on it");
		internalCoachesPage.setSearch(internalCoachUserUpdate);
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnSearchedCoachName(internalCoachUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User click on employment link");
		registerCoachLanguagesPageRm = registerCoachPersonalDetailsPage.clickLanguages();
		
		getExtentTestLogger().log(Status.PASS, "provide language to update");
		registerCoachLanguagesPageRm.selectFirstAvailableLanguage();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the update details button");
		registerCoachLanguagesPageRm.clickUpdateDetails();
			
		getExtentTestLogger().log(Status.PASS, "The user gets the updation notification");
		registerCoachLanguagesPageRm.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Then all coach Language fields updated successfully");
		registerCoachLanguagesPageRm.validateLanguagesDetails();
		
		return this;
	}
	
	
	private UpdateInternalCoachProfileTest validateInternalCoachCorporateExpertise() {
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User search the coach to update and click on it");
		internalCoachesPage.setSearch(internalCoachUserUpdate);
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnSearchedCoachName(internalCoachUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User click on corporate expertise link");
		registerCoachCorporateExpPage = registerCoachPersonalDetailsPage.clickCorporateExpertise();
		
		getExtentTestLogger().log(Status.PASS, "User senior position to update");
		registerCoachCorporateExpPage.selectFirstAvailableSeniorPoition();
		
		getExtentTestLogger().log(Status.PASS, "User provides industry to update");
		registerCoachCorporateExpPage.selectFirstAvailabelIndustry();
		
		getExtentTestLogger().log(Status.PASS, "User provides company to update");
		registerCoachCorporateExpPage.selectFirstAvailableCompany();
		
		getExtentTestLogger().log(Status.PASS, "User provides the companies provided coaching to update");
		registerCoachCorporateExpPage.setCompaniesProvidedCoachingToInput(coachCorporateExpUpdateDetails.getCompaniedProviedCoaching());
		
		getExtentTestLogger().log(Status.PASS, "User provides the roles held to update");
		registerCoachCorporateExpPage.setRolesYouHeld(coachCorporateExpUpdateDetails.getRolesHeld());
		
		getExtentTestLogger().log(Status.PASS, "User provides the career sme role to update");
		registerCoachCorporateExpPage.setCareerSMERoles(coachCorporateExpUpdateDetails.getCareerSmeRole());
		
		getExtentTestLogger().log(Status.PASS, "User provides the working week spend in corporate role update");
		registerCoachCorporateExpPage.setWorkingWeekInCorporateRole();
		
		getExtentTestLogger().log(Status.PASS, "User provides the career has been in business to update");
		registerCoachCorporateExpPage.setCareerInBusiness();
		
		getExtentTestLogger().log(Status.PASS, "User provides the career has been in corporate role to update");
		registerCoachCorporateExpPage.setCareerInCorporateRoles();
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the update details button");
		registerCoachCorporateExpPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets the updation notification");
		registerCoachPersonalDetailsPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "All the corporate expertise fields updated successfully");
		registerCoachCorporateExpPage.validateCoachCorporateExpertiseDetails(coachCorporateExpUpdateDetails);
		
		return this;
	}
	

	private UpdateInternalCoachProfileTest validateInternalCoachMentoringExperience() {
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User search the coach to update and click on it");
		internalCoachesPage.setSearch(internalCoachUserUpdate);
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnSearchedCoachName(internalCoachUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the coaching experience link");
		registerCoachMentoringExpPage = registerCoachPersonalDetailsPage.clickCoachingExperience();
		
		getExtentTestLogger().log(Status.PASS, "User provide the level of expertise to update");
		registerCoachMentoringExpPage.selectInternalAvailableExpertiseOption();
//		
//		getExtentTestLogger().log(Status.PASS, "User provides the industry sectors you coached to update");
//		registerCoachMentoringExpPage.selectFirstAvailableIndustrySectors();
//		
//		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
//			
//			getExtentTestLogger().log(Status.PASS, "User provides the companies you have coached to update");
//			registerCoachMentoringExpPage.selectFirstAvailableCoachedCompany();
//			
//			getExtentTestLogger().log(Status.PASS, "User provides the level of people you coached to update");
//			registerCoachMentoringExpPage.selectFirstAvailableCoachesLevelPeople();
//				
//			getExtentTestLogger().log(Status.PASS, "User provides the hours of coaching in a month to update");
//			registerCoachMentoringExpPage.setCoachingHours();
//		}
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the update details button");
		registerCoachMentoringExpPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets the updation notification");
		registerCoachPersonalDetailsPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "The coach coaching experience updated successfully");
		registerCoachMentoringExpPage.validateInternalCoachMentoringExperience();
		
		return this;
	}
	
	
	private UpdateInternalCoachProfileTest validateInternalCoachAbout() {
		
		getExtentTestLogger().log(Status.PASS, "User navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();
		
		getExtentTestLogger().log(Status.PASS, "User search the coach to update and click on it");
		internalCoachesPage.setSearch(internalCoachUserUpdate);
		registerCoachPersonalDetailsPage = internalCoachesPage.clickOnSearchedCoachName(internalCoachUserUpdate);
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the about link");
		registerCoachAboutPage = registerCoachPersonalDetailsPage.clickProfileForClient();
		
		getExtentTestLogger().log(Status.PASS, "User provides the summary to update");
		registerCoachAboutPage.setSummaryInput(coachAboutDetails.getSummry());
		
		getExtentTestLogger().log(Status.PASS, "User provides the Testinomial to update");
		registerCoachAboutPage.setTestinomial(coachAboutDetails.getTestimoial());
		
		getExtentTestLogger().log(Status.PASS, "User provides the coaching summary to update");
		registerCoachAboutPage.setCoachingSummary(coachAboutDetails.getCoachingSummary());
		
		if(Config.getTestPlatform().equalsIgnoreCase("thrive")) {
			getExtentTestLogger().log(Status.PASS, "User provides the top new industries to update");
			registerCoachAboutPage.setTopNewIndustries(coachAboutDetails.getTopNewIndustry());
			
//			getExtentTestLogger().log(Status.PASS, "User provides the about to update");
//			registerCoachAboutPage.setAboutInput(coachAboutDetails.getAbout());
//			
//			getExtentTestLogger().log(Status.PASS, "User provides the caoch offers to update");
//			String coachOffer = "Webinars";
//			registerCoachAboutPage.selectFirstAvailabelCoachOffers(coachOffer);
			
			getExtentTestLogger().log(Status.PASS, "User provides the coach experience to update");
			registerCoachAboutPage.setCoachExperience(coachAboutDetails.getYearsAsCoach());
			
			getExtentTestLogger().log(Status.PASS, "User provides the professional summary to update");
			registerCoachAboutPage.setProfessionalSummary(coachAboutDetails.getProfessionalSummary());
		}
		
		getExtentTestLogger().log(Status.PASS, "User clicks on the update details button");
		registerCoachAboutPage.clickUpdateDetails();
		
		getExtentTestLogger().log(Status.PASS, "The user gets the updation notification");
		registerCoachPersonalDetailsPage.validateToaster(ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue());
		
		getExtentTestLogger().log(Status.PASS, "The coach about updated successfully");
		registerCoachAboutPage.validateCoachAboutInfo(coachAboutDetails);
		
		return this;
	}
	
	
	

}
