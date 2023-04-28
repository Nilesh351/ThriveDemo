package com.thrive.ui.test.book_session;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionDetails;
import com.thrive.common.testdata.pojos.book_session.BookSessionSummaryDetails;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.book_session.ArrangeSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionConfirmationPage;
import com.thrive.ui.page.book_session.BookSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionSummaryPage;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.CoachBioPage;
import com.thrive.ui.page.book_session.SessionFeedbackPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class BookSessionFiltersTest extends BaseTestPage{
	
	ThriveLoginPage login = new ThriveLoginPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	LoginDetails saLoginDetails = new LoginDetails(superAdminUser, superAdminUserPassword);
	LoginDetails clientLoginDetails = new LoginDetails(clientUser, clientPassword);
	LoginDetails coachLoginDetails = new LoginDetails(globalMutableCoachUser,globalMutableCoachPassword);
	BookSessionViewPage bookSessionViewPage = new BookSessionViewPage();
	SessionsPage sessionsPage = new SessionsPage();
	BookSessionDetailsPage bookSessionDetailsPage = new BookSessionDetailsPage();
	BookSessionSummaryPage bookSessionSummaryPage = new BookSessionSummaryPage();
	ArrangeSessionDetailsPage arrangeSessionDetailsPage = new ArrangeSessionDetailsPage();
	BookSessionConfirmationPage bookSessionConfirmationPage;
	SessionFeedbackPage sessionFeedbackPage = new SessionFeedbackPage();
	BookSessionDetails bookSessionDetails;
	BookSessionDetails bookSessionDetailswithoutTime;
	BookSessionSummaryDetails bookSessionSummaryDetails;
	CoachBioPage coachBioPage;
	String region="Australia and Oceania";
	String searchKeyword = "Auto";
	String industry = "Accountancy";
	String coachType = "Internal";
	String language = "French";
	String coachGlobalFirstName =  globalCoachName.split("First")[0];
	String coachInternalFirstName =  internalCoachName.split("First")[0];
	String CoachEntInternalCoach = internalMutableCoachName.split("Fir1")[0];
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();
	
	@Test(description = "Validate user can select custom date range for Enterprise admin")
	public void customDateRangeSelection() {
		getExtentTestLogger().assignCategory("Book A Session - Date Range");
		
		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);
	
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Select date range");
		bookSessionViewPage.clickDateRange().selectDateRange();
		
		getExtentTestLogger().log(Status.PASS, "Validate date range selection");
		bookSessionViewPage.validateDateDateRangeSelection();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach availability in selected date range");
		bookSessionViewPage.verifyDisplayedCoachesData();
		
	}
	
	@Test(description = "Validate user can select custom date range for client")
	public void customDateRangeSelectionClient() {
		getExtentTestLogger().assignCategory("Book A Session - Date Range");
		
		getExtentTestLogger().log(Status.PASS, "Login with client credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(clientLoginDetails);
	
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Select date range");
		bookSessionViewPage.clickDateRange().selectDateRange();
		
		getExtentTestLogger().log(Status.PASS, "Validate date range selection");
		bookSessionViewPage.validateDateDateRangeSelection();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach availability in selected date range");
		bookSessionViewPage.verifyDisplayedCoachesData();
		
	}
	
	@Test(description = "Validate user can select custom date range for coach")
	public void customDateRangeSelectionCoach() {
		getExtentTestLogger().assignCategory("Book A Session - Date Range");
		
		getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(coachLoginDetails);
	
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaches");
		bookSessionViewPage=thriveHeaderMenuPage.clickCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Select date range");
		bookSessionViewPage.clickDateRange().selectDateRange();
		
		getExtentTestLogger().log(Status.PASS, "Validate date range selection");
		bookSessionViewPage.validateDateDateRangeSelection();
		
		getExtentTestLogger().log(Status.PASS, "Validate coach availability in selected date range");
		bookSessionViewPage.validateCoachesPresent();
	}
	
	@Test(description = "Validate selecting Favorites in 'Filter By Coach Type',displays list of favorite coaches only for enterprise admin")
	public void FilterByCoachTypeFavorites() {
		getExtentTestLogger().assignCategory("Book A Session - Filter By Coach Type");

		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);
	
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
	
		getExtentTestLogger().log(Status.PASS, "Click on Type");
		bookSessionViewPage.clickCoachTypeCheckbox(coachType);
		
		getExtentTestLogger().log(Status.PASS, "Validate only selected coach type list is displayed in Coaches section");
		bookSessionViewPage.validateCoachesTypePresent(coachType);
	}
	
	@Test(description = "Validate selecting Favorites in 'Filter By Coach Type',displays list of selected coach type for client")
	public void FilterByCoachTypeFavoritesClient() {
		getExtentTestLogger().assignCategory("Book A Session - Filter By Coach Type");

		getExtentTestLogger().log(Status.PASS, "Login with client credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(clientLoginDetails);
	
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
	
		getExtentTestLogger().log(Status.PASS, "Click on Coach Type");
		bookSessionViewPage.clickCoachTypeCheckbox(coachType);
		
		getExtentTestLogger().log(Status.PASS, "Validate only selected coach type Coaches displayed in section");
		bookSessionViewPage.validateCoachesTypePresent(coachType);
	}
	
	@Test(description = "Validate selecting Favorites in 'Filter By Coach Type',displays list of external coaches only for client")
	public void FilterByCoachTypeCoach() {
		getExtentTestLogger().assignCategory("Book A Session - Filter By Coach Type");
		
		coachType = "External";
		
		getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(coachLoginDetails);
	
		getExtentTestLogger().log(Status.PASS, "Navigate to Coaches");
		bookSessionViewPage=thriveHeaderMenuPage.clickCoaches();
	
		getExtentTestLogger().log(Status.PASS, "Click on Favorites in Filter By Coach Type");
		bookSessionViewPage.clickCoachTypeCheckbox(coachType);
		
		getExtentTestLogger().log(Status.PASS, "Validate only selectd Coach type section");
		bookSessionViewPage.validateCoachesTypePresent(coachType);
	}
	
	@Test(description = "Validate selecting language displays only selected langauge related coach data in Coaches section for enterprise admin")
	public void validateLanguageSelectionResult() {
		getExtentTestLogger().assignCategory("Book A Session - Langauge");

		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Select language");
		bookSessionViewPage.clickLanguageDropdown().selectlanguageCheckbox(language);

		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();

		getExtentTestLogger().log(Status.PASS,"Validate only selected language related coach data is displayed in Coaches section");
		coachBioPage.validateSelectedLanguageCoachData(language);
	}
	
	@Test(description = "Validate selecting language displays only selected langauge related coach data in Coaches section for client")
	public void validateLanguageSelectionResultClient() {
		getExtentTestLogger().assignCategory("Book A Session - Langauge");

		getExtentTestLogger().log(Status.PASS, "Login with client credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(clientLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Select language");
		bookSessionViewPage.clickLanguageDropdown().selectlanguageCheckbox(language);

		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();

		getExtentTestLogger().log(Status.PASS,"Validate only selected language related coach data is displayed in Coaches section");
		coachBioPage.validateSelectedLanguageCoachData(language);
	}
	
	@Test(description = "Validate selecting language displays only selected langauge related coach data in Coaches section for coach")
	public void validateLanguageSelectionResultCoach() {
		getExtentTestLogger().assignCategory("Book A Session - Langauge");

		getExtentTestLogger().log(Status.PASS, "Login with client credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(coachLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaches");
		bookSessionViewPage = thriveHeaderMenuPage.clickCoaches();

		getExtentTestLogger().log(Status.PASS, "Select language");
		bookSessionViewPage.clickLanguageDropdown().selectlanguageCheckbox(language);

		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();

		getExtentTestLogger().log(Status.PASS,"Validate only selected language related coach data is displayed in Coaches section");
		coachBioPage.validateSelectedLanguageCoachData(language);
	}
	
	@Test(description = "Validate selecting region, displays list of coaches in selected region only for enterprise admin")
	public void validateRegionSelectionResult() {
		getExtentTestLogger().assignCategory("Book A Session - Region");

		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Click Region filter and Select specific region");
		bookSessionViewPage.selectRegion(region);
		
		getExtentTestLogger().log(Status.PASS, "Validate list of coaches in selected region is displayed only");
		bookSessionViewPage.validateSelectedRegionCoaches(region);
	}
	
	@Test(description = "Validate selecting region, displays list of coaches in selected region only for client")
	public void validateRegionSelectionResultClient() {
		getExtentTestLogger().assignCategory("Book A Session - Region");

		getExtentTestLogger().log(Status.PASS, "Login with client credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(clientLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
		
		getExtentTestLogger().log(Status.PASS, "Click Region filter and Select specific region");
		bookSessionViewPage.selectRegion(region);
		
		getExtentTestLogger().log(Status.PASS, "Validate list of coaches in selected region is displayed only");
		bookSessionViewPage.validateSelectedRegionCoaches(region);
	}
	
	@Test(description = "Validate selecting region, displays list of coaches in selected region only for coach")
	public void validateRegionSelectionResultCoach() {
		getExtentTestLogger().assignCategory("Book A Session - Region");

		getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(coachLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaches");
		bookSessionViewPage = thriveHeaderMenuPage.clickCoaches();
		
		getExtentTestLogger().log(Status.PASS, "Click Region filter and Select specific region");
		bookSessionViewPage.selectRegion(region);
		
		getExtentTestLogger().log(Status.PASS, "Validate list of coaches in selected region is displayed only");
		bookSessionViewPage.validateSelectedRegionCoaches(region);
	}
	
	@Test(description = "Validate selecting Industry, displays list of coaches related to selected industrys only for enterprise admin")
	public void validateIndustrySelectionResult() {
		getExtentTestLogger().assignCategory("Book A Session - Industry");

		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Click Industry filter and Select specific industry");
		bookSessionViewPage.selectIndustry(industry);

		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();

		getExtentTestLogger().log(Status.PASS,
				"Validate list of coaches related to selected industry is displayed only");
		coachBioPage.validateSelectedIndustryCoachData(industry);
	}
	
	@Test(description = "Validate selecting Industry, displays list of coaches related to selected industrys only for client")
	public void validateIndustrySelectionResultclient() { 
		getExtentTestLogger().assignCategory("Book A Session - Industry");

		getExtentTestLogger().log(Status.PASS, "Login with client credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(clientLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Click Industry filter and Select specific industry");
		bookSessionViewPage.selectIndustry(industry);

		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();

		getExtentTestLogger().log(Status.PASS,
				"Validate list of coaches related to selected industry is displayed only");
		coachBioPage.validateSelectedIndustryCoachData(industry);
	}
	
	@Test(description = "Validate selecting Industry, displays list of coaches related to selected industrys only for coach")
	public void validateIndustrySelectionResultcoach() {
		getExtentTestLogger().assignCategory("Book A Session - Industry");

		getExtentTestLogger().log(Status.PASS, "Login with coach credentials");
		thriveHeaderMenuPage = new ThriveLoginPage().login(coachLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Navigate to Coaches");
		bookSessionViewPage = thriveHeaderMenuPage.clickCoaches();

		getExtentTestLogger().log(Status.PASS, "Click Industry filter and Select specific industry");
		bookSessionViewPage.selectIndustry(industry);

		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();

		getExtentTestLogger().log(Status.PASS,
				"Validate list of coaches related to selected industry is displayed only");
		coachBioPage.validateSelectedIndustryCoachData(industry);
	}


	@Test(description = "Validate user can search coach with keyword for enterprise admin")
   	public void coachSearchWithKeyWord() {   
   		getExtentTestLogger().assignCategory("Book A Session - Expertise Filter");

   		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials"); 
   		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);
   	
   		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
   		
   		getExtentTestLogger().log(Status.PASS, "click topic filter and select industry");
   		bookSessionViewPage.setCoach(searchKeyword);
   		
   		getExtentTestLogger().log(Status.PASS, "Validate Coaches section displays keyword related coach data");
   		bookSessionViewPage.validateKeywordSearch(searchKeyword);
    }
	
	@Test(description = "Validate user can search coach with keyword for client")
   	public void coachSearchWithKeyWordClient() {   
   		getExtentTestLogger().assignCategory("Book A Session - Expertise Filter");

   		getExtentTestLogger().log(Status.PASS, "Login with client credentials"); 
   		thriveHeaderMenuPage = new ThriveLoginPage().login(clientLoginDetails);
   	
   		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
   		
   		getExtentTestLogger().log(Status.PASS, "click topic filter and select industry");
   		bookSessionViewPage.setCoach(searchKeyword);
   		
   		getExtentTestLogger().log(Status.PASS, "Validate Coaches section displays keyword related coach data");
   		bookSessionViewPage.validateKeywordSearch(searchKeyword);
    }
	
	@Test(description = "Validate user can search coach with keyword for coach")
   	public void coachSearchWithKeyWordCoach() {   
   		getExtentTestLogger().assignCategory("Book A Session - Expertise Filter");

   		getExtentTestLogger().log(Status.PASS, "Login with coach credentials"); 
   		thriveHeaderMenuPage = new ThriveLoginPage().login(coachLoginDetails);
   	
   		getExtentTestLogger().log(Status.PASS, "Navigate to Coaches");
   		bookSessionViewPage=thriveHeaderMenuPage.clickCoaches();
   		
   		getExtentTestLogger().log(Status.PASS, "click topic filter and select industry");
   		bookSessionViewPage.setCoach(searchKeyword);
   		
   		getExtentTestLogger().log(Status.PASS, "Validate Coaches section displays keyword related coach data");
   		bookSessionViewPage.validateKeywordSearch(searchKeyword);
    }
	
	
	@Test(description = "Validate user selects expertise from topics for enterprise admin")
   	public void coachTopicsResult() {   
   		getExtentTestLogger().assignCategory("Book A Session - Expertise Filter");

   		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials"); 
   		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);
   	
   		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
   		
   		getExtentTestLogger().log(Status.PASS, "Enter keyword");
   		bookSessionViewPage.selectTopicExpertise(expertise);
   		
   		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();
   		
   		getExtentTestLogger().log(Status.PASS,
				"Validate list of coaches related to selected expertise is displayed only");
   		coachBioPage.validateExpertisepresent(expertise);
		
    }
	
	
	@Test(description = "Validate user selects expertise from topics for client")
   	public void coachTopicsResultClient() {   
   		getExtentTestLogger().assignCategory("Book A Session - Expertise Filter");

   		getExtentTestLogger().log(Status.PASS, "Login with client credentials"); 
   		thriveHeaderMenuPage = new ThriveLoginPage().login(clientLoginDetails);
   	
   		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
   		
   		getExtentTestLogger().log(Status.PASS, "Enter keyword");
   		bookSessionViewPage.selectTopicExpertise(expertise);
   		
   		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();
   		
   		getExtentTestLogger().log(Status.PASS,
				"Validate list of coaches related to selected expertise is displayed only");
   		coachBioPage.validateExpertisepresent(expertise);
		
    }
	
	@Test(description = "Validate user selects expertise from topics for coach")
   	public void coachTopicsResultCoach() {   
   		getExtentTestLogger().assignCategory("Book A Session - Expertise Filter");

   		getExtentTestLogger().log(Status.PASS, "Login with coach credentials"); 
   		thriveHeaderMenuPage = new ThriveLoginPage().login(coachLoginDetails);
   	
   		getExtentTestLogger().log(Status.PASS, "Navigate to Coaches");
   		bookSessionViewPage=thriveHeaderMenuPage.clickCoaches();
   		
   		getExtentTestLogger().log(Status.PASS, "Enter keyword");
   		bookSessionViewPage.selectTopicExpertise(expertise);
   		
   		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();
   		
   		getExtentTestLogger().log(Status.PASS,
				"Validate list of coaches related to selected expertise is displayed only");
   		coachBioPage.validateExpertisepresent(expertise);
		
    }
	
	@Test(description = "Validate user selects internal expertise from topics")
   	public void coachInternalTopicsResult() {   
   		getExtentTestLogger().assignCategory("Book A Session - Expertise Filter");

   		getExtentTestLogger().log(Status.PASS, "Login with enterprise admin credentials"); 
   		thriveHeaderMenuPage = new ThriveLoginPage().login(eaLoginDetails);
   	
   		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
   		
   		getExtentTestLogger().log(Status.PASS, "Enter keyword");
   		bookSessionViewPage.selectInternalTopicExpertise(internalExpertise);
   		
   		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();
   		
   		getExtentTestLogger().log(Status.PASS,
				"Validate list of coaches related to selected expertise is displayed only");
   		coachBioPage.validateExpertisepresent(internalExpertise);
		
    }
	
	@Test(description = "Validate user selects internal expertise from topics")
   	public void coachInternalTopicsResultClient() {   
   		getExtentTestLogger().assignCategory("Book A Session - Expertise Filter");

   		getExtentTestLogger().log(Status.PASS, "Login with client credentials"); 
   		thriveHeaderMenuPage = new ThriveLoginPage().login(clientLoginDetails);
   	
   		getExtentTestLogger().log(Status.PASS, "Navigate to Coaching -> Book A Session");
   		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();
   		
   		getExtentTestLogger().log(Status.PASS, "Enter keyword");
   		bookSessionViewPage.selectInternalTopicExpertise(internalExpertise);
   		
   		getExtentTestLogger().log(Status.PASS, "Clicking first coach name in Coaches section");
		coachBioPage = bookSessionViewPage.clickFirstCoachName();
   		
   		getExtentTestLogger().log(Status.PASS,
				"Validate list of coaches related to selected expertise is displayed only");
   		coachBioPage.validateExpertisepresent(internalExpertise);
		
    }
	
	
	
	

}
