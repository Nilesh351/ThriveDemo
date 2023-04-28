package com.thrive.ui.test.category_management;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.CategoryDetails;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.category.AddCategoryPage;
import com.thrive.ui.page.category.CoachCategoriesPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.category.EnterpriseConfigureCategoryPage;
import com.thrive.ui.page.category.ManageCategoryPage;
import com.thrive.ui.page.common.UserManagementCommonPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;
import com.thrive.ui.page.register.RegisterEnterpriseDetailsPage;
import com.thrive.ui.page.user_management.search_filter.CoachProfilePage;
import com.thrive.ui.page.user_management.search_filter.EnterprisesPage;
import com.thrive.ui.page.user_management.search_filter.GlobalCoachesPage;
import com.thrive.ui.page.user_management.search_filter.InternalCoachesPage;

public class CategoryAndExpertiseManagementTest extends BaseTestPage {

	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails eaMutableLoginDetails = new LoginDetails(eaMutableEmail, eaMutablePassword);
	ThriveLoginPage login = new ThriveLoginPage();
	ManageCategoryPage manageCategoryPage = new ManageCategoryPage();
	AddCategoryPage addCategoryPage = new AddCategoryPage();
	EnterpriseConfigureCategoryPage configureCategory = new EnterpriseConfigureCategoryPage();
	UserManagementCommonPage userManagementCommonPage = new UserManagementCommonPage();
	EnterprisesPage enterprisesPage = new EnterprisesPage();
	RegisterEnterpriseDetailsPage registerEnterpriseDetailsPage=new RegisterEnterpriseDetailsPage();
	EnterpriseConfigureCategoryPage enterpriseConfigureCategoryPage = new EnterpriseConfigureCategoryPage();
	RegisterEnterpriseDetailsPage enterpriseDetailsPage = new RegisterEnterpriseDetailsPage();
	CoachProfilePage coachProfilePage = new CoachProfilePage();
	GlobalCoachesPage globalCoachesPage = new GlobalCoachesPage();
	InternalCoachesPage internalCoachesPage = new InternalCoachesPage();
	CoachCategoriesPage coachCategoriesPage = new CoachCategoriesPage();
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();
	List<String> categories = new ArrayList<String>();

	
	@Test(description = "validate user successfully create category,topic and expertise", enabled = true)
	public void validateCategoryTopicAndExpertiseCreation() {
		
		CategoryDetails categoryDetails = TestDataGenerator.generateCategoryDetails();

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the coaching -> manage categories");
		manageCategoryPage = coachingMenuPage.clickManageCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects category option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_TYPE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides category deatils and click on save");
		addCategoryPage.setCategoryDetails(categoryDetails).clickSaveButton().clickDiscardButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects topic option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_TOPIC.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides topic details and click on save");
		addCategoryPage.setTopicDetailsForCategory(categoryDetails).clickSaveButton().clickDiscardButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects expertise option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_EXPERTISE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides expertise details and click on save");
		addCategoryPage.setExpertiseDetailsForCategory(categoryDetails).clickSaveButton().clickDiscardButton();

		getExtentTestLogger().log(Status.PASS, "The category, topic and expertise added successfully");
		manageCategoryPage.validateCategory(categoryDetails).clickCategoryCollapseElement(categoryDetails)
				.validateTopic(categoryDetails).clickTopicCollapseElement(categoryDetails).validateExpertise(categoryDetails);
	}

