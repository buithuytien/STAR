package entity;
import java.util.ArrayList;

public class Course {
	
	private String courseCode;
	private String schoolCode;
	private ArrayList<CourseIndex> courseIndexList = new ArrayList<CourseIndex>() ;
	
	/*
	 * Constructors
	 */
	public Course() {}
	
	public Course(ArrayList<CourseIndex> courseIndexList) {
		this.courseIndexList = courseIndexList;
	}
	/*
	 * TODO: get and set methods
	 */
	public int getTotalSize() {
		return 0;
	}
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public ArrayList<CourseIndex> getCourseIndexList() {
		return courseIndexList;
	}

	public void setCourseIndexList(ArrayList<CourseIndex> courseIndexList) {
		this.courseIndexList = courseIndexList;
	}

	public int getTotalVacancy() {
		return 0;
	}
	/*
	 *  TODO: get total vacancy and class size from its indices
	 */
	
	
	/*
	 *  TODO: add and remove course index
	 */
	public ArrayList<CourseIndex> addCourseIndex(CourseIndex ci){
		return null;
	}
	
	public ArrayList<CourseIndex> removeCourseIndex(CourseIndex ci){
		return null;
	} 

}

