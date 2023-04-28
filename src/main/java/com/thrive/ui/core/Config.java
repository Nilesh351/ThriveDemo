package com.thrive.ui.core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;

import com.thrive.common.utils.FileUtils;

public class Config {
	
	private static Configuration config;
	
	public static final String THRIVE_DIR = System.getProperty("thrive.test.properties.path", System.getProperty("user.dir"));
	public static final String TEST_RESOURCE_DIRECTORY = THRIVE_DIR + File.separator +  getResourcePath();
	private static final String KEY_FOLDER_THRIVE_TEST_SCREENSHOTS_RELATIVEPATH = "config.folder.thrivedev.screenshots.relativepath";	
	private static final String KEY_FOLDER_THRIVE_EXTENT_REPORTS_RELATIVEPATH = "config.folder.thrivedev.extent.report.relativepath";	

	private static final String TEST_CONFIG_PROPERTIES_FILENAME = "testConfig.properties";
	private static final String TEST_COFFIG_PROPERTIES_OVERRIDE_FILENAME = "testConfigOverride.properties";

	private static final String KEY_APP_NAME = "config.app.name";
	private static final String KEY_ENV = "config.env";
	private static final String KEY_TEST_PLATFORM = "config.test.platform";
	private static final String KEY_ENV_LOGINURL = "loginurl";

	private static final String KEY_BROWSER_PRIMARY = "config.browser.primary";
	private static final String KEY_BROWSER_SECONDARY = "config.browser.secondary";
	private static final String KEY_BROWSER_CHROME_HEADLESS = "config.browser.chrome.headless";
	private static final String KEY_RUNMODE = "config.runmode";
	private static final String KEY_RUNMODE_CICD = "config.runmode.cicd";
	private static final String KEY_GRIDURL = "config.gridurl";
	private static final String KEY_RETRY_COUNT = "config.retrycount";
	private static final String KEY_MFA_ENFORCE = "config.mfa.enforce";
	private static final String KEY_FEATUREFLAG_STATUS = "config.featureflag.status";
	private static final String KEY_LOCALIZATION_LANGUAGE = "config.localization.language";
	private static final String KEY_RUN_UI_TEST = "config.run.layer.ui";
	private static final String KEY_RUN_API_TEST = "config.run.layer.api";
	
	private static final String KEY_TASK_FAIL_DIRECTORY = "config.task.folder.fail.relativepath";
	private static final String KEY_TASK_LOG_DIRECTORY = "config.task.folder.log.relativepath";
	private static final String KEY_TASK_REDO_MIGRATION = "config.task.redomigration";
	private static final String KEY_EXTENT_REPORT_NAME = "config.test.extent.report.name";

	private static final String KEY_WEBDRIVER_CHROME_PROPERTY = "config.webdriver.chrome.driver.property";
	private static final String KEY_WEBDRIVER_FIREFOX_PROPERTY = "config.webdriver.gecko.driver.property";
	private static final String KEY_WEBDRIVER_IE_PROPERTY = "config.webdriver.ie.driver.property";
	private static final String KEY_WEBDRIVER_EDGE_PROPERTY = "config.webdriver.edge.driver.property";

	private static final String KEY_MAILDROP_URL = "config.maildrop.url";
	private static final String KEY_YAHOO_MAIL_URL = "config.yahoomail.url";
	private static final String KEY_YAHOO_MAIL_USERNAME = "config.yahoomail.username";
	private static final String KEY_YAHOO_MAIL_PASSWORD = "config.yahoomail.password";

	private static final String KEY_ENV_LOGOUTPAGEURL_SUFFIX = "logoutpageurl";
	private static final String KEY_ENV_SUPERUSER_USER_SUFFIX = "superuser.user";
	private static final String KEY_ENV_SUPERUSER_PASSWORD_SUFFIX = "superuser.password";
	private static final String KEY_ENV_AM_USER_SUFFIX = "am.user";
	private static final String KEY_ENV_AM_PASSWORD_SUFFIX = "am.password";
	private static final String KEY_ENV_EA_USER_SUFFIX = "ea.user";
	private static final String KEY_ENV_EA_PASSWORD_SUFFIX = "ea.password";
	private static final String KEY_ENV_EA_NAME_SUFFIX = "ea.name";
		
	private static final String KEY_ENV_AM_MUTABLE_USER_SUFFIX = "am.mutable.user";
	private static final String KEY_ENV_AM_MUTABLE_PASSWORD_SUFFIX = "am.mutable.password";
	private static final String KEY_ENV_AM_MUTABLE_NAME_SUFFIX = "am.mutable.name";
	
