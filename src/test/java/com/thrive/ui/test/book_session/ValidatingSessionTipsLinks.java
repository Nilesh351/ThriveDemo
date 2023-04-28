package com.thrive.ui.test.book_session;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.pojos.LoginDetails;
import com.thrive.common.testdata.utils.TestCategory;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.page.book_session.SessionsPage;
import com.thrive.ui.page.core.ThriveHeaderMenuPage;
import com.thrive.ui.page.core.ThriveLoginPage;

public class ValidatingSessionTipsLinks extends BaseTestPage{
	ThriveHeaderMenuPage thriveHeaderMenuPage = new ThriveHeaderMenuPage();
	LoginDetails loginDetails =new LoginDetails(eaMutableEmail,eaMutablePassword);
	ThriveLoginPage login = new ThriveLoginPage ();
	SessionsPage sessionsPage =new SessionsPage();

	@Test(description="Validating Clicking  CaochPromptLink Sends Email notification to Coach or not")
	public void validatingoCoachPromptLinkSendsEmail() {
		getExtentTestLogger().assignCategory(TestCategory.COACH_PROMPT_LINK.getValue());

		getExtentTestLogger().log(Status.PASS, "EnterpriseAdmin Provide Username,Password and clicks on Login");
		thriveHeaderMenuPage=login.login(loginDetails);

		getExtentTestLogger().log(Status.PASS, "EnterpriseAdmin Clicks on CoachingTab and JoinsSession");
		thriveHeaderMenuPage.clickCoachingButton().clickJoinSessionButton();

		getExtentTestLogger().log(Status.PASS, "EnterpriseAdmin Clicks on StartSession Button");
		sessionsPage.clickStartSessionButton();

		getExtentTestLogger().log(Status.PASS, "EntepriseAdmin Clicks on SessionTipsButton and validates Prompt,FAQ,Can't Connect Link");
		sessionsPage.clickSessionTips().ValidatePromptFAQCantConnectLinks();
	}


}
