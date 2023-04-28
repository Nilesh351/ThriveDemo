package com.thrive.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DateTimeUtils {
	
    private static final String FORMAT_MM_DD_YYYY = "MM/dd/yyyy";
    private static final String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    private static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    private static final String FORMAT_DD_MMMM_YYYY = "dd-MMMM-yyyy";
	private static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(FORMAT_DD_MM_YYYY);
	private static SimpleDateFormat DATE_YYYY_MM_DD = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
	private static SimpleDateFormat DATE_DD_MMMMM_YY = new SimpleDateFormat(FORMAT_DD_MMMM_YYYY);
    private static final Logger LOGGER = LogManager.getLogger(DateTimeUtils.class);
	private static final SimpleDateFormat DATE_FORMAT_MILI_SEC = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
	private static final SimpleDateFormat DATE_FORMAT_MNTS = new SimpleDateFormat("yyyy_MM_dd_HHmm");
	
	public static String getCurrentTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return DATE_FORMAT_MNTS.format(timestamp);
	}
	
	private enum Calender {
		DATE, MONTH, YEAR
	}
	
	private static String constructDate(SimpleDateFormat simpleDateFormat, int delta, Calender cal) {
		
		LOGGER.info("Constructing the date");
		
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date());
		String calEnum = cal.toString().toLowerCase();
		
		switch (calEnum) {
		case "date":
			calender.add(Calendar.DATE, delta);
			break;
		case "month":
			calender.add(Calendar.MONTH, delta);
			break;
		case "year":
			calender.add(Calendar.YEAR, delta);
			break;
		default:
			calender.add(Calendar.DATE, delta);
			break;
		}
				
		return simpleDateFormat.format(calender.getTime());
	}

	/**
	 * @return date
	 */
	public static String getTodaysDate() {
		return constructDate(DEFAULT_DATE_FORMAT, 0, Calender.DATE);
	}
	
	/**
	 * @return date
	 */
	public static String getTomorrowsDate() {
		return constructDate(DEFAULT_DATE_FORMAT, 1, Calender.DATE);
	}
	
	public static String getNextYearDate() {
		return constructDate(DEFAULT_DATE_FORMAT, 1, Calender.YEAR);
	}
	
	/**
	 * @return priorDate
	 */
	public static String getYesterdaysDate() {
		return constructDate(DEFAULT_DATE_FORMAT, -1, Calender.DATE);
	}
	
	public static String getYesterdaysDateYyyyMmDd() {
		return constructDate(DATE_YYYY_MM_DD, -1, Calender.DATE);
	}
	
	public static String getMonthName(int monthNumber) {
		return Month.of(monthNumber).name();
	}
	
	
	
	public enum TimeFormat {
		
		TWELVE_HR("12"),
		TWENTY_FOUR_HR("24");
		
		private String value;

		private TimeFormat(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public static String getParsedDate(String requiredDateFormat, String givenDateFormate, String givenDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(givenDateFormate);
	    Date date = formatter.parse(givenDate);
        String parsedDate = new SimpleDateFormat(requiredDateFormat).format(date);
        return parsedDate;
	}
}
