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
	
	public Course(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public Course(ArrayList<CourseIndex> courseIndexList) {
		this.courseIndexList = courseIndexList;
	}
	/*
	 * TODO: get and set methods
	 */
	
	public String getCourseCode() {return courseCode;}

	public void setCourseCode(String courseCode) {this.courseCode = courseCode;}

	public String getSchoolCode() {return schoolCode;}

	public void setSchoolCode(String schoolCode) {this.schoolCode = schoolCode;}

	public ArrayList<CourseIndex> getCourseIndexList() {return courseIndexList;}

	public void setCourseIndexList(ArrayList<CourseIndex> courseIndexList) {this.courseIndexList = courseIndexList;}

	
	/*
	 *  get total vacancy and class size from its indices
	 */
	public int getTotalVacancy() {
		int totalVacancy = 0;
		for(int i = 0; i < this.courseIndexList.size(); i++) {
			totalVacancy += courseIndexList.get(i).getVacancy();
		}
		return totalVacancy;
	}
	
	public void addCourseVacancy(int addedVacancy) {
		// divide all added vacancy to its indices. Create more slots
		int n = courseIndexList.size();
		int r = addedVacancy % n;
		int d = addedVacancy / n;
		for(int i = 0; i < n; i ++ ) {
			CourseIndex ci = this.courseIndexList.get(i);
			int current_index_vacancy = ci.getVacancy();
			if (i < r) {
				ci.setVacancy(current_index_vacancy + d + 1);
			} else {
				ci.setVacancy(current_index_vacancy + d);
			}
			this.courseIndexList.set(i, ci);
		}
	}

}