	private static final String KEY_ENV_AM_USER_UPDATE_SUFFIX = "am.user.update";
	private static final String KEY_ENV_AM_PASSWORD_UPDATE_SUFFIX = "am.password.update";
	private static final String KEY_ENV_AM_NAME_UPDATE_SUFFIX = "am.name.update";
	
	private static final String KEY_ENV_EA_MUTABLE_EMAIL = "ea.mutable.user";
	private static final String KEY_ENV_EA_MUTABLE_PASSWORD = "ea.mutable.password";
	private static final String KEY_ENV_EA_MUTABLE_NAME = "ea.mutable.name";

	private static final String KEY_ENV_EA_MUTABLE_USER_SUFFIX = "ea2.mutable.user";
	private static final String KEY_ENV_EA_MUTABLE_PASSWORD_SUFFIX = "ea2.mutable.password";
	
	private static final String KEY_ENV_EA_USER_UPDATE_SUFFIX = "ea.user.update";
	private static final String KEY_ENV_EA_PASSWORD_UPDATE_SUFFIX = "ea.password.update";
	private static final String KEY_ENV_EA_NAME_UPDATE_SUFFIX = "ea.name.update";
	
	private static final String KEY_ENV_CLIENT_USER_SUFFIX = "client.user";
	private static final String KEY_ENV_CLIENT_PASSWORD_SUFFIX = "client.password";
	private static final String KEY_ENV_CLIENT_NAME_SUFFIX = "client.name";
	
	private static final String KEY_ENV_CLIENT_USER_UPDATE_SUFFIX = "client.user.update";
	private static final String KEY_ENV_CLIENT_PASSWORD_UPDATE_SUFFIX = "client.password.update";
	private static final String KEY_ENV_CLIENT_NAME_UPDATE_SUFFIX = "client.name.update";
	
	private static final String KEY_ENV_COACH_INTERNAL_USER_SUFFIX = "coach.internal.user";
	private static final String KEY_ENV_COACH_INTERNAL_PASSWORD_SUFFIX = "coach.internal.password";
	
	private static final String KEY_ENV_COACH_INTERNAL_MUTABLE_USER_SUFFIX = "coach.internal.mutable.user";
	private static final String KEY_ENV_COACH_INTERNAL_MUTABLE_PASSWORD_SUFFIX = "coach.internal.mutable.password";
	private static final String KEY_ENV_COACH_INTERNAL_MUTABLE_COACH_NAME = "coach.internal.mutable.name";
	
	private static final String KEY_ENV_COACH_INTERNAL_COACH_NAME = "coach.internal.name";
	private static final String KEY_ENV_COACH_GLOBAL_COACH_NAME = "coach.global.name";

	private static final String KEY_ENV_COACH_GLOBAL_USER_SUFFIX = "coach.global.user";
	private static final String KEY_ENV_COACH_GLOBAL_PASSWORD_SUFFIX = "coach.global.password";
	
	private static final String KEY_ENV_COACH_GLOBAL_MUTABLE_USER_SUFFIX = "coach.global.mutable.user";
	private static final String KEY_ENV_COACH_GLOBAL_MUTABLE_PASSWORD_SUFFIX = "coach.global.mutable.password";
	private static final String KEY_ENV_COACH_GLOBAL_MUTABLE_COACH_NAME = "coach.global.mutable.name";
	
	private static final String KEY_ENV_COACH_GLOBAL_USER_UPDATE_SUFFIX = "coach.global.user.update";
	private static final String KEY_ENV_COACH_GLOBAL_PASSWORD_UPDATE_SUFFIX = "coach.global.password.update";
	private static final String KEY_ENV_COACH_GLOBAL_NAME_UPDATE_SUFFIX = "coach.global.name.update";
	
	private static final String KEY_ENV_COACH_INTERNAL_USER_UPDATE_SUFFIX = "coach.internal.user.update"; 
	private static final String KEY_ENV_COACH_INTERNAL_PASSWORD_UPDATE_SUFFIX = "coach.internal.password.update";
	private static final String KEY_ENV_COACH_INTERNAL_NAME_UPDATE_SUFFIX = "coach.internal.name.update";
	
	private static final String KEY_ENV_CATEGORY = "category";
	private static final String KEY_ENV_TOPIC = "topic";
	private static final String KEY_ENV_EXPERTISE = "expertise";
	private static final String KEY_ENV_MUTABLE_CATEGORY = "mutable.category";
	private static final String KEY_ENV_MUTABLE_TOPIC = "mutable.topic";
	private static final String KEY_ENV_MUTABLE_EXPERTISE = "mutable.expertise";
	
