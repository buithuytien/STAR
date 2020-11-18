package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import enums.InstructionType;

public class TimeHelper {
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	
	public static final String TIME_FORMAT="HH:mm"; 
	
	public static Date GetStudentDateTime(String strDateTime) throws ParseException {
		// use to check student registration date time
		Date temp = null;
		temp = dateFormat.parse(strDateTime);
//		 try {
//			temp = dateFormat.parse(strDateTime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("ERROR: Incorrect date time format.");
//		}
		return temp;
	}
	
	public static LocalTime convertStringToTime (String timeStr) throws DateTimeParseException{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
		LocalTime t = null;
		t = LocalTime.parse(timeStr, formatter);		
//		try {
//			t = LocalTime.parse(timeStr, formatter);			
//		} catch (DateTimeParseException e) {
//			e.printStackTrace();
//			System.out.println("ERROR: Wrong time format");
//		}
		return t;
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
	
//	public static void main(String[] args) {
//		convertStringToTime("aa bb");
//	}
	
}
