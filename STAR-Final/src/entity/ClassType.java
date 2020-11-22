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
	
	/**
	 * Constructor
	 */
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
		String[] elem = rawtempt.split(" ");
		type = InstructionType.valueOf(elem[0]);
		this.day = DayOfWeek.valueOf(elem[1]);
		this.startTime = TimeHelper.convertStringToTime(elem[2]);
		this.endTime = TimeHelper.convertStringToTime(elem[3]);
		this.venue = elem[4];
	}
	
	/**
	 * Convert ClassType Object To String
	 * @return string of this class type eg. LEC MONDAY 10:00 13:00 LT12
	 */
	public String toString() {
		// concatenate all information, get separated by ";"
		String temp;
		temp = this.type.typeStr(type) + " " + this.day.name() + " " +
				TimeHelper.convertTimeToString(this.startTime) + " " + 
				TimeHelper.convertTimeToString(this.endTime) + " " + 
				this.venue ;
		return temp;
		
	}
	
	/**
	 * Get InstructionType Object (Eg. Lec/Tut/Lab)
	 * @return which type of session is this class type object
	 */
	public InstructionType getType() {
		return type;
	}
	/**
	 * Set InstructionType (Eg. Lec/Tut/Lab)
	 * @param type
	 */
	public void setType(InstructionType type) {
		this.type = type;
	}
	/**
	 * Get ClassType Day (Eg.Monday)
	 * @return the day that this index class type falls on
	 */
	public DayOfWeek getDay() {
		return this.day;
	}
	/**
	 * Set ClassType Day
	 * @param day
	 */
	public void setDay(DayOfWeek day) {
		this.day = day;
	}
	/**
	 * Get Venue Of This Class
	 * @return the venue of this class type
	 */
	public String getVenue() {
		return venue;
	}
	/** 
	 * Set Venue Of This Class
	 * @param venue
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	/**
	 * Get Start Time Of This Class
	 * @return the start time of this class type
	 */
	public LocalTime getStartTime() {
		return startTime;
	}
	/**
	 * Set Start Time Of This Class
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	/**
	 * Get End Time Of This Class
	 * @return the end time of this class type
	 */
	public LocalTime getEndTime() {
		return endTime;
	}
	/**
	 * Set End Time Of This Class
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}	
}