	private static final String KEY_ENV_INTERNAL_CATEGORY = "internal.category";
	private static final String KEY_ENV_INTERNAL_TOPIC = "internal.topic";
	private static final String KEY_ENV_INTERNAL_EXPERTISE = "internal.expertise";
	private static final String KEY_ENV_INTERNAL_MUTABLE_CATEGORY = "internal.mutable.category";
	private static final String KEY_ENV_INTERNAL_MUTABLE_TOPIC = "internal.mutable.topic";
	private static final String KEY_ENV_INTERNAL_MUTABLE_EXPERTISE = "internal.mutable.expertise";
	
	private static final String KEY_ENV_ENTERPRISE_ARCHIVE_TEST = "enterprise.archive"; 
	private static final String KEY_ENV_ENTERPRISE_IMMUTABLE = "enterprise.immutable.name";
	private static final String KEY_ENV_ENTERPRISE_MUTABLE = "enterprise.mutable";
		
	private static final String KEY_ENV_THRIVE_LOGOUT_URL_PREFIX = "config.env.logoutprefix";
	private static final String KEY_ENV_THRIVE_LOGOUT_URL_SUFFIX = "config.env.logoutsuffix";
	private static final String LOGIN_URL_ENV = "config.loginurlenv";

	private static final String KEY_ENV_GENERICUSERPASSWORD = "config.env.genericuserpassword";
	private static final String KEY_DOWNLOAD_FOLDER = "config.download.folder.relativepath";
	private static final String KEY_GRID_DOWNLOAD_FOLDER = "config.grid.download.output.folder";

	//Config Params
	private static final String KEY_PARAMS_WAITSECS_SHORT = "config.params.waitsecs.short";
	private static final String KEY_PARAMS_WAITSECS_MEDIUM = "config.params.waitsecs.medium";
	private static final String KEY_PARAMS_WAITSECS_LONG = "config.params.waitsecs.long";
	private static final String KEY_PARAMS_WAITSECS_MAXTIMEOUT = "config.params.waitsecs.maxtimeout";
	private static final String KEY_PARAMS_WAITSECS_MAXTIMEOUTFORVISITLIST = "config.params.waitsecs.maxtimeoutforvisitlist";

	//Reporting
	private static final String KEY_REPORT_EMAIL_START_ENABLED = "config.report.email.start.enabled";
	private static final String KEY_REPORT_EMAIL_SUCCESS_ENABLED = "config.report.email.success.enabled";
	private static final String KEY_REPORT_EMAIL_FAILURE_ENABLED = "config.report.email.failure.enabled";

	private static final String KEY_REPORT_USER = "config.report.user";
	private static final String KEY_REPORT_OUTPUT_FOLDER = "config.report.output.folder";

	private static final String KEY_REPORT_EMAIL_HOST = "config.report.email.host";
	private static final String KEY_REPORT_EMAIL_PORT = "config.report.email.port";
	private static final String KEY_REPORT_EMAIL_AUTHENTICATOR_ADDRESS = "config.report.email.authenticator.address";
	private static final String KEY_REPORT_EMAIL_AUTHENTICATOR_PASSWORD = "config.report.email.authenticator.password";
	private static final String KEY_REPORT_EMAIL_AUTHENTICATOR_SSL = "config.report.email.authenticator.ssl";
	private static final String KEY_REPORT_EMAIL_TOLIST = "config.report.email.tolist";
	private static final String KEY_REPORT_EMAIL_FROM_ADDRESS = "config.report.email.from.address";
	private static final String KEY_REPORT_EMAIL_FROM_NAME = "config.report.email.from.name";
	private static final String KEY_REPORT_EMAIL_SUBJECT = "config.report.email.subject";
	private static final String KEY_DOWNLOAD_CSV_FILENAME = "config.download.csv.filename";
	private static final String AWS_ACCESS_KEY = "aws.accesskey";
	private static final String AWS_SECRET_KEY = "aws.secretkey";
	private static final String AWS_REGION="aws.region";
	private static final String AWS_REPORT_BUCKET_NAME="aws.report.bucket.name";
	private static final String AWS_REPORT_FOLDER_NAME = "aws.report.folder.name";
	private static final String AWS_SCREENSHOT_FOLDER_NAME = "aws.screenshot.folder.name";
	private static final String TEST_SUIT_FILE_NAME = "test.suit.file.name";
	private static final String MAIL_REPORT_TO = "mail.report.to";
	private static final String MAIL_REPORT_FROM = "mail.report.from";
	
	
	//Database
	private static final String KEY_DB = "config.db";
	private static final String KEY_DB_HOST_SUFFIX = "db.host";
	private static final String KEY_DB_PORT_SUFFIX = "db.port";
	private static final String KEY_DB_NAME_SUFFIX = "db.name";
	private static final String KEY_DB_USER_SUFFIX = "db.username";
	private static final String KEY_DB_DRIVER_SUFFIX = "db.driver";
	private static final String KEY_DB_PASSWORD_SUFFIX = "db.password";
	
	
	static {
		try {
			initialize();
		} catch (Throwable t) {
			throw new IllegalStateException("Can't initialize Config object", t);
		}
	}
	
