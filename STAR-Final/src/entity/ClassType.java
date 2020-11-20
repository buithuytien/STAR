package entity;


import enums.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import util.*;
import java.time.DayOfWeek;
import java.util.StringTokenizer;


public class ClassType {
	private InstructionType type; // either lecture, tutorial, or lab.
	private DayOfWeek day;
	private LocalTime startTime;
	private LocalTime endTime;
	private String venue;
	
	public ClassType() {}
	
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
		// LEC MONDAY 10:00 13:00 LT12
		String rawtempt = raw;
		rawtempt.replace("[", "");
		rawtempt.replace("]", "");
		//System.out.println(rawtempt);
		String[] elem = rawtempt.split(" ");
		//for (int j =0; j<elem.length; j++) { System.out.println(elem[j]);}
		//type.setName(elem[0]);
		//this.type = type.typeIT(elem[0]);
		type = InstructionType.valueOf(elem[0]);
		this.day = DayOfWeek.valueOf(elem[1]);
		this.startTime = TimeHelper.convertStringToTime(elem[2]);
		this.endTime = TimeHelper.convertStringToTime(elem[3]);
		this.venue = elem[4];			
	}
	
	public String toString() {
		// concatenate all information, get separated by ";"
		// eg. LEC MONDAY 10:00 13:00 LT12
		String temp;
		temp = this.type.typeStr(type) + " " + this.day.name() + " " +
				TimeHelper.convertTimeToString(this.startTime) + " " + 
				TimeHelper.convertTimeToString(this.endTime) + " " + 
				this.venue ;
		return temp;
		
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
	public void setDay(DayOfWeek day) {
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
	
}
