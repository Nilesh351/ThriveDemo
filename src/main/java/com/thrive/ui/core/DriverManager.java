package com.thrive.ui.core;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;

import com.thrive.common.testdata.utils.ThriveAppSharedData;
import com.thrive.common.utils.FileUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager{

	protected static final Logger LOGGER = LogManager.getLogger(DriverManager.class);
	private static boolean createDownloadFolder = true;
	private static String downloadFilePath = "";

	private enum DriverInUse {
		Primary, Secondary
	}

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<WebDriver> secondaryDriver = new ThreadLocal<WebDriver>();
	private static ThreadLocal<String> appName = new ThreadLocal<String>();
	private static DriverInUse driverInUse = DriverInUse.Primary;

	public static String getAppName() {
		return appName.get();
	}

	public static void usePrimaryDriver() {
		driverInUse = DriverInUse.Primary;
	}

	public static void useSecondaryDriver() {

		if (secondaryDriver.get() == null) {
			throw new IllegalStateException("Can't use secondary driver; was never set");
		}
		driverInUse = DriverInUse.Secondary;
	}

	public static boolean isSecondaryDriverCreated() {
		return secondaryDriver.get() != null;
	}

	public static WebDriver getDriver() {
		return (driverInUse == DriverInUse.Primary) ? getPrimaryDriver() : getSecondaryDriver();
	}

	private static WebDriver getPrimaryDriver() {
		return webDriver.get();
	}

	private static WebDriver getSecondaryDriver() {
		return secondaryDriver.get();
	}

	public static void setAppName(String value) {
		appName.set(value);
	}

	static void setPrimaryWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	static void setSecondaryWebDriver(WebDriver driver) {
		secondaryDriver.set(driver);
	}

	public static BasicCookieStore getDriverCookieStore() {

		Set<Cookie> cookies = getDriver().manage().getCookies();
		BasicCookieStore cookieStore = new BasicCookieStore();

		for (Cookie c : cookies) {
			BasicClientCookie cookie = new BasicClientCookie(c.getName(), c.getValue());
			Objects.requireNonNull(cookie).setDomain(c.getDomain());
			cookie.setPath(c.getPath());
			cookieStore.addCookie(cookie);
		}
		return cookieStore;
	}

	public static void initWebDriver(WebDriver driver, boolean secondary) {
		setPrimaryOrSecondaryWebDriver(driver,secondary);
		driver.get(Config.getLoginPageURL());
		setBrowserWindowSizeBasedOnTestRunMode(driver);
	}


	private static void setPrimaryOrSecondaryWebDriver(WebDriver driver, boolean secondary) {
		if (!secondary) {
			DriverManager.setPrimaryWebDriver(driver);
		} else {
			DriverManager.setSecondaryWebDriver(driver);
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		DriverManager.setAppName(Config.getAppName()); 
	}

	private static void setBrowserWindowSizeBasedOnTestRunMode(WebDriver driver) {
		if (Config.getRunMode().equalsIgnoreCase(TestRunMode.Grid.getValue())) {
			driver.manage().window().setSize(new Dimension(1600, 900));
		} else if (Config.getRunMode().equalsIgnoreCase(TestRunMode.Local.getValue())) {
			driver.manage().window().maximize();
		}
		LOGGER.info("WebDriver instantiation successful");
	}
	
	
	public static String getSesionId() {
		WebDriver driver = getDriver();
		String sessionId = ((RemoteWebDriver)driver).getSessionId().toString();
		return sessionId;	
	}

	public static WebDriver createInstance(String browserType) {
		
		String gridUrl = Config.getGridURL();
		WebDriver driver = null;
		DesiredCapabilities capability = null;

		if (browserType.equalsIgnoreCase("ie")) {
			browserType = "internet explorer";
		}

		if (Config.getRunMode().equalsIgnoreCase(TestRunMode.Grid.getValue())) {

			switch (browserType.toLowerCase()) {

			case BrowserType.FIREFOX:
				capability = DesiredCapabilities.firefox();
				break;

			case BrowserType.IE:
				capability = DesiredCapabilities.internetExplorer();
				break;

			case BrowserType.EDGE:
				capability = DesiredCapabilities.edge();
				break;

			case BrowserType.HTMLUNIT:
				capability = DesiredCapabilities.htmlUnit();
				capability.setCapability(ChromeOptions.CAPABILITY, getChromeHeadlessOption());
				break;

			case BrowserType.CHROME:
				System.setProperty("webdriver.chrome.driver", "/thrive-test-automation/src/test/resources/bin/chromedriver.exe");
				capability = DesiredCapabilities.chrome();
				if(Config.isChromeHeadless()) {
					if(Config.isRunModeCicd()) {
						capability.setCapability(ChromeOptions.CAPABILITY, getChromeHeadlessCicd());
					} else {
						capability.setCapability(ChromeOptions.CAPABILITY, getChromeHeadlessOption());
					}
				} else {
					if(Config.isRunModeCicd()) {
						capability.setCapability(ChromeOptions.CAPABILITY, getChromeOptionCicd());
					} else {
						capability.setCapability(ChromeOptions.CAPABILITY, getChromeOption());
					}

				}
				break;

			}

			if (capability != null) {
				try {
					driver = new RemoteWebDriver(new URL(gridUrl), capability);
					//((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
				} catch (MalformedURLException e) {
					LOGGER.error("Unable to create Remote Webdriver: " + e.getMessage());
				}
			} else {
				throw new NullPointerException("Capability is not set properly");
			}

		} else if (Config.getRunMode().equalsIgnoreCase(TestRunMode.Local.getValue())) {

			switch (browserType) {

			case BrowserType.FIREFOX:
				driver = WebDriverManager.firefoxdriver().create(); 
				break;

			case BrowserType.IE:

				if (SystemUtil.isMacOs()) {
					throw new RuntimeException("Cannot run IE locally on Mac OS");
				}

				driver = WebDriverManager.iedriver().capabilities(getIEOptions()).create();
//				synchronized (wdm) {
//					wdm.arch32().version(ThriveAppSharedData.IE_11_DRIVER_32_VERSION_3_141_59.getValue()).setup();
//				}
//				driver = new InternetExplorerDriver(getIEOptions());
				break;

			case BrowserType.EDGE:

				if (SystemUtil.isMacOs()) {
					throw new RuntimeException("Cannot run edge locally on Mac OS");
				}

				driver = WebDriverManager.edgedriver().create();
				break;

			case BrowserType.CHROME:
				
				if(Config.isChromeHeadless()) {
					if(Config.isRunModeCicd()) {
						driver = WebDriverManager.chromedriver().capabilities(getChromeHeadlessCicd()).create();
					} else {
						driver = WebDriverManager.chromedriver().capabilities(getChromeHeadlessOption()).create();
					}
				} else if(Config.isRunModeCicd()) {
						driver = WebDriverManager.chromedriver().capabilities(getChromeOptionCicd()).create();
				} else {
						driver = WebDriverManager.chromedriver().capabilities(getChromeOption()).create();
				}
				break;
			} 
				
		}
		LOGGER.info("WebDriver instantiation successful");
		return driver;
	}


	public static void afterMethod() {

		DriverManager.usePrimaryDriver();
		WebDriver driver = getDriver();

		if (driver != null) {
			deinitWebDriver(driver);
		}

		if (DriverManager.isSecondaryDriverCreated()) {

			DriverManager.useSecondaryDriver();
			driver = getDriver();

			if (driver != null) {
				deinitWebDriver(driver);
			}
		}
	}

	public static void deinitWebDriver(WebDriver driver) {
		driver.quit();
		LOGGER.info("Driver quit successfully");
	}

	public static void finishOnFail(ITestResult iTestResult) {
		try {
			LOGGER.info("Capturing screenshot");
			WebDriver driver = getDriver();

			if (driver != null) {
				String filePath = String.format(FileUtils.getScreenshotFilePath(), iTestResult.getMethod().getMethodName());
				BaseTestPage.captureScreenshot(filePath);
			}
		} catch (Exception e) {
			LOGGER.error("Failed to capture screenshot! " + e.getMessage());
		}
		try {
			LOGGER.info("Finishing test run");
			afterMethod();
		} catch (Exception e) {
			LOGGER.error("Failed to finish the session " + e.getMessage());
		}
	}

	private static ChromeOptions getChromeOption() {
		ChromeOptions chromeOptions = new ChromeOptions();
		HashMap<String, Object> chromeOptionsMap = new HashMap<>();
		
		String downloadFilepath = getDowloadFilePath();
		chromeOptionsMap.put("download.default_directory", downloadFilepath);
		 
		chromeOptions.setExperimentalOption("prefs", chromeOptionsMap);
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("--disable-extensions");
		chromeOptions.addArguments("--no-proxy-server");
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		//chromeOptions.setBinary("/mythrive-test-automation/selenium-server/chromedriver.exe");
		return chromeOptions;
	}
	
	private static ChromeOptions getChromeOptionCicd() {
		return getChromeOption().setBinary("/app/.apt/usr/bin/google-chrome");
	}

	private static InternetExplorerOptions getIEOptions() {

		InternetExplorerOptions ieOptions = new InternetExplorerOptions();

		ieOptions.setCapability("ignoreZoomSetting", true);
		ieOptions.setCapability("requireWindowFocus", false);
		ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieOptions.setCapability("unexpectedAlertBehaviour", "accept");
		ieOptions.setCapability("ignoreProtectedModeSettings", true);
		ieOptions.setCapability("disable-popup-blocking", true);
		ieOptions.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,
				true);
		return ieOptions;
	}

	private static ChromeOptions getChromeHeadlessOption() {

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		return chromeOptions;
	}
	
	private static ChromeOptions getChromeHeadlessCicd() {
		return getChromeHeadlessOption().setBinary("/app/.apt/usr/bin/google-chrome");
	}

	private static String getDowloadFilePath() {

		if (createDownloadFolder) {
			downloadFilePath = Config.getDownloadFolderPath();
			LOGGER.info(downloadFilePath);
			File f = new File(downloadFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			createDownloadFolder = false;
		}
		return downloadFilePath;
	}
}
