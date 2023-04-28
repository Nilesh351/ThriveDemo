package com.thrive.common.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.thrive.ui.core.BaseTestPage;

public class ExtentReportUtils extends BaseTestPage{
	
    private static String fileName;

    private static ExtentReports extentReports = ExtentReportUtils.createInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
   
    
    public static void setExtentTest(ExtentTest value) {
    	extentTest.set(value);
    }
    
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }
	 
	    public static ExtentReports getInstance() {
	        if (extentReports == null)
	            createInstance();
	        return extentReports;
	    }
	    
	    public static String getReportFileName() {
	    	return fileName;
	    }
	    
	    private static void setReportFileName(String name) {
	    	fileName=name;
	    }
	 
	    //Create an extent report instance
	    public static ExtentReports createInstance() {
	    	
	        String fileName = FileUtils.getReportFileName();
	        setReportFileName(fileName);
	        ExtentReports extentReports = new ExtentReports();
	        ExtentSparkReporter sparkReport = new ExtentSparkReporter(fileName);
	        
	        sparkReport.config().setTheme(Theme.DARK);
	        sparkReport.config().setDocumentTitle(fileName);
	        sparkReport.config().setEncoding("utf-8");
	        sparkReport.config().setReportName(fileName);
	        sparkReport.config().setTimelineEnabled(true);
	 
	        extentReports.attachReporter(sparkReport);
	 
	        return extentReports;
	    }
	    
	}
	
