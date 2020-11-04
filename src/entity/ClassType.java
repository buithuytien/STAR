package entity;


import enums.*;
import utils.*;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.StringTokenizer;

//import cache.Cache;
import util.DateTimeHelper;
import util.TextDB;

public class ClassType {
	private InstructionType type; // either lecture, tutorial, or lab.
	private DayOfWeek day;
	private LocalTime startTime;
	private LocalTime endTime;
	private String venue;
	
	
	public ClassType(InstructionType type, DayOfWeek day, LocalTime starTime, LocalTime endTime, String venue) {
		// check if start time > end time:
		super();
		this.type = type;
		this.setStartTime(starTime);
		this.setEndTime(endTime);
		this.day = day;
		this.venue = venue;
	}
	
	public ClassType(String raw) {
		// TODO: check date and time format.
		// LEC MONDAY 10:00 13:00 LT12
		StringTokenizer star = new StringTokenizer(raw, TextDB.SEPERATOR);
		
		String typeStr = star.nextToken().trim();
		String dayStr = star.nextToken().trim();
		String startTimeStr = star.nextToken().trim();
		String endTimeStr = star.nextToken().trim();
		this.venue = star.nextToken().trim();		
		
		this.type = InstructionType.valueOf(typeStr);
		this.day = DayOfWeek.valueOf(dayStr);
		this.setStartTime(DateTimeHelper.convertStringToTime(startTimeStr));	
		this.setEndTime(DateTimeHelper.convertStringToTime(endTimeStr));	
		
	}
	
	public InstructionType getType() {
		return type;
	}
	public void setType(InstructionType type) {
		this.type = type;
	}

	public DayOfWeek getDay() {
		return this.day;
	}
	public void setDate(DayOfWeek day) {
		this.day = day;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public boolean checkClash(ClassType c) {
		// TODO: check if 2 classes clash by comparing time and day in the week.
		
		return true;
	}

	
	
	
	
}
