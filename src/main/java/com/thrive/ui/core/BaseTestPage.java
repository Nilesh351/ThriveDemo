package com.thrive.ui.core;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.thrive.api.core.BaseApiTest;
import com.thrive.common.utils.ExtentReportUtils;
import com.thrive.common.utils.FileUtils;

@Listeners(TestListener.class)
public class BaseTestPage extends BaseApiTest {


	protected static final int MAX_DOM_ELEMENT_WAIT_SEC = 45;//90;
	protected static final int MAX_DOM_ELEMENT_FLUENT_WAIT_SEC = 20;//30;
	protected static final int MAX_DOM_ELEMENT_EXISTCHECK_WAIT_SEC = 10;
	private static final long POLLING_INTERVAL_MILI_SEC = 200;

	public static Table<String, String, String> translationTable;

	public static ExtentReports extentReports = ExtentReportUtils.createInstance();

	public static String resourcePath = Config.getTestResourceDir();
	public static String driversPath = Config.getTestResourceDir(); //TODO : delete
	public static String screenShotsPath = Config.getScreenshotsFolderPath();
	public static String getDownloadedFolderPath = Config.getDownloadFolderPath();

	public static String superAdminUser = Config.getSuperuserUser();
	public static String superAdminUserPassword = Config.getSuperuserPassword();

	public static String accountManagerUser = Config.getAccountManagerUser();
	public static String accountManagerPassword = Config.getAccountManagerPassword();

	public static String eaUserImmutableEmail = Config.getEAUserName();
	public static String eaUserPassword = Config.getEAUserPassword();
	public static String eaImmutableName = Config.getEAName();

	public static String amMutableEmail = Config.getAccountManagerMutableUser();
	public static String amMutablePassword = Config.getAccountManagerMutablePassword();
	public static String amMutableName = Config.getAccountManagerMutableName();

	public static String amUserUpdate = Config.getAccountManagerUserUpdate();
	public static String amPasswordUpdate = Config.getAccountManagerPasswordUpdate();
	public static String amNameUpdate = Config.getAccountManagerNameUpdate();

	public static String eaMutableEmail = Config.getEAMutableUserEmail();
	public static String eaMutablePassword = Config.getEAMutableUserPassword();
	public static String eaMutableName = Config.getEAMutableName();

	public static String ea2MutableUser = Config.getEA2MutableUserName();
	public static String ea2MutableUserPassword = Config.getEA2MutableUserPassword();

	public static String eaUserUpdate = Config.getEAUserUpdate();
	public static String eaPasswordUpdate = Config.getEAPasswordUpdate();
	public static String eaNameUpdate = Config.getEANameUpdate();

	public static String clientUser = Config.getClientUserName();
	public static String clientPassword = Config.getClientUserPassword();
	public static String clientName = Config.getClientName();

	public static String clientUserUpdate = Config.getClientUserUpdate();
	public static String clientPasswordUpdate = Config.getClientPasswordUpdate();
	public static String clientNameUpdate = Config.getClientNameUpdate();

	public static String internalCoachUser = Config.getInternalCoachUserName();
	public static String internalCoachPassword = Config.getInternalCoachPassword();
	public static String internalCoachName = Config.getInternalCoachName();

	public static String internalMutableCoachUser = Config.getInternalMutableCoachUserName();
	public static String internalMutableCoachPassword = Config.getInternalMutableCoachPassword();
	public static String internalMutableCoachName = Config.getInternalMutableCoachName();

	public static String internalCoachUserUpdate = Config.getInternalCoachUserUpdate();
	public static String internalCoachPasswordUpdate = Config.getInternalCoachPasswordUpdate();
	public static String internalCoachNameUpdate = Config.getInternalCoachNameUpdate();

	public static String globalCoachUser = Config.getGlobalCoachUserName();
	public static String globalCoachPassword = Config.getGlobalCoachPassword();
	public static String globalCoachName = Config.getGlobalCoachName();

