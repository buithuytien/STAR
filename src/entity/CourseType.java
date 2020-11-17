package entity;

import enums.*;

public class CourseType {
	InstructionCourseType type;
	public String courseCode;
	public String courseType;
	public String gerPEType;
	
	/*
	 * Constructor
	 */
	public CourseType() {}
	
	
	/*
	 * Getter and setter
	 */
	public InstructionCourseType getType() {return type;}
	public void setType(InstructionCourseType type) {this.type = type;}
	public String getCourseCode() {return courseCode;}
	public void setCourseCode(String courseCode) {this.courseCode = courseCode;}
	public String getCourseType() {return courseType;}
	public void setCourseType(String courseType) {this.courseType = courseType;}
	public String getGERType() {return gerPEType;}
	public void setGERType(String gerType) {this.gerPEType = gerType;}
}
