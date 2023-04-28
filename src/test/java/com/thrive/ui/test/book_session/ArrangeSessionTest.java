package com.thrive.ui.test.book_session;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.book_session.BookSessionViewPage;
import com.thrive.ui.page.category.CoachingMenuPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class ArrangeSessionTest  extends BaseTestPage {
	ThriveLoginPage login = new ThriveLoginPage();
	ThriveHeaderMenuPage thriveHeaderMenuPage;
	LoginDetails eaLoginDetails = new LoginDetails(eaUserImmutableEmail, eaUserPassword);
	BookSessionViewPage bookSessionViewPage = new BookSessionViewPage();
	String coachGlobalFirstName =  globalCoachName.split("First")[0];
	CoachingMenuPage coachingMenuPage = new CoachingMenuPage();

	@Test(description="Validate Enterprise Admin is unable to select SessionLength Until Selects ClientName")
	public void validateArrangeSessionLength() {
		getExtentTestLogger().assignCategory(TestCategory.ARRANGE_SESSIONS_VALIDATIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin Clicks on Arrange SessionCheckbox");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickArrangeSessioncheckbox();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin Vaidates Sessionlength");
		bookSessionViewPage.verifySessionLength();
	}


	@Test(description="Validate Enterprise Admin is unable to select SessionDate Until Selects SessionLength")
	public void validateArrangeSessionDate() {
		getExtentTestLogger().assignCategory(TestCategory.ARRANGE_SESSIONS_VALIDATIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin Clicks on Arrange SessionCheckbox");
		bookSessionViewPage.clickArrangeSessioncheckbox();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin Vaidates Sessiondate");
		bookSessionViewPage.verifySessionDate();
	}

	@Test(description="Validate Enterprise Admin is unable to select SessionTime Until Selects SessionDate")
	public void validateArrangeSessionTimr() {
		getExtentTestLogger().assignCategory(TestCategory.ARRANGE_SESSIONS_VALIDATIONS.getValue());

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin provides username,password and click on login");
		login = new ThriveLoginPage();
		thriveHeaderMenuPage = login.login(eaLoginDetails);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin navigates to the coaching -> book a session");
		this.bookSessionViewPage  = coachingMenuPage.clickBookSessionLink();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin selects global coach and clicks on book a session");
		bookSessionViewPage.clickBookASessionButton(coachGlobalFirstName);

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin Clicks on Arrange SessionCheckbox");
		bookSessionViewPage.clickArrangeSessioncheckbox();

		getExtentTestLogger().log(Status.PASS, "Enterpriseadmin Vaidates SessionTime");
		bookSessionViewPage.verifySessionTime();

	}



}
