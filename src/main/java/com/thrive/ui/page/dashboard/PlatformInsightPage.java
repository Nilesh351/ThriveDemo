package com.thrive.ui.page.dashboard;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.FileUtils;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class PlatformInsightPage extends UserManagementCommonPage{
	
	String pdfExtension = ThriveAppSharedData.PDFEXTENSION.getValue();
	
	private By getDownloadButton() {
		return By.xpath(getXpathByText(".//button[contains(text(),'admin.platform_insights.export')]"));
	}
	
	private By getRegisteredUsersCount() {
		return By.xpath(".//div[contains(@class,'register-user-container')]/span[2]");
	}
	
	private By getCompletedSessionCount() {
		return By.xpath(".//div[contains(@class,'completed-session-container')]/span[2]");
	}
	
	private By getActiveUsersCount() {
		return By.xpath(".//div[contains(@class,'chart-holder chart-holder-text active-user-container')]/span[2]");
	}
	
	private By getplatformDownloadSpinner() {
		return By.xpath(".//button[contains(@class,'btn btn-default export-platform-insights-btn tp-smallbtn primary-col primary-border h-auto mb-0 spinner')]");
	}
	
	
	public PlatformInsightPage validatePlatformRecordsDownloadedPdf() {
		LOGGER.info("Validating records dowloaded successfully in pdf format");
		waitUntilElementInvisible(getplatformDownloadSpinner());
		Assert.assertTrue(FileUtils.isFileDownloaded(getDownloadedFolderPath, pdfExtension));
		FileUtils.delteDownloadedFile(getDownloadedFolderPath,pdfExtension);
		return this;
	}
	
	
	public PlatformInsightPage clickDownloadButton() {
		LOGGER.info("Click on Downlad button");
		click(getDownloadButton());
		return this;
	}
	
	public int registeredCount() {
		LOGGER.info("Get registered users count");
		shortWait();
		waitUntilElementIsVisible(getRegisteredUsersCount());
		String users = getText(getRegisteredUsersCount());
		int value = Integer.parseInt(users);
		return value;
	}
	
	public int activeUsersCount() {
		LOGGER.info("Get active users count");
		shortWait();
		waitUntilElementIsVisible(getActiveUsersCount());
		String active = getText(getActiveUsersCount())	;
		int users = Integer.parseInt(active);
		return users;
		
	}
	
	public int completedSessionCount() {
		LOGGER.info("Get registered users count");
		shortWait();
		waitUntilElementIsVisible(getCompletedSessionCount());
		String session = getText(getCompletedSessionCount());
		int completed = Integer.parseInt(session);
		return completed;
	}
	
	
	
	public PlatformInsightPage validateRegisteredUsersCount(int count) {
		LOGGER.info("Get registered users count is updated successfully");
		shortWait();
		waitUntilElementIsPresent(getRegisteredUsersCount());
		String users = getText(getRegisteredUsersCount());
		int updated = Integer.parseInt(users);
		int expected = count + 1;
		System.out.println("actual count "+updated);
		System.out.println("expecetd count "+expected);
		Assert.assertEquals(updated, expected,"register users count not updated");	
		return this;
	}
	
	public PlatformInsightPage valdiateActiveusersCount(int count) {
		shortWait();
		waitUntilElementIsVisible(getActiveUsersCount());
		String active = getText(getActiveUsersCount())	;
		int updated = Integer.parseInt(active);
		int expected = count + 1;
		System.out.println("actual count "+updated);
		System.out.println("expecetd count "+expected);
		Assert.assertEquals(updated, expected,"register users count not updated");
		return this;
	}
	
	
	public PlatformInsightPage validateCompletedSessionCount(int count) {
		LOGGER.info("Get completed session count is updated successfully");
		// Static wait is added to wait for the data to get loaded in the element
		mediumWait();
		waitUntilElementIsPresent(getCompletedSessionCount());
		String sessions = getText(getCompletedSessionCount());
		int updated = Integer.parseInt(sessions);
		int expected = count + 1;
		LOGGER.info("actual count "+updated);
		LOGGER.info("expecetd count "+expected);
		Assert.assertEquals(updated, expected,"completed sessions count not updated");	
		return this;
	}
	
	

}
