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
	
	public int getTotalSize() {
		int totalSize = 0;
		for(int i = 0; i < this.courseIndexList.size(); i++) {
			totalSize += courseIndexList.get(i).getTotalSize();
		}
		return totalSize;
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
	
	public void setCourseVacancy(int newVacancy) {
		// if students already register to course, cannot reduce vacancy
		// TODO: implement
		
	}
	
	
	/*
	 *  add and remove course index
	 */
	public void addCourseIndex(CourseIndex ci){
		int i = this.courseIndexList.indexOf(ci); // location of course index ci in course index list		
		if(i > -1) { // if course index already present
			System.out.println("Course index is already present. Do nothing");
			return;
		} 
		this.courseIndexList.add(ci);			
	}
	
	public ArrayList<CourseIndex> removeCourseIndex(CourseIndex ci){
		int i = this.courseIndexList.indexOf(ci); // location of course index ci in course index list
		if(i == -1) { // if course index not added
			System.out.println("Course index is not present. Do nothing");
			return this.courseIndexList;
		}
		this.courseIndexList.remove(i);		
		return null;
	} 
	
	public String[] toStringDB() {
		String[] s = new String[3]; // course code, index list and school code
		String indices = "";
		// get string of indices: 10001;10002;10003 ...
		for(int i = 0; i < this.courseIndexList.size(); i++ ) {
			if(i < this.courseIndexList.size()-1 ) {
				indices += this.courseIndexList.get(i).getIndex() + ";";
			} else {
				indices += this.courseIndexList.get(i).getIndex();
			}
		}
		s[0] = this.courseCode;
		s[1] = indices;
		s[2] = this.schoolCode;
		return s;
	}

}

