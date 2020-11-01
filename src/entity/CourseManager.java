package entity;
import java.util.ArrayList;

public class CourseManager {
	private boolean authorized;
	public ArrayList<Course> courseList = new ArrayList<Course>( );
	
	public String createCourse(String index, String courseCode, String[] date, int vacancy, String venue,  String school) {
		Course course = new Course(index, courseCode, date, vacancy, venue, school);
		courseList.add(course);
		return index;
	}
	
	public String addCourseIndex() {
		return null;
		
	}
	
	public String removeCourseIndex() {
		return null;
		
	}
	
	public String updateCourseCode(Course course, String newCourseCode) { // String course_code
		return null;		
	}
	
	public String updateCourseSchool() {
		return null;
		
	}
	
	public String updateCourseVacancy() {
		return null;
		
	}
	
	
	
	

}
