package com.thrive.ui.core;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.AWSUtility;
import com.thrive.common.utils.DBUtils;
import com.thrive.common.utils.DateTimeUtils;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.common.utils.FileUtils;


public class TestListener extends TestListenerAdapter implements IReporter, ISuiteListener, ITestListener, IRetryAnalyzer   {

	protected static final Logger LOGGER = LogManager.getLogger(TestListener.class);
	private static String imagePath;
	ExtentTest extentTestLocal;
	
	public static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		
		Map<String, String> suiteParams  = iTestResult.getTestContext().getCurrentXmlTest().getAllParameters();
		Object[] dataProviderParams = iTestResult.getParameters();
		
		if(dataProviderParams.length == 0) {
			extentTestLocal = BaseTestPage.extentReports.createTest(iTestResult.getMethod().getMethodName(),
					iTestResult.getMethod().getDescription());
			ExtentReportUtils.setExtentTest(extentTestLocal);

			LOGGER.info("onTestStart method - " + iTestResult.getMethod().getConstructorOrMethod().getName() + " start");
		} 
		
		if(Config.isTestRunLayerUi()) {
			LOGGER.info("Instantiating WebDriver");
			String browserType ;
			try {
				if(suiteParams.get("browser")==null) {
					browserType = Config.getPrimaryBrowser();
				} else {
					browserType = suiteParams.get("browser");
				}
				WebDriver driver = DriverManager.createInstance(browserType);
				DriverManager.initWebDriver(driver, false);
			} catch (Exception e) {
				LOGGER.info(e);
				LOGGER.error("Failed to instantiate primary WebDriver instance");
			}
			
		}
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		LOGGER.info("onTestSuccess method - " + getTestMethodName(iTestResult) + " succeed");

		try {
			LOGGER.info("Finishing test run");
			if(Config.isTestRunLayerUi()) {
				DriverManager.afterMethod();
			}
		} catch (Exception e) {
			LOGGER.error("Failed to finish the session: " + e.getMessage());
		}

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		LOGGER.info("onTestSkipped method - " +  getTestMethodName(iTestResult) + " skipped");
		ExtentReportUtils.getInstance().removeTest(ExtentReportUtils.getExtentTest());
		ExtentReportUtils.getExtentTest().skip(iTestResult.getThrowable());
		DriverManager.afterMethod();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		LOGGER.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
		if(Config.isTestRunLayerUi()) {
			DriverManager.finishOnFail(iTestResult);
		}

	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		
		LOGGER.info("Test FAILED: " + getTestMethodName(iTestResult));
		ExtentTest eTest = ExtentReportUtils.getExtentTest();
		DriverManager.finishOnFail(iTestResult);
		
		int currentStepNumber=0;
		if(Config.isTestRunLayerUi()) {
			
			imagePath = String.format(FileUtils.getScreenshotFilePath(),
					iTestResult.getMethod().getMethodName());
			
			String screenshotLocation =  String.format(FileUtils.getAwsScreenshotFileName(),
					iTestResult.getMethod().getMethodName());
			
			String awsImagePath = String.format(FileUtils.getAwsScreenshotFilePath(),
					iTestResult.getMethod().getMethodName());

			try {
				AWSUtility.copyToS3(imagePath, screenshotLocation);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			currentStepNumber = ExtentReportUtils.getExtentTest().getModel().getLogs().size();
			if (currentStepNumber > 0) {
				eTest.getModel().getLogs().get(currentStepNumber-1).setStatus(Status.FAIL);
				eTest.fail(iTestResult.getThrowable(), MediaEntityBuilder
						.createScreenCaptureFromPath(awsImagePath).build());
			}
			
		} else {
			currentStepNumber = ExtentReportUtils.getExtentTest().getModel().getLogs().size();
			if (currentStepNumber > 0) {
				eTest.getModel().getLogs().get(currentStepNumber-1).setStatus(Status.FAIL);
				eTest.fail(iTestResult.getThrowable());
			}
		}
		 

	}

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		LOGGER.debug("generateReport method");
	}

	@Override
	public void onStart(ISuite suite) {

		PropertyConfigurator.configure(Config.TEST_RESOURCE_DIRECTORY + File.separator + "log4j.properties");
		LOGGER.setLevel(Level.ALL);
		LOGGER.debug("Running beforeSuite method");
		if(Config.isTestRunLayerUi()) {
			LOGGER.debug("Getting all CMS translation table data");
			BaseTestPage.translationTable = DBUtils.getAllTranslationTableData();
		}
		String fileName = FileUtils.getReportFileName();
		LOGGER.info("Storing report at " + fileName);
		
	}

	@Override
	public void onFinish(ISuite suite) {
		LOGGER.debug("afterSuite method");
		String folderName = Config.getAWSReportFolderName();
		String reportsLocation = folderName + "/"+Config.getExtentReportName() + "_" + DateTimeUtils.getCurrentTimeStamp() + ".html";
		String from = Config.getMailReportFrom();
		String to = Config.getMailReportTo();
		String subject = ThriveAppSharedData.EMAIL_SUBJECT.getValue();
		String body = ThriveAppSharedData.EMAIL_BODY.getValue();
		if(BaseTestPage.extentReports!=null) {
			BaseTestPage.extentReports.flush();
			try {
			AWSUtility.copyToS3(ExtentReportUtils.getReportFileName(), reportsLocation);
			String url = AWSUtility.getSignedUrl(reportsLocation);
			body=body.replace("<REPORT_URL>", url);
			String[] recipientList = to.split(",");
			for(String recipient: recipientList ){
				
			AWSUtility.sendMail(recipient, from, subject, body);
			}

			}
			catch (Exception e) {
				LOGGER.error(e);
			}
		}
	}
	
	int retryCount = Config.getRetryCount();
	int maxRetryCount = 2;
	@Override
	public boolean retry(ITestResult iTestResult) {
		if (retryCount <= maxRetryCount) {
			LOGGER.info("Retrying test " + iTestResult.getName() + " with status "
					+ getResultStatusName(iTestResult.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
			retryCount++;
			DriverManager.finishOnFail(iTestResult);
			return true;
			
		}
		return false;
	}

	public String getResultStatusName(int status) {
		String resultName = null;
		if (status == 1)
			resultName = "SUCCESS";
		if (status == 2)
			resultName = "FAILURE";
		if (status == 3)
			resultName = "SKIP";
		return resultName;
	}
	
	
	
	/*
	 * @Override public void beforeConfiguration(ITestResult iTestResult) {
	 * 
	 * extentTestLocal =
	 * extentReports.createTest(iTestResult.getMethod().getMethodName(),
	 * iTestResult.getMethod().getDescription());
	 * ExtentReportUtils.setExtentTest(extentTestLocal);
	 * 
	 * LOGGER.info("onTestStart method - " +
	 * iTestResult.getMethod().getConstructorOrMethod().getName() + " start");
	 * 
	 * 
	 * }
	 */
	
	
}
