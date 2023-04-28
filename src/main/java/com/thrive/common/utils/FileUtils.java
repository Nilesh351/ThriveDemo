package com.thrive.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.thrive.common.testdata.pojos.invite_details.CoachInviteDetails;
import com.thrive.common.testdata.pojos.invite_details.InviteClientDetails;
import com.thrive.common.testdata.utils.TestDataGenerator;
import com.thrive.ui.core.BaseTestPage;
import com.thrive.ui.core.Config;
import com.thrive.ui.page.common.UserManagementCommonPage;



public class FileUtils {

	private static boolean createScreenshotFolder = true;
	private static String screenshotFilePath = "";
	private static boolean createTestReportFolder = true;
	private static String testReportFilePath = "";
	private static final Logger LOGGER = LogManager.getLogger(FileUtils.class);
	private static String currentDateTime;
	
	public static String readFromFile(String filePath) {

		String fileContent = "";
		String line;

		try {

			BufferedReader input = new BufferedReader(new FileReader(filePath));
			StringBuffer buffer = new StringBuffer();
			while ((line = input.readLine()) != null)
				buffer.append(line + " ");
			fileContent = buffer.toString();
			input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileContent;
	}

	public static String getTestReportFilePath() {
		testReportFilePath = Config.getExtentReportOutputFolder();
		if (createTestReportFolder) {
			LOGGER.info(testReportFilePath);
			File f = new File(testReportFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			createTestReportFolder = false;
		}
		return testReportFilePath;
	}

	public static String getReportFileName() {

		String fileName = getTestReportFilePath() + Config.getExtentReportName() + "_"
				+ DateTimeUtils.getCurrentTimeStamp() + ".html";
		return fileName;
	}

	public static String getScreenshotFolderPath() {
		screenshotFilePath = Config.getScreenshotsFolderPath();
		if (createScreenshotFolder) {
			LOGGER.info(screenshotFilePath);
			File f = new File(screenshotFilePath);
			if (!f.exists()) {
				f.mkdirs();
			}
			createScreenshotFolder = false;
		}
		return screenshotFilePath;
	}

	public static String getScreenshotFilePath() {
		currentDateTime = DateTimeUtils.getCurrentTimeStamp();
		return getScreenshotFolderPath() + "%s_" + currentDateTime + ".png";
	}

	public static String getAwsScreenshotFilePath() {
		String filePath = "https://mythrive-test-automation.s3.eu-west-2.amazonaws.com/"
				+ Config.getAWSScreenshotFolderName() + "%s_" + currentDateTime + ".png";
		return filePath;
	}

	public static String getAwsScreenshotFileName() {
		String filePath = Config.getAWSScreenshotFolderName() + "%s_" + currentDateTime + ".png";
		return filePath;
	}

	public static boolean createTestConfigOverridePropertyFile() {
		try {
			if (new File(Config.getTestConfigOverridePropertiesFilePath()).createNewFile()) {
				LOGGER.info("TestConfigOverride File created successfully");
				return true;
			} else {
				LOGGER.info("TestConfigOverride File already exists");
				return false;
			}
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			return false;
		}
	}

	public static boolean setAllPermissionToFile(File file) {
		return file.setExecutable(true) && file.setReadable(true) && file.setWritable(true) ? true : false;
	}

	public static boolean isFileDownloaded(String dirPath, String name) {
		boolean flag = false;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			flag = false;
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(name)) {
				flag = true;
			}
		}
		return flag;
	}

	public static int getRecordCount(String filePath) throws IOException {

		int rowCount = 1;
		try {

			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
				String input;
				while ((input = bufferedReader.readLine()) != null) {
					rowCount++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rowCount;
	}

//	public static int getRecordCountOfCsvFile(String filePath) {
//		int lines = 0;
//		String value;
//		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//			while ((value=reader.readLine()) != null) {
//				lines++;
//			} 
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return lines;
//	}

	 public static int getRecordCountOfCsvFile(String filePath) {
	      LOGGER.info("Reading first column of CSV file");
	      String strFile = filePath;
	      int lines = 0;
	      CSVReader reader;
	      try {
	      reader = new CSVReader(new FileReader(strFile));
	      String [] nextLine;
	      while ((nextLine = reader.readNext()) != null) {
	    	  lines++;
	      }
	      } catch (Exception e) {
	    	  e.getStackTrace();
	      }
	      return lines;
	}
	 


	

	public static void writeDataAtOnceClient(String filePath) {
		LOGGER.info("Records added successfully in csv file");
		File file = new File(filePath);
		try {

			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile,',', 
                    CSVWriter.NO_QUOTE_CHARACTER, 
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                    CSVWriter.DEFAULT_LINE_END);
			        
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "Email", "First Name", "Last Name" });
			for (int i = 1; i <= 4; i++) {
				InviteClientDetails inviteClientDetails = TestDataGenerator.generateInviteClientDetails();
				data.add(new String[] { inviteClientDetails.getEmailAddress(), inviteClientDetails.getFirstName(), inviteClientDetails.getLastName() });
			}
			writer.writeAll(data);
			System.out.println(data+"\t");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeDataAtOnceCoach(String filePath) {
		LOGGER.info("Records added successfully in csv file");
		File file = new File(filePath);
		try {

			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile,',', 
                    CSVWriter.NO_QUOTE_CHARACTER, 
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                    CSVWriter.DEFAULT_LINE_END);
			        
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "Email", "First Name", "Last Name" });
			for (int i = 1; i <= 4; i++) {
				CoachInviteDetails globalCoachInviteDetails = TestDataGenerator.generateCoachInviteDetails(true);
				data.add(new String[] { globalCoachInviteDetails.getEmailAddress(), globalCoachInviteDetails.getFirstName(), globalCoachInviteDetails.getLastName() });
			}
			writer.writeAll(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeDataAtOnceInternalCoach(String filePath) {
		LOGGER.info("Records added successfully in csv file");
		File file = new File(filePath);
		try {

			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile,',', 
                    CSVWriter.NO_QUOTE_CHARACTER, 
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                    CSVWriter.DEFAULT_LINE_END);
			        
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "Email", "First Name", "Last Name" });
			for (int i = 1; i <= 4; i++) {
				CoachInviteDetails internalCoachInviteDetails = TestDataGenerator.generateInternalCoachInviteDetails(false);
				data.add(new String[] { internalCoachInviteDetails.getEmailAddress(), internalCoachInviteDetails.getFirstName(), internalCoachInviteDetails.getLastName() });
			}
			writer.writeAll(data);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	
	public static boolean delteDownloadedFile(String dirPath, String name) {
		boolean flag = false;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			flag = false;
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains(name)) {
				files[i].delete();
				flag = true;
			}
		}
		return flag;
	}

	public static String getFileName() {
		File folder = new File(BaseTestPage.getDownloadedFolderPath);
		File[] listOfFiles = folder.listFiles();
		String file = null;
		for (File files : listOfFiles) {
			if (files.toString().contains(".csv")) {

				if (files.isFile()) {
					file = files.getName();
					break;
				}
			}
		}
		return file;

	}

}
