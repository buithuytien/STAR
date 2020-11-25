package entity;
import java.util.ArrayList;
/**
 * Represents a course
 * A course can contain multiple index numbers
 * @author BUITT
 *
 */
public class Course {
	
	/**
	 * Unique code name of the course. Eg. AB1234
	 */
	private String courseCode;
	
	/**
	 * Hosting school of the course. Eg. NBS
	 */
	private String schoolCode;
	
	/**
	 * List of CourseIndex objects belonging to this course
	 */
	private ArrayList<CourseIndex> courseIndexList = new ArrayList<CourseIndex>() ;
	
	/*
	 * Constructors
	 */
	public Course() {}
	
	public Course(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public Course(ArrayList<CourseIndex> courseIndexList) {
		this.courseIndexList = courseIndexList;
	}
	
	
	/**
	 * accessor to get the code name of the course
	 * @return unique code name of the course
	 */
	public String getCourseCode() {return courseCode;}

	/**
	 * mutator to edit code name of the course
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode) {this.courseCode = courseCode;}

	/**
	 * accessor to get hosting school of the course
	 * @return name of the hosting school of the course
	 */
	public String getSchoolCode() {return schoolCode;}
	
	/**
	 * mutator to edit the hosting school of the course
	 * @param schoolCode
	 */
	public void setSchoolCode(String schoolCode) {this.schoolCode = schoolCode;}

	/**
	 * accessor to get the index numbers belonging to the course
	 * @return ArrayList of the CouseIndex object containing information of each index number
	 */
	public ArrayList<CourseIndex> getCourseIndexList() {return courseIndexList;}

	/**
	 * mutator to set the ArrayList of CourseIndex objects belonging to this course
	 * @param courseIndexList
	 */
	public void setCourseIndexList(ArrayList<CourseIndex> courseIndexList) {this.courseIndexList = courseIndexList;}


}