	private static File getPropertiesFile(String dir, String filename) {
		File configFileDir = new File(dir);
		if (!configFileDir.exists()) {
			throw new IllegalStateException("Cannot find test properties file; parent dir " + dir + " missing");
		}

		File propertiesFile = new File(configFileDir, filename);
		if (!propertiesFile.exists()) {
			throw new IllegalStateException("Cannot find test properties file; file does not exist: " + propertiesFile.getAbsolutePath());
		}

		return propertiesFile;
	}
	
	private static String getResourcePath() {
		Path resourceDirectory = Paths.get("src","test","resources");
		return resourceDirectory.toString();
	}

	private static void initialize() throws ConfigurationException {

		if (config != null) {
			return;
		}

		FileUtils.createTestConfigOverridePropertyFile();
		File propertiesFile = getPropertiesFile(TEST_RESOURCE_DIRECTORY, TEST_CONFIG_PROPERTIES_FILENAME);
		File overrideFile = getPropertiesFile(TEST_RESOURCE_DIRECTORY, TEST_COFFIG_PROPERTIES_OVERRIDE_FILENAME);

		config = loadConfigFiles(overrideFile, propertiesFile);	       

	}
	
	private static Configuration loadConfigFiles(File... files) throws ConfigurationException {

		CompositeConfiguration config = new CompositeConfiguration();

		for (File file : files) {
			PropertiesConfiguration propConfig = new PropertiesConfiguration();
			FileHandler propConfigFileHandler = new FileHandler(propConfig);
			propConfigFileHandler.load(file);

			config.addConfiguration(propConfig);
		}

		return config;
	}
	
