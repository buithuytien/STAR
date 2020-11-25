package util;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import enums.InstructionType;

/**
 * to get the date and time of student access period and class sessions
 * @author BUITT
 *
 */
public class TimeHelper {
	/**
	 * declare strings in a particular format
	 */
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");	
	public static final String TIME_FORMAT="HH:mm"; 
	
	/**
	 * method to convert the string into Date object used in student access period
	 * @param strDateTime
	 * @return Date object of student access period, either start time or end time
	 * @throws ParseException
	 */
	public static Date GetStudentDateTime(String strDateTime) throws ParseException {
		// use to check student registration date time
		Date temp = null;
		temp = dateFormat.parse(strDateTime);
		return temp;
	}
	
	/**
	 * method to convert the String of time in a day into LocalTime object used in class sessions' schedule
	 * @param timeStr
	 * @return
	 * @throws DateTimeParseException
	 */
	public static LocalTime convertStringToTime (String timeStr) throws DateTimeParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
		LocalTime t = null;
		t = LocalTime.parse(timeStr, formatter);		
		return t;
	}
	
	/**
	 * Method To Convert The Time Object Into A String
	 * @param time
	 * @return string of the time
	 */
	public static String convertTimeToString(LocalTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return time.format(formatter);		
	}
}
