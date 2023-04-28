package com.thrive.ui.page.dashboard;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.FileUtils;
import com.thrive.ui.page.common.UserManagementCommonPage;

public class ThriveInsightPage extends UserManagementCommonPage{
	
	String pdfExtension = ThriveAppSharedData.PDFEXTENSION.getValue();

	
	private By getDownloadDropdown() {
		return By.xpath(".//ng-select[@bindvalue='exportIn']");
	}
	
	private By getDownloadDropdownValue(String format) {
		return By.xpath(".//div[@class='ng-dropdown-panel-items scroll-host']//div[text()='"+format+"']");
	}
	
	private By getThriveDownloadSpinner() {
		return By.xpath(".//ng-select[contains(@class,'spinner')]");
	}
	
	public ThriveInsightPage validateThriveRecordsDownloadedPdf() {
		LOGGER.info("Validating records dowloaded successfully in pdf format");
		waitUntilElementInvisible(getThriveDownloadSpinner());
		Assert.assertTrue(FileUtils.isFileDownloaded(getDownloadedFolderPath, pdfExtension));
		FileUtils.delteDownloadedFile(getDownloadedFolderPath,pdfExtension);
		return this;
	}
	
	public ThriveInsightPage selectDownloadOption(String format) {
		LOGGER.info("Select download option form dropdown");
		mediumWait();
		click(getDownloadDropdown());
		click(getDownloadDropdownValue(format));
		return this;
		
	}
	
	
	
	

}
