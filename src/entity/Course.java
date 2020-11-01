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
	
	/*
	 *  TODO: get total vacancy and class size from its indices
	 */
	public int getTotalSize() {
		return 0;
	}
	
	public int getTotalVacancy() {
		return 0;
	}
	
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

