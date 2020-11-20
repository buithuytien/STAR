package util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//import crud.PublicHolidayCRUD;
//import entity.PublicHoliday;
//import enums.DayType;

/**
 * Class DateTimeHelper to get the date and time required for the calculation of prices
 *
 */
public class DateTimeHelper {
	/**
	 * method to declare strings in a particular format
	 */
	public static final String DATE_FORMAT="d-MM-yyyy";
	public static final String TIME_FORMAT="HH:mm";
	public static final String TRANSACTION_DATE_FORMAT="yyyyMMd";
	public static final String TRANSACTION_TIME_FORMAT="HHmm";
	
	/**
	 * declaring constant MINUTES_BEFORE to be 15
	 */
	public static final long MINUTES_BEFORE=15;
	
	/**
	 * method to convert the string of date into the DATE_FORMAT
	 * @param dateStr
	 * @return date in DATE_FORMAT
	 */
	public static LocalDate convertStringToDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return LocalDate.parse(dateStr, formatter);
	}
	
	/**
	 * method to convert the date into a string
	 * @param date
	 * @return string of date
	 */
	public static String convertDateToString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return date.format(formatter);
	}
	
	/**
	 * method to convert the string of the time to the TIME_FORMAT
	 * @param timeStr
	 * @return time in TIME_FORMAT
	 */
	public static LocalTime convertStringToTime(String timeStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
		return LocalTime.parse(timeStr, formatter);
	}
	
	/**
	 * method to convert the time into a string
	 * @param time
	 * @return string of the time
	 */
	public static String convertTimeToString(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
		return time.format(formatter);
	}
	
	/**
	 * method to check if date input is today's date
	 * return true if date input is today's date
	 * method overloading
	 * @param date
	 * @return True or False
	 */
	public static boolean isToday(LocalDate date) {
		return date.isEqual(LocalDate.now());
	}
	
	/**
	 * method to check if date input is today's date
	 * return true if date input is today's date
	 * method overloading
	 * @param dateStr
	 * @return True or False
	 */
	public static boolean isToday(String dateStr) {
		LocalDate date = convertStringToDate(dateStr);
		return isToday(date);
	}
	
	/**
	 * method to check if date input is today or after today
	 * returns true if the date input is today or after today
	 * @param date
	 * @return True or False
	 */
	public static boolean fromToday(LocalDate date) {
		return isToday(date) || date.isAfter(LocalDate.now());
	}
	
	/**
	 * method to check if the time input is more than 15mins from current time
	 * return true if the time input is more than 15mins from current time
	 * method overloading
	 * @param time
	 * @return True or False
	 */
	public static boolean checkAfterMinutesFromNow(LocalTime time) {
		Duration duration = Duration.between(LocalTime.now(), time);
		return (duration.toMinutes() > MINUTES_BEFORE);
	}
	
	/**
	 * method to check if the time input is more than 15mins from current time
	 * return true if the time input is more than 15mins from current time
	 * method overloading
	 * @param timeStr
	 * @return True or False
	 */
	public static boolean checkAfterMinutesFromNow(String timeStr) {
		LocalTime time = convertStringToTime(timeStr);
		return checkAfterMinutesFromNow(time);
	}
	
	/**
	 * method to calculate the minutes until 12am from the input time
	 * method overloading
	 * @param time
	 * @return integer value of how many minutes to midnight
	 */
	public static int getMinutesTillMidnight(LocalTime time) {
		return (int) (Duration.between(time, LocalTime.MIDNIGHT).toMinutes()+60*24);
	}
	
	/**
	 * method to calculate the minutes until 12am from the input time
	 * method overloading
	 * @param timeStr
	 * @return integer value of how many minutes to midnight
	 */
	public static int getMinutesTillMidnight(String timeStr) {
		LocalTime time = convertStringToTime(timeStr);
		return getMinutesTillMidnight(time);
	}
	
	/**
	 * method to get the type of day, WEEKEND, WEEKDAY or PUBLIC HOLIDAY
	 * depending on input date
	 * @param date
	 * @return class of category of the type of day, under enumerations of DayType class
	 
	public static DayType getDayType(LocalDate date) {
		DayType res;
		if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY)||date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
			res = DayType.WEEKEND;
		else 
			res = DayType.WEEKDAY;
		
		PublicHolidayCRUD<PublicHoliday> crud = new PublicHolidayCRUD<>(PublicHoliday.class);
		if (crud.isPublicHoliday(date))
			res = DayType.PUBLIC_HOLIDAY;
		
		return res;
	}
	*/
	
	/**
	 * accessor method to get current date
	 * @return date
	 */
	public static LocalDate getTodayDate() {
		return LocalDate.now();
	}
	
	/**
	 * accessor method to get current time
	 * @return time
	 */
	public static LocalTime getCurrentTime() {
		return LocalTime.now();
	}
	
	/**
	 * method to convert date to TRANSACTION_DATE_FORMAT
	 * @param date
	 * @return string of date
	 */
	public static String convertDateToTransactionString(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TRANSACTION_DATE_FORMAT);
		return date.format(formatter);
	}
	
	/**
	 * method to convert time to TRANSACTION_TIME_FORMAT
	 * @param time
	 * @return time in TRANSACTION_TIME_FORMAT
	 */
	public static String convertTimeToTransactionString(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TRANSACTION_TIME_FORMAT);
		return time.format(formatter);
	}
}
