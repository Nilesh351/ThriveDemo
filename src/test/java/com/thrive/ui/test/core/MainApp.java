package com.thrive.ui.test.core;

import java.util.Arrays;

import org.testng.TestNG;

import com.thrive.ui.core.Config;
public class MainApp {
	static TestNG testNG;
	
	public static void main(String[] args) {
		testNG = new TestNG();
		testNG.setThreadCount(1);
		testNG.setTestSuites(Arrays.asList(Config.getTestNGSuitFilePath()));
		testNG.run();
	}
	
	 
}