	public static String globalCoachUserUpdate = Config.getGlobalCoachUserUpdate();
	public static String globalCoachPasswordUpdate = Config.getGlobalCoachPasswordUpdate();
	public static String globalCoachNameUpdate = Config.getGlobalCoachNameUpdate();

	public static String globalMutableCoachUser = Config.getGlobalMutableCoachUserName();
	public static String globalMutableCoachPassword = Config.getGlobalMutableCoachPassword();
	public static String globalMutableCoachName = Config.getGlobalMutableCoachName();

	public static String enterprise = Config.getEnterpriseNameImmutable();
	public static String mutableEnterpirse = Config.getMutableEnterprise();
	public static String enterpriseArchiveTest = Config.getEnterpriseArchiveTest();

	public static String category = Config.getCategoryImmutable();
	public static String topic = Config.getTopic();
	public static String expertise = Config.getExpertise();

	public static String mutableCategory = Config.getMutableCategory();
	public static String mutableTopic = Config.getMutableTopic();
	public static String mutableExpertise = Config.getMutableExpertise();

	public static String internalCategory = Config.getInternalCategory();
	public static String internalTopic = Config.getInternalTopic();
	public static String internalExpertise = Config.getInternalExpertise();

	public static String internalMutableCategory = Config.getInternalMutableCategory();
	public static String internalMutableTopic = Config.getInternalMutableTopic();
	public static String internalMutableExpertise = Config.getInternalMutableExpertise();

	protected static final Logger LOGGER = LogManager.getLogger(BaseTestPage.class);

	protected WebDriver getDriver() {
		return DriverManager.getDriver();
	}

	protected void launchMailApp(String appUrl) {
		DriverManager.getDriver().get(appUrl);
	}

	protected void launchThriveApp(String appUrl) {
		DriverManager.getDriver().get(appUrl);
	}

