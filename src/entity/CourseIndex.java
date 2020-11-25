package entity;

import java.util.ArrayList;
import enums.*;

/**
 * Represents an index number of a course
 * An index may contain multiple class sessions of different instruction types (Eg. lecture, tutorial, laboratory)
 * An index may have multiple students registerred to, and multiple students in wait list if vacancy equals 0
 * @author BUITT
 *
 */
public class CourseIndex {
	/**
	 * the unique index number of this index
	 */
	private String index;
	
	/**
	 * the unique course code of the course to which this index belongs
	 */
	private String courseCode;
	
	/**
	 * the number of AUs of the course to which this index belong
	 */
	private int indexAU;
	
	/**
	 * the group number of this index
	 */
	private int grpNum;
	
	/**
	 * the type of the course to which this index belong. Eg. CORE, UE, PE
	 */
	private String courseType;
	
	/**
	 * the GER PE type of the course to which this index belong. Eg. STS, ...
	 */
	private String GERType;
	
	/**
	 * number of vacancy in this index
	 */
	private int vacancy;
	
	/**
	 * list of students' IDs successfully registered to this index
	 */
	private ArrayList<String> registerIDs = new ArrayList<String>();
	
	/**
	 * list of students' IDs in wait list of this index
	 */
	private ArrayList<String> waitIDs = new ArrayList<String>();
	
	/**
	 * the information of each class session of this index
	 * one index/ course may have multiple class sessions of different types of instruction (lecture, tutorial, laboratory)
	 */
	private ArrayList<ClassType> classList = new ArrayList<ClassType>(); // lab, tut, lecture
	
	/**
	 * Constructor
	 */
	public CourseIndex() {}
	
	/**
	 * Constructor
	 */
	public CourseIndex(String index) {
		this.index = index;
	}
	
	/*
	 * getter and setter
	 */
	
	/**
	 * accessor method to get group number
	 * @return the integer value of group number of this index
	 */
	public int getGrpNum() {return grpNum;}
	
	/**
	 * mutator method to edit group number of this index
	 * @param grpNum
	 */
	public void setGrpNum(int grpNum) {this.grpNum = grpNum;}
	
	/**
	 * get GER PE type of the course to which this index belong
	 * @return GER PE type as a String
	 */
	public String getGERType() {return GERType;}
	
	/**
	 * set GER PE type of the course to which this index belong
	 * @param GERType
	 */
	public void setGERType(String GERType) {this.GERType = GERType;}
	
	/**
	 * get number of AUs of the course to which this index belong
	 * @return numner of AUs as int
	 */
	public int getIndexAU() {return this.indexAU;}
	
	/**
	 * set number of AUs of the course to which this index belong
	 * @param indexAU
	 */
	public void setIndexAU(int indexAU) {this.indexAU = indexAU;}
	
	/**
	 * get the type of the of the course to which this index belong, eg. CORE, UE, PE, ...
	 * @return type of the of the course as String
	 */
	public String getCourseType() {return courseType;}
	
	/**
	 * set type of the course to which this index belong
	 * @param courseType
	 */
	public void setCourseType(String courseType) {this.courseType = courseType;}
	
	/**
	 * get information of the class sessions belonging to this index, eg. time, venue, mode of instruction
	 * may contain multiple class sessions
	 * @return
	 */
	public ArrayList<ClassType> getClassList() {return classList;}
	
	/**
	 * edit information of the class sessions belonging to this index
	 * @param classList
	 */
	public void setClassList(ArrayList<ClassType> classList) {this.classList = classList;}
	
	/**
	 * get the list of IDs of the students registerred to this index
	 * @return list of registered students' IDs
	 */
	public ArrayList<String> getRegisteredIDs() {return registerIDs;}
	
	/**
	 * set list of students' IDs registered to this index
	 * @param RegisterIDs
	 */
	public void setRegisterIDs(ArrayList<String> RegisterIDs) {this.registerIDs = RegisterIDs;}
	
