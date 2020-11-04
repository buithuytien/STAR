package entity;
import java.util.StringTokenizer;

import enums.*;
import utils.DateTimeHelper;
import utils.TextDB;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String raw = "LEC MONDAY 10:00 09:00 LT12"; // must be in this format
		
		StringTokenizer star = new StringTokenizer(raw);
		
		String typeStr = star.nextToken().trim();
		String dayStr = star.nextToken().trim();
		String startTimeStr = star.nextToken().trim();
		String endTimeStr = star.nextToken().trim();
		String venue = star.nextToken().trim();	
		
		InstructionType type = InstructionType.valueOf(typeStr);
		DayOfWeek day = DayOfWeek.valueOf(dayStr);
		LocalTime startTime = DateTimeHelper.convertStringToTime(startTimeStr);	
		LocalTime endTime = DateTimeHelper.convertStringToTime(endTimeStr);	
		
		System.out.println(type.getClass());
		System.out.println(day.getClass());
		System.out.println(startTime.getClass());
		System.out.println(endTime.getClass());
		System.out.println(venue.getClass());
		
		// compare 2 times/ 2 days of week
		int timeDif = endTime.compareTo(startTime);
		System.out.println(timeDif);
		
		DayOfWeek day2 = DayOfWeek.valueOf("MONDAY");
		System.out.println(day.equals(day2) );

	}

}
