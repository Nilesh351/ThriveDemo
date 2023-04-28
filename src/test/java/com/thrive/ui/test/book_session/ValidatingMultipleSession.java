package com.thrive.ui.test.book_session;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.book_session.BookSessionDetailsPage;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class ValidatingMultipleSession extends BaseTestPage{
	ThriveLoginPage login = new ThriveLoginPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	BookSessionViewPage bookSessionViewPage = new BookSessionViewPage();
	SessionsPage sessionsPage = new SessionsPage();
	BookSessionDetailsPage bookSessionDetailsPage = new BookSessionDetailsPage();
	String coachGlobalFirstName =  globalCoachName.split("First")[0];
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();


	@Test (description = "Validate Client is not able to book multiple sessions unless providing all previous Sessions deatils")
	public void validateMultipleSession() {
		getExtentTestLogger().assignCategory(TestCategory.VALIDATE_MULTIPLE_SESSIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Client provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Client navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Client gets a global coach and clicks on click on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);

		getExtentTestLogger().log(Status.PASS, "Client provides details");
		bookSessionViewPage.provideLengthDropdownvalues().selectSessionDate1();

		getExtentTestLogger().log(Status.PASS, "client verifies Multiple Sessions are available or not");
		bookSessionViewPage.verifyMulipleSessionbutton();

	}



}