	/**
	 * get list of IDs of the students placed in wait list of this index
	 * @return list of students' IDs in wait list
	 */
	public ArrayList<String> getWaitIDs() {return waitIDs;}
	
	/**
	 * set list of IDs of the students placed in wait list of this index
	 * @param waitIDs
	 */
	public void setWaitIDs(ArrayList<String> waitIDs) {this.waitIDs = waitIDs;}
	
	/**
	 * get the unique index number of this index
	 * @return index number of this index as a String
	 */
	public String getIndex() {return index;}
	
	/**
	 * set the index number for this index
	 * @param index
	 */
	public void setIndex(String index) {this.index = index;}
	
	/**
	 * get the unique course code of the code to which this index belongs
	 * @return course code of this index as a string
	 */
	public String getCourseCode() {return courseCode;}
	
	/**
	 * set the course where this index belongs to
	 * be careful as it will affect the students registered to this index.
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode) {this.courseCode = courseCode;}
	
	/**
	 * get the number of vacancy in this index
	 * @return the number of vacancy of this index as a int
	 */
	public int getVacancy() {return vacancy;}
	
	/**
	 * set the vacancy in this index.
	 * vacancy must be greater than 0
	 * @param vacancy
	 */
	public void setVacancy(int vacancy) { 
		if(vacancy  >= 0) {
			this.vacancy = vacancy;
		}		
	}

	/**
	 * join the information of the class sessions to a String, separated by ';' sign
	 * used when saving course index information to database
	 * @return a String containing information of all class sessions
	 */
	public String toStringClassList() {
		// eg. "LEC MONDAY 10:00 13:00 LT12;TUT MONDAY 9:00 10:00 TR101;LAB TUESDAY 9:00 11:00 SCELAB1"
		String temp = "";
		if(this.classList == null) {
			return "";
		}
		int n = this.classList.size();
		for(int i = 0; i < n; i++) {
			temp += this.classList.get(i).toString();
			if(i < n-1 ) {
				temp += ";";
			}
		}
		return temp;
	}
	
	/**
	 * join all the IDs of students registered to this index into a String, separated by ';'
	 * used when saving course index information to database
	 * @return a String of all IDs of registered students
	 */
	public String toStringRegisterIDs() {
		// eg. U123456B;U123456D;U123456H
		String temp = null;
		if(this.registerIDs == null || this.registerIDs.isEmpty()) {
			return "";
		}
		int n = this.registerIDs.size();
		for(int i = 0; i < n; i++) {
			temp += this.registerIDs.get(i);
			if(i < n-1 ) {
				temp += ";";
			}
		}
		return temp;
	}
	
	/**
	 * join all the IDs of students placed in wait list of this index into a String, separated by ';'
	 * used when saving course index information to database
	 * @return a String of all IDs of waitlisted students
	 */
	public String toStringWaitIDs() {
		// eg. U123456B;U123456D;U123456H
		String temp = null;
		if(this.waitIDs == null || this.waitIDs.isEmpty()) {
			return "";
		}
		int n = this.waitIDs.size();
		for(int i = 0; i < n; i++) {
			temp += this.waitIDs.get(i);
			if(i < n-1 ) {
				temp += ";";
			}
		}
		return temp;
	}
	
	/**
	 * join all the attributes of this index into an array of String
	 * used when saving course index information to database
	 * @return join all the attributes of this index as an array of String
	 */
	public String[] toStringDB() {
		// eg. LEC MONDAY 10:00 13:00 LT12; TUT MONDAY 9:00 10:00 TR101; LAB TUESDAY 9:00 11:00 SCELAB1; 
		String[] temp = {this.index, this.courseCode, String.valueOf(this.vacancy), 
						 this.toStringRegisterIDs(), this.toStringWaitIDs(),
						 String.valueOf(this.grpNum), this.courseType, this.GERType, 
						 String.valueOf(this.indexAU), this.toStringClassList() }; //ADJUSTED BY JY REGARDING COUSRETYPE
		return temp;
	}

}