	public String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}

	protected ExtentTest getExtentTestLogger() {
		return ExtentReportUtils.getExtentTest();
	}

	protected long getMaxWaitTimeOut() {
		return Config.getWaitSecsMaxTimeout();
	}

	protected void switchToDefaultFromFrame() {
		getDriver().switchTo().defaultContent();
	}

	protected void switchToFrame(By locator) {
		getDriver().switchTo().frame(findElement(locator));
	}

	/**
	 * Clicks on the elements and checks that page starts loading with default timeout
	 * @param ids - composite id of element
	 */
	protected void click(WebElement element) {
		click(Config.getWaitSecsMaxTimeout(), element);
	}

	protected void click(By locator) {
		WebElement element = findElement(locator);
		click(Config.getWaitSecsMaxTimeout(), element);
	}

	protected void clickByJavaScript(By locator) {
		WebElement element = findElement(locator);
		clickByJavaScript(element);
	}

	protected void clickByJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)getDriver();
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Clicks on the elements and checks that page starts loading
	 * @param timeout - maximum time to wait
	 * @param ids - composite id of element
	 */
	protected void click(long timeout, WebElement element) {

		waitForPageLoaded(timeout);
		Integer[] attempt = {0};
		LOGGER.info("Trying to click on click on :" + element.toString());
		new WebDriverWait(getDriver(), timeout)
		.ignoring(StaleElementReferenceException.class, WebDriverException.class)
		.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					if (element.isEnabled()) {
						String prev = getDriver().getPageSource();
						element.click();
						attempt[0]++;
						return (!prev.equals(getDriver().getPageSource()) || attempt[0] > 0);
					}
				} catch (NoSuchElementException e) {
					if (attempt[0] > 0) return true;
				}
				return false;
			}

			@Override
			public String toString() {
				return String.format("element (%s) to be clicked", element);
			}
		});
	}


	/**
	 * Sets the value to input field such as input, checkbox, radio buttons etc.
	 * @param l 
	 * @param postcode - value to set
	 * @param ids - composite id of element
	 */
	protected void setValue(long l, int postcode, By by) {
		setValue(Config.getWaitSecsMaxTimeout(), postcode, by);
	}


	protected void setValue(String value, By locator) {
		WebElement element = findElement(locator);
		setValue(Config.getWaitSecsMaxTimeout(), value, element);
	}

	protected void setValueWithoutValidation(String value, By locator) {
		WebElement element = findElement(locator);
		setValueWithotValidation(Config.getWaitSecsMaxTimeout(), value, element);
	}

	protected void setValueJavaScript(String value, By locator) {
		WebElement element = findElement(locator);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].value='"+value+"';", element);
	}

	/**
	 * Sets the value to input field such as input, checkbox, radio buttons etc.
	 * @param timeout - maximum time to wait
	 * @param value - value to set
	 * @param ids - composite id of element
	 */

	protected void setValue(long timeout, String value, WebElement element) {
		if (null == value) return;

		LOGGER.info("Trying to set value on :" + element.toString());
		new WebDriverWait(getDriver(), timeout)
		.ignoring(StaleElementReferenceException.class, WebDriverException.class)
		.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) { 
				element.clear();
				element.sendKeys(value);
				return (endsWith(value, getAttributeByValue("value", element)) ||
						startsWith(value, getAttributeByValue("value", element)) ||
						startsWith(value, getAttributeByValue("title", element)) ||
						startsWith(value, getText(element)));
			}

			@Override
			public String toString() {
				return String.format("text '%s' to be set in element (%s)", value, element);
			}
		});
	}

	protected void setValueWithotValidation(long timeout, String value, WebElement element) {
		if (null == value) return;

		LOGGER.info("Trying to set value on :" + element.toString());
		new WebDriverWait(getDriver(), timeout)
		.ignoring(StaleElementReferenceException.class, WebDriverException.class)
		.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) { 
				element.clear();
				element.sendKeys(value);
				return true;
			}

			@Override
			public String toString() {
				return String.format("text '%s' to be set in element (%s)", value, element);
			}
		});
	}

	protected String getAttributeByValue(String attributeName, WebElement element) {
		return element.getAttribute(attributeName);
	}

	protected String getAttributeByValue(String attributeName, By locator) {
		WebElement element = findElement(locator);
		return getAttributeByValue(attributeName, element);
	}

	private boolean startsWith(String string, String substring){
		if (null == substring) return false;
		if (string.isEmpty() && substring.isEmpty()) return true;
		if (!string.isEmpty() && substring.isEmpty()) return false;
		return string.startsWith(substring);
	}

	private boolean endsWith(String string, String substring){
		if (null == substring) return false;
		if (string.isEmpty() && substring.isEmpty()) return true;
		if (!string.isEmpty() && substring.isEmpty()) return false;
		return string.endsWith(substring);
	}

	protected String getText(WebElement element) {
		waitUntilElementIsVisible(element);
		return element.getText();
	}

	protected String getText(By locator) {
		WebElement element = findElement(locator);
		return getText(element);
	}


	protected List<String> getValuesFromList(By locator){    	
		return getValuesFromList (Config.getWaitSecsMedium(),findElement(locator));
	}

	protected List<String> getValuesFromList(long timeout, WebElement element) {
		List<String> values = Lists.newArrayList();
		return new WebDriverWait(getDriver(), timeout)
				.ignoring(StaleElementReferenceException.class, WebDriverException.class)
				.until(new ExpectedCondition<List<String>>() {
					@Override
					public List<String> apply(WebDriver driver) {
						Select dropList = new Select(element);
						dropList.getOptions().forEach(option -> values.add(option.getText()));
						return values;
					}

					@Override
					public String toString() {
						return String.format("all available options to be collected for element (%s)", element);
					}
				});
	}

	protected boolean isChecked(By locator) {
		return isElementPresent(locator) && findElement(locator).isSelected();
	}

	protected boolean isSelected(By locator) {
		return isChecked(locator);
	}

	protected void checkCheckbox(boolean check, WebElement element) {
		checkCheckbox(getMaxWaitTimeOut(), check, element);
	}

	protected void checkCheckbox(boolean check, By locator) {
		WebElement element = findElement(locator);
		checkCheckbox(getMaxWaitTimeOut(), check, element);
	}

	/**
	 * Checks or unchecks the checkbox dependant on check flag
	 * @param check - check/uncheck flag
	 * @param ids - composite id of element
	 */
	protected void checkCheckbox(long timeout, boolean check, WebElement element) {

		new WebDriverWait(getDriver(), timeout)
		.ignoring(StaleElementReferenceException.class, WebDriverException.class)
		.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {

				if (check == element.isSelected()) {
					return true;
				}
				if (!element.isEnabled()) {
					return  false;
				}

				element.click();
				return (check == element.isSelected());
			}

			@Override
			public String toString() {
				return String.format("element (%s) to be %s", element, (check ? "checked" : "unchecked"));
			}

		});
	}

	protected WebElement findElement(long timeout, By locator) {
		waitUntilElementIsPresent(timeout, locator);
		return getDriver().findElement(locator);
	}

	protected WebElement findElement(By locator) {
		return findElement(Config.getWaitSecsMaxTimeout(), locator);
	}

	protected List<WebElement> findElements(By locator) {
		waitUntilElementIsPresent(Config.getWaitSecsMaxTimeout(), locator);
		return getDriver().findElements(locator);
	}

	/**
	 * Waits while page to be loaded
	 */
	protected void waitForPageLoaded() {
		waitForPageLoaded(getMaxWaitTimeOut());
	}

	/**
	 * Waits while page to be loaded and wait for given timeout
	 */
	protected void waitForPageLoaded(long timeout) {
		new WebDriverWait(getDriver(), timeout)
		.until((ExpectedCondition<Boolean>) driver -> 
		((JavascriptExecutor) Objects.requireNonNull(driver))
		.executeScript("return document.readyState")
		.equals("complete"));
	}

	protected WebElement waitUntilElementIsVisible(WebElement element) {
		return waitUntilElementIsVisible(getMaxWaitTimeOut(), element);
	}

	protected WebElement waitUntilElementIsVisible(long timeout, WebElement element) {
		FluentWait<WebDriver> wait = new WebDriverWait(getDriver(), timeout, POLLING_INTERVAL_MILI_SEC).ignoring(NoSuchElementException.class)
				.withMessage(String.format("Waiting for presence of element (%s)", element.getText()));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected WebElement waitUntilElementIsVisible(By locator) {
		return waitUntilElementIsVisible(getMaxWaitTimeOut(), locator);
	}

	protected WebElement waitUntilElementIsVisible(long timeout, By locator) {
		WebElement element = findElement(locator);
		FluentWait<WebDriver> wait = new WebDriverWait(getDriver(), timeout, POLLING_INTERVAL_MILI_SEC).ignoring(NoSuchElementException.class)
				.withMessage(String.format("Waiting for presence of element (%s)", element.getText()));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected WebElement waitUntilElementIsPresent(By by) {
		return waitUntilElementIsPresent(getMaxWaitTimeOut(), by);
	}


	protected WebElement waitUntilElementIsPresent(long timeout, By by) {
		FluentWait<WebDriver> wait = new WebDriverWait(getDriver(), timeout, POLLING_INTERVAL_MILI_SEC).ignoring(NoSuchElementException.class)
				.withMessage(String.format("Waiting for presence of element (%s)", by.toString()));
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}


	/**
	 * Checks if element exists or not
	 * @param ids - composite id of element
	 * @return boolean statement
	 */
	protected boolean isElementPresent(List<WebElement> elements) {
		return isElementPresent(getMaxWaitTimeOut(), elements);
	}

	/**
	 * Checks if element exists or not, wait for given timeout
	 * @param ids - composite id of element
	 * @param timeout - maximum time to wait
	 * @return boolean statement
	 */
	protected boolean isElementPresent(long timeout, List<WebElement> elements) {
		waitForPageLoaded(timeout);
		return !elements.isEmpty();
	}


	/**
	 * Checks if element exists or not
	 * @param ids - element
	 * @return boolean statement
	 */
	protected boolean isElementVisible(WebElement element) {
		return isElementVisible(getMaxWaitTimeOut(), element);
	}

	protected boolean isElementVisible(By locator) {
		return isElementVisible(MAX_DOM_ELEMENT_EXISTCHECK_WAIT_SEC, locator);
	}

	/**
	 * Checks if element exists or not
	 * @param ids - By by
	 * @return boolean statement
	 */
	protected boolean isElementPresent(By by) {
		return isElementPresent(MAX_DOM_ELEMENT_EXISTCHECK_WAIT_SEC, by);
	}

	/**
	 * Checks if element exists or not, wait for given timeout
	 * @param ids - composite id of element
	 * @param timeout - maximum time to wait
	 * @return boolean statement
	 */
	protected boolean isElementVisible(long timeout, WebElement element) {		   

		waitForPageLoaded(timeout);       
		return new WebDriverWait(getDriver(), timeout)
				.ignoring(StaleElementReferenceException.class, WebDriverException.class)
				.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {                       
						return element.isDisplayed() && 
								(element.getSize().getHeight() > 0 && 
										element.getSize().getWidth() > 0 );            		  
					}

					@Override
					public String toString() {
						return String.format("element %s to be visible", element);
					}

				});

	}


	protected boolean isElementVisible(long timeout, By locator) {		   
		waitForPageLoaded(timeout);
		WebElement element = null;
		try {
			element = findElement(timeout, locator);
		} catch (Exception e) {
			return false;
		}
		return isElementVisible(timeout, element);
	}

	/**
	 * Checks if element exists or not, wait for given timeout
	 * @param locator - By by
	 * @param timeout - maximum time to wait
	 * @return boolean statement
	 */
	protected boolean isElementPresent(long timeout, By by) {	

		waitForPageLoaded(timeout);
		try {
			waitUntilElementIsPresent(timeout, by);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return getDriver().findElements(by).size() > 0 ? true : false;
	}


	/**
	 * Checks if element not exist
	 * @param locator - By by
	 * @return boolean statement
	 */
	protected boolean isElementNotVisible(By by) {

		try {
			waitUntilElementInvisible(MAX_DOM_ELEMENT_EXISTCHECK_WAIT_SEC,  by);
		} catch (Exception e) {
			return false;
		}
		return getDriver().findElements(by).size() < 1 ? true : false;
	}

	protected boolean isElementNotVisible(WebElement element) {

		waitUntilElementInvisible(MAX_DOM_ELEMENT_EXISTCHECK_WAIT_SEC,  element);
		return element.getSize().height < 1 && element.getSize().width < 1 ? true : false;
	}





	protected BaseTestPage staticWait(long secs) {
		try {
			LOGGER.info("Waiting " + secs + " Seconds for App Refresh");
			Thread.sleep(secs * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LOGGER.info("Finished waiting " + secs + " Seconds");
		return this;
	}

	protected void shortWait() {
		staticWait(Config.getWaitSecsShort());
	}

	protected void mediumWait() {
		staticWait(Config.getWaitSecsMedium());
	}

	protected void longWait() {
		staticWait(Config.getWaitSecsLong());
	}

	protected boolean waitUntilElementInvisible(long maxTimeOutInSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), maxTimeOutInSeconds, POLLING_INTERVAL_MILI_SEC);
		return wait.until(ExpectedConditions.invisibilityOf(element));
	}

	protected boolean waitUntilElementInvisible(WebElement element) {
		return waitUntilElementInvisible(getMaxWaitTimeOut(), element);
	}

	protected boolean waitUntilElementInvisible(long maxTimeOutInSeconds, By by) {
		WebDriverWait wait = new WebDriverWait(getDriver(), maxTimeOutInSeconds, POLLING_INTERVAL_MILI_SEC);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	protected boolean waitUntilElementInvisible(By by) {
		return waitUntilElementInvisible(getMaxWaitTimeOut(), by);
	}

	protected void selectListBoxByValue(String value, WebElement element) {
		selectListBoxByValue(getMaxWaitTimeOut(), value, element);
	}

	protected void selectListBoxByValue(String value, By locator) {
		WebElement element = findElement(locator);
		selectListBoxByValue(getMaxWaitTimeOut(), value, element);
	}

	/**
	 * Selects the value from dropdown and waits the given timeout
	 * @param timeout - maximum time to wait
	 * @param value - value to be selected (Exact Match)
	 * @param WebElement - element
	 */

	protected void selectListBoxByValue(long timeout, String value, WebElement element) {

		LOGGER.info("Trying to select value from list :" + element.toString());
		new WebDriverWait(getDriver(), timeout)
		.ignoring(StaleElementReferenceException.class, WebDriverException.class)
		.until(ExpectedConditions.elementToBeClickable(element));
		new Select(element).selectByVisibleText(value);
	}

	protected void deSlectListBoxByValue(String value, WebElement element) {
		deSelectListBoxByValue(getMaxWaitTimeOut(), value, element);
	}

	protected void deSelectListBoxByValue(String value, By locator) {
		WebElement element = findElement(locator);
		deSelectListBoxByValue(getMaxWaitTimeOut(), value, element);
	}

	/**
	 * De-Selects the value from dropdown and waits the given timeout
	 * @param timeout - maximum time to wait
	 * @param value - value to be selected (Exact Match)
	 * @param WebElement - element
	 */

	protected void deSelectListBoxByValue(long timeout, String value, WebElement element) {

		LOGGER.info("Trying to de-select value from list :" + element.toString());
		new WebDriverWait(getDriver(), timeout)
		.ignoring(StaleElementReferenceException.class, WebDriverException.class)
		.until(ExpectedConditions.elementToBeClickable(element));
		new Select(element).deselectByValue(value);
	}

	/**
	 * performs drag and drop using java script
	 * @param source - source element which has the id attribute
	 * @param target - target element which has the id attribute
	 */
	public void dragAndDropJs(WebElement source, WebElement target) {

		String sourceElementId = source.getAttribute("id");
		String targetElementId = target.getAttribute("id");		
		String dragDropJs =  FileUtils.readFromFile(getDragDropHelperFilePath());

		dragDropJs = dragDropJs
				+ "$('#"+sourceElementId+"').simulateDragDrop({ dropTarget: '#"+targetElementId+"'});";
		((JavascriptExecutor) getDriver()).executeScript(dragDropJs);
	}

	public void dragAndDropJs(By sourceLocator, By targetLocator) {

		WebElement sourceElement = findElement(sourceLocator);
		WebElement targetElement = findElement(targetLocator);		
		dragAndDropJs(sourceElement, targetElement);

	}

	public void dragDropByCoordinates(By sourceLocator, int xOffset, int yOffset) {
		Actions action = new Actions(getDriver());
		WebElement sourceElement = findElement(sourceLocator);
		action.dragAndDropBy(sourceElement, xOffset, yOffset).build().perform();
	}

	private String getDragDropHelperFilePath() {
		return Config.getTestResourceDir() + File.separator + "drag-drop-helper.js";
	}

	public static void captureScreenshot(String filePath) {

		try {
			TakesScreenshot takeScreenshot = (TakesScreenshot) DriverManager.getDriver();
			File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
			org.apache.commons.io.FileUtils.copyFile(source, new File(filePath));

		} catch (Exception e) {
			LOGGER.warn("Exception while taking screenshot " + e.getMessage());
		}
	}

	public void moveToElement(By locator) {
		WebElement element = findElement(locator);
		moveToElement(element);
	}

	protected void moveToElementJs(By locator) {    	
		WebElement element = findElement(locator);
		moveToElementJs(element);
	}

	protected void moveToElement(WebElement element) {	
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element);
		actions.perform();
	}

	protected void moveToElementJs(WebElement element) {    	
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected String getSelectedValueFromList(By locator) {
		return getSelectedValueFromList(getMaxWaitTimeOut(), findElement(locator));
	}

	protected String getSelectedValueFromList(WebElement element) {
		return getSelectedValueFromList(getMaxWaitTimeOut(), element);
	}

	protected String getSelectedValueFromList(long timeout, WebElement element) {
		return new WebDriverWait(getDriver(), timeout)
				.ignoring(StaleElementReferenceException.class, WebDriverException.class)
				.until(new ExpectedCondition<String>() {
					@Override
					public String apply(WebDriver driver) {
						Select dropDown = new Select(element);
						try {
							WebElement option = dropDown.getFirstSelectedOption();
							return option.getText();
						} catch (NoSuchElementException e) {
							return "";
						}
					}

					@Override
					public String toString() {
						return String.format("get current selected option for element %s", element);
					}
				});
	}

	protected void switchToChildWindow(String parentWindow) {

		Set<String> handles = getDriver().getWindowHandles();

		for (String handle : handles) {
			if (!handle.equals(parentWindow)) {
				getDriver().switchTo().window(handle)
				.manage()
				.window().maximize();
				waitForPageLoaded();

				break;
			}
		}
	}

	private String getCurrentWindowHandle() {
		String currentHandle = getDriver().getWindowHandle();
		return currentHandle;
	}

	private void switchToWindow(String window) {
		getDriver().switchTo().window(window);
	}

	protected void switchToParentWindow(String parentWindow) {
		switchToWindow(parentWindow);
	}

	protected String getParentWindowHandle() {
		return getCurrentWindowHandle();
	}

	protected void switchToPriorWindow() {

		Set<String> handles = getDriver().getWindowHandles();
		String currentWindow = getDriver().getWindowHandle();

		for (String handle : handles) {
			if (!handle.equals(currentWindow)) {
				getDriver().switchTo().window(handle);
				waitForPageLoaded();
				break;
			}
		}
	}

	public String pasteFromClipboard() { 
		return Keys.chord(Keys.CONTROL, "v"); 
	} 

	public void openNewTabInBrowser() { 
		((JavascriptExecutor) getDriver()).executeScript("window.open()");
	} 

	public void switchToNewlyCreatedTab(Set<String> tabsBeforeCreatingNewBrowserTab) {

		TreeSet<String> tabsAfterCreatingNewBrowserTab = new TreeSet<String>(getDriver().getWindowHandles());		
		tabsAfterCreatingNewBrowserTab.removeAll(tabsBeforeCreatingNewBrowserTab);		
		String newlyCreatedBrowserTab = tabsAfterCreatingNewBrowserTab.first();
		getDriver().switchTo().window(newlyCreatedBrowserTab).manage().window().maximize();		
	}

	public Set<String> getAllBrowserTabs() {
		return new HashSet<String>(getDriver().getWindowHandles());
	}

	public static String appendToUrl(String url, Map<String, String> parameters) throws URISyntaxException
	{
		URI uri = new URI(url);
		String query = uri.getQuery();

		StringBuilder builder = new StringBuilder();

		if (query != null)
			builder.append(query);

		for (Map.Entry<String, String> entry: parameters.entrySet())
		{
			String keyValueParam = entry.getKey() + "=" + entry.getValue();
			if (!builder.toString().isEmpty())
				builder.append("&");

			builder.append(keyValueParam);
		}

		URI newUri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), builder.toString(), uri.getFragment());
		return newUri.toString();
	}

	public void selectGivenValueFromAutoDropdown(By locator,String value){

		WebElement element = findElement(locator);
		List<WebElement> options = findElements(locator);

		for(WebElement option : options){
			if(option.getText().equalsIgnoreCase(value)){
				option.click();
				break;
			}
		}
	}

	protected WebElement waitUntilElementIsClickable(long maxTimeOutInSeconds, By by) {
		WebElement element = findElement(maxTimeOutInSeconds, by);
		WebDriverWait wait = new WebDriverWait(getDriver(), maxTimeOutInSeconds, POLLING_INTERVAL_MILI_SEC);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void controlClick(By locator) {
		WebElement element = findElement(locator);
		controlclick( element);
	}

	protected void controlclick(WebElement element) {
		Actions action=new Actions(getDriver());
		action.keyDown(Keys.CONTROL).build().perform();
		element.click();
		//action.keyUp(Keys.CONTROL).build().perform();
	}

	protected List<String> getSortedList(By locator) {

		List<String> elementsText = new ArrayList<>();
		List<WebElement> elements = findElements(locator);
		for(WebElement element : elements) {
			elementsText.add(element.getText());
		}
		Collections.sort(elementsText);
		return elementsText;
	}

	protected int getElementXCoordinate(By element) {
		return getDriver().findElement(element).getLocation().getX();
	}

	protected int getElementYCoordinate(By element) {
		return getDriver().findElement(element).getLocation().getY();
	}


	protected BaseTestPage scrollByJavaScript(String horizontalOffset, String verticalOffset) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(" + horizontalOffset + "," + verticalOffset + ")");
		return this;
	}

	protected List<String> convertSetToList(Set<String> setOfStrings) {
		List<String> list = new ArrayList<String>(setOfStrings.size());
		for (String value : setOfStrings)
			list.add(value);
		return list;
	}

	protected BaseTestPage refreshPage() {
		getDriver().navigate().refresh();
		shortWait();
		return this;
	}

	public static String getXpathByText(String xPath) {

		List<String> translationKeys = new ArrayList<>();
		String improvedXpath = "";

		String[] subStr = xPath.split("/");
		for (int i = 0; i < subStr.length; i++) {
			if (subStr[i].contains(("text()")) && !subStr[i].contains(("@")) && !subStr[i].contains((" ")) && subStr[i].contains(("."))) {
				translationKeys.addAll(Arrays.asList(StringUtils.substringsBetween(subStr[i], "'", "'")));
			}
		}

		String columnKey = "";

		for (int i = 0; i < translationKeys.size(); i++) {
			Map<String, String> rowMap = BaseTestPage.translationTable.row(translationKeys.get(i));
			switch (Config.getLocalizationLanguage().toLowerCase()) {
			case "en":
				columnKey = rowMap.keySet().toArray(new String[rowMap.keySet().size()])[0].toLowerCase();
				break;
			case "fr":
				columnKey = rowMap.get(rowMap.keySet().toArray(new String[rowMap.keySet().size()])[0]).toLowerCase();
				break;
			default:
				columnKey = rowMap.keySet().toArray(new String[rowMap.keySet().size()])[0].toLowerCase();
			}
			if(i==0) {
				improvedXpath = xPath.replace(translationKeys.get(i), columnKey);
			} else {
				improvedXpath= improvedXpath.replace(translationKeys.get(i), columnKey);
			}
		}

		improvedXpath = improvedXpath.replace("text()",
				"translate(normalize-space(text()),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')");

		return improvedXpath;
	}

	public static String getResultByKey(String dbKey) {
		String columnKey = "";

		Map<String, String> rowMap = BaseTestPage.translationTable.row(dbKey);
		switch (Config.getLocalizationLanguage().toLowerCase()) {
		case "en":
			columnKey = rowMap.keySet().toArray(new String[rowMap.keySet().size()])[0];
			break;
		case "fr":
			columnKey = rowMap.get(rowMap.keySet().toArray(new String[rowMap.keySet().size()])[0]);
			break;
		default:
			columnKey = rowMap.keySet().toArray(new String[rowMap.keySet().size()])[0];
		}

		return columnKey;
	}

	public void selectValueByAttributeFromDropdown(By locator,String value){

		WebElement element = findElement(locator);
		List<WebElement> options = findElements(locator);

		for(WebElement option : options){
			if(option.getAttribute("value").equalsIgnoreCase(value)){
				option.click();
				break;
			}
		}
	}

}