	private static String get(String propertyName, boolean required) {

		String value = null;
		//Linux property name support
		String key = propertyName.replace(".","_");

		if(System.getenv(propertyName) != null){
			return System.getenv(propertyName);
		}else if(System.getenv(key) != null){ //Supported for Linux Env variable
			return System.getenv(key); }

		try {

			value = config.getString(propertyName);

			if (required && (value == null)) {
				throw new IllegalArgumentException("No such property found: " + propertyName);
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}

		return value;
	}


		
	public static final String getDownloadFolderPath() {
		switch (getRunMode()) {
		case "local":
			return THRIVE_DIR + getFolderRelativePath(KEY_DOWNLOAD_FOLDER);
		default:
			return getGridDownloadFolder();
		}
	}

	public static String get(String propertyName) {
		return get(propertyName, true);
	}

	private static String getFolderRelativePath(String propertyName) {
		return get(propertyName, true).replace("/", File.separator);
	}


	public static final String getSuperuserUser() {
		return getEnvDependentProperty(KEY_ENV_SUPERUSER_USER_SUFFIX);
	}

	public static final String getSuperuserPassword() {
		return getEnvDependentProperty(KEY_ENV_SUPERUSER_PASSWORD_SUFFIX);
	}
	
	public static final String getAccountManagerUser() {
		return getEnvDependentProperty(KEY_ENV_AM_USER_SUFFIX);
	}

	public static final String getAccountManagerPassword() {
		return getEnvDependentProperty(KEY_ENV_AM_PASSWORD_SUFFIX);
	}
	
	public static final String getAccountManagerUserUpdate() {
		return getEnvDependentProperty(KEY_ENV_AM_USER_UPDATE_SUFFIX);
	}
	
	public static final String getAccountManagerPasswordUpdate() {
		return getEnvDependentProperty(KEY_ENV_AM_PASSWORD_UPDATE_SUFFIX);
	}
	
	public static final String getAccountManagerNameUpdate() {
		return getEnvDependentProperty(KEY_ENV_AM_NAME_UPDATE_SUFFIX);
	}
	
	public static final String getAccountManagerMutableUser() {
		return getEnvDependentProperty(KEY_ENV_AM_MUTABLE_USER_SUFFIX);
	}

	public static final String getAccountManagerMutablePassword() {
		return getEnvDependentProperty(KEY_ENV_AM_MUTABLE_PASSWORD_SUFFIX);
	}
	
	public static final String getAccountManagerMutableName() {
		return getEnvDependentProperty(KEY_ENV_AM_MUTABLE_NAME_SUFFIX);
	}
	
	public static final String getEAUserName() {
		return getEnvDependentProperty(KEY_ENV_EA_USER_SUFFIX);
	}

	public static final String getEAUserPassword() {
		return getEnvDependentProperty(KEY_ENV_EA_PASSWORD_SUFFIX);
	}
	
	public static final String getEAName() {
		return getEnvDependentProperty(KEY_ENV_EA_NAME_SUFFIX);
	}
	
	public static final String getEAMutableUserEmail() {
		return getEnvDependentProperty(KEY_ENV_EA_MUTABLE_EMAIL);
	}

	public static final String getEAMutableUserPassword() {
		return getEnvDependentProperty(KEY_ENV_EA_MUTABLE_PASSWORD);
	}
	
	public static final String getEAMutableName() {
		return getEnvDependentProperty(KEY_ENV_EA_MUTABLE_NAME);
	}
	
	public static final String getEA2MutableUserName() {
		return getEnvDependentProperty(KEY_ENV_EA_MUTABLE_USER_SUFFIX);
	}
	
	public static final String getEA2MutableUserPassword() {
		return getEnvDependentProperty(KEY_ENV_EA_MUTABLE_PASSWORD_SUFFIX);
	}
	
	public static final String getEAUserUpdate() {
		return getEnvDependentProperty(KEY_ENV_EA_USER_UPDATE_SUFFIX);
	}
	
	public static final String getEAPasswordUpdate() {
		return getEnvDependentProperty(KEY_ENV_EA_PASSWORD_UPDATE_SUFFIX);
	}
	
	public static final String getEANameUpdate() {
		return getEnvDependentProperty(KEY_ENV_EA_NAME_UPDATE_SUFFIX);
	}
	
	public static final String getClientUserName() {
		return getEnvDependentProperty(KEY_ENV_CLIENT_USER_SUFFIX);
	}

	public static final String getClientUserPassword() {
		return getEnvDependentProperty(KEY_ENV_CLIENT_PASSWORD_SUFFIX);
	}
	
	public static final String getClientName() {
		return getEnvDependentProperty(KEY_ENV_CLIENT_NAME_SUFFIX);
	}
	
	public static final String getClientUserUpdate() {
		return getEnvDependentProperty(KEY_ENV_CLIENT_USER_UPDATE_SUFFIX);
	}
	
	public static final String getClientPasswordUpdate() {
		return getEnvDependentProperty(KEY_ENV_CLIENT_PASSWORD_UPDATE_SUFFIX);
	}
	
	public static final String getClientNameUpdate() {
		return getEnvDependentProperty(KEY_ENV_CLIENT_NAME_UPDATE_SUFFIX);
	}
	
	public static final String getInternalCoachUserName() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_USER_SUFFIX);
	}

	public static final String getInternalCoachPassword() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_PASSWORD_SUFFIX);
	}
	
	public static final String getGlobalCoachUserName() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_USER_SUFFIX);
	}

	public static final String getGlobalCoachPassword() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_PASSWORD_SUFFIX);
	}
	
	public static final String getGlobalCoachUserUpdate() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_USER_UPDATE_SUFFIX);
	}
	
	public static final String getGlobalCoachPasswordUpdate() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_PASSWORD_UPDATE_SUFFIX);
	}
	
	public static final String getGlobalCoachNameUpdate() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_NAME_UPDATE_SUFFIX);
	}
	
	public static final String getInternalCoachUserUpdate() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_USER_UPDATE_SUFFIX);
	}
	
	public static final String getInternalCoachPasswordUpdate() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_PASSWORD_UPDATE_SUFFIX);
	}
	
	public static final String getInternalCoachNameUpdate() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_NAME_UPDATE_SUFFIX);
	}

	public static final String getGenericUserPassword() {
		return get(KEY_ENV_GENERICUSERPASSWORD);
	}

	public static final String getAppName() {
		return get(KEY_APP_NAME);
	}

	public static final String getTaskFailDirectory() {
		return TEST_RESOURCE_DIRECTORY + getFolderRelativePath(KEY_TASK_FAIL_DIRECTORY);
	}

	public static final String getTaskLogDirectory() {
		return TEST_RESOURCE_DIRECTORY + getFolderRelativePath(KEY_TASK_LOG_DIRECTORY);
	}

	public static final String getTaskRedoMigration() {
		return get(KEY_TASK_REDO_MIGRATION);
	}

	public static final String getScreenshotsFolderPath() {
		return THRIVE_DIR + getFolderRelativePath(KEY_FOLDER_THRIVE_TEST_SCREENSHOTS_RELATIVEPATH);
	}

	private static boolean getBoolean(String propertyName) {
		return Boolean.parseBoolean(get(propertyName));
	}

	public static final String getPrimaryBrowser() {
		return get(KEY_BROWSER_PRIMARY);
	}

	public static final String getSecondaryBrowser() {
		return get(KEY_BROWSER_SECONDARY);
	}

	public static final String getDB() {
		return get(KEY_DB);
	}

	private static final String getDBLowerCase() {
		return getDB().toLowerCase();
	}

	public static final String getDBHost() {
		return getEnvDependentProperty(KEY_DB_HOST_SUFFIX);
	}

	public static final String getDBPassword() {
		return getEnvDependentProperty(KEY_DB_PASSWORD_SUFFIX);
	}

	public static final int getDBPort() {
		return Integer.parseInt(getEnvDependentProperty(KEY_DB_PORT_SUFFIX));
	}

	public static final String getDBName() {
		return getEnvDependentProperty(KEY_DB_NAME_SUFFIX);
	}

	public static final String getDBUsername() {
		return getEnvDependentProperty(KEY_DB_USER_SUFFIX);
	}
	
	public static final String getDBDriver() {
		return getEnvDependentProperty(KEY_DB_DRIVER_SUFFIX);
	}
	
	public static final String getEnv() {
		return get(KEY_ENV);
	}
	
	public static final String getTestPlatform() {
		return get(KEY_TEST_PLATFORM);
	}

	private static final String getEnvLowerCase() {
		return getEnv().toLowerCase();
	}

	public static final String getGridURL() {
		return get(KEY_GRIDURL);
	}
	
	public static final int getRetryCount() {
		return Integer.parseInt(get(KEY_RETRY_COUNT));
	}
	
	public static final boolean isMfaEnforced() {
		return Boolean.parseBoolean(get(KEY_MFA_ENFORCE));
	}
	
	public static final boolean featureFlagStatus() {
		return Boolean.parseBoolean(get(KEY_FEATUREFLAG_STATUS));
	}
	

	private static int getInt(String propertyName) {
		return Integer.parseInt(get(propertyName));
	}

	public static final String getLoginPageURL() {
		return getEnvDependentProperty(KEY_ENV_LOGINURL);
	}
	
	public static final String getLogoutPageURL() {
		return getEnvDependentProperty(KEY_ENV_LOGOUTPAGEURL_SUFFIX);
	}

	private static long getLong(String propertyName) {
		return Long.parseLong(get(propertyName));
	}

	public static final String getReportEmailAuthenticatorAddress() {
		return get(KEY_REPORT_EMAIL_AUTHENTICATOR_ADDRESS);
	}

	public static final String getReportEmailAuthenticatorPassword() {
		return get(KEY_REPORT_EMAIL_AUTHENTICATOR_PASSWORD);
	}

	public static final String getReportEmailFromAddress() {
		return get(KEY_REPORT_EMAIL_FROM_ADDRESS);
	}

	public static final String getReportEmailFromName() {
		return get(KEY_REPORT_EMAIL_FROM_NAME);
	}

	public static final String getReportEmailHost() {
		return get(KEY_REPORT_EMAIL_HOST);
	}

	public static final int getReportEmailPort() {
		return getInt(KEY_REPORT_EMAIL_PORT);
	}

	public static final String getReportEmailSubject() {
		return get(KEY_REPORT_EMAIL_SUBJECT);
	}

	public static final String getReportEmailToList() {
		return get(KEY_REPORT_EMAIL_TOLIST);
	}

	public static final String getReportOutputFolder() {
		return get(KEY_REPORT_OUTPUT_FOLDER);
	}

	public static final String getGridDownloadFolder() {
		return get(KEY_GRID_DOWNLOAD_FOLDER);
	}

	public static final String getReportUser() {
		return get(KEY_REPORT_USER);
	}

	public static final String getRunMode() {
		return get(KEY_RUNMODE);
	}
	
	public static boolean isRunModeCicd() {
		return getBoolean(KEY_RUNMODE_CICD);
	}

	public static final String getChromeDriverSystemProperty() {
		return get(KEY_WEBDRIVER_CHROME_PROPERTY);
	}

	public static final String getFirefoxDriverSystemProperty() {
		return get(KEY_WEBDRIVER_FIREFOX_PROPERTY);
	}

	public static final String getIeDriverSystemProperty() {
		return get(KEY_WEBDRIVER_IE_PROPERTY);
	}

	public static final String getEdgeDriverSystemProperty() {
		return get(KEY_WEBDRIVER_EDGE_PROPERTY);
	}

	public static final long getWaitSecsLong() {
		return getLong(KEY_PARAMS_WAITSECS_LONG);
	}

	public static final long getWaitSecsMaxTimeout() {
		return getLong(KEY_PARAMS_WAITSECS_MAXTIMEOUT);
	}

	public static final long getWaitSecsMaxTimeoutForVisitList() {
		return getLong(KEY_PARAMS_WAITSECS_MAXTIMEOUTFORVISITLIST);
	}

	public static final long getWaitSecsMedium() {
		return getLong(KEY_PARAMS_WAITSECS_MEDIUM);
	}

	public static final long getWaitSecsShort() {
		return getLong(KEY_PARAMS_WAITSECS_SHORT);
	}

	public static final boolean isChromeHeadless() {
		return getBoolean(KEY_BROWSER_CHROME_HEADLESS);
	}

	public static final boolean isReportEmailAuthenticatorSSL() {
		return getBoolean(KEY_REPORT_EMAIL_AUTHENTICATOR_SSL);
	}

	public static final boolean isReportEmailStartEnabled() {
		return getBoolean(KEY_REPORT_EMAIL_START_ENABLED);
	}

	public static final boolean isReportEmailSuccessEnabled() {
		return getBoolean(KEY_REPORT_EMAIL_SUCCESS_ENABLED);
	}

	public static final boolean isReportEmailFailureEnabled() {
		return getBoolean(KEY_REPORT_EMAIL_FAILURE_ENABLED);
	}

	private static final String getEnvDependentProperty(String suffix) {
		return getDependentProperty(KEY_ENV, getEnvLowerCase(), suffix);
	}

	private static final String getDBDependentProperty(String suffix) {
		return getDependentProperty(KEY_DB, getDBLowerCase(), suffix);
	}

	private static final String getDependentProperty(String prefix, String infix, String suffix) {
		return get(prefix + "." + infix + "." + suffix);
	}

	public static final String getDownloadCSVFilename() {
		return get(KEY_DOWNLOAD_CSV_FILENAME);
	}

	public static final String getTestConfigPropertiesDir() {
		return TEST_RESOURCE_DIRECTORY;
	}

	public static final String getTestConfigOverridePropertiesFilePath() {
		return getTestConfigPropertiesDir() + File.separator + TEST_COFFIG_PROPERTIES_OVERRIDE_FILENAME;
	}

	public static final String getTestResourceDir() {
		return TEST_RESOURCE_DIRECTORY;
	}

	public static final String getExtentReportOutputFolder() {
		return THRIVE_DIR + getFolderRelativePath(KEY_FOLDER_THRIVE_EXTENT_REPORTS_RELATIVEPATH);
	}

	public static final String getExtentReportName() {
		return get(KEY_EXTENT_REPORT_NAME);
	}

	public static final String getEnvLogoutURL() {
		return get(KEY_ENV_THRIVE_LOGOUT_URL_PREFIX).concat(get(LOGIN_URL_ENV).concat(get(KEY_ENV_THRIVE_LOGOUT_URL_SUFFIX)));
	}
	
	public static final String getMaildropURL() {
		return get(KEY_MAILDROP_URL);
	}
	
	public static final String getYahooMailURL() {
		return get(KEY_YAHOO_MAIL_URL);
	}
	
	public static final String getYahooMailUserName() {
		return get(KEY_YAHOO_MAIL_USERNAME);
	}
	
	public static final String getYahooMailPassword() {
		return get(KEY_YAHOO_MAIL_PASSWORD);
	}
	
	public static final String getMutableCategory() {
		return getEnvDependentProperty(KEY_ENV_MUTABLE_CATEGORY);
	}
	
	public static final String getMutableTopic() {
		return getEnvDependentProperty(KEY_ENV_MUTABLE_TOPIC);
	}
	
	public static final String getMutableExpertise() {
		return getEnvDependentProperty(KEY_ENV_MUTABLE_EXPERTISE);
	}
	
	public static final String getCategoryImmutable() {
		return getEnvDependentProperty(KEY_ENV_CATEGORY);
	}
	
	public static final String getTopic() {
		return getEnvDependentProperty(KEY_ENV_TOPIC);
	}
	
	public static final String getExpertise() {
		return getEnvDependentProperty(KEY_ENV_EXPERTISE);
	}
	
	
	public static final String getEnterpriseNameImmutable() {
		return getEnvDependentProperty(KEY_ENV_ENTERPRISE_IMMUTABLE);
	}
	
	public static final String getInternalCategory() {
		return getEnvDependentProperty(KEY_ENV_INTERNAL_CATEGORY);
	}
	
	public static final String getInternalTopic() {
		return getEnvDependentProperty(KEY_ENV_INTERNAL_TOPIC);
	}
	
	public static final String getInternalExpertise() {
		return getEnvDependentProperty(KEY_ENV_INTERNAL_EXPERTISE);
	}
	
	public static final String getInternalMutableCategory() {
		return getEnvDependentProperty(KEY_ENV_INTERNAL_MUTABLE_CATEGORY);
	}
	
	public static final String getInternalMutableTopic() {
		return getEnvDependentProperty(KEY_ENV_INTERNAL_MUTABLE_TOPIC);
	}
	
	public static final String getInternalMutableExpertise() {
		return getEnvDependentProperty(KEY_ENV_INTERNAL_MUTABLE_EXPERTISE);
	}
	
	public static final String getEnterpriseArchiveTest() {
		return getEnvDependentProperty(KEY_ENV_ENTERPRISE_ARCHIVE_TEST);
	}
	
	public static final String getInternalCoachName() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_COACH_NAME);
	}

	public static final String getGlobalCoachName() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_COACH_NAME);
	}
	
	public static final String getInternalMutableCoachUserName() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_MUTABLE_USER_SUFFIX);
	}

	public static final String getInternalMutableCoachPassword() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_MUTABLE_PASSWORD_SUFFIX);
	}
	
	public static final String getInternalMutableCoachName() {
		return getEnvDependentProperty(KEY_ENV_COACH_INTERNAL_MUTABLE_COACH_NAME);
	}
	
	public static final String getGlobalMutableCoachUserName() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_MUTABLE_USER_SUFFIX);
	}

	public static final String getGlobalMutableCoachPassword() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_MUTABLE_PASSWORD_SUFFIX);
	}
	
	public static final String getGlobalMutableCoachName() {
		return getEnvDependentProperty(KEY_ENV_COACH_GLOBAL_MUTABLE_COACH_NAME);
	}
	
	public static final String getMutableEnterprise() {
		return getEnvDependentProperty(KEY_ENV_ENTERPRISE_MUTABLE);
	}
	
	public static final String getAWSAccessKey() {
		return get(AWS_ACCESS_KEY);
	}
	
	public static final String getAWSSecretKey() {
		return get(AWS_SECRET_KEY);
	}
	
	public static final String getAWSRegion() {
		return get(AWS_REGION);
	}
	
	public static final String getAWSReportBucketName() {
		return get(AWS_REPORT_BUCKET_NAME);
	}
	
	public static final String getAWSReportFolderName() {
		return get(AWS_REPORT_FOLDER_NAME);
	}
	
	public static final String getAWSScreenshotFolderName() {
		return get(AWS_SCREENSHOT_FOLDER_NAME);
	}
	
	public static final String[] getTestNGSuitFilePath() {
		return Arrays.stream(get(TEST_SUIT_FILE_NAME).split(",")).map(suit -> getTestConfigPropertiesDir() + "/"+ suit).toArray(String[]::new);
		
	}
	
	public static final String getMailReportTo() {
		return get(MAIL_REPORT_TO);
	}
	
	public static final String getMailReportFrom() {
		return get(MAIL_REPORT_FROM);
	}
	
	public static String getLocalizationLanguage() {
		return get(KEY_LOCALIZATION_LANGUAGE);
	}
	
	public static boolean isTestRunLayerUi() {
		return Boolean.parseBoolean(get(KEY_RUN_UI_TEST));
	}
	
	public static boolean isTestRunLayerApi() {
		return Boolean.parseBoolean(get(KEY_RUN_API_TEST));
	}

}