	@Test(description = "validate user successfully edit category,topic and expertise", enabled = true)
	public void validateEditCategoryTopicAndExpertise() {
		
		CategoryDetails categoryDetails = TestDataGenerator.generateCategoryDetails();
		
		CategoryDetails categoryDetailsUpdated = TestDataGenerator.generateCategoryDetails();

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin creates category which to be updated");
		createCategoryTopicExpertise(categoryDetails);
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the coaching -> manage categories");
		manageCategoryPage = coachingMenuPage.clickManageCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin edit the category name and click on save button");
		manageCategoryPage.clickCategoryEdit(categoryDetails).setCategoryDetails(categoryDetailsUpdated).clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin edit the topic name and click on save buttton");
		manageCategoryPage.clickCategoryCollapseElement(categoryDetailsUpdated).clickTopicEdit(categoryDetails)
				.setTopicDetailsForCategory(categoryDetailsUpdated).clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin edit the expertise name and click on save button");		
		manageCategoryPage.clickCategoryCollapseElement(categoryDetailsUpdated).clickTopicCollapseElement(categoryDetailsUpdated)
			.clickExpertiseEdit(categoryDetails).setExpertiseDetailsForCategory(categoryDetailsUpdated).clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "The category , topic and expertise edited successfully");
		manageCategoryPage.validateCategory(categoryDetailsUpdated).clickCategoryCollapseElement(categoryDetailsUpdated)
				.validateTopic(categoryDetailsUpdated).clickTopicCollapseElement(categoryDetailsUpdated).validateExpertise(categoryDetailsUpdated);
	}
	
	
	@Test(description = "validate SA successfully create internal category,topic and expertise", enabled = true)
	public void validateInternalCategoryTopicAndExpertiseCreationSA() {
		
		CategoryDetails categoryDetails = TestDataGenerator.generateInternalCategoryDetails();

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> Enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();
		
		getExtentTestLogger().log(Status.PASS, "User search the enterprise and click on it");
		enterprisesPage.setSearch(eaMutableEmail);
		registerEnterpriseDetailsPage = enterprisesPage.clickOnSearchedEnterpriseName(eaMutableEmail);
		
		getExtentTestLogger().log(Status.PASS, "User click on configure categories tab");
		configureCategory = enterpriseDetailsPage.clickConfigureCategories();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects category option from add dropdown");
		addCategoryPage = configureCategory.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_TYPE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides category deatils and click on save");
		addCategoryPage.setCategoryDetails(categoryDetails).clickSaveButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects topic option from add dropdown");
		addCategoryPage = configureCategory.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_TOPIC.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides topic details and click on save");
		addCategoryPage.setTopicDetailsForCategory(categoryDetails).clickSaveButton();
		
		getExtentTestLogger().log(Status.PASS, "Superadmin selects expertise option from add dropdown");
		addCategoryPage = configureCategory.clickAdd()
				.AddCategoryType(ThriveAppSharedData.CATEGORY_EXPERTISE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides expertise details and click on save");
		addCategoryPage.setExpertiseDetailsForCategory(categoryDetails).clickSaveButton();
		
		getExtentTestLogger().log(Status.PASS, "The category, topic and expertise added successfully");
		configureCategory.validateCategory(categoryDetails).clickCategoryCollapseElement(categoryDetails)
				.validateTopic(categoryDetails).clickTopicCollapseElement(categoryDetails).validateExpertise(categoryDetails);
	}
	
	@Test(description = "validate EA successfully create internal category,topic and expertise", enabled = true)
	public void validateInternalCategoryTopicAndExpertiseCreationEA() {
		
		CategoryDetails categoryDetails = TestDataGenerator.generateInternalCategoryDetails();

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(eaMutableLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the coaching -> manage categories");
		manageCategoryPage = coachingMenuPage.clickConfigureCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects category option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_TYPE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides category deatils and click on save");
		addCategoryPage.setCategoryDetails(categoryDetails).clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects topic option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_TOPIC.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides topic details and click on save");
		addCategoryPage.setTopicDetailsForCategory(categoryDetails).clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects expertise option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd()
				.AddCategoryType(ThriveAppSharedData.CATEGORY_EXPERTISE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides expertise details and click on save");
		addCategoryPage.setExpertiseDetailsForCategory(categoryDetails).clickSaveButton();

		getExtentTestLogger().log(Status.PASS, "The category, topic and expertise added successfully");
		manageCategoryPage.validateCategory(categoryDetails).clickCategoryCollapseElement(categoryDetails)
				.validateTopic(categoryDetails).clickTopicCollapseElement(categoryDetails).validateExpertise(categoryDetails);
		
	}

	@Test(description = "validate SA user successfully disable the categories",priority = 1)
	public void validatedisableCategory() {

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the coaching -> manage categories");
		manageCategoryPage = coachingMenuPage.clickManageCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on disable all option for specific category");
		categories.add(mutableCategory);
		manageCategoryPage.enableOrDisableCategories(false, categories);

		getExtentTestLogger().log(Status.PASS, "Validate the category gets disabled successfully");
		manageCategoryPage.validateEnableOrDisableCategories(false, categories);
	}

	@Test(description = "validate SA user successfully enable the categories",priority = 2)
	public void validateEnableCategory() {

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the coaching -> manage categories");
		manageCategoryPage = coachingMenuPage.clickManageCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on enable all option for specific category");
		categories.add(mutableCategory);
		manageCategoryPage.enableOrDisableCategories(true, categories);

		getExtentTestLogger().log(Status.PASS, "Validate the category gets enabled successfully");
		manageCategoryPage.validateEnableOrDisableCategories(true, categories);
	}

	@Test(description = "validate SA user successfully disable the categories for an enterprise",priority = 3)
	public void validateDisableCategoryForEnterprise() {

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the enterprise to be searched and click on it");
		this.enterprisesPage.setSearch(mutableEnterpirse);
		enterpriseDetailsPage = enterprisesPage.clickEnterprise(mutableEnterpirse);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on configure categories");
		enterpriseConfigureCategoryPage = enterpriseDetailsPage.clickConfigureCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on disable all option for specific category");
		categories.add(mutableCategory);
		manageCategoryPage.enableOrDisableCategories(false, categories);

		getExtentTestLogger().log(Status.PASS, "Validate the category gets disabled successfully");
		manageCategoryPage.validateEnableOrDisableCategories(false, categories);
	}

	@Test(description = "validate SA user successfully enable the categories for an enterprise",priority = 4)
	public void validateEnableCategoryForEnterprise() {

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> enterprises");
		this.enterprisesPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the enterprise to be searched and click on it");
		this.enterprisesPage.setSearch(mutableEnterpirse);
		enterpriseDetailsPage = enterprisesPage.clickEnterprise(mutableEnterpirse);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on configure categories");
		enterpriseConfigureCategoryPage = enterpriseDetailsPage.clickConfigureCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on enable all option for specific category");
		categories.add(mutableCategory);
		manageCategoryPage.enableOrDisableCategories(true, categories);

		getExtentTestLogger().log(Status.PASS, "Validate the category gets enabled successfully");
		manageCategoryPage.validateEnableOrDisableCategories(true, categories);
	}

	@Test(description = "validate SA user successfully enable the categories for global coach",priority = 5, enabled= false)
	public void validateEnableCategoryForGlobalCoach() {
		String expMesesage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the mutable coach to be searched and click on it");
		globalCoachesPage.setSearch(globalMutableCoachUser);
		coachProfilePage = globalCoachesPage.clickSearchedCoach(globalMutableCoachUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on coach categories");
		coachProfilePage.clickCoachCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the enable radio option");
		categories.add(mutableCategory);
		coachCategoriesPage.enableDisableCategories(true, categories);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on update details");
		coachCategoriesPage.clickSaveChangesButton();

		getExtentTestLogger().log(Status.PASS, "Validate category enabled successfully");
		coachCategoriesPage.validateToaster(expMesesage);

	}

	@Test(description = "validate SA user successfully disable the categories for global coach",priority = 6, enabled = false)
	public void validateDiasableCategoryForGlobalCoach() {
		String expMesesage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides username,password and click on login");
		thriveHeaderMenuPage = login.login(saLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the user management -> coaches");
		globalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Superadmin provides the mutable coach to be searched and click on it");
		globalCoachesPage.setSearch(globalMutableCoachUser);
		coachProfilePage = globalCoachesPage.clickSearchedCoach(globalMutableCoachUser);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on coach categories");
		coachProfilePage.clickCoachCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects the disable radio option");
		categories.add(mutableCategory);
		coachCategoriesPage.enableDisableCategories(false, categories);

		getExtentTestLogger().log(Status.PASS, "Superadmin clicks on update details");
		coachCategoriesPage.clickSaveChangesButton();

		getExtentTestLogger().log(Status.PASS, "Validate category disabled successfully");
		coachCategoriesPage.validateToaster(expMesesage);
	}

	@Test(description = "validate EA user successfully enable the categories for internal coach",priority = 7)
	public void validateEnableCategoryForInternalCoach() {
		String expMesesage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();

		categories.add(internalMutableCategory);
		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Prerequisite - Internal category must be enabled for the enterprise for it to show in internal coach category");
		enableDisableCategories(categories);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin mutable user is logged into the application");
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaMutableLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS, "Enterprise admin provides the mutable internal coach to be searched and click on it");
		internalCoachesPage.setSearch(internalMutableCoachUser);
		coachProfilePage = internalCoachesPage.clickSearchedCoach(internalMutableCoachUser);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on coach categories");
		coachProfilePage.clickCoachCategories();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin selects the enable radio option for internal category");
		coachCategoriesPage.enableDisableCategories(true, categories);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on update details");
		coachCategoriesPage.clickSaveChangesButton();

		getExtentTestLogger().log(Status.PASS, " Validate category enabled successfully");
		coachCategoriesPage.validateToaster(expMesesage);
	}

	@Test(description = "validate EA user successfully Disable the categories for internal coach",priority = 8)
	public void validateDisableCategoryForInternalCoach() {
		String expMesesage = ThriveAppSharedData.COACH_UPDATE_DETAILS_MESSAGE.getValue();

		categories.add(internalMutableCategory);
		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());
		
		getExtentTestLogger().log(Status.PASS, "Prerequisite - Internal category must be enabled for the enterprise to for it to show in coach category");
		enableDisableCategories(categories);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin mutable user is logged into the application");
		thriveHeaderMenuPage = login.loginIgnoreAccept(eaMutableLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the user management -> internal coaches");
		internalCoachesPage = thriveHeaderMenuPage.clickUserManagementLink().clickInternalCoachesLink();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin provides the mutable internal coach to be searched and click on it");
		internalCoachesPage.setSearch(internalMutableCoachUser);
		coachProfilePage = internalCoachesPage.clickSearchedCoach(internalMutableCoachUser);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on coach categories");
		coachProfilePage.clickCoachCategories();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin selects the disable radio option for internal category");
		coachCategoriesPage.enableDisableCategories(false, categories);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin clicks on update details");
		coachCategoriesPage.clickSaveChangesButton();

		getExtentTestLogger().log(Status.PASS, " Validate category disabled successfully");
		coachCategoriesPage.validateToaster(expMesesage);
	}

	@Test(description = "validate EA user successfully enable the categories for enterprise",priority = 9)
	public void validateEnableCategoryForEnterpriseByEA() {

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin mutable user is logged into the application");
		thriveHeaderMenuPage = login.login(eaMutableLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the coaching -> configure categories");
		manageCategoryPage = coachingMenuPage.clickConfigureCategories();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin clicks on enable all option for specific internal mutable category");
		categories.add(internalMutableCategory);
		manageCategoryPage.enableOrDisableCategories(true, categories);

		getExtentTestLogger().log(Status.PASS, "Validate the category gets enabled successfully");
		manageCategoryPage.validateEnableOrDisableCategories(true, categories);
	}

	@Test(description = "Validate EA user successfully disable the categories for enterprise",priority = 10)
	public void validateDisableCategoryForEnterpriseByEA() {

		getExtentTestLogger().assignCategory(TestCategory.CATEGORY_EXPERTISE_MANAGEMENT.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterprise admin mutable user is logged into the application");
		thriveHeaderMenuPage = login.login(eaMutableLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterprise admin navigates to the coaching -> configure categories");
		manageCategoryPage = coachingMenuPage.clickConfigureCategories();

		getExtentTestLogger().log(Status.PASS,
				"Enterprise admin clicks on disable all option for specific internal mutable category");
		categories.add(internalMutableCategory);
		manageCategoryPage.enableOrDisableCategories(false, categories);

		getExtentTestLogger().log(Status.PASS, "Validate the category gets disabled successfully");
		manageCategoryPage.validateEnableOrDisableCategories(false, categories);
	}
	
	private void enableDisableCategories(List<String> categories) {
	
				
		getExtentTestLogger().log(Status.PASS, "Prerequisite - Internal category must be enabled for the enterprise to for it to show in coach category");
		thriveHeaderMenuPage = login.login(saLoginDetails);
		
		enterpriseDetailsPage = thriveHeaderMenuPage.clickUserManagementLink().clickEnterprisesMenu()
				.setEnterpriseSearch(mutableEnterpirse).clickEnterprise(mutableEnterpirse);
		
		enterpriseDetailsPage.clickConfigureCategories().enableOrDisableCategories(true, categories);
		thriveHeaderMenuPage.logout();
		
		
	}
	
	private void createCategoryTopicExpertise(CategoryDetails categoryDetails) {
		
		getExtentTestLogger().log(Status.PASS, "Superadmin navigates to the coaching -> manage categories");
		manageCategoryPage = coachingMenuPage.clickManageCategories();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects category option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_TYPE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides category deatils and click on save");
		addCategoryPage.setCategoryDetails(categoryDetails).clickSaveButton().clickDiscardButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects topic option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd().AddCategoryType(ThriveAppSharedData.CATEGORY_TOPIC.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides topic details and click on save");
		addCategoryPage.setTopicDetailsForCategory(categoryDetails).clickSaveButton().clickDiscardButton();

		getExtentTestLogger().log(Status.PASS, "Superadmin selects expertise option from add dropdown");
		addCategoryPage = manageCategoryPage.clickAdd()
				.AddCategoryType(ThriveAppSharedData.CATEGORY_EXPERTISE.getValue());

		getExtentTestLogger().log(Status.PASS, "Superadmin provides expertise details and click on save");
		addCategoryPage.setExpertiseDetailsForCategory(categoryDetails).clickSaveButton().clickDiscardButton();

		getExtentTestLogger().log(Status.PASS, "The category, topic and expertise added successfully");
		manageCategoryPage.validateCategory(categoryDetails).clickCategoryCollapseElement(categoryDetails)
				.validateTopic(categoryDetails).clickTopicCollapseElement(categoryDetails).validateExpertise(categoryDetails);
		
	}

}
